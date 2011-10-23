package jp.ac.titech.keitanxkeitan.xmlconverter;

public class HeaderFile {
    
    String mCopyright;
    String mClassName;
    String mSuperClassName;
    String mVariableDeclarations;
    String mPropertyDeclarations;
    String mPrototypeDeclarations;
    
    public HeaderFile() {}
    public HeaderFile(String copyright, String className, String superClassName,
            String variableDeclarations, String propertyDeclarations,
            String prototypeDeclarations) {
        mCopyright = copyright;
        mClassName = className;
        mSuperClassName = superClassName;
        mVariableDeclarations = variableDeclarations;
        mPropertyDeclarations = propertyDeclarations;
        mPrototypeDeclarations = prototypeDeclarations;
    }
    
    public String getCopyright() {
        return mCopyright;
    }
    
    public String getClassName() {
        return mClassName;
    }
    
    public String getSuperClassName() {
        return mSuperClassName;
    }
    
    public String getVariableDeclarations() {
        return mVariableDeclarations;
    }
    
    public String getPropertyDeclarations() {
        return mPropertyDeclarations;
    }
    
    public String getPrototypeDeclarations() {
        return mPrototypeDeclarations;
    }
    
}
