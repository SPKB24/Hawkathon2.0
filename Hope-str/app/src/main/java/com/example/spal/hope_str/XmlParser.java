package com.example.spal.hope_str;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlParser {

	/*
	 * For test
	 */
	public static void main(String[] args) {}


	public static UserProfile XMLToProfile(InputStream stream) throws Exception {
        UserProfile newUser = new UserProfile();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
	         // use the factory to create a documentbuilder
	         DocumentBuilder builder = factory.newDocumentBuilder();

	         Document doc = builder.parse(stream);

	         // get the first element
	         Element element = doc.getDocumentElement();

             // get the username
             newUser.setUsername(element.getAttribute("username"));

	         // get all child nodes
	         NodeList nodes = element.getChildNodes();

	         // print the text content of each child
	         for (int i = 0; i < nodes.getLength(); i++) {
	        	if(nodes.item(i).getNodeType() == Node.ELEMENT_NODE)
	        	{
	        		Element el = (Element) nodes.item(i);
	        		
	        		if( el.getNodeName().contains("personal-info")){
	        			
	        			//concat first and last names
                        newUser.setName( el.getElementsByTagName("first-name").item(0).getTextContent() + " " +
	        							el.getElementsByTagName("last-name").item(0).getTextContent());
	        			
	        			//get the users age
	        			String sAge = el.getElementsByTagName("age").item(0).getTextContent();
                        newUser.setAge(Integer.parseInt(sAge));

                        // get Gender
                        String gender = el.getElementsByTagName("gender").item(0).getTextContent();
                        newUser.setGender(gender);

                        //retrieves all address fields and packages into a location
                        String city = el.getElementsByTagName("city").item(0).getTextContent();
                        String state = el.getElementsByTagName("state").item(0).getTextContent();
                        newUser.setLocation(city, state);

                        for (int j = 0; j < el.getElementsByTagName("illness").getLength(); j++) {
                            newUser.addIllness(el.getElementsByTagName("illness").item(i).getTextContent());
                        }
	        		}
	        	}
	         }
	      } catch (Exception ex) {
             // No data found
             return null;
	      }
		
		return newUser;
	}
}