package TimeManager;

import javax.swing.ImageIcon;

/**
 * 
 * @author Aries
 * Class to represent a Task Category
 */
public class TaskCategory {

	public String categoryName;
	public String categoryDescription;
	public ImageIcon icon;
	public final String iconLocation = config.Config.loadProperty("Images", "assets/images/");
	public final String iconExtension = ".gif";
	
	/**
	 * Create a new Task Category with the name and description of the category 
	 * @param catName name of the category
	 * @param catDesc description of the category
	 */
	public TaskCategory(String catName, String catDesc) {
		categoryName = catName;
		categoryDescription = catDesc;
		try {
			icon = new ImageIcon(iconLocation + categoryName + iconExtension);
		}
		catch(Exception e) {
			icon = null;
		}
	}
}
