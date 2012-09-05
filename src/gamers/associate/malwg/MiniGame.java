package gamers.associate.malwg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import gamers.associate.malwg.screens.BeforePlay;

public abstract class MiniGame implements Screen, InputProcessor {
	protected String title; 
	protected GameType gameType;
	protected BeforePlay beforeScreen;
	protected GameState state;
	
	protected SpriteBatch batch;
	protected BitmapFont font;
	
	public MiniGame() {
		this.batch = new SpriteBatch();
		this.font = Assets.getNewFont();
		
		this.title = this.getTitle();
		this.gameType = this.getGameType();
		this.state = GameState.INTRO;
		this.beforeScreen = new BeforePlay(this, this.getBackPath(), this.title);
	}

	public void play() {
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
		this.initGame();
		this.play();
	}
	
	protected abstract void initGame();

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
}
