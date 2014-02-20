package TimeManager;

import javax.swing.ImageIcon;

public class TaskCategory {

	public String categoryName;
	public String categoryDescription;
	public ImageIcon icon;
	public final String iconLocation = "images/";
	public final String iconExtension = ".gif";
	
	/**
	 * Sets the name and description of the category 
	 * @param catName name of the category
	 * @param catDesc desciption of the category
	 */
	public TaskCategory(String catName, String catDesc) {
		categoryName = catName;
		categoryDescription = catDesc;
		try {
			icon = new ImageIcon(this.getClass().getResource(iconLocation + categoryName + iconExtension));
		}
		catch(Exception e) {
			icon = null;
		}
	}
}
