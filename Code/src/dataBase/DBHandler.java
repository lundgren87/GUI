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
	static Document doc;
	static String DBFile;
	TimeManagerModel model;
	
	public DBHandler(TimeManagerModel m) {
		DBFile = config.Config.DBFile + ".xml";
		model = m;
	}
	
	/**
	 * Creates a new document containing only the skeleton structure
	 * @return 
	 */
	static void skeletonDoc(){
		doc = new Document();
		
		Element dB = new Element("DB");
		Element tasks = new Element("tasks");
		Element categories = new Element("categories");
		
		//Set root element and add children to doc
		doc.setRootElement(dB);
		dB.addContent(categories);
		dB.addContent(tasks);
	}
	
	/**
	 * Initialize the database from DBFile specified in config.java
	 * pre: If the DBFile exists, it has to have the correct format
	 */
	public void init() {
		//Create a new file object
		File file = new File(DBFile);
		
		if(file.exists()) {
			//Initialize a SAXBuilder to parse the file into a document
			SAXBuilder builder = new SAXBuilder();
			try{
				//build a new Document from the file
				doc = builder.build(file);
				
			} catch (Exception e) {
				System.out.println("Exception: " +e.getMessage());
			}
			//Read contents of doc and send it to the model
			readCategories();
			readTasks();
		}	
	}
	
	/**
	 * Exit saves the information in model to the file DBFile specified in config.java
	 */
	public void exit() {
		System.out.println("Saving DB state");
		
		//reset document to an empty one
		skeletonDoc();
		
		//fill it with categories and tasks from model
		storeCategories();
		storeTasks();
		
		//Output to file with pretty formatting
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
	public void storeTasks() {
		
		//Get all tasks present in the model
		List<TaskItem> taskList = model.getTasks();
		//List<TaskItem> taskList = dummyItem();
		
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
	public void storeCategories() {
		
		//Get all categories present in the model
		List<TaskCategory> categoryList = model.getCategories();
		//List<TaskCategory> categoryList = dummyCategory();
		
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

		// Loop over all categories in the Document
		for(Iterator it = categories.getChildren().iterator(); it.hasNext();) {
			Element category = (Element) it.next();
			
			//Get the contents of the text fields
			String categoryName = category.getChild("categoryName").getText();
			String categoryDescription = category.getChild("categoryDescription").getText();
			
			//Add a new category to the model with the retrieved text fields as parameters
			model.addNewCategory(categoryName, categoryDescription);
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
		
		// Loop over all tasks in the Document
		for(Iterator it = tasks.getChildren().iterator(); it.hasNext();) {
			Element task = (Element) it.next();
			
			//Get the contents of the text fields
			String taskCategory = task.getChild("taskCategory").getText();
			String taskDescription = task.getChild("taskDescription").getText();
			String taskPriority = task.getChild("taskPriority").getText();
			
			//Add a new task to the model with the retrieved text fields as parameters
			model.addNewTask(taskDescription, taskCategory, Integer.parseInt(taskPriority));
		}
	}
}
