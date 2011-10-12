package jp.ac.titech.keitanxkeitan.xmlconverter;

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
    
    public void createDatabaseFile() throws ClassNotFoundException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");

        Connection connection = null;

        try {
            // create a database connection
            // �f�[�^�x�[�X�t�@�C���𖾋L����ꍇ
            // �i��j/home/leo/work/mydatabase.db
            // Connection connection = DriverManager.getConnection("jdbc:sqlite:/home/leo/work/mydatabase.db");
            // �ڍׂ� http://www.xerial.org/trac/Xerial/wiki/SQLiteJDBC ���Q��
            connection = DriverManager.getConnection("jdbc:sqlite:gen/" + mName + ".db");
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
