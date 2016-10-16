/*
 * Used to print custm debug messages
 * 
 * Auther       : Eng. Ayman Atiyeh
 * Creation Date: 06/09/2004
 * 
 * ver   Developer          Date        Comments
 * ----  -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  06/09/2004  - First version
 */

package com.dataObject;

import javax.servlet.http.HttpSession;

/**
 * WARNING: This class will be removed from traffic project. Use Log4j API's.
 * 
 * Used to print custm debug messages
 *
 * @version 1.00 - 06/09/2004
 * @author Eng. shehab tarek
 */
public abstract class TrafficLogger {
    /*
     * Class variables.
     */

    /** Configuration object. */
    private static final TrafficConfig config = ServiceLocator.getInstance().getConfig();

    /*
     * Methods
     */

    /**
     * Print custom debug message. If the debugging flag in the 
     * "ae.gov.dphq.traffic.util.TrafficConfig" class was disabled, nothing
     * will be printed.
     * 
     * @param src Message source
     * @paran msg Message test
     */
    public static void debug(String src, Object msg) {
        // Check if debugging is enabled
        if (config.isDebugEnabled() == false) {
            return;
        }

        // Create log message
        StringBuffer log = new StringBuffer();
        log.append("[")
           .append(DateTimeFormatter.formatDateTime(System.currentTimeMillis()))
           .append("] ")
           .append(src)
           .append(": ")
           .append(msg);

        // Debug lof message
        System.out.println(log);
    }

    /**
     * Log error message. This message will be logged regarding of the debugging
     * flag status.
     * 
     * @param src Message source
     * @paran msg Message test
     */
    public static void logErrorMsg(String src, Object msg) {
        // Create log message
        StringBuffer log = new StringBuffer();
        log.append("[")
           .append(DateTimeFormatter.formatDateTime(System.currentTimeMillis()))
           .append("] [ERROR] [")
           .append(src)
           .append(": ")
           .append(msg);

        // Log error message
        System.out.println(log);
    }

    /**
     * Log stack trace in the application error pages
     * 
     * @param session HTTP user session info
     * @param exception Exception to be loged
     */
    public static void debugStackTrace(HttpSession session, Throwable exception) {
        // Check if error page stack trace debugging is enabled
        if (! config.isErrorPageDebugEnabled()) {
            return;
        }

        if (session == null || exception == null) {
            return;
        }

        // Create log message
        StringBuffer errLog = new StringBuffer();
        errLog.append("============ errLog ================")
             .append("\nTime: ").append(DateTimeFormatter.formatDateTime(System.currentTimeMillis()))
             .append("\nTotal memory: ").append(Runtime.getRuntime().totalMemory())
             .append("\nFree memory: ").append(Runtime.getRuntime().freeMemory());

        Profile profile = (Profile) session.getAttribute("user_profile");
        if (profile != null) {
            errLog.append("\nUser name: ").append(profile.getAttribute(profile.USER_NAME))
                  .append("\nCenter ID: ").append(profile.getAttribute(profile.USER_CENTER_ID));
        }
         
        errLog.append("\nStack trace:");
        System.out.println(errLog);
        exception.printStackTrace(System.out);
    }
}