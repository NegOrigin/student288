package modele;

public class Student {
	private String name;
	private StudentType type;
	private StudentProfile profile;
	private StudentState state;
	
	public Student(String name) {
		setName(name);
		setType(new StudentType());
		setProfile(new StudentProfile(type));
		setState(new StudentState(type));
	}
	
	public Student(Student student) {
		setName(student.getName());
		setType(student.getType());
		setProfile(new StudentProfile(student.getProfile()));
		setState(new StudentState(student.getState()));
	}
	
	public String toString() {
		return "Nom : "+name+",\n"
			+"Type : "+type+",\n"
			+"Profil :\n"+profile+",\n"
			+"Etat :\n"+state+",\n"
			+"Bonheur : "+calculateHappiness();
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public StudentType getType() {
		return type;
	}

	private void setType(StudentType type) {
		this.type = type;
	}

	public StudentProfile getProfile() {
		return profile;
	}

	private void setProfile(StudentProfile profile) {
		this.profile = profile;
	}

	public StudentState getState() {
		return state;
	}

	private void setState(StudentState state) {
		this.state = state;
	}
	
	public int calculateHappiness() {
		float happiness = 0;
		happiness += calculateHappinessByGaming();
		happiness += calculateHappinessByLove();
		happiness += calculateHappinessBySchool();
		happiness += calculateHappinessBySocial();
		happiness += calculateHappinessByHealth();
		happiness += calculateHappinessByRelaxation();
		happiness += calculateHappinessByVitality();
		return Math.round(happiness);
	}
	
	public float calculateHappinessByGaming() {
		float diff = state.getGaming()-profile.getGaming();
		return calculateHappinessForPrimary(diff);
	}
	
	public float calculateHappinessByLove() {
		float diff = state.getLove()-profile.getLove();
		return calculateHappinessForPrimary(diff);
	}
	
	public float calculateHappinessBySchool() {
		float diff = state.getSchool()-profile.getSchool();
		return calculateHappinessForPrimary(diff);
	}
	
	public float calculateHappinessBySocial() {
		float diff = state.getSocial()-profile.getSocial();
		return calculateHappinessForPrimary(diff);
	}
	
	public float calculateHappinessForPrimary(float diff) {
		if (diff >= 0) {
			if (diff <= 20) 
				return 8.75f+(20-diff)/20*8.75f;
			else
				return (8.75f-(1-(40-(diff-20))/40)*8.75f) > 0 ? 8.75f-(1-(40-(diff-20))/40)*8.75f : 0;
		}
		else {
			if (diff >= -10) 
				return 8.75f+(10+diff)/10*8.75f;
			else
				return (8.75f-(1-(20+(diff+10))/20)*8.75f) > 0 ? 8.75f-(1-(20+(diff+10))/20)*8.75f : 0;
		}
	}
	
	public float calculateHappinessByHealth() {
		float num = state.getHealth()-profile.getHealth();
		float denom = 100-profile.getHealth();
		return (num/denom*10) > 0 ? num/denom*10 : 0;
	}
	
	public float calculateHappinessByRelaxation() {
		float num = state.getRelaxation()-profile.getRelaxation();
		float denom = 100-profile.getRelaxation();
		return (num/denom*10) > 0 ? num/denom*10 : 0;
	}
	
	public float calculateHappinessByVitality() {
		float num = state.getVitality()-profile.getVitality();
		float denom = 100-profile.getVitality();
		return (num/denom*10) > 0 ? num/denom*10 : 0;
	}
}
