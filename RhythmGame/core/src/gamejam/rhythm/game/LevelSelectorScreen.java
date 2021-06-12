package gamejam.rhythm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class LevelSelectorScreen implements Screen{
	
	private final Rhythm game;
	private OrthographicCamera camera;
	
	//screen
	int width = Rhythm.WIDTH;
	int height = Rhythm.HEIGHT;
	int levelSelectHeight = 100;
	
	//variables
	static FileHandle directory = Gdx.files.local("levels");
	String[] levels;
	
	public LevelSelectorScreen(final Rhythm game) {
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Rhythm.WIDTH, Rhythm.HEIGHT);
		
		levels = directory.file().list();
		
		System.out.println("Levels: ");
		for(int i = 0; i< levels.length; i++) {
			System.out.println(levels[i]);
		}
		
		//levelSelectHeight = height/levels.length;
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
		BitmapFont font = game.font;
		
		//input
		int mouseY = Gdx.input.getY();
		int index = (mouseY) / levelSelectHeight;
		if(Gdx.input.isTouched()) {
			if(index >= 0 && index < levels.length) {
				game.setScreen(new GameScreen(game, levels[index]));
			}
		}
		
		batch.setProjectionMatrix(camera.combined);
		//Begin rendering
		batch.begin();
		//FilePath
		for(int i = 0; i< levels.length; i++ ) {
			if(index == i) {
				font.setColor(Color.DARK_GRAY);
			}
			else {
				font.setColor(Color.GREEN);
			}
			font.draw(batch, levels[i], width*0.5f - levels[i].length() * Rhythm.FONT_SIZE *.2f, (height - levelSelectHeight*i)-Rhythm.FONT_SIZE*0.5f);
		}
		
		//End rendering
		batch.end();
		
		
		

		
	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, width, height);
		this.width = width;
		this.height = height;
		//levelSelectHeight = height/levels.length;
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
		
	}
	
}
