package TimeManager;

/**
 * Contains the description, priority and category of a item
 * @author Aries, Sercan
 *
 */
public class TaskItem {

	public String taskCategory;
	public String taskDescripton;
	public String taskDueDate;
	public int taskProgress;
	public int taskPriority;
	
	/**
	 * Sets the information of a task
	 * @param description
	 * @param category
	 * @param priority
	 */
	public TaskItem(String description, String category, int priority) {
		taskDescripton = description;
		taskPriority = priority;
		taskCategory = category;
	}
	public TaskItem(String description, String category, String dueDate, int progress, int priority) {
		taskDescripton = description;
		taskPriority = priority;
		taskCategory = category;
		taskDueDate = dueDate;
		taskProgress = progress;
	}

}
