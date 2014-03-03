package TimeManager;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Main controller for the application. Serves as an observer in the observer pattern.
 * @author Sven, Pontus, Sercan, Kim, Aries
 *
 */
public class TimeManagerController implements Observer {

	private TimeManagerView theView;
	private TimeManagerModel theModel;
	
	/**
	 * Creates a new TimeManagerController
	 */
	public TimeManagerController() {		
		// The controller observes both view and model
		theView = new TimeManagerView();
		theView.addObserver(this);
		
		theModel = new TimeManagerModel();
		theModel.addObserver(this);
		
		theModel.initDatabase();
		
		// dummy data
		/*
		theModel.addNewCategory("all_categories","all_categories");
		theModel.addNewCategory("house","house");
		theModel.addNewCategory("school","school");
		theModel.addNewCategory("work","work");
		theModel.addNewCategory("add_new", "add_new");
		
		int randomPriority, randomCategory, noOfCategory;
		noOfCategory = theModel.taskCategories.size()-1;
		for(int i=0;i<20;i++) {
			randomCategory = (int) Math.floor(100 * Math.random()) % (noOfCategory-1)+1;
			randomPriority = (int) Math.floor(10 * Math.random() % 3 + 1);
			theModel.addNewTask("Task " + i, theModel.taskCategories.get(randomCategory).categoryName, randomPriority);
			//theModel.addNewTask("Task " + i, "all_categories", randomPriority);
		}
		*/
		
		theView.mainFrame.setVisible(true);
	}

	@Override
	/**
	 * Implementation of the update method from the Observer base class.
	 * Handles events from both model and view.
	 */
	public void update(Observable o, Object arg) {
		// TODO: should check to a more generic view object if there are multiple view
		if(o instanceof TimeManagerView) {
			TimeManagerView view = (TimeManagerView) o;
			if(view.isShuttingDown) {
				theModel.closeOperation();
				view.closeOperation();
				config.Config.saveConfig();
			}
			else if(arg instanceof TaskItem) {
				TaskItem item = (TaskItem) arg;
				theModel.addNewTask(item);
			}
		}
		else if(o instanceof TimeManagerModel) {
			// Model passes either list of categories or list of tasks
			List list = (List) arg;
			if(list.get(0) instanceof TaskCategory) {
				theView.loadCategories(list);
			}
			else {
				theView.loadTasks(list);
			}
		}
	}

}