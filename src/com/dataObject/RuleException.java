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
 * 1.01  Eng. Ayman Atiyeh  03/03/2008  - Adding rule parameters list.
 */

package com.dataObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Business layer exception. This kind of exceptions will be thrown when a 
 * business rule is violated.
 *
 * @author Eng. shehab .tarek
 * @version 1.00
 */
public class RuleException extends BusinessException {
    /*
     * Instance variables
     */
    
    /** Error code. */
    private String errorCode;
    
    /** Business rule parameters list. */
    private List parametersList = new ArrayList();
    
    /** Business rule parameters list English. */
    private List parametersListEn = new ArrayList();

    /*
     * Constructors.
     */

    /**
     * Construct new RuleException object.
     */
    public RuleException() {
    	super();
    }

    /**
     * Construct new RuleException object.
     * 
     * @param code error code.
     */
    public RuleException(String code) {
        setErrorCode(code);
    }

    /**
     * Construct new RuleException object.
     * 
     * @param code error code.
     * @param parameter Business rule parameter.
     */
    public RuleException(String code, String parameter) {
        this(code);
        addParameter(parameter);
    }

    /**
     * Construct new RuleException object.
     * 
     * @param code error code.
     * @param parameter Business rule parameter.
     */
    public RuleException(String code, String parameter1, String parameter2) {
        this(code, parameter1);
        addParameter(parameter2);
    }

    /**
     * Construct new RuleException object.
     * 
     * @param code error code.
     * @param parameter Business rule parameter.
     */
    public RuleException(String code, String parameter1, String parameter2, 
                         String parameter3) {
        // Set code and parameters
        this(code, parameter1, parameter2);
        addParameter(parameter3);
    }

    /**
     * Nest the geneterated exception inside RuleException object.
     * 
     * @param code error code.
     * @param nestedException Generated exception.
     */
    public RuleException(String code, Throwable nestedException) {
        super(nestedException);
        setErrorCode(code);
    }

    /*
     * Methods
     */

    /**
     * Add rule parameter. This parameter will be added to business rule
     * description.
     * 
     * @param parameter rule parameter.
     */
    public void addParameter(String parameter) {
        if (parameter == null) {
            throw new IllegalArgumentException("NULL parameter");
        }

        this.parametersList.add(parameter);
    }
    
    /**
     * Add English rule parameter. This parameter will be added to business rule
     * description.
     * 
     * @param parameter rule parameter.
     */
    public void addParameterEn(String parameterEn) {
        if (parameterEn == null) {
            throw new IllegalArgumentException("NULL parameterEn");
        }

        this.parametersListEn.add(parameterEn);
    }

    /**
     * Get rule parameters.
     * 
     * @return rule parameters.
     */
    public String[] getParameters() {
        if (this.parametersList.isEmpty()) {
            return new String[0];
        }

        return (String[]) parametersList.toArray(new String[parametersList.size()]);
    }
    
    /**
     * Get english rule parameters.
     * 
     * @return rule parameters.
     */
    public String[] getParametersEn() {
        if (this.parametersListEn.isEmpty()) {
            return new String[0];
        }

        return (String[]) parametersListEn.toArray(new String[parametersListEn.size()]);
    }
    
    /**
     * Check if this business rule has parameters or not.
     * 
     * @return if this business rule has parameters or not.
     */
    public boolean hasParameters() {
        return ! this.parametersList.isEmpty();
    }
    
    /**
     * Check if this business rule has english parameters or not.
     * 
     * @return if this business rule has english parameters or not.
     */
    public boolean hasParametersEn() {
        return ! this.parametersListEn.isEmpty();
    }

    /**
     * Overrides toString() method.
     * 
     * @return String representation of this exception.
     */
    public String toString() {
        StringBuffer msg = new StringBuffer();
        msg.append(getErrorCode());
        
        if (hasParameters()) {
            msg.append("\n, Parameters {");
            
            for (int i = 0; i < parametersList.size(); i++)  {
                if (i > 0) {
                    msg.append(", ");
                }
                
                msg.append(parametersList.get(i));
            }
            
            msg.append("}");
        }

        msg.append("\n, ")
           .append(" : ")
           .append(super.toString());

        return msg.toString();
    }

    /**
     * Overrides getMessage() to append error code.
     * 
     * @return Nested exception message.
     */
    public String getMessage() {
        StringBuffer msg = new StringBuffer();
        msg.append(getErrorCode())
          .append(" : ")
          .append(super.getMessage());

        return msg.toString();
    }

    /**
     * Set error code.
     * 
     * @param errorCode Error code.
     */
    public void setErrorCode(String code) {
        this.errorCode = code;
    }

    /**
     * Get error code.
     * 
     * @return error code.
     */
    public String getErrorCode() {
        return this.errorCode;
    }
}