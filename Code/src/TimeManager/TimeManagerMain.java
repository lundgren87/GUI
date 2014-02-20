package TimeManager;

import javax.swing.JFrame;

public class TimeManagerMain {

	
	public static void main(String[] args) {
		config.Config.loadConfig();
		TimeManagerController controller = new TimeManagerController(); 

	}
	

}
