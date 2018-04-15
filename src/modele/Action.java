package modele;

public class Action {
	private String name;
	
	private boolean alwaysAvailable;

	private float gaming;
	private float love;
	private float school;
	private float social;

	private float health;
	private float relaxation;
	private float satiety;
	private float vitality;
	
	public Action(String name, boolean alwaysAvailable, float gaming, float love, float school, float social, float health, float relaxation, float satiety, float vitality) {
		setName(name);
		setAlwaysAvailable(alwaysAvailable);
		setGaming(gaming);
		setLove(love);
		setSchool(school);
		setSocial(social);
		setHealth(health);
		setRelaxation(relaxation);
		setSatiety(satiety);
		setVitality(vitality);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public boolean isAlwaysAvailable() {
		return alwaysAvailable;
	}

	private void setAlwaysAvailable(boolean alwaysAvailable) {
		this.alwaysAvailable = alwaysAvailable;
	}

	public float getGaming() {
		return gaming;
	}

	private void setGaming(float gaming) {
		this.gaming = gaming;
	}

	public float getLove() {
		return love;
	}

	private void setLove(float love) {
		this.love = love;
	}

	public float getSchool() {
		return school;
	}

	private void setSchool(float school) {
		this.school = school;
	}

	public float getSocial() {
		return social;
	}

	private void setSocial(float social) {
		this.social = social;
	}

	public float getHealth() {
		return health;
	}

	private void setHealth(float health) {
		this.health = health;
	}

	public float getRelaxation() {
		return relaxation;
	}

	private void setRelaxation(float relaxation) {
		this.relaxation = relaxation;
	}

	public float getSatiety() {
		return satiety;
	}

	public void setSatiety(float satiety) {
		this.satiety = satiety;
	}

	public float getVitality() {
		return vitality;
	}

	private void setVitality(float vitality) {
		this.vitality = vitality;
	}
}
