package gamejam.rhythm.game.Generator;

import java.util.ArrayList;

public class ArrowSequence {

	public WordPicker wp = new WordPicker();
	public ArrayList<Arrow> arrowSeq;
	
	public ArrowSequence() {}
	
	public void addArrow(Arrow arrow) {
		arrowSeq.add(arrow);
	}
	
	public ArrowSequence AssignLetters(ArrayList<Arrow> arrows, String word) {
		ArrowSequence arrowSequence = new ArrowSequence();
		
		for(int i = 0; i < arrows.size(); i++) {
			arrowSequence.addArrow(new Arrow(wp.letterGenerator(word),arrows.get(i).getDirection()));
		}
		
		return arrowSequence;
	}
}
