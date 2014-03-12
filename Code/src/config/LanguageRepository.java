package config;

import java.util.HashMap;

import javax.swing.JMenuItem;


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
	 * sets currentLanguage to newLanguage and runs init to load the strings for the new language
	 */
	public static void setCurrentLanguage(String newLanguage) {
		if(currentLanguage != newLanguage) {
			currentLanguage = newLanguage;
			Config.saveProperty("startupLanguage", newLanguage);
			init();
		}
	}
	
	
	/**
	 * @return currentLanguage
	 * Gets the current language
	 */
	public static String getCurrentLanguage() {
		return currentLanguage;
	}
	
	
	/**
	 * Initializes languageMap to contain the data matching the current language
	 * sets isInitialized to true if successful
	 */
	static void init(){
		switch (currentLanguage) {
			
			case "English":
				// Insert text mappings for English here
				languageMap.put("EXAMPLE_LABLE","Example_text");
				languageMap.put("TIME_MANAGER", "Time Manager");
				languageMap.put("ADD", "Add");
				languageMap.put("TITEL", "titel");
				languageMap.put("FILE", "File");
				languageMap.put("EDIT", "Edit");
				languageMap.put("HELP", "Help");
				languageMap.put("SAVE", "Save");
				languageMap.put("LOAD", "Load");
				languageMap.put("EXIT", "Exit");
				languageMap.put("LANGUAGE", "Language");
				languageMap.put("DESC_ACTIV", "Description of activity");
				languageMap.put("DUEDATE", "Due Date");
				languageMap.put("CATEGORY", "Category");
				languageMap.put("HIGH", "High");
				languageMap.put("MEDIUM", "Medium");
				languageMap.put("LOW", "Low");
				languageMap.put("PRIORITY", "Priority");
				languageMap.put("JAN","January");
				languageMap.put("FEB","February");
				languageMap.put("MAR","March");
				languageMap.put("APR","April");
				languageMap.put("MAY","May");
				languageMap.put("JUN","June");
				languageMap.put("JUL","July");
				languageMap.put("AUG","August");
				languageMap.put("SEP","September");
				languageMap.put("OCT","October");
				languageMap.put("NOV","November");
				languageMap.put("DEC","December");
				languageMap.put("SWEDISH", "Swedish");
				languageMap.put("ENGLISH", "English");
				languageMap.put("THEME", "Theme");
				languageMap.put("OCEAN", "Ocean");
				languageMap.put("METAL", "Metal");
				languageMap.put("RED", "Red");
				languageMap.put("BLUE", "Blue");
				languageMap.put("THEMERESTART", "Change of theme takes effect after restart.");
				languageMap.put("LANGUAGERESTART", "Change of language takes effect after restart.");
				

				isInitialized = true;
				break;
			
			case "Swedish":
				// Insert text mappings for Swedish here
				languageMap.put("EXAMPLE_LABLE","Exempel_text");
				languageMap.put("TIME_MANAGER","Time Manager");
				languageMap.put("ADD","L�gg till");
				languageMap.put("TITLE","Titel");
				languageMap.put("FILE","Arkiv");
				languageMap.put("EDIT","Redigera");
				languageMap.put("HELP","Hj�lp");
				languageMap.put("SAVE","Spara");
				languageMap.put("LOAD","Ladda");
				languageMap.put("EXIT","Avsluta");
				languageMap.put("LANGUAGE","Spr�k");
				languageMap.put("DESC_ACTIV","Aktivitetsbeskrivning");
				languageMap.put("DUEDATE","Slutdatum");
				languageMap.put("CATEGORY","Kategori");
				languageMap.put("HIGH","H�g");
				languageMap.put("MEDIUM","Medel");
				languageMap.put("LOW","L�g");
				languageMap.put("PRIORITY","Prioritet");
				languageMap.put("JAN","Januari");
				languageMap.put("FEB","Februari");
				languageMap.put("MAR","Mars");
				languageMap.put("APR","April");
				languageMap.put("MAY","Maj");
				languageMap.put("JUN","Juni");
				languageMap.put("JUL","Juli");
				languageMap.put("AUG","Augusti");
				languageMap.put("SEP","September");
				languageMap.put("OCT","October");
				languageMap.put("NOV","November");
				languageMap.put("DEC","December");
				languageMap.put("SWEDISH","Svenska");
				languageMap.put("ENGLISH","Engelska");
				languageMap.put("THEME", "Tema");
				languageMap.put("OCEAN", "Hav");
				languageMap.put("METAL", "Metall");
				languageMap.put("RED", "R�d");
				languageMap.put("BLUE", "Bl�");
				languageMap.put("THEMERESTART", "Byte av tema sker vid omstart.");
				languageMap.put("LANGUAGERESTART", "Byte av spr�k sker vid omstart.");
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
	public static String getString(String stringName) {
		if (!isInitialized) init();
		return languageMap.get(stringName);
	}
}

