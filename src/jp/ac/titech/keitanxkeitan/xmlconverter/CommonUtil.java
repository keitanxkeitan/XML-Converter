package jp.ac.titech.keitanxkeitan.xmlconverter;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

/**
 * XML Converter �ŋ��ʂ��Ĉ������[�e�B���e�B�N���X
 * @author keitanxkeitan
 *
 */
public class CommonUtil {

    /**
     * �R���X�g���N�^
     * �C���X�^���X���֎~
     */
    private CommonUtil() {}
    
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
    
    /**
     * �R�s�[���C�g�����쐬����B
     * @param fileName �t�@�C���̖��O
     * @param appName �A�v���P�[�V�����̖��O
     * @param user �A�v���P�[�V�����̊J���҂̖��O
     * @param organization �A�v���P�[�V�����̊J���҂���������g�D�̖��O
     * @return
     */
    public static String createCopyright(String fileName, String appName, String user,
            String organization) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yy/MM/dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
        String date = sdf1.format(new Date());
        String year = sdf2.format(new Date());
                
        // Copyright �̏����쐬
        Copyright copyright = new Copyright(fileName, appName, user, date, year, organization);
        
        // Velocity �̏�����
        Velocity.init();
        
        // Velocity �R���e�L�X�g�ɒl��ݒ�
        VelocityContext context = new VelocityContext();
        context.put("copyright", copyright);
        
        StringWriter sw = new StringWriter();
        
        // �e���v���[�g�̍쐬
        Template template = Velocity.getTemplate("template/copyright.vm", "EUC-JP");
        
        // �e���v���[�g�ƃ}�[�W
        template.merge(context, sw);
        
        // �}�[�W�����f�[�^�� Writer �I�u�W�F�N�g�ł��� sw �������Ă���̂ł���𕶎���Ƃ��Ď擾
        String ret = sw.toString();
        sw.flush();
        return ret;
    }
    
}
