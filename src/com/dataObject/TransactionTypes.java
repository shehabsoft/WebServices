/*
 * Copyright (c) i-Soft 2008.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 * * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  03/01/2009  - File created.
 * 1.01  Moh Fayek          02/07/2011  - TRF-5237
 * 1.02  Ahmad M.Adawi      04/07/2012  - Add SVC_ELIGIBILITY_REQUEST
 * 1.03  Mohammed Kabeel    31/07/2012  - SDDI-2613
 */ 

package com.dataObject;


/**
 * Transactions XML types.
 *
 * @author Eng. shehab tarek
 * @version 1.03
 */   
public interface TransactionTypes {
    /** Transaction status values. */
    String TRS_STATUS_FAILED = "Failed";
    String TRS_STATUS_NEW = "New";
    String TRS_STATUS_CERTIFIED = "Certified";
    String TRS_STATUS_CONFURMED = "Confirmed";
    String TRS_STATUS_CANCELED = "Canceled";
    String TRS_STATUS_PENDING = "Pending";   
    String TRS_STATUS_POSTED = "Posted";
    
    /** Service code values */
    int SVC_SUBMIT_EYE_TEST = 116;
    int SVC_VEHICLE_REGISTRATION = 201;
    int SVC_BOOKLET_RENEWAL = 204;
    int SVC_POSSESSION = 205;
    int SVC_CHANGE_BOOKLET_INFO = 208;
    int SVC_BOOKLET_RENEWAL_CHANGE_NUMBER = 224;
    int SVC_DRIVING_LICENSE_RENEWAL = 103;
    int SVC_AUCTION_REGISTRATION = 406;
    int SVC_SELL_PLATE = 401;
    int SVC_REQUEST_EARLY_PLATE = 408;
    int SVC_ALLOCATING_PLATE = 407;
    int SVC_DEPOSIT_INSURANCE = 404;
    int SVC_OWNERSHIP_CERTIFICATE = 214;
    int SVC_NO_OWNERSHIP_CERTIFICATE = 215;
    int SVC_PLATE_RESERVATION_RENEWAL = 210;
    int SVC_RETURN_INSURANCE_CERTIFICATE = 216;
    int SVC_LOST_DAMAGE_PLATE = 218;
    int SVC_LOST_DAMAGE_BOOKLET = 211;
    int SVC_LOST_DAMAGE_LICENSE = 104;
    int SVC_LOST_DAMAGE_POSSESSION = 220;   
    int SVC_EXPORT_TO_EMIRATE = 207;
    int SVC_EXPORT_TO_COUNTRY = 206;
    int SVC_PAY_INSTEAD_OB_BOOKING = 303;  
    int SVC_PAY_UAE_FINES = 301;
    int SVC_PAY_OTHER_EMIRATE_FINES = 302;
    int SVC_PAY_ALL_TICKETS_WITH_ASSERTIONS= 316;
    int SVC_PAY_LOCAL_FINES = 315;
    int SVC_ISSUE_CLEARANCE_CERTIFICATE = 311;
    int SVC_PAY_EMIRATE_FINE_WITH_LICENSE_PRESENTATION = 314;
    int SVC_LOST_PLATE_CERTIFICATE = 402;
    int SVC_ISSUE_PARKING_CARD = 601;
    int SVC_RENEW_PARKING_CARD = 602;
    int SVC_EXPERIENCE_CERTIFICATE = 105;
    int SVC_TRANSFER_LICENSE_TO_EMIRATE = 107;
    int SVC_ISSUE_LOST_DRIVER_PERMIT = 604; 
    int SVC_REQUEST_DRIVER_TEST = 605;
    int SVC_RENEW_DRIVER_PERMIT = 603; 
    int SVC_CLEARANCE_CERTIFICATE = 213;
    int SVC_CHANGE_PLATE_NUMBER = 209;
    int SVC_NEW_BUILDING_DEMOLITION_PERMIT = 612;
    int SVC_NEW_VEHICLE_MOBILITY_PERMIT_APPLICATION = 610;
    int SVC_RENEW_BUILDING_DEMOLITION_PERMIT = 613;    
    int SVC_RENEW_VEHICLE_MOBILITY_PERMIT_APPLICATION = 611;
    int SVC_NEW_PARKING_RESERVATION_PERMIT = 608;
    int SVC_RENEW_PARKING_RESERVATION_PERMIT = 609;
    int SVC_SOLD_HANDBOOK = 118;
    int SVC_NEW_SITE_OCCUPATION_PERMIT = 606;
    int SVC_RENEW_SITE_OCCUPATION_PERMIT = 607;
    int SVC_ISSUE_TRADE_PERMIT = 410;
    int SVC_RENEW_TRADE_PERMIT = 411;
    int SVC_SELL_PLATES_PACKAGE = 413;
    int SVC_CHANGE_PLATE_OWNERSHIP = 403;
    int SVC_RENEW_CML_PERMIT = 804;
    int SVC_NEW_CML_PERMIT = 801;
    int SVC_MODIFY_CML_PERMIT = 802;
    int SVC_ISSUE_APPROVAL_LETTER = 803;
    int SVC_ISSUE_SPECIAL_CERTIFICATE = 227;
    int SVC_ELIGIBILITY_REQUEST = 14;
    int SVC_ISSUE_NEW_LICENSE = 101;
    int SVC_ISSUE_NEW_COMMERCIAL_PERMIT = 124;
    int SVC_RENEW_GENERAL_PERMIT = 125;
    int SVC_ISSUE_TOURISM_CERTIFICATE = 212;
    int SCV_ISSUE_VEHICLE_SALES= 228;
    int SCV_ISSUE_LEARNING_PERMIT=2;
    int SVC_CODE_LOCALIZE_EYE_TEST_RESULT= 131;
    int SVC_CODE_PURCHASE_IMAGE_PLATE = 231;
    int SVC_CODE_CHANGE_OWNERSHIP  = 202;
    int SVC_MEDICAL_ASSESSMENT_RESULTS = 132;  
 	int SVC_ADD_CLASS= 102;
	int SVC_TO_IT_MAY_CONCERN =113;
	int SVC_OPEN_APPLICATION =1;
	int SVC_INSPECTION_TEST_RESULT = 701;
    int SVC_REPRINT_GENERAL_PERMIT = 126;
    int SVC_CODE_ISSUE_TRADE_PLATES = 221;
    int SVC_CODE_ISSUE_PRO_PERMIT = 134;
    int SVC_CODE_RENEW_PRO_PERMIT = 135;
    
    /** Reset Delegation Password */ 
    int SVC_RESET_DELEGATION_PASSWORD = 237;
    
    /** Delegation Amendment */
    int SVC_DELEGATION_AMENDMENT = 238;

    /** Revoke Delegation */
    int SVC_REVOKE_DELEGATION = 239;
    
    /** Revoke Delegation */
    int SVC_CHANGE_POSSESSION_OWNERSHIP = 240;
    
    /** Revoke Delegation */
    int SVC_PRINT_REG_VHL_REPORT = 241;
    
    /** Issue Exam Appointment */
    int SVC_CODE_ISSUE_EXAM_APPOINTMENT = 7;
    
    /** Change Exam Appointment */
    int SVC_CODE_CHANGE_EXAM_APPOINTMENT = 8;
    
    /** Cancle Exam Appointment */
    int SVC_CODE_CANCLE_EXAM_APPOINTMENT = 22;
    
    /** Renew Trade Plate */
    int SVC_CODE_RENEW_TRADE_PLATE = 222;   

    /** Cancel PRO Permit */
    int SVC_CODE_PRO_CANCEL_PERMIT = 136;

    /** Suspend PRO Permit */
    int SVC_CODE_PRO_SUSPEND_PERMIT = 137;

    /** Activate PRO Permit */
    int SVC_CODE_PRO_ACTIVATE_PERMIT = 138;
    
    /** Lost/Damaged Export Certificate */
    int SVC_CODE_LOST_DAMAGED_EXPORT = 219;
	
	/** Reprint Document */
    int SVC_CODE_REPRINT_DOCUMENT = 807;
	
    int SERVICE_CODE_CANCEL_PERMIT = 806;
	
    int SVC_CODE_RES_TRAINING_APPLICATION = 810;
    
    /** Remove Plate Logo */    
    int SVC_CODE_REMOVE_PLATE_LOGO = 243;
    
    /**  Renew File */
   int SVC_RENEW_FILE = 23;
    
    /** Buy Plate Logo */
    int SVC_CODE_BUY_PLATE_LOGO = 242;   
    
    /** Change Vechicle Ownership Noc  */
    int SVC_CODE_CHANGE_VEHICLE_OWNERSHIP_NOC = 246;   

    /** Renew Learning Permit */
    int SVC_CODE_RENEW_LEARNING_PERMITS = 5;  
    
    /** Reissue Learning Permit */
    int SVC_CODE_REISSUE_LEARNING_PERMITS = 6;   

    /** svc add mortgage request */
    int SVC_ADD_MORTGAGE_REQUEST = 245;
     /** MODIFY_LICENSE_CLASS */
    int SVC_CODE_MODIFY_LICENSE_CLASS = 4;  
   
   
     int  SVC_CODE_RETURN_FROM_TOURISM=225; 

    /** Change Plate Logo Size. */
    int SVC_CODE_CHANGE_PLATE_LOGO_SIZE = 244;   
    
    /** Issue Exam Appointment For Special Permit. */
    int SVC_CODE_ISSUE_EXAM_APPOINTMENT_SPECIAL_PERMIT=127;
  
    /** Open traffic file. */
    int SVC_CODE_OPEN_TRAFFIC_FILE = 931;
    
    int SVC_CODE_POSSESS_UNREGISTERED_VEHICLE = 247;

    int SVC_CODE_ALLOCATE_AUCTION_PLATE = 409;
    
    int SVC_CODE_TOURISM_CLEARANCE = 250;
    
    /** Plate categories values. */ 
    String PLC_PRIVATE = "Private"; 
    String PLC_TAXI = "Taxi";
    String PLC_PUBLIC_TRANSAPORTATION = "PublicTransportation";
    String PLC_MOTORCYCLE = "Motorcycle";
    String PLC_TRADE_PLATE = "TradePlate";
    String PLC_EXPORT = "Export";
    String PLC_CONSULATE_AUTHORITY = "ConsulateAuthority";
    String PLC_POLITICAL_AUTHORITY = "PoliticalAuthority";
    String PLC_INTERNATIONAL_ORGANIZATION = "InternationalOrganization";
    String PLC_DELEGATE = "Delegate";
    String PLC_GOVERNMENT = "Government";
    String PLC_PRIVATE_TRANSPORTATION = "PrivateTransportation";

    /** Plate code values. */
    String PCD_A = "A";
    String PCD_B = "B";
    String PCD_C = "C";
    String PCD_D = "D";
    String PCD_E = "E";
    String PCD_F = "F";
    String PCD_G = "G";
    String PCD_H = "H";
    String PCD_I = "I";
    String PCD_J = "J";
    String PCD_K = "K";
    String PCD_L = "L";
    String PCD_M = "M";
    String PCD_N = "N";
    String PCD_O = "O";
    String PCD_P = "P";
    String PCD_Q = "Q";
    String PCD_R = "R";
    String PCD_S = "S";
    String PCD_T = "T";
    String PCD_U = "U";
    String PCD_V = "V";
    String PCD_W = "W";
    String PCD_X = "X";
    String PCD_Y = "Y";
    String PCD_Z = "Z";
    
    int CIS_VHL_ELE_OWNER_TRF_NO = 3;
    int CIS_VHL_ELE_CNT_ID = 7;
    int CIS_VHL_ELE_PLATE_CATEGORY = 13;
    int CIS_VHL_ELE_PLATE_CODE = 14;
    int CIS_VHL_ELE_PLATE_NO = 15;
    int CIS_VHL_ELE_WHEEL_DRIVE = 16;
    int CIS_VHL_ELE_CHASSIS_NO = 25;
    int CIS_VHL_ELE_VCL_CODE = 26;
    int CIS_VHL_ELE_ENGINE_NO = 27;
    int CIS_VHL_ELE_ENG_CAPACITY = 28;
    int CIS_VHL_ELE_ENGINE_POWER = 29;
    int CIS_VHL_ELE_BODY_TYPE = 30;
    int CIS_VHL_ELE_CYLINDERS = 31;
    int CIS_VHL_ELE_AXlES_WEIGHT = 32;
    int CIS_VHL_ELE_METER_UNIT = 33;
    int CIS_VHL_ELE_ODOMETER_NEW_READING = 35;
    int CIS_VHL_ELE_FUEL_CODE = 36; 
    int CIS_VHL_ELE_UNLOADED_WEIGHT = 37;
    int CIS_VHL_ELE_CARRY_WEIGHT = 38;
    int CIS_VHL_ELE_NO_OF_DOORS = 39;
    int CIS_VHL_ELE_NO_OF_SEATS = 40;
    int CIS_VHL_ELE_VSM_ID = 42;
    int CIS_VHL_ELE_MODEL_YEAR = 43;
    int CIS_VHL_ELE_COLOR = 44;
    int CIS_VHL_ELE_VEHICLE_ORIGIN_ID = 45;
    int CIS_VHL_ELE_PLATE_EMI_CODE = 54;
    int CIS_VHL_ELE_AXES_NO = 55;
    int SVC_CODE_PAY_CONFISCATION_FEES =319;

    public static final int SVC_APPROVE_MORTGAGE_REQUEST = 248;
    public static final int SVC_APPROVE_MORTGAGE_RELEASE = 251;
    public static final int SVC_APPROVE_MORTGAGE_NOC = 252;
    public static final int SVC_ISSUE_NOC  = 253;
    public static final int SVC_ISSUE_DTT_NOC  = 24;

}
