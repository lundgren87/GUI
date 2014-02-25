package config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Configuration file handler.
 * 
 * @author Pontus, Sven
 *
 */
public class Config {
	static String configFile = "config.properties";
	static Properties prop;
	
	public static String DBFile;
	public static String Images;
	public static String startupLanguage;
	
	
	/**
	 * Loads general properties from config file.
	 */
	public static void loadConfig() {
		prop = new Properties();
		InputStream input = null;
		
		//set default values
		DBFile = "assets/DBFile";
		Images = "assets/images/";
		startupLanguage = "English";
		
		try {
			//specify file to use and load its content to prop
			input = new FileInputStream(configFile);
			prop.load(input);
			
			//get local property values from the file
			DBFile 			= prop.getProperty("DBFile", DBFile );
			Images 			= prop.getProperty("Images", Images);
			startupLanguage = prop.getProperty("startupLanguage", startupLanguage);
			
		}
		catch (IOException e){
			//File does not exist (or other error). Set default values to prop
			System.out.println("Cannot access config.properties, it might not exist. Loading default values");
			prop.setProperty("DBFile", DBFile );
			prop.setProperty("Images", Images);
			prop.setProperty("startupLanguage", startupLanguage);
		}
		finally {
			if (input != null) {
				try {
					input.close();
				}
				catch (IOException e2){
					e2.printStackTrace();
				}
			} 
		}
		System.out.println(DBFile + Images + startupLanguage);
	}
	

	/**
	 * Saves general properties to config file.
	 */
	public static void saveConfig() {
		System.out.print("Saving config file...");
		OutputStream output = null;
		try {
			//specify file to use
			output = new FileOutputStream(configFile);
			
			//save local properties to prop
			prop.setProperty("DBFile", DBFile);
			prop.setProperty("startupLanguage", startupLanguage);
			
			//store contents of prop to file
			prop.store(output, null);
			System.out.println("done");
		}
		catch (IOException e){
			e.printStackTrace();
		}
		finally {
			if (output != null) {
				try {
					output.close();
				}
				catch (IOException e2){
					e2.printStackTrace();
				}
			} 
		}
	}
	
	/**
	 * Loads a the value of the property key from config file corresponding to key
	 * @param key
	 * @param defaultValue
	 * @return Value of key from config file or defaultValue if the key is not present
	 */
	public static String loadProperty(String key,String defaultValue) {
		//get the specified property value corresponding to key and return it
		return prop.getProperty(key, defaultValue);
	}
	
	/**
	 * Saves a property value to config file corresponding to key
	 * @param key
	 * @param value
	 */
	public static void saveProperty(String key,String value) {
		prop.setProperty(key, value);
	}
	
	/**
	 * Load an integer valued property corresponding to key
	 * @param key
	 * @param defaultValue
	 * @return Value of key from config file or defaultValue if the key is not present
	 */
	public static int loadInt(String key,int defaultValue) {
		return Integer.parseInt(loadProperty(key,Integer.toString(defaultValue)));
	}
	
	/**
	 * Save an integer valued property corresponding to key
	 * @param key
	 * @param value
	 */
	public static void saveInt(String key,int value) {
		saveProperty(key,Integer.toString(value));
	}
}
