package gamers.associate.malwg.screens;

import gamers.associate.malwg.Assets;
import gamers.associate.malwg.Malwg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Title implements Screen, InputProcessor {
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
		this.font.draw(this.batch, insert, Malwg.WIDTH / 2f - 100, Malwg.HEIGHT / 3f);
		this.batch.end();
	}

	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	public void show() {
		Gdx.input.setInputProcessor(this);		
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

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		Malwg.get().playNextGame();
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
