package com.adobe.aem.mongomk.core.propconverter.jmx;

import javax.management.DynamicMBean;
import javax.management.NotCompliantMBeanException;

import com.adobe.aem.mongomk.core.propconverter.PropertyConverter;
import com.adobe.aem.mongomk.core.propconverter.exceptions.PropertyConverterExceptions;
import com.adobe.granite.jmx.annotation.AnnotatedStandardMBean;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = DynamicMBean.class, property = {
    "jmx.objectname=com.adobe.aem.mongomk.core.propconverter.jmx:type=PropertyConverter"
})
public class PropertyInfoMBeanImpl extends AnnotatedStandardMBean implements PropertyInfoMBean {

    public PropertyInfoMBeanImpl() throws NotCompliantMBeanException {
        super(PropertyInfoMBean.class);
    }

    @Reference
    private PropertyConverter propertyConverter;

    @Override
    public void convertStringToBinary(String path, String propName) throws PropertyConverterExceptions {
        this.propertyConverter.convertProperty(path, propName, 1, 2);
    }
    
}
