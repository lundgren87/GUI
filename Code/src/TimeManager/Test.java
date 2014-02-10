package TimeManager;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class Test {

		public static void main(String[] args) throws Exception {
			String data =
					  "<root>" +
					  "<Companyname>" +
					  "<Employee name=\"Girish\" Age=\"25\">Developer</Employee>" +
					  "</Companyname>" +
					  "<Companyname>" +
					  "<Employee name=\"Komal\" Age=\"25\">Administrator</Employee>" +
					  "</Companyname>" +
					  "</root>";
			
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(new ByteArrayInputStream(data.getBytes()));
			Element root = document.getRootElement();
			List row = root.getChildren("Companyname");
			
			for (int i=0; i < row.size(); i++) {
				Element Companyname = (Element) row.get(i);

				List column = Companyname.getChildren("Employee");
				for (int j = 0; j < column.size(); j++) {
					Element Employee = (Element) column.get(j);
					String name = Employee.getAttribute("name").getValue();
					String value = Employee.getText();
					int length = Employee.getAttribute("Age").getIntValue();

					System.out.println("Name = " + name);
					System.out.println("Profile = " + value);
					System.out.println("Age = " + length);
				}
			}
		}
			
}
