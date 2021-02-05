package data;

import java.awt.Graphics;

import processing.MoveVisitor;
import processing.PaintVisitor;

public class Prisoner extends Human {

	public Prisoner(String name, int[] pos) {
		super(name, pos);
	}

	@Override
	public void accept(MoveVisitor v) {
		v.visit(this);
	}

	@Override
	public void accept(PaintVisitor v, Graphics g) {
		v.visit(this, g);
	}
	
}
