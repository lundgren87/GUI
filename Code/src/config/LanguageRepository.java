package config;

import java.util.HashMap;


/**
 * @author Sven, Pontus
 *
 * Language repository class
 */
public class LanguageRepository {
	static String currentLanguage = Config.startupLanguage;
	public static String[] availableLanguages = {"English", "Swedish"};
	static HashMap<String,String> languageMap = new HashMap<String,String>();
	static Boolean isInitialized = false;

	
	/**
	 * @param newLanguage
	 * If newLanguage is different from currentLanguage,
	 * sets newLanguage to currentLanguage and runs init
	 */
	static void setCurrentLanguage(String newLanguage) {
		if(currentLanguage != newLanguage) {
			currentLanguage = newLanguage;
			init();
		}
	}
	
	
	/**
	 * @return currentLanguage
	 * Gets the current language
	 */
	static String getCurrentLanguage() {
		return currentLanguage;
	}
	
	
	/**
	 * Initializes languageMap to contain the data matching the current language
	 * sets isInitialized to true if successful
	 */
	static void init(){
		switch (currentLanguage) {
			
			// Insert text mappings for English here
			case "English":
				languageMap.put("EXAMPLE_LABLE","Example_text");
				isInitialized = true;
				break;
			
			// Insert text mappings for Swedish here
			case "Swedish":
				languageMap.put("EXAMPLE_LABLE","Exempel_text");
				isInitialized = true;
				break;
				
			// If currentLanguage is invalid
			default:
				System.out.println("Invalid language");
		}
	}
	
	
	/**
	 * @param stringName
	 * @return value matching key stringName
	 * Returns the value matching the key stringName in languageMap
	 * Initializes languageMap if it is not initialized already
	 */
	static String getString(String stringName) {
		if (!isInitialized) init();
		return languageMap.get(stringName);
	}
}

