package modele;

public class StudentState {
	private float gaming;
	private float love;
	private float school;
	private float social;

	private float health;
	private float relaxation;
	private float satiety;
	private float vitality;
	
	public StudentState(StudentType type) {
		switch (type.getName()) {
		case "Gamer":
			setGaming((int)(Math.random()*40)+50);
			setLove((int)(Math.random()*70)+5);
			setSchool((int)(Math.random()*70)+5);
			setSocial((int)(Math.random()*70)+5);
			break;
		case "Romantic":
			setGaming((int)(Math.random()*70)+5);
			setLove((int)(Math.random()*40)+50);
			setSchool((int)(Math.random()*70)+5);
			setSocial((int)(Math.random()*70)+5);
			break;
		case "Studious":
			setGaming((int)(Math.random()*70)+5);
			setLove((int)(Math.random()*70)+5);
			setSchool((int)(Math.random()*40)+50);
			setSocial((int)(Math.random()*70)+5);
			break;
		case "Social":
			setGaming((int)(Math.random()*70)+5);
			setLove((int)(Math.random()*70)+5);
			setSchool((int)(Math.random()*70)+5);
			setSocial((int)(Math.random()*40)+50);
			break;
		default:
			setGaming((int)(Math.random()*100));
			setLove((int)(Math.random()*100));
			setSchool((int)(Math.random()*100));
			setSocial((int)(Math.random()*100));
			break;
		}

		setHealth((int)(Math.random()*30)+70);
		setRelaxation((int)(Math.random()*30)+70);
		setSatiety((int)(Math.random()*30)+70);
		setVitality((int)(Math.random()*30)+70);
	}
	
	public StudentState(StudentState state) {
		setGaming(state.getGaming());
		setLove(state.getLove());
		setSchool(state.getSchool());
		setSocial(state.getSocial());
		setHealth(state.getHealth());
		setRelaxation(state.getRelaxation());
		setSatiety(state.getSatiety());
		setVitality(state.getVitality());
	}
	
	public String toString() {
		return "Gaming : "+Math.round(getGaming())+", "
			+"Love : "+Math.round(getLove())+", "
			+"School : "+Math.round(getSchool())+", "
			+"Social : "+Math.round(getSocial())+",\n"
			+"Health : "+Math.round(getHealth())+", "
			+"Relaxation : "+Math.round(getRelaxation())+", "
			+"Satiety : "+Math.round(getSatiety())+", "
			+"Vitality : "+Math.round(getVitality());
	}

	public float getGaming() {
		return gaming;
	}

	private void setGaming(float gaming) {
		if (gaming < 0) this.gaming = 0;
		else if (gaming > 100) this.gaming = 100;
		else this.gaming = gaming;
	}

	public float getLove() {
		return love;
	}

	private void setLove(float love) {
		if (love < 0) this.love = 0;
		else if (love > 100) this.love = 100;
		else this.love = love;
	}

	public float getSchool() {
		return school;
	}

	private void setSchool(float school) {
		if (school < 0) this.school = 0;
		else if (school > 100) this.school = 100;
		else this.school = school;
	}

	public float getSocial() {
		return social;
	}

	private void setSocial(float social) {
		if (social < 0) this.social = 0;
		else if (social > 100) this.social = 100;
		else this.social = social;
	}

	public float getHealth() {
		return health;
	}

	private void setHealth(float health) {
		if (health < 0) this.health = 0;
		else if (health > 100) this.health = 100;
		else this.health = health;
	}

	public float getRelaxation() {
		return relaxation;
	}

	private void setRelaxation(float relaxation) {
		if (relaxation < 0) this.relaxation = 0;
		else if (relaxation > 100) this.relaxation = 100;
		else this.relaxation = relaxation;
	}

	public float getSatiety() {
		return satiety;
	}

	private void setSatiety(float satiety) {
		if (satiety < 0) this.satiety = 0;
		else if (satiety > 100) this.satiety = 100;
		else this.satiety = satiety;
	}

	public float getVitality() {
		return vitality;
	}

	private void setVitality(float vitality) {
		if (vitality < 0) this.vitality = 0;
		else if (vitality > 100) this.vitality = 100;
		else this.vitality = vitality;
	}
	
	public void updateState(float gaming, float love, float school, float social, float health, float relaxation, float satiety, float vitality) {
		setGaming(getGaming()+gaming);
		setLove(getLove()+love);
		setSchool(getSchool()+school);
		setSocial(getSocial()+social);
		setHealth(getHealth()+health);
		setRelaxation(getRelaxation()+relaxation);
		setSatiety(getSatiety()+satiety);
		setVitality(getVitality()+vitality);
	}
}
