package gamejam.rhythm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import gamejam.rhythm.game.Generator.Arrow;
import gamejam.rhythm.game.Generator.ArrowSequence;
import gamejam.rhythm.game.tools.ArrowRectangle;

import java.util.Iterator;

public class GameScreen implements Screen{

	//setup
	final Rhythm game;
	private OrthographicCamera camera;
	private Sound successSound;
	//rendering
	private int screenCenterX;
	private int arrowPadding = 10;
	private int arrowWidth = 128;
	
	//assets
	private Texture greyArrowUp;
	private Texture greyArrowDown;
	private Texture greyArrowLeft;
	private Texture greyArrowRight;
	
	private Texture arrowUp;
	private Texture arrowDown;
	private Texture arrowLeft;
	private Texture arrowRight;
	
	//music
	Music music;
	
	//game logic
	private ArrowSequence arrowSeq;
	private Array<ArrowRectangle> arrows;
	private long arrowSpawnCD = 1000000000;//in nano secodns
	private long lastArrowSpawnTime;
	private float arrowSpeed = 300f;

	public GameScreen(final Rhythm game) {
		this.game = game;
		
		//set up assets
		successSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
		greyArrowUp = new Texture(Gdx.files.internal("arrows/arrow-grey-up.png"));
		greyArrowDown = new Texture(Gdx.files.internal("arrows/arrow-grey-down.png"));
		greyArrowLeft = new Texture(Gdx.files.internal("arrows/arrow-grey-left.png"));
		greyArrowRight = new Texture(Gdx.files.internal("arrows/arrow-grey-right.png"));
		
		arrowUp = new Texture(Gdx.files.internal("arrows/arrow-up.png"));
		arrowDown = new Texture(Gdx.files.internal("arrows/arrow-down.png"));
		arrowLeft = new Texture(Gdx.files.internal("arrows/arrow-left.png"));
		arrowRight = new Texture(Gdx.files.internal("arrows/arrow-right.png"));
		
		//music = Gdx.audio.newMusic(Gdx.files.internal("music/Gospel (Puru's Piano Remix)- Puru.wav"));
		music = Gdx.audio.newMusic(Gdx.files.internal("music/Zavodila Remix.wav"));
		music.setVolume(.5f);
		music.setLooping(true);
		
		
		//set up game view
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		screenCenterX = (int) (camera.viewportWidth/2);
		//System.out.println(camera.viewportWidth);
		
		//set up game objects
		arrowSeq = new ArrowSequence(1);
		arrows = new Array<ArrowRectangle>();
		generateArrow();
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		
		SpriteBatch batch = game.batch;
		BitmapFont font = game.font;
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//start render texture
		
		//draw input arrows
		int centerX = screenCenterX - (int)(arrowWidth * 0.5f);
		batch.draw(greyArrowDown, centerX - (arrowWidth + arrowPadding)*.5f, arrowPadding, arrowWidth, arrowWidth); // down
		batch.draw(greyArrowUp, centerX + (arrowWidth + arrowPadding)*.5f, arrowPadding, arrowWidth, arrowWidth); // up
		batch.draw(greyArrowLeft, centerX - (arrowWidth + arrowPadding)*1.5f, arrowPadding, arrowWidth, arrowWidth);// left
		batch.draw(greyArrowRight, centerX + (arrowWidth + arrowPadding)*1.5f, arrowPadding, arrowWidth, arrowWidth);// right
		
		
		//draw game arrows and update arrows
		for (Iterator<ArrowRectangle> iter = arrows.iterator(); iter.hasNext(); ) {
			ArrowRectangle arrow = iter.next();
			float x;
			float y = arrow.getRect().y;
			switch(arrow.getArrow().getDirection()) {
			case 0:
				x = centerX - (arrowWidth + arrowPadding)*1.5f;
				batch.draw(arrowLeft, x, y, arrowWidth, arrowWidth);// left
				font.draw(batch, String.valueOf(arrow.getArrow().getLetter()), x - 15 + arrowWidth*.5f, y + 25 + arrowWidth*.5f);
				break;
			case 1:
				x = centerX - (arrowWidth + arrowPadding)*.5f;
				batch.draw(arrowDown, x, y, arrowWidth, arrowWidth);// down
				font.draw(batch, String.valueOf(arrow.getArrow().getLetter()), x - 15 + arrowWidth*.5f, y + 25 + arrowWidth*.5f);
				break;
			case 2:
				x = centerX + (arrowWidth + arrowPadding)*.5f;
				batch.draw(arrowUp, x, y, arrowWidth, arrowWidth);// up
				font.draw(batch, String.valueOf(arrow.getArrow().getLetter()), x - 15 + arrowWidth*.5f, y + 25 + arrowWidth*.5f);
				break;
			case 3:
				x = centerX + (arrowWidth + arrowPadding)*1.5f;
				batch.draw(arrowRight, x, y, arrowWidth, arrowWidth);// right
				font.draw(batch, String.valueOf(arrow.getArrow().getLetter()), x - 15 + arrowWidth*.5f, y + 25 + arrowWidth*.5f);
				break;
			}
			
			arrow.getRect().y -= arrowSpeed * delta;
			if(arrow.getRect().y + arrowWidth*.5f < 0) iter.remove();
			if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT))  {
				successSound.play();
//				iter.remove();
			}
			if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
				successSound.play();
//				iter.remove();
			}
			if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
				successSound.play();
//				iter.remove();
			}
			if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
				successSound.play();
//				iter.remove();
			}
			//DO INPUT CHECKS HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		}
		
		//stop render texture
		batch.end();
		
		//do updates here
		if(TimeUtils.nanoTime() - lastArrowSpawnTime > arrowSpawnCD) generateArrow();
		
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
		
		arrows.add(arrowRect);
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
		greyArrowUp.dispose();
		greyArrowDown.dispose();
		greyArrowLeft.dispose();
		greyArrowRight.dispose();
		
		arrowUp.dispose();
		arrowDown.dispose();
		arrowLeft.dispose();
		arrowRight.dispose();
		
	}
}