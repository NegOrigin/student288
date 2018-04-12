package modele;

public class Student {
	private String name;
	private Profile profile;
	private State state;
	
	public Student(String name) {
		setName(name);
		setProfile(new Profile());
		setState(new State());
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public Profile getProfile() {
		return profile;
	}

	private void setProfile(Profile profile) {
		this.profile = profile;
	}

	public State getState() {
		return state;
	}

	private void setState(State state) {
		this.state = state;
	}
}
