package test.unit;

import javax.swing.JFrame;

import data.Guardian;
import data.Prison;
import ihm.Simulation;
import processing.PrisonCreator;

public class OobGuardian {
	
	private final static int GUARDIANSAMOUNT = 100000;
	private final static int PRISONERAMOUNT	 = 1;

	public static void main(String[] args) {
		
		Prison prison = PrisonCreator.creation(0, PRISONERAMOUNT);
		for(int i = 0; i < GUARDIANSAMOUNT; i ++)
			prison.getGuardians().add(new Guardian("name", new int[] {19, 9}));
		
		Simulation sim = new Simulation(new JFrame(), 0, 0);
		sim.setPrison(prison);
		Thread t = new Thread(sim);
		t.start();
		
	}
	
}
