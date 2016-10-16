/* 
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Tariq Abu Amireh    23/06/2008  - First version
 */

package com.dataObject;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @version 
 * @author Tariq Abu Amireh
 */
public class SystemTypeVO extends ValueObject  {
    
    /*
     * Constants.
     */
    
    /** System Codes. */
    public static final String CODE_DTT = "01";
    public static final String CODE_DRL = "02";
    public static final String CODE_VHL = "03";
    public static final String CODE_FINES = "04";
    public static final String CODE_SPECIAL_PLATES = "06";
    public static final String CODE_CML = "08";
    public static final String CODE_CIS = "26";

    private static final Set SYS_CODE = new HashSet();
    static {
        SYS_CODE.add(CODE_DTT);
        SYS_CODE.add(CODE_DRL);
        SYS_CODE.add(CODE_VHL);
        SYS_CODE.add(CODE_FINES);
        SYS_CODE.add(CODE_SPECIAL_PLATES);
        SYS_CODE.add(CODE_CML);
        SYS_CODE.add(CODE_CIS);
    }
    
    /*
     * Instance Variable
     */
    /** system type Code */
    private String code;
    
    /** system type description Arabic */
    private String descriptionAr;
    
    /** system type description English */
    private String descriptionEn;
    
    /** related ExceptionEpsStepSeq */
    private Integer relatedExceptionEpsStepSeq;

    
    /*
     * Constructors
     */
    /** Default Constructor */ 
    public SystemTypeVO() {
    }

    /**
     * set Code
     * 
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * get Code
     * 
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * set Description Arabic
     * 
     * @param descriptionAr
     */
    public void setDescriptionAr(String descriptionAr) {
        this.descriptionAr = descriptionAr;
    }

    /**
     * get Description Arabic
     * 
     * @return descriptionAr
     */
    public String getDescriptionAr() {
        return descriptionAr;
    }
    
    /**
     * Is Driving Test
     * 
     * @return true if code is equal (01)
     */
    public boolean isDrivingTest() {
        return code != null && code.equals(CODE_DTT);
    }    
    
    /**
     * Is Driving License
     * 
     * @return true if code is equal (02)
     */
    public boolean isDrivingLicense() {
        return code != null && code.equals(CODE_DRL);
    }        
    
    /**
     * Is Vehicle License
     * 
     * @return true if code is equal (03)
     */
    public boolean isVehicleLicense() {
        return code != null && code.equals(CODE_VHL);
    }        
    
    /**
     * Is Fines
     * 
     * @return true if code is equal (04)
     */
    public boolean isFines() {
        return code != null && code.equals(CODE_FINES);
    }        

    /**
     * Is Special Plates
     * 
     * @return true if code is equal (06)
     */
    public boolean isSpecialPlates() {
        return code != null && code.equals(CODE_SPECIAL_PLATES);
    }                 
    
    /**
     * Is Commercial Licensing
     * 
     * @return true if code is equal (08)
     */
    public boolean isCommercialLicensing() {
        return code != null && code.equals(CODE_CML);
    }           

    /**
     * Check If System Is CIS.
     * 
     * @return true If System Is CIS.
     */
    public boolean isCentralInspectionSystem() {
        return code != null && code.equals(CODE_CIS);
    }           

    /**
     * setter for descriptionEn
     * @param descriptionEn
     */
    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    /**
     * getter for descriptionEn
     * @return descriptionEn
     */
    public String getDescriptionEn() {
        return descriptionEn;
    }
    
    /**
     * Setter relatedExceptionEpsStepSeq.
     *
     * @param relatedExceptionEpsStepSeq : related Exception Eps Step Seq.
     */
    public void setRelatedExceptionEpsStepSeq(Integer relatedExceptionEpsStepSeq) {
        this.relatedExceptionEpsStepSeq = relatedExceptionEpsStepSeq;
    }

    /**
     * Get relatedExceptionEpsStepSeq.
     * 
     * @return related Exception Eps Step Seq.
     */
    public Integer getRelatedExceptionEpsStepSeq() {
        return relatedExceptionEpsStepSeq;
    }


}
