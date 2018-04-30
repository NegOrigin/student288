package modele;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class Solver {
	public Solver() {
		
	}
	
	public Event findEvent(Student student, GameTime gameTime, EventContainer eventContainer) {
		//On calcule un temps max
		Calendar timeLimit = (Calendar) student.getEventCurrent().getEnd().clone();
		timeLimit.add(Calendar.MINUTE, 60);

		ArrayList<Node> chemins=new ArrayList<Node>();
		ArrayList<Node> cheminsSelectionnes=new ArrayList<Node>();
		
		//On fabrique le node de début
		Node node = new Node(student.getEventCurrent(), null, 0, 0, student);
		Node choice = null;
		float totalScore=0;
		float tmpScore=0;
		float randomNumber;
		
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
				fringe.insertAll(expand(fringe.getFrontNode(), eventContainer, student, gameTime));				
			}
			fringe.deleteFrontNode();
		}
		
		//On a tous les chemins possibles
		//On calcule le score total
		
		for (Node chemin : chemins) {
			chemin.setPathScore((chemin.getPathScore()/(chemin.getEvent().getEnd().getTimeInMillis()/1800000f - student.getEventCurrent().getEnd().getTimeInMillis()/1800000f)));
		}

		float min = Collections.min(chemins, new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				if (n1.getPathScore() < n2.getPathScore()) return -1;
				if (n1.getPathScore() > n2.getPathScore()) return 1;
				return 0;
			}
		}).getPathScore();
		
		//On tri la liste
		Collections.sort(chemins, new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				if (n1.getPathScore() < n2.getPathScore()) return 1;
				if (n1.getPathScore() > n2.getPathScore()) return -1;
				return 0;
			}
		});
		
		//On récupère les x meilleurs et un echantillon des autres
		int i=0;
		for (Node chemin : chemins) {
			if(i < 30 || (int)(Math.random()*100)+1 <= 3) {				
				//On ajoute le minimum partout pour avoir des scores positifs
				chemin.setPathScore(chemin.getPathScore()-min+1);
				
				totalScore+=chemin.getPathScore();
				//System.out.println("Avant : " + chemin.getPathScore());
				
				//System.out.println("Après : " + chemin.getPathScore());
				cheminsSelectionnes.add(chemin);
				//System.out.println("poids : " + chemin.getPathScore());
			}
			i++;
		}
		
		//On choisi le node à suivre avec un tirage pondéré
		randomNumber = (float)(Math.random()*totalScore);
		System.out.println("rand : " + randomNumber + " total : " + totalScore + " nb chemins : " + cheminsSelectionnes.size());
		
		for (Node chemin : cheminsSelectionnes) {
			tmpScore+=chemin.getPathScore();
			if(randomNumber<tmpScore){
				choice=chemin;
				System.out.println("poids : " + choice.getPathScore());
				break;
			}
		}		

		//On récupère le deuxième event, puisque le premier est l'event actuel
		if(choice.getParent() != null) {
			while(choice.getParent().getParent()!=null) {
				choice = choice.getParent();
			}
		}
		
		System.out.println("choix : " + choice.getEvent().getAction().getName() +
				" \ndébut : " + choice.getEvent().getStart().getTime() + " fin : " + choice.getEvent().getEnd().getTime());
		return choice.getEvent();
	}
	
	public ArrayList<Node> expand(Node node, EventContainer eventContainer, Student studentInitial, GameTime gameTime) {
		ArrayList<Node> successors = new ArrayList<Node>();
		float score=0;
		Student studd;
		for (Event event : eventContainer.getEventsByDate(node.getEvent().getEnd())) {
			//System.out.println("event dispo : " + event.getAction().getName());
			studd = new Student(node.getStudent());
			studd.setEventCurrent(event);
			
			//Calcul du score de l'event
			score = node.getPathScore() + 4*(studd.calculateHappiness() - node.getStudent().calculateHappiness());
			
			score += studd.getStudentState().getGaming() - node.getStudent().getStudentState().getGaming();
			score += studd.getStudentState().getLove() - node.getStudent().getStudentState().getLove();
			score += studd.getStudentState().getSchool() - node.getStudent().getStudentState().getSchool();
			score += studd.getStudentState().getSocial() - node.getStudent().getStudentState().getSocial();
			
			score += studd.getStudentState().getHealth() - node.getStudent().getStudentState().getHealth();
			score += studd.getStudentState().getRelaxation() - node.getStudent().getStudentState().getRelaxation();
			score += studd.getStudentState().getSatiety() - node.getStudent().getStudentState().getSatiety();
			score += studd.getStudentState().getVitality() - node.getStudent().getStudentState().getVitality();
			
			//Pour chaque stat, si l'ecart entre profil et état diminue, on rajoute cet écart au score
			//Pour valoriser les actions qui remontent une stat très loin de son idéal
			if(Math.abs(studd.getStudentState().getGaming() - studd.getStudentProfile().getGaming()) < 
					Math.abs(node.getStudent().getStudentState().getGaming() - node.getStudent().getStudentProfile().getGaming())) {
				score += 100*Math.abs(studd.getStudentState().getGaming() - studd.getStudentProfile().getGaming())/100/(node.getDepth()+1);
			}
			if(Math.abs(studd.getStudentState().getLove() - studd.getStudentProfile().getLove()) < 
					Math.abs(node.getStudent().getStudentState().getLove() - node.getStudent().getStudentProfile().getLove())) {
				score += 100*Math.abs(studd.getStudentState().getLove() - studd.getStudentProfile().getLove())/100/(node.getDepth()+1);
			}
			if(Math.abs(studd.getStudentState().getSchool() - studd.getStudentProfile().getSchool()) < 
					Math.abs(node.getStudent().getStudentState().getSchool() - node.getStudent().getStudentProfile().getSchool())) {
				score += 100*Math.abs(studd.getStudentState().getSchool() - studd.getStudentProfile().getSchool())/100/(node.getDepth()+1);
			}
			if(Math.abs(studd.getStudentState().getSocial() - studd.getStudentProfile().getSocial()) < 
					Math.abs(node.getStudent().getStudentState().getSocial() - node.getStudent().getStudentProfile().getSocial())) {
				score += 100*Math.abs(studd.getStudentState().getSocial() - studd.getStudentProfile().getSocial())/100/(node.getDepth()+1);
			}
			
			//Si une des stats de l'état est en dessous du seuil critique et l'action actuelle remonte cette stat
			if(node.getStudent().getStudentState().getHealth() < node.getStudent().getStudentProfile().getHealth() && 
					studd.getStudentState().getHealth() > node.getStudent().getStudentState().getHealth()) {
				score += 300*(0.5+studd.getStudentProfile().getHealth()-studd.getStudentState().getHealth())/(node.getDepth()+1);
			}
			if(node.getStudent().getStudentState().getRelaxation() < node.getStudent().getStudentProfile().getRelaxation() && 
					studd.getStudentState().getRelaxation() > node.getStudent().getStudentState().getRelaxation()) {
				score += 300*(0.5+studd.getStudentProfile().getRelaxation()-studd.getStudentState().getRelaxation())/(node.getDepth()+1);
			}
			if(node.getStudent().getStudentState().getSatiety() < node.getStudent().getStudentProfile().getSatiety() && 
					studd.getStudentState().getSatiety() > node.getStudent().getStudentState().getSatiety()) {
				score += 300*(0.5+studd.getStudentProfile().getSatiety()-studd.getStudentState().getSatiety())/(node.getDepth()+1);
			}
			if(node.getStudent().getStudentState().getVitality() < node.getStudent().getStudentProfile().getVitality() && 
					studd.getStudentState().getVitality() > node.getStudent().getStudentState().getVitality()) {
				score += 300*(0.5+studd.getStudentProfile().getVitality()-studd.getStudentState().getVitality())/(node.getDepth()+1);
			}
			
			//Dormir la nuit c'est mieux
			if(gameTime.getNow().get(Calendar.HOUR_OF_DAY)>0 && gameTime.getNow().get(Calendar.HOUR_OF_DAY)<8 
					&& event.getAction().getName().contains("Dormir")) {
				score += 1500/(node.getDepth()+1); //Plus il est proche de le faire, plus il a de points
			}
			else if(event.getAction().getName().contains("Dormir")){
				score -= 50;
			}
			
			//Manger n'importe quand c'est mal
			if(event.getAction().getName().contains("Manger")) {			
				if(((gameTime.getNow().get(Calendar.HOUR_OF_DAY)>13 && gameTime.getNow().get(Calendar.HOUR_OF_DAY)<14) 
						|| (gameTime.getNow().get(Calendar.HOUR_OF_DAY)>19 && gameTime.getNow().get(Calendar.HOUR_OF_DAY)<21)
						|| (gameTime.getNow().get(Calendar.HOUR_OF_DAY)>8 && gameTime.getNow().get(Calendar.HOUR_OF_DAY)<9))) {
					score += 1000/(node.getDepth()+1);
				}
				else {
					score -= 50;
				}				
			}
			
			if(!event.getAction().isAlwaysAvailable()) {
				score += 1000*(event.getEnd().getTimeInMillis()/1800000f - studentInitial.getEventCurrent().getEnd().getTimeInMillis()/1800000f)/(node.getDepth()+1);
				System.out.println("Faut y aller");
				System.out.println(score);
			}
			if(event.getAction().getName().contains("/!\\")) {
				score += 2000*(event.getEnd().getTimeInMillis()/1800000f - studentInitial.getEventCurrent().getEnd().getTimeInMillis()/1800000f)/(node.getDepth()+1);
				System.out.println("Pas bien");
				System.out.println(score);
			}
			
			Node s = new Node(event, node, score, node.getDepth()+1, studd);
			successors.add(s);
		}
		return successors;
	}
}
