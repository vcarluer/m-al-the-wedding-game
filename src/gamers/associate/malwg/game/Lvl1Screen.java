package gamers.associate.malwg.game;

import gamers.associate.malwg.Assets;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Lvl1Screen implements Screen {
	private SpriteBatch batch;
	private BitmapFont font;
	
	public Lvl1Screen() {
		this.batch = new SpriteBatch();
		this.font = Assets.getNewFont();
	}
	
	@Override
	public void render(float delta) {
		this.batch.begin();
		this.font.draw(this.batch, "yop", 50, 50);
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
