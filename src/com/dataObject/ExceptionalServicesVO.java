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
 * Exceptional Services value object.
 *
 * @author Mohamed Fayek
 * @version 1.00
 */
public class ExceptionalServicesVO extends ValueObject {
    
    
    /*
     * Instance variables.
     */
     
     /** exceptions VO */
     private  ExceptionsVO exceptionsVO;
     
   
     
     
     
    /*
     * Constructors
     */

    /**
     * Default constructor.
     */
    public ExceptionalServicesVO() {
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

  
}