package modele;

import java.util.Calendar;
import java.util.GregorianCalendar;

import application.MainSceneController;
import javafx.application.Platform;

public class GameTime extends Thread {
	private Calendar now;
	private int minute;
	
	private MainSceneController controller = null;
	
	public GameTime(int minute) {
		setNow(new GregorianCalendar(2018, Calendar.SEPTEMBER, 2, 12, 0));
		setMinute(minute);
	}
	
	public void run() {
		while (true) {
			now.add(Calendar.MINUTE, 1);
			if (controller != null)
				updateUI();
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
		if (controller != null)
			updateUI();
	}

	public int getMinute() {
		return minute;
	}

	private void setMinute(int minute) {
		this.minute = minute;
	}

	public void setController(MainSceneController controller) {
		this.controller = controller;
		initializeUI();
		updateUI();
	}
	
	public void startGame() {
		start();
	}
	
	private void initializeUI() {
		Platform.runLater(new Runnable() {
		    public void run() {
				controller.initializeAddEventDateDatePicker(now);
		    }
		});
	}
	
	private void updateUI() {
		Platform.runLater(new Runnable() {
		    public void run() {
				controller.refreshDate(now);
		    }
		});
	}
}
