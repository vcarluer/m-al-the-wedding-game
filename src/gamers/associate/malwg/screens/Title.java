package gamers.associate.malwg.screens;

import gamers.associate.malwg.Assets;
import gamers.associate.malwg.Malwg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Title implements Screen {
	private SpriteBatch batch;
	private Sprite sprite;
	private BitmapFont font;
	private static String insert = "INSERT LOVE";
	
	public Title() {
		this.batch = new SpriteBatch();
		this.sprite = Assets.getTitleSprite();
		this.sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.font = Assets.getNewFont();
	}
	
	public void render(float delta) {
		this.batch.begin();
		this.sprite.draw(this.batch);
		this.font.draw(this.batch, insert, Gdx.graphics.getWidth() / 2f - 100, Gdx.graphics.getHeight() / 3f);
		this.batch.end();
		
		if (Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
			Malwg.get().playNextGame();
		}
	}

	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	public void show() {
		// TODO Auto-generated method stub
		
	}

	public void hide() {
		// TODO Auto-generated method stub
		
	}

	public void pause() {
		// TODO Auto-generated method stub
		
	}

	public void resume() {
		// TODO Auto-generated method stub
		
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
