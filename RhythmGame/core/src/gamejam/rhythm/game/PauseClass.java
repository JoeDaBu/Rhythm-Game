package gamejam.rhythm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class PauseClass implements Screen{

    private final Rhythm game;
    private OrthographicCamera camera;

    private String levelName = "";
    public int highScore = 0;

    //screen
    int width = Rhythm.WIDTH;
    int height = Rhythm.HEIGHT;

    private static  final int PLAY_BUTTON_WIDTH = 150;
    private static  final int PLAY_BUTTON_HEIGHT = 30;

    private Boolean display = false;

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
    Texture resumeButtonActive;
    Texture resumeButtonInactive;
    private static GameScreen gameScreen;

    public PauseClass(final Rhythm game, String levelName, int highScore, GameScreen gameScreen) {
        this.game = game;
        this.levelName = levelName;
        this.highScore = highScore;
        this.gameScreen = gameScreen;

        resumeButtonActive = new Texture(Gdx.files.internal("Buttons/Resume.png"));
        resumeButtonInactive = new Texture(Gdx.files.internal("Buttons/Resume_in.png"));
        playButtonActive = new Texture(Gdx.files.internal("Buttons/Restart.png"));
        playButtonInactive = new Texture(Gdx.files.internal("Buttons/Restart_in.png"));
        exitButtonActive = new Texture(Gdx.files.internal("Buttons/exit.png"));
        exitButtonInactive = new Texture(Gdx.files.internal("Buttons/exit_in.png"));
        scoreButtonInactive = new Texture(Gdx.files.internal("Buttons/score_in.png"));
        scoreButtonActive = new Texture(Gdx.files.internal("Buttons/score.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Rhythm.WIDTH, Rhythm.HEIGHT);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();

        SpriteBatch batch = game.batch;
        BitmapFont font = game.font;

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        if(Gdx.input.getX()> Rhythm.WIDTH/2 - PLAY_BUTTON_WIDTH/2 - 30 &&
                Gdx.input.getX()< Rhythm.WIDTH/2 + PLAY_BUTTON_WIDTH/2 + 30 &&
                Gdx.input.getY() < Rhythm.HEIGHT - 100 + PLAY_BUTTON_HEIGHT*3 &&
                Gdx.input.getY() > Rhythm.HEIGHT - 100 + PLAY_BUTTON_HEIGHT*2) {
            game.batch.draw(exitButtonActive, Rhythm.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2,
                    100 - PLAY_BUTTON_HEIGHT*3, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(exitButtonInactive, Rhythm.WIDTH/2 - PLAY_BUTTON_WIDTH/2,
                    100 - PLAY_BUTTON_HEIGHT*3,PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);
        }

        if(Gdx.input.getX()> Rhythm.WIDTH/2 - PLAY_BUTTON_WIDTH/2 - 30 &&
                Gdx.input.getX()< Rhythm.WIDTH/2 + PLAY_BUTTON_WIDTH/2 + 30 &&
                Gdx.input.getY() < Rhythm.HEIGHT - 100 + PLAY_BUTTON_HEIGHT*2 &&
                Gdx.input.getY() > Rhythm.HEIGHT - 100 + PLAY_BUTTON_HEIGHT) {
            game.batch.draw(scoreButtonActive, Rhythm.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2,
                    100 - PLAY_BUTTON_HEIGHT*2, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                display = display ? false:true;

            }
        } else {
            game.batch.draw(scoreButtonInactive, Rhythm.WIDTH/2 - PLAY_BUTTON_WIDTH/2,
                    100 - PLAY_BUTTON_HEIGHT*2,PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);
        }

        if(Gdx.input.getX()> Rhythm.WIDTH/2 - PLAY_BUTTON_WIDTH/2 - 30 &&
                Gdx.input.getX()< Rhythm.WIDTH/2 + PLAY_BUTTON_WIDTH/2 + 30 &&
                Gdx.input.getY() < Rhythm.HEIGHT - 100 + PLAY_BUTTON_HEIGHT &&
                Gdx.input.getY() > Rhythm.HEIGHT - 100) {
            game.batch.draw(playButtonActive, Rhythm.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2,
                    100 - PLAY_BUTTON_HEIGHT, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                gameScreen.dispose();
                dispose();
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                game.setScreen(new GameScreen(game, "Zavodila"));
                //game.setScreen(new LevelSelectorScreen(game));
            }
        } else {
            game.batch.draw(playButtonInactive, Rhythm.WIDTH/2 - PLAY_BUTTON_WIDTH/2,
                    100 - PLAY_BUTTON_HEIGHT,PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);
        }

        if(     Gdx.input.getX()> Rhythm.WIDTH/2 - PLAY_BUTTON_WIDTH/2 - 30 &&
                Gdx.input.getX()< Rhythm.WIDTH/2 + PLAY_BUTTON_WIDTH/2 + 30 &&
                Gdx.input.getY() < Rhythm.HEIGHT - 100 &&
                Gdx.input.getY() > Rhythm.HEIGHT - 100 - PLAY_BUTTON_HEIGHT){
            game.batch.draw(resumeButtonActive, Rhythm.WIDTH/2 - PLAY_BUTTON_WIDTH/2,
                    100,PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                gameScreen.paused = false;
                dispose();
                gameScreen.resume();
                game.setScreen(gameScreen);
            }
        } else {
            game.batch.draw(resumeButtonInactive, Rhythm.WIDTH/2 - PLAY_BUTTON_WIDTH/2,
                    100,PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);
        }

        if (display) {
            game.font.draw(game.batch, "Scores", 20, Rhythm.HEIGHT -10);
            game.font.draw(game.batch, "1." + Rhythm.scores[4], 20, Rhythm.HEIGHT -60);
            game.font.draw(game.batch, "2." + Rhythm.scores[3], 20, Rhythm.HEIGHT -110);
            game.font.draw(game.batch, "3." + Rhythm.scores[2], 20, Rhythm.HEIGHT -160);
            game.font.draw(game.batch, "4." + Rhythm.scores[1], 20, Rhythm.HEIGHT -210);
            game.font.draw(game.batch, "5." + Rhythm.scores[0], 20, Rhythm.HEIGHT -260);
        }

        font.draw(batch, levelName, width*0.5f - levelName.length() * Rhythm.FONT_SIZE *.2f, height - Rhythm.FONT_SIZE);
        font.draw(batch, "Score:", width*0.5f - 6 * Rhythm.FONT_SIZE *.2f, height - Rhythm.FONT_SIZE*2);
        String score = String.valueOf(highScore);
        font.draw(batch, score, width*0.5f - score.length() * Rhythm.FONT_SIZE *.2f, height - Rhythm.FONT_SIZE*3);
        batch.end();
    }


    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        this.width = width;
        this.height = height;
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

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
        resumeButtonActive.dispose();
        resumeButtonInactive.dispose();
        scoreButtonInactive.dispose();
        scoreButtonActive.dispose();
    }
}
