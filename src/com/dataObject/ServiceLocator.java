/*
 * Copyright (c) i-Soft 2006.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer               Date        Comments
 * ----- -----------------      ----------  ----------------------------------------
 * 1.00  Eng. shehab eldin tarek  19/01/2019  - File created.
 */

package com.dataObject;



import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;

/**
 * Centralize distributed service objects lookups and provides a centralized
 * point of control for locating services and resources.
 *
 * @author Eng. shehab eldin tarek 
 * @version 1.00
 */
public class ServiceLocator {
    /*
     * Fields.
     */

    /** Initial context reference. */
    private InitialContext context;

    /** Cashed resources map. */
    private Map<String, Object> cache;

    /** Singleton object. */
    private static ServiceLocator singleton;

    /** Logger object. */
    private static final Logger logger = Logger.getLogger(ServiceLocator.class.getName());

    /*
     * Constructors
     */

    /**
     * Construct and initialize ServiceLocator singleton object.
     */
    private ServiceLocator() throws ServiceLocatorException {
        try {
        // Create initial context
        context = new InitialContext();            
        cache = Collections.synchronizedMap(new HashMap<String, Object>());

      } catch (NamingException ex) {
            logger.log(Level.SEVERE, "Failed to InitialContext", ex);
            throw new ServiceLocatorException(ex);
       }
    }

    /*
     * Methods
     */

    /**
     * This methods will throw CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("ServiceLocator cannot be cloned");
    }

    /**
     * Get ServiceLocator reference.
     * 
     * @return ServiceLocator reference.
     */
    public synchronized static ServiceLocator getInstance() 
                        throws ServiceLocatorException {
        if (singleton == null) {
            singleton = new ServiceLocator();
        }

        return singleton;
    }

    /**
     * Get configuration object.
     * 
     * @return configuration object.
     */
    public SmartHomeBudgetConfig getConfig() {
       

        try {
            logger.info("Loading system configuration");
            SmartHomeBudgetConfig config = getSystemConfiguration();

            cache.put(JNDINames.SYS_CONFIG, config);
            return config;

        } catch (Exception ex) {
            String err = "Failed to load system configuration";
            logger.log(Level.SEVERE, err, ex);
            throw new ServiceLocatorException(err, ex);
        }
    }

    /**
     * Get URLs related to pages migrated from old traffic system with custom security logic inside the old pages.
     * This Security-Mode will be used by security filter to apply the custome security logic on such URLs for backword
     * compatability
     * 
     * @return Map<URL, SecurityMode>
     */
    public Map<String, Integer> getSecurityModeURLs() {
        if (cache.containsKey(JNDINames.SEC_ROLE_SECURITY_MODE)) {
            return (Map<String, Integer>) cache.get(JNDINames.SEC_ROLE_SECURITY_MODE);
        }

        try {
            logger.info("Load security mode urls...");
            Map<String, Integer> map = loadSecurityModeURLs();

            cache.put(JNDINames.SEC_ROLE_SECURITY_MODE, map);
            return map;

        } catch (Exception ex) {
            String err = "Failed to load security mode URL's";
            logger.log(Level.SEVERE, err, ex);
            throw new ServiceLocatorException(err, ex);
        }
    }

    

    
    
    /*
     * Private Helper Methods
     */

    /**
     * Get system configuration.
     * 
     * @return system configuration.
     */
    private SmartHomeBudgetConfig getSystemConfiguration() {
    
        Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());

       

        // Save config object on the cache map
        SmartHomeBudgetConfig config = new SmartHomeBudgetConfig(map);

        // Return configuration reference
        logger.info(new StringBuffer("Config loaded: ").append(config).toString());
        return config;
    }

    


    /**
     * Get URLs related to pages migrated from old traffic system with custom security logic inside the old pages.
     * This Security-Mode will be used by security filter to apply the custome security logic on such URLs for backword
     * compatability
     * 
     * @return Map<URL, SecurityMode>
     */
    private Map<String, Integer> loadSecurityModeURLs() {
        return new RoleHandler().getSecurityModeURLs();
    }
}