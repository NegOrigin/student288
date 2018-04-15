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
	
	public String toString() {
		return action.getName()+" \n"
			+"de "+String.format("%02d", start.get(Calendar.HOUR_OF_DAY))+"h"+String.format("%02d", start.get(Calendar.MINUTE))
			+" le "+String.format("%02d", start.get(Calendar.DAY_OF_MONTH))+"/"+String.format("%02d", (start.get(Calendar.MONTH)+1))+"/"+start.get(Calendar.YEAR)+" \n"
			+"à "+String.format("%02d", end.get(Calendar.HOUR_OF_DAY))+"h"+String.format("%02d", end.get(Calendar.MINUTE))
			+" le "+String.format("%02d", end.get(Calendar.DAY_OF_MONTH))+"/"+String.format("%02d", (end.get(Calendar.MONTH)+1))+"/"+end.get(Calendar.YEAR);
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
