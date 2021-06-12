package gamejam.rhythm.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Rhythm extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 800);
		batch = new SpriteBatch();
		
	}

	@Override
	public void render () {
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
