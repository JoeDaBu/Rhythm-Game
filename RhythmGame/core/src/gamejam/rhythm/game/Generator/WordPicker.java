package gamejam.rhythm.game.Generator;

import java.util.*;

import com.badlogic.gdx.Gdx;

public class WordPicker {

	final String file = "assets/Words.txt";
	public ArrayList<String> wordList;
	
	public float[] letterFreq;
	
	public WordPicker() {
		resetWordList();
		
		letterFreq = new float[26];
		int total = 0;
		for(String word : wordList)
			for(int i = 0; i < word.length(); i++) {
				letterFreq[word.charAt(i) - 'a']++;
				total++;
			}
		
		for(int i = 0; i < letterFreq.length; i++)
			letterFreq[i] /= total;
		
		/*
		for(int i = 0; i < letterFreq.length; i++)
			System.out.println((char)(i+97) + " | " + letterFreq[i]);
		*/
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
	
	public char letterGenerator(String word, float spaceFreq, float additionalChance) {
		if(Math.random() < spaceFreq)
			return ' ';
		
		float randomNum = (float)Math.random() * (1 + additionalChance);
		
		for(int i = 0; i < letterFreq.length; i++) {
			if(word.contains("" + (char)(i+97)))
				randomNum -= (letterFreq[i] + additionalChance/word.length());
			else
				randomNum -= letterFreq[i];
						
			if(randomNum < 0)
				return (char)(i+65);
		}
		
		return ' ';
	}
}
