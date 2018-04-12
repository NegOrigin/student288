package modele;

public class Profile {
	private int tiredness;
	private int stress;
	private int erudition;
	private int geekiness;
	private int health;
	private int love;
	private int social;
	private int school;
	private int happiness;
	
	public Profile() {
		setTiredness(tiredness);
		setStress(stress);
		setErudition(erudition);
		setGeekiness(geekiness);
		setHealth(health);
		setLove(love);
		setSocial(social);
		setSchool(school);
		setHappiness(happiness);
	}

	public int getTiredness() {
		return tiredness;
	}

	private void setTiredness(int tiredness) {
		this.tiredness = tiredness;
	}

	public int getStress() {
		return stress;
	}

	private void setStress(int stress) {
		this.stress = stress;
	}

	public int getErudition() {
		return erudition;
	}

	private void setErudition(int erudition) {
		this.erudition = erudition;
	}

	public int getGeekiness() {
		return geekiness;
	}

	private void setGeekiness(int geekiness) {
		this.geekiness = geekiness;
	}

	public int getHealth() {
		return health;
	}

	private void setHealth(int health) {
		this.health = health;
	}

	public int getLove() {
		return love;
	}

	private void setLove(int love) {
		this.love = love;
	}

	public int getSocial() {
		return social;
	}

	private void setSocial(int social) {
		this.social = social;
	}

	public int getSchool() {
		return school;
	}

	private void setSchool(int school) {
		this.school = school;
	}

	public int getHappiness() {
		return happiness;
	}

	private void setHappiness(int happiness) {
		this.happiness = happiness;
	}
}
