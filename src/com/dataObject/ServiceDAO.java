/*
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 * * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00 Abdelhameed Elnaggar  04/01/2012   - File created.
 */

package  com.dataObject;

import java.util.List;

/**
 * Service data access object interface.
 *
 * @author Abdelhameed Elnaggar
 * @version 1.00
 */
public interface ServiceDAO extends DataAccessObject {
    /*
     * Methods
     */
     

    /**
     * Find Services.
     * 
     * @param searchVO : Service VO.
     * @param pageNo page No.
     * @return Search page value object of ServiceVOs.
     */
    SearchPageVO find(ServiceVO searchVO, int pageNo) ;
    
    /**
     * Get Appointment Service Value Object By Id.
     * 
     * @param serviceId : Long Appointment Service Id.
     * @return ServiceVO
     */
    ServiceVO getById(Long serviceId);
    
    /**
     * Create new Appointment Service.
     * 
     * @param vo :ServiceVO Appointment Service Value Object.
     */
    void insert(ServiceVO vo);
    
    /**
     * Update Appointment Service Value Object.
     * 
     * @param vo :ServiceVO.
     */
    void update(ServiceVO vo);
    
    /**
     * Get Appointment service 
     * 
     * @return List of ServiceVO
     */
     List getApponitmentServices();
    
    /**
     * Check if Servcie Code exists.
     * 
     * @param serviceCode : service Code.
     * @param serviceId : service Id. (used in update).
     * 
     * @return true if Servcie Code exists.
     */
    boolean isServiceCodeExists(Integer serviceCode, Long serviceId);
}