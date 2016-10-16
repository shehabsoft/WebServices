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
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * JDBC configurations parser. Used to parse and load JDBC conigurations such
 * as SQL queries.
 *
 * @author Eng. Ayman Atiyeh
 * @version 1.00
 */
public abstract class JdbcConfigurationParser {
    /*
     * Constants
     */
     
    /** Class logger. */
    private static final Logger logger = Logger.getLogger(
        JdbcConfigurationParser.class.getName());

    /** Default JDBC configuration file path. */
    private static final String JDBC_CONFIG_FILE = "jdbc.cfg.xml";

    /*
     * Methods
     */
    
    /**
     * Parse and load JDBC conigurations.
     * 
     * @return SQL queries files.
     */
    public static List parse() {
        return parse(JDBC_CONFIG_FILE);
    }

    /**
     * Parse and load JDBC conigurations.
     * 
     * @param filePath Configurations file path.
     * @return SQL queries files.
     */
    public static List parse(String filePath) {
        logger.info(new StringBuffer("Parsing JDBC configuration file: ")
              .append(filePath).append(" ...").toString());

        if (filePath == null || filePath.trim().length() == 0) {
            throw new DataAccessException("Invalid filePath: " + filePath);
        }

        try {
            // Get configuration file as InputStream
            InputStream in = JdbcConfigurationParser.class.getClassLoader()
                            .getResourceAsStream(filePath);
            if (in == null) {
                throw new DataAccessException("JDBC configuration file not found: "
                          + filePath);
            }

            // Parse config file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(in);

            // Get JDBC resources files
            NodeList resourceList = doc.getElementsByTagName("resource");
            if (resourceList == null || resourceList.getLength() == 0) {
                logger.info("No JDBC resource files ...");
                return new ArrayList();
            }

            // Read resources files names
            List resources = new ArrayList();
            for (int i = 0; i < resourceList.getLength(); i++)  {
                Node node = resourceList.item(i);
                NamedNodeMap attributes = node.getAttributes();
                if (attributes == null || attributes.getLength() == 0) {
                    throw new DataAccessException("No attributes for resource tag");
                }

                Node attr = attributes.getNamedItem("file");
                if (attr == null) {
                    throw new DataAccessException("file attribute not found for resource tag");
                }

                String file = attr.getNodeValue();
                if (file == null || file.trim().length() == 0) {
                    throw new DataAccessException("Empty file attribute for resource tag");
                }
                
                resources.add(file.trim());
            }

            // return resources list
            logger.info("Number of JDBC resource files: " +
                   String.valueOf(resources.size()));
            return resources;

        } catch (Exception ex) {
            throw new DataAccessException("Failed to parse JDBC configuration file", ex);
        }
    }
}