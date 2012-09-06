package gamers.associate.malwg;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static final String DATA_TITLE = "data/title.png";
	public static final String DATA_MATHIEU = "data/mathieuplay.png";
	public static final String DATA_ANNELAURE = "data/annelaureplay.png";
	public static final String DATA_BOTH = "data/both.png";
	
	public static final String DATA_FONT_FNT = "data/ocra.fnt";
	public static final String DATA_FONT_PNG = "data/ocra.png";
	public static final String STR_MATHIEU = "Poupe";
	public static final String STR_ANNELAURE = "Nanou";
	
	private static Map<String, Sound> sounds = new HashMap<String, Sound>();
	private static Map<String, Texture> textures = new HashMap<String, Texture>();
	private static Map<String, Sprite> sprites = new HashMap<String, Sprite>();
	
	public static void load() {		
		addTexture(DATA_TITLE);
		addTexture(DATA_MATHIEU);
		addTexture(DATA_ANNELAURE);
	}
	
	public static void addTexture(String path) {
		textures.put(path, createTexture(path));
	}
	
	public static void addSound(String path) {
		sounds.put(path, createSound(path));
	}
	
	private static Sound createSound(String path) {
		return Gdx.audio.newSound(Gdx.files.internal(path));
	}
	
	private static Texture createTexture(String path) {
		return new Texture(Gdx.files.internal(path));
	}

	public static Sound getSound(String path) {
		return sounds.get(path);
	}
	
	public static Texture getTexture(String path) {
		return textures.get(path);
	}
	
	public static BitmapFont getNewFont() {
		return new BitmapFont(Gdx.files.internal(DATA_FONT_FNT), Gdx.files.internal(DATA_FONT_PNG), false);
	}
	
	public static Sprite getSprite(String path) {
		Sprite s = null;
		if (!sprites.containsKey(path)) {
			s = new Sprite(Assets.getTexture(path));
			sprites.put(path, s);
		} else {
			s = sprites.get(path);
		}
		
		return s;
	}
	
	public static Sprite getTitleSprite() {
		return new Sprite(new TextureRegion(getTexture(DATA_TITLE)), 0, 0, 800, 600);
	}
	
	public static Sprite getBackMathieuSprite() {
		return new Sprite(new TextureRegion(getTexture(DATA_MATHIEU)), 0, 0, 800, 600);
	}
	
	public static Sprite getBackAnneLaureSprite() {
		return new Sprite(new TextureRegion(getTexture(DATA_ANNELAURE)), 0, 0, 800, 600);
	}
	
	public static Sprite getBackBothSprite() {
		return new Sprite(new TextureRegion(getTexture(DATA_BOTH)), 0, 0, 800, 600);
	}
	
	public static Sprite getBackSprite(String path) {
		return new Sprite(new TextureRegion(getTexture(path)), 0, 0, 800, 600);
	}
}
