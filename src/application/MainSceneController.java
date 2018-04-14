package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import modele.Student;

public class MainSceneController implements Initializable {

    @FXML
    private ImageView activityImageView;

    @FXML
    private ProgressBar happinessProgressBar;

    @FXML
    private ProgressBar gamingProgressBar;

    @FXML
    private ProgressBar loveProgressBar;

    @FXML
    private ProgressBar schoolProgressBar;

    @FXML
    private ProgressBar socialProgressBar;

    @FXML
    private ProgressBar healthProgressBar;

    @FXML
    private ProgressBar relaxationProgressBar;

    @FXML
    private ProgressBar vitalityProgressBar;

    @FXML
    private ComboBox<?> addEventTypeComboBox;

    @FXML
    private DatePicker addEventDateDatePicker;

    @FXML
    private Slider addEventHourSlider;

    @FXML
    private Label addEventHourLabel;

    @FXML
    private Button addEventButton;

    @FXML
    private TextArea consoleTextArea;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		consoleTextArea.setText("Initialisation");

		printInConsole("Création de l'étudiant");
		Student student = new Student("Etudiant test");

		printInConsole("Mise à jour de l'affichage de l'état de l'étudiant");
		happinessProgressBar.setProgress(student.calculateHappiness()/100f);
		gamingProgressBar.setProgress(student.getState().getGaming()/100f);
		loveProgressBar.setProgress(student.getState().getLove()/100f);
		schoolProgressBar.setProgress(student.getState().getSchool()/100f);
		socialProgressBar.setProgress(student.getState().getSocial()/100f);
		healthProgressBar.setProgress(student.getState().getHealth()/100f);
		relaxationProgressBar.setProgress(student.getState().getRelaxation()/100f);
		vitalityProgressBar.setProgress(student.getState().getVitality()/100f);
		
		addEventHourSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				addEventHourLabel.setText(String.format("%02d", (int)(addEventHourSlider.getValue()/2f))+"h"+String.format("%02d", (int)(addEventHourSlider.getValue()%2f*30)));
			}
		});
		addEventHourLabel.setText(String.format("%02d", (int)(addEventHourSlider.getValue()/2f))+"h"+String.format("%02d", (int)(addEventHourSlider.getValue()%2f*30)));
	}
	
	private void printInConsole(String message) {
		consoleTextArea.setText(consoleTextArea.getText()+"\n"+message);
	}
	
}
