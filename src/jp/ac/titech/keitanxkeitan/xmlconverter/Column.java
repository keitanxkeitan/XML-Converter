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
        // TODO: DEFAULT制約、CHECK制約に対応する。
    }
    
    /**
     * カラムを定義する SQL コマンドを返す。
     * @return カラムを定義する SQL コマンド
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
    
    /**
     * クラスメンバ変数宣言を得る。
     * @return クラスメンバ変数宣言
     */
    public String getObjcClassMemberVariableDeclaration() {
        String ret = new String();
        ret += ObjcUtil.createVariableDeclarationStatement(mDataType,
                ObjcUtil.toClassMemberVariableName(mName));
        return ret;
    }
    
    /**
     * プロパティを得る。
     * @return プロパティ
     */
    public String getProperty() {
        String ret = new String();
        ret += ObjcUtil.createProperty(mDataType,
                CommonUtil.toLowerCamelCase(mName));
        return ret;
    }
    
    /**
     * 引数を得る。
     * @return 引数
     */
    public String getArgument() {
        String ret = new String();
        ret += CommonUtil.toLowerCamelCase(mName) + ":(" +
                ObjcUtil.toDataTypeString(mDataType) + ")" +
                CommonUtil.toLowerCamelCase(mName);
        return ret;
    }
    
    /**
     * シンセサイズを得る。
     * @return シンセサイズ
     */
    public String getSynthesize() {
        String ret = new String();
        ret += ObjcUtil.createSynthesize(CommonUtil.toLowerCamelCase(mName));
        return ret;
    }
    
    /**
     * イニシャライズを得る。
     * @return イニシャライズ
     */
    public String getInitialize() {
        String ret = new String();
        ret += CommonUtil.toLowerCamelCase(mName) + " = "
                + ObjcUtil.createRetainSentence(mDataType, mName) + ";";
        return ret;
    }
    
    /**
     * リリースを得る。
     * @return リリース
     */
    public String getRelease() {
        String ret = new String();
        if (ObjcUtil.doesNeedRelease(mDataType)) {
            ret += "[" + ObjcUtil.toClassMemberVariableName(mName) + " release]";
        }
        return ret;
    }
    
}
