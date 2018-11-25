import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLUpdater {

    public static void main(String[] args) {
       UpdateFile();
    }
    
   private static void UpdateFile() {
       String filePath = "C:\\\\Users\\\\Gerald Trinidad\\\\Desktop\\\\XML.xml";
       File xmlFile = new File(filePath);
       DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
       DocumentBuilder dBuilder;
       try {
           dBuilder = dbFactory.newDocumentBuilder();
           Document doc = dBuilder.parse(xmlFile);
           doc.getDocumentElement().normalize();
           //write the updated document to file or console
           doc.getDocumentElement().normalize();
           
           updateElementValue(doc);
           
           TransformerFactory transformerFactory = TransformerFactory.newInstance();
           Transformer transformer = transformerFactory.newTransformer();
           DOMSource source = new DOMSource(doc);
           StreamResult result = new StreamResult(new File("C:\\\\Users\\\\Gerald Trinidad\\\\Desktop\\\\XML.xml"));
           transformer.setOutputProperty(OutputKeys.INDENT, "yes");
           transformer.transform(source, result);
           System.out.println("XML file updated successfully");
           
       } catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
           e1.printStackTrace();
       }
   }

    private static void updateElementValue(Document doc) {
        NodeList employees = doc.getElementsByTagName("Employee");
        Element emp = null;
        //loop for each employee
        for(int i=0; i<employees.getLength();i++){
            emp = (Element) employees.item(i);
            Node name = emp.getElementsByTagName("name").item(0).getFirstChild();
            Node age = emp.getElementsByTagName("age").item(0).getFirstChild();
            Node role = emp.getElementsByTagName("role").item(0).getFirstChild();
            Node gender = emp.getElementsByTagName("gender").item(0).getFirstChild();
            name.setNodeValue("gerd");
            age.setNodeValue("22");
            role.setNodeValue("Software Engineer");
            gender.setNodeValue("Male");
        }
    }

}