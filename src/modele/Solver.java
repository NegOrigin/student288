package modele;

import java.util.Calendar;

public class Solver extends Thread {
	public Solver() {
		
	}
	
	public Event findEvent(Student student, GameTime gameTime, EventContainer eventContainer) {
		Calendar start = student.getEventCurrent().getEnd();
		Calendar end = (Calendar) start.clone();
		end.add(Calendar.MINUTE, 30);
		return new Event(new Action("Rien", "../images/actions/tmpBackground.png", false, 0, 0, 0, 0, 0, 0, 0, 0), start, end);
	}
}
