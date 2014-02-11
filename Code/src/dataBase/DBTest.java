package dataBase;

import java.io.FileWriter;

import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class DBTest {
	public static void main(String[] args) {
		DBHandler handler = new DBHandler();
		handler.init();
		handler.print();
		
		handler.readCategories();
		System.out.println("Done.");
	}
}
