/*
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  29/12/2009  - File created.
 * 
 * 1.01  Eng. Ayman Atiyeh  21/04/2012  - Add truncate() method.
 *                                      - Overload setParameter() method.
 */

//package ae.rta.util.dao.jdbc;
package com.dataObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import com.HomeBudget.common.GlobalUtilities;

/**
 * JDBC named query class.
 *
 * @author Eng. Ayman Atiyeh
 * @version 1.00
 */
public class NamedQuery {
    /*
     * Constants
     */
    
    /** SQL charachters used to split named query for parsing. */
    private static final String SQL_TOKENS = ": (),'=<>\n\t";

    /*
     * Instance variables
     */

    /** Query name. */
    private String name;
    
    /** SQL query. */
    private StringBuffer query = new StringBuffer();
    
    /** Query parameters. */
    private Map parameters = new HashMap();
    
    /*
     * Constructors
     */

    /**
     * Default constructor
     */
    public NamedQuery() {
        // Empty body
    }
    
    /**
     * Construct and initialize new NamedQuery object.
     * 
     * @param name Query name.
     * @param query SQL query code.
     */
    public NamedQuery(String name, String query) {
        setName(name);
        setQuery(query);
    }

    /**
     * Construct and initialize new NamedQuery object.
     * 
     * @param name Query name.
     * @param query SQL query code.
     */
    public NamedQuery(NamedQuery namedQuery) {
        if (namedQuery == null) {
            throw new DataAccessException("NULL NamedQuery parameter");
        }
        
        setName(namedQuery.getName());
        setQuery(namedQuery.getQuery());
    }

    /*
     * Methods
     */

     /**
      * Returns the String representation of this object.
      * 
      * @return String representation of this object.
      */
     public String toString() {
         StringBuffer buf = new StringBuffer();
         buf.append("\n").append(getName())
            .append("\n").append(getQuery());
        
        if (parameters != null && parameters.isEmpty() == false) {
            buf.append("\nparameters=[");
            Iterator keys = parameters.keySet().iterator();
            
            while (keys.hasNext()) {
                String name = keys.next().toString();
                buf.append("\n\t")
                   .append(name).append("=");
                
                Object parameterValue = parameters.get(name);
                if(parameterValue != null){
                    if (parameterValue.getClass().isArray()) {
                        Object[] objectArray = (Object[]) parameterValue;
                        buf.append("[");
                        for (int i = 0; i < objectArray.length; i++) {
                            if (i > 0) {
                                buf.append(",\t\n");
                            }
                            buf.append(objectArray[i]);
                        }
                        buf.append("\t]");
    
                    } else if (parameterValue instanceof List) {
                        List list = (List) parameterValue;
                        for (int i = 0; i < list.size(); i++)  {
                            if (i > 0) {
                                buf.append(",\t\n");
                            }
                            buf.append("?");
                            buf.append(list.get(i));
                        }
    
                    } else if (parameterValue instanceof Set) {
                        Set set = (Set) parameterValue;
                        Iterator itr = set.iterator();
                        boolean firstParameter = true;
                        while(itr.hasNext()) {
                            if (firstParameter) {
                                firstParameter = false;
                            } else {
                                buf.append(",\t\n");
                            }
                            buf.append("?");
                            buf.append(itr.next());
                        }
    
                    } else {
                        buf.append(parameterValue);
                    }
                }
            }
            
            buf.append("\n]");
        }
        
        return buf.toString();
     }

    /**
     * Set Query name.
     * 
     * @param name Query name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Get Query name.
     * 
     * @return Query name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Set SQL query.
     * 
     * @param sql SQL query.
     */
    public void setQuery(String sql) {
        if (query.length() > 0) {
            query.delete(0, sql.length());
        }
        
        query.append(sql);
    }
    
    /**
     * Get SQL query.
     * 
     * @return SQL query.
     */
    public String getQuery() {
        return query.toString();
    }

    /**
     * Set Query parameters.
     * 
     * @param parameters Query parameters.
     */
    public void setParameters(Map parameters) {
        if (parameters == null) {
            throw new DataAccessException("NULL parameters map");
        }

        this.parameters = parameters;
    }

    /**
     * Get Query parameters names on the same order defined by the named query.
     * 
     * @return Query parameters names on the same order defined by the named query.
     */
    public Map getParameters() {
        return parameters;
    }

    /**
     * Set query parameter value.
     * 
     * @param name Parameter name.
     * @param value Parameter value.
     */
    public void setParameter(String name, Object value) {
        // Validate parameters
        if (GlobalUtilities.isBlankOrNull(name)) {
            throw new DataAccessException("Invalid query parameter name: " + name);
        }

        this.parameters.put(name, value);
    }

    /**
     * Set query parameter value.
     * 
     * @param name Parameter name.
     * @param value Parameter value.
     * @param maxLength Max allowed length.
     */
    public void setParameter(String name, Object value, int maxLength) {
        // Call overloaded method
        setParameter(name, truncate(value, maxLength));
    }

    /**
     * Append where clause to origional query.
     * 
     * @param sql SQL where clause.
     */
    public void appendWhereClause(String sql) {
        if (GlobalUtilities.isBlankOrNull(sql)) {
            throw new DataAccessException("NULL appended where clause");
        }
        
        this.query.append(" ").append(sql);
    }

    /**
     * Append SQL code origional query.
     * 
     * @param sql SQL code to be appended.
     * @param index Appended SQL code index.
     */
    public void appendQuery(String sql, int index) {
        if (GlobalUtilities.isBlankOrNull(sql)) {
            throw new DataAccessException("NULL appended where clause");
        }
        
        if (index < 0 || index > query.length()) {
            throw new DataAccessException(new StringBuffer(
                "Invalid appended where clause index: index=")
                .append(index).append(", queryLength=")
                .append(query.length()).toString());
        }

        this.query.insert(index, sql);
    }

    /**
     * Update query string code by replacing oldSQL code with newSQL code.
     * 
     * @param oldSQL Old SQL query code to be replaced.
     * @param newSQL New SQL code to replace the old one.
     */
    public void replaceQueryCode(String oldSQL, String newSQL) {
        if (GlobalUtilities.isBlankOrNull(oldSQL)) {
            throw new DataAccessException("NULL old SQL query parameter");
        }
        
        int startIndex;
        while ((startIndex = query.indexOf(oldSQL)) != -1) {
            int endIndex = startIndex + oldSQL.length();
            query.replace(startIndex, endIndex, newSQL);
        }
    }

    /**
     * Append where clause to origional query.
     * 
     * @param sql SQL where clause.
     * @param paramName Query parameter name.
     * @param paramValue Query parameter value.
     */
    public void appendWhereClause(String sql, String paramName, Object paramValue) {
        if (GlobalUtilities.isBlankOrNull(sql)) {
            throw new DataAccessException("NULL appended where clause");
        }
        
        setParameter(paramName, paramValue);
        this.query.append("\n ").append(sql);
    }
    
    /**
     * Format query String and return its related SQL query by replacing all
     * named parameters with '?' charachter.
     * 
     * @return Related SQL query which includes '?' instead of named parameters.
     */
    public String getSqlQuery() {
        if (this.query == null || this.query.length() == 0) {
            return "";
        }

        return (String) getSqlMetaData()[0];
    }

    /**
     * Get related SQL query parameters list.
     * 
     * @return Related SQL query parameters list.
     */
    public List getSqlParameters() {
        if (this.query == null || this.query.length() == 0) {
            return new ArrayList();
        }
    
        return (List) getSqlMetaData()[1];
    }
    
    /**
     * Return related SQL query and parameters. This method is used to enhance
     * quey execution performance by parsing the named query once and instead 
     * of using getSqlQuery() and getSqlParameters() methods separetly which
     * will parse the named-query twice.
     * 
     * @return Related SQL info, Object[0]=sqlQuery, Object[1]=sqlParametersList.
     */
    public Object[] getSqlMetaData() {
        if (this.query == null || this.query.length() == 0) {
            return new Object[]{"", new ArrayList()};
        }

        // Create query StringBuffer and parameters list
        StringBuffer sql = new StringBuffer();
        List params = new ArrayList();

        // Create StringTokenizer to parse the named query
        StringTokenizer tokenizer = new StringTokenizer(
            this.query.toString(), SQL_TOKENS, true);
        
        boolean isParameter = false;
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();

            if (isParameter) {
                // Validate date/time key-words
                if (token.equalsIgnoreCase("MI") || 
                    token.equalsIgnoreCase("SS") ||
                    token.equalsIgnoreCase("HH") ||
                    token.equalsIgnoreCase("H24")||
                    GlobalUtilities.isLong("" + token.charAt(0))) {
                    // Time key-words are not parameters
                    sql.append(":").append(token);
                    isParameter = false;
                    continue;
                }

                // Add parameter value to parameters list
                if (! this.parameters.containsKey(token)) {
                    throw new DataAccessException(
                        "Parameter was not initialized:" + token);
                }

                // Add bind variable to SQL query
                Object parameterValue = this.parameters.get(token);

                if (parameterValue == null) {
                    params.add(parameterValue);
                    sql.append("?");

                } else if (parameterValue.getClass().isArray()) {
                    Object[] objectArray = (Object[]) parameterValue;
                    for (int i = 0; i < objectArray.length; i++)  {
                        if (i > 0) {
                            sql.append(",");
                        }
                        sql.append("?");
                        params.add(objectArray[i]);
                    }

                } else if (parameterValue instanceof List) {
                    List list = (List) parameterValue;
                    for (int i = 0; i < list.size(); i++)  {
                        if (i > 0) {
                            sql.append(",");
                        }
                        sql.append("?");
                        params.add(list.get(i));
                    }

                } else if (parameterValue instanceof Set) {
                    Set set = (Set) parameterValue;
                    Iterator itr = set.iterator();
                    boolean firstParameter = true;
                    while(itr.hasNext()) {
                        if (firstParameter) {
                            firstParameter = false;
                        } else {
                            sql.append(",");
                        }
                        sql.append("?");
                        params.add(itr.next());
                    }

                } else {
                    params.add(parameterValue);
                    sql.append("?");
                }
                
                isParameter = false;
                continue;
            }

            if (token.equals(":")) {
                isParameter = true;
                continue;
            }
            
            // Add SQL text to SQL query
            sql.append(token);
        }

        // Return related SQL data
        return new Object[]{sql.toString(), params};
    }

    /**
     * Truncate String object parameter. Value data type must be String.
     * 
     * @param value Parameter value.
     * @param maxLength Max allowed length.
     * @return truncated value.
     */
    private static Object truncate(Object value, int maxLength) {
        // Validate parameters
        if (value == null) {
            return value;
        }

        if (! (value instanceof String)) {
            throw new DataAccessException("You can truncate only String parameters: " 
                + value.getClass().getName());
        }
        
        if (maxLength <= 0) {
            throw new DataAccessException("maxLength parameter must be > zero: " 
                + maxLength);
        }
        
        // Check if value length is less than maxLength
        if (value.toString().length() <= maxLength) {
            return value;
        }
        
        // Truncate value text
        return value.toString().substring(0, maxLength);
    }
}