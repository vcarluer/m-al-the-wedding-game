package gamers.associate.malwg.game;

import com.badlogic.gdx.Screen;

import gamers.associate.malwg.GameType;
import gamers.associate.malwg.MiniGame;

public class Lvl1 extends MiniGame {

	@Override
	protected GameType getGameType() {
		return GameType.MATHIEU;
	}

	@Override
	protected String getTitle() {
		return "Bretagne";
	}

	@Override
	public MiniGame getNextLevel() {
		return new Lvl2();
	}

	
	float totalDelta;
	@Override
	public void render(float delta) {
		super.render(delta);
		this.totalDelta += delta;
		this.batch.begin();
		this.font.draw(this.batch, "yopy", 50, 50);
		this.batch.end();
	}

	@Override
	public boolean keyUp(int keycode) {
		boolean ret = super.keyUp(keycode);
		
		if (!ret) {
			if (this.totalDelta > 2) {
				this.win();
				ret = true;
			} else {
				this.lose();
				ret = true;
			}
		}
		
		return ret;
		
		
//		return false;
	}

	@Override
	protected void initGame() {
		this.totalDelta = 0;
	}
}
