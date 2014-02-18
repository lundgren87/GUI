package config;

import java.util.List;

public class LanguageRepository {
	static int currentLanguage = 0;
	
	static void setCurrentLanguage(int newLanguage) {
		currentLanguage = newLanguage;
	}
	
	static int getCurrentLanguage() {
		return currentLanguage;
	}
	
	static String getString(String stringName) {
		String languageName = Config.availableLanguages[currentLanguage];
		Language<?> act;
		try {
			act = Class.forName("config."+languageName);
		} catch (ClassNotFoundException e) {
			System.out.println("Language" + languageName + "does not exist.");
			e.printStackTrace();
		}
		return act.getString(stringName);
		
	}
}

