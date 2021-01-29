package data;

/*
 * Contains all the data for a prison, with the map and all the humans.
 * 
 */

public class Prison {

	private char map[][] = new char[20][20];
	private Guardian guardian1;
	private Guardian guardian2;
	private Prisoner prisoner;
	
	public Prison(char[][] map, Guardian guardian1, Guardian guardian2, Prisoner prisoner) {
		this.map = map;
		this.guardian1 = guardian1;
		this.guardian2 = guardian2;
		this.prisoner = prisoner;
	}
	
	public char[][] getMap() {
		return map;
	}
	public void setMap(char[][] map) {
		this.map = map;
	}
	public Guardian getGuardian1() {
		return guardian1;
	}
	public void setGuardian1(Guardian guardian1) {
		this.guardian1 = guardian1;
	}
	public Guardian getGuardian2() {
		return guardian2;
	}
	public void setGuardian2(Guardian guardian2) {
		this.guardian2 = guardian2;
	}
	public Prisoner getPrisoner() {
		return prisoner;
	}
	public void setPrisoner(Prisoner prisoner) {
		this.prisoner = prisoner;
	}
	
	public String toString() {
		String line = "";
		for(int i = 0; i < 20; i ++) {
			for(int j = 0; j < 20; j ++)
				line += map[i][j] + " ";
			line += "\n";
		}
		return line;
	}
	
}
