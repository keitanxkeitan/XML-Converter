package jp.ac.titech.keitanxkeitan.xmlconverter;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

/**
 * Objective-C 関連のユーティリティクラス
 * @author keitanxkeitan
 *
 */
public class ObjcUtil {
    
    public static final String INDENT = "  ";

    /**
     * コンストラクタ
     * インスタンス化禁止
     */
    private ObjcUtil() {}
    
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
    public static String toDataTypeString(DataType dataType) {
        String ret = new String();
        switch (dataType) {
        case NULL:
            break;
        case INTEGER:
            ret = "int";
            break;
        case REAL:
            ret = "float";
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
        String ret = new String();
        ret += toDataTypeString(dataType);
        if (dataType != DataType.TEXT) {
            ret += " ";
        }
        ret += varName;
        return ret;
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
    
    /**
     * 与えられたデータ型と変数名からプロパティを作成する。
     * @param dataType データ型
     * @param varName 変数名
     * @return 作成したプロパティ
     */
    public static String createProperty(DataType dataType, String varName) {
        String ret = new String();
        ret += "@property (nonatomic, ";
        switch (dataType) {
        case NULL:
            break;
        case INTEGER:
        case REAL:
            ret += "assign) ";
            break;
        case TEXT:
            ret += "copy) ";
            break;
        case BLOB:
            break;
        default:
            break;
        }
        ret += createVariableDeclarationStatement(dataType, varName);
        return ret;
    }
    
    /**
     * ヘッダファイルを作成する。
     * @param copyright コピーライト文
     * @param importSentences インポート文
     * @param className クラス名
     * @param superClassName スーパークラス名
     * @param variableDeclarations クラスメンバ変数宣言
     * @param propertyDeclarations プロパティ宣言
     * @param prototypeDeclarations プロトタイプ宣言
     * @return
     */
    public static String createHeaderFile(String copyright, String importSentences,
            String className, String superClassName, String variableDeclarations,
            String propertyDeclarations, String prototypeDeclarations) {
        // HeaderFile の情報を作成
        HeaderFile headerFile = new HeaderFile(copyright, importSentences, className,
                superClassName, variableDeclarations, propertyDeclarations, prototypeDeclarations);
                                        
        Velocity.setProperty("file.resource.loader.path",
                "/Users/keitanxkeitan/Eclipse Workspace/research/XML Converter/template");
        
        // Velocity の初期化
        Velocity.init();
        
        // Velocity のコンテキストに値を設定
        VelocityContext context = new VelocityContext();
        context.put("headerFile", headerFile);
        
        StringWriter sw = new StringWriter();
        
        // テンプレートの作成
        Template template = Velocity.getTemplate("header_file.vm", "EUC-JP");
        
        // テンプレートとマージ
        template.merge(context, sw);
        
        // マージしたデータは Writer オブジェクトである sw が持っているのでそれを文字列として取得
        String ret = sw.toString();
        sw.flush();
        return ret;
    }
    
    /**
     * 実装ファイルを作成する。
     * @param copyright コピーライト文
     * @param importSentences インポート文
     * @param className クラス名
     * @param synthesizes シンセサイズ
     * @param methods メソッド
     * @return
     */
    public static String createImplementationFile(String copyright, String importSentences,
            String className, String synthesizes, String methods) {
        // ImplementationFile の情報を作成
        ImplementationFile implementationFile = new ImplementationFile(copyright, importSentences,
                className, synthesizes, methods);
        
        Velocity.setProperty("file.resource.loader.path",
                "/Users/keitanxkeitan/Eclipse Workspace/research/XML Converter/template");

        // Velocity の初期化
        Velocity.init();
        
        // Velocity のコンテキストに値を設定
        VelocityContext context = new VelocityContext();
        context.put("implementationFile", implementationFile);
        
        StringWriter sw = new StringWriter();
        
        // テンプレートの作成
        Template template = Velocity.getTemplate("implementation_file.vm", "EUC-JP");
        
        // テンプレートとマージ
        template.merge(context, sw);
        
        // マージしたデータは Writer オブジェクトである sw が持っているのでそれを文字列として取得
        String ret = sw.toString();
        sw.flush();
        return ret;
    }
    
    /**
     * 与えられた変数名からシンセサイズを作成する。
     * @param varName 変数名
     * @return シンセサイズ
     */
    public static String createSynthesize(String varName) {
        return "@synthesize " + varName + " = " + toClassMemberVariableName(varName) + ";";
    }
    
    /**
     * 与えられたデータ型と変数名から変数を保持する文を作成する。
     * @param dataType データ型
     * @param varName 変数名
     * @return 作成した変数を保持する文
     */
    public static String createRetainSentence(DataType dataType, String varName) {
        String ret = new String();
        switch (dataType) {
        case NULL:
            break;
        case INTEGER:
        case REAL:
            ret += varName;
            break;
        case TEXT:
            ret += String.format("[%s copy]", varName);
            break;
        case BLOB:
            break;
        default:
            break;
        }
        return ret;
    }
    
    public static boolean doesNeedRelease(DataType dataType) {
        boolean ret = false;
        switch (dataType) {
        case NULL:
        case INTEGER:
        case REAL:
            ret = false;
            break;
        case TEXT:
        case BLOB:
            ret = true;
            break;
        default:
            break;
        }
        return ret;
    }
    
}
