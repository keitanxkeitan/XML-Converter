package jp.ac.titech.keitanxkeitan.xmlconverter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
        return sql;         
    }
    
    /**
     * Data Transfer Object �N���X�̖��O��Ԃ��B
     * @return Data Transfer Object �N���X�̖��O
     */
    String getDtoClassName() {
        String dtoClassName = "Tb" + CommonUtil.toUpperCamelCase(mName);
        return dtoClassName;
    }
    
    /**
     * Data Transfer Object �N���X�̃w�b�_�t�@�C�����쐬����B
     * @param appName �A�v���P�[�V�����̖��O
     * @param user �A�v���P�[�V�����̊J���҂̖��O
     * @param organization �A�v���P�[�V�����̊J���҂���������g�D�̖��O
     */
    void createDtoClassHeaderFile(String appName, String user, String organization) {
        try {
            String fileName = getDtoClassName() + ".h";
            File file = new File(fileName);
            FileWriter fw;
            fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            // �t�@�C���̓��e���쐬����
            
            // �R�s�[���C�g�����쐬����
            String copyright = CommonUtil.createCopyright(fileName, appName, user, organization);
            
            // �C���^�t�F�[�X�錾���쐬����
            String className = "Tb" + CommonUtil.toUpperCamelCase(mName);
            String superClassName = "NSObject";
            
            // �N���X�����o�ϐ��錾���쐬����
            String variableDeclarations = new String();
            for (Column column : mColumns) {
                variableDeclarations += ObjectivecUtil.INDENT +
                        column.getObjcClassMemberVariableDeclaration() + "\n";
            }
            variableDeclarations =
                    variableDeclarations.substring(0, variableDeclarations.length() - 1);
                        
            // �v���p�e�B�錾���쐬����
            String propertyDeclarations = new String();
            for (Column column : mColumns) {
                propertyDeclarations += column.getProperty() + "\n";
            }
            propertyDeclarations =
                    propertyDeclarations.substring(0, propertyDeclarations.length() - 1);
            
            // �C�j�V�����C�U�̃v���g�^�C�v�錾���쐬����
            String prototypeDeclarations = new String();
            prototypeDeclarations += "- (id)initWith";
            boolean isFirst = true;
            for (Column column : mColumns) {
                if (isFirst) {
                    isFirst = false;
                    prototypeDeclarations += CommonUtil.toUpperCamelCase(column.getArgument());
                } else {
                    prototypeDeclarations += " " + column.getArgument();
                }
            }

            String headerFile = ObjectivecUtil.createHeaderFile(copyright, className,
                    superClassName, variableDeclarations, propertyDeclarations,
                    prototypeDeclarations);
            pw.println(headerFile);
            
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * �\�[�X�t�@�C�����쐬����B
     * @param appName �A�v���P�[�V�����̖��O
     * @param user �A�v���P�[�V�����̊J���҂̖��O
     * @param organization �A�v���P�[�V�����̊J���҂���������g�D�̖��O
     */
    void createSourceFile(String appName, String user, String organization) {
        createDtoClassHeaderFile(appName, user, organization);
    }
}
