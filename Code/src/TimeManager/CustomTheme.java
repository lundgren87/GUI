package TimeManager;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;

/**
 * @author Sven
 * 
 * This class imports colors from a properties file to create a custom theme
 * Themes should be stored as NAMEtheme.properties, where name is the name of the theme
 * Example: Redtheme.properties, Milkytheme.properties, Darktheme.properties
 */
public class CustomTheme extends DefaultMetalTheme {

	//File path and name containing the theme
	String themeFile;
	
	//Custom theme name
	private String themeName = "Custom Theme";

	//Theme colors
    private ColorUIResource primary1;
    private ColorUIResource primary2;
    private ColorUIResource primary3;
    private ColorUIResource secondary1;
    private ColorUIResource secondary2;
    private ColorUIResource secondary3;
    private ColorUIResource black;
    private ColorUIResource white;
    
    
    /**
     * CustomTheme() is the constructor for a custom theme.
     * The custom theme is stored as specified above.
     * Default colors for the MetalLookAndFeel are first loaded,
     * and later overwritten by the custom theme's own colors.
     */
    public CustomTheme(){
    	loadDefaultColors();
    	loadProperties();
    }
	
    
    /**
     * Method that loads the default colors of MetalLookAndFeel.
     * Used to handle the case where the custom theme file is corrupt or does not exist.
     */
    private void loadDefaultColors()
    {
    	primary1 		= super.getPrimary1();
        primary2 		= super.getPrimary2();
        primary3 		= super.getPrimary3();

        secondary1 		= super.getSecondary1();
        secondary2 		= super.getSecondary2();
        secondary3 		= super.getSecondary3();

		black = super.getBlack();
		white = super.getWhite();
    }
    
  
	/**
	 * Method that loads the properties of a color Theme specified in config.properties.
	 * It calls parseColor(String color) for the properties that reflect RGB colors.
	 */
	private void loadProperties(){
		Properties prop = new Properties();
		InputStream input = null;
		
		try{
			input = new FileInputStream(config.Config.loadProperty("theme", "Red") + "theme.thm");
			prop.load(input);
		}catch(IOException e){
			System.out.println("Cannot access customtheme.properties, make sure file exists.");
		}
		

		//Get theme name
    	String temp = prop.getProperty("name");
    	if(null != temp)
    	{
    		themeName = temp;
    	}
    	
    	//Get and parse primary colors	
    	temp = prop.getProperty("primary1");
    	if(null != temp)
    	{
    		primary1 = parseColor(temp);
    	}
    	temp = prop.getProperty("primary2");
    	if(null != temp)
    	{
    		primary2 = parseColor(temp);
    	}
    	temp = prop.getProperty("primary3");
    	if(null != temp)
    	{
    		primary3 = parseColor(temp);
    	}
    	
    	//Get and parse secondary colors
    	temp = prop.getProperty("secondary1");
    	if(null != temp)
    	{
    		secondary1 = parseColor(temp);
    	}
    	temp = prop.getProperty("secondary2");
    	if(null != temp)
    	{
    		secondary2 = parseColor(temp);
    	}
    	temp = prop.getProperty("secondary3");
    	if(null != temp)
    	{
    		secondary3 = parseColor(temp);
    	}
    	
    	//Get and parse black & white
    	temp = prop.getProperty("black");
    	if(null != temp)
    	{
    		black = parseColor(temp);
    	}
    	temp = prop.getProperty("white");
    	if(null != temp)
    	{
    		white = parseColor(temp);
    	}
	}
	
	
	
	/**
	 * @param color
	 * @return ColorUIResource
	 * Parses a String containing a decimal RGB color representation of RED, GREEN, BLUE
	 * The string "255,0,0" would return a ColorUIResource of the color red.
	 */
	private ColorUIResource parseColor(String color)
    {
    	int red, green, blue;
    	red = green = blue = 0;
    	
    	try{
    			//Step through the string with "," separating tokens
    			StringTokenizer stok = new StringTokenizer(color, ",");
    			
    			//Parse token String to Int
    			red = Integer.parseInt(stok.nextToken());
    			green = Integer.parseInt(stok.nextToken());
    			blue = Integer.parseInt(stok.nextToken());
    			
    	}catch(Exception e)
    	{
    		System.out.println(e);
	    	System.out.println("Couldn't parse color :" + color);
    	}
    	
    	return new ColorUIResource(red, green, blue);
    }
	
	
	
	//Functions overriden from DefaultMetalTheme
	public String getName() { return themeName; }
	
    protected ColorUIResource getPrimary1() { return primary1; }
    protected ColorUIResource getPrimary2() { return primary2; }
    protected ColorUIResource getPrimary3() { return primary3; }
    
    protected ColorUIResource getSecondary1() { return secondary1; }
    protected ColorUIResource getSecondary2() { return secondary2; }
    protected ColorUIResource getSecondary3() { return secondary3; }
    
    protected ColorUIResource getBlack() { return black; }
    protected ColorUIResource getWhite() { return white; }
}
