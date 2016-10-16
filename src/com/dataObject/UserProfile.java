/*
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  15/01/2008  - File created.
 */

//package ae.rta.util.web;
package com.dataObject;



import java.io.Serializable;

import java.util.List;

import com.HomeBudget.common.GlobalUtilities;

/**
 * User profile bean value object. Used to encapsulated authenticated user
 * profile.
 *
 * @author Eng. Ayman Atiyeh
 * @version 1.00
 */
public class UserProfile implements Serializable {
    /*
     * Constants and class variables.
     */

    /** HTTP session attribute used to save user profile. */
    public static final String USER_PROFILE_BEAN = "user_profile";
    
    /** User levels. */
    public static final Integer LEVEL_TRAFFIC_ADMIN = new Integer(1);
    public static final Integer LEVEL_RTA_ADMIN = new Integer(2);
    public static final Integer LEVEL_DUBAI_POLICE_ADMIN = new Integer(3);
    public static final Integer LEVEL_REMOTE_USER = new Integer(4);
    public static final Integer LEVEL_SYSTEM_USER = new Integer(5);
    public static final Integer LEVEL_DUBAI_POLICE_USER = new Integer(6);
    
    /*
     * Instance variables
     */
    
    /** Traffic profile value object. */
    private Profile profile;
    
    /** Service Channel Id.*/
    private Long serviceChannelId;
    
    /** Password.*/
    private String password;

    /*
     * Constructors
     */

    /**
     * Default constructor.
     */
    public UserProfile() {
        // Empty body.
    }

    /**
     * Construct and initialize new UserProfile object.
     * 
     * @param prof Traffic profile value object.
     */
    public UserProfile(Profile prof) {
        setProfile(prof);
    }

    /*
     * Methods
     */
    
    /**
     * Set Traffic profile value object.
     * 
     * @param prof Traffic profile value object.
     */
    public void setProfile(Profile prof) {
        if (prof == null) {
            throw new IllegalStateException("NULL profile object");
        }

        this.profile = prof;
    }

    /**
     * Get Traffic profile value object.
     * 
     * @return Traffic profile value object.
     */
    public Profile getProfile() {
        return profile;
    }
    
    /**
     * Get profile attribute
     * 
     * @param attrId attribute ID.
     * @return profile attribute.
     */
    private Object getAttribute(short attrId) {
        if (getProfile() == null) {
            throw new IllegalStateException("NULL profile");
        }

        return getProfile().getAttribute(attrId);
    }
    
    /**
     * Get String attribute.
     * 
     * @param attrId attribute ID.
     * @return String attribute.
     */
    private String getString(short attrId) {
        Object obj = getAttribute(attrId);
        return (obj != null) ? obj.toString() : null;
    }
    
    /**
     * Get long attribute.
     * 
     * @param attrId attribute ID.
     * @return Long attribute.
     */
    private Long getLong(short attrId) {
        Object obj = getAttribute(attrId);
        if (obj == null) {
            return null;
        }

        if (obj instanceof Integer) {
            return new Long(((Integer) obj).intValue());
        }

        throw new IllegalStateException(new StringBuffer(
          "Invalid Integer attribute type")
            .append(", class=").append(obj.getClass().getName())
              .append(", value=").append(obj).toString());
    }

    /**
     * Get integer attribute.
     * 
     * @param attrId attribute ID.
     * @return integer attribute.
     */
    private Integer getInteger(short attrId) {
        Object obj = getAttribute(attrId);
        if (obj == null) {
            return null;
        }

        if (obj instanceof Integer) {
            return (Integer) obj;
        }

        throw new IllegalStateException(new StringBuffer(
          "Invalid Integer attribute type")
            .append(", class=").append(obj.getClass().getName())
              .append(", value=").append(obj).toString());
    }

    /**
     * Get boolean attribute.
     * 
     * @param attrId attribute ID.
     * @return boolean attribute.
     */
    private Boolean getBoolean(short attrId) {
        Object obj = getAttribute(attrId);
        if (obj == null) {
            return null;
        }

        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }

        throw new IllegalStateException(new StringBuffer(
          "Invalid Boolean attribute type")
            .append(", class=").append(obj.getClass().getName())
              .append(", value=").append(obj).toString());
    }

    /**
     * Get username.
     * 
     * @return username.
     */
    public String getUsername() {
        return getString(Profile.USER_NAME);
    }
    
    /**
     * Get user ID.
     * 
     * @return user ID.
     */
    public Long getUserId() {
        return getLong(Profile.USER_ID);
    }
    
    /**
     * Get user emirate code used for login.
     * 
     * @return user emirate code used for login.
     */
    public String getEmirateCode() {
        return getString(Profile.USER_EMIRATE_CODE);
    }

    /**
     * Get center ID.
     * 
     * @return center ID.
     */
    public Long getCenterId() {
        return getLong(Profile.USER_CENTER_ID);
    }

    /**
     * Get user center name used for login.
     * 
     * @return user center name used for login.
     */
    public String getCenterName() {
        return getString(Profile.USER_CENTER_NAME_E);
    }

    /**
     * Get employee name.
     * 
     * @return employee name.
     */
    public String getEmployeeName() {
        return getString(Profile.EMPLOYEE_NAME);
    }
    
    /**
     * Get employee name E.
     * 
     * @return employee name E.
     */
    public String getEmployeeNameE() {
        return getString(Profile.EMPLOYEE_NAME_E);
    }

    /**
     * Get employee rank description.
     * 
     * @return employee rank description.
     */
    public String getEmployeeRank() {
        return getString(Profile.EMPLOYEE_RANK_DESC);
    }

    /**
     * Get employee number.
     * 
     * @return employee number.
     */
    public String getEmployeeNo() {
        return getString(Profile.EMPLOYEE_NUMBER);
    }

    /**
     * Get employee ID.
     * 
     * @return employee ID.
     */
    public Long getEmployeeId() {
        return getLong(Profile.EMPLOYEE_ID);
    }

    /**
     * Get user groups.
     * 
     * @return user groups.
     */
    public List getUserGroups() {
        return (List) getProfile().getAttribute(Profile.USER_GROUPS);
    }

    /**
     * Get center name in arabic.
     * 
     * @return center name in arabic.
     */
    public String getCenterNameAr() {
        return getString(Profile.USER_CENTER_NAME);
    }

    /**
     * Get user level.
     * 
     * @return user level.
     */
    public Integer getUserLevel() {
        return getInteger(Profile.USER_LEVEL);
    }

    /**
     * Get user security admin level.
     * 
     * @return user security admin level.
     */
    public Integer getAdminLevel() {
        if (isTrafficAdmin()) {
            return RoleVO.ADMIN_LEVEL_SYSTEM;
            
        } else if (isRTAAdmin()) {
            return RoleVO.ADMIN_LEVEL_RTA;

        } else if (isDubaiPoliceAdmin()) {
            return RoleVO.ADMIN_LEVEL_DUBAI_POLICE;

        } else {
            return null;
        }
    }

    /**
     * Check if the logged in user is traffic admin or not.
     * 
     * @return true if admin.
     */
    public boolean isTrafficAdmin() {
        Integer userLevel = getUserLevel();
        return (userLevel != null && userLevel.equals(LEVEL_TRAFFIC_ADMIN));
    }
    
    /**
     * Check if the logged in user is RTA admin or not.
     * 
     * @return true if admin.
     */
    public boolean isRTAAdmin() {
        Integer userLevel = getUserLevel();
        return (userLevel != null && userLevel.equals(LEVEL_RTA_ADMIN));
    }

    /**
     * Check if the logged in user is dubai police admin or not.
     * 
     * @return true if admin.
     */    
    public boolean isDubaiPoliceAdmin() {
        Integer userLevel = getUserLevel();
        return (userLevel != null && userLevel.equals(LEVEL_DUBAI_POLICE_ADMIN));
    }
    
      
    
    
    /**
     * Check if the logged in user is a remote.
     * 
     * @return true if the logged in user is a remote user.
     */    
    public boolean isRemoteUser() {
        Integer userLevel = getUserLevel();
        return (userLevel != null && userLevel.equals(LEVEL_REMOTE_USER));
    }

    /**
     * Check if the logged in user is a system.
     * 
     * @return true if the logged in user is a system user.
     */    
    public boolean isSystemUser() {
        Integer userLevel = getUserLevel();
        return (userLevel != null && userLevel.equals(LEVEL_SYSTEM_USER));
    }

    /**
     * Check if this user profile belongs to the internet user.
     * 
     * @return true if this user profile belongs to the internet user.
     */
    public boolean isInternetUser() {
        Boolean attr = getBoolean(Profile.IS_INTERNET_USER);
        return (attr != null) ? attr.booleanValue() : false;
    }

    /**
     * Check if this user profile belongs to the kiosk user.
     * 
     * @return true if this user profile belongs to the kiosk user.
     */
    public boolean isKioskUser() {
        Boolean attr = getBoolean(Profile.IS_KIOSK_USER);
        return (attr != null) ? attr.booleanValue() : false;
    }

    /**
     * Check if this user profile belongs to the IVR user.
     * 
     * @return true if this user profile belongs to the IVR user.
     */
    public boolean isIVRUser() {
        Boolean attr = getBoolean(Profile.IS_IVR_USER);
        return (attr != null) ? attr.booleanValue() : false;
    }

    /**
     * Check if this center is using credit cards for its payments.
     * 
     * @return true if this center is using credit cards for its payments.
     */
    public boolean isCreditCardCenter() { 
        Integer attr = getInteger(Profile.IS_CREDIT_CENTER);
        return (attr != null && attr.intValue() == 2);
    }

    
    /**
     * Returns the String representation of this object.
     * @return String representation of this object.
     */
    public String toString() {
        StringBuffer buf = new StringBuffer(getClass().getName());
        buf.append("{\n")
           .append(  "  userId=").append(getUserId())
           .append("\n, username=").append(getUsername())
           .append("\n, employeeId=").append(getEmployeeId())
           .append("\n, employeeName=").append(getEmployeeName())
           .append("\n, employeeRank=").append(getEmployeeRank())
           .append("\n, employeeNo=").append(getEmployeeNo())
           .append("\n, emirateCode=").append(getEmirateCode())
           .append("\n, centerId=").append(getCenterId())
           .append("\n, centerName=").append(getCenterName())
           .append("\n, centerNameAr=").append(getCenterNameAr())
           .append("\n, userLevel=").append(getUserLevel())
           .append("\n, isTrafficAdmin=").append(isTrafficAdmin())
           .append("\n, userGroups=").append(getUserGroups())
           .append("\n, isRTAAdmin=").append(isRTAAdmin())
           .append("\n, isDubaiPoliceAdmin=").append(isDubaiPoliceAdmin())
           .append("\n, isInternetUser=").append(isInternetUser())
           .append("\n, isKioskUser=").append(isKioskUser())
           .append("\n, isIVRUser=").append(isIVRUser())
           .append("\n, isCreditCardCenter=").append(isCreditCardCenter())
          // .append("\n, multipleLoginMode=").append(getMultipleLoginMode())
           .append("\n, lanCode=").append(getLanCode())
           .append( "\n}");

        return buf.toString();
    }

    /**
     * Check if the logged in user center can pay by credit
     * 
     * @return true if the logged in user center can pay by credit
     */
    public boolean isCanPayByCredit(){
        Integer payByCredit = getInteger(Profile.CAN_PAY_BY_CREDIT);
        return (payByCredit != null) ? payByCredit.equals(new Integer(2)) : false;
    }
    
    /**
     * Check if the logged in user is dubai police user or not.
     * 
     * @return true if dubai police user.
     */    
    public boolean isDubaiPoliceUser() {
        Integer userLevel = getUserLevel();
        return (userLevel != null && userLevel.equals(LEVEL_DUBAI_POLICE_USER));
    }    

    
    /**
     * Check If Need To Check New Security Model.
     * 
     * @return true If Need To Check New Security Model.
     */
    public boolean isNeedToCheckNewSecurityModel() {
        Boolean checkSecurity = (Boolean)getAttribute(Profile.CHECK_NEW_SECURITY_MODEL);
        if(checkSecurity != null) {
            return checkSecurity.booleanValue();
        }
        return false;
    }
    
    public boolean isCheckMacCenter() {
        Boolean checkMacCenter = (Boolean)getAttribute(Profile.IS_CHECK_MAC_CENTER);
        if(checkMacCenter != null) {
            return checkMacCenter.booleanValue();
        }
        return false;
    }
    
    public boolean isUserMacChecked() {
        Boolean userMacChecked = (Boolean)getAttribute(Profile.IS_USER_MAC_CHECKED);
        if(userMacChecked != null) {
            return userMacChecked.booleanValue();
        }
        return false;
    }

    /**
     * Check If The User Is Granted To A Role.
     * 
     * @param roleCode Role Code.
     * @return true If The User Is Granted To A Role.
     */
    public boolean isUserInRole(String roleCode) {
//        UserSecurityContext userSecurityContext = getUserSecurityContext();
//        if(!GlobalUtilities.isBlankOrNull(roleCode) && userSecurityContext != null) {
//            return userSecurityContext.isUserInRole(roleCode,getCenterId());
//        }
        return false;
    }


    /**
     * Get lan code.
     * 
     * @return lan.
     */
    public String getLanCode() {
        return getString(Profile.LANG_CODE);
    }

    /**
     * Setter Service Channel Id.
     * 
     * @param serviceChannelId : Service Channel Id.
     */
    public void setServiceChannelId(Long serviceChannelId) {
        this.serviceChannelId = serviceChannelId;
    }

    /**
     * Getter Service Channel Id.
     * 
     * @return Service Channel Id.
     */
    public Long getServiceChannelId() {
        return serviceChannelId;
    }

    /**
     * Setter Password.
     * 
     * @param password : Password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter Password.
     * 
     * @return Password.
     */
    public String getPassword() {
        return password;
    }


    /**
     * Getter for Domain User Host
     * @return domainUserHost Domain User Host
     */
    public String getDomainUserHost() {
        return getString(Profile.DOMAIN_USER_HOST);
    }

    /**
     * Getter for User IP Adress
     * @return userIPAdress User IP Adress
     */
    public String getUserIPAdress() {
        return getString(Profile.USER_IP_ADDRESS);
    }
 
    /**
     * Getter for Session Id
     * @return sessionId Session Id
     */
    public String getSessionId() {
        return getString(Profile.SESSION_ID);
    }
    /**
     * Getter for User Log Id
     * @return userLogId User Log Id
     */
    public Long getUserLogId() {
        return getLong(Profile.USER_LOG_ID); 
    }

}
