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
//        exitButtonActive = new Texture("exit.png");
//        exitButtonInactive = new Texture("exit_in.png");
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


		Gdx.gl.glClearColor(1,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();

		if(
		        Gdx.input.getX()> Rhythm.WIDTH/2 - PLAY_BUTTON_WIDTH/2 - 30 &&
            Gdx.input.getX()< Rhythm.WIDTH/2 + PLAY_BUTTON_WIDTH/2 + 30 &&
//                Gdx.input.getY()< Rhythm.HEIGHT- PLAY_BUTTON_HEIGHT-60
        Gdx.input.getY() < Rhythm.HEIGHT - 100 &&
                Gdx.input.getY() > Rhythm.HEIGHT - 100 - PLAY_BUTTON_HEIGHT){
            game.batch.draw(playButtonActive, Rhythm.WIDTH/2 - PLAY_BUTTON_WIDTH/2,
                    100,PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                dispose();
                game.setScreen(new GameScreen(game));
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
		
	}

}