package application;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modele.Action;
import modele.ActionContainer;
import modele.EventContainer;
import modele.Event;

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
    private ProgressBar satietyProgressBar;

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
    private ListView<String> eventListView;

    private ActionContainer actionContainer = null;
    private EventContainer eventContainer = null;
    
    public void setActionContainer(ActionContainer actionContainer) {
		this.actionContainer = actionContainer;
	}
    
    public void setEventContainer(EventContainer eventContainer) {
		this.eventContainer = eventContainer;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		consoleTextArea.setText("Initialisation");
		
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
	}
	
	public void printInConsole(String message) {
		consoleTextArea.appendText("\n"+message);
	}

    @FXML
    void addEvent(ActionEvent event) {
    	if (actionContainer != null && eventContainer != null && addEventTypeComboBox.getValue() != null && addEventDateDatePicker.getValue() != null) {
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
	    	Event newEvent = new Event(action, start, end);
	    	eventContainer.addEvent(newEvent);
	    	printInConsole("Evénement ajouté : "+newEvent.toString().replace("\n", ""));
    	}
    }
    
    public void initializeAddEventDateDatePicker(Calendar now) {
    	addEventDateDatePicker.setValue(LocalDateTime.ofInstant(now.toInstant(), ZoneId.systemDefault()).toLocalDate());
    }
	
	public void refreshDate(Calendar now) {
		dateLabel.setText(String.format("%02d", now.get(Calendar.DAY_OF_MONTH))+"/"+String.format("%02d", (now.get(Calendar.MONTH)+1))+"/"+now.get(Calendar.YEAR));
    	hourLabel.setText(String.format("%02d", now.get(Calendar.HOUR_OF_DAY))+" : "+String.format("%02d", now.get(Calendar.MINUTE)));
	}
	
	public void initializeAddEventTypeComboBox(ArrayList<Action> actions) {
		addEventTypeComboBox.getItems().clear();
		for (Action action : actions) {
			addEventTypeComboBox.getItems().add(action.getName());
		}
	}
	
	public void refreshEventList(ArrayList<Event> events) {
		eventListView.getItems().clear();
		for (Event event : events) {
			eventListView.getItems().add(event.toString());
		}
	}
	
	public void refreshStudentState(int happiness, float gaming, float love, float school, float social, float health, float relaxation, float satiety, float vitality) {
		happinessProgressBar.setProgress(happiness/100f);
		gamingProgressBar.setProgress(gaming/100f);
		loveProgressBar.setProgress(love/100f);
		schoolProgressBar.setProgress(school/100f);
		socialProgressBar.setProgress(social/100f);
		healthProgressBar.setProgress(health/100f);
		relaxationProgressBar.setProgress(relaxation/100f);
		satietyProgressBar.setProgress(satiety/100f);
		vitalityProgressBar.setProgress(vitality/100f);
	}
	
	public void refreshStudentActivityImage(String image) {
		if (Main.class.getResourceAsStream(image) != null)
			activityImageView.setImage(new Image(Main.class.getResourceAsStream(image)));
	}
	
}
