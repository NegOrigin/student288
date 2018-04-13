package modele;

public class StudentState {
	private int gaming;
	private int love;
	private int school;
	private int social;
	
	private int health;
	private int stress;
	private int tiredness;
	
	private int happiness;
	
	public StudentState(StudentType type) {
		setGaming(0);
		setLove(0);
		setSchool(0);
		setSocial(0);

		setHealth(0);
		setStress(0);
		setTiredness(0);
		
		setHappiness(0);
	}

	public int getGaming() {
		return gaming;
	}

	private void setGaming(int gaming) {
		this.gaming = gaming;
	}

	public int getLove() {
		return love;
	}

	private void setLove(int love) {
		this.love = love;
	}

	public int getSchool() {
		return school;
	}

	private void setSchool(int school) {
		this.school = school;
	}

	public int getSocial() {
		return social;
	}

	private void setSocial(int social) {
		this.social = social;
	}

	public int getHealth() {
		return health;
	}

	private void setHealth(int health) {
		this.health = health;
	}

	public int getStress() {
		return stress;
	}

	private void setStress(int stress) {
		this.stress = stress;
	}

	public int getTiredness() {
		return tiredness;
	}

	private void setTiredness(int tiredness) {
		this.tiredness = tiredness;
	}

	public int getHappiness() {
		return happiness;
	}

	private void setHappiness(int happiness) {
		this.happiness = happiness;
	}
}
