package data;

public class Prison {

	private char map[][] = new char[20][20];
	private int doorPos[];
	
	public Prison(char[][] map, int[] doorPos) {
		super();
		this.map = map;
		this.doorPos = doorPos;
	}
	
	public char[][] getMap() {
		return map;
	}
	public void setMap(char[][] map) {
		this.map = map;
	}
	public int[] getDoorPos() {
		return doorPos;
	}
	public void setDoorPos(int[] doorPos) {
		this.doorPos = doorPos;
	}
	
}
