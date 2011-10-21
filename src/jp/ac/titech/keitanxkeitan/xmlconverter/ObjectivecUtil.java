package jp.ac.titech.keitanxkeitan.xmlconverter;

/**
 * Objective-C �֘A�̃��[�e�B���e�B�N���X
 * @author keitanxkeitan
 *
 */
public class ObjectivecUtil {

    /**
     * �R���X�g���N�^
     * �C���X�^���X���֎~
     */
    private ObjectivecUtil() {}
    
    /**
     * �C���|�[�g�����쐬����B
     * @param fileName �w�b�_�t�@�C���̃p�X
     * @param isPublic ���J�w�b�_�t�@�C�����ǂ���
     * @return �쐬�����C���|�[�g��
     */
    public static String createImportSentence(String fileName, boolean isPublic) {
        String ret = new String();
        if (isPublic) {
            ret = "#import <" + fileName + ">";
        } else {
            ret = "#import \"" + fileName + "\"";
        }
        return ret;
    }
    
    /**
     * �^����ꂽ DataType �^�̃f�[�^�^��
     * Objective-C �őΉ�����f�[�^�^�ɕϊ�����B
     * @param dataType �ϊ�����f�[�^�^
     * @return �ϊ������f�[�^�^
     */
    public static String toDataTypeStringWithSpace(DataType dataType) {
        String ret = new String();
        switch (dataType) {
        case NULL:
            break;
        case INTEGER:
            ret = "int ";
            break;
        case REAL:
            ret = "float ";
            break;
        case TEXT:
            ret = "NSString *";
            break;
        case BLOB:
            break;
        }
        return ret;
    }
    
}
