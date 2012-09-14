package gamers.associate.malwg.screens;

import gamers.associate.malwg.Assets;
import gamers.associate.malwg.Malwg;
import gamers.associate.malwg.MiniGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BeforePlay implements Screen {
	private String backPath;
	private String title;
	
	private SpriteBatch batch;
	private Sprite backSprite;
	private BitmapFont font;
	private MiniGame game;
	
	private int startWidth;
	private int startHeight;
	
	public BeforePlay(MiniGame game, String backPath, String title) {
		this.backPath = backPath;
		this.title = title;
		this.game = game;
		
		this.batch = new SpriteBatch();
		this.backSprite = Assets.getBackSprite(this.backPath);
		this.backSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.font = Assets.getNewFont();
		
		this.startWidth = Gdx.graphics.getWidth();
		this.startHeight = Gdx.graphics.getHeight();
	}
	
	@Override
	public void render(float delta) {
		this.batch.begin();
		this.backSprite.draw(this.batch);
		this.font.draw(this.batch, this.title, this.startWidth / 2f, this.startHeight / 2f);
		this.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
