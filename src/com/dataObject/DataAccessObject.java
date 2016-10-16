/*
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  14/01/2008  - File created.
 */

package  com.dataObject;


import java.sql.Connection;

/**
 * Defines the common functionality for all data access objects. All DAO 
 * implementation classes must implement this interface directly or indirectly 
 * by extending one of its implementation classes such as JdbcDataAccessObject.
 *
 * @author Eng. Ayman Atiyeh
 * @version 1.00
 */
public interface DataAccessObject {
    /**
     * Close internal datasource connection.
     */
    void close();
    
    /**
     * Commit internal datasource transaction
     */
    void commit();

    /**
     * Rollback internal datasource transaction.
     */
    void rollback();
    
    /**
     * Post database changes.
     */
    void postChanges();
    
    /**
     * Insert value object to database.
     * 
     * @param vo Value object to be saved.
     */
    void insert(ValueObject vo);

    /**
     * Load value object from database.
     * 
     * @param vo Value object to be loaded.
     * @return Value object or null if no object exists with this ID.
     */
    ValueObject load(ValueObject vo);

    /**
     * Update value object.
     * 
     * @param vo value object to be updated.
     */
    void update(ValueObject vo);

    /**
     * Delete value object.
     * 
     * @param vo value object to be deleted.
     * @return true if the object deleted successfuly.
     */
    void delete(ValueObject vo);

    /**
     * Load value object from database.
     * 
     * @param vo Value object to be loaded.
     * @param id Value object ID.
     * @return Value object or null if no object exists with this ID.
     */
    ValueObject load(ValueObject vo, java.io.Serializable id);
    
    /**
     * Connect this DAO class with new DAO object. This will enable both DAO 
     * classes to participate on the same transaction. This method will end the
     * old transaction and participate inthe new one.
     * 
     * @param dao New DAO class.
     */
    void connect(DataAccessObject dao);
    
    /**
     * Connect this DAO class with new connection object. This will enable 
     * both DAO classes to participate on the same transaction. This method 
     * will end the old transaction and participate inthe new one.
     * 
     * @param connection JDBC transaction to be used.
     */
    void connect(Connection connection);
}