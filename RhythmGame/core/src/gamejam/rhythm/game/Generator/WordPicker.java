package gamejam.rhythm.game.Generator;

import java.util.*;

import com.badlogic.gdx.Gdx;

public class WordPicker {

	final String file = "assets/Words.txt";
	public ArrayList<String> wordList;
	
	public WordPicker() {
		resetWordList();
	}
	
	public String getWord(int length) { // length = 4,5,7,8
		boolean flag = true;
		for(String word : wordList)
			if(word.length() == length) {
				flag = false;
				break;
			}
		if(flag)
			resetWordList();
		
		while(true) { // Yes this could potentially run forever, cry about it
			Collections.shuffle(wordList);
			if(wordList.get(0).length() == length)
				return wordList.remove(0); // This is so the same word wont come up twice until reset 
		}
	}
	
	public void resetWordList() {
		wordList = new ArrayList<>();
		
		String wordsArray[] = Gdx.files.local("words.txt").readString().split("\\r?\\n");
		for(String word : wordsArray)
			wordList.add(word);
	}	
	
	public char letterGenerator(String word) {
		return ' ';
	}
}
