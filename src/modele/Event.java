package modele;

import java.util.Calendar;

public class Event {
	private Action action;
	private Calendar start;
	private Calendar end;
	
	public Event(Action action, Calendar start, Calendar end) {
		setAction(action);
		setStart(start);
		setEnd(end);
	}

	public Action getAction() {
		return action;
	}

	private void setAction(Action action) {
		this.action = action;
	}

	public Calendar getStart() {
		return start;
	}

	private void setStart(Calendar start) {
		this.start = start;
	}

	public Calendar getEnd() {
		return end;
	}

	private void setEnd(Calendar end) {
		this.end = end;
	}
}
