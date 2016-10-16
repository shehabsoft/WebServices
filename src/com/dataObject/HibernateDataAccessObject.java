/*
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  16/01/2008  - File created.
 */

//package ae.rta.util.dao.hibernate;
package  com.dataObject;



import java.sql.Connection;
import java.sql.Timestamp;
import java.util.logging.Logger;
import org.hibernate.Session;

/**
 * Defines the common functionality for hibernate data access objects. All 
 * hibernate DAO classes must extend this class.
 *
 * @author Eng. Ayman Atiyeh
 * @version 1.00
 */
public class HibernateDataAccessObject implements DataAccessObject {
    /*
     * Instance variables.
     */

    /** Hibernate session object. */
    private Session session;

    /*
     * Constructors
     */

    /**
     * Construct new HibernateDataAccessObject instance. This method will
     * open new hibernate transactional session.
     */
    public HibernateDataAccessObject() {
        try {
            // open new session
            this.session = ServiceLocator.getInstance()
                                         .getHibernateSessionFactory()
                                         .openSession();
                                         
            this.session.beginTransaction();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DataAccessException(
                   "Failed to create HibernateDataAccessObject", ex);
        }
    }

    /**
     * Construct and initialize new HibernateDataAccessObject instance.
     * 
     * @param dao Data access object used to get transaction object.
     */
    public HibernateDataAccessObject(HibernateDataAccessObject dao) {
        if (dao == null) {
            throw new DataAccessException("NULL dao parameter");
        }

        this.session = dao.getSession();
    }

    /**
     * Construct and initialize new HibernateDataAccessObject instance.
     * 
     * @param daoSession Hibernate session object.
     */
    public HibernateDataAccessObject(Session daoSession) {
        if (daoSession == null) {
            throw new DataAccessException("NULL session parameter");
        }

        this.session = daoSession;
    }

    /*
     * Methods
     */

    /**
     * Close internal datasource connection.
     */
    public void close() {
        try {
            getSession().close();

        } catch (Exception ex) {
            throw new DataAccessException("Failed to close connection", ex);
        }
    }

    /**
     * Rollback internal datasource transaction.
     */
    public void rollback() {
        try {
            getSession().getTransaction().rollback();

        } catch (Exception ex) {
            throw new DataAccessException("Failed to rollback transaction", ex);
        }
    }

    /**
     * Commit internal datasource transaction
     */
    public void commit() {
        try {
            getSession().getTransaction().commit();

        } catch (Exception ex) {
            throw new DataAccessException("Failed to commit transaction", ex);
        }
    }

    /**
     * Post database changes. This method will throw DataAccessException.
     */
    public void postChanges() {
        throw new DataAccessException("Un-Supported operation...");
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
     * Get hibernate session.
     * 
     * @return hibernate session.
     */
    public Session getSession() {
        return this.session;
    }

    /**
     * Insert value object to database.
     * 
     * @param vo Value object to be saved.
     */
    public void insert(ValueObject vo) {
        // Validate value object
        if (vo == null) {
            throw new DataAccessException("insert - NULL value object parameter");
        }

        // Set creation/update date
        Timestamp date = new Timestamp(System.currentTimeMillis());
        vo.setCreationDate(date);
        vo.setUpdateDate(date);
        
        // Save value object
        getSession().save(vo);
    }

    /**
     * Load value object from database.
     * 
     * @param vo Value object to be loaded.
     * @return Value object or null if no object exists with this ID.
     */
    public ValueObject load(ValueObject vo) {
        // Validate value object
        if (vo == null) {
            throw new DataAccessException("load - NULL value object parameter");
        }

        // Load value object
        return load(vo, vo.getId());
    }
    
    /**
     * Load value object from database.
     * 
     * @param vo Value object to be loaded.
     * @param id Value object ID.
     * @return Value object or null if no object exists with this ID.
     */
    public ValueObject load(ValueObject vo, java.io.Serializable id) {
        // Validate value object
        if (vo == null) {
            throw new DataAccessException("load - NULL value object parameter");
        }

        if (id == null) {
            throw new DataAccessException("load - NULL ID object parameter");
        }

        // Load value object
        return (ValueObject) getSession().get(vo.getClass(), id);
    }

    /**
     * Update value object.
     * 
     * @param vo value object to be updated.
     */
    public void update(ValueObject vo) {
        // Validate value object
        if (vo == null) {
            throw new DataAccessException("update - NULL value object parameter");
        }

        // Set update date
        vo.setUpdateDate(new Timestamp(System.currentTimeMillis()));

        // Update value object
        session.update(vo);
    }
    
    /**
     * Delete value object.
     * 
     * @param vo value object to be deleted.
     * @return true if the object deleted successfuly.
     */
    public void delete(ValueObject vo) {
        // Validate value object
        if (vo == null) {
            throw new DataAccessException("delete - NULL value object parameter");
        }

        // Delete value object
        session.delete(vo);
    }

    /**
     * Connect this DAO class with new DAO object. This will enable both DAO 
     * classes to participate on the same transaction. This method will end the
     * old transaction and participate inthe new one.
     * 
     * @param dao New DAO class.
     */
    public void connect(DataAccessObject dao) {
        throw new DataAccessException("connect operation is not supported");
    }

    /**
     * Connect this DAO class with new connection object. This will enable 
     * both DAO classes to participate on the same transaction. This method 
     * will end the old transaction and participate inthe new one.
     * 
     * @param connection JDBC transaction to be used.
     */
    public void connect(Connection connection) {
        throw new DataAccessException("connect operation is not supported");
    }
}