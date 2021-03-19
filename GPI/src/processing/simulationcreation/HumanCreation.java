package processing.simulationcreation;

import java.util.ArrayList;

import data.Guardian;
import data.Human;
import data.Prison;
import data.Prisoner;

public class HumanCreation {

	public static void create(int guardianAmount, int prisonerAmount, Prison prison){
		HumanPlacer hp = new HumanPlacer(guardianAmount + prisonerAmount, prison);
		ArrayList<Human> humans = prison.getHumans();
		for(int i = 0; i < guardianAmount; i ++) {
			humans.add(new Guardian("Name", hp.place()));
		}
		for(int i = 0; i < prisonerAmount; i ++) {
			humans.add(new Prisoner("Name", hp.place()));
		}
	}
	
}
