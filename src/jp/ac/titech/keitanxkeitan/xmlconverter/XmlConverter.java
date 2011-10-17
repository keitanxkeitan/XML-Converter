package jp.ac.titech.keitanxkeitan.xmlconverter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class XmlConverter {
    
    Application application;

    protected String getRootElementName() {
        return "xmlDescription";
    }
    
    /**
     * 与えられたXML記述から構文木を生成する。
     * @param stm 処理対象XML記述
     * @throws SAXException
     * @throws IOException
     */
    public void read(InputStream stm) throws SAXException, IOException {
        
        final XMLReader r = XMLReaderFactory.createXMLReader();
        r.setContentHandler(new ElementHandler() {
            { mReader = r; }
            public void startElement(String uri, String localName, String qName, Attributes attrs)
                    throws SAXException {
                if (!qName.equals(getRootElementName())) {
                    super.startElement(uri, localName, qName, attrs);
                }
            }
            protected void addElement(Element element) {
                application = (Application)element;
            }
            public void endElement(String uri, String localName, String qName)
                    throws SAXException {
            }
        });
        
        r.parse(new InputSource(stm));
        
    }
    
    /**
     * XML記述を変換する。
     * @throws Exception
     */
    public void convert() throws Exception {
        application.generate();
    }
    
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.err.println("usage: java jp.ac.titech.keitanxkeitan.xmlconverter.XmlConverter xmlfile");
            System.exit(1);
        }
        XmlConverter converter = new XmlConverter();
        FileInputStream fi = new FileInputStream(args[0]);
        converter.read(fi);
        fi.close();
        converter.convert();
    }
        
}
