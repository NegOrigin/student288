package modele;

public class StudentProfile {
	private float gaming;
	private float love;
	private float school;
	private float social;

	private float health;
	private float relaxation;
	private float satiety;
	private float vitality;
	
	public StudentProfile(StudentType type) {
		switch (type.getName()) {
		case "Gamer":
			setGaming((int)(Math.random()*20)+70);
			setLove((int)(Math.random()*50)+25);
			setSchool((int)(Math.random()*50)+25);
			setSocial((int)(Math.random()*50)+25);
			break;
		case "Romantic":
			setGaming((int)(Math.random()*50)+25);
			setLove((int)(Math.random()*20)+70);
			setSchool((int)(Math.random()*50)+25);
			setSocial((int)(Math.random()*50)+25);
			break;
		case "Studious":
			setGaming((int)(Math.random()*50)+25);
			setLove((int)(Math.random()*50)+25);
			setSchool((int)(Math.random()*20)+70);
			setSocial((int)(Math.random()*50)+25);
			break;
		case "Social":
			setGaming((int)(Math.random()*50)+25);
			setLove((int)(Math.random()*50)+25);
			setSchool((int)(Math.random()*50)+25);
			setSocial((int)(Math.random()*20)+70);
			break;
		default:
			setGaming((int)(Math.random()*100));
			setLove((int)(Math.random()*100));
			setSchool((int)(Math.random()*100));
			setSocial((int)(Math.random()*100));
			break;
		}

		setHealth((int)(Math.random()*30)+40);
		setRelaxation((int)(Math.random()*30)+40);
		setSatiety((int)(Math.random()*30)+40);
		setVitality((int)(Math.random()*30)+40);
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
