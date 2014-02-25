package TimeManager;

/**
 * Class to represent a Task Item
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
	 * [TOBE DEPRECATED] Creates a new Task Item with Task Description, Task Category, and Task Priority
	 * @param description Description of the new task
	 * @param category Category of the new task
	 * @param priority Priority of the new task 
	 */
	public TaskItem(String description, String category, int priority) {
		taskDescripton = description;
		taskPriority = priority;
		taskCategory = category;
	}
	/**
	 * Creates a new Task Item with Task Description, Task Category, Due Date, Progress, and Task Priority
	 * @param description Description of the new task
	 * @param category Category of the new task
	 * @param dueDate Due date of the new task
	 * @param progress Current progress of new task
	 * @param priority Priority of the new task 
	 */
	public TaskItem(String description, String category, String dueDate, int progress, int priority) {
		taskDescripton = description;
		taskPriority = priority;
		taskCategory = category;
		taskDueDate = dueDate;
		taskProgress = progress;
	}

}
