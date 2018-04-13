package modele;

public class StudentState {
	private int gaming;
	private int love;
	private int school;
	private int social;

	private int confidence;
	private int health;
	private int shape;
	
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

		setConfidence((int)(Math.random()*30)+70);
		setHealth((int)(Math.random()*30)+70);
		setShape((int)(Math.random()*30)+70);
		
		setHappiness(0);
	}
	
	public StudentState(StudentState state) {
		setGaming(state.getGaming());
		setLove(state.getLove());
		setSchool(state.getSchool());
		setSocial(state.getSocial());
		setConfidence(state.getConfidence());
		setHealth(state.getHealth());
		setShape(state.getShape());
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

	public int getConfidence() {
		return confidence;
	}

	private void setConfidence(int confidence) {
		this.confidence = confidence;
	}

	public int getHealth() {
		return health;
	}

	private void setHealth(int health) {
		this.health = health;
	}

	public int getShape() {
		return shape;
	}

	private void setShape(int shape) {
		this.shape = shape;
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
