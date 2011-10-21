package jp.ac.titech.keitanxkeitan.xmlconverter;

/**
 * Objective-C 関連のユーティリティクラス
 * @author keitanxkeitan
 *
 */
public class ObjectivecUtil {

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
     * Objective-C で対応するデータ型に変換する。
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
    
}
