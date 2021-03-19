package processing.simulationcreation;

import java.util.ArrayList;

import data.Prison;

public class HumanPlacer {

	private int placed = 0;
	private Prison prison;
	private ArrayList<int[]> positions = new ArrayList<int[]>();
	
	public HumanPlacer(int humanAmount, Prison prison) {
		this.prison = prison;
		int i = 0;
		while (i < humanAmount) {
			int posX = (int)(Math.random()*20);
			int posY = (int)(Math.random()*20);
			int[] pos = new int[] {posX, posY};
			if(verifier(pos)) {
				positions.add(pos);
				i ++;
			}
		}
	}
	
	public int[] place() {
		int[] res = positions.get(placed);
		placed ++;
		return res;
	}
	
	private boolean verifier(int[] pos) {
		if(prison.getMap()[pos[0]][pos[1]] == 'f') {
			for(int[] position : positions)
				if(position[0] == pos[0] && position[1] == pos[1])
					return false;
			return true;
		} 
		return false;
	}
	
}
