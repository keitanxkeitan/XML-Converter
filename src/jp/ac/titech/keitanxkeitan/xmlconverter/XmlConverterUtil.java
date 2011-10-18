package jp.ac.titech.keitanxkeitan.xmlconverter;

/**
 * XML Converter �ň������[�e�B���e�B�N���X
 * @author keitanxkeitan
 *
 */
public class XmlConverterUtil {

    /**
     * �R���X�g���N�^
     * �C���X�^���X���֎~
     */
    private XmlConverterUtil() {}
    
    /**
     * �^����ꂽ��������A�b�p�[�L�������P�[�X�ɕϊ�����B
     * �X�y�[�X ' ' �ƃA���_�[�X�R�A '_' ��v�f��̋�؂�Ƃ��ėp����B
     * �Ⴆ�� "todo_list" �Ƃ������O�� "TodoList" �ɕϊ������B
     * @param word �ϊ����镡����
     * @return �ϊ�����������
     */
    public static String toUpperCamelCase(String word) {
        // �A���_�[�X�R�A '_' ���X�y�[�X ' ' �ɕϊ����Ă���
        // �X�y�[�X ' ' ����؂蕶���Ƃ��ĕ�������B
        word = word.replace('_', ' ');
        String[] elements = word.split(" ");
        
        word = new String();
        
        // �v�f��̐擪�̕�����啶���ɂ���B
        for (String element : elements) {
            element = element.substring(0, 1).toUpperCase() + element.substring(1);
            word += element;
        }
        
        return word;
    }

    /**
     * �^����ꂽ����������[���[�L�������P�[�X�ɕϊ�����B
     * �X�y�[�X ' ' �ƃA���_�[�X�R�A '_' ��v�f��̋�؂�Ƃ��ėp����B
     * �Ⴆ�� "todo_list" �Ƃ������O�� "todoList" �ɕϊ������B
     * @param word �ϊ����镡����
     * @return �ϊ�����������
     */
    public static String toLowerCamelCase(String word) {
        word = toUpperCamelCase(word);
        
        word = word.substring(0, 1).toLowerCase() + word.substring(1);
        
        return word;
    }
    
}
