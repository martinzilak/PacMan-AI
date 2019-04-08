package sk.zilak.pacman_ai.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import sk.zilak.pacman_ai.PacMan;

public class DesktopLauncher {
	public static void main (String[] arg) {

		// a hack to make the OpenAL library work
		System.setProperty("user.name", "Mroz");

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new PacMan(), config);
	}
}
