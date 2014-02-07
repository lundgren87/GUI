package TimeManager;

import java.util.Observer;

/**
 * The Controller comunicates between the view and model
 * @author Kim
 *
 */
public class TimeManagerController {

	private TimeManagerView theView;
	private TimeManagerModel theModel;
	
	/**
	 * 
	 * @param view, the view of the TimeManager
	 * @param model, the model of the TimaManager
	 */
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
