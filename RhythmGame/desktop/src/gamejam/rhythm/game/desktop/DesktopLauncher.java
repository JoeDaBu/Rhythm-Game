package gamejam.rhythm.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import gamejam.rhythm.game.Rhythm;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Rhythm Game";
		config.width = Rhythm.WIDTH;
		config.height = Rhythm.HEIGHT;
		config.foregroundFPS = 60;
		config.resizable = false;
		new LwjglApplication(new Rhythm(), config);
	}
}
