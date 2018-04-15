package modele;

import java.util.ArrayList;

public class ActionContainer {
	private ArrayList<Action> actions;
	
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
		ArrayList<Action> alwaysAvailableActions = new ArrayList<Action>();
		for (Action action : actions) {
			if (!action.isAlwaysAvailable())
				alwaysAvailableActions.add(action);
		}
		return alwaysAvailableActions;
	}
	
	public void addAction(Action action) {
		actions.add(action);
	}
	
	public void removeAction(int index) {
		actions.remove(index);
	}
}
