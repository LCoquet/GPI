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
                //déplacement vers la droite
            	yCheck++;
                break;
            case 1:
                //déplacement vers la gauche
            	yCheck--;
                break;
            case 2 :
                //déplacement vers le haut
            	xCheck--;
                break;
            case 3 :
                //déplacement vers le bas
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
				direction = 2;
			} else {
				xCheck ++;
				direction = 3;
			}
		} else {
			if(vj < 0) {
				yCheck --;
				direction = 1;
			} else {
				yCheck ++;
				direction = 0;
			}
		}
		
		if(!oob(xCheck, yCheck)) {
			if(!isAWall(xCheck, yCheck)) {
				h.setPos(new int[] {xCheck, yCheck});
	            h.setDirection(direction);
			} else {
				if(direction == 0 || direction == 1){
					if(vj < 0) {
						xCheck = h.getPos()[0] - 1;
						yCheck = h.getPos()[1];
						if(!oob(xCheck, yCheck))
							if(isAWall(xCheck, yCheck)) {
								xCheck = h.getPos()[0] + 1;
								yCheck = h.getPos()[1];
							}
					} else {
						xCheck = h.getPos()[0] + 1;
						yCheck = h.getPos()[1];
						if(!oob(xCheck, yCheck))
							if(isAWall(xCheck, yCheck)) {
								xCheck = h.getPos()[0] - 1;
								yCheck = h.getPos()[1];
							}
					}
				} else {
					if(vi < 0) {
						xCheck = h.getPos()[0];
						yCheck = h.getPos()[1] - 1;
						if(!oob(xCheck, yCheck))
							if(isAWall(xCheck, yCheck)) {
								xCheck = h.getPos()[0];
								yCheck = h.getPos()[1] + 1;
							}
					} else {
						xCheck = h.getPos()[0];
						yCheck = h.getPos()[1] + 1;
						if(!oob(xCheck, yCheck))
							if(isAWall(xCheck, yCheck)) {
								xCheck = h.getPos()[0];
								yCheck = h.getPos()[1] - 1;
							}
					}
				}
		        h.setPos(new int[] {xCheck, yCheck});
	            h.setDirection(direction);
			}
		}
		if(h.getPos()[0] == h.getObjectivePos()[0] && h.getPos()[1] == h.getObjectivePos()[1])
			h.setObjectivePos(null);
	}
	
	private boolean oob(int posX, int posY) {
		return posX < 0 && posX >= 20 && posY < 0 && posY >= 20;
	}
	
	private boolean isAWall(int posX, int posY) {
		return prison.getMap()[posX][posY] == 'w';
	}
	
}
