/*
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 * * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Mohamed Fayek  01/04/2014  - File created.
 */

//package ae.rta.eps.vo;
package com.dataObject;

import java.util.Date;

/**
 * Exceptions value object.
 *
 * @author Mohamed Fayek
 * @version 1.00
 */
public class ExceptionsVO extends ValueObject {
   
    
    
    /*
     * static variables.
     */
     
    /** Status Active. */
    public static final Integer STATUS_ACTIVE = new Integer(2);

    /** Status inactive. */
    public static final Integer STATUS_INACTIVE = new Integer(1);
    
    /** EXEMPTED FOR PLATE */
    public static final int EXEMPTED_FOR_PLATE = 1;
    
    /** EXEMPTED FOR CHASSIS */
    public static final int EXEMPTED_FOR_CHASSIS = 2;
    
    /** EXEMPTED FOR TRAFFIC FILE. */
    public static final int EXEMPTED_FOR_TRAFFIC_FILE = 3;
    
    /** EXEMPTED FOR EMIRATE ID. */
    public static final int EXEMPTED_FOR_EMIRATE_ID = 4;     
    /*
     * Instance variables.
     */
     
    /* allowed Star tDate */
    private Date allowedStartDate;
    
    /* allowed End Date*/    
	private Date allowedEndDate;
    
    /* status*/  
	private Integer status;
    
    /* status Date*/  
	private Date statusDate;
    
    /* others Exception Reason*/  
	private String othersExceptionReason;
    
    /* salik Objection No*/  
	private String salikObjectionNo;
    
    /* cancelation Reason*/  
	private String cancelationReason;
    
    /* approval Reason*/  
	private String approvalReason;
    
    /* remarks*/  
	private String remarks;
    
    /* plate VO*/ 
   
    
    /* status Description */ 
    private String statusDescription;
    
    /* status Date From*/ 
    private Date statusDateFrom;
    
    /* status Date To*/ 
    private Date statusDateTo;
    
    /* plate Info*/ 
    private String plateInfo;
    
    /* services Desc*/ 
    private String servicesDesc;
    
    /** Chassis Number */
    private String chassisNo;
    
    /** Excempted For*/
    private Integer excemptedFor;

    private String emirateId;
    
    private Date emirateExpiryDate;

    /*
     * Constructors
     */

    /**
     * Default constructor.
     */
    public ExceptionsVO() {
	// Empty body
    }

    /**
     * Set allowedStartDate
     *
     * @param allowedStartDate : allowed Start Date
     */
    public void setAllowedStartDate(Date allowedStartDate) {
	this.allowedStartDate = allowedStartDate;
    }

    /**
     * Get allowedStartDate.
     *
     * @return allowed Start Date.
     */
    public Date getAllowedStartDate() {
	return allowedStartDate;
    }
    
    /**
     * Set allowedEndDate
     *
     * @param allowedEndDate : allowed End Date.
     */
    public void setAllowedEndDate(Date allowedEndDate) {
	this.allowedEndDate = allowedEndDate;
    }

    /**
     * Get allowedEndDate.
     *
     * @return allowed End Date.
     */
    public Date getAllowedEndDate() {
	return allowedEndDate;
    }
    
    /**
     * Set status
     *
     * @param status : status.
     */
    public void setStatus(Integer status) {
	this.status = status;
    }

    /**
     * Get status.
     *
     * @return status.
     */
    public Integer getStatus() {
	return status;
    }

    /**
     * Set statusDate
     *
     * @param statusDate : status Date.
     */
    public void setStatusDate(Date statusDate) {
	this.statusDate = statusDate;
    }

    /**
     * Get statusDate.
     *
     * @return status Date.
     */
    public Date getStatusDate() {
	return statusDate;
    }

    /**
     * Set othersExceptionReason
     *
     * @param othersExceptionReason : others Exception Reason.
     */
    public void setOthersExceptionReason(String othersExceptionReason) {
	this.othersExceptionReason = othersExceptionReason;
    }

    /**
     * Get othersExceptionReason.
     *
     * @return others Exception Reason.
     */
    public String getOthersExceptionReason() {
	return othersExceptionReason;
    }

    /**
     * Set salikObjectionNo
     *
     * @param salikObjectionNo : salik Objection No.
     */
    public void setSalikObjectionNo(String salikObjectionNo) {
	this.salikObjectionNo = salikObjectionNo;
    }

    /**
     * Get salikObjectionNo.
     *
     * @return salik Objection No.
     */
    public String getSalikObjectionNo() {
	return salikObjectionNo;
    }

    /**
     * Set cancelationReason
     *
     * @param cancelationReason : cancelation Reason.
     */
    public void setCancelationReason(String cancelationReason) {
	this.cancelationReason = cancelationReason;
    }
    
     /**
     * Get cancelationReason.
     *
     * @return cancelation Reason.
     */
    public String getCancelationReason() {
	return cancelationReason;
    }
    
    /**
     * Set approvalReason
     *
     * @param approvalReason : approval Reason.
     */
    public void setApprovalReason(String approvalReason) {
	this.approvalReason = approvalReason;
    }

    /**
     * Get approvalReason.
     *
     * @return approval Reason.
     */
    public String getApprovalReason() {
	return approvalReason;
    }

    /**
     * Set remarks
     *
     * @param remarks : remarks.
     */
    public void setRemarks(String remarks) {
	this.remarks = remarks;
    }

    /**
     * Get remarks.
     *
     * @return remarks.
     */
    public String getRemarks() {
	return remarks;
    }

    
    /**
     * Set statusDescription
     *
     * @param statusDescription : status Description.
     */
    public void setStatusDescription(String statusDescription) {
	this.statusDescription = statusDescription;
    }

    /**
     * Get statusDescription.
     *
     * @return status Description.
     */
    public String getStatusDescription() {
	return statusDescription;
    }

    /**
     * Set statusDateFrom
     *
     * @param statusDateFrom : status Date From.
     */
    public void setStatusDateFrom(Date statusDateFrom) {
	this.statusDateFrom = statusDateFrom;
    }

    /**
     * Get statusDateFrom.
     *
     * @return status Date From.
     */
    public Date getStatusDateFrom() {
	return statusDateFrom;
    }

    /**
     * Set statusDateTo
     *
     * @param statusDateTo : status Date To.
     */
    public void setStatusDateTo(Date statusDateTo) {
	this.statusDateTo = statusDateTo;
    }

    /**
     * Get statusDateTo.
     *
     * @return status Date To.
     */
    public Date getStatusDateTo() {
	return statusDateTo;
    }

    /**
     * Set plateInfo
     *
     * @param plateInfo : plate Info.
     */
    public void setPlateInfo(String plateInfo) {
	this.plateInfo = plateInfo;
    }

    /**
     * Get plateInfo.
     *
     * @return plate Info.
     */
    public String getPlateInfo() {
	return plateInfo;
    }

    /**
     * Set servicesDesc
     *
     * @param servicesDesc : services Desc.
     */
    public void setServicesDesc(String servicesDesc) {
	this.servicesDesc = servicesDesc;
    }

    /**
     * Get servicesDesc.
     *
     * @return services Desc.
     */
    public String getServicesDesc() {
	return servicesDesc;
    }
    
    /**
     * Set Chassis Number
     * 
     * @param chassisNo Chassis Number
     */
    public void setChassisNo(String chassisNo) {
	this.chassisNo = chassisNo;
    }

    /**
     * Get Chassis Number
     * 
     * @return Chassis Number
     */
    public String getChassisNo() {
	return chassisNo;
    }

    /**
     * Set Excempted For
     * 
     * @param excemptedFor
     */
    public void setExcemptedFor(Integer excemptedFor) {
	this.excemptedFor = excemptedFor;
    }

    /**
     * Set Excempted For.
     * 
     * @return excemptedFor
     */
    public Integer getExcemptedFor() {
	return excemptedFor;
    }


    public void setEmirateId(String emirateId) {
        this.emirateId = emirateId;
    }

    public String getEmirateId() {
        return emirateId;
    }

    public void setEmirateExpiryDate(Date emirateExpiryDate) {
        this.emirateExpiryDate = emirateExpiryDate;
    }

    public Date getEmirateExpiryDate() {
        return emirateExpiryDate;
    }
}
