package modele;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class GameTime extends Thread {
	private Calendar now;
	
	public GameTime() {
		setNow(new GregorianCalendar(2018, 9, 3));
		start();
	}
	
	public void run() {
		while (true) {
			now.add(Calendar.MINUTE, 30);
			try {
				Thread.sleep(3000);
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
}
