package data;

import processing.Visitor;


/**
 * @brief Prisonner Data Class
 * 
 * @author CHABOT Yohan, COQUET Leo, DE SOUSA Julia, GASTEBOIS Emma, HANG Alexandre, POUPET Maria-Lorena
 *
 */
public class Prisoner extends Human {

	public Prisoner(String name, int[] pos) {
		super(name, pos);
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
	
}
