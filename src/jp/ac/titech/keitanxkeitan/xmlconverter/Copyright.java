package jp.ac.titech.keitanxkeitan.xmlconverter;

public class Copyright {

    String mFileName;
    String mAppName;
    String mUser;
    String mDate;
    String mYear;
    String mOrganization;
    
    public Copyright() {}
    public Copyright(String fileName, String appName, String user, String date, String year,
            String organization) {
        mFileName = fileName;
        mAppName = appName;
        mUser = user;
        mDate = date;
        mYear = year;
        mOrganization = organization;
    }
    
    public String getFileName() {
        return mFileName;
    }
    
    public String getAppName() {
        return mAppName;
    }
    
    public String getUser() {
        return mUser;
    }
    
    public String getDate() {
        return mDate;
    }
    
    public String getYear() {
        return mYear;
    }
    
    public String getOrganization() {
        return mOrganization;
    }
    
}
