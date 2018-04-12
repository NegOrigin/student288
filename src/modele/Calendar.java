package modele;

import java.util.ArrayList;

public class Calendar {
	private ArrayList<Event> events;
	
	public Calendar() {
		setEvents(new ArrayList<Event>());
	}

	public ArrayList<Event> getEvents() {
		return events;
	}

	private void setEvents(ArrayList<Event> events) {
		this.events = events;
	}
}
