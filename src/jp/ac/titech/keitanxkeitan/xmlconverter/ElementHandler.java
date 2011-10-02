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
     * �m�[�h��ݒ肷��B
     * @param element
     */
    protected void setElement(Element element) {
        mElement = element;
    }
    
    /**
     * �����ݒ���s���B�I�[�o�[���C�h����ꍇ�͕K���ŏ��ɐe�N���X�̌Ăяo�����s���B
     * @param reader ���݃p�[�W���O�ɗ��p���Ă���XMLReader
     * @param parent �e�v�f�n���h��
     * @param attrs ���̗v�f�̑���
     * @throws SAXException
     */
    protected void initialize(XMLReader reader, ElementHandler parent, Attributes attrs)
            throws SAXException {
        mReader = reader;
        mParent = parent;
    }
    
    /**
     * �q�v�f���쐬�����m�[�h���󂯎��B���N���X��SAXException���X���[����B
     * @param element �쐬�����m�[�h
     * @throws SAXException
     */
    protected void addElement(Element element) throws SAXException {
        throw new SAXException("no enclosing elements: " + element);
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs)
            throws SAXException {
        ElementHandler h;
        HandlerFactory fac = HandlerFactory.getInstance(qName);
        // TODO: OreProg.java��123�s�ڕӂ�͕K�v���H
        if (fac == null) {
            throw new SAXException("unknown element:" + qName);
        }
        h = fac.create();
        h.initialize(mReader, mParent, attrs);
        mReader.setContentHandler(h);
    }
    
    /**
     * �ێ����Ă���{@link Element}���Q�Ƃ���̂ŁA
     * �I�[�o�[���C�h����ꍇ�͍Ō�ɌĂяo�����ƁB
     */
    public void endElement(String uri, String localName, String qName) throws SAXException {
        mParent.addElement(mElement);
        mReader.setContentHandler(mParent);
    }
    
}
