/*
 * Copyright (c) i-Soft 2004.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 * 
 * Creation Date: 10/01/2005
 * 
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  10/01/2005  - File created.
 */

package  com.dataObject;



/**
 * Used as a general exception for traffic application and used to 
 * hide/encapsulate application exceptions generated in different tiers and 
 * application layers.
 *
 * @version 1.00 - 01/01/2005
 * @author Eng. shehab tarek
 */
public class TrafficException extends NestingException {
    /*
     * Constructors
     */

    /**
     * Creates new TrafficException object
     */
    public TrafficException() {
        super();
    }

    /**
     * Creates new TrafficException object with custom error message.
     */
    public TrafficException(String msg) {
        super(msg);
    }

    /**
     * Creates new TrafficException object and initializes its root cause to the
     * parameter value.
     */
    public TrafficException(Throwable t) {
        super(t);
    }

    /**
     * Creates a new TrafficException object and initializes its message and root
     * cause to the parameter values.
     */
    public TrafficException(String msg, Throwable t) {
        super(msg, t);
    }
}