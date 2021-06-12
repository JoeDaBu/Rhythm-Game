package gamejam.rhythm.game.fileio;

import gamejam.rhythm.game.Generator.ArrowSequence;

public class LevelClass{
	private String name;
	private String music;
	private int difficulty;
	private int arrowSpeed;
	private long spawnCD;
	private ArrowSequence sequence;
	
	public LevelClass(String name, String music, int difficulty, int arrowSpeed, long spawnCD, ArrowSequence sequence) {
		this.name = name;
		this.music = music;
		this.difficulty = difficulty;
		this.arrowSpeed = arrowSpeed;
		this.spawnCD = spawnCD;
		this.sequence = sequence;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public String getMusic() {
		return music;
	}

	public void setMusic(String music) {
		this.music = music;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public int getArrowSpeed() {
		return arrowSpeed;
	}

	public void setArrowSpeed(int arrowSpeed) {
		this.arrowSpeed = arrowSpeed;
	}

	public long getSpawnCD() {
		return spawnCD;
	}

	public void setSpawnCD(long spawnCD) {
		this.spawnCD = spawnCD;
	}

	public ArrowSequence getSequence() {
		return sequence;
	}

	public void setSequence(ArrowSequence sequence) {
		this.sequence = sequence;
	}
	
	
	
	
}
