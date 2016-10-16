/*
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  07/02/2008  - File created.
 * 
 * 1.10  Alaa Salem         15/11/2009  - Adding checkServiceParentModule()
 * 
 * 1.11  Alaa Salem         18/01/2010  - Adding find() Method.
 * 
 *                          20/01/2010  - Adding getByCode() Method.
 *                                      - Adding add() Method.
 *                                      - Adding update() Method.
 *                                      - Adding addLog() Method.
 *                                      - Adding isRecordsExist() Method.
 * 
 * 1.12  Bashar Alnemrawi   21/01/2014  - Adding getByTransactionId() Method.
 */

package com.dataObject;



import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.NamedQuery;

/**
 * Transaction services data access object implementation class.
 *
 * @author Eng. Ayman Atiyeh
 * @version 1.00
 */
public class ServiceDAOJdbcImpl extends JdbcDataAccessObject implements ServiceDAO {
    /*
     * Contants and class variables
     */

    /** Get visible systems services. */
    private static final String GET_SYS_SERVICES
    = " SELECT CODE,"
    +        " SYS_CODE,"
    +        " DESCRIPTION_A,"
    +        " DESCRIPTION"
    + " FROM TF_STP_SERVICES"
    + " WHERE SYS_CODE IN(:sysCodes)"
    +   " AND IS_VISIBILE = 2"
    + " ORDER BY CODE";

    /** Get visible services. */
    private static final String GET_SERVICES_BY_CODE
    = " SELECT CODE,"
    +        " SYS_CODE,"
    +        " DESCRIPTION_A,"
    +        " DESCRIPTION"
    + " FROM TF_STP_SERVICES"
    + " WHERE CODE IN(:svcCodes)"
    +   " AND IS_VISIBILE = 2"
    + " ORDER BY CODE";

    /** Get allowed services for this user query. */
    private static final String GET_USER_SERVICES =
          " SELECT DISTINCT"
        +        " GSV.SVC_CODE AS SVC_CODE,"
        +        " SVC.DESCRIPTION_A AS DESC_A,"
        +        " SVC.DESCRIPTION AS DESC_E"
        + " FROM TF_STP_GROUP_SERVICES GSV,"
        +      " TF_STP_SERVICES SVC"
        + " WHERE GSV.SVC_CODE = SVC.CODE"
        +   " AND SVC.IS_VISIBILE = 2"
        +   " AND SVC.SYS_CODE = ?"
        +   " AND GRP_GRP_ID IN (SELECT GRP.ID"
        +                      " FROM SF_INF_USER_GROUPS UGP,"
        +                           " SF_INF_USERS USR,"
        +                           " SF_INF_GROUPS GRP"
        +                      " WHERE UGP.USR_ID = USR.ID"
        +                        " AND UGP.GRP_ID = GRP.ID"
        +                        " AND GRP.STATUS = 2"
        +                        " AND USR.ID = ?" // USER_ID
        +                       ")"
        + " ORDER BY GSV.SVC_CODE ASC";
    
    /** Test Parent Module Code Query */    
    private static final String CHECK_PARENT_MODULE_QUERY
        =   " SELECT 1"
        +   " FROM   TF_STP_SERVICES SVC"
        +   " WHERE  SVC.CODE = ?"
        +   "    AND SVC.SYS_CODE = ?";

    /** Get main services for this user query. */
    private static final String GET_USER_MAIN_SERVICES =
          " SELECT DISTINCT"
        +        " SVC.CODE AS SVC_CODE,"
        +        " SVC.DESCRIPTION_A AS DESC_A,"
        +        " SVC.DESCRIPTION AS DESC_E"
        + " FROM TF_STP_SERVICES SVC"
        + " WHERE SVC.IS_VISIBILE = 2"
        +   " AND SVC.SYS_CODE = ?"
        + " ORDER BY SVC.CODE ASC";
    /*
     * Methods
     */
     
    /**
     * Get allowed services for this user.
     * 
     * @param userId User ID.
     * @param systemCode System code.
     * @return allowed services for this user.
     */
    public ServiceVO[] getAllowedUserServices(Long userId, String systemCode) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            getLogger().info(GET_USER_SERVICES);

            stmt = getConnection().prepareStatement(GET_USER_SERVICES);
            setString(stmt,1, systemCode);
            stmt.setLong(2, userId.longValue());
            rs = stmt.executeQuery();

            List list = new ArrayList();
            while (rs.next()) {
                ServiceVO vo = new ServiceVO();
//                vo.setSystemCode(systemCode);
//                vo.setCode(getInteger(rs, "SVC_CODE"));
//                vo.setDescriptionAr(getString(rs,"DESC_A"));
//                vo.setDescriptionEn(getString(rs,"DESC_E"));
                
                list.add(vo);
            }

            return (ServiceVO[]) list.toArray(new ServiceVO[list.size()]);

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(rs);
            close(stmt);
        }
    }

    /**
     * Get visible systems services.
     * 
     * @param systemCodes Array of related system codes.
     * @return visible systems services info.
     */
    public List getSystemsServices(String[] systemCodes) {
        // Set named parameter values
        String query = setNamedParameter(GET_SYS_SERVICES, ":sysCodes", systemCodes);
        debugQuery(query, Arrays.asList(systemCodes));

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = getConnection().prepareStatement(query);
            rs = stmt.executeQuery();

            List list = new ArrayList();
            while (rs.next()) {
                ServiceVO vo = new ServiceVO();
//                vo.setSystemCode(getString(rs, "SYS_CODE"));
//                vo.setCode(getInteger(rs, "CODE"));
//                vo.setDescriptionAr(getString(rs,"DESCRIPTION_A"));
//                vo.setDescriptionEn(getString(rs,"DESCRIPTION"));
                
                list.add(vo);
            }

            return list;

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(rs);
            close(stmt);
        }
    }

    /**
     * Get visible systems services.
     * 
     * @param svcCodes Array of related services codes.
     * @return visible systems services info.
     */
    public List getServicesByCode(Integer[] svcCodes) {
        // Set named parameter values
        String query = setNamedParameter(GET_SERVICES_BY_CODE, ":svcCodes", svcCodes);
        debugQuery(query, Arrays.asList(svcCodes));

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = getConnection().prepareStatement(query);
            rs = stmt.executeQuery();

            List list = new ArrayList();
            while (rs.next()) {
                ServiceVO vo = new ServiceVO();
//                vo.setSystemCode(getString(rs, "SYS_CODE"));
//                vo.setCode(getInteger(rs, "CODE"));
//                vo.setDescriptionAr(getString(rs,"DESCRIPTION_A"));
//                vo.setDescriptionEn(getString(rs,"DESCRIPTION"));
                
                list.add(vo);
            }

            return list;

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(rs);
            close(stmt);
        }
    }

    /** Check if role Service Code is already exists.
     * 
     * @param svcCode service code
     * @param sysCode system code
     * @return true if role Service Code is already exists, otherwise false.
     */
    public boolean checkServiceParentModule(Integer svcCode, String sysCode) {
        List params = new ArrayList();
        params.add(svcCode);
        params.add(sysCode);

        // Debug query
        debugQuery(CHECK_PARENT_MODULE_QUERY, params);

        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            stm = getConnection().prepareStatement(CHECK_PARENT_MODULE_QUERY);
            setQueryParameters(stm, params);
            rs = stm.executeQuery();

            return rs.next();

        } catch(Exception ex) {
            throw new DataAccessException(ex);
        } finally{
            close(rs);
            close(stm);
        }
    }

    /**
     * Find System Services.
     * 
     * @param pageNo Pagination Page Number.
     * @param paramVO Service VO.
     * @return Search Page Value Object.
     */
    public SearchPageVO find(int pageNo, ServiceVO paramVO) {
    
//        NamedQuery query = getNamedQuery("ae.rta.trs.dao.jdbc.Service.find");
//        
//        if (!isBlankOrNull(paramVO.getDescriptionAr())) {
//            query.appendWhereClause(" AND SVC.DESCRIPTION_A LIKE :serviceNameAr ",
//                                    "serviceNameAr",
//                                    "%" + paramVO.getDescriptionAr().trim() + "%");
//        }
//        
//        if (!isBlankOrNull(paramVO.getDescriptionEn())) {
//            query.appendWhereClause(" AND SVC.DESCRIPTION LIKE :serviceNameEn ",
//                                    "serviceNameEn",
//                                    "%" + paramVO.getDescriptionEn().trim() + "%");
//        }
//        
//        if (paramVO.getCode() != null) {
//            query.appendWhereClause(" AND SVC.CODE = :serviceCode ",
//                                    "serviceCode",
//                                    paramVO.getCode());
//        }
//        
//        if (paramVO.getSystem() != null &&
//            !isBlankOrNull(paramVO.getSystem().getCode())) {
//            query.appendWhereClause(" AND SVC.SYS_CODE = :systemCode ",
//                                    "systemCode",
//                                    paramVO.getSystem().getCode());
//        }
//        
//        if (paramVO.getMainService() != null) {
//            query.appendWhereClause(" AND SVC.IS_VISIBILE = :isMainService ",
//                                    "isMainService",
//                                    paramVO.getMainService());
//        }
//        
//        
//        PreparedStatement prepStmt = null;
//        ResultSet resultSet = null;          
//        try {
//            prepStmt = doSearch(query, pageNo);
//            resultSet = prepStmt.executeQuery();
//            
//            SearchPageVO searchPage = getSearchPage(query, pageNo);
//
//            while(resultSet.next()) {
//                
//                ServiceVO vo = new ServiceVO();
//                vo.setSystem(new SystemTypeVO());
//                
//                vo.setId(getLong(resultSet, "SERVICE_CODE"));
//                vo.setCode(getInteger(resultSet, "SERVICE_CODE"));
//                vo.setMainService(getInteger(resultSet, "IS_MAIN_SERVICE"));
//                vo.getSystem().setCode(getString(resultSet, "SYSTEM_CODE"));
//                vo.setDescriptionAr(getString(resultSet, "SERVICE_DESC_A"));
//                vo.setDescriptionEn(getString(resultSet, "SERVICE_DESC_E"));
//                vo.getSystem().setDescriptionAr(getString(resultSet, "SYSTEM_DESC_A"));
//                vo.setMainServiceDesc(getString(resultSet, "IS_MAIN_SERVICE_DESC"));
//                
//                searchPage.addRecord(vo);
//            }
//            return searchPage;
//
//        } catch(Exception ex) {
//            throw new DataAccessException(ex);
//        } finally {
//            close(resultSet);
//            close(prepStmt);
//        } 
    	return null;
    }

    /**
     * Get Service By Code.
     * 
     * @param serviceCode Service Code.
     * @return Service VO.
     */
    public ServiceVO getByCode(Integer serviceCode) {
    
//        NamedQuery query = getNamedQuery("ae.rta.trs.dao.jdbc.Service.getByCode");
//        
//        query.setParameter("serviceCode", serviceCode);
//        
//        PreparedStatement prepStmt = null;
//        ResultSet resultSet = null;          
//        try {
//            prepStmt = prepareStatement(query);
//            resultSet = prepStmt.executeQuery();
//            
//            ServiceVO vo = null;
//            
//            if(resultSet.next()) {
//                
//                vo = new ServiceVO();                
//                vo.setSystem(new SystemTypeVO());
//                
//                vo.setId(getLong(resultSet, "SERVICE_CODE"));
//                vo.setCode(getInteger(resultSet, "SERVICE_CODE"));
//                vo.setDescriptionAr(getString(resultSet, "SERVICE_DESC_A"));
//                vo.setDescriptionEn(getString(resultSet, "SERVICE_DESC_E"));
//                vo.setServiceCategory(getInteger(resultSet, "SERVICE_CATEGORY"));
//                vo.getSystem().setCode(getString(resultSet, "SYSTEM_CODE"));
//                vo.setTimeToCompleteInMinutes(getInteger(resultSet, "TIME_TO_COMPLETE"));
//                vo.setServiceDetailsAr(getString(resultSet, "SERVICE_DETAILS_A"));
//                vo.setServiceDetailsEn(getString(resultSet, "SERVICE_DETAILS_E"));
//                vo.setMainService(getInteger(resultSet, "IS_MAIN_SERVICE"));
//                vo.setAutoService(getInteger(resultSet, "IS_AUTO_SERVICE"));
//                vo.setHasEForm(getInteger(resultSet, "HAS_E_FORM"));
//                vo.setRequiresManualAuditing(getInteger(resultSet, "NEEDS_REVIEW"));
//                vo.setRequiresPersmissions(getInteger(resultSet, "REQUIRE_PERMISSIONS"));
//                vo.setHasFileCopy(getInteger(resultSet, "HAS_FILE_COPY"));
//                vo.setViewedInCatalog(getInteger(resultSet, "VIEWED_IN_CATALOGUE"));
//                vo.setNeedsPassportInfo(getInteger(resultSet, "NEEDS_PASSPORT_INFO"));
//                vo.setSddiMainService(getInteger(resultSet, "SDDI_MAIN_SERVICE"));
//                vo.setViewLocation(getLong(resultSet, "SDDI_VIEW_LOCATION"));
//                vo.setHasECertificate(getChoice(resultSet, "IS_ECERTIFICATE"));
//                vo.setNeedToCollectFee(getChoice(resultSet, "COLLECT_OPEN_FILE_FEE"));
//                vo.setValidityDays(getLong(resultSet, "ECR_VALIDITY_DAYS"));
//                vo.setRetentionDays(getLong(resultSet, "ECR_RETENTION_DAYS"));
//                vo.setIsMostUsed(getChoice(resultSet, "MOST_USED"));               
//                vo.setIsLockEntities(getChoice(resultSet, "LOCK_ENTITIES"));
//                vo.setNeedReceipt(getInteger(resultSet,"NEED_RECEIPT"));
//                vo.setReserveEgovPayment(getChoice(resultSet,"RESERVE_E_GOV_PAYMENT"));
//                vo.setNeedsReview(getChoice(resultSet,"NEEDS_REVIEW"));
//                vo.setDeliveryByMail(getChoice(resultSet,"IS_MAIL_DELIVERY"));
//                vo.setDeliveryByKiosk(getChoice(resultSet,"IS_KIOSK_DELIVERY"));
//                vo.setVisible(getInteger(resultSet, "IS_VISIBILE"));
//                vo.setIsCourierAndCollection(getChoice(resultSet,"IS_COURIER_AND_COLLECTION"));
//                vo.getSystem().setDescriptionAr(getString(resultSet,"DESCRIPTION_A"));
//                vo.getSystem().setCode(getString(resultSet,"SYS_CODE"));
//                vo.getSystem().setRelatedExceptionEpsStepSeq(getInteger(resultSet,"RELATED_EXCEPTION_EPS_STEP_SEQ"));
//                vo.getSystem().setAllowedToCheckServiceByCenter(getChoice(resultSet, "ALLOWED_TO_CHECK_SVC_BY_CTR"));
//                vo.setRequiresPlateManufacture(getChoice(resultSet,"REQUIRES_PLATE_MANUFACTURE"));
//                vo.setCMLApplicationAvailable(getInteger(resultSet,"IS_CML_APPLICATION_AVAILABLE"));
//                vo.setDirectPILink(getString(resultSet,"PI_DIRECT_LINK"));
//                vo.setNotifyClientAfterCompletionOfApprovals(getChoice(resultSet,"NOTIFY_COMPLETE_APPROVALS"));
//                vo.setEidBufferDays(getInteger(resultSet, "FTF_EID_BUFFER"));
//                vo.setSalesTransactionType(getInteger(resultSet, "FORCE_SALE_TRANSACTION_TYPE"));
//            }
//            return vo;
//
//        } catch(Exception ex) {
//            throw new DataAccessException(ex);
//        } finally {
//            close(resultSet);
//            close(prepStmt);
//        } 
    	return null;
    }

    /**
     * Add New Service.
     * 
     * @param Service VO.
     * @return Service VO.
     */
    public ServiceVO add(ServiceVO vo) {

       // NamedQuery query = getNamedQuery("ae.rta.trs.dao.jdbc.Service.add");
//
//        query.setParameter("code", vo.getCode());
//        query.setParameter("systemCode", vo.getSystem().getCode());
//        query.setParameter("serviceDescA", vo.getDescriptionAr());
//        query.setParameter("serviceDescE", vo.getDescriptionEn());
//        query.setParameter("mainService", vo.getMainService());
//        query.setParameter("createdBy", vo.getCreatedBy());
//        query.setParameter("requirePermessions", vo.getRequiresPersmissions());
//        query.setParameter("serviceCategory", vo.getServiceCategory());
//        query.setParameter("hasFileCopy", vo.getHasFileCopy());
//        query.setParameter("autoService", vo.getAutoService());
//        query.setParameter("hasEForm", vo.getHasEForm());
//        query.setParameter("requireManualAuditing", vo.getRequiresManualAuditing());
//        query.setParameter("timeToComplete", vo.getTimeToCompleteInMinutes());
//        query.setParameter("serviceDetailsE", vo.getServiceDetailsEn());
//        query.setParameter("serviceDetailsA", vo.getServiceDetailsAr());
//        query.setParameter("viewedInCatalog", vo.getViewedInCatalog());
//        query.setParameter("needsPassportInfo", vo.getNeedsPassportInfo());
//        query.setParameter("sddiMainService", vo.getSddiMainService());
//        query.setParameter("viewLocation", vo.getViewLocation());        
//        query.setParameter("validityDays", vo.getValidityDays());        
//        query.setParameter("retentionDays", vo.getRetentionDays());
//        
//        query.setParameter("isMostUsed", vo.getIsMostUsed() == null ? 
//                                         vo.FALSE : vo.getIsMostUsed().getInteger());
//        query.setParameter("hasECertificate", vo.getHasECertificate() == null ? 
//                                         vo.FALSE : vo.getHasECertificate().getInteger());
//        query.setParameter("needToCollectFee", vo.getNeedToCollectFee() == null ? 
//                                         vo.FALSE : vo.getNeedToCollectFee().getInteger());
//                                         
//        query.setParameter("mailDelivery", vo.getDeliveryByMail() == null ? 
//                                           vo.FALSE : vo.getDeliveryByMail().getInteger());    
//        
//        query.setParameter("kioskDelivery", vo.getDeliveryByKiosk() == null ? 
//                                           vo.FALSE : vo.getDeliveryByKiosk().getInteger()); 
//        if (vo.getRequiresPlateManufacture() != null) {
//            query.setParameter("requiresPlateManufacture", vo.getRequiresPlateManufacture().getInteger());
//        } else {
//            query.setParameter("requiresPlateManufacture", null);
//        }
//        
//        if (vo.getIsCourierAndCollection() != null) {
//            query.setParameter("isCourierAndCollection", vo.getIsCourierAndCollection().getInteger());
//        } else {
//            query.setParameter("isCourierAndCollection", null);
//        }
//        query.setParameter("directPILink", vo.getDirectPILink());
//        
//        if (vo.getNotifyClientAfterCompletionOfApprovals() != null) {
//            query.setParameter("notifyOnceCompleteApprovals", vo.getNotifyClientAfterCompletionOfApprovals().getInteger());
//        }
//        query.setParameter("eidBufferDays", vo.getEidBufferDays());
//        
//        query.setParameter("salesTransactionType", vo.getSalesTransactionType());
//
//        PreparedStatement prepStmt = null;
//        try {
//            prepStmt = prepareStatement(query);            
//            int count = prepStmt.executeUpdate();
//            
//            if (count != 1) {
//                throw new DataAccessException(new StringBuffer(
//                    "Error while adding new service: serviceCode=")
//                        .append(vo.getCode()).append(", affectedRecords=")
//                           .append(count).toString());
//            }
//
//            // Save new ID (Service Code).
//            vo.setId(new Long(vo.getCode().longValue()));
//                        
//            return vo;
//                             
//        } catch(Exception ex) {
//            throw new DataAccessException(ex);
//        } finally {
//            close(prepStmt);
//        } 
        return null;
    }

    /**
     * Update Existing Service.
     * 
     * @param Service VO.
     * @return Service VO.
     */
    public void update(ServiceVO vo) {
//
//        NamedQuery query = getNamedQuery("ae.rta.trs.dao.jdbc.Service.update");
//
//        query.setParameter("id", vo.getId());
//
//        query.setParameter("code", vo.getCode());
//        query.setParameter("systemCode", vo.getSystem().getCode());
//        query.setParameter("serviceDescA", vo.getDescriptionAr());
//        query.setParameter("serviceDescE", vo.getDescriptionEn());
//        query.setParameter("mainService", vo.getMainService());
//        query.setParameter("updatedBy", vo.getUpdatedBy());
//        query.setParameter("requirePermessions", vo.getRequiresPersmissions());
//        query.setParameter("serviceCategory", vo.getServiceCategory());
//        query.setParameter("hasFileCopy", vo.getHasFileCopy());
//        query.setParameter("autoService", vo.getAutoService());
//        query.setParameter("hasEForm", vo.getHasEForm());
//        query.setParameter("requireManualAuditing", vo.getRequiresManualAuditing());
//        query.setParameter("timeToComplete", vo.getTimeToCompleteInMinutes());
//        query.setParameter("serviceDetailsE", vo.getServiceDetailsEn());
//        query.setParameter("serviceDetailsA", vo.getServiceDetailsAr());
//        query.setParameter("viewedInCatalog", vo.getViewedInCatalog());
//        query.setParameter("needsPassportInfo", vo.getNeedsPassportInfo());
//        query.setParameter("sddiMainService", vo.getSddiMainService());
//        query.setParameter("viewLocation", vo.getViewLocation());
//        query.setParameter("validityDays", vo.getValidityDays());
//        query.setParameter("retentionDays", vo.getRetentionDays());
//        
//        query.setParameter("isMostUsed", vo.getIsMostUsed() == null ? 
//                                         vo.FALSE : vo.getIsMostUsed().getInteger());
//        query.setParameter("hasECertificate", vo.getHasECertificate() == null ? 
//                                         vo.FALSE : vo.getHasECertificate().getInteger());
//        query.setParameter("needToCollectFee", vo.getNeedToCollectFee() == null ? 
//                                         vo.FALSE : vo.getNeedToCollectFee().getInteger());
//                                         
//        query.setParameter("mailDelivery", vo.getDeliveryByMail() == null ? 
//                                           vo.FALSE : vo.getDeliveryByMail().getInteger());  
//        
//        query.setParameter("kioskDelivery", vo.getDeliveryByKiosk() == null ? 
//                                           vo.FALSE : vo.getDeliveryByKiosk().getInteger());
//        if (vo.getRequiresPlateManufacture() != null) {
//            query.setParameter("requiresPlateManufacture", vo.getRequiresPlateManufacture().getInteger());
//        } else {
//            query.setParameter("requiresPlateManufacture", null);
//        }
//        
//        if (vo.getIsCourierAndCollection() != null) {
//            query.setParameter("isCourierAndCollection", vo.getIsCourierAndCollection().getInteger());
//        } else {
//            query.setParameter("isCourierAndCollection", null);
//        }
//        
//        query.setParameter("directPILink", vo.getDirectPILink());
//        
//        query.setParameter("notifyOnceCompleteApprovals", vo.getNotifyClientAfterCompletionOfApprovals() == null ? 
//                                              vo.FALSE :  vo.getNotifyClientAfterCompletionOfApprovals().getInteger());
//        
//        query.setParameter("eidBufferDays", vo.getEidBufferDays());
//        
//        query.setParameter("salesTransactionType", vo.getSalesTransactionType());
//
//        PreparedStatement prepStmt = null;
//        try {
//            prepStmt = prepareStatement(query);            
//            int count = prepStmt.executeUpdate();
//            
//            if (count != 1) {
//                throw new DataAccessException(new StringBuffer(
//                    "Error while update existing service: serviceCode=")
//                        .append(vo.getCode()).append(", affectedRecords=")
//                           .append(count).toString());
//            }
//
//            // Save new ID (Service Code).
//            vo.setId(new Long(vo.getCode().longValue()));
//            
//            return vo;
//                             
//        } catch(Exception ex) {
//            throw new DataAccessException(ex);
//        } finally {
//            close(prepStmt);
//        }     
    	
    }

    /**
     * Add New Service Log.
     * 
     * @param Service VO.
     */
    public void addLog(ServiceLogVO vo) {

//        NamedQuery query = getNamedQuery("ae.rta.trs.dao.jdbc.Service.addLog");
//
//        query.setParameter("actionType", vo.getActionType());
//        query.setParameter("employeeName", vo.getEmployeeName());
//        query.setParameter("centerName", vo.getCenterName());
//        query.setParameter("createdBy", vo.getCreatedBy());
//
//        query.setParameter("code", vo.getCode());
//        query.setParameter("systemCode", (vo.getSystem() != null) ?
//                                                    vo.getSystem().getCode() :
//                                                    null);
//        query.setParameter("serviceDescA", vo.getDescriptionAr());
//        query.setParameter("serviceDescE", vo.getDescriptionEn());
//        query.setParameter("mainService", vo.getMainService());
//        query.setParameter("requirePermessions", vo.getRequiresPersmissions());
//        query.setParameter("serviceCategory", vo.getServiceCategory());
//        query.setParameter("hasFileCopy", vo.getHasFileCopy());
//        query.setParameter("autoService", vo.getAutoService());
//        query.setParameter("hasEForm", vo.getHasEForm());
//        query.setParameter("requireManualAuditing", vo.getRequiresManualAuditing());
//        query.setParameter("timeToComplete", vo.getTimeToCompleteInMinutes());
//        query.setParameter("serviceDetailsE", vo.getServiceDetailsEn());
//        query.setParameter("serviceDetailsA", vo.getServiceDetailsAr());
//
//        query.setParameter("viewedInCatalog", vo.getViewedInCatalog());
//        query.setParameter("needsPassportInfo", vo.getNeedsPassportInfo());
//        query.setParameter("serviceChannelId", vo.getServiceChannelId());
//        query.setParameter("sddiMainService", vo.getSddiMainService());
//        query.setParameter("viewLocation", vo.getViewLocation());
//        
//        query.setParameter("brKey", vo.getBrKey());
//        query.setParameter("eidBufferDays", vo.getEidBufferDays());
//        
//        PreparedStatement prepStmt = null;
//        try {
//            prepStmt = prepareStatement(query);            
//            int count = prepStmt.executeUpdate();
//            
//            if (count != 1) {
//                throw new DataAccessException(new StringBuffer(
//                    "Error while adding new service log: serviceCode=")
//                        .append(vo.getCode())
//                        .append(", affectedRecords=")
//                        .append(count).toString());
//            }
//
//        } catch(Exception ex) {
//            throw new DataAccessException(ex);
//        } finally {
//            close(prepStmt);
//        }     
    }
    
    /**
     * Test If Service Code Is Already Assigned In One Of The Records.
     * 
     * @param serviceCode Value To Be Tested.
     * @param execludedServiceCode If Not Null, The Service Code Will Not Be
     *                             Considered In The Equation.
     * @return true If Service Code Is Already Assigned In One Of The Records.
     */
    public boolean isServiceCodeAssignedBefore(Integer serviceCode,
                                                Integer execludedServiceCode) {

//        NamedQuery query = getNamedQuery("ae.rta.trs.dao.jdbc.Service.isRecordsExist");
//
//        if(execludedServiceCode != null) {
//            query.appendWhereClause(" AND CODE <> :execludedServiceCode ",
//                                    "execludedServiceCode",
//                                    execludedServiceCode);
//        }
//
//        query.appendWhereClause(" AND CODE = :serviceCode ",
//                                "serviceCode",
//                                serviceCode);
//        
//        return isRecordsExist(query);
    	return true;
    }

    /**
     * Test If Service Arabic Description Is Already Assigned In One Of The Records.
     * 
     * @param serviceDescAr Value To Be Tested.
     * @param execludedServiceCode If Not Null, The Service Code Will Not Be
     *                             Considered In The Equation.
     * @return true If Service Arabic Description Is Already Assigned In One Of
     *              The Records.
     */
    public boolean isServiceArabicDescAssignedBefore(String serviceDescAr,
                                                     Integer execludedServiceCode) {
                                                     
//        NamedQuery query = getNamedQuery("ae.rta.trs.dao.jdbc.Service.isRecordsExist");
//
//        if(execludedServiceCode != null) {
//            query.appendWhereClause(" AND CODE <> :execludedServiceCode ",
//                                    "execludedServiceCode",
//                                    execludedServiceCode);
//        }
//
//        query.appendWhereClause(" AND DESCRIPTION_A = :serviceDescAr ",
//                                "serviceDescAr",
//                                serviceDescAr);
//        
//        return isRecordsExist(query);
    	return true;
    }
                                              
    /**
     * Test If Service English Description Is Already Assigned In One Of The Records.
     * 
     * @param serviceDescEn Value To Be Tested.
     * @param execludedServiceCode If Not Null, The Service Code Will Not Be
     *                             Considered In The Equation.
     * @return true If Service English Description Is Already Assigned In One Of
     *              The Records.
     */
    public boolean isServiceEnglishDescAssignedBefore(String serviceDescEn,
                                                      Integer execludedServiceCode) {
                                                     
//        NamedQuery query = getNamedQuery("ae.rta.trs.dao.jdbc.Service.isRecordsExist");
//
//        if(execludedServiceCode != null) {
//            query.appendWhereClause(" AND CODE <> :execludedServiceCode ",
//                                    "execludedServiceCode",
//                                    execludedServiceCode);
//        }
//
//        query.appendWhereClause(" AND DESCRIPTION = :serviceDescEn ",
//                                "serviceDescEn",
//                                serviceDescEn);
//        
//        return isRecordsExist(query);
    	return true;
    }
                                              
    /**
     * Test If There At Least One Record Returned From Given Query.
     * 
     * @param query Named Query To Be Executed.
     * @return true If There At Least One Record Returned From Given Query.
     */
    private boolean isRecordsExist(NamedQuery query) {

//        PreparedStatement prepStmt = null;
//        ResultSet resultSet = null;
//        try {
//            prepStmt = prepareStatement(query);            
//            resultSet = prepStmt.executeQuery();
//            
//            return resultSet.next();
//            
//        } catch(Exception ex) {
//            throw new DataAccessException(ex);
//        } finally {
//            close(resultSet);
//            close(prepStmt);
//        }    
    	return true;
    }


//    public List<ae.rta.trs.vo.ServiceVO> getAllServices(String serviceCode) {
//        NamedQuery namedQuery = getNamedQuery("ae.rta.trs.dao.jdbc.Service.getAllServicesAndSystemDetails");
//        PreparedStatement prepStmt = null;
//        ResultSet resultSet = null; 
//        List<ae.rta.trs.vo.ServiceVO> serviceList = new ArrayList<ae.rta.trs.vo.ServiceVO>();
//        if (!isBlankOrNull(serviceCode)) {
//            namedQuery.appendWhereClause(" AND SVC.CODE = :code");
//            namedQuery.setParameter("code", serviceCode);
//        }
//
//        try {
//            prepStmt = prepareStatement(namedQuery);
//            resultSet = prepStmt.executeQuery();
//            
//            while(resultSet.next()) {
//                ae.rta.trs.vo.ServiceVO serviceVO = new ae.rta.trs.vo.ServiceVO();
//        
//                
//                serviceVO.setCode(getInteger(resultSet, "SVC_CODE"));
//
//                serviceVO.setDescriptionAr(getString(resultSet, "SVC_DESCRIPTION_A"));
//                serviceVO.setDescriptionEn(getString(resultSet, "SVC_DESCRIPTION"));
//                serviceVO.setCreatedBy(getString(resultSet, "SVC_CREATED_BY"));
//                serviceVO.setCreationDate(getDate(resultSet, "SVC_CREATION_DATE"));
//                serviceVO.setUpdatedBy(getString(resultSet, "SVC_UPDATED_BY"));
//                serviceVO.setUpdateDate(getDate(resultSet, "SVC_UPDATE_DATE"));
//                 
//                
//                SystemTypeVO systemTypeVO =new SystemTypeVO();
//                systemTypeVO.setCode(getString(resultSet, "SYS_CODE"));
//             
//                systemTypeVO.setDescriptionAr(getString(resultSet, "SYS_DESCRIPTION_A"));
//                systemTypeVO.setDescriptionEn(getString(resultSet, "SYS_DESCRIPTION"));
//                systemTypeVO.setCreatedBy(getString(resultSet, "SYS_CREATED_BY"));
//                systemTypeVO.setCreationDate(getDate(resultSet, "SYS_CREATION_DATE"));
//                systemTypeVO.setUpdatedBy(getString(resultSet, "SYS_UPDATED_BY"));
//                systemTypeVO.setUpdateDate(getDate(resultSet, "SYS_UPDATE_DATE"));
//                serviceVO.setSystem(systemTypeVO);
//                
//                
//                serviceList.add(serviceVO);
//            }
//            return serviceList;
//           
//        } catch(Exception ex){
//            throw new DataAccessException(ex);
//        } finally  {
//            close(resultSet);
//            close(prepStmt);
//        }
//    }
    
//    public List<ae.rta.trs.vo.ServiceVO> getAllServices() {
//        NamedQuery namedQuery = getNamedQuery("ae.rta.trs.dao.jdbc.Service.getAllServices");
//        PreparedStatement prepStmt = null;
//        ResultSet resultSet = null; 
//        List<ae.rta.trs.vo.ServiceVO> serviceList = new ArrayList<ae.rta.trs.vo.ServiceVO>();
//        try {
//            prepStmt = prepareStatement(namedQuery);
//            resultSet = prepStmt.executeQuery();
//            
//            while(resultSet.next()) {
//                ae.rta.trs.vo.ServiceVO serviceVO = new ae.rta.trs.vo.ServiceVO();
//        
//                
//                serviceVO.setCode(getInteger(resultSet, "SVC_CODE"));
//
//                serviceVO.setDescriptionAr(getString(resultSet, "SVC_DESCRIPTION_A"));
//                serviceVO.setDescriptionEn(getString(resultSet, "SVC_DESCRIPTION"));
//                serviceVO.setCreatedBy(getString(resultSet, "SVC_CREATED_BY"));
//                serviceVO.setCreationDate(getDate(resultSet, "SVC_CREATION_DATE"));
//                serviceVO.setUpdatedBy(getString(resultSet, "SVC_UPDATED_BY"));
//                serviceVO.setUpdateDate(getDate(resultSet, "SVC_UPDATE_DATE"));
//                 
//                
//                SystemTypeVO systemTypeVO =new SystemTypeVO();
//                systemTypeVO.setCode(getString(resultSet, "SYS_CODE"));
//             
//                systemTypeVO.setDescriptionAr(getString(resultSet, "SYS_DESCRIPTION_A"));
//                systemTypeVO.setDescriptionEn(getString(resultSet, "SYS_DESCRIPTION"));
//                systemTypeVO.setCreatedBy(getString(resultSet, "SYS_CREATED_BY"));
//                systemTypeVO.setCreationDate(getDate(resultSet, "SYS_CREATION_DATE"));
//                systemTypeVO.setUpdatedBy(getString(resultSet, "SYS_UPDATED_BY"));
//                systemTypeVO.setUpdateDate(getDate(resultSet, "SYS_UPDATE_DATE"));
//                serviceVO.setSystem(systemTypeVO);
//                
//                
//                serviceList.add(serviceVO);
//            }
//            return serviceList;
//           
//        } catch(Exception ex){
//            throw new DataAccessException(ex);
//        } finally  {
//            close(resultSet);
//            close(prepStmt);
//        }
//    }
//


    /**
     * get Services List
     * 
     * @return Services Option List
     */
    public List getServicesList(){
        
//      NamedQuery query = getNamedQuery("ae.rta.trs.dao.jdbc.Service.getServicesList");
//      PreparedStatement prepStmt = null;
//      ResultSet resultSet = null;
//      
//      ArrayList list=null;
//      try {
//          prepStmt = prepareStatement(query);            
//          resultSet = prepStmt.executeQuery();
//           
//          ServiceVO service=null;
//          list=new ArrayList();
//           
//          while(resultSet.next()){
//              service=new ServiceVO();
//              service.setCode(getInteger(resultSet,"CODE"));
//              service.setDescriptionAr(getString(resultSet,"DESCRIPTION_A"));
//              list.add(service);
//          } 
//          
//       } catch(Exception ex) {
//            throw new DataAccessException(ex);
//        } finally {
//            close(resultSet);
//            close(prepStmt);
//        }
//        
//        return list;
    	return null;
    }
    
    /**
     * Find Exempted Services
     * 
     * @param ServiceVO : Service VO
     * @param pageNo : Page No
     * @return get exempted services list
     */
    public SearchPageVO findExemptedServices(ServiceVO serviceVO ,int pageNo) {
//        NamedQuery query = getNamedQuery("ae.rta.trs.dao.jdbc.Service.findExemptedServices");
//        PreparedStatement prepStmt = null;
//        ResultSet resultSet = null;
//        try {
//        
//            if(!isBlankOrNull(serviceVO.getDescriptionAr())) {
//                query.appendWhereClause(" AND DESCRIPTION_A LIKE "+"'%"+serviceVO.getDescriptionAr()+"%'");
//            }
//            
//            if(serviceVO.getCode() != null) {
//                query.appendWhereClause(" AND CODE = " + serviceVO.getCode());
//            }
//            
//            if (serviceVO.isCmlOnlineServices()) {
//                TrafficConfig config = ServiceLocator.getInstance().getConfig();
//                String SDDIProductionServices = (String)config.get("cml.SDDIProductionServices");
//                if (!isBlankOrNull(SDDIProductionServices)) {
//                    query.appendWhereClause(" AND SVC.CODE IN  ( " + SDDIProductionServices + ")" );      
//                }
//                
//                query.appendWhereClause(" AND SYS_CODE IN (08) " );    
//            }
//            
//            if(!isBlankOrNull(serviceVO.getSelectedCodes())) {
//                query.appendWhereClause(" AND CODE NOT IN ( " + serviceVO.getSelectedCodes() + ")");
//            }
//            
//            if(!isBlankOrNull(serviceVO.getSystemCode())) {
//                query.appendWhereClause(" AND SYS_CODE = " + serviceVO.getSystemCode());
//            }
//            
//            query.appendWhereClause(" ORDER BY DESCRIPTION_A ");
//            
//            SearchPageVO searchPage = getSearchPage(query, pageNo);
//            prepStmt = doSearch(query, pageNo);
//            resultSet = prepStmt.executeQuery();
//                        
//            while(resultSet.next()) {
//                ServiceVO service = new ServiceVO();
//                service.setCode(getInteger(resultSet,"CODE"));
//                service.setDescriptionAr(getString(resultSet,"DESCRIPTION_A")); 
//                service.setSystemCode(getString(resultSet,"SYS_CODE"));
//                service.setCMLApplicationAvailable(getInteger(resultSet,"IS_CML_APPLICATION_AVAILABLE"));
//                searchPage.addRecord(service);
//            }
//            return searchPage;
//        } catch(Exception ex) {
//            throw new DataAccessException(ex);
//        } finally {
//            close(resultSet);
//            close(prepStmt);
//        }
    	return null;
    }
    /**
     * Find Exempted Services
     * 
     * @return exempted services list
     */
    public List <ServiceVO> findExemptedServices() {
//        NamedQuery query = getNamedQuery("ae.rta.trs.dao.jdbc.Service.findExemptedServices");
//        PreparedStatement prepStmt = null;
//        ResultSet resultSet = null;
//        try {
//        
//            query.appendWhereClause(" ORDER BY DESCRIPTION_A ");
//            
//            prepStmt = prepareStatement(query);            
//            resultSet = prepStmt.executeQuery();
//            List <ServiceVO> list = new ArrayList<ServiceVO>();            
//            while(resultSet.next()) {
//                ServiceVO service = new ServiceVO();
//                service.setCode(getInteger(resultSet,"CODE"));
//                service.setDescriptionAr(getString(resultSet,"DESCRIPTION_A")); 
//                service.setSystemCode(getString(resultSet,"SYS_CODE"));
//                service.setCMLApplicationAvailable(getInteger(resultSet,"IS_CML_APPLICATION_AVAILABLE"));
//                list.add(service);
//            }
//            return list;
//        } catch(Exception ex) {
//            throw new DataAccessException(ex);
//        } finally {
//            close(resultSet);
//            close(prepStmt);
//        }
    	return null;
    }
    
    /**
     * Is service defined by service code and system code
     * 
     * @param serviceCode Service Code
     * @param systemCode System Code
     * @return true if service defined by service code and system code
     */
    public boolean isServiceDefined(Integer serviceCode,String systemCode) {
        
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        try  {
//            NamedQuery query = getNamedQuery("ae.rta.trs.dao.jdbc.Service.isServiceDefined");
//            query.setParameter("serviceCode",serviceCode);
//            query.setParameter("systemCode",systemCode);
//            statement = prepareStatement(query);
//            resultSet = statement.executeQuery();
//            if (!resultSet.next())  {
//                return false;
//            }
//            return getInteger(resultSet,"COUNT") != null &&
//                   getInteger(resultSet,"COUNT").intValue() > 0;
//        } catch (Exception ex)  {
//            throw new DataAccessException(ex);
//        } finally  {
//            close(statement);
//            close(resultSet);
//        }
    	return true;
    }
    
    /**
     * Check if the service is related to electronic insurance or not
     * 
     * @param serviceCode Service Code
     * @return true if it is related to electronic insurance or not
     */
    public boolean isEirService(Integer serviceCode) {
        
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        try  {
//            NamedQuery query = getNamedQuery("ae.rta.trs.dao.jdbc.Service.isEirService");
//            query.setParameter("serviceCode",serviceCode);
//            
//            statement = prepareStatement(query);
//            resultSet = statement.executeQuery();
//            if (resultSet.next())  {
//                return true;
//            }
//            return false;
//        } catch (Exception ex)  {
//            throw new DataAccessException(ex);
//        } finally  {
//            close(statement);
//            close(resultSet);
//        }
    	return true;
    }
    
    /**
     * Get Service By Transaction ID.
     * 
     * @param transactionId : Transaction ID.
     * @return Service VO.
     */
    public ServiceVO getByTransactionId(Long transactionId) {
    
//        NamedQuery query = getNamedQuery("ae.rta.trs.dao.jdbc.Service.getByTransactionId");
//        
//        query.setParameter("transactionId", transactionId);
//        
//        PreparedStatement prepStmt = null;
//        ResultSet resultSet = null;          
//        try {
//            prepStmt = prepareStatement(query);
//            resultSet = prepStmt.executeQuery();
//            
//            ServiceVO serviceDTO = null;
//            
//            if(resultSet.next()) {
//                
//                serviceDTO = new ServiceVO();                
//                
//                // Fill Service Info.
//                serviceDTO.setCode(getInteger(resultSet, "CODE"));
//                serviceDTO.setDescriptionAr(getString(resultSet, "DESCRIPTION_A"));
//                serviceDTO.setDescriptionEn(getString(resultSet, "DESCRIPTION"));
//                serviceDTO.setDeliveryByKiosk(getChoice(resultSet,"IS_KIOSK_DELIVERY"));
//                serviceDTO.setCourierDeliveryEnabled(getChoice(resultSet , "IS_COURIER_DELIVERY_ENABLED"));
//                serviceDTO.setKioskAvailability(getInteger(resultSet , "KIOSK_AVAILABILITY"));
//                serviceDTO.setMailAvailability(getInteger(resultSet , "IS_MAIL_DELIVERY"));
//                serviceDTO.setIsCourierAndCollection(getChoice(resultSet , "IS_COURIER_AND_COLLECTION"));
//                serviceDTO.setEidBufferDays(getInteger(resultSet, "FTF_EID_BUFFER"));
//            }
//            return serviceDTO;
//
//        } catch(Exception ex) {
//            throw new DataAccessException(ex);
//        } finally {
//            close(resultSet);
//            close(prepStmt);
//        } 
    	return null;
    }
    
    /**
     * get Services List
     * 
     * @return Services Option List
     */
    public List getPaymentClearanceServices(){
        
//      NamedQuery query = getNamedQuery("ae.rta.trs.dao.jdbc.Service.getPaymentClearanceServices");
//      PreparedStatement prepStmt = null;
//      ResultSet resultSet = null;
//      
//      ArrayList list=null;
//      try {
//          prepStmt = prepareStatement(query);            
//          resultSet = prepStmt.executeQuery();
//           
//          ServiceVO service=null;
//          list=new ArrayList();
//           
//          while(resultSet.next()){
//              service=new ServiceVO();
//              service.setCode(getInteger(resultSet,"CODE"));
//              service.setDescriptionAr(getString(resultSet,"DESCRIPTION_A"));
//              list.add(service);
//          } 
//          
//       } catch(Exception ex) {
//            throw new DataAccessException(ex);
//        } finally {
//            close(resultSet);
//            close(prepStmt);
//        }
//        
//        return list;
    	return null;
    }
    
    /**
     * Get allowed services for this user.
     * 
     * @param userId User ID.
     * @param systemCode System code.
     * @return allowed services for this user.
     */
    public ServiceVO[] getAllowedUserMainServices(Long userId, String systemCode) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            getLogger().info(GET_USER_MAIN_SERVICES);

            stmt = getConnection().prepareStatement(GET_USER_MAIN_SERVICES);
            setString(stmt,1, systemCode);
            rs = stmt.executeQuery();

            List list = new ArrayList();
            while (rs.next()) {
                ServiceVO vo = new ServiceVO();
//                vo.setSystemCode(systemCode);
//                vo.setCode(getInteger(rs, "SVC_CODE"));
//                vo.setDescriptionAr(getString(rs,"DESC_A"));
//                vo.setDescriptionEn(getString(rs,"DESC_E"));
                
                list.add(vo);
            }

            return (ServiceVO[]) list.toArray(new ServiceVO[list.size()]);

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(rs);
            close(stmt);
        }
    }

	@Override
	public SearchPageVO find(ServiceVO searchVO, int pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceVO getById(Long serviceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(ServiceVO vo) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public List getApponitmentServices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isServiceCodeExists(Integer serviceCode, Long serviceId) {
		// TODO Auto-generated method stub
		return false;
	}
}