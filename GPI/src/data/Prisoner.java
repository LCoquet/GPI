package data;

import processing.Visitor;

public class Prisoner extends Human {

	public Prisoner(String name, int[] pos) {
		super(name, pos);
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
	
}
