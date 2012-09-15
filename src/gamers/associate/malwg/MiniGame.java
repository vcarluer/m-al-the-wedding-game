package gamers.associate.malwg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import gamers.associate.malwg.screens.BeforePlay;

public abstract class MiniGame implements Screen, InputProcessor {
	private static String HEART = "data/heart.png";
	private Texture heart;
	protected String title; 
	protected GameType gameType;
	protected BeforePlay beforeScreen;
	protected GameState state;
	
	protected SpriteBatch batch;
	protected BitmapFont font;
	
	protected Stage stage;
	
	private OrthographicCamera cam;
	private Sprite bkg;
	
	private int startWidth;
	private int startHeight;
	
	protected int direction;
	protected int directionV;
	
	protected boolean disableMoveKeys;
	
	public MiniGame() {
		this.batch = new SpriteBatch();
		this.font = Assets.getNewFont();
		
		this.title = this.getTitle();
		this.gameType = this.getGameType();
		this.state = GameState.INTRO;
		this.beforeScreen = new BeforePlay(this, this.getBackPath(), this.title);
		
		this.cam = new OrthographicCamera(Malwg.WIDTH, Malwg.HEIGHT);
		this.cam.position.set(0, 0, 0);
		this.cam.zoom = this.getZoom();
		
		this.stage = new Stage(Malwg.WIDTH, Malwg.HEIGHT, false);
		this.stage.setCamera(this.cam);
		
		if (getMusic() != null) {
			Assets.addSound(getMusic());
		}		
		
		Assets.addTexture(HEART);
		this.heart = Assets.getTexture(HEART);
		
		this.startWidth = Gdx.graphics.getWidth();
		this.startHeight = Gdx.graphics.getHeight();
		
		String text = this.getBackground();
		if (text != null) {
			Assets.addTexture(text);
			this.bkg = new Sprite(Assets.getTexture(text));
			this.bkg.setSize(this.startWidth, this.startHeight);
		}		
	}

	private float getZoom() {
		return 1;
	}

	public void play() {
		this.initGame();
		Gdx.input.setInputProcessor(this);
		this.state = GameState.INTRO;
		this.setScreen();
	}
	
	protected void setScreen() {
		Malwg.get().setCurrentScreen(this.getScreen());
	}
	
	protected abstract GameType getGameType();
	protected abstract String getTitle();
	
	protected String getBackPath() {
		switch (this.gameType) {
		case ANNELAURE:
			return Assets.DATA_ANNELAURE;
		case MATHIEU:
			return Assets.DATA_MATHIEU;
		case BOTH:
		default:
			return Assets.DATA_BOTH;
		}
	}
	
	public Screen getScreen() {
		switch (this.state) {
		case INTRO:
			return this.beforeScreen;
		default:
			return this;
		}
	}

	public abstract MiniGame getNextLevel();

	public void startGame() {
		this.state = GameState.RUNNING;
		this.setScreen();
	}
	
	protected float totalDelta;
	protected boolean lastLevel;
	
	@Override
	public void render(float delta) {
		if (this.state == GameState.LOSE) {
			this.restart();
			return;
		}
		
		this.totalDelta += delta;
		this.stage.act(delta);
		this.batch.begin();
		this.drawBack(this.batch);
		this.batch.end();
		this.stage.draw();
		
		// hud
		this.batch.begin();
		this.batch.draw(this.heart, this.getScaledW(10), this.startHeight - this.getScaledW(60), this.getScaledW(50), this.getScaledW(50), 0, 0, 32, 32, false, false);
		this.batch.draw(this.heart, this.getScaledW(70), this.startHeight - this.getScaledW(60), this.getScaledW(50), this.getScaledW(50), 0, 0, 32, 32, false, false);
		this.batch.draw(this.heart, this.getScaledW(130), this.startHeight - this.getScaledW(60), this.getScaledW(50), this.getScaledW(50), 0, 0, 32, 32, false, false);
		
		
		if (this.state == GameState.WIN) {
			this.font.setScale(2);		
			String txt = "YOU WIN!";
			if (this.lastLevel) {
				txt = "CONGRATULATION!";
			}
			this.font.draw(batch, txt, this.startWidth / 2f - 200, this.startHeight / 2f);
		}
		
		this.batch.end();
	}
	
	protected void drawBack(SpriteBatch batch) {
		if (this.bkg != null) {
			this.bkg.draw(batch);
		}		
	}

	@Override
	public boolean keyUp(int keycode) {		
		if (this.state == GameState.INTRO) {
			this.startGame();
			return true;
		}
		
		if (this.state == GameState.WIN) {
			if (!this.lastLevel) {
				Malwg.get().playNextGame();
				return true;
			}
			
		}
		
		if (!this.disableMoveKeys) {
			if ((keycode == Keys.RIGHT && this.direction == 1) || (keycode == Keys.LEFT && this.direction == -1)) {
				direction = 0;
				return true;
			}
			
			if ((keycode == Keys.UP && this.directionV == 1) || (keycode == Keys.DOWN && this.directionV == -1)) {
				directionV = 0;
				return true;
			}
		}
		
		
		if (keycode == Keys.SPACE) {
			this.action();
			return true;
		}
		
		return false;
	}
	protected abstract void action();

	@Override
	public boolean keyDown(int keycode) {
		if (this.state == GameState.RUNNING && !this.disableMoveKeys) {
			if (keycode == Keys.LEFT) {
				direction = -1;
				return true;
			}
			
			if (keycode == Keys.RIGHT) {
				direction = 1;
				return true;
			}
			
			if (keycode == Keys.UP) {
				directionV = 1;
				return true;
			}
			
			if (keycode == Keys.DOWN) {
				directionV = -1;
				return true;				
			}
		}
		
		return false;
	}
	
	private void restart() {		
		this.play();
	}
	
	protected void initGame() {
		if (getMusic() != null) {
			Assets.getSound(getMusic()).stop();
			Assets.getSound(getMusic()).play();
		}
		
		this.direction = 0;
		this.directionV = 0;
		
		this.totalDelta = 0;
		this.initLvl();
	}
	
	protected abstract void initLvl();

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}	

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	protected void win() {
		this.state = GameState.WIN;
	}
	
	protected void lose() {
		this.state = GameState.LOSE;
	}

	public void endGame() {
		if (getMusic() != null) {
			Assets.getSound(getMusic()).stop();
		}
	}
	
	protected abstract String getMusic();
	
	protected abstract String getBackground();
	
	protected float getScaledW(float width) {
		return width * (this.startWidth / Malwg.WIDTH);
	}
	
	protected float getScaledH(float height) {
		return height * (this.startHeight / Malwg.HEIGHT);
	}
	
	protected void move(GameItemSprite item, float delta) {
		float x = item.x;
		x += this.direction * item.speed * delta;
		if ((x + (item.width / 2f) < Malwg.WIDTH / 2f) && ((x - item.width / 2f) > - Malwg.WIDTH / 2f)) {
			item.x = x;
		}
		
		
		float y = item.y;
		y += this.directionV * item.speedV * delta;
		
		if ((y + (item.height / 2f) < Malwg.HEIGHT / 2f) && ((y - item.height / 2f) > - Malwg.HEIGHT / 2f)) {
			item.y = y;
		}
	}
	
	protected float get0x(float width) {
		return - Malwg.WIDTH / 2f + width / 2f;
	}
	
	protected float get0y(float height) {
		return - Malwg.HEIGHT / 2f + height / 2f;
	}
}
