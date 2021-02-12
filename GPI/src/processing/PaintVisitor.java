package processing;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import data.Guardian;
import data.Prisoner;

public class PaintVisitor implements Visitor {

	private Graphics g;
	private Image humans;
	
	public PaintVisitor(Graphics g) {
		this.g = g;
		try {
            humans = ImageIO.read(new File("GPI/ressources/img/humans.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } 
	}
	
	public void visit(Prisoner prisoner) {
		int i = prisoner.getPos()[0];
		int j = prisoner.getPos()[1];
		g.drawImage(humans, j*30, i*30, j*30+30, i*30+30, 0, 0, 30, 30, null);
	}
	
	public void visit(Guardian guardian) {
		int i = guardian.getPos()[0];
		int j = guardian.getPos()[1];
		g.drawImage(humans, j*30, i*30, j*30+30, i*30+30, 30, 0, 60, 30, null);
	}
	
}
