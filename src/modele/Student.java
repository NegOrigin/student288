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
			+"Etat :\n"+state;
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
		int happiness = 0;
		return happiness;
	}
}
