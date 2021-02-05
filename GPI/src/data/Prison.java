package data;

import java.util.ArrayList;

/*
 * Contains all the data for a prison, with the map and all the humans.
 * 
 */

public class Prison {

	private char map[][] = new char[20][20];
	private ArrayList<Human> humans;
	
	
	public Prison(char[][] map, ArrayList<Human> humans) {
		this.map = map;
		this.humans = humans;
	}
	
	public char[][] getMap() {
		return map;
	}
	public void setMap(char[][] map) {
		this.map = map;
	}	
	public ArrayList<Human> getHumans() {
		return humans;
	}
	public void setHumans(ArrayList<Human> humans) {
		this.humans = humans;
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
