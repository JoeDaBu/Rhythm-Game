package gamejam.rhythm.game.Generator;

import java.util.ArrayList;

public class ArrowSequence {

	
	public WordPicker wp = new WordPicker();
	public ArrayList<Arrow> arrowSeq;
	public int index;
	
	public ArrowSequence() {
		index = 0;
	}
	
	public ArrowSequence(int i) {
		index = 0;
		//** TEST
		ArrayList<Integer> TEST_DIRECTIONS = new ArrayList<>();
		TEST_DIRECTIONS.add(1);
		TEST_DIRECTIONS.add(3);
		TEST_DIRECTIONS.add(2);
		TEST_DIRECTIONS.add(4);
		TEST_DIRECTIONS.add(-1);
		AssignLetters(TEST_DIRECTIONS, "");
		//** TEST
		
	}
	
	public void addArrow(Arrow arrow) {
		arrowSeq.add(arrow);
	}
	
	public void AssignLetters(ArrayList<Integer> directions, String word) {
		arrowSeq = new ArrayList<>();
		
		for(int i = 0; i < directions.size(); i++)
			arrowSeq.add(new Arrow(wp.letterGenerator(word),directions.get(i)));
	}
	
	public Arrow getNextArrow() {
		if(index == arrowSeq.size())
			index = 0;
		return arrowSeq.get(index++);
	}
}
