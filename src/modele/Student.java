package modele;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import application.MainSceneController;
import javafx.application.Platform;

public class Student {
	private String name;
	private StudentType studentType;
	private StudentProfile studentProfile;
	private StudentState studentState;
	
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
		InitializeUI();
	}

	public void setGameTimeAndEventContainer(GameTime gameTime, EventContainer eventContainer) {
		setGameTime(gameTime);
		setEventContainer(eventContainer);
		
		Calendar start = gameTime.getNow();
		Calendar end = (Calendar) start.clone();
		end.add(Calendar.MINUTE, 120);
		setEventCurrent(new Event(new Action("Rien", "../images/actions/chargement.gif", false, 0, 0, 0, 0, 0, 0, 0, 0, 0), start, end));	
		setEventNext(solver.findEvent(getThis(), gameTime, eventContainer));
		
		Timer scheduler = new Timer(true);
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

	public void setEventCurrent(Event eventCurrent) {
		this.eventCurrent = eventCurrent;
		
		if (controller != null)
			controller.printInConsole("Événement en cours : "+eventCurrent.toString().replace("\n", ""));
		
		float gaming = eventCurrent.getAction().getGaming();
		float love = eventCurrent.getAction().getLove();
		float school = eventCurrent.getAction().getSchool();
		float social = eventCurrent.getAction().getSocial();

		float health = eventCurrent.getAction().getHealth();
		float relaxation = eventCurrent.getAction().getRelaxation();
		float satiety = eventCurrent.getAction().getSatiety();
		float vitality = eventCurrent.getAction().getVitality();
		
		//Temps en millisecondes -> tranche de 30 minutes : temps/(1000*60*30)
		int time = (int)(eventCurrent.getEnd().getTimeInMillis()/1800000 - eventCurrent.getStart().getTimeInMillis()/1800000);
		
		//Traitement uniquement pour les événements qui n'impactent pas que sur les stats liées à la santé
		if(gaming != 0 || love != 0 || school != 0 || social != 0) {
			gaming = gaming*getStudentProfile().getGaming()/100;
			love = love*getStudentProfile().getLove()/100;
			school = school*getStudentProfile().getSchool()/100;
			social = social*getStudentProfile().getSocial()/100;
			
			//Gestion de la relaxation dépendamment de la stat proéminente de l'événement
			ArrayList<Float> statList = new ArrayList<Float>();
			statList.add(gaming);
			statList.add(love);
			statList.add(school);
			statList.add(social);

			if(gaming == Collections.max(statList)) {
				relaxation = relaxation*(getStudentProfile().getGaming()-50)/100;
			}
			else if(love == Collections.max(statList)) {
				relaxation = relaxation*(getStudentProfile().getLove()-50)/100;
			}
			else if(school == Collections.max(statList)) {
				relaxation = relaxation*(getStudentProfile().getSchool()-50)/100;
			}
			else {
				relaxation = relaxation*(getStudentProfile().getSocial()-50)/100;
			}		
		}
		
		if(health > 0) {
			health = (100-studentState.getHealth())/(100-studentProfile.getHealth())*health;
		}
		if(relaxation > 0) {
			relaxation = (100-studentState.getRelaxation())/(100-studentProfile.getRelaxation())*relaxation;
		}
		if(satiety > 0) {
			satiety = (100-studentState.getSatiety())/(100-studentProfile.getSatiety())*satiety;
		}
		if(vitality > 0) {
			vitality = (100-studentState.getVitality())/(100-studentProfile.getVitality())*vitality;
		}
		
		updateStudentState(gaming*time, love*time, school*time, social*time, health*time, relaxation*time, satiety*time, vitality*time);
	}

	public void setEventNext(Event eventNext) {
		this.eventNext = eventNext;
	}

	public void setController(MainSceneController controller) {
		this.controller = controller;
		InitializeUI();
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
	
	private void InitializeUI() {
		if (controller != null)
			Platform.runLater(new Runnable() {
			    public void run() {
					controller.refreshStudentState(calculateHappiness(), studentState.getGaming(), studentState.getLove(), studentState.getSchool(), studentState.getSocial(), 
							studentState.getHealth(), studentState.getRelaxation(), studentState.getSatiety(), studentState.getVitality());
			    }
			});
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
