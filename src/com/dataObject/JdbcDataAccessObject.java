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
 * 
 * 1.01  Hamzeh Abu Lawi    02/03/2008  - Move setQueryParameters from 
 *                                       PaginationDAO class to this class
 *                                       
 * 1.02  Alaa Salem         10/02/2010  - Adding getInteger() Overloaded Method.
 * 
 * 1.03  Sami Abudayeh      15/06/2013  - Add getTableMetaData method
 * 
 * 1.04  Zaid Akel          21/10/2014  - Add update() method.
 *                                        Add query() method.
 *                                        Add queryForInt() method.
 *                                        Add queryForObject() method.
 * 
 */

//package ae.rta.util.dao.jdbc;
package com.dataObject;



import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.HomeBudget.DAO.JPA.DataAccessObject1;
import com.HomeBudget.common.GlobalUtilities;

/**
 * Defines the common functionality for JDBC data access objects. All JDBC DAO 
 * classes must extend this class.
 *
 * @author Eng. Ayman Atiyeh
 * @version 1.00
 */
public abstract class JdbcDataAccessObject implements DataAccessObject1 {
    
    
    private static String CALENDER_CLS_QUERY =    "SELECT  1 \n" + 
                                                  "FROM    TF_DTT_CALENDAR_CLS \n" + 
                                                  "WHERE   MAIN_CLS_ID = ?\n" + 
                                                  "AND     EXT_CLS_ID = ?";
    
    /*
     * Instance variables.
     */

    /** JDBC connection object. */
    private Connection con;

    /*
     * Constructors
     */

    /**
     * Construct new JdbcDataAccessObject instance. This method will initialize
     * the connection object.
     */
    public JdbcDataAccessObject() {
        this(true);
    }

    /**
     * Construct and initialize new DataAccessObject instance.
     * 
     * @param openConnection Open connection flag.
     */
    protected JdbcDataAccessObject(boolean openConnection) {
        if (! openConnection) {
            return;
        }

        try {
            // Get new connection
//            this.con = ServiceLocator.getInstance().getDataSource().getConnection();
//
//            // Set auto-commit mode to false
//            con.setAutoCommit(false);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DataAccessException(
                   "Failed to create JdbcDataAccessObject", ex);
        }
    }

    /**
     * Construct and initialize new DataAccessObject instance.
     * 
     * @param dao Data access object used to get transaction object.
     */
    public JdbcDataAccessObject(JdbcDataAccessObject dao) {
        if (dao == null) {
            throw new DataAccessException("NULL dao parameter");
        }

        this.con = dao.getConnection();
    }

    /**
     * Construct and initialize new DataAccessObject instance.
     * 
     * @param connection JDBC connection.
     */
    public JdbcDataAccessObject(Connection connection) {
        if (connection == null) {
            throw new DataAccessException("NULL connection parameter");
        }

        this.con = connection;
    }
    
    /*
     * DataAccessObject methods implementation
     */

    /**
     * Close internal datasource connection.
     */
    public void close() {
        if (getConnection() == null) {
            return;
        }

        try {
            getConnection().close();

        } catch (Exception ex) {
            throw new DataAccessException("Failed to close connection", ex);
        }
    }

    /**
     * Rollback internal datasource transaction.
     */
    public void rollback() {
        try {
            getConnection().rollback();

        } catch (Exception ex) {
            throw new DataAccessException("Failed to rollback transaction", ex);
        }
    }

    /**
     * Commit internal datasource transaction
     */
    public void commit() {
        try {
            getConnection().commit();

        } catch (Exception ex) {
            throw new DataAccessException("Failed to commit transaction", ex);
        }
    }

    /**
     * Insert value object to database.
     * 
     * @param vo Value object to be saved.
     */
    public void insert(ValueObject vo) {
        throw new DataAccessException("insert operation is not supported");
    }

    /**
     * Load value object from database.
     * 
     * @param vo Value object to be loaded.
     * @return Value object or null if no object exists with this ID.
     */
    public ValueObject load(ValueObject vo) {
        throw new DataAccessException("load operation is not supported");
    }

    /**
     * Load value object from database.
     * 
     * @param vo Value object to be loaded.
     * @param id Value object ID.
     * @return Value object or null if no object exists with this ID.
     */
    public ValueObject load(ValueObject vo, java.io.Serializable id) {
        throw new DataAccessException("load operation is not supported");
    }

    /**
     * Update value object.
     * 
     * @param vo value object to be updated.
     */
    public void update(ValueObject vo) {
        throw new DataAccessException("update operation is not supported");
    }

    /**
     * Delete value object.
     * 
     * @param vo value object to be deleted.
     * @return true if the object deleted successfuly.
     */
    public void delete(ValueObject vo) {
        throw new DataAccessException("delete operation is not supported");
    }

    /*
     * Methods
     */

    /**
     * Get DAO connection.
     * 
     * @return DAO connection object.
     */
    public Connection getConnection() {
        return this.con;
    }

    /**
     * Generate new sequence number.
     * 
     * @param sequenceName Sequence name.
     * @return new sequence number.
     */
    public Long generateSequence(String sequenceName)  {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ").append(sequenceName).append(".NEXTVAL FROM DUAL");

        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {
            pstm = getConnection().prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            if (! rs.next()) {
                throw new DataAccessException(new StringBuffer(
                  "Failed to generate new sequence: ").append(sequenceName)
                    .toString());
            }

            return new Long(rs.getLong(1));

        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        } finally {
            close(rs);
            close(pstm);
        }
    }

    /**
     * Close the JDBC Statement. This method used to enhance code clarity and 
     * readability.
     * 
     * @param stm CallableStatement to be closed.
     */
    protected static boolean close(Statement stm) {
        if (stm == null) {
            return false;
        }

        try {stm.close();} 
        catch (Exception ex) {
            return false;
        }

        return true;
    }

    /**
     * Close the JDBC ResultSet. This method used to enhance code clarity 
     * and readability.
     * 
     * @param rs ResultSet to be closed.
     */
    protected static boolean close(ResultSet rs) {
        if (rs == null) {
            return false;
        }

        try {rs.close();} 
        catch (Exception ex) {
            return false;
        }

        return true;
    }
    
    /**
     * Set prepared statement parameter.
     * 
     * @param stm Prepared statement.
     * @param index Parameter index.
     * @param val Parameter value.
     */
    public void setLong(PreparedStatement stm, int index, Long val) throws SQLException {
        if (val == null) {
            stm.setString(index, null);
        } else {
            stm.setLong(index, val.longValue());
        }
    }

    /**
     * Set prepared statement parameter.
     * 
     * @param stm Prepared statement.
     * @param index Parameter index.
     * @param val String Parameter value.
     */
    public void setString(PreparedStatement stm, int index, String val)
           throws SQLException {
        if (val == null) {
            stm.setString(index, null);
        } else {
            stm.setString(index, formatString(val));
        }
    }

    /**
     * Set prepared statement parameter.
     * 
     * @param stm Prepared statement.
     * @param index Parameter index.
     * @param val String Parameter value.
     * @param maxLength If String value length was more than maxLength it will
     *        be trucated.
     */
    public void setString(PreparedStatement stm, int index, String val, int maxLength)
           throws SQLException {
        if (val == null) {
            stm.setString(index, null);
            return;
        }
        
        if (val.length() > maxLength) {
            val = val.substring(0, maxLength);
        }
        
        stm.setString(index, formatString(val));
    }

    /**
     * Get String object
     * 
     * @param call CallableStatement instance.
     * @param name parameter name
     * @return String value
     * @throws java.sql.SQLException
     */
    public String getString(CallableStatement call, String name) throws SQLException {
        return reformatString(call.getString(name));
    }

    /**
     * Get String object
     * 
     * @param call CallableStatement instance.
     * @param index parameter index
     * @return String value
     * @throws java.sql.SQLException
     */
    public String getString(CallableStatement call, int index) throws SQLException {
        return reformatString(call.getString(index));
    }

    /**
     * Get String object
     * 
     * @param rs result set
     * @param name column name
     * @return String value
     * @throws java.sql.SQLException
     */
    public String getString(ResultSet rs, String name) throws SQLException{
        return reformatString(rs.getString(name));
    }      

    /**
     * This method used to exclude and replace special characters such as 
     * "\n, ', <, >" before saving data to database to avoid SQL injection and
     * cross-site scripting.
     * 
     * @param str String to be formatted.
     * @return formatted String.
     */
    public static String formatString(String str) {
        if (str == null) { 
            return null;
        }

        str = str.trim();
        str = str.replaceAll("\n", " ");
        str = str.replaceAll("\r", " ");

        while (str.indexOf("  ") >= 0) {
            str = str.replaceAll("  ", " ");
        }
        
        return str;
    }

    /**
     * This method used to reformat string values after comming from database.
     * 
     * @param str String to be formatted.
     * @return formatted String.
     */
    public static String reformatString(String str){
        /*
         * Currently same format method is used
         */
        return formatString(str);
    }

    /**
     * Set prepared statement parameter.
     * 
     * @param stm Prepared statement.
     * @param index Parameter index.
     * @param val Parameter value.
     */
    public void setInteger(PreparedStatement stm, int index, Integer val) throws SQLException {
        if (val == null) {
            stm.setString(index, null);
        } else {
            stm.setInt(index, val.intValue());
        }
    }
    
    /**
     * Set prepared statement parameter.
     * 
     * @param stm Prepared statement.
     * @param index Parameter index.
     * @param val Parameter value.
     */
    public void setDouble(PreparedStatement stm, int index, Double val) throws SQLException {
        if (val == null) {
            stm.setString(index, null);
        } else {
            stm.setDouble(index, val.doubleValue());
        }
    }

    /**
     * Set prepared statement parameter.
     * 
     * @param stm Prepared statement.
     * @param index Parameter index.
     * @param date Parameter value.
     */
    public void setDate(PreparedStatement stm, int index, java.util.Date date)
           throws SQLException {
        if (date == null) {
            stm.setDate(index, null);
        } else {
            stm.setDate(index, new java.sql.Date(date.getTime()));
        }
    }

    /**
     * Set Timestamp object
     * 
     * @param stm Prepared statement.
     * @param index Parameter index.
     * @param date Parameter date.
     * @throws java.sql.SQLException
     */
    public void setTimestamp(PreparedStatement stm, int index,java.util.Date date)
           throws SQLException {
        if (date == null) {
            stm.setTimestamp(index, null);
        } else {
            stm.setTimestamp(index, new Timestamp(date.getTime()));
        }
    }

    /**
     * Get Long object
     * 
     * @param rs result set
     * @param name column name
     * @return Long object
     * @throws java.sql.SQLException
     */
    public Long getLong(ResultSet rs, String name) throws SQLException{
        String val = rs.getString(name);
        if (val == null) {
            return null;
        } else {
            return new Long(val);
        }
    }      

    /**
     * Get Long object
     * 
     * @param call CallableStatement instance.
     * @param name column name
     * @return Long object
     * @throws java.sql.SQLException
     */
    public Long getLong(CallableStatement call, String name) throws SQLException{
        String val = call.getString(name);
        if (val == null) {
            return null;
        } else {
            return new Long(val);
        }
    }

    /**
     * Get Integer object
     * 
     * @param call CallableStatement instance.
     * @param name column name
     * @return Integer object
     * @throws java.sql.SQLException
     */
    public Integer getInteger(CallableStatement call, String name) throws SQLException{
        String val = call.getString(name);
        if (val == null) {
            return null;
        } else {
            return new Integer(val);
        }
    }      

    /**
     * Get Integer Object.
     * 
     * @param call CallableStatement instance.
     * @param index column index
     * @return Integer object
     * @throws java.sql.SQLException
     */
    public Integer getInteger(CallableStatement call, int index) throws SQLException{
        String val = call.getString(index);
        if(val == null) {
            return null;
        } else {
            return new Integer(val);
        }
    }      

    /**
     * Get Long Object.
     * 
     * @param call CallableStatement instance.
     * @param index column index
     * @return Integer object
     * @throws java.sql.SQLException
     */
    public Long getLong(CallableStatement call, int index) throws SQLException{
        String val = call.getString(index);
        if(val == null) {
            return null;
        } else {
            return new Long(val);
        }
    }

    /**
     * Get Integer object
     * 
     * @param name column name
     * @param rs result set
     * @return Integer object 
     * @throws java.sql.SQLException
     */    
    public Integer getInteger(ResultSet rs, String name) throws SQLException {
        String val = rs.getString(name);
        if (val == null) {
            return null;
        } else {
            return new Integer(val);
        }
    }

    /**
     * Get Integer object
     * 
     * @param name column name
     * @param rs result set
     * @return Integer object 
     * @throws java.sql.SQLException
     */    
    public Choice getChoice(ResultSet rs, String name) throws SQLException {
        return getChoice(rs, name,true);
    }

    /**
     * Get Choice Wrapper object
     * 
     * @param name column name
     * @param rs result set
     * @return Choice Wrapper
     * @throws java.sql.SQLException
     */    
    public ChoiceWrapper getChoiceWrapper(ResultSet rs, String name) throws SQLException {
        String val = rs.getString(name);
        if (val == null) {
            return new ChoiceWrapper();
        } else {
            return new ChoiceWrapper(val);
        }
    }

    /**
     * Get Integer object
     * 
     * @param name column name
     * @param rs result set
     * @param falseIfNull : False If Null flag
     * @return Integer object 
     * @throws java.sql.SQLException
     */    
    public Choice getChoice(ResultSet rs, String name,boolean falseIfNull) throws SQLException {
        String val = rs.getString(name);
        if (val == null) {
            if(falseIfNull) {
                return Choice.NO;
            }
            return null;
        } else {
            return new Choice(val);
        }
    }

    /**
     * Get Double object
     * 
     * @param name column name
     * @param rs result set
     * @return Double object
     * @throws java.sql.SQLException
     */    
    public Double getDouble(ResultSet rs, String name) throws SQLException {
        String val = rs.getString(name);
        if (val == null) {
            return null;
        } else {
            return new Double(val);
        }
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
     * Post database changes.
     */
    public void postChanges() {
        throw new DataAccessException("Un-Supported operation...");
    }

    /**
     * Debug SQL query
     * 
     * @param sql Query to debug.
     * @param params Query parameters.
     */
    protected void debugQuery(Object sql, List params) {
        StringBuffer log = new StringBuffer();

        log.append("Parameters: ");
        for (int i = 0; params != null && i < params.size(); i++)  {
            log.append(params.get(i)).append(", ");
        }

        if (sql != null) {
            log.append("\n QUERY: ").append(sql.toString());
        }
        
        getLogger().info(log.toString());
    }
    
    /**
     * Debug named SQL query
     * 
     * @param namedQuery Named SQL query to debug.
     */
    protected void debugQuery(NamedQuery namedQuery) {
        getLogger().info(namedQuery.toString());
    }

    /**
     * Clear time from date object.
     * 
     * @param date Date object to be processed.
     * @return Date without time or null if the date parameter was null.
     */
    protected Date clearTime(Date date) {
        return GlobalUtilities.clearTime(date);
    }
    
    /**
     * Checks if the field is not null or contains only blank spaces.
     * 
     * @param value String value to be checked.
     * @return true if the field is not null or blank.
     */
    protected boolean isBlankOrNull(String value) {
        return GlobalUtilities.isBlankOrNull(value);
    }
        
    /**
     * Get Float object
     * 
     * @param name column name
     * @param rs result set
     * @return Float object
     * @throws java.sql.SQLException
     */    
    public Float getFloat(ResultSet rs, String name) throws SQLException {
        String val = rs.getString(name);
        if (val == null) {
            return null;
        } else {
            return new Float(val);
        }
    }
    
    /**
     * Set PreparedStatement parameters.
     * 
     * @param stm PreparedStatement to be initialized.
     * @param parameters Search query parameters.
     */
    protected void setQueryParameters(PreparedStatement stm, List parameters) 
             throws SQLException {
        // Validate query parameters
        if (parameters == null || parameters.size() == 0) {
            return;
        }

        for (int i = 0; i < parameters.size(); i++) {
            Object param = parameters.get(i);
            int index = (i + 1);

            if (param instanceof java.util.Date) {
                java.util.Date date = (java.util.Date) param;
                stm.setTimestamp(index, new java.sql.Timestamp(date.getTime()));

            } else if (param instanceof Integer) {
                stm.setInt(index, ((Integer) param).intValue());

            } else if (param instanceof Long) {
                stm.setLong(index, ((Long) param).longValue());

            } else if (param instanceof Float) {
                stm.setFloat(index, ((Float) param).floatValue());

            } else if (param instanceof Double) {
                stm.setDouble(index, ((Double) param).doubleValue());
            }else if (param instanceof ChoiceWrapper) {
                if (((ChoiceWrapper) param).getInteger() != null) {
                    stm.setInt(index, ((ChoiceWrapper) param).getInteger().intValue());
                }else {
                    stm.setString(index, null);
                }

            } else if (param instanceof Choice) {
                stm.setInt(index, ((Choice) param).getInteger().intValue());

            } else if (param instanceof Blob) {
                stm.setBlob(index, ((Blob) param));

            } else if (param != null) {
                setString(stm, index, param.toString());

            } else {
                stm.setString(index, null);
            }
        }
    }

    /**
     * Get BLOB contents as array of bytes.
     * 
     * @param rs Result set to be extracted.
     * @param colName BLOB column name.
     * @return BLOB contents as array of bytes.
     */
    public byte[] getBlob(ResultSet rs, String colName) throws SQLException {
        Blob blob = rs.getBlob(colName);
        if(blob == null) {
            return null;
        }
        return blob.getBytes(1, (int) blob.length());
    }

    /**
     * Get BLOB contents as array of bytes.
     * 
     * @param rs Result set to be extracted.
     * @param index BLOB column index.
     * @return BLOB contents as array of bytes.
     */
    public byte[] getBlob(ResultSet rs, int index) throws SQLException {
        Blob blob = rs.getBlob(index);
        return blob.getBytes(1, (int) blob.length());
    }

    /**
     * Set BLOB contents.
     * 
     * @param stm SQL DML statment.
     * @param index BLOB column index.
     * @return BLOB contents as array of bytes.
     */
    public void setBlob(PreparedStatement stm, int index, byte[] bytes) throws SQLException {
        stm.setBytes(index, bytes);
    }

    /**
     * Update BLOB data.
     * 
     * @param lob BLOB object to be updated.
     * @param data New data bytes.
     */
    public void updateBlob(Blob lob, byte[] data) throws SQLException, IOException {
        BufferedOutputStream out = null;
        try {
//            out = new BufferedOutputStream(
//                ((oracle.sql.BLOB) lob).getBinaryOutputStream());
//
//            out.write(data);
//            out.flush();

        } finally {
            if (out != null) {try {out.close();} catch (Exception ex) {}}
        }
    }

    /**
     * Set query parameter.
     * 
     * @param query SQL query to be initialized.
     * @param parameterName Query parameter name.
     * @param values Query parameter values.
     * @return initialized query.
     */
    protected String setNamedParameter(String query, String parameterName, Object[] values) {
        if (values == null || values.length == 0) {
            return query.replaceAll(parameterName, "null");
        }

        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < values.length; i++)  {
            if (buf.length() > 0) {
                buf.append(",");
            }

            buf.append(values[i]);
        }

        return query.replaceAll(parameterName, buf.toString());
    }
    
    /**
     * Get Date object
     * 
     * @throws java.sql.SQLException
     * @return Date object
     * @param name
     * @param rs
     */
    public Date getDate(ResultSet rs, String name) throws SQLException {
        Timestamp val = rs.getTimestamp(name);
        if (val == null) {
            return null;
        } else {
            return new Date(val.getTime());
        }
    }    

    /**
     * Get encryption key.
     * 
     * @return encryption key.
     */
    public static String getEncryptionKey() {
        return ServiceLocator.getInstance().getConfig().getEncryptionKey();
    }

    /**
     * Execute search command. The PreparedStatement returned will contain only
     * requierd page records.
     * 
     * @param searchQuery SQL search query.
     * @param parameters Search query parameters.
     * @param pageNo Requid page number.
     * @param pageSize Search page size.
     * @return Initialized PreparedStatement object.
     */
    public PreparedStatement doSearch(String searchQuery, List parameters,
           int pageNo, int pageSize) throws SQLException {
            return doSearch(searchQuery, parameters,pageNo, pageSize, 0);
    }

    /**
     * Execute search command. The PreparedStatement returned will contain only
     * requierd page records.
     * 
     * @param searchQuery SQL search query.
     * @param parameters Search query parameters.
     * @param pageNo Requid page number.
     * @param pageSize Search page size.
     * @param maxPaginationRecords Max pagination records
     * @return Initialized PreparedStatement object.
     */
    public PreparedStatement doSearch(String searchQuery, List parameters,
           int pageNo, int pageSize, int maxPaginationRecords) throws SQLException {
           
        if (maxPaginationRecords <= 0) {
            maxPaginationRecords = getMaxPaginationRecords();
        }
           
        // Create new search query to return only active page records
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT * FROM (SELECT SRC_SQL.*, ROWNUM ROWNO FROM (")
               .append("SELECT * FROM (")
               .append(searchQuery)
               .append(") WHERE ROWNUM <= ").append(maxPaginationRecords)
           .append(") SRC_SQL  WHERE ROWNUM <= ?") // last index: pageNo + pageSize
           .append(") WHERE ROWNO > ?"); // pageNo (starting from 0)

        // Create PreparedStatement object
        getLogger().info(sql.toString());
        PreparedStatement stm = getConnection().prepareStatement(sql.toString());
        setQueryParameters(stm, parameters);

        int nextParamIndex = (parameters == null || parameters.size() == 0) 
                           ? 1 
                           : (parameters.size() + 1);
        
        int pageEnd = getPageEnd(pageNo, pageSize);
        int pageOffset = getPageOffset(pageNo, pageSize);
        
        getLogger().info(new StringBuffer("pageEnd=").append(pageEnd)
          .append(", pageOffset=").append(pageOffset).toString());
        
        stm.setInt(nextParamIndex++, pageEnd);
        stm.setInt(nextParamIndex, pageOffset);

        return stm;
    }

    /**
     * Get page offset, i.e., first record index starting from 0.
     * 
     * @param pageNo Requid page number.
     * @param pageSize Search page size.
     * @return page offset, i.e., first record index starting from 0.
     */
    private int getPageOffset(int pageNo, int pageSize) {
        return pageNo * pageSize;
    }

    /**
     * Get page end, i.e., last record index starting from 0.
     * 
     * @param pageNo Requid page number.
     * @param pageSize Search page size.
     * @return page page end, i.e., last record index starting from 0.
     */
    private int getPageEnd(int pageNo, int pageSize) {
        return getPageOffset(pageNo, pageSize) + pageSize;
    }
    
    /**
     * Get default page size.
     * 
     * @return default page size.
     */
    public int getDefaultPageSize() {
        return ServiceLocator.getInstance()
                             .getConfig()
                             .getSearchPageSize();
    }

    /**
     * Get maximum number of records retrieved by pagination pages.
     * 
     * @return maximum number of records retrieved by pagination pages.
     */
    public int getMaxPaginationRecords() {
        return ServiceLocator.getInstance()
                             .getConfig()
                             .getMaxPaginationRecords();
    }

    /**
     * Execute search command. The PreparedStatement returned will contain only
     * requierd page records.
     * 
     * @param searchQuery SQL search query.
     * @param parameters Search query parameters.
     * @param pageNo Requid page number.
     * @return Initialized PreparedStatement object.
     */
    public PreparedStatement doSearch(String searchQuery, List parameters,
           int pageNo) throws SQLException {
        // Use default page size for the required page
        return doSearch(searchQuery, parameters, pageNo, getDefaultPageSize());
    }

    /**
     * Execute search command. The PreparedStatement returned will contain only
     * requierd page records.
     * 
     * @param namedQuery Named SQL search query.
     */
    public PreparedStatement doSearch(NamedQuery namedQuery) throws SQLException {
        return doSearch(namedQuery, 0);
    }

    /**
     * Execute search command. The PreparedStatement returned will contain only
     * requierd page records.
     * 
     * @param namedQuery Named SQL search query.
     * @param pageNo Requid page number.
     * @return Initialized PreparedStatement object.
     */
    public PreparedStatement doSearch(NamedQuery namedQuery, int pageNo) 
           throws SQLException {
        return doSearch(namedQuery, pageNo, getDefaultPageSize());
    }

    /**
     * Execute search command. The PreparedStatement returned will contain only
     * requierd page records.
     * 
     * @param namedQuery Named SQL search query.
     * @param pageNo Requid page number.
     * @param pageSize Search page size.
     * @return Initialized PreparedStatement object.
     */
    public PreparedStatement doSearch(NamedQuery namedQuery,
           int pageNo, int pageSize) throws SQLException {
           return doSearch(namedQuery,pageNo, pageSize, 0);
    }

    /**
     * Execute search command. The PreparedStatement returned will contain only
     * requierd page records.
     * 
     * @param namedQuery Named SQL search query.
     * @param pageNo Requid page number.
     * @param pageSize Search page size.
     * @param  maxPaginationRecords max pagination records
     * @return Initialized PreparedStatement object.
     */
    public PreparedStatement doSearch(NamedQuery namedQuery,
           int pageNo, int pageSize, int maxPaginationRecords) throws SQLException {
        // Get SQL info
        Object[] sqlInfo = namedQuery.getSqlMetaData();
        
        // Call overloaded method
        return doSearch(sqlInfo[0].toString(), 
                        ((List) sqlInfo[1]), 
                        pageNo, 
                        pageSize,
                        maxPaginationRecords);
    }

    /**
     * Execute search command. The PreparedStatement returned will contain only
     * requierd page records.
     * 
     * @param searchQuery SQL search query.
     * @param parameters Search query parameters.
     * @return Initialized PreparedStatement object.
     */
    public PreparedStatement doSearch(String searchQuery, List parameters) 
           throws SQLException {
        // Use default page size for the required page and get first page
        int pageNo = 0;
        return doSearch(searchQuery, parameters, pageNo);
    }

    /**
     * Get query total number of records.
     * 
     * @param namedQuery Named SQL search query.
     * @return total records count (without pagination).
     */
    protected int getTotalCount(NamedQuery namedQuery) throws SQLException {
        return getTotalCount(namedQuery, 0);
    }

    /**
     * Get query total number of records.
     * 
     * @param namedQuery Named SQL search query.
     * @param  maxPaginationRecords Max pagination records
     * @return total records count (without pagination).
     */
    protected int getTotalCount(NamedQuery namedQuery, int maxPaginationRecords) 
              throws SQLException {
        // Get SQL info
        Object[] sqlInfo = namedQuery.getSqlMetaData();
        
        // Call overloaded method
        return getTotalCount(sqlInfo[0].toString(), (List) sqlInfo[1], maxPaginationRecords);
    }

    /**
     * Get query total number of records.
     * 
     * @param searchQuery SQL search query.
     * @param parameters Search query parameters.
     * @return total records count (without pagination).
     */
    protected int getTotalCount(String searchQuery, List parameters) throws SQLException {
        return getTotalCount(searchQuery, parameters, 0);
    }

    /**
     * Get query total number of records.
     * 
     * @param searchQuery SQL search query.
     * @param parameters Search query parameters.
     * @param maxPaginationRecords Max pagination records
     * @return total records count (without pagination).
     */
    protected int getTotalCount(String searchQuery, List parameters, int maxPaginationRecords) 
              throws SQLException {
        // If maxPaginationRecords <= 0, use the default value
        if (maxPaginationRecords <= 0) {
            maxPaginationRecords = getMaxPaginationRecords();
        }
              
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT COUNT(*) FROM(").append(searchQuery)
           .append(") WHERE ROWNUM <= ").append(maxPaginationRecords);
        getLogger().info(sql.toString());

        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            // Execute search query
            stm = getConnection().prepareStatement(sql.toString());
            setQueryParameters(stm, parameters);
            rs = stm.executeQuery();
            rs.next();

            return rs.getInt(1);

        } finally {
            close(rs);
            close(stm);
        }
    }

    /**
     * Connect this DAO class with new DAO object. This will enable both DAO 
     * classes to participate on the same transaction. This method will end the
     * old transaction and participate inthe new one.
     * 
     * @param dao New DAO class.
     */
    public void connect(DataAccessObject dao) {
        // Validate new DAO
        if (dao == null) {
            throw new DataAccessException("NULL DAO parameter");
        }
        
        // Check DAO type
        if (! (dao instanceof JdbcDataAccessObject)) {
            throw new DataAccessException(new StringBuffer("Invalid DAO type. ")
                .append("Expected: ").append(JdbcDataAccessObject.class.getName())
                .append(", found: ").append(dao.getClass().getName())
                .toString());
        }
        
        // Check new DAO connection
        JdbcDataAccessObject newDAO = (JdbcDataAccessObject) dao;
        Connection newCon = newDAO.getConnection();
        if (newCon == null) {
            throw new DataAccessException("New DAO connection is NULL");
        }

        // Close old connection
        close();
        
        // Use new DAO cinnection
        this.con = newCon;
    }

    /**
     * Connect this DAO class with new connection object. This will enable 
     * both DAO classes to participate on the same transaction. This method 
     * will end the old transaction and participate inthe new one.
     * 
     * @param connection JDBC transaction to be used.
     */
    public void connect(Connection connection) {
        // Validate new connection
        if (connection == null) {
            throw new DataAccessException("NULL connection parameter");
        }

        // Close old connection
        close();

        // Use new DAO cinnection
        this.con = connection;
    }

    /**
     * Replace all oldValue occurences on the src text with newValue.
     * 
     * @param src Source text to be updated,
     * @param oldValue Old token value.
     * @param newValue New token value.
     * @return Updated text.
     */
//    protected StringBuffer replaceAll(StringBuffer src, 
//                                       String oldValue, 
//                                       Object newValue) {
//        return GlobalUtilities.replaceAll(src, oldValue, newValue);
//    }

    /**
     * Get named SQL query.
     *
     * @param queryName SQL query name.
     * @return SQL query.
     */
    protected NamedQuery getNamedQuery(String queryName) {
        return DAOFactory.getInstance().getNamedQuery(queryName);
    }
    
    /**
     * Create and initialize PreparedStatement using the named query provided.
     * 
     * @param namedQuery Named SQL query.
     * @return Initialized PreparedStatement.
     */
    protected PreparedStatement prepareStatement(NamedQuery namedQuery) 
              throws SQLException {
        // Debug named query
        debugQuery(namedQuery);

        // Get sql info
        Object sqlInfo[] = namedQuery.getSqlMetaData();

        // Create PreparedStatement
        PreparedStatement stm = getConnection().prepareStatement(
            sqlInfo[0].toString());
            
        // Initialize PreparedStatement
        setQueryParameters(stm, (List) sqlInfo[1]);
        
        // Return PreparedStatement
        return stm;
    }
    
    /**
     * Get and initialize new search page.
     * 
     * @param namedQuery Named SQL query.
     * @param pageNo Search page number.
     * @return Initialized PreparedStatement.
     */
    public SearchPageVO getSearchPage(NamedQuery namedQuery, int pageNo) 
           throws SQLException {
        // Call overloaded method
        return getSearchPage(namedQuery, pageNo, getDefaultPageSize());
    }
    
    /**
     * Get and initialize new search page.
     * 
     * @param namedQuery Named SQL query.
     * @param pageNo Search page number.
     * @return Initialized PreparedStatement.
     */
    public SearchPageVO getSearchPage(String query, List param, int pageNo) 
           throws SQLException {
        // Call overloaded method
        return getSearchPage(query,param, pageNo, getDefaultPageSize());
    }

    /**
     * Get and initialize new search page.
     * 
     * @param namedQuery Named SQL query.
     * @param pageNo Search page number.
     * @param pageSize Search page size.
     * @return Initialized PreparedStatement.
     */
    public SearchPageVO getSearchPage(NamedQuery namedQuery, 
                                      int pageNo, 
                                      int pageSize) throws SQLException {
        // Get total search records without pagination
        return getSearchPage(namedQuery, pageNo, pageSize, 0);
    }
    
    /**
     * Get and initialize new search page.
     * 
     * @param namedQuery Named SQL query.
     * @param pageNo Search page number.
     * @param pageSize Search page size.
     * @return Initialized PreparedStatement.
     */
    public SearchPageVO getSearchPage(String query, 
                                      List param,
                                      int pageNo, 
                                      int pageSize) throws SQLException {
        // Get total search records without pagination
        return getSearchPage(query,param,  pageNo, pageSize, 0);
    }

    /**
     * Get and initialize new search page.
     * 
     * @param namedQuery Named SQL query.
     * @param pageNo Search page number.
     * @param pageSize Search page size.
     * @param maxPaginationRecords max pagination records
     * @return Initialized PreparedStatement.
     */
    public SearchPageVO getSearchPage(NamedQuery namedQuery, 
                                      int pageNo, 
                                      int pageSize,
                                      int maxPaginationRecords) throws SQLException {
        // Get total search records without pagination
        int totalRecordCounts = getTotalCount(namedQuery, maxPaginationRecords);
        
        // Return new search page
        return new SearchPageVO(pageNo, pageSize, totalRecordCounts);
    }
    
    /**
     * Get and initialize new search page.
     * 
     * @param namedQuery Named SQL query.
     * @param pageNo Search page number.
     * @param pageSize Search page size.
     * @param maxPaginationRecords max pagination records
     * @return Initialized PreparedStatement.
     */
    public SearchPageVO getSearchPage(String query, 
                                      List param, 
                                      int pageNo, 
                                      int pageSize,
                                      int maxPaginationRecords) throws SQLException {
        // Get total search records without pagination
        int totalRecordCounts = getTotalCount(query, param,  maxPaginationRecords);
        
        // Return new search page
        return new SearchPageVO(pageNo, pageSize, totalRecordCounts);
    }
    
	/**
	* Get Table Meta Data.
	* 
	* @throws java.lang.ClassNotFoundException
	* @throws java.sql.SQLException
	* @param tableName : Table Name.
	*/
	public Map getTableMetaData(String tableName) {
        	
//		ResultSet rs = null;
//		try {
////
////			DatabaseMetaData dbMetaData = getConnection().getMetaData();
////			rs = dbMetaData.getColumns(null, null, tableName, null);
////			
////			ColumnMetaInfo metaColumn;
////			Map columnMetaMap = new HashMap();
////            
////			while(rs.next()) {
////                metaColumn = new ColumnMetaInfo(rs.getString("COLUMN_NAME"), rs.getInt("DATA_TYPE"), rs.getInt("COLUMN_SIZE"));
////                columnMetaMap.put(metaColumn.getName(), metaColumn);
//                              
//			}	  
//		
//			//return columnMetaMap;
//		
//		} catch(Exception ex) {
//			throw new DataAccessException(ex);
//            
//		} finally {
//			close(rs);
//		}
		return null;
	}    
    
    /**
     * Executes the given named query, maps the first row to a Java object via a JdbcRowMapper.
     * @return The Mapped object or null if no data found.
     * @param rowMapper
     * @param namedQry
     */    
    public Object queryForObject(NamedQuery namedQry, JdbcRowMapper rowMapper){
        if(namedQry == null || rowMapper == null){
            return null;
        }
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;
        try {
            prepStmt = prepareStatement(namedQry);
            resultSet = prepStmt.executeQuery();
            
            if(!resultSet.next()) {
                return null;
            }
            
            Object result = rowMapper.mapRow(resultSet);
            return result;          
            
        } catch(Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(resultSet);
            close(prepStmt);
        }    
    }
    
    /**
     * Executes the given named query, and returns the value at index 1.
     * @return The value at index 1.
     * @param rowMapper
     * @param namedQry
     */    
    public int queryForInt(NamedQuery namedQry){
        if(namedQry == null){
            return 0;
        }
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;
        try {
            prepStmt = prepareStatement(namedQry);
            resultSet = prepStmt.executeQuery();
            
            if(resultSet.next()) {
                int result = resultSet.getInt(1);
                return result;
            }
            return 0;
        } catch(Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(resultSet);
            close(prepStmt);
        }    
    }    
    
    /**
     * Executes the given named query, maps each row to a Java object via a JdbcRowMapper.
     * @return The Result List
     * @param rowMapper
     * @param namedQry
     */
    public List query(NamedQuery namedQry, JdbcRowMapper rowMapper){
        List result = new ArrayList();
        if(namedQry == null || rowMapper == null){
            return result;
        }
        
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;
        try {
            prepStmt = prepareStatement(namedQry);
            resultSet = prepStmt.executeQuery();
            
            while(resultSet.next()) {
                Object obj = rowMapper.mapRow(resultSet);
                if(obj != null) {
                    result.add(obj);
                }
            }
            
            return result;
            
        } catch(Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(resultSet);
            close(prepStmt);
        }    
    }
    
    /**
     * Sets parameters passed in <i>parametersMap</i> to <i>query</i> and  
     * issues a single SQL update operation (such as an insert, update or delete statement).
     * @return the number of rows affected
     * @param parametersMap Parameters to set on Named Query before execution
     * @param query Named Query to execute
     */
    public int update(NamedQuery query, Map parametersMap) {
        PreparedStatement prepStmt = null;
        try {
            if(parametersMap != null){
                Set keySet =  parametersMap.keySet();
                Iterator itr =  keySet.iterator();
                while(itr.hasNext()){
                     Object key = itr.next();
                     query.setParameter(GlobalUtilities.getString(key), parametersMap.get(key));
                }
            }
            
            prepStmt = prepareStatement(query);
            int count = prepStmt.executeUpdate();
            return count;
        } catch(Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(prepStmt);
        }
    }
    
    /**
     * Executes the given named query, maps each row to a Java object via a JdbcRowMapper.
     * @return SearchPageVO The search page which contains result list
     * @param rowMapper
     * @param namedQry
     * @throws DataAccessException if JdbcRowMapper maps non-serializable object or if a 
     * SQL Exception occurs
     */    
    public SearchPageVO query(int pageNo, NamedQuery namedQuery, JdbcRowMapper rowMapper) {
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;
        debugQuery(namedQuery);
        
        try {
            SearchPageVO searchPage = getSearchPage(namedQuery, pageNo);
            prepStmt = doSearch(namedQuery, pageNo);
            resultSet = prepStmt.executeQuery();
        
            while(resultSet.next()) {
                Object obj = rowMapper.mapRow(resultSet);
                if(obj != null) {
                    if(obj instanceof Serializable){
                        searchPage.addRecord((Serializable)obj);
                    }else {
                        throw new DataAccessException(
                            "Failed add Mapped Object: Not Serializable. " + GlobalUtilities.getString(obj));
                    }
                }
            }
            return searchPage;
        } catch (DataAccessException e) {
            throw e;
        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(resultSet);
            close(prepStmt);
        } 
    }
    
    /**
     * Executes the given named query, and returns the value at index 1.
     * @return The value at index 1.
     * @param rowMapper
     * @param namedQry
     */    
    public Object queryForObject(NamedQuery namedQry){
        if(namedQry == null){
            return null;
        }
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;
        try {
            prepStmt = prepareStatement(namedQry);
            resultSet = prepStmt.executeQuery();
            
            if(resultSet.next()) {
                Object result = resultSet.getObject(1);
                return result;
            }
            return null;
        } catch(Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(resultSet);
            close(prepStmt);
        }    
    }
    
    /**
     * Sets parameters passed in <i>parametersMap</i> to <i>query</i> and  
     * issues a batch SQL update operation (such as an insert, update or delete statement).
     * @return the number of rows affected
     * @param parametersMapList List of Map of Parameters to set on Named Query before execution (List<Map>)
     * @param query Named Query to execute
     */
    public int[] batchUpdate(NamedQuery query, List parametersMapList) {
        PreparedStatement prepStmt = null;

        try {
            prepStmt =  getConnection().prepareStatement(query.getQuery());
            
            if(parametersMapList != null){
                for(int i=0;i<parametersMapList.size();i++){
                    Map parametersMap = (Map)parametersMapList.get(i);
                    Set keySet =  parametersMap.keySet();
                    Iterator itr =  keySet.iterator();
                    while(itr.hasNext()){
                         Object key = itr.next();
                         query.setParameter(GlobalUtilities.getString(key), parametersMap.get(key));
                    }
                    
                    setQueryParameters(prepStmt, (List) query.getSqlMetaData()[1]);
                    debugQuery(query);
                    prepStmt.addBatch();
                }
            }
            
            int[] count = prepStmt.executeBatch();
            return count;
        } catch(Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(prepStmt);
        }
    }
    
    /**
     * Executes the given named query, and returns a map which has key at index 1 and value at index 2.
     * 
     * @return map which has key at index 1 and value at index 2.
     * @param rowMapper
     * @param namedQry
     */    
    public Map queryForMap(NamedQuery namedQry){
        if(namedQry == null){
            return null;
        }
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;
        try {
            prepStmt = prepareStatement(namedQry);
            resultSet = prepStmt.executeQuery();
            
            Map result = new LinkedHashMap();
            while(resultSet.next()) {
                result.put(resultSet.getObject(1), resultSet.getObject(2));
            }
            return result;
        } catch(Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(resultSet);
            close(prepStmt);
        }    
    }    

    /**
     * Get String object
     * 
     * @param rs result set
     * @param name column name
     * @return String value
     * @throws java.sql.SQLException
     */
    public String getStringWithoutFormat(ResultSet rs, String name) throws SQLException{
        return formatStringWithoutRemoveSpecialChar(rs.getString(name));
    }   

    /**
     * Format String Without Without Remove Special Char
     * 
     * @param str String to be formatted.
     * @return formatted String.
     */
    public static String formatStringWithoutRemoveSpecialChar(String str) {
        if (str == null) { 
            return null;
        }

        str = str.trim();
        
        
        return str;
    }

    /**
     * Set prepared statement parameter.
     * 
     * @param stm Prepared statement.
     * @param index Parameter index.
     * @param val String Parameter value.
     */
    public void setStringWithoutRemoveSpecialChar(PreparedStatement stm, int index, String val)
           throws SQLException {
        if (val == null) {
            stm.setString(index, null);
        } else {
            stm.setString(index, formatStringWithoutRemoveSpecialChar(val));
        }
    }
    

    /**
     * Get Byte object
     * 
     * @param name column name
     * @param rs result set
     * @return Byte object 
     * @throws java.sql.SQLException
     */
    public Byte getByte(ResultSet rs, String name) throws SQLException {
        String val = rs.getString(name);
        if (val == null) {
            return null;
        } else {
            return new Byte(val);
        }
    }   
    
    protected boolean isLVRoadTestClassExam(Long clsId) {
        if (clsId == null ) {
            return false;
        } 
//        if (clsId.equals(ClassExamVO.CLASS_EXAM_ID_21) ) { 
//            List list = new ArrayList();
//            list.add(ClassExamVO.CLASS_EXAM_ID_22);
//            list.add(ClassExamVO.CLASS_EXAM_ID_21);
//            if (JdbcAdapter.selectIntValue(CALENDER_CLS_QUERY,list,0) != 0) {
//                return true;
//            }
        
        return false;
    }

}