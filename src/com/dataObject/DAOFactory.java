/*
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  22/03/2009  - File created.
 */

package  com.dataObject;

import java.sql.Connection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.HomeBudget.DAO.JPA.DataAccessObject1;

/**
 * Data access objects factory.
 *
 * @author Eng. Ayman Atiyeh
 * @version 1.00
 */
public class DAOFactory {
    /*
     * Class variables
     */

    /** Singleton object. */
    private static DAOFactory singleton;

    /*
     * Instance variables.
     */

    /** Named SQL queries. */
    private Map namedQueriesMap;

    /*
     * Constructors
     */

    /**
     * Construct and initialize DAOFactory singleton object.
     */
    private DAOFactory() {
//        namedQueriesMap = Collections.synchronizedMap(new HashMap());
//
//        // Load named queries
//        List jdbcResources = JdbcConfigurationParser.parse();
//        for (int i = 0; i < jdbcResources.size(); i++) {
//            Map queries = JdbcResourceParser.parse(jdbcResources.get(i).toString());
//
//            Iterator names = queries.keySet().iterator();
//            while (names.hasNext()) {
//                String name = names.next().toString();
//                addNamedQuery((NamedQuery) queries.get(name));
//            }
//        }
    }

    /*
     * Methods
     */

    /**
     * This methods will throw CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("DAOFactory cannot be cloned");
    }

    /**
     * Get DAOFactory reference.
     *
     * @return DAOFactory reference.
     */
    public synchronized static DAOFactory getInstance() {
        if (singleton == null) {
            singleton = new DAOFactory();
        }

        return singleton;
    }

    /**
     * Add named SQL query.
     *
     * @param namedQuery Named query object.
     */
    private void addNamedQuery(NamedQuery namedQuery) {
        // Validate parameters
        if (namedQuery == null) {
            throw new DataAccessException("NULL NamedQuery parameter");
        }

        String name = namedQuery.getName();
        String query = namedQuery.getQuery();

        if (name == null || name.trim().length() == 0) {
            throw new DataAccessException("Invalid query name: " + name);
        }

        if (query == null || query.trim().length() == 0) {
            throw new DataAccessException("NULL SQL query");
        }

        if (namedQueriesMap.containsKey(name.trim())) {
            throw new DataAccessException("Named query already exist: " + name);
        }

        // Add query
        namedQueriesMap.put(name.trim(), namedQuery);
    }

    /**
     * Get named SQL query.
     *
     * @param queryName SQL query name.
     * @return Named query object.
     */
    public NamedQuery getNamedQuery(String queryName) {
        // Validate parameter
        if (queryName == null || queryName.trim().length() == 0) {
            throw new DataAccessException("Invalid query name " + queryName);
        }

        // Check if queriy exists
        if (namedQueriesMap == null) {
            throw new DataAccessException("Named queries are not loaded ...");
        }

        if (!namedQueriesMap.containsKey(queryName.trim())) {
            throw new DataAccessException("Named query not found: " + queryName);
        }

        return new NamedQuery((NamedQuery) namedQueriesMap.get(queryName.trim()));

    }

    /**
     * Get Data Access Object implementation class.
     *
     * @param daoType Data Access Object interface.
     * @return Data Access Object implementation class.
     */
    public static DataAccessObject1 getDAO(Class daoType) {
        String daoName = daoType.getName().replaceAll(".dao.", ".dao.jdbc.");
        String implName = new StringBuffer(daoName).append("Impl").toString();

        try {
            return (DataAccessObject1) Class.forName(implName).newInstance();

        } catch (Exception ex) {
            throw new DataAccessException(new StringBuffer().append("Failed to load DAO class: ").append(implName).toString(),
                                          ex);
        }
    }

    /**
     * Get Data Access Object implementation class.
     *
     * @param daoType Data Access Object interface.
     * @return Data Access Object implementation class.
     */
    public <T extends DataAccessObject1> T getDataAccessObject(Class<T> daoType) {
        return (T) getDAO(daoType);
    }

    /**
     * Get Data Access Object implementation class.
     *
     * @param daoType Data Access Object interface.
     * @param dao The new DAO will use this DAO transaction.
     * @return Data Access Object implementation class.
     */
    public static DataAccessObject1 getDAO(Class daoType, DataAccessObject1 dao) {
        // Validate parameters
        if (daoType == null) {
            throw new DataAccessException("NULL DAO class type");
        }

        if (dao == null) {
            throw new DataAccessException("NULL transactional DAO instance");
        }

        // Validate implementation class
        if (daoType.getName().equals(dao.getClass().getName())) {
            return dao;
        }

        // Create new DAO
        DataAccessObject1 newDAO = getDAO(daoType);

        // Connect the new DAO to the same dao transaction
        newDAO.connect(dao);

        // Return new DAO class
        return newDAO;
    }

    /**
     * Get Data Access Object implementation class.
     *
     * @param daoType Data Access Object interface.
     * @param connection The new DAO will use this connection.
     * @return Data Access Object implementation class.
     */
    public DataAccessObject1 getDAO(Class daoType, Connection connection) {
        // Validate parameters
        if (daoType == null) {
            throw new DataAccessException("NULL DAO class type");
        }

        if (connection == null) {
            throw new DataAccessException("NULL transactional connection instance");
        }

        // Create new DAO
        DataAccessObject1 newDAO = getDAO(daoType);

        // Connect the new DAO to the same dao transaction
        newDAO.connect(connection);

        // Return new DAO class
        return newDAO;
    }
}
