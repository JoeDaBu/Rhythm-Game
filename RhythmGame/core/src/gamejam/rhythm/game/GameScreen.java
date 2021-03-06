package gamejam.rhythm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import gamejam.rhythm.game.Generator.Arrow;
import gamejam.rhythm.game.Generator.ArrowSequence;
import gamejam.rhythm.game.fileio.LevelClass;
import gamejam.rhythm.game.fileio.LevelFileReader;
import gamejam.rhythm.game.tools.ArrowRectangle;

import java.util.Arrays;
import java.util.Iterator;

import static java.lang.Character.toUpperCase;

public class GameScreen implements Screen{

	//setup
    public static String levelName;
	final Rhythm game;
	private OrthographicCamera camera;
	private Sound successSound;
	private Sound failSound;
	//rendering
	private int screenCenterX;
	private int arrowPadding = 20;
	private int arrowWidth = 128;

	//assets
	private long startTime = TimeUtils.millis();
	private double elapsedTime;
	private Texture background;
	
	private Texture greyArrowUp;
	private Texture greyArrowDown;
	private Texture greyArrowLeft;
	private Texture greyArrowRight;

	private Texture arrowUp;
	private Texture arrowDown;
	private Texture arrowLeft;
	private Texture arrowRight;

	private Texture miss;
	private Texture okay;
	private Texture great;
	private Texture perfect;
	private Texture marvelous;
	private Texture nice;
	private Texture fine;

	private static double resumeTime;
    private static double stopTime = 0;

	int highScore = 0;
    int increment  = -2;
	//Reference<Integer> score;

	//music
	Music music;

	//game logic
    public Boolean paused = false;
	private ArrowSequence arrowSeq;
	private Array<ArrowRectangle> arrows;

	private long arrowSpawnCD = 1000000000;//in nano secodns
	private long lastArrowSpawnTime;
	private float arrowSpeed = 300f;
	private float spaceFreq = .8f;
	private float setAdditionalChance = 1f;

	public static String currentWord;
	public int wordIndex = 0;
	
	final LevelEndScreen endScreen;

	//settings
	private static float musicVol = 0.5f; 



	//testing
	//ShapeRenderer shapeRender = new ShapeRenderer();

	public GameScreen(final Rhythm game) {
		this(game, "Zavodila");
//		this.game = game;
//
//		//set up assets
//		successSound = Gdx.audio.newSound(Gdx.files.internal("okayyy.wav"));
//		greyArrowUp = new Texture(Gdx.files.internal("arrows/arrow-grey-up.png"));
//		greyArrowDown = new Texture(Gdx.files.internal("arrows/arrow-grey-down.png"));
//		greyArrowLeft = new Texture(Gdx.files.internal("arrows/arrow-grey-left.png"));
//		greyArrowRight = new Texture(Gdx.files.internal("arrows/arrow-grey-right.png"));
//
//		arrowUp = new Texture(Gdx.files.internal("arrows/arrow-up.png"));
//		arrowDown = new Texture(Gdx.files.internal("arrows/arrow-down.png"));
//		arrowLeft = new Texture(Gdx.files.internal("arrows/arrow-left.png"));
//		arrowRight = new Texture(Gdx.files.internal("arrows/arrow-right.png"));
//
//		miss = new Texture(Gdx.files.internal("arrows/arrow-grey-up.png"));
//		fine = new Texture(Gdx.files.internal("arrows/arrow-grey-up.png"));
//		okay = new Texture(Gdx.files.internal("arrows/arrow-grey-up.png"));
//		nice = new Texture(Gdx.files.internal("arrows/arrow-grey-up.png"));
//		great = new Texture(Gdx.files.internal("arrows/arrow-grey-up.png"));
//		perfect = new Texture(Gdx.files.internal("arrows/arrow-grey-up.png"));
//		marvelous = new Texture(Gdx.files.internal("arrows/arrow-grey-up.png"));
//
//		//music = Gdx.audio.newMusic(Gdx.files.internal("music/Gospel (Puru's Piano Remix)- Puru.wav"));
//		music = Gdx.audio.newMusic(Gdx.files.internal("music/Zavodila Remix.wav"));
//		music.setVolume(musicVol);
//		music.setLooping(false);
//
//
//		//set up game view
//		camera = new OrthographicCamera();
//		camera.setToOrtho(false, 1280, 720);
//		screenCenterX = (int) (camera.viewportWidth/2);
//		//System.out.println(camera.viewportWidth);
//
//		//set up game objects
//		arrowSeq = new ArrowSequence(1);
//		currentWord = arrowSeq.wp.getWord(4);
//		arrowSeq.setCurrentWord(currentWord);
//		arrowSeq.setSpaceFrequency(spaceFreq);
//		arrowSeq.setAdditionalChance(setAdditionalChance);
//		arrowSeq.ResetLetters();
//
//		arrows = new Array<ArrowRectangle>();
//		//generateArrow();

	}

	public GameScreen(final Rhythm game, String levelName) {
		this.game = game;
		//this(game);
		//		LevelClass level = LevelFileReader.getLevel(levelName);
		//		arrowSeq = level.getSequence();
		//		currentWord = arrowSeq.wp.getWord(4);
		//		arrowSeq.setCurrentWord(currentWord);
		//		arrowSeq.setSpaceFrequency(spaceFreq);
		//		arrowSeq.setAdditionalChance(setAdditionalChance);
		//		arrowSeq.ResetLetters();
		//		System.out.println(currentWord);
         this.levelName = levelName;

		//set up assets
		background = new Texture(Gdx.files.internal("coolbackground.jpg"));
		successSound = Gdx.audio.newSound(Gdx.files.internal("okayyy.wav"));
		failSound = Gdx.audio.newSound(Gdx.files.internal("failsound.wav"));
		greyArrowUp = new Texture(Gdx.files.internal("arrows/arrow-grey-up.png"));
		greyArrowDown = new Texture(Gdx.files.internal("arrows/arrow-grey-down.png"));
		greyArrowLeft = new Texture(Gdx.files.internal("arrows/arrow-grey-left.png"));
		greyArrowRight = new Texture(Gdx.files.internal("arrows/arrow-grey-right.png"));

		arrowUp = new Texture(Gdx.files.internal("arrows/arrow-up.png"));
		arrowDown = new Texture(Gdx.files.internal("arrows/arrow-down.png"));
		arrowLeft = new Texture(Gdx.files.internal("arrows/arrow-left.png"));
		arrowRight = new Texture(Gdx.files.internal("arrows/arrow-right.png"));

		miss = new Texture(Gdx.files.internal("arrows/arrow-grey-up.png"));
		fine = new Texture(Gdx.files.internal("arrows/arrow-grey-up.png"));
		okay = new Texture(Gdx.files.internal("arrows/arrow-grey-up.png"));
		nice = new Texture(Gdx.files.internal("arrows/arrow-grey-up.png"));
		great = new Texture(Gdx.files.internal("arrows/arrow-grey-up.png"));
		perfect = new Texture(Gdx.files.internal("arrows/arrow-grey-up.png"));
		marvelous = new Texture(Gdx.files.internal("arrows/arrow-grey-up.png"));


		//set up game view
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		screenCenterX = (int) (camera.viewportWidth/2);

		LevelClass level = LevelFileReader.getLevel(levelName);
		arrowSeq = level.getSequence();
		currentWord = arrowSeq.wp.getWord(4);
		arrowSeq.setCurrentWord(currentWord);
		arrowSeq.setSpaceFrequency(spaceFreq);
		arrowSeq.setAdditionalChance(setAdditionalChance);
		arrowSeq.ResetLetters();
		System.out.println(currentWord);

		arrowSpeed = level.getArrowSpeed();
		arrowSpawnCD = level.getSpawnCD();

		//music.dispose();
		music = Gdx.audio.newMusic(Gdx.files.internal("music/"+level.getMusic()));
		music.setVolume(musicVol);
		music.setLooping(false);
		
		endScreen = new LevelEndScreen(game, levelName, 0);
		music.setOnCompletionListener(new Music.OnCompletionListener() {

			@Override
			public void onCompletion(Music music) {
			    if (highScore > Rhythm.scores[0]) {
			        Rhythm.scores[0] = highScore;
                    Arrays.sort(Rhythm.scores);
                }
                endScreen.highScore = highScore;
                game.setScreen(endScreen);
                dispose();
			}
		});
		
		arrows = new Array<ArrowRectangle>();
	}

	@Override
	public void render(float delta) {
	    if (highScore < 0) {
            endScreen.highScore = highScore;
            game.setScreen(endScreen);
            dispose();
        }
        if (paused) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            ScreenUtils.clear(0, 0, 0.2f, 1);
            camera.update();

            SpriteBatch batch = game.batch;
            BitmapFont font = game.font;
            font.setColor(Color.WHITE);
            batch.setProjectionMatrix(camera.combined);
            batch.begin();
            batch.draw(background, 0, 0);
            //start render texture
            font.draw(batch, "Score: " + highScore, 20, 680);
            font.draw(batch, "Word: " + currentWord, 20, 50);
            font.draw(batch, "Target Letter: " + currentWord.charAt(wordIndex), 20, 100);
            elapsedTime = (((double)TimeUtils.timeSinceMillis(startTime)/1000) + stopTime);
            font.draw(batch, "Time: " + String.format("%.3f", elapsedTime), 20, 600);

            //draw input arrows
            int centerX = screenCenterX - (int)(arrowWidth * 0.5f);
            batch.draw(greyArrowDown, centerX - (arrowWidth + arrowPadding)*.5f, arrowWidth, arrowWidth, arrowWidth); // down
            batch.draw(greyArrowUp, centerX + (arrowWidth + arrowPadding)*.5f, arrowWidth, arrowWidth, arrowWidth); // up
            batch.draw(greyArrowLeft, centerX - (arrowWidth + arrowPadding)*1.5f, arrowWidth, arrowWidth, arrowWidth);// left
            batch.draw(greyArrowRight, centerX + (arrowWidth + arrowPadding)*1.5f, arrowWidth, arrowWidth, arrowWidth);// right
            switch (increment) {
                case 200:
                    font.draw(batch, "marvelous", centerX, arrowPadding * 2);
                    //                batch.draw(marvelous, centerX + (arrowWidth + arrowPadding)*1.5f, arrowWidth, arrowWidth, arrowWidth);
                    break;
                case 100:
                    font.draw(batch, "perfect", centerX, arrowPadding * 2);
                    //                batch.draw(perfect, centerX + (arrowWidth + arrowPadding)*1.5f, arrowWidth, arrowWidth, arrowWidth);
                    break;
                case 50:
                    font.draw(batch, "great", centerX, arrowPadding * 2);
                    //batch.draw(great, centerX + (arrowWidth + arrowPadding)*1.5f, arrowWidth, arrowWidth, arrowWidth);
                    break;
                case 30:
                    font.draw(batch, "nice", centerX, arrowPadding * 2);
                    //batch.draw(nice, centerX + (arrowWidth + arrowPadding)*1.5f, arrowWidth, arrowWidth, arrowWidth);
                    break;
                case 20:
                    font.draw(batch, "okay", centerX, arrowPadding * 2);
                    //batch.draw(okay, centerX + (arrowWidth + arrowPadding)*1.5f, arrowWidth, arrowWidth, arrowWidth);
                    break;
                case 10:
                    font.draw(batch, "fine", centerX, arrowPadding * 2);
                    //batch.draw(fine, centerX + (arrowWidth + arrowPadding)*1.5f, arrowWidth, arrowWidth, arrowWidth);
                    break;
                case 0:
                    font.draw(batch, "miss", centerX, arrowPadding * 2);
                    //batch.draw(miss, centerX + (arrowWidth + arrowPadding)*1.5f, arrowWidth, arrowWidth, arrowWidth);
                    break;
                case -200:
                    font.draw(batch, "Incorrect Letter", centerX, arrowPadding * 2);
                default:
            }


            //draw game arrows and update arrows
            for (Iterator<ArrowRectangle> iter = arrows.iterator(); iter.hasNext(); ) {


                ArrowRectangle arrow = iter.next();
                float x;
                float y = arrow.getRect().y;
                switch (arrow.getArrow().getDirection()) {
                    case 0:
                        x = centerX - (arrowWidth + arrowPadding) * 1.5f;
                        batch.draw(arrowLeft, x, y, arrowWidth, arrowWidth);// left
                        font.draw(batch, String.valueOf(arrow.getArrow().getLetter()), x - 15 + arrowWidth * .5f, y + 25 + arrowWidth * .5f);
                        break;
                    case 1:
                        x = centerX - (arrowWidth + arrowPadding) * .5f;
                        batch.draw(arrowDown, x, y, arrowWidth, arrowWidth);// down
                        font.draw(batch, String.valueOf(arrow.getArrow().getLetter()), x - 15 + arrowWidth * .5f, y + 25 + arrowWidth * .5f);
                        break;
                    case 2:
                        x = centerX + (arrowWidth + arrowPadding) * .5f;
                        batch.draw(arrowUp, x, y, arrowWidth, arrowWidth);// up
                        font.draw(batch, String.valueOf(arrow.getArrow().getLetter()), x - 15 + arrowWidth * .5f, y + 25 + arrowWidth * .5f);
                        break;
                    case 3:
                        x = centerX + (arrowWidth + arrowPadding) * 1.5f;
                        batch.draw(arrowRight, x, y, arrowWidth, arrowWidth);// right
                        font.draw(batch, String.valueOf(arrow.getArrow().getLetter()), x - 15 + arrowWidth * .5f, y + 25 + arrowWidth * .5f);
                        break;
                }


                if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && (arrow.getRect().y > (arrowPadding)) && (arrow.getRect().y < (2 * arrowWidth) &&

                        arrow.getArrow().getDirection() == 0)) {
                    iter.remove();
                    if (arrow.getArrow().getLetter() == toUpperCase(currentWord.charAt(wordIndex))) {
                        //if(currentWord.contains((String.valueOf(arrow.getArrow().getLetter()).toLowerCase()))) {
                        successSound.play();
                        wordIndex++;
                    } else if (arrow.getArrow().getLetter() != toUpperCase(currentWord.charAt(wordIndex)) && (arrow.getArrow().getLetter() != ' ')) {
                        increment = -200;
                        failSound.play();
                    } else if (arrow.getRect().y < arrowWidth * 2 - (arrowPadding * 6) &&
                            arrow.getRect().y > (arrowPadding * 6 + arrowPadding / 2)) {
                        increment = 200;
                    } else if (arrow.getRect().y < arrowWidth * 2 - (arrowPadding * 5 + arrowPadding / 2)) {
                        increment = 100;
                    } else if (arrow.getRect().y < (arrowWidth * 2 - (arrowPadding * 5)) &&
                            arrow.getRect().y > (arrowPadding * 5)) {
                        increment = 50;
                    } else if (arrow.getRect().y < (arrowWidth * 2 - (arrowPadding * 4)) &&
                            arrow.getRect().y > (arrowPadding * 4)) {
                        increment = 30;
                    } else if (arrow.getRect().y < (arrowWidth * 2 - (arrowPadding * 2)) &&
                            arrow.getRect().y > (arrowPadding * 2)) {
                        increment = 20;
                    } else if (arrow.getRect().y < (arrowWidth * 2 - arrowPadding)) {
                        increment = 10;
                    } else {
                        increment = 0;
                    }
                    highScore += increment;
                }

                if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && (arrow.getRect().y > (arrowPadding)) && (arrow.getRect().y < (2 * arrowWidth) &&
                        arrow.getArrow().getDirection() == 1)) {
                    iter.remove();
                    if (arrow.getArrow().getLetter() == toUpperCase(currentWord.charAt(wordIndex))) {
                        //if(currentWord.contains((String.valueOf(arrow.getArrow().getLetter()).toLowerCase()))) {
                        successSound.play();
                        wordIndex++;
                    } else if (arrow.getArrow().getLetter() != toUpperCase(currentWord.charAt(wordIndex)) && (arrow.getArrow().getLetter() != ' ')) {
                        increment = -200;
                        failSound.play();
                    } else if (arrow.getRect().y < arrowWidth * 2 - (arrowPadding * 6) &&
                            arrow.getRect().y > (arrowPadding * 6 + arrowPadding / 2)) {
                        increment = 200;
                    } else if (arrow.getRect().y < arrowWidth * 2 - (arrowPadding * 5 + arrowPadding / 2)) {
                        increment = 100;
                    } else if (arrow.getRect().y < (arrowWidth * 2 - (arrowPadding * 5)) &&
                            arrow.getRect().y > (arrowPadding * 5)) {
                        increment = 50;
                    } else if (arrow.getRect().y < (arrowWidth * 2 - (arrowPadding * 4)) &&
                            arrow.getRect().y > (arrowPadding * 4)) {
                        increment = 30;
                    } else if (arrow.getRect().y < (arrowWidth * 2 - (arrowPadding * 2)) &&
                            arrow.getRect().y > (arrowPadding * 2)) {
                        increment = 20;
                    } else if (arrow.getRect().y < (arrowWidth * 2 - arrowPadding)) {
                        increment = 10;
                    } else {
                        increment = 0;
                    }
                    highScore += increment;
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && (arrow.getRect().y > (arrowPadding)) && (arrow.getRect().y < (2 * arrowWidth) &&
                        arrow.getArrow().getDirection() == 2)) {
                    iter.remove();
                    if (arrow.getArrow().getLetter() == toUpperCase(currentWord.charAt(wordIndex))) {
                        //if(currentWord.contains((String.valueOf(arrow.getArrow().getLetter()).toLowerCase()))) {
                        successSound.play();
                        wordIndex++;
                    } else if (arrow.getArrow().getLetter() != toUpperCase(currentWord.charAt(wordIndex)) && (arrow.getArrow().getLetter() != ' ')) {
                        increment = -200;
                        failSound.play();
                    } else if (arrow.getRect().y < arrowWidth * 2 - (arrowPadding * 6) &&
                            arrow.getRect().y > (arrowPadding * 6 + arrowPadding / 2)) {
                        increment = 200;
                    } else if (arrow.getRect().y < arrowWidth * 2 - (arrowPadding * 5 + arrowPadding / 2)) {
                        increment = 100;
                    } else if (arrow.getRect().y < (arrowWidth * 2 - (arrowPadding * 5)) &&
                            arrow.getRect().y > (arrowPadding * 5)) {
                        increment = 50;
                    } else if (arrow.getRect().y < (arrowWidth * 2 - (arrowPadding * 4)) &&
                            arrow.getRect().y > (arrowPadding * 4)) {
                        increment = 30;
                    } else if (arrow.getRect().y < (arrowWidth * 2 - (arrowPadding * 2)) &&
                            arrow.getRect().y > (arrowPadding * 2)) {
                        increment = 20;
                    } else if (arrow.getRect().y < (arrowWidth * 2 - arrowPadding)) {
                        increment = 10;
                    } else {
                        increment = 0;
                    }
                    highScore += increment;
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && (arrow.getRect().y > (arrowPadding)) && (arrow.getRect().y < (2 * arrowWidth) &&
                        arrow.getArrow().getDirection() == 3)) {
                    iter.remove();
                    if (arrow.getArrow().getLetter() == toUpperCase(currentWord.charAt(wordIndex))) {
                        //if(currentWord.contains((String.valueOf(arrow.getArrow().getLetter()).toLowerCase()))) {
                        successSound.play();
                        wordIndex++;
                    } else if (arrow.getArrow().getLetter() != toUpperCase(currentWord.charAt(wordIndex)) && (arrow.getArrow().getLetter() != ' ')) {
                        increment = -200;
                        failSound.play();
                    } else if (arrow.getRect().y < arrowWidth * 2 - (arrowPadding * 6) &&
                            arrow.getRect().y > (arrowPadding * 6 + arrowPadding / 2)) {
                        increment = 200;
                    } else if (arrow.getRect().y < arrowWidth * 2 - (arrowPadding * 5 + arrowPadding / 2)) {
                        increment = 100;
                    } else if (arrow.getRect().y < (arrowWidth * 2 - (arrowPadding * 5)) &&
                            arrow.getRect().y > (arrowPadding * 5)) {
                        increment = 50;
                    } else if (arrow.getRect().y < (arrowWidth * 2 - (arrowPadding * 4)) &&
                            arrow.getRect().y > (arrowPadding * 4)) {
                        increment = 30;
                    } else if (arrow.getRect().y < (arrowWidth * 2 - (arrowPadding * 2)) &&
                            arrow.getRect().y > (arrowPadding * 2)) {
                        increment = 20;
                    } else if (arrow.getRect().y < (arrowWidth * 2 - arrowPadding)) {
                        increment = 10;
                    } else {
                        increment = 0;
                    }
                    highScore += increment;
                }

                if (wordIndex >= currentWord.length()) {
                    currentWord = arrowSeq.wp.getWord(MathUtils.random(4, 5));
                    arrowSeq.setCurrentWord(currentWord);
                    arrowSeq.ResetLetters();
                    wordIndex = 0;
                    highScore += 500;
                }

                arrow.getRect().y -= arrowSpeed * delta;
                if (arrow.getRect().y + arrowWidth < 0) {
                    if (arrow.getArrow().getLetter() == currentWord.charAt(wordIndex) || ' ' == arrow.getArrow().getLetter() || arrow.getArrow().getLetter() == toUpperCase(currentWord.charAt(wordIndex))) {
                        increment = 0;
                    }
                    iter.remove();

                }

                //DO INPUT CHECKS HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }


            //stop render texture
            batch.end();

            //do updates here
            if (TimeUtils.nanoTime() - lastArrowSpawnTime > arrowSpawnCD) generateArrow();

            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                pause();
//                endScreen.highScore = highScore;
//                if (highScore > Rhythm.scores[0]) {
//                    Rhythm.scores[0] = highScore;
//                    Arrays.sort(Rhythm.scores);
//                }
//                game.setScreen(endScreen);
//                dispose();
            }
        }
	}

	private void generateArrow() {
		Rectangle rect = new Rectangle();
		rect.x = 0; //JUST CHECK FOR Y WHEN DOING INPUT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		rect.y = camera.viewportHeight;
		rect.width = arrowWidth;
		rect.height = arrowWidth;

		Arrow arrow = arrowSeq.getNextArrow(); //new Arrow('a', MathUtils.random(0, 3))
		//arrow.setLetter('A');
		ArrowRectangle arrowRect = new ArrowRectangle(arrow, rect);
		//System.out.println(arrow.getDirection());
        if (arrowRect.getArrow().getDirection() != -1) {
            arrows.add(arrowRect);
        }
		lastArrowSpawnTime = TimeUtils.nanoTime();
	}

	@Override
	public void show() {
		music.play();

	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, width, height);
		screenCenterX = width/2;
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
        paused = true;
        stopTime = elapsedTime;
        music.pause();
        game.setScreen(new PauseClass(game, levelName, highScore, this));

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
        startTime = TimeUtils.millis();

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
	    startTime = 0;
	    stopTime = 0;
		greyArrowUp.dispose();
		greyArrowDown.dispose();
		greyArrowLeft.dispose();
		greyArrowRight.dispose();

		arrowUp.dispose();
		arrowDown.dispose();
		arrowLeft.dispose();
		arrowRight.dispose();

		music.dispose();

	}
}
