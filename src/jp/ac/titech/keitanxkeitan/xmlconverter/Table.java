package jp.ac.titech.keitanxkeitan.xmlconverter;

import java.util.List;

public class Table implements Element {
    
    String mName;
    List<Column> mColumns;

    Table(String name, List<Column> columns) {
        mName = name;
        mColumns = columns;
    }
    
    @Override
    public String encode() throws Exception {
        String code = "CREATE TABLE " + mName + "(\n";
        for (Column column : mColumns) {
            code += column.encode() + ",\n";
        }
        code = code.substring(0, code.length() - ",\n".length()) + "\n);";
        System.out.println(code);
        return code;
    }

}
