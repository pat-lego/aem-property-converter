package com.adobe.aem.mongomk.core.propconverter;

import javax.jcr.LoginException;
import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.PropertyType;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import com.adobe.aem.mongomk.core.propconverter.exceptions.PropertyConverterExceptions;

import org.apache.jackrabbit.value.BinaryValue;
import org.apache.sling.jcr.api.SlingRepository;
import org.apache.sling.serviceusermapping.ServiceUserMapped;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = PropertyConverter.class, immediate = true)
public class SimplePropertyConverter implements PropertyConverter {

    private final String SERVICE_USER = "property-converter";

    @Reference
    private SlingRepository slingRepository;

    @Override
    public void convertProperty(String path, String propertyName, int from, int to)
            throws PropertyConverterExceptions {

        if (path == null || propertyName == null) {
            throw new PropertyConverterExceptions(
                    "All parameters are required for the convertProperty operation to be fulfilled please make sure there are no null parameters");
        }

        if (path.isEmpty()) {
            throw new PropertyConverterExceptions(
                    "Cannot supply the convertProperty function with a blank string representing the node path please provide a valid string");
        }

        if (propertyName.isEmpty()) {
            throw new PropertyConverterExceptions(
                    "Cannot supply the convertProperty function with a blank string representing the node property please provide a valid string");
        }

        if (from < 0 || from > 12) {
            throw new PropertyConverterExceptions(
                    "Cannot supply the convertProperty function with a from property that is larger or smaller then the defined range of types from the JCR spec");
        }

        if (to < 0 || to > 12) {
            throw new PropertyConverterExceptions(
                    "Cannot supply the convertProperty function with a from property that is larger or smaller then the defined range of types from the JCR spec");
        }

        if (from == PropertyType.STRING && to == PropertyType.BINARY) {
            convertStringToBinary(path, propertyName);
        } else {
            throw new PropertyConverterExceptions("The specified from and to conversion are not defined");
        }
    }

    public void convertStringToBinary(String path, String propertyName) throws PropertyConverterExceptions {
        try {
            Session session = this.slingRepository.loginService(SERVICE_USER, null);
            Node node = session.getNode(path);
            Property property = node.getProperty(propertyName);

            String propertyValue = property.getString();
            node.getProperty(propertyName).remove();
            session.save();
            session.refresh(true);

            node.setProperty(propertyName, new BinaryValue(propertyValue));
            session.save();
            session.logout();

        } catch (LoginException e) {
            throw new PropertyConverterExceptions(e.getMessage(), e);
        } catch (RepositoryException e) {
            throw new PropertyConverterExceptions(e.getMessage(), e);
        }
    }

}
