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
	public static String DBFile;
	public static String Images;
	public static String startupLanguage;
	
	/**
	 * Loads general properties from config file.
	 */
	public static void loadConfig() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("config.properties");
			
			DBFile 			= prop.getProperty("DBFile",          "assets/DBFile" );
			Images 			= prop.getProperty("Images",          "/images/"      );
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
	}
	

	/**
	 * Saves general properties to config file.
	 */
	public static void saveConfig() {
		Properties prop = new Properties();
		OutputStream output = null;
		try {
			output = new FileOutputStream("config.properties");
			
			prop.setProperty("DBFile",DBFile);
			prop.setProperty("startupLanguage",startupLanguage);
			
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
	 * Loads a the value of the property key from config file.
	 * @param key
	 * @param defaultValue
	 * @return Value of key from config file
	 */
	public static String loadProperty(String key,String defaultValue) {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("config.properties");
			
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
	 * Saves a property value to config file.
	 * @param key
	 * @param value
	 */
	public static void saveProperty(String key,String value) {
		Properties prop = new Properties();
		OutputStream output = null;
		try {
			output = new FileOutputStream("config.properties");
			
			prop.setProperty(key, value);
			
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
}
