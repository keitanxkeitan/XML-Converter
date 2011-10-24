package jp.ac.titech.keitanxkeitan.xmlconverter;

public class ImplementationFile {

    String mCopyright;
    String mImportSentences;
    String mClassName;
    String mSynthesizes;
    String mMethods;
    
    public ImplementationFile() {}
    public ImplementationFile(String copyright, String importSentences, String className,
            String synthesizes, String methods) {
        mCopyright = copyright;
        mImportSentences = importSentences;
        mClassName = className;
        mSynthesizes = synthesizes;
        mMethods = methods;
    }
    
    public String getCopyright() {
        return mCopyright;
    }
    
    public String getImportSentences() {
        return mImportSentences;
    }
    
    public String getClassName() {
        return mClassName;
    }
    
    public String getSynthesizes() {
        return mSynthesizes;
    }
    
    public String getMethods() {
        return mMethods;
    }
    
}
