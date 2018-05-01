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
		float totalScore = 0;
		float tmpScore = 0;
		float randomNumber;
		
		Fringe fringe = new Fringe();
		fringe.insertNode(node);
		
		while (!fringe.isFringeEmpty()) {
			//Si le fontNode se termine après la date au plus tard recherchée, on le stock
			if (fringe.getFrontNode().getEvent().getEnd().after(timeLimit))
				chemins.add(fringe.getFrontNode());
			//Sinon, on explore ses fils
			else
				fringe.insertAll(expand(fringe.getFrontNode(), eventContainer, student, gameTime));
			//On retire le frontNode des noeuds à explorer
			fringe.deleteFrontNode();
		}
		
		//On définit le score pour chaque branche comme étant le score moyen pour cette branche par 30 minutes
		for (Node chemin : chemins) {
			chemin.setPathScore((chemin.getPathScore()/(chemin.getEvent().getEnd().getTimeInMillis()/1800000f - student.getEventCurrent().getEnd().getTimeInMillis()/1800000f)));
		}
		
		//On tri la liste du noeud avec le plus grand score au plus petit
		Collections.sort(chemins, new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				if (n1.getPathScore() < n2.getPathScore()) return 1;
				if (n1.getPathScore() > n2.getPathScore()) return -1;
				return 0;
			}
		});
		//On récupère le score minimal, soit le dernier élément de la liste
		float min = chemins.get(chemins.size()-1).getPathScore();
		
		//On récupère les 30 meilleurs noeuds et un echantillon des autres
		int i = 0;
		for (Node chemin : chemins) {
			if (i < 30 || (int)(Math.random()*100)+1 <= 3) {				
				//On ajoute le score minimum additionné de 1 à tous les scores pour n'avoir que des scores positifs
				chemin.setPathScore(chemin.getPathScore()-min+1);
				//On récupère le score total que représente l'ensemble du tirage
				totalScore+=chemin.getPathScore();
				//On ajoute la branche à la séléction
				cheminsSelectionnes.add(chemin);
			}
			i++;
		}
		
		//On choisi la branche à suivre avec un tirage pondéré
		randomNumber = (float)(Math.random()*totalScore);
		
		for (Node chemin : cheminsSelectionnes) {
			tmpScore += chemin.getPathScore();
			if (randomNumber<tmpScore){
				choice=chemin;
				break;
			}
		}		

		//On récupère le deuxième event, puisque le premier est l'event actuel
		if (choice.getParent() != null) {
			while (choice.getParent().getParent() != null) {
				choice = choice.getParent();
			}
		}
		
		//On renvoit l'événement sélectionné
		return choice.getEvent();
	}
	
	public ArrayList<Node> expand(Node node, EventContainer eventContainer, Student studentInitial, GameTime gameTime) {
		ArrayList<Node> successors = new ArrayList<Node>();
		
		float score = 0;
		Student student;
		
		//Pour chaque événement qu'il est possible de réaliser à la suite de l'événement du noeud actuel
		for (Event event : eventContainer.getEventsByDate(node.getEvent().getEnd())) {
			//Copie de l'étudiant en lui donnant comme événement courant l'événement étudié
			student = new Student(node.getStudent());
			student.setEventCurrent(event);
			
			//Calcul du score de l'event
			score = node.getPathScore() + 4*(student.calculateHappiness() - node.getStudent().calculateHappiness());
			
			score += student.getStudentState().getGaming() - node.getStudent().getStudentState().getGaming();
			score += student.getStudentState().getLove() - node.getStudent().getStudentState().getLove();
			score += student.getStudentState().getSchool() - node.getStudent().getStudentState().getSchool();
			score += student.getStudentState().getSocial() - node.getStudent().getStudentState().getSocial();
			
			score += student.getStudentState().getHealth() - node.getStudent().getStudentState().getHealth();
			score += student.getStudentState().getRelaxation() - node.getStudent().getStudentState().getRelaxation();
			score += student.getStudentState().getSatiety() - node.getStudent().getStudentState().getSatiety();
			score += student.getStudentState().getVitality() - node.getStudent().getStudentState().getVitality();
			
			//Pour chaque stat, si l'ecart entre profil et état diminue, on rajoute cet écart au score
			//de sorte à valoriser les actions qui remontent une stat très loin de son idéal
			if (Math.abs(student.getStudentState().getGaming() - student.getStudentProfile().getGaming()) 
					< Math.abs(node.getStudent().getStudentState().getGaming() - node.getStudent().getStudentProfile().getGaming()))
				score += 100*Math.abs(student.getStudentState().getGaming() - student.getStudentProfile().getGaming())/100/(node.getDepth()+1);
			if (Math.abs(student.getStudentState().getLove() - student.getStudentProfile().getLove()) 
					< Math.abs(node.getStudent().getStudentState().getLove() - node.getStudent().getStudentProfile().getLove()))
				score += 100*Math.abs(student.getStudentState().getLove() - student.getStudentProfile().getLove())/100/(node.getDepth()+1);
			if (Math.abs(student.getStudentState().getSchool() - student.getStudentProfile().getSchool()) 
					< Math.abs(node.getStudent().getStudentState().getSchool() - node.getStudent().getStudentProfile().getSchool()))
				score += 100*Math.abs(student.getStudentState().getSchool() - student.getStudentProfile().getSchool())/100/(node.getDepth()+1);
			if (Math.abs(student.getStudentState().getSocial() - student.getStudentProfile().getSocial()) 
					< Math.abs(node.getStudent().getStudentState().getSocial() - node.getStudent().getStudentProfile().getSocial()))
				score += 100*Math.abs(student.getStudentState().getSocial() - student.getStudentProfile().getSocial())/100/(node.getDepth()+1);
			
			//Si une des stats de l'état est en dessous du seuil critique et que l'action actuelle remonte cette stat
			if (node.getStudent().getStudentState().getHealth() < node.getStudent().getStudentProfile().getHealth() 
					&& student.getStudentState().getHealth() > node.getStudent().getStudentState().getHealth())
				score += 300*(0.5+student.getStudentProfile().getHealth()-student.getStudentState().getHealth())/(node.getDepth()+1);
			if (node.getStudent().getStudentState().getRelaxation() < node.getStudent().getStudentProfile().getRelaxation() 
					&& student.getStudentState().getRelaxation() > node.getStudent().getStudentState().getRelaxation())
				score += 300*(0.5+student.getStudentProfile().getRelaxation()-student.getStudentState().getRelaxation())/(node.getDepth()+1);
			if (node.getStudent().getStudentState().getSatiety() < node.getStudent().getStudentProfile().getSatiety() 
					&& student.getStudentState().getSatiety() > node.getStudent().getStudentState().getSatiety())
				score += 300*(0.5+student.getStudentProfile().getSatiety()-student.getStudentState().getSatiety())/(node.getDepth()+1);
			if (node.getStudent().getStudentState().getVitality() < node.getStudent().getStudentProfile().getVitality() 
					&& student.getStudentState().getVitality() > node.getStudent().getStudentState().getVitality())
				score += 300*(0.5+student.getStudentProfile().getVitality()-student.getStudentState().getVitality())/(node.getDepth()+1);
			
			//Dormir la nuit c'est mieux
			if (gameTime.getNow().get(Calendar.HOUR_OF_DAY)>0 && gameTime.getNow().get(Calendar.HOUR_OF_DAY)<8 
					&& event.getAction().getName().contains("Dormir"))
				score += 1500/(node.getDepth()+1); //Plus il est proche de le faire, plus il a de points
			else if (event.getAction().getName().contains("Dormir"))
				score -= 50;
			
			//Manger n'importe quand c'est mal
			if (event.getAction().getName().contains("Manger"))	{		
				if (((gameTime.getNow().get(Calendar.HOUR_OF_DAY)>13 && gameTime.getNow().get(Calendar.HOUR_OF_DAY)<14) 
						|| (gameTime.getNow().get(Calendar.HOUR_OF_DAY)>19 && gameTime.getNow().get(Calendar.HOUR_OF_DAY)<21)
						|| (gameTime.getNow().get(Calendar.HOUR_OF_DAY)>8 && gameTime.getNow().get(Calendar.HOUR_OF_DAY)<9)))
					score += 1000/(node.getDepth()+1);
			}
			
			if (!event.getAction().isAlwaysAvailable())
				score += 1000*(event.getEnd().getTimeInMillis()/1800000f - studentInitial.getEventCurrent().getEnd().getTimeInMillis()/1800000f)/(node.getDepth()+1);
			if (event.getAction().getName().contains("/!\\")) 
				score += 2000*(event.getEnd().getTimeInMillis()/1800000f - studentInitial.getEventCurrent().getEnd().getTimeInMillis()/1800000f)/(node.getDepth()+1);
			
			Node s = new Node(event, node, score, node.getDepth()+1, student);
			successors.add(s);
		}
		return successors;
	}
}
