package jp.ac.titech.keitanxkeitan.xmlconverter;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

/**
 * XML Converter で共通して扱うユーティリティクラス
 * @author keitanxkeitan
 *
 */
public class CommonUtil {

    /**
     * コンストラクタ
     * インスタンス化禁止
     */
    private CommonUtil() {}
    
    /**
     * 与えられた複合語をアッパーキャメルケースに変換する。
     * スペース ' ' とアンダースコア '_' を要素語の区切りとして用いる。
     * 例えば "todo_list" という名前は "TodoList" に変換される。
     * @param word 変換する複合語
     * @return 変換した複合語
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

    /**
     * 与えられた複合語をローワーキャメルケースに変換する。
     * スペース ' ' とアンダースコア '_' を要素語の区切りとして用いる。
     * 例えば "todo_list" という名前は "todoList" に変換される。
     * @param word 変換する複合語
     * @return 変換した複合語
     */
    public static String toLowerCamelCase(String word) {
        word = toUpperCamelCase(word);
        
        word = word.substring(0, 1).toLowerCase() + word.substring(1);
        
        return word;
    }
    
    /**
     * コピーライト文を作成する。
     * @param fileName ファイルの名前
     * @param appName アプリケーションの名前
     * @param user アプリケーションの開発者の名前
     * @param organization アプリケーションの開発者が所属する組織の名前
     * @return
     */
    public static String createCopyright(String fileName, String appName, String user,
            String organization) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yy/MM/dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
        String date = sdf1.format(new Date());
        String year = sdf2.format(new Date());
                
        // Copyright の情報を作成
        Copyright copyright = new Copyright(fileName, appName, user, date, year, organization);
        
        // Velocity の初期化
        Velocity.init();
        
        // Velocity コンテキストに値を設定
        VelocityContext context = new VelocityContext();
        context.put("copyright", copyright);
        
        StringWriter sw = new StringWriter();
        
        // テンプレートの作成
        Template template = Velocity.getTemplate("template/copyright.vm", "EUC-JP");
        
        // テンプレートとマージ
        template.merge(context, sw);
        
        // マージしたデータは Writer オブジェクトである sw が持っているのでそれを文字列として取得
        String ret = sw.toString();
        sw.flush();
        return ret;
    }
    
}
