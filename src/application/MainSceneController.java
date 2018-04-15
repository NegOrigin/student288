package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
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
import modele.Action;
import modele.EventContainer;
import modele.Event;
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
    private ComboBox<String> addEventTypeComboBox;

    @FXML
    private DatePicker addEventDateDatePicker;

    @FXML
    private Slider addEventHourSlider;

    @FXML
    private Label addEventHourLabel;

    @FXML
    private Slider addEventDurationSlider;

    @FXML
    private Label addEventDurationLabel;

    @FXML
    private Button addEventButton;

    @FXML
    private TextArea consoleTextArea;
    
    private Student student;
    private EventContainer eventContainer;
    private ArrayList<Action> actions;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		consoleTextArea.setText("Initialisation");

		printInConsole("Création de l'étudiant");
		student = new Student("Etudiant test");
		student.getState().addObserver(new Observer() {
			public void update(Observable o, Object arg) {
				printInConsole("Mise à jour de l'affichage de l'état de l'étudiant");
				happinessProgressBar.setProgress(student.calculateHappiness()/100f);
				gamingProgressBar.setProgress(student.getState().getGaming()/100f);
				loveProgressBar.setProgress(student.getState().getLove()/100f);
				schoolProgressBar.setProgress(student.getState().getSchool()/100f);
				socialProgressBar.setProgress(student.getState().getSocial()/100f);
				healthProgressBar.setProgress(student.getState().getHealth()/100f);
				relaxationProgressBar.setProgress(student.getState().getRelaxation()/100f);
				vitalityProgressBar.setProgress(student.getState().getVitality()/100f);
			}
		});

		printInConsole("Mise à jour de l'affichage de l'état de l'étudiant");
		happinessProgressBar.setProgress(student.calculateHappiness()/100f);
		gamingProgressBar.setProgress(student.getState().getGaming()/100f);
		loveProgressBar.setProgress(student.getState().getLove()/100f);
		schoolProgressBar.setProgress(student.getState().getSchool()/100f);
		socialProgressBar.setProgress(student.getState().getSocial()/100f);
		healthProgressBar.setProgress(student.getState().getHealth()/100f);
		relaxationProgressBar.setProgress(student.getState().getRelaxation()/100f);
		vitalityProgressBar.setProgress(student.getState().getVitality()/100f);
		
		printInConsole("Création du calendrier");
		eventContainer = new EventContainer();
		
		printInConsole("Définition des actions réalisables");
		actions = new ArrayList<Action>();
		actions.add(new Action("Dormir", true, 0, 0, 0, 0, 1, 1, -1, 5));
		actions.add(new Action("Manger", true, 0, 0, 0, 0, 1, 0, 5, 0));
		actions.add(new Action("LAN", false, 5, -1, -1, 2, -2, -2, -1, -2));
		actions.add(new Action("Cours", false, -2, -1, 5, 1, -2, -2, -1, -2));
		
		for (Action action : actions) {
			if (!action.isAlwaysAvailable())
				addEventTypeComboBox.getItems().add(action.getName());
		}
		
		addEventHourSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				addEventHourLabel.setText(String.format("%02d", (int)(addEventHourSlider.getValue()/2f))+"h"+String.format("%02d", (int)(addEventHourSlider.getValue()%2f*30)));
			}
		});
		
		addEventHourLabel.setText(String.format("%02d", (int)(addEventHourSlider.getValue()/2f))+"h"+String.format("%02d", (int)(addEventHourSlider.getValue()%2f*30)));
		
		addEventDurationSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				addEventDurationLabel.setText(String.format("%02d", (int)(addEventDurationSlider.getValue()/2f))+"h"+String.format("%02d", (int)(addEventDurationSlider.getValue()%2f*30)));
			}
		});
		
		addEventDurationLabel.setText(String.format("%02d", (int)(addEventDurationSlider.getValue()/2f))+"h"+String.format("%02d", (int)(addEventDurationSlider.getValue()%2f*30)));
	}
	
	private void printInConsole(String message) {
		consoleTextArea.setText(consoleTextArea.getText()+"\n"+message);
	}

    @FXML
    void addEvent(ActionEvent event) {
    	Calendar start = new GregorianCalendar(
			addEventDateDatePicker.getValue().getYear(), 
			addEventDateDatePicker.getValue().getMonthValue()-1, 
			addEventDateDatePicker.getValue().getDayOfMonth(), 
			(int)(addEventHourSlider.getValue()/2f), 
			(int)(addEventHourSlider.getValue()%2f*30)
		);
    	Calendar end = new GregorianCalendar(
			start.get(Calendar.YEAR), 
			start.get(Calendar.MONTH), 
			start.get(Calendar.DAY_OF_MONTH), 
			start.get(Calendar.HOUR_OF_DAY)+(int)(addEventDurationSlider.getValue()/2f), 
			start.get(Calendar.MINUTE)+(int)(addEventDurationSlider.getValue()%2f*30)
		);
    	for (Action action : actions) {
			if (action.getName() == addEventTypeComboBox.getValue())
				eventContainer.addEvent(new Event(action, start, end));
		}
    	printInConsole("Evénement ajouté");
    }
	
}
