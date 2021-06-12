package gamejam.rhythm.game.Generator;

import java.io.*;
import java.util.*;

public class WordPicker {

	final String file = "assets/Words.txt";
	public ArrayList<String> wordList;
	
	public String getWord(int length) { // length = 4,5,7,8
		while(true) {
			Collections.shuffle(wordList);
			if(wordList.get(0).length() == length)
				return wordList.remove(0);
		}
	}
	
	public void resetWordList() {
		wordList = new ArrayList<>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
		    String line;
		    while ((line = br.readLine()) != null) {
		    	wordList.add(line);
		    }
		    br.close();
		} catch (Exception e) {
			System.out.println("Word Picker -> getNewWord => ERROR");
		}
	}	
}
