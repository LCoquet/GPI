package data;

import processing.Visitor;

/*
 * The basis class for humans in the simulation.
 * Guardians and Prisoner are split for processing.
 *
 * The directions are :
 * 1 : right
 * 2 : left
 * 3 : up
 * 4 : down
 *
 */

public abstract class Human {

	private String name;
	private int pos[] = new int[2];
	private int direction;
	private int objectivePos[] = null;
	
	public Human(String name, int[] pos) {
		this.name = name;
		this.pos = pos;
		
	}
	
	public abstract void accept(Visitor v);
	
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
	
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public int[] getObjectivePos() {
		return objectivePos;
	}
	public void setObjectivePos(int[] objectivePos) {
		this.objectivePos = objectivePos;
	}
		
}
