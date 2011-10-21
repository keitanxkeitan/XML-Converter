package jp.ac.titech.keitanxkeitan.xmlconverter;

/**
 * Objective-C �֘A�̃��[�e�B���e�B�N���X
 * @author keitanxkeitan
 *
 */
public class ObjectivecUtil {
    
    public static final String INDENT = "  ";

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
     * Objective-C �̑Ή�����f�[�^�^�ɕϊ�����B
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
    
    /**
     * �^����ꂽ�f�[�^�^�ƕϐ�������ϐ��錾�i�f�[�^�^�ƕϐ����̑g�j���쐬����B
     * @param dataType �f�[�^�^
     * @param varName �ϐ���
     * @return �ϐ��錾
     */
    public static String createVariableDeclaration(DataType dataType, String varName) {
        return toDataTypeStringWithSpace(dataType) + varName;
    }
    
    /**
     * �^����ꂽ�f�[�^�^�ƕϐ�������ϐ��錾�i�f�[�^�^�ƕϐ����̑g�j���쐬����B
     * �쐬���ꂽ�ϐ��錾�̓Z�~�R���� ';' �ŏI�[����B
     * @param dataType �f�[�^�^
     * @param varName �ϐ���
     * @return �ϐ��錾
     */
    public static String createVariableDeclarationStatement(DataType dataType, String varName) {
        return createVariableDeclaration(dataType, varName) + ";";
    }
    
    /**
     * �^����ꂽ��������N���X�����o�ϐ����ɕϊ�����B
     * @param word �ϊ����镡����
     * @return �ϊ������N���X�����o�ϐ���
     */
    public static String toClassMemberVariableName(String word) {
        return CommonUtil.toLowerCamelCase(word) + "_";
    }
    
}
