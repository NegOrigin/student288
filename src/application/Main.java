package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import modele.Student;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			/*BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();*/
			Student student = new Student("Je suis un etudiant test");
			System.out.println("Nom : "+student.getName());
			System.out.println("Type : "+student.getType().getName());
			System.out.println("Profil :");
			System.out.print("Gaming : "+student.getProfile().getGaming()+", ");
			System.out.print("Love : "+student.getProfile().getLove()+", ");
			System.out.print("School : "+student.getProfile().getSchool()+", ");
			System.out.println("Social : "+student.getProfile().getSocial()+", ");
			System.out.print("Confidence : "+student.getProfile().getConfidence()+", ");
			System.out.print("Health : "+student.getProfile().getHealth()+", ");
			System.out.println("Shape : "+student.getProfile().getShape());
			System.out.println("Etat :");
			System.out.print("Gaming : "+student.getState().getGaming()+", ");
			System.out.print("Love : "+student.getState().getLove()+", ");
			System.out.print("School : "+student.getState().getSchool()+", ");
			System.out.println("Social : "+student.getState().getSocial()+", ");
			System.out.print("Confidence : "+student.getState().getConfidence()+", ");
			System.out.print("Health : "+student.getState().getHealth()+", ");
			System.out.println("Shape : "+student.getState().getShape());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
