package processing.message;

import data.Guardian;
import data.Prison;

/**
 * @brief Treatment Class for the vision of all guardian
 * 
 * @author CHABOT Yohan, COQUET Leo, DE SOUSA Julia, GASTEBOIS Emma, HANG Alexandre, POUPET Maria-Lorena
 *
 */
public class PrisonerFoundMessage extends Message<int[]> {

	public PrisonerFoundMessage(int[] content, Prison prison) {
		super(content, prison);
	}

	@Override
	public void send() {
		
		for(Guardian h : getPrison().getGuardians())
			h.setObjectivePos(getContent());
	}
	
}
