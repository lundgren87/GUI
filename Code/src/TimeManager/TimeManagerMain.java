package TimeManager;

public class TimeManagerMain {

	
	public static void main(String[] args) {
		
		TimeManagerModel model = new TimeManagerModel();
		TimeManagerView view = new TimeManagerView();
		TimeManagerController controller = new TimeManagerController(view, model); 
		
		// dummy data
		model.addNewCategory("house","Show house related tasks");
		model.addNewCategory("school","Show school related tasks");
		model.addNewCategory("work","Show work related tasks");
		
		int randomPriority, randomCategory, noOfCategory;
		noOfCategory = model.taskCategories.size();
		for(int i=0;i<50;i++) {
			randomCategory = (int) Math.floor(100 * Math.random()) % noOfCategory;
			randomPriority = (int) Math.floor(10 * Math.random());
			model.addNewTask("Task " + i, model.taskCategories.get(randomCategory).categoryName, randomPriority);
		}
		
		view.mainFrame.setVisible(true);
	}
	

}
