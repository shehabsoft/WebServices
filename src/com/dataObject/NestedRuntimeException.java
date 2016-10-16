/*
 * Copyright (c) i-Soft 2006.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  07/12/2006  - File created.
 */

package  com.dataObject;

import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.StringWriter;

/**
 * This class used to wrap other exceptions. Its main purpose is to hide other 
 * layers exceptions and implementation details.
 *
 * @author Eng. Ayman Atiyeh
 * @version 1.00
 */
public class NestedRuntimeException extends RuntimeException {
    /*
     * Instance variables
     */

    /** Nested exception. */
    private Throwable nestedException;

    /*
     * Constructors
     */

    /**
     * Construct new NestedRuntimeException object.
     */
    public NestedRuntimeException() {
    	super();
    }

    /**
     * Construct new NestedRuntimeException object.
     * 
     * @param msg Exception message.
     */
    public NestedRuntimeException(String msg) {
        super(msg);
    }

    /**
     * Nest the geneterated exception inside NestedRuntimeException object.
     * 
     * @param NestedRuntimeException Generated exception.
     */
    public NestedRuntimeException(Throwable nestedException) {
        this.nestedException = nestedException;
    }

    /**
     * Nest the geneterated exception inside NestedRuntimeException object.
     * 
     * @param msg Exception message.
     * @param NestedRuntimeException Generated exception.
     */
    public NestedRuntimeException(String msg, Throwable nestedException) {
        this(msg);
        this.nestedException = nestedException;
    }

    /*
     * methods
     */

    /**
     * Returns the nested Exception object.
     * 
     * @return nested Exception object.
     */
    public Throwable getNestedException() {
    	return nestedException;
    }

    /**
     * Overrides toString() method.
     * 
     * @return String representation of this exception.
     */
    public String toString() {
        StringBuffer msg = new StringBuffer();

        // Add this exception message if exists
        if (super.toString() != null) {
            msg.append(super.toString());
        }

        // Append nested exception message if exists
        if (this.nestedException != null) {
            if (msg.length() > 0) {
                msg.append("; \n\t---> nested: ");
            }

            msg.append(getNestedException());
        }

        return msg.toString();
    }

    /**
     * Overrides getMessage() to append the nested Exception message.
     * 
     * @return Nested exception message.
     */
    public String getMessage() {
        // Create message StringBuffer
        StringBuffer msg = new StringBuffer();

        // Append whatever message was passed into the constructor
        if (super.getMessage() != null) {
            msg.append(super.getMessage());
        }

        // Append nested exception message
        if (getNestedException() != null) {
            if (msg.length() > 0) {
                msg.append("; \t---> nested: ");
            }

            msg.append(getNestedException().getMessage());
        }

        // return message contents
        return msg.toString();
    }

    /**
     * Overrides printStackTrace() to append the nested stack trace.
     */
    public void printStackTrace() {
        super.printStackTrace();

        if (getNestedException() != null) {
            System.err.println("===========================> nested by\n");
            getNestedException().printStackTrace();
        }
    }

    /**
     * Overrides printStackTrace() to append the nested stack trace.
     * 
     * @param out PrintWriter destination.
     */
    public void printStackTrace(PrintWriter out) {
        super.printStackTrace(out);

        if (getNestedException() != null) {
            out.println("===========================> nested by\n");
            getNestedException().printStackTrace(out);
        }
    }

    /**
     * Overrides printStackTrace() to append the nested stack trace.
     * 
     * @param out PrintStream destination.
     */
    public void printStackTrace(PrintStream out) {
        super.printStackTrace(out);

        if (getNestedException() != null) {
            out.println("===========================> nested by\n");
            getNestedException().printStackTrace(out);
        }
    }
}