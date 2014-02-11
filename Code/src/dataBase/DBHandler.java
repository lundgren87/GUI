package dataBase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import TimeManager.TaskCategory;
import TimeManager.TaskItem;
import TimeManager.TimeManagerModel;

public class DBHandler {
	static String DBFile;
	static Document doc;
	public DBHandler() {
		DBFile = config.Config.DBFile + ".xml";
	}
	
	public void init() {
		File file = new File(DBFile);
		SAXBuilder builder = new SAXBuilder();
		try{
			doc = builder.build(file);
		} catch (Exception e) {
			System.out.println("Exception: " +e.getMessage());
		}
	}
	
	public void exit() {
		//Output with pretty formatting
		XMLOutputter xmlOutput = new XMLOutputter();
		xmlOutput.setFormat(Format.getPrettyFormat());
		try {
			xmlOutput.output(doc, new FileWriter("assets/test2.xml"));
		} catch (IOException io) {
			System.out.println("Exception: " +io.getMessage());
		}
	}
	
//	public static void StoreCategories() {
//		
//	}
//	
//	public static void StoreTasks() {
//		
//	}
	
	public void readCategories() {
		Element root = doc.getRootElement();
		Element categories = root.getChild("categories");
//		System.out.println("Root: " + root);
//		System.out.println("Categories: " + categories);

		for(Iterator it = categories.getChildren().iterator(); it.hasNext();) {
			Element category = (Element) it.next();
			//System.out.println("Category: " + category);
			String categoryName = category.getChild("categoryName").getText();
			String categoryDescription = category.getChild("categoryDescription").getText();
			
			//System.out.println("CategoryName: " + categoryName);
			//System.out.println("CategoryDescription: " + categoryDescription);
			//TimeManagerModel.addNewCategory(categoryName, categoryDescription);
		}
	}
	
//	public List<TaskItem> ReadTasks() {
//		
//	}
	
	public void print() {
		//Output with pretty formatting
		XMLOutputter xmlOutput = new XMLOutputter();
		xmlOutput.setFormat(Format.getPrettyFormat());
		
		//Output to console
		try {
			xmlOutput.output(doc, System.out);
		} catch (IOException e) {
			System.out.println("Exception: " + e + " Stacktrace:");
			e.printStackTrace();
		}
	}
}
