package TimeManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class TimeManagerModel extends Observable {
	
	public List<TaskItem> taskItems;
	public List<TaskCategory> taskCategories;
	
	public TimeManagerModel() {
		taskItems = new ArrayList<TaskItem>();
		taskCategories = new ArrayList<TaskCategory>();
	}
	
	public void addNewTask(String taskDesc, String taskCat, int taskPrio) {
		taskItems.add(new TaskItem(taskDesc, taskCat, taskPrio));
		setChanged();
		notifyObservers(taskItems);
	}
	
	public void addNewCategory(String categoryName, String categoryDescription) {
		taskCategories.add(new TaskCategory(categoryName, categoryDescription));
		setChanged();
		notifyObservers(taskCategories);
	}
		
}
