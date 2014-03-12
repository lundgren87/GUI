package TimeManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import dataBase.DBHandler;

/**
 * Class as a Model in the MVC.
 * It contains data structure (a list) of Task Items and Task Category
 * @author Aries, Sercan
 *
 */
public class TimeManagerModel extends Observable {
	
	public List<TaskItem> taskItems;			//List of task's
	public List<TaskCategory> taskCategories;	//list of catogories
	DBHandler dataBase;
	
	/**
	 * Creates a new, blank model
	 */
	public TimeManagerModel() {
		taskItems = new ArrayList<TaskItem>();
		taskCategories = new ArrayList<TaskCategory>();
	}
	
	/**
	 * Initialize database handler to access database
	 */
	public void initDatabase() {
		dataBase = new DBHandler(this);
		dataBase.init();
		//dataBase.readCategories();
		//dataBase.readTasks();
	}
	
	
	/**
	 * Add the Task Item task to the list and notify observers
	 * @param item TaskItem object containing all the task information
	 */
	public void addNewTask(TaskItem item) {
		taskItems.add(item);
		setChanged();
		notifyObservers(taskItems);
	}
	
	/**
	 * [TOBE DEPRECATED] Creates a new Task Item with Task description, Task category, and TaskPriority
	 * Add it to the list then notify observers.
	 * @param taskDesc String taskDescription
	 * @param taskCat String task category
	 * @param taskPrio int task priotiry
	 */
	public void addNewTask(String taskDesc, String taskCat, int taskPrio) {
		System.out.println("Added new task");
		taskItems.add(new TaskItem(taskDesc, taskCat, taskPrio));
		setChanged();
		notifyObservers(taskItems);
	}
	
	/**
	 * Creates a new Task Item with Task description, Task category, Task Due Date, Task Progress, and Task Priority
	 * Add the task to to the list then notify observers.
	 * @param taskDesc Description of the task
	 * @param taskCat Category that this task belongs to.
	 * @param dueDate Due date of the task
	 * @param progress Progress of the task
	 * @param taskPrio Priority of the task
	 */
	public void addNewTask(String taskDesc, String taskCat, String dueDate, int progress, int taskPrio) {
		System.out.println("Added new task2");
		taskItems.add(new TaskItem(taskDesc, taskCat, dueDate, progress, taskPrio));
		setChanged();
		notifyObservers(taskItems);
	}
	/**
	 * Add new category to category list
	 * @param categoryName Name of category. Must match its icon name.
	 * @param categoryDescription Description of category
	 */
	public void addNewCategory(String categoryName, String categoryDescription) {
		System.out.println("Added new category");
		taskCategories.add(new TaskCategory(categoryName, categoryDescription));
		setChanged();
		notifyObservers(taskCategories);
	}
	
	/**
	 * Getter for list of tasks
	 */
	public List<TaskItem> getTasks() {
		return taskItems;
	}
	
	/**
	 * Getter list of categories
	 */
	public List<TaskCategory> getCategories() {
		return taskCategories;
	}
	
	/**
	 * Close methods that is executed when the application exits
	 */
	public void closeOperation() {
		dataBase.exit();
	}

	public void save() {
		dataBase.exit();
	}

	public void load() {
		taskItems = new ArrayList<TaskItem>();
		taskCategories = new ArrayList<TaskCategory>();
		dataBase.init();
	}
	
		
}
