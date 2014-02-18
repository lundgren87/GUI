package dataBase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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


/**
 * Class to handle the XML database of TimeManager.
 * @author Pontus, Sven
 */
public class DBHandler {
	static String DBFile;
	static Document doc;   //Referred to as "the Document"
	
	public DBHandler() {
		DBFile = config.Config.DBFile + ".xml";
	}
	
	
	/**
	 * Initialize the data base from DBFile specified in config.java
	 * If the file does not exist, creates a skeleton document
	 * pre: If the DBFile exists, it has to have the correct format
	 */
	public void init() {
		
		//Create a new file object
		File file = new File(DBFile);
		
		if(file.exists()) {
			//Initialize a SAXBuilder to parse the file in to a document
			SAXBuilder builder = new SAXBuilder();
			try{
				doc = builder.build(file);
			} catch (Exception e) {
				System.out.println("Exception: " +e.getMessage());
			}
		}
		else {
			
			//No file exists, create a new Document and manually add skeleton structure
			doc = new Document();
			
			Element dB = new Element("DB");
			Element tasks = new Element("tasks");
			Element categories = new Element("categories");
			
			//Set root element and add children
			doc.setRootElement(dB);
			dB.addContent(categories);
			dB.addContent(tasks);
		}
	}
	
	
	/**
	 * Exit saves the existing Document to the file DBFile specified in config.java
	 * pre: init() has to be called prior to exit()
	 */
	public void exit() {
		//Output with pretty formatting
		XMLOutputter xmlOutput = new XMLOutputter();
		xmlOutput.setFormat(Format.getPrettyFormat());
		try {
			xmlOutput.output(doc, new FileWriter(DBFile));
		} catch (IOException io) {
			System.out.println("Exception: " +io.getMessage());
		}
	}
	

	/**
	 * Stores all tasks present in the model to the Document.
	 * pre: init() has to be called prior to storeTasks()
	 */
	public static void storeTasks() {
		
		//Get all tasks present in the model
//		List<TaskItem> taskList = TimeManagerModel.getTasks();
		List<TaskItem> taskList = dummyItem();
		
		//To access the correct part of the document, get root element and desired child
		Element root = doc.getRootElement();
		Element tasks = root.getChild("tasks");
		
		//Loop over all tasks in taskList and 
		for (Iterator it = taskList.iterator(); it.hasNext();) {
			TaskItem ti = (TaskItem) it.next();
			
			//Create new task element and create it's data elements
			Element task = new Element("task");
			Element taskCategory = new Element("taskCategory").setText(ti.taskCategory);
			Element taskDescription = new Element("taskDescription").setText(ti.taskDescripton);
			Element taskPriority = new Element("taskPriority").setText(Integer.toString(ti.taskPriority));
			
			//Add the data elements to the created task, and add the created task to the Document
			task.addContent(taskCategory);
			task.addContent(taskDescription);
			task.addContent(taskPriority);
			tasks.addContent(task);
		}
	}
	
	
	/**
	 * Stores all categories present in the model to the Document.
	 * pre: init() has to be called prior to storeCategories()
	 */
	public static void storeCategories() {
		
		//Get all categories present in the model
		//List<TaskCategory> categoryList = TimeManagerModel.getCategories();
		List<TaskCategory> categoryList = dummyCategory();
		
		//To access the correct part of the document, get root element and desired child
		Element root = doc.getRootElement();
		Element categories = root.getChild("categories");
		
		//Loop over the categories in categoryList
		for (Iterator it = categoryList.iterator(); it.hasNext();) {
			TaskCategory ti = (TaskCategory) it.next();
			
			//Create new category element and create it's data elements
			Element category = new Element("category");
			Element categoryName = new Element("categoryName").setText(ti.categoryName);
			Element categoryDescription = new Element("categoryDescription").setText(ti.categoryDescription);
			
			//Add the data elements to the created category, and add the created category to the Document
			category.addContent(categoryName);
			category.addContent(categoryDescription);
			categories.addContent(category);
		}
	}
	
	
	/**
	 * Reads all categories present in the Document and adds them to the model.
	 * pre: init() has to be called prior to readCategories()
	 */
	public void readCategories() {
		
		//To access the correct part of the document, get root element and desired child
		Element root = doc.getRootElement();
		Element categories = root.getChild("categories");
		
//		System.out.println("Root: " + root);
//		System.out.println("Categories: " + categories);

		// Loop over all categories in the Document
		for(Iterator it = categories.getChildren().iterator(); it.hasNext();) {
			Element category = (Element) it.next();
			
			//Get the contents of the text fields
			String categoryName = category.getChild("categoryName").getText();
			String categoryDescription = category.getChild("categoryDescription").getText();
			
//			System.out.println("CategoryName: " + categoryName);
//			System.out.println("CategoryDescription: " + categoryDescription);
			
			//Add a new category to the model with the retrieved text fields as parameters
			//TimeManagerModel.addNewCategory(categoryName, categoryDescription);
		}
	}
	
	
	
	/**
	 * Reads all categories present in the Document and adds them to the model.
	 * pre: init() has to be called prior to readCategories()
	 */
	public void readTasks() {
		
		//To access the correct part of the document, get root element and desired child
		Element root = doc.getRootElement();
		Element tasks = root.getChild("tasks");
//		System.out.println("Root: " + root);
//		System.out.println("Tasks: " + tasks);
		
		// Loop over all tasks in the Document
		for(Iterator it = tasks.getChildren().iterator(); it.hasNext();) {
			Element task = (Element) it.next();
			
			//Get the contents of the text fields
			String taskCategory = task.getChild("taskCategory").getText();
			String taskDescription = task.getChild("taskDescription").getText();
			String taskPriority = task.getChild("taskPriority").getText();
			
//			System.out.println("taskCategory: " + taskCategory);
//			System.out.println("taskDescription: " + taskDescription);
//			System.out.println("taskPriority: " + taskPriority);
			
			//Add a new task to the model with the retrieved text fields as parameters
			//TimeManagerModel.addNewTask(taskCategory, taskDescription, Integer.parseInt(taskPriority));
		}
	}
	
	
	// DUMMY SHIT
	public static List<TaskItem> dummyItem(){
		TaskItem t1 = new TaskItem( "Do stuff at home", "Home", 3);
		TaskItem t2 = new TaskItem("Do stuff at work", "Work", 2);
		TaskItem t3 = new TaskItem("Do other stuff at home", "Home", 1);
		List<TaskItem> taskList = new ArrayList<TaskItem>();
		taskList.add(t1);
		taskList.add(t2);
		taskList.add(t3);
		
		return taskList;
	}
	
	
	// DUMMY SHIT
	public static List<TaskCategory> dummyCategory(){
		TaskCategory t1 = new TaskCategory("Home", "stuff at home");
		TaskCategory t2 = new TaskCategory("Work", "stuff at work");
		List<TaskCategory> categoryList = new ArrayList<TaskCategory>();
		categoryList.add(t1);
		categoryList.add(t2);
		
		return categoryList;
	}
	
	
	// PRINT SHIT
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
