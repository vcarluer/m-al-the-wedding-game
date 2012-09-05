package gamers.associate.malwg.game;

import com.badlogic.gdx.Screen;

import gamers.associate.malwg.GameType;
import gamers.associate.malwg.MiniGame;

public class Lvl1 extends MiniGame {

	@Override
	protected GameType getGameType() {
		return GameType.Mathieu;
	}

	@Override
	protected String getTitle() {
		return "Bretagne";
	}

	@Override
	public MiniGame getNextLevel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Screen getGameScreen() {
		return new Lvl1Screen();
	}
}
