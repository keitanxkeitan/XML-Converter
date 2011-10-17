package jp.ac.titech.keitanxkeitan.xmlconverter;

import java.util.List;

public class Application implements Element {

    String mName;
    String mUser;
    String mOrganization;
    List<Database> mDatabases;
    
    Application(String name, String user, String organization, List<Database> databases) {
        mName = name;
        mUser = user;
        mOrganization = organization;
        mDatabases = databases;
    }
    
    public void generate() throws ClassNotFoundException {
        for (Database database : mDatabases) {
            database.createDatabaseFile(mName);
        }
    }

}
