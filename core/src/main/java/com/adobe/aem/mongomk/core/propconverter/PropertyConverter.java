package com.adobe.aem.mongomk.core.propconverter;

import javax.jcr.PropertyType;

import com.adobe.aem.mongomk.core.propconverter.exceptions.PropertyConverterExceptions;

public interface PropertyConverter {

    /**
     * Converts a property from a given PropertyType to another PropertyType
     * @param path Path to the node that contains the property
     * @param propertyName The name of the property
     * @param from The original property type (i.e. String, Binary) See <a href="https://developer.adobe.com/experience-manager/reference-materials/spec/javax.jcr/javadocs/jcr-2.0/constant-values.html#javax.jcr.PropertyType.BINARY">for type information</a>
     * @param to The type the property should have after the conversion See <a href="https://developer.adobe.com/experience-manager/reference-materials/spec/javax.jcr/javadocs/jcr-2.0/constant-values.html#javax.jcr.PropertyType.BINARY">for type information</a>
     * @throws PropertyConverterExceptions - Failed to convert the property
    */
    public void convertProperty(String path, String propertyName, int from, int to) throws PropertyConverterExceptions;
    
}
