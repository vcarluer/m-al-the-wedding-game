package gamers.associate.malwg.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.OnActionCompleted;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleTo;

import gamers.associate.malwg.Assets;
import gamers.associate.malwg.GameItemSprite;
import gamers.associate.malwg.GameState;
import gamers.associate.malwg.GameType;
import gamers.associate.malwg.Malwg;
import gamers.associate.malwg.MiniGame;

public class Lvl5 extends MiniGame {
	private static final String MAKI_PNG = "data/lvl5/maki.png";
	private static final String BAGUETTES = "data/lvl5/baguettes_128_91.png";
	private static final int makiCount = 7;
	private static final float offsety_baguettes = -25;
	private List<GameItemSprite> makis;
	private GameItemSprite baguettes;
	
	public Lvl5() {
		Assets.addTexture(BAGUETTES);
		Assets.addTexture(MAKI_PNG);
		this.makis = new ArrayList<GameItemSprite>();
		
		float scale = 1.5f;
		this.baguettes = new GameItemSprite(BAGUETTES, 0, 0, 128 * scale, 91 * scale, 128, 91, 128, 128);
		this.baguettes.speed = 300;
		this.baguettes.speedV = 300;
		
		
		for (int i = 0; i < makiCount; i++) {
			
			float x = (float) (Math.random() * (Malwg.WIDTH - 400)) - (Malwg.WIDTH / 2f - 200) ;
			float y = (float) (Math.random() * (Malwg.HEIGHT - 400)) - (Malwg.HEIGHT / 2f - 200);
			GameItemSprite maki = new GameItemSprite(MAKI_PNG, x, y, 64, 64, 64, 64, 64, 64);
			this.makis.add(maki);
			this.stage.addActor(maki);
		}
		
		this.stage.addActor(this.baguettes);
	}
	
	@Override
	public void render(float delta) {
		if (this.state == GameState.RUNNING) {
			this.move(baguettes, delta);
			ArrayList<GameItemSprite> toDelete = new ArrayList<GameItemSprite>();
			for (GameItemSprite maki : makis) {
				if (Math.abs(maki.x - (baguettes.x - baguettes.width / 2f)) < 15 && Math.abs(maki.y - (baguettes.y + offsety_baguettes)) < 15) {
					toDelete.add(maki);
				}
			}
			
			for (GameItemSprite maki : toDelete) {
				makis.remove(maki);
				ScaleTo st = ScaleTo.$(2, 2, 0.3f);
				st.setCompletionListener(new OnActionCompleted() {
					
					@Override
					public void completed(Action action) {
						stage.removeActor(action.getTarget());
					}
				});
				
				maki.action(st);
			}
			
			if (makis.size() == 0) {
				this.win();
			}
		}
		super.render(delta);
	}

	@Override
	protected GameType getGameType() {
		return GameType.BOTH;
	}

	@Override
	protected String getTitle() {
		return "Voyage au japon";
	}

	@Override
	public MiniGame getNextLevel() {
		// TODO Auto-generated method stub
		return null;
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
		return "data/lvl5/music.ogg";
	}

	@Override
	protected String getBackground() {
		return "data/lvl5/fond.png";
	}

}
