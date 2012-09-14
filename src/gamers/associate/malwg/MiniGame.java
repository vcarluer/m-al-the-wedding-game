package gamers.associate.malwg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
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
		
		String text = this.getBackground();
		if (text != null) {
			Assets.addTexture(text);
			this.bkg = new Sprite(Assets.getTexture(text));
			this.bkg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
	
	@Override
	public void render(float delta) {
		if (this.state == GameState.WIN) {
			Malwg.get().playNextGame();
			return;
		}
		
		if (this.state == GameState.LOSE) {
			this.restart();
			return;
		}
		
		this.stage.act(delta);
		this.batch.begin();
		this.drawBack(this.batch);
		this.batch.end();
		this.stage.draw();
		
		// hud
		this.batch.begin();
		this.batch.draw(this.heart, 10, Gdx.graphics.getHeight() - 60, 50, 50, 0, 0, 32, 32, false, false);
		this.batch.draw(this.heart, 70, Gdx.graphics.getHeight() - 60, 50, 50, 0, 0, 32, 32, false, false);
		this.batch.draw(this.heart, 130, Gdx.graphics.getHeight() - 60, 50, 50, 0, 0, 32, 32, false, false);
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
		
		return false;
	}
	@Override
	public boolean keyDown(int keycode) {			
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
}
