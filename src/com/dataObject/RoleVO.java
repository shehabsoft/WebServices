/*
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Alaa Salem         12/10/2009  - File created.
 */
package  com.dataObject;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Security Role Value object
 *
 * @author Alaa Salem
 * @version 1.00
 */
public class RoleVO extends ValueObject {

    /*
     * Constants.
     */

    /** Admin levels. */
    public static final Integer ADMIN_LEVEL_SYSTEM = new Integer(1);
    public static final Integer ADMIN_LEVEL_RTA = new Integer(2);
    public static final Integer ADMIN_LEVEL_DUBAI_POLICE = new Integer(3);
    public static final Integer ADMIN_LEVEL_ALL = new Integer(4);

    /** Role types. */
    public static final Integer ROLE_TYPE_BUSINESS_OWNER = new Integer(1);
    public static final Integer ROLE_TYPE_MODULE = new Integer(2);
    public static final Integer ROLE_TYPE_SERVICES = new Integer(3);
    public static final Integer ROLE_TYPE_PAGE = new Integer(4);
    public static final Integer ROLE_TYPE_PAGE_OBJECT = new Integer(5);
    public static final Integer ROLE_TYPE_OBJECT_ITEM = new Integer(6);
    public static final Integer ROLE_TYPE_CUSTOM_ROLE = new Integer(7);
    
    /** Security Mode. */
    public static final Integer SECURITY_MODE_NO_CONTENT_SECURITY = new Integer(1);
    public static final Integer SECURITY_MODE_OLD_MODEL = new Integer(2);

    /** ROLE CODES */ 
    public static final String BANK_CHECKER_ROLE = "VHL-006";
    
    public static final String BANK_MAKER_ROLE = "VHL-008";
    
    public final static String SALES_CHECKER_ROLE = "VEHICLE_SALES_CHECKER";
    
    public final static String SALES_MAKER_ROLE = "VEHICLE_SALES_MAKER";
    
    public static final String ADD_MORTGAGE_ROLE = "MORTGAGE_ADD";
    public static final String APPROVE_MORTGAGE_ROLE = "MORTGAGE_APPROVE";
    
    /*
     * Fields.
     */

    /** Role Path. */
    private String path;
    
    /** Role Arabic description. */
    private String descriptionAr;
    
    /** Role English description. */
    private String descriptionEn;
    
    /** Role Admin Level. */
    private Integer adminLevel;
    
    /** Rarent Role. */
    private RoleVO parentRole;
    
    /** Role Status. */
    private Integer status;
    
    /** Role Status Date. */
    private Date statusDate;

    /** Role Remarks. */
    private String remarks;

    /** Virtual Role Flag. */
    private Integer virtualRole;

    /** Role Type. */
    private Integer roleType;
    
 
    
    /** Service Code. */
    private ServiceVO service;
    
    /** URL. */
    private String url;
    
    /** New URL. */
    private String newURL;

    /** Role Code. */
    private String roleCode;
    
    /** Item Domain. */
    private String itemDomain;
    
    /** Item Value. */
    private String itemValue;
    
    /** Admin Level Description. */
    private String adminLevelDesc;

    /** Role Status Description. */
    private String statusDesc;
    
    /** Role Type Description. */
    private String roleTypeDesc;
    
    /** Require Emirates Id. */
    private Integer requireEid;
    
    private Integer objectSeq;
    
    /** can be denied. */
    private Integer canBeDenied;
    
    /** security mode*/
    private Integer securityMode;

    /*
     * Constructors.
     */
     
    /**
     * Override Default Constructor.
     */
    public RoleVO() {
        // Empty Constructor.
    }

    /**
     * Overloaded Constructor To Extend Functionality.
     * 
     * @param pageUrl Page URL.
     * @param roleType Role Type.
     */
    public RoleVO(String pageUrl, Integer roleType) {
        setUrl(pageUrl);
        setRoleType(roleType);
    }

    /*
     * Methods.
     */

    /**
     * Set Role Path.
     * 
     * @param path Role Path.
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Get Role Path.
     * 
     * @return Role Path.
     */
    public String getPath() {
        return path;
    }

    /**
     * Set Role Arabic description.
     * 
     * @param descriptionAr  Role Arabic description.
     */
    public void setDescriptionAr(String descriptionAr) {
        this.descriptionAr = descriptionAr;
    }

    /**
     * Get Role Arabic description.
     * 
     * @return  Role Arabic description.
     */
    public String getDescriptionAr() {
        return descriptionAr;
    }

    /**
     * Set Role English description.
     * 
     * @param descriptionEn Role English description.
     */
    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    /**
     * Get Role English description.
     * 
     * @return Role English description.
     */
    public String getDescriptionEn() {
        return descriptionEn;
    }

    /**
     * Set Role Admin Level.
     * 
     * @param adminLevel Role Admin Level.
     */
    public void setAdminLevel(Integer adminLevel) {
        if(adminLevel == null ||
            adminLevel.equals(ADMIN_LEVEL_SYSTEM) || 
            adminLevel.equals(ADMIN_LEVEL_RTA) || 
            adminLevel.equals(ADMIN_LEVEL_DUBAI_POLICE) ||
            adminLevel.equals(ADMIN_LEVEL_ALL)) {
            this.adminLevel = adminLevel;
            return;
        }
        throw new IllegalArgumentException("Invalid admin level: " + adminLevel);
    }

    /**
     * Check if admin level is `system`.
     * 
     * @return true if admin level is `system`.
     */
    public boolean isAdminLevelSystem() {
        return adminLevel != null && adminLevel.equals(ADMIN_LEVEL_SYSTEM);
    }

    /**
     * Check if admin level is `RTA`.
     * 
     * @return true if admin level is `RTA`.
     */
    public boolean isAdminLevelRta() {
        return adminLevel != null && adminLevel.equals(ADMIN_LEVEL_RTA);
    }

    /**
     * Check if admin level is `dubai police`.
     * 
     * @return true if admin level is `dubai police`.
     */
    public boolean isAdminLevelDubaiPolice() {
        return adminLevel != null &&
            adminLevel.equals(ADMIN_LEVEL_DUBAI_POLICE);
    }

    /**
     * Check if admin level is `all`.
     * 
     * @return true if admin level is `all`.
     */
    public boolean isAdminLevelAll() {
        return adminLevel != null &&
            adminLevel.equals(ADMIN_LEVEL_ALL);
    }

    /**
     * Get Role Admin Level.
     * 
     * @return Role Admin Level.
     */
    public Integer getAdminLevel() {
        return adminLevel;
    }

    /**
     * Set Rarent Role.
     * 
     * @param parentRole Rarent Role.
     */
    public void setParentRole(RoleVO parentRole) {
        this.parentRole = parentRole;
    }

    /**
     * Get Rarent Role.
     * 
     * @return Rarent Role.
     */
    public RoleVO getParentRole() {
        return parentRole;
    }

    /**
     * Set Role Status.
     * 
     * @param status Role Status.
     */
    public void setStatus(Integer status) {
         if (status == null ||
            status.equals(TRUE) ||
            status.equals(FALSE)) {
            this.status = status;
            return;
        }
        throw new IllegalArgumentException("Invalid status: " + status);
    }

    /**
     * Check if role status is active.
     * 
     * @return true if role status is active.
     */
    public boolean isStatusActive() {
        return status != null && status.equals(TRUE);
    }

    /**
     * Get Role Status.
     * 
     * @return Role Status.
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Set Role Status Date.
     * 
     * @param statusDate Role Status Date.
     */
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    /**
     * Get Role Status Date.
     * 
     * @return Role Status Date.
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * Set Role Remarks.
     * 
     * @param remarks Role Remarks.
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * Get Role Remarks.
     * 
     * @return Role Remarks.
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * Set Virtual Role Flag.
     * 
     * @param virtualRole Virtual Role Flag.
     */
    public void setVirtualRole(Integer virtualRole) {
         if (virtualRole == null ||
            virtualRole.equals(TRUE) ||
            virtualRole.equals(FALSE)) {
            this.virtualRole = virtualRole;
            return;
        }
        throw new IllegalArgumentException("Invalid virtualRole: " + virtualRole);
    }

    /**
     * Check if role is virtual.
     * 
     * @return true if role is virtual.
     */
    public boolean isVirtualRole() {
        return virtualRole != null && virtualRole.equals(TRUE);
    }

    /**
     * Get Virtual Role Flag.
     * 
     * @return Virtual Role Flag.
     */
    public Integer getVirtualRole() {
        return virtualRole;
    }

    /**
     * Set Role Type.
     * 
     * @param roleType Role Type.
     */
    public void setRoleType(Integer roleType) {
        if(roleType == null ||
            roleType.equals(ROLE_TYPE_BUSINESS_OWNER) || 
            roleType.equals(ROLE_TYPE_CUSTOM_ROLE) || 
            roleType.equals(ROLE_TYPE_MODULE) ||
            roleType.equals(ROLE_TYPE_OBJECT_ITEM) ||
            roleType.equals(ROLE_TYPE_PAGE) ||
            roleType.equals(ROLE_TYPE_PAGE_OBJECT) ||
            roleType.equals(ROLE_TYPE_SERVICES)) {
            this.roleType = roleType;
            return;
        }
        throw new IllegalArgumentException("Invalid roleType: " + roleType);
    }
    

    /**
     * Check if role type is `business owner`.
     * 
     * @return true if role type is `business owner`.
     */
    public boolean isRoleTypeBusinessOwner() {
        return roleType != null && roleType.equals(ROLE_TYPE_BUSINESS_OWNER);
    }

    /**
     * Check if role type is `custom role`.
     * 
     * @return true if role type is `custom role`.
     */
    public boolean isRoleTypeCustomRole() {
        return roleType != null && roleType.equals(ROLE_TYPE_CUSTOM_ROLE);
    }

    /**
     * Check if role type is `module`.
     * 
     * @return true if role type is `module`.
     */
    public boolean isRoleTypeModule() {
        return roleType != null && roleType.equals(ROLE_TYPE_MODULE);
    }

    /**
     * Check if role type is `object item`.
     * 
     * @return true if role type is `object item`.
     */
    public boolean isRoleTypeObjectItem() {
        return roleType != null && roleType.equals(ROLE_TYPE_OBJECT_ITEM);
    }

    /**
     * Check if role type is `page`.
     * 
     * @return true if role type is `page`.
     */
    public boolean isRoleTypePage() {
        return roleType != null && roleType.equals(ROLE_TYPE_PAGE);
    }

    /**
     * Check if role type is `page object`.
     * 
     * @return true if role type is `page object`.
     */
    public boolean isRoleTypePageObject() {
        return roleType != null && roleType.equals(ROLE_TYPE_PAGE_OBJECT);
    }

    /**
     * Check if role type is `services`.
     * 
     * @return true if role type is `services`.
     */
    public boolean isRoleTypeServices() {
        return roleType != null && roleType.equals(ROLE_TYPE_SERVICES);
    }

    /**
     * Get Role Type.
     * 
     * @return Role Type.
     */
    public Integer getRoleType() {
        return roleType;
    }

    /**
     * Set Service.
     * 
     * @param service Service.
     */
    public void setService(ServiceVO service) {
        this.service = service;
    }

    /**
     * Get Service.
     * 
     * @return Service.
     */
    public ServiceVO getService() {
        return service;
    }

    /**
     * Set URL.
     * 
     * @param url URL.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get URL.
     * 
     * @return URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set Role Code.
     * 
     * @param roleCode Role Code.
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * Get Role Code.
     * 
     * @return Role Code.
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * Set Item Domain.
     * 
     * @param itemDomain Item Domain.
     */
    public void setItemDomain(String itemDomain) {
        this.itemDomain = itemDomain;
    }

    /**
     * Get Item Domain.
     * 
     * @return Item Domain.
     */
    public String getItemDomain() {
        return itemDomain;
    }

    /**
     * Set Item Value.
     * 
     * @param itemValue Item Value.
     */
    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    /**
     * Get Item Value.
     * 
     * @return Item Value.
     */
    public String getItemValue() {
        return itemValue;
    }

    /**
     * Set Admin Level Desciption.
     * 
     * @param adminLevelDesc Admin Level Desciption.
     */
    public void setAdminLevelDesc(String adminLevelDesc) {
        this.adminLevelDesc = adminLevelDesc;
    }

    /**
     * Get Admin Level Desciption.
     * 
     * @return Admin Level Desciption.
     */
    public String getAdminLevelDesc() {
        return adminLevelDesc;
    }

    /**
     * Set Status Desciption.
     * 
     * @param statusDesc Status Desciption.
     */
    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    /**
     * Get Status Desciption.
     * 
     * @return Status Desciption.
     */
    public String getStatusDesc() {
        return statusDesc;
    }

    /**
     * Set Role Type Description.
     * 
     * @param roleTypeDesc Role Type Description.
     */
    public void setRoleTypeDesc(String roleTypeDesc) {
        this.roleTypeDesc = roleTypeDesc;
    }

    /**
     * Get Role Type Description.
     * 
     * @return Role Type Description.
     */
    public String getRoleTypeDesc() {
        return roleTypeDesc;
    }

    /**
     * Set require Emirates id.
     * 
     * @param requireEid :require Emirates id.
     */
    public void setRequireEid(Integer requireEid) {
        this.requireEid = requireEid;
    }

    /**
     * Get require Emirates id.
     * 
     * @return requireEid : require Emirates id.
     */
    public Integer getRequireEid() {
        return requireEid;
    }


    public void setObjectSeq(Integer objectSeq) {
        this.objectSeq = objectSeq;
    }


    public Integer getObjectSeq() {
        return objectSeq;
    }

    /**
     * Set can be denied.
     * 
     * @param canBeDenied
     */
    public void setCanBeDenied(Integer canBeDenied) {
        this.canBeDenied = canBeDenied;
    }

    /**
     * Get can be denied.
     * 
     * @return
     */
    public Integer getCanBeDenied() {
        return canBeDenied;
    }
    
    /**
     * Set new page URL related to revamped JSF pages.
     *
     * @param newURL new page URL related to revamped JSF pages.
     */
    public void setNewURL(String newURL) {
        this.newURL = newURL;
    }
    
    /**
     * Get new page URL related to revamped JSF pages.
     * 
     * @return new page URL related to revamped JSF pages.
     */
    public String getNewURL() {
        return newURL;
    }


    /**
     * Set Security Mode.
     * 
     * @param securityMode 
     */
    public void setSecurityMode(Integer securityMode) {
        this.securityMode = securityMode;
    }
    
    /**
     * Get Security Mode.
     * 
     * @return SecurityMode Security Mode.
     */
    public Integer getSecurityMode() {
        return securityMode;
    }
}
