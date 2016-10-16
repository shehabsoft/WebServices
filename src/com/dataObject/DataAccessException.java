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

package  com.dataObject;



/**
 * Data access layer exception. Data access layer implementation must be hidden
 * form business objects. All implementation specific exceptions such as 
 * SQLException must be wrapped by this exception.
 *
 * @author Eng. Ayman Atiyeh
 * @version 1.00
 */
public class DataAccessException extends NestedRuntimeException {
    /**
     * Construct new DataAccessException object.
     */
    public DataAccessException() {
    	super();
    }

    /**
     * Construct new DataAccessException object.
     * 
     * @param msg Exception error message.
     */
    public DataAccessException(String msg) {
        super(msg);
    }

    /**
     * Nest the geneterated exception inside DataAccessException object.
     * 
     * @param nestedException Generated exception.
     */
    public DataAccessException(Throwable nestedException) {
        super(nestedException);
    }

    /**
     * Nest the geneterated exception inside DataAccessException object.
     * 
     * @param msg Exception error message.
     * @param nestedException Generated exception.
     */
    public DataAccessException(String msg, Throwable nestedException) {
        super(msg, nestedException);
    }
}