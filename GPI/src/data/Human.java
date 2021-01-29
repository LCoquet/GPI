package data;

/*
 * The basis class for humans in the simulation.
 * Guardians and Prisoner are splitted for processing.
 *
 */

public class Human {

	private String name;
	private int pos[] = new int[2];
	
	public Human(String name, int[] pos) {
		this.name = name;
		this.pos = pos;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int[] getPos() {
		return pos;
	}
	public void setPos(int[] pos) {
		this.pos = pos;
	}
	
	
	
}
