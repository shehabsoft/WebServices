/*
 * Copyright (c) i-Soft 2007.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  29/05/2007  - File created.
 */

package com.dataObject;


import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.util.logging.Logger;

import com.HomeBudget.DAO.JPA.DataAccessObject1;

/**
 * Defines the common functionality for business objects. All BO classes must 
 * extend this class.
 *
 * @author Eng. Ayman Atiyeh
 * @version 1.00
 */
public abstract class BusinessObject {
    /*
     * Methods
     */

    /**
     * Close DAO object and rlease its resources. This method used to enhance 
     * code clarity and readability.
     * 
     * @param dao DataAccessObject to be closed.
     */
    public void close(DataAccessObject1 dao) {
        if (dao == null) {
            return;
        }

        dao.close();
    }

    /**
     * This method used to enhance code clarity and readability.
     * 
     * @param dao DataAccessObject to be rolled back.
     */
    public void rollback(DataAccessObject1 dao) {
        if (dao == null) {
            return;
        }

        dao.rollback();
    }
    
    /**
     * Get class logger object.
     * 
     * @return class logger object.
     */
    protected Logger getLogger() {
        return Logger.getLogger(this.getClass().getName());
    }   
    
    /**
     * This method used to enhance code clarity and readability.
     * 
     * @param dao DataAccessObject to be commit.
     */
    public void commit(DataAccessObject dao) {
        if (dao == null) {
            return;
        }

        dao.commit();
    }
    
    /**
     * Chekc if this value is null or empty string with white spaces.
     * 
     * @param value String value to be tested.
     * @return true if this value is null or empty string with white spaces.
     */
    public boolean isBlankOrNull(String value) {
        return (value == null || value.trim().length() == 0);
    }
    
    
    
    /**
     * Get Data Access Object implementation class.
     * 
     * @param daoType Data Access Object interface.
     * @return Data Access Object implementation class.
     */
    protected DataAccessObject1 getDAO(Class daoType) {
        return DAOFactory.getInstance().getDAO(daoType);
    }
    
    public <T extends DataAccessObject1> T getDataAccessObject(Class<T> daoType){
        return DAOFactory.getInstance().getDataAccessObject(daoType);
    }


    /**
     * Get Data Access Object implementation class.
     * 
     * @param daoType Data Access Object interface.
     * @param trsDAO Transactional DAO used to initialize the new created DAO.
     * @return Data Access Object implementation class.
     */
    protected DataAccessObject1 getDAO(Class daoType, DataAccessObject1 trsDAO) {
        return DAOFactory.getInstance().getDAO(daoType, trsDAO);
    }

    /**
     * Get Data Access Object implementation class.
     * 
     * @param daoType Data Access Object interface.
     * @param connection The new DAO will use this connection.
     * @return Data Access Object implementation class.
     */
    protected DataAccessObject1 getDAO(Class daoType, Connection connection) {
        return DAOFactory.getInstance().getDAO(daoType, connection);
    }

    /**
     * Get configuration object.
     * 
     * @return configuration object.
     */
    protected TrafficConfig getConfig() {
        return ServiceLocator.getInstance().getConfig();        
    }

    /**
     * Close network socket.
     * 
     * @param socket network socket.
     */
    protected void close(Socket socket) {
        if (socket == null) {
            return;
        }

        try {socket.close();} 
        catch (Exception ex) {
            // Ignore exception
        }
    }

    /**
     * Close output stream.
     * 
     * @param out output stream.
     */
    protected void close(OutputStream out) {
        if (out == null) {
            return;
        }

        try {out.close();} 
        catch (Exception ex) {
            // Ignore exception
        }
    }

    /**
     * Close input stream.
     * 
     * @param in input stream.
     */
    protected void close(InputStream in) {
        if (in == null) {
            return;
        }

        try {in.close();} 
        catch (Exception ex) {
            // Ignore exception
        }
    }
    
}