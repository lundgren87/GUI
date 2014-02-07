package TimeManager;

import java.util.Observer;

public class TimeManagerController {

	private TimeManagerView theView;
	private TimeManagerModel theModel;
	
	public TimeManagerController(TimeManagerView view, TimeManagerModel model) {
		this.theView = view;    //Sets the view
		this.theModel = model;  //Sets the model		
		theModel.addObserver((Observer) theView);
	}
	
	//add a listener to the view
		//actionperformed
			//do something in the model
			//set the new value in the view

}
