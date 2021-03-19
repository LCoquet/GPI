package processing.message;

import data.Guardian;
import data.Human;
import data.Prison;

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
