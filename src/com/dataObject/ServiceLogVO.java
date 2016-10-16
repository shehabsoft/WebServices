/*
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Alaa Salem         20/01/2010  - File created.
 * 
 * 1.01  Alaa Salem         24/01/2010  - Re-Structure Class.
 */
package com.dataObject;



/**
 * Service Log Value Object.
 *
 * @author Alaa Salem
 * @version 1.00
 */
public class ServiceLogVO extends LogValueObject {

    /*
     * Constants.
     */

    /** Service Log Action Types. */
    public static final Integer ACTION_TYPE_ADD = new Integer(1);
    public static final Integer ACTION_TYPE_UPDATE = new Integer(2);
    public static final Integer ACTION_TYPE_DELETE = new Integer(3);
    public static final Integer ACTION_TYPE_LINK_TO_CHANNEL = new Integer(4);
    public static final Integer ACTION_TYPE_UNLINK_FROM_CHANNEL = new Integer(5);
    public static final Integer ACTION_TYPE_LINK_TO_RULE = new Integer(6);
    public static final Integer ACTION_TYPE_UNLINK_FROM_RULE = new Integer(7);
    
    /** System code values. */
    public static final String  SYS_CODE_DRIVING_TEST = "01";
    public static final String  SYS_CODE_DRIVING_LICENSING = "02";
    public static final String  SYS_CODE_VEHICLES = "03";
    public static final String  SYS_CODE_FINES = "04";
    public static final String  SYS_CODE_SPECIAL_PLATES = "06";
    public static final String  SYS_CODE_COMMERCIAL_LICENSING = "08";
    
    /** Service Categories. */
    public static final Integer SERVICE_CATEGORY_DP = new Integer(1);
    public static final Integer SERVICE_CATEGORY_RTA = new Integer(2);

    /*
     * Instance Variables.
     */     
    
    /** Service code. */
    private Integer code;
    
    /** Service arabic description. */
    private String descriptionAr;
    
    /** Service english description. */
    private String descriptionEn;
    
    /** Has File Copy */
    private Integer hasFileCopy;
    
    /** Main Service Flag. */
    private Integer mainService;
    
    /** Service System. */
    private SystemTypeVO system;
     
    /** Service Category. */
    private Integer serviceCategory;
     
    /** Service Time To Complete In Minutes. */
    private Integer timeToCompleteInMinutes;
     
    /** Service Arabic Details. */
    private String serviceDetailsAr;
     
    /** Service English Details. */
    private String serviceDetailsEn;
     
    /** Available As Auto-Service Flag. */
    private Integer autoService;

    /** Service Has E-Form Flag. */
    private Integer hasEForm;

    /** Service Requires Manual Auditing Flag. */
    private Integer requiresManualAuditing;

    /** Service Requires Security Permission Flag. */
    private Integer requiresPersmissions;

    /** Can Viewed In Catalog Flag. */
    private Integer viewedInCatalog;

    /** Needs Passport Info Flag. */
    private Integer needsPassportInfo;

    /** Service Channel ID. */
    private Long serviceChannelId;

    /** Service Rule Key. */
    private String brKey;

    /** View Location */
    private Long viewLocation;
    
    /** is SDDI Main Service */
    private Integer sddiMainService;
    
     /** EID Buffer Days */
     private Integer eidBufferDays;
    
    /*
     * Methods
     */

    /**
     * Overrided Method to Set Action Types.
     * 
     * @param actionType Service Log Action Type.
     */
    public void setActionType(Integer actionType) {
        if(actionType == null ||
           actionType.equals(ACTION_TYPE_ADD) ||
           actionType.equals(ACTION_TYPE_UPDATE) ||
           actionType.equals(ACTION_TYPE_DELETE) ||
           actionType.equals(ACTION_TYPE_LINK_TO_CHANNEL) ||
           actionType.equals(ACTION_TYPE_UNLINK_FROM_CHANNEL) ||
           actionType.equals(ACTION_TYPE_LINK_TO_RULE) ||
           actionType.equals(ACTION_TYPE_UNLINK_FROM_RULE)) {
            super.setActionType(actionType);
            return;
        }
        throw new IllegalArgumentException("Invalid actionType: " + actionType);
    }

    /**
     * Test If Action Type Is `Add Service`.
     * 
     * @return true If Action Type Is `Add Service`.
     */
    public boolean isActionTypeAdd() {
        return getActionType() != null && getActionType().equals(ACTION_TYPE_ADD);
    }
    
    /**
     * Test If Action Type Is `Update Service`.
     * 
     * @return true If Action Type Is `Update Service`.
     */
    public boolean isActionTypeUpdate() {
        return getActionType() != null && getActionType().equals(ACTION_TYPE_UPDATE);
    }
    
    /**
     * Test If Action Type Is `Delete Service`.
     * 
     * @return true If Action Type Is `Delete Service`.
     */
    public boolean isActionTypeDelete() {
        return getActionType() != null && getActionType().equals(ACTION_TYPE_DELETE);
    }
    
    /**
     * Test If Action Type Is `Link To Channel`.
     * 
     * @return true If Action Type Is `Link To Channel`.
     */
    public boolean isActionTypeLinkToChannel() {
        return getActionType() != null &&
                            getActionType().equals(ACTION_TYPE_LINK_TO_CHANNEL);
    }
    
    /**
     * Test If Action Type Is `Unlink From Channel`.
     * 
     * @return true If Action Type Is `Unlink From Channel`.
     */
    public boolean isActionTypeUnlinkFormChannel() {
        return getActionType() != null &&
                          getActionType().equals(ACTION_TYPE_UNLINK_FROM_CHANNEL);
    }
    
    /**
     * Test If Action Type Is `Link To Channel`.
     * 
     * @return true If Action Type Is `Link To Channel`.
     */
    public boolean isActionTypeLinkToRule() {
        return getActionType() != null &&
                            getActionType().equals(ACTION_TYPE_LINK_TO_RULE);
    }
    
    /**
     * Test If Action Type Is `Unlink From Rule`.
     * 
     * @return true If Action Type Is `Unlink From Rule`.
     */
    public boolean isActionTypeUnlinkFromRule() {
        return getActionType() != null &&
                            getActionType().equals(ACTION_TYPE_UNLINK_FROM_RULE);
    }
    
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
     * Set Has file copy
     * 
     * @param hasFileCopy Has file copy
     */
    public void setHasFileCopy(Integer hasFileCopy) {
        if(hasFileCopy == null ||
           hasFileCopy.equals(TRUE) ||
           hasFileCopy.equals(FALSE)) {
           this.hasFileCopy = hasFileCopy;
            return;
        }
        throw new IllegalArgumentException("Invalid hasFileCopy: " + hasFileCopy);
    }

    /**
     * Get Has file copy
     * 
     * @return Has file copy
     */
    public Integer getHasFileCopy() {
        return hasFileCopy;
    }
    
    /**
     * Check if the system can print file copy
     * 
     * @return true if yes
     */
    public boolean printFileCopy() {
        return getHasFileCopy()!=null && getHasFileCopy().intValue() == TRUE.intValue();
    }

    /**
     * Set Main Service Flag.
     * 
     * @param mainService Main Service Flag.
     */
    public void setMainService(Integer mainService) {
         if(mainService == null ||
            mainService.equals(TRUE) ||
            mainService.equals(FALSE)) {
            this.mainService = mainService;
            return;
        }
        throw new IllegalArgumentException("Invalid mainService: " + mainService);
    }

    /**
     * Test If This Service Is Main Service.
     * 
     * @return true If This Service Is Main Service.
     */
    public boolean isMainService() {
        return mainService != null && mainService.equals(TRUE);
    }
    
    /**
     * Get Main Service Flag.
     * 
     * @return Main Service Flag.
     */
    public Integer getMainService() {
        return mainService;
    }

    /**
     * Set Service System.
     * 
     * @param system Service System.
     */
    public void setSystem(SystemTypeVO system) {
        this.system = system;
    }

    /**
     * Get Service System.
     * 
     * @return Service System.
     */
    public SystemTypeVO getSystem() {
        return system;
    }

    /**
     * Set Service Category.
     * 
     * @param serviceCategory Service Category.
     */
    public void setServiceCategory(Integer serviceCategory) {
         if(serviceCategory == null ||
            serviceCategory.equals(SERVICE_CATEGORY_DP) ||
            serviceCategory.equals(SERVICE_CATEGORY_RTA)) {
            this.serviceCategory = serviceCategory;
            return;
        }
        throw new IllegalArgumentException("Invalid serviceCategory: " + serviceCategory);
    }

    /**
     * Test If This Service Category Is `Dubai Police`.
     * 
     * @return true If This Service Category Is `Dubai Police`.
     */
    public boolean isServiceCategoryDubaiPolice() {
        return serviceCategory != null && serviceCategory.equals(SERVICE_CATEGORY_DP);
    }
    
    /**
     * Test If This Service Category Is `RTA`.
     * 
     * @return true If This Service Category Is `RTA`.
     */
    public boolean isServiceCategoryDubaiRta() {
        return serviceCategory != null && serviceCategory.equals(SERVICE_CATEGORY_RTA);
    }
    
    /**
     * Get Service Category.
     * 
     * @return Service Category.
     */
    public Integer getServiceCategory() {
        return serviceCategory;
    }

    /**
     * Set Service Time To Complete In Minutes.
     * 
     * @param timeToCompleteInMinutes Service Time To Complete In Minutes.
     */
    public void setTimeToCompleteInMinutes(Integer timeToCompleteInMinutes) {
        this.timeToCompleteInMinutes = timeToCompleteInMinutes;
    }

    /**
     * Get Service Time To Complete In Minutes.
     * 
     * @return Service Time To Complete In Minutes.
     */
    public Integer getTimeToCompleteInMinutes() {
        return timeToCompleteInMinutes;
    }

    /**
     * Set Service Arabic Details.
     * 
     * @param serviceDetailsAr Service Arabic Details.
     */
    public void setServiceDetailsAr(String serviceDetailsAr) {
        this.serviceDetailsAr = serviceDetailsAr;
    }

    /**
     * Get Service Arabic Details.
     * 
     * @return Service Arabic Details.
     */
    public String getServiceDetailsAr() {
        return serviceDetailsAr;
    }

    /**
     * Set Service English Details.
     * 
     * @param serviceDetailsEn Service English Details.
     */
    public void setServiceDetailsEn(String serviceDetailsEn) {
        this.serviceDetailsEn = serviceDetailsEn;
    }

    /**
     * Get Service English Details.
     * 
     * @return Service English Details.
     */
    public String getServiceDetailsEn() {
        return serviceDetailsEn;
    }

    /**
     * Set Auto-Service Flag.
     * 
     * @param autoService Auto-Service Flag.
     */
    public void setAutoService(Integer autoService) {
         if(autoService == null ||
            autoService.equals(TRUE) ||
            autoService.equals(FALSE)) {
            this.autoService = autoService;
            return;
        }
        throw new IllegalArgumentException("Invalid autoService: " + autoService);
    }

    /**
     * Test If This Service Is Auto-Service.
     * 
     * @return true If This Service Is Auto-Service.
     */
    public boolean isAutoService() {
        return autoService != null && autoService.equals(TRUE);
    }
    
    /**
     * Get Auto-Service Flag.
     * 
     * @return Auto-Service Flag.
     */
    public Integer getAutoService() {
        return autoService;
    }

    /**
     * Set Service Has E-Form Flag.
     * 
     * @param hasEForm Service Has E-Form Flag.
     */
    public void setHasEForm(Integer hasEForm) {
        if(hasEForm == null ||
           hasEForm.equals(TRUE) ||
           hasEForm.equals(FALSE)) {
           this.hasEForm = hasEForm;
            return;
        }
        throw new IllegalArgumentException("Invalid hasEForm: " + hasEForm);
    }

    /**
     * Test If This Service Has E-Form.
     * 
     * @return true If This Service Has E-Form.
     */
    public boolean isHasEForm() {
        return hasEForm != null && hasEForm.equals(TRUE);
    }
    
    /**
     * Get Service Has E-Form Flag.
     * 
     * @return Service Has E-Form Flag.
     */
    public Integer getHasEForm() {
        return hasEForm;
    }

    /**
     * Set Service Requires Manual Auditing Flag.
     * 
     * @param requiresManualAuditing Service Requires Manual Auditing Flag.
     */
    public void setRequiresManualAuditing(Integer requiresManualAuditing) {
        if (requiresManualAuditing == null ||
            requiresManualAuditing.equals(TRUE) ||
            requiresManualAuditing.equals(FALSE)) {
            this.requiresManualAuditing = requiresManualAuditing;
            return;
        }
        throw new IllegalArgumentException("Invalid requiresManualAuditing: " +
                                                        requiresManualAuditing);
    }

    /**
     * Test If This Service Requires Manual Auditing.
     * 
     * @return true If This Service Requires Manual Auditing.
     */
    public boolean isRequiresManualAuditing() {
        return requiresManualAuditing != null && requiresManualAuditing.equals(TRUE);
    }
    
    /**
     * Get Service Requires Manual Auditing Flag.
     * 
     * @return Service Requires Manual Auditing Flag.
     */
    public Integer getRequiresManualAuditing() {
        return requiresManualAuditing;
    }

    /**
     * Set Service Requires Security Permissions Flag.
     * 
     * @param requiresSecurityPersmissions Service Requires Security Permission Flag.
     */
    public void setRequiresPersmissions(Integer requiresPersmissions) {
        if (requiresPersmissions == null ||
            requiresPersmissions.equals(TRUE) ||
            requiresPersmissions.equals(FALSE)) {
            this.requiresPersmissions = requiresPersmissions;
            return;
        }
        throw new IllegalArgumentException("Invalid requiresPersmissions: " +
                                                    requiresPersmissions);
    }

    /**
     * Test If This Service Requires Security Permissions.
     * 
     * @return true If This Service Requires Security Permissions.
     */
    public boolean isRequiresPersmissions() {
        return requiresPersmissions != null && requiresPersmissions.equals(TRUE);
    }
    
    /**
     * Get Service Requires Security Permissions Flag.
     * 
     * @return Service Requires Security Permission Flag.
     */
    public Integer getRequiresPersmissions() {
        return requiresPersmissions;
    }

    /**
     * Test If This Service Has File Copy.
     * 
     * @return true If This Service Has File Copy.
     */
    public boolean isHasFileCopy() {
        return hasFileCopy != null && hasFileCopy.equals(TRUE);
    }

    /**
     * Set Can Viewed In Catalog Flag.
     * 
     * @param viewedInCatalog Can Viewed In Catalog Flag.
     */
    public void setViewedInCatalog(Integer viewedInCatalog) {
        if (viewedInCatalog == null ||
            viewedInCatalog.equals(TRUE) ||
            viewedInCatalog.equals(FALSE)) {
            this.viewedInCatalog = viewedInCatalog;
            return;
        }
        throw new IllegalArgumentException("Invalid viewedInCatalog: " +
                                                    viewedInCatalog);
    }

    /**
     * Test If This Service Can Be Viewd In Catalog.
     * 
     * @return true If This Service Can Be Viewd In Catalog.
     */
    public boolean isViewedInCatalog() {
        return viewedInCatalog != null && viewedInCatalog.equals(TRUE);
    }

    /**
     * Get Can Viewed In Catalog Flag.
     * 
     * @return Can Viewed In Catalog Flag.
     */
    public Integer getViewedInCatalog() {
        return viewedInCatalog;
    }

    /**
     * Set Needs Passport Info Flag.
     * 
     * @param needsPassportInfo Needs Passport Info Flag.
     */
    public void setNeedsPassportInfo(Integer needsPassportInfo) {
        if (needsPassportInfo == null ||
            needsPassportInfo.equals(TRUE) ||
            needsPassportInfo.equals(FALSE)) {
            this.needsPassportInfo = needsPassportInfo;
            return;
        }
        throw new IllegalArgumentException("Invalid needsPassportInfo: " +
                                                    needsPassportInfo);
    }

    /**
     * Test If This Service Needs Passport Info.
     * 
     * @return true If This Service Needs Passport Info.
     */
    public boolean isNeedsPassportInfo() {
        return needsPassportInfo != null && needsPassportInfo.equals(TRUE);
    }

    /**
     * Get Needs Passport Info Flag.
     * 
     * @return Needs Passport Info Flag.
     */
    public Integer getNeedsPassportInfo() {
        return needsPassportInfo;
    }

    /**
     * Set Service Channel ID.
     * 
     * @param serviceChannelId Service Channel ID.
     */
    public void setServiceChannelId(Long serviceChannelId) {
        this.serviceChannelId = serviceChannelId;
    }

    /**
     * Get Service Channel ID.
     * 
     * @return Service Channel ID.
     */
    public Long getServiceChannelId() {
        return serviceChannelId;
    }

    /**
     * Set Service Rule Key.
     * 
     * @param brKey Service Rule Key.
     */
    public void setBrKey(String brKey) {
        this.brKey = brKey;
    }

    /**
     * Get Service Rule Key.
     * 
     * @return Service Rule Key.
     */
    public String getBrKey() {
        return brKey;
    }
    
    /**
     * Set View Location
     * 
     * @param viewLocation View Location
     */
    public void setViewLocation(Long viewLocation) {
        this.viewLocation = viewLocation;
    }

    /**
     * Get View Location
     * 
     * @return viewLocation View Location
     */
    public Long getViewLocation() {
        return viewLocation;
    }

    /**
     * Set SDDI Main Service
     * 
     * @param sddiMainService SDDI Main Service
     */
    public void setSddiMainService(Integer sddiMainService) {
        this.sddiMainService = sddiMainService;
    }

    /**
     * Get SDDI Main Service
     * 
     * @return sddiMainService SDDI Main Service
     */
    public Integer getSddiMainService() {
        return sddiMainService;
    }

    
    /**
     * Overloaded Method Set Data In This Log VO From Service VO.
     * 
     * @param Service VO.
     */
    public void setLogData(ServiceVO vo) {

//        if(vo != null) {
//        
//            code = vo.getCode();
//            descriptionAr = vo.getDescriptionAr();
//            descriptionEn = vo.getDescriptionEn();
//            hasFileCopy = vo.getHasFileCopy();
//            mainService = vo.getMainService();
//            system = vo.getSystem();
//            serviceCategory = vo.getServiceCategory();
//            timeToCompleteInMinutes = vo.getTimeToCompleteInMinutes();
//            serviceDetailsAr = vo.getServiceDetailsAr();
//            serviceDetailsEn = vo.getServiceDetailsEn();
//            autoService = vo.getAutoService();
//            hasEForm = vo.getHasEForm();
//            requiresManualAuditing = vo.getRequiresManualAuditing();
//            requiresPersmissions = vo.getRequiresPersmissions();
//            viewedInCatalog = vo.getViewedInCatalog();
//            needsPassportInfo = vo.getNeedsPassportInfo();
//            
//            setCreatedBy(vo.getCreatedBy());
//            setUpdatedBy(vo.getUpdatedBy());
//            setCreationDate(vo.getCreationDate());
//            setUpdateDate(vo.getUpdateDate());
//            setId(vo.getId());
//            setEmployeeName(vo.getEmployeeName());
//            setCenterName(vo.getCenterName());
//            setUserEmirateCode(vo.getUserEmirateCode());
//            setSddiMainService(vo.getSddiMainService());
//            setViewLocation(vo.getViewLocation());
//            
//        }
        
    }
    
    /**
     * Overloaded Method Set Data In This Log VO From Service VO.
     * 
     * @param Service VO.
     */
    public void setLogData(BusinessRuleVO vo) {

        if(vo != null) {
        
            brKey = vo.getKey();
            
        }
        
    }
    
    /**
     * Overloaded Method Set Data In This Log VO From Service VO.
     * 
     * @param Service Rule VO.
     */
    public void setLogData(ServiceRuleVO vo) {

        if(vo != null) {
        
            if(vo.getService() != null) {
                setLogData(vo.getService());
            }            
            
            if(vo.getBusinessRule() != null) {
                setLogData(vo.getBusinessRule());
            }
            
            setCreatedBy(vo.getCreatedBy());
            setUpdatedBy(vo.getUpdatedBy());
            setCreationDate(vo.getCreationDate());
            setUpdateDate(vo.getUpdateDate());
            setId(vo.getId());
            setEmployeeName(vo.getEmployeeName());
            setCenterName(vo.getCenterName());
            setUserEmirateCode(vo.getUserEmirateCode());
            
        }
        
    }
    
     /**
      * Gets EID Buffer Days.
      * 
      * @return EID Buffer Days.
      */
     public Integer getEidBufferDays() {
         return eidBufferDays;
     }
     
     /**
      * Sets EID Buffer Days.
      * 
      * @param eidBufferDays : EID Buffer Days.
      */
     public void setEidBufferDays(Integer eidBufferDays) {
         this.eidBufferDays = eidBufferDays;
     }
    
}