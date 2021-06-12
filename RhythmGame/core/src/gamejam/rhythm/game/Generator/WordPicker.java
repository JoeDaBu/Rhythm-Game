package gamejam.rhythm.game.Generator;

import java.io.*;
import java.util.*;

public class WordPicker {

	final String file = "assets/Words.txt";
	public ArrayList<String> wordList;
	
	public WordPicker() {
		resetWordList();
	}
	
	public String getWord(int length) { // length = 4,5,7,8
		boolean flag = false;
		for(String word : wordList)
			if(word.length() == length)
				flag = true;
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
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
		    String line;
		    while ((line = br.readLine()) != null)
		    	wordList.add(line);
		    br.close();
		} catch (Exception e) {
			System.out.println("Word Picker -> getNewWord => ERROR");
		}
	}	
	
	public char letterGenerator(String word) {
		return ' ';
	}
}
