package TimeManager;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class TimeManagerController implements Observer {

	private TimeManagerView theView;
	private TimeManagerModel theModel;
	
	public TimeManagerController() {		
		theView = new TimeManagerView();
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
	public void update(Observable o, Object arg) {
		List list = (List) arg;
		if(list.get(0) instanceof TaskCategory) {
			theView.loadCategories(list);
		}
		else {
			theView.loadTasks(list);
		}
	}
	
	//add a listener to the view
		//actionperformed
			//do something in the model ->observable
			//set the new value in the view

}