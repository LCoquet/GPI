package processing;

import data.Guardian;
import data.Human;
import data.Prison;
import data.Prisoner;
import processing.message.PrisonerFoundMessage;

public class Detector {
	
	public Prison prison ;
	public int[] sortie ;
	
	public Detector(Prison prison) {
		
		this.prison = prison ;
		
		for(int i = 0; i < 20; i ++) {
			for(int j = 0; j < 20; j ++) {
				if(prison.getMap()[i][j] == 'd')
					sortie = new int[] {i, j};
			}
		}
		
	}
	
	
	public void detect(Human h) {
		
		int pos[] = h.getPos();
		
		switch(h.getDirection()) {
			case 3 :
				for(int i = 0; i < 3; i++) {
					for(int j = -1; j < 2; j++) {	
						int checkCase[] = new int[] { pos[0] + 1 + i, pos[1] + j };
						if(((checkCase[0] >= 0) && (checkCase[0] < 20)) && ((checkCase[1] >= 0) && (checkCase[1] < 20))) {
							checker(h, checkCase);
						}		
					}
				}
				break;
			
			case 2 :
				for(int i = -2 ; i < 1 ; i++) {
					for(int j = -1; j < 2; j++) {	
						int checkCase[] = new int[] { pos[0] - 1 + i, pos[1] + j };
						if(((checkCase[0] >= 0) && (checkCase[0] < 20)) && ((checkCase[1] >= 0) && (checkCase[1] < 20))) {
							checker(h, checkCase);
						}	
					}
				}
				break;
				
			case 0 :
				for(int i = -1; i < 2; i++) {
					for(int j = 0; j < 3; j++) {	
						int checkCase[] = new int[] { pos[0] + i, pos[1] + 1 + j };
						if(((checkCase[0] >= 0) && (checkCase[0] < 20)) && ((checkCase[1] >= 0) && (checkCase[1] < 20))) {
							checker(h, checkCase);
						}
					}
				}
				break;
				
			case 1 :
				for(int i = -1; i < 2; i++) {
					for(int j = -2; j < 1; j++) {	
						int checkCase[] = new int[] { pos[0] + i, pos[1]  - 1 + j };
						if(((checkCase[0] >= 0) && (checkCase[0] < 20)) && ((checkCase[1] >= 0) && (checkCase[1] < 20))) {
							checker(h, checkCase);
						}		
					}
				}
				break;			
		}
		
	}
	
	public void checker(Human h, int checkCase[]) {
		
		if(h.getClass() == Prisoner.class)
		{
			if((checkCase[0] == sortie[0]) && (checkCase[1] == sortie[1])){
				int[] objectivePos = new int[] {checkCase[0], checkCase[1]} ;
				h.setObjectivePos(objectivePos);
			}
			else {
				for(Human human : prison.getHumans()) {
					if(human.getClass() == Guardian.class) {
						if((human.getPos()[0] == checkCase[0]) && (human.getPos()[1] == checkCase[1])) {
							//Direction set->Op
						}
					}
				}
			}
		}
		
		if(h.getClass() == Guardian.class)
		{
			for(Human human : prison.getHumans()) {
				if(human.getClass() == Prisoner.class) {
					if((human.getPos()[0] == checkCase[0]) && (human.getPos()[1] == checkCase[1])) {
						int[] objectivePos = new int[] {checkCase[0], checkCase[1]} ;
						//h.setObjectivePos(objectivePos);
						PrisonerFoundMessage pfm = new PrisonerFoundMessage(objectivePos, prison);
						pfm.send();
					}
				}
			}
		}
		
	}
	
//	public static void main(String[] args) {
//		for(int i = -2; i < 1; i++) {
//			for(int j = -1; j < 2; j++) {	
//				System.out.println("i : " + i + " j : " + j);
//			}
//		}
//	}
	
}
