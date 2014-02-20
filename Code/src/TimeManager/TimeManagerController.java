package TimeManager;

import java.util.Observer;

public class TimeManagerController {

	private TimeManagerView theView;
	private TimeManagerModel theModel;
	
	public TimeManagerController() {		
		theModel = new TimeManagerModel(this);
		theView = new TimeManagerView(this);
		theModel.addObserver((Observer) theView);
		
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
				
		//theModel.addObserver((Observer) theView);
	}
	
	//add a listener to the view
		//actionperformed
			//do something in the model ->observable
			//set the new value in the view

}