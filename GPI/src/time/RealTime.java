package time;

import time.Time;

/**
 * @brief Timer thread for chronometer
 * 
 * @author CHABOT Yohan, COQUET Leo, DE SOUSA Julia, GASTEBOIS Emma, HANG Alexandre, POUPET Maria-Lorena
 *
 */
public class RealTime implements Runnable{

	private Time hour = new Time(0, 1, 0);
	private Time minute = new Time(0, 15, 0);
	private Time second = new Time(0, 59, 0);
	
	private boolean running = true;
	
	public void init() {
		hour.setValue(0);
		minute.setValue(0);
		second.setValue(0);
	}
	
	public void run() {
		while(running) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			increment();
		}
		
	}
	
	public void increment() {
		second.increment();
		if (second.getValue() == 0) {
			minute.increment();
			if (minute.getValue() == 0) {
				hour.increment();
			}
		}
	}
	
	public static String transform(int value) {
		String result = "";
		if (value < 10) {
			result = "0" + value;
		} else {
			result = String.valueOf(value);
		}
		return result;
	}
	
	public String toString() {
		//System.out.println("\n"+RealTime.transform(getHour().getValue()) + " : "+ RealTime.transform(getMinute().getValue()) + " : " + RealTime.transform(getSecond().getValue())); 
		return "l'heure" ;
	}
	
	public Time getHour() {
		return hour;
	}
	
	public Time getMinute() {
		return minute;
	}
	
	public Time getSecond() {
		return second;
	}
	
	public String getToString() {
		String heure = RealTime.transform(getHour().getValue()) + " : "+ RealTime.transform(getMinute().getValue()) + " : " + RealTime.transform(getSecond().getValue());
		return heure;
	}


	
}
