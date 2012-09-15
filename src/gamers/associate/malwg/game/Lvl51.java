package gamers.associate.malwg.game;

import gamers.associate.malwg.Assets;
import gamers.associate.malwg.GameItemSprite;
import gamers.associate.malwg.GameState;
import gamers.associate.malwg.GameType;
import gamers.associate.malwg.MiniGame;

public class Lvl51 extends MiniGame {
	private static final String COUTNUT = "data/lvl51/cout_nut_128_34.png";
	private static final String TART = "data/lvl51/tart_128_95.png";
	private static final String COUT = "data/lvl51/cout_128_34.png";
	private static final String NUT = "data/lvl51/nut.png";
	private GameItemSprite nut;
	private GameItemSprite cout;
	private GameItemSprite coutnut;
	private GameItemSprite tart;
	private boolean step1ok;
	
	public Lvl51() {
		Assets.addTexture(NUT);
		Assets.addTexture(COUT);
		Assets.addTexture(COUTNUT);
		Assets.addTexture(TART);
		
		int scale = 2;
		this.nut = new GameItemSprite(NUT, -200, -150, 256, 256, 128, 128, 128, 128);
		this.cout = new GameItemSprite(COUT, 0, 0, 128 * scale, 34 * scale, 128, 34, 128, 128);
		int speed = 150;
		this.cout.speed = speed;
		this.cout.speedV = speed;
		this.coutnut = new GameItemSprite(COUTNUT, 0, 0, 128 * scale, 34 * scale, 128, 34, 128, 128);
		this.coutnut.speed = speed;
		this.coutnut.speedV = speed;
		this.tart = new GameItemSprite(TART, 150, 150 , 128 * scale, 95 * scale, 128, 95, 128, 128);
		this.stage.addActor(tart);
		
		this.stage.addActor(nut);
		this.stage.addActor(cout);
	}
	
	@Override
	public void render(float delta) {
		if (this.state == GameState.RUNNING) {
			if (!step1ok) {
				this.move(cout, delta);
				if (Math.abs(nut.x - (cout.x - 128)) < 50 && Math.abs(nut.y + 115 - (cout.y + 70)) < 50) {
					step1ok = true;
					this.stage.removeActor(cout);
					coutnut.x = cout.x;
					coutnut.y = cout.y;
					this.stage.addActor(coutnut);
				}
			} else {
				this.move(coutnut, delta);
				if (Math.abs(tart.x - (coutnut.x - 128)) < 50 && Math.abs(tart.y - (coutnut.y + 70)) < 50) {
					win();
				}
			}
			
		}
		super.render(delta);
	}

	@Override
	protected GameType getGameType() {
		return GameType.MATHIEU;
	}

	@Override
	protected String getTitle() {
		return "NUTELLA!";
	}

	@Override
	public MiniGame getNextLevel() {
		return new Lvl5();
	}

	@Override
	protected void action() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initLvl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getMusic() {
		return "data/lvl51/bob.ogg";
	}

	@Override
	protected String getBackground() {
		return "data/lvl51/fond.png";
	}

}
