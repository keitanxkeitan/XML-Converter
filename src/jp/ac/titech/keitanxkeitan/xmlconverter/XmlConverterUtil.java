package jp.ac.titech.keitanxkeitan.xmlconverter;

/**
 * XML Converter で扱うユーティリティクラス
 * @author keitanxkeitan
 *
 */
public class XmlConverterUtil {

    /**
     * コンストラクタ
     * インスタンス化禁止
     */
    private XmlConverterUtil() {}
    
    /**
     * 与えられた複合語の名前をアッパーキャメルケースに変換する。
     * スペース ' ' とアンダースコア '_' を要素語の区切りとして用いる。
     * 例えば "todo_list" という名前は "TodoList" に変換される。
     * @param word 変換する名前
     * @return 変換した名前
     */
    public static String toUpperCamelCase(String word) {
        // アンダースコア '_' をスペース ' ' に変換してから
        // スペース ' ' を区切り文字として分割する。
        word = word.replace('_', ' ');
        String[] elements = word.split(" ");
        
        word = new String();
        
        // 要素語の先頭の文字を大文字にする。
        for (String element : elements) {
            element = element.substring(0, 1).toUpperCase() + element.substring(1);
            word += element;
        }
        
        return word;
    }
    
}
