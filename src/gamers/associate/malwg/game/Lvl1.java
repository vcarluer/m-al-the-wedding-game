package gamers.associate.malwg.game;

import gamers.associate.malwg.Assets;
import gamers.associate.malwg.GameItemSprite;
import gamers.associate.malwg.GameState;
import gamers.associate.malwg.GameType;
import gamers.associate.malwg.MiniGame;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Lvl1 extends MiniGame {
	private static int GOAL = 1;
	private static final int crepiere_offset = 20;
	private static final String DATA_LVL1_BRETAGNE_OGG = "data/lvl1/bretagne.ogg";
	private static final String DATA_LVL1_FLAGGE_BRETAGNE_PNG = "data/lvl1/flagge-bretagne.png";
	private static final String DATA_LVL1_CREPIERE_PNG = "data/lvl1/crepiere.png";
	private static final String DATA_LVL1_CREPE_PNG = "data/lvl1/crepe.png";
	
	private GameItemSprite crepiere;
	private List<GameItemSprite> crepes;
	
	public Lvl1() {
		Assets.addTexture(DATA_LVL1_CREPE_PNG);
		Assets.addTexture(DATA_LVL1_CREPIERE_PNG);
		Assets.addTexture(DATA_LVL1_FLAGGE_BRETAGNE_PNG);		
		
		this.crepiere = new GameItemSprite(DATA_LVL1_CREPIERE_PNG, 0, - Gdx.graphics.getHeight() / 2f + 150, 128, 30, 64, 15, 64, 64);
		this.crepiere.speed = 500;
		this.stage.addActor(this.crepiere);
		
		this.crepes = new ArrayList<GameItemSprite>();
	}
	
	private void newCrepe() {
		float x = (float) (- Gdx.graphics.getWidth() / 2f + 100 + (Math.random() * (Gdx.graphics.getWidth() - 200)));
		GameItemSprite crepe = new GameItemSprite(DATA_LVL1_CREPE_PNG, x, Gdx.graphics.getHeight() / 2f + 30, 64, 15, 64, 15, 64, 64);
		
		crepe.speed = (int) (- 150 + Math.random() * 250) + 250;
		this.crepes.add(crepe);
		this.stage.addActor(crepe);
	}
	
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
	float crepeDelta;
	@Override
	public void render(float delta) {
		super.render(delta);
		this.totalDelta += delta;
		this.crepeDelta += delta;
		float x = this.crepiere.x;
		x += this.direction * this.crepiere.speed * delta;
		this.crepiere.x = x;
		
		this.crepesGravity(delta);
		
		if (this.crepeDelta > 1.5f) {
			this.newCrepe();
			this.crepeDelta = 0;
		}

	}

	private int crepeLoose;
	private int crepeWin;
	
	private ArrayList<GameItemSprite> crepesWin = new ArrayList<GameItemSprite>();
	private void crepesGravity(float delta) {
		ArrayList<GameItemSprite> toDelete = new ArrayList<GameItemSprite>();
		ArrayList<GameItemSprite> toDeleteWin = new ArrayList<GameItemSprite>();
		for(GameItemSprite crepe : this.crepes) {
			crepe.y -= crepe.speed * delta;
			
			if ((Math.abs(this.crepiere.x + crepiere_offset - crepe.x) < 50) && (Math.abs(this.crepiere.y - crepe.y) < 10)) {
				this.crepeWin++;
				toDeleteWin.add(crepe);
				if (this.crepeWin >= GOAL) {
					this.win();
					break;
				}
			}
		
			if (crepe.y <= - Gdx.graphics.getHeight() / 2f) {
				this.crepeLoose++;
				toDelete.add(crepe);
				if (crepeLoose > 3) {
					this.lose();
					break;
				}
				
				
			}
		}
		
		for(GameItemSprite crepe: toDelete) {
			this.crepes.remove(crepe);
			this.stage.removeActor(crepe);
		}
		
		for(GameItemSprite crepe: toDeleteWin) {
			this.crepes.remove(crepe);
			this.crepesWin.add(crepe);
		}
		
		for(GameItemSprite crepe : this.crepesWin) {
			crepe.setPosition(this.crepiere.x + crepiere_offset, this.crepiere.y);
		}
	}

	@Override
	public boolean keyUp(int keycode) {
		boolean ret = super.keyUp(keycode);
		
		if (!ret) {
			if ((keycode == Keys.RIGHT && this.direction == 1) || (keycode == Keys.LEFT && this.direction == -1)) {
				direction = 0;
				return true;
			}
		}
		
		return ret;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (this.state == GameState.RUNNING) {
			if (keycode == Keys.LEFT) {
				direction = -1;
				return true;
			}
			
			if (keycode == Keys.RIGHT) {
				direction = 1;
				return true;
			}
		}
		
		return false;
	}
	
	private int direction;

	@Override
	protected void initLvl() {
		this.totalDelta = 0;
		this.crepiere.setPosition(0, - Gdx.graphics.getHeight() / 2f + 150);
		for(GameItemSprite crepe : this.crepes) {
			this.stage.removeActor(crepe);
		}
		
		for(GameItemSprite crepe : this.crepesWin) {
			this.stage.removeActor(crepe);
		}
		
		this.crepes.clear();
		this.crepesWin.clear();
		this.crepeLoose = 0;
		this.crepeWin = 0;
		this.direction = 0;
	}

	@Override
	protected String getMusic() {
		return DATA_LVL1_BRETAGNE_OGG;
	}

	@Override
	protected String getBackground() {
		return DATA_LVL1_FLAGGE_BRETAGNE_PNG;
	}
}
