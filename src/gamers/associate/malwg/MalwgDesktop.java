package gamers.associate.malwg;

import gamers.associate.malwg.Malwg;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class MalwgDesktop {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new LwjglApplication(Malwg.get(), Malwg.TITLE, Malwg.WIDTH, Malwg.HEIGHT, true);
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}
}
