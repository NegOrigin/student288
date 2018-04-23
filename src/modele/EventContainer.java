package modele;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import application.MainSceneController;
import javafx.application.Platform;

public class EventContainer {
	private ArrayList<Event> events;
	
	private Timer scheduler = new Timer();

	private GameTime gameTime = null;
	private ActionContainer actioncontainer = null;
	private MainSceneController controller = null;
	
	public EventContainer() {
		setEvents(new ArrayList<Event>());
	}

	public ArrayList<Event> getEvents() {
		return events;
	}

	private void setEvents(ArrayList<Event> events) {
		this.events = events;
		updateUI();
	}
	
	public Event getEvent(int index) {
		return events.get(index);
	}
	
	public ArrayList<Event> getEventsByDate(Calendar start) {
		ArrayList<Event> eventsAt = new ArrayList<Event>();
		for (Event event : events) {
			if (event.getStart() == start)
				eventsAt.add(event);
		}
		if (actioncontainer != null)
			for (Action action : actioncontainer.getNotAlwaysAvailableActions()) {
				Calendar end = (Calendar) start.clone();
				end.set(Calendar.MINUTE, end.get(Calendar.MINUTE)+30);
				eventsAt.add(new Event(action, start, end));
			}
		return eventsAt;
	}
	
	public ArrayList<Event> getEventsNotStarted(Calendar start) {
		ArrayList<Event> eventsAt = new ArrayList<Event>();
		for (Event event : events) {
			if (event.getStart().after(start))
				eventsAt.add(event);
		}
		return eventsAt;
	}
	
	public void addEvent(Event event) {
		events.add(event);
		updateUI();
	}
	
	public void removeEvent(int index) {
		events.remove(index);
		updateUI();
	}

	public void setGameTime(GameTime gameTime) {
		this.gameTime = gameTime;
		scheduler.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (gameTime.getNow().get(Calendar.MINUTE) == 0 || gameTime.getNow().get(Calendar.MINUTE) == 30)
					updateUI();
			}
		}, gameTime.getMinute(), gameTime.getMinute());
	}

	public void setActioncontainer(ActionContainer actioncontainer) {
		this.actioncontainer = actioncontainer;
	}

	public void setController(MainSceneController controller) {
		this.controller = controller;
		updateUI();
	}
	
	private void updateUI() {
		if (controller != null)
			Platform.runLater(new Runnable() {
			    public void run() {
					controller.refreshEventList(getEventsNotStarted(gameTime.getNow()));
			    }
			});
	}
}
