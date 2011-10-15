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

}
