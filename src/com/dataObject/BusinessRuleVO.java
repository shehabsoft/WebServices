/*
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  17/01/2008  - File created.
 */

package com.dataObject;

import java.io.Serializable;

/**
 * Encapsulate business rules key and description.
 *
 * @author Eng. shehab eldin tarek
 * @version 1.00
 */
public class BusinessRuleVO implements Serializable {
    /*
     * Constants.
     */
    public static final String GEN_RULE_CODE = "BR_GEN_001";
    
    /*
     * Instance variables
     */

    /** Business rule arabic description. */
    private String descriptionAr;

    /** Business rule english description. */
    private String descriptionEn;

    /** Business rule key. */
    private String key;
    
    /** business Rules. */
    private String businessRules;
    
    /** has Exception. */ 
    private boolean hasException;
    
    /** exception Description. */ 
    private String exceptionDescription;
    
    /** exception Reason Id. */ 
    private Long exceptionReasonId;
    
    /** user Has Authentication On Business Rule. */ 
    private String userHasAuthenticationOnBusinessRule;
    
    /*
     * Constructors
     */
    
    /**
     * Construct new BusinessRuleVO object.
     */
    public BusinessRuleVO() {
        // Empty body
    }
    
    /**
     * Construct and initialize new BusinessRuleVO object.
     * 
     * @param newKey Business rule key.
     */
    public BusinessRuleVO(String newKey) {
        setKey(newKey);
    }
    
    /**
     * Construct and initialize new BusinessRuleVO object.
     * 
     * @param newKey Business rule key.
     * @param descAr Business rule arabic description.
     */
    public BusinessRuleVO(String newKey, String descAr) {
        this(newKey);
        setDescriptionAr(descAr);
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
        StringBuffer buf = new StringBuffer(getClass().getName());
        buf.append( "{key=").append(getKey())
           .append(", descriptionAr=").append(getDescriptionAr())
           .append(", descriptionEn=").append(getDescriptionEn())
           .append("}");

        return buf.toString();
    }

    /**
     * Set Business rule arabic description.
     * 
     * @param descAr Business rule arabic description.
     */
    public void setDescriptionAr(String descAr) {
        this.descriptionAr = descAr;
    }

    /**
     * Get Business rule arabic description.
     * 
     * @return Business rule arabic description.
     */
    public String getDescriptionAr() {
        return descriptionAr;
    }

    /**
     * Set Business rule key.
     * 
     * @param newkey Business rule key.
     */
    public void setKey(String newkey) {
        this.key = newkey;
    }

    /**
     * Get Business rule key.
     * 
     * @return Business rule key.
     */
    public String getKey() {
        return key;
    }

    /**
     * Set Business rule english description.
     * 
     * @param descriptionEn Business rule english description.
     */
    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    /**
     * Get Business rule english description.
     * 
     * @return Business rule english description.
     */
    public String getDescriptionEn() {
        return descriptionEn;
    }

    /**
     * Set business Rules.
     * 
     * @param businessRules : business Rules.
     */
    public void setBusinessRules(String businessRules) {
        this.businessRules = businessRules;
    }

    /**
     * Get business Rules.
     * 
     * @return businessRules: business Rules.
     */
    public String getBusinessRules() {
        return businessRules;
    }
    
    /**
     * Set has Exception.
     * 
     * @param hasException : has Exception.
     */
    public void setHasException(boolean hasException) {
        this.hasException = hasException;
    }

    /**
     * Get hasException.
     * 
     * @return hasException : has Exception.
     */
    public boolean isHasException() {
        return hasException;
    }

    /**
     * Set exceptionDescription.
     * 
     * @param exceptionDescription : exception Description.
     */
    public void setExceptionDescription(String exceptionDescription) {
        this.exceptionDescription = exceptionDescription;
    }

    /**
     * Get exceptionDescription.
     * 
     * @return exceptionDescription : exception Description.
     */
    public String getExceptionDescription() {
        return exceptionDescription;
    }

    /**
     * Set exceptionReasonId.
     * 
     * @param exceptionReasonId : exception Reason Id.
     */
    public void setExceptionReasonId(Long exceptionReasonId) {
        this.exceptionReasonId = exceptionReasonId;
    }

    /**
     * Get exceptionReasonId.
     * 
     * @return exceptionReasonId : exception Reason Id.
     */
    public Long getExceptionReasonId() {
        return exceptionReasonId;
    }

    /**
     * Set userHasAuthenticationOnBusinessRule.
     * 
     * @param userHasAuthenticationOnBusinessRule : userHasAuthenticationOnBusinessRule.
     */
    public void setUserHasAuthenticationOnBusinessRule(String userHasAuthenticationOnBusinessRule) {
        this.userHasAuthenticationOnBusinessRule = userHasAuthenticationOnBusinessRule;
    }

    /**
     * Get userHasAuthenticationOnBusinessRule.
     * 
     * @return userHasAuthenticationOnBusinessRule : user Has Authentication On Business Rule.
     */
    public String getUserHasAuthenticationOnBusinessRule() {
        return userHasAuthenticationOnBusinessRule;
    }

}