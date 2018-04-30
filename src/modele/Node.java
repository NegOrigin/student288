package modele;

public class Node {
	private Event event;
	private Node parent;
	private float pathScore;
	private int depth;
	private Student student;
	
	public Node(Event event, Node parent, float pathScore, int depth, Student student) {
		setEvent(event);
		setParent(parent);
		setPathScore(pathScore);
		setDepth(depth);
		setStudent(student);
	}

	public Event getEvent() {
		return event;
	}

	private void setEvent(Event event) {
		this.event = event;
	}

	public Node getParent() {
		return parent;
	}

	private void setParent(Node parent) {
		this.parent = parent;
	}

	public float getPathScore() {
		return pathScore;
	}

	public void setPathScore(float pathScore) {
		this.pathScore = pathScore;
	}

	public int getDepth() {
		return depth;
	}

	private void setDepth(int depth) {
		this.depth = depth;
	}

	public Student getStudent() {
		return student;
	}

	private void setStudent(Student student) {
		this.student = student;
	}
}
