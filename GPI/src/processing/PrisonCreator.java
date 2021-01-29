package processing;

import data.Guardian;
import data.Prison;
import data.Prisoner;

public class PrisonCreator {

	public static Prison creation() {
		
		char[][] map = new char[20][20];
		
		for (int i = 0; i < 20; i ++) {
			for(int j = 0; j < 20; j ++) {
				if(i == 13 && j == 0)
					map[i][j] = 'd';
				else if(i == 0 || j == 0 || i == 19 || j == 19)
					map[i][j] = 'w';
				else if(i == 7 && (j < 15))
					map[i][j] = 'w';
				else if(j == 3 && (i < 4))
					map[i][j] = 'w';
				else
					map[i][j] = 'f';
			}
		}
		Guardian guardian1 = new Guardian("Yohan", new int[] {1, 7});
		Guardian guardian2 = new Guardian("Alexandre", new int[] {14, 12});
		Prisoner prisoner = new Prisoner("Léo", new int[] {1, 1});
		
		return new Prison(map, guardian1, guardian2, prisoner);
		
	}
	
}
