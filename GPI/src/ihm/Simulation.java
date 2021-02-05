package ihm;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import data.Guardian;
import data.Human;
import data.Prison;
import data.Prisoner;
import processing.MoveVisitor;
import processing.PaintVisitor;
import processing.PrisonCreator;

public class Simulation extends JPanel implements Runnable{

	private static Prison prison;
	private JPanel simulation;
	MoveVisitor mv = new MoveVisitor();
	PaintVisitor pv = new PaintVisitor();
	
	public Simulation() {
		
		prison = PrisonCreator.creation();
		
		JFrame frame = new JFrame("Guardians");
		simulation = this;
		frame.add(simulation);
		frame.setSize(616,639);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
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
		
		for(Human h : prison.getHumans())
			h.accept(pv, g);
		g.dispose();
	}
	
	public void run() {
		while(!isFinished()) {
			try {
				for(Human h : prison.getHumans()) {
					h.accept(mv);
				}
				simulation.repaint();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("FINI");
	}
	
	private boolean isFinished() {
		Prisoner p1 = null;
		Guardian g1 = null;
		Guardian g2 = null;
		for(Human h : prison.getHumans()) {
			if(h.getClass().equals(Prisoner.class))
				p1 = (Prisoner) h;
			else if (g1 == null)
				g1 = (Guardian) h;
			else
				g2 = (Guardian) h;
		}
		return(caught(g1, g2, p1) || escape(p1));
	}
	
	public boolean caught (Guardian g1, Guardian g2, Prisoner p1) {
        if ((g1.getPos()[0] == p1.getPos()[0] && g1.getPos()[1] == p1.getPos()[1]) || (g2.getPos()[0] == p1.getPos()[0] && g2.getPos()[1] == p1.getPos()[1]) ) {
        	System.out.println("ATTRAPED");
            return true;
            
        }else {
            return false;
        }
    }
	
    public boolean escape (Prisoner p1) {
    	int posDoor[] = {13, 0};
        if(p1.getPos()[0] == posDoor[0] && p1.getPos()[1] == posDoor[1]) {
        	System.out.println("ECHAPED");
            return true;
        } else {
            return false;
        }
    }
	
	public static void main (String[] args) {
		Simulation sim = new Simulation();
		Thread t = new Thread(sim);
		t.start();
    }
	
}
