package application;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import modele.Action;
import modele.ActionContainer;
import modele.EventContainer;
import modele.EventGenrator;
import modele.GameTime;
import modele.Event;
import modele.Student;

public class MainSceneController implements Initializable {

    @FXML
    private Label dateLabel;

    @FXML
    private Label hourLabel;

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
    private ImageView activityImageView;

    @FXML
    private TextArea consoleTextArea;

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
    private ListView<String> eventsListView;

    private GameTime gameTime;
    private ActionContainer actionContainer;
    private EventContainer eventContainer;
    private EventGenrator eventGenrator;
    private Student student;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		consoleTextArea.setText("Initialisation");
		
		printInConsole("Création de l'espace temporel");
		gameTime = new GameTime(100);
		refreshDate();
		addEventDateDatePicker.setValue(LocalDateTime.ofInstant(gameTime.getNow().toInstant(), ZoneId.systemDefault()).toLocalDate());
		
		printInConsole("Création du conteneur d'actions");
		actionContainer = new ActionContainer();
		printInConsole("Définition des actions réalisables");
		actionContainer.addAction(new Action("Dormir", true, 0, 0, 0, 0, 1, 1, -1, 5));
		actionContainer.addAction(new Action("Manger", true, 0, 0, 0, 0, 1, 0, 5, 0));
		actionContainer.addAction(new Action("LAN", false, 5, -1, -1, 2, -2, -2, -1, -2));
		actionContainer.addAction(new Action("Cours", false, -2, -1, 5, 1, -2, -2, -1, -2));
		
		for (Action action : actionContainer.getNotAlwaysAvailableActions()) {
			addEventTypeComboBox.getItems().add(action.getName());
		}
		
		printInConsole("Création du conteneur d'événements");
		eventContainer = new EventContainer();
		eventContainer.setActioncontainer(actionContainer);
		
		printInConsole("Création du générateur d'événements");
		eventGenrator = new EventGenrator(gameTime, eventContainer, actionContainer);

		printInConsole("Création de l'étudiant");
		student = new Student("Etudiant test");
		student.setGameTime(gameTime);
		student.setEventContainer(eventContainer);
		student.getStudentState().addObserver(new Observer() {
			public void update(Observable o, Object arg) {
				happinessProgressBar.setProgress(student.calculateHappiness()/100f);
				gamingProgressBar.setProgress(student.getStudentState().getGaming()/100f);
				loveProgressBar.setProgress(student.getStudentState().getLove()/100f);
				schoolProgressBar.setProgress(student.getStudentState().getSchool()/100f);
				socialProgressBar.setProgress(student.getStudentState().getSocial()/100f);
				healthProgressBar.setProgress(student.getStudentState().getHealth()/100f);
				relaxationProgressBar.setProgress(student.getStudentState().getRelaxation()/100f);
				vitalityProgressBar.setProgress(student.getStudentState().getVitality()/100f);
			}
		});

		happinessProgressBar.setProgress(student.calculateHappiness()/100f);
		gamingProgressBar.setProgress(student.getStudentState().getGaming()/100f);
		loveProgressBar.setProgress(student.getStudentState().getLove()/100f);
		schoolProgressBar.setProgress(student.getStudentState().getSchool()/100f);
		socialProgressBar.setProgress(student.getStudentState().getSocial()/100f);
		healthProgressBar.setProgress(student.getStudentState().getHealth()/100f);
		relaxationProgressBar.setProgress(student.getStudentState().getRelaxation()/100f);
		vitalityProgressBar.setProgress(student.getStudentState().getVitality()/100f);
		
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
		
		consoleTextArea.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				consoleTextArea.setScrollTop(Double.MAX_VALUE);
			}
		});
		
		printInConsole("Lancement de la simulation");
		gameTime.startGame();
		eventGenrator.startGenerator();
	}
	
	private void printInConsole(String message) {
		consoleTextArea.appendText("\n"+message);
	}
	
	private void refreshDate() {
		Task<Void> sleeper = new Task<Void>() {
            protected Void call() throws Exception {
                try {
                    Thread.sleep(gameTime.getMinute());
                } catch (InterruptedException e) {}
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            public void handle(WorkerStateEvent event) {
            	refreshDate();
            	dateLabel.setText(String.format("%02d", gameTime.getNow().get(Calendar.DAY_OF_MONTH))+"/"+String.format("%02d", (gameTime.getNow().get(Calendar.MONTH)+1))+"/"+gameTime.getNow().get(Calendar.YEAR));
            	hourLabel.setText(String.format("%02d", gameTime.getNow().get(Calendar.HOUR_OF_DAY))+" : "+String.format("%02d", gameTime.getNow().get(Calendar.MINUTE)));
            	refreshEventList();
            }
        });
        new Thread(sleeper).start();
	}
	
	private void refreshEventList() {
		eventsListView.getItems().clear();
		for (Event event : eventContainer.getEventsNotStarted(gameTime.getNow())) {
			eventsListView.getItems().add(event.toString());
		}
	}

    @FXML
    void addEvent(ActionEvent event) {
    	Action action = actionContainer.getActionByName(addEventTypeComboBox.getValue());
    	Calendar start = new GregorianCalendar(
			addEventDateDatePicker.getValue().getYear(), 
			addEventDateDatePicker.getValue().getMonthValue()-1, 
			addEventDateDatePicker.getValue().getDayOfMonth(), 
			(int)(addEventHourSlider.getValue()/2f), 
			(int)(addEventHourSlider.getValue()%2f*30)
		);
    	Calendar end = (Calendar) start.clone();
    	end.set(Calendar.HOUR_OF_DAY, end.get(Calendar.HOUR_OF_DAY)+(int)(addEventDurationSlider.getValue()/2f));
    	end.set(Calendar.MINUTE, end.get(Calendar.MINUTE)+(int)(addEventDurationSlider.getValue()%2f*30));
    	eventContainer.addEvent(new Event(action, start, end));
    	printInConsole("Evénement ajouté : "+eventContainer.getEvents().get(eventContainer.getEvents().size()-1).toString().replace("\n", ""));
    }
	
}
