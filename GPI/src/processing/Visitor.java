package processing;

import data.Guardian;
import data.Prisoner;

public interface Visitor {

	public abstract void visit(Prisoner prisoner);
	
	public abstract void visit(Guardian guardian);
	
}
