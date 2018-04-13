package modele;

public class StudentState {
	private int gaming;
	private int love;
	private int school;
	private int social;

	private int health;
	private int relaxation;
	private int vitality;
	
	private int happiness;
	
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
		setVitality((int)(Math.random()*30)+70);
		
		setHappiness(0);
	}
	
	public StudentState(StudentState state) {
		setGaming(state.getGaming());
		setLove(state.getLove());
		setSchool(state.getSchool());
		setSocial(state.getSocial());
		setHealth(state.getHealth());
		setRelaxation(state.getRelaxation());
		setVitality(state.getVitality());
		setHappiness(state.getHappiness());
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

	public int getHappiness() {
		return happiness;
	}

	private void setHappiness(int happiness) {
		this.happiness = happiness;
	}
	
	public void calculateHappiness(StudentProfile profile) {
		int happiness = 0;
		//Calcul
		setHappiness(happiness);
	}
}
