package test;

import data.Prisoner;

/*
 *   Modification de la classe du MOE2 par le RVR.
 *   Modification de la classe du MOE2 par le QA.
 */
public class TestLeo {

	public static void main(String[] args) {
		
		Prisoner leo = new Prisoner(null, null);
		if(leo.getClass().equals(Prisoner.class)) {
			System.out.println("prout");
		}
		System.out.println(leo.getClass() + "\n" + Prisoner.class);
	}
	
}
