package gamejam.rhythm.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import gamejam.rhythm.game.Rhythm;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "JoeIsNotTheBoss";
		config.width = 800;
		config.height = 800;
		new LwjglApplication(new Rhythm(), config);
	}
}
