package gamers.associate.malwg.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import gamers.associate.malwg.GameType;
import gamers.associate.malwg.MiniGame;

public class Lvl2 extends MiniGame {

	@Override
	public void render(float delta) {
		super.render(delta);
		this.batch.begin();
		this.font.draw(this.batch, "lvl2", 50, 50);
		this.batch.end();
	}

	@Override
	public boolean keyUp(int keycode) {
		return super.keyUp(keycode);
	}

	@Override
	protected GameType getGameType() {
		return GameType.ANNELAURE;
	}

	@Override
	protected String getTitle() {
		return "Lagnieu";
	}

	@Override
	public MiniGame getNextLevel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void initGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void drawBack(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

}
