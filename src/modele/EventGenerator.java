package modele;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class EventGenerator {
	private GameTime gameTime = null;
	private EventContainer eventContainer = null;
	private ActionContainer actionContainer = null;
	
	private Timer scheduler = new Timer();
	
	public EventGenerator(GameTime gameTime, EventContainer eventContainer, ActionContainer actionContainer) {
		setGameTime(gameTime);
		setEventContainer(eventContainer);
		setActionContainer(actionContainer);
	}

	private void setGameTime(GameTime gameTime) {
		this.gameTime = gameTime;
		scheduler.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				Calendar now;
				Boolean dayDone = false;
				while (true) {
					now = (Calendar) gameTime.getNow().clone();
					if (!dayDone && now.get(Calendar.HOUR_OF_DAY) == 12 && now.get(Calendar.MINUTE) == 30) {
						dayDone = true;
						generateDailyEvents(now);
						generateRandomEvents(now);
					}
					else if (dayDone && now.get(Calendar.HOUR_OF_DAY) == 0 && now.get(Calendar.MINUTE) == 0) {
						dayDone = false;
					}
				}
			}
		}, gameTime.getMinute(), gameTime.getMinute());
	}

	private void setEventContainer(EventContainer eventContainer) {
		this.eventContainer = eventContainer;
	}

	private void setActionContainer(ActionContainer actionContainer) {
		this.actionContainer = actionContainer;
	}
	
	private void generateDailyEvents(Calendar date) {
		Calendar start = (Calendar) date.clone();
		start.set(Calendar.DAY_OF_MONTH, start.get(Calendar.DAY_OF_MONTH)+1);
		Calendar end = (Calendar) start.clone();
		
		if (start.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && start.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
			start.set(Calendar.HOUR_OF_DAY, 9);
			start.set(Calendar.MINUTE, 30);
			end.set(Calendar.HOUR_OF_DAY, 12);
			end.set(Calendar.MINUTE, 30);
			eventContainer.addEvent(new Event(actionContainer.getActionByName("Aller en Cours"), (Calendar) start.clone(), (Calendar) end.clone()));
			
			start.set(Calendar.HOUR_OF_DAY, 14);
			start.set(Calendar.MINUTE, 0);
			end.set(Calendar.HOUR_OF_DAY, 17);
			end.set(Calendar.MINUTE, 0);
			eventContainer.addEvent(new Event(actionContainer.getActionByName("Aller en Cours"), (Calendar) start.clone(), (Calendar) end.clone()));
		}
	}
	
	private void generateRandomEvents(Calendar date) {
		Calendar start = (Calendar) date.clone();
		start.set(Calendar.DAY_OF_MONTH, start.get(Calendar.DAY_OF_MONTH)+1);
		Calendar end = (Calendar) start.clone();
		
		for (Action action : actionContainer.getNotAlwaysAvailableActions()) {
			if (Math.random() < action.getProbability()) {
				start.set(Calendar.HOUR_OF_DAY, (int)(Math.random()*15)+8);
				start.set(Calendar.MINUTE, (int)(Math.round(Math.random())*30));
				end = (Calendar) start.clone();
				end.add(Calendar.MINUTE, (int)(Math.round(Math.random()*6+1)*30));
				eventContainer.addEvent(new Event(action, (Calendar) start.clone(), (Calendar) end.clone()));
			}
		}
	}
}
