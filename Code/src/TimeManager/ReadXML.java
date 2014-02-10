package TimeManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class ReadXML {
	public static void main(String[] args) {
		try {
			String fileName = "assets/test2.xml";
			File file = new File(fileName);
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(file);
			Element root = doc.getRootElement();
			System.out.println("Root" + root);
			
			/* This is the XML file
			  	<?xml version="1.0" encoding="UTF-8"?>
				<company>
				  <staff id="1">
				    <firstname>yong</firstname>
				    <lastname>mook kim</lastname>
				    <nickname>mkyong</nickname>
				    <salary>5000</salary>
				  </staff>
				</company>
			 */
			
			//Update staff id attribute from 1 to 2
			Element staff = root.getChild("staff");
			staff.getAttribute("id").setValue("2");
			
			//Add new age element
			Element age = new Element("age").setText("28");
			staff.addContent(age);
			
			//Update Salary Value
			staff.getChild("salary").setText("7000");
			
			//remove first name element
			staff.removeChild("firstname");
			
			//Output with pretty formatting
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("assets/test2.xml"));
			
			//Output to console
			xmlOutput.output(doc,  System.out);
			
			
		} catch (IOException io) {
			System.out.println("IOException " + io.getMessage());
			io.printStackTrace();
		} catch (JDOMException jd) {
			System.out.println("JDOMException " + jd.getMessage());
			jd.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		}
	}
}
