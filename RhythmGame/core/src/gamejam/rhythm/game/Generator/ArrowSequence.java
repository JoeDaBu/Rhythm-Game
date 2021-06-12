package gamejam.rhythm.game.Generator;

public class ArrowSequence {

	public Arrow[] arrowSeq;
	
	public ArrowSequence(int length) {
		arrowSeq = new Arrow[length];
	}
	
	public Arrow getArrow(int index) { return arrowSeq[index]; }
}
