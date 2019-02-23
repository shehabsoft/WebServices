/*
 * Copyright (c) i-Soft 2019.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer                Date        Comments
 * ----- -----------------        ----------  ----------------------------------------
 * 1.00  Eng. shehab eldin tarek  19/1/2019  - File created.
 */

package com.dataObject;

/**
 * This class is the central location to store the internal JNDI names.
 *
 * @author Eng.shehab eldin tarek 
 * @version 1.00
 */
public interface JNDINames {

    /** System configuration JNDI name. */
    public static final String SYS_CONFIG = 
          "hb.Config";

    

    /**
     * Map<url, securityMode> related to pages migrated from old traffic system with custom security logic inside the old pages.
     * This Security-Mode will be used by security filter to apply the custome security logic on such URLs for backword
     * compatability
     */
    public static final String SEC_ROLE_SECURITY_MODE = 
        "hb.security.Role.SecurityMode";
}