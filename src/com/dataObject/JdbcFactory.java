/*
 * Copyright (c) i-Soft 2004.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 * 
 * Creation Date: 10/01/2005
 * 
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  10/01/2005  - File created.
 */

package com.dataObject;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;



/**
 * This classed used to manage JDBC connections creation.
 *
 * @version 1.00 - 10/01/2005
 * @author Eng. shehab.tarek
 */
public abstract class JdbcFactory {
    /*
     * Methods
     */

    /**
     * Get JDBC connection from connections pool.
     * 
     * @return JDBC connection from connections pool.
     */
    public static Connection getConnection() throws TrafficException, SQLException {
        return null;
    }

}