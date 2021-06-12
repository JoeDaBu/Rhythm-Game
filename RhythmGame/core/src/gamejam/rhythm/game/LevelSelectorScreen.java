package gamejam.rhythm.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class LevelSelectorScreen implements Screen{
	
	private final Rhythm game;
	private OrthographicCamera camera;
	
	//screen
	int width = Rhythm.WIDTH;
	int height = Rhythm.HEIGHT;
	
	public LevelSelectorScreen(final Rhythm game) {
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Rhythm.WIDTH, Rhythm.HEIGHT);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		SpriteBatch batch = game.batch;
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		
		batch.end();

		
	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, width, height);
		this.width = width;
		this.height = height;
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
