package modele;

public class StudentType {
	private int id;
	private String name;
	
	public StudentType() {
		setId((int)(Math.random()*4)+1);
		
		if ((int)(Math.random()*1000) == 42)
			setId(0);
		
		switch (id) {
		case 1:
			setName("Gamer");
			break;
		case 2:
			setName("Romantic");
			break;
		case 3:
			setName("Studious");
			break;
		case 4:
			setName("Social");
			break;
		default:
			setName("T%^r#K&");
			break;
		}
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}
}
