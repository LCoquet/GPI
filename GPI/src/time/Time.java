package time;

/**
 * @brief Time class for chronometer
 * 
 * @author CHABOT Yohan, COQUET Leo, DE SOUSA Julia, GASTEBOIS Emma, HANG Alexandre, POUPET Maria-Lorena
 *
 */
public class Time {

	private int value;
	private int max;
	private int min;
	
	public Time(int value, int max, int min) {
		this.value = value;
		this.max = max;
		this.min = min;
	}
	
	public void increment() {
		if (value < max) {
			value ++;
		} else {
			setValue(getMin());
		}
	}
	
	public int getValue() {
		return value;
	}
	
	public int getMax() {
		return max;
	}
	
	public int getMin() {
		return min;
	}
	
	protected void setValue(int value) {
		this.value = value;
	}

}
