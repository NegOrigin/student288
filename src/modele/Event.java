package modele;

public class Event {
	private Action action;
	private int start;
	private int end;
	
	public Event(Action action, int start, int end) {
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

	public int getStart() {
		return start;
	}

	private void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	private void setEnd(int end) {
		this.end = end;
	}
}
