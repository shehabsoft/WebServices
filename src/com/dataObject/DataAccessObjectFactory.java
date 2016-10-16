/*
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  26/01/2008  - File created.
 */

package com.dataObject;


/**
 * Data access objects factory. All DAO factories must extend this class.
 *
 * @author Eng. Ayman Atiyeh
 * @version 1.00
 */
public abstract class DataAccessObjectFactory {
    /*
     * Methods
     */

    /**
     * Get domain data access object implementation class.
     *
     * @return domain data access object implementation class.
     */
    public static DataAccessObject getDomainObjectDAO() {
        return new HibernateDataAccessObject();
    }
}