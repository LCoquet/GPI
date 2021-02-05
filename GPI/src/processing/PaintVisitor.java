package processing;

import java.awt.Color;
import java.awt.Graphics;

import data.Guardian;
import data.Prisoner;

public class PaintVisitor implements Visitor {

	private Graphics g;
	
	public PaintVisitor(Graphics g) {
		this.g = g;
	}
	
	public void visit(Prisoner prisoner) {
		g.setColor(Color.ORANGE);
		g.fillRect(prisoner.getPos()[1]*30, prisoner.getPos()[0]*30, 30, 30);
	}
	
	public void visit(Guardian guardian) {
		g.setColor(Color.BLUE);
		g.fillRect(guardian.getPos()[1]*30, guardian.getPos()[0]*30, 30, 30);
	}
	
}
