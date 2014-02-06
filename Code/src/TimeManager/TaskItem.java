package TimeManager;

public class TaskItem {

	public String taskCategory;
	public String taskDescripton;
	public int taskPriority;
	
	public TaskItem(String description, String category, int priority) {
		taskDescripton = description;
		taskPriority = priority;
		taskCategory = category;
	}

}
