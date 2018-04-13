package modele;

public class StudentProfile {
	private int gaming;
	private int love;
	private int school;
	private int social;

	private int health;
	private int relaxation;
	private int vitality;
	
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
		setVitality((int)(Math.random()*30)+40);
	}
	
	public StudentProfile(StudentProfile profile) {
		setGaming(profile.getGaming());
		setLove(profile.getLove());
		setSchool(profile.getSchool());
		setSocial(profile.getSocial());
		setHealth(profile.getHealth());
		setRelaxation(profile.getRelaxation());
		setVitality(profile.getVitality());
	}
	
	public String toString() {
		return "Gaming : "+getGaming()+", "
			+"Love : "+getLove()+", "
			+"School : "+getSchool()+", "
			+"Social : "+getSocial()+",\n"
			+"Health : "+getHealth()+", "
			+"Relaxation : "+getRelaxation()+", "
			+"Vitality : "+getVitality();
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

	public int getRelaxation() {
		return relaxation;
	}

	private void setRelaxation(int relaxation) {
		this.relaxation = relaxation;
	}

	public int getVitality() {
		return vitality;
	}

	private void setVitality(int vitality) {
		this.vitality = vitality;
	}
}
