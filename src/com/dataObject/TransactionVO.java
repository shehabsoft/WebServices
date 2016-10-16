/*
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  28/01/2008  - File created.
 * 
 * 1.01  Eng. Ayman Atiyeh  18/02/2008  - Adding commercial licensing system.
 * 
 * 1.02  Moh Fayek          16/07/2012  - TRF-5237
 * 
 * 1.03  Sami Abudayeh      26/06/2012  - Adding isSentDescription property
 *                                      - Adding sentDate propery
 *                                      
 * 1.04  Mohammed Kabeel    31/07/2012  - SDDI-2613                        
 * 
 * 1.05  Ahmed Abdelwahab   21/08/2012  - TRF-7515.
 * 
 * 1.06  Mahmoud Atiyeh     05/11/2012  - Added nationality property
 *                                      - Added plateVO property
 *                                      - Added ticketsList property
 * 
 */

package com.dataObject;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Traffic transaction value object. Encapsulates common transaction attributes.
 *
 * @author Eng. shehab .tarek
 * @version 1.05
 */
public class TransactionVO {
    /*
     * Constants and class variables.
     */
    /** Column ( TF_STP_TRANSACTIONS ) */
    public static final String IS_RECEPTION = "IS_RECEPTION";
    public static final String TEMP_ATTACHMENTS_SEQUENCE = "TEMP_ATTACHMENTS_SEQUENCE";
    public static final String ID = "ID";


    /** Table Name */
    public static final String TABLE_NAME = "TF_STP_TRANSACTIONS";
     
     
    /** Transaction status values. */
    public static final int STATUS_NEW = 1;
    public static final int STATUS_CERTIFIED = 2;
    public static final int STATUS_CONFIRMED = 3;
    public static final int STATUS_CANCELED = 4;
    public static final int STATUS_PENDING = 5;
    public static final int STATUS_POSTED = 6;
    public static final int STATUS_REVERSED = 7;

    
    /** Transaction type values. */
    public static final int TYPE_VEHICLES = 1;
    public static final int TYPE_DRIVING_LICENSES = 2;
    public static final int TYPE_FINES = 3;
    public static final int TYPE_SPECIAL_PLATES = 4;
    public static final int TYPE_DRIVING_TEST = 5;
    public static final int TYPE_COMMERCIAL_LICENSING = 6;
    public static final int TYPE_SPECIAL_RECEIPTS = 7;
    public static final int TYPE_REVAMP = 8 ;
    public static final int TYPE_CAR_INSPECTION_SYSTEM = 26;
    public static final int TYPE_GENERAL = 11; // [11] On SDDI.
    
    
    
    /** Delivery Mode Values */
    public static final Integer DELIVERY_MODE_COURIER = new Integer(1);
    public static final Integer DELIVERY_MODE_COLLECTION = new Integer(2);
    public static final Integer DELIVERY_MODE_ECERTIFICATE = new Integer(3);
    public static final Integer DELIVERY_MODE_MAIL = new Integer(4);
    public static final Integer DELIVERY_MODE_IMMIDIATE_CARD_PRINTING = new Integer(5);
    public static final Integer DELIVERY_MODE_PRINTED_KISOK = new Integer(6);
    public static final Integer DELIVERY_MODE_COURIER_AND_COLLECTION = new Integer(7);

    
                                   
    /** Delivery Method Values */
    public static final Integer DELIVERY_METHOD_FEDEX = new Integer(1);
    public static final Integer DELIVERY_METHOD_MANUAL_DELIVERY = new Integer(3);
    public static final Integer DELIVERY_METHOD_EMAIL = new Integer(2);
    
        
    /** Is Sent Values */
    public static final Integer IS_NOT_SENT = new Integer(1);
    public static final Integer IS_SENT_LOCKED = new Integer(2);
    public static final Integer IS_SENT_SUCCESS = new Integer(3);
    public static final Integer IS_SENT_TO_EWALLET = new Integer(4);
    
    /**Eid DATA SOURCE */
    public static final Integer EID_DATA_SOURCE_CARD_READER = new Integer(1); 
    public static final Integer EID_DATA_SOURCE_MANAUL_FTF = new Integer(2); 
    public static final Integer EID_DATA_SOURCE_MANUAL_SDDI = new Integer(3); 
    public static final Integer EID_DATA_SOURCE_EXTERNAL_INTEGRATION = new Integer(4); 


    /** Session Changed Values */
    public static final Integer SESSION_CHANGED_DATA_CORRECT = new Integer(1);
    public static final Integer SESSION_CHANGED_DUH_IN_PROFILE_NULL = new Integer(2);
    public static final Integer SESSION_CHANGED_DUH_IN_RECEIPT_NULL = new Integer(3);
    public static final Integer SESSION_CHANGED_DUH_DEF_IN_PROF_AND_RECEIPT = new Integer(4);
    public static final Integer SESSION_CHANGED_IP_ADDRESS_IN_PROFILE_NULL = new Integer(5);
    public static final Integer SESSION_CHANGED_IP_ADDRESS_IN_RECEIPT_NULL = new Integer(6);
    public static final Integer SESSION_CHANGED_IP_ADDRESS_DEF_IN_PROF_AND_RECEIPT = new Integer(7);
    public static final Integer SESSION_CHANGED_SESSION_ID_IN_PROFILE_NULL = new Integer(8);
    public static final Integer SESSION_CHANGED_SESSION_ID_IN_RECEIPT_NULL = new Integer(9);
    public static final Integer SESSION_CHANGED_SESSION_ID_DEF_IN_PROF_AND_RECEIPT = new Integer(10);
    public static final Integer SESSION_CHANGED_ULG_ID_IS_NULL_PROFILE = new Integer(11);
    public static final Integer SESSION_CHANGED_ULG_ID_IS_NOT_EXISTS_ON_DB = new Integer(12);
    public static final Integer SESSION_CHANGED_ULG_DUH_DEF_PROF = new Integer(13);
    public static final Integer SESSION_CHANGED_ULG_DUH_DEF_REC = new Integer(14);
    public static final Integer SESSION_CHANGED_ULG_IP_ADD_DEF_PROF = new Integer(15);
    public static final Integer SESSION_CHANGED_ULG_IP_ADD_DEF_REC = new Integer(16);
    public static final Integer SESSION_CHANGED_ULG_SESSION_ID_DEF_PROF = new Integer(17);
    public static final Integer SESSION_CHANGED_ULG_SESSION_ID_DEF_REC = new Integer(18);
    public static final Integer SESSION_CHANGED_ULG_IP_ADD_NULL = new Integer(19);
    public static final Integer SESSION_CHANGED_ULG_DUH_NULL = new Integer(20);
    public static final Integer SESSION_CHANGED_ULG_SESSION_ID_NULL = new Integer(21);
    /*
     * Instance variables.
     */
    
    /** Violations. */
    private BusinessRuleVO[] violations;
    
    /** Transaction type, vehicles, fines... . */
    private Integer transactionType;
    
    /** Transaction center ID. */
    private Long centerId;
    
    /** Transaction status. */
    private Integer status;
    
    /** Transaction status date. */
    private Date statusDate;
    
    /** Transaction service code. */
    private Integer serviceCode;
    
    /** Service name. */
    private String serviceName;
    
    /** Person ID related to the transaction owner. */
    private Long personId;
    
    /** Organization ID related to the transaction owner. */
    private Long organizationId;
    
    /** Queue ID for this transaction. */
    private Long queueId;
    
    /** User ID related to the employee. */
    private Long userId;
    

    
    /** CML application ID. */
    private Long cmlApplicationId;
    
    /** Transaction remarks. */
    private String remarks;
    

    
    /** Driving license ID. */
    private Long drivingLicenseId;
    
    /** Service value object */
    private ServiceVO service;

    /** Related transaction receipts. */
    private List receiptsList;
    
    /** E-Services Note Arabic */
    private String eServicesNoteAr;
    
    /** E-Services Note English */
    private String eServicesNoteEn;
    

    
    /** Delivery Mode. */
    private String deliveryModeAr;

    

    
    /** Service code vhl. */
    private Integer serviceCodeVhl;
    
    /** Service code Rvp. */
    private Integer serviceCodeRvp;
    
    /** Service code ACT (SPL service Code). */
    private Integer serviceCodeAct;

    
    /** Indecator if service info was changes and must be updated. */
    private boolean serviceInfoChanged;
    
    /** Sent to EGov */
    private Integer sentToEGov;
    
    /** Agent Name */
    private String agentName;
    
    /** Attachment Sequence No. */
    private Long attachmentRefNo;
    
    /** List of Transaction Attachments */
    private List transactionAttachments;
    


    /** Receipts Total Amount */
    private Long totalAmount;     
    
    /** Number Of Receipts. */
    private Integer numberOfReceipts;
     
    /** Ship Package No */
    private Long shipPackageNo;
    
    /** Sequence In Ship Package */
    private Long sequenceInShipPackage;
    
    /** Transaction Status Arabic Description */
    private String trsStatusDescAr;
    
    /** Transaction Status English Description */
    private String trsStatusDescEn;    
    
    /** DEG Transaction Reference Number. */
    private Long degRefNo;
    
    /** DEG Send Date */
    private Date degSendDate;
    
    /** Is Receiption */
    private Integer isReceiption;    
    
    /** Address */
    private String address;
    
    /** P O Box */
    private String poBox;
    
    /** Phone */
    private String phone;
    
    /** Office Phone */
    private String officePhone;    
    
    /** Mobile */
    private String mobile;
    
    /** Emirates Code */
    private String addressEmiCode;
    
    /** Is Sent */
    private Integer isSent;
    

    
    /** Delivery Mode */
    private Integer deliveryMode;
  
    
 
    

    

    
    /** Delivery Mode Arabic Description */
    private String deliveryModeDescAr;
    
   /** Delivery Mode English Description */
    private String deliveryModeDescEn;
    

    
    /** Email */
    private String email;
    
  
    
    /** Transaction "is Sent" Description. */
    private String isSentDescription;
    
    /** Transaction sent date  **/
    private Date sentDate;

    
    /**  Bank Transaction Id */
    private String bankTranactionId;
    
    /** lan Code */
    private String lanCode;
    
 
    
    /** Related transaction tickets. */
    private List ticketsList;
    
    /** Service code cml. */
    private Integer serviceCodeCml;
    

    
    /** E-Wallet Account Number */
    private String eWalletAccountNo;
    
    /** E-Wallet Reverse Reference */
    private Long eWalletReverseRef;
    
    /** share Email */
    private String shareEmail;
    
    /** issue Date From*/
    private Date issueDateFrom;
      
    /** issue Date To*/
    private Date issueDateTo;
    
    /** print Status*/
    private Integer printStatus;
    
    /** List of Allocating Auction Plate Transaction Value Object.*/
    private List allocatingAuctionPlatesList;
  
    
    /** status Arabic Description*/
    private String statusArDesc;
    
    /** status English Description*/
    private String statusEnDesc;
    
    /** Transaction Count */
    private Integer transactionCount;
    


    /** eid Data Source */
    private Integer eidDataSource;
    
    /** eid Data Source */
    private Date eidExpiryDate;
    
    /** eid Number */
    private String eidNumber;

  
    /** Dsg Sp Code */
    private String dsgSpCode;
    
    /** Has Unprinted Basket Card */
    private Integer hasUnprintedBasketCard;
    /** Dsg Service Code */
    private String dsgServiceCode;
    
    
    /** List Of Trs Authority Nocs VO.*/
    private List authorityNocsList;
    
    /** List of Res Application Trainee Value Object */
    private List traineesList;
    
  

    private Integer parentServiceCode ;
    
   
    /** public Relations Officer (PRO) */
    private boolean publicRelationsOfficer;
    
    /** permit Category Id */
    private Integer permitCategoryId;
    
    /** need To Be Received By Pro */
    private boolean needToBeReceivedByPro;
    
    /** selected Pro Value */
    private Integer selectedProValue;
    
    /** Payment method */
    private Integer paymentMethod;
    
    /** Auto-Certify flag */
    private boolean autoCertify = true;
    private CategoryVO categoryVO;
    private PurchaseVO purchaseVO;
    private LocationVO locationVO;
   
    
    public LocationVO getLocationVO() {
		return locationVO;
	}

	public void setLocationVO(LocationVO locationVO) {
		this.locationVO = locationVO;
	}

	public PurchaseVO getPurchaseVO() {
		return purchaseVO;
	}

	public void setPurchaseVO(PurchaseVO purchaseVO) {
		this.purchaseVO = purchaseVO;
	}

	public CategoryVO getCategoryVO() {
		return categoryVO;
	}

	public void setCategoryVO(CategoryVO categoryVO) {
		this.categoryVO = categoryVO;
	}

	/** Employee Number. */
    private String employeeNo;

    /** List of Allocating Plate Transaction Value Object.*/
    private List allocatingPlatesList;
    
    /** DEG Custom Transaction Reference Number. */
    private String degRefNoCust;
    
    /** SDDI Logged In Traffic Id */
    private Long sddiLoggedInTrafficId;

    /** Is Sell Package Plate */
    private boolean sellPackagePlate;
  
    
    

    /** Has Zero Total Amount */
    private boolean hasZeroTotalAmount;


    /*
     * Constructors.
     */
     
  
    
    /**
     * Construct and initialize new TransactionVO object.
     * 
     * @param newId transaction ID.
     */
    public TransactionVO() {
        
    }

    /*
     * Methods
     */

    /**
     * Set Transaction center ID.
     * 
     * @param ctrId Transaction center ID.
     */
    public void setCenterId(Long ctrId) {
        this.centerId = ctrId;
    }

    /**
     * Get Transaction center ID.
     * 
     * @return Transaction center ID.
     */
    public Long getCenterId() {
        return centerId;
    }

    /**
     * Set Transaction status.
     * 
     * @param sts Transaction status.
     */
    public void setStatus(Integer sts) {
        if (sts == null) {
            this.status = sts;
            return;
        }
        
        switch (sts.intValue()) {
            case STATUS_NEW:
            case STATUS_CERTIFIED:
            case STATUS_CONFIRMED:
            case STATUS_CANCELED:
            case STATUS_PENDING:
            case STATUS_POSTED:
            case STATUS_REVERSED:
                 this.status = sts;
                 break;
            default:
                 throw new IllegalArgumentException(new StringBuffer(
                   "Invalid transaction status: ").append(sts).toString());
        }
    }

    /**
     * Get Transaction status.
     * 
     * @return Transaction status.
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Set Transaction status date.
     * 
     * @param date Transaction status date.
     */
    public void setStatusDate(Date date) {
        this.statusDate = date;
    }

    /**
     * Get Transaction status date.
     * 
     * @return Transaction status date.
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * Set Transaction service code.
     * 
     * @param code Transaction service code.
     */
    public void setServiceCode(Integer code) {
        this.serviceCode = code;
    }

    /**
     * Get Transaction service code.
     * 
     * @return Transaction service code.
     */
    public Integer getServiceCode() {
        return serviceCode;
    }

    /**
     * Set Person ID related to the transaction owner.
     * 
     * @param prsId Person ID related to the transaction owner.
     */
    public void setPersonId(Long prsId) {
        this.personId = prsId;
    }

    /**
     * Get Person ID related to the transaction owner.
     * 
     * @return Person ID related to the transaction owner.
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * Set Organization ID related to the transaction owner.
     * 
     * @param orgId Organization ID related to the transaction owner.
     */
    public void setOrganizationId(Long orgId) {
        this.organizationId = orgId;
    }

    /**
     * Get Organization ID related to the transaction owner.
     * 
     * @return Organization ID related to the transaction owner.
     */
    public Long getOrganizationId() {
        return organizationId;
    }

    /**
     * Set Queue ID for this transaction.
     * 
     * @param queId Queue ID for this transaction.
     */
    public void setQueueId(Long queId) {
        this.queueId = queId;
    }

    /**
     * Get Queue ID for this transaction.
     * 
     * @return Queue ID for this transaction.
     */
    public Long getQueueId() {
        return queueId;
    }

    /**
     * Set Transaction type, vehicles, fines...
     * 
     * @param type Transaction type, vehicles, fines...
     */
    public void setTransactionType(Integer type) {
        if (type == null) {
            this.transactionType = type;
            return;
        }

        switch (type.intValue()) {
            case TYPE_VEHICLES:
            case TYPE_DRIVING_LICENSES:
            case TYPE_FINES:
            case TYPE_SPECIAL_PLATES:
            case TYPE_DRIVING_TEST:
            case TYPE_COMMERCIAL_LICENSING:
            case TYPE_SPECIAL_RECEIPTS:
            case TYPE_REVAMP:
            case TYPE_CAR_INSPECTION_SYSTEM:
            case TYPE_GENERAL:
                 this.transactionType = type;
                 break;
            default:
                 throw new IllegalArgumentException(new StringBuffer(
                   "Invalid transaction type: ").append(type).toString());
        }
    }

    /**
     * Get Transaction type, vehicles, fines...
     * 
     * @return Transaction type, vehicles, fines...
     */
    public Integer getTransactionType() {
        return transactionType;
    }
    
    /**
     * Check if transaction status is new.
     * 
     * @return true if transaction status is new.
     */
    public boolean isNew() {
        return (getStatus() != null && getStatus().intValue() == STATUS_NEW);
    }

    /**
     * Check if transaction status is certified.
     * 
     * @return true if transaction status is certified.
     */
    public boolean isCertified() {
        return (getStatus() != null && getStatus().intValue() == STATUS_CERTIFIED);
    }

    /**
     * Check if transaction status is confirmed.
     * 
     * @return true if transaction status is confirmed.
     */
    public boolean isConfirmed() {
        return (getStatus() != null && getStatus().intValue() == STATUS_CONFIRMED);
    }

    /**
     * Check if transaction status is canceled.
     * 
     * @return true if transaction status is canceled.
     */
    public boolean isCanceled() {
        return (getStatus() != null && getStatus().intValue() == STATUS_CANCELED);
    }

    /**
     * Check if transaction status is pending.
     * 
     * @return true if transaction status is pending.
     */
    public boolean isPending() {
        return (getStatus() != null && getStatus().intValue() == STATUS_PENDING);
    }

    /**
     * Check if transaction status is posted.
     * 
     * @return true if transaction status is posted.
     */
    public boolean isPosted() {
        return (getStatus() != null && getStatus().intValue() == STATUS_POSTED);
    }

    /**
     * Check if transaction status is reversed.
     * 
     * @return true if transaction status is reversed.
     */
    public boolean isReversed() {
        return (getStatus() != null && getStatus().intValue() == STATUS_REVERSED);
    }

    /**
     * Check if transaction type is related to vehicles.
     * 
     * @return true if transaction type is related to vehicles.
     */
    public boolean isVehicleTransaction() {
        return (getTransactionType() != null && 
                getTransactionType().intValue() == TYPE_VEHICLES);
    }

    /**
     * Check if transaction type is related to fines.
     * 
     * @return true if transaction type is related to fines.
     */
    public boolean isFineTransaction() {
        return (getTransactionType() != null && 
                getTransactionType().intValue() == TYPE_FINES);
    }

    /**
     * Check if transaction type is related to driving license.
     * 
     * @return true if transaction type is related to driving license.
     */
    public boolean isDrivingLicenseTransaction() {
        return (getTransactionType() != null && 
                getTransactionType().intValue() == TYPE_DRIVING_LICENSES);
    }

    /**
     * Check if transaction type is related to special plates.
     * 
     * @return true if transaction type is related to special plates.
     */
    public boolean isSpecialPlateTransaction() {
        return (getTransactionType() != null && 
                getTransactionType().intValue() == TYPE_SPECIAL_PLATES);
    }

    /**
     * Check if transaction type is related to driving test.
     * 
     * @return true if transaction type is related to driving test.
     */
    public boolean isDrivingTestTransaction() {
        return (getTransactionType() != null && 
                getTransactionType().intValue() == TYPE_DRIVING_TEST);
    }

    /**
     * Check if transaction type is related to commercial licensing.
     * 
     * @return true if transaction type is related to commercial licensing.
     */
    public boolean isCommercialLicensingTransaction() {
        return (getTransactionType() != null && 
                getTransactionType().intValue() == TYPE_COMMERCIAL_LICENSING);
    }

    /**
     * Check if transaction type is related to special receipts.
     * 
     * @return true if transaction type is related to special receipts.
     */
    public boolean isSpecialReceiptsTransaction() {
        return (getTransactionType() != null && 
                getTransactionType().intValue() == TYPE_SPECIAL_RECEIPTS);
    }  
    
    /**
     * Check if transaction type is related to parking and pavement.
     * 
     * @return true if transaction type is related parking and pavement.
     */
    public boolean isParkingAndPavementTransaction() {
        return (getTransactionType() != null && 
                getTransactionType().intValue() == TYPE_REVAMP);
    }
    /**
     * Set User ID related to the employee.
     * 
     * @param newId User ID related to the employee.
     */
    public void setUserId(Long newId) {
        this.userId = newId;
    }

    /**
     * Get User ID related to the employee.
     * 
     * @return User ID related to the employee.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Set Service name.
     * 
     * @param name Service name.
     */
    public void setServiceName(String name) {
        this.serviceName = name;
    }

    /**
     * Get Service name.
     * 
     * @return Service name.
     */
    public String getServiceName() {
        return serviceName;
    }


    /**
     * Set CML application ID.
     * 
     * @param cmlApplicationId CML application ID.
     */
    public void setCmlApplicationId(Long cmlApplicationId) {
        this.cmlApplicationId = cmlApplicationId;
    }

    /**
     * Get CML application ID.
     * 
     * @return CML application ID.
     */
    public Long getCmlApplicationId() {
        return cmlApplicationId;
    }

    /**
     * Set Transaction remarks.
     * 
     * @param remarks Transaction remarks.
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * Get Transaction remarks.
     * 
     * @return Transaction remarks.
     */
    public String getRemarks() {
        return remarks;
    }

 
    /**
     * Set Driving license ID.
     * 
     * @param drivingLicenseId Driving license ID.
     */
    public void setDrivingLicenseId(Long drivingLicenseId) {
        this.drivingLicenseId = drivingLicenseId;
    }

    /**
     * Get Driving license ID.
     * 
     * @return Driving license ID.
     */
    public Long getDrivingLicenseId() {
        return drivingLicenseId;
    }


    /**
     * Set Related transaction receipts.
     * 
     * @param receiptsList Related transaction receipts.
     */
    public void setReceiptsList(List receiptsList) {
        this.receiptsList = receiptsList;
    }

    /**
     * Get Related transaction receipts.
     * 
     * @return Related transaction receipts.
     */
    public List getReceiptsList() {
        return receiptsList;
    }
    

    /**
     * Set E-Services Note Arabic
     * 
     * @param eServicesNoteAr E-Services Note Arabic
     */
    public void setEServicesNoteAr(String eServicesNoteAr) {
        this.eServicesNoteAr = eServicesNoteAr;
    }

    /**
     * get E-Services Note Arabic
     * 
     * @return eServicesNoteAr E-Services Note Arabic
     */
    public String getEServicesNoteAr() {
        return eServicesNoteAr;
    }

    /**
     * Set E-Services Note English
     * 
     * @param eServicesNoteEn E-Services Note English
     */
    public void setEServicesNoteEn(String eServicesNoteEn) {
        this.eServicesNoteEn = eServicesNoteEn;
    }

    /**
     * Get E-Services Note English
     * 
     * @return eServicesNoteEn E-Services Note English
     */
    public String getEServicesNoteEn() {
        return eServicesNoteEn;
    }



 


    /**
     * Set Delivery Mode.
     * 
     * @param deliveryModeAr Delivery Mode.
     */
    public void setDeliveryModeAr(String deliveryModeAr) {
        this.deliveryModeAr = deliveryModeAr;
    }

    /**
     * Get Delivery Mode.
     * 
     * @return Delivery Mode.
     */
    public String getDeliveryModeAr() {
        return deliveryModeAr;
    }

    /**
     * Set Service code vhl.
     * 
     * @param serviceCodeVhl Service code vhl.
     */
    public void setServiceCodeVhl(Integer serviceCodeVhl) {
        this.serviceCodeVhl = serviceCodeVhl;
    }

    /**
     * Get Service code vhl.
     * 
     * @return Service code vhl.
     */
    public Integer getServiceCodeVhl() {
        return serviceCodeVhl;
    }

    /**
     * Set Service code vhl.
     * 
     * @param serviceCodeRvp Service code vhl.
     */
    public void setServiceCodeRvp(Integer serviceCodeRvp) {
        this.serviceCodeRvp = serviceCodeRvp;
    }

    /**
     * Get Service code rvp.
     * 
     * @return Service code rvp.
     */
    public Integer getServiceCodeRvp() {
        return serviceCodeRvp;
    }

    /**
     * Set Service code ACT (SPL service Code).
     * 
     * @param serviceCodeAct Service code ACT (SPL service Code).
     */
    public void setServiceCodeAct(Integer serviceCodeAct) {
        this.serviceCodeAct = serviceCodeAct;
    }

    /**
     * Get Service code ACT (SPL service Code).
     * 
     * @return Service code ACT (SPL service Code).
     */
    public Integer getServiceCodeAct() {
        return serviceCodeAct;
    }
    


    /**
     * Set Indecator if service info was changes and must be updated.
     * 
     * @param flag Indecator if service info was changes and must be updated.
     */
    public void setServiceInfoChanged(boolean flag) {
        this.serviceInfoChanged = flag;
    }

    /**
     * Check if service info was changes and must be updated.
     * 
     * @return true if service info was changes and must be updated.
     */
    public boolean isServiceInfoChanged() {
        return serviceInfoChanged;
    }

    /**
     * Set Sent to EGov
     * 
     * @param sentToEGov Sent to EGov
     */
    public void setSentToEGov(Integer sentToEGov) {
        this.sentToEGov = sentToEGov;
    }

    /**
     * Get Sent to EGov
     * 
     * @return Sent to EGov
     */
    public Integer getSentToEGov() {
        return sentToEGov;
    }

    /**
     * Set Agent Name
     * 
     * @param agentName Agent Name
     */
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    /**
     * Get Agent Name
     * 
     * @return agentName Agent Name
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     * Set Attachment Sequence No.
     * 
     * @param attachmentRefNo Attachment Sequence No.
     */
    public void setAttachmentRefNo(Long attachmentRefNo) {
        this.attachmentRefNo = attachmentRefNo;
    }

    /**
     * Get Attachment Sequence No.
     * 
     * @return Attachment Sequence No.
     */
    public Long getAttachmentRefNo() {
        return attachmentRefNo;
    }



    /**
     * Set Receipts Total Amount
     * 
     * @param totalAmount Receipts Total Amount
     */
    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * Get Receipts Total Amount
     * 
     * @return Receipts Total Amount
     */
    public Long getTotalAmount() {
        return totalAmount;
    }

    /**
     * Set Number Of Receipts.
     * 
     * @param numberOfReceipts Number Of Receipts.
     */
    public void setNumberOfReceipts(Integer numberOfReceipts) {
        this.numberOfReceipts = numberOfReceipts;
    }

    /**
     * Get Number Of Receipts.
     * 
     * @return Number Of Receipts.
     */
    public Integer getNumberOfReceipts() {
        return numberOfReceipts;
    }

    /**
     * Set Ship Package Number
     * 
     * @param shipPackageNo Ship Package Number
     */
    public void setShipPackageNo(Long shipPackageNo) {
        this.shipPackageNo = shipPackageNo;
    }

    /**
     * Get Ship Package Number
     * 
     * @return shipPackageNo Ship Package Number
     */
    public Long getShipPackageNo() {
        return shipPackageNo;
    }

    /**
     * Set Sequence In Ship Package
     * 
     * @param sequenceInShipPackage Sequence In Ship Package
     */
    public void setSequenceInShipPackage(Long sequenceInShipPackage) {
        this.sequenceInShipPackage = sequenceInShipPackage;
    }

    /**
     * Get Sequence In Ship Package
     * 
     * @return sequenceInShipPackage Sequence In Ship Package
     */
    public Long getSequenceInShipPackage() {
        return sequenceInShipPackage;
    }
    
    /**
     * Set Transaction status arabic descripotion
     * 
     * @param trsStatusDescAr Transaction status arabic descripotion
     */
    public void setTrsStatusDescAr(String trsStatusDescAr) {
        this.trsStatusDescAr = trsStatusDescAr;
    }

    /**
     * Get Transaction status arabic descripotion
     * 
     * @return Transaction status arabic descripotion
     */
    public String getTrsStatusDescAr() {
        return trsStatusDescAr;
    }

    /**
     * Set Transaction status english description
     * 
     * @param trsStatusDescEn Transaction status english description
     */
    public void setTrsStatusDescEn(String trsStatusDescEn) {
        this.trsStatusDescEn = trsStatusDescEn;
    }

    /**
     * Get Transaction status english description
     * 
     * @return Transaction status english description
     */
    public String getTrsStatusDescEn() {
        return trsStatusDescEn;
    }    

    /**
     * Set DEG Transaction Reference Number.
     * 
     * @param degRefNo DEG Transaction Reference Number.
     */
    public void setDegRefNo(Long degRefNo) {
        this.degRefNo = degRefNo;
    }

    /**
     * Get DEG Transaction Reference Number.
     * 
     * @return DEG Transaction Reference Number.
     */
    public Long getDegRefNo() {
        return degRefNo;
    }
    
    /**
     * Set Is Receiption
     * 
     * @param isReceiption Is Receiption
     */
    public void setIsReceiption(Integer isReceiption) {
        this.isReceiption = isReceiption;
    }

    /**
     * Get Is Receiption
     * 
     * @return isReceiption Is Receiption
     */
    public Integer getIsReceiption() {
        return isReceiption;
    }
    
 

    /**
     * Set Address
     * 
     * @param address Address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get Address
     * 
     * @return address Address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set P O Box
     * 
     * @param poBox P O Box
     */
    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

    /**
     * Get P O Box
     * 
     * @return poBox P O Box
     */
    public String getPoBox() {
        return poBox;
    }

    /**
     * Set Phone
     * 
     * @param phone Phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Get Phone
     * 
     * @return phone Phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set Mobile
     * 
     * @param mobile Mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * Get Mobile
     * 
     * @return mobile Mobile
     */
    public String getMobile() { 
        return mobile;
    }

    /**
     * Set Emirates Code
     * 
     * @param addressEmiCode Emirates Code
     */
    public void setAddressEmiCode(String addressEmiCode) {
        this.addressEmiCode = addressEmiCode;
    } 

    /**
     * Get Emirates Code
     * 
     * @return addressEmiCode Emirates Code
     */
    public String getAddressEmiCode() {
        return addressEmiCode;
    }

    /**
     * Set Office Phone
     * @param officePhone Office Phone
     */
    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    /**
     * Get Office Phone
     * 
     * @return officePhone Office Phone
     */
    public String getOfficePhone() {
        return officePhone;
    }

    /**
     * Set Is Sent
     * 
     * @param isSent Is Sent
     */
    public void setIsSent(Integer isSent) {
        this.isSent = isSent;
    }

    /**
     * Get Is Sent
     * 
     * @return isSent Is Sent
     */
    public Integer getIsSent() {
        return isSent;
    }



    /**
     * Set Delivery Mode
     * 
     * @param deliveryMode Delivery Mode
     */
    public void setDeliveryMode(Integer deliveryMode) {
        if(deliveryMode == null || 
            deliveryMode.equals(DELIVERY_MODE_COURIER) ||             
            deliveryMode.equals(DELIVERY_MODE_COLLECTION) ||
            deliveryMode.equals(DELIVERY_MODE_ECERTIFICATE) ||  
            deliveryMode.equals(DELIVERY_MODE_MAIL) ||  
            deliveryMode.equals(DELIVERY_MODE_IMMIDIATE_CARD_PRINTING)||
            deliveryMode.equals(DELIVERY_MODE_PRINTED_KISOK) ||
            deliveryMode.equals(DELIVERY_MODE_COURIER_AND_COLLECTION)) {
            
            this.deliveryMode = deliveryMode;
            
        } else {
            throw new IllegalArgumentException("Invalid deliveryMode value:"+deliveryMode) ;
        }
    }

    /**
     * Get Delivery Mode
     * 
     * @return Delivery Mode
     */
    public Integer getDeliveryMode() {
        return deliveryMode;
    }
    
    /**
     * Check if delivery mode courier
     * 
     * @return true if yes
     */
    public boolean isDeliveryModeCourier() {
        return getDeliveryMode() != null && getDeliveryMode().equals(DELIVERY_MODE_COURIER);
    }
    
    /**
     * Check if delivery mode Mail
     * 
     * @return true if yes
     */
    public boolean isDeliveryModeMail() {
        return getDeliveryMode() != null && getDeliveryMode().equals(DELIVERY_MODE_MAIL);
    }
    
    /**
     * Check if delivery mode Immidiate Card Printing.
     * 
     * @return true if yes
     */
    public boolean isDeliveryModeImmidiateCardPrinting() {
        return getDeliveryMode() != null && getDeliveryMode().equals(DELIVERY_MODE_IMMIDIATE_CARD_PRINTING);
    }

    /**
     * Check if delivery mode Collection
     * 
     * @return true if yes
     */
    public boolean isDeliveryModeCollection() {
        return getDeliveryMode() != null && getDeliveryMode().equals(DELIVERY_MODE_COLLECTION);
    }
    
    /**
     * Check if delivery mode E-Certificate
     * 
     * @return true if yes
     */
    public boolean isDeliveryModeECertificate() {
        return getDeliveryMode() != null && getDeliveryMode().equals(DELIVERY_MODE_ECERTIFICATE);
    }    




    
    /**
     * Is Sent locked
     * 
     * @return true if yes
     */
    public boolean isSentLocked() {
        return getIsSent() != null && getIsSent().equals(IS_SENT_LOCKED);
    }
    
    /**
     * Is Sent Success
     * 
     * @return true if yes
     */
    public boolean isSentSuccess() {
        return getIsSent() != null && getIsSent().equals(IS_SENT_SUCCESS);
    }    

    /**
     * Is Sent To EWallet
     * 
     * @return true if the transaction is sent to ewallet
     */
    public boolean isSentToEwallet() {
        return getIsSent() != null && getIsSent().equals(IS_SENT_TO_EWALLET);
    }   

 

    /**
     * Set Transaction Attachments
     * 
     * @param transactionAttachments Transaction Attachments
     */
    public void setTransactionAttachments(List transactionAttachments) {
        this.transactionAttachments = transactionAttachments;
    }

    /**
     * Get Transaction Attachments
     * 
     * @return transactionAttachments Transaction Attachments
     */
    public List getTransactionAttachments() {
        return transactionAttachments;
    }

   



    /**
     * Set Delivery Mode Arabic Description
     * 
     * @param deliveryModeDescAr Delivery Mode Arabic Description
     */
    public void setDeliveryModeDescAr(String deliveryModeDescAr) {
        this.deliveryModeDescAr = deliveryModeDescAr;
    }

    /**
     * Get Delivery Mode Arabic Description
     * 
     * @return Delivery Mode Arabic Description
     */
    public String getDeliveryModeDescAr() {
        return deliveryModeDescAr;
    }


    /**
     * Setter for Email
     * @param email Email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for Email
     * @return email Email
     */
    public String getEmail() {
        return email;
    }


    
    /**
     * Setter For is Sent Description
     * 
     * @param isSentDescription is Sent Description
     */
    public void setIsSentDescription(String isSentDescription) {
        this.isSentDescription = isSentDescription;
    }

    /**
     * Getter For is Sent Description
     * 
     * @return isSentDescription is Sent Description
     */
    public String getIsSentDescription() {
        return isSentDescription;
    }

    /**
     * Setter For Sent Date
     * 
     * @param sentDate Sent Date
     */
    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    /**
     * Getter For Sent Date
     * 
     * @return sentDate Sent Date
     */
    public Date getSentDate() {
        return sentDate;
    }
    
    /**
     * Check if is SPL Transaction Service.
     * 
     * @return True if is SPL Transaction Service.
     *         False if not SPL Transaction Service.
     */
    public boolean isSplTransaction() {
        return getServiceCodeAct() != null;
    }
    


    /**
     * Set Bank Transaction Id.
     * 
     * @param bankTranactionId Bank Transaction Id.
     */
    public void setBankTranactionId(String bankTranactionId) {
        this.bankTranactionId = bankTranactionId;
    }

    /**
     * Get Bank Transaction Id.
     * 
     * @return Bank Transaction Id.
     */
    public String getBankTranactionId() {
        return bankTranactionId;
    }

    /**
     * Set lanCode.
     * 
     * @param lanCode lan Code.
     */
    public void setLanCode(String lanCode) {
        this.lanCode = lanCode;
    }

    /**
     * Get lanCode.
     * 
     * @return lanCode lan Code.
     */
    public String getLanCode() {
        return lanCode;
    }

    /**
     * Set Deg Send Date
     * 
     * @param degSendDate Deg Send Date
     */
    public void setDegSendDate(Date degSendDate) {
        this.degSendDate = degSendDate;
    }




    
    /**
     * Set Related transaction tickets.
     * 
     * @param ticketsList Related transaction tickets.
     */
    public void setTicketsList(List ticketsList) {
        this.ticketsList = ticketsList;
    }

    /**
     * Get Related transaction tickets.
     * 
     * @return Related transaction tickets.
     */
    public List getTicketsList() {
        return ticketsList;
    }
    
    



    /**
     * Setter for Service Code Cml
     * @param serviceCodeCml:Service Code Cml
     */
    public void setServiceCodeCml(Integer serviceCodeCml) {
        this.serviceCodeCml = serviceCodeCml;
    }

    /**
     * Getter for Service Code Cml
     * @return serviceCodeCml:Service Code Cml
     */
    public Integer getServiceCodeCml() {
        return serviceCodeCml;
    }


    /**
     * Set E-Wallet Account Number
     * 
     * @param eWalletAccountNo E-Wallet Account Number
     */
    public void setEWalletAccountNo(String eWalletAccountNo) {
        this.eWalletAccountNo = eWalletAccountNo;
    }

    /**
     * Get E-Wallet Account Number
     * 
     * @return eWalletAccountNo E-Wallet Account Number
     */
    public String getEWalletAccountNo() { 
        return eWalletAccountNo;
    }

    /**
     * Set E-Wallet Reverse Reference
     * 
     * @param eWalletReverseRef E-Wallet Reverse Reference
     */
    public void setEWalletReverseRef(Long eWalletReverseRef) {
        this.eWalletReverseRef = eWalletReverseRef;
    }

    /**
     * Get E-Wallet Reverse Reference
     * 
     * @return eWalletReverseRef E-Wallet Reverse Reference
     */
    public Long getEWalletReverseRef() {
        return eWalletReverseRef;
    }

    /**
     * Set shareEmail
     * 
     * @param shareEmail : share Email
     */
    public void setShareEmail(String shareEmail) {
        this.shareEmail = shareEmail;
    }

    /**
     * Get shareEmail
     * 
     * @return shareEmail : share Email
     */
    public String getShareEmail() {
        return shareEmail;
    }
    



    /**
     * Set issueDateFrom
     * 
     * @param issueDateFrom : issue Date From.
     */
    public void setIssueDateFrom(Date issueDateFrom) {
        this.issueDateFrom = issueDateFrom;
    }

    /**
     * Get issueDateFrom
     * 
     * @return issueDateFrom : issue Date From.
     */
    public Date getIssueDateFrom() {
        return issueDateFrom;
    }

    /**
     * Set issueDateTo
     * 
     * @param issueDateTo : issue Date To.
     */
    public void setIssueDateTo(Date issueDateTo) {
        this.issueDateTo = issueDateTo;
    }

    /**
     * Get issueDateTo
     * 
     * @return issueDateTo : issue Date To.
     */
    public Date getIssueDateTo() {
        return issueDateTo;
    }

    /**
     * Set printStatus
     * 
     * @param printStatus : print Status.
     */
    public void setPrintStatus(Integer printStatus) {
        this.printStatus = printStatus;
    }

    /**
     * Get printStatus
     * 
     * @return printStatus : print Status.
     */
    public Integer getPrintStatus() {
        return printStatus;
    }

    /**
     * Setter List of Allocating Auction Plate Transaction Value Object.
     * 
     * @param allocatingAuctionPlatesList : List of Allocating Auction Plate Transaction Value Object.
     */
    public void setAllocatingAuctionPlatesList(List allocatingAuctionPlatesList) {
        this.allocatingAuctionPlatesList = allocatingAuctionPlatesList;
    }

    /**
     * Get List of Allocating Auction Plate Transaction Value Object.
     * 
     * @return List of Allocating Auction Plate Transaction Value Object.
     */
    public List getAllocatingAuctionPlatesList() {
        return allocatingAuctionPlatesList;
    }

  

    /**
     * Set status Arabic Description
     * 
     * @param statusArDesc
     */
    public void setStatusArDesc(String statusArDesc) {
        this.statusArDesc = statusArDesc;
    }

    /**
     * Get Status Arabic Description
     * 
     * @return statusArDesc
     */
    public String getStatusArDesc() {
        return statusArDesc;
    }

   /**
     *Set Status English Description
     *
     * @param statusEnDesc
     */
    public void setStatusEnDesc(String statusEnDesc) {
        this.statusEnDesc = statusEnDesc;
    }

    /**
     * Get English Description
     * 
     * @return statusEnDesc
     */
    public String getStatusEnDesc() {
        return statusEnDesc;
    }

   /**
     *Set Delivery Mode English Description
     *
     * @param deliveryModeDescEn
     */
    public void setDeliveryModeDescEn(String deliveryModeDescEn) {
        this.deliveryModeDescEn = deliveryModeDescEn;
    }

    /**
     * Get Delivry Mode English Description
     * 
     * @return deliveryModeDescEn
     */
    public String getDeliveryModeDescEn() {
        return deliveryModeDescEn;
    }

    /**
     * Setter for Transaction Count
     * 
     * @param transactionCount Transaction Count
     */
    public void setTransactionCount(Integer transactionCount) {
        this.transactionCount = transactionCount;
    }

    /**
     * Getter for Transaction Count
     * 
     * @return transactionCount Transaction Count
     */
    public Integer getTransactionCount() {
        return transactionCount;
    }
    
 
    /**
     * Setter For eid Data Source.
     * 
     * @param eidDataSource : eid Data Source.
     */
    public void setEidDataSource(Integer eidDataSource) {
        this.eidDataSource = eidDataSource;
    }
    
    /**
     * Getter For eidDataSource.
     * 
     * @return eidDataSource : eid Data Source.
     */
    public Integer getEidDataSource() {
        return eidDataSource;
    }

    /**
     * Setter For eid Expiry Date.
     * 
     * @param eidExpiryDate : eid Expiry Date.
     */
    public void setEidExpiryDate(Date eidExpiryDate) {
        this.eidExpiryDate = eidExpiryDate;
    }

    /**
     * Getter For eidExpiryDate.
     * 
     * @return eidExpiryDate : eid Expiry Date.
     */
    public Date getEidExpiryDate() {
        return eidExpiryDate;
    }



	/**
     * Setter For eidNumber.
     * 
     * @param eidNumber : eidNumber.
     */
    public void setEidNumber(String eidNumber) {
        this.eidNumber = eidNumber;
    }

    /**
     * Getter For eidNumber.
     * 
     * @return eidNumber : eid Number.
     */
    public String getEidNumber() {
        return eidNumber;
    }
    
  
    
 

    /**
     * Setter for publicRelationsOfficer
     * 
     * @param publicRelationsOfficer : public Relations Officer.
     */
    public void setPublicRelationsOfficer(boolean publicRelationsOfficer) {
        this.publicRelationsOfficer = publicRelationsOfficer;
    }
    
    /**
     * Getter for publicRelationsOfficer
     * 
     * @return publicRelationsOfficer : public Relations Officer.
     */
    public boolean isPublicRelationsOfficer() {
        return publicRelationsOfficer;
    }

    /**
     * Setter for permitCategoryId
     * 
     * @param permitCategoryId : permit Category Id.
     */
    public void setPermitCategoryId(Integer permitCategoryId) {
        this.permitCategoryId = permitCategoryId;
    }

    /**
     * Getter for permitCategoryId
     * 
     * @return permitCategoryId : permit Category Id.
     */
    public Integer getPermitCategoryId() {
        return permitCategoryId;
    }

    /**
     * Setter for needToBeReceivedByPro
     * 
     * @param needToBeReceivedByPro : need To Be Received By Pro.
     */
    public void setNeedToBeReceivedByPro(boolean needToBeReceivedByPro) {
        this.needToBeReceivedByPro = needToBeReceivedByPro;
    }

    /**
     * Getter for needToBeReceivedByPro
     * 
     * @return needToBeReceivedByPro : need To Be Received By Pro.
     */
    public boolean isNeedToBeReceivedByPro() {
        return needToBeReceivedByPro;
    }

    /**
     * Setter for DSG SP Code 
     * 
     * @param dsgSpCode DSG SP Code 
     */
    public void setDsgSpCode(String dsgSpCode) {
        this.dsgSpCode = dsgSpCode;
    }

    /**
     * Getter for DSG SP Code 
     * 
     * @return dsgSpCode DSG SP Code 
     */
    public String getDsgSpCode() {
        return dsgSpCode;
    }

    /**
     * Setter for DSG Service Code
     * 
     * @param dsgServiceCode DSG Service Code
     */
    public void setDsgServiceCode(String dsgServiceCode) {
        this.dsgServiceCode = dsgServiceCode;
    }

    /**
     * Getter for DSG Service Code
     * 
     * @return dsgServiceCode DSG Service Code
     */
    public String getDsgServiceCode() {
        return dsgServiceCode;
    }


    /**
     * Setter for Has Unprinted Basket Card
     * 
     * @param hasUnprintedBasketCard Has Unprinted Basket Card
     */
    public void setHasUnprintedBasketCard(Integer hasUnprintedBasketCard) {
        this.hasUnprintedBasketCard = hasUnprintedBasketCard;
    }

    /**
     * Getter for Has Unprinted Basket Card
     * 
     * @return hasUnprintedBasketCard Has Unprinted Basket Card
     */
    public Integer getHasUnprintedBasketCard() {
        return hasUnprintedBasketCard;
    }

    /**
     * Setter for selectedProValue
     * 
     * @param selectedProValue :  selected Pro Value.
     */
    public void setSelectedProValue(Integer selectedProValue) {
        this.selectedProValue = selectedProValue;
    }

    /**
     * Getter for selected Pro Value
     * 
     * @return selectedProValue :  selected Pro Value.
     */
    public Integer getSelectedProValue() {
        return selectedProValue;
    }
    
    
     /**
     * Setter for List Of Trs Authority Nocs VO
     *   
     * @param authorityNocsList : authority Nocs List.
     */
    public void setAuthorityNocsList(List authorityNocsList) {
        this.authorityNocsList = authorityNocsList;
    }

    /**
     * Getter for List Of Trs Authority Nocs VO
     * 
     * @param authorityNocsList : authority Nocs List.
     */
    public List getAuthorityNocsList() {
        return authorityNocsList;
    }
    
    /**
     * Set Res Application Trainee Value Objects List
     * 
     * @param traineeList : List of Res Application Trainee Value Objects
     */
    public void setTraineesList(List traineesList) {
        this.traineesList = traineesList;
    }

    /**
     * Get Res Application Trainee Value Objects List
     * 
     * @return traineeList : List of Res Application Trainee Value Objects
     */
    public List getTraineesList() { 
        return traineesList;
    }
   





    public void setParentServiceCode(Integer parentServiceCode) {
        this.parentServiceCode = parentServiceCode;
    }


    public Integer getParentServiceCode() {
        return parentServiceCode;
    }





    /**
     * Setter Payment Method
     * 
     * @param paymentMethod
     */
    public void setPaymentMethod(Integer paymentMethod) {
      this.paymentMethod = paymentMethod;
    }

    /**
     * Getter for Payment Method
     * 
     * @return paymentMethod
     */
    public Integer getPaymentMethod() {
      return paymentMethod;
    }

    /**
     * Setter for autoCertify
     * 
     * @param autoCertify
     */
    public void setAutoCertify(boolean autoCertify) {
      this.autoCertify = autoCertify;
    }

    /**
     * Getter for autoCertify
     * 
     * @return autoCertify
     */
    public boolean isAutoCertify() {
      return autoCertify;
    }
    

    
    /**
     * Setter For Employee Number.
     * 
     * @param employeeNo
     */
    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }
    
    /**
     * Getter For Employee Number.
     * 
     * @return employeeNo
     */
    public String getEmployeeNo() {
        return employeeNo;
    }
    
    /**
     * Setter For Allocating Plates List.
     * 
     * @param allocatingPlatesList
     */
    public void setAllocatingPlatesList(List allocatingPlatesList) {
        this.allocatingPlatesList = allocatingPlatesList;
    }
    
    /**
     * Getter For Allocating Plates List.
     * 
     * @return allocatingPlatesList
     */
    public List getAllocatingPlatesList() {
        return allocatingPlatesList;
    }
    
    /**
     * Setter for Set Sddi Logged In Traffic Id
     * 
     * @param sddiLoggedInTrafficId Sddi Logged In Traffic Id
     */
    public void setSddiLoggedInTrafficId(Long sddiLoggedInTrafficId) {
        this.sddiLoggedInTrafficId = sddiLoggedInTrafficId;
    }
    
    /**
     * Getter for Set Sddi Logged In Traffic Id
     * 
     * @return sddiLoggedInTrafficId Sddi Logged In Traffic Id
     */
    public Long getSddiLoggedInTrafficId() {
        return sddiLoggedInTrafficId;
    }
    
    /**
     * Setter for Sell Packge Plate
     * 
     * @param sellPackagePlate Sell Packge Plate
     */
    public void setSellPackagePlate(boolean sellPackagePlate) {
        this.sellPackagePlate = sellPackagePlate;
    }
    
    /**
     * Getter for is Sell Package Plate
     * 
     * @return is Sell Package Plate
     */
    public boolean isSellPackagePlate() {
        return sellPackagePlate;
    }
    
    /**
     * Set DEG Custom Transaction Reference Number
     * 
     * @param degRefNoCust DEG Custom Transaction Reference Number.
     */
    public void setDegRefNoCust(String degRefNoCust) {
        this.degRefNoCust = degRefNoCust;
    }
    /**
     * Get DEG Custom Transaction Reference Number.
     * 
     * @return DEG Custom Transaction Reference Number.
     */
    public String getDegRefNoCust() {
        return degRefNoCust;
    }
    
  
    
    /**
     * Set Violations.
     * 
     * @param violations
     */
    public void setViolations(BusinessRuleVO[] violations) {
        this.violations = violations;
    }
    
    /**
     * Get Violations.
     * 
     * @return violations
     */
    public BusinessRuleVO[] getViolations() {
        return violations;
    }
    
    /**
     * Setter for hasZeroTotalAmount
     * @param hasZeroTotalAmount
     */
    public void setHasZeroTotalAmount(boolean hasZeroTotalAmount) {
        this.hasZeroTotalAmount = hasZeroTotalAmount;
    }

    /**
     *Getter for hasZeroTotalAmount
     * @return hasZeroTotalAmount
     */
    public boolean isHasZeroTotalAmount() {
        return hasZeroTotalAmount;
    }    
}
