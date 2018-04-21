package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import modele.Action;
import modele.ActionContainer;
import modele.EventContainer;
import modele.EventGenrator;
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
			actionContainer.addAction(new Action("Dormir", null, true, 0, 0, 0, 0, 0, 0, 0, 0));
			actionContainer.addAction(new Action("Manger", null, true, 0, 0, 0, 0, 0, 0, 0, 0));
			actionContainer.addAction(new Action("Jouer", null, true, 0, 0, 0, 0, 0, 0, 0, 0));
			actionContainer.addAction(new Action("R�viser", null, true, 0, 0, 0, 0, 0, 0, 0, 0));
			actionContainer.addAction(new Action("Se promener", null, true, 0, 0, 0, 0, 0, 0, 0, 0));
			actionContainer.addAction(new Action("Faire du sport", null, true, 0, 0, 0, 0, 0, 0, 0, 0));
			actionContainer.addAction(new Action("Aller chez le m�decin", null, true, 0, 0, 0, 0, 0, 0, 0, 0));
			actionContainer.addAction(new Action("Aller en LAN", null, false, 0, 0, 0, 0, 0, 0, 0, 0));
			actionContainer.addAction(new Action("Aller en Cours", null, false, 0, 0, 0, 0, 0, 0, 0, 0));
			actionContainer.addAction(new Action("Sortir avec des amis", null, false, 0, 0, 0, 0, 0, 0, 0, 0));
			actionContainer.addAction(new Action("Sortir avec son(sa) copain(ine)", null, false, 0, 0, 0, 0, 0, 0, 0, 0));
			
			controller.printInConsole("Cr�ation du conteneur d'�v�nements");
			EventContainer eventContainer = new EventContainer();
			eventContainer.setActioncontainer(actionContainer);
			eventContainer.setController(controller);
			controller.setEventContainer(eventContainer);
			
			controller.printInConsole("Cr�ation du g�n�rateur d'�v�nements");
			EventGenrator eventGenrator = new EventGenrator(gameTime, eventContainer, actionContainer);
			
			controller.printInConsole("Cr�ation de l'�tudiant");
			Student student = new Student("Etudiant test");
			student.setGameTime(gameTime);
			student.setEventContainer(eventContainer);
			student.setController(controller);
			
			controller.printInConsole("Lancement de la simulation");
			gameTime.startGame();
			eventGenrator.startGenerator();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
