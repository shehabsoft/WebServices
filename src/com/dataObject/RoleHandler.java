/*
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Alaa Salem         12/10/2009  - File created.
 */
package com.dataObject;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Security business object. Used by security components for handleing
 * security roles.
 *
 * @author shehab .tarek
 * @version 1.00
 */
public class RoleHandler extends BusinessObject {

    /*
     * Business Objects.
     */

    /** Security roles business object. */
    private ServiceHandler servicehandler = new ServiceHandler();

    /*
     * Methods.
     */

    /**
     * Get Role By ID.
     *
     * @return Role VO.
     * @param roleId Role ID.
     */
    public RoleVO getById(Long roleId) {
        if (roleId == null) {
            throw new BusinessException("Null Role ID.");
        }
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.getById(roleId);

        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /**
     * Find Roles.
     *
     * @return Search Page Value Object
     * @param pageNo pagination page Number
     * @param vo Role VO
     * @param actionUserAdminLevel Admin Level For Logged-In User.
     */
    public SearchPageVO findRoles(int pageNo, RoleVO vo, Integer actionUserAdminLevel) {
        if (vo == null) {
            throw new BusinessException("Null Role Value Object.");
        }

        if (actionUserAdminLevel == null) {
            throw new BusinessException("Null Action User Admin Level.");
        }

        if (isBlankOrNull(vo.getDescriptionAr()) && isBlankOrNull(vo.getDescriptionEn()) && vo.getStatus() == null &&
            vo.getStatusDate() == null && vo.getAdminLevel() == null && vo.getParentRole().getId() == null &&
            vo.getRoleType() == null) {
            throw new BusinessException("Invalid search criteria.");
        }

        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.find(pageNo, vo, actionUserAdminLevel, false);

        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /**
     * Find Role Parents.
     *
     * @param vo Role VO
     * @param pageNo pagination page Number
     * @param actionUserAdminLevel Logged-in User Admin Level.
     * @return Search Page Value Object
     */
    public SearchPageVO findParentRoles(int pageNo, RoleVO vo, Integer actionUserAdminLevel) {

        // Validate Parameters.
        if (vo == null) {
            throw new BusinessException("Null Role Value Object");
        }

        if (actionUserAdminLevel == null) {
            throw new BusinessException("Null Action User Admin Level.");
        }

        if (isBlankOrNull(vo.getDescriptionAr()) && isBlankOrNull(vo.getDescriptionEn()) && vo.getStatus() == null &&
            vo.getStatusDate() == null && vo.getAdminLevel() == null && vo.getParentRole().getId() == null &&
            vo.getRoleType() == null) {
            throw new BusinessException("Invalid search criteria.");
        }

        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.find(pageNo, vo, actionUserAdminLevel, true);

        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /**
     * Get Child Roles.
     *
     * @return List Of RoleVO
     * @param roleId Role ID
     */
    public List getChildRoles(Long roleId) {

        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.getChildRoles(roleId);

        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /**
     * Add Role Of Type Business Owner.
     *
     * @return RoleVO Role VO
     * @param vo Role VO
     */
    public RoleVO addBusinessOwner(RoleVO vo) {
        if (vo == null) {
            throw new BusinessException("Null Role Value Object");
        }
        if (isBlankOrNull(vo.getCenterName())) {
            throw new BusinessException("Null CenterName Role Value Object");
        }
        if (isBlankOrNull(vo.getCreatedBy())) {
            throw new BusinessException("Null CreatedBy Role Value Object");
        }
        // Bussiness rule "BR_ROL001_ATT"
        if (isBlankOrNull(vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL001_ATT");
        }
        // Bussiness rule "BR_ROL002_TBL"
        if (isDescriptionArExists(vo.getId(), vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL002_TBL");
        }
        // Bussiness rule "BR_ROL003_ATT"
        if (isBlankOrNull(vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL003_ATT");
        }
        // Bussiness rule "BR_ROL004_TBL"
        if (isDescriptionEnExists(vo.getId(), vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL004_TBL");
        }
        // Bussiness rule "BR_ROL005_ATT"
        if (vo.getRoleType() == null || !vo.getRoleType().equals(vo.ROLE_TYPE_BUSINESS_OWNER)) {
            throw new RuleException("BR_ROL005_ATT");
        }
        // Bussiness rule "BR_ROL006_TPL"
        if (vo.getParentRole() != null && vo.getParentRole().getId() != null) {
            throw new RuleException("BR_ROL006_TPL");
        }
        // Bussiness rule "BR_ROL014_ATT"
        if (vo.getStatus() == null) {
            throw new RuleException("BR_ROL014_ATT");
        }
        // Bussiness rule "BR_ROL017_TPL"
        if (!isBlankOrNull(vo.getUrl())) {
            throw new RuleException("BR_ROL017_TPL");
        }
        // Bussiness rule "BR_ROL020_TPL"
        if (!isBlankOrNull(vo.getRoleCode())) {
            throw new RuleException("BR_ROL020_TPL");
        }
        // Bussiness rule "BR_ROL023_TPL"
       
        // Bussiness rule "BR_ROL029_TPL"
        if (!isBlankOrNull(vo.getItemDomain()) || !isBlankOrNull(vo.getItemValue())) {
            throw new RuleException("BR_ROL029_TPL");
        }
        // Bussiness rule "BR_ROL030_TBL"
        if (!vo.isVirtualRole()) {
            throw new RuleException("BR_ROL030_TBL");
        }
        // Bussiness rule "BR_ROL032_ATT"
        if (vo.getAdminLevel() == null) {
            throw new RuleException("BR_ROL032_ATT");
        }
        // Bussiness rule "BR_ROL040_TPL"
        if (!vo.isStatusActive()) {
            throw new RuleException("BR_ROL040_TPL");
        }
        // perform add
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            RoleVO newVO = dao.add(vo);
            dao.commit();
            return newVO;

        } catch (DataAccessException ex) {
            rollback(dao);
            throw ex;
        } catch (BusinessException ex) {
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
     * Update Role Of Type Business Owner.
     *
     * @return RoleVO Role VO
     * @param vo role VO
     */
    public RoleVO updateBusinessOwner(RoleVO vo) {
        if (vo == null) {
            throw new BusinessException("Null Role Value Object");
        }
        if (isBlankOrNull(vo.getCenterName())) {
            throw new BusinessException("Null CenterName Role Value Object");
        }
        if (isBlankOrNull(vo.getUpdatedBy())) {
            throw new BusinessException("Null UpdatedBy Role Value Object");
        }
        // Bussiness rule "BR_ROL001_ATT"
        if (isBlankOrNull(vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL001_ATT");
        }
        // Bussiness rule "BR_ROL002_TBL"
        if (isDescriptionArExists(vo.getId(), vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL002_TBL");
        }
        // Bussiness rule "BR_ROL003_ATT"
        if (isBlankOrNull(vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL003_ATT");
        }
        // Bussiness rule "BR_ROL004_TBL"
        if (isDescriptionEnExists(vo.getId(), vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL004_TBL");
        }
        // Bussiness rule "BR_ROL005_ATT"
        if (vo.getRoleType() == null || !vo.getRoleType().equals(vo.ROLE_TYPE_BUSINESS_OWNER)) {
            throw new RuleException("BR_ROL005_ATT");
        }
        // Bussiness rule "BR_ROL006_TPL"
        if (vo.getParentRole() != null && vo.getParentRole().getId() != null) {
            throw new RuleException("BR_ROL006_TPL");
        }
        // Bussiness rule "BR_ROL014_ATT"
        if (vo.getStatus() == null) {
            throw new RuleException("BR_ROL014_ATT");
        }
        // Bussiness rule "BR_ROL017_TPL"
        if (!isBlankOrNull(vo.getUrl())) {
            throw new RuleException("BR_ROL017_TPL");
        }
        // Bussiness rule "BR_ROL020_TPL"
        if (!isBlankOrNull(vo.getRoleCode())) {
            throw new RuleException("BR_ROL020_TPL");
        }
       
        // Bussiness rule "BR_ROL029_TPL"
        if (!isBlankOrNull(vo.getItemDomain()) || !isBlankOrNull(vo.getItemValue())) {
            throw new RuleException("BR_ROL029_TPL");
        }
        // Bussiness rule "BR_ROL030_TBL"
        if (!vo.isVirtualRole()) {
            throw new RuleException("BR_ROL030_TBL");
        }
        // Bussiness rule "BR_ROL032_ATT"
        if (vo.getAdminLevel() == null) {
            throw new RuleException("BR_ROL032_ATT");
        }
        // Bussiness rule "BR_ROL037_TBL"
        if (!vo.isStatusActive() && isRoleHasActiveChild(vo.getId())) {
            throw new RuleException("BR_ROL037_TBL");
        }
        // perform update
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            RoleVO newVO = dao.update(vo);
            dao.commit();
            return newVO;

        } catch (DataAccessException ex) {
            rollback(dao);
            throw ex;
        } catch (BusinessException ex) {
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
     * Add Role Of Type Module.
     *
     * @return RoleVO Role VO
     * @param vo role VO
     */
    public RoleVO addModule(RoleVO vo) {
        if (vo == null) {
            throw new BusinessException("Null Role Value Object");
        }
        if (isBlankOrNull(vo.getCenterName())) {
            throw new BusinessException("Null CenterName Role Value Object");
        }
        if (isBlankOrNull(vo.getCreatedBy())) {
            throw new BusinessException("Null CreatedBy Role Value Object");
        }
        // Bussiness rule "BR_ROL001_ATT"
        if (isBlankOrNull(vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL001_ATT");
        }
        // Bussiness rule "BR_ROL002_TBL"
        if (isDescriptionArExists(vo.getId(), vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL002_TBL");
        }
        // Bussiness rule "BR_ROL003_ATT"
        if (isBlankOrNull(vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL003_ATT");
        }
        // Bussiness rule "BR_ROL004_TBL"
        if (isDescriptionEnExists(vo.getId(), vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL004_TBL");
        }
        // Bussiness rule "BR_ROL005_ATT"
        if (vo.getRoleType() == null || !vo.getRoleType().equals(vo.ROLE_TYPE_MODULE)) {
            throw new RuleException("BR_ROL005_ATT");
        }
        // Bussiness rule "BR_ROL007_TPL"
        if (vo.getParentRole() == null || vo.getParentRole().getId() == null ||
            !vo.getParentRole().getRoleType().equals(vo.ROLE_TYPE_BUSINESS_OWNER)) {
            throw new RuleException("BR_ROL007_TPL");
        }
        // Bussiness rule "BR_ROL014_ATT"
        if (vo.getStatus() == null) {
            throw new RuleException("BR_ROL014_ATT");
        }
        // Bussiness rule "BR_ROL017_TPL"
        if (!isBlankOrNull(vo.getUrl())) {
            throw new RuleException("BR_ROL017_TPL");
        }
        // Bussiness rule "BR_ROL020_TPL"
        if (!isBlankOrNull(vo.getRoleCode())) {
            throw new RuleException("BR_ROL020_TPL");
        }
     
       
        // Bussiness rule "BR_ROL029_TPL"
        if (!isBlankOrNull(vo.getItemDomain()) || !isBlankOrNull(vo.getItemValue())) {
            throw new RuleException("BR_ROL029_TPL");
        }
        // Bussiness rule "BR_ROL030_TBL"
        if (!vo.isVirtualRole()) {
            throw new RuleException("BR_ROL030_TBL");
        }
        // Bussiness rule "BR_ROL032_ATT"
        if (vo.getAdminLevel() == null) {
            throw new RuleException("BR_ROL032_ATT");
        }
        // Bussiness rule "BR_ROL040_TPL"
        if (!vo.isStatusActive()) {
            throw new RuleException("BR_ROL040_TPL");
        }
        // Get Parent Role.
        RoleVO parentRole = getById(vo.getParentRole().getId());
        // Bussiness rule "BR_ROL038_TBL"
        if (vo.isStatusActive() && !parentRole.isStatusActive()) {
            throw new RuleException("BR_ROL038_TBL");
        }
        // perform add
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            RoleVO newVO = dao.add(vo);
            dao.commit();
            return newVO;

        } catch (DataAccessException ex) {
            rollback(dao);
            throw ex;
        } catch (BusinessException ex) {
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
     * Update Role Of Type Module.
     *
     * @return RoleVO Role VO
     * @param vo role VO
     */
    public RoleVO updateModule(RoleVO vo) {
        if (vo == null) {
            throw new BusinessException("Null Role Value Object");
        }
        if (isBlankOrNull(vo.getCenterName())) {
            throw new BusinessException("Null CenterName Role Value Object");
        }
        if (isBlankOrNull(vo.getUpdatedBy())) {
            throw new BusinessException("Null UpdatedBy Role Value Object");
        }
        // Bussiness rule "BR_ROL001_ATT"
        if (isBlankOrNull(vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL001_ATT");
        }
        // Bussiness rule "BR_ROL002_TBL"
        if (isDescriptionArExists(vo.getId(), vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL002_TBL");
        }
        // Bussiness rule "BR_ROL003_ATT"
        if (isBlankOrNull(vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL003_ATT");
        }
        // Bussiness rule "BR_ROL004_TBL"
        if (isDescriptionEnExists(vo.getId(), vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL004_TBL");
        }
        // Bussiness rule "BR_ROL005_ATT"
        if (vo.getRoleType() == null || !vo.getRoleType().equals(vo.ROLE_TYPE_MODULE)) {
            throw new RuleException("BR_ROL005_ATT");
        }
        // Bussiness rule "BR_ROL007_TPL"
        if (vo.getParentRole() == null || vo.getParentRole().getId() == null ||
            !vo.getParentRole().getRoleType().equals(vo.ROLE_TYPE_BUSINESS_OWNER)) {
            throw new RuleException("BR_ROL007_TPL");
        }
        // Bussiness rule "BR_ROL014_ATT"
        if (vo.getStatus() == null) {
            throw new RuleException("BR_ROL014_ATT");
        }
        // Bussiness rule "BR_ROL017_TPL"
        if (!isBlankOrNull(vo.getUrl())) {
            throw new RuleException("BR_ROL017_TPL");
        }
        // Bussiness rule "BR_ROL020_TPL"
        if (!isBlankOrNull(vo.getRoleCode())) {
            throw new RuleException("BR_ROL020_TPL");
        }
      
    
        // Bussiness rule "BR_ROL029_TPL"
        if (!isBlankOrNull(vo.getItemDomain()) || !isBlankOrNull(vo.getItemValue())) {
            throw new RuleException("BR_ROL029_TPL");
        }
        // Bussiness rule "BR_ROL030_TBL"
        if (!vo.isVirtualRole()) {
            throw new RuleException("BR_ROL030_TBL");
        }
        // Bussiness rule "BR_ROL032_ATT"
        if (vo.getAdminLevel() == null) {
            throw new RuleException("BR_ROL032_ATT");
        }
        // Bussiness rule "BR_ROL039_TBL"
        if (vo.getId().equals(vo.getParentRole().getId()) ||
            isRoleChildToChilds(vo.getId(), vo.getParentRole().getId())) {
            throw new RuleException("BR_ROL039_TBL");
        }
        // Bussiness rule "BR_ROL037_TBL"
        if (!vo.isStatusActive() && isRoleHasActiveChild(vo.getId())) {
            throw new RuleException("BR_ROL037_TBL");
        }
        // Get Parent Role.
        RoleVO parentRole = getById(vo.getParentRole().getId());
        // Bussiness rule "BR_ROL038_TBL"
        if (vo.isStatusActive() && !parentRole.isStatusActive()) {
            throw new RuleException("BR_ROL038_TBL");
        }
        // perform update
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            RoleVO newVO = dao.update(vo);
            dao.commit();
            return newVO;

        } catch (DataAccessException ex) {
            rollback(dao);
            throw ex;
        } catch (BusinessException ex) {
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
     * Add Role Of Type Service.
     *
     * @return RoleVO Role VO
     * @param vo role VO
     */
    public RoleVO addService(RoleVO vo) {
        if (vo == null) {
            throw new BusinessException("Null Role Value Object");
        }
        if (isBlankOrNull(vo.getCenterName())) {
            throw new BusinessException("Null CenterName Role Value Object");
        }
        if (isBlankOrNull(vo.getCreatedBy())) {
            throw new BusinessException("Null CreatedBy Role Value Object");
        }
        if (vo.getCanBeDenied() == null) {
            throw new BusinessException("Null CanBeDenied Value.");
        }

        // Bussiness rule "BR_ROL001_ATT"
        if (isBlankOrNull(vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL001_ATT");
        }
        // Bussiness rule "BR_ROL002_TBL"
        if (isDescriptionArExists(vo.getId(), vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL002_TBL");
        }
        // Bussiness rule "BR_ROL003_ATT"
        if (isBlankOrNull(vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL003_ATT");
        }
        // Bussiness rule "BR_ROL004_TBL"
        if (isDescriptionEnExists(vo.getId(), vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL004_TBL");
        }
        // Bussiness rule "BR_ROL005_ATT"
        if (vo.getRoleType() == null || !vo.getRoleType().equals(vo.ROLE_TYPE_SERVICES)) {
            throw new RuleException("BR_ROL005_ATT");
        }
        // Bussiness rule "BR_ROL008_TPL"
        if (vo.getParentRole() == null || vo.getParentRole().getId() == null ||
            !vo.getParentRole().getRoleType().equals(vo.ROLE_TYPE_MODULE)) {
            throw new RuleException("BR_ROL008_TPL");
        }
        // Bussiness rule "BR_ROL014_ATT"
        if (vo.getStatus() == null) {
            throw new RuleException("BR_ROL014_ATT");
        }
        // Bussiness rule "BR_ROL017_TPL"
        if (!isBlankOrNull(vo.getUrl())) {
            throw new RuleException("BR_ROL017_TPL");
        }
        // Bussiness rule "BR_ROL020_TPL"
        if (!isBlankOrNull(vo.getRoleCode())) {
            throw new RuleException("BR_ROL020_TPL");
        }
        
        // Bussiness rule "BR_ROL029_TPL"
        if (!isBlankOrNull(vo.getItemDomain()) || !isBlankOrNull(vo.getItemValue())) {
            throw new RuleException("BR_ROL029_TPL");
        }
        // Bussiness rule "BR_ROL031_TPL"
        if (vo.isVirtualRole()) {
            throw new RuleException("BR_ROL031_TPL");
        }
        // Bussiness rule "BR_ROL032_ATT"
        if (vo.getAdminLevel() == null) {
            throw new RuleException("BR_ROL032_ATT");
        }
        // Get Parent Role.
        RoleVO parentRole = getById(vo.getParentRole().getId());
   
        // Bussiness rule "BR_ROL040_TPL"
        if (!vo.isStatusActive()) {
            throw new RuleException("BR_ROL040_TPL");
        }
        // Bussiness rule "BR_ROL038_TBL"
        if (vo.isStatusActive() && !parentRole.isStatusActive()) {
            throw new RuleException("BR_ROL038_TBL");
        }
        // perform add
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            RoleVO newVO = dao.add(vo);
            dao.commit();
            return newVO;

        } catch (DataAccessException ex) {
            rollback(dao);
            throw ex;
        } catch (BusinessException ex) {
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
     * Update Role Of Type Service.
     *
     * @return RoleVO Role VO
     * @param vo role VO
     */
    public RoleVO updateService(RoleVO vo) {
        if (vo == null) {
            throw new BusinessException("Null Role Value Object");
        }
        if (isBlankOrNull(vo.getCenterName())) {
            throw new BusinessException("Null CenterName Role Value Object");
        }
        if (isBlankOrNull(vo.getUpdatedBy())) {
            throw new BusinessException("Null UpdatedBy Role Value Object");
        }
        if (vo.getCanBeDenied() == null) {
            throw new BusinessException("Null CanBeDenied Value.");
        }

        // Bussiness rule "BR_ROL001_ATT"
        if (isBlankOrNull(vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL001_ATT");
        }
        // Bussiness rule "BR_ROL002_TBL"
        if (isDescriptionArExists(vo.getId(), vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL002_TBL");
        }
        // Bussiness rule "BR_ROL003_ATT"
        if (isBlankOrNull(vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL003_ATT");
        }
        // Bussiness rule "BR_ROL004_TBL"
        if (isDescriptionEnExists(vo.getId(), vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL004_TBL");
        }
        // Bussiness rule "BR_ROL005_ATT"
        if (vo.getRoleType() == null || !vo.getRoleType().equals(vo.ROLE_TYPE_SERVICES)) {
            throw new RuleException("BR_ROL005_ATT");
        }
        // Bussiness rule "BR_ROL008_TPL"
        if (vo.getParentRole() == null || vo.getParentRole().getId() == null ||
            !vo.getParentRole().getRoleType().equals(vo.ROLE_TYPE_MODULE)) {
            throw new RuleException("BR_ROL008_TPL");
        }
        // Bussiness rule "BR_ROL014_ATT"
        if (vo.getStatus() == null) {
            throw new RuleException("BR_ROL014_ATT");
        }
        // Bussiness rule "BR_ROL017_TPL"
        if (!isBlankOrNull(vo.getUrl())) {
            throw new RuleException("BR_ROL017_TPL");
        }
        // Bussiness rule "BR_ROL020_TPL"
        if (!isBlankOrNull(vo.getRoleCode())) {
            throw new RuleException("BR_ROL020_TPL");
        }
       
        // Bussiness rule "BR_ROL029_TPL"
        if (!isBlankOrNull(vo.getItemDomain()) || !isBlankOrNull(vo.getItemValue())) {
            throw new RuleException("BR_ROL029_TPL");
        }
        // Bussiness rule "BR_ROL031_TPL"
        if (vo.isVirtualRole()) {
            throw new RuleException("BR_ROL031_TPL");
        }
        // Bussiness rule "BR_ROL032_ATT"
        if (vo.getAdminLevel() == null) {
            throw new RuleException("BR_ROL032_ATT");
        }
        // Bussiness rule "BR_ROL033_TBL"
        RoleVO parentRole = getById(vo.getParentRole().getId());
       
        // Bussiness rule "BR_ROL039_TBL"
        if (vo.getId().equals(vo.getParentRole().getId()) ||
            isRoleChildToChilds(vo.getId(), vo.getParentRole().getId())) {
            throw new RuleException("BR_ROL039_TBL");
        }
        // Bussiness rule "BR_ROL037_TBL"
        if (!vo.isStatusActive() && isRoleHasActiveChild(vo.getId())) {
            throw new RuleException("BR_ROL037_TBL");
        }
        // Bussiness rule "BR_ROL038_TBL"
        if (vo.isStatusActive() && !parentRole.isStatusActive()) {
            throw new RuleException("BR_ROL038_TBL");
        }

        // perform update
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            RoleVO newVO = dao.update(vo);
            dao.commit();
            return newVO;

        } catch (DataAccessException ex) {
            rollback(dao);
            throw ex;
        } catch (BusinessException ex) {
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
     * Add Role Of Type Page.
     *
     * @return RoleVO Role VO
     * @param vo role VO
     */
    public RoleVO addPage(RoleVO vo) {
        if (vo == null) {
            throw new BusinessException("Null Role Value Object");
        }
        if (isBlankOrNull(vo.getCenterName())) {
            throw new BusinessException("Null CenterName Role Value Object");
        }
        if (isBlankOrNull(vo.getCreatedBy())) {
            throw new BusinessException("Null CreatedBy Role Value Object");
        }
        // Bussiness rule "BR_ROL001_ATT"
        if (isBlankOrNull(vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL001_ATT");
        }
        // Bussiness rule "BR_ROL002_TBL"
        if (isDescriptionArExists(vo.getId(), vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL002_TBL");
        }
        // Bussiness rule "BR_ROL003_ATT"
        if (isBlankOrNull(vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL003_ATT");
        }
        // Bussiness rule "BR_ROL004_TBL"
        if (isDescriptionEnExists(vo.getId(), vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL004_TBL");
        }
        // Bussiness rule "BR_ROL005_ATT"
        if (vo.getRoleType() == null || !vo.getRoleType().equals(vo.ROLE_TYPE_PAGE)) {
            throw new RuleException("BR_ROL005_ATT");
        }
        // Bussiness rule "BR_ROL009_TPL"
        if (vo.getParentRole() == null || vo.getParentRole().getId() == null ||
            (!vo.getParentRole().getRoleType().equals(vo.ROLE_TYPE_MODULE) &&
             !vo.getParentRole().getRoleType().equals(vo.ROLE_TYPE_SERVICES) &&
             !vo.getParentRole().getRoleType().equals(vo.ROLE_TYPE_PAGE))) {
            throw new RuleException("BR_ROL009_TPL");
        }
        // Bussiness rule "BR_ROL014_ATT"
        if (vo.getStatus() == null) {
            throw new RuleException("BR_ROL014_ATT");
        }
        // Bussiness rule "BR_ROL015_TPL"
        if (isUrlExists(vo.getId(), vo.getUrl())) {
            throw new RuleException("BR_ROL015_TPL");
        }
        // Bussiness rule "BR_ROL016_TPL"
        if (isBlankOrNull(vo.getUrl())) {
            throw new RuleException("BR_ROL016_TPL");
        }
        // Bussiness rule "BR_ROL020_TPL"
        if (!isBlankOrNull(vo.getRoleCode())) {
            throw new RuleException("BR_ROL020_TPL");
        }
        
        // Bussiness rule "BR_ROL029_TPL"
        if (!isBlankOrNull(vo.getItemDomain()) || !isBlankOrNull(vo.getItemValue())) {
            throw new RuleException("BR_ROL029_TPL");
        }
        // Bussiness rule "BR_ROL032_ATT"
        if (vo.getAdminLevel() == null) {
            throw new RuleException("BR_ROL032_ATT");
        }
        // Bussiness rule "BR_ROL040_TPL"
        if (!vo.isStatusActive()) {
            throw new RuleException("BR_ROL040_TPL");
        }

        // user should define if requires EID or not.
        if (vo.getRequireEid() == null) {
            throw new RuleException("BR_ROL041_ATT");
        }
        // Get Parent Role.
        RoleVO parentRole = getById(vo.getParentRole().getId());
        // Bussiness rule "BR_ROL038_TBL"
        if (vo.isStatusActive() && !parentRole.isStatusActive()) {
            throw new RuleException("BR_ROL038_TBL");
        }
        // perform add
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            RoleVO newVO = dao.add(vo);
            dao.commit();
            return newVO;

        } catch (DataAccessException ex) {
            rollback(dao);
            throw ex;
        } catch (BusinessException ex) {
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
     * Update Role Of Type Page.
     *
     * @return RoleVO Role VO
     * @param vo role VO
     */
    public RoleVO updatePage(RoleVO vo) {
        if (vo == null) {
            throw new BusinessException("Null Role Value Object");
        }
        if (isBlankOrNull(vo.getCenterName())) {
            throw new BusinessException("Null CenterName Role Value Object");
        }
        if (isBlankOrNull(vo.getUpdatedBy())) {
            throw new BusinessException("Null UpdatedBy Role Value Object");
        }
        // Bussiness rule "BR_ROL001_ATT"
        if (isBlankOrNull(vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL001_ATT");
        }
        // Bussiness rule "BR_ROL002_TBL"
        if (isDescriptionArExists(vo.getId(), vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL002_TBL");
        }
        // Bussiness rule "BR_ROL003_ATT"
        if (isBlankOrNull(vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL003_ATT");
        }
        // Bussiness rule "BR_ROL004_TBL"
        if (isDescriptionEnExists(vo.getId(), vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL004_TBL");
        }
        // Bussiness rule "BR_ROL005_ATT"
        if (vo.getRoleType() == null || !vo.getRoleType().equals(vo.ROLE_TYPE_PAGE)) {
            throw new RuleException("BR_ROL005_ATT");
        }
        // Bussiness rule "BR_ROL009_TPL"
        if (vo.getParentRole() == null || vo.getParentRole().getId() == null ||
            (!vo.getParentRole().getRoleType().equals(vo.ROLE_TYPE_MODULE) &&
             !vo.getParentRole().getRoleType().equals(vo.ROLE_TYPE_SERVICES) &&
             !vo.getParentRole().getRoleType().equals(vo.ROLE_TYPE_PAGE))) {
            throw new RuleException("BR_ROL009_TPL");
        }
        // Bussiness rule "BR_ROL014_ATT"
        if (vo.getStatus() == null) {
            throw new RuleException("BR_ROL014_ATT");
        }
        // Bussiness rule "BR_ROL015_TPL"
        if (isUrlExists(vo.getId(), vo.getUrl())) {
            throw new RuleException("BR_ROL015_TPL");
        }
        // Bussiness rule "BR_ROL016_TPL"
        if (isBlankOrNull(vo.getUrl())) {
            throw new RuleException("BR_ROL016_TPL");
        }
        // Bussiness rule "BR_ROL020_TPL"
        if (!isBlankOrNull(vo.getRoleCode())) {
            throw new RuleException("BR_ROL020_TPL");
        }
       
        // Bussiness rule "BR_ROL029_TPL"
        if (!isBlankOrNull(vo.getItemDomain()) || !isBlankOrNull(vo.getItemValue())) {
            throw new RuleException("BR_ROL029_TPL");
        }
        // Bussiness rule "BR_ROL032_ATT"
        if (vo.getAdminLevel() == null) {
            throw new RuleException("BR_ROL032_ATT");
        }
        // Bussiness rule "BR_ROL039_TBL"
        if (vo.getId().equals(vo.getParentRole().getId()) ||
            isRoleChildToChilds(vo.getId(), vo.getParentRole().getId())) {
            throw new RuleException("BR_ROL039_TBL");
        }
        // Bussiness rule "BR_ROL037_TBL"
        if (!vo.isStatusActive() && isRoleHasActiveChild(vo.getId())) {
            throw new RuleException("BR_ROL037_TBL");
        }
        // Get Parent Role.
        RoleVO parentRole = getById(vo.getParentRole().getId());
        // Bussiness rule "BR_ROL038_TBL"
        if (vo.isStatusActive() && !parentRole.isStatusActive()) {
            throw new RuleException("BR_ROL038_TBL");
        }

        // user should define if requires EID or not.
        if (vo.getRequireEid() == null) {
            throw new RuleException("BR_ROL041_ATT");
        }

        // perform update
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            RoleVO newVO = dao.update(vo);
            dao.commit();
            return newVO;

        } catch (DataAccessException ex) {
            rollback(dao);
            throw ex;
        } catch (BusinessException ex) {
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
     * Add Role Of Type Page Object.
     *
     * @return RoleVO Role VO
     * @param vo role VO
     */
    public RoleVO addPageObject(RoleVO vo) {
        if (vo == null) {
            throw new BusinessException("Null Role Value Object");
        }
        if (isBlankOrNull(vo.getCenterName())) {
            throw new BusinessException("Null CenterName Role Value Object");
        }
        if (isBlankOrNull(vo.getCreatedBy())) {
            throw new BusinessException("Null CreatedBy Role Value Object");
        }
        // Bussiness rule "BR_ROL001_ATT"
        if (isBlankOrNull(vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL001_ATT");
        }
        // Bussiness rule "BR_ROL002_TBL"
        if (isDescriptionArExists(vo.getId(), vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL002_TBL");
        }
        // Bussiness rule "BR_ROL003_ATT"
        if (isBlankOrNull(vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL003_ATT");
        }
        // Bussiness rule "BR_ROL004_TBL"
        if (isDescriptionEnExists(vo.getId(), vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL004_TBL");
        }
        // Bussiness rule "BR_ROL005_ATT"
        if (vo.getRoleType() == null || !vo.getRoleType().equals(vo.ROLE_TYPE_PAGE_OBJECT)) {
            throw new RuleException("BR_ROL005_ATT");
        }
        // Bussiness rule "BR_ROL010_TPL"
        if (vo.getParentRole() == null || vo.getParentRole().getId() == null ||
            !vo.getParentRole().getRoleType().equals(vo.ROLE_TYPE_PAGE)) {
            throw new RuleException("BR_ROL010_TPL");
        }
        // Bussiness rule "BR_ROL014_ATT"
        if (vo.getStatus() == null) {
            throw new RuleException("BR_ROL014_ATT");
        }
        // Bussiness rule "BR_ROL017_TPL"
        if (!isBlankOrNull(vo.getUrl())) {
            throw new RuleException("BR_ROL017_TPL");
        }
        // Bussiness rule "BR_ROL018_TPL"
        if (vo.getParentRole() == null) {
            if (isCodeExists(vo.getId(), vo.getRoleCode(), null)) {
                throw new RuleException("BR_ROL018_TPL");
            }
        } else {
            if (isCodeExists(vo.getId(), vo.getRoleCode(), vo.getParentRole().getId())) {
                throw new RuleException("BR_ROL018_TPL");
            }
        }

        // Bussiness rule "BR_ROL019_TPL"
        if (isBlankOrNull(vo.getRoleCode())) {
            throw new RuleException("BR_ROL019_TPL");
        }
        
        // Bussiness rule "BR_ROL029_TPL"
        if (!isBlankOrNull(vo.getItemDomain()) || !isBlankOrNull(vo.getItemValue())) {
            throw new RuleException("BR_ROL029_TPL");
        }
        // Bussiness rule "BR_ROL032_ATT"
        if (vo.getAdminLevel() == null) {
            throw new RuleException("BR_ROL032_ATT");
        }
        // Bussiness rule "BR_ROL040_TPL"
        if (!vo.isStatusActive()) {
            throw new RuleException("BR_ROL040_TPL");
        }
        // Get Parent Role.
        RoleVO parentRole = getById(vo.getParentRole().getId());
        // Bussiness rule "BR_ROL038_TBL"
        if (vo.isStatusActive() && !parentRole.isStatusActive()) {
            throw new RuleException("BR_ROL038_TBL");
        }

        // user should define if requires EID or not.
        if (vo.getRequireEid() == null) {
            throw new RuleException("BR_ROL041_ATT");
        }

        // perform add
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            RoleVO newVO = dao.add(vo);
            dao.commit();
            return newVO;

        } catch (DataAccessException ex) {
            rollback(dao);
            throw ex;
        } catch (BusinessException ex) {
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
     * Update Role Of Type Page Object.
     *
     * @return RoleVO Role VO
     * @param vo role VO
     */
    public RoleVO updatePageObject(RoleVO vo) {
        if (vo == null) {
            throw new BusinessException("Null Role Value Object");
        }
        if (isBlankOrNull(vo.getCenterName())) {
            throw new BusinessException("Null CenterName Role Value Object");
        }
        if (isBlankOrNull(vo.getUpdatedBy())) {
            throw new BusinessException("Null UpdatedBy Role Value Object");
        }
        // Bussiness rule "BR_ROL001_ATT"
        if (isBlankOrNull(vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL001_ATT");
        }
        // Bussiness rule "BR_ROL002_TBL"
        if (isDescriptionArExists(vo.getId(), vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL002_TBL");
        }
        // Bussiness rule "BR_ROL003_ATT"
        if (isBlankOrNull(vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL003_ATT");
        }
        // Bussiness rule "BR_ROL004_TBL"
        if (isDescriptionEnExists(vo.getId(), vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL004_TBL");
        }
        // Bussiness rule "BR_ROL005_ATT"
        if (vo.getRoleType() == null || !vo.getRoleType().equals(vo.ROLE_TYPE_PAGE_OBJECT)) {
            throw new RuleException("BR_ROL005_ATT");
        }
        // Bussiness rule "BR_ROL010_TPL"
        if (vo.getParentRole() == null || vo.getParentRole().getId() == null ||
            !vo.getParentRole().getRoleType().equals(vo.ROLE_TYPE_PAGE)) {
            throw new RuleException("BR_ROL010_TPL");
        }
        // Bussiness rule "BR_ROL014_ATT"
        if (vo.getStatus() == null) {
            throw new RuleException("BR_ROL014_ATT");
        }
        // Bussiness rule "BR_ROL017_TPL"
        if (!isBlankOrNull(vo.getUrl())) {
            throw new RuleException("BR_ROL017_TPL");
        }
        // Bussiness rule "BR_ROL018_TPL"
        if (vo.getParentRole() == null) {
            if (isCodeExists(vo.getId(), vo.getRoleCode(), null)) {
                throw new RuleException("BR_ROL018_TPL");
            }
        } else {
            if (isCodeExists(vo.getId(), vo.getRoleCode(), vo.getParentRole().getId())) {
                throw new RuleException("BR_ROL018_TPL");
            }
        }

        // Bussiness rule "BR_ROL019_TPL"
        if (isBlankOrNull(vo.getRoleCode())) {
            throw new RuleException("BR_ROL019_TPL");
        }
    
        // Bussiness rule "BR_ROL029_TPL"
        if (!isBlankOrNull(vo.getItemDomain()) || !isBlankOrNull(vo.getItemValue())) {
            throw new RuleException("BR_ROL029_TPL");
        }
        // Bussiness rule "BR_ROL032_ATT"
        if (vo.getAdminLevel() == null) {
            throw new RuleException("BR_ROL032_ATT");
        }
        // Bussiness rule "BR_ROL039_TBL"
        if (vo.getId().equals(vo.getParentRole().getId()) ||
            isRoleChildToChilds(vo.getId(), vo.getParentRole().getId())) {
            throw new RuleException("BR_ROL039_TBL");
        }
        // Bussiness rule "BR_ROL037_TBL"
        if (!vo.isStatusActive() && isRoleHasActiveChild(vo.getId())) {
            throw new RuleException("BR_ROL037_TBL");
        }
        // Get Parent Role.
        RoleVO parentRole = getById(vo.getParentRole().getId());
        // Bussiness rule "BR_ROL038_TBL"
        if (vo.isStatusActive() && !parentRole.isStatusActive()) {
            throw new RuleException("BR_ROL038_TBL");
        }

        // user should define if requires EID or not.
        if (vo.getRequireEid() == null) {
            throw new RuleException("BR_ROL041_ATT");
        }

        // perform update
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            RoleVO newVO = dao.update(vo);
            dao.commit();
            return newVO;

        } catch (DataAccessException ex) {
            rollback(dao);
            throw ex;
        } catch (BusinessException ex) {
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
     * Add Role Of Type Object Item.
     *
     * @return RoleVO Role VO
     * @param vo role VO
     */
    public RoleVO addObjectItem(RoleVO vo) {
        if (vo == null) {
            throw new BusinessException("Null Role Value Object");
        }
        if (isBlankOrNull(vo.getCenterName())) {
            throw new BusinessException("Null CenterName Role Value Object");
        }
        if (isBlankOrNull(vo.getCreatedBy())) {
            throw new BusinessException("Null CreatedBy Role Value Object");
        }
        // Bussiness rule "BR_ROL001_ATT"
        if (isBlankOrNull(vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL001_ATT");
        }
        // Bussiness rule "BR_ROL002_TBL"
        if (isDescriptionArExists(vo.getId(), vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL002_TBL");
        }
        // Bussiness rule "BR_ROL003_ATT"
        if (isBlankOrNull(vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL003_ATT");
        }
        // Bussiness rule "BR_ROL004_TBL"
        if (isDescriptionEnExists(vo.getId(), vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL004_TBL");
        }
        // Bussiness rule "BR_ROL005_ATT"
        if (vo.getRoleType() == null || !vo.getRoleType().equals(vo.ROLE_TYPE_OBJECT_ITEM)) {
            throw new RuleException("BR_ROL005_ATT");
        }
        // Bussiness rule "BR_ROL011_TPL"
        if (vo.getParentRole() == null || vo.getParentRole().getId() == null ||
            !vo.getParentRole().getRoleType().equals(vo.ROLE_TYPE_PAGE_OBJECT)) {
            throw new RuleException("BR_ROL011_TPL");
        }
        // Bussiness rule "BR_ROL014_ATT"
        if (vo.getStatus() == null) {
            throw new RuleException("BR_ROL014_ATT");
        }
        // Bussiness rule "BR_ROL017_TPL"
        if (!isBlankOrNull(vo.getUrl())) {
            throw new RuleException("BR_ROL017_TPL");
        }
        // Bussiness rule "BR_ROL018_TPL"
        if (vo.getParentRole() == null) {
            if (isCodeExists(vo.getId(), vo.getRoleCode(), null)) {
                throw new RuleException("BR_ROL018_TPL");
            }
        } else {
            if (isCodeExists(vo.getId(), vo.getRoleCode(), vo.getParentRole().getId())) {
                throw new RuleException("BR_ROL018_TPL");
            }
        }

        // Bussiness rule "BR_ROL019_TPL"
        if (isBlankOrNull(vo.getRoleCode())) {
            throw new RuleException("BR_ROL019_TPL");
        }
        
        // Bussiness rule "BR_ROL027_TPL"
        if (isItemDomainOrValueExists(vo.getId(), vo.getItemDomain(), vo.getItemValue(),
                                      (vo.getParentRole() == null) ? null : vo.getParentRole().getId())) {
            throw new RuleException("BR_ROL027_TPL");
        }
        // Bussiness rule "BR_ROL028_TPL"
        if (isBlankOrNull(vo.getItemDomain()) || isBlankOrNull(vo.getItemValue())) {
            throw new RuleException("BR_ROL028_TPL");
        }
        // Bussiness rule "BR_ROL031_TPL"
        if (vo.isVirtualRole()) {
            throw new RuleException("BR_ROL031_TPL");
        }
        // Bussiness rule "BR_ROL032_ATT"
        if (vo.getAdminLevel() == null) {
            throw new RuleException("BR_ROL032_ATT");
        }
        // Bussiness rule "BR_ROL040_TPL"
        if (!vo.isStatusActive()) {
            throw new RuleException("BR_ROL040_TPL");
        }
        // Get Parent Role.
        RoleVO parentRole = getById(vo.getParentRole().getId());
        // Bussiness rule "BR_ROL038_TBL"
        if (vo.isStatusActive() && !parentRole.isStatusActive()) {
            throw new RuleException("BR_ROL038_TBL");
        }

        // user should define if requires EID or not.
        if (vo.getRequireEid() == null) {
            throw new RuleException("BR_ROL041_ATT");
        }

        // perform add
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            RoleVO newVO = dao.add(vo);
            dao.commit();
            return newVO;

        } catch (DataAccessException ex) {
            rollback(dao);
            throw ex;
        } catch (BusinessException ex) {
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
     * Update Role Of Type Object Item.
     *
     * @return RoleVO Role VO
     * @param vo role VO
     */
    public RoleVO updateObjectItem(RoleVO vo) {
        if (vo == null) {
            throw new BusinessException("Null Role Value Object");
        }
        if (isBlankOrNull(vo.getCenterName())) {
            throw new BusinessException("Null CenterName Role Value Object");
        }
        if (isBlankOrNull(vo.getUpdatedBy())) {
            throw new BusinessException("Null UpdatedBy Role Value Object");
        }
        // Bussiness rule "BR_ROL001_ATT"
        if (isBlankOrNull(vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL001_ATT");
        }
        // Bussiness rule "BR_ROL002_TBL"
        if (isDescriptionArExists(vo.getId(), vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL002_TBL");
        }
        // Bussiness rule "BR_ROL003_ATT"
        if (isBlankOrNull(vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL003_ATT");
        }
        // Bussiness rule "BR_ROL004_TBL"
        if (isDescriptionEnExists(vo.getId(), vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL004_TBL");
        }
        // Bussiness rule "BR_ROL005_ATT"
        if (vo.getRoleType() == null || !vo.getRoleType().equals(vo.ROLE_TYPE_OBJECT_ITEM)) {
            throw new RuleException("BR_ROL005_ATT");
        }
        // Bussiness rule "BR_ROL011_TPL"
        if (vo.getParentRole() == null || vo.getParentRole().getId() == null ||
            !vo.getParentRole().getRoleType().equals(vo.ROLE_TYPE_PAGE_OBJECT)) {
            throw new RuleException("BR_ROL011_TPL");
        }
        // Bussiness rule "BR_ROL014_ATT"
        if (vo.getStatus() == null) {
            throw new RuleException("BR_ROL014_ATT");
        }
        // Bussiness rule "BR_ROL017_TPL"
        if (!isBlankOrNull(vo.getUrl())) {
            throw new RuleException("BR_ROL017_TPL");
        }
        // Bussiness rule "BR_ROL018_TPL"
        if (vo.getParentRole() == null) {
            if (isCodeExists(vo.getId(), vo.getRoleCode(), null)) {
                throw new RuleException("BR_ROL018_TPL");
            }
        } else {
            if (isCodeExists(vo.getId(), vo.getRoleCode(), vo.getParentRole().getId())) {
                throw new RuleException("BR_ROL018_TPL");
            }
        }

        // Bussiness rule "BR_ROL019_TPL"
        if (isBlankOrNull(vo.getRoleCode())) {
            throw new RuleException("BR_ROL019_TPL");
        }
       
        // Bussiness rule "BR_ROL027_TPL"
        if (isItemDomainOrValueExists(vo.getId(), vo.getItemDomain(), vo.getItemValue(),
                                      (vo.getParentRole() == null) ? null : vo.getParentRole().getId())) {
            throw new RuleException("BR_ROL027_TPL");
        }
        // Bussiness rule "BR_ROL028_TPL"
        if (isBlankOrNull(vo.getItemDomain()) || isBlankOrNull(vo.getItemValue())) {
            throw new RuleException("BR_ROL028_TPL");
        }
        // Bussiness rule "BR_ROL031_TPL"
        if (vo.isVirtualRole()) {
            throw new RuleException("BR_ROL031_TPL");
        }
        // Bussiness rule "BR_ROL032_ATT"
        if (vo.getAdminLevel() == null) {
            throw new RuleException("BR_ROL032_ATT");
        }
        // Bussiness rule "BR_ROL039_TBL"
        if (vo.getId().equals(vo.getParentRole().getId()) ||
            isRoleChildToChilds(vo.getId(), vo.getParentRole().getId())) {
            throw new RuleException("BR_ROL039_TBL");
        }
        // Bussiness rule "BR_ROL037_TBL"
        if (!vo.isStatusActive() && isRoleHasActiveChild(vo.getId())) {
            throw new RuleException("BR_ROL037_TBL");
        }
        // Get Parent Role.
        RoleVO parentRole = getById(vo.getParentRole().getId());
        // Bussiness rule "BR_ROL038_TBL"
        if (vo.isStatusActive() && !parentRole.isStatusActive()) {
            throw new RuleException("BR_ROL038_TBL");
        }

        // user should define if requires EID or not.
        if (vo.getRequireEid() == null) {
            throw new RuleException("BR_ROL041_ATT");
        }

        // perform update
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            RoleVO newVO = dao.update(vo);
            dao.commit();
            return newVO;

        } catch (DataAccessException ex) {
            rollback(dao);
            throw ex;
        } catch (BusinessException ex) {
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
     * Add Role Of Type Custom Role.
     *
     * @return RoleVO Role VO
     * @param vo role VO
     */
    public RoleVO addCustom(RoleVO vo) {
        if (vo == null) {
            throw new BusinessException("Null Role Value Object");
        }
        if (isBlankOrNull(vo.getCenterName())) {
            throw new BusinessException("Null CenterName Role Value Object");
        }
        if (isBlankOrNull(vo.getCreatedBy())) {
            throw new BusinessException("Null CreatedBy Role Value Object");
        }
        // Bussiness rule "BR_ROL001_ATT"
        if (isBlankOrNull(vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL001_ATT");
        }
        // Bussiness rule "BR_ROL002_TBL"
        if (isDescriptionArExists(vo.getId(), vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL002_TBL");
        }
        // Bussiness rule "BR_ROL003_ATT"
        if (isBlankOrNull(vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL003_ATT");
        }
        // Bussiness rule "BR_ROL004_TBL"
        if (isDescriptionEnExists(vo.getId(), vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL004_TBL");
        }
        // Bussiness rule "BR_ROL005_ATT"
        if (vo.getRoleType() == null || !vo.getRoleType().equals(vo.ROLE_TYPE_CUSTOM_ROLE)) {
            throw new RuleException("BR_ROL005_ATT");
        }
        // Bussiness rule "BR_ROL011_TPL"
        if (vo.getParentRole() == null || vo.getParentRole().getId() == null ||
            !vo.getParentRole().getRoleType().equals(vo.ROLE_TYPE_MODULE)) {
            throw new RuleException("BR_ROL011_TPL");
        }
        // Bussiness rule "BR_ROL014_ATT"
        if (vo.getStatus() == null) {
            throw new RuleException("BR_ROL014_ATT");
        }
        // Bussiness rule "BR_ROL017_TPL"
        if (!isBlankOrNull(vo.getUrl())) {
            throw new RuleException("BR_ROL017_TPL");
        }
        // Bussiness rule "BR_ROL018_TPL"
        if (vo.getParentRole() == null) {
            if (isCodeExists(vo.getId(), vo.getRoleCode(), null)) {
                throw new RuleException("BR_ROL018_TPL");
            }
        } else {
            if (isCodeExists(vo.getId(), vo.getRoleCode(), vo.getParentRole().getId())) {
                throw new RuleException("BR_ROL018_TPL");
            }
        }
        // Bussiness rule "BR_ROL019_TPL"
        if (isBlankOrNull(vo.getRoleCode())) {
            throw new RuleException("BR_ROL019_TPL");
        }
        
        // Bussiness rule "BR_ROL029_TPL"
        if (!isBlankOrNull(vo.getItemDomain()) || !isBlankOrNull(vo.getItemValue())) {
            throw new RuleException("BR_ROL029_TPL");
        }
        // Bussiness rule "BR_ROL031_TPL"
        if (vo.isVirtualRole()) {
            throw new RuleException("BR_ROL031_TPL");
        }
        // Bussiness rule "BR_ROL032_ATT"
        if (vo.getAdminLevel() == null) {
            throw new RuleException("BR_ROL032_ATT");
        }
        // Bussiness rule "BR_ROL040_TPL"
        if (!vo.isStatusActive()) {
            throw new RuleException("BR_ROL040_TPL");
        }
        // Get Parent Role.
        RoleVO parentRole = getById(vo.getParentRole().getId());
        // Bussiness rule "BR_ROL038_TBL"
        if (vo.isStatusActive() && !parentRole.isStatusActive()) {
            throw new RuleException("BR_ROL038_TBL");
        }

        // user should define if requires EID or not.
        if (vo.getRequireEid() == null) {
            throw new RuleException("BR_ROL041_ATT");
        }

        // perform add
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            RoleVO newVO = dao.add(vo);
            dao.commit();
            return newVO;

        } catch (DataAccessException ex) {
            rollback(dao);
            throw ex;
        } catch (BusinessException ex) {
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
     * Update Role Of Type Custom Role.
     *
     * @return RoleVO Role VO
     * @param vo role VO
     */
    public RoleVO updateCustom(RoleVO vo) {
        if (vo == null) {
            throw new BusinessException("Null Role Value Object");
        }
        if (isBlankOrNull(vo.getCenterName())) {
            throw new BusinessException("Null CenterName Role Value Object");
        }
        if (isBlankOrNull(vo.getUpdatedBy())) {
            throw new BusinessException("Null UpdatedBy Role Value Object");
        }
        // Bussiness rule "BR_ROL001_ATT"
        if (isBlankOrNull(vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL001_ATT");
        }
        // Bussiness rule "BR_ROL002_TBL"
        if (isDescriptionArExists(vo.getId(), vo.getDescriptionAr())) {
            throw new RuleException("BR_ROL002_TBL");
        }
        // Bussiness rule "BR_ROL003_ATT"
        if (isBlankOrNull(vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL003_ATT");
        }
        // Bussiness rule "BR_ROL004_TBL"
        if (isDescriptionEnExists(vo.getId(), vo.getDescriptionEn())) {
            throw new RuleException("BR_ROL004_TBL");
        }
        // Bussiness rule "BR_ROL005_ATT"
        if (vo.getRoleType() == null || !vo.getRoleType().equals(vo.ROLE_TYPE_CUSTOM_ROLE)) {
            throw new RuleException("BR_ROL005_ATT");
        }
        // Bussiness rule "BR_ROL011_TPL"
        if (vo.getParentRole() == null || vo.getParentRole().getId() == null ||
            !vo.getParentRole().getRoleType().equals(vo.ROLE_TYPE_MODULE)) {
            throw new RuleException("BR_ROL011_TPL");
        }
        // Bussiness rule "BR_ROL014_ATT"
        if (vo.getStatus() == null) {
            throw new RuleException("BR_ROL014_ATT");
        }
        // Bussiness rule "BR_ROL017_TPL"
        if (!isBlankOrNull(vo.getUrl())) {
            throw new RuleException("BR_ROL017_TPL");
        }
        // Bussiness rule "BR_ROL018_TPL"
        if (vo.getParentRole() == null) {
            if (isCodeExists(vo.getId(), vo.getRoleCode(), null)) {
                throw new RuleException("BR_ROL018_TPL");
            }
        } else {
            if (isCodeExists(vo.getId(), vo.getRoleCode(), vo.getParentRole().getId())) {
                throw new RuleException("BR_ROL018_TPL");
            }
        }
        // Bussiness rule "BR_ROL019_TPL"
        if (isBlankOrNull(vo.getRoleCode())) {
            throw new RuleException("BR_ROL019_TPL");
        }
       
        // Bussiness rule "BR_ROL029_TPL"
        if (!isBlankOrNull(vo.getItemDomain()) || !isBlankOrNull(vo.getItemValue())) {
            throw new RuleException("BR_ROL029_TPL");
        }
        // Bussiness rule "BR_ROL031_TPL"
        if (vo.isVirtualRole()) {
            throw new RuleException("BR_ROL031_TPL");
        }
        // Bussiness rule "BR_ROL032_ATT"
        if (vo.getAdminLevel() == null) {
            throw new RuleException("BR_ROL032_ATT");
        }
        // Bussiness rule "BR_ROL039_TBL"
        if (vo.getId().equals(vo.getParentRole().getId()) ||
            isRoleChildToChilds(vo.getId(), vo.getParentRole().getId())) {
            throw new RuleException("BR_ROL039_TBL");
        }
        // Bussiness rule "BR_ROL037_TBL"
        if (!vo.isStatusActive() && isRoleHasActiveChild(vo.getId())) {
            throw new RuleException("BR_ROL037_TBL");
        }
        // Get Parent Role.
        RoleVO parentRole = getById(vo.getParentRole().getId());
        // Bussiness rule "BR_ROL038_TBL"
        if (vo.isStatusActive() && !parentRole.isStatusActive()) {
            throw new RuleException("BR_ROL038_TBL");
        }

        // user should define if requires EID or not.
        if (vo.getRequireEid() == null) {
            throw new RuleException("BR_ROL041_ATT");
        }

        // perform update
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            RoleVO newVO = dao.update(vo);
            dao.commit();
            return newVO;

        } catch (DataAccessException ex) {
            rollback(dao);
            throw ex;
        } catch (BusinessException ex) {
            rollback(dao);
            throw ex;
        } catch (Exception ex) {
            rollback(dao);
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /** Check if role arabic description is already exists.
     *
     * @return true if arabic description is already exists, otherwise false.
     * @param roleId role ID
     * @param descAr role arabic description
     */
    private boolean isDescriptionArExists(Long roleId, String descAr) {
        if (isBlankOrNull(descAr)) {
            throw new BusinessException("Null Or Empty Role Arabic Name Value.");
        }
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.isDescriptionArExists(roleId, descAr);
        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /** Check if role english description is already exists.
     *
     * @return true if english description is already exists, otherwise false.
     * @param roleId role ID
     * @param descAr role english description
     */
    private boolean isDescriptionEnExists(Long roleId, String descEn) {
        if (isBlankOrNull(descEn)) {
            throw new BusinessException("Null Or Empty Role English Name Value.");
        }
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.isDescriptionEnExists(roleId, descEn);
        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /** Check if role System Code is already exists.
     *
     * @return true if role System Code is already exists, otherwise false.
     * @param roleId role ID
     * @param code system code
     */
    private boolean isSystemCodeExists(Long roleId, String code) {
        if (isBlankOrNull(code)) {
            throw new BusinessException("Null System Code Value.");
        }
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.isSystemCodeExists(roleId, code);
        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /** Check if role Service Code is already exists.
     *
     * @return true if role Service Code is already exists, otherwise false.
     * @param roleId role ID
     * @param code service code
     */
    private boolean isServiceCodeExists(Long roleId, Integer code) {
        if (code == null) {
            throw new BusinessException("Null Service Code Value.");
        }
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.isServiceCodeExists(roleId, code);
        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /** Check if role page URL is already exists.
     *
     * @return true if role page URL is already exists, otherwise false.
     * @param roleId role ID
     * @param url page URL
     */
    private boolean isUrlExists(Long roleId, String url) {
        if (isBlankOrNull(url)) {
            throw new BusinessException("Null Page URL Value.");
        }
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.isUrlExists(roleId, url);
        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /** Check if role code is already exists.
     *
     * @return true if role code is already exists, otherwise false.
     * @param roleId role ID
     * @param code Role Code
     */
    private boolean isCodeExists(Long roleId, String code, Long parentRole) {
        if (isBlankOrNull(code)) {
            throw new BusinessException("Null Role Code Value.");
        }
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.isCodeExists(roleId, code, parentRole);
        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /** Check if Item Domain or Value is already exists.
     *
     * @return true if Item Domain or Value is already exists, otherwise false.
     * @param roleId role ID
     * @param itemDomain Item Domain
     * @param itemValue Item Value
     * @param parentRoleId : Parent Role Id
     */
    private boolean isItemDomainOrValueExists(Long roleId, String itemDomain, String itemValue, Long parentRoleId) {
        if (isBlankOrNull(itemDomain)) {
            throw new BusinessException("Null Item Domain Value.");
        }
        if (isBlankOrNull(itemValue)) {
            throw new BusinessException("Null Item Value Value.");
        }
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.isItemDomainOrValueExists(roleId, itemDomain, itemValue, parentRoleId);
        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /**
     * Delete Role.
     *
     * @param vo Role VO
     */
    public void delete(RoleVO vo) {
        // perform delete
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            delete(vo, dao);
            dao.commit();

        } catch (DataAccessException ex) {
            rollback(dao);
            throw ex;
        } catch (BusinessException ex) {
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
     * Delete Role.
     *
     * @param vo Role VO
     * @param callerDAO Inhereted DAO Object.
     */
    private void delete(RoleVO vo, RoleDAO callerDAO) {
        if (vo == null) {
            throw new BusinessException("Null Role Value Object.");
        }
        if (isBlankOrNull(vo.getCenterName())) {
            throw new BusinessException("Null Role CenterName.");
        }
        if (isBlankOrNull(vo.getUpdatedBy())) {
            throw new BusinessException("Null Role UpdatedBy.");
        }
        // Bussiness rule "BR_ROL035_MER"
        if (isRoleRelatedToGroup(vo.getId())) {
            throw new RuleException("BR_ROL035_MER");
        }
        // Bussiness rule "BR_ROL036_MER"
        if (isRoleAssignedToUser(vo.getId())) {
            throw new RuleException("BR_ROL036_MER");
        }
        List listOfChildRoles = getChildRoles(vo.getId());
        if (listOfChildRoles != null && listOfChildRoles.size() > 0) {
            for (int i = 0; i < listOfChildRoles.size(); i++) {
                RoleVO childeRole = (RoleVO) listOfChildRoles.get(i);
                childeRole.setCenterName(vo.getCenterName());
                childeRole.setUpdatedBy(vo.getUpdatedBy());
                delete(childeRole, callerDAO);
            }
        }
        callerDAO.delete(vo);
    }

    /**
     * Check if role is a child to one of its childs.
     *
     * @return true if if role is a child to one of its childs.
     * @param roleId role ID
     * @param parentRoleId parent role ID
     */
    public boolean isRoleChildToChilds(Long roleId, Long parentRoleId) {
        if (roleId == null) {
            throw new BusinessException("Null Role ID Value");
        }
        if (parentRoleId == null) {
            throw new BusinessException("Null Parent Role ID Value");
        }
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.isRoleChildToChilds(roleId, parentRoleId);
        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /**
     * Check if role path is already exists.
     *
     * @return true if path is already exists, otherwise false.
     * @param roleId role ID
     * @param path role path
     */
    public boolean isPathExists(Long roleId, String path) {
        if (isBlankOrNull(path)) {
            throw new BusinessException("Null Role Path Value.");
        }
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.isPathExists(roleId, path);
        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /**
     * Check if role is connected to a child role.
     *
     * @return true if role is connected to a child role, otherwise false.
     * @param roleId role ID
     */
    public boolean isRoleHasChild(Long roleId) {
        if (roleId == null) {
            throw new BusinessException("Null Role ID Value.");
        }
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.isRoleHasChild(roleId);
        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /**
     * Check if role is connected to a group.
     *
     * @return true if role is connected to a group, otherwise false.
     * @param roleId role ID
     */
    public boolean isRoleRelatedToGroup(Long roleId) {
        if (roleId == null) {
            throw new BusinessException("Null Role ID Value.");
        }
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.isRoleRelatedToGroup(roleId);
        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /**
     * Check if role is assigned to a user.
     *
     * @return true if role is assigned to a user, otherwise false.
     * @param roleId role ID
     */
    public boolean isRoleAssignedToUser(Long roleId) {
        if (roleId == null) {
            throw new BusinessException("Null Role ID Value.");
        }
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.isRoleAssignedToUser(roleId);
        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /**
     * Check if role has active childs.
     *
     * @return true if role has active childs, otherwise false.
     * @param roleId role ID
     */
    public boolean isRoleHasActiveChild(Long roleId) {
        if (roleId == null) {
            throw new BusinessException("Null Role ID Value.");
        }
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.isRoleHasActiveChild(roleId);
        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /**
     * Find Roles By Group ID.
     *
     * @return Search Page Value Object
     * @param pageNo pagination page Number
     * @param groupId group ID
     */
    public SearchPageVO findByGroupId(int pageNo, Long groupId) {
        if (groupId == null) {
            throw new BusinessException("Null Role Group ID Value.");
        }
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.findByGroupId(pageNo, groupId);

        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /**
     * Update Role Status.
     *
     * @return Role VO
     * @param roleVO role VO
     */
    public RoleVO updateStatus(RoleVO vo) {
        if (vo == null) {
            throw new BusinessException("Null Role Value Object");
        }
        if (isBlankOrNull(vo.getCenterName())) {
            throw new BusinessException("Null CenterName Value.");
        }
        // Bussiness rule "BR_ROL014_ATT"
        if (vo.getStatus() == null) {
            throw new RuleException("BR_ROL014_ATT");
        }

        // perform update
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            RoleVO newVO = dao.updateStatus(vo);
            dao.commit();
            return newVO;

        } catch (DataAccessException ex) {
            rollback(dao);
            throw ex;
        } catch (BusinessException ex) {
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
     * Get Role by URL.
     *
     * @param url Page-Role URL.
     * @return User Role Info.
     */
    public RoleVO getPrivatePage(String url) {

        // Validate Parameters.
        if (isBlankOrNull(url)) {
            throw new BusinessException("Invalid Page URL.");
        }

        // Perform Operation.
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.getPrivatePage(url);

        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /**
     * Is Role Exist
     *
     * @param url Page URL
     * @return True if page url has Role
     */
    public boolean isRoleExist(String url) {
        // Validation
        if (isBlankOrNull(url)) {
            throw new BusinessException("Invalid Page URL.");
        }

        RoleDAO dao = null;
        try {

            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.isRoleExist(url);

        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /**
     * Get Sensitive Childrens Of Role.
     *
     * @param roleId : role Id.
     * @param userId : user Id.
     *
     * @return List of Childrens Roles.
     */
    public List getSensitiveChildrensOfRole(Long roleId, Long userId) {
        // Validate Parameters.
        if (roleId == null) {
            throw new BusinessException("Null role Id.");
        }

        // Perform Operation.
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.getSensitiveChildrensOfRole(roleId, userId);
        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /**
     * Get Childrens Of Role.
     *
     * @param pageUrl : page Url.
     *
     * @return List of Childrens Roles.
     */
    public List getChildrensOfRole(String pageUrl, Long userId, Integer objSeq) {
        // Validate Parameters.
        if (isBlankOrNull(pageUrl)) {
            throw new BusinessException("Null page URL.");
        }

        if (userId == null) {
            throw new BusinessException("Null user Id.");
        }

        // Perform Operation.
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.getChildrensOfRole(pageUrl, userId, objSeq);
        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /**
     * Search
     *
     * @param roleCode : Role Code.
     *
     * @return List of Role VO
     */
    public List<RoleVO> search(String roleCode, String roleDesc) {
        // Validate Parameters.

        // Perform Operation.
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.search(roleCode, roleDesc);
        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }

    }

    /**
     * Get all roles that can be denied access to temporarily.
     *
     * @param roleId if null then fetch all records.
     * @return List of can be denied roles.
     */
    public List<RoleVO> getCanBeDeniedRoles(Long roleId) {
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.getCanBeDeniedRoles(roleId);

        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /**
     * lookup for roles based on query search
     * @param query : text to search
     * @param lang : language for logged-in user
     * @param paramsMap : extra where condition Parameters Map
     * @return list of roles matches this query
     */
    public List<RoleVO> lookup(String query, String lang, int pageSize, HashMap<String,String> paramsMap) {
        // Validate Parameters.
        if (query == null) {
            throw new BusinessException("Null Role Lookup.");
        }
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.lookup(query, lang, pageSize, paramsMap);
        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /**
     *  validate (id, text) for role recived from lookup componant
     * @param id : id of the role
     * @param text : role description based on language
     * @param lang : language for loogged-in user
     * @return true if data is valid
     */
 
    public boolean validateLookupValue(String id, String text, String lang) {
        // Validate Parameters.
        if (id == null || text == null) {
            throw new BusinessException("Null Role lookup result");
        }

        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.validateLookup(id, text, lang);
        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /**
     * Get role info related to this newURL
     * 
     * @param newURL New-URL related to the requested role
     * @return role info related to this newURL
     */
    public RoleVO getByNewURL(String newURL) {
        // Validate Parameters.
        if (isBlankOrNull(newURL)) {
            throw new BusinessException("Null newURL parameter");
        }

        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.getByNewURL(newURL);

        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

    /**
     * Get URLs related to pages migrated from old traffic system with custom security logic inside the old pages.
     * This Security-Mode will be used by security filter to apply the custome security logic on such URLs for backword
     * compatability
     * 
     * @return Map<URL, SecurityMode>
     */
    public Map<String, Integer> getSecurityModeURLs() {
        RoleDAO dao = null;
        try {
            dao = (RoleDAO) getDAO(RoleDAO.class);
            return dao.getSecurityModeURLs();

        } catch (DataAccessException ex) {
            throw ex;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        } finally {
            close(dao);
        }
    }

}
