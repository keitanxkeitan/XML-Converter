package jp.ac.titech.keitanxkeitan.xmlconverter;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class ElementHandler extends DefaultHandler {

    XMLReader mReader;
    ElementHandler mParent;
    Element mElement;
    
    /**
     * ノードを設定する。
     * @param element
     */
    protected void setElement(Element element) {
        mElement = element;
    }
    
    /**
     * 初期設定を行う。オーバーライドする場合は必ず最初に親クラスの呼び出しを行う。
     * @param reader 現在パージングに利用しているXMLReader
     * @param parent 親要素ハンドラ
     * @param attrs この要素の属性
     * @throws SAXException
     */
    protected void initialize(XMLReader reader, ElementHandler parent, Attributes attrs)
            throws SAXException {
        mReader = reader;
        mParent = parent;
    }
    
    /**
     * 子要素が作成したノードを受け取る。基底クラスはSAXExceptionをスローする。
     * @param element 作成したノード
     * @throws SAXException
     */
    protected void addElement(Element element) throws SAXException {
        throw new SAXException("no enclosing elements: " + element);
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs)
            throws SAXException {
        ElementHandler h;
        HandlerFactory fac = HandlerFactory.getInstance(qName);
        // TODO: OreProg.javaの123行目辺りは必要か？
        if (fac == null) {
            throw new SAXException("unknown element:" + qName);
        }
        h = fac.create();
        h.initialize(mReader, mParent, attrs);
        mReader.setContentHandler(h);
    }
    
    /**
     * 保持している{@link Element}を参照するので、
     * オーバーライドする場合は最後に呼び出すこと。
     */
    public void endElement(String uri, String localName, String qName) throws SAXException {
        mParent.addElement(mElement);
        mReader.setContentHandler(mParent);
    }
    
}
