package data;

import processing.Visitor;

public class Guardian extends Human {

	public Guardian(String name, int[] pos) {
		super(name, pos);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
	
}
