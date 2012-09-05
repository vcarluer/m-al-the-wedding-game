package gamers.associate.malwg;

import com.badlogic.gdx.Screen;

import gamers.associate.malwg.screens.BeforePlay;

public abstract class MiniGame {
	protected String title; 
	protected GameType gameType;
	protected BeforePlay beforeScreen;
	protected GameState state;
	protected Screen gameScreen;
	
	public MiniGame() {
		this.title = this.getTitle();
		this.gameType = this.getGameType();
		this.state = GameState.INTRO;
		this.beforeScreen = new BeforePlay(this, this.getBackPath(), this.title);
		this.gameScreen = this.getGameScreen();
	}
	
	protected abstract Screen getGameScreen();

	public void play() {
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
		case AnneLaure:
			return Assets.DATA_ANNELAURE;
		case Mathieu:
			return Assets.DATA_MATHIEU;
		case Both:
		default:
			return Assets.DATA_BOTH;
		}
	}
	
	public Screen getScreen() {
		switch (this.state) {
		case INTRO:
			return this.beforeScreen;
		default:
			return this.gameScreen;
		}
	}

	public abstract MiniGame getNextLevel();

	public void startGame() {
		this.state = GameState.RUNNING;
		this.setScreen();
	}
}
