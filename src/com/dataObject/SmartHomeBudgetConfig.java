

package  com.dataObject;



import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.HomeBudget.Bus.PropertyHandler;
import com.HomeBudget.common.GlobalUtilities;



/**
 * Used to encapsulate all common configuration for traffic module
 *
 * @version 1.21 - 06/09/2004
 * @author Eng.  shehab.tarek
 */
public class SmartHomeBudgetConfig implements java.io.Serializable {
    /*
     * Constants       
     */
    
    /** Class logger. */
    private static final Logger logger = Logger.getLogger(SmartHomeBudgetConfig.class.getName());

    /** Configuration file name. */
    public static final String CONFIG_FILE = "trf";
    
    

    /** Debugging mode */
    private static final String DEBUG = "ae.gov.dphq.traffic.util.debug";

    /** Log stack trace in the application error pages */
    private static final String ERROR_PAGE_DEBUG = "errorPage.debugStackTrace";
    
    /** Max attachments size in Mega Byte. */
    private static final String MAX_ATTACHMENT_SIZE = "ae.rta.attachment.maxSize";

    /** Digital radar tickets max locking period. */
    private static final String DIGITAL_RADAR_TCK_LOCK_TIMEOUT =
          "ffu.radar.digital.ticketLockTimeout";

    /** Supported MIME types for digital radar pictures. */
    private static final String RADAR_PICTURE_MIME_TYPES = 
           "ffu.radar.pictures.mime";
                         
    /** Salik fine image web-service URL. */
    private static final String SALIK_FINE_IMAGE_WS_URL = 
           "ffu.salik.ws.getImageService";

    /** UTS reporting service URL. */
    private static final String CFI_UTS_RPT_URL = 
           "cfi.uts.report.url";

    /** UTS reporting SOAP action fro "getTicketInfo". */
    private static final String CFI_UTS_RPT_SOAP_ACTION_GET_TCK = 
           "cfi.uts.report.soapAction.getTicketInfo";

    /** UTS reporting SOAP action fro "getTicketHistoryInfo". */
    private static final String CFI_UTS_RPT_SOAP_ACTION_GET_TCK_HST = 
           "cfi.uts.report.soapAction.getTicketHistoryInfo";

    /** UTS reporting SOAP action fro "getVehicleInfo". */
    private static final String CFI_UTS_RPT_SOAP_ACTION_GET_VEHICLE = 
           "cfi.uts.report.soapAction.getVehicleInfo";

    /** Unified Traffic System Header user name */
    private static final String CFI_UTS_USER_NAME
         = "cfi.uts.report.header.username";

    /** Unified Traffic System Header password */
    private static final String CFI_UTS_PASSWORD
         = "cfi.uts.report.header.password";    

    /** Default page size for pagination search pages. */
    private static final String SEARCH_PAGE_SIZE = "ae.rta.pagination.pageSize";

    /** Maximum number of records retrieved by pagination pages. */
    
    private static final String MAX_PAGINATION_RECORDS = 
      "ae.rta.pagination.maxPaginationRecords";
    
    /** Othet Emirates Light Vehicle Class */  
    private static final String OTHER_EMIRATES_LIGHT_VEHICLE_CLASS = 
          "ae.rta.esrv.common.otherEmiratesLightVehicleClass";

    /** Reports server settings. */ 
    private static final String REPORTS_SERVER = "ae.traffic.reports.server";
    private static final String REPORTS_WEB_HOST = "ae.traffic.reports.webHost";
    private static final String REPORTS_PORT = "ae.traffic.reports.port";
    private static final String REPORTS_USER_ID = "ae.traffic.reports.userId";
    //Below 2 properties added to handle the calling for report TF_STP3024_E,TF_STP3024_A
    private static final String REPORTS_SERVER_WEB_HOST = "ae.traffic.reports.server.webHost";
    private static final String REPORTS_SERVER_RDF_PORT = "ae.traffic.reports.server.port";
    
    private static final String REPORTS_SERVER_INTRANET_WEB_HOST = "ae.traffic.reports.server.intranet.webHost";
    private static final String REPORTS_SERVER_INTRANET_RDF_PORT = "ae.traffic.reports.server.intranet.port";
    private static final String REPORTS_INTRANET_USER_ID = "ae.traffic.reports.intranet.userId";
    private static final String REPORTS_INTRANET_SERVER = "ae.traffic.reports.intranet.server";
    
    /** Number of wating trials for a process to finish its current task. */
    private static final String PROCESS_STOP_TRIALS = "ae.traffic.process.stopTrials";

    /**
     * This property defines logging level for all application loggers:
     * OFF, SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST, ALL
     */
    private static final String LOGGING_LEVEL = "ae.rta.logging.level";
    
    /**
     * This property defines logging level for all smart application loggers:
     * OFF, SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST, ALL
     */
    private static final String SMART_LOGGING_LEVEL = "ae.rta.smart.logging.level";
    
    /** Used to pare/format date web layer dates. */
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
     
    /** Used to pare/format dates using the default application date format. */
    private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    
    /** Date Time Formatter */ 
    public static final SimpleDateFormat DATE_TIME_FORMAT =
                            new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.US);
                            
    /** Logging levels values. */
    private static Map LOGGING_LEVEL_VALUES = new HashMap();
    
    /** Web Service Client Timeout */
    private static final String WEBSERVICE_CLIENT_TIMEOUT =
      "webservice.client.timeout";

    /** Web Service Client Timeout */
    private static final String WEBSERVICE_CLIENT_TIMEOUT_TEST =
      "webservice.client.timeout.testUTS";

    /** Emirates Identity Start Date for Locals */
    private static final String EMIRATES_IDENTITY_LOCALS_START_DATE =
      "ae.rta.stp.emirates.identitiy.locals.start.date";

    /** Emirates Identity Start Date for Residents */
    private static final String EMIRATES_IDENTITY_RESIDENTS_START_DATE =
      "ae.rta.stp.emirates.identitiy.residents.start.date";
    
    /** Traffic application url. */
    private static final String TRAFFIC_URL = "ae.rta.traffic.url";
    
    /** Booklet print allowed period */
    private static final String BOOKLET_PRINT_ALLOWED_PERIOD = "ae.vhl.bkt.print.allowed_period";
    
    /** License print allowed period */
    private static final String LICENSE_PRINT_ALLOWED_PERIOD = "ae.drl.dlc.print.allowed_period";
    
    /** Traffic application ip. */
    private static final String TRAFFIC_IP = "ae.rta.traffic.ip";    
    
    /** Traffic Threads application ip. */
    private static final String TRAFFIC_THREADS_IP = "ae.rta.traffic.threads.ip";    
    
    /** Traffic Threads application enabled */
    private static final String IS_TRAFFIC_THREADS_ENABLED = "ae.rta.traffic.threads.enabled";

    /** Used by business-rules business object to chache business-rules. */
    private static final String CACHE_BUSINESS_RULES
    = "ae.rta.esrv.common.bus.BusinessRule.useCache";

    /** Used by domain-values business object to chache domain-values. */
    private static final String CACHE_DOMAIN_VALUES
    = "ae.rta.esrv.common.bus.DomainValue.useCache";
    
    /** Traffic Network Performance Log Process Delay  */
    private static final String TRAFFIC_NPL_DELAY
    = "ae.traffic.npl.process.delay";

    /** Traffic Network Performance Log Process Active  */
    private static final String TRAFFIC_NPL_ACTIVE
    = "ae.traffic.npl.process.active";
    
    /** EGOV Payment Response Time */
    private static final String EGOV_PAYMENT_RESPONSE_TIME
    = "ae.rta.egov.payment.responseTime";
    
    /** Traffic Testing environment  */
    private static final String TRAFFIC_TESTING
    = "ae.traffic.isTesting";
    
    /** Booklet print transfer center ID */
    private static final String BOOKLET_PRINT_TRANSFER_CENTER
         = "ae.rta.vhl.bkt.defaultPrintTransferCenterCode"; 

    /** SSL login max waiting period. */
    private static final String SSL_LOGIN_TIMEOUT =
          "ae.rta.traffic.ssl.timeoutSeconds";
    
    /** Traffic application SSL url. */
    private static final String TRAFFIC_SSL_URL = "ae.rta.traffic.sslUrl";
    
    /** Receipt Installment Cheque Minimum Amount */
    private static final String RECIEPT_INSTALLMENT_CHEQUE_MIN_AMOUNT
    = "ae.rta.stp.rch.minimumAmount";
          
    /** DNRD GetPersonInfo Web Service URL. */
    private static final String DNRD_PERSON_INFO_SERVICE_URL = 
                            "ae.rta.common.ws.dnrd.getPersonInfoService.url";

    private static final String OLD_DNRD_PERSON_INFO_SERVICE_URL = 
                            "ae.rta.common.ws.dnrd.getOldPersonInfoService.url";
    
    /** Tibco DNRD GetPersonInfo Web Service URL. */
    private static final String TIBCO_DNRD_PERSON_INFO_SERVICE_URL = 
                            "ae.rta.common.ws.tibco.dnrd.getPersonInfoWithPhotoService.url";
                            
    /** DNRD GetLookupData Web Service URL. */
    private static final String DNRD_LOOKUP_DATA_SERVICE_URL = 
                            "ae.rta.common.ws.dnrd.getLookupDataService.url";

    /** DNRD GetPersonInfo Web Service SOAP Action. */
    private static final String DNRD_PERSON_INFO_SOAP_ACTION =
                    "ae.rta.common.ws.dnrd.getPersonInfoService.soap.action";
    
    /** DNRD New GetPersonInfo Web Service SOAP Action. */
    private static final String TIBCO_DNRD_PERSON_INFO_SOAP_ACTION =
                    "ae.rta.common.ws.tibco.dnrd.getPersonInfoService.soap.action";
    
    /** DNRD GetLookupData Web Service SOAP Action. */
    private static final String DNRD_LOOKUP_DATA_SOAP_ACTION =
                    "ae.rta.common.ws.dnrd.getLookupDataService.soap.action";
    
    /** DNRD GetPersonInfo Web Service URL. */
    private static final String DNRD_PERSON_INFO_SECURITY_ID = 
                            "ae.rta.common.ws.dnrd.getPersonInfoService.securityId";

    /** DNRD GetLookupData Web Service URL. */
    private static final String DNRD_LOOKUP_DATA_SECURITY_ID = 
                            "ae.rta.common.ws.dnrd.getLookupDataService.securityId";
    /** SSL login */
    private static final String SSL_LOGIN_ENABLED = "ae.rta.traffic.ssl.enabled";

    /** FedEx FSM server IP. */
    private static final String FEDEX_FSM_IP = "ae.rta.fedex.fsm.ip";

    /** FedEx FSM server port. */
    private static final String FEDEX_FSM_PORT = "ae.rta.fedex.fsm.port";
    
    /** FedEx FSM printer location. */
    private static final String FEDEX_FSM_PRINTER_LOCATION = "ae.rta.fedex.fsm.printerLocation";
    
    /** UTS reort service enabled */
    private static final String UTS_ENABLED = "ae.gov.trf.cfi.uts.reportingService.enabled";

    /** Center Performance Auto-Refresh Delay In Seconds. */
    private static final String SCD_CENTER_PERFORMANCE_DELAY =
                                "ae.rta.scd.centerPerformance.autorefresh.delay";
    
    /** Max Payable Fines Count  */
    private static final String MAX_PAYABLE_FINES_COUNT
    = "ae.rta.trs.maxPayableFinesCount" ;

    /** DEG Payment Activation Flag. */
    private static final String DEG_PAYMENT_ACTIVATION_FLAG = "ae.rta.deg.payment.active";

    /** DEG Payment Activation Flag On Traffic Application. */
    private static final String DEG_PAYMENT_ACTIVATION_TRAFFIC_FLAG = "ae.rta.deg.payment.traffic.active";
    
    /** Number of Displayed Payable Fines */
    private static final String NO_OF_DISPLAYED_PAYABLE_FINES
    = "ae.rta.ffu.payableFinesSearch.noOfDisplayedFines";

    /** check the dnrd service value if enabled/disabled */
    private static final String IS_DNRD_SERVICE_ENABLED= "ae.rta.common.ws.dnrd.active";

    /** RTA Counters DEG ePay Hub MD5 Hashing Secret Code. */
    private static final String RTA_COUNTERS_DEG_EPAY_SECRET_CODE
        = "ae.traffic.rtaCounters.degPayment.secretCode";
        
    /** DP Counters DEG ePay Hub MD5 Hashing Secret Code. */
    private static final String DP_COUNTERS_DEG_EPAY_SECRET_CODE
        = "ae.traffic.dpCounters.degPayment.secretCode";
        
    /** RTA Self-Services DEG ePay Hub MD5 Hashing Secret Code. */
    private static final String RTA_SELFSERVICES_DEG_EPAY_SECRET_CODE
        = "ae.traffic.rtaSelfServices.degPayment.secretCode";
        
    /** DP Self-Services DEG ePay Hub MD5 Hashing Secret Code. */
    private static final String DP_SELFSERVICES_DEG_EPAY_SECRET_CODE
        = "ae.traffic.dpSelfServices.degPayment.secretCode";           

    /** DEG ePay Transaction Status Inquery Timeout (Milliseconds). */
    private static final String DEG_EPAY_TIMEOUT
        = "ae.rta.trs.ws.payment.deg.timeout";
        
    /** DEG ePay Transaction Status Inquery Timeout (Minets). */
    private static final String DEG_LOCK_TIMEOUT_MIN
        = "ae.rta.trs.bus.deg.lockTimeOut";        

    /** User locale key. */
    private static final String USER_LOCALE = "ae.rta.esrv.i18n.locale.key";
    
    /** Supported languages. */
    private static final String SUPPORTED_LANGUAGES = "ae.rta.esrv.i18n.languages";
    
    /** Appication context path URL. */
    private static final String CONTEXT_PATH_URL = "ae.rta.grt.url";
    
    /**
     * Web-application welcome page. When the user performs a successful login
     * the application will forward his  request to the welcome page.
     */
    private static final String WELCOME_PAGE_URL = "ae.rta.grt.web.welcomePage";
    
    /** Transaction Cancellation Allowed Period */
    private static final String TRANSACTION_CANCEL_ALLOWED_PERIOD
    = "ae.rta.trs.cancellationAllowedPeriod";
    
   /** Maximum number of vehicles per seasonal card for organizations. */
    private static final String MAX_VEHICLES_PER_SEASONAL_CARD_FOR_ORGANIZATIONS
                                     = "ae.rta.rvp.sc.maxVehiclesPerCardForOrganizations";
    
    /** Maximum number of vehicles per seasonal card for persons. */
    private static final String MAX_VEHICLES_PER_SEASONAL_CARD_FOR_PERSONS
                                     = "ae.rta.rvp.sc.maxVehiclesPerCardForPersons";
    
    /** Minimum Activation Date In Days */
    private static final String MIN_ACTIVATION_DATE_DAYS
                                        = "ae.rta.rvp.sc.minActivationDateDays";
                                        
    /** Maximum Activation Date In Days */
    private static final String MAX_ACTIVATION_DATE_DAYS
                                        = "ae.rta.rvp.sc.maxActivationDateDays";

    /** Max Synchronized Fines Count */                                        
    private static final String MAX_SYNCHRONIZED_FINES_COUNT
    = "ae.rta.ffu.maxSynchronizedFinesCount";
    

    /** Get Permit Details Service Endpoint */
    private static final String GET_PERMIT_DETAILS_SERVICE_ENDPOINT = 
            "ae.rta.esrv.ept.ws.GetPermitDetails.url";
    
    /** EPT Services Header Username */
    private static final String EPTSERVICES_HEADER_USERNAME = 
            "ae.rta.EPTServices.header.username";
    
    /** EPT Services Header Password */        
    private static final String EPTSERVICES_HEADER_PASSWORD = 
            "ae.rta.EPTServices.header.password";
     
    /** Get Black Listed Service Endpoint */
    private static final String GET_BLACK_LISTED_SERVICE_ENDPOINT = 
            "ae.rta.ept.ws.BlackListed.url";
            
    /** Get Renewal Permit Service Endpoint */
    private static final String GET_RENEWAL_PERMIT_SERVICE_ENDPOINT = 
            "ae.rta.ept.ws.RenewalPermit.url";
            
    /** Get Lost Permit Service Endpoint */
    private static final String GET_LOST_PERMIT_SERVICE_ENDPOINT = 
            "ae.rta.ept.ws.LostPermit.url";             
            
    /** Get Test Appointment Service Endpoint */
    private static final String GET_TEST_APPOINTMENT_SERVICE_ENDPOINT = 
            "ae.rta.ept.ws.TestAppointment.url";        
            
    /** Get Print Special Permits Buttons Status */
    private static final String PRINT_SPECIAL_PERMITS_STATUS = 
            "ae.rta.pmt.PrintSpecialPermitsStatus";
    
    /** Safety Information Service Header Username Property */
    private static final String SAFETY_INFORMATION_SERVICE_USER_NAME =
            "ae.rta.SafetyInformationService.header.username";
            
    /** Safety Information Service Header Password Property */
    private static final String SAFETY_INFORMATION_SERVICE_PASSWORD =
            "ae.rta.SafetyInformationService.header.password";
            
    /** Get Permits Service Client EndPoint.*/
    private static final String GET_PERMITS_SERVICE_CLIENT_ENDPOINT = 
                "ae.rta.esrv.ept.ws.getPermitsServiceClientEndPoint.url";
                
    /** Emirates Identity Start Date for Local */
    private static final String LOCAL_EMIRATES_IDENTITY_START_DATE =
            "ae.rta.stp.emirates.identitiy.locals.start.date";

    /** Allowed Eid Receipt Period */
    private static final String ALLOWED_EID_RECEIPT_PERIOD = "ae.rta.stp.allowedEidReceiptPeriod";
            
    /** Emirates Identity Start Date for Residents */
    private static final String RESIDENTS_EMIRATES_IDENTITY_START_DATE =
            "ae.rta.stp.emirates.identitiy.residents.start.date";
    
    /** UTS service End Point */
    private static final String UTS_SERVICE_END_POINT = 
                                
                    "ae.rta.uts.ws.getUTSServiceEndPoint.url";
    
    /** maximum duration in days for vehicle mobility permit.*/
    private static final String MAXIMUM_EXTRA_LOAD_VEHICLE_MOBILITY_PERMIT_DURATION_DAYS =
                                    "ae.rta.rvp.vmp.maxDurationDays";

    /** Maximun request Duration Days */
    private static final String MAX_DURATION_DAYS = "ae.rta.rvp.bdp.maxDurationDays";     
    
    /** auditing center id */
    private static final String AUDITING_CENTER_ID="ae.rta.stp.common.transactionsAuditingCenterID";   
    
    /** UTS Time Out period */
    private static final String UTS_TIME_OUT="ae.rta.stp.common.utsTimeOutPeriod";
    
    /** EDMS Integration Target End Point URL. */
    private static final String EDMS_INT_TARGET_END_POINT_URL = "ae.rta.edms.integration.targetEndPointUrl";
    
    /** EDMS Integration Owner URI. */
    private static final String EDMS_INT_OWNER_URI = "ae.rta.edms.integration.ownerUri";
    
    /** EDMS Integration Network Type. */
    private static final String EDMS_INT_NETWORK_TYPE = "ae.rta.edms.integration.networkType";
    
    /** EDMS Integration Unit Name. */
    private static final String EDMS_INT_UNIT_NAME = "ae.rta.edms.integration.unitName";
    
    /** EDMS Integration Username. */
    private static final String EDMS_INT_USERNAME = "ae.rta.edms.integration.username";
    
    /** EDMS Integration Password. */
    private static final String EDMS_INT_PASSWORD = "ae.rta.edms.integration.password";
    
    /** EDMS Integration Target Library. */
    private static final String EDMS_INT_TARGET_LIBRARY = "ae.rta.edms.integration.targetLibrary";
    
    /** EDMS Integration Search Max Records. */
    private static final String EDMS_INT_SEARCH_MAX_RECORDS = "ae.rta.edms.integration.searchMaxRecords";
    
    /** EDMS Integration Connection Timeout. */
    private static final String EDMS_INT_CONNECTION_TIMEOUT = "ae.rta.edms.integration.connectionTimeout";
    
    /** EDMS Integration Socket Timeout. */
    private static final String EDMS_INT_SOCKET_TIMEOUT = "ae.rta.edms.integration.socketTimeout";
    
    /** EDMS Integration Document Author Name. */
    private static final String EDMS_INT_AUTHOR_NAME = "ae.rta.edms.integration.authorName";
    
   /** MOI Service Enabled */
    private static final String MOI_SERVICE_ENABLED = "ae.rta.uts.ws.moiService.enabled";

   /** No Insurance Company Id */
    private static final String NO_INSURANCE_COMPANY_ID = "ae.rta.common.noInsuranceCompanyId";

    /** Entertainment Motor Activity */
    private static final String ENTERTAINMENT_MOTOR_ACTIVITY = "ae.rta.common.entertainmentMotorActivity";
    
    /** Vcc Integration Outage Time From */
    private static final String VCC_INTEGRATION_OUTAGE_TIME_FROM = "ae.rta.vcc.vccIntegrationOutageTimeFrom";
    
    /** Vcc Integration Outage Time To */
    private static final String VCC_INTEGRATION_OUTAGE_TIME_TO = "ae.rta.vcc.vccIntegrationOutageTimeTo";
    
    /** UTS Integration Outage Time From */
    private static final String UTS_INTEGRATION_OUTAGE_TIME_FROM = "ae.rta.uts.integrationOutageTimeFrom";
    
    /** UTS Integration Outage Time To */
    private static final String UTS_INTEGRATION_OUTAGE_TIME_TO = "ae.rta.uts.integrationOutageTimeTo";
    
    /** Vcc Xmlns WS Security */
    private static final String VCC_XMLNS_WS_SECURITY = "ae.rta.vcc.vccXmlnsWSSecurity";
    
    /** Ckeck Note Codes */
    private static final String CHECK_NOTE_CODES = "ae.rta.vhl.checkNoteCodes";

    /** Export vehicle cutoff date */
    private static final String EXPORT_VEHICLE_CUTOFF_DATE = "ae.rta.vhl.export.vehicle.cutoff.date";
    
    /** Knowledge Fee Id.*/
    private static final String KNOWLEDGE_FEE_ID = "ae.rta.pss.knowledgeFeeId";
    
    /** transaction Intermediate User Name.*/
    private static final String TRANSACTION_INTERMDIATE_USER_NAME = "ae.rta.trs.ws.transactionIntermediateUserName";

    /** Person Activate Br */
    private static final String PERSON_ACTICATE_BRS = "ae.rta.prs.activateBr";
    
    /** Property name for if no person papers transactions business is enabled*/
    public static final String NO_PERSON_PAPERS_TRANSACTIONS_PROPERTY =
                                                "ae.rta.common.noPersonPapersTransactions";
    /** Appointment Maximum Working Hours Count/*/
    public static final String MAXIMUM_APPOINTMENT_CENTER_WORKING_HOURS_COUNT =
                                                    "ae.rta.apt.maximumShiftNumber";

    /** Ckeck Note Codes */
    private static final String CKECK_NOTE_CODES = "ae.rta.vhl.checkNoteCodes";
    
    /** Property for Appointement System Active or not*/
    public static final String IS_APPOINTMENT_SYSTEM_ACTIVE =
                                                    "ae.rta.esrv.apt.isSystemActive";
    
    /** Booklet print allowed period */
    private static final String TRADE_PLATE_ALLOWED_PERIOD = "ae.vhl.tradePlate.alloweddays";
    
    /** Allowed No Of Cange Plate Ownership Transactions */
    private static final String ALLOWED_CPO_TRANSACTIONS_NO = "ae.rta.spl.allowedNoOfCPOTransactions";
    
    /** Active Old Dnrd Service */
    private static final String ACTIVE_OLD_DNRD = "ae.rta.common.ws.dnrd.getOldPersonInfoService.activeService";
    
    /** Active CTA Activity */
    private static final String ACTIVE_CTA_ACTIVITY = "ae.rta.cml.ActivateReplacementCTAActivity";
    
    /** Active Non CTA Activity */
    private static final String ACTIVE_NON_CTA_ACTIVITY = "ae.rta.cml.ActivateReplacementNonCTAActivity";

    /** CML Max Attachment Size. */
    public static final String CML_MAX_ATTACHMENT_SIZE =
                                                    "ae.rta.esrv.cml.maxAttachmentSize";

    /** Get Permit Details Service Endpoint */
    private static final String GET_UTS_LICENSE_INFO_SERVICE_ENDPOINT = 
            "uts.getUTSLicenseInfoEndPoint.url";

    /** Get Allocated Plates Lookup Max Count */
    private static final String ALLOCATED_PLATES_LOOKUP_MAX_COUNT = 
            "ae.rta.spl.allocatedPlatesLookup.maxCount";
    

    /** Get Sms Prohibited Characters */
    private static final String SMS_PROHIBITED_CHARACTERS = 
            "ae.rta.ntf.smsProhibitedCharacters";
            
    /** Get Export To Country */
    private static final String EXPORT_TO_COUNTRY = 
            "ae.gov.trf.vhl.getExportToCountry";
            
    /** Get Export Active Note */
    private static final String EXPORT_ACTIVE_NOTE = 
            "ae.gov.trf.vhl.ExportActiveNote";
            
    /** get export max year */
    private static final String EXPORT_MAX_YEAR = 
            "ae.gov.trf.vhl.ExportMaxYear";


    /** Get Driving Institue Activity Code */
    private static final String DRIVING_INSTITUE_ACTIVITY_CODE = 
                                                "ae.rta.vhl.drivingInstitueActivityCode";

    /** Get Driving Institue Service Code */
    private static final String DRIVING_INSTITUE_SERVICE_CODE =
                                                "ae.rta.vhl.drivingInstitueServiceCode";

    /** Get Driving Institue Center Code */
    private static final String DRIVING_INSTITUE_CENTER_CODE =
                                                "ae.rta.vhl.drivingInstitueCenterCode";
                                                
    /** Get Sms Allowed Special Characters */
    private static final String SMS_ALLOWED_SPECIAL_CHARACTERS = 
            "ae.rta.eps.proc.rlc.smsAllowedSpecialCharacters";

    /** Get Emirate Identity Allowed Center Channel Code */
    private static final String EMIRATES_IDENTITY_ALLOWED_CENTER_CHANNEL_CODE = 
                                "ae.rta.stp.emiratesIdentitiyAllowedCenterChannelCode";

    /** Emirates Identity Start Date for Residents */
    private static final String EMIRATES_IDENTITY_NO_PAPER_START_DATE =
                                            "ae.rta.stp.emiratesIdentitiyNoPaperStartDate";
    
    /** Enable Trade Permit */
    private static final String ENABLE_TRADE_PERMIT =
                                            "ae.rta.spl.enableTradePermit";

    /** special characters  */
    private static final String  SPECIAL_CHARACTERS =
                                            "ae.rta.common.specialCharacters";

    /** Allowed Insurance For Reserved Payment */
    private static final String ALLOWED_INSURANCE_FOR_RESERVED_PAYMENT =
                                "ae.rta.spl.allowedInsuranceForReservedPayment";
                                
    /** Allowed Payment Reservation Period */
    private static final String ALLOWED_PAYMENT_RESERVATION_PERIOD =
                                "ae.rta.spl.allowedPaymentReservationPeriod";
                                            
    /** UTS Reports Service End Point Url  */
    private static final String  UTS_REPORTS_SERVICE_END_POINT_URL =
                                            "ae.rta.uts.ws.UTSReportsServiceEndPointURL";
                                            
    /** Maximum Number Of Days From Traffic File Creation Date  */
    private static final String ISSUE_NEW_LICENSE_MAX_NO_OF_DAYS =
                                            "ae.rta.drl.IssueNewLicense.maxNoOfDaysFromTrafficCreationDate";
                                            
    /** UTS Reports Service End Point Url  */
    private static final String  USER_LOGIN =
                                            "ae.rta.report.userLogin";  
                                            
    /** UTS Reports Service User Name  */
    private static final String  UTS_REPORTS_SERVICE_USER_NAME =
                                            "ae.rta.uts.ws.UTSReportsServiceUserName";
                                            
    /** UTS Reports Service Password  */
    private static final String  UTS_REPORTS_SERVICE_Password =
                                            "ae.rta.uts.ws.UTSReportsServicePassword";                                            

    /** Black List cut off date */
    private static final String BLACK_LIST_CUTOFF_DATE = "ae.dp.spl.blackListCutoffDate";
    
    /** Active Trailer Enhancement */
    private static final String ACTIVE_TRAILER_ENHANCEMENT =
                                            "ae.rta.vhl.activeTrailerEnhancement";
    
    /** Capture Transaction Processor active */
    private static final String CAPTURE_TRANSACTION_PROCESSOR_ACTIVE = 
                            "ae.rta.trs.bus.deg.captureProcessorActive.active";
    
    /** Reverse Transaction Processor active */
    private static final String REVERSE_TRANSACTION_PROCESSOR_ACTIVE = 
                            "ae.rta.trs.bus.deg.reverseProcessorActive.active";
                            
    /** Capture Transaction Processor Delay */
    private static final String CAPTURE_TRANSACTION_PROCESSOR_DELAY = 
                            "ae.rta.trs.bus.captureTransactionProcessor.delay";
                            
    /** Reverse Transaction Processor Delay */
    private static final String REVERSE_TRANSACTION_PROCESSOR_DELAY = 
                            "ae.rta.trs.bus.reverseTransactionProcessor.delay";
                            
    /** Assessment Validity Period */
    private static final String ASSESSMENT_VALIDITY_PERIOD_DAYES 
                = "ae.rta.drl.mda.assessmentValidityPeriod";

    /** Max Charachters for Arabic Name */
    private static final String LICENSE_PRINT_MAX_ARABIC = "ae.rta.drl.printDrivingLicense.maxArabicCharacters";

    /** Max Charachters for English Name */
    private static final String LICENSE_PRINT_MAX_ENGLISH = "ae.rta.drl.printDrivingLicense.maxEnglishCharacters";    

    /** Min Charachters for Arabic Name */
    private static final String LICENSE_PRINT_MIN_ARABIC = "ae.rta.drl.printDrivingLicense.minArabicCharacters";

    /** Min Charachters for English Name */
    private static final String LICENSE_PRINT_MIN_ENGLISH = "ae.rta.drl.printDrivingLicense.minEnglishCharacters";     

    /** EWallet service provider id*/
    private static final String EWALLET_SERVICE_PROVIDER_ID
                                            = "ae.rta.esrv.common.eWalletSPID";

    /** EWallet FTF Channel Code */
    private static final String EWALLET_FTF_CHANNEL_CODE
                                            = "ae.rta.common.eWalletFTFChannelCode";

    /** EWallet Online Channel Code */
    private static final String EWALLET_ONLINE_CHANNEL_CODE
                                            = "ae.rta.esrv.common.eWalletOnlineChannelCode";

    /** EWallet Payment Active */
    private static final String EWALLET_PAYMENT_ACTIVE = "ae.rta.ewallet.payment.active";

    
    /** Cancel Vehicle Center Code */
    private static final String CANCEL_VEHICLE_CENTER_CODE = "ae.gov.trf.vhl.CancelVehicleCenterCode";
    
    /** UTS lookup info active thread */
    private static final String UTS_LOOKUP_INFO_THREAD_ACTIVE = "ae.rta.uts.bus.UTSLookupInfo.active";
    
    /** UTS lookup info delay thread */
    private static final String UTS_LOOKUP_INFO_THREAD_DELAY  = "ae.rta.uts.bus.UTSLookupInfo.delay";
    
    /** DI Cut Off Date */
    private static final String DI_CUT_OFF_DATE  = "ae.rta.idi.CuttOffDate";
    
    /** Excluded Pay And Present License Pay Types */
    private static final String EXCLUDED_PAY_AND_PERSENT_LICENSE_PAY_TYPES = "ae.rta.common.excludedPayAndPresentLicensePayTypes";

    /** Excluded Confiscate Vehicle Pay Types */
    private static final String EXCLUDED_CONFISCATE_VEHICLE_PAY_TYPES = "ae.rta.common.excludedConfiscateVehiclePayTypes";
    
    /** coloring Permit Deactivate message */
    private static final String COLORING_PERMIT_DEACTIVATE_MESSAGTE = "ae.rta.vhl.coloring.deactivate.message";
    
    /** Rental Car Notes Categories */
    private static final String RENTAL_CAR_NOTES_CATEGORIES = "ae.rta.cml.certificates.showRentalCarsNote.establishmentsActivities";
    

    /** Driving Institute Service End Point URL  */
    private static final String DRIVING_INSTITUTE_SERVICE_END_POINT_URL = "ae.rta.dtt.DrivingInstituteService.endPointURL";
    
    /** Driving Institute Service Thread Active  */
    private static final String DRIVING_INSTITUTE_SERVICE_THREAD_ACTIVE = "ae.rta.dtt.bus.DrivingInstituteServiceThread.active";
    
    /** Driving Institute Service Thread Delay In Seconds  */
    private static final String DRIVING_INSTITUTE_SERVICE_THREAD_DELAY = "ae.rta.dtt.bus.DrivingInstituteServiceThread.delay";
    

    /** Person Photo Validation Period in Years */
    private static final String PERSON_PHOTO_VALIDATION_PERIOD = "ae.rta.person.personValidationPeriod";
    
    /** Cut Off Date For P.O.BOX */
    private static final String CUT_OFF_DATE_P_O_BOX = "ae.rta.trs.cutOffDateForP_O_BOX";
    
    /** Allowed Channel For P_O_BOX */
    private static final String ALLOWED_CHANNEL_FOR_P_O_BOX = "ae.rta.trs.allowedChannelForP_O_BOX";
    
    /** vehicle sales transaction expiry period */
    private static final String VEHICLE_SALE_EXPIRY_PERIOD= "ae.rta.vhl.vehiclesales.expiryPeriod";
    

    /** Ewallet Reconciliation Processor start hour */
    private static final String EWALLET_PROCESSOR_START_HOUR = "ae.rta.trs.eWallet.processor.startHour";

    /** track start time */
    private static final String FEDEX_TRACK_START_TIME = "ae.rta.fedex.trackStartTime";
    
    /** Ewallet Reconciliation Processor start Minute */
    private static final String EWALLET_PROCESSOR_START_MINUTE = "ae.rta.trs.eWallet.processor.startMinute";
    
    /** Ewallet Reconciliation Processor Active Flag */
    private static final String EWALLET_PROCESSOR_ACTIVE_FLAG = "ae.rta.trs.eWallet.processor.active";
    
    /** Digital Pen Processor Active Flag */
    private static final String DIGITAL_PEN_PROCESSOR_ACTIVE_FLAG = "ae.rta.dtt.bus.digitalPenProcessor.active";
    
    /** Digital Pen Processor Delay */
    private static final String DIGITAL_PEN_PROCESSOR_DELAY = "ae.rta.dtt.bus.digitalPenProcessor.delay";
    
    /** Digital Pen Client End Point */
    private static final String DIGITAL_PEN_CLIENT_END_POINT = "ae.rta.dtt.bus.digitalPenClient.endPoint";

    /**Cml Nat Active Changes  */
    private static final String CML_NTA_ACTIVECHAGES = "ae.rta.cml.nta.ActiveChanges"; 

    /** Operation Logs Active */
    private static final String WS_OPERATION_LOGS_ACTIVE = "ae.rta.common.operationLogsActive"; 

    /** Rest Logs Active */
    private static final String WS_REST_LOGS_ACTIVE = "ae.rta.common.restLogsActive"; 
    
    /** Fedex Client End Point */
    private static final String FEDEX_END_POINT = "ae.rta.fedex.ws.client.endPoint.url"; 
    
    /** Allowed Preservation Expiry Period */
    private static final String ALLOWED_PRESERVATION_EXPIRY_PERIOD = "ae.rta.spl.trs.ChangePlatePreserver.allowedPreservationExpiryPeriod"; 
    
    /** Activate Business Rule Spl_050_014 */
    private static final String ACTIVE_BR_SPL_050_014 = "ae.rta.spl.activateBrSpl_050_014";
    
    /** old plates comparison date */
    private static final String OLD_PLATES_COMPARISON_DATE = "ae.rta.spl.oldPlatesComparisonDate";
    
    /** Electronic Insurance Phase2 Cut */
    private static final String ELECTRONG_INSURANCE_PHASE2_CUT_OFF_DATE = 
                "ae.traffic.vhl.eir.electronicInsurancePhas2CutOffDate";

    /** Deg Validation Services */
    private static final String DEG_STATUS_VALIDATION_SERVICES = "ae.rta.stp.degValidationServices";
    
    /** Driving Institute Service User Name */
    private static final String DRIVING_INSTITUTE_SERVICE_USER_NAME = "ae.rta.dtt.DrivingInstituteService.userName";
    
    /** Driving Institute Service Password */
    private static final String DRIVING_INSTITUTE_SERVICE_PASSWORD = "ae.rta.dtt.DrivingInstituteService.password";
    
    /** UTS Reports Web Service Enhanced Flag.*/
    private static final String UTS_REPORTS_SERVICE_ENHANCED_FLAG 
        = "ae.gov.dphq.traffic.ffu.cfi.reports.isEnhancedService";
    
    /** Disable/Enable Trace Log applicable table */
    private static final String APPLICABLE_TRACE_LOGS = "ae.rta.common.logging.disableAddTraceLog";

    /** Enable Trs Slowness Followup */
    private static final String ENABLE_TRS_SLOWNESS_FOLLOWUP = "ae.rta.common.enableTrsSlownessFollowup";
    
    /** Default Thermal Printer Name */
    private static final String Default_Thermal_Printer_Name = "ae.rta.bo.defaultThermalPrinterName";
    
    /** Fee Of Delayed Period For Heavy Vehicle */
    private static final String FEE_OF_DELAYED_PERIOD_FOR_HEAVY_VEHICLE = "ae.dp.ffu.feeOfDelayedPeriodForHeavyVehicle";
    
    /** approved Ftf Centers */
    private static final String APPROVED_FTF_CENTERS = "ae.rta.drl.approvedFtfCtr";
    
    /** Local Eye Test Customer Allowed Image Extension */
    private static final String LOCAL_EYE_TEST_CUSTOMER_ALLOWED_IMG_EXTENSION = "ae.rta.esrv.drl.localiseEyeTestResultPhotoType";
    
    
    /** Send Mail Delivery Information WS Timeout. */
    private static final String MAIL_DELIVERY_INFORMATION_TIME_OUT = "ae.rta.scd.ws.client.sendMailDeliveryInformation.timeout";

    /** Deg Skipped Codes */
    private static final String DEG_SKIPPED_CODES = "ae.rta.trs.degSkippedCodes";

    /** Manipulation Ticket Service End Point. */
    private static final String MANIPULATION_TICKET_SERVICE = "ae.gov.trf.ffu.ManipulationTicketService.url";
         
    /** Move EPS To Delivery Step Processor Start Time */
    private static final String MOVE_EPS_TO_DELIVERY_STPE_PROCESSOR_STRAT_TIME = "ae.rta.common.bus.moveEPSToDeliveryStepProcessor.startTime";
    
    /** Local Eye Test Customer Allowed Image Size */
    private static final String LOCAL_EYE_TEST_CUSTOMER_ALLOWED_IMG_SIZE = "ae.rta.esrv.drl.localiseEyeTestResultPhotoSize";
    
    /** Manual Max Attach Size */
    private static final String MANUAL_MAX_ATTACH_SIZE = "ae.rta.common.manualMaxAttachSize";
    
    /** Is Br_PLT021_MER enabled flag property */
    private static final String IS_BR_PLT021_MER_ENABLED_FLAG = "ae.rta.spl.enableBrPlt021_MER";
    
    /** Is Br_PLT022_MER enabled flag property */
    private static final String IS_BR_PLT022_MER_ENABLED_FLAG = "ae.rta.spl.enableBrPlt022_MER";
    
    /** Approved FTF Centers */
    private static final String APPROVED_VHL_FTF_CTR = "ae.rta.vhl.approvedFtfCtr";

    /** Dubai Health Authority CTR ID */
    private static final String DUBAI_HEALTH_AUTHORITY_CTR_ID = "ae.rta.drl.mdc.dubaiHealthAuthorityCTRID";
    
    /** Clearance Certificate Delay Fee */
    private static final String CLEARANCE_DELAY_FEE = "ae.ffu.vcn.clearanceCertficateDelayFee";
    
    /** Apply Medical Assessment Renewal license */
    private static final String APPLY_MEDICAL_ASSESSMENT_RENEWAL_LICENSE = "ae.rta.dtt.applyMedicalAssessmentTestOnSvcCode103";
    
    /** Apply Medical Assessment Modify Classes */
    private static final String APPLY_MEDICAL_ASSESSMENT_MODIFY_CLASSES = "ae.rta.dtt.applyMedicalAssessmentTestOnSvcCode4";

    /** Apply Medical Assessment Renewal Learn Permits  */
    private static final String APPLY_MEDICAL_ASSESSMENT_RENEWAL_LEARN_PERMITS = "ae.rta.dtt.applyMedicalAssessmentTestOnSvcCode5";
    
    /** Apply Medical Assessment License Applications  */
    private static final String APPLY_MEDICAL_ASSESSMENT_LICENSE_APPLICATIONS = "ae.rta.dtt.applyMedicalAssessmentTestOnSvcCode1";
    
    /** Apply Medical Assessment Issue Learning Permits  */
    private static final String APPLY_MEDICAL_ASSESSMENT_ISSUE_LEARNING_PERMITS = "ae.rta.dtt.applyMedicalAssessmentTestOnSvcCode2";
    
    /** Apply Medical Assessment Women Permit  */
    private static final String APPLY_MEDICAL_ASSESSMENT_WOMEN_PERMIT = "ae.rta.dtt.applyMedicalAssessmentTestOnSvcCode3";
    
    /** Apply Medical Assessment Reissuel Learn Permits  */
    private static final String APPLY_MEDICAL_ASSESSMENT_REISSUEL_LEARN_PERMITS = "ae.rta.dtt.applyMedicalAssessmentTestOnSvcCode6";
    
    /** Number Of Black Points Per Course */
    private static final String NUMBER_OF_CANCELED_BLACK_POINTS_PER_COURSE = "ae.dp.ffu.noOfBlackPointsPerCourse";        
    
    /** Vehicle Confiscation Max Upload Size */
    private static final String VCN_MAX_UPLOAD_SIZE = "ae.dp.ffu.vcn.maxAttSize";
    
    /** Search For Booked Veh Msg */
    private static final String SEARCH_BOOKED_VEH_MSG = "ae.ffu.dp.searchForBookedVehMsg";
     
    /** Not Allowed Service To cash Payment */
    private static final String NOT_ALLOWED_SERVICE_CODE_CASH_PAYMENT = "ae.rta.stp.notAllowedServiceCode";
    
    /** Min Allowed Registered Vehilce to Cash payment */
    private static final String MIN_ALLOWED_REGISTERED_VEHICLE = "ae.rta.stp.minAllowRegisteredVehicle";
    

    /** Initial NOC template Codes */
    private static final String INITIAL_NOC_TEMPLATE_CODES ="ae.rta.cml.InitialNocTemplateCodes";

    /** Allowed Center To Issue Releas Order */
    private static final String ALLOWED_CENTER_TO_ISSUE_RELEAS_ORDER = "ae.dp.ffu.allowedCenterToIssueReleasOrder";

    /** active Pearson project tab in Dtt */
    private static final String ACTIVE_PEARSON_PROJECT = "ae.rta.dtt.activatePearsonProject";
    

    /** Delegation PassWord Reset Max Usage */
    private static final String DELEGATION_PASSWORD_RESET_MAX_USAGE = "ae.rta.trs.delegationPassWordResetMaxUsage";
    

    /** RTA Receipt Header Image Name.*/
    private static final String RTA_RECEIPT_HEADER_IMAGE_NAME = "ae.rta.stp.rtaReceiptHeaderImageName";
    
    /** list of channel codes for channels that pays in 3D.*/
    private static final String RTA_TRS_RTA_3D_CHANNEL_CODES = "ae.rta.trs.rta3DChannelCodes";
    
    /** Original exam time per second */
    private static final String RTA_DTT_ORIGINAL_EXAM_TIME_PER_SECOND = "ae.rta.dtt.originalExamTimePerSeconds";
    
    /** Warning Milli Seconds.*/
    private static final String RTA_DTT_WARNING_MILLI_SECONDS = "ae.rta.dtt.rtaDttWarningMilliSeconds";

    /** RTA DTT Theorytest Refresh Millis*/
    private static final String RTA_DTT_THEORYTEST_REFRESH_MILLIS= "ae.rta.dtt.theorytest.refreshMillis";
    
    /** Warning Expiry Time Per Second */
    private static final String RTA_DTT_WARNING_EXPIRY_TIME_PER_SECOND = "ae.rta.dtt.warningExpiryTimePerSecond";

    /** Not A Manager Codes */
    private static final String NOT_A_MANAGER_CODES = "ae.rta.cml.dedPartnerLegalTypes.notAManagerCodes";

    
    /** Mortgage BlackList Cutoff Date*/
    private static final String MORTGAGE_BLACKLIST_CUTOFF_DATE  ="ae.rta.vhl.activateMortgageBlackListCutoffDate";
    
    /** Successfull License Application Validity Months */
    private static final String  SUCCESSFULL_LICENSE_APPLICATION_VALIDITY_MONTH = "ae.rta.drl.successfulLicenseApplicationValidityMonths";
    
    /** Cis Check Url*/
    private static final String CIS_CHECK_URL  ="ae.rta.esrv.vhl.cisCheckUrl";

    /** Number of allowed receipt updates */
    private static final String NUMBER_OF_ALLOWED_RECEIPT_UPDATES = "ae.rta.setup.numberOfAllowedReceiptUpdates";
    
    /** Cis Manual Certificate Scan Path*/
    private static final String CIS_MANUAL_CERT_SCAN_PATH  ="ae.rta.esrv.vhl.cisManualCertScanPath";

    /** Booklet Extended Expiry Period */
    private static final String BOOKLET_EXTENDED_EXPIRY_PERIOD_IN_MONTHS = "ae.rta.trs.bookletExtendedExpiryPeriodInMonths";
    
    /** Vcc User Name */
    private static final String VCC_USER_NAME = "ae.rta.vcc.username";
    
    /** Vcc Password */
    private static final String VCC_PASSWORD = "ae.rta.vcc.password";
    
    /** auto Distribute Exception Request */ 
    private static final String AUTO_DISTRIBUTE_EXCEPTION_REQUEST = "ae.rta.eps.autoDistributeExceptionRequest";
    
    /** start Night Shift*/ 
    private static final String START_NIGHT_SHIFT = "ae.rta.eps.startNightShift";
    
    /** end Night Shift */ 
    private static final String END_NIGHT_SHIFT = "ae.rta.eps.endNightShift";
    
    /** approved Templates */ 
    private static final String NOT_ASSIGNED_APPROVED_TEMPLATES = "ae.rta.eps.notAssignedApprovedTemplates";
    
    /** vhl activate Exception System  */ 
    private static final String VHL_ACTIVATE_EXCEPTION_SYSTEM  = "ae.rta.vhl.activateExceptionSystem";
    
    /** dtt activate Exception System  */ 
    private static final String DTT_ACTIVATE_EXCEPTION_SYSTEM  = "ae.rta.dtt.activateExceptionSystem";
    
    /** spl activate Exception System  */ 
    private static final String SPL_ACTIVATE_EXCEPTION_SYSTEM  = "ae.rta.spl.activateExceptionSystem";
    
    /** drl activate Exception System  */ 
    private static final String DRL_ACTIVATE_EXCEPTION_SYSTEM  = "ae.rta.drl.activateExceptionSystem";
    
    /** cml activate Exception System  */ 
    private static final String CML_ACTIVATE_EXCEPTION_SYSTEM = "ae.rta.cml.activateExceptionSystem";
    
    /** FEE OF CRANE FOR HEAVY VEHICLE IDS */
    private static final String  HEAVY_VEHICLE_CLASSES_IDS="ae.dp.ffu.heavyVehicleClassesIds";
 
    /** RTA DTT Exam Start Refresh Millis*/
    private static final String RTA_DTT_EXAM_START_REFRESH_MILLIS= "ae.rta.dtt.examStart.refreshMillis";

    /** Sddi Esrv Host */
    private static final String SDDI_ESRV_HOST = "ae.rta.sddi.esrv.host";
    
    /** Black Points Course Location Arabic. */
    private static final String BLACK_POINTS_COURSE_ARABIC_LOCATION = "ae.rta.ffu.fines.blackPointsCourseLocationA";

    /** Black Points Course Location English. */
    private static final String BLACK_POINTS_COURSE_ENGLISH_LOCATION = "ae.rta.ffu.fines.blackPointsCourseLocationE";

    /** cutoff date of employees */
    private static final String CUTOFF_DATE_OF_EMPLOYEES = "ae.rta.security.web.cutoffDateOfEmployees";
    
    /** skipped sensitive urls */
    private static final String SKIPPED_SENSITIVE_URLS = "ae.rta.security.web.skippedSensitiveUrls";
    
    /** Renew license minimum person's age */
    private static final String RENEW_LICENSE_MINIMUM_PERSON_AGE = "renew.license.minimum.person.age";
    
      /**Flag to toggle the necessity of a vehicle inspection before a sales transaction */
    private static final String INSPECTION_BEFORE_SALES_TRANSACTION = "ae.traffic.vhl.eir.inspectionBeforeSalesTransaction";
    
    /** default Support Center*/
    private static final String  DEFAULT_CENTER_CODE = "traffic.defaultSupportCenter";
    
    /** Person Age Need Parents Info */
    private static final String PERSON_AGE_NEED_PARENTS_INFO = "ae.rta.stp.prs.need.parentsInfo.age";
    
    /** Active Parent Notification */
    private static final String ACTIVE_PARENT_NOTIFICATION = "ae.rta.ntf.active.parent.notification";  
    
    /** Send Receipt By Email */
    private static final String SEND_RECEIPT_BY_EMAIL = "ae.rta.ntf.send.receiptByEmail";     
                                                        
     /** Decoupling flag between Vehicle Sales transaction and after sales */
    private static final String IS_COUPLED_SALES_TRANSACTION = "ae.traffic.vhl.eir.coupledSalesTransaction";

    /** unlink services activation flag */
    private static final String UNLINK_SERVICES_ACTIVATION_FLAG = "ae.rta.dtt.unlinkServices.active";   
    
    /** skip Br Trs013 Mer For Positive */
    private static final String SKIP_BR_TRS013_MER_FOR_POSITIVE = "ae.rta.stp.skipBrTrs013MerForPositive";

    /** Military number of employees loaned to RTA */
    private static final String EMPLOYEES_MILITARY_NO_LOANED_TO_RTA = "ae.rta.ffu.military.no.loaned.to.rta";  
    
    /** Substitutable Licence Classes */
    private static final String SUBSTITUTABLE_LICENSE_CLASSES = "ae.rta.drl.substitutableLicenceClasses";

    /** Enable And Disable E-Traffic Threads Property: The Default Value False. */
    private static final String ENABLE_E_TRAFFIC_THREADS = "ae.rta.common.enableETrafficThreads";
    
    /** Issue License Classes From Test Approved Channels */
    private static final String ISSUE_LICENSE_CLASSES_FROM_TEST_APPROVED_CHANNELS = "ae.rta.drl.issueNewLicenseClassesFromTest.approvedChannels";

    /** Issue License Classes From Replacement Approved Channels */
    private static final String ISSUE_LICENSE_CLASSES_FROM_REPLACEMENT_APPROVED_CHANNELS = "ae.rta.drl.issueNewLicenseClassesFromReplacement.approvedChannels";

    /** Allowed Users For View Tickets Elements Property. */
    private static final String ALLOWED_USERS_FOR_VIEW_TICKETS_ELEMENTS = 
                                  "ae.rta.trs.viewTicketsElements.allowedUsers";

    /** FFU Transaction Service End Point */
    private static final String FFU_TRANSACTION_SERVICE = "ae.rta.ffu.transaction.service.endPoint";
    
    /** Enable and disable XSS remove  filter */
    private static final String ENABLE_XSS_REMOVE_FILTER= "ae.rta.util.enableXSSRemoveFilter";

    /** Issue Permit Category Allowed Eps Code. */
    private static final String ISSUE_PERMIT_CATEGORY_ALLOWED_EPS_CODE = "ae.rta.stp.issuePermitCategory.allowedEpsCode";
    
    /** Flag used to couple the registeration screen with vechile sales transaction */
    private static final String IS_REGISTERATION_COUPLED_TO_SALES_TRANSACTION = "ae.traffic.vhl.eir.registerationCoupledToSalesTransaction";
    
        /** Flag used to couple the possession screen into vechile sales transaction */
    private static final String IS_POSSESSION_COUPLED_TO_SALES_TRANSACTION = "ae.traffic.vhl.eir.possessionCoupledToSalesTransaction";
    
        /** Flag used to couple the ownership transfer screen into vechile sales transaction */
    private static final String IS_TRANSFER_OWNERSHIP_COUPLED_TO_SALES_TRANSACTION = "ae.traffic.vhl.eir.ownershipCoupledToSalesTransaction";

    
    /** Flag used to couple the export screen into vechile sales transaction */
    private static final String IS_EXPORT_COUPLED_TO_SALES_TRANSACTION = "ae.traffic.vhl.eir.exportCoupledToSalesTransaction";
    
    /** Flag used to decouple the transfer screen into vechile sales transaction */
    private static final String IS_TRANSFER_TO_ANOTHER_EMIRATE_COUPLED_TO_SALES_TRANSACTION = "ae.traffic.vhl.eir.transferCoupledToSalesTransaction";
    
    /** Flag used to couple the transfer possession screen into vechile sales transaction */
    private static final String IS_TRANSFER_POSSESSION_COUPLED_TO_SALES_TRANSACTION = "ae.traffic.vhl.eir.transferPossessionCoupledToSalesTransaction";
    
    /** moving Eps Of Modify Permit App Task minutes */
    private static final String MOVING_EPS_OF_MODIFY_PERMIT_APP_TASK_MINUTES = "ae.rta.cml.pmt.bus.tasks.movingEpsOfModifyPermitAppTask.minutes";
    
    /** moving Eps Of Modify Permit App Task active */
    private static final String MOVING_EPS_OF_MODIFY_PERMIT_APP_TASK_ACTIVE = "ae.rta.cml.pmt.bus.tasks.movingEpsOfModifyPermitAppTask.active";

    /** Protal Profile Service End Point */
    private static final String PORTAL_PROFILE_SERVICE_END_POINT ="ae.rta.mst.PortalProfileService.endPoint";
    
    /** Card Image Service End Point */
    private static final String CARD_IMAGE_SERVICE_END_POINT = "ae.rta.mst.CardImageService.endPoint";
    
    /** User Profile User Name */
    private static final String PORTAL_PROFILE_SERVICE_USER_NAME ="traffic.PortalProfileService.userName";
    
    /** User Profile Passwrod */
    private static final String PORTAL_PROFILE_SERVICE_PASSWORD ="traffic.PortalProfileService.password";
    
    /** Car Registration Card Layout Name */
    private static final String CARD_REGISTRATION_CARD_LAYOUT_NAME ="car.registration.card.layout.name";
    
    /** Driving License Layout Name */
    private static final String DRIVING_LICENSE_LAYOUT_NAME ="driving.License.layout.name";
    
    /** Private Vehicle Certification Layout Name */
    private static final String PRIVATE_VEHICLE_CERTIFICATE_LAYOUT_NAME ="private.vehicle.certificate.layout.name";
    
    /** Motorcycle Certificate Layout Name */
    private static final String MOTORCYCLE_CERTIFICATE_LAYOUT_NAME ="motorcycle.certificate.layout.name";
    
    /** Enertainment Motor Layout Name */
    private static final String ENTERTAINMENT_MOTOR_CERTIFICATE_LAYOUT_NAME="entertainment.motor.certificate.layout.name";
    
    /** Classic Car Layout Name */
    private static final String CLASSIC_CAR_CERTIFICATE_LAYOUT_NAME="classic.car.certificate.layout.name";
    
    /** Image Card User Name */
    private static final String IMAGE_CARD_SERVICE_USER_NAME ="traffic.ImageCardService.userName";
    
    /** Image Card Passwrod */
    private static final String IMAGE_CARD_SERVICE_PASSWORD ="traffic.ImageCardService.password";
    /** Activate Session Management */
    private static final String ACTIVATE_SESSION_MANAGEMENT = "ae.rta.trs.activateSessionManagement";

    /** Activate Session Management Login Forward */
    private static final String ACTIVATE_SESSION_MANAGEMENT_LOGIN_FORWARD = "ae.rta.trs.activateSessionManagementLoginForward";    

    /** Direct Selling Auction Notification Message Body **/
    private static final String DIRECT_SELLING_AUCTION_NOTIFICATION_MESSAGE_BODY = "ae.rta.spl.plt.bus.lcd.group.notification.body";

    /** Direct Selling Auction Notification Message Subject **/
    private static final String DIRECT_SELLING_AUCTION_NOTIFICATION_MESSAGE_SUBJECT = "ae.rta.spl.plt.bus.lcd.group.notification.subject";    

    /** pro Allowance Date */
    private static final String PRO_ALLOWANCE_DATE = "ae.rta.pmt.proAllowanceDate";
    
    /** pro Minimum Number Allowed Vehicle */
    private static final String PRO_MINIMUM_NUMBER_ALLOWED_VEHICLE = "ae.rta.pmt.proMinimumNumberAllowedVehicle";    

    /** Active Send Push Notification */
    private static final String ACTIVE_SEND_PUSH_NOTIFICATION = "ae.rta.ntf.pushNotification.activeSendPushNotification";
    /** Epay User Name */
    private static final String EPAY_SERVICE_USER_NAME ="traffic.epay5.userName";
    
    /** Epay Password */
    private static final String EPAY_SERVICE_PASSWORD="traffic.epay5.password";
    
    /** Number of days to allowed complete loss damage exports certificate. */
    private static final String NUMBER_OF_DAYS_ALLOWED_TO_COMPLETE_LOSS_DMG_EXPORTS_CERTIFICATE 
                                                            = "ae.rta.vhl.lossDmgExports.numberOfDaysToAllowedCompleteLossDmgExportsCertificate";
    

    /** Epay End Point */
    private static final String EPAY_SERVICE_END_POINT ="ae.rta.mst.epay5.endPoint";
    
    /** Change Ownership Owned Plate Status Values */
    public static final String CHANG_OWNERSHIP_OWNED_PLATE_STATUS_VALUES = "ae.rta.vhl.ownedChangeOwnershipPLTTRSStatusValues";

    /** Minimum Minute To Send Notification. */
    private static final String MINIMUM_MINUTE_TO_SEND_NOTIFICATION = "ae.rta.spl.lcd.minimumMinuteToSendNotification";

    /** Issue Permit Allowed Permit Period. */
    private static final String ISSUE_PERMIT_ALLOWED_PERMIT_PERIOD = "ae.rta.spl.trs.issuePermit.allowedPermitPeriod";

    /** Change Ownership Not Owned Plate Status Values */
    public static final String CHANG_OWNERSHIP_NOT_OWNED_PLATE_STATUS_VALUES = "ae.rta.vhl.changeOwnershipPlateTRSStatusValues";    

    /** Change Ownership NOC Requet Validity Period */
    public static final String CHANGE_OWNERSHIP_NOC_PERIOD = "ae.rta.esrv.vhl.ChangeOwnershipNoc.requetValidityPeriod";

    /** Skip PRO Permit Person Age Validation */
    private static final String TF_STP_SKIP_PRO_PERMIT_PERSON_AGE_VALIDATION = "ae.rta.stp.skipProPermitPersonAgeValidation";
    
    /** Enable Validate Set Avaiable Delivery */
    private static final String ENABLE_VALIDATE_SETAVAIABLE_DELIVERY ="ae.rta.trs.enableValidateSetAvaiableDelivery";
    
    /** Min Allowed Year Registertion */
    private static final String MIN_ALLAOWED_YEAR_REGISTERATION ="ae.rta.stp.minAllowedYearRegistertion";

    /** Renew Permit Allowed Permit Period. */
    private static final String RENEW_PERMIT_ALLOWED_PERMIT_PERIOD = "ae.rta.spl.trs.renewPermit.allowedPermitPeriod";    
    
    /** Plate Package VO Arabic Description Default Label. */
    private static final String PLATE_PACKAGE_DESC_AR_LABEL = "ae.rta.spl.stp.vo.packageVO.descriptionAr.defaultLabel";
    
    /** Plate Package VO English Description Default Label. */
    private static final String PLATE_PACKAGE_DESC_EN_LABEL = "ae.rta.spl.stp.vo.packageVO.descriptionEn.defaultLabel";
    
    /** Electonic Auction Package VO Arabic Description Default Label. */
    private static final String AUCTION_PACKAGE_DESC_AR_LABEL = "ae.rta.spl.act.vo.auctionVO.descriptionAr.defaultLabel";
    
    /** Electonic Auction Package VO English Description Default Label. */
    private static final String AUCTION_PACKAGE_DESC_EN_LABEL = "ae.rta.spl.act.vo.auctionVO.descriptionEn.defaultLabel";    
    
    
    /** Get Permit Details Service Endpoint */
    private static final String SEND_NOTIFICATION_SERVICE_ENDPOINT = 
            "ae.rta.ntf.sendNotificationService.endpoint";

    /** TRAINING_AUDIT_CENTER_CODE */
    private static final String TRAINING_AUDIT_CENTER_CODE = "ae.rta.trs.trainingAuditingCenterCode";

    /** Operation Logs Disabled For (Services Names) */
    private static final String OPERATION_LOGS_DISABLED_FOR = "ae.rta.common.operationLogsDisabledFor";
 
    /** Operation Logs Enabled For (Services Names) */
    private static final String OPERATION_LOGS_ENABLED_FOR = "ae.rta.common.operationLogsEnabledFor";

    /** Enable issueExamAppoint BR_IEA019 */
    private static final String ENABLE_ISSUE_EXAM_APPOINT_BR_IEA019 = "tf.rta.dtt.issueExamAppoint.enableBrIEA019";            
    
    /** Enable issueExamAppoint BR_IEA019 */
    private static final String ENABLE_ISSUE_EXAM_APPOINT_BR_CEA024 = "tf.rta.dtt.changeExamAppoint.enableBrCEA024";            
    
    /**Enable SCRF attacks handling*/
    private static final String ENABLE_CSRF_ATTACKS_HANDLING = "ae.rta.traffic.security.csrf.handling.enabled";

    /**Enable SCRF attacks handling*/
    private static final String ENABLE_SMART_FILTERS = "ae.rta.smart.web.filter.FilterStartupListener";

    /** ACTIVATE_RENEW_INSTRUCTOR_PERMIT */
    private static final String ACTIVATE_RENEW_INSTRUCTOR_PERMIT = "ae.rta.trs.activateRenewInsPermit";

    /** Enable Circular Note Service */
    private static final String ENABLE_CIRCULAR_NOTE_SERVICE = "ae.rta.common.enableCircularNoteService";
    
    /** Service Delivery Proccessor Delay */
    private static final String SERVICE_DELIVARY_PROCCESSOR_DELAY = "ae.rta.serviceDeliveryProccessor.delay";
    
    /** Service Delivery Proccessor Enabled */
    private static final String SERVICE_DELIVARY_PROCCESSOR_ENABLED = "ae.rta.serviceDeliveryProccessor.enabled";
    
    /** Service Delivery Proccessor Max Trials */
    private static final String SERVICE_DELIVARY_PROCCESSOR_MAX_TRIALS = "ae.rta.serviceDeliveryProccessor.maxTrials";
    
    /** Service Delivery Proccessor Period */
    private static final String SERVICE_DELIVARY_PROCCESSOR_PERIOD = "ae.rta.serviceDeliveryProccessor.period";
   
    /**Appointment Service */
    private static final String PAYMENT_CLEARANCE_SERVICE_URL =
        "ae.rta.esrv.dtt.ws.pce.client.PaymentClearanceService.url"; 
    
    /** Smart Screen User */
    private static final String SMART_SCREEN_USER =
        "dtt.smartScreen.username"; 
        

    /** Is operation logs filter enabled */  
    private static final String IS_OPERATION_LOG_FILTER_ENABLED    = "ae.rta.common.OperationLogFilterEnabled";
    
    /** enable Task SDDI 6325 */
    private static final String ENABLE_TASK_SDDI_6325 = "enableTask.SDDI-6325";
    
    /** Allow Register Polaris Vehicle */
    private static final String ALLOW_REGISTER_POLARIS_VEHICLE = "allow.register.polaris.vehicle";

    /** enable update info sending mail */
    private static final String ENABLE_UPDATE_INFO_MAIL_NOTIFICATION= "enableTask.TRF-24914";

    /** ENABLE TASK TRF-16517*/
    private static final String ENABLE_TASK_TRF_16517  = "enableTask.TRF-16517";
    
    /** enable task  TRF-1750*/
    private static final String IS_MORTGAGED_ORG_PLATE_CHANGED = "enableTask.TRF-17501";    
    
    /** Is Checking License Application Traning DI Info Active */
    private static final String IS_CHECKING_TRANING_DI_INFO_ACTIVE = "ae.rta.dtt.isCheckingTraningDIInfoActive";
    
    /** Max Days For Examiner To Search */  
    private static final String MAX_DAYS_FOR_EXAMINER_TO_SEARCH = "ae.rta.ddt.maxDaysForExaminerToSearch";
        
    /** Monitoring Stock Of Drivers Handbooks Refresh Period */  
    private static final String MONITORING_STOCK_OF_DRIVERS_HANDBOOKS_REFRESH_PERIOD = "ae.rta.dtt.monitoringStockOfDriversHandbooks.refreshPeriod";    

    /** CHECK_BR_RLC012_CHANNEL_CODES */
    private static final String CHECK_BR_RLC012_CHANNEL_CODES = "ae.rta.trs.drl.checkBRRLC012ChaneelCodes";

        
    /** Driving Institute sending booking details properties */
    private static final String DRIVING_INSTITUTE_BOOKING_DETAILS_SERVICE_EP = "ae.rta.dtt.ws.tibco.client.drivinginstituteservice.ep";
    private static final String DRIVING_INSTITUTE_BOOKING_DETAILS_SERVICE_USERNAME = "ae.rta.dtt.ws.tibco.client.drivinginstituteservice.username";
    private static final String DRIVING_INSTITUTE_BOOKING_DETAILS_SERVICE_PASSWORD = "ae.rta.dtt.ws.tibco.client.drivinginstituteservice.password";
    private static final String DRIVING_INSTITUTE_BOOKING_DETAILS_SERVICE_ENABLED = "enable-TRF-33139";
    private static final String DRIVING_INSTITUTE_WARNING_MESSAGES_SERVICE_ENABLED = "enable-TRF-37781";
    
    /** enable task  TRF-26663*/
    /** Enable task  TRF-26663*/
    private static final String IS_TASK_TRF_26663_ENABLED = "enableTask.TRF-26663";
    
    /** Enable task  TRF-27901*/
    private static final String IS_TASK_TRF_27901_ENABLED = "enableTask.TRF-27901";
    
    /** Enable Task TRF-17182 */  
    private static final String ENABLE_TASK_TRF_30922 = "enableTask.TRF-30922";

    /** new Vehicles Should Be Tested */
    private static final String NEW_VEHICLE_SHOULD_BE_TESTED = "ae.rta.vhl.possessUnRegVehicle.newVehiclesShouldBeTested";

    private static final String Enable_BR_TRS202_MER = "ae.rta.enable.br_trs202_mer";

    /** Minimum Registration date for exam appointment t=thread */
    private static final String MIN_REG_DATE_EXAM_APPT_THREAD = "ae.rta.dtt.CustomerJourney.ExamAppointmentProcessor.minRegDate";
    
    private static final String DATE_EXAM_APPT_RANGE = "ae.rta.dtt.CustomerJourney.ExamAppointmentProcessor.daysRange";
       
    /** Trial IDs to be processed */
    private static final String TRIAL_IDS_EXAM_APPT_THREAD = "ae.rta.dtt.CustomerJourney.ExamAppointmentProcessor.trialIds";
    
    /** Minimum Hour for ExamAppointmentProcessor */
    private static final String MIN_START_HOUR_EXAM_APPT_THREAD = "ae.rta.dtt.CustomerJourney.ExamAppointmentProcessor.minHour";
    
    /** Enable Task TRF-20683 */  
    private static final String ENABLE_TASK_TRF_20683 = "enableTask.TRF-20683";
    
    /** Min. No. of days to be shifted by thread t=thread */
    private static final String NO_RESCHEDULE_DAYS_EXAM_APPT_THREAD = "ae.rta.dtt.CustomerJourney.ExamAppointmentProcessor.rescheduleAfterDays";
    
    /** Min. No. of days to be shifted */
    private static final String NO_RESCHEDULE_DAYS_EXAM_APPT = "ae.rta.dtt.appointmentRescheduleView.rescheduleAfterDays";
    
    /** ENABLE TASK TRF-34966*/
    private static final String ENABLE_TASK_TRF_34966  = "enableTask.TRF-34966";
    
    /** Max Days For Stock To Search */  
    private static final String MAX_DAYS_FOR_STOCK_TO_SEARCH = "ae.rta.ddt.maxDaysForStockToSearch";
      

    /** Enable task  TRF-26846*/
    private static final String ENABLE_TASK_TRF_35380 = "enableTask.TRF-35380";    

    /** ACTIVATE_TWO_SESSIONS_VALIDATION */
    private static final String ACTIVATE_TWO_SESSIONS_VALIDATION = "ae.rta.trs.activateTwoSessionsValidation";
    
    /** Enable Transactions check status */
    private static final String ENABLE_TRANSACTION_CHECK_STATUS = "tf.stp.enabled.check.trnasctions.status";
    
    /** Data Validation Mobiles property */
    private static final String DATA_VALIDATION_MOBILES = "ae.rta.ntf.dataValidationCases.mobiles";
    
    /** Appoitment Google Map Key. */
    private static final String APPOITMENT_GOOGLE_MAP_KEY
                = "ae.rta.esrv.apt.google.map.key";

    /** CHECK_REGISTER_VEHICLE_CLASS */
    private static final String CHECK_REGISTER_VEHICLE_CLASS = "ae.rta.vhl.checkRegisterVehicleClass";    
   
    /** enable task  TRF-36583 revamp print receipt page */
    private static final String IS_TASK_TRF_36583_ENABLED = "enableTask.TRF-36583";
    
    /** enable task  TRF-34291 revamp print receipt page */
    private static final String IS_TASK_TRF_34291_ENABLED = "enableTask.TRF-34291";
    
    /** enable task TRF-36647 */
    private static final String IS_TASK_TRF_36647_ENABLED = "enableTask.TRF-36647";
    
    /** Enable Task TRF-36634 **/
    private static final String IS_TASK_TRF_36634_ENABLED = "enableTask.TRF-36634";	    
    
    /** Enable Task TRF-44961 **/
    private static final String IS_TASK_TRF_44961_ENABLED = "enableTask.TRF-44961";         
    
    /** Required Vehicle Classes for commercial permits */
    private static final String REQUIRED_VEHICLE_CLASSES_FOR_COMMERCIAL_PERMITS = "ae.rta.stp.specialPermits.requiredVehicleClassesIdsForComercialPermits";
    
    /** Allowed Allocating Plates Period */
    private static final String ALLOWED_ALLOCATING_PLATES_PERIOD = "ae.rta.spl.allowedAllocatingPlatesPeriod";
    
    /** Number Of Allowed FTF Booklets Reprints In Same Day */
    private static final String NUMBER_OF_ALLOWED_FTF_BOOKLETS_REPRINTS_IN_SAME_DAY = "ae.rta.vhl.numberOfAllowedFTFBKTReprintsInSameDay";

    /** Number Of Allowed FTF Certificates Reprints In Same Day */
    private static final String NUMBER_OF_ALLOWED_FTF_CERTIFICATES_REPRINTS_IN_SAME_DAY = "ae.rta.vhl.numberOfAllowedFTFCertificatesReprintsInSameDay";
    
    /** Max Image Size For Person */
    private static final String MAX_IMAGE_SIZE_FOR_PERSON = "ae.rta.common.maxImageSizeForPerson";        
    
    /** OURISEM_CLEARANCE_CUTT_OFF_DATE*/
    private static final String TOURISEM_CLEARANCE_CUTT_OFF_DATE = "ae.rta.dtt.TourisimClearance.cutOffDate";    
    
    private static final String ENABLE_TASK_TRF_38814_1= "enableTask.TRF-38814_1";  
    
    private static final String ENABLE_TASK_TRF_38814_2= "enableTask.TRF-38814_2";  
    
    /** enable task  TRF-36639*/
    private static final String IS_TASK_TRF_36639_ENABLED = "enableTask.TRF-36639";
        
 
    /** Enable task  TRF-38698*/
    private static final String ENABLE_TASK_TRF_38698 = "enableTask.TRF-38698";  
    
    /** enable task  TRF-31779 pay confication through WS*/
    private static final String IS_TASK_TRF_31779_ENABLED = "enableTask.TRF-31779";
	
    /** Franchise Contract Traffic No */

    private static final String FRANCHISE_CONTRACT_TRAFFIC_NO = "cml.contracts.FranchiseContractTrafficNo";
	
    
    /** RTA DTT Change Device Refresh Peroid */
    private static final String RTA_DTT_CHANGE_DEVICE_REFRESH_PEROID = "ae.rta.dtt.changeDevice.refreshPeroid";
    
    /** RTA DTT Change Device Refresh Peroid */
    private static final String RTA_DTT_EXAM_FINISHED_REFRESH_PEROID = "ae.rta.dtt.examFinished.refreshPeroid";
    
    /** RTA DTT Touch Message Screen Time out*/
    private static final String RTA_DTT_TOUCH_MESSAGE_SCREEN_TIME_OUT = "ae.rta.dtt.examTest.messageTimeOut";
    
    /** enable task  TRF-35671*/
    private static final String IS_TASK_TRF_35671_ENABLED = "enableTask.TRF-35671";    
	
    /** Enable task  TRF-24652/ */
    private static final String ENABLE_TASKE_TRF_24652 = "enableTask.TRF-24652";

    /** Enable Task TRF-37510 */
    private static final String ENABLE_TASK_TRF_37510_DATE = "enableTask.TRF-37510_Date";    
    
    /** ENABLE_TRF-48302  */
    private static final String  ENABLE_TASK_TRF48302 = "enableTask.TRF-48302"; 
    
    /** Daemon Thread pool size */
    private static final String DAEMON_THREAD_POOL_SIZE = "ae.rta.job.Scheduler.daemonThreadPoolSize";
    
    /** User Thread pool size */
    private static final String USER_THREAD_POOL_SIZE = "ae.rta.job.Scheduler.userThreadPoolSize";
    
    /** Enable Chassis Classes */
    private static final String ENABLED_CHASSIS_CLASSES = "ae.rta.vhl.registerVehicle.allowedChassisClasses";

    /** mininum allowed age to substitute a license */
    private static final String SUBSTITUTABLE_COUNTRY_MAX_AGE = "ae.rta.dtt.substitutableCountry.maxAge";
    
    /** maximum allowed age to substitute a license */
    private static final String SUBSTITUTABLE_COUNTRY_MIN_AGE = "ae.rta.dtt.substitutableCountry.minAge";

    /** Threads Monitoring Enabled */
    private static final String THREADS_MONITORING_ENABLED = "ae.rta.traffic.threads.monitoring.enabled";

    /** ENABLE_INVALID_PLATE_CATEGORY_DESC */
    private static final String ENABLE_INVALID_PLATE_CATEGORY_DESC = "ae.rta.ffu.enableInvalidCntPLTDesc";
    
    /** ENABLE_CHECK_PENDING_TRANSACTION_ON_CERTIFY */
    private static final String ENABLE_CHECK_PENDING_TRANSACTION_ON_CERTIFY = "enable.check.pending.transaction.on.certify";
    
    /** br ATR005_MER enabled */
    private static final String BR_ATR005_MER_ENABLED = "brATR005_MER.enabled";        
    
    /** TRF_35668  enabled */
    private static final String TRF_35668_ENABLED = "TRF-35668.enabled";    
    
    
    /** enable task TRF-38842 */
    private static final String ENABLE_TASK_TRF_38842 = "enableTask.TRF-38842";    
    
    /** Contract Allowance Expiry Period */
    private static final String CONTRACT_ALLOWANCE_EXPIRY_PERIOD = "ae.rta.cml.contractAllowanceExpiryPeriod";
    

    /* fast lookup page size */
    private static final String FAST_LOOKUP_PAGE_SIZE = "ae.rta.util.web.jsf.LookupServlet.pageSize";
    /* flag for Breadcrumb Allowed*/
    private static final String IS_BREADCRUMB_ALLOWED = "ae.rta.common.web.jsf.isBreadcrumbAllowed";    
    /** enable task TRF-39560 */
    private static final String ENABLE_TASK_TRF_39560 = "enableTask.TRF-39560"; 
    
    private static final String TELEMATICS_DEVICE_STATUS_SERVICE_TIMEOUT = "ae.rta.telematics.ws.client.TelematicsDeviceStatusTimeout";
    private static final String TELEMATICS_DEVICE_STATUS_SERVICE_USERNAME = "ae.rta.telematics.ws.client.TelematicsDeviceStatusUserName";
    private static final String TELEMATICS_DEVICE_STATUS_SERVICE_PASSWORD = "ae.rta.telematics.ws.client.TelematicsDeviceStatusPassword";
    private static final String TELEMATICS_DEVICE_STATUS_SERVICE_ENDPOINT = "ae.rta.telematics.ws.client.TelematicsDeviceStatusEndpoint";
    
    private static final String TELEMATICS_VHL_ALLOWED_PERIOD = "ae.rta.vhl.telematics.allowedPeriod";
        
    /** Contract Lock Timeout */
    private static final String CONTRACT_LOCK_TIMEOUT = "ae.rta.cml.contractLockTimeout";    
    
    /** Contract Registered Vehicle Percentage */
    private static final String CONTRACT_REGISTERED_VEH_PERCENTAGE = "ae.rta.cml.contractRegVehiclePercentage";
    
    /** Allowed Plate Codes For Franchise Organization */
    private static final String ALLOWED_PLT_CODES_FOR_FRANCHISE_ORG = "ae.rta.cml.allowedPlateCodesForFranchiseOrg";  
    
    /** Denied No Of Day Before Trial */
    private static final String  DENIED_NO_OF_DAY_BEFORE_TRIAL = "ae.rta.dtt.ApplicationTranssfer.deniedNoOfDayBeforeTrial";    
    
    /** Max No Of Transfer */
    private static final String  MAX_NO_OF_TRANSFER = "ae.rta.dtt.ApplicationTranssfer.maxNoOfTransfer";    
    
    /** Franchise Contract Display Warning Message if Booklet Pool Less Than Specficed Number **/
    private static final String FRANCHISE_BOOKLET_POOL_NUMBER_WARNING = "ae.rta.cml.FranchiseBookletPoolNumberWarning";
    
    /** Franchise Taxi Contract Display Warning Message if Booklet Pool Less Than Specficed Number **/
    private static final String FRANCHISE_TAXI_BOOKLET_POOL_NUMBER_WARNING = "ae.rta.cml.FranchiseTaxiBookletPoolNumberWarning";

    private static final String ENABLE_CHECK_EWALLET_DUPLICATED_REQUEEST =  "enable.check.ewallet.duplicated.request";    
        
    private static final String REPORT_BUFFRING_SIZE = "report.buffring.size";
    
    /** Enable or Disable Returning Kiosk Centers Id */
    private static final String ENABLE_TRF_40960  = "enable.TRF-40960";
        
    /** enable task TRF-39485 */
    private static final String ENABLE_TASK_TRF_39485 = "enableTask.TRF-39485";
    
    /** enable task TRF-38697 */
    private static final String ENABLE_TASK_TRF_38697 = "enableTask.TRF-38697";        
    
    /** Enable Task TRF-42437*/
    private static final String ENABLE_TASK_TRF_42437 = "enableTask.TRF-42437"; 
    
    
    /** enable Validation On DTT Services With Elec Sign proprity */
    private static final String ENABLE_VALIDATION_ON_DTT_SERVICES_WITH_ELEC_SIGN = "ae.rta.common.enableValidationOnDTTServicesWithElecSign";  
    
    /** Franchise Organization Allowed Registration Years proprity */
    private static final String FRANCHISE_ORGANIZATION_ALLOWED_REGISTRATION_YEARS = "ae.rta.cml.contracts.franchiseOrgAllowedRegYears";
    
    /** Franchise Expired Renewal Organization Monthes */
    private static final String FRANCHISE_EXPIRED_ORG_RENEWAL_MONTHES = "ae.rta.cml.contracts.franchiseExpiredOrgRenewalMonthes";
    
    /** Dealer Trade License Expiry Grace Period */
    private static final String DEALER_TRADE_LICENSE_EXPIRY_GRACE_PERIOD = "ae.rta.vhl.vehicleSales.dealerTradeLicenseExpiryGracePeriod";
    
    /** Use Car Dealer Trade License Expiry Grace Period */
    private static final String USE_CAR_DEAlER_TRADE_LICENSE_EXPIRY_GRACE_PERIOD = "ae.rta.vhl.vehicleSales.useCarDealerTradeLicenseExpiryGracePeriod";

    /** Services Need Category Check  */ 
    private static final String SERVICES_NEED_CATEGORY_CHICK  = "ae.rta.pmt.servicesNeedCategoryCeck";
    
    /** Use Car Dealer Trade License Expiry Grace Period */
    private static final String ENABLE_SMART_TRAFFIC_ACCESS = "ae.rta.smart.web.filter.EnableSmartTrafficAccess";
    
    /** used in smart security filter to force page security definition **/
    private static final String IS_BYPASS_PAGE_INFO_ENABLED = "ae.rta.auth.web.jsf.UserAuthorizationBean.bypassPageInfo";

    /** Enable BR_TRS215_ATT*/
    private static final String ENABLE_BR_TRS215_ATT = "enable.BR_TRS215_ATT"; 
    
    /** White points whorking days*/
    private static final String WHITE_POINT_JOB_WORKING_DAY = "ae.rta.ffu.whitePoint.workingDay"; 
    
    private static final String JASPER_SERVICE_IP_PROPRITY = "ae.rta.jasperService.serverIP";
    
    /** Jasper Service Server Port Proprity */
    private static final String JASPER_SERVICE_PORT_PROPRITY = "ae.rta.jasperService.serverPort";
    
    /** Jasper Service Server UserName Proprity */
    private static final String JASPER_SERVICE_USERNAME_PROPRITY = "ae.rta.jasperService.userName";
    
    /** Jasper Service Server User Passwod Proprity */
    private static final String JASPER_SERVICE_PASWWORD_PROPRITY = "ae.rta.jasperService.userPassword";

    /** enable task TRF-40596 */
    private static final String ENABLE_TASK_TRF_40596  = "enableTask.TRF-40596";
    
    /** flag to enable soluation of Double Slash in URL problem */
    private static final String HANDLING_DOUBLE_SLASH_IN_URL = "ae.rta.security.web.SecurityFilter.handlingDoubleSlashInURL";
    /** new system range size */
    private static final String TABLES_RANGE_SIZE = "ae.rta.common.web.jsf.AppPreferencesBean.tableRangeSize";
    /** WS Booklets Service End Point */
    private static final String WS_BOOKLETS_END_POINT = "ae.gov.trf.ws.BookletService.url";
    
    /** WS Booklets Service UserName */
    private static final String WS_BOOKLETS_USER_NAME = "ae.rta.vhl.exportToVehicle.wsBookletsUserName";
    
    /** WS Booklets Service Password */
    private static final String WS_BOOKLETS_PASSWORD = "ae.rta.vhl.exportToVehicle.wsBookletsPassword";
    
    
    /** URLs to be Skipped from the soluation of Double Slash in URL */
    private static final String DOUBLE_SLASH_SKIPPED_URLS = "ae.rta.security.web.SecurityFilter.doubleSlashSkippedURLs";
    
    /** Max Search Days */
    private static final String MAX_SEARCH_DAYS = "ae.rta.dtt.web.RescheduleAppointmentSearch.maxSearchDays";
    
    /** Enable Task TRF-39562 */
    private static final String ENABLE_TASK_TRF_39562 = "enableTask.TRF-39562";

    /** enable task  TRF-43536 Vehicles Makes and Models Migration */
    private static final String IS_TASK_TRF_43536_ENABLED = "enableTask.TRF-43536";
    
    /** Electronic Mortgage Uploaded. */
    private static final String ELECTRONIC_MORTGAGE_UPLOADED_MSG = "ae.rta.vhl.mortgage.electronicMortgageUploaded";

    /** Default mobile No to be displaied in vip_special_plates_search.jsf screen */
    private static final String DEFAULT_VIP_MOBILE_NO = "ae.rta.spl.web.jsf.VipSpecialPlatesSearchBean.DefaultVIPMobileNo";
                                        
    /** Max Date Days */
    private static final String MAX_SEARCH_DATE_DAYS_DTT_REPORT
                                        = "ae.rta.dtt.rpt.maxDateDays";

    
    /** Replace Plates Remarks Codes. */
    private static final String REPLACE_PLATES_REMARKS_CODES = "ae.rta.vhl.vehicleTest.replacePlatesRemarksCodes";

    /** Use For Enabled Or Disabled The English Smart Traffic */
    private static final String ENABLE_English_SMART_TRAFFIC_ACCESS = "ae.rta.smart.web.filter.EnableEnglishSmartTrafficAccess";        
    
    /** enable task TRF-72167 */
    private static final String ENABLE_TASK_TRF_42167 = "enableTask.TRF-42167";
    
    /** Get Services that may skip by plate ids */
    private static final String PLATE_ID_SKIP_SERVICE_CODES = "ae.rta.common.Exceptions.servicesWithPlateId";
    /** enable Validate Plate Logo Fees */
    private static final String ENABLE_PLATE_LOGO_FEES_VALIDATION = "ae.rta.trs.enablePlateLogoFeesValidation";
    /** Enable Export task for validate the new chasiss */
    private static final String ENABLE_TRF46367_VALIDATION = "ae.rta.trs.enableTRF46367Validation";
    
    /** enable task TRF-46188 */
    private static final String IS_TASK_TRF_46188_ENABLED = "enableTask.TRF-46188";
    
    /** Enable Task TRF-46361*/
    private static final String ENABLE_TASK_TRF_46361 = "enableTask.TRF-46361";
    
    /** No of Sales. */
    private static final String NO_OF_SALES = "ae.rta.vhl.vehicleSales.noOfSales";
    
    
    /** Enable Vehicle Contracts II TRF-42109 Task Property */
    private static final String ENABLE_TASK_TRF_42109 = "enableTask.TRF-42109";
    
    /** Replace Released Booklets Days Validity */
    private static final String REPLACE_RELEASED_BOOKLETS_DAYS_VALIDITY = "cml.contracts.replaceReleasedBookletsDaysValidity";
    
    /** Enable Vehicle Contracts II TRF_45741 Task Property */
    private static final String ENABLE_TASK_TRF_45741 = "enableTask.TRF-45741";
    
    /** Enable Vehicle Contracts II TRF_47258 Task Property */
    private static final String ENABLE_TASK_TRF_47258 = "enableTask.TRF-47258";
    
    /** Data Signing Certs */
    private static final String DATA_SIGNING_CERTS = "ae.rta.common.web.eid.dataSigningCerts";
    
    /** EID ReadDateExceedingPeriodInDays */
    private static final String EID_READ_DATE_PERIOD_IN_DAYS = "ae.rta.common.eidReadDateExceedingPeriodInDays";

    /** Enable TRF-47529 Task Property */
    private static final String ENABLE_TASK_TRF_47529 = "enableTask.TRF-47529";
    
    /** FTF Cat Codes */
    public static final String FTF_CAT_CODES = "ae.rta.cml.FTFCatCodes";
    
    /** SDDI Cat Codes */
    public static final String SDDI_CAT_CODES = "ae.rta.cml.SDDICatCodes";

    /** Enable TRF-32575 Task Property */
    private static final String ENABLE_TASK_TRF_32575 = "enableTask.TRF-32575";
    
    /** enable task  TRF-46484*/
    private static final String IS_EID_WEBSERVICE_ENABLED = "enableTask.TRF-46484";

    /** enable Task TRF 29400 */
    private static final String ENABLE_TASK_TRF_29400 = "enableTask.TRF-29400";
    
    /** enable task TRF-43140 */
    private static final String IS_TASK_TRF_43140_ENABLED = "enableTask.TRF-43140";
    
    /** Skip Br TRS217_CEV*/
    private static final String SKIP_BR_TRS217_CEV = "ae.rta.stp.skipTRS217CEV";
    
    /** Skip TRS 49548*/
    private static final String SKIP_TRS_49548 = "ae.rta.trs.skipTRS49548";
    
    /** enable Task TRF 48419 */
    private static final String ENABLE_TASK_TRF_48419 = "enableTask.TRF-48419";
    

    /** Allowed services while release mortgage */
    private static final String ALLOWED_SERVICES_WHILE_RELEASE_MORTGAGE = "allowed.services.while.release.mortgage";
    
    /** enable Task TRF 41555 */
    private static final String ENABLE_TASK_TRF_41555 = "enableTask.TRF-41555";
    
    /** enable  TRS 41555 */
    private static final String ENABLE_TRS_41555 = "enable.TRS-41555";


    /** enable task TRF-49548 */
    private static final String IS_TASK_TRF_49548_ENABLED = "enableTask.TRF-49548";
    

    /** DRL Services Excluded from fines */
    private static final String DRL_SERVICES_EXCLUDED_FROM_FINES = "drl.services.excluded.from.fines";
    
    /** Enable  TRS_52240 flag */
    private static final String ENABLE_TASK_TRF_52240 = "enableTask.TRF-52240";
    
    
    /** Dnrd Tibco Header User Name */
    private static final String TIBCO_DNRD_HEADER_USER_NAME
         = "tibco.dnrd.header.username";

    /** Dnrd Tibco Header Password */
    private static final String TIBCO_DNRD_HEADER_PASSWORD
         = "tibco.dnrd.header.password";
    
    /*
     * Static initializer
     */

    static {
        LOGGING_LEVEL_VALUES.put("OFF", Level.OFF);
        LOGGING_LEVEL_VALUES.put("SEVERE", Level.SEVERE);
        LOGGING_LEVEL_VALUES.put("WARNING", Level.WARNING);
        LOGGING_LEVEL_VALUES.put("INFO", Level.INFO);
        LOGGING_LEVEL_VALUES.put("CONFIG", Level.CONFIG);
        LOGGING_LEVEL_VALUES.put("FINE", Level.FINE);
        LOGGING_LEVEL_VALUES.put("FINER", Level.FINER);
        LOGGING_LEVEL_VALUES.put("FINEST", Level.FINEST);
        LOGGING_LEVEL_VALUES.put("ALL", Level.ALL);
    }

    /*
     * Instance variables
     */

    /** Configuration Map. */
    private static Map map = new HashMap();

    /*
     * Constructors
     */

    /**
     * Create new Configuration object
     */
    public SmartHomeBudgetConfig() {
        // Empty implementation
    }

    /**
     * Create new Configuration object
     * 
     * @param configMap Configuration info to be added
     */
    public SmartHomeBudgetConfig(Map configMap) {
        // Add configMap parameters
        map.putAll(configMap);
        
        // Generate new JVM ID
        map.put("ae.rta.common.jvm.id", generateJvmId());
    }

    /*
     * Methods
     */
     
    /**
     * Generate new JVM ID.
     * 
     * @return Generated JVM ID.
     */
    private String generateJvmId() {
        StringBuffer newId = new StringBuffer();
        newId.append(System.currentTimeMillis())
             .append("_")
             .append(new Random().nextDouble());
             
        return newId.toString();
    }
    
    /**
     * Get current JVM ID.
     * 
     * @return current JVM ID.
     */
    public String getJvmId() {
        return getProperty("ae.rta.common.jvm.id");
    }

    /**
     * Returns the String representation of this object.
     *
     * @return String representation of this object.
     */
    public String toString() {
        StringBuffer buf = new StringBuffer("SmartHomeBudgetConfig [");
        Iterator keys = map.keySet().iterator();

        while (keys.hasNext()) {
            String name = keys.next().toString();
            buf.append("\n    ").append(name).append("=").append(map.get(name));
        }

        buf.append("\n]");
        return buf.toString();
    }
    

    /**
     * Returns applictation property using its name.
     *
     * @param Property name.
     * @return Property value.
     */
    public Object get(String key) {
        // Check if property already on the cach
        if (this.map.containsKey(key)) {
            return map.get(key);
        }

        // Get property value from database
        PropertyHandler handler=new PropertyHandler();
        String value=  handler.getByName(key);
        
        if (value==null) {
            logger.warning(new StringBuffer("Smart Home Budget  property not found: ")
                  .append(key).toString());
            return null;
        }
        // If this is a static property, save it on the cach
            map.put(key, value);
        // Return property value
        return value;
    }

    /**
     * Returns applictation property using its name.
     *
     * @param Property name.
     * @return Property value.
     */
    public String getProperty(String key) {
        Object val =  get(key);
        return (val != null) ? val.toString() : null;
    }

    /**
     * Returns applictation property using its name.
     *
     * @param Property name.
     * @param delimiter : delimiter.
     * @return Property value formated by list.
     */
    public List getListProperty(String key,String delimiter) {
        
        Object val = get(key);
        
        if(val == null || val.toString() == null) {
            return new ArrayList();
        }
        String[] valuesArray = val.toString().split(",");
        
        if(valuesArray == null) {
            return new ArrayList();
        }
        
        List valueList = new ArrayList();
        
        for(int index = 0;valuesArray.length > index;index++) {
            valueList.add(valuesArray[index]);
        }
        
        return valueList;
    }

    /**
     * Returns applictation property using its name.
     *
     * @param  Property name.
     * @return Property value.
     */
    public int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }

    /**
     * Get Max Days For Examiner To Search
     *
     * @return Max Days For Examiner To Search
     */
    public int getMaxDaysForExaminerToSearch() {
        return getIntProperty(MAX_DAYS_FOR_EXAMINER_TO_SEARCH);
    }

    /**
     * Get Reschedule Appointment Search Max Page Search Days
     *
     * @return Reschedule Appointment Search Page Max Search Days
     */
    public int getRescheduleAppointmentSearchPageMaxSearchDays() {
        return getIntProperty(MAX_SEARCH_DAYS);
    }
    
    /**
     * Get Max Days For Stock To Search
     *
     * @return Max Days For Stock To Search
     */
    public int getMaxDaysForStockToSearch() {
        return getIntProperty(MAX_DAYS_FOR_STOCK_TO_SEARCH);
    }
    
    
    /**
     * Get Integer Property
     * 
     * @param propertyName : Property Name.
     * 
     * @return Integer value for passed property.
     */
    public Integer getIntegerProperty(String propertyName) {
        
        String value = getProperty(propertyName);
        
        if (GlobalUtilities.isBlankOrNull(value))  {
            return null;
        }
        
        if (!GlobalUtilities.isLong(value))  {
            throw new NumberFormatException("Invalid Inetger value for property name : "
                                                + propertyName);
        }
        
        return GlobalUtilities.getInteger(value);
    }
    
    /**
     * Returns applictation property using its name.
     *
     * @param  Property name.
     * @return Property value.
     */
    public long getLongProperty(String key) {
        return Long.parseLong(getProperty(key));
    }    
    
    /**
     * Returns applictation property using its name.
     *
     * @param  Property name.
     * @return Property value.
     */
    public Date getDateProperty(String key) {
        String property = getProperty(key);
        if (property == null) {
            return null;
        }

        try {
                SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                return dataFormat.parse(property);
        } catch (ParseException e) {
            logger.severe(new StringBuffer("Invalid date property format: ")
                  .append("name=").append(key)
                  .append(", value=").append(property).toString());

            return null;
        } 
    }
    
    /**
     * Returns date property using its name by default format.
     *
     * @param  Property name.
     * @return Property value.
     */
    public Date getDatePropertyForDefaultFormat(String key) {
        String property = getProperty(key);
        if (property == null) {
            return null;
        }

        try {
            return DEFAULT_DATE_FORMAT.parse(property);

        } catch (ParseException e) {
            logger.severe(new StringBuffer("Invalid date property format: ")
                  .append("name=").append(key)
                  .append(", value=").append(property).toString());

            return null;
        } 
    }
    
    /**
     * Returns applictation property using its name.
     *
     * @param  Property name.
     * @return Property value.
     */
    public Date getDateTimeProperty(String key) {
        String property = getProperty(key);
        if (property == null) {
            return null;
        }

        try {
            return DATE_TIME_FORMAT.parse(property);

        } catch (ParseException e) {
            logger.severe(new StringBuffer("Invalid date time property format: ")
                  .append("name=").append(key)
                  .append(", value=").append(property).toString());

            return null;
        }
    }

    /**
     * Returns applictation property using its name.
     *
     * @param name Property name.
     * @return Property value.
     */
    public boolean getBooleanProperty(String name) {
        String property = getProperty(name);
        return (property != null && property.trim().equalsIgnoreCase("true"));
    }

    /**
     * Get Digital radar tickets max locking period.
     *
     * @return Digital radar tickets max locking period.
     */
    public int getDigitalRadarTicketTimeout() {
        return getIntProperty(DIGITAL_RADAR_TCK_LOCK_TIMEOUT);
    }

    /**
     * Check if the debugging mode was enabled or not
     *
     * boolean true if debugging was enabled, false otherwise.
     */
    public boolean isDebugEnabled() {
        return getBooleanProperty(DEBUG);
    }

    /**
     * check if logging stack trace in the application error pages is enabled
     *
     * @return true if logging stack trace in the application error pages is
     *         enabled
     */
    public boolean isErrorPageDebugEnabled() {
        return getBooleanProperty(ERROR_PAGE_DEBUG);
    }

    /**
     * Get Supported MIME types for digital radar pictures.
     *
     * @return Supported MIME types for digital radar pictures.
     */
    public String getRadarPicturesMimeTypes() {
        return getProperty(RADAR_PICTURE_MIME_TYPES);
    }

    /**
     * Check if the radar picture MIME type is supported.
     *
     * @param mime Radar picture MIME type.
     * @return true if radar picture MIME type is supported.
     */
    public boolean isRadarPictureMimeTypeSupported(String mime) {
        return getRadarPicturesMimeTypes().indexOf(mime.toLowerCase()) >= 0;
    }
    
    /**
     * Get Salik fine image web-service URL.
     * 
     * @return Salik fine image web-service URL.
     */
    public String getSalikFineImageWebService() {
        return getProperty(SALIK_FINE_IMAGE_WS_URL);
    }
    
    /**
     * Get UTS reporting service URL.
     * 
     * @return UTS reporting service URL.
     */
    public String getUTSReportingServiceURL() {
        return getProperty(CFI_UTS_RPT_URL);
    }
    
    /**
     * Get Other Emirates Light Vehicle Class
     * 
     * @return Abu Dhabi Light Vehicle Class.
     */
    public String getOtherEmiratesLightVehicleClass() {
        return getProperty(OTHER_EMIRATES_LIGHT_VEHICLE_CLASS);
    }

    /**
     * Get UTS reporting SOAP action for "getTicketInfo".
     * 
     * @return UTS reporting SOAP action fro "getTicketInfo".
     */
    public String getUTSTicketSoapAction() {
        return getProperty(CFI_UTS_RPT_SOAP_ACTION_GET_TCK);
    }

    /**
     * Get UTS reporting SOAP action for "getTicketHistoryInfo".
     * 
     * @return UTS reporting SOAP action fro "getTicketHistoryInfo".
     */
    public String getUTSTicketHistorySoapAction() {
        return getProperty(CFI_UTS_RPT_SOAP_ACTION_GET_TCK_HST);
    }

    /**
     * Get UTS reporting SOAP action for "getVehicleInfo".
     * 
     * @return UTS reporting SOAP action fro "getVehicleInfo".
     */
    public String getUTSVehicleSoapAction() {
        return getProperty(CFI_UTS_RPT_SOAP_ACTION_GET_VEHICLE);
    }

    /**
     * Get UTS header user name
     * 
     * @return UTS header user name
     */
    public String getUTSHeaderUserName() {
        return getProperty(CFI_UTS_USER_NAME);
    }    

    /**
     * Get UTS header password
     * 
     * @return UTS header password
     */
    public String getUTSHeaderPassword() {
        return getProperty(CFI_UTS_PASSWORD);
    }
        
    /**
     * Get default page size for EPS search pages
     * 
     * @return default page size for EPS search pages
     */
    public int getSearchPageSize() {
        return getIntProperty(SEARCH_PAGE_SIZE);
    }

    /**
     * Get Maximum number of records retrieved by pagination pages.
     * 
     * @return Maximum number of records retrieved by pagination pages.
     */
    public int getMaxPaginationRecords() {
        return getIntProperty(MAX_PAGINATION_RECORDS);
    }

    /**
     * Get logging level for all CFI application loggers.
     * 
     * @return logging level for all CFI application loggers. If no level 
     *         propery exists or the property name was invalid, Level.INFO will
     *         be returned.
     */
    public Level getLoggingLevel() {
        String propertyName = getProperty(LOGGING_LEVEL);
        Object level = LOGGING_LEVEL_VALUES.get(propertyName);
        if (level != null && level instanceof Level) {
            return (Level) level;
        }

        StringBuffer msg = new StringBuffer();
        msg.append("WARNING: Logging level property was not found,")
           .append(" INFO logging level will be used, ")
           .append(LOGGING_LEVEL).append("=").append(getProperty(LOGGING_LEVEL));

        return Level.INFO;
    }
    
    /**
     * Get logging level for all smart application loggers.
     * 
     * @return logging level for all smart application loggers. If no level 
     *         propery exists or the property name was invalid, Level.INFO will
     *         be returned.
     */
    public String getSmartLoggingLevel() {
        return getProperty(SMART_LOGGING_LEVEL);
    }
    
    /**
     * Get Reports server parameter
     * 
     * @return Reports server parameter
     * @return 
     */
    public String getReportsServer() {
        return getProperty(REPORTS_SERVER);
    }
    /**
     * Get Reports server parameter
     * 
     * @return Reports server parameter
     * @return 
     */
    public String getReportsInternalServer() {
        return getProperty(REPORTS_INTRANET_SERVER);
    }
    
    
    /**
     * Get Reports server web host
     * 
     * @return Reports server web host
     */
    public String getReportsWebHost() {
        return getProperty(REPORTS_WEB_HOST);
    }

    /**
     * Get Reports server port
     * 
     * @return Reports server port
     */
    public String getReportsServerPort() {
        return getProperty(REPORTS_PORT);
    }

    /**
     * Get Reports user ID
     * 
     * @return Reports user ID
     */
    public String getReportsUserId() {
        return getProperty(REPORTS_USER_ID);
    }
    /**
     * Get Reports user ID
     * 
     * @return Reports user ID
     */
    public String getReportsInternalUserId() {
        return getProperty(REPORTS_INTRANET_USER_ID);
    }
    
    /**
     * Get Number of wating trials for a process to finish its current task
     * 
     * @return Number of wating trials for a process to finish its current task
     */
    public int getProcessStopTrials() {
        return getIntProperty(PROCESS_STOP_TRIALS);
    }
    
    /**
     * Get Web service client timeout
     * 
     * @return Web service client timeout
     */
    public Integer getWebserviceClientTimeout() {
        return new Integer(1000*getIntProperty(WEBSERVICE_CLIENT_TIMEOUT));
    }

    /**
     * Get Web service client timeout
     * 
     * @return Web service client timeout
     */
    public Integer getWebserviceClientTimeoutTest() {
        return new Integer(1000*getIntProperty(WEBSERVICE_CLIENT_TIMEOUT_TEST));
    }

    
    /**
     * Get Emirates Identity start date for locals
     * 
     * @return Emirates Identity start date for locals
     */
    public Date getEMIIdentityLocalsStartDate() {
        return getDateProperty(EMIRATES_IDENTITY_LOCALS_START_DATE);
    }
    
    /**
     * Get Emirates Identity start date for residents
     * 
     * @return Emirates Identity start date for residents
     */
    public Date getEMIIdentityResidentsStartDate() {
        return getDateProperty(EMIRATES_IDENTITY_RESIDENTS_START_DATE);
    }
    
    /**
     * Get traffic application URL.
     * 
     * @return traffic application URL.
     */
    public String getTrafficUrl() {
        return getProperty(TRAFFIC_URL);
    }
    
    /**
     * Get booklet print allowed period
     * 
     * @return booklet print allowed period
     */
    public int getBookletPrintAllowedPeriod() {
        return getIntProperty(BOOKLET_PRINT_ALLOWED_PERIOD);
    }
    
    /**
     * Get license print allowed period
     * 
     * @return license print allowed period
     */
    public int getLicensePrintAllowedPeriod() {
        return getIntProperty(LICENSE_PRINT_ALLOWED_PERIOD);
    }

    /**
     * Get traffic application IP.
     * 
     * @return traffic application IP.
     */
    public String getTrafficIP() {
        return getProperty(TRAFFIC_IP);
    }    

    /**
     * Check if business-rules cache is enabled.
     * 
     * @return true if if business-rules cache is enabled.
     */
    public boolean isBusinessRulesCacheEnabled() {
        return getBooleanProperty(CACHE_BUSINESS_RULES);
    }

    /**
     * Check if domain-values cache is enabled.
     * 
     * @return true if if domain-values cache is enabled.
     */
    public boolean isDomainValuesCacheEnabled() {
        return getBooleanProperty(CACHE_DOMAIN_VALUES);
    }
    
    /**
     * Get encryption key.
     * 
     * @return encryption key.
     */
    public static String getEncryptionKey() {
        return "1234567891";
    }
     
    /**
     * Get Network performance process delay in seconds
     * 
     * @return Network performance process delay in seconds
     */
    public int getNPLProcessDelayMinutes() {
        return getIntProperty(TRAFFIC_NPL_DELAY); 
    }
    
    /**
     * Get Network performance process active
     * 
     * @return Network performance process active
     */
    public boolean isNetworkPerformanceMonitorActive() {
        return getBooleanProperty(TRAFFIC_NPL_ACTIVE); 
    }
    
    /**
     * EGOv Payment response time in minutes
     * 
     * @return EGOv Payment response time in minutes 
     */
    public int getEgovPaymentResponseTimeMinutes() {
        return getIntProperty(EGOV_PAYMENT_RESPONSE_TIME); 
    }
    
    /**
     * Get Max attachments size in Mega Byte.
     * 
     * @return Max attachments size in Mega Byte.
     */
    public int getMaxAttachmentSize() {
        return getIntProperty(MAX_ATTACHMENT_SIZE);
    }
    
    /**
     * Check if traffic is testing environment.
     * 
     * @return true if  testing environment is enabled.
     */
    public boolean isTesting() {
        return getBooleanProperty(TRAFFIC_TESTING);
    }
    
    /**
     * Get booklet print transfer center ID
     * 
     * @return booklet print transfer center Id
     */
    public String getBookletPrintTransferCenter() {
        return getProperty(BOOKLET_PRINT_TRANSFER_CENTER);
    }
    
    /**
     * Get SSL max time out login.
     *
     * @return SSL max time out login.
     */
    public int getSSLLoginTimeoutseconds() {
        return getIntProperty(SSL_LOGIN_TIMEOUT);
    }
    
    /**
     * Get traffic application SSL URL.
     * 
     * @return traffic application SSL URL.
     */
    public String getTrafficSSLUrl() {
       return getProperty(TRAFFIC_SSL_URL);
        
    }
    
    /**
     * Get Receipt Installment Cheque minimum amount
     *
     * @return Receipt Installment Cheque minimum amount
     */
    public int getReceiptInstallmentChequeMinAmount() {
        return getIntProperty(RECIEPT_INSTALLMENT_CHEQUE_MIN_AMOUNT);
    }    

    /**
     * Get DNRD Person Info Web Service URL.
     * 
     * @return DNRD Person Info Web Service URL.
     */
    public String getDNRDPersonInfoServiceUrl() {
        return getProperty(DNRD_PERSON_INFO_SERVICE_URL);
    }
    
    /**
     * Get Tibco DNRD Person Info Web Service URL.
     * 
     * @return Tibco DNRD Person Info Web Service URL.
     */
    public String getTibcoDNRDPersonInfoServiceUrl() {
        return getProperty(TIBCO_DNRD_PERSON_INFO_SERVICE_URL);
    }
    
    /**
     * Get Old DNRD Person Info Web Service URL.
     * 
     * @return Old DNRD Person Info Web Service URL.
     */
    public String getOldDNRDPersonInfoServiceUrl() {
        return getProperty(OLD_DNRD_PERSON_INFO_SERVICE_URL);
    }
    
    /**
     * Get DNRD Lookup Data Web Service URL.
     * 
     * @return DNRD Lookup Data Web Service URL.
     */
    public String getDNRDLookupDataServiceUrl() {
        return getProperty(DNRD_LOOKUP_DATA_SERVICE_URL);
    }

    /**
     * Get DNRD Person Info Web Service SOAP Action.
     * 
     * @return DNRD Person Info Web Service SOAP Action.
     */
    public String getDNRDPersonInfoSoapAction() {
        return getProperty(DNRD_PERSON_INFO_SOAP_ACTION);
    }
    
    /**
     * Get Tibco DNRD New Person Info Web Service SOAP Action.
     * 
     * @return Tibco DNRD Person Info Web Service SOAP Action.
     */
    public String getTibcoDNRDPersonInfoSoapAction() {
        return getProperty(TIBCO_DNRD_PERSON_INFO_SOAP_ACTION);
    }
    
    
    
    /**
     * Get DNRD Lookup Data Web Service SOAP Action.
     * 
     * @return DNRD Lookup Data Web Service SOAP Action.
     */
    public String getDNRDLookupDataSoapAction() {
        return getProperty(DNRD_LOOKUP_DATA_SOAP_ACTION);
    }
    
    /**
     * Get DNRD Person Info Web Service Security ID.
     * 
     * @return DNRD Person Info Web Service Security ID.
     */
    public String getDNRDPersonInfoSecurityId() {
        return getProperty(DNRD_PERSON_INFO_SECURITY_ID);
    }
    
    /**
     * Get DNRD Lookup Data Web Service Security ID.
     * 
     * @return DNRD Lookup Data Web Service Security ID.
     */
    public String getDNRDLookupDataSecurityId() {
        return getProperty(DNRD_LOOKUP_DATA_SECURITY_ID);
    }
    
    /**
     * Check if the SSL logine was enabled or not
     *
     * boolean true if SSL logine was enabled, false otherwise.
     */
    public boolean isSSLEnabled() {
        return getBooleanProperty(SSL_LOGIN_ENABLED);
    }

    /**
     * Get FedEx FSM server IP address.
     * 
     * @return FedEx FSM server IP address.
     */
    public String getFedexFsmIP() {
        return getProperty(FEDEX_FSM_IP);
    }

    /**
     * Get FedEx FSM server port number.
     * 
     * @return FedEx FSM server port number.
     */
    public int getFedexFsmPort() {
        return getIntProperty(FEDEX_FSM_PORT);
    }
    
    /**
     * Check if UTS report services is enabled or not
     *
     * boolean true if UTS report services is enabled otherwise false
     */
    public boolean isUTSReportServiceEnabled() {
        return getBooleanProperty(UTS_ENABLED);
    }
    
    /**
     * Get FedEx FSM printer location.
     * 
     * @return FedEx FSM printer location.
     */
    public String getFedexFsmPrinterLocation() {
        return getProperty(FEDEX_FSM_PRINTER_LOCATION);
    }
    
    /**
     * Get Center Performance Auto-Refresh Delay In Seconds.
     * 
     * @return Center Performance Auto-Refresh Delay In Seconds.
     */
    public int getCenterPerformanceAutoRefreshDelay() {
        return getIntProperty(SCD_CENTER_PERFORMANCE_DELAY);
    }
    
    /**
     * Get Max payable fines count
     * 
     * @return Max payable fines count
     */
    public long getMaxPayableFinesCount() {
        return getLongProperty(MAX_PAYABLE_FINES_COUNT);
    }
    
    /**
     * Check If DEG Payment Is Active.
     *
     * boolean true If DEG Payment Is Active.
     */
    public boolean isDEGPaymentActive() {
        return getBooleanProperty(DEG_PAYMENT_ACTIVATION_FLAG);
    }
    
    /**
     * Check If DEG Payment Is Active On Traffic Application.
     *
     * boolean true If DEG Payment Is Active On Traffic Application.
     */
    public boolean isDEGPaymentActiveOnTraffic() {
        return getBooleanProperty(DEG_PAYMENT_ACTIVATION_TRAFFIC_FLAG);
    }    
    
    /**
     * Get Number of displayed payable fines
     * 
     * @return Number of displayed payable fines
     */
    public int getNoOfDisplayedPayableFines() {
        return getIntProperty(NO_OF_DISPLAYED_PAYABLE_FINES);
    }
    
    /**
     * Check for dnrd service value 
     * @return true id IS_DNRD_SERVICE_ENABLED is active
     */
    public boolean isDnrdServiceEnabled() {
        return getBooleanProperty(IS_DNRD_SERVICE_ENABLED);
    }
    
    /**
     * Get RTA Counters DEG ePay Hub MD5 Hashing Secret Code.
     * 
     * @return RTA Counters DEG ePay Hub MD5 Hashing Secret Code.
     */
    public String getRTACountersDEGPaymentSecretCode() {
        return getProperty(RTA_COUNTERS_DEG_EPAY_SECRET_CODE);
    }

    /**
     * Get DEG ePay Transaction Status Inquery Timeout (Milliseconds).
     * 
     * @return DEG ePay Transaction Status Inquery Timeout (Milliseconds).
     */
    public long getDEGPaymentTimeout() {
        return getLongProperty(DEG_EPAY_TIMEOUT);
    }
    
    /**
     * Get Deg Lock Timeout
     * 
     * @return DEG lock Transaction Timeout.
     */
    public long getDegLockTimeout() {
        return getLongProperty(DEG_LOCK_TIMEOUT_MIN);
    }
    
    /**
     * Get DP Counters DEG ePay Hub MD5 Hashing Secret Code.
     * 
     * @return DP Counters DEG ePay Hub MD5 Hashing Secret Code.
     */
    public String getDPCountersDEGPaymentSecretCode() {
        return getProperty(DP_COUNTERS_DEG_EPAY_SECRET_CODE);
    }
    
    /**
     * Get RTA Self-Services DEG ePay Hub MD5 Hashing Secret Code.
     * 
     * @return RTA Self-Services DEG ePay Hub MD5 Hashing Secret Code.
     */
    public String getRTASelfServicesDEGPaymentSecretCode() {
        return getProperty(RTA_SELFSERVICES_DEG_EPAY_SECRET_CODE);
    }
    
    /**
     * Get DP Self-Services DEG ePay Hub MD5 Hashing Secret Code.
     * 
     * @return DP Self-Services DEG ePay Hub MD5 Hashing Secret Code.
     */
    public String getDPSelfServicesDEGPaymentSecretCode() {
        return getProperty(DP_SELFSERVICES_DEG_EPAY_SECRET_CODE);
    }
    
    /**
     * Get user locale key.
     * 
     * @return User locale key.
     */
    public String getUserLocaleKey() {
        return getProperty(USER_LOCALE);
    }
    
    /**
     * Get supported languages. If languages property "ae.rta.esrv.i18n.languages"
     * was not found the default language "en" will be returned.
     * 
     * @return supported languages.
     */
    public Map getSupportedLanguages() {
        // Retrieve property value
        Object property = get(SUPPORTED_LANGUAGES);
        
        // If languages property not found throw exception
        if (property == null) {
            return null;
        }
        
        // If the property was instance of java.util.Map, it is already processed
        if (property instanceof Map) {
            return (Map) property;
        }
        
        // Check if the languages property is empty
        String languages = property.toString().trim();
        if (GlobalUtilities.isBlankOrNull(languages)) {
            return null;
        }
        
        // Parse supported languages
        String[] supportedLanguages = languages.split(",");
        Map languagesMap = new HashMap();
        for (int i = 0; i < supportedLanguages.length; i++)  {
            String[] values = supportedLanguages[i].trim().split("=");
            if (values.length != 2) {
                continue;
            }
            
            languagesMap.put(values[0].trim(), values[1].trim());
        }        
        
        // return supported languages
        return languagesMap;
    }
    
    /**
     * Get appication context path URL.
     * 
     * @return Appication context path URL.
     */
    public String getContextPathUrl() {
        return getProperty(CONTEXT_PATH_URL);
    }
    
    /**
     * Get Web-application welcome page. When the user performs a successful
     * login the application will forward his  request to the welcome page.
     * 
     * @return Web-application welcome page.
     */
    public String getWelcomePageUrl() {
        return getProperty(WELCOME_PAGE_URL);
    }
    
//    /**
//     * Get DEG Failure Transaction Cancelation Timeout (Minutes).
//     * 
//     * @return DEG Failure Transaction Cancelation Timeout (Minutes).
//     */
//    public int getDegFailureTransactionCancelationTimeout() {
//        return getIntProperty(DEG_FAILURE_TRANSACTION_CANCELATION_TIMEOUT);
//    }

    /**
     * Transaction Cancellation allowed period
     * 
     * @return Transaction Cancellation allowed period
     */
    public int getTrsCancellationAllowedPeriod() {
        
        return getIntProperty(TRANSACTION_CANCEL_ALLOWED_PERIOD);
    }
    
    /**
     * Get maximum number of vehicles per seasonal card for persons.
     * 
     * @return maximum number of vehicles per seasonal card for persons.
     */
    public int getMaxVehiclesNoPerSeasonalCardForPersons() {
        return getIntProperty(MAX_VEHICLES_PER_SEASONAL_CARD_FOR_PERSONS);
    }
    
    /**
     * Get maximum number of vehicles per seasonal card for organizations.
     * 
     * @return maximum number of vehicles per seasonal card for organizations.
     */
    public int getMaxVehiclesNoPerSeasonalCardForOrganizations() {
        return getIntProperty(MAX_VEHICLES_PER_SEASONAL_CARD_FOR_ORGANIZATIONS);
    }
    
    /**
     * Get Min Activation Date In Days
     * 
     * @return Min Activation Date In Days
     */
    public int getMinActivationDateInDays() {
        
        return getIntProperty(MIN_ACTIVATION_DATE_DAYS);
    }
    
    /**
     * Get Max Number Of Vehicles Per Card
     * 
     * @return Max Activation Date In Days
     */
    public int getMaxActivationDateInDays() {
        
        return getIntProperty(MAX_ACTIVATION_DATE_DAYS);
    }
    
    /**
     * Get Max Synchronized Fines Count
     * 
     * @return Max Synchronized Fines Count
     */
    public int getMaxSynchronizedFinesCount() {
        
        return getIntProperty(MAX_SYNCHRONIZED_FINES_COUNT);
    }    
    
    /**
     * Get Permit Details Service endpoint
     * 
     * @return Permit Details Service endpoint
     */
    public String getPermitDetailsServiceEndPoint() {
        return getProperty(GET_PERMIT_DETAILS_SERVICE_ENDPOINT);
    } 
    
    /**
     * Get EPT Services Header Username
     * 
     * @return  EPT Services Header Username
     */
    public String getEPTServicesHeaderUsername() {
        return getProperty(EPTSERVICES_HEADER_USERNAME);
    }
    
    /**
     * Get EPT Services Header Password
     * 
     * @return  EPT Services Header Password
     */
    public String getEPTServicesHeaderPassword() {
        return getProperty(EPTSERVICES_HEADER_PASSWORD);
    }    

    /**
     * Get Black Listed Endpoint
     * 
     * @return Black Listed Endpoint
     */
    public String getBlackListedServiceEndPoint() {
        return getProperty(GET_BLACK_LISTED_SERVICE_ENDPOINT);
    }

    /**
     * Get Renewal Permit Service Endpoint
     * 
     * @return Renewal Permit Service Endpoint
     */
    public String getRenewalPermitServiceEndPoint() {
        return getProperty(GET_RENEWAL_PERMIT_SERVICE_ENDPOINT);
    }

    /**
     * Get Lost Permit Service Endpoint
     * 
     * @return Lost Permit Service Endpoint
     */
    public String getLostPermitServiceEndPoint() {
        return getProperty(GET_LOST_PERMIT_SERVICE_ENDPOINT);
    }
    
    /**
     * Get Test Appointment Service Endpoint
     * 
     * @return Test Appointment Service Endpoint
     */
    public String getTestAppointmentServiceEndPoint() {
        return getProperty(GET_TEST_APPOINTMENT_SERVICE_ENDPOINT);
    }
    
    /**
     * Get Print Special Permits Buttons Status
     * 
     * @return 2 to enable buttons 
     *         1 to disable buttons
     */
    public int getPrintSpecialPermitsButtonsStatus() {
        return getIntProperty(PRINT_SPECIAL_PERMITS_STATUS);
    }
    
    /**
     * Get Safety Information Service User Name
     * 
     * @return Safety Information Service User Name
     */
    public String getSafetyInformationServiceUserName() {
        return getProperty(SAFETY_INFORMATION_SERVICE_USER_NAME);
    }
    
    /**
     * Get Safety Information Service Password
     * 
     * @return Safety Information Service Password
     */
    public String getSafetyInformationServicePassword() {
        return getProperty(SAFETY_INFORMATION_SERVICE_PASSWORD);
    }
        
    /**
     * Get Permits Service Client EndPoint.
     * 
     * @return URL of Permits Service Client EndPoint.
     */
    public String getPermitsServiceClientEndPoint() {
        return getProperty(GET_PERMITS_SERVICE_CLIENT_ENDPOINT);
    }
    
    /**
     * Get Local Emirates Identity Start Date
     * 
     * @return Local Emirates Identity Start Date property value
     */
    public Date getLocalEmiratesIdentityStartDate() {
        return getDateProperty(LOCAL_EMIRATES_IDENTITY_START_DATE);
    }
    
    /**
     * Get Residents Emirates Identity Start Date
     * 
     * @return Residents Emirates Identity Start Date property value
     */
    public Date getResidentsEmiratesIdentityStartDate() {
        return getDateProperty(RESIDENTS_EMIRATES_IDENTITY_START_DATE);
    }

    /**
     * Get UTS Service End Point
     * 
     * @return UTS Service End Point
     */
    public String getUTSServiceEndPoint() {
        return getProperty(UTS_SERVICE_END_POINT);
    }

    /**
     * Get Maximum duration in days for vehicle mobility permit.
     * 
     * @return String maximum duration in days for vehicle mobility permit.
     */
    public String getMaximumExtraLoadVehicleMobilityPermitDurationDays() {
        return getProperty(MAXIMUM_EXTRA_LOAD_VEHICLE_MOBILITY_PERMIT_DURATION_DAYS);
    }

    /**
     * Get Max Duration Days
     * 
     * @return Vsd WebService Password.
     */
    public String getMaxDurationsDays() {
        return getProperty(MAX_DURATION_DAYS);
    }    
    
   /**
     * Get Auditing Center Id
     * 
     * @return Auditing Center Id
     */
    public Long getAuditingCenterId() {
          return new Long ( getLongProperty(AUDITING_CENTER_ID) );
    }   
    
   /**
    * Get UTS Time Out period
    * 
    * @return UTS Time Out period
    */
    public Integer getUTSTimeOut() {
        return new Integer(1000*getIntProperty(UTS_TIME_OUT));
    }

    /**
     * Get EDMS Integration Target End Point URL.
     * 
     * @return EDMS Integration Target End Point URL.
     */
    public String getEdmsIntTargetEndPointUrl() {
        return getProperty(EDMS_INT_TARGET_END_POINT_URL);
    }

    /**
     * Get EDMS Integration Owner URI.
     * 
     * @return EDMS Integration Owner URI.
     */
    public String getEdmsIntOwnerUri() {
        return getProperty(EDMS_INT_OWNER_URI);
    }

    /**
     * Get EDMS Integration Network Type.
     * 
     * @return EDMS Integration Network Type.
     */
    public int getEdmsIntNetworkType() {
        return getIntProperty(EDMS_INT_NETWORK_TYPE);
    }

    /**
     * Get EDMS Integration Unit Name.
     * 
     * @return EDMS Integration Unit Name.
     */
    public String getEdmsIntUnitName() {
        return getProperty(EDMS_INT_UNIT_NAME);
    }

    /**
     * Get EDMS Integration Username.
     * 
     * @return EDMS Integration Username.
     */
    public String getEdmsIntUsername() {
        return getProperty(EDMS_INT_USERNAME);
    }

    /**
     * Get EDMS Integration Password.
     * 
     * @return EDMS Integration Password.
     */
    public String getEdmsIntPassword() {
        return getProperty(EDMS_INT_PASSWORD);
    }

    /**
     * Get EDMS Integration Target Library.
     * 
     * @return EDMS Integration Target Library.
     */
    public String getEdmsIntTargetLibrary() {
        return getProperty(EDMS_INT_TARGET_LIBRARY);
    }

    /**
     * Get EDMS Integration Search Max Records.
     * 
     * @return EDMS Integration Search Max Records.
     */
    public int getEdmsIntSearchMaxRecords() {
        return getIntProperty(EDMS_INT_SEARCH_MAX_RECORDS);
    }

    /**
     * Get EDMS Integration Connection Timeout.
     * 
     * @return EDMS Integration Connection Timeout.
     */
    public Integer getEdmsIntConnectionTimeout() {
        return new Integer(getIntProperty(EDMS_INT_CONNECTION_TIMEOUT));
    }

    /**
     * Get EDMS Integration Socket Timeout.
     * 
     * @return EDMS Integration Socket Timeout.
     */
    public Integer getEdmsIntSocketTimeout() {
        return new Integer(getIntProperty(EDMS_INT_SOCKET_TIMEOUT));
    }

    /**
     * Get EDMS Integration Document Author Name.
     * 
     * @return EDMS Integration Document Author Name.
     */
    public String getEdmsIntAuthorName() {
        return getProperty(EDMS_INT_AUTHOR_NAME);
    }

    /**
     * Check if the MOI Service is Enabled
     * 
     * @return TRUE if yes
     *         FALSE if not.
     */
    public boolean isMOIServiceEnabled() {
        return getBooleanProperty(MOI_SERVICE_ENABLED);
    }

    /**
     * Get No Insurance Company Id
     * 
     * @return No Insurance Company Id
     *         
     */
    public Long getNoInsuranceCompanyId() {
        return new Long( getProperty(NO_INSURANCE_COMPANY_ID) );
    }

    /**
     * Get Entertmainment Motor Activity
     * 
     * @return Entertmainment Motor Activity
     *         
     */
    public Long getEntertainmentMotorActivity() {
        return new Long( getProperty(ENTERTAINMENT_MOTOR_ACTIVITY));
    }
    
    /**
     * Get Vcc Integration Outage Time From
     * 
     * @return Vcc Integration Outage Time From
     */
    public Date getVccOutageTimeFrom() {
        return getDateTimeProperty(VCC_INTEGRATION_OUTAGE_TIME_FROM);
    }
    
    /**
     * Get Vcc Integration Outage Time To
     * 
     * @return Vcc Integration Outage Time To
     */
    public Date getVccOutageTimeTo() {
        return getDateTimeProperty(VCC_INTEGRATION_OUTAGE_TIME_TO);
    }
    
    /**
     * Get UTS Integration Outage Time From
     * 
     * @return UTS Integration Outage Time From
     */
    public Date getUTSOutageTimeFrom() {
        return getDateTimeProperty(UTS_INTEGRATION_OUTAGE_TIME_FROM);
    }
    
    /**
     * Get UTS Integration Outage Time To
     * 
     * @return UTS Integration Outage Time To
     */
    public Date getUTSOutageTimeTo() {
        return getDateTimeProperty(UTS_INTEGRATION_OUTAGE_TIME_TO);
    }
    
    /**
     * Get Vcc Xmlns WS Security
     * 
     * @return Vcc Xmlns WS Security
     */
    public String getVccXmlnsSecurity() {
        return getProperty(VCC_XMLNS_WS_SECURITY);
    }
    
    /**
     * Get Check Note Codes
     * 
     * @return Ckeck Note Codes
     */
    public String getCheckNoteCodes() {
        return getProperty(CHECK_NOTE_CODES);
    }
    
    /**
     * Get Export vehicle cutoff date
     * 
     * @return Export vehicle cutoff date
     */
    public Date getExportVehicleCutoffDate() {
        return getDateProperty(EXPORT_VEHICLE_CUTOFF_DATE);
    }
    
    /**
     * Get Knwoledge Fee Id.(provided by rta) to be assigned to special 
     * benfrciary used in parking reservation and row site occupation permits.
     * 
     * @return Knwoledge Fee Id.
     */
    public Long getKnowledgeFeeId() {
        return new Long( getProperty(KNOWLEDGE_FEE_ID) );
    }
    
    /**
     * TransactionIntermediateUserName.
     * 
     * @return Transaction Intermediate UserName.
     */
    public String getTransactionIntermediateUserName() {
        return getProperty(TRANSACTION_INTERMDIATE_USER_NAME);
    }

    /**
     * Get Person Activate Brs
     * @return true/false
     */
    public boolean getPersonActivateBr() {
        return getBooleanProperty(PERSON_ACTICATE_BRS);
    }
    
    /**
     * Get Person Activate Brs
     * @return true/false
     */
    public boolean getPersonPapersBusinessEnabled() {
        return getBooleanProperty(NO_PERSON_PAPERS_TRANSACTIONS_PROPERTY);
    }
    
    /**
     * Get Appointment Maximum Working Hours Count.
     * 
     * @return Appointment Maximum Working Hours Count.
     */
    public Integer getMaximumAppointmentCenterWorkingHoursCount(){
        return new Integer(getIntProperty(
                                MAXIMUM_APPOINTMENT_CENTER_WORKING_HOURS_COUNT));
    }
    
    /**
     * Get Ckeck Note Codes
     * 
     * @return Ckeck Note Codes
     */
    public String getCkeckNoteCodes() {
        return getProperty(CKECK_NOTE_CODES);
    }
    
    /**
     * Get Appointement System Enabled.
     * 
     * @return true/false
     */
    public boolean getAppointementSystemEnabled() {
        return getBooleanProperty(IS_APPOINTMENT_SYSTEM_ACTIVE);
    }
    
    /**
     * Get Trade Plate Allowed Period in Days.
     * 
     * @return Trade Plate Allowed Period in Days.
     */
    public int getTradePlateAllowedPeriod() {
        return getIntProperty(TRADE_PLATE_ALLOWED_PERIOD);
    }

    /**
     * Get Allowed No Of Change Plate Ownership Transactions
     * 
     * @return Allowed No Of Change Plate Ownership Transactions.
     */
    public int getAllowedNoOfCPOTransactions() {
        return getIntProperty(ALLOWED_CPO_TRANSACTIONS_NO);
    }  
    

    /**
     * Get if Old Dnrd Service is Active
     * 
     * @return Active Old Dnrd Service;
     */
    public boolean isOldDnrdServiceActive() {
        return getBooleanProperty(ACTIVE_OLD_DNRD);
    }
    
    /**      
     * Get if CTA Activity is Active
     * 
     * @return true if active
     *         false otherwise
     */
    public boolean isActiveCTAActivity() {
        return getBooleanProperty(ACTIVE_CTA_ACTIVITY);
    }
    
    /**
     * Get if Non CTA Activity is Active 
     *     
     * @return true if active
     *         false otherwise
     */
    public boolean isActiveNonCTAActivity() {
        return getBooleanProperty(ACTIVE_NON_CTA_ACTIVITY);
    }
    
    /**
     * Get CML Max Attachment Size.
     * 
     * @return CML Max Attachment Size.
     */
    public int getCMLMaxAttachmentSize() {
        return getIntProperty(CML_MAX_ATTACHMENT_SIZE);
    }
    
    /**
     * Getter method for Get UTS License Info Service EndPoint.
     * 
     * @return URL of Get UTS License Info Service EndPoint.
     */
    public String getUTSLicenseServiceEndPoint() {
        return getProperty(GET_UTS_LICENSE_INFO_SERVICE_ENDPOINT);
    }

    /**
     * Getter method for Get Allocated Plates Lookup Max Count.
     * 
     * @return  Allocated Plates Lookup Max Count.
     */
    public String getAllocatedPlatesLookupMaxCount() {
        return getProperty(ALLOCATED_PLATES_LOOKUP_MAX_COUNT);
    }

    /**
     * Getter method for Get Sms Prohibited Characters .
     * 
     * @return  Sms Prohibited Characters.
     */
    public String getSmsProhibitedCharacters() {
        return getProperty(SMS_PROHIBITED_CHARACTERS);
    }
    
    /**
     * Getter method for get Export To Country
     * 
     * @return  Export To Country.
     */
    public String getExportToCountry() {
        return getProperty(EXPORT_TO_COUNTRY);
    }
    
    /**
     * Getter method for get Export To Active Note
     * 
     * @return  Export To Active Note.
     */
    public String getExportToActiveNote() {
        return getProperty(EXPORT_ACTIVE_NOTE);
    }
    
    /**
     * Get Export Max Year
     * 
     * @return CML Export Max Year.
     */
    public int getExportMaxYear() {
        return getIntProperty(EXPORT_MAX_YEAR);
    }

    /**
     * Getter Driving Institue Activity Code
     * 
     * @return  Driving Institue Activity Code
     */
    public String getDrivingInstitueActivityCode() {
        return getProperty(DRIVING_INSTITUE_ACTIVITY_CODE);
    }

    /**
     * Getter Driving Institue Service Code
     *      
     * @return  Driving Institue Service Code
     */
    public String getDrivingInstitueServiceCode() {
        return getProperty(DRIVING_INSTITUE_SERVICE_CODE);
    }

    /**
     * Getter Driving Institue Center Code
     * 
     * @return Driving Institue Center Code
     */
    public String getDrivingInstitueCenterCode() {
        return getProperty(DRIVING_INSTITUE_CENTER_CODE);
    }
    
    /**
     * Getter method for Get Sms Allowed Special Characters.
     * 
     * @return  Sms Allowed Special Characters..
     */
    public String getSmsAllowedSpecialCharacters() {
        return getProperty(SMS_ALLOWED_SPECIAL_CHARACTERS);
    }

    /**
     * Get Emirates Identity Allowed Center Channel Code
     * 
     * @return  Emirates Identity Allowed Center Channel Code
     */  
    public String getEmiratesIdentityAllowedCenterChannelCode() {
        return getProperty(EMIRATES_IDENTITY_ALLOWED_CENTER_CHANNEL_CODE);
    }

    /**
     * Get Emirates Identity No Paper Start Date   
     * 
     * @return  Emirates Identity No Paper Start Date
     */   
    public Date getEmiratesIdentityNoPaperStartDate() {
        return getDateProperty(EMIRATES_IDENTITY_NO_PAPER_START_DATE);
    }

     /**
     * Is Enable Trade Permite
     * 
     * @return true/false
     */
    public boolean isEnableTradePermit() {
        return getBooleanProperty(ENABLE_TRADE_PERMIT);
    }
     /**
     * get special characters
     * 
     * @return special characters
     */
    public String getSpecialCharacters() {
        return getProperty(SPECIAL_CHARACTERS);
    }
    
    /**
     * Get Allowed Insurance For Reserved Payment.
     * 
     * @return Allowed Insurance For Reserved Payment.
     */
    public String getAllowedInsuranceForReservedPayment() {
        return getProperty(ALLOWED_INSURANCE_FOR_RESERVED_PAYMENT);
    }
    
    /**
     * Get Allowed Payment Reservation Period.
     * 
     * @return Allowed Payment Reservation Period.
     */
    public int getAllowedPaymentReservationPeriod() {
        return getIntProperty(ALLOWED_PAYMENT_RESERVATION_PERIOD);
    }
    
    /**
     * Get UTS Reports Service End Point URL.
     * 
     * @return UTS Reports Service End Point URL.
     */
    public String getUTSReportsEndPointURL() {
        return getProperty(UTS_REPORTS_SERVICE_END_POINT_URL);
    }
    
    /**
     * Get Maximum Number Of Days For issueing New License From Traffic File Creation Date.
     * 
     * @return Maximum Number Of Days For issueing New License From Traffic File Creation Date.
     */
    public int getIssueNewLicenseMaxNoOfDays() {
        return getIntProperty(ISSUE_NEW_LICENSE_MAX_NO_OF_DAYS);
    }
    
     /**
     * Get USER LOGIN.
     * 
     * @return 
     */
    public String getReportServerUserLogin() {
        return getProperty(USER_LOGIN);
    }

    /**
     * Get Allowed Eid Receipt Period
     * 
     * @return Allowed Eid Receipt Period
     */
    public int getAllowedEidReceiptPeriod() {
        return getIntProperty(ALLOWED_EID_RECEIPT_PERIOD);
    }

    
    /**
     * Get UTS Reports Service User Name.
     * 
     * @return UTS Reports Service User Name.
     */
    public String getUTSReportsUserName() {
        return getProperty(UTS_REPORTS_SERVICE_USER_NAME);
    }
    
    /**
     * Get UTS Reports Service Password.
     * 
     * @return UTS Reports Service Password.
     */
    public String getUTSReportsPassword() {
        return getProperty(UTS_REPORTS_SERVICE_Password);
    }

    /**
     * Get Black List Cutoff Date
     * 
     * @return Black List Cutoff Date
     */
    public Date getBlackListCutoffDate() { 
        return getDateProperty(BLACK_LIST_CUTOFF_DATE);
    }

    /**
     * Is Trailer Enhancement Active 
     *  
     * @return Is Trailer Enhancement Active 
     */
    public boolean isTrailerEnhancementActive() {
        return getBooleanProperty(ACTIVE_TRAILER_ENHANCEMENT);
    }      
    
    /**
     * Check if Deg Capture Processor Active ot not.
     * 
     * @return  true : if Deg Capture Processor Active ot not.
     *          false: otherwise.
     */
    public boolean isDegCaptureProcessorActive() {
        return getBooleanProperty(CAPTURE_TRANSACTION_PROCESSOR_ACTIVE);
    }
    
    /**
     * Check if Deg Capture Processor Active ot not.
     * 
     * @return  true : if Deg Capture Processor Active ot not.
     *          false: otherwise.
     */
    public boolean isDegReverseProcessorActive() {
        return getBooleanProperty(REVERSE_TRANSACTION_PROCESSOR_ACTIVE);
    }
    
    /**
     * Get Capture Transaction Processor Delay.
     * 
     * @return Capture Transaction Processor Delay.
     */
    public int getCaptureTransactionProcessorDelay() {
        return getIntProperty(CAPTURE_TRANSACTION_PROCESSOR_DELAY);
    }
    
    /**
     * Get Reverse Transaction Processor Delay.
     * 
     * @return Reverse Transaction Processor Delay.
     */
    public int getReverseTransactionProcessorDelay() {
        return getIntProperty(REVERSE_TRANSACTION_PROCESSOR_DELAY);
    }
    
    /**
     * Get Assessment Validity Period Days.
     * 
     * @return assessment validity period in days.
     */
    public int getAssessmentValidityPeriodDays() {
        return getIntProperty(ASSESSMENT_VALIDITY_PERIOD_DAYES);
    }

    /**
     * Get Minimum number of charachters for English Name on License
     * 
     * @return Minimum number of charachters for English Name on License.
     */
    public int getMinLicensePrintEnglish() {
        return getIntProperty(LICENSE_PRINT_MIN_ENGLISH);
    }    

    /**
     * Get Minimum number of charachters for Arabic Name on License
     * 
     * @return Minimum number of charachters for Arabic Name on License.
     */
    public int getMinLicensePrintArabic() {
        return getIntProperty(LICENSE_PRINT_MIN_ARABIC);
    } 

    /**
     * Get Maximum number of charachters for Arabic Name on License
     * 
     * @return Maximum number of charachters for Arabic Name on License.
     */
    public int getMaxLicensePrintArabic() {
        return getIntProperty(LICENSE_PRINT_MAX_ARABIC);
    }

    /**
     * Get Maximum number of charachters for English Name on License
     * 
     * @return Maximum number of charachters for English Name on License.
     */
    public int getMaxLicensePrintEnglish() {
        return getIntProperty(LICENSE_PRINT_MAX_ENGLISH);
    }     
    
    
    /**
     * Get Cancel Vehicle Center Code
     * 
     * @return Cancel Vehicle Center Code
     */
    public String getCancelVehicleCenterCode() {
        return getProperty(CANCEL_VEHICLE_CENTER_CODE);
    }

    /**
     * Get EWallet Service Provider Id
     * 
     * @return EWallet Service Provider Id
     */
    public Long getEWalletServiceProviderId() {
        return GlobalUtilities.getLong(getProperty(EWALLET_SERVICE_PROVIDER_ID));
    }

    /**
     * Get EWallet FTF Channel Code
     * 
     * @return EWallet FTF Channel Code
     */
    public String getEWalletFTFChannelCode() {
        return getProperty(EWALLET_FTF_CHANNEL_CODE);
    }

    /**
     * Get EWallet Online Channel Code
     * 
     * @return EWallet Online Channel Code
     */
    public String getEWalletOnlineChannelCode() {
        return getProperty(EWALLET_ONLINE_CHANNEL_CODE);
    }

    /**
     * Is E-Wallet Payment Active
     * @return true if the EWallet payment is active
     */
    public boolean isEwalletPaymentActive() {
        return getBooleanProperty(EWALLET_PAYMENT_ACTIVE);
    }

    /**
     * Is UTS Lookup Thread Active 
     * 
     * @return UTS Lookup Thread Active 
     */
    public boolean isUTSLookupInfoThreadActive() {
        return getBooleanProperty(UTS_LOOKUP_INFO_THREAD_ACTIVE);
    }    

    /**
     * Is UTS Lookup Thread Delay
     * 
     * @return UTS Lookup Thread Delay
     */
    public int getUTSLookupInfoThreadDelay() {
        return getIntProperty(UTS_LOOKUP_INFO_THREAD_DELAY);
    }
    
    /**
     * Get DI Cutt Off Date
     * 
     * @return DI Cutt Off Date
     */
    public Date getDICuttOffDate() {
        return getDateProperty(DI_CUT_OFF_DATE);
    }
    
    /**
     * Get Excluded Pay And Presented License Pay Types
     * 
     * @return Excluded Pay And Presented License Pay Types
     */
    public String getExcludedPayAndPresentedLicensePayTypes() {
        return getProperty(EXCLUDED_PAY_AND_PERSENT_LICENSE_PAY_TYPES);
    } 
    
    /**
     * Get Excluded Confiscate Vehicle Pay Types
     * 
     * @return Excluded Confiscate Vehicle Pay Types
     */
    public String getExcludedConfiscateVehiclePayTypes() {
        return getProperty(EXCLUDED_CONFISCATE_VEHICLE_PAY_TYPES);
    } 

    /**
     * Get coloring Permit Deactivate message  
     * 
     * @return coloring Permit Deactivate message
     */
    public String getColoringPermitDeactivateMessage() {
        return getProperty(COLORING_PERMIT_DEACTIVATE_MESSAGTE);
    }
    
    /**
     * Get Rental Car Notes Categories.
     * 
     * @return Rental Car Notes Categories.
     */
    public String getRentalCarNotesCategories() {
        return getProperty(RENTAL_CAR_NOTES_CATEGORIES);
    }

    
    /**
     * Get Driving Institute Service End Point URL.
     * 
     * @return Driving Institute Service End Point URL.
     */
    public String getDrivingInstituteServiceEndPointURL() {
        return getProperty(DRIVING_INSTITUTE_SERVICE_END_POINT_URL);
    }
    
    /**
     * Is Driving Institute Service Thread Active
     * 
     * @return true if Driving Institute Service Thread Active or not
     */
    public boolean isDrivingInstituteServiceThreadActive() {
        return getBooleanProperty(DRIVING_INSTITUTE_SERVICE_THREAD_ACTIVE);
    }
    
    /**
     * Get Driving Institute Service Thread Delay
     * 
     * @return Driving Institute Service Thread Delay In Seconds
     */
    public int getDrivingInstituteServiceThreadDelay() {
        return getIntProperty(DRIVING_INSTITUTE_SERVICE_THREAD_DELAY);
    }
    
    /**
     * Get Max Image Size For Person
     * 
     * @return Max Image Size For Person
     */
    public int getMaxImageSizeForPerson() {
        return getIntProperty(MAX_IMAGE_SIZE_FOR_PERSON);
    }
    
    /**
     * Get Person Photo Validation Period.
     * 
     * @return Person Photo Validation Period.
     */
    public int getPersonPhotoValidationPeriod() {
        return getIntProperty(PERSON_PHOTO_VALIDATION_PERIOD);
    }
    
    /**
     * Get Transaction Cut Off Date For P.O.BOX.
     * 
     * @return Cut Off Date For P.O.BOX.
     */
    public Date getPOBoxCutoffDate() {
        return getDateProperty(CUT_OFF_DATE_P_O_BOX);
    }
     
    /**
     * Get Center Is From Allowed Channel Code.
     * 
     * @return Center Is From Allowed Channel Code.
     */
    public String getAllowedPOBoxChannels() {
        return getProperty(ALLOWED_CHANNEL_FOR_P_O_BOX);
    }
    
     /**
     * Vehicle Sale Transaction Expiry Period
     * 
     * @return Vehicle Sale Transaction Expiry Period
     */
    public int getVehicleSaleTransactionExpiryPeriod() {
        return getIntProperty(VEHICLE_SALE_EXPIRY_PERIOD);
    }
    
    /**
     * Get Ewallet Reconciliation Processor Start Hour.
     * 
     * @return Ewallet Reconciliation Processor Start Hour.
     */
    public int getEwalletReconciliationProcessorStartHour() {
        return getIntProperty(EWALLET_PROCESSOR_START_HOUR);
    }
    
    /**
     * Get Ewallet Reconciliation Processor Start Minute.
     * 
     * @return Ewallet Reconciliation Processor Start Minute.
     */
    public int getEwalletReconciliationProcessorStartMinute() {
        return getIntProperty(EWALLET_PROCESSOR_START_MINUTE);
    }
    
    /**
     * Check If Ewallet Reconciliation Processor is Active.
     * 
     * @return true If Ewallet Reconciliation Processor is Active.
     */
    public boolean isEwalletReconciliationProcessorActive() {
        return getBooleanProperty(EWALLET_PROCESSOR_ACTIVE_FLAG);
    }
    
    /**
     * Get Fedex Track Start Time.
     * 
     * @return Fedex Track Start Time.
     */
    public String getFedexTrackStartTime() {
        return getProperty(FEDEX_TRACK_START_TIME);
    }
    
    /**
     * Check if Digital Pen Processor is Active.
     * 
     * @return true if Digital Pen Processor is Active.
     */
    public boolean isDigitalPenProcessorActive() {
        return getBooleanProperty(DIGITAL_PEN_PROCESSOR_ACTIVE_FLAG);
    }
    
    /**
     * Get Digital Pen Processor Delay.
     * 
     * @return Digital Pen Processor Delay.
     */
    public int getDigitalPenProcessorDelay() {
        return getIntProperty(DIGITAL_PEN_PROCESSOR_DELAY);
    }
    
    /**
     * Get Digital Pen Client End Point.
     * 
     * @return Digital Pen Client End Point.
     */
    public String getDigitalPenClientEndPoint() {
        return getProperty(DIGITAL_PEN_CLIENT_END_POINT);
    }
     
    /**
     * Get Cml Nta Active Changes
     * 
     * @return true  if active
     *         false if otherwise 
     */
    public boolean isCmlNtaActiveChanges() {
        return getBooleanProperty(CML_NTA_ACTIVECHAGES);
    }

    /**
     * Get if Ws Operation Logs is Active
     * 
     * @return true  if Ws Operation Logs is Active
     */
    public boolean isWsOperationLogsActive() {
        return getBooleanProperty(WS_OPERATION_LOGS_ACTIVE);
    }

    /**
     * Get if Ws Rest Logs is Active
     * 
     * @return true  if Ws Rest Logs is Active
     */
    public boolean isWsRestLogsActive() {
        return getBooleanProperty(WS_REST_LOGS_ACTIVE);
    }
    
    /**
     * Get Fedex Client End Point.
     * 
     * @return Fedex Client End Point.
     */
    public String getFedexClientEndPoint() {
        return getProperty(FEDEX_END_POINT);
    }
    
    /**
     * Get Allowed Preservation Expiry Period.
     * 
     * @return Allowed Preservation Expiry Period.
     */
    public int getAllowedPreservationExpiryPeriod() {
        return getIntProperty(ALLOWED_PRESERVATION_EXPIRY_PERIOD);
    }
    
    
    /**
     * Get if Br_050_014 Active.
     * 
     * @return true if Active Business Rule SPL_050_014
     */
    public boolean isActiveBr_050_014() {
        return getBooleanProperty(ACTIVE_BR_SPL_050_014);
    }
    
    /**
     * Get Old Plates Comparison Date
     * 
     * @return Old Plates Comparison Date.
     */
    public String getOldPlatesComparisonDate() {
        return getProperty(OLD_PLATES_COMPARISON_DATE);
    }
    
    /**
     * Get Electronic Insurance Phase2 Cut Off Date.
     * 
     * @return Electronic Insurance Phase2 Cut Off Date.
     */
    public Date getElectronicInsurancePhase2CutOffDate() {
        return getDateProperty(ELECTRONG_INSURANCE_PHASE2_CUT_OFF_DATE);
    }
    
    /**
     * Get Deg Validation Services
     * 
     * @return Deg Validation Services
     */
    public String getDegValidationServices() {
        return getProperty(DEG_STATUS_VALIDATION_SERVICES); 
    }
    
    /**
     * Get Driving Institute Service User Name
     * 
     * @return Driving Institute Service User Name
     */
    public String getDrivingInstituteServiceUserName() {
        return getProperty(DRIVING_INSTITUTE_SERVICE_USER_NAME);
    }
    
    /**
     * Get Driving Institute Service User Password
     * 
     * @return Driving Institute Service User Password
     */
    public String getDrivingInstituteServiceUserPassword() {
        return getProperty(DRIVING_INSTITUTE_SERVICE_PASSWORD);
    }

    /**
     * Check if is enhanced UTS Reports Service.
     *
     * boolean true if is enhanced UTS Reports Service.
     */
    public boolean isEnhancedUTSReportsWS() {
        return getBooleanProperty(UTS_REPORTS_SERVICE_ENHANCED_FLAG);
    }
    
    /**
     * To Disable/Enable database logging on Trace Log applicable table.
     * 
     * @return true or false.
     */
    public boolean enableTraceLog() {
        return getBooleanProperty(APPLICABLE_TRACE_LOGS);
    }

    /**
     * To Disable/Enable Trs Slowness Followup
     * 
     * @return true or false.
     */
    public boolean enableTrsSlownessFollowupTraceLog() {
        return getBooleanProperty(ENABLE_TRS_SLOWNESS_FOLLOWUP);
    }
    
    /**
     * Get Fee Of Delayed Period For Heavy Vehicle
     * 
     * @return Fee Of Delayed Period For Heavy Vehicle
     */
    public long getFeeOfDelayedPeriodForHeavyVehicle() {
        return getLongProperty(FEE_OF_DELAYED_PERIOD_FOR_HEAVY_VEHICLE);
    }
    /**
     * Get Default Thermal Printer Name.
     * 
     * @return Default Thermal Printer Name.
     */
    public String getDefaultThermalPrinterName() {
        return getProperty(Default_Thermal_Printer_Name);
    }
    
    /**
     * Get Approved Ftf Centers .
     * 
     * @return Approved Ftf Centers .
     */
    public String getApprovedFtfCenters() {
        return getProperty(APPROVED_FTF_CENTERS);
    }

    
    /**
     * Get Eye Test Result Customer Allowed Image Extension
     * @return Allowed Image Extension
     */
    public Set getEyeTestCustomerAllowdImg() {
        String imageExt = getProperty(LOCAL_EYE_TEST_CUSTOMER_ALLOWED_IMG_EXTENSION);
        if(GlobalUtilities.isBlankOrNull(imageExt)) {
            return new HashSet();
        }
        
        String [] imageExtArr = new String[1];
        
        if (imageExt.indexOf(",") == -1)  {
            imageExtArr[0] = imageExt;
        } else {
            imageExtArr = imageExt.split(",");
        }
        
        Set imageExteSet = new HashSet();
        for (int i = 0; i < imageExtArr.length; i++) {
            
            imageExteSet.add(new String(imageExtArr[i]));
        }
        
        return imageExteSet;
        

    }

    
    public Integer getMailDeliveryInformationTimeout() {
        return new Integer(1000*getIntProperty(MAIL_DELIVERY_INFORMATION_TIME_OUT));
    }


     /**
     * Get Deg Skipped Codes
     * @return Get Deg Skipped Codes
     */
    public Set getDegSkippedCodes() {
        
        String strDegSkippedCodes = getProperty(DEG_SKIPPED_CODES);
        
        if (GlobalUtilities.isBlankOrNull(strDegSkippedCodes)) {
            return new HashSet();
        }
        
        String [] arrayDegSkippedCodes = new String[1];
        
        if (strDegSkippedCodes.indexOf(",") == -1)  {
            arrayDegSkippedCodes[0] = strDegSkippedCodes;
        } else {
            arrayDegSkippedCodes = strDegSkippedCodes.split(",");
        }
        
        Set degSkippedCodes = new HashSet();
        
        for (int i = 0; i < arrayDegSkippedCodes.length; i++) {
            
            degSkippedCodes.add(new Integer(arrayDegSkippedCodes[i]));
        }
        
        return degSkippedCodes;
    } 
    
    /**
     * Get Manipulation Ticket Service End Point
     * 
     * @return Manipulation Ticket Service End Point.
     */
    public String getManipulationTicketServiceEndPoint() {
        return getProperty(MANIPULATION_TICKET_SERVICE);
    }
    
    /**
     * Get Eye Test Result Customer Allowed Image Extension
     * @return Allowed Image Extension
     */
    public Integer getEyeTestCustomerAllowdImgSize() {
        return new Integer(1024*1024*(getIntProperty(LOCAL_EYE_TEST_CUSTOMER_ALLOWED_IMG_SIZE)));
    } 
    
    /**
     * Get Manual Max Attachment Size In Bytes.
     * 
     * @return Manual Max Attachment Size In Bytes.
     */
    public Integer getManualMaxAttachSizeInByte() { 
        return new Integer(1024*1024*(getIntProperty(MANUAL_MAX_ATTACH_SIZE))); 
    }
    
    /**
     * Get Manual Max Attachment Size In Mega Bytes.
     * 
     * @return Manual Max Attach Size In Mega Bytes.
     */
    public Integer getManualMaxAttachSizeInMegaByte() {
        return new Integer(getIntProperty(MANUAL_MAX_ATTACH_SIZE));
    }
    
    public String getMoveEPSToDeliveryStepProcessorStartTime() {
        return getProperty(MOVE_EPS_TO_DELIVERY_STPE_PROCESSOR_STRAT_TIME);
    }
    
    /**
     * Is BR_PLT021_MER Enabled or not
     * 
     * @return true if BR_PLT021_MER is enabled else it return false 
     */
    public boolean isBR_PLT021_MEREnabled() {
        return getBooleanProperty(IS_BR_PLT021_MER_ENABLED_FLAG);
    }
    
    /**
     * Is BR_PLT022_MER Enabled or not
     * 
     * @return true if BR_PLT022_MER is enabled else it return false 
     */
    public boolean isBR_PLT022_MEREnabled() {
        return getBooleanProperty(IS_BR_PLT022_MER_ENABLED_FLAG);
    }

    /**
     * Get Approved FTF Centers.
     * 
     * @return Get Approved FTF Centers.
     */
    public String getApprovedVhlFtfCenters() {
        return getProperty(APPROVED_VHL_FTF_CTR);    
    }

    
    /**
     * Get Eye Test Allowed Size
     * 
     * @return Eye Test Allowed Image Size
     */
    public String getEyeTestAllowdImgSize() {
        return getProperty(LOCAL_EYE_TEST_CUSTOMER_ALLOWED_IMG_SIZE);
    }
    
    /**
     * Get Eye Test Result Customer Allowed Image Extension
     * @return Allowed Image Extension
     */
    public String getEyeTestAllowdImg() {
        return getProperty(LOCAL_EYE_TEST_CUSTOMER_ALLOWED_IMG_EXTENSION);
    }
    public Integer getDubaiHealthAuthorityCTRID() { 
      return new Integer(getIntProperty(DUBAI_HEALTH_AUTHORITY_CTR_ID));
    }

    /**
     * Get Clearance Delay Fees
     * @return Clearance Delay Fees
     */
    public int getClearanceDelayFees() {
        return getIntProperty(CLEARANCE_DELAY_FEE);
    }
    
   /**
     * Apply Medical Assessment in renwal license
     *
     *  boolean true if Medica Assessment is enabled.
     */
    public boolean applyMedicalAssessmentTestOnSvcCode103() {
        return getBooleanProperty(APPLY_MEDICAL_ASSESSMENT_RENEWAL_LICENSE);
    }   
    
  /**
     * Apply Medical Assessment in Modify Classes
     *
     *  boolean true if Medica Assessment is enabled.
     */
    public boolean applyMedicalAssessmentTestOnSvcCode4() {
        return getBooleanProperty(APPLY_MEDICAL_ASSESSMENT_MODIFY_CLASSES);
    }
    
  /**
     * Apply Medical Assessment in Renewal Learn Permits
     *
     *  boolean true if Medica Assessment is enabled.
     */
    public boolean applyMedicalAssessmentTestOnSvcCode5() {
        return getBooleanProperty(APPLY_MEDICAL_ASSESSMENT_RENEWAL_LEARN_PERMITS);
    }
    
  /**
     * Apply Medical Assessment in License Applications
     *
     *  boolean true if Medica Assessment is enabled.
     */
    public boolean applyMedicalAssessmentTestOnSvcCode1() {
        return getBooleanProperty(APPLY_MEDICAL_ASSESSMENT_LICENSE_APPLICATIONS);
    }
    
  /**
     * Apply Medical Assessment in Issue Learning Permits
     *
     * boolean true if Medica Assessment is enabled.
     */
    public boolean applyMedicalAssessmentTestOnSvcCode2() {
        return getBooleanProperty(APPLY_MEDICAL_ASSESSMENT_ISSUE_LEARNING_PERMITS);
    }
    
  /**
     * Apply Medical Assessment in Women Permit
     *
     *  boolean true if Medica Assessment is enabled..
     */
    public boolean applyMedicalAssessmentTestOnSvcCode3() {
        return getBooleanProperty(APPLY_MEDICAL_ASSESSMENT_WOMEN_PERMIT);
    }
    
  /**
     * Apply Medical Assessment in Reissuel Learn Permits
     *
     * boolean true if Medica Assessment is enabled.
     */
    public boolean applyMedicalAssessmentTestOnSvcCode6() {
        return getBooleanProperty(APPLY_MEDICAL_ASSESSMENT_REISSUEL_LEARN_PERMITS);
    }  
    
    /**
     * Get Number Of Canceled Black Points Per Course.
     * 
     * @return Number Of Canceled Black Points Per Course.
     */
    public Integer getNumberOfCanceledBlackPointsPerCourse() {
        return new Integer(getIntProperty(NUMBER_OF_CANCELED_BLACK_POINTS_PER_COURSE));
    }        
    
    /**
     * Get Vcn File max Upload
     * @return Vcn File max Upload
     */
    public int getVcnFileMaxUpload() {
        return getIntProperty(VCN_MAX_UPLOAD_SIZE);
    }
    
    /**
     * Get Search For Booked VehMsg
     * @return Search For Booked VehMsg
     */
    public String getSearchForBookedVehMsg() { 
        return getProperty(SEARCH_BOOKED_VEH_MSG);  
    }

    /**
     * Get Not Allowed Service Code Cash Payment 
     * 
     * @return Not Allowed Service Code Cash Payment 
     */
    public String getNotAllowedServiceCodeCashPayment() { 
        return getProperty(NOT_ALLOWED_SERVICE_CODE_CASH_PAYMENT);  
    }

    /**
     * Min Allowed Registered Vehilce to Cash payment 
     * 
     * @return Min Allowed Registered Vehilce to Cash payment 
     */
    public int getMinAllowedRegisteredVehicleNumber() { 
        return getIntProperty(MIN_ALLOWED_REGISTERED_VEHICLE);
    }
    
    /**
     * Get Initial Noc Template Codes
     * 
     * @return Initial Noc Template Codes
     */
    public String getInitialNocTemplateCodes() { 
        return getProperty(INITIAL_NOC_TEMPLATE_CODES);   
    }      

    /**
     * Allowed Center To Issue Releas Order
     * 
     * @return Allowed Center To Issue Releas Order
     */
    public String getAllowedCenterToIssueReleasOrder() { 
        return getProperty(ALLOWED_CENTER_TO_ISSUE_RELEAS_ORDER);  
    }  
    
    /**
     * Active Person Project
     * 
     * @return true to show tab , false to hide tab
     */
    public boolean getActivePearsonProject() { 
        return getBooleanProperty(ACTIVE_PEARSON_PROJECT);  
    } 
    
    /**
     * Get Delegation Password Reset Max Usage.
     *
     * @return Delegation Password Reset Max Usage.
     */
    public int getDelegationPasswordResetMaxUsage() {
        return getIntProperty(DELEGATION_PASSWORD_RESET_MAX_USAGE);
    }

    /**
     * Get RTA Receipt Header Image Name.
     * 
     * @return RTA Receipt Header Image Name.
     */
    public String getRTAReceiptHeaderImageName() {
        return getProperty(RTA_RECEIPT_HEADER_IMAGE_NAME);
    }

    /**
     * Get RTA Trs Rta 3d Channel Codes
     * 
     * @return RTA Trs Rta 3d Channel Codes
     */
    public String getRTATrsRta3dChannelCodes() {
        return getProperty(RTA_TRS_RTA_3D_CHANNEL_CODES);
    } 
    
   /**
    * Get Original exam time per second.
    * 
    * @return Original exam time per second.
    */
    public String getOriginalExamTimePerSecond() {
        return getProperty(RTA_DTT_ORIGINAL_EXAM_TIME_PER_SECOND);
    }    

   /**
    * Get RTA Dtt Warning Milli Seconds
    * 
    * @return RTA Dtt Warning Milli Seconds
    */
    public String getRTADttWarningMilliSeconds() {
        return getProperty(RTA_DTT_WARNING_MILLI_SECONDS);
    } 

   /**
    * Get RTA DTT Theorytest Refresh Millis
    * 
    * @return RTA DTT Theorytest Refresh Millis
    */
    public String getRTADttTheorytestRefreshMillis() {
        return getProperty(RTA_DTT_THEORYTEST_REFRESH_MILLIS);
    }    


    /**
     * Get RTA DTT Exam Start Refresh Millis
     * 
     * @return RTA DTT Exam Start Refresh Millis
     */
    public String getRTADttExamStartRefreshMillis() {
        return getProperty(RTA_DTT_EXAM_START_REFRESH_MILLIS);
    }   

    /**
     * Get Change Device Refresh Peroid
     * 
     * @return Change Device Refresh Peroid
     */
    public String getChangeDeviceRefreshPeroid() {
        return getProperty(RTA_DTT_CHANGE_DEVICE_REFRESH_PEROID);
    } 

    /**
     * Get Exam Finished Refresh Peroid
     * 
     * @return Exam Finished Refresh Peroid
     */
    public String getExamFinishedRefreshPeroid() {
        return getProperty(RTA_DTT_EXAM_FINISHED_REFRESH_PEROID);
    } 
    /**
     * Get Touch Message Screen Time out
     * 
     * @return Touch Message Screen Time out
     */
    public String getTouchMessageScreenTimeOut() {
        return getProperty(RTA_DTT_TOUCH_MESSAGE_SCREEN_TIME_OUT);
    } 
    
    /**
     * Get Warning Expiry Time Per Second.
     * 
     * @return Original exam time per second.
     */

    public String getWarningExpiryTimePerSecond() {
        return getProperty(RTA_DTT_WARNING_EXPIRY_TIME_PER_SECOND);
    }
    /**
     * Get DED Not A Manager Codes
     * 
     * @return DED Not A Manager Codes
     */

    public String getDEDNotAManagerCodes() {
        return getProperty(NOT_A_MANAGER_CODES);
    }  

    /**
     * Get Activate Mortgage BlackList Cutoff Date
     * 
     * @return Activate Mortgage BlackList Cutoff Date
     */
    public Date getMortgageBlackListCutoffDate(){
    
        return getDateProperty(MORTGAGE_BLACKLIST_CUTOFF_DATE);
    
    }
    
    /**
     * get Successfull License Application Validity Months
     * 
     * @return  Successfull License Application Validity Months
     */
    public Integer getSuccessfulLicenseApplicationValidityMonths() {
        return getIntegerProperty(SUCCESSFULL_LICENSE_APPLICATION_VALIDITY_MONTH);
    }
    
    /**
     * Get CIS Check Url.
     * 
     * @return CIS Check Url
     */
    public String getCISCheckURL() {
        return getProperty(CIS_CHECK_URL);
    }  
    /** Get Number Of Allowed Receipt Updates
     * 
     * @return Number Of Allowed Receipt Updates
     */
    public Integer getNumberOfAllowedReceiptUpdates() {
        return getIntegerProperty(NUMBER_OF_ALLOWED_RECEIPT_UPDATES);
    }
    
    /**
     * Get CIS Manua lCertificate Scan Path.
     * 
     * @return CIS Manua lCertificate Scan Path.
     */

    public String getCISManualCertScanPath() {
        return getProperty(CIS_MANUAL_CERT_SCAN_PATH);

    }
    /**
     * Get Booklet Extended Expiry Peroid In Months
     * 
     * @return Booklet Extended Expiry Peroid In Months
     */
    public Integer getBookletExtendedExpiryPeroidInMonths() {
        return getIntegerProperty(BOOKLET_EXTENDED_EXPIRY_PERIOD_IN_MONTHS);
    }
    
    /**
     * Get VCC Username
     * 
     * @return vcc username
     */
    public String getVccUsername() {
        return getProperty(VCC_USER_NAME);
    }
    
    /**
     * Get VCC password
     * 
     * @return vcc password
     */
    public String getVccPassword() {
        return getProperty(VCC_PASSWORD);
    }
    
    /**
     * Get auto Distribute Exception Request.
     * 
     * @return auto Distribute Exception Request.
     */
    public boolean getAutoDistributeExceptionRequest() {
        return getBooleanProperty(AUTO_DISTRIBUTE_EXCEPTION_REQUEST);
    }
      
    /**
     * Get start night shift
     * 
     * @return start night shift
     */
    public String getStartNightShift() {
        return getProperty(START_NIGHT_SHIFT);
    }
    
    /**
     * Get end night shift
     * 
     * @return end night shift             
     */
    public String getEndNightShift() {
        return getProperty(END_NIGHT_SHIFT);
    }
    
    /**
     * Get Not Assigned Approved Templates
     * 
     * @return Not Assigned Approved Templates.
     */
    public String getNotAssignedApprovedTemplates() {
        return getProperty(NOT_ASSIGNED_APPROVED_TEMPLATES);
    }
    
    /**
     * Get vhl activate exception system.
     * 
     * @return value of vhl activate exception system.
     */
    public boolean getVHLActivateExceptionSystem() {
        return getBooleanProperty(VHL_ACTIVATE_EXCEPTION_SYSTEM);
    }
    
    /**
     * Get DTT activate exception system.
     * 
     * @return value of DTT activate exception system.
     */
    public boolean getDTTActivateExceptionSystem() {
        return getBooleanProperty(DTT_ACTIVATE_EXCEPTION_SYSTEM);
    }
    
    /**
     * Get SPL activate exception system.
     * 
     * @return value of SPL activate exception system.
     */
    public boolean getSPLActivateExceptionSystem() {
        return getBooleanProperty(SPL_ACTIVATE_EXCEPTION_SYSTEM);
    }
    
    /**
     * Get DRL activate exception system.
     * 
     * @return value of DRL activate exception system.
     */
    public boolean getDRLActivateExceptionSystem() {
        return getBooleanProperty(DRL_ACTIVATE_EXCEPTION_SYSTEM);
    }
    
    /**
     * Get CML activate exception system.
     * 
     * @return value of CML activate exception system.
     */
    public boolean getCMLActivateExceptionSystem() {
        return getBooleanProperty(CML_ACTIVATE_EXCEPTION_SYSTEM);
    }
    
    /**
     * Getter For Heavy Vehicle Classes Ids
     * @return  String contains HeavyVehicleClassesIds
     */
    public String getHeavyVehicleClassesIds(){
     return  getProperty(HEAVY_VEHICLE_CLASSES_IDS);
    }
    
    /**
     * Get Sddi Host
     * 
     * @return Sddi Esrv Host (development/testing/staging/traffic)
     */
    public String getSddiEsrvHost() {
        return getProperty(SDDI_ESRV_HOST);    
    }
    
    /**
     * Get Black Points Course Arabic Location.
     * 
     * @return Black Points Course Arabic Location.
     */
    public String getBlackPointsCourseArabicLocation() {
        return getProperty(BLACK_POINTS_COURSE_ARABIC_LOCATION);
    }
    
    /**
     * Get Black Points Course English Location.
     * 
     * @return Black Points Course English Location.
     */
    public String getBlackPointsCourseEnglishLocation() {
        return getProperty(BLACK_POINTS_COURSE_ENGLISH_LOCATION);
    }    
    
    /**
     * Get cutoff date of employees
     * 
     * @return cutoff date of employees.
     */
    public Date getCutoffDateOfEmployees() {
        return getDateProperty(CUTOFF_DATE_OF_EMPLOYEES);
    }
    
    /**
     * Get Skipped Sensitive Urls.
     * 
     * @return Skipped Sensitive Urls.
     */
    public String getSkippedSensitiveUrls() { 
        return getProperty(SKIPPED_SENSITIVE_URLS);    
    }
    
    /**
     * Get Renew Licens Minimum Person Age
     * 
     * @return Renew Licens Minimum Person Age
     */
    public Integer getRenewLicenseMinimumPersonAge() {        
        return getIntegerProperty(RENEW_LICENSE_MINIMUM_PERSON_AGE);
    }    
    /**
     * Get Min Allowed Year Registertion
     * 
     * @return Min Allowed Year Registertion
     */
    public Integer getMinAllowedYearRegistertion() {        
        return getIntegerProperty(MIN_ALLAOWED_YEAR_REGISTERATION);
    } 
    
    
    /**
     * Check if is registeration coupled to sale vehicle sales transaction
     *
     * boolean true if is registeration coupled to sale vehicle sales transaction
     */
    public boolean isCoupledToVehicleSalesTransaction() {
        return getBooleanProperty(IS_COUPLED_SALES_TRANSACTION);
    }
    
     /**
     * Check if is Ownership coupled to sale vehicle sales transaction
     *
     * boolean true if is Ownership coupled to sale vehicle sales transaction
     */
    public boolean isTransferOwnershipCoupledToSalesTransaction() {
        return getBooleanProperty(IS_TRANSFER_OWNERSHIP_COUPLED_TO_SALES_TRANSACTION);
    }

     /**
     * Check if is Registeration coupled to sale vehicle sales transaction
     *
     * boolean true if is Registeration coupled to sale vehicle sales transaction
     */
    public boolean isRegisterationCoupledToSalesTransaction() {
        return getBooleanProperty(IS_REGISTERATION_COUPLED_TO_SALES_TRANSACTION);
    }
    
     /**
     * Check if is Possession coupled to sale vehicle sales transaction
     *
     * boolean true if is Possession coupled to sale vehicle sales transaction
     */
    public boolean isPossessionCoupledToSalesTransaction() {
        return getBooleanProperty(IS_POSSESSION_COUPLED_TO_SALES_TRANSACTION);
    }
    
    /**
     * Check if is Export coupled to sale vehicle sales transaction
     *
     * boolean true if is Export coupled to sale vehicle sales transaction
     */
    public boolean isExportCoupledToSalesTransaction() {
        return getBooleanProperty(IS_EXPORT_COUPLED_TO_SALES_TRANSACTION);
    }
    
     /**
     * Check if is Transfer coupled to sale vehicle sales transaction
     *
     * boolean true if is Transfer coupled to sale vehicle sales transaction
     */
    public boolean isTransferToAnotherEmirateCoupledToSalesTransaction() {
        return getBooleanProperty(IS_TRANSFER_TO_ANOTHER_EMIRATE_COUPLED_TO_SALES_TRANSACTION);
    }
    
  
    /**
     * Check if is Transfer Possession coupled to sale vehicle sales transaction
     *
     * boolean true if is Transfer Possession coupled to sale vehicle sales transaction
     */
    public boolean isTransferPossessionCoupledToSalesTransaction() {
        return getBooleanProperty(IS_TRANSFER_POSSESSION_COUPLED_TO_SALES_TRANSACTION);
    }
    
     /**
     *check the Flag to toggle the necessity of a vehicle inspection before a sales transaction
     *
     * boolean true if  toggle the necessity of a vehicle inspection before a sales transaction 
     */
    public boolean isInspectionBeforeSalesTransaction() {
        return getBooleanProperty(INSPECTION_BEFORE_SALES_TRANSACTION);
    }
    /**
     * Get defult Center Code
     * 
     * @return defult Center Code
     */
    public String getCenterCode() {       
        return getProperty(DEFAULT_CENTER_CODE);
    }                                      
                  
    /**
     * Get Person Age Need Parents Info
     * 
     * @return long value of person age need parents info 
     */
    public long getPersonAgeNeedParentsInfo(){
        return getLongProperty(PERSON_AGE_NEED_PARENTS_INFO);
    }
    
    /**
     * Check if the unlink services is active or not.
     * <p>Default is not active.
     * 
     * @return true if active.
     */
    public boolean isUnlinkServicesActive() {
        return getBooleanProperty(UNLINK_SERVICES_ACTIVATION_FLAG); 
    }
    
    /**
     * Active Parent Notification
     *
     * boolean true if  Active Parent Notification 
     */
    public boolean isActiveParentNotification() {
        return getBooleanProperty(ACTIVE_PARENT_NOTIFICATION);
    }  
    
    /**
     * Send Receipt By Email
     *
     * boolean true if  Send Receipt By Email
     */
    public boolean sendReceiptByEmail() {
        return getBooleanProperty(SEND_RECEIPT_BY_EMAIL);
    }   
    
    /**
     * skip Br Trs013Mer For Positive
     *
     * boolean true or false.
     */
    public boolean skipBrTrs013MerForPositive() {
        return getBooleanProperty(SKIP_BR_TRS013_MER_FOR_POSITIVE);
    }  

    /**
     * Get Numbers Of Emplyees Loaned To RTA
     * 
     * @return Coma Sperator List of employees loaned to RTA.
     */
    public String getNumbersOfEmplyeesLoanedToRTA() {
        return getProperty(EMPLOYEES_MILITARY_NO_LOANED_TO_RTA);
    }        
    
    /**
     * Get Substitutable Licence Classes
     * 
     * @return Substitutable Licence Classes
     */
    public String getSubstitutableLicenceClasses() {
        return getProperty(SUBSTITUTABLE_LICENSE_CLASSES);        
    }

    /**
     * Disable And Enable E-Traffic Threads.
     * 
     * @return true to Enable and false Disable.
     */
    public boolean enableETrafficThreads() {
        return getBooleanProperty(ENABLE_E_TRAFFIC_THREADS);
    }
    
    /**
     * Get send notification service endpoint.
     * 
     * @return send notification service endpoint
     */
    public String getSendNotificationEndPoint() {
        return getProperty(SEND_NOTIFICATION_SERVICE_ENDPOINT);
    }
    /**
     * Get Issue License Classes From Test Approved Channels.
     * 
     * @return 
     */
    public String getIssueLicenseClassesFromTestApprovedChannels(){
        return getProperty(ISSUE_LICENSE_CLASSES_FROM_TEST_APPROVED_CHANNELS);
    }

    /**
     * Get Issue License Classes From Replacement Approved Channels.
     * 
     * @return 
     */
    public String getIssueLicenseClassesFromReplacementApprovedChannels(){
        return getProperty(ISSUE_LICENSE_CLASSES_FROM_REPLACEMENT_APPROVED_CHANNELS);
    }

     
    /**
     * Get traffic threads application IP.
     * 
     * @return traffic threads application IP.
     */
    public String getTrafficThreadsIP() {
        return getProperty(TRAFFIC_THREADS_IP);
    }
    
    /**
     * Get enable traffic threads application.
     * 
     * @return if traffic threads application is enabled.
     */
    public boolean getIsTrafficThreadsEnabled() {
        return getBooleanProperty(IS_TRAFFIC_THREADS_ENABLED);
    }
    
    /**
     * Get Allowed Users For View Tickets Elements Property.
     * 
     * @return Allowed Users For View Tickets Elements Property.
     */
    public String getAllowedUsersForViewTicketsElements() {
        return getProperty(ALLOWED_USERS_FOR_VIEW_TICKETS_ELEMENTS);
    }
    
    /**
     * Get FFU Transaction Service End Point 
     * 
     * @return FFU Transaction Service End Point 
     */
    public String getFFUTransactionServiceEndPoint() {
        return getProperty(FFU_TRANSACTION_SERVICE);
    }  
   
    /**
     * Get enable XSS remove filter
     * 
     * @return if XSS remove filter is enabled.
     */
    public boolean  isXSSRemovalFilterActive(){
        return getBooleanProperty(ENABLE_XSS_REMOVE_FILTER);
    }
    
    /**
     * Get Issue Permit Category Allowed Eps Code.
     * 
     * @return array of Issue Permit Category Execlude Eps Code
     */
    public String[] getIssuePermitCategoryAllowedEpsCode() {
        String values = getProperty(ISSUE_PERMIT_CATEGORY_ALLOWED_EPS_CODE);
        String []valuesArr = null;
        if(!GlobalUtilities.isBlankOrNull(values)) { 
            if(values.indexOf(",") != -1) {
                valuesArr = values.split(",");
            } else {
                valuesArr = new String[]{values};
            }
        }
        return valuesArr;    
    }


    /**
     * Get Reports server web host
     * 
     * @return Reports server web host
     */
    public String getReportsServerWebHost() {
        return getProperty(REPORTS_SERVER_WEB_HOST);
    }
    /**
     * Get Reports server Internal web host
     * 
     * @return Reports server Internal web host
     */
    public String getReportsServerInternalWebHost() {
        return getProperty(REPORTS_SERVER_INTRANET_WEB_HOST);
    }
    
    /**
     * Get Reports server RDF port
     * 
     * @return Reports server port
     */
    public String getReportsServerRDFPort() {
        return getProperty(REPORTS_SERVER_RDF_PORT);
    }
    /**
     * Get Reports server RDF port
     * 
     * @return Reports server port
     */
    public String getReportsServerInternalRDFPort() {
        return getProperty(REPORTS_SERVER_INTRANET_RDF_PORT);
    }
    
    /**
     * get Task Minutes Of Moving Eps Modify Permit App.
     * 
     * @return Minutes.
     */
    public Integer getTaskMinutesOfMovingEpsModifyPermitApp() {
        return getIntegerProperty(MOVING_EPS_OF_MODIFY_PERMIT_APP_TASK_MINUTES);
    }
    
    /**
     * is Moving Eps Modify Permit App Task Active.
     * 
     * @return true if active.
     */
    public boolean isMovingEpsModifyPermitAppTaskActive() {
        return getBooleanProperty(MOVING_EPS_OF_MODIFY_PERMIT_APP_TASK_ACTIVE);
    }
    
    /**
     * Get Portal Profile End Point
     * 
     * @return Portal Profile End Point
     */
    public String getPortalProfileEndPoint() {
        return getProperty(PORTAL_PROFILE_SERVICE_END_POINT);
    }
    
    /**
     * Get Card Image Service End Point
     * 
     * @return Card Image Service End Point 
     */
    public String getCardImageServiceEndPoint(){
        return getProperty(CARD_IMAGE_SERVICE_END_POINT);
    }
    
    /**
     * Get User Profile User Name
     * 
     * @return PORTAL_PROFILE_SERVICE_USER_NAME 
     */
    public String getUserProfileUserName(){
        return getProperty(PORTAL_PROFILE_SERVICE_USER_NAME);
    }
    
    /**
     * Get User Profile Password
     * 
     * @return PORTAL_PROFILE_SERVICE_PASSWORD
     */
    public String getUserProfilePassword(){
        return getProperty(PORTAL_PROFILE_SERVICE_PASSWORD);
    }
    
    
    /**
     * Get Registration Card Layout Name
     * 
     * @return  Registration Card Layout Name  
     */
    public String getRegistrationCardLayoutName(){
        return getProperty(CARD_REGISTRATION_CARD_LAYOUT_NAME);
    }
    /**
     * Get Driving License Layout Name
     * 
     * @return Driving License Layout Name
     */
    public String getDrivingLicenseLayoutName(){
        return getProperty(DRIVING_LICENSE_LAYOUT_NAME);
    }
    
    /**
     * Get Private Vehicle Certificate Layout Name 
     * 
     * @return Private Vehicle Certificate Layout Name 
     */
    public String getPrivateVehicleCertificateLayoutName(){
        return getProperty(PRIVATE_VEHICLE_CERTIFICATE_LAYOUT_NAME);
    }
    
    /**
     * Get Motor Cycle Layout Name
     * 
     * @return Motor Cycle Layout Name 
     */
    public String getMotorCycleLayoutName(){
        return getProperty(MOTORCYCLE_CERTIFICATE_LAYOUT_NAME);
    }
    
    /**
     * Get Entertainment Motor Certificate Layout Name 
     * 
     * @return Entertainment Motor Certificate Layout Name  
     */
    public String getEntertainmentMotorCertificateLayoutName(){
        
        return getProperty(ENTERTAINMENT_MOTOR_CERTIFICATE_LAYOUT_NAME);
    }
    
    /**
     * Get Classic Car Certifcate Layout Name
     * 
     * @return Classic Car Certifcate Layout Name
     */
    public String getClassicCarCertificateLayoutName(){
        return getProperty(CLASSIC_CAR_CERTIFICATE_LAYOUT_NAME);
    }
    
    /**
     * Get Image Card Service User Name 
     * 
     * @return Image Card Service User Name 
     */
    public String getImageCardUserName(){
        return getProperty(IMAGE_CARD_SERVICE_USER_NAME);
    }
    /**
     * Get Image Card Service Password 
     * 
     * @return Image Card Service Password 
     */
    public String getImageCardPassword(){
        return getProperty(IMAGE_CARD_SERVICE_PASSWORD);
    }
 
    /**
     * Get Number Of Dayas Allowed To Complete Loss Dmg Exports Certificate.
     * 
     * @return Number Of Dayas Allowed To Complete Loss Dmg Exports Certificate
     */
    public long getNumberOfDayasAllowedToCompleteLossDmgExportsCertificate() {
        return getLongProperty(NUMBER_OF_DAYS_ALLOWED_TO_COMPLETE_LOSS_DMG_EXPORTS_CERTIFICATE);
    }
    
    /**
     * Get Active Send Push Notification
     * 
     * @return if the value is true
     */
    public boolean getActiveSendPushNotification() {
        return getBooleanProperty(ACTIVE_SEND_PUSH_NOTIFICATION);
    }    

    /**
     * Get Epay Service Password 
     * 
     * @return Epay Service Password
     */
    public String getEpayServicePassword(){
        return getProperty(EPAY_SERVICE_PASSWORD);
    }

    /**
     * Get Epay Service End Point
     * 
     * @return Epay Service End Point
     */
    public String getEpayServiceEndPoint(){
        return getProperty(EPAY_SERVICE_END_POINT);
    }
    /**
     * Get Activate Session Management
     * 
     * @return if the value is true
     */
    public boolean getActivateSessionManagement() {
        return getBooleanProperty(ACTIVATE_SESSION_MANAGEMENT);
    }

    /**
     * Get Activate Session Management Login Forward
     * 
     * @return if the value is true
     */
    public boolean getActivateSessionManagementLoginForward() {
        return getBooleanProperty(ACTIVATE_SESSION_MANAGEMENT_LOGIN_FORWARD);
    }    

    /**
     * Direct Selling Auction LCD Group Notification Body
     * 
     * @return Direct Selling Auction LCD Group Notification Body
     */
    public String getDirectSellingAuctionNotificationBody() {
        return getProperty(DIRECT_SELLING_AUCTION_NOTIFICATION_MESSAGE_BODY);
    }

    /**
     * Direct Selling Auction LCD Group Notification Subject
     * 
     * @return Direct Selling Auction LCD Group Notification Subject
     */
    public String getDirectSellingAuctionNotificationSubject() {
        return getProperty(DIRECT_SELLING_AUCTION_NOTIFICATION_MESSAGE_SUBJECT);
    }    

    /**
     * get Pro Allowance Date
     * 
     * @return get Pro Allowance Date.
     */
    public Date getProAllowanceDate() {
        return getDateProperty(PRO_ALLOWANCE_DATE);
    }
    
    /**
     * get Pro Minimum Number Allowed Vehicle.
     * 
     * @return Pro Minimum Number Allowed Vehicle. 
     */
    public Integer getProMinimumNumberAllowedVehicle() {
        return getIntegerProperty(PRO_MINIMUM_NUMBER_ALLOWED_VEHICLE);
    }   

    /**
     * Get Epay Service User Name 
     * 
     * @return Epay Service User Name
     */
    public String getEpayServiceUserName(){
        return getProperty(EPAY_SERVICE_USER_NAME);
    }

    /**
    * Get Allowed Issue Permit Period By Months.
    * 
    * @return Allowed Issue Permit Period By Months.
    */
    public int getIssuePermitAllowedPermitPeriod() {
        return getIntProperty(ISSUE_PERMIT_ALLOWED_PERMIT_PERIOD);
    }    
    
    /**
    * Get Allowed Renew Permit Period By Months.
    * 
    * @return Allowed Renew Permit Period By Months.
    */
    public int getRenewPermitAllowedPermitPeriod() {
        return getIntProperty(RENEW_PERMIT_ALLOWED_PERMIT_PERIOD);
    }        
    
    
    /**
    * Get Plate Package VO Arabic Description Default Label.
    * 
    * @return Plate Package VO Arabic Description Default Label.
    */
    public String getPlatePackageArDescLabel() {
        return getProperty(PLATE_PACKAGE_DESC_AR_LABEL);
    }         
    
    /**
    * Get Plate Package VO English Description Default Label.
    * 
    * @return Plate Package VO English Description Default Label.
    */
    public String getPlatePackageEnDescLabel() {
        return getProperty(PLATE_PACKAGE_DESC_EN_LABEL);
    }
    
    
    /**
    * Get Electonic Auction Package VO Arabic Description Default Label.
    * 
    * @return Plate Package VO Arabic Description Default Label.
    */    
    public String getAuctionPackageArDescLabel() {
        return getProperty(AUCTION_PACKAGE_DESC_AR_LABEL);
    }         
    
    /**
    * Get Electonic Auction Package VO English Description Default Label.
    * 
    * @return Plate Package VO English Description Default Label.
    */    
    public String getAuctionPackageEnDescLabel() {
        return getProperty(AUCTION_PACKAGE_DESC_EN_LABEL);
    }

    /**
     * Get Minumum Minute To Send Notification.
     * 
     * @return Minumum Minute To Send Notification
     */
    public String getMinumumMinuteToSendNotification() {
        return getProperty(MINIMUM_MINUTE_TO_SEND_NOTIFICATION);
    }    
    
    /**
     * Skip Pro Permit Person Age Validation flag
     * 
     * @return true if Person Age Validation is Skiped for PRO Permit
     */
    public boolean skipProPermitPersonAgeValidation() {
        return getBooleanProperty(TF_STP_SKIP_PRO_PERMIT_PERSON_AGE_VALIDATION);
	}

    /**
     * Get Change Ownership Not Owned Plate Status Values
     * 
     * @return Change Ownership Not Owned Plate Status Values 
     */
    public List getChangeOwnershipNotOwnedPlateStatusValues() {
        return getListProperty(CHANG_OWNERSHIP_NOT_OWNED_PLATE_STATUS_VALUES,",");
    }
    
    /**
     * Get Change Ownership Owned Plate Status Values
     * 
     * @return Change Ownership Owned Plate Status Values 
     */
    public List getChangeOwnershipOwnedPlateStatusValues() {
        return getListProperty(CHANG_OWNERSHIP_OWNED_PLATE_STATUS_VALUES,",");
    }

    /**
     * Change Ownership NOC Requet Validity Period
     * 
     * @return Change Ownership NOC Requet Validity Period
     */
    public String changeOwnershipNOCRequetValidityPeriod() {
        return getProperty(CHANGE_OWNERSHIP_NOC_PERIOD);
    }

    /**
     * Get Enable Validate Set Avaiable Delivery
     * 
     * @return Enable Validate Set Avaiable Delivery
     */
    public boolean getEnableValidateSetAvaiableDelivery(){
        return getBooleanProperty(ENABLE_VALIDATE_SETAVAIABLE_DELIVERY);
    }
    /**
     * Get Trainging Audit Center Code
     * 
     * @return Trainging Audit Center Code
     */
    public Integer getTraingingAuditCenterCode() {
        return getIntegerProperty(TRAINING_AUDIT_CENTER_CODE);
    }
    
    /**
     * Get Operation Logs Disabled For Services 
     * 
     * @return Operation Logs Disabled For Services Names
     */
    public String[] getOperationLogsEnabledForServices(){
       return getProperty(OPERATION_LOGS_ENABLED_FOR).split(",");
    }
    
    /**
     * Get Enable Issue Exam Appoint BrIEA019
     * 
     * @return true if this BR enabled 
     */
    public boolean getEnableIssueExamAppointBrIEA019(){
        return getBooleanProperty(ENABLE_ISSUE_EXAM_APPOINT_BR_IEA019);
    }
    
    /**
     * Get Enable Issue Exam Appoint BrIEA019
     * 
     * @return true if this BR enabled 
     */
    public boolean getEnableIssueExamAppointBrCEA024(){
        return getBooleanProperty(ENABLE_ISSUE_EXAM_APPOINT_BR_CEA024);
    }
    
    /**
     * Enable Csrf Attacks Handling
     * 
     * @return true if Enable Csrf Attacks is Enabled 
     */
    public boolean getEnableCsrfAttacksHandling( ) {
        return getBooleanProperty(ENABLE_CSRF_ATTACKS_HANDLING);
    }
    
    /**
     * Enable Smart Filters
     * 
     * @return true if Smart Filters are Enabled 
     */
    public boolean getEnableSmartFilters( ) {
        return getBooleanProperty(ENABLE_SMART_FILTERS);
    }
    

    /**
     * Get Activate Renew Instructor Permit
     * 
     * @return true if this BR enabled 
     */
    public boolean getActivateRenewInsPermit(){
        return getBooleanProperty(ACTIVATE_RENEW_INSTRUCTOR_PERMIT);
    }    

    
    /**
     * Enable Circular Note Service
     * 
     * @return true if Circular Note Service is Enabled
     */
    public boolean getEnableCircularNoteService() {
        return getBooleanProperty(ENABLE_CIRCULAR_NOTE_SERVICE);
    }
    
    public int getDsgDeliveryConfirmationProccessorDelay() {
        return getIntProperty(SERVICE_DELIVARY_PROCCESSOR_DELAY);
    } 
	
	/**
     * Get Dsg Delivery Confirmation Proccessor Max Trials.
     * 
     * @return Dsg Delivery Confirmation Proccessor Max Trials.
     */
    public int getDsgDeliveryConfirmationProccessorMaxTrials() {
        return getIntProperty(SERVICE_DELIVARY_PROCCESSOR_MAX_TRIALS);
    } 
    

    /**
     * Get Dsg Delivery Confirmation Proccessor Period in Hours.
     * 
     * @return Dsg Delivery Confirmation Proccessor Period Hours.
     */
    public int getDsgDeliveryConfirmationProccessorPeriod() {
        return getIntProperty(SERVICE_DELIVARY_PROCCESSOR_PERIOD);
    }
    
    /**
     * Get Monitoring Stock Of Drivers Handbooks Refresh Period.
     * 
     * @return Monitoring Stock Of Drivers Handbooks Refresh Period In Second.
     */
    public int getMonitoringStockOfDriversHandbooksRefreshPeriod() {
        return getIntProperty(MONITORING_STOCK_OF_DRIVERS_HANDBOOKS_REFRESH_PERIOD);
    }
    
	/**
     * Get Enable Dsg Delivery Confirmation Proccessor
     * 
     * @return Enable Dsg Delivery Confirmation Proccessor
     */
    public boolean getEnableDsgDeliveryConfirmationProccessor(){
        return getBooleanProperty(SERVICE_DELIVARY_PROCCESSOR_ENABLED);
    }
    
    /**
     * Payment Clearance Service URL
     * 
     * @return Payment Clearance Service URL
     */
    public String getPaymentClearanceServiceEndPoint() {
        return getProperty(PAYMENT_CLEARANCE_SERVICE_URL);
    }
    
    /**
     * Get Smart Screen User
     * 
     * @return Smart Screen User
     */
    public String getSmartScreenUser() {
        return getProperty(SMART_SCREEN_USER);
    }    

    /**
     * Is operation logs filter enabled
     * 
     * @return value of property true or false
     */
    public boolean isOperationLogsFilterEnabled(){
        return getBooleanProperty(IS_OPERATION_LOG_FILTER_ENABLED);
    }
    
    /**
     * is Enable Task Sddi-6325
     * 
     * @return value of property true or false 
     */
    public boolean isEnableTaskSddi6325() {
        return getBooleanProperty(ENABLE_TASK_SDDI_6325);       
    }
       
    /**
     * is Enable Task Sddi-6325
     * 
     * @return value of property true or false
     */
    public boolean isEnableTaskTrf16517() {
        return getBooleanProperty(ENABLE_TASK_TRF_16517);       
    }

    /**
     * Is Allowed tor rigester polaris vehicle
     * 
     * @return value of property true or false
     */
    public boolean isAllowedToRigsterPolarisVehicle(){
        return getBooleanProperty(ALLOW_REGISTER_POLARIS_VEHICLE); 
    }
    
    /**
     * get the boolean value ENABLE_UPDATE_INFO_MAIL_NOTIFICATION
     * @return true/false
     */
    public boolean isEnableUpdateInfoEmailNotification(){
        return getBooleanProperty(ENABLE_UPDATE_INFO_MAIL_NOTIFICATION);
    }
    
    /**	
     * is enable task TRF-17501
     * @return value of property true or false
     */
    public boolean isEnableTaskTRF17501(){
        return getBooleanProperty(IS_MORTGAGED_ORG_PLATE_CHANGED);
    }        
    
    /**	
     * is enable task TRF-31968
     * @return value of property true or false
     */
    public boolean isEnableTaskTRF31968(){
        return getBooleanProperty("enableTask.TRF-31968");
    }
    
    /** 
     * Is enable task TRF-36639
     * @return value of property true or false
     */
    public boolean isEnableTaskTRF36639(){
        return getBooleanProperty("enableTask.TRF-36639");
    }

    /**	
     * Is task TRF-26663 enabled.
     * 
     * @return value of property true or false
     */
    public  boolean isEnableTaskTRF26663(){
        return getBooleanProperty(IS_TASK_TRF_26663_ENABLED);
    }
    
    /** 
     * Is task TRF-27901 enabled.
     * 
     * @return value of property true or false
     */
    public  boolean isEnableTaskTRF27901(){
        return getBooleanProperty(IS_TASK_TRF_27901_ENABLED);
    }

    /**
     *
     * Is Task TRF-30922 Enabled
     * 
     * @return value of property true or false
     */
    public boolean isTaskTRF30922Enabled(){
        return getBooleanProperty(ENABLE_TASK_TRF_30922); 
	}
	
     /**
     * Get Driving Istitute Booking EP
     * 
     * @return Driving Istitute Booking EP
     */
    public String getDrivingInstituteBookingDetailsEP() {
        return getProperty(DRIVING_INSTITUTE_BOOKING_DETAILS_SERVICE_EP);
    }
    
    /**
     * Get Driving Istitute Booking Username
     *
     * @return Driving Istitute Booking Username
     */    
    public String getDrivingInstituteBookingDetailsUsername() {
        return getProperty(DRIVING_INSTITUTE_BOOKING_DETAILS_SERVICE_USERNAME);
    }
    
    /**
     * Get Driving Istitute Booking Password
     *
     * @return Driving Istitute Booking Password
     */    
    public String getDrivingInstituteBookingDetailsPassword() {
        return getProperty(DRIVING_INSTITUTE_BOOKING_DETAILS_SERVICE_PASSWORD);
    }
    
    /**
     * Get enabling flag
     *
     * @return true Driving Istitute Booking if enabled
     */    
    public boolean getDrivingInstituteBookingDetailsEnabled() {
        return getBooleanProperty(DRIVING_INSTITUTE_BOOKING_DETAILS_SERVICE_ENABLED);
    }
    
    /**
     * Get enabling flag
     *
     * @return true Driving Istitute Booking if enabled
     */    
    public boolean isDrivingInstituteWarningMessagesEnabled() {
        return getBooleanProperty(DRIVING_INSTITUTE_WARNING_MESSAGES_SERVICE_ENABLED);
    }
    
    /**
     * Is Checking License Application Traning DI Info Active
     * 
     * @return Checking License Application Traning DI Info Active
     */
    public boolean isCheckingLicenseApplicationTraningDIInfoActive(){
        return getBooleanProperty(IS_CHECKING_TRANING_DI_INFO_ACTIVE);
    }
        
    /**
     * Is new Vehicles Should Be Tested
     * 
     * @return value of property true or false
     */
    public boolean isNewVehiclesShouldBeTested(){
        return getBooleanProperty(NEW_VEHICLE_SHOULD_BE_TESTED); 
    }
    
    
    /**
    * Check if the passed task number enabled
    *
    * @return value of property true or false
    */
    public boolean isTaskEnabled(String taskNumber){
       return getBooleanProperty("enableTask."+taskNumber); 
    }
    /**
     * Enable Business Rule BR_TRS202_MER
     * 
     * @return true/false
     */
    public boolean isBR_TRS202_MEREnabled(){
        return getBooleanProperty(Enable_BR_TRS202_MER);
    }

    /**
     * Is Task TRF-35380 Enabled
     *
     * @return value of property true or false
     */
     public boolean isTaskTRF35380Enabled(){
        return getBooleanProperty(ENABLE_TASK_TRF_35380); 
     }
 
    /**
     * Get Minimum registration date for exam appointment processor
     *
     * @return Minimum registration date for exam appointment processor
     */    
    public String getMinRegDateForExamApptProcessor() {
        return getProperty(MIN_REG_DATE_EXAM_APPT_THREAD);
    }
    
    /**
     * Get Minimum registration date for exam appointment processor
     *
     * @return Minimum registration date for exam appointment processor
     */    
    public Integer geRegDateRangeForExamApptProcessor() {
        return getIntegerProperty(DATE_EXAM_APPT_RANGE);
    }
    
    /**
     * Get Trial IDs to be processed by thread
     *
     * @return Trial IDs to be processed by thread
     */    
    public String getTrialIdsForExamApptProcessor() {
        return getProperty(TRIAL_IDS_EXAM_APPT_THREAD);
    }
    
    /**
     * Get Trial IDs to be processed by thread
     *
     * @return Trial IDs to be processed by thread
     */    
    public Integer getMinHourForExamApptProcessor() {
        return getIntProperty(MIN_START_HOUR_EXAM_APPT_THREAD);
    }
    
    /**
     * Get nO. OF DAYS FOR rescheduling
     *
     * @return Minimum registration date for exam appointment processor
     */    
    public int getNoOfRescheduleDaysForExamApptProcessor() {
        return getIntProperty(NO_RESCHEDULE_DAYS_EXAM_APPT_THREAD);
    }
    
    /**
     * Get nO. OF DAYS FOR rescheduling
     *
     * @return Minimum registration date for exam appointment processor
     */    
    public int getNoOfRescheduleDaysForExamAppt() {
        return getIntProperty(NO_RESCHEDULE_DAYS_EXAM_APPT);
    }
    
    /**
     * Get Enabled Chassis Classes
     * 
     * @return Enabled Chassis classes separated by comma ','
     */
    public String getEnabledChassisClassFlag() {
        return getProperty(ENABLED_CHASSIS_CLASSES);
    }


    /**
     * Activate Two Sessions Validation
     * 
     * @return true if the property value is true
     */
    public boolean getActivateTwoSessionsValidation(){
        return getBooleanProperty(ACTIVATE_TWO_SESSIONS_VALIDATION);
    }
    
    /**
     * Is Transactions Check status enabled. 
     * 
     * @return true if the property value is true
     */
    public boolean isTransactionsCheckStatusEnabled(){
        return getBooleanProperty(ENABLE_TRANSACTION_CHECK_STATUS);
    }
    
    /**
     * Is Task TRF-42109 Enabled
     * @return boolean
     */
    public boolean isEnableTaskTrf42109() {
        return getBooleanProperty(ENABLE_TASK_TRF_42109);
    }
    
    /**
     * Is Task TRF-47258 Enabled
     * @return boolean
     */
    public boolean isEnableTaskTrf47258() {
        return getBooleanProperty(ENABLE_TASK_TRF_47258);
    }
    
    
    /**
     * Is Task TRF-34966 Enabled
     *
     * @return value of property true or false
     */
     public boolean isTaskTRF34966Enabled(){
        return getBooleanProperty(ENABLE_TASK_TRF_34966); 
     }
        
    public List<String> getDataValidationMobiles() {
        
        return getListProperty(DATA_VALIDATION_MOBILES, ",");
    }

    
    /**
     * Get Appoitment Google Map Server Key.
     * 
     * @return Appoitment Google Map Server Key.
     */
    public String getAppoitmentGoogleMapServerKey() {
        return getProperty(APPOITMENT_GOOGLE_MAP_KEY);
    } 
    
    /**
     * Check if the Check register vehicle Class enabled or not
     *
     * @return true if the value is true 
     */
    public boolean checkRegisterVehicleClassEnabled() {
        return getBooleanProperty(CHECK_REGISTER_VEHICLE_CLASS);
    }
    
     /**
     * is Task TRF-24652 Enabled.
     * 
     * @return return value of property true or false
     */
    public boolean isTaskTRF24652Enabled() {
        return getBooleanProperty(ENABLE_TASKE_TRF_24652);                    
    }   
    
    /**
     * is Enable Task TRF-48302
     * 
     * @return true  
     */
    public boolean isEnableTaskTrf48302() {
        return getBooleanProperty(ENABLE_TASK_TRF48302);
    }
        
    /**
     *  Get Allowed Allocating Plates Period
     * @return
     */
    public Integer getAllowedAllocatingPlatesPeriod(){
        return getIntegerProperty(ALLOWED_ALLOCATING_PLATES_PERIOD);
    }
     
    /**
     * Get Tourisem Clearance Cutt Off Date
     *
     * @return value.
     */
     public String getTCCuttOffDate(){
        return getProperty(TOURISEM_CLEARANCE_CUTT_OFF_DATE); 
     } 
    public boolean isTaskTRF38814_1_Enabled(){
       return getBooleanProperty(ENABLE_TASK_TRF_38814_1); 
    }    
    
    public boolean isTaskTRF38814_2_Enabled(){
       return getBooleanProperty(ENABLE_TASK_TRF_38814_2); 
    }    
    
     
    
    
    
    
     
    /**       
    /*
 
     * Is Task TRF-38698 Enabled
     *
     * @return value of property true or false
     */
     public boolean isTaskTRF38698Enabled(){
        return getBooleanProperty(ENABLE_TASK_TRF_38698); 
     }  
        
    /**
     * Gets Allowed Vehicle Classes For Commercial Permits
     * 
     * @return String :vehicle classes sepirated by comma
     */
    public String getRequiredVehicleClassesIdsForCommercialPermits(){
        return getProperty(REQUIRED_VEHICLE_CLASSES_FOR_COMMERCIAL_PERMITS);
    }
	 
    
    /** 
     * Is task TRF_36583 enabled ( revamp print receipt page ).
     * 
     * @return value of property true or false
     */
    public boolean isEnableTaskTRF36583() {
        return getBooleanProperty(IS_TASK_TRF_36583_ENABLED);
    }
    
    /** 
     * Is task TRF-34291 enabled ( Data Cleansing ).
     * 
     * @return value of property true or false
     */
    public boolean isEnableTaskTRF34291() {
        return getBooleanProperty(IS_TASK_TRF_34291_ENABLED);
    }
    
    /**
     * Is Task TRF-31779 Enabled
     * 
     * @return value of property true or false
     */
     public boolean isTaskTRF31779Enabled(){
        return getBooleanProperty(IS_TASK_TRF_31779_ENABLED); 
    }
    
    /**
     * Is Task TRF-35671 Enabled
     *
     * @return value of property true or false
     */
     public boolean isTaskTRF35671Enabled(){
        return getBooleanProperty(IS_TASK_TRF_35671_ENABLED); 
     }     
    
    /**
     * Franchise Contract Traffic No
     *
     * @return value of Franchise Contract Traffic No property
     */
     public Long getFranchiseContractTrafficNo(){
        return getLongProperty(FRANCHISE_CONTRACT_TRAFFIC_NO); 
     }
    
    /**
     *  Get Number Of Allowed FTF Certificates Reprints In Same Day
     *  
     * @return Number Of Allowed FTF Certificates Reprints In Same Day
     */
    public Integer getNumberOfAllowedFTFCertificatesReprintsInSameDay(){
        return getIntegerProperty(NUMBER_OF_ALLOWED_FTF_CERTIFICATES_REPRINTS_IN_SAME_DAY);
    }
    
    /**
     * Gets Daemon Thread pool size
     * 
     * @return Integer : daemon thread pool size
     */
    public Integer getDaemonThreadPoolSize(){
        return getIntegerProperty(DAEMON_THREAD_POOL_SIZE);
    }
    
    /**
     * Gets User Thread pool size
     * 
     * @return Integer : user thread pool size
     */
    public Integer getUserThreadPoolSize(){
        return getIntegerProperty(USER_THREAD_POOL_SIZE);
    }
    /**
     *  Get Number Of Allowed FTF Booklets Reprints In Same Day
     * @return
     */
    public Integer getNumberOfAllowedFTFBookletsReprintsInSameDay(){
        return getIntegerProperty(NUMBER_OF_ALLOWED_FTF_BOOKLETS_REPRINTS_IN_SAME_DAY);
    }
    
    /**
     * Get substitubale country minimum allowed age.
     * 
     * @return min age : Integer
     */
    public Integer getSubstitutableCountryMinAge(){
        return getIntegerProperty(SUBSTITUTABLE_COUNTRY_MIN_AGE);
    }

    /**
     * Get Denied No Of Day Before Trial
     * 
     * @return Denied No Of Day Before Trial
     */
    public Integer getDeniedNoOfDayBeforeTrial(){
        return getIntegerProperty(DENIED_NO_OF_DAY_BEFORE_TRIAL);
    } 
    
    
    /**
     * Get Max No Of Transfer
     * 
     * @return Max No Of Transfer
     */
    public Integer getMaxNoOfTransfer(){
        return getIntegerProperty(MAX_NO_OF_TRANSFER);
    }
    
    /**
     * Get Franchise Booklet Pool Number Warning
     * @return Franchise Booklet Pool Number Warning
     */
    public Integer getFranchiseBookletNumberWarning(){
        return getIntegerProperty(FRANCHISE_BOOKLET_POOL_NUMBER_WARNING);
    } 
    
    /**
     * Get Franchise Booklet Pool Number Warning
     * @return Franchise Booklet Pool Number Warning
     */
    public Integer getFranchiseTaxiBookletNumberWarning(){
        return getIntegerProperty(FRANCHISE_TAXI_BOOKLET_POOL_NUMBER_WARNING);
    }
    
    /**
     * Get substitubale country maximum allowed age.
     * 
     * @return max age : Integer
     */
    public Integer getSubstitutableCountryMaxAge(){
        return getIntegerProperty(SUBSTITUTABLE_COUNTRY_MAX_AGE);
    }
   
    /** 
     * Is Task TRF_36647 enabled.
     * 
     * @return value of property true or false.
     */
    public boolean isEnableTaskTRF36647() {
        return getBooleanProperty(IS_TASK_TRF_36647_ENABLED);
    }
    
    /**
     * Is Task TRF_36634 enabled.
	 *
     * @return value of property true or false.
     */
    public boolean isEnableTaskTRF36634(){
        return getBooleanProperty(IS_TASK_TRF_36634_ENABLED);    
	}		
    

    
    /**
     * Is task TRF-38842 enabled ( Remove Expiration date for taxi contract ).
     *  
     * @return value of property true or false
     */
    public boolean isEnableTaskTRF38842() {
        return getBooleanProperty(ENABLE_TASK_TRF_38842);
    }
    
    
    /** 
     * Is task TRF-39485 enabled ( Special Permits Report Screen ).
     *  
     * @return value of property true or false
     */
    public boolean isEnableTaskTRF39485() {
        return getBooleanProperty(ENABLE_TASK_TRF_39485);
    }
    
    /** 
     * Is task TRF-38697 enabled ( Permit Category Gender Constrain ).
     *  
     * @return value of property true or false
     */
    public boolean isEnableTaskTRF38697() {
        return getBooleanProperty(ENABLE_TASK_TRF_38697);
    }
    
    /**
     * Is Threads Monitoring Enabled 
     * 
     * @return true if log enbaled 
     */
    public boolean isThreadsMonitoringEnabled(){
       return getBooleanProperty(THREADS_MONITORING_ENABLED); 
    }
    /** 
     * Is Threads Monitoring Enabled
     * 
     * @return true if log enbaled 
     */
    public boolean isInvalidPlateCategoryDescEnabled(){
       return getBooleanProperty(ENABLE_INVALID_PLATE_CATEGORY_DESC); 
    }
    
    /**
     * isCheckingPendingTransactionEnabled
     * 
     * @return true if checking pending transaction on certify is enabled or disabled.
     */
    public boolean isCheckingPendingTransactionEnabled(){
        return getBooleanProperty(ENABLE_CHECK_PENDING_TRANSACTION_ON_CERTIFY);
    }    
    
    /**
     * Is brATR005_MER Enabled
     * 
     * @return true if br ATR005 MER enabled
     */
    public boolean isBrATR005_MEREnabled(){
       return getBooleanProperty(BR_ATR005_MER_ENABLED); 
    } 
    
    /** 
     * Is brATR005_MER Enabled
     * 
     * @return true if TRF-35668 nabled
     */
    public boolean isTRF_35668Enabled(){
       return getBooleanProperty(TRF_35668_ENABLED); 
    }         
    
    /**
     *  Get Allowance Period After Contract Expired In Months
     * @return
     */
    public int getContractAllowanceExpiryPeriod(){
        return getIntProperty(CONTRACT_ALLOWANCE_EXPIRY_PERIOD);    
    }
    
    /**
     *  Get Contract Lock Timeout
     * @return
     */
    public int getContractLockTimeout(){
        return getIntProperty(CONTRACT_LOCK_TIMEOUT);    
    }
    
    /**
     *  Get Contract Registered Vehicle Percentage
     * @return
     */
    public int getContractRegVehiclePercentage(){
        return getIntProperty(CONTRACT_REGISTERED_VEH_PERCENTAGE);    
    }
    
    /**
     * Get timeout for TelematicsDeviceStatus service.
     *
     * @return timeout.
     */
    public int getTelematicsDeviceStatusServiceTimeout() {
        return getIntProperty(TELEMATICS_DEVICE_STATUS_SERVICE_TIMEOUT);
    }
    
    /**
     * Get username for TelematicsDeviceStatus service.
     *
     * @return username.
     */
    public String getTelematicsDeviceStatusServiceUserName() {
        return getProperty(TELEMATICS_DEVICE_STATUS_SERVICE_USERNAME);
    }
    
    /**
     * Get password for TelematicsDeviceStatus service.
     *
     * @return password.
     */
    public String getTelematicsDeviceStatusServicePassword() {
        return getProperty(TELEMATICS_DEVICE_STATUS_SERVICE_PASSWORD);
    }
    
    /**
     * Get endpoint for TelematicsDeviceStatus service.
     *
     * @return endpoint.
     */
    public String getTelematicsDeviceStatusServiceEndpoint() {
        return getProperty(TELEMATICS_DEVICE_STATUS_SERVICE_ENDPOINT);
    }
    
    /**
     * Get if task 39560 enabled or not
     *
     * @return status of task.
     */
    public Boolean isTaskTRF39560Enabled() {
        return getBooleanProperty(ENABLE_TASK_TRF_39560);
    }
    
    /**
     * Get the number of years that the vhl needs to exceed for telematics checking
     *
     * @return years number.
     */
    public int getTelematicsAllowedPeriod() {
        return getIntProperty(TELEMATICS_VHL_ALLOWED_PERIOD);
    }	    
    
    /**
     *  Get Allowed Plate Codes For Franchise Organization
     * @return plate codes
     */
    public List<String> getAllowedPlateCodesForFranchiseOrg(){
        return getListProperty(ALLOWED_PLT_CODES_FOR_FRANCHISE_ORG, ",");    
    } 
	
	/*
     * Is Check Douplicated EWallet Request Enabled
     * 
     * @return boolean True if enabled. 
     */
    public boolean isCheckDouplicatedEWalletRequestEnabled(){
        return getBooleanProperty(ENABLE_CHECK_EWALLET_DUPLICATED_REQUEEST);    
        
    }
    
    /**
     * Get Report Buffring Size
     * 
     * @return Report Buffring Size As Integer Value 
     */
    public Integer getReportBuffringSize(){
        return getIntegerProperty(REPORT_BUFFRING_SIZE);
    }
    
    /**
     * Is TRF-40960 Enalbed
     * 
     * @return true if returing kiosk centers id list enalbed
     */
    public boolean isTRF_40960Enabled(){
        return getBooleanProperty(ENABLE_TRF_40960);    
    }        
    
    /**
     * Is Enabled Task TRF-42437.
     * 
     * @return boolean True if enabled. 
     */
    public boolean isTask42437Enabled() {
        return getBooleanProperty(ENABLE_TASK_TRF_42437);
    }


    /**
     * Get Check BRRLC012 Channel Codes
     * 
     * @return Check BRRLC012 Channel Codes values
     */
    public String getCheckBRRLC012ChannelCodes() {
        return getProperty(CHECK_BR_RLC012_CHANNEL_CODES);
    }
    
    /**
     * Get page size for fast lookup componant.
     * 
     * @return page size for fast lookup componant : Integer
     */
    public Integer getLookupsPageSize(){
        return getIntegerProperty(FAST_LOOKUP_PAGE_SIZE);
    }
    
    /**
     * Is validation on DTT services active on Electronic signature. 
     *  
     * @return value of property true or false
     */
    public boolean isEnableValidationOnDTTServiceWithElecSign() {
        return getBooleanProperty(ENABLE_VALIDATION_ON_DTT_SERVICES_WITH_ELEC_SIGN);
    }
    
    /**
     * Get flag for Breadcrumb Allowed.
     * 
     * @return Get flag for Breadcrumb Allowed : Boolean
     */
    public Boolean isBreadcrumbAllowed(){
        return getBooleanProperty(IS_BREADCRUMB_ALLOWED);
    }
    
    /**
     * Get Franchise Organization Allowed Registration Years proprity
     * 
     * @return Franchise Organization Allowed Registration Years proprity Value
     */
    public Integer franchiseOrgAllowedRegYears() {
        return getIntegerProperty(FRANCHISE_ORGANIZATION_ALLOWED_REGISTRATION_YEARS);
    }
    
    /**
     * Franchise Expired Renewal Organization Monthes Property
     *
     * @return Franchise Expired Renewal Organization Monthes Property Value
     */
    public Integer franchiseExpiredOrgRenewalMonthes() {
        return getIntegerProperty(FRANCHISE_EXPIRED_ORG_RENEWAL_MONTHES);
    }
    
    /**
     * Dealer Trade License Expiry Grace Period
     * 
     * @return Dealer Trade License Expiry Grace Period value
     */
    public Integer dealerTradeLicenseExpiryGracePeriod() {
        return getIntegerProperty(DEALER_TRADE_LICENSE_EXPIRY_GRACE_PERIOD);
    }
    
    /**
     * Use Car Dealer Trade License Expiry Grace Period
     * 
     * @return Use Car Dealer Trade License Expiry Grace Period value
     */
    public Integer useCarDealerTradeLicenseExpiryGracePeriod() {
        return getIntegerProperty(USE_CAR_DEAlER_TRADE_LICENSE_EXPIRY_GRACE_PERIOD);
    }
    
    /**
     * Get Services Need Category Check
     * 
     * @return Services Need Category Check
     */
    public String getServicesNeedCategoryCheck() {
        return getProperty(SERVICES_NEED_CATEGORY_CHICK);
    }
    
    /**
     * Disable access to smart traffic
     * 
     * @return boolean
     */
    public boolean getEnableSmartTrafficAccess( ) {
        return getBooleanProperty(ENABLE_SMART_TRAFFIC_ACCESS);
    }
    
    /**
     * used in smart security filter to force page security definition
     * @return
     */
    public boolean isBypassPageInfoEnabled() {
        return getBooleanProperty(IS_BYPASS_PAGE_INFO_ENABLED);
    }
    
    /**
     * Is Enabled Task BrTrs215
     * 
     * @return boolean True if enabled. 
     */
    public boolean isBrTrs215Enabled() {
        return getBooleanProperty(ENABLE_BR_TRS215_ATT);
    }
    
    /**
     * Gets the Jasper report server IP.
     *
     * @return Jasper report server IP Property Value
     */
    public String getJasperServerIP() {
        return getProperty(JASPER_SERVICE_IP_PROPRITY);
    }
    
    /**
     * Gets the Jasper report server Port.
     *
     * @return Jasper report server Port Property Value
     */
    public String getJasperServerPort() {
        return getProperty(JASPER_SERVICE_PORT_PROPRITY);
    }
    
    /**
     * Gets the Jasper report server UserName.
     *
     * @return Jasper report server UserName Property Value
     */
    public String getJasperServerUserName() {
        return getProperty(JASPER_SERVICE_USERNAME_PROPRITY);
    }
    
    /**
     * Gets the Jasper report server Paswword.
     *
     * @return Jasper report server Paswword Property Value
     */
    public String getJasperServerPassword() {
        return getProperty(JASPER_SERVICE_PASWWORD_PROPRITY);
    }
    
    /**
     * Ges White Points Job Working Day.
     * 
     * @return boolean True if enabled. 
     */
    public Integer getWhitePointsJobWorkingDay() {
        return getIntegerProperty(WHITE_POINT_JOB_WORKING_DAY);
    }    
    
    /**
     * Get table range size for tables in new system.
     * 
     * @return range size. 
     */
    public Integer getTableRangeSize() {
        return getIntegerProperty(TABLES_RANGE_SIZE);
    }
    
    /**
     * Get Max Date Days
     * 
     * @return range size. 
     */
    public Integer getMaxDateDays() {
        return getIntegerProperty(MAX_SEARCH_DATE_DAYS_DTT_REPORT);
    }        

    /** 
     * is enable task TRF_40596
     * @return value of property true or false
     */
    public boolean isEnableTaskTRF40596(){
        return getBooleanProperty(ENABLE_TASK_TRF_40596);
    }

    /**
     * Get WS Booklets Service End Point.
     * 
     * @return WS Booklets Service End Point.
     */
    public String getWSBookletsServiceEndPoint() {
        return getProperty(WS_BOOKLETS_END_POINT);
    }
    
    /**
     * Get WS Booklets Service User Name.
     * 
     * @return WS Booklets Service User Name.
     */
    public String getWSBookletsServiceUserName() {
        return getProperty(WS_BOOKLETS_USER_NAME);
    }
    
    /**
     * Get WS Booklets Service Password.
     * 
     * @return WS Booklets Service Password.
     */
    public String getWSBookletsServicePassword() {
        return getProperty(WS_BOOKLETS_PASSWORD);
    }

    /**
     * flag to enable soluation of Double Slash in URL problem
     * @return value of property true or false
     */
    public boolean isHandlingDoubleSlashInURL() {
        return getBooleanProperty(HANDLING_DOUBLE_SLASH_IN_URL);
    }
    
    /**
     * flag to enable soluation of Double Slash in URL problem
     * @return value of property true or false
     */
    public boolean isEnableTaskTrf39562() {
        return getBooleanProperty(ENABLE_TASK_TRF_39562);
    }    
    
    /**
     * URLs to be Skipped from the soluation of Double Slash in URL
     * @return value of property
     */
    public String getDoubleSlashSkippedURLs() {
        return getProperty(DOUBLE_SLASH_SKIPPED_URLS);
    }
    
    /** 
     * Is task TRF-43536 enabled ( Vehicles Makes and Models Migration ).
     * 
     * @return value of property true or false
     */
    public boolean isEnableTaskTRF43536() {
        return getBooleanProperty(IS_TASK_TRF_43536_ENABLED);
    }
    
    /**
     * Get Electronic Mortgage Uploaded Msg.
     * 
     * @return getElectronicMortgageUploadedMsg
     */
    public String getElectronicMortgageUploadedMsg() {
        return getProperty(ELECTRONIC_MORTGAGE_UPLOADED_MSG);
    }
    
    /**
     * Default mobile No to be displaied in vip_special_plates_search.jsf screen
     * @return value of property
     */
    public String getDefaultVIPMobileNo() {
        return getProperty(DEFAULT_VIP_MOBILE_NO);
    }
    
    /**
     * Get Dealers Channel Codes.
     * 
     * @return array of channel code
     */
    public List getDealersChannelCodes() {
        return getListProperty("ae.rta.vhl.mortgage.dealersChannelCodes", ",");
    }
    
    /**
     * Get Replace Plates Remarks Codes.
     * 
     * @return List
     */
    public List getReplacePlatesRemarksCodes() {
        return getListProperty(REPLACE_PLATES_REMARKS_CODES, ",");
    }
    
    /**
     * Enable English Smart Traffic Access
     * 
     * @return boolean
     */
    public boolean getEnableEnglishSmartTrafficAccess() {
        return getBooleanProperty(ENABLE_English_SMART_TRAFFIC_ACCESS);
    }
        
    
    /** 
     * Is task TRF-42167 enabled .
     * 
     * @return value of property true or false
     */
    public boolean isEnableTaskTRF42167() {
        return getBooleanProperty(ENABLE_TASK_TRF_42167);
    }
    
    /** 
     * Is task TRF-25960 enabled ( Exceptions task ).
     * 
     * @return value of property true or false
     */
    public List getPlateIdSkipServiceCodes() {
        return getListProperty(PLATE_ID_SKIP_SERVICE_CODES, ",");
    }

    /** 
     * Is Enable Plate Logo Fees Validation.
     * 
     * @return value of property true or false
     */
    public boolean isEnablePlateLogoFeesValidation() {
        return getBooleanProperty(ENABLE_PLATE_LOGO_FEES_VALIDATION);
    }
    
    /** 
     * Is Task TRF_46188 enabled.
     * 
     * @return value of property true or false.
     */
    public boolean isEnableTaskTRF46188() {
        return getBooleanProperty(IS_TASK_TRF_46188_ENABLED);
    }
    /** 
     * Is Enable TRF46367 Validation.
     * 
     * @return value of property true or false
     */
    public boolean isEnableTRF46367Validation() {
        return getBooleanProperty(ENABLE_TRF46367_VALIDATION);
    }    
    
    /**
     * Is Enabled Task TRF-46361.
     * 
     * @return boolean True if enabled. 
     */
    public boolean isTask46361Enabled() {
        return getBooleanProperty(ENABLE_TASK_TRF_46361);
    }
    
    /**
     * Replace Released Booklets Days Validity.
     * 
     * @return Replace Released Booklets Days Validity.
     */
    public Integer replaceReleasedBookletsDaysValidity() {
        return getIntegerProperty(REPLACE_RELEASED_BOOKLETS_DAYS_VALIDITY);
    }
    
    /**
     * Is Task TRF-45741 Enabled
     * @return True if task TRF-45741 is enabled
     */
    public boolean isEnableTaskTrf45741() {
        return getBooleanProperty(ENABLE_TASK_TRF_45741);
    }

    /**
     * Get Data Signing Certs

     * @return value of property
     */
    public String getDataSigningCerts() {
        return getProperty(DATA_SIGNING_CERTS);
    }
    
    /**
     * Get EID Read Date Exceeding Period In Days
     * 
     * @return EID Read Date Exceeding Period In Days
     */
    public Integer getEIDReadDateExceedingPeriodInDays() {
        return getIntegerProperty(EID_READ_DATE_PERIOD_IN_DAYS);
    }

    /**
     * Is Enabled Task TRF-32575.
     * 
     * @return boolean True if enabled. 
     */
    public boolean isTaskTRF32575Enabled() {
        return getBooleanProperty(ENABLE_TASK_TRF_32575);
    }
    
    /**
     * Is Enabled Task TRF-46484.
     * 
     * @return boolean True if enabled. 
     */
    public boolean isTaskTRF46484Enabled() {
        return getBooleanProperty(IS_EID_WEBSERVICE_ENABLED);
    }  
    
    /**
     * Is Enabled Task TRF-29400.
     * 
     * @return boolean True if enabled. 
     */
    public boolean isTaskTRF29400Enabled() {
        return getBooleanProperty(ENABLE_TASK_TRF_29400);
    }
    
    /**
     * Is Enabled Task TRF-48419.
     * 
     * @return boolean True if enabled. 
     */
    public boolean isTaskTRF48419Enabled() {
        return getBooleanProperty(ENABLE_TASK_TRF_48419);
    }
    
    /**
     * Get No of Sales.
     * 
     * @return noOfSales
     */
    public String getNoOfSales() {
        return getProperty(NO_OF_SALES);
    }

    /**

    * Is Enabled Task TRF-47529.
    * 
    * @return boolean True if enabled. 
    */
    public boolean isTaskTRF47529Enabled() {
        return getBooleanProperty(ENABLE_TASK_TRF_47529);
    }
      
    /**
     * Get FTF Cat Codes
     * 
     * @return FTF Cat Codes Values 
     */
    public List getFTFCatCodes() {
        return getListProperty(FTF_CAT_CODES,",");
    }
    
    /**
     * Get SDDI Cat Codes
     * 
     * @return SDDI Cat Codes Values 
     */
    public List getSDDICatCodes() {
        return getListProperty(SDDI_CAT_CODES,",");
    }
    
    /**
     * Is Enabled Task TRF-43140.
     * 
     * @return boolean True if enabled. 
     */
    public boolean isEnableTaskTRF43140() {
        return getBooleanProperty(IS_TASK_TRF_43140_ENABLED);
    }

    /** 
     * Is Task TRF_49548 enabled.
     * 
     * @return value of property true or false.
     */
    public boolean isEnableTaskTRF49548() {
        return getBooleanProperty(IS_TASK_TRF_49548_ENABLED);
    }
    
    /**
     * Is Skip BR TRS217_CEV.
     * 
     * @return boolean True if enabled. 
     */
    public boolean isSkipBRTrs217Cev() {
        return getBooleanProperty(SKIP_BR_TRS217_CEV);
    }
    
    /**
     * Get Allowed services while release mortgage
     * 
     * @return Allowed services while release mortgage Service Codes
     */
    public List getAllowedServiceWileReleaseMortgage() {
        return getListProperty(ALLOWED_SERVICES_WHILE_RELEASE_MORTGAGE,","); 
    }
    
    /**
<<<<<<< HEAD
     * Is Skip TRS 49548.
     * 
     * @return boolean True if enabled. 
     */
    public boolean isSkipTRS49548() {
        return getBooleanProperty(SKIP_TRS_49548);
    }
    /**
     * Is Enabled Task TRF-41555.
     * 
     * @return boolean True if enabled. 
     */
    public boolean isTaskTRF41555Enabled() {
        return getBooleanProperty(ENABLE_TASK_TRF_41555);
    }
    
    /**
     * Is Enabled TRS-41555.
     * 
     * @return boolean True if enabled. 
     */
    public boolean isTRS41555Enabled() {
        return getBooleanProperty(ENABLE_TRS_41555);
    }
    /**
     * Get Drl Services Excluded from fines
     * 
     * @return list of service codes excluded from fines
     */
    public List getDrlServicesExcludedFromFines() {
        return getListProperty(DRL_SERVICES_EXCLUDED_FROM_FINES,","); 
    }
    
    /**
     * Is TRF_52240 Task  Enabled.
     * 
     * @return boolean True if enabled. 
     */
    public boolean isTRF52240Enabled() {
        return getBooleanProperty(ENABLE_TASK_TRF_52240);
    }
    
    /**
     * Get Tibco Dnrd Header User Name
     * @return String
     */
    public String getTibcoDnrdHeaderUserName() {
        return getProperty(TIBCO_DNRD_HEADER_USER_NAME);
    }
    
    /**
     * Get Dnrd Tibco Header Password
     * @return String
     */
    public String getTibcoDnrdHeaderPassword() {
        return getProperty(TIBCO_DNRD_HEADER_PASSWORD);
    }
}
