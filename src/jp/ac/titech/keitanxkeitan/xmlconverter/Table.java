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
        String code = "";
        for (Column column : mColumns) {
            code += column.encode() + ",\n";
        }
        return code;
    }

}
