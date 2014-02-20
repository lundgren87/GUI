package TimeManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Model contains all the information about taskitems and taskcategory
 * @author Aries, Sercan
 *
 */
public class TimeManagerModel extends Observable {
	
	public List<TaskItem> taskItems;			//List of task's
	public List<TaskCategory> taskCategories;	//list of catogories
	
	/**
	 * Makes a new list of taskItems and Categories
	 */
	public TimeManagerModel() {

	}
	
	
	public TimeManagerModel(TimeManagerController controller) {
		taskItems = new ArrayList<TaskItem>();
		taskCategories = new ArrayList<TaskCategory>();
	}
	
	/**
	 * Add new task to the taskItems list
	 * @param taskDesc String taskDescription
	 * @param taskCat String task category
	 * @param taskPrio int task priotiry
	 */
	public void addNewTask(String taskDesc, String taskCat, int taskPrio) {
		taskItems.add(new TaskItem(taskDesc, taskCat, taskPrio));
		setChanged();
		notifyObservers(taskItems);
	}
	
	/**
	 * Add new category to category list
	 * @param categoryName Sting Name of category
	 * @param categoryDescription String of category description
	 */
	public void addNewCategory(String categoryName, String categoryDescription) {
		taskCategories.add(new TaskCategory(categoryName, categoryDescription));
		setChanged();
		notifyObservers(taskCategories);
	}
		
}
