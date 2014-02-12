package TimeManager;

/**
 * Contains the description, priority and category of a item
 * @author Aries, Sercan
 *
 */
public class TaskItem {

	public String taskCategory;
	public String taskDescripton;
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

}
