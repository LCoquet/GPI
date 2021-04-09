package processing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import data.Guardian;
import data.Human;
import data.Prison;
import data.Prisoner;
import processing.message.PrisonerFoundMessage;

public class Detector {
	
	public Prison prison ;
	//public int[] sortie ;
	public ArrayList<int[]> sorties;
	private BufferedWriter bw;
	
	public Detector(Prison prison) {
		
		try {
			bw = new BufferedWriter(new FileWriter("log.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		} 
		this.prison = prison ;
		sorties = new ArrayList<int[]>();
		for(int i = 0; i < 20; i ++) {
			for(int j = 0; j < 20; j ++) {
				if(prison.getMap()[i][j] == 'd')
					sorties.add(new int[] {i, j});
			}
		}
	}
	
	
	public void detect(Human h, BufferedWriter out) {
		
		int pos[] = h.getPos();
		
		switch(h.getDirection()) {
			case 3 :
				for(int i = 0; i < 3; i++) {
					for(int j = -1; j < 2; j++) {	
						int checkCase[] = new int[] { pos[0] + 1 + i, pos[1] + j };
						if(((checkCase[0] >= 0) && (checkCase[0] < 20)) && ((checkCase[1] >= 0) && (checkCase[1] < 20))) {
							checker(h, checkCase, out);
						}		
					}
				}
				break;
			
			case 2 :
				for(int i = -2 ; i < 1 ; i++) {
					for(int j = -1; j < 2; j++) {	
						int checkCase[] = new int[] { pos[0] - 1 + i, pos[1] + j };
						if(((checkCase[0] >= 0) && (checkCase[0] < 20)) && ((checkCase[1] >= 0) && (checkCase[1] < 20))) {
							checker(h, checkCase, out);
						}	
					}
				}
				break;
				
			case 0 :
				for(int i = -1; i < 2; i++) {
					for(int j = 0; j < 3; j++) {	
						int checkCase[] = new int[] { pos[0] + i, pos[1] + 1 + j };
						if(((checkCase[0] >= 0) && (checkCase[0] < 20)) && ((checkCase[1] >= 0) && (checkCase[1] < 20))) {
							checker(h, checkCase, out);
						}
					}
				}
				break;
				
			case 1 :
				for(int i = -1; i < 2; i++) {
					for(int j = -2; j < 1; j++) {	
						int checkCase[] = new int[] { pos[0] + i, pos[1]  - 1 + j };
						if(((checkCase[0] >= 0) && (checkCase[0] < 20)) && ((checkCase[1] >= 0) && (checkCase[1] < 20))) {
							checker(h, checkCase, out);
						}		
					}
				}
				break;			
		}
		
	}
	
	public void checker(Human h, int checkCase[], BufferedWriter out) {
		
		if(h.getClass() == Prisoner.class)
		{
			for(int[] sortie : sorties) {
				if((checkCase[0] == sortie[0]) && (checkCase[1] == sortie[1])){
					int[] objectivePos = new int[] {checkCase[0], checkCase[1]} ;
					h.setObjectivePos(objectivePos);
				}
				else {
					for(Guardian g : prison.getGuardians()) {
							if((g.getPos()[0] == checkCase[0]) && (g.getPos()[1] == checkCase[1])) {
								//Direction set->Op Release 3
							}
						}
					}
				}			
		}
		
		if(h.getClass() == Guardian.class)
		{
			for(Prisoner p: prison.getPrisoners()) {
				if((p.getPos()[0] == checkCase[0]) && (p.getPos()[1] == checkCase[1])) {
					int[] objectivePos = new int[] {checkCase[0], checkCase[1]} ;
					//h.setObjectivePos(objectivePos);
					PrisonerFoundMessage pfm = new PrisonerFoundMessage(objectivePos, prison);
					pfm.send();	
			        try {
						out.write("Prisoner found at position : " + objectivePos[0] + "," + objectivePos[1] + "\n");
					} catch (IOException e) {
						e.printStackTrace();
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
