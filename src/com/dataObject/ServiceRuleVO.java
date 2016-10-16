/*
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Ali Abdel-Aziz     17/01/2010  - File created.
 * 
 * 1.02  Mena Emiel         09/02/2010  - Add Implementation.
 */

package com.dataObject;



/**
 * Service Rule value object.
 *
 * @author Ali Abdel-Aziz
 * @version 1.00
 */
public class ServiceRuleVO extends ValueObject {
    /*
     * Instance variables.
     */
    
    /** service VO. */
    private ServiceVO service;
    
    /** business Rule VO. */
    private BusinessRuleVO businessRule;
    
    /** urlValue .*/
    private String urlValue;
    
    /** url Arabic Value .*/
    private String urlArabicValue;
    
    /** url English Value .*/
    private String urlEnglishValue;
    
    /*
     * Constructors
     */

    /**
     * Default constructor.
     */
    public ServiceRuleVO() {
        // Empty body
    }
    
    /**
     * setter for service
     * @param service
     */
    public void setService(ServiceVO service) {
        this.service = service;
    }

    /**
     * getter for service
     * @return service
     */
    public ServiceVO getService() {
        return service;
    }

    /**
     * setter for businessRule
     * @param businessRule
     */
    public void setBusinessRule(BusinessRuleVO businessRule) {
        this.businessRule = businessRule;
    }

    /**
     * getter for businessRule
     * @return businessRule
     */
    public BusinessRuleVO getBusinessRule() {
        return businessRule;
    }

    /**
     * setter for UrlValue
     * @param urlValue
     */
    public void setUrlValue(String urlValue) {
        this.urlValue = urlValue;
    }

    /**
     * getter for UrlValue
     * @return String
     */
    public String getUrlValue() {
        return urlValue;
    }

    /**
     * setter for UrlArabicValue
     * @param urlArabicValue
     */
    public void setUrlArabicValue(String urlArabicValue) {
        this.urlArabicValue = urlArabicValue;
    }

    /**
     * getter for UrlArabicValue
     * @return String
     */
    public String getUrlArabicValue() {
        return urlArabicValue;
    }

    /**
     * setter for UrlEnglishValue
     * @param urlEnglishValue
     */
    public void setUrlEnglishValue(String urlEnglishValue) {
        this.urlEnglishValue = urlEnglishValue;
    }

    /**
     * getter for UrlEnglishValue
     * @return 
     */
    public String getUrlEnglishValue() {
        return urlEnglishValue;
    }
}