package jp.ac.titech.keitanxkeitan.xmlconverter;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class TableHandler extends ElementHandler {

    static final String NAME = "name";
    
    public String mName;
    public List<Column> mColumns = new ArrayList<Column>();
    
    protected void initialize(XMLReader reader, ElementHandler parent, Attributes attrs)
            throws SAXException {
        super.initialize(reader, parent, attrs);
        mName = attrs.getValue(NAME);
    }
    
    protected void addElement(Element element) {
        mColumns.add((Column)element);
    }
    
    public void endElement(String uri, String localName, String qName) throws SAXException {
        setElement(new Table(mName, mColumns));
        super.endElement(uri, localName, qName);
    }
    
}
