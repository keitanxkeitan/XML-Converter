package jp.ac.titech.keitanxkeitan.xmlconverter;

import java.util.HashMap;
import java.util.Map;

public abstract class HandlerFactory {

    static Map<String, HandlerFactory> sHandlers = new HashMap<String, HandlerFactory>();
    
    static {
        sHandlers.put("application", new HandlerFactory() {
            public ElementHandler create() {
                return new ApplicationHandler();
            }
        });
        sHandlers.put("database", new HandlerFactory() {
            public ElementHandler create() {
                return new DatabaseHandler();
            }
        });
        sHandlers.put("table", new HandlerFactory() {
            public ElementHandler create() {
                return new TableHandler();
            }
        });
        sHandlers.put("column", new HandlerFactory() {
            public ElementHandler create() {
                return new ColumnHandler();
            }
        });
    }
    
    public abstract ElementHandler create();
    
    public static HandlerFactory getInstance(String elementName) {
        return sHandlers.get(elementName);
    }
    
    protected static void addHandlerFactory(String elemName, HandlerFactory fac) {
        sHandlers.put(elemName, fac);
    }
    
}
