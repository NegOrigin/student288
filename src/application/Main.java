package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import modele.Action;
import modele.ActionContainer;
import modele.EventContainer;
import modele.EventGenerator;
import modele.GameTime;
import modele.Student;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
			Parent root = loader.load();
			MainSceneController controller =  loader.getController();
			
			Scene scene = new Scene(root,1080,720);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Student 288");
			primaryStage.setResizable(false);
			primaryStage.sizeToScene();
			primaryStage.show();
			
			controller.printInConsole("Cr�ation de l'espace temporel");
			GameTime gameTime = new GameTime(100);
			gameTime.setController(controller);
			
			controller.printInConsole("Cr�ation du conteneur d'actions");
			ActionContainer actionContainer = new ActionContainer();
			actionContainer.setController(controller);
			controller.setActionContainer(actionContainer);
			
			controller.printInConsole("D�finition des actions r�alisables");
			//name;image;alwaysAvailable;proba;	gaming;love;school;social;	health;relaxation;satiety;vitality;	
			//Les actions qui commencent par /!\\ sont mauvaises pour les stats de l'�tudiant
			actionContainer.addAction(new Action("Dormir", null, true, 0, 0, 0, 0, 0, 0.5f, 0.5f, -1, 3));
			actionContainer.addAction(new Action("Manger", null, true, 0, 0, 0, 0, 0, 0.5f, 0.5f, 20, -0.5f));
			actionContainer.addAction(new Action("Jouer en solo", null, true, 0, 1, -0.5f, -0.5f, -0.5f, -0.5f, 2, -3, -1.5f));
			actionContainer.addAction(new Action("Jouer en multi", null, true, 0, 1, 0, -0.5f, 0.5f, -0.5f, 2, -3, -2));
			actionContainer.addAction(new Action("R�viser", null, true, 0, -0.5f, -0.5f, 1.5f, -0.5f, -0.5f, 1, -3, -1.5f));
			actionContainer.addAction(new Action("Se promener", null, true, 0, 0, 0, 0, 0, 0.5f, 1, -5, -2.5f));
			actionContainer.addAction(new Action("Faire du sport", null, true, 0, 0, 0, 0, 0, 1, 1, -8, -4));
			actionContainer.addAction(new Action("Aller chez le m�decin", null, true, 0, 0, 0, 0, 0, 20, -1, -3, -2));
			actionContainer.addAction(new Action("Aller en LAN", null, false, 0, 3, -0.5f, -1, 1, -0.5f, 1, -4, -2.5f));
			actionContainer.addAction(new Action("Aller en Cours", null, false, 0, -0.5f, -0.5f, 1, 0.5f, -0.5f, 1, -4, -2.5f));
			actionContainer.addAction(new Action("Sortir avec des amis", null, false, 0.4f, -0.5f, -0.5f, -0.5f, 2, 0, 2, -5, -2.5f));
			actionContainer.addAction(new Action("Sortir avec son(sa) copain(ine)", null, false, 0.2f, -0.5f, 10, -0.5f, 0.5f, 0, 4, -5, -2.5f));
			actionContainer.addAction(new Action("/!\\ Maladie", null, false, 0.02f, 0, 0, 0, 0, -12, -6, -0.5f, -6));
			actionContainer.addAction(new Action("/!\\ Controle", null, false, 0.1f, 0, 0, 0, 0, -1, -8, -4, -3));
			actionContainer.addAction(new Action("/!\\ Dispute de couple", null, false, 0.04f, -0.5f, -6, -0.5f, -0.5f, -1, -10, -4, -5));
			actionContainer.addAction(new Action("/!\\ Dispute entre amis", null, false, 0.03f, -0.5f, -0.5f, -0.5f, -6, -1, -10, -4, -5));

			controller.printInConsole("Cr�ation du conteneur d'�v�nements");
			EventContainer eventContainer = new EventContainer();
			eventContainer.setGameTime(gameTime);
			eventContainer.setActioncontainer(actionContainer);
			eventContainer.setController(controller);
			controller.setEventContainer(eventContainer);
			
			controller.printInConsole("Cr�ation du g�n�rateur d'�v�nements");
			EventGenerator eventGenerator = new EventGenerator(gameTime, eventContainer, actionContainer);
			
			controller.printInConsole("Cr�ation de l'�tudiant");
			Student student = new Student("Etudiant test");
			student.setGameTimeAndEventContainer(gameTime, eventContainer);
			student.setController(controller);
			
			controller.printInConsole("Lancement de la simulation");
			gameTime.startGame();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
