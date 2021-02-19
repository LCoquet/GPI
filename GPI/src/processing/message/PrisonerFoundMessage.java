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
		for(Human h : getPrison().getHumans())
			if(h.getClass() == Guardian.class)
				h.setObjectivePos(getContent());
	}
	
}
