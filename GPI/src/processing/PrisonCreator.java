package processing;

import java.util.ArrayList;

import data.Guardian;
import data.Human;
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
		
		ArrayList<Human> al = new ArrayList<>();
		al.add(new Prisoner("Léo", new int[] {10, 10}));
		al.add(new Guardian("Yohan", new int[] {1, 7}));
		al.add(new Guardian("Alexandre", new int[] {13, 17}));
		
		return new Prison(map, al);
		
	}
	
}
