/*
 * Copyright (c) i-Soft 2006.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  07/12/2006  - File created.
 */

package com.dataObject;

/**
 * This class is the central location to store the internal JNDI names.
 *
 * @author Eng. Ayman Atiyeh
 * @version 1.00
 */
public interface JNDINames {

    /** System configuration JNDI name. */
    public static final String SYS_CONFIG = 
          "ae.gov.dphq.traffic.util.common.Config";

    /** DataSource JNDI name. */
    public static final String DATA_SOURCE = 
          "ae.gov.dphq.traffic.jdbc.dataSource";

    /** Hibernate SessionFactory JNDI name. */
    public static final String HIBERNATE_SESSION_FACTORY = 
        "ae.gov.dphq.eps.jndi.hibernate.SessionFactory";

    /**
     * Map<url, securityMode> related to pages migrated from old traffic system with custom security logic inside the old pages.
     * This Security-Mode will be used by security filter to apply the custome security logic on such URLs for backword
     * compatability
     */
    public static final String SEC_ROLE_SECURITY_MODE = 
        "ae.rta.security.Role.SecurityMode";
}