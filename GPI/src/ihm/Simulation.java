package ihm;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data.Guardian;
import data.Human;
import data.Prison;
import data.Prisoner;
import processing.HumanMovement;
import processing.PaintVisitor;
import processing.PrisonCreator;

@SuppressWarnings("serial")
public class Simulation extends JPanel implements Runnable{

	private Prison prison;
	private JPanel simulation;
	HumanMovement hm;
	PaintVisitor pv;
	
	public Simulation() {
		
		prison = PrisonCreator.creation();
		hm = new HumanMovement(prison);
		
		JFrame frame = new JFrame("Guardians");
		simulation = this;
		frame.add(simulation);
		frame.setSize(616,639);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		pv = new PaintVisitor(g);
		BackgroundPaint bp = new BackgroundPaint(g);
		
		bp.paint(prison.getMap());
		
		for(Human h : prison.getHumans())
			h.accept(pv);
		g.dispose();
	}
	
	public void run() {
		while(!isFinished()) {
			try {
				for(Human h : prison.getHumans()) {
					hm.move(h);
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
