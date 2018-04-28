package modele;

import java.util.ArrayList;
import java.util.Calendar;

public class Solver extends Thread {
	public Solver() {
		
	}
	
	public Event treeSearch(Student student, GameTime gameTime, EventContainer eventContainer) {
		//On calcule un temps max
		Calendar timeLimit = (Calendar) student.getEventCurrent().getEnd().clone();
		timeLimit.add(Calendar.MINUTE, 90);

		ArrayList<Node> chemins=new ArrayList<Node>();
		
		//On fabrique le node de début
		Node node = new Node(student.getEventCurrent(), null, 0, 0);
		Node choice=null;
		
		Fringe fringe = new Fringe();
		fringe.insertNode(node);
		
		while(!fringe.isFringeEmpty()) {
			//Temps max atteint pour le front node
			//On stock le node
			if(fringe.getFrontNode().getEvent().getEnd().after(timeLimit)) {
				chemins.add(fringe.getFrontNode());
			}
			//Temps max NON atteint
			//On explore un niveau de plus
			else {
				fringe.insertAll(expand(fringe.getFrontNode(), eventContainer));
			}
			fringe.deleteFrontNode();
		}
		
		//On a tous les chemins possibles
		//On calcule leur probabilite par rapport au poids
		for (Node chemin : chemins) {
			
		}
		return choice.getEvent();
	}
	
	public ArrayList<Node> expand(Node node, EventContainer eventContainer) {
		ArrayList<Node> successors = new ArrayList<Node>();
		int score=0;
		for (Event event : eventContainer.getEventsByDate(node.getEvent().getEnd())) {
			//Calcul du score de l'event
			//TODO
			Node s = new Node(event, node, score, node.getDepth()+1);
			successors.add(s);
		}
		return successors;
	}
	
	public Event findEvent(Student student, GameTime gameTime, EventContainer eventContainer) {
		Calendar start = student.getEventCurrent().getEnd();
		Calendar end = (Calendar) start.clone();
		end.add(Calendar.MINUTE, 30);
		return new Event(new Action("Rien", "../images/actions/tmpBackground.png", false, 0, 0, 0, 0, 0, 0, 0, 0, 0), start, end);
	}
}
