package modele;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class Solver extends Thread {
	public Solver() {
		
	}
	
	public Event treeSearch(Student student, GameTime gameTime, EventContainer eventContainer) {
		//On calcule un temps max
		Calendar timeLimit = (Calendar) student.getEventCurrent().getEnd().clone();
		timeLimit.add(Calendar.MINUTE, 90);

		ArrayList<Node> chemins=new ArrayList<Node>();
		
		//On fabrique le node de début
		Node node = new Node(student.getEventCurrent(), null, 0, 0, student);
		Node choice = null;
		int totalScore=0;
		int tmpScore=0;
		int randomNumber;
		
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
				fringe.insertAll(expand(fringe.getFrontNode(), eventContainer, student));
			}
			fringe.deleteFrontNode();
		}
		
		//On a tous les chemins possibles
		//On calcule le score total
		for (Node chemin : chemins) {
			totalScore+=chemin.getPathScore();
		}
		//On choisi le node à suivre avec un tirage pondéré
		randomNumber = (int)Math.random()*totalScore;
		
		for (Node chemin : chemins) {
			tmpScore+=chemin.getPathScore();
			if(randomNumber<tmpScore){
				choice=chemin;
				break;
			}	
		}
		
		//On récupère le deuxième event, puisque le premier est l'event actuel
		while(choice.getParent().getParent()!=null) {
			choice=choice.getParent();
		}
		return choice.getEvent();
	}
	
	public ArrayList<Node> expand(Node node, EventContainer eventContainer, Student studentInitial, GameTime gameTime) {
		ArrayList<Node> successors = new ArrayList<Node>();
		int score=0;
		Student studd;
		for (Event event : eventContainer.getEventsByDate(node.getEvent().getEnd())) {		
			studd = new Student(node.getStudent());
			studd.setEventCurrent(event);
			
			//Calcul du score de l'event
			//TODO
			score = studd.calculateHappiness() - studentInitial.calculateHappiness();
			
			score += studd.getStudentState().getGaming() - studentInitial.getStudentState().getGaming();
			score += studd.getStudentState().getLove() - studentInitial.getStudentState().getLove();
			score += studd.getStudentState().getSchool() - studentInitial.getStudentState().getSchool();
			score += studd.getStudentState().getSocial() - studentInitial.getStudentState().getSocial();
			
			score += studd.getStudentState().getHealth() - studentInitial.getStudentState().getHealth();
			score += studd.getStudentState().getRelaxation() - studentInitial.getStudentState().getRelaxation();
			score += studd.getStudentState().getSatiety() - studentInitial.getStudentState().getSatiety();
			score += studd.getStudentState().getVitality() - studentInitial.getStudentState().getVitality();
			
			//Dormir la nuit c'est mieux
			if(gameTime.getNow().get(Calendar.HOUR_OF_DAY)>0 && gameTime.getNow().get(Calendar.HOUR_OF_DAY)<8 
					&& event.getAction().getName().contains("Dormir")) {
				score += 50;
			}
			
			if(!event.getAction().isAlwaysAvailable()) {
				score += 100;
			}
			if(event.getAction().getName().contains("/!\\")) {
				score += 200;
			}
			Node s = new Node(event, node, score, node.getDepth()+1, studd);
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
