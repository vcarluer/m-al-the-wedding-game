package gamers.associate.malwg;

public class GameItemSprite extends GameItem {
	private float referenceWidth;
	private float referenceHeight;
	private float textureWidth;
	private float textureHeight;
	public int speed;
	public int speedV;
	
	public GameItemSprite(
			String spritePath, 
			float x, 
			float y, 
			float width, 
			float height, 
			float referenceWidth, 
			float referenceHeight, 
			float textureWidth, 
			float textureHeight) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.referenceWidth = referenceWidth;
		this.referenceHeight = referenceHeight;
		this.textureWidth = textureWidth;
		this.textureHeight = textureHeight;
		this.setSprite(spritePath);
	}
	
	@Override
	protected float getRefereceWidth() {
		return referenceWidth;
	}

	@Override
	protected float getReferenceHeight() {
		return referenceHeight;
	}

	@Override
	protected float getTextureWidth() {
		return textureWidth;
	}

	@Override
	protected float getTextureHeight() {
		return textureHeight;
	}

}
