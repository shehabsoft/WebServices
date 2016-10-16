/*
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  03/01/2009  - File created.
 */

package  com.dataObject;

import java.util.ArrayList;
import java.util.List;

import com.HomeBudget.common.GlobalUtilities;

/**
 * Transaction parsing exception.
 *
 * @author Eng. shehab tarek
 * @version 1.00
 */
public class TransactionException extends WebServiceException {
    /*
     * Instance variables
     */
    
    /** Arabic violation list<String>. */
    private List violationsAr = new ArrayList();

    /** English violation list<String>. */
    private List violationsEn = new ArrayList();

    /*
     * Constructors.
     */

    /**
     * Construct new TransactionException object.
     */
    public TransactionException() {
    	super();
    }

    /**
     * Construct new TransactionException object.
     */
    public TransactionException(String msgAr, String msgEn) {
    	super();
        addViolation(msgAr, msgEn);
    }

    /**
     * Construct new TransactionException object.
     * 
     * @param msg Exception error message. 
     */
    public TransactionException(String msg) {
        super(msg);
        addViolation(msg, msg);
    }

    /**
     * Nest the geneterated exception inside TransactionException object.
     * 
     * @param nestedException Generated exception.
     */
    public TransactionException(Throwable nestedException) {
        super(nestedException);
    }

    /**
     * Nest the geneterated exception inside TransactionException object.
     * 
     * @param msg Exception error message.
     * @param nestedException Generated exception.
     */
    public TransactionException(String msg, Throwable nestedException) {
        super(msg, nestedException);
    }

    /*
     * Methods
     */

    /**
     * Add violation message.
     * 
     * @param messageAr Arabic violation message.
     * @param messageEn English violation message.
     */
    public void addViolation(String messageAr, String messageEn) {
        // Validate parameters
        if (GlobalUtilities.isBlankOrNull(messageAr)) {
            throw new WebServiceException("NULL arabic violation message: ?");
        }

        if (GlobalUtilities.isBlankOrNull(messageEn)) {
            throw new WebServiceException("NULL english violation message: ?");
        }
 
        messageAr.replaceAll("<P>"," ");
        messageEn.replaceAll("<P>"," ");
        // Save violations
        this.violationsAr.add(messageAr);
        this.violationsEn.add(messageEn);
    }

    /**
     * Get arabic violation messages.
     * 
     * @return arabic violation messages.
     */
    public String[] getViolationsAr() {
        if (this.violationsAr.isEmpty()) {
            return new String[0];
        }

        String[] violations = (String[]) violationsAr.toArray(new String[violationsAr.size()]);
        
        for (int i = 0; violations != null && i < violations.length; i++) {
            violations[i] = violations[i].replaceAll("<P>", "");
        }
        
        
        return violations;
    }
    
    /**
     * Get english violation messages.
     * 
     * @return english violation messages.
     */
    public String[] getViolationsEn() {
        if (this.violationsEn.isEmpty()) {
            return new String[0];
        }

        String[] violations = (String[]) violationsEn.toArray(new String[violationsEn.size()]);
        
        for (int i = 0; violations != null && i < violations.length; i++) {
            violations[i] = violations[i].replaceAll("<P>", "");
        }
        
        
        return violations;
    }

    /**
     * Check if this transaction exception has violation messages or not.
     * 
     * @return true if this transaction exception has violation messages.
     */
    public boolean hasViolations() {
        return ! this.violationsEn.isEmpty();
    }
    
    /**
     * Get current violations count.
     * 
     * @return current violations count.
     */
    public int getViolationsCount() {
        return this.violationsAr.size();
    }

    /**
     * Overrides toString() method.
     * 
     * @return String representation of this exception.
     */
    public String toString() {
        StringBuffer msg = new StringBuffer();

        int violationsCount = getViolationsCount();

        if (hasViolations()) {
            msg.append("\n, Violations(").append(violationsCount).append(") {");
            
            for (int i = 0; i < violationsCount; i++)  {
                if (i > 0) {
                    msg.append(",\n");
                }
                
                msg.append("\t[EN=").append(violationsEn.get(i))
                   .append(" | AR=").append(violationsAr.get(i))
                   .append("]");
            }
            
            msg.append("}");
        }

        msg.append("\n, ")
           .append(" ===> ")
           .append(super.toString());

        return msg.toString();
    }
}