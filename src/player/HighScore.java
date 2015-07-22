package player;

import java.io.Serializable;

public class HighScore implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private int level;
	
	public HighScore(String n, int l){
		name=n;
		level=l;
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}
	
}
