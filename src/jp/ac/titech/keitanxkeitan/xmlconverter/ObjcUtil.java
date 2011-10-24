package jp.ac.titech.keitanxkeitan.xmlconverter;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

/**
 * Objective-C �֘A�̃��[�e�B���e�B�N���X
 * @author keitanxkeitan
 *
 */
public class ObjcUtil {
    
    public static final String INDENT = "  ";

    /**
     * �R���X�g���N�^
     * �C���X�^���X���֎~
     */
    private ObjcUtil() {}
    
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
    public static String toDataTypeString(DataType dataType) {
        String ret = new String();
        switch (dataType) {
        case NULL:
            break;
        case INTEGER:
            ret = "int";
            break;
        case REAL:
            ret = "float";
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
        String ret = new String();
        ret += toDataTypeString(dataType);
        if (dataType != DataType.TEXT) {
            ret += " ";
        }
        ret += varName;
        return ret;
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
    
    /**
     * �^����ꂽ�f�[�^�^�ƕϐ�������v���p�e�B���쐬����B
     * @param dataType �f�[�^�^
     * @param varName �ϐ���
     * @return �쐬�����v���p�e�B
     */
    public static String createProperty(DataType dataType, String varName) {
        String ret = new String();
        ret += "@property (nonatomic, ";
        switch (dataType) {
        case NULL:
            break;
        case INTEGER:
        case REAL:
            ret += "assign) ";
            break;
        case TEXT:
            ret += "copy) ";
            break;
        case BLOB:
            break;
        default:
            break;
        }
        ret += createVariableDeclarationStatement(dataType, varName);
        return ret;
    }
    
    /**
     * �w�b�_�t�@�C�����쐬����B
     * @param copyright �R�s�[���C�g��
     * @param importSentences �C���|�[�g��
     * @param className �N���X��
     * @param superClassName �X�[�p�[�N���X��
     * @param variableDeclarations �N���X�����o�ϐ��錾
     * @param propertyDeclarations �v���p�e�B�錾
     * @param prototypeDeclarations �v���g�^�C�v�錾
     * @return
     */
    public static String createHeaderFile(String copyright, String importSentences,
            String className, String superClassName, String variableDeclarations,
            String propertyDeclarations, String prototypeDeclarations) {
        // HeaderFile �̏����쐬
        HeaderFile headerFile = new HeaderFile(copyright, importSentences, className,
                superClassName, variableDeclarations, propertyDeclarations, prototypeDeclarations);
                                        
        Velocity.setProperty("file.resource.loader.path",
                "/Users/keitanxkeitan/Eclipse Workspace/research/XML Converter/template");
        
        // Velocity �̏�����
        Velocity.init();
        
        // Velocity �̃R���e�L�X�g�ɒl��ݒ�
        VelocityContext context = new VelocityContext();
        context.put("headerFile", headerFile);
        
        StringWriter sw = new StringWriter();
        
        // �e���v���[�g�̍쐬
        Template template = Velocity.getTemplate("header_file.vm", "EUC-JP");
        
        // �e���v���[�g�ƃ}�[�W
        template.merge(context, sw);
        
        // �}�[�W�����f�[�^�� Writer �I�u�W�F�N�g�ł��� sw �������Ă���̂ł���𕶎���Ƃ��Ď擾
        String ret = sw.toString();
        sw.flush();
        return ret;
    }
    
    /**
     * �����t�@�C�����쐬����B
     * @param copyright �R�s�[���C�g��
     * @param importSentences �C���|�[�g��
     * @param className �N���X��
     * @param synthesizes �V���Z�T�C�Y
     * @param methods ���\�b�h
     * @return
     */
    public static String createImplementationFile(String copyright, String importSentences,
            String className, String synthesizes, String methods) {
        // ImplementationFile �̏����쐬
        ImplementationFile implementationFile = new ImplementationFile(copyright, importSentences,
                className, synthesizes, methods);
        
        Velocity.setProperty("file.resource.loader.path",
                "/Users/keitanxkeitan/Eclipse Workspace/research/XML Converter/template");

        // Velocity �̏�����
        Velocity.init();
        
        // Velocity �̃R���e�L�X�g�ɒl��ݒ�
        VelocityContext context = new VelocityContext();
        context.put("implementationFile", implementationFile);
        
        StringWriter sw = new StringWriter();
        
        // �e���v���[�g�̍쐬
        Template template = Velocity.getTemplate("implementation_file.vm", "EUC-JP");
        
        // �e���v���[�g�ƃ}�[�W
        template.merge(context, sw);
        
        // �}�[�W�����f�[�^�� Writer �I�u�W�F�N�g�ł��� sw �������Ă���̂ł���𕶎���Ƃ��Ď擾
        String ret = sw.toString();
        sw.flush();
        return ret;
    }
    
    /**
     * �^����ꂽ�ϐ�������V���Z�T�C�Y���쐬����B
     * @param varName �ϐ���
     * @return �V���Z�T�C�Y
     */
    public static String createSynthesize(String varName) {
        return "@synthesize " + varName + " = " + toClassMemberVariableName(varName) + ";";
    }
    
    /**
     * �^����ꂽ�f�[�^�^�ƕϐ�������ϐ���ێ����镶���쐬����B
     * @param dataType �f�[�^�^
     * @param varName �ϐ���
     * @return �쐬�����ϐ���ێ����镶
     */
    public static String createRetainSentence(DataType dataType, String varName) {
        String ret = new String();
        switch (dataType) {
        case NULL:
            break;
        case INTEGER:
        case REAL:
            ret += varName;
            break;
        case TEXT:
            ret += String.format("[%s copy]", varName);
            break;
        case BLOB:
            break;
        default:
            break;
        }
        return ret;
    }
    
    public static boolean doesNeedRelease(DataType dataType) {
        boolean ret = false;
        switch (dataType) {
        case NULL:
        case INTEGER:
        case REAL:
            ret = false;
            break;
        case TEXT:
        case BLOB:
            ret = true;
            break;
        default:
            break;
        }
        return ret;
    }
    
}
