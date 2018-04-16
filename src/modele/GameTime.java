package modele;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class GameTime extends Thread {
	private Calendar now;
	private int minute;
	
	public GameTime(int minute) {
		setNow(new GregorianCalendar(2018, 9, 3));
		setMinute(minute);
	}
	
	public void run() {
		while (true) {
			now.add(Calendar.MINUTE, 1);
			try {
				Thread.sleep(minute);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public Calendar getNow() {
		return now;
	}

	private void setNow(Calendar now) {
		this.now = now;
	}

	public int getMinute() {
		return minute;
	}

	private void setMinute(int minute) {
		this.minute = minute;
	}
	
	public void startGame() {
		start();
	}
}
