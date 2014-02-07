package TimeManager;

public class TimeManagerMain {

	
	public static void main(String[] args) {
		
		TimeManagerModel model = new TimeManagerModel();
		TimeManagerView view = new TimeManagerView();
		TimeManagerController controller = new TimeManagerController(view, model); 
		
		// dummy data
		model.addNewCategory("all_categories","all_categories");
		model.addNewCategory("house","house");
		model.addNewCategory("school","school");
		model.addNewCategory("work","work");
		model.addNewCategory("add_new", "add_new");
		
		int randomPriority, randomCategory, noOfCategory;
		noOfCategory = model.taskCategories.size()-1;
		for(int i=0;i<20;i++) {
			randomCategory = (int) Math.floor(100 * Math.random()) % (noOfCategory-1)+1;
			randomPriority = (int) Math.floor(10 * Math.random());
			model.addNewTask("Task " + i, model.taskCategories.get(randomCategory).categoryName, randomPriority);
			model.addNewTask("Task " + i, "all_categories", randomPriority);
		}
		
		view.mainFrame.setVisible(true);
	}
	

}
