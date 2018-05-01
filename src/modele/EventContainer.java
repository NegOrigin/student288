package modele;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;

import application.MainSceneController;
import javafx.application.Platform;

public class EventContainer {
	private ArrayList<Event> events;

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
		Collections.sort(this.events, new Comparator<Event>() {
			public int compare(Event e1, Event e2) {
		      return e1.getStart().compareTo(e2.getStart());
			}
		});
	}
	
	public Event getEvent(int index) {
		return events.get(index);
	}
	
	public ArrayList<Event> getEventsByDate(Calendar start) {
		ArrayList<Event> eventsAt = new ArrayList<Event>();
		for (Event event : events) {
			if (event.getStart().equals(start))
				eventsAt.add(event);
		}
		if (actioncontainer != null)
			for (Action action : actioncontainer.getAlwaysAvailableActions()) {
				Calendar end = (Calendar) start.clone();
				end.set(Calendar.MINUTE, end.get(Calendar.MINUTE)+30);
				eventsAt.add(new Event(action, start, end));
			}
		return eventsAt;
	}
	
	public ArrayList<Event> getEventsNotStarted(Calendar start) {
		ArrayList<Event> eventsNotStarted = new ArrayList<Event>();
		for (Event event : events) {
			if (event.getStart().after(start))
				eventsNotStarted.add(event);
		}
		return eventsNotStarted;
	}
	
	public void addEvent(Event event) {
		events.add(event);
		Collections.sort(events, new Comparator<Event>() {
			public int compare(Event e1, Event e2) {
		      return e1.getStart().compareTo(e2.getStart());
			}
		});
		updateUI(getEventsNotStarted(gameTime.getNow()));
	}
	
	public void removeEvent(int index) {
		events.remove(index);
		updateUI(getEventsNotStarted(gameTime.getNow()));
	}

	public void setGameTime(GameTime gameTime) {
		this.gameTime = gameTime;
		Timer scheduler = new Timer(true);
		scheduler.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (gameTime.getNow().get(Calendar.MINUTE) == 0 || gameTime.getNow().get(Calendar.MINUTE) == 30)
					updateUI(getEventsNotStarted(gameTime.getNow()));
			}
		}, gameTime.getMinute(), gameTime.getMinute());
	}

	public void setActioncontainer(ActionContainer actioncontainer) {
		this.actioncontainer = actioncontainer;
	}

	public void setController(MainSceneController controller) {
		this.controller = controller;
		updateUI(getEventsNotStarted(gameTime.getNow()));
	}
	
	private void updateUI(ArrayList<Event> eventsNotStarted) {
		if (controller != null)
			Platform.runLater(new Runnable() {
			    public void run() {
					controller.refreshEventList(eventsNotStarted);
			    }
			});
	}
}
