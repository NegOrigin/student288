package modele;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import application.MainSceneController;
import javafx.application.Platform;

public class Student {
	private String name;
	private StudentType studentType;
	private StudentProfile studentProfile;
	private StudentState studentState;
	
	private Timer scheduler = new Timer();
	private Solver solver;
	private Event eventCurrent = null;
	private Event eventNext = null;
	
	private MainSceneController controller = null;
	
	public Student(String name) {
		setName(name);
		setStudentType(new StudentType());
		setStudentProfile(new StudentProfile(studentType));
		setStudentState(new StudentState(studentType));
		setSolver(new Solver());
	}
	
	public Student(Student student) {
		setName(student.getName());
		setStudentType(student.getStudentType());
		setStudentProfile(new StudentProfile(student.getStudentProfile()));
		setStudentState(new StudentState(student.getStudentState()));
		setSolver(new Solver());
	}
	
	public String toString() {
		return "Nom : "+getName()+",\n"
			+"Type : "+studentType+",\n"
			+"Profil :\n"+studentProfile+",\n"
			+"Etat :\n"+studentState+",\n"
			+"Bonheur : "+calculateHappiness();
	}
	
	private Student getThis() {
		return this;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public StudentType getStudentType() {
		return studentType;
	}

	private void setStudentType(StudentType studentType) {
		this.studentType = studentType;
	}

	public StudentProfile getStudentProfile() {
		return studentProfile;
	}

	private void setStudentProfile(StudentProfile studentProfile) {
		this.studentProfile = studentProfile;
	}

	public StudentState getStudentState() {
		return studentState;
	}

	private void setStudentState(StudentState studentState) {
		this.studentState = studentState;
		updateUI();
	}

	public void setGameTimeAndEventContainer(GameTime gameTime, EventContainer eventContainer) {
		setGameTime(gameTime);
		setEventContainer(eventContainer);
		
		Calendar start = gameTime.getNow();
		Calendar end = (Calendar) start.clone();
		end.add(Calendar.MINUTE, 120);
		setEventCurrent(new Event(new Action("Rien", "../images/actions/chargement.gif", false, 0, 0, 0, 0, 0, 0, 0, 0, 0), start, end));
		setEventNext(solver.findEvent(getThis(), gameTime, eventContainer));

		scheduler.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if ((gameTime.getNow().get(Calendar.MINUTE) == 1 || gameTime.getNow().get(Calendar.MINUTE) == 31))
					if (eventCurrent.getEnd().before(gameTime.getNow())) {
						setEventCurrent(eventNext);
						setEventNext(solver.findEvent(getThis(), gameTime, eventContainer)); 
					}
			}
		}, gameTime.getMinute(), gameTime.getMinute());
	}

	private void setGameTime(GameTime gameTime) {
	}

	private void setEventContainer(EventContainer eventContainer) {
	}

	private void setSolver(Solver solver) {
		this.solver = solver;
	}
	
	public Event getEventCurrent() {
		return eventCurrent;
	}

	private void setEventCurrent(Event eventCurrent) {
		this.eventCurrent = eventCurrent;
		updateStudentState(eventCurrent.getAction().getGaming(), eventCurrent.getAction().getLove(), eventCurrent.getAction().getSchool(), eventCurrent.getAction().getSocial(), 
				eventCurrent.getAction().getHealth(), eventCurrent.getAction().getRelaxation(), eventCurrent.getAction().getSatiety(), eventCurrent.getAction().getVitality());
	}

	public void setEventNext(Event eventNext) {
		this.eventNext = eventNext;
	}

	public void setController(MainSceneController controller) {
		this.controller = controller;
		updateUI();
	}
	
	public int calculateHappiness() {
		float happiness = 0;
		happiness += calculateHappinessByPrimary(studentProfile.getGaming(), studentState.getGaming());
		happiness += calculateHappinessByPrimary(studentProfile.getLove(), studentState.getLove());
		happiness += calculateHappinessByPrimary(studentProfile.getSchool(), studentState.getSchool());
		happiness += calculateHappinessByPrimary(studentProfile.getSocial(), studentState.getSocial());
		happiness += calculateHappinessBySecondary(studentProfile.getHealth(), studentState.getHealth());
		happiness += calculateHappinessBySecondary(studentProfile.getRelaxation(), studentState.getRelaxation());
		happiness += calculateHappinessBySecondary(studentProfile.getSatiety(), studentState.getSatiety());
		happiness += calculateHappinessBySecondary(studentProfile.getVitality(), studentState.getVitality());
		return Math.round(happiness);
	}
	
	private float calculateHappinessByPrimary(float profile, float state) {
		float diff = state-profile;
		if (diff >= 0) {
			if (diff <= 20) 
				return 10f+(20-diff)/20*10f;
			else
				return (10f-(1-(40-(diff-20))/40)*10f) > 0 ? 10f-(1-(40-(diff-20))/40)*10f : 0;
		}
		else {
			if (diff >= -10) 
				return 10f+(10+diff)/10*10f;
			else
				return (10f-(1-(20+(diff+10))/20)*10f) > 0 ? 10f-(1-(20+(diff+10))/20)*10f : 0;
		}
	}
	
	private float calculateHappinessBySecondary(float profile, float state) {
		return ((state-profile)/(100-profile)*5) > 0 ? (state-profile)/(100-profile)*5 : 0;
	}
	
	public void updateStudentState(float gaming, float love, float school, float social, float health, float relaxation, float satiety, float vitality) {
		studentState.updateState(gaming, love, school, social, health, relaxation, satiety, vitality);
		updateUI();
	}
	
	private void updateUI() {
		if (controller != null)
			Platform.runLater(new Runnable() {
			    public void run() {
					controller.refreshStudentActivityImage(eventCurrent.getAction().getImage());
					controller.refreshStudentState(calculateHappiness(), studentState.getGaming(), studentState.getLove(), studentState.getSchool(), studentState.getSocial(), 
							studentState.getHealth(), studentState.getRelaxation(), studentState.getSatiety(), studentState.getVitality());
			    }
			});
	}
}
