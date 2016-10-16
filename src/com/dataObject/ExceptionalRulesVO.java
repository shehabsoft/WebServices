/*
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 * * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Mohamed Fayek  01/04/2014  - File created.
 */

//package ae.rta.eps.vo;
package com.dataObject;



/**
 * Exceptiona Rules value object.
 *
 * @author Mohamed Fayek
 * @version 1.00
 */
public class ExceptionalRulesVO extends ValueObject {
     
    /*
     * Instance variables.
     */
    
     /** exceptions VO */
     private ExceptionsVO exceptionsVO;
     
     /** business Rule VO */
     private BusinessRuleVO businessRuleVO;
     
     /** disabled Exceptional */
     private String disabledExceptional;
    
    /*
     * Constructors
     */

    /**
     * Default constructor.
     */
    public ExceptionalRulesVO() {
        // Empty body
    }
    
    /**
     * Set exceptionsVO
     *
     * @param exceptionsVO : exceptions VO.
     */
    public void setExceptionsVO(ExceptionsVO exceptionsVO) {
        this.exceptionsVO = exceptionsVO;
    }

    /**
     * Get exceptionsVO.
     *
     * @return exceptions VO.
     */
    public ExceptionsVO getExceptionsVO() {
        return exceptionsVO;
    }

    /**
     * Set businessRuleVO
     *
     * @param businessRuleVO : business Rule VO.
     */
    public void setBusinessRuleVO(BusinessRuleVO businessRuleVO) {
        this.businessRuleVO = businessRuleVO;
    }

    /**
     * Get businessRuleVO.
     *
     * @return business Rule VO.
     */
    public BusinessRuleVO getBusinessRuleVO() {
        return businessRuleVO;
    }

    /**
     * Set disabledExceptional
     *
     * @param disabledExceptional : disabled Exceptional.
     */
    public void setDisabledExceptional(String disabledExceptional) {
        this.disabledExceptional = disabledExceptional;
    }

    /**
     * Get disabledExceptional.
     *
     * @return disabled Exceptional.
     */
    public String getDisabledExceptional() {
        return disabledExceptional;
    }

}