package gamers.associate.malwg;

import gamers.associate.malwg.screens.Title;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;

public class Malwg extends Game {

	public static final String TITLE = "Mathieu / Anne-Laure - The wedding game";
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private static Malwg game;

	public void create() {
		this.setScreen(new Title());
	}

	public static ApplicationListener get() {
		if (game == null) {
			game = new Malwg();
		}
		
		return game;
	}
}
