/*
 * Copyright (c) i-Soft 2006.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  19/09/2006  - File created.
 */

package com.dataObject;



/**
 * Service lookup exception
 *
 * @author Eng.  shehab.tarek
 * @version 1.00
 */
public class ServiceLocatorException extends NestedRuntimeException {
    /**
     * Construct new ServiceLocatorException object.
     */
    public ServiceLocatorException() {
    	super();
    }

    /**
     * Construct new ServiceLocatorException object.
     * 
     * @param msg Exception error message.
     */
    public ServiceLocatorException(String msg) {
        super(msg);
    }

    /**
     * Nest the geneterated exception inside ServiceLocatorException object.
     * 
     * @param nestedException Generated exception.
     */
    public ServiceLocatorException(Throwable nestedException) {
        super(nestedException);
    }

    /**
     * Nest the geneterated exception inside ServiceLocatorException object.
     * 
     * @param msg Exception error message.
     * @param nestedException Generated exception.
     */
    public ServiceLocatorException(String msg, Throwable nestedException) {
        super(msg, nestedException);
    }
}