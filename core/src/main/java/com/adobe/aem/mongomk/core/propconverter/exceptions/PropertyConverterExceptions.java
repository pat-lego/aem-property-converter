package com.adobe.aem.mongomk.core.propconverter.exceptions;

public class PropertyConverterExceptions extends Exception {

    public PropertyConverterExceptions(String msg, Throwable t) {
        super(msg, t);
    }

    public PropertyConverterExceptions(String msg) {
        super(msg);
    }
    
}
