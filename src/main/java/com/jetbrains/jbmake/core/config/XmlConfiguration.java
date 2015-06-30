package com.jetbrains.jbmake.core.config;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author daywalker
 * @since 29/06/15.
 */
public class XmlConfiguration implements Configuration {
    private static final XPath XPATH = XPathFactory.newInstance().newXPath();

    private final Node root;

    private XmlConfiguration(Node root) {
        this.root = root;
    }

    public static Configuration load(InputStream configurationInputStream) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setValidating(false);
            documentBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(configurationInputStream);
            Node root = (Node) XPATH.evaluate("preferences/root/node[@name='makefile-editor']",
                    document, XPathConstants.NODE);
            return new XmlConfiguration(root);
        } catch (ParserConfigurationException | XPathExpressionException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getValue(String key) {
        try {
            String expression = "map/entry [@key='" + key + "']/@value";
            return (String) XPATH.evaluate(expression, root, XPathConstants.STRING);
        } catch (XPathExpressionException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void putValue(String key, String value) {
        // do nothing
    }

    @Override
    public void save() {
        // do nothing, do not write over default settings
    }
}