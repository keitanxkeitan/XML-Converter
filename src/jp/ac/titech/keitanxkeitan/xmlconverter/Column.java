package jp.ac.titech.keitanxkeitan.xmlconverter;

public class Column implements Element {

    String mName;
    DataType mDataType;
    boolean mIsPrimaryKey;
    boolean mIsAutoincrement;
    boolean mIsNotNull;
    boolean mIsUnique;
    
    Column(String name, String dataType, String isPrimaryKey, String isAutoincrement,
            String isNotNull, String isUnique, String default_, String check) {
        mName = name;
        mDataType = DataType.valueOf(dataType);
        mIsPrimaryKey = isPrimaryKey.equals("true") ? true : false;
        mIsAutoincrement = isAutoincrement.equals("true") ? true : false;
        mIsNotNull = isNotNull.equals("true") ? true : false;
        mIsUnique = isUnique.equals("true") ? true : false;
        // TODO: DEFAULT����ACHECK����ɑΉ�����B
    }
    
    /**
     * �J�������`���� SQL �R�}���h��Ԃ��B
     * @return �J�������`���� SQL �R�}���h
     */
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
    
    public String getObjcClassMemberVariableDeclaration() {
        String ret = new String();
        ret += ObjectivecUtil.createVariableDeclarationStatement(mDataType,
                ObjectivecUtil.toClassMemberVariableName(mName));
        return ret;
    }

}
