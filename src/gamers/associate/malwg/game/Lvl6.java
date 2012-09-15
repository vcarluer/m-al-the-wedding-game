package gamers.associate.malwg.game;

import sun.java2d.loops.ScaledBlit;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.OnActionCompleted;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleTo;

import gamers.associate.malwg.Assets;
import gamers.associate.malwg.GameItemSprite;
import gamers.associate.malwg.GameState;
import gamers.associate.malwg.GameType;
import gamers.associate.malwg.MiniGame;

public class Lvl6 extends MiniGame {
	private static final String HEART = "data/lvl6/heart.png";
	private static final String AL = "data/lvl6/al.png";
	private static final String MAT = "data/lvl6/mat.png";
	private GameItemSprite mat;
	private GameItemSprite al;
	private float smx;
	private float salx;
	
	private boolean wining;
	private GameItemSprite heart;
	public Lvl6() {
		this.lastLevel = true;
		Assets.addTexture(MAT);
		Assets.addTexture(AL);
		Assets.addTexture(HEART);
		float scale = 2.5f;
		smx = -200;
		this.mat = new GameItemSprite(MAT, smx, 15, 128 * scale, 128 * scale, 128, 128, 128, 128);
		this.stage.addActor(mat);
		salx = 200;
		this.al = new GameItemSprite(AL, salx, 0, 128 * scale, 128 * scale, 128, 128, 128, 128);
		this.stage.addActor(al);
		
		this.disableMoveKeys = true;
		
		this.heart = new GameItemSprite(HEART, 0, 0, 32, 32, 32, 32, 32, 32);
		
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (this.state == GameState.RUNNING && ! wining) {
			if (keycode == Keys.RIGHT) {
				this.al.x -= 4;
			}
			
			if (keycode == Keys.LEFT) {
				this.mat.x += 4;
			}
		}
		return super.keyDown(keycode);
	}

	@Override
	public void render(float delta) {
		if (this.state == GameState.RUNNING) {
			if (!wining) {
				this.mat.x -= 10 * delta;
				this.al.x += 10 *delta;
			}
			
			
			if (this.mat.x < smx) this.mat.x = smx;
			if (this.al.x > salx) this.al.x = salx;
			
			if (Math.abs(this.mat.x - this.al.x) < 220 && wining == false) {
				wining = true;
				this.stage.addActor(this.heart);
				ScaleTo st = ScaleTo.$(10, 10, 3);
				st.setCompletionListener(new OnActionCompleted() {
					
					@Override
					public void completed(Action action) {
						win();
					}
				});
				
				heart.action(st);
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
		return "Love";
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
		return "data/lvl6/lover.ogg";
	}

	@Override
	protected String getBackground() {
		return "data/lvl6/fond.png";
	}

}
