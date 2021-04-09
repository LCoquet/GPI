package data;

import processing.Visitor;

/**
 * @brief Guardian Data class
 * 
 * @author CHABOT Yohan, COQUET Leo, DE SOUSA Julia, GASTEBOIS Emma, HANG Alexandre, POUPET Maria-Lorena
 *
 */
public class Guardian extends Human {

	public Guardian(String name, int[] pos) {
		super(name, pos);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
	
}
