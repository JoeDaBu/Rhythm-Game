package gamejam.rhythm.game.fileio;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import gamejam.rhythm.game.Generator.ArrowSequence;

public class LevelFileReader {
	public static LevelClass getLevel(String fileName) {
		FileHandle handle = Gdx.files.local("levels/"+fileName);
		String text = handle.readString();
		String lines[] = text.split("\\r?\\n");
		
		if(lines.length < 6) {
			System.out.println("LevelFileReader: invalid file length");
			return null;
		}
		
		String name = lines[0];
		String music = lines[1];
		int diff;
		int arrowSpeed;
		long spawnCD;
		try {
			diff = Integer.valueOf(lines[2]); 
			arrowSpeed = Integer.valueOf(lines[3]); 
			spawnCD = Long.valueOf(lines[4]);
		}
		catch(NumberFormatException ex) {
			System.out.println("LevelFileReader: invalid number");
			return null;
		}
		
		ArrayList<Integer> dirs = new ArrayList<>();
		for(int i = 5; i < lines.length; i++) {
			try {
				int dir = Integer.valueOf(lines[i]);
				if(dir >= -1 && dir<=3) {
					dirs.add(dir);
				}
			}
			catch(NumberFormatException ex) {
				System.out.println("LevelFileReader: invalid sequence number");
				return null;
			}
		}
		//System.out.println(dirs.size());
		ArrowSequence seq = new ArrowSequence();
		seq.AssignLetters(dirs);
		
		return new LevelClass(name, music, diff, arrowSpeed, spawnCD, seq);
	}
}
