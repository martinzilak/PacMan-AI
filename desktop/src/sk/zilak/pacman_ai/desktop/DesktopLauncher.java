package sk.zilak.pacman_ai.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import sk.zilak.pacman_ai.PacManGame;

import static sk.zilak.pacman_ai.PacManGame.WINDOW_HEIGHT;
import static sk.zilak.pacman_ai.PacManGame.WINDOW_WIDTH;

public class DesktopLauncher {
	public static void main (String[] arg) {

		// a hack to make the OpenAL library work
		System.setProperty("user.name", "Mroz");

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = WINDOW_WIDTH;
		config.height = WINDOW_HEIGHT;
		config.forceExit = false;
		new LwjglApplication(new PacManGame(), config);
	}
}
