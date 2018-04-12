package modele;

public class Node {
	private Event event;
	private Node parent;
	private int pathScore;
	private int depth;
	
	public Node(Event event, Node parent, int pathScore, int depthn) {
		setEvent(event);
		setParent(parent);
		setPathScore(pathScore);
		setDepth(depth);
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

	public int getPathScore() {
		return pathScore;
	}

	private void setPathScore(int pathScore) {
		this.pathScore = pathScore;
	}

	public int getDepth() {
		return depth;
	}

	private void setDepth(int depth) {
		this.depth = depth;
	}
}