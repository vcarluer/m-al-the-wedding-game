package gamers.associate.malwg;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class GameItem extends Actor {
	protected Sprite sprite;
	protected Rectangle boundingBox;
	protected Texture baseTexture;
	
	public GameItem() {
		this.boundingBox = new Rectangle();
		this.width = this.getRefereceWidth();
		this.height = this.getReferenceHeight();
	}
	
	protected abstract float getRefereceWidth();
	protected abstract float getReferenceHeight();
	
	protected abstract float getTextureWidth();
	protected abstract float getTextureHeight();
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (this.sprite != null) {
			this.sprite.setPosition(this.x - this.width / 2f, this.y - this.height / 2f);
			this.sprite.setSize(this.width, this.height);
			this.sprite.setRotation(this.rotation);
			this.sprite.setScale(this.scaleX, this.scaleY);
			this.sprite.setColor(this.color);
			this.sprite.draw(batch);
		}
	}

	@Override
	public Actor hit(float x, float y) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected void setSprite(String path) {
		int srcX = (int) ((this.getTextureWidth() - this.getRefereceWidth()) / 2);
		int srcY = (int) ((this.getTextureHeight() - this.getReferenceHeight()) / 2);
		this.baseTexture = Assets.getTexture(path);
		this.sprite = new Sprite(this.baseTexture, srcX, srcY, (int) this.getRefereceWidth(), (int) this.getReferenceHeight());
	}
	
	public Rectangle getBoundingBox() {
		return this.getBoundingBox(this.x, this.y);
	}
	
	public Rectangle getBoundingBox(float x, float y) {
		this.boundingBox.x = x - this.width / 2f;
		this.boundingBox.y = y - this.height / 2f;
		this.boundingBox.width = this.width;
		this.boundingBox.height = this.height;
		return this.boundingBox;
	}
	
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
}
