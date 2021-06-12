package gamejam.rhythm.game.tools;

import com.badlogic.gdx.math.Rectangle;

import gamejam.rhythm.game.Generator.Arrow;

public class ArrowRectangle {
	private Arrow arrow;
	private Rectangle rect;
	
	public ArrowRectangle(Arrow arrow, Rectangle rect) {
		this.arrow = arrow;
		this.rect = rect;
	}

	public Arrow getArrow() {
		return arrow;
	}

	public void setArrow(Arrow arrow) {
		this.arrow = arrow;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
	
	
}
