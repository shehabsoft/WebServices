/*
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 * * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00 Abdelhameed Elnaggar  04/01/2012  - File created.
 */

package com.dataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NamedQuery;

import com.HomeBudget.DAO.JPA.DataAccessObject1;

/**
 * Service data access object JDBC implementation.
 *
 * @author Abdelhameed Elnaggar
 * @version 1.00
 */
public class ServiceDAOImpl extends JdbcDataAccessObject implements ServiceDAO {
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
    public SearchPageVO find(ServiceVO searchVO, int pageNo) {
        
       // NamedQuery namedQuery = getNamedQuery("ae.rta.apt.dao.jdbc.Service.find");
        

//        
//        if (searchVO.getCode() != null)  {
//            namedQuery.appendWhereClause(" AND SERVICE_CODE = :code ",
//                                        "code", searchVO.getCode());
//        }
//        
//        if (searchVO.getNameAr() != null )  {
//            namedQuery.appendWhereClause("AND SERVICE_NAME_A LIKE :nameAr",
//                                         "nameAr","%"+searchVO.getNameAr()+"%");
//                    
//        }

//        if (searchVO.getNameEn() != null )  {
//            namedQuery.appendWhereClause("AND upper(SERVICE_NAME) LIKE upper(:nameEn)",
//                                         "nameEn","%"+searchVO.getNameEn()+"%");
//                    
//        }

                
        // Debug query
        //debugQuery(namedQuery);
        
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        
        try {
        
//            int totalRecordCounts = getTotalCount(namedQuery);
//            int pageSize = getDefaultPageSize();
//            stmt = doSearch(namedQuery, pageNo, pageSize);
//            SearchPageVO searchPage = new SearchPageVO(pageNo, pageSize,
//                                                       totalRecordCounts);
            resultSet = stmt.executeQuery();
            
            while(resultSet.next()) {
                
                ServiceVO vo = new ServiceVO();
                
//                vo.setId(getLong(resultSet, "ID"));
//                vo.setCode(getInteger(resultSet, "SERVICE_CODE"));
//
//                vo.setNameAr(getString(resultSet, "SERVICE_NAME_A"));
//                vo.setNameEn(getString(resultSet, "SERVICE_NAME"));
//                
//                vo.setDescriptionAr(getString(resultSet, "SERVICE_DESCRIPTION_A"));
//                vo.setDescriptionEn(getString(resultSet, "SERVICE_DESCRIPTION"));
//                vo.setServiceType(getInteger(resultSet, "SERVICE_TYPE"));
                
                //searchPage.addRecord(vo);
            }

            return null;

        } catch(Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(resultSet);
            close(stmt);
        }
    }
    
    /**
     * Get Appointment Service Value Object By Id.
     * 
     * @param serviceId : Long Appointment Service Id.
     * @return ServiceVO
     */
    public ServiceVO getById(Long serviceId) {
//    
//        NamedQuery query = getNamedQuery("ae.rta.apt.dao.jdbc.Service.getById");
//        
//        query.setParameter("id", serviceId);
//        
//        debugQuery(query);
//        PreparedStatement stmt = null;
//        ResultSet resultSet = null;
//        
//        try {
//            stmt = prepareStatement(query);
//            resultSet = stmt.executeQuery();
//            
//            if (! resultSet.next()) {
//                return null;
//            }
//            
//            ServiceVO vo = new ServiceVO();
//            
////            vo.setId(getLong(resultSet, "ID"));
////            vo.setCode(getInteger(resultSet, "SERVICE_CODE"));
////
////            vo.setNameAr(getString(resultSet, "SERVICE_NAME_A"));
////            vo.setNameEn(getString(resultSet, "SERVICE_NAME"));
////            
////            vo.setDescriptionAr(getString(resultSet, "SERVICE_DESCRIPTION_A"));
////            vo.setDescriptionEn(getString(resultSet, "SERVICE_DESCRIPTION"));
////            vo.setServiceType(getInteger(resultSet, "SERVICE_TYPE"));
////            
//            
//            return vo;
//        
//        } catch(Exception ex) {
//            throw new DataAccessException(ex);
//            
//        } finally {
//            close(resultSet);
//            close(stmt);
//        }
    	return null;
    }
    
    /**
     * Create new Appointment Service.
     * 
     * @param vo :ServiceVO Appointment Service Value Object.
     */
    public void insert(ServiceVO vo) {
    
//        NamedQuery namedQuery = getNamedQuery("ae.rta.apt.dao.jdbc.Service.insert");
//        
//        Long id = generateSequence("IAS_SEQ");
//        
//        namedQuery.setParameter("id", id);
//        
//        namedQuery.setParameter("serviceCode", vo.getCode());
//        namedQuery.setParameter("serviceName", vo.getNameEn());
//        namedQuery.setParameter("serviceNameA", vo.getNameAr());
//        namedQuery.setParameter("serviceDesc", vo.getDescriptionEn());
//        namedQuery.setParameter("serviceDescA", vo.getDescriptionAr());
//        namedQuery.setParameter("serviceType", vo.getServiceType());
//        
//        namedQuery.setParameter("createdBy", vo.getCreatedBy());
//        namedQuery.setParameter("updatedBy", vo.getUpdatedBy());
//        
//        debugQuery(namedQuery);
//        PreparedStatement prepStmt = null;
//        
//        try{
//        
//            prepStmt = prepareStatement(namedQuery);
//            int count = prepStmt.executeUpdate();
//            
//            if(count != 1) {
//                throw new DataAccessException("Error while adding new Appointment Service");
//            }
//            vo.setId(id);
//            
//        } catch(Exception ex) {
//            throw new DataAccessException(ex);
//        } finally {
//            close(prepStmt);
//        }
    }
    
    /**
     * Update Appointment Service Value Object.
     * 
     * @param vo :ServiceVO.
     */
    public void update(ServiceVO vo) {
    
//        NamedQuery namedQuery = getNamedQuery("ae.rta.apt.dao.jdbc.Service.update");
//        
//        namedQuery.setParameter("id", vo.getId());
//        
//        namedQuery.setParameter("serviceCode", vo.getCode());
//        namedQuery.setParameter("serviceName", vo.getNameEn());
//        namedQuery.setParameter("serviceNameA", vo.getNameAr());
//        namedQuery.setParameter("serviceDesc", vo.getDescriptionEn());
//        namedQuery.setParameter("serviceDescA", vo.getDescriptionAr());
//        namedQuery.setParameter("updatedBy", vo.getUpdatedBy());
//        namedQuery.setParameter("serviceType", vo.getServiceType());
//        
//        debugQuery(namedQuery);
//        PreparedStatement prepStmt = null;
//        
//        try{
//            prepStmt = prepareStatement(namedQuery);
//            int count = prepStmt.executeUpdate();
//            
//            if (count != 1) {
//                throw new DataAccessException("Error while updateing Appointment Service.");
//            }
//            
//        } catch(Exception ex) {
//            throw new DataAccessException(ex);
//        } finally {
//            close(prepStmt);
//        }
//        
    }
    
    /**
     * Get Appointment Service 
     * 
     * @return List of ServiceVO
     */
    public List getApponitmentServices() {
        
//        NamedQuery namedQuery = getNamedQuery("ae.rta.apt.dao.jdbc.Service.getApponitmentServices");
//
//        // Debug query
//        debugQuery(namedQuery);
//        PreparedStatement stmt = null;
//        ResultSet resultSet = null;
//        
//        try {
//        
//            stmt = prepareStatement(namedQuery);
//            resultSet = stmt.executeQuery();
//            List centersList = new ArrayList();
//            
//            while(resultSet.next()) {
//                
//                ServiceVO vo = new ServiceVO();
//                
//                vo.setId(getLong(resultSet, "ID"));
//                vo.setNameAr(getString(resultSet, "SERVICE_NAME_A"));
//                centersList.add(vo);
//            }
//
//            return centersList;
//
//        } catch(Exception ex) {
//            throw new DataAccessException(ex);
//        } finally {
//            close(resultSet);
//            close(stmt);
//        }
    	return null;
    }
    
    /**
     * Check if Servcie Code exists.
     * 
     * @param serviceCode : service Code.
     * @param serviceId : service Id. (used in update).
     * 
     * @return true if Servcie Code exists.
     */
    public boolean isServiceCodeExists(Integer serviceCode, Long serviceId) {
    
//        NamedQuery query = getNamedQuery("ae.rta.apt.dao.jdbc.Service.isServiceCodeExists");
//        
//        query.setParameter("serviceCode", serviceCode);
//        
//        if (serviceId != null)  {
//            query.appendWhereClause(" AND IAS.ID <> :id", "id", serviceId);
//        }
//        
//        // Debug query
//        debugQuery(query);
//        
//        PreparedStatement stmt = null;
//        ResultSet resultSet = null;
//        
//        try {
//            stmt = prepareStatement(query);
//            resultSet = stmt.executeQuery();
//            
//            while(resultSet.next()) {
//                int count = getInteger(resultSet, "COUNT").intValue();
//                return count > 0;
//            }
//            
//            return false;
//        
//        } catch(Exception ex) {
//            throw new DataAccessException(ex);
//        } finally {
//            close(resultSet);
//            close(stmt);
//        }
    	return true;
    }

	@Override
	public void connect(DataAccessObject1 dao) {
		// TODO Auto-generated method stub
		
	}
   
}