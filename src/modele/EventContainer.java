package modele;

import java.util.ArrayList;
import java.util.Observable;

public class EventContainer extends Observable {
	private ArrayList<Event> events;
	
	public EventContainer() {
		setEvents(new ArrayList<Event>());
	}

	public ArrayList<Event> getEvents() {
		return events;
	}

	private void setEvents(ArrayList<Event> events) {
		this.events = events;
	}
	
	public Event getEvent(int index) {
		return events.get(index);
	}
	
	public void addEvent(Event event) {
		events.add(event);
		setChanged();
		notifyObservers();
	}
	
	public void removeEvent(int index) {
		events.remove(index);
		setChanged();
		notifyObservers();
	}
}
