/*
 * Copyright (c) i-Soft 2006.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  07/02/2010  - File created.
 */

//package ae.gov.trf.util.vo;
package com.dataObject;

import java.io.Serializable;

/**
 * Domains are immutable classes that represent scalar data-types that occur 
 * often in our applications. Domain data-types contain a validation in the 
 * constructor which performs the check we need to do to make sure they are 
 * proper values. Once-constructed, instances of Domains can be freely passed 
 * around in a type-safe way as method parameters. In fact, Domains makes it 
 * easy to avoid the mistake of passing a Quantity to a method expecting a 
 * Price since the compiler will flag the error immediately.
 *
 * @author Eng. Ayman Atiyeh
 * @version 1.00
 */
public abstract class DataType implements Serializable {
    /*
     * Fields
     */
    
    /** Domain data-type value. */
    private Object domainValue;
    
    /*
     * Constructors
     */
    
    /**
     * Default constructor.
     */
    protected DataType() {
        // Empty Body
    }
    
    /**
     * Create and initialize new daomain data-type.
     * 
     * @param value Domain value.
     */
    public DataType(Object value) {
        setDomainValue(value);
    }

    /*
     * Methods
     */

    /**
     * Check if this domain value is valid.
     * 
     * @param value Domain value.
     * @return true if this domain value is valid.
     */
    protected abstract boolean isValidDomainValue(Object value);

    /**
     * Get object String representation. This method is used by toString()
     * method to get object string representation.
     * 
     * @return Object String representation.
     */
    public String toString() {
        return (domainValue == null) ? null : domainValue.toString();
    }

    /**
     * Set domain value.
     * 
     * @param value Domain data-type value.
     */
    protected void setDomainValue(Object value) {
        if (! isValidDomainValue(value)) {
            throw new IllegalArgumentException(" value >> " + value);
        }

        this.domainValue = value;
    }

    /**
     * Get Domain data-type value.
     * 
     * @return Domain data-type value.
     */
    protected Object getDomainValue() {
        return domainValue;
    }
}