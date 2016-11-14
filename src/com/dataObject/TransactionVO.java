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

import com.HomeBudget.dataObject.PurchaseHistoryVO;

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
    

    
    /** Service value object */
    private ServiceVO service;

    
    /** Auto-Certify flag */
    private boolean autoCertify = true;
    private CategoryVO categoryVO;
    private PurchaseVO purchaseVO;
    private PurchaseHistoryVO purchaseHistoryVO;
   

	private LocationVO locationVO;
    private MonthlyBudgetVO monthlyBudgetVO;
    private UserVO userVO;
    
    
    public UserVO getUserVO() {
	return userVO;
}

public void setUserVO(UserVO userVO) {
	this.userVO = userVO;
}

	public MonthlyBudgetVO getMonthlyBudgetVO() {
	return monthlyBudgetVO;
}

public void setMonthlyBudgetVO(MonthlyBudgetVO monthlyBudgetVO) {
	this.monthlyBudgetVO = monthlyBudgetVO;
}

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
	 public PurchaseHistoryVO getPurchaseHistoryVO() {
			return purchaseHistoryVO;
		}

		public void setPurchaseHistoryVO(PurchaseHistoryVO purchaseHistoryVO) {
			this.purchaseHistoryVO = purchaseHistoryVO;
		}
	

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
    
}
