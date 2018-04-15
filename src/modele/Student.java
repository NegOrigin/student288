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
		happiness += calculateHappinessByPrimary(profile.getGaming(), state.getGaming());
		happiness += calculateHappinessByPrimary(profile.getLove(), state.getLove());
		happiness += calculateHappinessByPrimary(profile.getSchool(), state.getSchool());
		happiness += calculateHappinessByPrimary(profile.getSocial(), state.getSocial());
		happiness += calculateHappinessBySecondary(profile.getHealth(), state.getHealth());
		happiness += calculateHappinessBySecondary(profile.getRelaxation(), state.getRelaxation());
		happiness += calculateHappinessBySecondary(profile.getSatiety(), state.getSatiety());
		happiness += calculateHappinessBySecondary(profile.getVitality(), state.getVitality());
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
