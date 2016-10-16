/*
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  29/01/2008  - File created.
 * 
 * 1.01  Hamzeh Abu Lawi    04/03/2008  - Modify getTransactionDAO to 
 *                                        enable create TransactionDAOJdbcImpl
 * 
 */

package com.dataObject;





import java.util.Map;

/**
 * Traffic transactions data access objects factory.
 *
 * @author Eng. Ayman Atiyeh
 * @version 1.00
 */
public class TransactionDAOFactory extends DataAccessObjectFactory {
    /**
     * Get transaction services data access object.
     *
     * @return transaction services data access object.
     */
    public static ServiceDAO getServiceDAO() {
        return new ServiceDAOJdbcImpl();
    }

   
    
    
}