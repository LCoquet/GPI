package processing;

import data.Guardian;
import data.Human;
import data.Prison;
import data.Prisoner;

public class MoveVisitor implements Visitor {
	
	private Prison prison;
	
	public MoveVisitor(Prison prison) {
		this.prison = prison;
	}

	public void visit(Prisoner prisoner) {
		randomMove(prisoner);
	}
	
	public void visit(Guardian guardian) {
		randomMove(guardian);
	}
	
	public void randomMove(Human h) {
        int ran = (int) (Math.random()*4);
        int pos[] = h.getPos();
        int xCheck = pos[0];
        int yCheck = pos[1];
        switch(ran)
        {
            case 0:
                //déplacement vers la droite
            	xCheck++;
                break;
            case 1:
                //déplacement vers la gauche
            	xCheck--;
                break;
            case 2 :
                //déplacement vers le haut
            	yCheck--;
                break;
            case 3 :
                //déplacement vers le bas
            	yCheck++;
                break;
        }

        if(prison.getMap()[xCheck][yCheck] != 'w') {
                pos[0] = xCheck;
                pos[1] = yCheck;
                h.setDirection(ran);
        }
	}
	
}
