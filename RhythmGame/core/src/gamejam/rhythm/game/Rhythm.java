package gamejam.rhythm.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;


public class Rhythm extends Game {

    public static final int scores[] = new int[5];
    public SpriteBatch batch;
    public BitmapFont font;
    public static final int HEIGHT = 720;
    public static final int WIDTH = 1280;
    public static final int FONT_SIZE = 50;

    @Override
    public void create () {
        batch = new SpriteBatch();
        //font = new BitmapFont();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ALGER.TTF"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = FONT_SIZE;
        parameter.color = Color.WHITE;
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