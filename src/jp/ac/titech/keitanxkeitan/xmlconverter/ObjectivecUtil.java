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
     * @return
     */
    public static String createImportSentence(String fileName, boolean isPublic) {
        String importSentence = new String();
        if (isPublic) {
            importSentence = "#import <" + fileName + ">";
        } else {
            importSentence = "#import \"" + fileName + "\"";
        }
        return importSentence;
    }
    
}
