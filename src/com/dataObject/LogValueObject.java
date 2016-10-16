/*
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  28/02/2008  - File created.
 */

package com.dataObject;

/**
 * Encapsulate common attributes and methods used by most logs value objects
 * such as actionType.
 *
 * @author Eng. Ayman Atiyeh
 * @version 1.00
 */
public class LogValueObject extends ValueObject {
    /*
     * Constants and class variables.
     */
     
    /** Logs action type. */
    public static final Integer ACTION_TYPE_ADD = new Integer(1);
    public static final Integer ACTION_TYPE_UPDATE = new Integer(2);
    public static final Integer ACTION_TYPE_DELETE = new Integer(3);


    /*
     * Instance variables.
     */

    /** Log action type. */
    private Integer actionType;
    
    /** Log action type description **/
    private String actionTypeDesc;

    /*
     * Methods
     */

    /**
     * Returns the String representation of this object.
     * 
     * @return String representation of this object.
     */
    public String toString() {
        StringBuffer buf = new StringBuffer(super.toString());
        buf.append( ", actionType=").append(getActionType());

        return buf.toString();
    }

    /**
     * Set  Log action type.
     * 
     * @param actionType  Log action type.
     */
    public void setActionType(Integer actionType) {
            this.actionType = actionType;
    }

    /**
     * Get  Log action type.
     * 
     * @return  Log action type.
     */
    public Integer getActionType() {
        return actionType;
    }


    /**
     * Set Log action type description
     * @param actionTypeDesc
     */
    public void setActionTypeDesc(String actionTypeDesc)
    {
        this.actionTypeDesc = actionTypeDesc;
    }


    /**
     * Get Log action type description
     * @return 
     */
    public String getActionTypeDesc()
    {
        return actionTypeDesc;
    }
    /**
     * check if action Type Add
     * 
     * @return true if action type add
     */
    public boolean isActionTypeAdd(Integer actionType){
        return actionType != null && actionType.equals(ACTION_TYPE_ADD);
    }    

    /**
     * check if action Type Update
     * 
     * @return true if action type update
     */
    public boolean isActionTypeUpdate(Integer actionType){
        return actionType != null && actionType.equals(ACTION_TYPE_UPDATE);
    }   

    /**
     * check if action Type Delete
     * 
     * @return true if action type delete
     */
    public boolean isActionTypeDelete(Integer actionType){
        return actionType != null && actionType.equals(ACTION_TYPE_DELETE);
    }            


                    
}