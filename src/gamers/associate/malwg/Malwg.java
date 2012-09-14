package gamers.associate.malwg;

import gamers.associate.malwg.game.Lvl3;
import gamers.associate.malwg.screens.Title;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

public class Malwg extends Game {

	public static final String TITLE = "Mathieu / Anne-Laure - The wedding game";
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private static Malwg game;
	
	private MiniGame miniGame; 

	public void create() {
		Assets.load();
		this.setScreen(new Title());
	}

	public static Malwg get() {
		if (game == null) {
			game = new Malwg();
		}
		
		return game;
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		super.render();
	}
	
	public MiniGame getCurrentGame() {
		return this.miniGame;
	}
	
	public void playGame() {
		if (this.miniGame != null) {
			this.miniGame.play();
		}		
	}
	
	public void setCurrentScreen(Screen screen) {
		this.setScreen(screen);
	}
	
	public void playNextGame() {
		if (this.miniGame != null) {
			this.miniGame.endGame();
		}
		this.miniGame = this.getNextGame();
		this.playGame();
	}
	
	private MiniGame getNextGame() {
		if (this.miniGame == null) {
			return new Lvl3();
		} else {
			return this.miniGame.getNextLevel();
		}
		
	}

	@Override
	public void resize(int width, int height) {
		if (this.miniGame != null) {
			
		}
		
		super.resize(width, height);
	}
}
