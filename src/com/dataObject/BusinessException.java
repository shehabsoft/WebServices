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
 */

package com.dataObject;



/**
 * Business layer exception. Business layer implementation must be hidden form 
 * other layers. All business implementation specific exceptions must be 
 * wrapped by this exception.
 *
 * @author Eng. Ayman Atiyeh
 * @version 1.00
 */
public class BusinessException extends NestedRuntimeException {
    /**
     * Construct new BusinessException object.
     */
    public BusinessException() {
    	super();
    }

    /**
     * Construct new BusinessException object.
     * 
     * @param msg Exception error message.
     */
    public BusinessException(String msg) {
        super(msg);
    }

    /**
     * Nest the geneterated exception inside BusinessException object.
     * 
     * @param nestedException Generated exception.
     */
    public BusinessException(Throwable nestedException) {
        super(nestedException);
    }

    /**
     * Nest the geneterated exception inside BusinessException object.
     * 
     * @param msg Exception error message.
     * @param nestedException Generated exception.
     */
    public BusinessException(String msg, Throwable nestedException) {
        super(msg, nestedException);
    }

}