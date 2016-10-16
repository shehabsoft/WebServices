/*
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  29/12/2009  - File created.
 */

//package ae.rta.util.dao.jdbc;
package com.dataObject;



import java.util.logging.Logger;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * JDBC resource parser. Used to parse and load JDBC resource files which contain
 * SQL queries.
 *
 * @author Eng. Ayman Atiyeh
 * @version 1.00
 */
public abstract class JdbcResourceParser {
    /*
     * Constants
     */
     
    /** Class logger. */
    private static final Logger logger = Logger.getLogger(
        JdbcResourceParser.class.getName());

    /*
     * Methods
     */

    /**
     * Parse and load JDBC resource file.
     * 
     * @param filePath JDBC resource file path.
     * @return SQL queries map.
     */
    public static Map parse(String filePath) {
        logger.info(new StringBuffer("Parsing JDBC resource file: ")
              .append(filePath).append(" ...").toString());
        
        if (filePath == null || filePath.trim().length() == 0) {
            throw new DataAccessException("Invalid resource filePath: " + 
                filePath);
        }

        try {
            // Get configuration file as InputStream
            InputStream in = JdbcResourceParser.class.getClassLoader()
                            .getResourceAsStream(filePath);
            if (in == null) {
                throw new DataAccessException("JDBC resource file not found: " +
                          filePath);
            }

            // Parse config file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(in);

            // Get JDBC queries
            NodeList nodeList = doc.getElementsByTagName("sql-query");
            if (nodeList == null || nodeList.getLength() == 0) {
                logger.info("No JDBC queries ...");
                return new HashMap();
            }

            // Read queries
            Map sqlMap = new HashMap();
            for (int i = 0; i < nodeList.getLength(); i++) {
                // Get query name
                Node node = nodeList.item(i);
                String queryName = getQueryName(node);
                
                // Check if query name already loaded
                if (sqlMap.containsKey(queryName)) {
                    throw new DataAccessException("Duplicate query name: " + 
                        queryName);
                }

                // Get SQL query code
                String sqlQuery = getQuery(node);
                
                // Create new named quesy object
                NamedQuery namedQuery = new NamedQuery(queryName, sqlQuery);

                // Save named SQL query
                sqlMap.put(queryName, namedQuery);
            }

            // return sql queries map
            return sqlMap;

        } catch (Exception ex) {
            throw new DataAccessException("Failed to parse JDBC resource file >> " + filePath, ex);
        }
    }
    
    /**
     * Return SQL query name.
     * 
     * @param node Query node to be parsed.
     * @return SQL query name.
     */
    private static String getQueryName(Node node) {
        NamedNodeMap attributes = node.getAttributes();
        if (attributes == null || attributes.getLength() == 0) {
            throw new DataAccessException("No attributes for sql-query tag");
        }

        Node attr = attributes.getNamedItem("name");
        if (attr == null) {
            throw new DataAccessException("name attribute not found for sql-query tag");
        }

        String name = attr.getNodeValue();
        if (name == null || name.trim().length() == 0) {
            throw new DataAccessException("Empty name attribute for sql-query tag");
        }
        
        return name.trim();
    }

    /**
     * Return SQL query value.
     * 
     * @param node Query node to be parsed.
     * @return SQL query value.
     */
    private static String getQuery(Node node) {
        // Check if the node has a value or not
        if (! node.hasChildNodes()) {
            throw new DataAccessException("Empty sql-query tag");
        }
        
        /*
         * Method node.getTextContent() is available since JDK 5
         */
        // String query = node.getTextContent();
        String query = getTextContent(node);

        if (query == null || query.trim().length() == 0) {
            throw new DataAccessException("Empty query value");
        }

        return query.trim();
    }

    /**
     * Get node text content. Method <B>node.getTextContent()</B>
     * is available since JDK-5 and it returns the same result.
     * 
     * @param node Node to be parsed.
     * @return Node text content.
     */
    private static String getTextContent(Node node) {
        StringBuffer content = new StringBuffer();
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++)  {
            content.append(nodeList.item(i).getNodeValue());
        }

        return content.toString().trim();
    }
}