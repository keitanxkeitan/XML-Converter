package jp.ac.titech.keitanxkeitan.xmlconverter;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class ApplicationHandler extends ElementHandler {

    static final String NAME = "name";
    static final String USER = "user";
    static final String ORGANIZATION = "organization";
    
    String mName;
    String mUser;
    String mOrganization;
    List<Database> mDatabases = new ArrayList<Database>();
 
    protected void initialize(XMLReader reader, ElementHandler parent, Attributes attrs)
            throws SAXException {
        super.initialize(reader, parent, attrs);
        mName = attrs.getValue(NAME);
        mUser = attrs.getValue(USER);
        mOrganization = attrs.getValue(ORGANIZATION);
    }
    
    protected void addElement(Element element) {
        if (Database.class.isInstance(element)) {
            mDatabases.add((Database)element);
        }
    }
    
    public void endElement(String uri, String localName, String qName) throws SAXException {
        setElement(new Application(mName, mUser, mOrganization, mDatabases));
        super.endElement(uri, localName, qName);
    }
    
}
