package gamejam.rhythm.game.Generator;

public class Arrow {

	private char let = ' ';
	private int dir = -1; // 0 = Left, 1 == Down, 2 = Up, 3 = Right
	
	public Arrow() {}
	
	public Arrow(int direction) {
		this.dir = direction;
	}
	
	public Arrow(char letter, int direction) {
		this.let = letter;
		this.dir = direction;
	}
	
	public char getLetter() { return this.let; }
	public int getDirection() { return this.dir; }
	
	public void setLetter(char letter) { this.let = letter; }
	
}
