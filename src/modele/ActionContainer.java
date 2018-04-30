package modele;

import java.util.ArrayList;

import application.MainSceneController;
import javafx.application.Platform;

public class ActionContainer {
	private ArrayList<Action> actions;
	
	private MainSceneController controller = null;
	
	public ActionContainer() {
		setActions(new ArrayList<Action>());
	}

	public ArrayList<Action> getActions() {
		return actions;
	}

	private void setActions(ArrayList<Action> actions) {
		this.actions = actions;
	}
	
	public Action getAction(int index) {
		return actions.get(index);
	}
	
	public Action getActionByName(String name) {
		for (Action action : actions) {
			if (action.getName() == name)
				return action;
		}
		return null;
	}
	
	public ArrayList<Action> getAlwaysAvailableActions() {
		ArrayList<Action> alwaysAvailableActions = new ArrayList<Action>();
		for (Action action : actions) {
			if (action.isAlwaysAvailable())
				alwaysAvailableActions.add(action);
		}
		return alwaysAvailableActions;
	}
	
	public ArrayList<Action> getNotAlwaysAvailableActions() {
		ArrayList<Action> notAlwaysAvailableActions = new ArrayList<Action>();
		for (Action action : actions) {
			if (!action.isAlwaysAvailable())
				notAlwaysAvailableActions.add(action);
		}
		return notAlwaysAvailableActions;
	}
	
	public void addAction(Action action) {
		actions.add(action);
		updateUI(getNotAlwaysAvailableActions());
	}
	
	public void removeAction(int index) {
		actions.remove(index);
		updateUI(getNotAlwaysAvailableActions());
	}

	public void setController(MainSceneController controller) {
		this.controller = controller;
		updateUI(getNotAlwaysAvailableActions());
	}
	
	private void updateUI(ArrayList<Action> notAlwaysAvailableActions) {
		if (controller != null)
			Platform.runLater(new Runnable() {
			    public void run() {
					controller.initializeAddEventTypeComboBox(notAlwaysAvailableActions);
			    }
			});
	}
}
