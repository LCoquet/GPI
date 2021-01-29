package ihm;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import data.Guardian;
import data.Human;
import data.Prison;
import data.Prisoner;
import processing.PrisonCreator;
import test.TestAlex;

public class Simulation extends JPanel implements Runnable{

	private static Prison prison;
	private Guardian g1;
	private Guardian g2;
	private Prisoner p1;
	private JPanel simulation;
	
	public Simulation() {
		prison = PrisonCreator.creation();
		g1 = prison.getGuardian1();
		g2 = prison.getGuardian2();
		p1 = prison.getPrisoner();
		
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
		g.setColor(Color.ORANGE);
		int i = prison.getPrisoner().getPos()[0];
		int j = prison.getPrisoner().getPos()[1];
		g.fillRect(j*30, i*30, 30, 30);
		
		g.setColor(Color.BLUE);
		i = prison.getGuardian1().getPos()[0];
		j = prison.getGuardian1().getPos()[1];
		g.fillRect(j*30, i*30, 30, 30);

		i = prison.getGuardian2().getPos()[0];
		j = prison.getGuardian2().getPos()[1];
		g.fillRect(j*30, i*30, 30, 30);

		g.dispose();
	}
	
	public void run() {
		while(!escape() && !caught()) {
			try {
				deplacer(g1);
				deplacer(g2);
				deplacer(p1);
				simulation.repaint();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("FINI");
	}
	
	public boolean caught () {
        if ((g1.getPos()[0] == p1.getPos()[0] && g1.getPos()[1] == p1.getPos()[1]) || (g2.getPos()[0] == p1.getPos()[0] && g2.getPos()[1] == p1.getPos()[1]) ) {
        	System.out.println("ATTRAPED");
            return true;
            
        }else {
            return false;
        }
    }
	
    public boolean escape () {
    	int posDoor[] = {13, 0};
        if(p1.getPos()[0] == posDoor[0] && p1.getPos()[1] == posDoor[1]) {
        	System.out.println("ECHAPED");
            return true;
        }else {
            return false;
        }
    }
	
	public void deplacer(Human h) {	 
			//System.out.println("Je suis : " + h.getName());
	        int ran =(int) (Math.random()*4);
	        int pos[] = h.getPos();
	        char map[][]=prison.getMap();
	        int xCheck = pos[0];
	        int yCheck = pos[1];
	        switch(ran)
	        {
	            case 0:
	                //déplacement vers la droite
	            	xCheck++;
	            	//System.out.println("Droite");
	                break;
	            case 1:
	                //déplacement vers la gauche
	            	xCheck--;
	            	//System.out.println("Gauche");
	                break;
	            case 2 :
	                //déplacement vers le haut
	            	yCheck--;
	            	//System.out.println("Haut");
	                break;
	            case 3 :
	                //déplacement vers le bas
	            	yCheck++;
	            	//System.out.println("Bas");
	                break;
	        }
	        if(map[xCheck][yCheck] != 'w') {
                pos[0] = xCheck;
                pos[1] = yCheck;
               // System.out.println("Se déplace");
	        }
	        else {
	        	//System.out.println("Est bloqué");
	        }
	}
	
	public static void main (String[] args) {
		Simulation sim = new Simulation();
		Thread t = new Thread(sim);
		t.start();

    }
	
}
