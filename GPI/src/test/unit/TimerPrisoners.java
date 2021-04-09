package test.unit;

import javax.swing.JFrame;

import data.Prison;
import ihm.Simulation;
import processing.Detector;
import processing.PrisonCreator;

public class TimerPrisoners {
	private final static int GUARDIANSAMOUNT = 1; // Ne pas faire 10 millions
	private final static int PRISONERAMOUNT	 = 10;

	public static void main(String[] args) {
		Prison prison = PrisonCreator.creation(GUARDIANSAMOUNT, PRISONERAMOUNT);
		Simulation sim = new Simulation(new JFrame(), 0, 0);
		sim.setPrison(prison);
		sim.setDetector(new Detector(prison));
		Thread t = new Thread(sim);
		t.start();	
	}
	
}
