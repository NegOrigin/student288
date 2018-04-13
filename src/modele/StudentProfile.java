package modele;

public class StudentProfile {
	private int gaming;
	private int love;
	private int school;
	private int social;

	private int confidence;
	private int health;
	private int shape;
	
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

		setConfidence((int)(Math.random()*30)+40);
		setHealth((int)(Math.random()*30)+40);
		setShape((int)(Math.random()*30)+40);
	}
	
	public StudentProfile(StudentProfile profile) {
		setGaming(profile.getGaming());
		setLove(profile.getLove());
		setSchool(profile.getSchool());
		setSocial(profile.getSocial());
		setConfidence(profile.getConfidence());
		setHealth(profile.getHealth());
		setShape(profile.getShape());
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
}
