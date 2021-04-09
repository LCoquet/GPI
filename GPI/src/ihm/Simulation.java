package ihm;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
import time.RealTime;

@SuppressWarnings("serial")
public class Simulation extends JPanel implements Runnable, KeyListener{
	
	JFrame frame ;

	private final static int SLEEP_TIME = 200;
	
	private Prison prison;
	private JPanel simulation;
	HumanMovement hm;
	PaintVisitor pv;
	Detector d ;
	
	private int timer = 0;
	private int victoryTimer = 30000; // 30 Sec = 30 000 ms
	private boolean timeout = false;
	
	private ArrayList<int[]> sorties;
	private ArrayList<Human> toRemove;
	
	private BufferedWriter out;
	
	private int nbEscaped = 0;
	private int nbCaught = 0;
	
	/* Chronometer */
	private RealTime clock = new RealTime();
	private Thread t = new Thread(clock);
	
	private Font categoryFont = new Font("Arial", Font.BOLD, 22);
	
	public Simulation(JFrame frame, int guardianAmount, int prisonerAmount) {
		prison = PrisonCreator.creation(guardianAmount, prisonerAmount);
		hm = new HumanMovement(prison);
		d = new Detector(prison);
		initSortie();
		toRemove = new ArrayList<Human>();
		simulation = this;
		
		this.frame = frame ;
		this.frame.add(simulation);
		this.frame.setSize(616,639);
		this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
		this.frame.addKeyListener(this); 
		startTimer();
	}
	
	public void startTimer() {
		t.start();
	}
	
	public void initSortie() {
    	sorties = new ArrayList<int[]>();
		for(int i = 0; i < 20; i ++) {
			for(int j = 0; j < 20; j ++) {
				if(prison.getMap()[i][j] == 'd')
					sorties.add(new int[] {i, j});
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		pv = new PaintVisitor(g);
		BackgroundPaint bp = new BackgroundPaint(g);
		
		bp.paint(prison.getMap());
        g.setFont(categoryFont);
        /* Timer */
        String chrono = writeTimer();

        g.drawString(chrono, 255, 23);
        g.drawString("ESCAPED : " + nbEscaped, 0 , 23);
        g.drawString("CAUGHT : " + nbCaught, 450, 23);
		for(Prisoner h : prison.getPrisoners())
			h.accept(pv);
		for(Human h : prison.getGuardians())
			h.accept(pv);
		g.dispose();
	}
	
	public String writeTimer() {
        int hours = clock.getHour().getValue();
        int minutes = clock.getMinute().getValue();
        int seconds = clock.getSecond().getValue();
    	String hoursExt = "0";
    	String minutesExt = "0";
    	String secondsExt = "0";
        if(hours >= 10) {
        	hoursExt = "";
        }
        if(minutes >= 10) {
        	minutesExt = "";
        }
        if(seconds >= 10) {
        	secondsExt = "";
        }
        return hoursExt + hours + ":" + minutesExt+ minutes + ":" + secondsExt+seconds;
	}
	
	public void run() {
		try {
			out = new BufferedWriter(new FileWriter("log.txt"));
			while(!isFinished() && !timeout) {
				try {
					cleanLists();
					for(Prisoner h : prison.getPrisoners()) {
						d.detect(h, out);
						hm.move(h);
					}
					
					for(Guardian h : prison.getGuardians()) {
						d.detect(h, out);
						hm.move(h);
					}
	
					caught();
					escape();
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
			out.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(timeout) {
			System.out.println("TIME OUT : " + victoryTimer/1000 + "sec");
		}
		System.out.println("Nombre d'échapés : " + nbEscaped);
		System.out.println("Nombre d'attrapés : " + nbCaught);
		System.out.println("FINI");
		
		frame.remove(simulation);
		frame.repaint();
		new EndGame(frame, nbCaught, nbEscaped, writeTimer());
	}
	
	public void cleanLists() {
		for(Human h : toRemove) {
			if(h.getClass().equals(Prisoner.class))
				prison.getPrisoners().remove(h);
			else
				prison.getGuardians().remove(h);
		}	
	}
	private boolean isFinished() {
		if(prison.getPrisoners().size() == 0) {
			return true;
		}
		return false;
	}
	
	public void caught () {
		for(Guardian g : prison.getGuardians()) {
				for(Prisoner p : prison.getPrisoners()) {
					if (g.getPos()[0] == p.getPos()[0] && g.getPos()[1] == p.getPos()[1] && !toRemove.contains(p)) {
			        	toRemove.add(p);
			        	System.out.println("CAUGHT");
			        	nbCaught++;
			        }
				}
		}
    }
	
    public void escape () {
    	for(Prisoner p : prison.getPrisoners()) {
    		for(int[] sortie : sorties) {
    	        if((p.getPos()[0] == sortie[0] && p.getPos()[1] == sortie[1])) {
    	        	toRemove.add(p);
    	        	System.out.println("ESCAPED");
    	        	nbEscaped++;
    	        }
    		}
    	}

    }
    
    public void keyPressed(KeyEvent k) {
    	System.out.println("Pressed");
    	if (k.getKeyCode() == KeyEvent.VK_ESCAPE) {
    		System.out.println("Exit Program");
    		System.exit(0);
		} 
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public Prison getPrison() {
		return prison;
	}

	public void setPrison(Prison prison) {
		this.prison = prison;
	}
	
	public Detector getDetector() {
		return d;
	}
	
	public void setDetector(Detector d) {
		this.d = d;
	}
	
}
