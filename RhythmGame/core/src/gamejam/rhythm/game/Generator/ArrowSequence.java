package gamejam.rhythm.game.Generator;

import java.util.ArrayList;

public class ArrowSequence {

	public WordPicker wp = new WordPicker();
	public ArrayList<Arrow> arrowSeq;
	
	public int index;
	public String curWord;
	public float spaceFreq, additionalChance;
	
	public ArrowSequence() {
		index = 0;
		spaceFreq = 0.5f;
		additionalChance = 0;
		curWord = "";
	}
	
	public ArrowSequence(int i) {
		index = 0;
		curWord = "test";
		//** TEST
		ArrayList<Integer> TEST_DIRECTIONS = new ArrayList<>();
		TEST_DIRECTIONS.add(0);
		TEST_DIRECTIONS.add(2);
		TEST_DIRECTIONS.add(1);
		TEST_DIRECTIONS.add(3);
		TEST_DIRECTIONS.add(-1);
		AssignLetters(TEST_DIRECTIONS);
		//** TEST
		
	}
	
	public void addArrow(Arrow arrow) {
		arrowSeq.add(arrow);
	}
	
	public void AssignLettersDefault(ArrayList<Integer> directions) {
		arrowSeq = new ArrayList<>();
		
		for(int i = 0; i < directions.size(); i++)
			arrowSeq.add(new Arrow(' ',directions.get(i)));
	}
	
	public void AssignLetters(ArrayList<Integer> directions) {
		arrowSeq = new ArrayList<>();
		
		for(int i = 0; i < directions.size(); i++)
			arrowSeq.add(new Arrow(wp.letterGenerator(curWord, spaceFreq, additionalChance),directions.get(i)));
	}
	
	public void ResetLetters() {
		index = 0;
		for(int i = 0; i < arrowSeq.size(); i++)
			arrowSeq.get(i).setLetter(wp.letterGenerator(curWord, spaceFreq, additionalChance));
	}
	
	public Arrow getNextArrow() {
		if(index == arrowSeq.size())
			ResetLetters();
		
		return arrowSeq.get(index++);
	}
	
	public void setCurrentWord(String curWord) { this.curWord = curWord; }
	public void setSpaceFrequency(float spaceFreq) { this.spaceFreq = spaceFreq; }
	public void setAdditionalChance(float additionalChance) { this.additionalChance = additionalChance; }
}
