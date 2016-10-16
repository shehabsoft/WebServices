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
 * Web service integration layer exception.
 *
 * @author Eng. shehab eldin
 * @version 1.00
 */
public class WebServiceException extends NestedRuntimeException {
        /**
     * Construct new WebServiceException object.
     */
    public WebServiceException() {
    	super();
    }

    /**
     * Construct new WebServiceException object.
     * 
     * @param msg Exception error message.
     */
    public WebServiceException(String msg) {
        super(msg);
    }

    /**
     * Nest the geneterated exception inside WebServiceException object.
     * 
     * @param nestedException Generated exception.
     */
    public WebServiceException(Throwable nestedException) {
        super(nestedException);
    }

    /**
     * Nest the geneterated exception inside WebServiceException object.
     * 
     * @param msg Exception error message.
     * @param nestedException Generated exception.
     */
    public WebServiceException(String msg, Throwable nestedException) {
        super(msg, nestedException);
    }

}