package gamejam.rhythm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {

    private static  final int PLAY_BUTTON_WIDTH = 150;
    private static  final int PLAY_BUTTON_HEIGHT = 30;
	final Rhythm game;
	OrthographicCamera camera;
	Boolean display = false;
	Texture exitButtonActive;
	Texture exitButtonInactive;
    Texture optionsButtonActive;
    Texture optionsButtonInactive;
    Texture scoreButtonActive;
    Texture scoreButtonInactive;
    Texture aboutButtonActive;
    Texture aboutButtonInactive;
	Texture playButtonActive;
	Texture playButtonInactive;

	public MainMenuScreen(final Rhythm game) {
		this.game = game;
		playButtonActive = new Texture(Gdx.files.internal("Buttons/play.png"));
        playButtonInactive = new Texture(Gdx.files.internal("Buttons/play_in.png"));
        exitButtonActive = new Texture(Gdx.files.internal("Buttons/exit.png"));
        scoreButtonInactive = new Texture(Gdx.files.internal("Buttons/score_in.png"));
        scoreButtonActive = new Texture(Gdx.files.internal("Buttons/score.png"));
        exitButtonInactive = new Texture(Gdx.files.internal("Buttons/exit_in.png"));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Rhythm.WIDTH, Rhythm.HEIGHT);
	}


        //...Rest of class omitted for succinctness.

        //public MainMenuScreen(final Drop game)....        

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);


//		Gdx.gl.glClearColor(0,0,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();

        if(Gdx.input.getX()> Rhythm.WIDTH/2 - PLAY_BUTTON_WIDTH/2 - 30 &&
                Gdx.input.getX()< Rhythm.WIDTH/2 + PLAY_BUTTON_WIDTH/2 + 30 &&
                Gdx.input.getY() < Rhythm.HEIGHT - 100 + PLAY_BUTTON_HEIGHT*2 &&
                Gdx.input.getY() > Rhythm.HEIGHT - 100 + PLAY_BUTTON_HEIGHT) {
            game.batch.draw(exitButtonActive, Rhythm.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2,
                    100 - PLAY_BUTTON_HEIGHT*2, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(exitButtonInactive, Rhythm.WIDTH/2 - PLAY_BUTTON_WIDTH/2,
                    100 - PLAY_BUTTON_HEIGHT*2,PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);
        }

        if(Gdx.input.getX()> Rhythm.WIDTH/2 - PLAY_BUTTON_WIDTH/2 - 30 &&
                Gdx.input.getX()< Rhythm.WIDTH/2 + PLAY_BUTTON_WIDTH/2 + 30 &&
                Gdx.input.getY() < Rhythm.HEIGHT - 100 + PLAY_BUTTON_HEIGHT &&
                Gdx.input.getY() > Rhythm.HEIGHT - 100) {
            game.batch.draw(scoreButtonActive, Rhythm.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2,
                    100 - PLAY_BUTTON_HEIGHT, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
//                display = true;
                game.font.draw(game.batch, "Scores", 20, Rhythm.HEIGHT -10);
                game.font.draw(game.batch, "1." + Rhythm.scores[4], 20, Rhythm.HEIGHT -60);
                game.font.draw(game.batch, "2." + Rhythm.scores[3], 20, Rhythm.HEIGHT -110);
                game.font.draw(game.batch, "3." + Rhythm.scores[2], 20, Rhythm.HEIGHT -160);
                game.font.draw(game.batch, "4." + Rhythm.scores[1], 20, Rhythm.HEIGHT -210);
                game.font.draw(game.batch, "5." + Rhythm.scores[0], 20, Rhythm.HEIGHT -260);
            }
        } else {
            game.batch.draw(scoreButtonInactive, Rhythm.WIDTH/2 - PLAY_BUTTON_WIDTH/2,
                    100 - PLAY_BUTTON_HEIGHT,PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);
        }

		if(     Gdx.input.getX()> Rhythm.WIDTH/2 - PLAY_BUTTON_WIDTH/2 - 30 &&
                Gdx.input.getX()< Rhythm.WIDTH/2 + PLAY_BUTTON_WIDTH/2 + 30 &&
                Gdx.input.getY() < Rhythm.HEIGHT - 100 &&
                Gdx.input.getY() > Rhythm.HEIGHT - 100 - PLAY_BUTTON_HEIGHT){
            game.batch.draw(playButtonActive, Rhythm.WIDTH/2 - PLAY_BUTTON_WIDTH/2,
                    100,PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                dispose();
                game.setScreen(new GameScreen(game, "Zavodila"));
                //game.setScreen(new LevelSelectorScreen(game));
            }
        } else {
            game.batch.draw(playButtonInactive, Rhythm.WIDTH/2 - PLAY_BUTTON_WIDTH/2,
                    100,PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);
        }

		game.batch.end();
	}


	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, width, height);
		
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
		playButtonActive.dispose();
		playButtonInactive.dispose();
		exitButtonActive.dispose();
		exitButtonInactive.dispose();
	}

}