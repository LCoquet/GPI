package processing.simulationcreation;

import java.util.ArrayList;

import data.Guardian;
import data.Prison;
import data.Prisoner;

public class HumanCreation {

	public static void create(int guardianAmount, int prisonerAmount, Prison prison){
		HumanPlacer hp = new HumanPlacer(guardianAmount + prisonerAmount, prison);
		ArrayList<Guardian> guardians = prison.getGuardians();
		ArrayList<Prisoner> prisoners = prison.getPrisoners();
		for(int i = 0; i < guardianAmount; i ++) {
			guardians.add(new Guardian("Name", hp.place()));
		}
		for(int i = 0; i < prisonerAmount; i ++) {
			prisoners.add(new Prisoner("Name", hp.place()));
		}
	}
	
}
