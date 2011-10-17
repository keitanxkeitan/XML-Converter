package jp.ac.titech.keitanxkeitan.xmlconverter;

import java.util.List;

public class Table implements Element {
    
    String mName;
    List<Column> mColumns;

    Table(String name, List<Column> columns) {
        mName = name;
        mColumns = columns;
    }
    
    /**
     * �e�[�u�����쐬���� SQL �R�}���h��Ԃ��B
     * @return �e�[�u�����쐬���� SQL �R�}���h
     */
    public String getSql() {
        String sql = "CREATE TABLE " + mName + "(\n";
        for (Column column : mColumns) {
            sql += column.getSql() + ",\n";
        }
        sql = sql.substring(0, sql.length() - ",\n".length()) + "\n);";
        System.out.println(sql);
        return sql;         
    }
    
    /**
     * �\�[�X�t�@�C�����쐬����B
     * @param appName �A�v���P�[�V�����̖��O
     * @param user �A�v���P�[�V�����̊J���҂̖��O
     * @param organization �A�v���P�[�V�����̊J���҂���������g�D�̖��O
     */
    void createSourceFile(String appName, String user, String organization) {
        
    }
}
