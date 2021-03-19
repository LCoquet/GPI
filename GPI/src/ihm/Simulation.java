package ihm;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data.Guardian;
import data.Human;
import data.Prison;
import data.Prisoner;
import processing.Detector;
import processing.HumanMovement;
import processing.PaintVisitor;
import processing.PrisonCreator;

@SuppressWarnings("serial")
public class Simulation extends JPanel implements Runnable, KeyListener{

	private final static int SLEEP_TIME = 200;
	
	private Prison prison;
	private JPanel simulation;
	HumanMovement hm;
	PaintVisitor pv;
	Detector d ;
	
	private int timer = 0;
	private int victoryTimer = 30000; // 30 Sec = 30 000 ms
	private boolean timeout = false;
	
	public Simulation() {
		
		prison = PrisonCreator.creation();
		hm = new HumanMovement(prison);
		d = new Detector(prison);
		
		JFrame frame = new JFrame("Guardians");
		simulation = this;
		frame.add(simulation);
		frame.setSize(616,639);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addKeyListener(this); 
		
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
		while(!isFinished() && !timeout) {
			try {
				for(Human h : prison.getHumans()) {
					d.detect(h);
					hm.move(h);
				}
				simulation.repaint();
				Thread.sleep(SLEEP_TIME);
				timer++;
				if(timer % (victoryTimer/SLEEP_TIME) == 0) { // If the prisoner wasn't caught for "victoryTimer" ms
					timeout = true;
				}
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
    	ArrayList<int[]> sorties = new ArrayList<int[]>();
		for(int i = 0; i < 20; i ++) {
			for(int j = 0; j < 20; j ++) {
				if(prison.getMap()[i][j] == 'd')
					sorties.add(new int[] {i, j});
			}
		}
		for(int[] sortie : sorties) {
	        if((p1.getPos()[0] == sortie[0] && p1.getPos()[1] == sortie[1])) {
	        	System.out.println("ECHAPED");
	            return true;
	        }
		}
		return false;

    }
    
    public void keyPressed(KeyEvent k) {
    	if (k.getKeyCode() == KeyEvent.VK_ESCAPE) {
    		System.out.println("Exit Program");
    		System.exit(0);
		} 
    }
	
	public static void main (String[] args) {
		Simulation sim = new Simulation();
		Thread t = new Thread(sim);
		t.start();
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
