package test.unit;

import javax.swing.JFrame;

import data.Guardian;
import data.Prison;
import ihm.Simulation;
import processing.Detector;
import processing.PrisonCreator;

public class NoHumans {
	private final static int GUARDIANSAMOUNT = 0; // Ne pas faire 10 millions
	private final static int PRISONERAMOUNT	 = 0;

	public static void main(String[] args) {
		
		Prison prison = PrisonCreator.creation(0, PRISONERAMOUNT);
		for(int i = 0; i < GUARDIANSAMOUNT; i ++)
			prison.getGuardians().add(new Guardian("name", new int[] {19, 9}));
		
		Simulation sim = new Simulation(new JFrame(), 0, 0);
		sim.setPrison(prison);
		sim.setDetector(new Detector(prison));
		Thread t = new Thread(sim);
		t.start();
		
	}
}
