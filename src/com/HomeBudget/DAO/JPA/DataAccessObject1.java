package com.HomeBudget.DAO.JPA;

import java.sql.Connection;

import com.dataObject.ValueObject;

public interface DataAccessObject1 {
   
	
	
	
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
     * Connect this DAO class with new DAO object. This will enable both DAO 
     * classes to participate on the same transaction. This method will end the
     * old transaction and participate inthe new one.
     * 
     * @param dao New DAO class.
     */
    void connect(DataAccessObject1 dao);
    
    /**
     * Connect this DAO class with new connection object. This will enable 
     * both DAO classes to participate on the same transaction. This method 
     * will end the old transaction and participate inthe new one.
     * 
     * @param connection JDBC transaction to be used.
     */
    void connect(Connection connection);
    
    
}
