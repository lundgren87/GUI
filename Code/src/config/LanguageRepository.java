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
	 * sets newLanguage to currentLanguage and runs init
	 */
	public static void setCurrentLanguage(String newLanguage) {
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

				isInitialized = true;
				break;
			
			// Insert text mappings for Swedish here
			case "Swedish":
				languageMap.put("EXAMPLE_LABLE","Exempel_text");
				languageMap.put("TIME_MANAGER","Time Manager");
				languageMap.put("ADD","Lägg till");
				languageMap.put("TITLE","Titel");
				languageMap.put("FILE","Arkiv");
				languageMap.put("EDIT","Redigera");
				languageMap.put("HELP","Hjälp");
				languageMap.put("SAVE","Spara");
				languageMap.put("LOAD","Ladda");
				languageMap.put("EXIT","Avsluta");
				languageMap.put("LANGUAGE","Språk");
				languageMap.put("DESC_ACTIV","Aktivitetsbeskrivning");
				languageMap.put("DUEDATE","Slutdatum");
				languageMap.put("CATEGORY","Kategori");
				languageMap.put("HIGH","Hög");
				languageMap.put("MEDIUM","Medel");
				languageMap.put("LOW","Låg");
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

