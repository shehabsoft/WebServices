/*
 * Copyright (c) i-Soft 2006.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  25/01/2010  - File created.
 */

package com.dataObject;

import java.io.Serializable;

/**
 * Traffic system uses "1" for false and "2" for true to save boolean flags in
 * database. This class used to map database boolean values to Java code.
 *
 * @author Eng. Ayman Atiyeh
 * @version 1.00
 */
public class Choice extends DataType {
    /*
     * Contants
     */

    /** Integer choice value. */
    public static final Integer TRUE = new Integer(2);
    public static final Integer FALSE = new Integer(1);

    /** Choice value. */
    public static final Choice YES = new Choice(TRUE);
    public static final Choice NO = new Choice(FALSE);

    private Serializable domainValue;

    public void setDomainValue(Serializable value) {
        super.setDomainValue(value);
    }

    public Serializable getDomainValue() {
        return (Serializable) super.getDomainValue();
    }
    /*
     * Cnstructors
     */
    
    /**
     * Default constructor.
     */
    protected Choice() {
        // Empty Body
    }
    
    /**
     * Create new choice object.
     * 
     * @param choice Domain value.
     */
    public Choice(String choice) {
        setDomainValue(choice);
    }

    /**
     * Create new choice object.
     * 
     * @param choice Domain value.
     */
    public Choice(Integer choice) {
        setDomainValue(choice);
    }

    /**
     * Create new choice object.
     * 
     * @param choice Domain value.
     */
    public Choice(int choice) {
        setDomainValue(new Integer(choice));
    }

    /**
     * Create new choice object.
     * 
     * @param choice Domain value.
     */
    public Choice(Long choice) {
        setDomainValue(choice);
    }

    /**
     * Create new choice object.
     * 
     * @param choice Domain value.
     */
    public Choice(boolean choice) {
        if (choice == true) {
            setDomainValue(TRUE);
        } else {
            setDomainValue(FALSE);
        }
    }

    /**
     * Create new choice object.
     * 
     * @param choice Domain value.
     */
    public Choice(Boolean choice) {
        setDomainValue(choice);
    }
    
    /**
     * Create new choice object.
     * 
     * @param choice Domain value.
     */
    public Choice(Choice value) {
        if (value == null || value.getBoolean() == false) {
            setDomainValue(FALSE);
        } else {
            setDomainValue(TRUE);
        }
    }

    /*
     * Class Methods
     */

    /**
     * Get Integer value for this choice.
     * 
     * @param value Coice domain value.
     * @return Integer value for this choice.
     */
    public static Integer getInteger(String value) {
        return new Choice(value).getInteger();
    }
    
    /**
     * Get Integer value for this choice.
     * 
     * @param value Coice domain value.
     * @return Integer value for this choice.
     */
    public static Integer getInteger(boolean value) {
        return new Choice(value).getInteger();
    }

    /**
     * Get Integer value for this choice.
     * 
     * @param value Coice domain value.
     * @return Integer value for this choice.
     */
    public static Integer getInteger(Boolean value) {
        return new Choice(value).getInteger();
    }

    /**
     * Get Integer value for this choice.
     * 
     * @param value Coice domain value.
     * @return Integer value for this choice.
     */
    public static Integer getInteger(Choice value) {
        return value == null ? NO.getInteger() : value.getInteger();
    }

    /**
     * Get boolean value for this choice.
     * 
     * @param value Coice domain value.
     * @return boolean value for this choice.
     */
    public static boolean getBoolean(Choice value) {
        boolean finalValue = false;
        if(value != null) {
            finalValue = value.getInteger().intValue() == YES.getInteger().intValue();
        }
        
        return finalValue;
    }

    /**
     * Get Integer value for this choice.
     * 
     * @param value Coice domain value.
     * @return Integer value for this choice.
     */
    public static Integer getInteger(int value) {
        return new Choice(value).getInteger();
    }

    /**
     * Get Integer value for this choice.
     * 
     * @param value Coice domain value.
     * @return Integer value for this choice.
     */
    public static String getString(Choice value) {
        return value == null ? NO.getString() : value.getString();
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
        return getInteger().toString();
    }

    /**
     * Check if this domain value is valid.
     * 
     * @param value Domain value.
     * @return true if this domain value is valid.
     */
    protected boolean isValidDomainValue(Object value) {
        // Validate NULL values
        if (value == null) {
            return true;
        }
        
        // Validate Boolean values
        if (value instanceof Boolean) {
            return true;
        }
        
        // Validate Integer values
        if (value instanceof Integer) {
            Integer choice = (Integer) value;
            return choice.equals(TRUE) || choice.equals(FALSE);
        }
        
        // Validate Long values
        if (value instanceof Long) {
            int choice = ((Long) value).intValue();
            return choice == TRUE.intValue() || choice == FALSE.intValue();
        }

        // Validate String values
        return value.toString().equals(TRUE.toString()) ||
               value.toString().equals(FALSE.toString());
    }
    
    /**
     * Get Integer value for this choice.
     * 
     * @return Integer value for this choice.
     */
    public Integer getInteger() {
        // Validate NULL values
        if (getDomainValue() == null) {
            return FALSE;
        }

        // Validate Boolean values
        if (getDomainValue() instanceof Boolean) {
            return ((Boolean) getDomainValue()).booleanValue() ? TRUE : FALSE;
        }
        
        // Validate Integer values
        if (getDomainValue() instanceof Integer) {
            return (Integer) getDomainValue();
        }
        
        // Validate Long values
        if (getDomainValue() instanceof Long) {
            int choice = ((Long) getDomainValue()).intValue();
            return choice == TRUE.intValue() ? TRUE : FALSE;
        }

        // Validate String values
        return getDomainValue().toString().equals(TRUE.toString()) ? TRUE : FALSE;
    }

    /**
     * Get boolean value for this choice.
     * 
     * @return boolean value for this choice.
     */
    public boolean getBoolean() {
        Integer choice = getInteger();
        return choice != null && choice.equals(TRUE);
    }

    /**
     * Get String value for this choice.
     * 
     * @return String value for this choice.
     */
    public String getString() {
        Integer choice = getInteger();
        return choice == null ? null : choice.toString();
    }
    
    /**
     * Get Arabic choice description.
     * 
     * @return Arabic choice description.
     */
    public String getDescriptionAr() {
        return getBoolean() ? "‰⁄„" : "·«";
    }

    /**
     * Get English choice description.
     * 
     * @return English choice description.
     */
    public String getDescriptionEn() {
        return getBoolean() ? "Yes" : "No";
    }
    
    /**
     * Compares this object to the specified object.
     * The result is true if and only if the argument 
     * is not null and is an Choice object that
     *  contains the same value as this object.
     *
     * @param   object the object to compare with.
     * @return  true if the objects are the same;
     *          false otherwise.
     */   
    public boolean equals(Object object) {
        
        if (this == object) {
            return true;
        }
        
        if (!(object instanceof Choice)) {
            return false;
        }
        
        Choice obj = (Choice) object;
        
        if (this.getInteger() == null || obj.getInteger() == null) {
            return false;
        }

        return this.getInteger().equals(obj.getInteger());
    }
}