package processing;

import data.Guardian;
import data.Prisoner;

/**
 * @brief Visitor Design Pattern Class for guardian and prisonners
 * 
 * @author CHABOT Yohan, COQUET Leo, DE SOUSA Julia, GASTEBOIS Emma, HANG Alexandre, POUPET Maria-Lorena
 *
 */
public interface Visitor {

	public abstract void visit(Prisoner prisoner);
	
	public abstract void visit(Guardian guardian);
	
}
