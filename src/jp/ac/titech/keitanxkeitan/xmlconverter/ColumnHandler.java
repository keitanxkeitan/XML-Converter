package jp.ac.titech.keitanxkeitan.xmlconverter;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class ColumnHandler extends ElementHandler {

    static final String NAME = "name";
    static final String DATA_TYPE = "data_type";
    
    String mName;
    String mDataType;
    
    protected void initialize(XMLReader reader, ElementHandler parent, Attributes attrs)
            throws SAXException {
        super.initialize(reader, parent, attrs);
        mName = attrs.getValue(NAME);
        mDataType = attrs.getValue(DATA_TYPE);
    }
    
    public void endElement(String uri, String localName, String qName) throws SAXException {
        setElement(new Column(mName, mDataType));
        super.endElement(uri, localName, qName);
    }
    
}
