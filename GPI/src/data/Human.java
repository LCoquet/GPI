package data;

public class Human {

	private String name;
	private int pos[];
	
	public Human(String name, int[] pos) {
		super();
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
