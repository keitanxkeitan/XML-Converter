package jp.ac.titech.keitanxkeitan.xmlconverter;

import java.util.List;

public class Application implements Element {

    String mName;
    List<Database> mDatabases;
    
    Application(String name, List<Database> databases) {
        mName = name;
        mDatabases = databases;
    }
    
    public void generate() throws Exception {
        for (Database database : mDatabases) {
            database.encode();
        }
    }
    
    @Override
    public String encode() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
