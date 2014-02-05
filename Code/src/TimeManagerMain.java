
public class TimeManagerMain {

	//This is Kim, HELOO!
	public static void main(String[] args) {
		TimeManagerView view = new TimeManagerView();
		TimeManagerModel model = new TimeManagerModel();
		TimeManagerController controller = new TimeManagerController(view, model); 

	}

}
