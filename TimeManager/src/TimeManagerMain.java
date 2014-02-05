
public class TimeManagerMain {
	
	
	// this is aries 
	public static void main(String[] args) {
		TimeManagerView view = new TimeManagerView();
		TimeManagerModel model = new TimeManagerModel();
		TimeManagerController controller = new TimeManagerController(view, model); 

	}

}
