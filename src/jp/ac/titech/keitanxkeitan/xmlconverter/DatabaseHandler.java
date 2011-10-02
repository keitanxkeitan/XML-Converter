package jp.ac.titech.keitanxkeitan.xmlconverter;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class DatabaseHandler extends ElementHandler {

    static final String NAME = "name";
    
    String mName;
    List<Table> mTables = new ArrayList<Table>();
    
    protected void initialize(XMLReader reader, ElementHandler parent, Attributes attrs)
            throws SAXException {
        super.initialize(reader, parent, attrs);
        mName = attrs.getValue(NAME);
    }
    
    protected void addElement(Element elememt) {
        mTables.add((Table)elememt);
    }
    
    public void endElement(String uri, String localName, String qName) throws SAXException {
        setElement(new Database(mName, mTables));
        super.endElement(uri, localName, qName);
    }
    
}
