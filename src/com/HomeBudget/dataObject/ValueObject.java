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
 * 1.01  Eng. Ayman Atiyeh  06/03/2008  - Adding boolean constants.
 * 
 * 1.02  Eng. Ayman Atiyeh  08/05/2008  - Impelemnting toString() using java 
 *                                        reflection API's.
 */

package com.HomeBudget.dataObject;


import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;

import com.HomeBudget.common.GlobalUtilities;

/**
 * Encapsulate common attributes and methods used by mostvalue objects such as
 * ID and creation date.
 *
 * @author Eng. Ayman Atiyeh
 * @version 1.00
 */
public class ValueObject implements Serializable {
    /*
     * Constants and class variables.
     */
     
    /**
     * Traffic boolean values. Traffic database uses numeric values to
     * represent boolean values.
     */
    public static final Integer FALSE = new Integer(1);
    public static final Integer TRUE  = new Integer(2);
    
    /** Lock values */
    public static final int LOCK_MODE_LOCKED = 2;
    public static final int LOCK_MODE_UNLOCKED = 1;
    /*
     * Instance variables.
     */

    /** User name used to create this value object related record. */
    private String createdBy;

    /** Last user name used to update this value object related record. */
    private String updatedBy;

    /** Related record creation date. */
    private Date creationDate;

    /** Related record last update date. */
    private Date updateDate;

    /** Value object ID. */
    private Long id;
    
    /** Employee name used to update this value object related record. */
    private String employeeName;
    
    /** Center name used by user for login. */
    private String centerName;
    
    /** User emirate code from profile */
    private String userEmirateCode;

    /*
     * Constructors
     */

    /**
     * Construct new ValueObject.
     */
    public ValueObject() {
        // Empty body
    }

    /**
     * Construct new ValueObject.
     * 
     * @param newId Value object ID.
     */
    public ValueObject(Long newId) {
        setId(newId);
    }

    /*
     * Methods
     */

    /**
     * Get object String representation. This method is used by toString()
     * method to get object string representation.
     * 
     * @return Object String representation.
     */
    public String toString() {
        return toString(this, "");
    }

    /**
     * Get object String representation. This method is used by toString()
     * method to get object string representation.
     * 
     * @param obj Object to be parsed.
     * @param tab Spaces appended before each line. Used to enhance readability.
     * @return Object String representation.
     */
    public static String toString(Object obj, String tab) {
        // Return null if the argument is null
        if (obj == null) {
            return "null";
        }

        // Class name
        StringBuffer msg = new StringBuffer(tab);
        msg.append(obj.getClass().getName()).append("{\n");

        // Add tab before appending object properties
        String fieldTab = tab + "  ";
        
        // Loop on object methods and print related properties
        Method[] methods = obj.getClass().getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            String methodName = method.getName();
            
            // Skip non getter methods
            if (! methodName.startsWith("get") || methodName.startsWith("getClass")) {
                continue;
            }

            // Invoke the getter method
            try {
                Object rv = method.invoke(obj, null);
                if (rv != null) {
                    Class clazz = rv.getClass();
                    if (clazz != String.class &&
                        clazz != Long.class &&
                        clazz != Integer.class &&
                        clazz != Boolean.class &&
                        !(rv instanceof ValueObject) &&
                        !(rv instanceof Collection) &&
                        ! clazz.isArray() &&
                        clazz != java.util.Date.class &&
                        clazz != java.sql.Date.class &&
                        clazz != Float.class ) {
                        continue;
                    }
                }

                // If argument has nested VO variable, parse it
                if (rv instanceof ValueObject){
                    String fieldName = methodName.substring(3, 4).toLowerCase()
                                     + methodName.substring(4, methodName.length());

                    msg.append(fieldTab).append(fieldName).append("=\n")
                       .append(toString((ValueObject) rv, fieldTab + "  "))
                       .append("\n");

                // Collection
                } else if (rv instanceof Collection) {
                    String fieldName = methodName.substring(3, 4).toLowerCase()
                                     + methodName.substring(4, methodName.length());
                    int size = ((Collection) rv).size();

                    msg.append(fieldTab).append(fieldName).append("=[")
                       .append(size).append("]\n");

                // Array
                } else if (rv != null && rv.getClass().isArray()) {
                    String fieldName = methodName.substring(3, 4).toLowerCase()
                                     + methodName.substring(4, methodName.length());
                    int size = ((Object[]) rv).length;

                    msg.append(fieldTab).append(fieldName).append("=[")
                       .append(size).append("]\n");

                // Not VO or collection, append its value
                } else {
                    String fieldName = methodName.substring(3, 4).toLowerCase()
                                     + methodName.substring(4, methodName.length());

                    String value = (rv == null) ? "" : rv.toString();

                    msg.append(fieldTab).append(fieldName).append("=")
                       .append(value).append("\n");
                }

            } catch (Exception ex)  {
                msg.append(ex);
            }
        }

        msg.append(tab).append("}");
        return msg.toString();
    }

    /**
     * Get User name used to create this value object related record.
     *
     * @return User name used to create this value object related record.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Set User name used to create this value object related record.
     *
     * @param name User name used to create this value object related record.
     */
    public void setCreatedBy(String name) {
        if (name != null) {
            name = name.toLowerCase();
        }

        createdBy = name;
    }

    /**
     * Get Last user name used to update this value object related record.
     *
     * @return Last user name used to update this value object related record.
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * Set Last user name used to update this value object related record.
     *
     * @param name Last user name used to update this value object related record.
     */
    public void setUpdatedBy(String name) {
        if (name != null) {
            name = name.toLowerCase();
        }

        updatedBy = name;
    }

    /**
     * Get Related record creation date.
     *
     * @return Related record creation date.
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Set Related record creation date.
     *
     * @param creationDate Related record creation date.
     */
    public void setCreationDate(Date creationDate) {
        creationDate = creationDate;
    }

    /**
     * Get Related record last update date.
     *
     * @return Related record last update date.
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * Set Related record last update date.
     *
     * @param date Related record last update date.
     */
    public void setUpdateDate(Date date) {
        updateDate = date;
    }

    /**
     * Get Value object ID.
     *
     * @return Value object ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set Value object ID.
     *
     * @param newId Value object ID.
     */
    public void setId(Long newId) {
        id = newId;
    }

    /**
     * Set Employee name used to update this value object related record.
     * 
     * @param empName Employee name used to update this value object related record.
     */
    public void setEmployeeName(String empName) {
        this.employeeName = empName;
    }

    /**
     * Get Employee name used to update this value object related record.
     * 
     * @return Employee name used to update this value object related record.
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * Set Center name used by user for login.
     * 
     * @param ctrName Center name used by user for login.
     */
    public void setCenterName(String ctrName) {
        this.centerName = ctrName;
    }

    /**
     * Get Center name used by user for login.
     * 
     * @return Center name used by user for login.
     */
    public String getCenterName() {
        return centerName;
    }


    /**
     * Set User Emirate Code 
     * 
     * @param userEmirateCode User emirate code from porfile
     */
    public void setUserEmirateCode(String userEmirateCode) {
        this.userEmirateCode = userEmirateCode;
    }

    /**
     * Get User Emirate Code
     * 
     * @return User emirate code
     */
    public String getUserEmirateCode() {
        return userEmirateCode;
    }
    /**
     * Checks if the field is not null or contains only blank spaces.
     * 
     * @param value String value to be checked.
     * @return true if the field is not null or blank.
     */
    protected static boolean isBlankOrNull(String value) {
       return GlobalUtilities.isBlankOrNull(value);
    }
        
    /**
     * get Boolean Arabic Description 
     * 
     * @return 
     * @param value
     */
    protected String getBooleanDescAr(Integer value) {
        return (value != null && value.equals(TRUE)) ? "���" : "��";
    }
    
    /**
     * Compares this object to the specified object.  The result is
     * <code>true</code> if and only if the argument is not
     * <code>null</code> and is a <code>ValueObject</code> object that
     * contains the same <code>Id</code> value as this object.
     *
     * @param   obj   the object to compare with.
     * @return  <code>true</code> if the objects are the same;
     *          <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {
        
        if (obj == null) {
            return false;        
        }
        
        if (!(obj instanceof ValueObject)) {
            return false;
        }
        
        ValueObject vo = (ValueObject) obj;
        
        if (vo.getId() == null || this.getId() == null) {
            return false;
        }
        
        return this.getId().equals(vo.getId());
    }

    /**
     * Get Long object or null if the value is null or empty string.
     * 
     * @param value String value to be tested and parsed.
     * @return Long object or null if the value is null or empty string.
     */
    protected Long getLong(Object value) {
       return GlobalUtilities.getLong(value);
    }

    /**
     * Get Integer object or null if the value is null or empty string.
     * 
     * @param value String value to be tested and parsed.
     * @return Integer object or null if the value is null or empty string.
     */
    protected Integer getInteger(Object value) {
       return GlobalUtilities.getInteger(value);
    }

}