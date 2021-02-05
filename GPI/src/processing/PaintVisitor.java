package processing;

import java.awt.Color;
import java.awt.Graphics;

import data.Guardian;
import data.Prisoner;

public class PaintVisitor {

	public void visit(Prisoner prisoner, Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(prisoner.getPos()[1]*30, prisoner.getPos()[0]*30, 30, 30);
	}
	
	public void visit(Guardian guardian, Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(guardian.getPos()[1]*30, guardian.getPos()[0]*30, 30, 30);
	}
	
}
