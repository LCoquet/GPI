package test;
import data.*;
import processing.*;

public class TestAlex implements Runnable {
	private Prison p;
	private Guardian g1;
	private Guardian g2;
	private Prisoner p1;
	
	public TestAlex() {
		System.out.println("Bonjour je suis le QA");
		p = PrisonCreator.creation();
		System.out.println(p);
		g1 = p.getGuardian1();
		g2 = p.getGuardian2();
		p1 = p.getPrisoner();
		
	}
	
	public void run() {
		while(!escape()) {
			try {
				deplacer(g1);
				deplacer(g2);
				deplacer(p1);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean cought (Prison p) {

        if (g1.getPos().equals(p1.getPos())|| g2.getPos().equals(p1.getPos())) {
            return true;
        }else {
            return false;
        }
    }
	
    public boolean escape () {
    	int posDoor[] = {13, 0};
        if(p.getPrisoner().getPos() == posDoor) {
            return true;
        }else {
            return false;
        }
    }
	
	public void deplacer(Human h) {	 
			System.out.println("Je suis : " + h.getName());
	        int ran =(int) (Math.random()*4);
	        int pos[] = h.getPos();
	        char map[][]=p.getMap();
	        int xCheck = pos[0];
	        int yCheck = pos[1];
	        switch(ran)
	        {
	            case 0:
	                //déplacement vers la droite
	            	xCheck++;
	            	System.out.println("Droite");
	                break;
	            case 1:
	                //déplacement vers la gauche
	            	xCheck--;
	            	System.out.println("Gauche");
	                break;
	            case 2 :
	                //déplacement vers le haut
	            	yCheck--;
	            	System.out.println("Haut");
	                break;
	            case 3 :
	                //déplacement vers le bas
	            	yCheck++;
	            	System.out.println("Bas");
	                break;
	        }
	        if(map[xCheck][yCheck] != 'w') {
                pos[0] = xCheck;
                pos[1] = yCheck;
                System.out.println("Se déplace");
	        }
	        else {
	        	System.out.println("Est bloqué");
	        }
	}
	
	public static void main(String args[]) {
		Thread t = new Thread(new TestAlex());
		t.start(); 
	}
}
