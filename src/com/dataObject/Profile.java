package com.dataObject;

import java.io.Serializable;
/*-------Log of Chanegs---------------------------------------------------------

Ver     Programmer       Date        Description
------- ---------------  ----------  -------------------------------------------
1.0.4.1 Husam A. Barham  18-03-2006  - adding is_credit_center member.
------------------------------------------------------------------------------*/
public interface Profile extends Serializable
{
    public final short USER_NAME = 1;
    public final short USER_ID = 2;
    public final short USER_PASSWORD = 3;
    public final short USER_EMIRATE_CODE = 4;
    public final short USER_CENTER_ID = 5;
    public final short USER_GENDER = 6;
    public final short USER_GENDER_DESC = 7;
    public final short USER_CENTER_NAME = 8;
    public final short EMPLOYEE_NAME = 9;
    public final short USER_GROUP_ID = 10;
    public final short CAN_COLLECT_MONEY = 11;
    public final short IS_SUPER = 12;
    public final short EMPLOYEE_RANK = 13;
    public final short EMPLOYEE_RANK_DESC = 14;
    public final short EMPLOYEE_NUMBER = 15;
    public final short EMPLOYEE_ID = 16;
    public final short USER_CENTER_NAME_E = 17;
    public final short LANG_CODE= 19;
    
    //These have been because DP project has its own profile with different attributes.  

    public final short TRAFFIC_ORG_ID  = 23;
    public final short TRAFFIC_PRS_ID  = 24;
    public final short IS_INTERNET_USER= 27; 
    public final short IS_KIOSK_USER   = 28;        

    public final short SKIPPED_BRS     = 29;            

    public final short USER_GROUPS     = 30; 

    public final short IS_IVR_USER      = 31;
    public final short IS_CREDIT_CENTER = 32;
    public final short USER_LEVEL = 33;
    public final short CAN_PAY_BY_CREDIT = 34;
    public final short CENTER_CATEGORY = 35;
    public final short DLC_PRINTER_TYPE = 36;
    public final short CAN_ENTER_TEST_MANUALLY = 37;
    public final short USE_NEW_BKT_PRINT_CARD = 38;

    public final short USER_SECURITY_CONTEXT = 39;
    public final short CHECK_NEW_SECURITY_MODEL = 40;
    public final short DOMAIN_USER_HOST = 41;
    public final short USER_IP_ADDRESS = 42;
    public final short SESSION_ID = 43;
    public final short USER_LOG_ID = 44;
    public final short EMPLOYEE_NAME_E = 45;
    public final short IS_FTF_USER = 54;    
    public final short IS_SMART= 55;    
    public final short MAC_ADDRESS= 56;
    
    public final short IS_EXAMINER= 57;
    public final short EXAMINER_TYPE= 58;
    
    
    public final short IS_CHECK_MAC_CENTER= 59;
    public final short IS_USER_MAC_CHECKED= 60;
    public Object getAttribute(short attCode);
    public void setAttribute(short attCode, Object value);
}