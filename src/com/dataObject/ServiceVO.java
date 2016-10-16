/*
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Abdelhameed Elnaggar  04/01/2012  - File created.
 * 
 */
//package ae.rta.apt.vo;
package com.dataObject;



/**
 * Service value object.
 *
 * @author Abdelhameed Elnaggar
 * @version 1.00
 */
public class ServiceVO extends ValueObject {


    /*
     * Instance variables.
     */     
    
   /** Service code. */
    private Integer code;
    
    /** Service arabic description. */
    private String NameAr;
    
    /** Service english description. */
    private String NameEn;
    
    /** Service arabic description. */
    private String descriptionAr;
    
    /** Service english description. */
    private String descriptionEn;
    
//    /** Service arabic description. */
//    private Integer appearsInCatalogue;
    

    
    /** Service Type.*/
    private Integer serviceType;

    /*
     * Constructors
     */
    
    /**
     * Default constructor.
     */
    public ServiceVO() {
        super();
    }
    
    /*
     * Methods
     */


    /**
     * Set Service code.
     * 
     * @param code Service code.
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * Get Service code.
     * 
     * @return Service code.
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Set Service arabic description.
     * 
     * @param descriptionAr Service arabic description.
     */
    public void setDescriptionAr(String descriptionAr) {
        this.descriptionAr = descriptionAr;
    }

    /**
     * Get Service arabic description.
     * 
     * @return Service arabic description.
     */
    public String getDescriptionAr() {
        return descriptionAr;
    }

    /**
     * Set Service english description.
     * 
     * @param descriptionEn Service english description.
     */
    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    /**
     * Get Service english description.
     * 
     * @return Service english description.
     */
    public String getDescriptionEn() {
        return descriptionEn;
    }


    /**
     * Get Service Arabic name.
     * 
     * @return Service Arabic name.
     */
    public String getNameAr() {
        return NameAr;
    }

    /**
     * Set Service Arabic name.
     * 
     * @param NameAr Service Arabic name.
     */
    public void setNameAr(String NameAr) {
        this.NameAr = NameAr;
    }

    /**
     * Get Service english name.
     * 
     * @return Service english name.
     */
    public String getNameEn() {
        return NameEn;
    }


    /**
     * Set Service english name.
     * 
     * @param NameEn Service english name.
     */
    public void setNameEn(String NameEn) {
        this.NameEn = NameEn;
    }
    
    
//    /**
//     * Set Appears In Catalogue.
//     * 
//     * @param appearsInCatalogue Appears In Catalogue..
//     */
//    public void setAppearsInCatalogue(Integer appearsInCatalogue) {
//        this.appearsInCatalogue = appearsInCatalogue;
//    }
//
//    /**
//     * Get Appears In Catalogue.
//     * 
//     * @return appearsInCatalogue Appears In Catalogue.
//     */
//    public Integer getAppearsInCatalogue() {
//        return appearsInCatalogue;
//    }


    /**
     * Set Service Type.
     * 
     * @param serviceType :Service Type
     */
    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * Get Service Type
     * 
     * @return Service Type
     */
    public Integer getServiceType() {
        return serviceType;
    }
    
}