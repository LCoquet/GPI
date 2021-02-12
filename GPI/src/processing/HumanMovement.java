package processing;

import data.Human;
import data.Prison;

public class HumanMovement {

	private Prison prison;
	
	public HumanMovement(Prison prison) {
		this.prison = prison;
	}
	
	public void move(Human h) {
		if(h.getObjectivePos() == null)
			randomMove(h);
		else
			reachObjective(h);
	}
	
	private void randomMove(Human h) {
        int direction = (int) (Math.random()*4);
        int xCheck = h.getPos()[0];
        int yCheck = h.getPos()[1];
        switch(direction)
        {
            case 0:
                //d�placement vers la droite
            	yCheck++;
                break;
            case 1:
                //d�placement vers la gauche
            	yCheck--;
                break;
            case 2 :
                //d�placement vers le haut
            	xCheck--;
                break;
            case 3 :
                //d�placement vers le bas
            	xCheck++;
                break;
        }

        if(prison.getMap()[xCheck][yCheck] != 'w') {
	            h.setPos(new int[] {xCheck, yCheck});
                h.setDirection(direction);
        }
	}
	
	private void reachObjective(Human h) {
		int vi = h.getObjectivePos()[0] - h.getPos()[0];
		int vj = h.getObjectivePos()[1] - h.getPos()[1];
        int xCheck = h.getPos()[0];
        int yCheck = h.getPos()[1];
        int direction;
		if(Math.abs(vi) > Math.abs(vj)) {
			if(vi < 0) {
				xCheck --;
				direction = 1;
			} else {
				xCheck ++;
				direction = 0;
			}
		} else {
			if(vj < 0) {
				yCheck --;
				direction = 2;
			} else {
				yCheck ++;
				direction = 3;
			}
		}
		
		if(prison.getMap()[xCheck][yCheck] != 'w') {
			h.setPos(new int[] {xCheck, yCheck});
            h.setDirection(direction);
		} else {
	        xCheck = h.getPos()[0];
	        yCheck = h.getPos()[1] + 1;
	        h.setPos(new int[] {xCheck, yCheck});
            h.setDirection(direction);
		}
	}
	
}