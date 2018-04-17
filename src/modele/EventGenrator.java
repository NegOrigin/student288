package modele;

import java.util.Calendar;

public class EventGenrator extends Thread {
	private GameTime gameTime = null;
	private EventContainer eventContainer = null;
	private ActionContainer actionContainer = null;
	
	public EventGenrator(GameTime gameTime, EventContainer eventContainer, ActionContainer actionContainer) {
		setGameTime(gameTime);
		setEventContainer(eventContainer);
		setActionContainer(actionContainer);
	}
	
	public void run() {
		Calendar now;
		Boolean dayDone = false;
		while (true) {
			try {
				sleep(50);
				now = (Calendar) gameTime.getNow().clone();
				if (!dayDone && now.get(Calendar.HOUR_OF_DAY) == 0 && now.get(Calendar.MINUTE) == 30) {
					dayDone = true;
					generateDailyEvents(now);
					generateRandomEvents(now);
				}
				else if (dayDone && now.get(Calendar.HOUR_OF_DAY) == 23 && now.get(Calendar.MINUTE) == 59) {
					dayDone = false;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void setGameTime(GameTime gameTime) {
		this.gameTime = gameTime;
	}

	private void setEventContainer(EventContainer eventContainer) {
		this.eventContainer = eventContainer;
	}

	private void setActionContainer(ActionContainer actionContainer) {
		this.actionContainer = actionContainer;
	}
	
	private void generateDailyEvents(Calendar date) {
		Calendar start = (Calendar) date.clone();
		Calendar end = (Calendar) start.clone();
		
		if (date.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && date.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
			start.set(Calendar.HOUR_OF_DAY, 9);
			start.set(Calendar.MINUTE, 30);
			end.set(Calendar.HOUR_OF_DAY, 12);
			end.set(Calendar.MINUTE, 30);
			eventContainer.addEvent(new Event(actionContainer.getActionByName("Cours"), (Calendar) start.clone(), (Calendar) end.clone()));
			
			start.set(Calendar.HOUR_OF_DAY, 14);
			start.set(Calendar.MINUTE, 0);
			end.set(Calendar.HOUR_OF_DAY, 17);
			end.set(Calendar.MINUTE, 0);
			eventContainer.addEvent(new Event(actionContainer.getActionByName("Cours"), (Calendar) start.clone(), (Calendar) end.clone()));
		}
	}
	
	private void generateRandomEvents(Calendar date) {
		Calendar start = (Calendar) date.clone();
		Calendar end = (Calendar) start.clone();
		
		//TODO
	}
	
	public void startGenerator() {
		start();
	}
}
