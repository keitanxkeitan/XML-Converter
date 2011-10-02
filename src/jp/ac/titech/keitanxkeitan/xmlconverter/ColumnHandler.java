package jp.ac.titech.keitanxkeitan.xmlconverter;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class ColumnHandler extends ElementHandler {

    static final String NAME = "name";
    static final String DATA_TYPE = "data_type";
    
    String mName;
    String mType;
    
    protected void initialize(XMLReader reader, ElementHandler parent, Attributes attrs)
            throws SAXException {
        super.initialize(reader, parent, attrs);
        mName = attrs.getValue(NAME);
        mType = attrs.getValue(DATA_TYPE);
    }
    
    public void endElement(String uri, String localName, String qName) throws SAXException {
        setElement(new Column(mName, mType));
        super.endElement(uri, localName, qName);
    }
    
}
