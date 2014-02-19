package config;

import java.util.HashMap;

public class LanguageRepository {
	static String currentLanguage = Config.startupLanguage;
	public static String[] availableLanguages = {"English", "Swedish"};
	static HashMap<String,String> languageMap = new HashMap<String,String>();
	static Boolean isInitialized = false;

	static void setCurrentLanguage(String newLanguage) {
		currentLanguage = newLanguage;
		init();
	}
	
	static String getCurrentLanguage() {
		return currentLanguage;
	}
	
	static void init(){
		isInitialized = true;
		switch (currentLanguage) {
			
			// Insert text mappings for English here
			case "English":
				languageMap.put("EXAMPLE_LABLE","Example_text");
				break;
			
			// Insert text mappings for Swedish here
			case "Swedish":
				languageMap.put("EXAMPLE_LABLE","Exempel_text");
				break;
				
			default:
				System.out.println("Invalid language");
		}
	}
	static String getString(String stringName) {
		if (!isInitialized) init();
		return languageMap.get(stringName);
	}
}

