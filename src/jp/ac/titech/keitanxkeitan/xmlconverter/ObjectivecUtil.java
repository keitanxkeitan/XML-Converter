package jp.ac.titech.keitanxkeitan.xmlconverter;

/**
 * Objective-C 関連のユーティリティクラス
 * @author keitanxkeitan
 *
 */
public class ObjectivecUtil {
    
    public static final String INDENT = "  ";

    /**
     * コンストラクタ
     * インスタンス化禁止
     */
    private ObjectivecUtil() {}
    
    /**
     * インポート文を作成する。
     * @param fileName ヘッダファイルのパス
     * @param isPublic 公開ヘッダファイルかどうか
     * @return 作成したインポート文
     */
    public static String createImportSentence(String fileName, boolean isPublic) {
        String ret = new String();
        if (isPublic) {
            ret = "#import <" + fileName + ">";
        } else {
            ret = "#import \"" + fileName + "\"";
        }
        return ret;
    }
    
    /**
     * 与えられた DataType 型のデータ型を
     * Objective-C の対応するデータ型に変換する。
     * @param dataType 変換するデータ型
     * @return 変換したデータ型
     */
    public static String toDataTypeStringWithSpace(DataType dataType) {
        String ret = new String();
        switch (dataType) {
        case NULL:
            break;
        case INTEGER:
            ret = "int ";
            break;
        case REAL:
            ret = "float ";
            break;
        case TEXT:
            ret = "NSString *";
            break;
        case BLOB:
            break;
        }
        return ret;
    }
    
    /**
     * 与えられたデータ型と変数名から変数宣言（データ型と変数名の組）を作成する。
     * @param dataType データ型
     * @param varName 変数名
     * @return 変数宣言
     */
    public static String createVariableDeclaration(DataType dataType, String varName) {
        return toDataTypeStringWithSpace(dataType) + varName;
    }
    
    /**
     * 与えられたデータ型と変数名から変数宣言（データ型と変数名の組）を作成する。
     * 作成された変数宣言はセミコロン ';' で終端する。
     * @param dataType データ型
     * @param varName 変数名
     * @return 変数宣言
     */
    public static String createVariableDeclarationStatement(DataType dataType, String varName) {
        return createVariableDeclaration(dataType, varName) + ";";
    }
    
    /**
     * 与えられた複合語をクラスメンバ変数名に変換する。
     * @param word 変換する複合語
     * @return 変換したクラスメンバ変数名
     */
    public static String toClassMemberVariableName(String word) {
        return CommonUtil.toLowerCamelCase(word) + "_";
    }
    
}
