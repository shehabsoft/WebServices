/*
 * Copyright (c) i-Soft 2004.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * Creation Date: 12/12/2004
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  12/12/2004  - File created.
 * 1.01  Ahmed Abdelwahab   09/07/2012  - TRF-6791 update getOrgExpiredBooklets
 * 1.02  Bashar Alnemrawi   19/02/2013  - Add addLog() method.
 *                                      - Improvement All Brs to Add Log exceptions.
 */

package  com.dataObject;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import com.HomeBudget.common.GlobalUtilities;


/**
 * This class used to implement business rules validation on GUI. It was 
 * created to move the code from JSP pages (such as vehicle main page) into 
 * Java class.
 * 
 * WARNING: Most of the BR implementation must be moved from VehicleServletDAO
 *          class to this class.
 *
 * @version 1.01
 * @author Eng.  shehab .tarek
 */
public abstract class BrValidator {
    /*
     * Constants and class variables
     */
  
    /** Business rule name. */
    private static final String BR_TRS012_MER = "BR_TRS012_MER";
    private static final String BR_TRS050_MER = "BR_TRS050_MER";
    private static final String BR_TRS013_MER = "BR_TRS013_MER";
    private static final String BR_TRS014_MER = "BR_TRS014_MER";
    private static final String BR_TRS018_MER = "BR_TRS018_MER";
    private static final String BR_TRS036_MER = "BR_TRS036_MER";
    private static final String BR_TRS042_MER = "BR_TRS042_MER";
    private static final String BR_REG011_MER = "BR_REG011_MER";
    private static final String BR_REG026_MER = "BR_REG026_MER";
    private static final String BR_COS002_MER = "BR_COS002_MER";
    private static final String BR_COS006_MER = "BR_COS006_MER";
    private static final String BR_COS007_MER = "BR_COS007_MER";
    private static final String BR_COS022_MER = "BR_COS022_MER";
    private static final String BR_COS050_MER = "BR_COS050_MER";
    private static final String BR_ETE007_MER = "BR_ETE007_MER";
    private static final String BR_ETE014_MER = "BR_ETE014_MER";
    private static final String BR_ETC006_MER = "BR_ETC006_MER";
    private static final String BR_BKT021_MER = "BR_BKT021_MER";
    private static final String BR_TRS051_MER = "BR_TRS051_MER";
    private static final String BR_TRS052_MER = "BR_TRS052_MER";
    private static final String BR_TRS053_MER = "BR_TRS053_MER";
    private static final String BR_TRS093_MER = "BR_TRS093_MER";
    private static final String BR_REG035_MER = "BR_REG035_MER";
    private static final String BR_REG040_MER = "BR_REG040_MER";
    private static final String BR_TRS0128_MER = "BR_TRS0128_MER";
    private static final String BR_REG042_MER = "BR_REG042_MER";
    private static final String BR_REG043_MER = "BR_REG043_MER";
    private static final String BR_REG044_MER = "BR_REG044_MER";
    private static final String BR_COS059_MER = "BR_COS059_MER";
    private static final String BR_TRS221_MER = "BR_TRS221_MER";
    

    /** Servic code */
    private static final int SVC_REGISTRATION      = 201;
    private static final int SVC_CHANGE_OWNERSHIP  = 202;
    private static final int SVC_CODE_VHL_CHG_OWNERSHIP_FOR_YEAR  = 203;     
    private static final int SVC_EXPORT_TO_COUNTRY = 206;
    private static final int SVC_EXPORT_TO_EMIRATE = 207;
    
    
    private static final Long IS_DED_LICENSE = new Long(1);

    /*
     * Param names constants.
     */

    /** Param name constant: Traffic ID. */
    public static final String PARAM_TRF_ID = "trfId";

    /** Param name constant: Booklet ID. */
    public static final String PARAM_BKT_ID = "bktId";

    /** Param name constant: VLE ID. */
    public static final String PARAM_VLE_ID = "vleId";

    /** Param name constant: Traffic number. */
    public static final String PARAM_TRF_NO = "trfNo";

    /** Param name constant: No of wanted confiscated vehicles. */
    public static final String PARAM_WANTED_VLE_CONFIS = "wantedVleConfis";

    /** Param name constant: Total no of circulated notes. */
    public static final String PARAM_TOTAL_CIRC_NOTES = "totalCircNotes";

    /** Param name constant: Expired booklets count. */
    public static final String PARAM_EXPIRED_BOOKLETS_COUNT = "expiredBktCount";

    /** Param name constant: booklet expiry date. */
    public static final String PARAM_BKT_EXPIRY_DATE = "bktExpiryDate";

    /** Param name constant: If "2" means organization file. */
    public static final String PARAM_SEARCH_ORG = "searchOrg";

    /** Param name constant: Plate number. */
    public static final String PARAM_PLATE_NO = "plateNo";

    /** Param name constant: Plate category. */
    public static final String PARAM_PLATE_CATEGORY = "plateCat";

    /** Param name constant: Plate code. */
    public static final String PARAM_PLATE_CODE = "plateCode";

    /** Param name constant: Plate ID. */
    public static final String PARAM_PLATE_ID = "plateId";
    
    /** Param name constant: VDS ID. */
    public static final String PARAM_VDS_ID = "vdsId";

    /** Param name constant: File source ID (PRS_ID or ORG_ID). */
    public static final String PARAM_SRC_ID = "srcId";

    /** Param name constant: VCL_CLASS. */
    public static final String PARAM_VCL_CLASS = "vclClass";

    /** Param name constant: Old traffic number. */
    public static final String PARAM_OLD_TRF_NO = "oldTrfNo";

    /** Param name constant: Model year. */
    public static final String PARAM_MODEL_YEAR = "modelYear";

    /** Param name constant: Chasiss number. */
    public static final String PARAM_CHASISS_NO = "chasissNo";

    /** Param name constant: BKT source. */
    public static final String PARAM_BKT_SOURCE = "bktSrc";

    /** Param name constant: Emirates name. */
    public static final String PARAM_EMIRATES = "emirates";

    /** Param name constant: User emirates name. */
    public static final String PARAM_USER_EMI_CODE = "userEmiCode";
    
    /** Uae  Country. */
    private static final int UAE_COUNTRY = 10;
    /** Has No Paper Countru */
    private static final int HAS_NO_PAPERS = 900; 

    /*
     * Methods.
     */

    /**
     * Used to print debug messages
     * 
     * @param msg Message to be printed
     */
    public static void debug(String msg) {
        TrafficLogger.debug("ae.gov.dphq.traffic.util.BrValidator", msg);
    }

    /**
     * Returns violation message related to this business rule.
     * 
     * @return violation message related to this business rule.
     */
    public static String getBrMessage(String brName) {
        String query = " SELECT DESCRIPTION FROM TF_STP_BR_MESSAGES"
                     + " WHERE BRS_BR_LABEL=? AND UPPER(LAN_CODE) = 'AR'";

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = JdbcFactory.getConnection();
            stm = con.prepareStatement(query);
            stm.setString(1, brName);
            rs = stm.executeQuery();

            String err = "No message found for this rule";
            if (! rs.next()) {
                debug("getBrMessage: NOT FOUND, " + brName);
                
                return err + " ( " + brName + " ) ";
            }

            //return JboUtil.toString(rs.getString("DESCRIPTION"), err);
            return null;

        } catch (SQLException sqle) {
            throw new TrafficException(sqle);
        } finally {
            JdbcAdapter.close(rs);
            JdbcAdapter.close(stm);
            JdbcAdapter.close(con);
        }
    }

    /**
     * Get formatted violation message related to this business rule.
     * 
     * @param brName Business rule name.
     * @return Formatted violation message related to this business rule.
     */
    private static String getViolationMessage(String brName, long trfId, int svcCode) {
 
 
        if(ServiceLocator.getInstance().getConfig().isTaskTRF47529Enabled()){
            
            ExceptionalServicesVO exceptionalServicesVO = new ExceptionalServicesVO();
//            exceptionalServicesVO.setExceptionsVO(new ExceptionsVO());
//            exceptionalServicesVO.getExceptionsVO().setTrafficFileVO(new TrafficFileVO());
//            exceptionalServicesVO.getExceptionsVO().getTrafficFileVO().setId(trfId);
//            exceptionalServicesVO.setServicesVO(new ae.rta.common.vo.ServicesVO());
//            exceptionalServicesVO.getServicesVO().setServiceCode(""+svcCode);
              
//            List<ExceptionalRulesVO> exceptionalRules = exceptionalRulesHandler.fetchValidExceptionsBr(exceptionalServicesVO);                
//            
//            if (exceptionalRules != null && exceptionalRules.size() > 0) {
//                for (ExceptionalRulesVO exceptionalRule : exceptionalRules) {
//                    if(exceptionalRule != null && exceptionalRule.getBusinessRuleVO() != null &&
//                        !GlobalUtilities.isBlankOrNull(exceptionalRule.getBusinessRuleVO().getKey())) {
//                        if(exceptionalRule.getBusinessRuleVO().getKey().equals(brName)) {
//                            return "";
//                        }
//                    }
//                    
//                }
//            }        
            
        }
 
        // Append business rule message.
        StringBuffer msg = new StringBuffer();
        msg.append(getBrMessage(brName));

        // Return formatted message.
        msg.append("\n");
        return msg.toString();
    }

    /**
     * Get formatted violation message related to this business rule.
     * 
     * @param brName Business rule name.
     * @return Formatted violation message related to this business rule.
     */
    private static String getViolationMessage(String brName, StringBuffer info, long trfId, int svcCode) {
        // Get BR message
        String brMsg = getBrMessage(brName);

        // Create Tokenizer for the BR message and its info
        StringTokenizer infoTokenizer = new StringTokenizer(info.toString(), ",");
        
        while ( infoTokenizer.hasMoreTokens() )
        {
        
            int start = brMsg.indexOf("<P>", 0);
            if (start == -1) {
                break;
            }
   
            brMsg = brMsg.replaceFirst("<P>", infoTokenizer.nextToken());
        }        

        // Return formatted message.
        brMsg+="\n";
        return brMsg;
    }

    /**
     * Used to check if the BR can be skipped. This method used to implement 
     * the electronic signutre functionality.
     * 
     * @param  trfId Traffic ID to be skipped.
     * @param  svcCode Service code.
     * @param  brName BR name to be skipped.
     * @param  emiCode User emirate code.
     * @return true if the BR was skipped, false otherwise.
     */
    public static boolean canBeSkipped(long trfId, int svcCode, String brName, String emiCode) {
        // Create SQL query
        StringBuffer query = new StringBuffer();
        query.append(" SELECT 1 ")
             .append(" FROM TF_STP_ELECTRONIC_SEGNITURES ")
             .append(" WHERE EMI_CODE = ? ")
             .append(  " AND TRF_ID = ? ")
             .append(  " AND SVC_CODE = ? ")
             .append(  " AND SIGN_DATE = TRUNC(SYSDATE) ")
             .append(  " AND NO_OF_TRANSACTIONS > 0 ")
             .append(  " AND ").append(brName).append("= 2 ");

        // Print debug message
        StringBuffer msg = new StringBuffer("canBeSkipped: ");
        msg.append("trfId=").append(trfId)
           .append(", svcCode=").append(svcCode)
           .append(", EMI_CODE=").append(emiCode);
        debug(msg.toString());
        debug("canBeSkipped: " + query);

        // Execute query
        boolean canBeSkipped = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = JdbcFactory.getConnection();
            stm = con.prepareStatement(query.toString());
            stm.setString(1, emiCode);
            stm.setLong(2, trfId);
            stm.setInt(3, svcCode);
            rs = stm.executeQuery();

            if (rs.next()) {
                debug("canBeSkipped: SKIP, " + brName);
                return true;

            } else {
                debug("canBeSkipped: Cannot SKIP, " + brName);
                return false;
            }

        } catch (Exception sqle) {
            throw new TrafficException(sqle);
        } finally {
            JdbcAdapter.close(rs);
            JdbcAdapter.close(stm);
            JdbcAdapter.close(con);
        }
    }

    /**
     * Check if BR_REG011_MER is valid or not. This business rule checks if the
     * related person allowed to register vehicle or not.
     * 
     * @param trfId Traffic file ID.
     * @param emiCode Emirate code.
     * @return true if the Business rule was valid, false otherwise.
     */
    public static boolean isBrReg011MerInvalid(long trfId, String emiCode) {
        //return ! VehicleServletDAO.isPersonAllowedToRegisterVehicle(trfId, emiCode);
    	return true;
    }

    /**
     * Check if BR_ETE007_MER is valid or not.
     * 
     * @param  bktId Booklet ID.
     * @param  vleId VLE_ID.
     * @param  pltId Plate ID.
     * @param  info BR violation info.
     * @return true if the Business rule was valid, false otherwise.
     */
    public static boolean isBrEte007MerInvalid(long trfId, long bktId, 
                  long vleId, long pltId, StringBuffer info, int svcCode) {
        if (trfId == 19999999) {
            debug("isBrEte007MerInvalid: SKIP, trfId=19999999");
            return false;
        }

        return isVehicleCirculated(vleId, bktId, pltId, null, info, trfId, svcCode);
    }

    /**
     * Check if BR_TRS050_MER is valid or not.
     * 
     * @param trfNo Traffic file number.
     * @param vhlSvcCode VHL service code.
     * @param bktId Booklet ID.
     * @return true if the Business rule was valid, false otherwise.
     */
    public static boolean isBrTrs050MerInvalid(int trfNo, int vhlSvcCode, 
                          long bktId, StringBuffer info) {
        // Validate trfNo
//        if (trfNo <= 0) {
//            debug("isTrfFileHasFines: Missing trfNo...");
//            return false;
//        }
//
//        if (trfNo == 19999999) {
//            debug("isTrfFileHasFines: SKIP, trfNo=19999999");
//            return false;
//        }
//
//        // Get trafic file info.
//        TrafficFile file = VehicleServletDAO.getTrafficFileByNo(trfNo);
//        if (file == null) {
//            debug("isTrfFileHasFines: file not found...");
//            return false;
//        }
//
//        /* *********************************************************************
//         * ************************** Person ***********************************
//         * *********************************************************************
//         */
//        if (file.isPersonFile()) {
//            debug("isTrfFileHasFines: Person traffic file...");
//            // VIP Person
//            if (VehicleServletDAO.isVipPerson(file.getPersonId())) {
//                // (1,3) = (BOOKLET_STATUS_WITH_OWNER, BOOKLET_STATUS_LOST)
//                debug("isTrfFileHasFines: VIP person");
//                if (VehicleServletDAO.isBookletHasSalikTickets(bktId, "1,3")) {
//                    info.append("«·„—ﬂ»…");
//                    return true;
//                    
//                } else {
//                    return false;
//                }
//
//            // Not VIP Person
//            } else {
//                debug("isTrfFileHasFines: Not VIP person");
//                if (BrHandler.isTrfIdHasSalikTickets(file.getId())) {
//                    info.append(" „—ﬂ»«  „„·Êﬂ… ·Â–« «·„·› «·„—Ê—Ì √Ê ⁄·Ï —Œ’…");
//                    return true;
//                    
//                } else {
//                    return false;
//                }
//            }
//
//        /* *********************************************************************
//         * ************************* Organization ******************************
//         * *********************************************************************
//         */
//        } else if (file.isOrganizationFile()) {
//            debug("isTrfFileHasFines: Organization traffic file...");
//            int payFine = orgHandler.getPayFinesType(new Long(file.getOrganizationId()));
//            switch (payFine) {
//                // PAY_FINES_ALL_VHL
//                case 1:
//                     debug("isTrfFileHasFines: PAY_FINES_ALL_VHL");
//                     if (BrHandler.isTrfIdHasSalikTickets(file.getId())) {
//                         info.append("„—ﬂ»«  „„·Êﬂ… ·„·› «·„—Ê—");
//                         return true;
//                     } else {
//                         return false;
//                     }
//                // PAY_FINES_ONE_VHL
//                case 2:
//                     debug("isTrfFileHasFines: PAY_FINES_ONE_VHL");
//                     if (VehicleServletDAO.isBookletHasSalikTickets(bktId)) {
//                         info.append("«·„—ﬂ»…");
//                         return true;
//                     } else {
//                         return false;
//                     }
//                // PAY_FINES_EXP_VHL
//                case 3:
//                     debug("isTrfFileHasFines: PAY_FINES_EXP_VHL");
//                     if (VehicleServletDAO.isTrfIdHasActiveSalikTickets(file.getId())) {
//                         info.append("„—ﬂ»«  „‰ ÂÌ… „„·Êﬂ… ·„·› «·„—Ê—");
//                         return true;
//                     } else {
//                         return false;
//                     }
//            }
//        }
        
         return false;
    }
    
        
    /**
	 *skip Register Entertainment Motorcycle
	 *             
	 * short Description:
     * skipped if the service is register vehicle and vehicle class is entertainment motorcycle
     * 
	 * @return true if can skip else return false.
	 */
    private static boolean skipRegisterEntertainmentMotorcycle(int svcCode , long vehicleClassCode) {
      
//        if (new Integer(svcCode).equals(VehiclesTransactionVO.SVC_CODE_VEHICLE_REGISTRATION)) {
//        
//            //oracle.jbo.domain.Number VclCode  = transactionsImpl.getVehicles().getVehicleClasses().getCode();
//            if( vehicleClassCode == VehicleClassVO.VEHICLE_CLASS_ID_ENTERTAINMENT_MOTORCYCLE.longValue()) {
//                return true; 
//            }  
//        }
       
       return false; 
    }
    
    /**
     * Check if BR_TRS012_MER is valid or not.
     * 
     * @param trfNo Traffic file number.
     * @param vhlSvcCode VHL service code.
     * @param bktId Booklet ID.
     * @return true if the Business rule was valid, false otherwise.
     */
    public static boolean isBrTrs012MerInvalid(int trfNo, int vhlSvcCode, 
                          long bktId, StringBuffer info, String emiCode ,long vehicleClassCode) {
        // Validate trfNo
        if (trfNo <= 0) {
            debug("isTrfFileHasFines: Missing trfNo...");
            return false;
        }

        if (trfNo == 19999999) {
            debug("isTrfFileHasFines: SKIP, trfNo=19999999");
            return false;
        }
        

        //BR to be skipped if the service is register vehicle and vehicle class is entertainment motorcycle
        if(skipRegisterEntertainmentMotorcycle(vhlSvcCode,vehicleClassCode)) {
            return false;
         }
         
        //......................................................................
//        Long plateId = null;
//        if (bktId > 0) {
//            BookletVO bookletVO = bookletHandler.getActiveBookletById(new Long(bktId));
//            if (bookletVO != null && 
//                bookletVO.getPlate() != null &&
//                bookletVO.getPlate().getId() != null) {
//                plateId = bookletVO.getPlate().getId();            
//            }             
//        }
        
//
//        // Get trafic file info.
//        TrafficFile file = VehicleServletDAO.getTrafficFileByNo(trfNo);
//        if (file == null) {
//            debug("isTrfFileHasFines: file not found...");
//            return false;
//        }
        
        // If service is change Owner ship them exclude booklet fines
        boolean exceludeBookletFines = (vhlSvcCode == 202 || vhlSvcCode == 203);
                
        /* *********************************************************************
         * ************************** Person ***********************************
         * *********************************************************************
         */
//        if (file.isPersonFile()) {
//            debug("isTrfFileHasFines: Person traffic file...");
//            // VIP Person
//            if (VehicleServletDAO.isVipPerson(file.getPersonId())) {
//                // (1,3) = (BOOKLET_STATUS_WITH_OWNER, BOOKLET_STATUS_LOST)
//                debug("isTrfFileHasFines: VIP person");
//                
//                /** New Implementation (Rami) */
//                Map beneficiariesCount = fineHandler.getBookletTicketsBeneficiariesCounts(new Long(bktId));
//                FineVO fineVOForBookletTicketsNotPayable = fineHandler.getBookletTicketsForNotPayable(new Long(bktId));
//                
//                if(beneficiariesCount == null || beneficiariesCount.size() == 0) {
//                    return false;
//                }
//                
//                boolean haveElectronicSignature =
//                            checkElectronicSignaturesAndExceptionRequest(beneficiariesCount,
//                                                                         new Long(file.getId()),
//                                                                         new Integer(vhlSvcCode),
//                                                                         "BR_TRS012_MER",
//                                                                         emiCode,
//                                                                         fineVOForBookletTicketsNotPayable,
//                                                                         plateId);
//                
//                if(!haveElectronicSignature && !exceludeBookletFines) {
//                    info.append("«·„—ﬂ»…");
//                    return true;
//                    
//                } else {
//                    return false;
//                }
//
//            // Not VIP Person
//            } else {
//                debug("isTrfFileHasFines: Not VIP person");
//                boolean checkAllCircularTickets = true;
//                
//                if(bktId > 0) {
//                    checkAllCircularTickets = false;
//                }
//                
//                Map trafficFileFinesBenificiariesCount = fineHandler.getTrafficFilesUnPayableFines(new Long(file.getId()),checkAllCircularTickets);
//                FineVO fineVOForTrafficFilesTicketsUnPayableFines = fineHandler.getTrafficFilesTicketsForUnPayableFines(new Long(file.getId()),checkAllCircularTickets);
//                
////                if (BrHandler.isTrfIdHasTickets(file.getId(),checkAllCircularTickets)) {
//                    if(!checkElectronicSignaturesAndExceptionRequest(trafficFileFinesBenificiariesCount,
//                                                              new Long(file.getId()),
//                                                              new Integer(vhlSvcCode),
//                                                              "BR_TRS012_MER",emiCode,
//                                                              fineVOForTrafficFilesTicketsUnPayableFines,
//                                                              plateId)) {
//                                                  
//                    info.append(" „—ﬂ»«  „„·Êﬂ… ·Â–« «·„·› «·„—Ê—Ì √Ê ⁄·Ï —Œ’…");
//                    return true;
//                    
//                } else {
//                    /** New Implementation (Rami) */
//                    Map bookletTicketsBenificiaries = circularTicketHandler.getBookletCircularTicketsBenificiariesCounts(new Long(bktId));
//                    Map trafficFileTicketsBenificiaries = circularTicketHandler.getTrafficFileCircularTicketsBenificiariesCounts(new Long(file.getId()));
//                    Map drivingLicenseTicketsBenificiaries = circularTicketHandler.getDrivingLicenseCircularTicketsBenificiariesCounts(new Long(file.getId()));
//                    
//                    FineVO fineVOForBookletCircularTicketsNotPayable = circularTicketHandler.getBookletCircularTicketsForNotPayable(new Long(bktId));
//                    FineVO fineVOForTrafficFileCircularTicketsNotPayable = circularTicketHandler.getTrafficFileCircularTicketsForNotPayable(new Long(file.getId()));
//                    FineVO fineVOForDrivingLicenseCircularTicketsNotPayable = circularTicketHandler.getDrivingLicenseCircularTicketsForNotPayable(new Long(file.getId()));
//                    
////                    Map ticketsCounts = 
////                    circularTicketHandler.getBookletNonLocalAuthoritiesTicketsCounts(new Long(bktId) , new Long(file.getId())); 
//                    if(!exceludeBookletFines 
//                            && !checkElectronicSignaturesAndExceptionRequest(bookletTicketsBenificiaries,
//                                                                             new Long(file.getId()),
//                                                                             new Integer(vhlSvcCode),
//                                                                             "BR_TRS012_MER",emiCode,
//                                                                             fineVOForBookletCircularTicketsNotPayable,
//                                                                             plateId)) {
//                        info.append("«·„—ﬂ»…");
//                        return true;
//                    } else if(!checkElectronicSignaturesAndExceptionRequest(trafficFileTicketsBenificiaries,
//                                                                            new Long(file.getId()),
//                                                                            new Integer(vhlSvcCode),
//                                                                            "BR_TRS012_MER",emiCode,
//                                                                            fineVOForTrafficFileCircularTicketsNotPayable,
//                                                                            plateId)) {
//
//                        info.append("„—ﬂ»«  „”Ã·… ⁄·Ï „·› «·„—Ê—");
//                        return true;                        
//                    } else if(!checkElectronicSignaturesAndExceptionRequest(drivingLicenseTicketsBenificiaries,
//                                                                            new Long(file.getId()),
//                                                                            new Integer(vhlSvcCode),
//                                                                            "BR_TRS012_MER",emiCode,
//                                                                            fineVOForDrivingLicenseCircularTicketsNotPayable,
//                                                                            plateId)) {
//                        info.append("«·—Œ’…");
//                        return true;                        
//                    } else  {
//                        return false;
//                    }
//                }
//            }

        /* *********************************************************************
         * ************************* Organization ******************************
         * *********************************************************************
         */
//        } else if (file.isOrganizationFile()) {
//            debug("isTrfFileHasFines: Organization traffic file...");
//            int payFine = orgHandler.getPayFinesType(new Long(file.getOrganizationId()));
//            switch (payFine) {
//                // PAY_FINES_ALL_VHL
//                case 1:
//                     debug("isTrfFileHasFines: PAY_FINES_ALL_VHL");
//                    boolean checkAllCircularTickets = true;
//                    
//                    if(bktId > 0) {
//                        checkAllCircularTickets = false;
//                    }
//                    
//                    Map trafficFileFinesBenificiariesCount = fineHandler.getTrafficFilesUnPayableFines(new Long(file.getId()),checkAllCircularTickets);
//                    FineVO fineVOForTrafficFilesTicketsUnPayableFines = fineHandler.getTrafficFilesTicketsForUnPayableFines(new Long(file.getId()),checkAllCircularTickets);
//                    
////                  if (BrHandler.isTrfIdHasTickets(file.getId(),checkAllCircularTickets)) {
//                    if(!checkElectronicSignaturesAndExceptionRequest(trafficFileFinesBenificiariesCount,
//                                                                     new Long(file.getId()),
//                                                                     new Integer(vhlSvcCode),
//                                                                     "BR_TRS012_MER",emiCode,
//                                                                     fineVOForTrafficFilesTicketsUnPayableFines,
//                                                                     plateId)) {
//                        info.append("„—ﬂ»«  „„·Êﬂ… ·„·› «·„—Ê—");
//                        return true;
//                    } else {
//                    
////                        Map ticketsCounts = 
////                        circularTicketHandler.getBookletNonLocalAuthoritiesTicketsCounts(new Long(bktId) , null); 
//
//                        Map bookletTicketsBenificiaries = circularTicketHandler.getBookletCircularTicketsBenificiariesCounts(new Long(bktId));
//                        Map trafficFileTicketsBenificiaries = circularTicketHandler.getTrafficFileCircularTicketsBenificiariesCounts(new Long(file.getId()));
//                        
//                        FineVO fineVOForBookletCircularTicketsNotPayable  = circularTicketHandler.getBookletCircularTicketsForNotPayable(new Long(bktId));
//                        FineVO fineVOForTrafficFileCircularTicketsNotPayable = circularTicketHandler.getTrafficFileCircularTicketsForNotPayable(new Long(file.getId()));
//                        
//                        if(!exceludeBookletFines
//                                && !checkElectronicSignaturesAndExceptionRequest(bookletTicketsBenificiaries,
//                                                                                 new Long(file.getId()),
//                                                                                 new Integer(vhlSvcCode),
//                                                                                 "BR_TRS012_MER",emiCode,
//                                                                                 fineVOForBookletCircularTicketsNotPayable,
//                                                                                 plateId)) {
//                            info.append("«·„—ﬂ»…");
//                            return true;
//                         } else if(!checkElectronicSignaturesAndExceptionRequest(trafficFileTicketsBenificiaries,
//                                                                                 new Long(file.getId()),
//                                                                                 new Integer(vhlSvcCode),
//                                                                                 "BR_TRS012_MER",emiCode,
//                                                                                 fineVOForTrafficFileCircularTicketsNotPayable,
//                                                                                 plateId)) {
//                            info.append("„—ﬂ»«  „”Ã·… ⁄·Ï „·› «·„—Ê—");
//                            return true;
//                        } else  {
//                            return false;
//                        }
//                    }
//                // PAY_FINES_ONE_VHL
//                case 2:
//                     debug("isTrfFileHasFines: PAY_FINES_ONE_VHL");
//                    
//                    //if (VehicleServletDAO.isBookletHasTickets(bktId)) {
//                        /** New Implementation (Rami) */
//                     Map booletsTicketsBeniificiaryCount = fineHandler.getBookletUnpayableTicketsBeneficiariesCounts(new Long(bktId));
//                     FineVO fineVOForBookletUnpayableTickets = fineHandler.getBookletUnpayableTickets(new Long(bktId));
//                     
//                     if (!exceludeBookletFines &&
//                         !checkElectronicSignaturesAndExceptionRequest(booletsTicketsBeniificiaryCount,
//                                                  new Long(file.getId()),
//                                                  new Integer(vhlSvcCode),
//                                                  "BR_TRS012_MER",emiCode,
//                                                  fineVOForBookletUnpayableTickets,
//                                                  plateId)) {
//                         info.append("«·„—ﬂ»…");
//                         return true;
//                     } else {
//                         return false;
//                     }
//                // PAY_FINES_EXP_VHL
//                case 3:
//                     debug("isTrfFileHasFines: PAY_FINES_EXP_VHL");
//                    if (VehicleServletDAO.isTrfIdHasActiveTickets(file.getId())) {
//                         info.append("„—ﬂ»«  „‰ ÂÌ… „„·Êﬂ… ·„·› «·„—Ê—");
//                         return true;
//                    } else {
//                        /** New Implementation (Rami) */
//                        Map bookletTicketsBenificiaries = circularTicketHandler.getBookletCircularTicketsBenificiariesCounts(new Long(bktId));
//                        Map trafficFileTicketsBenificiaries = circularTicketHandler.getTrafficFileCircularTicketsBenificiariesCounts(new Long(file.getId()));
//                        
//                        FineVO fineVOForBookletCircularTicketsNotPayable = circularTicketHandler.getBookletCircularTicketsForNotPayable(new Long(bktId));
//                        FineVO fineVOForTrafficFileCircularTicketsNotPayable = circularTicketHandler.getTrafficFileCircularTicketsForNotPayable(new Long(file.getId()));
//                        
////                        Map ticketsCounts = 
////                        circularTicketHandler.getBookletNonLocalAuthoritiesTicketsCounts(new Long(bktId) , null);
//                        if(!exceludeBookletFines
//                                && !checkElectronicSignaturesAndExceptionRequest(bookletTicketsBenificiaries,
//                                                                                 new Long(file.getId()),
//                                                                                 new Integer(vhlSvcCode),
//                                                                                 "BR_TRS012_MER",emiCode,
//                                                                                 fineVOForBookletCircularTicketsNotPayable,
//                                                                                 plateId)) {
//                            info.append("«·„—ﬂ»…");
//                            return true;
//                        } else if(!checkElectronicSignaturesAndExceptionRequest(
//                                                    trafficFileTicketsBenificiaries,
//                                                    new Long(file.getId()),
//                                                    new Integer(vhlSvcCode),
//                                                    "BR_TRS012_MER",emiCode,
//                                                    fineVOForTrafficFileCircularTicketsNotPayable,
//                                                    plateId)) {
//
//                            info.append("„—ﬂ»«  „”Ã·… ⁄·Ï „·› «·„—Ê—");
//                            return true;                        
//                        } else  {
//                            return false;
//                        }
//                    }
//            }
//        }
        return false;
    }
//
//    /**
//     * Check Electronic Signatures
//     * 
//     * @param beneficiariesCount : Beneficiaries Count Map
//     * @param trafficId : Traffic Id
//     * @param serviceCode : Service Code
//     * @param ruleCode : Rule Code
//     * @param emiCode  : Emirate Code
//     * @param fineVO : fine VO.
//     * @return true if Violation is skipped
//     */
//    public static boolean checkElectronicSignaturesAndExceptionRequest(Map beneficiariesCount,
//                                                                        Long trafficId,
//                                                                        Integer serviceCode,
//                                                                        String ruleCode,
//                                                                        String emiCode, 
//                                                                        FineVO fineVO,
//                                                                        Long plateId) {
//        
//        if(beneficiariesCount == null || beneficiariesCount.size() == 0) {
//            return true;
//        }
//        
//        Set beneficiaries = beneficiariesCount.keySet();
//        Iterator beneficiariesIterator = beneficiaries.iterator();
//        List beneficiaryIds = new ArrayList();
//        while(beneficiariesIterator.hasNext()) {
//            beneficiaryIds.add((Long)beneficiariesIterator.next());    
//        }
//        
//        boolean haveValidExceptionRequest = exceptionsHandler.areBenificiariesHaveValidExceptionRequest(
//                                                                                      trafficId,
//                                                                                      beneficiaryIds,
//                                                                                      serviceCode,
//                                                                                      ruleCode,
//                                                                                      plateId); 
//                                                                                      
////        boolean haveElectronicSignature = elsBenificiariesHandler.areBenificiariesHaveElectronicSignature (trafficId,
////                                                                 beneficiaryIds,serviceCode,ruleCode,emiCode);  
////        //.......................Exception Request For Tickets .................                                                                                                           
////        List allExceptedTickets = exceptionsHandler.getExceptedTickets(trafficId,serviceCode,plateId);
////        boolean isExcepted = ExceptionsHandler.isExceptedTickets(fineVO,allExceptedTickets);                                                         
//                                                                
//        return (haveValidExceptionRequest) ;//|| haveElectronicSignature || isExcepted);
//    }
//    
//    /**
//     * Check if the vehicle is wanted for CID.
//     * 
//     * @param vleId Vehicle ID
//     * @return true if the vehicle is wanted for CID.
//     */
//    public static boolean isVehicleWantedForCid(long vleId) {
//        // Get plate info
//        StringBuffer query = new StringBuffer();
//        query.append(" SELECT ")
//             .append(  " PLATES.PLATE_NO, ")
//             .append(  " PLATECODES.PLC_CODE, ")
//             .append(  " PLATECODES.CODE ")
//             .append(" FROM ")
//             .append(  " TF_VHL_PLATES PLATES, ")
//             .append(  " TF_VHL_PLATE_CODES PLATECODES ")
//             .append(" WHERE ")
//             .append(      " PLATES.PCD_ID = PLATECODES.ID ")
//             .append(  " AND PLATES.ID = (SELECT PLT_ID ")
//             .append(                   " FROM TF_VHL_BOOKLETS ")
//             .append(                   " WHERE TRS_END_DATE IS NULL ")
//             .append(                     " AND EXPIRY_DATE IS NOT NULL ")
//             .append(                     " AND VLE_ID = ? ") // vleId
//             .append(                   ")");
//
//        String plateNo = "";
//        String plcCode = "";
//        String code    = "";
//
//        debug("isVehicleWantedForCid: vleId=" + vleId);
//        debug("isVehicleWantedForCid: " + query);
//        Connection con = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            con = JdbcFactory.getConnection();
//            stm = con.prepareStatement(query.toString());
//            stm.setLong(1, vleId);
//            rs = stm.executeQuery();
//
//            if (! rs.next()) {
//                return false;
//            }
//
//            // Get plate info fields
//            plateNo = toString(rs.getString("PLATE_NO"), "-1");
//            plcCode = toString(rs.getString("PLC_CODE"), "-1");
//            code    = toString(rs.getString("CODE"), "-1");
//
//            // Close old statement
//            JdbcAdapter.close(rs);
//            JdbcAdapter.close(stm);
//            query.delete(0, query.length());
//
//            query.append(" SELECT ")
//                 .append(  " COUNT(1) ")
//                 .append(" FROM ")
//                 .append(  " TF_STP_WANTED_VEHICLES_CID ")
//                 .append(" WHERE ")
//                 .append(      " PLATE_NO = ? ")       // plateNo
//                 .append(  " AND PLATE_CODE = ? ")     // code
//                 .append( " AND PLATE_CATEGORY = ? "); // plcCode
//
//            debug("isVehicleWantedForCid: plateNo=" + plateNo);
//            debug("isVehicleWantedForCid: code=" + code);
//            debug("isVehicleWantedForCid: plcCode=" + plcCode);
//            debug("isVehicleWantedForCid: " + query);
//            stm = con.prepareStatement(query.toString());
//            stm.setString(1, plateNo);
//            stm.setString(2, code);
//            stm.setString(3, plcCode);
//            rs = stm.executeQuery();
//
//            if (! rs.next()) {
//                return false;
//            }
//
//            return rs.getInt(1) > 0;
//
//        } catch (SQLException sqle) {
//            throw new TrafficException(sqle);
//        } finally {
//            JdbcAdapter.close(rs);
//            JdbcAdapter.close(stm);
//            JdbcAdapter.close(con);
//        }
//    }
    /**
     * Used to check if the vehicle was circulated or not. This method is used
     * by many business rules such as (BR_CLC001_MER,BR_LPO007_MER,...). 
     * If bktId or vleId was less or equals zero, its value will be execluded 
     * from the query. If chasissNo was null or empty string its value will be 
     * execluded from the query.
     * 
     * @param  vleId Vehicle ID.
     * @param  bktId Booklet ID.
     * @param  pltId Plate ID.
     * @param  chasissNo Vehicle chasiss number.
     * @param  info Used to return the circular not info to the caller in the
     *         following format: "reason,refNo,date,source"
     * @return true if the vehicle has circulated note.
     */
    public static boolean isVehicleCirculated(long vleId, 
                          long bktId, long pltId, String chasissNo,
                          StringBuffer info, long trfId, int svcCode) {
        
        return isVehicleCirculated(vleId, bktId, pltId, chasissNo, info, trfId, svcCode,false);
    }
    
    /**
     * Used to check if the vehicle was circulated or not. This method is used
     * by many business rules such as (BR_CLC001_MER,BR_LPO007_MER,...). 
     * If bktId or vleId was less or equals zero, its value will be execluded 
     * from the query. If chasissNo was null or empty string its value will be 
     * execluded from the query.
     * 
     * @param  vleId Vehicle ID.
     * @param  bktId Booklet ID.
     * @param  pltId Plate ID.
     * @param  chasissNo Vehicle chasiss number.
     * @param  info Used to return the circular not info to the caller in the
     *         following format: "reason,refNo,date,source"
     * @param  checkisPositive : Check is Positive flag.
     * @return true if the vehicle has circulated note.
     */
    public static boolean isVehicleCirculated(long vleId, 
                          long bktId, long pltId, String chasissNo,
                          StringBuffer info, long trfId, int svcCode, boolean checkIsPositive) {
        try {
            // Check circulation notes.
//            CircularNote note = VehicleServletDAO.getCircularNote(
//                                vleId, bktId, pltId, chasissNo, trfId, svcCode,checkIsPositive);
//
//            // If theres is a c cricular note return its info
//            if (note != null) {
//                info.append(note.getReason())
//                    .append(",")
//                    .append("(").append(note.getRefNo()).append(")")
//                    .append(",")
//                    .append(DateTimeFormatter.formatDate(note.getDate()))
//                    .append(",")
//                    .append(note.getSource());
//                return true;
//            }

            // Check if vehicle was wanted for CID
//            if (isVehicleWantedForCid(vleId)) {
//                info.append(" , , ,") // Reason,RefNo,Date
//                    .append("«· Õ—Ì« ");
//                return true;
//            }

            // Vehicle is not circulated.
            return false;

        } catch (Exception ex) {
            throw new TrafficException(ex);
        }
    }

    /**


    /**
     * Check if BR_REG026_MER is valid or not.
     * 
     * @param  bktId Booklet ID.
     * @param  vleId VLE_ID.
     * @param  info Violation info.
     * @return true if the Business rule was valid, false otherwise.
     */
    public static boolean isBrReg026MerInvalid(long bktId, long vleId, long pltId, 
                          String chasissNo, StringBuffer info, long trfId, int svcCode) {
        return isVehicleCirculated(vleId, bktId, pltId, chasissNo, info, trfId, svcCode);
    }

    /**
     * Check if BR_TRS013_MER is valid or not.
     * 
     * @param totalCircNotes Total no of circulated notes.
     * @return true if the Business rule was valid, false otherwise.
     */
    public static boolean isBrTrs013MerInvalid(long trfId, 
                                               long prsId, 
                                               long orgId, 
                                               int svcCodeDtt,
                                               int svcCodeFfu,
                                               int svcCodeVhl,
                                               int svcCodeDrl,
                                               long vclClass) {
        if (trfId == 19999999) {
            debug("isBrTrs013MerInvalid: SKP, trfId=19999999");
            return false;
        }

         //BR to be skipped if the service is register vehicle and vehicle class is entertainment motorcycle
        if(skipRegisterEntertainmentMotorcycle(svcCodeVhl,vclClass)) {
            return false;
         }
         
        //......................................................................
        
        //######################################################################
        //order to skip the circular note by depend on the following condition :
        //i.	IS_POSITIVE = ì·’«·Õ «·„⁄„„î
        //ii.property ìae.rta.stp.skipBrTrs013MerForPositive = true
//        if (trafficConfig != null && trafficConfig.skipBrTrs013MerForPositive()) {
//            if (circularNoteHandler.skipBrTrs013MerForPositive(new Long(trfId))) {
//                return false;        
//            }
//        }
        //######################################################################

        // Check svcCodeFfu 
        if (svcCodeFfu > 0) {
            debug("isBrTrs013MerInvalid-SKIP: svcCodeFfu=" + svcCodeFfu);
            return false;
        }

        if (svcCodeDtt > 0) {
            debug("isBrTrs013MerInvalid-SKIP: svcCodeDtt=" + svcCodeDtt);
            return false;
        }

        if (prsId < 0 && orgId < 0) {
            debug("isBrTrs013MerInvalid-SKIP: null prsId and orgId...");
            return false;
        }
//
//        // Skip for VIP
//        if (prsId > 0 && VehicleServletDAO.isVipPerson(prsId)) {
//            debug("isBrTrs013MerInvalid-SKIP: VIP person");
//            return false;
//        }

        // Check org
//        if (orgId > 0 && orgHandler.getPayFinesType(new Long(orgId)) == 2) {
//            debug("isBrTrs013MerInvalid-SKIP: ORG payFines...");
//            return false;
//        }

        //return VhlBrHandler.isCirculated(trfId, svcCodeVhl, svcCodeDrl);
        return true;
    }

    /**
     * Check if BR_TRS013_MER is valid or not.
     * 
     * @param totalCircNotes Total no of circulated notes.
     * @return true if the Business rule was valid, false otherwise.
     */
    public static boolean isBrTrs013MerInvalid(long trfId, 
                                               long prsId, 
                                               long orgId, 
                                               int svcCodeDtt,
                                               int svcCodeFfu,
                                               int svcCodeVhl,
                                               int svcCodeDrl,
                                               int svcCodeAct) {
        if (trfId == 19999999) {
            debug("isBrTrs013MerInvalid: SKP, trfId=19999999");
            return false;
        }

        // Check svcCodeFfu
        if (svcCodeFfu > 0) {
            debug("isBrTrs013MerInvalid-SKIP: svcCodeFfu=" + svcCodeFfu);
            return false;
        }

        if (svcCodeDtt > 0) {
            debug("isBrTrs013MerInvalid-SKIP: svcCodeDtt=" + svcCodeDtt);
            return false;
        }

        if (prsId < 0 && orgId < 0) {
            debug("isBrTrs013MerInvalid-SKIP: null prsId and orgId...");
            return false;
        }

//        // Skip for VIP
//        if (prsId > 0 && VehicleServletDAO.isVipPerson(prsId)) {
//            debug("isBrTrs013MerInvalid-SKIP: VIP person");
//            return false;
//        }
//
//        // Check org
//        if (orgId > 0 && orgHandler.getPayFinesType(new Long(orgId)) == 2) {
//            debug("isBrTrs013MerInvalid-SKIP: ORG payFines...");
//            return false;
//        }
        
        //######################################################################
        //order to skip the circular note by depend on the following condition :
        //i.	IS_POSITIVE = ì·’«·Õ «·„⁄„„î
        //ii.property ìae.rta.stp.skipBrTrs013MerForPositive = true
//        if (trafficConfig != null && trafficConfig.skipBrTrs013MerForPositive()) {
//            if (circularNoteHandler.skipBrTrs013MerForPositive(new Long(trfId))) {
//                return false;        
//            }
//        }
        //######################################################################
        
        
        //return VhlBrHandler.isCirculated(trfId, svcCodeVhl, svcCodeDrl,svcCodeAct);
        return true;
    }
    
	/**
	 * This method used by isBrTrs014MerInvalid to retrieve expired booklets.
     *
	 * @param prsId TRS.PRS_ID value.
	 * @return Set of TF_VHL_BOOKLETS.ID of all expierd booklets. The objects 
	 *         type will be String.
//	 */
//	private static Set getPersonExpiredBooklets(long prsId, String userEmiCode) {
//		// Traffic file ID query
//        StringBuffer trfIdQuery = new StringBuffer();
//        trfIdQuery.append(" SELECT ID ")
//                  .append(" FROM TF_STP_TRAFFIC_FILES ")
//                  .append(" WHERE PRS_ID = ? ") // prsId
//                  .append(  " AND EMI_CODE = ? "); // userEmiCode
//
//		// expired booklets IDs
//		StringBuffer query = new StringBuffer();
//		query.append(" SELECT ID ")
//			 .append(" FROM TF_VHL_BOOKLETS BKT ")
//			 .append(" WHERE EXPIRY_DATE IS NOT NULL ")
//			 .append(  " AND TRS_END_DATE IS NULL")
//			 .append(  " AND TRF_ID = (").append(trfIdQuery).append(")")
//			 .append(  " AND EXPIRY_DATE <= ADD_MONTHS(SYSDATE, -4) ")
//             .append(  " AND NOT EXISTS(SELECT 1 ")
//             .append(                 " FROM TF_STP_CIRCULAR_NOTES CRN ")
//             .append(                 " WHERE CRN.BKT_ID = BKT.ID ")
//             .append(                 "   AND IS_POSITIVE = 2")
//             .append(                 ")" )
//             .append(  " AND NOT EXISTS(SELECT 1 ")
//             .append(                 " FROM TF_FFU_VEHICLE_CONFISCATIONS VCN ")
//             .append(                 " WHERE VCN.BKT_ID = BKT.ID ")
//             .append(                 " AND BOOKING_STATUS = 3")
//             .append(                 ")" );
//
//        debug("getPersonExpiredBooklets: prsId=" + prsId);
//        debug("getPersonExpiredBooklets: userEmiCode=" + userEmiCode);
//        debug("getPersonExpiredBooklets: " + query);
//        Connection con = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            con = JdbcFactory.getConnection();
//            stm = con.prepareStatement(query.toString());
//            stm.setLong(1, prsId);
//            stm.setString(2, userEmiCode);
//            rs = stm.executeQuery();
//
//            Set bookletsSet = new HashSet();
//            while (rs.next()) {
//                bookletsSet.add(rs.getString("ID"));
//            }
//
//            return bookletsSet;
//
//        } catch (SQLException sqle) {
//            throw new TrafficException(sqle);
//        } finally {
//            JdbcAdapter.close(rs);
//            JdbcAdapter.close(stm);
//            JdbcAdapter.close(con);
//        }
//	}

	/**
	 * This method used by isBrTrs014MerInvalid to retrieve expired booklets.
	 * 
	 * @param orgId TRS.ORG_ID value.
	 * @return Set of TF_VHL_BOOKLETS.ID of all expierd booklets. The objects 
	 *         type will be String.
	 */
	private static Set getOrgExpiredBooklets(long orgId, String userEmiCode) {
		// Traffic file ID query
        StringBuffer trfIdQuery = new StringBuffer();
        trfIdQuery.append(" SELECT ID ")
                  .append(" FROM TF_STP_TRAFFIC_FILES ")
                  .append(" WHERE ORG_ID = ? ") // orgId
                  .append(  " AND EMI_CODE = ? "); // userEmiCode

		// expired booklets IDs
		StringBuffer query = new StringBuffer();
		query.append(" SELECT ")
			 .append(  " ID ")
			 .append(" FROM ")
			 .append(  " TF_VHL_BOOKLETS BKT ")
			 .append(" WHERE ")
			 .append(      " EXPIRY_DATE IS NOT NULL ")
			 .append(  " AND TRS_END_DATE IS NULL")
			 .append(  " AND TRF_ID = (").append(trfIdQuery).append(")")
			 .append(  " AND EXPIRY_DATE <= ADD_MONTHS(SYSDATE, (SELECT -1 * ALLOWED_EXPIRY_MONTHS FROM TF_VHL_PREFERENCES)) ")
             .append(  " AND NOT EXISTS(SELECT 1 ")
             .append(                 " FROM TF_STP_CIRCULAR_NOTES CRN, ")
             .append(                 "      TF_VHL_BOOKLETS BKT1 ")
             .append(                 " WHERE CRN.BKT_ID = BKT1.ID ")
             .append(                 "   AND BKT1.VLE_ID = BKT.VLE_ID ")
             .append(                 "   AND CRN.STATUS = 2 ")
             .append(                 "   AND IS_POSITIVE = 2 ) ")
             .append(  " AND NOT EXISTS(SELECT 1 ")
             .append(                 " FROM TF_FFU_VEHICLE_CONFISCATIONS VCN ")
             .append(                 " WHERE VCN.BKT_ID = BKT.ID ")
             .append(                 " AND BOOKING_STATUS = 3")
             .append(                 ")");

        debug("getOrgExpiredBooklets: orgId=" + orgId);
        debug("getOrgExpiredBooklets: userEmiCode=" + userEmiCode);
        debug("getOrgExpiredBooklets: " + query); 
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = JdbcFactory.getConnection();
            stm = con.prepareStatement(query.toString());
            stm.setLong(1, orgId);
            stm.setString(2, userEmiCode);
            rs = stm.executeQuery();

			Set bookletsSet = new HashSet();
			while (rs.next())  {
				bookletsSet.add(rs.getString("ID"));
			}

			return bookletsSet;

        } catch (SQLException sqle) { 
            throw new TrafficException(sqle);
        } finally {
            JdbcAdapter.close(rs);
            JdbcAdapter.close(stm);
            JdbcAdapter.close(con);
        }
	}

    /**
     * Check if BR_TRS014_MER is valid or not.
     * 
     * @param trfNo Traffic file number.
     * @return true if the Business rule was valid, false otherwise.
     */
    public static boolean isBrTrs014MerInvalid(long trfId, 
                                               String userEmiCode,
                                               int svcCodeVhl,
                                               int svcCodeFfu,
                                               long prsId,
                                               long orgId,
                                               long bktId,
                                               long vclClass) {
        if (trfId == 19999999) {
            debug("isBrTrs014MerInvalid: SKIP, trfId=19999999");
            return false;
        }
        
         //BR to be skipped if the service is register vehicle and vehicle class is entertainment motorcycle
        if(skipRegisterEntertainmentMotorcycle(svcCodeVhl,vclClass)) {
            return false;
         }
        //......................................................................


        
		// Validate service codes
        boolean invalidVhlCode =  svcCodeVhl > 0    // Not null 
                               && svcCodeVhl != 201 
                               && svcCodeVhl != 202 
                               && svcCodeVhl != 203 
                               && svcCodeVhl != 208 
                               && svcCodeVhl != 209 
                               && svcCodeVhl != 211;
		if (invalidVhlCode || svcCodeFfu > 0) {
            StringBuffer msg = new StringBuffer("isBrTrs014MerInvalid-SKIP: ");
            msg.append("svcCodeVhl=")
               .append(svcCodeVhl)
               .append(", svcCodeFfu=")
               .append(svcCodeFfu);
            debug(msg.toString());
            return false; // SKIP
        }

        // Validate person/organization ID
        if (prsId <= 0 && orgId <= 0) {
            debug("isBrTrs014MerInvalid-WARNING: prsId and orgId are null...");
            return false; // SKIP
        }
        
        
		/* *********************************************************************
		 * *********** Get expired booklets IDs using person info **************
		 * *********************************************************************
		 */
		Set bookletsIds = null; // Contains String objects
		if (prsId > 0) {
//            boolean isVip = VehicleServletDAO.isVipPerson(prsId);
//			// Skip validation for VIP persons
//			if (isVip) {
//                debug("isBrTrs014MerInvalid: SKIP for VIP persons...");
//				return false; // SKIP
//			}
//
//			bookletsIds = getPersonExpiredBooklets(prsId, userEmiCode);

		/* *********************************************************************
		 * ********* Get expired booklets IDs using organization info **********
		 * *********************************************************************
		 */
		} else if (orgId > 0) {
//            int payFines = orgHandler.getPayFinesType(new Long(orgId));
//			if (payFines == 2) {
//                debug("isBrTrs014MerInvalid: SKIP for org payFines=2 ...");
//				return false; // SKIP
//			}
//
//			bookletsIds = getOrgExpiredBooklets(orgId, userEmiCode);
		}

		// If no expired booklets were found skip validation
		if (bookletsIds.isEmpty()) {
            debug("isBrTrs014MerInvalid: SKIP no expired booklets were found...");
			return false; // SKIP
		}

        boolean checkVhlCode = svcCodeVhl != 202 
                            && svcCodeVhl != 203 
                            && svcCodeVhl != 208 
                            && svcCodeVhl != 209 
                            && svcCodeVhl != 211;
        if (svcCodeVhl <= 0 || checkVhlCode) {
            // Rais error
			if (bookletsIds.isEmpty()== false) {
                debug("isBrTrs014MerInvalid: expired VHL booklets were found...");
				return true; // ERROR
			}
        }

        // Check service 202, 203
        if (svcCodeVhl == 202 || svcCodeVhl == 203) {
             if (bookletsIds.contains(Long.toString(bktId))) {
                 debug("isBrTrs014MerInvalid: SKIP expired booklets for 202/203");
                 return false; // SKIP
             } else {
                 debug("isBrTrs014MerInvalid: ERROR expired booklets for 202/203");
                 return true; // ERROR
             }
        }

        StringBuffer query = new StringBuffer();
        query.append(" SELECT 1 ")
             .append(" FROM TF_VHL_BOOKLETS ")
             .append(" WHERE ID = ? ") // bktId
             .append(  " AND EXPIRY_DATE <= SYSDATE ");

        debug("isBrTrs014MerInvalid: bktId=" + bktId);
        debug("isBrTrs014MerInvalid: " + query);
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = JdbcFactory.getConnection();
            stm = con.prepareStatement(query.toString());
            stm.setLong(1, bktId);
            rs = stm.executeQuery();

            if (rs.next()) {
                debug("isBrTrs014MerInvalid: ERROR final test...");
                return true; // ERROR
            }

        } catch (SQLException sqle) {
            throw new TrafficException(sqle);
        } finally {
            JdbcAdapter.close(rs);
            JdbcAdapter.close(stm);
            JdbcAdapter.close(con);
        }

        debug("isBrTrs014MerInvalid: SKIP, All conditions was valid..");
        return false;
    }
     
    /**
     * Check if BR_TRS014_MER is valid or not.
     * 
     * @param trfNo Traffic file number.
     * @return true if the Business rule was valid, false otherwise.
     */
    public static boolean isBrTrs014MerInvalid(long trfId, 
                                               String userEmiCode,
                                               int svcCodeVhl,
                                               int svcCodeFfu,
                                               long prsId,
                                               long orgId,
                                               long bktId
                                               ) {  
        if (trfId == 19999999) {
            debug("isBrTrs014MerInvalid: SKIP, trfId=19999999");
            return false;
        }
        
       
        
		// Validate service codes
        boolean invalidVhlCode =  svcCodeVhl > 0    // Not null 
                               && svcCodeVhl != 201 
                               && svcCodeVhl != 202 
                               && svcCodeVhl != 203 
                               && svcCodeVhl != 208 
                               && svcCodeVhl != 209 
                               && svcCodeVhl != 211;
		if (invalidVhlCode || svcCodeFfu > 0) {
            StringBuffer msg = new StringBuffer("isBrTrs014MerInvalid-SKIP: ");
            msg.append("svcCodeVhl=")
               .append(svcCodeVhl)
               .append(", svcCodeFfu=")
               .append(svcCodeFfu);
            debug(msg.toString());
            return false; // SKIP
        }

        // Validate person/organization ID
        if (prsId <= 0 && orgId <= 0) {
            debug("isBrTrs014MerInvalid-WARNING: prsId and orgId are null...");
            return false; // SKIP
        }
        
        
		/* *********************************************************************
		 * *********** Get expired booklets IDs using person info **************
		 * *********************************************************************
		 */
		Set bookletsIds = null; // Contains String objects
		if (prsId > 0) {
//            boolean isVip = VehicleServletDAO.isVipPerson(prsId);
//			// Skip validation for VIP persons
//			if (isVip) {
//                debug("isBrTrs014MerInvalid: SKIP for VIP persons...");
//				return false; // SKIP
//			}
//
//			bookletsIds = getPersonExpiredBooklets(prsId, userEmiCode);

		/* *********************************************************************
		 * ********* Get expired booklets IDs using organization info **********
		 * *********************************************************************
		 */
		} else if (orgId > 0) {
//            int payFines = orgHandler.getPayFinesType(new Long(orgId));
//			if (payFines == 2) {
//                debug("isBrTrs014MerInvalid: SKIP for org payFines=2 ...");
//				return false; // SKIP
//			}
//
//			bookletsIds = getOrgExpiredBooklets(orgId, userEmiCode);
		}

		// If no expired booklets were found skip validation
		if (bookletsIds.isEmpty()) {
            debug("isBrTrs014MerInvalid: SKIP no expired booklets were found...");
			return false; // SKIP
		}

        boolean checkVhlCode = svcCodeVhl != 202 
                            && svcCodeVhl != 203 
                            && svcCodeVhl != 208 
                            && svcCodeVhl != 209 
                            && svcCodeVhl != 211;
        if (svcCodeVhl <= 0 || checkVhlCode) {
            // Rais error
			if (bookletsIds.isEmpty()== false) {
                debug("isBrTrs014MerInvalid: expired VHL booklets were found...");
				return true; // ERROR
			}
        }

        // Check service 202, 203
        if (svcCodeVhl == 202 || svcCodeVhl == 203) {
             if (bookletsIds.contains(Long.toString(bktId))) {
                 debug("isBrTrs014MerInvalid: SKIP expired booklets for 202/203");
                 return false; // SKIP
             } else {
                 debug("isBrTrs014MerInvalid: ERROR expired booklets for 202/203");
                 return true; // ERROR
             }
        }

        StringBuffer query = new StringBuffer();
        query.append(" SELECT 1 ")
             .append(" FROM TF_VHL_BOOKLETS ")
             .append(" WHERE ID = ? ") // bktId
             .append(  " AND EXPIRY_DATE <= SYSDATE ");

        debug("isBrTrs014MerInvalid: bktId=" + bktId);
        debug("isBrTrs014MerInvalid: " + query);
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = JdbcFactory.getConnection();
            stm = con.prepareStatement(query.toString());
            stm.setLong(1, bktId);
            rs = stm.executeQuery();

            if (rs.next()) {
                debug("isBrTrs014MerInvalid: ERROR final test...");
                return true; // ERROR
            }

        } catch (SQLException sqle) {
            throw new TrafficException(sqle);
        } finally {
            JdbcAdapter.close(rs);
            JdbcAdapter.close(stm);
            JdbcAdapter.close(con);
        }

        debug("isBrTrs014MerInvalid: SKIP, All conditions was valid..");
        return false;
    } 
     
     
    /**
     * Check if BR_TRS018_MER is valid or not.
     * 
     * @param expired true if the residence was expired.
     * @return true if the Business rule was valid, false otherwise.
     */
    public static boolean isBrTrs018MerInvalid(long prsId) {
        String sql = " SELECT 1 FROM TF_STP_PERSONS"
                   + " WHERE ID = ?" // prsId
                   +   " AND RES_EXPIRY_DATE <= SYSDATE";

        debug("isBrTrs018MerInvalid: " + sql);
        return (JdbcAdapter.selectIntValue(sql, prsId, -1) == 1);
    }

//    /**
//     * Check if BR_COS002_MER is valid or not.
//     * 
//     * @param bktExpiryDate Booklet expiry date.
//     * @param vleId VLE_ID.
//     * @return true if the Business rule was valid, false otherwise.
//     */
//    public static boolean isBrCos002MerInvalid(String bktExpiryDate, 
//           long vleId, String emiCode) {
//        // Call helper method
//        return ChangeOwnershipsBrHandler.isBrCos002MerInvalid(
//               bktExpiryDate, vleId, emiCode);
//    }
//
//    /**
//     * Check if BR_COS050_MER is valid or not.
//     * 
//     * @param vleId VLE_ID.
//     * @param oldTrfId Old traffic file ID.
//     * @return true if the Business rule was valid, false otherwise.
//     */
//    public static boolean isBrCos050MerInvalid(long vleId, long oldTrfId) {
//        // Call helper method
//        return ChangeOwnershipsBrHandler.isBrCos050MerInvalid(vleId, oldTrfId);
//    }
//
//    /**
//     * Check if BR_COS006_MER is valid or not.
//     * 
//     * @param bktId Booklet Id.
//     * @return true if the Business rule was valid, false otherwise.
//     */
//    public static boolean isBrCos006MerInvalid(long bktId, StringBuffer info) {
//        // Call helper method
//        return ChangeOwnershipsBrHandler.isBrCos006MerInvalid(bktId, info);
//    }
//
//    /**
//     * Used to check if the license was circulated or not.
//     * 
//     * @param  prsId Person ID.
//     * @param  info Used to return the circular not info to the caller in the
//     *         following format: "reason,refNo,date,source"
//     * @return true if the license has circulated note.
//     */
//    public static boolean isDrivingLicenseCirculated(long prsId, StringBuffer info) {
//        // Check circulation notes.
//        CircularNote note = VehicleServletDAO.getDrivingLicenseCircularNote(prsId);
//
//        // If theres is a c cricular note return its info
//        if (note != null) {
//            info.append(note.getReason())
//                .append(",")
//                .append(note.getRefNo())
//                .append(",")
//                .append(DateTimeFormatter.formatDate(note.getDate()))
//                .append(",")
//                .append(note.getSource());
//            return true;
//        }
//
//        // Vehicle is not circulated.
//        return false;
//    }
//
//    /**
//     * Check if BR_TRS042_MER is valid or not.
//     * 
//     * @param  prsId Person Id.
//     * @param  info BR violation info.
//     * @return true if the Business rule was valid, false otherwise.
//     */
//    public static boolean isBrTrs042MerInvalid(long prsId, StringBuffer info) {
//        try {
//            return isDrivingLicenseCirculated(prsId, info);
//        } catch (Exception ex) {
//            throw new TrafficException(ex);
//        }
//    }
//
//    /**
//     * Check if BR_COS022_MER is valid or not.
//     * 
//     * @param  bktId Booklet Id.
//     * @param  vleId VLE Id.
//     * @param  pltId Plate ID.
//     * @param  info BR violation info.
//     * @return true if the Business rule was valid, false otherwise.
//     */
//    public static boolean isBrCos022MerInvalid(long bktId, long vleId, 
//                  long pltId, StringBuffer info, long trfId, int svcCode) {
//        return isVehicleCirculated(vleId, bktId, pltId, null, info, trfId, svcCode);
//    }
//
//    /**
//     * Check if BR_COS007_MER is valid or not.
//     * 
//     * @param bktId Booklet Id.
//     * @param oldTrfNo Old traffic number.
//     * @param userEmiCode :  user EMI Code
//     * @return true if the Business rule was valid, false otherwise.
//     */
//    public static boolean isBrCos007MerInvalid(long bktId, int oldTrfNo,String userEmiCode) {
//        // Call handler implementation
//        return ChangeOwnershipsBrHandler.isBrCos007MerInvalid(bktId, oldTrfNo, userEmiCode);
//    }
//
//    /**
//     * Check if BR_BKT021_MER is valid or not.
//     * 
//     * @return true if the Business rule was valid, false otherwise.
//     */
//    public static boolean isBrBkt021MerInvalid(boolean searchOrg,
//                                               String plateNo,
//                                               String plateCategory,
//                                               String plateCode,
//                                               long srcId,
//                                               long trfId,
//                                               String emiCode,
//                                               long vclClass,
//                                               int svcCode,
//                                               String chasissNo) {
//        // Call handler implementation
//        return BookletsBrHandler.isBrBkt021MerInvalid(searchOrg, plateNo, plateCategory, plateCode,
//               srcId, trfId, emiCode, vclClass, svcCode, chasissNo);
//    }

    /**
     * Check if the business rule is valid or not and append valiolation 
     * messages into violations StringBuffer object.
     * 
     * @param brName Businnes rule to be checked.
     * @param svcCode Service code.
     * @param violations Violation messages will be appended to violations
     *        StringBuffer param.
     * @param profile User profile info.
     * @param params Parameters values.
     */
    private static void checkBr(String brName, int svcCode, 
                        StringBuffer violations, Map params) { 

//        // Get params
//        UserProfile userProfile = new UserProfile((Profile)params.get("userProfile"));
//        long trfId = toLong(params.get(PARAM_TRF_ID), 0);
//        long bktId = toLong(params.get(PARAM_BKT_ID), 0);
//        long vleId = toLong(params.get(PARAM_VLE_ID), 0);
//        long srcId = toLong(params.get(PARAM_SRC_ID), 0);
//        long pltId = toLong(params.get(PARAM_PLATE_ID), 0);
//        long vdsId = toLong(params.get(PARAM_VDS_ID), 0);
//        long vclClass = toLong(params.get(PARAM_VCL_CLASS), 0);
//        long ctrId = userProfile.getCenterId() != null ? userProfile.getCenterId().longValue() :  0;
//        int trfNo = toInt(params.get(PARAM_TRF_NO), 0);
//        int oldTrfNo = toInt(params.get(PARAM_OLD_TRF_NO), 0);
//        int confisVlesCount = toInt(params.get(PARAM_WANTED_VLE_CONFIS), 0);
//        int totalCircNotes  = toInt(params.get(PARAM_TOTAL_CIRC_NOTES), 0);
//        int expiredBktCount = toInt(params.get(PARAM_EXPIRED_BOOKLETS_COUNT), 0);
//        int modelYear = toInt(params.get(PARAM_MODEL_YEAR), 0);
//        Profile profile = userProfile.getProfile();
//
//        // Get old trfId
//        long oldTrfId = -1;
//        if (oldTrfNo > 0) {
//            TrafficFile oldTrfFile = VehicleServletDAO.getTrafficFileByNo(oldTrfNo);
//            oldTrfId = (oldTrfFile == null) ? -1 : oldTrfFile.getId();
//        }

        // Get orgId, prsId info
        long prsId = 0;
        long orgId = 0;
        String query = "SELECT ORG_ID, PRS_ID FROM TF_STP_TRAFFIC_FILES WHERE ID = ?";
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = JdbcFactory.getConnection();
            stm = con.prepareStatement(query);
           // stm.setLong(1, "");
            rs = stm.executeQuery();

            if (! rs.next()) {
                throw new IllegalStateException("TRF_ID NOT FOUND, " + "");
            }

            orgId = rs.getLong("ORG_ID");
            prsId = rs.getLong("PRS_ID");

        } catch (SQLException sqle) {
            throw new TrafficException(sqle);
        } finally {         
            JdbcAdapter.close(rs);
            JdbcAdapter.close(stm);
            JdbcAdapter.close(con);
        }
         
        boolean searchOrg = (orgId > 0);

//        String bktExpiryDate = toString(params.get(PARAM_BKT_EXPIRY_DATE), "");
//        String plateNo = toString(params.get(PARAM_PLATE_NO), "");
//        String plateCat = toString(params.get(PARAM_PLATE_CATEGORY), "");
//        String plateCode = toString(params.get(PARAM_PLATE_CODE), "");
//        String chasissNo = toString(params.get(PARAM_CHASISS_NO), "");
//        String bktSrc = toString(params.get(PARAM_BKT_SOURCE), "");
//        String emirates = toString(params.get(PARAM_EMIRATES), "");
//        String userEmiCode = toString(params.get(PARAM_USER_EMI_CODE), "");
//        
        // try to add log any exception except prevalidation exception.
//        try {
//            
//            if(svcCode == SVC_CHANGE_OWNERSHIP || svcCode == SVC_CODE_VHL_CHG_OWNERSHIP_FOR_YEAR) { 
//            
//                if(searchOrg){
//                    orgHandler.excludeUnpayableOtherEmiratesFines(orgId, trfId, svcCode, userProfile, bktId,null,true);
//                }
//                
//                TrafficFileVO oldTrafficFileVO = trafficFileHandler.getTrfFileById(oldTrfId);
//                Long oldOrgId = oldTrafficFileVO.getOrgId();
//                if(oldOrgId != null){
//                    orgHandler.excludeUnpayableOtherEmiratesFines(oldOrgId, oldTrfId, svcCode, userProfile, bktId,null,true);
//                }    
//            }
//            
//            // BR_REG011_MER
//            if (brName.equals(BR_REG011_MER)) {
//                if (canBeSkipped(trfId, svcCode, "NOT_RESIDENT", userEmiCode)) {
//                    debug("<<ELS>> SKIPPING BR_REG011_MER...");
//                } else {
//                    if (isBrReg011MerInvalid(trfId, userEmiCode)) {
//                        debug(BR_REG011_MER + " Violation...");
//                        violations.append(getViolationMessage(BR_REG011_MER, trfId, svcCode));
//                        // add log Rule Exception if BrReg011Mer is fired.
//                        addLog(ctrId , trfId , profile , svcCode , null , BR_REG011_MER);
//                    }
//                }
//    
//            // BR_ETE007_MER
//            } else if (brName.equals(BR_ETE007_MER)) {
//                StringBuffer info = new StringBuffer();
//                if (isBrEte007MerInvalid(trfId, bktId, vleId, pltId, info, svcCode)) {
//                    debug(BR_ETE007_MER + " Violation...");
//                    violations.append(getViolationMessage(BR_ETE007_MER, info, trfId, svcCode));
//                    // add log Rule Exception if BrEte007Mer is fired.
//                    addLog(ctrId , trfId , profile , svcCode , null , BR_ETE007_MER);
//                }
//    
//            // BR_ETE014_MER
//            } else if (brName.equals(BR_ETE014_MER)) {
//                
//                StringBuffer info = new StringBuffer();
//                if (! VhlBrHandler.isBktIdBookletStatusValid(bktId, info)) {
//                    debug(BR_ETE014_MER + " Violation...");
//                    violations.append(getViolationMessage(BR_ETE014_MER, info, trfId, svcCode));
//                    // add log Rule Exception if BktIdBookletStatus is fired.
//                    addLog(ctrId , trfId , profile , svcCode , null , BR_ETE014_MER);
//                }
//    
//            // BR_ETC006_MER
//            } else if (brName.equals(BR_ETC006_MER)) {
//                StringBuffer info = new StringBuffer();
//                if (! VhlBrHandler.isBktIdBookletStatusValid(bktId, info)) {
//                    debug(BR_ETC006_MER + " Violation...");
//                    violations.append(getViolationMessage(BR_ETC006_MER, info, trfId, svcCode));
//                    // add log Rule Exception if BktIdBookletStatus is fired.
//                    addLog(ctrId , trfId , profile , svcCode , null , BR_ETC006_MER);
//                }
//    
//            // BR_TRS012_MER
//            } else if (brName.equals(BR_TRS012_MER)) {
//    //            if (canBeSkipped(trfId, svcCode, "BR_TRS012_MER", userEmiCode)) {
//    //                debug("<<ELS>> SKIPPING BR_TRS012_MER...");
//    //            } else {
//    //                StringBuffer info = new StringBuffer();
//    //                if (isBrTrs012MerInvalid(trfNo, svcCode, bktId, info ,userEmiCode)) {
//    //                    debug(BR_TRS012_MER + " Violation...");
//    //                    violations.append(getViolationMessage(BR_TRS012_MER, info));
//    //                }
//    //            }
//
//                boolean isTask26502Enabled = ServiceLocator.getInstance().getConfig().isTaskEnabled("TRF-26502");
//                boolean allowedToAvoidFine = isTask26502Enabled &&
//                    vehicleDescriptionHandler.isVehicleDescriptionAllowedToAvoidFine(vdsId);
//                StringBuffer info = new StringBuffer();
//                if (!allowedToAvoidFine 
//                    && isBrTrs012MerInvalid(trfNo,svcCode, bktId, info ,userEmiCode , vclClass)) {
//                        debug(BR_TRS012_MER + " Violation...");
//                        violations.append(getViolationMessage(BR_TRS012_MER, info, trfId, svcCode));
//                        // add log Rule Exception if BrTrs012Mer is fired.
//                        addLog(ctrId , trfId , profile , svcCode , null , BR_TRS012_MER);
//                } else {
//                    debug("<<ELS>> SKIPPING BR_TRS012_MER...");
//                }    
//
//            // BR_TRS050_MER
//            } else if (brName.equals(BR_TRS050_MER)) {
//    //            if (canBeSkipped(trfId, svcCode, "BR_TRS050_MER", userEmiCode)) {
//    //                debug("<<ELS>> SKIPPING BR_TRS050_MER...");
//    //            } else {
//    //                StringBuffer info = new StringBuffer();
//    //                if (isBrTrs050MerInvalid(trfNo, svcCode, bktId, info)) {
//    //                    debug(BR_TRS050_MER + " Violation...");
//    //                    violations.append(getViolationMessage(BR_TRS050_MER, info));
//    //                }
//    //            }
//    
//            // BR_TRS036_MER
//            } else if (brName.equals(BR_TRS036_MER)) {
//                StringBuffer info = new StringBuffer();
//                if (isBrTrs036MerInvalid(svcCode, -1, prsId, orgId, bktId, ctrId, userEmiCode, info,vclClass)) {
//                    debug(BR_TRS036_MER + " Violation...");
//                    violations.append(getViolationMessage(BR_TRS036_MER, info, trfId, svcCode));
//                    // add log Rule Exception if BrTrs036Mer is fired.
//                    addLog(ctrId , trfId , profile , svcCode , null , BR_TRS036_MER);
//                }
//    
//            // BR_REG026_MER
//            } else if (brName.equals(BR_REG026_MER)) {
//                StringBuffer info = new StringBuffer();
//                if (isBrReg026MerInvalid(bktId, vleId, pltId, chasissNo, info, trfId, svcCode)) {
//                    debug(BR_REG026_MER + " Violation...");
//                    violations.append(getViolationMessage(BR_REG026_MER, info, trfId, svcCode));
//                    // add log Rule Exception if BrReg026Mer is fired.
//                    addLog(ctrId , trfId , profile , svcCode , null , BR_REG026_MER);
//                }
//    
//            // BR_TRS013_MER
//            } else if (brName.equals(BR_TRS013_MER)) {
//                if (BrUtil.skipBr(trfId, svcCode, brName, userEmiCode, new Long(pltId))) {
//                    debug("<<ELS>> SKIPPING BR_TRS013_MER...");
//                } else {
//                    if (isBrTrs013MerInvalid(trfId, prsId, orgId, -1,  -1, svcCode, -1,vclClass)) {
//                        debug(BR_TRS013_MER + " Violation...");
//                        violations.append(getViolationMessage(BR_TRS013_MER, trfId, svcCode));
//                        // add log Rule Exception if BrTrs013Mer is fired.
//                        addLog(ctrId , trfId , profile , svcCode , null , BR_TRS013_MER);
//                    }
//                }
//    
//            // BR_TRS014_MER
//            } else if (brName.equals(BR_TRS014_MER)) {
//                 if(BrUtil.skipBr(trfId,svcCode,brName,userEmiCode,null)){
//                    debug("<<ELS>> SKIPPING BR_TRS014_MER...");   
//                }else {
//                    if (isBrTrs014MerInvalid(trfId, userEmiCode, svcCode, -1, prsId, orgId, bktId ,vclClass )) {
//                        debug(BR_TRS014_MER + " Violation...");
//                        violations.append(getViolationMessage(BR_TRS014_MER, trfId, svcCode));
//                        // add log Rule Exception if BrTrs014Mer is fired.
//                        addLog(ctrId , trfId , profile , svcCode , null , BR_TRS014_MER);
//                    }
//                }
//    
//            // BR_TRS018_MER
//            } else if (brName.equals(BR_TRS018_MER)) { 
//                if (canBeSkipped(trfId, svcCode, "BR_TRS018_MER", userEmiCode)) {
//                    debug("<<ELS>> SKIPPING BR_TRS018_MER...");
//                } else {
//                    if (isBrTrs018MerInvalid(prsId)) {
//                        debug(BR_TRS018_MER + " Violation...");
//                         
//                        PersonVO personVO = personHandler.getById(new Long(prsId));
//                        
//                        boolean residencyValidationFailed = false;
//                        
//                        if(personVO==null) {
//                            violations.append(getViolationMessage(BR_TRS018_MER, trfId, svcCode));
//                            
//                        } else {
//                            
//                            if(GlobalUtilities.isBlankOrNull(personVO.getResidencyNo())) {
//                                residencyValidationFailed = true;
//                                
//                            } else {
//    
//                                DnrdPersonVO vo = dnrdHandler.getPersonInfo(new Integer(2),
//                                                                    personVO.getResidencyNo(),
//                                                                    DnrdPersonVO.REQUESTED_FROM_RESIDENCY_EXPIRY_BR,
//                                                                    userProfile);
//                                
//                                
//                                if(vo==null || vo.getStatus()==null || vo.getResidencyExpiryDate()==null){
//                                    residencyValidationFailed = true;
//                                    
//                                }else if( vo.getStatus().equals(DnrdPersonVO.STATUS_SUCCESS)){
//                                    if( vo.getResidencyExpiryDate().compareTo(BrUtil.getSysDate().getValue())!=1){
//                                        residencyValidationFailed = true;  
//                                    }
//                                } else{
//                                    residencyValidationFailed = true;
//                                }
//                                
//                            }
//                            
//                            if(residencyValidationFailed) {
//                                
//                                
//                                // Eid validation will be excecuted
//                                //....accept EID in addition to residency (only one of they have to be valid) ..
//                                java.util.Date emirateIdStartDate = null;
//                                Date sysDate = GlobalUtilities.clearTime(new Date());
//                                TrafficConfig config = ServiceLocator.getInstance().getConfig();
//                                String emirateId = personVO.getPersonCIDId();
//                                java.util.Date cidExpiryDate = null;
//                                if (personVO.getPersonCIDExpiryDate() != null) {
//                                    cidExpiryDate = GlobalUtilities.clearTime(personVO.getPersonCIDExpiryDate());
//                                }
//                                ServiceRuleVO serviceRule = new ServiceRuleVO();
//                                serviceRule.setService(new ServiceVO());
//                                serviceRule.setBusinessRule(new BusinessRuleVO());
//                                serviceRule.getService().setCode(new Integer(svcCode));
//                                serviceRule.getBusinessRule().setKey("BR_TRS018_MER");
//                                
//            
//                                emirateIdStartDate = config.getResidentsEmiratesIdentityStartDate();
//                                if (( personVO!=null && personVO.getPersonCountryId()!=null && 
//                                      personVO.getPersonCountryId().intValue()!= UAE_COUNTRY &&
//                                      personVO.getPersonCountryId().intValue()!= HAS_NO_PAPERS) &&
//                                      (emirateIdStartDate != null && emirateIdStartDate.compareTo(sysDate) <= 0) &&
//                                       serviceRuleHandler.isRuleLinkedToService(serviceRule)) {
//                                    boolean checkCidExpiryDate = true;
//                                    boolean checkCidData = true;
//                                    String eidReceiptNo = personVO.getEidReceiptNo();
//                                    Date eidReceiptDate = personVO.getEidReceiptDate();
//                                    Date allowedReceiptPeriod = null;
//                                    if(eidReceiptDate != null) {
//                                        Calendar calendar = Calendar.getInstance();
//                                        calendar.setTime(eidReceiptDate);
//                                        calendar.add(Calendar.MONTH, config.getAllowedEidReceiptPeriod());
//                                        
//                                        allowedReceiptPeriod = calendar.getTime();
//                                    }
//    
//                                    
//                                    
//                                    if(GlobalUtilities.isBlankOrNull(emirateId) &&
//                                        !GlobalUtilities.isBlankOrNull(personVO.getEidReceiptNo()) &&
//                                        (allowedReceiptPeriod != null &&
//                                        sysDate.compareTo(allowedReceiptPeriod) <= 0)) {
//                                        checkCidData = false;
//                                    }
//                                    if(checkCidData) {
//                                        if (GlobalUtilities.isBlankOrNull(emirateId)) {
//                                             violations.append(getViolationMessage("BR_TRS018_MER_1", trfId, svcCode));
//                                             checkCidExpiryDate = false;
//                                        }
//                                        
//                                        if (cidExpiryDate == null &&
//                                            checkCidExpiryDate) {
//                                            violations.append(getViolationMessage("BR_TRS018_MER_1", trfId, svcCode));
//                                        }
//                                        
//                                        if (cidExpiryDate != null && cidExpiryDate.before(sysDate)) {
//                                           violations.append(getViolationMessage("BR_TRS018_MER", trfId, svcCode));
//                                        }                                    
//                                    }
//    
//                                } 
//                                else {
//                                        if(personVO != null &&
//                                            personVO.getPersonCountryId() != null &&
//                                            personVO.getPersonCountryId().intValue() != HAS_NO_PAPERS) {
//                                            violations.append(getViolationMessage("BR_TRS018_MER", trfId, svcCode));    
//                                        }
//                                        
//                                } 
//                             
//                            }
//                            
//                        }
//                        // add log Rule Exception if BrTrs018Mer is fired.
//                        addLog(ctrId , trfId , profile , svcCode , null , BR_TRS018_MER);    
//                    }
//                }
//    
//            // BR_COS002_MER
//            } else if (brName.equals(BR_COS002_MER)) {
//                if (isBrCos002MerInvalid(bktExpiryDate, vleId, userEmiCode)) {
//                    debug(BR_COS002_MER + " Violation...");
//                    violations.append(getViolationMessage(BR_COS002_MER, trfId, svcCode));
//                    // add log Rule Exception if BrCos002Mer is fired.
//                    addLog(ctrId , trfId , profile , svcCode , null , BR_COS002_MER);
//                }
//    
//            // BR_COS050_MER
//            } else if (brName.equals(BR_COS050_MER)) {
//                if (isBrCos050MerInvalid(vleId, oldTrfId)) {
//                    debug(BR_COS050_MER + " Violation...");
//                    violations.append(getViolationMessage(BR_COS050_MER, trfId, svcCode));
//                    // add log Rule Exception if BrCos050Mer is fired.
//                    addLog(ctrId , trfId , profile , svcCode , null , BR_COS050_MER);
//                }
//    
//            // BR_BKT021_MER
//            } else if (brName.equals(BR_BKT021_MER)) {   
//                if (isBrBkt021MerInvalid(searchOrg, plateNo, plateCat, plateCode, 
//                                         srcId, trfId, userEmiCode, vclClass, svcCode, chasissNo)) {
//                    debug(BR_BKT021_MER + " Violation...");
//                    violations.append(getViolationMessage(BR_BKT021_MER, trfId, svcCode));
//                    // add log Rule Exception if BrBkt021Mer is fired.
//                    addLog(ctrId , trfId , profile , svcCode , null , BR_BKT021_MER);
//                }
//    
//            // BR_COS006_MER
//            } else if (brName.equals(BR_COS006_MER)) {
//                StringBuffer info = new StringBuffer();
//                if (isBrCos006MerInvalid(bktId, info)) {
//                    debug(BR_COS006_MER + " Violation...");
//                    violations.append(getViolationMessage(BR_COS006_MER, info, trfId, svcCode));
//                    // add log Rule Exception if BrCos006Mer is fired.
//                    addLog(ctrId , trfId , profile , svcCode , null , BR_COS006_MER);
//                }
//    
//            // BR_COS022_MER                             
//
//            } else if (brName.equals(BR_COS022_MER)) {
//                if (BrUtil.skipBr(trfId, svcCode, "BR_TRS013_MER", userEmiCode, new Long(pltId))) {
//                    debug("<<ELS>> SKIPPING BR_COS022_MER...");
//                } else {
//                    StringBuffer info = new StringBuffer();
//                    if (isBrCos022MerInvalid(bktId, vleId, pltId, info, trfId, svcCode)) {
//                        debug(BR_COS022_MER + " Violation...");
//                        violations.append(getViolationMessage(BR_COS022_MER, info, trfId, svcCode));
//                        // add log Rule Exception if BrCos022Mer is fired.
//                        addLog(ctrId , trfId , profile , svcCode , null , BR_COS022_MER);
//                    }
//                }
//    
//            // BR_COS007_MER
//            } else if (brName.equals(BR_COS007_MER)) {
//                /** Old Implementation */
//    //            if (canBeSkipped(oldTrfId, svcCode, "BR_COS007_MER", userEmiCode)) {
//    //                debug("<<ELS>> SKIPPING BR_COS007_MER...");
//    //            } else {
//    //                if (isBrCos007MerInvalid(bktId, oldTrfNo,userEmiCode)) {
//    //                    debug(BR_COS007_MER + " Violation...");
//    //                    violations.append(getViolationMessage(BR_COS007_MER));
//    //                }
//    //          }
//                if (isBrCos007MerInvalid(bktId, oldTrfNo,userEmiCode)) {
//                    debug(BR_COS007_MER + " Violation...");
//                    violations.append(getViolationMessage(BR_COS007_MER, trfId, svcCode));
//                    // add log Rule Exception if BrCos007Mer is fired.
//                    addLog(ctrId , trfId , profile , svcCode , null , BR_COS007_MER);
//                }
//    
//                
//              
//             // BR_TRS0128_MER
//            } else if (brName.equals(BR_TRS0128_MER)) {
//                
//                if (isBrTrs0128MerInvalid(prsId ,svcCode)) {
//                    
//                    debug(BR_TRS0128_MER + " Violation...");
//                    violations.append(getViolationMessage(BR_TRS0128_MER, trfId, svcCode));
//                    // add log Rule Exception if BrTrs0128Mer is fired.
//                    addLog(ctrId , trfId , profile , svcCode , null , BR_TRS0128_MER);
//                }  
//                
//    
//            // BR_TRS042_MER
//            } else if (brName.equals(BR_TRS042_MER)) {
//                StringBuffer info = new StringBuffer();
//                if (isBrTrs042MerInvalid(prsId, info)) {
//                    debug(BR_TRS042_MER + " Violation...");
//                    violations.append(getViolationMessage(BR_TRS042_MER, info, trfId, svcCode));
//                    // add log Rule Exception if BrTrs042Mer is fired.
//                    addLog(ctrId , trfId , profile , svcCode , null , BR_TRS042_MER);
//                }
//            } else if(brName.equals(BR_TRS093_MER)){
//                if(isBrTRS093MerInvalid(svcCode, orgId, prsId)){
//                        debug(BR_TRS093_MER + " Violation...");
//                        violations.append(getViolationMessage(BR_TRS093_MER, trfId, svcCode));  
//                        // add log Rule Exception if BrTRS093Mer is fired.
//                        addLog(ctrId , trfId , profile , svcCode , null , BR_TRS093_MER);
//                }
//            } 
////            else if(brName.equals(BR_REG035_MER)){
////                if(isBrReg035MerValid(trfNo,vclClass)){
////                    debug(BR_REG035_MER + " Violation...");
////                    violations.append(getViolationMessage(BR_REG035_MER));
////                    // add log Rule Exception if BrReg035Mer is fired.
////                    addLog(ctrId , trfId , profile , svcCode , null , BR_REG035_MER);
////                }
////            }
//            
//            else if(brName.equals(BR_REG040_MER)){
//                if(isBrReg040MerValid(trfNo,chasissNo,vclClass)){
//                    debug(BR_REG040_MER + " Violation...");
//                    violations.append(getViolationMessage(BR_REG040_MER, trfId, svcCode));
//                    // add log Rule Exception if BrReg040Mer is fired.
//                    addLog(ctrId , trfId , profile , svcCode , null , BR_REG040_MER);
//                }            
//            } else if (brName.equals(BR_REG042_MER)) {
//                
//                if (isBrReg042MerValid(plateCat,orgId))  {
//                    
//                    debug(BR_REG042_MER + " Violation...");
//                    violations.append(getViolationMessage(BR_REG042_MER, trfId, svcCode));
//                    // add log Rule Exception if BrReg042Mer is fired.
//                    addLog(ctrId , trfId , profile , svcCode , null , BR_REG042_MER);
//                }
//            } else if (brName.equals(BR_REG043_MER)) {
//                
//                if (isBrReg043MerValid(plateCat,orgId))  {
//                    
//                    debug(BR_REG043_MER + " Violation...");
//                    violations.append(getViolationMessage(BR_REG043_MER, trfId, svcCode));
//                    // add log Rule Exception if BrReg043Mer is fired.
//                    addLog(ctrId , trfId , profile , svcCode , null , BR_REG043_MER);
//                }
//            } else if (brName.equals(BR_REG044_MER)) {
//                
//                if (isBrReg044MerValid(plateCat,orgId,vclClass))  {
//                    
//                    debug(BR_REG044_MER + " Violation...");
//                    violations.append(getViolationMessage(BR_REG044_MER, trfId, svcCode)); 
//                    // add log Rule Exception if BrReg044Mer is fired.
//                    addLog(ctrId , trfId , profile , svcCode , null , BR_REG044_MER);
//                }
//            } else if (brName.equals(BR_COS059_MER)) {
//                
//                if (isBrCos059MerValid(plateCat,orgId))  {
//                    
//                    debug(BR_REG043_MER + " Violation...");
//                    violations.append(getViolationMessage(BR_COS059_MER, trfId, svcCode));   
//                    // add log Rule Exception if BrCos059Mer is fired.
//                    addLog(ctrId , trfId , profile , svcCode , null , BR_COS059_MER);
//                }
//            } else if (brName.equals(BR_TRS221_MER)) {
//                
//                if (isBrTRS221MerInvalid(prsId,orgId,svcCode))  {
//                    
//                    debug(BR_TRS221_MER + " Violation...");
//                    violations.append(getViolationMessage(BR_TRS221_MER, trfId, svcCode));   
//                    // add log Rule Exception if BR_TRS221_MER is fired.
//                    addLog(ctrId , trfId , profile , svcCode , null , BR_TRS221_MER);
//                }
//            }
//        } catch (Exception ex) {
//            // add log type any exception except prevalidation exception.
//            addLog(ctrId , trfId , profile , svcCode , ex , null);
//            throw new TrafficException(ex); 
//        }   
    }
    
   

    /**
     * Validate VHL transaction.
     * 
     * @return Violations messages.
     */
    public static String getVhlViolations(int svcCode, Map params) {
        // Violations message
        StringBuffer violations = new StringBuffer();

        switch (svcCode) {
            case SVC_REGISTRATION:
                //checkBr(BR_REG011_MER, svcCode, violations, params);
                checkBr(BR_TRS012_MER, svcCode, violations, params);
                //checkBr(BR_TRS050_MER, svcCode, violations, params);
                checkBr(BR_TRS036_MER, svcCode, violations, params);
//                checkBr(BR_TRS042_MER, svcCode, violations, params);
                checkBr(BR_REG026_MER, svcCode, violations, params);
                checkBr(BR_TRS013_MER, svcCode, violations, params);
                checkBr(BR_TRS014_MER, svcCode, violations, params);
                checkBr(BR_TRS018_MER, svcCode, violations, params);
                checkBr(BR_COS002_MER, svcCode, violations, params);
                checkBr(BR_BKT021_MER, svcCode, violations, params);
                checkBr(BR_TRS051_MER, svcCode, violations, params);
                checkBr(BR_TRS052_MER, svcCode, violations, params);
                checkBr(BR_TRS053_MER, svcCode, violations, params);
                checkBr(BR_TRS093_MER, svcCode, violations, params);
                checkBr(BR_REG035_MER, svcCode, violations, params);
                checkBr(BR_REG040_MER, svcCode, violations, params);
                checkBr(BR_TRS0128_MER, svcCode, violations, params);
                checkBr(BR_REG042_MER, svcCode, violations, params);
                checkBr(BR_REG043_MER, svcCode, violations, params);
                checkBr(BR_REG044_MER, svcCode, violations, params);
                checkBr(BR_TRS221_MER, svcCode, violations, params);
                break;
            case SVC_CHANGE_OWNERSHIP:
                checkBr(BR_TRS012_MER, svcCode, violations, params);
                //checkBr(BR_TRS050_MER, svcCode, violations, params);
                checkBr(BR_TRS036_MER, svcCode, violations, params);
                checkBr(BR_TRS013_MER, svcCode, violations, params);
                checkBr(BR_TRS014_MER, svcCode, violations, params);
                checkBr(BR_TRS018_MER, svcCode, violations, params);
//                checkBr(BR_TRS042_MER, svcCode, violations, params);
                checkBr(BR_COS002_MER, svcCode, violations, params);
                checkBr(BR_COS006_MER, svcCode, violations, params);
                checkBr(BR_COS022_MER, svcCode, violations, params);
                checkBr(BR_COS007_MER, svcCode, violations, params);
                checkBr(BR_COS050_MER, svcCode, violations, params);
                checkBr(BR_TRS051_MER, svcCode, violations, params);
                checkBr(BR_TRS052_MER, svcCode, violations, params);
                checkBr(BR_TRS053_MER, svcCode, violations, params);
                checkBr(BR_TRS093_MER, svcCode, violations, params);
                checkBr(BR_TRS0128_MER, svcCode, violations, params);
                checkBr(BR_COS059_MER, svcCode, violations, params);
                checkBr(BR_TRS221_MER, svcCode, violations, params);
                break;
            case SVC_EXPORT_TO_COUNTRY:
                checkBr(BR_TRS012_MER, svcCode, violations, params);
                //checkBr(BR_TRS050_MER, svcCode, violations, params);
                checkBr(BR_TRS036_MER, svcCode, violations, params);
//                checkBr(BR_TRS042_MER, svcCode, violations, params);
                checkBr(BR_REG026_MER, svcCode, violations, params);
                checkBr(BR_TRS013_MER, svcCode, violations, params);
                checkBr(BR_ETC006_MER, svcCode, violations, params);
                checkBr(BR_TRS0128_MER, svcCode, violations, params);
                checkBr(BR_TRS221_MER, svcCode, violations, params);
                break;
            case SVC_EXPORT_TO_EMIRATE:
                checkBr(BR_ETE007_MER, svcCode, violations, params);
                checkBr(BR_ETE014_MER, svcCode, violations, params);
                checkBr(BR_TRS012_MER, svcCode, violations, params);
                //checkBr(BR_TRS050_MER, svcCode, violations, params);
                checkBr(BR_TRS036_MER, svcCode, violations, params);
                checkBr(BR_TRS013_MER, svcCode, violations, params); 
//                checkBr(BR_TRS042_MER, svcCode, violations, params);
                checkBr(BR_TRS0128_MER, svcCode, violations, params);
                checkBr(BR_TRS221_MER, svcCode, violations, params);
                break;
        }

        return violations.toString();
    }
    
    
    
//    /**
//     * Check if BR_REG035_MER is valid or not.
//     * 
//     * @param trfNo traffic file number
//     * @param vclClass Vehicle Class Id
//     * 
//     * @return true if the Business rule was valid, false otherwise.
//     */
//    public static boolean isBrReg035MerValid(int trfNo ,long vclClass){
//    
//        // Get trafic file info.
//        TrafficFile file = VehicleServletDAO.getTrafficFileByNo(trfNo);
//        if (file == null) {
//            debug(" Traffic file not found...");
//            return false;
//        }
//        
//         List allowedTrafficFileList = new ArrayList ();
//        
//         
//        // Check If Traffic File Belongs To Person    
//        if ( file.isPersonFile() && 
//             vclClass == VehicleClassVO.VEHICLE_CLASS_ID_TRAILER.longValue()  ) {
//             Boolean vclHasVehDescAllowedFlag = vehicleDescriptionHandler.isVehicleDescriptionAllowedForPerson(new Long (vclClass));
//             
//            return !vclHasVehDescAllowedFlag.booleanValue();      
//        }
//        
//        return false;        
//    }
    
    
}