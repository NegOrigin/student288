package modele;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class EventContainer {
	private ArrayList<Event> events;
	
	private ActionContainer actioncontainer = null;
	
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
	
	public ArrayList<Event> getEventsByDate(Calendar start) {
		ArrayList<Event> eventsAt = new ArrayList<Event>();
		for (Event event : events) {
			if (event.getStart() == start)
				eventsAt.add(event);
		}
		if (actioncontainer != null)
			for (Action action : actioncontainer.getNotAlwaysAvailableActions()) {
				Calendar end = new GregorianCalendar(
					start.get(Calendar.YEAR), 
					start.get(Calendar.MONTH), 
					start.get(Calendar.DAY_OF_MONTH), 
					start.get(Calendar.HOUR_OF_DAY), 
					start.get(Calendar.MINUTE)+30
				);
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
	}
	
	public void removeEvent(int index) {
		events.remove(index);
	}

	public void setActioncontainer(ActionContainer actioncontainer) {
		this.actioncontainer = actioncontainer;
	}
}
