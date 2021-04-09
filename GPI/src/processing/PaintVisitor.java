package processing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import data.Guardian;
import data.Human;
import data.Prisoner;

/**
 * @brief Visitor to paint the elements of the prison
 * 
 * @author CHABOT Yohan, COQUET Leo, DE SOUSA Julia, GASTEBOIS Emma, HANG Alexandre, POUPET Maria-Lorena
 *
 */
public class PaintVisitor implements Visitor {

	private Graphics g;
	private Image humans;
	
	public PaintVisitor(Graphics g) {
		this.g = g;
		try {
            humans = ImageIO.read(new File("ressources/img/humans.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } 
	}
	
	public void visit(Prisoner prisoner) {
		int i = prisoner.getPos()[0];
		int j = prisoner.getPos()[1];
		auraPaint(prisoner);
		g.drawImage(humans, j*30, i*30, j*30+30, i*30+30, 0, 0, 30, 30, null);
	}
	
	public void visit(Guardian guardian) {
		int i = guardian.getPos()[0];
		int j = guardian.getPos()[1];
		auraPaint(guardian);
		g.drawImage(humans, j*30, i*30, j*30+30, i*30+30, 30, 0, 60, 30, null);
	}
	
	public void auraPaint(Human h) {

		int pos[] = h.getPos();
		g.setColor(Color.GREEN);
		
//		System.out.println("x : " + pos[0] + " y : " + pos[1]);
		
		switch(h.getDirection()) {
			case 3 :
				for(int i = 0; i < 3; i++) {
					for(int j = -1; j < 2; j++) {	
						int checkCase[] = new int[] { pos[0] + 1 + i, pos[1] + j };
						if(((checkCase[0] >= 0) && (checkCase[0] < 20)) && ((checkCase[1] >= 0) && (checkCase[1] < 20))) {
//							System.out.println("(x,y) : " + checkCase[0] + " " + checkCase[1]);
							g.drawRect(checkCase[1]*30, checkCase[0]*30, 30, 30);
						}
					}
				}
				break;
			
			case 2 :
				for(int i = -2 ; i < 1 ; i++) {
					for(int j = -1; j < 2; j++) {	
						int checkCase[] = new int[] { pos[0] - 1 + i, pos[1] + j };
						if(((checkCase[0] >= 0) && (checkCase[0] < 20)) && ((checkCase[1] >= 0) && (checkCase[1] < 20))) {
//							System.out.println("(x,y) : " + checkCase[0] + " " + checkCase[1]);
							g.drawRect(checkCase[1]*30, checkCase[0]*30, 30, 30);
						}
					}
				}
				break;
				
			case 0 :
				for(int i = -1; i < 2; i++) {
					for(int j = 0; j < 3; j++) {	
						int checkCase[] = new int[] { pos[0] + i, pos[1] + 1 + j };
						if(((checkCase[0] >= 0) && (checkCase[0] < 20)) && ((checkCase[1] >= 0) && (checkCase[1] < 20))) {
//							System.out.println("(x,y) : " + checkCase[0] + " " + checkCase[1]);
							g.drawRect(checkCase[1]*30, checkCase[0]*30, 30, 30);
						}
					}
				}
				break;
				
			case 1 :
				for(int i = -1; i < 2; i++) {
					for(int j = -2; j < 1; j++) {	
						int checkCase[] = new int[] { pos[0] + i, pos[1] - 1 + j };
						if(((checkCase[0] >= 0) && (checkCase[0] < 20)) && ((checkCase[1] >= 0) && (checkCase[1] < 20))) {
//							System.out.println("(x,y) : " + checkCase[0] + " " + checkCase[1]);
							g.drawRect(checkCase[1]*30, checkCase[0]*30, 30, 30);
						}
					}
				}
				break;			
		}
	}
}
