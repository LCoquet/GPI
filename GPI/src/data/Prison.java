package data;

import java.util.ArrayList;

/**
 * @brief Prison Data Class
 * 
 * @author CHABOT Yohan, COQUET Leo, DE SOUSA Julia, GASTEBOIS Emma, HANG Alexandre, POUPET Maria-Lorena
 *
 */
public class Prison {

	private char map[][] = new char[20][20];
	private ArrayList<Guardian> guardians = new ArrayList<Guardian>();
	private ArrayList<Prisoner> prisoners = new ArrayList<Prisoner>();
	
	public Prison(char[][] map) {
		this.map = map;
	}
	
	public char[][] getMap() {
		return map;
	}
	public void setMap(char[][] map) {
		this.map = map;
	}	

	public ArrayList<Guardian> getGuardians() {
		return guardians;
	}

	public void setGuardians(ArrayList<Guardian> guardians) {
		this.guardians = guardians;
	}

	public ArrayList<Prisoner> getPrisoners() {
		return prisoners;
	}

	public void setPrisoners(ArrayList<Prisoner> prisoners) {
		this.prisoners = prisoners;
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
