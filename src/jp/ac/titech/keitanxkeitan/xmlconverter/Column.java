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

    @Override
    public String encode() throws Exception {
        String code = mName + " " + mDataType;
        if (mIsPrimaryKey) {
            code += " PRIMARY KEY";
        }
        if (mIsAutoincrement) {
            code += " AUTOINCREMENT";
        }
        if (mIsNotNull) {
            code += " NOT NULL";
        }
        if (mIsUnique) {
            code += " UNIQUE";
        }
        return code;
    }

}
