package gamers.associate.malwg.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Title implements Screen {
	private SpriteBatch batch;
	private Sprite sprite;
	
	public Title() {
		this.batch = new SpriteBatch();
		this.sprite = new Sprite(new TextureRegion(new Texture(Gdx.files.internal("data/title.png")), 0, 0, 800, 600));
		this.sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public void render(float delta) {
		this.batch.begin();
		this.sprite.draw(this.batch);
		this.batch.end();
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
