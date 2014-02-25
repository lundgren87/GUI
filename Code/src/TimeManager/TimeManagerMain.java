package TimeManager;

import javax.swing.JFrame;

/**
 * Main class of the application
 * @author Sven, Kim, Sercan, Pontus, Aries
 *
 */
public class TimeManagerMain {

	/**
	 * Main function of the application
	 * @param args
	 */
	public static void main(String[] args) {
		config.Config.loadConfig();
		TimeManagerController controller = new TimeManagerController(); 

	}
	

}
