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
 * 
 * 1.01  Husam A. Barham    11-01-2005  - adding mehtods to:
 *                                       1- retrive the first row as a Map.
 *                                       2- retrive the whole result as List of  Maps.
 *                                       3- retrive the whole result as map of maps.
 *                                       4- retrive one column as object.
 *                                       
 * 1.02  Husam A. Barham    05-02-2005  - using PreparedStatement to get values for 
 *                                          queries with fixed one argument.
 *
 * 1.03  Eng. Ayman Atiyeh  24/03/2005  - Adding rollback method
 *                                      - Adding debug method
 *                                      - Add debug messages to trace exceptions
 * 
 * 1.04  Eng. Ayman Atiyeh  01/05/2005  - Adding getRefCodeDesc method
 *                                      - Adding getRefCodeDescEn method
 *                                      
 * 1.05  Eng. Ayman Atiyeh  22/09/2005  - Overloading executeQuery() method by
 *                                        passing query parameters and using 
 *                                        PreparedStatement and 
 * 
 * 1.06  Eng. Ayman Atiyeh  26/09/2005  - Overloading selectLongValue() method by
 *                                        passing query parameters and using 
 *                                        PreparedStatement and
 * 1.07  Uqba OWDA  23/11/2009          - Overloading selectStringValue() method by
 *                                        passing query parameters and using 
 *                                        PreparedStatement and
 */

package com.dataObject;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.Types;
import java.sql.SQLException;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * This class used as a base class for classes that interact with database.
 * It is encapsulate common functionality to access/interact with database
 * using JDBC.
 *
 * @version 1.00 - 10/01/2005
 * @author Eng. shehab .tarek
 */
public abstract class JdbcAdapter {
    /**
     * Print debuggeng messages
     * 
     * @param msg Message to be printed
     */
    public static void debug(String msg) {
        TrafficLogger.debug("ae.gov.dphq.traffic.util.JdbcAdapter", msg);
    }

    /**
     * Return JDBC connection.
     * 
     * @return JDBC connection.
     */
    public static Connection getConnection() {
        return JdbcFactory.getConnection();
    }

    /**
     * Close the JDBC connection. This method used to enhance code clarity
     * and readability.
     * 
     * @param con The JDBC connection to be closed.
     */
    public static void close(Connection con) {
        if (con == null) {
            return;
        }

        try {con.close();} 
        catch (Exception ex) {
            debug("close: WARNING, " + ex);
        }
    }

    /**
     * Close the JDBC CallableStatement. This method used to enhance code 
     * clarity and readability.
     * 
     * @param stm CallableStatement to be closed.
     */
    public static void close(CallableStatement stm) {
        if (stm == null) {
            return;
        }

        try {stm.close();} 
        catch (Exception ex) {
            debug("close: WARNING, " + ex);
        }
    }

    /**
     * Close the JDBC PreparedStatement. This method used to enhance code 
     * clarity and readability.
     * 
     * @param stm PreparedStatement to be closed.
     */
    public static void close(PreparedStatement stm) {
        if (stm == null) {
            return;
        }

        try {stm.close();} 
        catch (Exception ex) {
            debug("close: WARNING, " + ex);
        }
    }

    /**
     * Close the JDBC Statement. This method used to enhance code clarity 
     * and readability.
     * 
     * @param stm Statement to be closed.
     */
    public static void close(Statement stm) {
        if (stm == null) {
            return;
        }

        try {stm.close();} 
        catch (Exception ex) {
            debug("close: WARNING, " + ex);
        }
    }

    /**
     * Close the JDBC ResultSet. This method used to enhance code clarity 
     * and readability.
     * 
     * @param rs ResultSet to be closed.
     */
    public static void close(ResultSet rs) {
        if (rs == null) {
            return;
        }

        try {rs.close();} 
        catch (Exception ex) {
            debug("close: WARNING, " + ex);
        }
    }

    /**
     * Rollback the transaction and ignore any generated exceptions.
     */
    public static void rollback(Connection con) {
        if (con == null) {
            return;
        }

        try {con.rollback();} 
        catch (Exception ex) {
            debug("rollback: WARNING, " + ex);
        }
    }

    /**
     * This method will execute the query and returns the first column value in
     * the first row.
     * 
     * @param  query query to be executed.
     * @param  defaultVal Default value if no records where found.
     * @return The first column value in the first row.
     */
    public static int selectIntValue(String query, int defaultVal) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try  {
            con = getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(query);

            if (rs.next() == false) {
                return defaultVal;
            }

            return rs.getInt(1);
            
        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(stm);
            close(con);
        }        
    }

    /**
     * This method will execute the query and returns the first column value in
     * the first row. (overloaded to use prepared statement)
     * 
     * @param  query query to be executed.
     * @param  defaultVal Default value if no records where found.
     * @return The first column value in the first row.
     */
    public static int selectIntValue(String query, long param, int defaultVal) {
        Connection con = null;
        PreparedStatement prStm = null;
        ResultSet rs = null;
        try  {
            con = getConnection();
            prStm = con.prepareStatement(query);
            prStm.setLong(1, param);
            rs = prStm.executeQuery();

            if (rs.next() == false) {
                return defaultVal;
            }

            return rs.getInt(1);
            
        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(prStm);
            close(con);
        }        
    }
    
    /**
     * This method will execute the query and returns the first column value in
     * the first row. (overloaded to use prepared statement)
     * 
     * @param  query query to be executed.
     * @param  defaultVal Default value if no records where found.
     * @return The first column value in the first row.
     */
    public static int selectIntValue(String query, List params, int defaultVal) {
        Connection con = null;
        PreparedStatement prStm = null;
        ResultSet rs = null;
        try  {
            con = getConnection();
            prStm = con.prepareStatement(query);
            for(int i=0; i<params.size();i++){
                Object param = params.get(i);
                prStm.setString((i + 1), param.toString());
            }
            rs = prStm.executeQuery();

            if (rs.next() == false) {
                return defaultVal;
            }

            return rs.getInt(1);
            
        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(prStm);
            close(con);
        }        
    }
    
    /**
     * This method will execute the query and returns the first column value in
     * the first row.
     * 
     * @param  query query to be executed.
     * @param  defaultVal Default value if no records where found.
     * @return The first column value in the first row.
     */
    public static long selectLongValue(String query, long defaultVal) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try  {
            con = getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(query);

            if (rs.next() == false) {
                return defaultVal;
            }

            return rs.getLong(1);
            
        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(stm);
            close(con);
        }        
    }

    /**
     * This method will execute the query and returns the first column value in
     * the first row using PreparedStatement and the query has one fixed parameter
     * 
     * @param  query query to be executed.
     * @param  param key Parameter for the query.
     * @param  defaultVal Default value if no records where found.
     * @return The first column value in the first row.
     */
    public static long selectLongValue(String query, long param, long defaultVal) {
        Connection con        = null;
        PreparedStatement stm = null;
        ResultSet rs          = null;
        try  {
            con = getConnection();
            stm = con.prepareStatement(query);
            stm.setLong(1, param);
            rs = stm.executeQuery();
            if (rs.next() == false) {
                return defaultVal;
            }
            return rs.getLong(1);
            
        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(stm);
            close(con);
        }        
    }

    /**
     * This method will execute the query and returns the first column value in
     * the first row using PreparedStatement and the query has one fixed parameter
     * 
     * @param  query query to be executed.
     * @param  params Query parameter for the query.
     * @param  defaultVal Default value if no records where found.
     * @return The first column value in the first row.
     */
    public static long selectLongValue(String query, List params, long defaultVal) {
        if(params ==null || params.size()==0){
            return selectLongValue(query,new String[0],defaultVal);
        }
        
        return selectLongValue(query, (String[]) params.toArray(new String[params.size()]),defaultVal);
    }
    
    /**
     * This method will execute the query and returns the first column value in
     * the first row using PreparedStatement and the query has one fixed parameter
     * 
     * @param  query query to be executed.
     * @param  params Query parameter for the query.
     * @param  defaultVal Default value if no records where found.
     * @return The first column value in the first row.
     */
    public static long selectLongValue(String query, String[] params, long defaultVal) {
        Connection con        = null;
        PreparedStatement stm = null;
        ResultSet rs          = null;
        try  {
            con = getConnection();
            stm = con.prepareStatement(query);

            if (params != null) {
                for (int i = 0; i < params.length; i++)  {
                    stm.setString((i + 1), params[i]);
                }
            }

            rs = stm.executeQuery();
            if (rs.next() == false) {
                return defaultVal;
            }

            return rs.getLong(1);
            
        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(stm);
            close(con);
        }        
    }

    /**
     * This method will execute the query and returns the first column value in
     * the first row.
     * 
     * @param  query query to be executed.
     * @param  defaultVal Default value if no records where found.
     * @return The first column value in the first row.
     */
    public static String selectStringValue(String query, String defaultVal) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try  {
            con = getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(query);

            if (rs.next() == false) {
                return defaultVal;
            }

            return rs.getString(1);
            
        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(stm);
            close(con);
        }        
    }

    /**
     * This method will execute the query and returns the first column value in
     * the first row using PreparedStatement and the query has one fixed parameter
     * 
     * @param  query query to be executed.
     * @param  param key Parameter for the query.
     * @param  defaultVal Default value if no records where found.
     * @return The first column value in the first row.
     */
    public static String selectStringValue(String query, String param, String defaultVal) {
        Connection con        = null;
        PreparedStatement stm = null;
        ResultSet rs          = null;
        try  {
            con = getConnection();
            stm = con.prepareStatement(query);
            stm.setString(1, param);
            rs = stm.executeQuery();
            if (rs.next() == false) {
                return defaultVal;
            }

            return rs.getString(1);
            
        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(stm);
            close(con);
        }        
    }

    /**
     * This method will execute the query and returns the first column value in
     * the first row using PreparedStatement and the query has List of parameters
     * 
     * @param  query query to be executed.
     * @param  params Parameters for the query.
     * @param  defaultVal Default value if no records where found.
     * @return The first column value in the first row.
     */
    public static String selectStringValue(String query, List params, String defaultVal) {
        Connection con        = null;
        PreparedStatement stm = null;
        ResultSet rs          = null;
        try  {
            con = getConnection();
            stm = con.prepareStatement(query);

            if (params != null) {
                for (int i = 0; i < params.size(); i++)  {
                    Object param = params.get(i);
                    stm.setString((i + 1), param.toString());
                }
            }
            rs = stm.executeQuery();
            if (rs.next() == false) {
                return defaultVal;
            }

            return rs.getString(1);
            
        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(stm);
            close(con);
        }        
    }
    
    /**
     * This method will execute the query and returns the first column value in
     * the first row.
     * 
     * @param  query query to be executed.
     * @param  defaultVal Default value if no records where found.
     * @return The first column value in the first row.
     */
    public static double selectDoubleValue(String query, double defaultVal) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try  {
            con = getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(query);

            if (rs.next() == false) {
                return defaultVal;
            }

            return rs.getDouble(1);
            
        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(stm);
            close(con);
        }        
    }

    /**
     * This method will execute the query and returns the first column value in
     * the first row.
     * 
     * @param  query query to be executed.
     * @param  defaultVal Default value if no records where found.
     * @return The first column value in the first row.
     */
    public static float selectFloatValue(String query, float defaultVal) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try  {
            con = getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(query);

            if (rs.next() == false) {
                return defaultVal;
            }

            return rs.getFloat(1);
            
        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(stm);
            close(con);
        }        
    }

    /**
     * This method will execute the query and returns the first column value in
     * the first row.
     * 
     * @param  query query to be executed.
     * @param  defaultVal Default value if no records where found.
     * @return The first column value in the first row.
     */
    public static byte selectByteValue(String query, byte defaultVal) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try  {
            con = getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(query);

            if (rs.next() == false) {
                return defaultVal;
            }

            return rs.getByte(1);
            
        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(stm);
            close(con);
        }        
    }

    /**
     * This method will execute the query and returns the first column value in
     * the first row.
     * 
     * @param  query query to be executed.
     * @param  defaultVal Default value if no records where found.
     * @return The first column value in the first row.
     */
    public static Date selectDateValue(String query) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try  {
            con = getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(query);

            if (rs.next() == false) {
                return null;
            }

            return rs.getDate(1);
            
        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(stm);
            close(con);
        }        
    }

    /**
     * This method will execute the query and returns the first column value in
     * the first row as an object
     * 
     * @param  query query to be executed.
     * @param  defaultVal Default value if no records where found.
     * @return The first column value in the first row.
     */
    public static Object selectObjectValue(String query, Object defaultVal) {
        Connection con = null;
        Statement  stm = null;
        ResultSet  rs  = null;
        try  {
            con = getConnection();
            stm = con.createStatement();
            rs  = stm.executeQuery(query);

            if (rs.next() == false) {
                return defaultVal;
            }

            return rs.getObject(1);
            
        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(stm);
            close(con);
        }        
    }

    /**
     * Execute the query and return the result as array of Map. If no data were
     * found, empty array were found.
     * 
     * @param  query SQL query to be executed.
     * @return query result.
     */
    public static Map[] executeQuery(String query) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try  {
            con = getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(query);

            List list = new ArrayList();
            while (rs.next()) {
                Map map = new HashMap();
                int colCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= colCount; i++)  {
                    String name = rs.getMetaData().getColumnName(i);
                    map.put(name, rs.getObject(name));
                }

                list.add(map);
            }

            Map[] map = new Map[list.size()];
            for (int i = 0; i < list.size(); i++)  {
                map[i] = (Map) list.get(i);
            }

            return map;

        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(stm);
            close(con);
        }        
    }

    /**
     * Execute the query and return the result as java.util.List object.
     * 
     * @param  query SQL query to be executed.
     * @param params query parameters
     * @return query result.
     */
    public static List executeListQuery(String query, List params) {
        if (params == null || params.size() == 0) {
            return executeListQuery(query, new String[0]);
        }
        
        return executeListQuery(query, (String[]) params.toArray(new String[params.size()]));
    }

    /**
     * Execute the query and return the result as java.util.List object.
     * 
     * @param  query SQL query to be executed.
     * @param params query parameters
     * @return query result.
     */
    public static List executeListQuery(String query, String[] params) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try  {
            con = getConnection();
            stm = con.prepareStatement(query);

            if (params != null) {
                for (int i = 0; i < params.length; i++)  {
                    stm.setString((i + 1), params[i]);
                }
            }
            rs = stm.executeQuery();
            List list = new ArrayList();
            while (rs.next()) {
                Map map = new HashMap();
                int colCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= colCount; i++)  {
                    String name = rs.getMetaData().getColumnName(i);
                    map.put(name, rs.getObject(name));
                }

                list.add(map);
            }

            return list;

        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(stm);
            close(con);
        }        
    }

    /**
     * Execute the query and return the result as array of Map.
     * 
     * @param  query SQL query to be executed.
     * @param paramsList query parameters list
     * @return query result.
     */
    public static Map[] executeQuery(String query, List paramsList) {
        String[] params = (String[]) paramsList.toArray(
            new String[paramsList.size()]);

        return executeQuery(query, params);
    }

    /**
     * Execute the query and return the result as array of Map.
     * 
     * @param  query SQL query to be executed.
     * @param params query parameters
     * @return query result.
     */
    public static Map[] executeQuery(String query, String[] params) {
        List list = executeListQuery(query, params);

        Map[] map = new Map[list.size()];
        for (int i = 0; i < list.size(); i++)  {
            map[i] = (Map) list.get(i);
        }

        return map;
    }

    /**
     * Execute the query and return the first row as a map.
     * if not found  found, empty will be send
     * 
     * @param  query SQL query to be executed.
     * @return query result.
     */
    public static Map execSingleRowQry(String query) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try  {
            con = getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(query);

            Map map = new HashMap();
            if (rs.next()) {
                int colCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= colCount; i++)  {
                    String name = rs.getMetaData().getColumnName(i);
                    map.put(name, rs.getObject(name));
                }
            }

            return map;

        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(stm);
            close(con);
        }        
    }

    /**
     * Execute the query and return the first row as a map.
     * if not found  found, empty will be send
     * 
     * @param  query SQL query to be executed.
     * @param  params Parameters for the query.
     * @return query result.
     */
    public static Map execSingleRowQry(String query, List params) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try  {
            con = getConnection();
            stm = con.prepareStatement(query);
            for(int i=0;i<params.size();i++){
                Object param = params.get(i);
                
                stm.setString(i+1, param.toString());
            }
            
            rs = stm.executeQuery();

            Map map = new HashMap();
            if (rs.next()) {
                int colCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= colCount; i++)  {
                    String name = rs.getMetaData().getColumnName(i);
                    map.put(name, rs.getObject(name));
                }
            }

            return map;

        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(stm);
            close(con);
        }        
    }
    
    /**
     * Execute the query and return the first row as a map.
     * if not found  found, empty will be send
     * 
     * @param  query SQL query to be executed.
     * @param  param key Parameter for the query.
     * @return query result.
     */
    public static Map execSingleRowQry(String query, String param) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try  {
            con = getConnection();
            stm = con.prepareStatement(query);
            stm.setString(1, param);
            rs = stm.executeQuery();

            Map map = new HashMap();
            if (rs.next()) {
                int colCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= colCount; i++)  {
                    String name = rs.getMetaData().getColumnName(i);
                    map.put(name, rs.getObject(name));
                }
            }

            return map;

        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(stm);
            close(con);
        }        
    }
    
    /**
     * Execute the query and return the result as Array List of Maps If no data were
     * found, empty array were found.
     * 
     * @param  query SQL query to be executed.
     * @return query result.
     */
    public static List executeQueryList(String query) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try  {
            con = getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(query);

            List list = new ArrayList();
            while (rs.next()) {
                Map map = new HashMap();
                int colCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= colCount; i++)  {
                    String name = rs.getMetaData().getColumnName(i);
                    map.put(name, rs.getObject(name));
                }

                list.add(map);
            }

            return list;

        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(stm);
            close(con);
        }        
    }
    
    /**
     * Execute the query and return the result as Array List of Maps If no data were
     * found, empty array were found.
     * 
     * @param  query SQL query to be executed.
     * @return query result.
     */
    public static List executeQueryList(String query, List param) {
        Connection con = null; 
        PreparedStatement stm = null;
        ResultSet rs = null; 
        try  {
            con = getConnection(); 
            stm = con.prepareStatement(query);
             
            if(param != null){
                for(int i = 0 ; i < param.size() ; i++){
                   stm.setString(i + 1, String.valueOf(param.get(i))); 
                }
            }
            
            rs = stm.executeQuery();
   
            List list = new ArrayList();
            while (rs.next()) {
                Map map = new HashMap();
                int colCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= colCount; i++)  {
                    String name = rs.getMetaData().getColumnName(i);
                    map.put(name, rs.getObject(name));
                }

                list.add(map);
            }

            return list;

        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(stm);
            close(con);
        }        
    }
    /**
     * Execute the query and return the result as map of Maps If no data were
     * found, empty array were found.
     * First column will be the key
     * 
     * @param  query SQL query to be executed.
     * @return query result.
     */
    public static Map executeQueryMap(String query, List param) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try  {
            con = getConnection();
            stm = con.prepareStatement(query);
             
            if(param != null){
                for(int i = 1 ; i < param.size() ; i++){
                   stm.setString(i + 1, param.get(i).toString()); 
                }
            }
            
            rs = stm.executeQuery();

            Map records = new HashMap();
            while (rs.next()) {
                Map map = new HashMap();
                int colCount = rs.getMetaData().getColumnCount();                
                String id   = rs.getObject(rs.getMetaData().getColumnName(1)).toString();
                for (int i = 1; i <= colCount; i++)  {
                    String name = rs.getMetaData().getColumnName(i);
                    map.put(name, rs.getObject(name));
                }

                records.put(id,map);
            }

            return records;

        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(stm);
            close(con);
        }        
    }
    
    
    
    
    
    
    /**
     * Execute the query and return the result as map If no data were
     * found, empty array were found.
     * First column will be the key
     * 
     * @param  query SQL query to be executed.
     * @return query result.
     */
    public static Map executeMapQuery(String query) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try  {
            con = getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(query);

            Map records = new HashMap();
            while (rs.next()) {
                int colCount = rs.getMetaData().getColumnCount();                

                String id   = rs.getObject(rs.getMetaData().getColumnName(1)).toString();
                String desc = rs.getObject(rs.getMetaData().getColumnName(2)).toString();

                records.put(id,desc);
            }

            return records;

        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(stm);
            close(con);
        }        
    }    
    
    
    
    
    
    
    /**
     * Execute the query and return the result as Array List of Strings.
     * 
     * @param  query SQL query to be executed.
     * @return query result.
     */
    public static List execValListQry(String query) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try  {
            con = getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(query);

            List list = new ArrayList();
            while (rs.next()) {
                String name = rs.getMetaData().getColumnName(1);                
                list.add(rs.getObject(name).toString());
            }
            return list;

        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(stm);
            close(con);
        }        
    }
    /**
     * Execute the query and return the result as Array List of Strings.
     *  this overloaded version will use the preparedstatment
     * @param  query SQL query to be executed.
     * @param  param key Parameter for the query.
     * @return query result.
     */
    public static List execValListQry(String query, String param) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try  {
            con = getConnection();
            stm = con.prepareStatement(query);
            stm.setString(1, param);
            rs = stm.executeQuery();

            List list = new ArrayList();
            while (rs.next()) {
                String name = rs.getMetaData().getColumnName(1);                
                list.add(rs.getObject(name).toString());
            }
            return list;

        } catch (SQLException sqlex) {
            throw new TrafficException(sqlex);
        } finally {
            close(rs);
            close(stm);
            close(con);
        }        
    }

    /**
     * Returns the related DB attribute arabic description from CG_REF_CODS 
     * table.
     * 
     * @param  domain The attribute domain name (CG_REF_CODS.RV_DOMAIN)
     * @param  lowValue Attribute value (CG_REF_CODS.RV_LOW_VALUE)
     * @return related DB attribute arabic description from CG_REF_CODS table.
     */
    public static String getRefCodeDesc(String domain, int lowValue) {
        return getRefCodeDesc(domain, String.valueOf(lowValue));
    }

    /**
     * Returns the related DB attribute arabic description from CG_REF_CODS 
     * table.
     * 
     * @param  domain The attribute domain name (CG_REF_CODS.RV_DOMAIN)
     * @param  lowValue Attribute value (CG_REF_CODS.RV_LOW_VALUE)
     * @return related DB attribute arabic description from CG_REF_CODS table.
     */
    public static String getRefCodeDesc(String domain, String lowValue) {
        String query = "{call ? := PKG_DB_MIL_CORE_TOOLS.F_DB_GET_REF_CODE_DESC(?, ?)}";
        Connection con = null;
        CallableStatement func = null;

        try {
            con = JdbcFactory.getConnection();
            func = con.prepareCall(query);

            func.registerOutParameter(1, Types.VARCHAR);
            func.setString(2, domain);
            func.setString(3, lowValue);

            func.execute();
           // return JboUtil.toString(func.getString(1), "");
            return null;

        } catch(SQLException ex) {
            throw new TrafficException(ex);

        } finally {
            close(func);
            close(con);
        }
    }

    /**
     * Returns the related DB attribute arabic description from CG_REF_CODS 
     * table.
     * 
     * @param  domain The attribute domain name (CG_REF_CODS.RV_DOMAIN)
     * @param  lowValue Attribute value (CG_REF_CODS.RV_LOW_VALUE)
     * @return related DB attribute arabic description from CG_REF_CODS table.
     */
    public static String getRefCodeDescEn(String domain, int lowValue) {
        return getRefCodeDescEn(domain, String.valueOf(lowValue));
    }

    /**
     * Returns the related DB attribute arabic description from CG_REF_CODS 
     * table.
     * 
     * @param  domain The attribute domain name (CG_REF_CODS.RV_DOMAIN)
     * @param  lowValue Attribute value (CG_REF_CODS.RV_LOW_VALUE)
     * @return related DB attribute arabic description from CG_REF_CODS table.
     */
    public static String getRefCodeDescEn(String domain, String lowValue) {
        String query = "{call ? := PKG_DB_MIL_CORE_TOOLS.F_DB_GET_REF_CODE_DESC_E(?, ?)}";
        Connection con = null;
        CallableStatement func = null;

        try {
            con = JdbcFactory.getConnection();
            func = con.prepareCall(query);

            func.registerOutParameter(1, Types.VARCHAR);
            func.setString(2, domain);
            func.setString(3, lowValue);

            func.execute();
            //return JboUtil.toString(func.getString(1), "");
            return null;

        } catch(SQLException ex) {
            throw new TrafficException(ex);

        } finally {
            close(func);
            close(con);
        }
    }

}