package jp.ac.titech.keitanxkeitan.xmlconverter;

public class Column implements Element {

    String mName;
    String mDataType;
    boolean mIsPrimaryKey;
    boolean mIsAutoincrement;
    boolean mIsNotNull;
    boolean mIsUnique;
    
    Column(String name, String dataType, String isPrimaryKey, String isAutoincrement,
            String isNotNull, String isUnique, String default_, String check) {
        mName = name;
        mDataType = dataType;
        mIsPrimaryKey = isPrimaryKey.equals("true") ? true : false;
        mIsAutoincrement = isAutoincrement.equals("true") ? true : false;
        mIsNotNull = isNotNull.equals("true") ? true : false;
        mIsUnique = isUnique.equals("true") ? true : false;
        // TODO: DEFAULTêßñÒÅACHECKêßñÒÇ…ëŒâûÇ∑ÇÈÅB
    }
    
    public String getSql() {
        String sql = mName + " " + mDataType;
        if (mIsPrimaryKey) {
            sql += " PRIMARY KEY";
        }
        if (mIsAutoincrement) {
            sql += " AUTOINCREMENT";
        }
        if (mIsNotNull) {
            sql += " NOT NULL";
        }
        if (mIsUnique) {
            sql += " UNIQUE";
        }
        return sql;
    }

}
