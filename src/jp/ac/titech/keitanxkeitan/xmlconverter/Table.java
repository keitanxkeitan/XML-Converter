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
     * テーブルを作成する SQL コマンドを返す。
     * @return テーブルを作成する SQL コマンド
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
     * Data Transfer Object クラスの名前を返す。
     * @return Data Transfer Object クラスの名前
     */
    String getDtoClassName() {
        String dtoClassName = "Tb" + CommonUtil.toUpperCamelCase(mName);
        return dtoClassName;
    }
        
    /**
     * Data Transfer Object クラスのヘッダファイルを作成する。
     * @param appName アプリケーションの名前
     * @param user アプリケーションの開発者の名前
     * @param organization アプリケーションの開発者が所属する組織の名前
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
            
            // ファイルの内容を作成する
            
            // コピーライト文を作成する
            String copyright = CommonUtil.createCopyright(fileName, appName, user, organization);
            
            // インポート文を作成する
            String importSentences = ObjcUtil.createImportSentence("Foundation/Foundation.h", true);
            
            // インタフェース宣言を作成する
            String className = "Tb" + CommonUtil.toUpperCamelCase(mName);
            String superClassName = "NSObject";
            
            // クラスメンバ変数宣言を作成する
            String variableDeclarations = new String();
            for (Column column : mColumns) {
                variableDeclarations += ObjcUtil.INDENT +
                        column.getObjcClassMemberVariableDeclaration() + "\n";
            }
            variableDeclarations =
                    variableDeclarations.substring(0, variableDeclarations.length() - 1);
                        
            // プロパティ宣言を作成する
            String propertyDeclarations = new String();
            for (Column column : mColumns) {
                propertyDeclarations += column.getProperty() + "\n";
            }
            propertyDeclarations =
                    propertyDeclarations.substring(0, propertyDeclarations.length() - 1);
            
            // イニシャライザのプロトタイプ宣言を作成する
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
     * Data Transfer Object クラスの実装ファイルを作成する。
     * @param appName アプリケーションの名前
     * @param user アプリケーションの開発者の名前
     * @param organization アプリケーションの開発者が所属する組織の名前
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
            
            // ファイルの内容を作成する
            
            // コピーライト文を作成する
            String copyright = CommonUtil.createCopyright(fileName, appName, user, organization);

            // クラス名を作成する
            String className = "Tb" + CommonUtil.toUpperCamelCase(mName);
            
            // インポート文を作成する
            String importSentences = ObjcUtil.createImportSentence(className + ".h", false);
            
            // シンセサイズを作成する
            String synthesizes = new String();
            for (Column column : mColumns) {
                synthesizes += column.getSynthesize() + "\n";
            }
            synthesizes = synthesizes.substring(0, synthesizes.length() - 1);
            
            // メソッドを作成する
            String methods = new String();
            
            // イニシャライザを作成する
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
            
            // dealloc を作成する
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
     * ソースファイルを作成する。
     * @param appName アプリケーションの名前
     * @param user アプリケーションの開発者の名前
     * @param organization アプリケーションの開発者が所属する組織の名前
     */
    void createSourceFile(String appName, String user, String organization) {
        createDtoClassHeaderFile(appName, user, organization);
        createDtoClassImplementationFile(appName, user, organization);
    }
}
