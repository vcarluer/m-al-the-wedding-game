package gamers.associate.malwg.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import gamers.associate.malwg.Assets;
import gamers.associate.malwg.GameItemSprite;
import gamers.associate.malwg.GameType;
import gamers.associate.malwg.MiniGame;

public class Lvl2 extends MiniGame {
	private static String MUSIC = "data/lvl2/mylove.ogg";
	private static String AGNEAU = "data/lvl2/blason_34_40.png";
	private static String BLASON = "data/lvl2/fond_blason_58_64.png";
	private static String BLOCK = "data/lvl2/block.png";
	
	private GameItemSprite agneau;
	private GameItemSprite blason;
	private List<GameItemSprite> blocks;
	
	private static int BLOCK_COUNT = 6;
	
	public Lvl2() {
		Assets.addTexture(AGNEAU);
		Assets.addTexture(BLASON);
		Assets.addTexture(BLOCK);
		
		this.blocks = new ArrayList<GameItemSprite>();
		
		this.agneau = new GameItemSprite(AGNEAU, 100, 100, 34, 40, 34, 40, 64, 64);
		this.blason = new GameItemSprite(BLASON, 100, 100, 58, 64, 58, 64, 64, 64);
		for (int i = 0; i < BLOCK_COUNT; i++) {
			GameItemSprite block = new GameItemSprite(BLOCK, 0, 0, 64, 64, 16, 16, 16, 16);
			this.stage.addActor(block);
		}
		
		this.stage.addActor(this.blason);
		this.stage.addActor(agneau);		
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		this.batch.begin();
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
	protected void initLvl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getMusic() {
		return MUSIC;
	}

	@Override
	protected String getBackground() {
		// TODO Auto-generated method stub
		return null;
	}

}
