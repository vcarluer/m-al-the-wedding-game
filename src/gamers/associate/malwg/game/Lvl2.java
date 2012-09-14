package gamers.associate.malwg.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import gamers.associate.malwg.Assets;
import gamers.associate.malwg.GameItemSprite;
import gamers.associate.malwg.GameState;
import gamers.associate.malwg.GameType;
import gamers.associate.malwg.Malwg;
import gamers.associate.malwg.MiniGame;

public class Lvl2 extends MiniGame {
	private static final String DATA_LVL2_FOND_PNG = "data/lvl2/fond.png";
	private static String MUSIC = "data/lvl2/mylove.ogg";
	private static String AGNEAU = "data/lvl2/blason_34_40.png";
	private static String BLASON = "data/lvl2/fond_blason_58_64.png";
	private static String BLOCK = "data/lvl2/block.png";
	
	private GameItemSprite agneau;
	private GameItemSprite blason;
	private List<GameItemSprite> blocks;
	
	private static int BLOCK_COUNT = 9;
	private int scale;
	private float sax;
	private float say;
	public Lvl2() {
		Assets.addTexture(AGNEAU);
		Assets.addTexture(BLASON);
		Assets.addTexture(BLOCK);
		int blocksize = 128;
		this.blocks = new ArrayList<GameItemSprite>();
		
		scale = 3;
		this.sax = Malwg.WIDTH / 2f - (34 * scale) / 2f;
		this.say = - Malwg.HEIGHT / 2f + (40 * scale) / 2f;
		this.agneau = new GameItemSprite(AGNEAU, sax, say, 34 * scale, 40 * scale, 34, 40, 64, 64);
		this.agneau.speed = 500;
		this.agneau.speedV = 1000;
		this.onGround = true;
		this.blason = new GameItemSprite(BLASON, - Malwg.WIDTH / 2f + (58 * scale) / 2f, - Malwg.HEIGHT / 2f + 3 * blocksize + (64 * scale) / 2f, 58 * scale, 64 * scale, 58, 64, 64, 64);
		
		for (int i = 0; i < BLOCK_COUNT; i++) {
			float x = 0;
			float y = 0;
			switch (i){
			case 0:
				x = this.get0x(blocksize);
				y = this.get0y(blocksize);
				break;
			case 1:
				x = this.get0x(blocksize) + blocksize;
				y = this.get0y(blocksize);
				break;
			case 2:
				x = this.get0x(blocksize) + 2 * blocksize;
				y = this.get0y(blocksize);
				break;
			case 3:
				x = this.get0x(blocksize) + 3 * blocksize;
				y = this.get0y(blocksize);
				break;
			case 4:
				x = this.get0x(blocksize) + 4 * blocksize;
				y = this.get0y(blocksize);
				break;
			case 5:
				x = this.get0x(blocksize);
				y = this.get0y(blocksize) + blocksize;
				break;
			case 6:
				x = this.get0x(blocksize) + blocksize;
				y = this.get0y(blocksize) + blocksize;
				break;
			case 7:
				x = this.get0x(blocksize) + 2 * blocksize;
				y = this.get0y(blocksize) + blocksize;
				break;
			case 8:
				x = this.get0x(blocksize);
				y = this.get0y(blocksize) + 2 * blocksize;
				break;
			case 9:
				x = this.get0x(blocksize) + blocksize;
				y = this.get0y(blocksize) + 2 * blocksize;
				break;
			}
			
			GameItemSprite block = new GameItemSprite(BLOCK, x, y, blocksize, blocksize, 16, 16, 16, 16);
			this.blocks.add(block);
			this.stage.addActor(block);
		}
		
		this.stage.addActor(this.blason);
		this.stage.addActor(agneau);		
	}
	
	private float jumpV;
	private static float gravity = - 42f;
	private boolean onGround;
	
	@Override
	public void render(float delta) {
		if (this.state == GameState.RUNNING) {
				
			if ((Math.abs(this.blason.x - this.agneau.x) < 15) && (Math.abs(this.blason.y - this.agneau.y) < 15)) {
				this.win();
			} else {
				
				float newX = this.agneau.x + this.direction * delta * this.agneau.speed;
				this.jumpV += gravity;
				float newY = this.agneau.y  + this.jumpV * delta;
				boolean contactX = false;
				boolean contactY = false;
				for(GameItemSprite block : this.blocks) {
					if (block.getBoundingBox().overlaps(this.agneau.getBoundingBox(newX, this.agneau.y))) {
						contactX = true;
					}
					
					if (block.getBoundingBox().overlaps(this.agneau.getBoundingBox(this.agneau.x, newY))) {
						contactY = true;
						onGround = true;
						jumpV = 0;
					}
				}
				
				if ((newX + this.agneau.width / 2f) > Malwg.WIDTH / 2f) contactX = true;
				if ((newX - this.agneau.width / 2f) < - Malwg.WIDTH / 2f) contactX = true;
				if ((newY + this.agneau.height / 2f) > Malwg.HEIGHT / 2f) contactY = true;
				if ((newY - this.agneau.height / 2f) < - Malwg.HEIGHT / 2f) {
					contactY = true;
					onGround = true;
				}
				
				if (!contactX) {
					this.agneau.x = newX;
				}
				
				if (!contactY) {
					this.agneau.y = newY;
				}
				
			}
		}
		super.render(delta);
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
	protected void initLvl() {
		this.agneau.x = this.sax;
		this.agneau.y = this.say;
	}

	@Override
	protected String getMusic() {
		return MUSIC;
	}

	@Override
	protected String getBackground() {
		return DATA_LVL2_FOND_PNG;
	}

	@Override
	protected void action() {
		if (onGround) {
			this.jumpV = this.agneau.speedV;;
		}
		
	}

}
