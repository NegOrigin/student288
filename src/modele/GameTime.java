package modele;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import application.MainSceneController;
import javafx.application.Platform;

public class GameTime {
	private Calendar now;
	private int minute;
	
	private Timer scheduler = new Timer();
	
	private MainSceneController controller = null;
	
	public GameTime(int minute) {
		setNow(new GregorianCalendar(2018, Calendar.SEPTEMBER, 2, 12, 0));
		setMinute(minute);
	}

	public Calendar getNow() {
		return now;
	}

	private void setNow(Calendar now) {
		this.now = now;
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
		scheduler.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				now.add(Calendar.MINUTE, 1);
				updateUI();
			}
		}, minute, minute);
	}
	
	private void initializeUI() {
		Platform.runLater(new Runnable() {
		    public void run() {
				controller.initializeAddEventDateDatePicker(now);
		    }
		});
	}
	
	private void updateUI() {
		if (controller != null)
			Platform.runLater(new Runnable() {
			    public void run() {
					controller.refreshDate(now);
			    }
			});
	}
}
