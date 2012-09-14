package gamers.associate.malwg.game;

import gamers.associate.malwg.Assets;
import gamers.associate.malwg.GameItemSprite;
import gamers.associate.malwg.GameState;
import gamers.associate.malwg.GameType;
import gamers.associate.malwg.Malwg;
import gamers.associate.malwg.MiniGame;

public class Lvl3 extends MiniGame {
	private static final String HAND = "data/lvl3/hand_34_64.png";
	private GameItemSprite hand;
	private float startX;
	private float startY;
	private static float size = 128;
	
	public Lvl3() {
		Assets.addTexture(HAND);
		this.startY = this.get0y(size);
		this.startX = - Malwg.WIDTH / 3f;
		this.hand = new GameItemSprite(HAND, startX, startY, size, size, 34, 64, 64, 64);
		this.hand.speed = 300;
		this.stage.addActor(hand);
	}
	
	@Override
	protected GameType getGameType() {
		return GameType.MATHIEU;
	}

	@Override
	protected String getTitle() {
		return "Jeux video";
	}

	@Override
	public MiniGame getNextLevel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void action() {
		if ((this.hand.x > - Malwg.WIDTH / 3f + size) && (this.hand.x < Malwg.WIDTH / 3f - size)) {
			this.win();
		}
	}

	@Override
	protected void initLvl() {
		this.hand.x = this.startX;
		this.hand.y = this.startY;
	}

	@Override
	protected String getMusic() {
		return "data/lvl3/greenhill.ogg";
	}

	@Override
	protected String getBackground() {
		return "data/lvl3/fond.png";
	}

	@Override
	public void render(float delta) {
		if (this.state == GameState.RUNNING) {
			this.move(this.hand, delta);
		}
		
		super.render(delta);
	}
	
}
