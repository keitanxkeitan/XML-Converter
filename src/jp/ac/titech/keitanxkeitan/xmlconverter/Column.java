package jp.ac.titech.keitanxkeitan.xmlconverter;

public class Column implements Element {

    String mName;
    String mDataType;
    
    Column(String name, String dataType) {
        mName = name;
        mDataType = dataType;
    }
    
    @Override
    public String encode() throws Exception {
        return mName + " " + mDataType;
    }

}
