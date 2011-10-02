package jp.ac.titech.keitanxkeitan.xmlconverter;

/**
 * @author keitanxkeitan
 * 構文木のノードが実装するインタフェース。
 */
public interface Element {
    /**
     * エンコードする。
     * @return エンコード後のソースコード
     * @throws Exception
     */
    String encode() throws Exception;
}
