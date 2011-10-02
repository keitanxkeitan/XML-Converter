package jp.ac.titech.keitanxkeitan.xmlconverter;

import java.util.List;

public class Database implements Element {

    String mName;
    List<Table> mTables;
    
    Database(String name, List<Table> tables) {
        mName = name;
        mTables = tables;
    }
    
    @Override
    public String encode() throws Exception {
        String code = "";
        
        for (Table table : mTables) {
            code += "CREATE TABLE " + table.mName + "(\n";
            code += "id INTEGER PRIMARY KEY,\n";
            code += table.encode();
            code += ");\n";
        }
        
        System.out.println(code);
        
        return code;
    }

}
