package modele;

import java.util.ArrayList;

public class Fringe {
	private ArrayList<Node> nodeList;
	
	public Fringe() {
		setNodeList(new ArrayList<Node>());
	}

	public ArrayList<Node> getNodeList() {
		return nodeList;
	}

	private void setNodeList(ArrayList<Node> nodeList) {
		this.nodeList = nodeList;
	}
	
	public void insertNode(Node node) {
		this.getNodeList().add(node);
	}
	
	public void insertAll(ArrayList<Node> nodes) {
		this.getNodeList().addAll(nodes);
	}
	
	public Node getNode(int index) {
		return this.getNodeList().get(index);
	}
	
	public Node getFrontNode() {
		return this.getNode(0);
	}
	
	public void deleteNode(int index) {
		this.getNodeList().remove(index);
	}
	
	public void deleteFrontNode() {
		this.deleteNode(0);
	}
	
	public boolean isFringeEmpty() {
		return this.getNodeList().isEmpty();
	}
	
	public int nodeListSize() {
		return this.getNodeList().size();
	}
}
