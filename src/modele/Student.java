package modele;

public class Student extends Thread {
	private StudentType studentType;
	private StudentProfile studentProfile;
	private StudentState studentState;
	
	private GameTime gameTime = null;
	private EventContainer eventContainer = null;
	
	public Student(String name) {
		super(name);
		setStudentType(new StudentType());
		setStudentProfile(new StudentProfile(studentType));
		setStudentState(new StudentState(studentType));
		start();
	}
	
	public Student(Student student) {
		super(student.getName());
		setStudentType(student.getStudentType());
		setStudentProfile(new StudentProfile(student.getStudentProfile()));
		setStudentState(new StudentState(student.getStudentState()));
		start();
	}
	
	public void run() {
		
	}
	
	public String toString() {
		return "Nom : "+getName()+",\n"
			+"Type : "+studentType+",\n"
			+"Profil :\n"+studentProfile+",\n"
			+"Etat :\n"+studentState+",\n"
			+"Bonheur : "+calculateHappiness();
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
	}

	public void setGameTime(GameTime gameTime) {
		this.gameTime = gameTime;
	}

	public void setEventContainer(EventContainer eventContainer) {
		this.eventContainer = eventContainer;
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
}
