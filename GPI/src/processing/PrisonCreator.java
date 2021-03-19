package processing;

import data.Prison;
import processing.simulationcreation.HumanCreation;

public class PrisonCreator {

	public static Prison creation(int guardianAmount, int prisonerAmount) {
		
		char[][] map = new char[20][20];
		
		for (int i = 0; i < 20; i ++) {
			for(int j = 0; j < 20; j ++) {
				/* V1 Map
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
					*/
				
				/* V2 Map */
				if((i == 19 && j == 9) || (i==19 && j==10))
					map[i][j] = 'd';
				else if(i%3 == 0 && i!=18) {
					if(j<4 || j==7 || j==12||j>15)
						map[i][j] = 'w';
					else if(i == 0 || j == 0 || i == 19 || j == 19)
						map[i][j] = 'w';
					else
						map[i][j] = 'f';
				}
				else if(i == 0 || j == 0 || i == 19 || j == 19)
					map[i][j] = 'w';
				else
					map[i][j] = 'f';
			}
		}
		
		Prison prison = new Prison(map);
		HumanCreation.create(guardianAmount, prisonerAmount, prison);
		return prison;
		
	}
	
}
