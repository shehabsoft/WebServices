/*
 * 
 * Created By: Tarek Nabil
 * Creation Date:  30/05/2004
 *
 * MODIFICATION HISTORY
 * Ver      Person          DD/MM/YYYY  Comments
 * ---      --------------  ----------  -------------------------------------------
 * 1.0      Tarek Nabil     10/02/2004  - First Version.
 */
package com.dataObject;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * An Exception that nests another Exception object.
 * This class can be used as the parent class for any custom nested
 * Exception.
 */
public class NestingException extends RuntimeException {

    // the nested exception
    private Throwable nestedException;

    // String representation of stack trace - not transient!
    private String stackTraceString;

    /**
     * Converts a stack trace to a String so it can be serialized
     */
    static public String generateStackTraceString(Throwable t) {
        StringWriter s = new StringWriter();
        t.printStackTrace(new PrintWriter(s));
        return s.toString();
    }

    /*
     * java.lang.Exception constructors
     */

    public NestingException() {
    	super();
    }

    public NestingException(String msg) {
        super(msg);
    }

    /*
     * additional c'tors - nest the exceptions, storing the stack trace
     */
    public NestingException(Throwable nestedException) {
        this.nestedException = nestedException;
        stackTraceString = generateStackTraceString(nestedException);
    }

    public NestingException(String msg, Throwable nestedException) {
        this(msg);
        this.nestedException = nestedException;
        stackTraceString = generateStackTraceString(nestedException);
    }

    /*
     * methods
     */

    /**
     * Returns the nested Exception object.
     */
    public Throwable getNestedException() {
    	return nestedException;
    }

    /**
     * Descends through linked-list of nesting exceptions, & outputs trace
     * , note that this displays the 'deepest' trace first.
     */
    public String getStackTraceString() {
        // if there's no nested exception, there's no stackTrace
        if (nestedException == null)
            return null;

        StringBuffer traceBuffer = new StringBuffer();

        if (nestedException instanceof NestingException) {
            traceBuffer.append(((NestingException)nestedException).getStackTraceString());
                traceBuffer.append("-------- nested by:\n");
        }

        traceBuffer.append(stackTraceString);

        return traceBuffer.toString();
    }

    /**
     * Overrides Exception.getMessage() to append the nested Exception message.
     */
    public String getMessage() {
        // superMsg will contain whatever String was passed into the
        // constructor, and null otherwise.
        String superMsg = super.getMessage();

        // if there's no nested exception, do like we would always do
        if (getNestedException() == null)
            return superMsg;

        StringBuffer theMsg = new StringBuffer();

        // get the nested exception's message
        String nestedMsg = getNestedException().getMessage();

        if (superMsg != null)
            theMsg.append(superMsg).append(": ").append(nestedMsg);
        else
            theMsg.append(nestedMsg);

        return theMsg.toString();
    }

    /**
     * Overrides Exception.toString()
     */
    public String toString() {
        StringBuffer theMsg = new StringBuffer(super.toString());

        if (getNestedException() != null)
            theMsg.append("; \n\t---> nested ").append(getNestedException());

        return theMsg.toString();
    }
}
