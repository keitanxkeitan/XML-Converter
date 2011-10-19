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
     * テーブルを作成する SQL コマンドを返す。
     * @return テーブルを作成する SQL コマンド
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
     * Data Transfer Object クラスの名前を返す。
     * @return Data Transfer Object クラスの名前
     */
    String getDtoClassName() {
        String dtoClassName = "Tb" + XmlConverterUtil.toUpperCamelCase(mName);
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
            String fileName = getDtoClassName() + ".h";
            File file = new File(fileName);
            FileWriter fw;
            fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            // TODO: ファイルの内容を作成する。
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
    }
}
