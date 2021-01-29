package ihm;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import data.Prison;
import processing.PrisonCreator;

public class Simulation extends JPanel {

	private static Prison prison;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i = 0; i < 20; i ++) {
			for(int j = 0; j < 20; j ++) {
				switch(prison.getMap()[i][j]) {
				case 'f' :
					g.setColor(Color.WHITE);
					break;
				case 'w' :
					g.setColor(Color.BLACK);
					break;
				case 'd' :
					g.setColor(Color.GREEN);
					break;
				}
				g.fillRect(j*30, i*30, 30, 30);
			}
		}
		
		g.setColor(Color.BLUE);
		int i = prison.getGuardian1().getPos()[0];
		int j = prison.getGuardian1().getPos()[1];
		g.fillRect(j*30, i*30, 30, 30);

		i = prison.getGuardian2().getPos()[0];
		j = prison.getGuardian2().getPos()[1];
		g.fillRect(j*30, i*30, 30, 30);
		
		g.setColor(Color.ORANGE);
		i = prison.getPrisoner().getPos()[0];
		j = prison.getPrisoner().getPos()[1];
		g.fillRect(j*30, i*30, 30, 30);
		g.dispose();
	}
	
	public static void main (String[] args) {
		prison = PrisonCreator.creation();
		JFrame frame = new JFrame("Guardians");
		JPanel simulation = new Simulation();
		frame.add(simulation);
		frame.setSize(620,640);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
	
}
