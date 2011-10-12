package jp.ac.titech.keitanxkeitan.xmlconverter;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class ColumnHandler extends ElementHandler {

    static final String NAME = "name";
    static final String DATA_TYPE = "dataType";
    static final String IS_PRIMARY_KEY = "isPrimaryKey";
    static final String IS_AUTOINCREMENT = "isAutoincrement";
    static final String IS_NOT_NULL = "isNotNull";
    static final String IS_UNIQUE = "isUnique";
    static final String DEFAULT = "default";
    static final String CHECK = "check";
    
    String mName;
    String mDataType;
    String mIsPrimaryKey;
    String mIsAutoincrement;
    String mIsNotNull;
    String mIsUnique;
    String mDefault;
    String mCheck;
    
    protected void initialize(XMLReader reader, ElementHandler parent, Attributes attrs)
            throws SAXException {
        super.initialize(reader, parent, attrs);
        mName = attrs.getValue(NAME);
        mDataType = attrs.getValue(DATA_TYPE);
        mIsPrimaryKey = attrs.getValue(IS_PRIMARY_KEY);
        mIsAutoincrement = attrs.getValue(IS_AUTOINCREMENT);
        mIsNotNull = attrs.getValue(IS_NOT_NULL);
        mIsUnique = attrs.getValue(IS_UNIQUE);
        mDefault = attrs.getValue(DEFAULT);
        mCheck = attrs.getValue(CHECK);
    }
    
    public void endElement(String uri, String localName, String qName) throws SAXException {
        setElement(new Column(mName, mDataType, mIsPrimaryKey, mIsAutoincrement, mIsNotNull,
                mIsUnique, mDefault, mCheck));
        super.endElement(uri, localName, qName);
    }
    
}
