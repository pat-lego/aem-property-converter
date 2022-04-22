package com.adobe.aem.mongomk.core.propconverter.jmx;

import com.adobe.aem.mongomk.core.propconverter.exceptions.PropertyConverterExceptions;
import com.adobe.granite.jmx.annotation.Description;
import com.adobe.granite.jmx.annotation.Name;

public interface PropertyInfoMBean {
    
    @Description("Convert property from String to Binary")
    void convertStringToBinary(@Name("JCR Path") String path, @Name("JCR Property Name") String propName) throws PropertyConverterExceptions;
}
