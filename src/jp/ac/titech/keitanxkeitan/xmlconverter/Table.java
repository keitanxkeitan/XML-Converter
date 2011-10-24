package jp.ac.titech.keitanxkeitan.xmlconverter;

import java.io.File;
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
            String dirPath = String.format("gen/%s/objc", appName);
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = getDtoClassName() + ".h";
            String filePath = String.format("%s/%s", dirPath, fileName);
            PrintWriter pw = CommonUtil.getPrintBufferedFileWriter(filePath);
            
            // �t�@�C���̓��e���쐬����
            
            // �R�s�[���C�g�����쐬����
            String copyright = CommonUtil.createCopyright(fileName, appName, user, organization);
            
            // �C���|�[�g�����쐬����
            String importSentences = ObjcUtil.createImportSentence("Foundation/Foundation.h", true);
            
            // �C���^�t�F�[�X�錾���쐬����
            String className = "Tb" + CommonUtil.toUpperCamelCase(mName);
            String superClassName = "NSObject";
            
            // �N���X�����o�ϐ��錾���쐬����
            String variableDeclarations = new String();
            for (Column column : mColumns) {
                variableDeclarations += ObjcUtil.INDENT +
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

            String headerFile = ObjcUtil.createHeaderFile(copyright, importSentences, className,
                    superClassName, variableDeclarations, propertyDeclarations,
                    prototypeDeclarations);
            pw.println(headerFile);
            
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Data Transfer Object �N���X�̎����t�@�C�����쐬����B
     * @param appName �A�v���P�[�V�����̖��O
     * @param user �A�v���P�[�V�����̊J���҂̖��O
     * @param organization �A�v���P�[�V�����̊J���҂���������g�D�̖��O
     */
    void createDtoClassImplementationFile(String appName, String user, String organization) {
        try {
            String dirPath = String.format("gen/%s/objc", appName);
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            String fileName = getDtoClassName() + ".m";
            String filePath = String.format("%s/%s", dirPath, fileName);
            PrintWriter pw = CommonUtil.getPrintBufferedFileWriter(filePath);
            
            // �t�@�C���̓��e���쐬����
            
            // �R�s�[���C�g�����쐬����
            String copyright = CommonUtil.createCopyright(fileName, appName, user, organization);

            // �N���X�����쐬����
            String className = "Tb" + CommonUtil.toUpperCamelCase(mName);
            
            // �C���|�[�g�����쐬����
            String importSentences = ObjcUtil.createImportSentence(className + ".h", false);
            
            // �V���Z�T�C�Y���쐬����
            String synthesizes = new String();
            for (Column column : mColumns) {
                synthesizes += column.getSynthesize() + "\n";
            }
            synthesizes = synthesizes.substring(0, synthesizes.length() - 1);
            
            // ���\�b�h���쐬����
            String methods = new String();
            
            // �C�j�V�����C�U���쐬����
            methods += "- (id)initWith";
            boolean isFirst = true;
            for (Column column : mColumns) {
                if (isFirst) {
                    isFirst = false;
                    methods += CommonUtil.toUpperCamelCase(column.getArgument());
                } else {
                    methods += " " + column.getArgument();
                }
            }
            methods += "{\n";
            methods += ObjcUtil.INDENT + "self = [super init];\n";
            methods += ObjcUtil.INDENT + "if (self) {\n";
            
            for (Column column : mColumns) {
                methods += ObjcUtil.INDENT + ObjcUtil.INDENT + column.getInitialize() + "\n";
            }
            
            methods += ObjcUtil.INDENT + "}\n";
            methods += ObjcUtil.INDENT + "return self;\n";
            methods += "}\n";
            methods += "\n";
            
            // dealloc ���쐬����
            String releases = new String();
            for (Column column : mColumns) {
                String release = column.getRelease();
                if (release.length() > 0) {
                    releases += ObjcUtil.INDENT + release + ";\n";
                }
            }
            if (releases.length() > 0) {
                methods += "- (void)dealloc {\n";
                methods += releases;
                methods += ObjcUtil.INDENT + "[super dealloc];\n";
                methods += "}";
            }
            
            String implementationFile = ObjcUtil.createImplementationFile(copyright,
                    importSentences, className, synthesizes, methods);
            pw.println(implementationFile);
            
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
        createDtoClassImplementationFile(appName, user, organization);
    }
}
