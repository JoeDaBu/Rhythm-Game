package gamejam.rhythm.game.Generator;

public class Arrow {

	private String let = null;
	private int dir = -1; // 0 = Left, 1 == Down, 2 = Up, 3 = Right
	
	public Arrow() {}
	
	public Arrow(String letter, int direction) {
		this.let = letter;
		this.dir = direction;
	}
	
	public String getLetter() { return this.let; }
	public int getDirection() { return this.dir; }
	
}
