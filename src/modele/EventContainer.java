package modele;

import java.util.ArrayList;
import java.util.Calendar;

import application.MainSceneController;
import javafx.application.Platform;

public class EventContainer {
	private ArrayList<Event> events;
	
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
		if (controller != null)
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
		if (controller != null)
			updateUI();
	}
	
	public void removeEvent(int index) {
		events.remove(index);
		if (controller != null)
			updateUI();
	}

	public void setActioncontainer(ActionContainer actioncontainer) {
		this.actioncontainer = actioncontainer;
	}

	public void setController(MainSceneController controller) {
		this.controller = controller;
		updateUI();
	}
	
	private void updateUI() {
		Platform.runLater(new Runnable() {
		    public void run() {
				controller.refreshEventList(events);
		    }
		});
	}
}
