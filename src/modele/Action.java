package modele;

public class Action {
	private String name;
	
	public Action(String name) {
		setName(name);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}
}
