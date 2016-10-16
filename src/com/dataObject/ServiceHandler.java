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
 *                                      
 * 1.12  Alaa Salem         24/01/2010  - Overload addLog() Method.
 * 
 * 1.13  Alaa Salem         27/01/2010  - Modifing BR_SVC_011 Checking In add()
 *                                        & update() Methods.
 * 
 */

package  com.dataObject;



import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Transaction services handler Business object.
 *
 * @author Eng. Ayman Atiyeh
 * @version 1.00
 */
public class ServiceHandler extends BusinessObject {
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
        // Validate parameters
        if (systemCode == null || systemCode.trim().length() == 0) {
            throw new BusinessException(
              new StringBuffer("Invalid system code: ").append(systemCode)
                .toString());
        }
        
        if (userId == null) {
            throw new BusinessException("NULL user ID");
        }

        // Call related DAO class
        ServiceDAO dao = null;
        try {
            dao = TransactionDAOFactory.getServiceDAO();
          //  return dao.getAllowedUserServices(userId, systemCode);
            return null;

        } catch (DataAccessException ex) {
            throw ex;
        } catch(BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }
    
    /**
     * Get visible systems services.
     * 
     * @param systemCodes Array of related system codes.
     * @return visible systems services info.
     */
    public List getSystemsServices(String[] systemCodes) {
        // Validate parameters
        if (systemCodes == null || systemCodes.length == 0) {
            throw new BusinessException("Invalid system codes: " + systemCodes);
        }

        // Call related DAO class
        ServiceDAO dao = null;
        try {
            dao = TransactionDAOFactory.getServiceDAO();
            return ((ServiceHandler) dao).getSystemsServices(systemCodes);

        } catch (DataAccessException ex) {
            throw ex;
        } catch(BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }
    
    /**
     * Get visible systems services.
     * 
     * @param svcCodes Array of related services codes.
     * @return visible systems services info.
     */
    public List getServicesByCode(Integer[] svcCodes) {
        // Validate parameters
        if (svcCodes == null || svcCodes.length == 0) {
            throw new BusinessException("Invalid service codes: " + svcCodes);
        }

        // Call related DAO class
        ServiceDAO dao = null;
        try {
            dao = TransactionDAOFactory.getServiceDAO();
            return ((ServiceHandler) dao).getServicesByCode(svcCodes);

        } catch (DataAccessException ex) {
            throw ex;
        } catch(BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /** Check if role Service Code is already exists.
     * 
     * @param svcCode service code
     * @param sysCode system code
     * @return true if role Service Code is already exists, otherwise false.
     */
    public boolean checkServiceParentModule(Integer svcCode, String sysCode) {
        if (svcCode == null) {
            throw new BusinessException("Null Service Code Value");
        }
        if (isBlankOrNull(sysCode)) {
            throw new BusinessException("Null System Code Value");
        }
        ServiceDAO dao = null;
        try {
            dao = TransactionDAOFactory.getServiceDAO();
            return ((ServiceHandler) dao).checkServiceParentModule(svcCode, sysCode);
        } catch (DataAccessException ex) {
            throw ex;
        } catch(BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
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
    
        if(paramVO == null) {
            throw new BusinessException("Null Service VO.");
        }
        
      
        
        ServiceDAO dao = null;
        try {
            dao = TransactionDAOFactory.getServiceDAO();
            //return dao.find(pageNo, paramVO);
            return null;
            
        } catch (DataAccessException ex) {
            throw ex;
        } catch(BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }
    
    /**
     * Get Service By Code.
     * 
     * @param serviceCode Service Code.
     * @return Service VO.
     */
    public ServiceVO getByCode(Integer serviceCode) {
        if(serviceCode == null) {
            throw new BusinessException("Null Service Code.");
        }
        ServiceDAO dao = null;
        try {
            dao = TransactionDAOFactory.getServiceDAO();
            return ((ServiceHandler) dao).getByCode(serviceCode);

        } catch (DataAccessException ex) {
            throw ex;
        } catch(BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /**
     * Add New Service.
     * 
     * @param Service VO.
     * @return Service VO.
     */
    public ServiceVO add(ServiceVO vo) {
    
        if(vo == null) {
            throw new BusinessException("Null Service VO.");
        }
        
        if(isBlankOrNull(vo.getUpdatedBy())) {
            throw new BusinessException("Null updatedBy Value.");
        }
        
        if(isBlankOrNull(vo.getCreatedBy())) {
            throw new BusinessException("Null createdBy Value.");
        }
        
        if(isBlankOrNull(vo.getCenterName())) {
            throw new BusinessException("Null centerName Value.");
        }
        
        if(isBlankOrNull(vo.getEmployeeName())) {
            throw new BusinessException("Null employeeName Value.");
        }
        
//        if(vo.getMainService() == null) {
//            throw new BusinessException("Null mainService Flag Value.");
//        }
//        
//        if(vo.getHasFileCopy() == null) {
//            throw new BusinessException("Null hasFileCopy Flag Value.");
//        }
//        
//        if(vo.getAutoService() == null) {
//            throw new BusinessException("Null autoService Flag Value.");
//        }        
//        
//        if(vo.getRequiresManualAuditing() == null) {
//            throw new BusinessException("Null requiresManualAuditing Flag Value.");
//        }
//        
//        if(vo.getHasEForm() == null) {
//            throw new BusinessException("Null hasEForm Flag Value.");
//        }
//        
//        if(vo.getRequiresPersmissions() == null) {
//            throw new BusinessException("Null requiresPersmissions Flag Value.");
//        }
        
        // Business Rule "BR_SVC_001"
        if(vo.getCode() == null) {
            throw new RuleException("BR_SVC_001");
        }
        
        // Business Rule "BR_SVC_002"
        if(isServiceCodeAssignedBefore(vo.getCode(), null)) {
            throw new RuleException("BR_SVC_002");
        }
        
        // Business Rule "BR_SVC_003"
        if(isBlankOrNull(vo.getDescriptionAr())) {
            throw new RuleException("BR_SVC_003");
        }
        
        // Business Rule "BR_SVC_004"
        if(isServiceArabicDescAssignedBefore(vo.getDescriptionAr(), null)) {
            throw new RuleException("BR_SVC_004");
        }
        
        // Business Rule "BR_SVC_005"
        if(isBlankOrNull(vo.getDescriptionEn())) {
            throw new RuleException("BR_SVC_005");
        }
        
        // Business Rule "BR_SVC_006"
        if(isServiceEnglishDescAssignedBefore(vo.getDescriptionEn(), null)) {
            throw new RuleException("BR_SVC_006");
        }
//        
//        // Business Rule "BR_SVC_007"
//        if(vo.getServiceCategory() == null) {
//            throw new RuleException("BR_SVC_007");
//        }
//        
//        // Business Rule "BR_SVC_008"
//        if(vo.getSystem() == null || isBlankOrNull(vo.getSystem().getCode())) {
//            throw new RuleException("BR_SVC_008");
//        }
//        
//        // Business Rule "BR_SVC_009"
//        if(vo.isAutoService() && !vo.isMainService()) {
//            throw new RuleException("BR_SVC_009");
//        }
//        
//        // Business Rule "BR_SVC_010"
//        if(vo.isHasEForm() && !vo.isMainService()) {
//            throw new RuleException("BR_SVC_010");
//        }
//        
//        // Business Rule "BR_SVC_011"
//        if(vo.isRequiresManualAuditing() && !vo.isAutoService()) {
//            throw new RuleException("BR_SVC_011");
//        }
//        
//        // Business Rule "BR_SVC_012"
//        if(vo.isHasFileCopy() && !vo.isMainService()) {
//            throw new RuleException("BR_SVC_012");
//        }
//        
//        // Business Rule "BR_SVC_013"
//        if(vo.isRequiresPersmissions() &&
//           !vo.getSystem().getCode().equals(ServiceVO.SYS_CODE_COMMERCIAL_LICENSING)) {
//            throw new RuleException("BR_SVC_013");
//        }
//
//        // Business Rule "BR_SVC_014"
////       if(vo.isNeedsPassportInfo() && !vo.isMainService()) {
////            throw new RuleException("BR_SVC_014");
////        }
//        
//        // Business Rule "BR_SVC_033"        
//        if(vo.getViewLocation() == null) {
//            throw new RuleException("BR_SVC_033");
//        }
        
        // Perform Add Operation.        
        ServiceDAO dao = null;
        try {
            dao = TransactionDAOFactory.getServiceDAO();
          //  vo = dao.add(vo);

            // Insert Log.
            ServiceLogVO logVO = new ServiceLogVO();
            logVO.setLogData(vo);
            logVO.setActionType(ServiceLogVO.ACTION_TYPE_ADD);
            addLog(logVO, dao);

            dao.commit();
            return vo;

        } catch (DataAccessException ex) {
            rollback(dao);
            throw ex;
        } catch(BusinessException ex) {
            rollback(dao);
            throw ex;
        } catch (Exception ex) {
            rollback(dao);
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /**
     * Update Existing Service.
     * 
     * @param Service VO.
     * @return Service VO.
     */
    public ServiceVO update(ServiceVO vo) {
    
        if(vo == null) {
            throw new BusinessException("Null Service VO.");
        }
        
        if(isBlankOrNull(vo.getUpdatedBy())) {
            throw new BusinessException("Null updatedBy Value.");
        }
        
        if(isBlankOrNull(vo.getCreatedBy())) {
            throw new BusinessException("Null createdBy Value.");
        }
        
        if(isBlankOrNull(vo.getCenterName())) {
            throw new BusinessException("Null centerName Value.");
        }
        
        if(isBlankOrNull(vo.getEmployeeName())) {
            throw new BusinessException("Null employeeName Value.");
        }
        
//        if(vo.getMainService() == null) {
//            throw new BusinessException("Null mainService Flag Value.");
//        }
//        
//        if(vo.getHasFileCopy() == null) {
//            throw new BusinessException("Null hasFileCopy Flag Value.");
//        }
//        
//        if(vo.getAutoService() == null) {
//            throw new BusinessException("Null autoService Flag Value.");
//        }        
//        
//        if(vo.getRequiresManualAuditing() == null) {
//            throw new BusinessException("Null requiresManualAuditing Flag Value.");
//        }
//        
//        if(vo.getHasEForm() == null) {
//            throw new BusinessException("Null hasEForm Flag Value.");
//        }
//        
//        if(vo.getRequiresPersmissions() == null) {
//            throw new BusinessException("Null requiresPersmissions Flag Value.");
//        }
        
        // Business Rule "BR_SVC_001"
        if(vo.getCode() == null) {
            throw new RuleException("BR_SVC_001");
        }
        
        // Business Rule "BR_SVC_002"
        if(isServiceCodeAssignedBefore(vo.getCode(),
                                       new Integer(vo.getId().intValue()))) {
            throw new RuleException("BR_SVC_002");
        }
        
        // Business Rule "BR_SVC_003"
        if(isBlankOrNull(vo.getDescriptionAr())) {
            throw new RuleException("BR_SVC_003");
        }
        
        // Business Rule "BR_SVC_004"
        if(isServiceArabicDescAssignedBefore(vo.getDescriptionAr(),
                                             new Integer(vo.getId().intValue()))) {
            throw new RuleException("BR_SVC_004");
        }
        
        // Business Rule "BR_SVC_005"
        if(isBlankOrNull(vo.getDescriptionEn())) {
            throw new RuleException("BR_SVC_005");
        }
        
        // Business Rule "BR_SVC_006"
        if(isServiceEnglishDescAssignedBefore(vo.getDescriptionEn(),
                                              new Integer(vo.getId().intValue()))) {
            throw new RuleException("BR_SVC_006");
        }
//        
//        // Business Rule "BR_SVC_007"
//        if(vo.getServiceCategory() == null) {
//            throw new RuleException("BR_SVC_007");
//        }
//        
//        // Business Rule "BR_SVC_008"
//        if(vo.getSystem() == null || isBlankOrNull(vo.getSystem().getCode())) {
//            throw new RuleException("BR_SVC_008");
//        }
//        
//        // Business Rule "BR_SVC_009"
//        if(vo.isAutoService() && !vo.isMainService()) {
//            throw new RuleException("BR_SVC_009");
//        }
//        
//        // Business Rule "BR_SVC_010"
//        if(vo.isHasEForm() && !vo.isMainService()) {
//            throw new RuleException("BR_SVC_010");
//        }
//        
//        // Business Rule "BR_SVC_011"
//        if(vo.isRequiresManualAuditing() && !vo.isAutoService()) {
//            throw new RuleException("BR_SVC_011");
//        }
//        
//        // Business Rule "BR_SVC_012"
//        if(vo.isHasFileCopy() && !vo.isMainService()) {
//            throw new RuleException("BR_SVC_012");
//        }
//        
//        // Business Rule "BR_SVC_013"
//        if(vo.isRequiresPersmissions() &&
//           !vo.getSystem().getCode().equals(ServiceVO.SYS_CODE_COMMERCIAL_LICENSING)) {
//            throw new RuleException("BR_SVC_013");
//        }

        // Business Rule "BR_SVC_014"
//        if(vo.isNeedsPassportInfo() && !vo.isMainService()) {
//            throw new RuleException("BR_SVC_014");
//        }
         
        // Business Rule "BR_SVC_033"        
//        if(vo.getViewLocation() == null) {
//            throw new RuleException("BR_SVC_033");
//        }
        
        // Perform Update Operation.        
        ServiceDAO dao = null;
        try {
            dao = TransactionDAOFactory.getServiceDAO();
           // vo = dao.update(vo);

            // Insert Log.
            ServiceLogVO logVO = new ServiceLogVO();
            logVO.setLogData(vo);
            logVO.setActionType(ServiceLogVO.ACTION_TYPE_UPDATE);
            addLog(logVO, dao);

            dao.commit();
            return vo;

        } catch (DataAccessException ex) {
            rollback(dao);
            throw ex;
        } catch(BusinessException ex) {
            rollback(dao);
            throw ex;
        } catch (Exception ex) {
            rollback(dao);  
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /**
     * Add New Service Log.
     * 
     * @param Service Log VO.
     */
    public void addLog(ServiceLogVO vo) {
    
        ServiceDAO dao = null;
        try {
            dao = TransactionDAOFactory.getServiceDAO();
            
            // Calling Overloaded Method.
            addLog(vo, dao);
            
            dao.commit();

        } catch (DataAccessException ex) {
            rollback(dao);
            throw ex;
        } catch(BusinessException ex) {
            rollback(dao);
            throw ex;
        } catch (Exception ex) {
            rollback(dao);
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
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

        if(serviceCode == null) {
            throw new BusinessException("Null Service Code Value.");
        }
                                                
        // Perform Checking.        
        ServiceDAO dao = null;
        try {
            dao = TransactionDAOFactory.getServiceDAO();
//            return dao.isServiceCodeAssignedBefore(serviceCode,
//                                                   execludedServiceCode);
            return true;

        } catch (DataAccessException ex) {
            throw ex;
        } catch(BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
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

        if(isBlankOrNull(serviceDescAr)) {
            throw new BusinessException("Invalid Service Arabic Description Value.");
        }
                                                
        // Perform Checking.        
        ServiceDAO dao = null;
        try {
            dao = TransactionDAOFactory.getServiceDAO();
//            return dao.isServiceArabicDescAssignedBefore(serviceDescAr,
//                                                         execludedServiceCode);
            return true;

        } catch (DataAccessException ex) {
            throw ex;
        } catch(BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
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

        if(isBlankOrNull(serviceDescEn)) {
            throw new BusinessException("Invalid Service English Description Value.");
        }
                                                
        // Perform Checking.        
        ServiceDAO dao = null;
        try {
            dao = TransactionDAOFactory.getServiceDAO();
//            return dao.isServiceEnglishDescAssignedBefore(serviceDescEn,
//                                                          execludedServiceCode);
            return true;

        } catch (DataAccessException ex) {
            throw ex;
        } catch(BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }
    
    /**
     * Add New Service Log.
     * 
     * @param Service VO.
     * @param callerDAO Caller Passed Data Access Object.
     */
    public void addLog(ServiceLogVO vo, DataAccessObject callerDAO) {
    
        if(vo == null) {
            throw new BusinessException("Null Service Log VO.");
        }
        
        if(vo.getCode() == null) {
            throw new BusinessException("Null Service Code Value.");
        }
        
        if(isBlankOrNull(vo.getCreatedBy())) {
            throw new BusinessException("Null createdBy Value.");
        }
        
        if(isBlankOrNull(vo.getCenterName())) {
            throw new BusinessException("Null centerName Value.");
        }
        
        if(isBlankOrNull(vo.getEmployeeName())) {
            throw new BusinessException("Null employeeName Value.");
        }
        
        if(vo.getActionType() == null) {
            throw new BusinessException("Null actionType Value.");
        }
        
        if(callerDAO == null) {
            throw new BusinessException("Null DAO Object.");
        }
        
        ServiceDAO dao = TransactionDAOFactory.getServiceDAO();
        
        // Since We can'nt use getDAO() Method...
        // Connect the new DAO to the same dao transaction
        dao.connect(callerDAO);
        
       // dao.addLog(vo);

    }

    /**
     * get Services List
     * 
     * @return Services Option List
     */
    public List getServicesList(){
    
        ServiceDAO dao = null;
        try {
            dao = TransactionDAOFactory.getServiceDAO();
          //  return dao.getServicesList();
            return null;

        } catch (DataAccessException ex) {
            throw ex;
        } catch(BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }        
        
    }
    
    /**
     * Find Exempted Services
     * 
     * @param ServiceVO : Service VO
     * @param pageNo : Page No
     * @return get exempted services list
     */
     public SearchPageVO findExemptedServices(ServiceVO serviceVO ,int pageNo) {
        ServiceDAO dao = null;
        try {
            dao = TransactionDAOFactory.getServiceDAO();
         //   return dao.findExemptedServices(serviceVO,pageNo);
            return null;
        } catch (DataAccessException ex) {
            throw ex;
        } catch(BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }
    /**
     * Find Exempted Services
     * 
     * @return exempted services list
     */
    public List <ServiceVO> findExemptedServices() {
        ServiceDAO dao = null;
        try {
            dao = TransactionDAOFactory.getServiceDAO();
          //  return dao.findExemptedServices();
            return null;
        } catch (DataAccessException ex) {
            throw ex;
        } catch(BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }
    /**
     * Is service defined by service code and system code
     * 
     * @param serviceCode Service Code
     * @param systemCode System Code
     * @return true if service defined by service code and system code
     */
    public boolean isServiceDefined(Integer serviceCode,String systemCode) {
        
        //Validate mandatory parameters
        if (serviceCode == null)  {
            throw new BusinessException("Emty Service Code Value");
        }
        if (isBlankOrNull(systemCode))  {
            throw new BusinessException("Emty System Code Value");
        }
        
        //GetRelated DAO
        ServiceDAO dao = null;
        try {
            dao = TransactionDAOFactory.getServiceDAO();
          //  return dao.isServiceDefined(serviceCode,systemCode);
            return true;
        } catch (DataAccessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }
    
    /**
     * Check if the service is related to electronic insurance or not
     * 
     * @param serviceCode Service Code
     * @return true if it is related to electronic insurance or not
     */
    public boolean isEirService(Integer serviceCode) {
        
        //Validate mandatory parameters
        if (serviceCode == null)  {
            throw new BusinessException("Emty Service Code Value");
        }
        
        //GetRelated DAO
        ServiceDAO dao = null;
        try {
            dao = TransactionDAOFactory.getServiceDAO();
           // return dao.isEirService(serviceCode);
            return true;
        } catch (DataAccessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }
    
    /**
     * Get Service By Transaction ID.
     * 
     * @param transactionId : Transaction ID.
     * @return Service VO.
     */
    public ServiceVO getByTransactionId(Long transactionId) {
        
        // Validate The Mandatory Transaction ID Parameter.
        if (transactionId == null)  {
        
            throw new BusinessException("Transaction ID Is Mandatory.");
        }
        
        ServiceDAO dao = null;
        try {
        
            dao = TransactionDAOFactory.getServiceDAO();
            //return dao.getByTransactionId(transactionId);
            return null;
        } catch (DataAccessException ex) {
        
            throw ex;
        } catch(BusinessException ex) {
        
            throw ex;
        } catch (Exception ex) {
        
            throw new BusinessException(ex);
        } finally {
        
            close(dao);
        }
    }
    
    /**
     * get Payment Clearance Services List
     * 
     * @return Payment Clearance Services Option List
     */
    public List getPaymentClearanceServices(){
    
        ServiceDAO dao = null;
        try {
            dao = TransactionDAOFactory.getServiceDAO();
          //  return dao.getPaymentClearanceServices();
            return null;

        } catch (DataAccessException ex) {
            throw ex;
        } catch(BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }        
        
    }
    
    
    /**
      * Get All Services
      * 
      * @param serviceCode Service Code
      * @return List Of Service VO
//      */    
//    public List<ae.rta.trs.vo.ServiceVO> getAllServices(String serviceCode) { 
//        ServiceDAO dao = null; 
//        
//        try {
//            dao = TransactionDAOFactory.getServiceDAO();
//            
//            return dao.getAllServices(serviceCode);
//
//        } catch (DataAccessException ex) {
//            throw ex;
//
//        } catch(BusinessException ex) {
//            throw ex;
//
//        } catch (Exception ex) {
//            throw new BusinessException(ex);
//
//        } finally {
//            close(dao);
//        }    
//    }  
//    
    /**
      * Get All Services
      * 
      * 
      * @return List Of Service VO
      */    
    public List<ServiceVO> getAllServices() { 
        ServiceDAO dao = null; 
        
        try {
            dao = TransactionDAOFactory.getServiceDAO();
            
          //  return dao.getAllServices();
            return null;

        } catch (DataAccessException ex) {
            throw ex;

        } catch(BusinessException ex) {
            throw ex;

        } catch (Exception ex) {
            throw new BusinessException(ex);

        } finally {
            close(dao);
        }    
    } 
    
    /**
     * Get allowed services for this user.
     * 
     * @param userId User ID.
     * @param systemCode System code.
     * @return allowed services for this user.
     */
    public ServiceVO[] getAllowedUserMainServices(Long userId, String systemCode) {
        ServiceDAO dao = null; 
        
        try {
            dao = TransactionDAOFactory.getServiceDAO();
            //return dao.getAllowedUserMainServices(userId, systemCode);
            return null;

        } catch (DataAccessException ex) {
            throw ex;
        } catch(BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }    
    }
}