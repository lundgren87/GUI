package TimeManager;

import javax.swing.UIManager;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;


/**
 * @author Sven
 * This class serves to initialize the look and feel of the client
 * It loads the selected team from config.properties, if there is none stored it selects Ocean,
 * and then loads the selected theme with the MetalLookAndFeel (Java Default Theme) theme.
 */
public class lookAndFeel {
	
	public static void initLookAndFeel() {
		//Get Theme name from config file
		String THEME = config.Config.loadProperty("theme", "Ocean");
		
		//Set the desired theme
		try {			
			if(THEME.equals("Ocean")) {
				MetalLookAndFeel.setCurrentTheme(new OceanTheme());
			}
			else if(THEME.equals("Metal")) {
				MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
			}
			else{
				MetalLookAndFeel.setCurrentTheme(new CustomTheme());
			}
			
			//Set the JAVA look and feel
			UIManager.setLookAndFeel(new MetalLookAndFeel());
			
		
		} catch (Exception e) {
			System.out.println("Exception setting Look And Feel or Theme.");
			e.printStackTrace();
		}
	}
}
