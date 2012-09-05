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
	
	public BeforePlay(MiniGame game, String backPath, String title) {
		this.backPath = backPath;
		this.title = title;
		this.game = game;
		
		this.batch = new SpriteBatch();
		this.backSprite = Assets.getBackSprite(this.backPath);
		this.font = Assets.getNewFont();
	}
	
	@Override
	public void render(float delta) {
		this.batch.begin();
		this.backSprite.draw(this.batch);
		this.font.draw(this.batch, this.title, Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 3f);
		this.batch.end();
		
		if (Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
			this.game.startGame();
		}
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
