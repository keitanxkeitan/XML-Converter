package jp.ac.titech.keitanxkeitan.xmlconverter;

/**
 * @author keitanxkeitan
 * �\���؂̃m�[�h����������C���^�t�F�[�X�B
 */
public interface Element {
    /**
     * �G���R�[�h����B
     * @return �G���R�[�h��̃\�[�X�R�[�h
     * @throws Exception
     */
    String encode() throws Exception;
}
