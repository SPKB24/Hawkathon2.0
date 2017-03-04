package com.example.spal.hope_str;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;

public class UserProfile {
    private String mName;
    private int mAge;
    private String mUsername;
    private String mGender;
    private Location location;
    private List<String> mIllnesses;
    
    /*Constructor */
    public UserProfile() {}
    /** Setters */
    public void setName(String name) {
        mName = name;
    }
    public void setAge(int age) {
        mAge = age;
    }
    public void setUsername(String username) { mUsername = username; }
    public void setGender(String gender) {
        mGender = gender.toLowerCase();
    }
    public void setLocation(String city, String state) {
        location = new Location(city, state);
    }
    public void addIllness(String illness) {
        mIllnesses.add(illness.toLowerCase());
    }

    /** Getters */
    public String getName() {
        return mName;
    }
    public int getAge() {
        return mAge;
    }
    public String getUsername() { return mUsername; }
    public String getGender() { return mGender; }
    public Location getLocation() {
        return location;
    }
    public List<String> getmIllnesses() { return mIllnesses; }


    public String[] toStringArray() {
        ArrayList<String> temp = new ArrayList<>();

        temp.add(getName());
        temp.add(getAge() + "");
        temp.add(getLocation().toString());
        temp.add(getUsername());
        temp.add(getGender());

        String[] toReturn = new String[temp.size()];
        toReturn = temp.toArray(toReturn);

        return toReturn;
    }

//    /**
//     * DOM Parser - Create XML Document
//     */
//    public class createXMLFile
//    {
//        public void main(String[] args) {
//
//            try {
//
//                String temp_street = location.getStreet();
//                String temp_city = location.getCity();
//                String temp_state = location.getState();
//                String id = "";
//
//                DocumentBuilderFactory dbFactory =
//                        DocumentBuilderFactory.newInstance();
//                DocumentBuilder dBuilder =
//                        dbFactory.newDocumentBuilder();
//                Document doc = dBuilder.newDocument();
//                // root element
//                Element rootElement = doc.createElement("volunteer");
//                doc.appendChild(rootElement);
//
//                // name element
//                Element name = doc.createElement("name");
//                name.appendChild(
//                        doc.createTextNode(mName));
//                name.appendChild(name);
//
//                // age element
//                Element age = doc.createElement("age");
//                age.appendChild(
//                        doc.createTextNode(String.valueOf(mAge)));
//                age.appendChild(age);
//
//                // location element
//                Element location = doc.createElement("location");
//                location.appendChild(location);
//
//                //street element
//                Element street = doc.createElement("street");
//                street.appendChild(doc.createTextNode(temp_street));
//                location.appendChild(street);
//
//                //city element
//                Element city = doc.createElement("city");
//                city.appendChild(doc.createTextNode(temp_city));
//                location.appendChild(city);
//
//                //state element
//                Element state = doc.createElement("state");
//                state.appendChild(doc.createTextNode(temp_state));
//                location.appendChild(state);
//
//                // matches element
//                Element matches = doc.createElement("matches");
//                matches.appendChild(
//                        doc.createTextNode(id));
//                matches.appendChild(matches);
//
//
//                // write the content into xml file
//                TransformerFactory transformerFactory =
//                        TransformerFactory.newInstance();
//                Transformer transformer =
//                        transformerFactory.newTransformer();
//                DOMSource source = new DOMSource(doc);
//                StreamResult result =
//                        new StreamResult(new File("senior_profile.xml"));
//                transformer.transform(source, result);
//                // Output to console for testing
//                StreamResult consoleResult =
//                        new StreamResult(System.out);
//                transformer.transform(source, consoleResult);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
}