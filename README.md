# AEM Property Converter

This repository exposes an MBean which allows users to convert a property to another node type (**Note:** As of now it only supports String to Binary as this is the most seen use case).

# How to build

mvn clean install

- Java 8
- MVN 3.8

# How to install

mvn clean install -PautoInstallSinglePackage

# MBean Location

http://localhost:4502/system/console/jmx/com.adobe.aem.mongomk.core.propconverter.jmx%3Atype%3DPropertyConverter

If all goes well during the conversion you will see null as a result. Then you can go into the CRX and validate that the content changed types

# Contributor

[Patrique Legault](mailto:patrique.legault@gmail.com)