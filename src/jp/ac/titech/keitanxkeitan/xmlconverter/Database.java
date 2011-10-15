package jp.ac.titech.keitanxkeitan.xmlconverter;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Database implements Element {

    String mName;
    List<Table> mTables;
    
    Database(String name, List<Table> tables) {
        mName = name;
        mTables = tables;
    }
    
    /**
     * データベースファイルを作成する。
     * @param appName アプリケーションの名前
     * @throws ClassNotFoundException
     */
    public void createDatabaseFile(String appName) throws ClassNotFoundException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");

        Connection connection = null;

        try {
            String dirPath = String.format("gen/%s/database", appName);
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String dbPath = String.format("%s/%s.db", dirPath, mName);
            // create a database connection
            // データベースファイルを明記する場合
            // （例）/home/leo/work/mydatabase.db
            // Connection connection = DriverManager.getConnection("jdbc:sqlite:/home/leo/work/mydatabase.db");
            // 詳細は http://www.xerial.org/trac/Xerial/wiki/SQLiteJDBC を参照
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            for (Table table : mTables) {
                String sql = table.getSql();
                statement.executeUpdate(sql);    
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }

}
