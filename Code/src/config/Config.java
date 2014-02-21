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
		try {
			//specify file to use
			input = new FileInputStream(configFile);
			prop.load(input);
			
			//get local property values from the file
			DBFile 			= prop.getProperty("DBFile",          "assets/DBFile" );
			Images 			= prop.getProperty("Images",          "assets/images/");
			startupLanguage = prop.getProperty("startupLanguage", "English"       );
			
		}
		catch (IOException e){
			e.printStackTrace();
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
		OutputStream output = null;
		try {
			//specify file to use
			output = new FileOutputStream(configFile);
			
			//save local properties to the file
			prop.setProperty("DBFile", DBFile);
			prop.setProperty("startupLanguage", startupLanguage);
			
			//apply changes to file
			prop.store(output, null);
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
		InputStream input = null;
		try {
			//specify file to use
			input = new FileInputStream(configFile);
			
			//get the specified property value corresponding to key and return it
			return prop.getProperty(key, defaultValue);
		}
		catch (IOException e){
			e.printStackTrace();
			return "Error";
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
	}
	
	/**
	 * Saves a property value to config file corresponding to key
	 * @param key
	 * @param value
	 */
	public static void saveProperty(String key,String value) {
		OutputStream output = null;
		try {
			//specify file to use
			output = new FileOutputStream(configFile);
		
			//store the property
			prop.setProperty(key, value);
			
			//apply changes
			prop.store(output, null);
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
