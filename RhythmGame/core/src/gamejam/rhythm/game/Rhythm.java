package gamejam.rhythm.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;


public class Rhythm extends Game {
	
	public SpriteBatch batch;
	public BitmapFont font;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//font = new BitmapFont();
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("BAUHS93.TTF"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 50;
		font = generator.generateFont(parameter);
		generator.dispose();
		
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
