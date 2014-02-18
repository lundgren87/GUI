package dataBase;

import java.io.FileWriter;

import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class DBTest {
	public static void main(String[] args) {
		DBHandler handler = new DBHandler();
		handler.init();
		
		handler.storeCategories();
		handler.storeTasks();
		
		handler.print();
		
		
		handler.readCategories();
		handler.readTasks();
		System.out.println("Done.");
		handler.exit();
	}
}
