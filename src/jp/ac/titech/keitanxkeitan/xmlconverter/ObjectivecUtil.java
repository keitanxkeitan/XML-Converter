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
     * @return
     */
    public static String createImportSentence(String fileName, boolean isPublic) {
        String importSentence = new String();
        if (isPublic) {
            importSentence = "#import <" + fileName + ">";
        } else {
            importSentence = "#import \"" + fileName + "\"";
        }
        return importSentence;
    }
    
}
