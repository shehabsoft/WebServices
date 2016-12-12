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
//package ae.rta.security.dao.jdbc;
package com.dataObject;



import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.HomeBudget.DAO.JPA.DataAccessObject1;

/**
 * Security data access object implementation class. This DAO defines all data
 * access methods for security role.
 *
 * @author Alaa Salem
 * @version 1.00
 */
public class RoleDAOImpl extends JdbcDataAccessObject implements RoleDAO {

    /*
     * JDBC SQL statements.
     */

    /** Get Role Query */
    private static final String GET_ROLE_BY_ID_QUERY =
        " SELECT ROL.ID AS ROLE_ID," + " ROL.DESCRIPTION_A AS ROLE_NAME_A," + " ROL.DESCRIPTION_E AS ROLE_NAME_E," +
        " ROL.SYS_CODE AS SYSTEM_CODE," + " SYS.DESCRIPTION_A AS SYSTEM_NAME_A," + " ROL.ADMIN_LEVEL AS ADMIN_LEVEL," +
        " PKG_DB_MIL_CORE_TOOLS.F_DB_GET_REF_CODE_DESC(" + " 'TF_ADMIN_LEVEL',ROL.ADMIN_LEVEL) AS ADMIN_LEVEL_DESC," +
        " ROL.ROL_ID AS PARENT_ROLE_ID," + " PARENRT_ROL.DESCRIPTION_A AS PARENT_ROLE_NAME_A," +
        " PARENRT_ROL.PATH AS PARENT_ROLE_PATH," + " PARENRT_ROL.ROLE_TYPE AS PARENT_ROLE_TYPE," +
        " PARENRT_ROL.SYS_CODE AS PARENT_ROLE_SYSTEM_CODE," + " ROL.STATUS AS ROLE_STATUS," +
        " PKG_DB_MIL_CORE_TOOLS.F_DB_GET_REF_CODE_DESC(" + " 'TF_STATUS',ROL.STATUS) AS ROLE_STATUS_DESC," +
        " ROL.REMARKS AS ROLE_REMARKS," + " ROL.PATH AS ROLE_PATH," + " ROL.SVC_CODE AS SERVICE_CODE," +
        " SVC.DESCRIPTION_A AS SERVICE_NAME_A," + " ROL.URL AS PAGE_URL," + " ROL.IS_VIRTUAL AS VIRTUAL_ROLE," +
        " ROL.ROLE_CODE AS ROLE_CODE," + " ROL.ITEM_DOMAIN AS ITEM_DOMAIN," + " ROL.REQUIRE_EID AS REQUIRE_EID," +
        " ROL.ITEM_VALUE AS ITEM_VALUE," + " ROL.CAN_BE_DENIED AS CAN_BE_DENIED," +"ROL.SECURITY_MODE AS SECURITY_MODE"+ " FROM   IS_SEC_ROLES ROL," +
        " IS_SEC_ROLES PARENRT_ROL," + " TF_STP_SYSTEMS SYS," + " TF_STP_SERVICES SVC" +
        " WHERE  ROL.ROL_ID = PARENRT_ROL.ID(+)" + " AND ROL.SYS_CODE = SYS.CODE(+)" +
        " AND ROL.SVC_CODE = SVC.CODE(+)" + " AND ROL.ID = ?";

    /** Find Role Query */
    private static final String FIND_ROLE_QUERY =
        " SELECT ROL.ID ROLE_ID," + " ROL.DESCRIPTION_A ROLE_NAME_A," + " ROL.ROLE_TYPE ROLE_TYPE," +
        " PKG_DB_MIL_CORE_TOOLS.F_DB_GET_REF_CODE_DESC(" + " 'TF_ROLE_TYPE',ROL.ROLE_TYPE) ROLE_TYPE_DESC," +
        " ROL.STATUS ROLE_STATUS," + " PKG_DB_MIL_CORE_TOOLS.F_DB_GET_REF_CODE_DESC(" +
        " 'TF_STATUS',ROL.STATUS) ROLE_STATUS_DESC," + " ROL.STATUS_DATE ROLE_STATUS_DATE," + " ROL.PATH ROLE_PATH," +
        " ROL.ROL_ID AS PARENT_ROLE_ID," + " PARENRT_ROL.DESCRIPTION_A AS PARENT_ROLE_NAME_A," +
        " PARENRT_ROL.PATH AS PARENT_ROLE_PATH," + " PARENRT_ROL.ROLE_TYPE AS PARENT_ROLE_TYPE" +
        " FROM   IS_SEC_ROLES ROL," + " IS_SEC_ROLES PARENRT_ROL," + " TF_STP_SYSTEMS SYS," + " TF_STP_SERVICES SVC" +
        " WHERE  ROL.ROL_ID = PARENRT_ROL.ID(+)" + "    AND ROL.SYS_CODE = SYS.CODE(+)" +
        "    AND ROL.SVC_CODE = SVC.CODE(+)";

    /** Add Role Query */
    private static final String ADD_ROLE_QUERY =
        " INSERT INTO IS_SEC_ROLES" + " (ID, PATH, DESCRIPTION_A, DESCRIPTION_E," +
        " ADMIN_LEVEL, ROL_ID, STATUS_DATE, STATUS," + " REMARKS, CREATED_BY, CREATION_DATE, UPDATE_DATE," +
        " UPDATED_BY, IS_VIRTUAL, ROLE_TYPE, SYS_CODE, SVC_CODE, URL," +
        " ROLE_CODE, ITEM_DOMAIN, ITEM_VALUE,REQUIRE_EID,CAN_BE_DENIED,SECURITY_MODE)" +
        " VALUES(?,?,?,?,?,?,SYSDATE,?,?,?,SYSDATE,SYSDATE,?,?,?,?,?,?,?,?,?,?,?,?) ";

    //STATUS_DATE	7		N	DATE		No		

    /** Update role query. */
    private static final String UPDATE_ROLE_QUERY =
        " UPDATE IS_SEC_ROLES SET" + " STATUS = ?," + " STATUS_DATE = DECODE(STATUS, ?, STATUS_DATE, SYSDATE)," +
        " PATH = ?," + " DESCRIPTION_A = ?," + " DESCRIPTION_E = ?," + " ADMIN_LEVEL = ?," + " ROL_ID = ?," +
        " REMARKS = ?," + " UPDATE_DATE = SYSDATE," + " UPDATED_BY = ?," + " IS_VIRTUAL = ?," + " ROLE_TYPE	= ?," +
        " SYS_CODE = ?," + " SVC_CODE = ?," + " URL = ?," + " ROLE_CODE = ?," + " ITEM_DOMAIN = ?," +
        " ITEM_VALUE = ?," + " REQUIRE_EID = ?," + " CAN_BE_DENIED = ? ," + "SECURITY_MODE=?"+ " WHERE ID = ?";

    /** Add Role LOG Query */
    private static final String ADD_ROLE_LOG_QUERY =
        " INSERT INTO IS_SEC_ROLE_LOGS" + " (ID, PATH, DESCRIPTION_A, DESCRIPTION_E, ADMIN_LEVEL, ROL_ID," +
        " STATUS_DATE, STATUS, REMARKS, CREATED_BY, CREATION_DATE," +
        " EMPLOYEE_NAME, CENTER_NAME, ACTION, ROL_ROL_ID, IS_VIRTUAL," +
        " ROLE_TYPE, SYS_CODE, SVC_CODE, URL, ROLE_CODE, ITEM_DOMAIN," + " ITEM_VALUE)" + " SELECT ROLL_SEQ.NEXTVAL," +
        "        PATH," + "        DESCRIPTION_A," + "        DESCRIPTION_E," + "        ADMIN_LEVEL," + "        ID," +
        "        STATUS_DATE," + "        STATUS," + "        REMARKS," + "        NVL(?, UPDATED_BY) UPDATED_BY," // USERNAME
        + "        SYSDATE," + "        F_DB_GET_EMP_NAME_A(NVL(?, UPDATED_BY)) EMPLOYEE_NAME," //
        + "        ?," // CENTER_NAME
        + "        ?," // ACTION
        + "        ROL_ID," + "        IS_VIRTUAL," + "        ROLE_TYPE," + "        SYS_CODE," + "        SVC_CODE," +
        "        URL," + "        ROLE_CODE," + "        ITEM_DOMAIN," + "        ITEM_VALUE" + " FROM IS_SEC_ROLES" +
        " WHERE ID = ?"; // ROLE ID

    /** Check Role Arabic Description Query */
    private static final String CHECK_AR_DESC_EXIST_ROLE_QUERY =
        " SELECT 1" + " FROM IS_SEC_ROLES ROL" + " WHERE ROL.DESCRIPTION_A = ?";

    /** Check Role English Description Query */
    private static final String CHECK_EN_DESC_EXIST_ROLE_QUERY =
        " SELECT 1" + " FROM IS_SEC_ROLES ROL" + " WHERE ROL.DESCRIPTION_E = ?";

    /** Check if role is a child for one of its childs Query */
    private static final String CHECK_ROLE_CHILD_TO_CHILDS_QUERY = " SELECT 1" + " FROM 	IS_SEC_ROLES ROL" + " WHERE	ID = ?" // SELECTED FROM LOOCKUP AS NEW PARENT
        + " CONNECT BY PRIOR ID = ROL_ID" + " START WITH	ID = ?"; // EDITED ROLE"

    /** Check Role Path Query */
    private static final String CHECK_PATH_EXIST_ROLE_QUERY =
        " SELECT 1" + " FROM IS_SEC_ROLES ROL" + " WHERE ROL.PATH = ?";

    /** Retrive child roles. */
    private static final String GET_CHILD_ROLES_QUERY =
        " SELECT ID, PATH" + " FROM 	IS_SEC_ROLES ROL" + " CONNECT BY PRIOR ID = ROL_ID" +
        " START WITH ID = ?"; // EDITED ROLE

    /** Update role path query. */
    private static final String UPDATE_ROLE_PATH_QUERY =
        " UPDATE IS_SEC_ROLES SET" + " PATH = ?," + " UPDATE_DATE = SYSDATE," + " UPDATED_BY = ?" + " WHERE ID = ?";

    /** Delete role query. */
    private static final String DELETE_ROLE_QUERY = " DELETE FROM IS_SEC_ROLES ROL WHERE ROL.ID = ?";

    /** Check if role is related to a child query. */
    private static final String CHECK_ROLE_HAS_CHILD_QUERY =
        " SELECT    1" + " FROM      IS_SEC_ROLES CHILD_ROL," + "           IS_SEC_ROLES ROL" +
        " WHERE     CHILD_ROL.ROL_ID = ROL.ID AND ROL.ID = ?";

    /** Check if role is related to a group. */
    private static final String CHECK_ROLE_CONNECTED_TO_GROUP_QUERY =
        " SELECT    1" + " FROM      IS_SEC_GROUP_ROLES GRL" + " WHERE     GRL.ROL_ID = ?";

    /** Check if role is assigned to a user. */
    private static final String CHECK_ROLE_CONNECTED_TO_USER_QUERY =
        " SELECT    1" + " FROM      IS_SEC_USER_ROLES URL" + " WHERE     URL.ROL_ID = ?";

    /** Find Roles By Group ID Query */
    private static final String FIND_ROLES_BY_GROUP_ID_QUERY =
        " SELECT ROL.ID," + "        ROL.DESCRIPTION_A" + " FROM   IS_SEC_ROLE_GROUPS RLG," +
        "        IS_SEC_ROLES ROL," + "        IS_SEC_GROUP_ROLES GRL" + " WHERE  RLG.ID = GRL.RLG_ID" +
        "    AND ROL.ID = GRL.ROL_ID" + "    AND RLG.ID = ?";

    /** Check Role System Code Query */
    private static final String CHECK_SYSTEM_CODE_EXIST_ROLE_QUERY =
        " SELECT 1" + " FROM IS_SEC_ROLES ROL" + " WHERE ROL.SYS_CODE = ?";

    /** Check Role Service Code Query */
    private static final String CHECK_SERVICE_CODE_EXIST_ROLE_QUERY =
        " SELECT 1" + " FROM IS_SEC_ROLES ROL" + " WHERE ROL.SVC_CODE = ?";

    /** Check Role URL Query */
    private static final String CHECK_URL_EXIST_ROLE_QUERY =
        " SELECT 1" + " FROM IS_SEC_ROLES ROL" + " WHERE ROL.URL = ?";

    /** Check Role Code Query */
    private static final String CHECK_ROLE_CODE_EXIST_ROLE_QUERY =
        " SELECT 1" + " FROM IS_SEC_ROLES ROL" + " WHERE ROL.ROLE_CODE = ?";

    /** Check Role Code Query */
    private static final String CHECK_ITEM_EXIST_ROLE_QUERY =
        " SELECT 1" + " FROM IS_SEC_ROLES ROL" + " WHERE ROL.ITEM_DOMAIN = ?" + "   AND ROL.ITEM_VALUE = ?" +
        "   AND ROL_ID = ? ";

    /** Update role status query. */
    private static final String UPDATE_ROLE_STATUS_QUERY =
        " UPDATE IS_SEC_ROLES SET" + " STATUS = ?," + " STATUS_DATE = SYSDATE," + " UPDATE_DATE = SYSDATE," +
        " UPDATED_BY = ?" + " WHERE ID = ?";

    /** Check Role Has Active Child Query */
    private static final String CHECK_ROLE_HAS_ACTIVE_CHILD_QUERY =
        " SELECT 1" + " FROM IS_SEC_ROLES ROL" + " WHERE ROL.STATUS = 2" +
        " AND ROL.PATH LIKE (SELECT PATH FROM IS_SEC_ROLES WHERE ID = ?) || '\\%' ";


    /*
     * Methods.
     */

    /**
     * Get Role via ID.
     *
     * @return Role VO.
     * @param roleId Role ID.
     */
    public RoleVO getById(Long roleId) {
        List params = new ArrayList();
        params.add(roleId);

        // Debug Query.
        debugQuery(GET_ROLE_BY_ID_QUERY, params);

        ResultSet rs = null;
        PreparedStatement stm = null;
        RoleVO vo = null;
        try {
            stm = getConnection().prepareStatement(GET_ROLE_BY_ID_QUERY);
            setQueryParameters(stm, params);
            rs = stm.executeQuery();

            if (!rs.next()) {
                return vo;
            }

            vo = new RoleVO();
            vo.setParentRole(new RoleVO());
//            vo.getParentRole().setSystem(new SystemTypeVO());
//            vo.setSystem(new SystemTypeVO());
            vo.setService(new ServiceVO());

            vo.setId(getLong(rs, "ROLE_ID"));
            vo.setDescriptionAr(getString(rs, "ROLE_NAME_A"));
            vo.setDescriptionEn(getString(rs, "ROLE_NAME_E"));
            //vo.getSystem().setCode(getString(rs, "SYSTEM_CODE"));
           // vo.getSystem().setDescriptionAr(getString(rs, "SYSTEM_NAME_A"));
            vo.setAdminLevel(getInteger(rs, "ADMIN_LEVEL"));
            vo.setAdminLevelDesc(getString(rs, "ADMIN_LEVEL_DESC"));
            vo.getParentRole().setId(getLong(rs, "PARENT_ROLE_ID"));
            vo.getParentRole().setDescriptionAr(getString(rs, "PARENT_ROLE_NAME_A"));
            vo.getParentRole().setPath(getString(rs, "PARENT_ROLE_PATH"));
            vo.getParentRole().setRoleType(getInteger(rs, "PARENT_ROLE_TYPE"));
          //  vo.getParentRole().getSystem().setCode(getString(rs, "PARENT_ROLE_SYSTEM_CODE"));
            vo.setStatus(getInteger(rs, "ROLE_STATUS"));
            vo.setStatusDesc(getString(rs, "ROLE_STATUS_DESC"));
            vo.setRemarks(getString(rs, "ROLE_REMARKS"));
            vo.setPath(getString(rs, "ROLE_PATH"));
            vo.getService().setCode(getInteger(rs, "SERVICE_CODE"));
            vo.getService().setDescriptionAr(getString(rs, "SERVICE_NAME_A"));
            vo.setUrl(getString(rs, "PAGE_URL"));
            vo.setVirtualRole(getInteger(rs, "VIRTUAL_ROLE"));
            vo.setRoleCode(getString(rs, "ROLE_CODE"));
            vo.setItemDomain(getString(rs, "ITEM_DOMAIN"));
            vo.setItemValue(getString(rs, "ITEM_VALUE"));
            vo.setRequireEid(getInteger(rs, "REQUIRE_EID"));
            vo.setCanBeDenied(getInteger(rs, "CAN_BE_DENIED"));
            vo.setSecurityMode(getInteger(rs, "SECURITY_MODE"));
          
            return vo;

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(rs);
            close(stm);
        }
    }

    /**
     * Find Role.
     *
     * @return Search Page Value Object
     * @param pageNo pagination page Number
     * @param roleVO role VO
     * @param actionUserAdminLevel Admin Level For Logged-In User.
     * @param parentSearch determine if this a parent search
     */
    public SearchPageVO find(int pageNo, RoleVO roleVo, Integer actionUserAdminLevel, boolean parentSearch) {
        StringBuffer query = new StringBuffer(FIND_ROLE_QUERY);
        List params = new ArrayList();

        if (!isBlankOrNull(roleVo.getDescriptionAr())) {
            query.append(" AND ROL.DESCRIPTION_A LIKE ?");
            params.add("%" + roleVo.getDescriptionAr().trim() + "%");
        }

        if (!isBlankOrNull(roleVo.getDescriptionEn())) {
            query.append(" AND ROL.DESCRIPTION_E LIKE ?");
            params.add("%" + roleVo.getDescriptionEn().trim() + "%");
        }

        if (roleVo.getStatus() != null) {
            query.append(" AND ROL.STATUS = ?");
            params.add(roleVo.getStatus());
        }

        if (roleVo.getAdminLevel() != null) {
            if (actionUserAdminLevel.equals(RoleVO.ADMIN_LEVEL_SYSTEM)) {
                query.append(" AND ROL.ADMIN_LEVEL = ?");
            } else {
                query.append(" AND ROL.ADMIN_LEVEL IN (?, ").append(RoleVO.ADMIN_LEVEL_ALL).append(")");
            }
            params.add(roleVo.getAdminLevel());
        }

        if (roleVo.getStatusDate() != null) {
            query.append(" AND ROL.STATUS_DATE > ?");
            query.append(" AND ROL.STATUS_DATE <= ? + 1");
            params.add(roleVo.getStatusDate());
            params.add(roleVo.getStatusDate());
        }

        if (roleVo.getParentRole() != null && roleVo.getParentRole().getId() != null) {
            query.append(" AND ROL.ROL_ID = ?");
            params.add(roleVo.getParentRole().getId());
        }

        if (roleVo.getRoleType() != null) {
            if (parentSearch) {
                if (roleVo.isRoleTypeModule()) {
                    query.append(" AND ROL.ROLE_TYPE = 1");
                } else if (roleVo.isRoleTypeServices()) {
                    query.append(" AND ROL.ROLE_TYPE = 2");
                } else if (roleVo.isRoleTypePage()) {
                    query.append(" AND (ROL.ROLE_TYPE = 2 OR ROL.ROLE_TYPE = 3 OR ROL.ROLE_TYPE = 4)");
                } else if (roleVo.isRoleTypePageObject()) {
                    query.append(" AND ROL.ROLE_TYPE = 4");
                } else if (roleVo.isRoleTypeObjectItem()) {
                    query.append(" AND ROL.ROLE_TYPE = 5");
                } else if (roleVo.isRoleTypeCustomRole()) {
                    query.append(" AND ROL.ROLE_TYPE = 2");
                }
            } else {
                query.append(" AND ROL.ROLE_TYPE = ?");
                params.add(roleVo.getRoleType());
            }
        }

//        if (!isBlankOrNull(roleVo.getSystem().getCode())) {
//            query.append(" AND ROL.SYS_CODE = SYS.CODE");
//            query.append(" AND SYS.CODE = ?");
//            params.add(roleVo.getSystem().getCode());
//        }

        if (roleVo.getService().getCode() != null) {
            query.append(" AND ROL.SVC_CODE = SVC.CODE");
            query.append(" AND SVC.CODE = ?");
            params.add(roleVo.getService().getCode());
        }

        if (!isBlankOrNull(roleVo.getRoleCode())) {
            query.append(" AND ROL.ROLE_CODE = ?");
            params.add(roleVo.getRoleCode());
        }

        if (!isBlankOrNull(roleVo.getUrl())) {
            query.append(" AND ROL.URL LIKE ?");
            params.add("%" + roleVo.getUrl().trim() + "%");
        }
                
        if(roleVo.getCanBeDenied() != null &&
           roleVo.getCanBeDenied().intValue() > 0) {
            
            query.append(" AND ROL.CAN_BE_DENIED = ?");
            params.add(roleVo.getCanBeDenied());
        }

        debugQuery(query, params);

        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;
        try {
            int pageSize = getDefaultPageSize();
            int totalRecordCounts = getTotalCount(query.toString(), params);
            prepStmt = doSearch(query.toString(), params, pageNo, pageSize);
            resultSet = prepStmt.executeQuery();

            SearchPageVO searchPage = new SearchPageVO(pageNo, pageSize, totalRecordCounts);
            while (resultSet.next()) {
                RoleVO vo = new RoleVO();
                vo.setParentRole(new RoleVO());
                //vo.setSystem(new SystemTypeVO());
                vo.setService(new ServiceVO());

                searchPage.addRecord(vo);

                vo.setId(getLong(resultSet, "ROLE_ID"));
                vo.setDescriptionAr(getString(resultSet, "ROLE_NAME_A"));
                vo.setRoleType(getInteger(resultSet, "ROLE_TYPE"));
                vo.setRoleTypeDesc(getString(resultSet, "ROLE_TYPE_DESC"));
                vo.setStatus(getInteger(resultSet, "ROLE_STATUS"));
                vo.setStatusDesc(getString(resultSet, "ROLE_STATUS_DESC"));
                vo.setStatusDate(resultSet.getDate("ROLE_STATUS_DATE"));
                vo.getParentRole().setId(getLong(resultSet, "PARENT_ROLE_ID"));
                vo.getParentRole().setDescriptionAr(getString(resultSet, "PARENT_ROLE_NAME_A"));
                vo.getParentRole().setPath(getString(resultSet, "PARENT_ROLE_PATH"));
                vo.getParentRole().setRoleType(getInteger(resultSet, "PARENT_ROLE_TYPE"));
            }
            return searchPage;

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(resultSet);
            close(prepStmt);
        }
    }

    /**
     * Get Child Role.
     *
     * @return List Of RoleVO
     * @param roleId Role ID
     */
    public List getChildRoles(Long roleId) {
        StringBuffer query = new StringBuffer(FIND_ROLE_QUERY);
        List params = new ArrayList();
        List result = new ArrayList();

        if (roleId != null) {
            query.append(" AND ROL.ROL_ID = ?");
            params.add(roleId);
        } else {
            query.append(" AND ROL.ROL_ID IS NULL");
        }

        debugQuery(query, params);

        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;
        try {
            prepStmt = getConnection().prepareStatement(query.toString());
            setQueryParameters(prepStmt, params);
            resultSet = prepStmt.executeQuery();

            while (resultSet.next()) {
                RoleVO vo = new RoleVO();
                vo.setParentRole(new RoleVO());
               // vo.setSystem(new SystemTypeVO());
                vo.setService(new ServiceVO());

                result.add(vo);

                vo.setId(getLong(resultSet, "ROLE_ID"));
                vo.setDescriptionAr(getString(resultSet, "ROLE_NAME_A"));
                vo.setRoleType(getInteger(resultSet, "ROLE_TYPE"));
                vo.setRoleTypeDesc(getString(resultSet, "ROLE_TYPE_DESC"));
                vo.setStatus(getInteger(resultSet, "ROLE_STATUS"));
                vo.setStatusDesc(getString(resultSet, "ROLE_STATUS_DESC"));
                vo.setStatusDate(resultSet.getDate("ROLE_STATUS_DATE"));
                vo.setPath(resultSet.getString("ROLE_PATH"));
                vo.getParentRole().setId(getLong(resultSet, "PARENT_ROLE_ID"));
                vo.getParentRole().setDescriptionAr(getString(resultSet, "PARENT_ROLE_NAME_A"));
                vo.getParentRole().setPath(getString(resultSet, "PARENT_ROLE_PATH"));
                vo.getParentRole().setRoleType(getInteger(resultSet, "PARENT_ROLE_TYPE"));
            }
            return result;

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(resultSet);
            close(prepStmt);
        }
    }

    /**
     * Add Role.
     *
     * @return role VO
     * @param roleVO role VO
     */
    public RoleVO add(RoleVO roleVO) {
        PreparedStatement stm = null;
        try {
            // Generate new sequence
            Long newId = generateSequence("ROL_SEQ");

            // Fix Parent Path
            String path = newId.toString();
            Long parentRoleId = roleVO.getParentRole().getId();
            if (parentRoleId != null) {
                RoleVO parentVO = getById(parentRoleId);
                if (parentVO != null) {
                    String parentPath = parentVO.getPath();
                    if (parentPath != null) {
                        path = parentPath + "\\" + path;
                    }
                }
            }
            roleVO.setPath(path);

            List params = new ArrayList();
            params.add(newId);
            params.add(roleVO.getPath());
            params.add(roleVO.getDescriptionAr());
            params.add(roleVO.getDescriptionEn());
            params.add(roleVO.getAdminLevel());
            params.add(roleVO.getParentRole().getId());
            params.add(roleVO.getStatus());
            params.add(roleVO.getRemarks());
            params.add(roleVO.getCreatedBy());
            params.add(roleVO.getUpdatedBy());
            params.add(roleVO.getVirtualRole());
            params.add(roleVO.getRoleType());
           // params.add(roleVO.getSystem().getCode());
            params.add(roleVO.getService().getCode());
            params.add(roleVO.getUrl());
            //params.add(roleVO.getRoleCode());
            params.add(roleVO.getItemDomain());
            params.add(roleVO.getItemValue());
            params.add(roleVO.getRequireEid());
            params.add(roleVO.getCanBeDenied());
            params.add(roleVO.getSecurityMode());

            // Debug query
            debugQuery(ADD_ROLE_QUERY, params);

            // Execute DML statement
            stm = getConnection().prepareStatement(ADD_ROLE_QUERY);
            setQueryParameters(stm, params);

            stm.executeUpdate();

            // Save new ID
            roleVO.setId(newId);

            // Add LOG record
            addLog(roleVO.getId(), LogValueObject.ACTION_TYPE_ADD, roleVO.getCenterName(), roleVO.getUpdatedBy());

            // Return the VO
            return roleVO;

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(stm);
        }
    }

    /**
     * Update Role.
     *
     * @return Role VO
     * @param roleVO role VO
     */
    public RoleVO update(RoleVO roleVO) {
        PreparedStatement stm = null;
        try {

            // Fix Parent Path
            String path = roleVO.getId().toString();
            Long parentRoleId = roleVO.getParentRole().getId();
            if (parentRoleId != null) {
                RoleVO parentVO = getById(parentRoleId);
                if (parentVO != null) {
                    String parentPath = parentVO.getPath();
                    if (parentPath != null) {
                        path = parentPath + "\\" + path;
                    }
                }
            }
            roleVO.setPath(path);

            List params = new ArrayList();
            params.add(roleVO.getStatus());
            params.add(roleVO.getStatus());
            params.add(roleVO.getPath());
            params.add(roleVO.getDescriptionAr());
            params.add(roleVO.getDescriptionEn());
            params.add(roleVO.getAdminLevel());
            params.add(roleVO.getParentRole().getId());
            params.add(roleVO.getRemarks());
            params.add(roleVO.getUpdatedBy());
            params.add(roleVO.getVirtualRole());
            params.add(roleVO.getRoleType());
           // params.add(roleVO.getSystem().getCode());
            params.add(roleVO.getService().getCode());
            params.add(roleVO.getUrl());
            params.add(roleVO.getRoleCode());
            params.add(roleVO.getItemDomain());
            params.add(roleVO.getItemValue());
            params.add(roleVO.getRequireEid());
            params.add(roleVO.getCanBeDenied());
            params.add(roleVO.getSecurityMode());
            params.add(roleVO.getId());

            // Debug query
            debugQuery(UPDATE_ROLE_QUERY, params);

            // Execute DML statement
            stm = getConnection().prepareStatement(UPDATE_ROLE_QUERY);
            setQueryParameters(stm, params);
            int count = stm.executeUpdate();

            if (count != 1) {
                throw new DataAccessException(new StringBuffer("Error while updating role: roleId=").append(roleVO.getId()).append(", updatedRecords=").append(count).toString());
            }

            // Add LOG for EDITED record.
            addLog(roleVO.getId(), LogValueObject.ACTION_TYPE_UPDATE, roleVO.getCenterName(), roleVO.getUpdatedBy());

            // Update childs.
            updateChildRolesPaths(roleVO.getId(), roleVO.getPath(), roleVO.getUpdatedBy(), roleVO.getCenterName());

            return roleVO;

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(stm);
        }
    }

    /**
     * Delete Role
     *
     * @param roleVO Role VO
     */
    public void delete(RoleVO roleVO) {
        PreparedStatement prepStmt = null;
        try {

            // Add LOG record
            addLog(roleVO.getId(), LogValueObject.ACTION_TYPE_DELETE, roleVO.getCenterName(), roleVO.getUpdatedBy());

            List params = new ArrayList();
            params.add(roleVO.getId());

            // Debug Query.
            debugQuery(DELETE_ROLE_QUERY, params);

            prepStmt = getConnection().prepareStatement(DELETE_ROLE_QUERY);
            setQueryParameters(prepStmt, params);
            int count = prepStmt.executeUpdate();

            if (count != 1) {
                throw new DataAccessException(new StringBuffer("Error while deleteing resource: roleId=").append(roleVO.getId()).append(", deletedRecords=").append(count).toString());
            }
        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(prepStmt);
        }
    }

    /**
     * Add Role LOG.
     *
     * @return role VO
     * @param resourceId role ID
     * @param actionType role action ID
     * @param centerName center name who commited the action
     * @param username username who commited the action
     * @param roleId role ID
     */
    private void addLog(Long roleId, Integer actionType, String centerName, String username) {
        PreparedStatement stm = null;
        try {
            List params = new ArrayList();
            params.add(username);
            params.add(username);
            params.add(centerName);
            params.add(actionType);
            params.add(roleId);

            // Debug query
            debugQuery(ADD_ROLE_LOG_QUERY, params);

            // Execute DML statement
            stm = getConnection().prepareStatement(ADD_ROLE_LOG_QUERY);
            setQueryParameters(stm, params);
            stm.executeUpdate();

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(stm);
        }
    }

    /** Check if role arabic description is already exists.
     *
     * @return true if arabic description is already exists, otherwise false.
     * @param roleId role ID
     * @param descAr role arabic description
     */
    public boolean isDescriptionArExists(Long roleId, String descAr) {
        StringBuffer queryStr = new StringBuffer(CHECK_AR_DESC_EXIST_ROLE_QUERY);
        List params = new ArrayList();
        params.add(descAr);

        if (roleId != null) {
            queryStr.append(" AND ROL.ID <> ?");
            params.add(roleId);
        }

        // Debug query
        debugQuery(queryStr.toString(), params);

        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            stm = getConnection().prepareStatement(queryStr.toString());
            setQueryParameters(stm, params);
            rs = stm.executeQuery();

            return rs.next();

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(rs);
            close(stm);
        }
    }

    /** Check if role english description is already exists.
     *
     * @return true if english description is already exists, otherwise false.
     * @param roleId role ID
     * @param descAr role english description
     */
    public boolean isDescriptionEnExists(Long roleId, String descEn) {
        StringBuffer queryStr = new StringBuffer(CHECK_EN_DESC_EXIST_ROLE_QUERY);
        List params = new ArrayList();
        params.add(descEn);

        if (roleId != null) {
            queryStr.append(" AND ROL.ID <> ?");
            params.add(roleId);
        }

        // Debug query
        debugQuery(queryStr.toString(), params);

        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            stm = getConnection().prepareStatement(queryStr.toString());
            setQueryParameters(stm, params);
            rs = stm.executeQuery();

            return rs.next();

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(rs);
            close(stm);
        }
    }

    /** Check if role is a child to one of its childs.
     *
     * @return true if if role is a child to one of its childs.
     * @param roleId role ID
     * @param parentRoleId parent role ID
     */
    public boolean isRoleChildToChilds(Long roleId, Long parentRoleId) {
        StringBuffer queryStr = new StringBuffer(CHECK_ROLE_CHILD_TO_CHILDS_QUERY);
        List params = new ArrayList();
        params.add(parentRoleId);
        params.add(roleId);

        // Debug query
        debugQuery(queryStr.toString(), params);

        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            stm = getConnection().prepareStatement(queryStr.toString());
            setQueryParameters(stm, params);
            rs = stm.executeQuery();

            return rs.next();

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(rs);
            close(stm);
        }
    }

    /** Check if role path is already exists.
     *
     * @return true if path is already exists, otherwise false.
     * @param roleId role ID
     * @param path role path
     */
    public boolean isPathExists(Long roleId, String path) {
        StringBuffer queryStr = new StringBuffer(CHECK_PATH_EXIST_ROLE_QUERY);
        List params = new ArrayList();
        params.add(path);

        if (roleId != null) {
            queryStr.append(" AND ROL.ID <> ?");
            params.add(roleId);
        }

        // Debug query
        debugQuery(queryStr.toString(), params);

        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            stm = getConnection().prepareStatement(queryStr.toString());
            setQueryParameters(stm, params);
            rs = stm.executeQuery();

            return rs.next();

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(rs);
            close(stm);
        }
    }

    /**
     * Update Role Path.
     *
     * @param roleId role ID
     * @param path role path
     * @param username user name who commited the update
     * @param centerName center name whom commited the update
     */
    private void updateRolePath(Long roleId, String path, String username, String centerName) {
        PreparedStatement stm = null;
        try {

            List params = new ArrayList();
            params.add(path);
            params.add(username);
            params.add(roleId);

            // Debug query
            debugQuery(UPDATE_ROLE_PATH_QUERY, params);

            // Execute DML statement
            stm = getConnection().prepareStatement(UPDATE_ROLE_PATH_QUERY);
            setQueryParameters(stm, params);
            int count = stm.executeUpdate();

            if (count != 1) {
                throw new DataAccessException(new StringBuffer("Error while updating role: roleId=").append(roleId).append(", updatedRecords=").append(count).toString());
            }

            // Add LOG record
            addLog(roleId, LogValueObject.ACTION_TYPE_UPDATE, centerName, username);

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(stm);
        }
    }

    /**
     * Update Child Role's Path's.
     *
     * @param roleId role ID
     * @param path role path
     * @param username user name who commited the update
     * @param centerName center name whom commited the update
     */
    public void updateChildRolesPaths(Long roleId, String path, String username, String centerName) {
        PreparedStatement stm = null;
        try {

            List params = new ArrayList();
            params.add(roleId);

            // Debug query for chils retrival
            debugQuery(GET_CHILD_ROLES_QUERY, params);

            // Execute DML statement
            stm = getConnection().prepareStatement(GET_CHILD_ROLES_QUERY);
            setQueryParameters(stm, params);
            ResultSet resultSet = stm.executeQuery();

            while (resultSet.next()) {
                Long childId = getLong(resultSet, "ID");

                if (roleId.equals(childId))
                    continue;

                String childPath = getString(resultSet, "PATH");

                childPath =
                    path +
                    childPath.substring(childPath.substring(childPath.indexOf(roleId.toString() + "\\")).indexOf("\\"));

                updateRolePath(childId, childPath, username, centerName);
            }

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(stm);
        }
    }

    /** Check if role is connected to a child role.
     *
     * @return true if role is connected to a child role, otherwise false.
     * @param roleId role ID
     */
    public boolean isRoleHasChild(Long roleId) {
        List params = new ArrayList();
        params.add(roleId);

        // Debug query
        debugQuery(CHECK_ROLE_HAS_CHILD_QUERY, params);

        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            stm = getConnection().prepareStatement(CHECK_ROLE_HAS_CHILD_QUERY);
            setQueryParameters(stm, params);
            rs = stm.executeQuery();

            return rs.next();

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(rs);
            close(stm);
        }
    }

    /** Check if role is connected to a group.
     *
     * @return true if role is connected to a group, otherwise false.
     * @param roleId role ID
     */
    public boolean isRoleRelatedToGroup(Long roleId) {
        List params = new ArrayList();
        params.add(roleId);

        // Debug query
        debugQuery(CHECK_ROLE_CONNECTED_TO_GROUP_QUERY, params);

        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            stm = getConnection().prepareStatement(CHECK_ROLE_CONNECTED_TO_GROUP_QUERY);
            setQueryParameters(stm, params);
            rs = stm.executeQuery();

            return rs.next();

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(rs);
            close(stm);
        }
    }

    /** Check if role is assigned to a user.
     *
     * @return true if role is assigned to a user, otherwise false.
     * @param roleId role ID
     */
    public boolean isRoleAssignedToUser(Long roleId) {
        List params = new ArrayList();
        params.add(roleId);

        // Debug query
        debugQuery(CHECK_ROLE_CONNECTED_TO_USER_QUERY, params);

        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            stm = getConnection().prepareStatement(CHECK_ROLE_CONNECTED_TO_USER_QUERY);
            setQueryParameters(stm, params);
            rs = stm.executeQuery();

            return rs.next();

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(rs);
            close(stm);
        }
    }

    /**
     * Check if role has active childs.
     *
     * @return true if role has active childs, otherwise false.
     * @param roleId role ID
     */
    public boolean isRoleHasActiveChild(Long roleId) {
        List params = new ArrayList();
        params.add(roleId);

        // Debug query
        debugQuery(CHECK_ROLE_HAS_ACTIVE_CHILD_QUERY, params);

        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            stm = getConnection().prepareStatement(CHECK_ROLE_HAS_ACTIVE_CHILD_QUERY);
            setQueryParameters(stm, params);
            rs = stm.executeQuery();

            return rs.next();

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(rs);
            close(stm);
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
        List params = new ArrayList();
        params.add(groupId);

        // Debug Query.
        debugQuery(FIND_ROLES_BY_GROUP_ID_QUERY, params);

        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;
        try {
            int pageSize = getDefaultPageSize();
            int totalRecordCounts = getTotalCount(FIND_ROLES_BY_GROUP_ID_QUERY, params);
            prepStmt = doSearch(FIND_ROLES_BY_GROUP_ID_QUERY, params, pageNo, pageSize);
            resultSet = prepStmt.executeQuery();

            SearchPageVO searchPage = new SearchPageVO(pageNo, pageSize, totalRecordCounts);
            while (resultSet.next()) {

                RoleVO vo = new RoleVO();
                searchPage.addRecord(vo);

                vo.setId(getLong(resultSet, "ID"));
                vo.setDescriptionAr(getString(resultSet, "DESCRIPTION_A"));
            }
            return searchPage;

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(resultSet);
            close(prepStmt);
        }
    }

    /** Check if role System Code is already exists.
     *
     * @return true if role System Code is already exists, otherwise false.
     * @param roleId role ID
     * @param code system code
     */
    public boolean isSystemCodeExists(Long roleId, String code) {
        StringBuffer queryStr = new StringBuffer(CHECK_SYSTEM_CODE_EXIST_ROLE_QUERY);
        List params = new ArrayList();
        params.add(code);

        if (roleId != null) {
            queryStr.append(" AND ROL.ID <> ?");
            params.add(roleId);
        }

        // Debug query
        debugQuery(queryStr.toString(), params);

        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            stm = getConnection().prepareStatement(queryStr.toString());
            setQueryParameters(stm, params);
            rs = stm.executeQuery();

            return rs.next();

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(rs);
            close(stm);
        }
    }

    /** Check if role Service Code is already exists.
     *
     * @return true if role Service Code is already exists, otherwise false.
     * @param roleId role ID
     * @param code service code
     */
    public boolean isServiceCodeExists(Long roleId, Integer code) {
        StringBuffer queryStr = new StringBuffer(CHECK_SERVICE_CODE_EXIST_ROLE_QUERY);
        List params = new ArrayList();
        params.add(code);

        if (roleId != null) {
            queryStr.append(" AND ROL.ID <> ?");
            params.add(roleId);
        }

        // Debug query
        debugQuery(queryStr.toString(), params);

        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            stm = getConnection().prepareStatement(queryStr.toString());
            setQueryParameters(stm, params);
            rs = stm.executeQuery();

            return rs.next();

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(rs);
            close(stm);
        }
    }

    /** Check if role page URL is already exists.
     *
     * @return true if role page URL is already exists, otherwise false.
     * @param roleId role ID
     * @param url page URL
     */
    public boolean isUrlExists(Long roleId, String url) {
        StringBuffer queryStr = new StringBuffer(CHECK_URL_EXIST_ROLE_QUERY);
        List params = new ArrayList();
        params.add(url);

        if (roleId != null) {
            queryStr.append(" AND ROL.ID <> ?");
            params.add(roleId);
        }

        // Debug query
        debugQuery(queryStr.toString(), params);

        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            stm = getConnection().prepareStatement(queryStr.toString());
            setQueryParameters(stm, params);
            rs = stm.executeQuery();

            return rs.next();

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(rs);
            close(stm);
        }
    }

    /** Check if role code is already exists.
     *
     * @return true if role code is already exists, otherwise false.
     * @param roleId role ID
     * @param code Role Code
     */
    public boolean isCodeExists(Long roleId, String code, Long parentRole) {
        StringBuffer queryStr = new StringBuffer(CHECK_ROLE_CODE_EXIST_ROLE_QUERY);
        List params = new ArrayList();
        params.add(code);
        if (roleId != null) {
            queryStr.append(" AND ROL.ID <> ?");
            params.add(roleId);
        }
        if (parentRole != null) {
            queryStr.append(" AND ROL.ROL_ID = ?");
            params.add(parentRole);
        }

        // Debug query
        debugQuery(queryStr.toString(), params);

        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            stm = getConnection().prepareStatement(queryStr.toString());
            setQueryParameters(stm, params);
            rs = stm.executeQuery();

            return rs.next();

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(rs);
            close(stm);
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
    public boolean isItemDomainOrValueExists(Long roleId, String itemDomain, String itemValue, Long parentRoleId) {
        StringBuffer queryStr = new StringBuffer(CHECK_ITEM_EXIST_ROLE_QUERY);
        List params = new ArrayList();
        params.add(itemDomain);
        params.add(itemValue);
        params.add(parentRoleId);

        if (roleId != null) {
            queryStr.append(" AND ROL.ID <> ?");
            params.add(roleId);
        }

        // Debug query
        debugQuery(queryStr.toString(), params);

        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            stm = getConnection().prepareStatement(queryStr.toString());
            setQueryParameters(stm, params);
            rs = stm.executeQuery();

            return rs.next();

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(rs);
            close(stm);
        }
    }

    /**
     * Update Role Status.
     *
     * @return Role VO
     * @param roleVO role VO
     */
    public RoleVO updateStatus(RoleVO roleVO) {
        PreparedStatement stm = null;
        try {

            List params = new ArrayList();
            params.add(roleVO.getStatus());
            params.add(roleVO.getUpdatedBy());
            params.add(roleVO.getId());

            // Debug query
            debugQuery(UPDATE_ROLE_STATUS_QUERY, params);

            // Execute DML statement
            stm = getConnection().prepareStatement(UPDATE_ROLE_STATUS_QUERY);
            setQueryParameters(stm, params);
            int count = stm.executeUpdate();

            if (count != 1) {
                throw new DataAccessException(new StringBuffer("Error while updating role status: roleId=").append(roleVO.getId()).append(", updatedRecords=").append(count).toString());
            }

            // Add LOG for EDITED record.
            addLog(roleVO.getId(), LogValueObject.ACTION_TYPE_UPDATE, roleVO.getCenterName(), roleVO.getUpdatedBy());

            return roleVO;

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(stm);
        }
    }

    /**
     * Get Role by URL.
     *
     * @param url Page-Role URL.
     * @return Role Info.
     */
    public RoleVO getPrivatePage(String url) {

        NamedQuery query = getNamedQuery("ae.rta.security.dao.jdbc.Role.getPrivatePage");

        query.setParameter("pageUrl", url);

        debugQuery(query);

        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            RoleVO vo = new RoleVO();

            vo.setId(getLong(resultSet, "ID"));
            vo.setPath(getString(resultSet, "PATH"));
            vo.setDescriptionAr(getString(resultSet, "DESCRIPTION_A"));
            vo.setDescriptionEn(getString(resultSet, "DESCRIPTION_E"));
            vo.setAdminLevel(getInteger(resultSet, "ADMIN_LEVEL"));
            vo.setStatus(getInteger(resultSet, "STATUS"));
            vo.setRemarks(getString(resultSet, "REMARKS"));
            vo.setStatusDate(getDate(resultSet, "STATUS_DATE"));
            vo.setRoleType(getInteger(resultSet, "ROLE_TYPE"));
            vo.setVirtualRole(getInteger(resultSet, "IS_VIRTUAL"));
            vo.setUrl(getString(resultSet, "URL"));
            vo.setRoleCode(getString(resultSet, "ROLE_CODE"));
            vo.setItemDomain(getString(resultSet, "ITEM_DOMAIN"));
            vo.setItemValue(getString(resultSet, "ITEM_VALUE"));
            vo.setCreatedBy(getString(resultSet, "CREATED_BY"));
            vo.setUpdatedBy(getString(resultSet, "UPDATED_BY"));
            vo.setCreationDate(getDate(resultSet, "CREATION_DATE"));
            vo.setUpdateDate(getDate(resultSet, "UPDATE_DATE"));
            vo.setRequireEid(getInteger(resultSet, "REQUIRE_EID"));
            vo.setParentRole(new RoleVO());
            vo.getParentRole().setId(getLong(resultSet, "ROL_ID"));

//            vo.setSystem(new SystemTypeVO());
//            vo.getSystem().setCode(getString(resultSet, "SYS_CODE"));

            vo.setService(new ServiceVO());
            vo.getService().setCode(getInteger(resultSet, "SVC_CODE"));

            return vo;

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(resultSet);
            close(preparedStatement);
        }
    }

    /**
     * Is Role Exist
     *
     * @param url Page URL
     * @return True if page url has Role
     */
    public boolean isRoleExist(String url) {

        NamedQuery query = getNamedQuery("ae.rta.security.dao.jdbc.Role.isRoleExist");
        query.appendWhereClause(" AND UPPER(URL) = UPPER(:pageUrl) ", "pageUrl", url);

        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return false;
            }

            return getInteger(resultSet, "COUNT").intValue() > 0;

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(resultSet);
            close(preparedStatement);
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

        NamedQuery query = getNamedQuery("ae.rta.security.dao.jdbc.Role.getSensitiveChildrensOfRole");

        query.setParameter("roleId", roleId);
        query.setParameter("userId", userId);

        debugQuery(query);

        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            List roles = new ArrayList();
            while (resultSet.next()) {
                RoleVO vo = new RoleVO();
                vo.setId(getLong(resultSet, "ID"));
                vo.setAdminLevel(getInteger(resultSet, "ADMIN_LEVEL"));
                vo.setUrl(getString(resultSet, "URL"));
                vo.setRoleCode(getString(resultSet, "ROLE_CODE"));
                vo.setRoleType(getInteger(resultSet, "ROLE_TYPE"));
                vo.setParentRole(new RoleVO());
                vo.getParentRole().setId(getLong(resultSet, "ROL_ID"));
                vo.setRequireEid(getInteger(resultSet, "REQUIRE_EID"));
                roles.add(vo);
            }

            return roles;

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(resultSet);
            close(preparedStatement);
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

        NamedQuery query = getNamedQuery("ae.rta.security.dao.jdbc.Role.getChildrensOfRole");

        query.setParameter("userId", userId);
        query.setParameter("pageUrl", pageUrl);
        if (objSeq != null) {
            query.appendWhereClause("AND ROL.OBJECT_SEQUENCE = :objSeq", "objSeq", objSeq);
        }
        debugQuery(query);

        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            List roles = new ArrayList();
            while (resultSet.next()) {
                RoleVO vo = new RoleVO();
                vo.setId(getLong(resultSet, "ID"));
                vo.setPath(getString(resultSet, "PATH"));
                vo.setDescriptionAr(getString(resultSet, "DESCRIPTION_A"));
                vo.setDescriptionEn(getString(resultSet, "DESCRIPTION_E"));
                vo.setAdminLevel(getInteger(resultSet, "ADMIN_LEVEL"));
                vo.setStatus(getInteger(resultSet, "STATUS"));
                vo.setRemarks(getString(resultSet, "REMARKS"));
                vo.setStatusDate(getDate(resultSet, "STATUS_DATE"));
                vo.setRoleType(getInteger(resultSet, "ROLE_TYPE"));
                vo.setVirtualRole(getInteger(resultSet, "IS_VIRTUAL"));
                vo.setUrl(getString(resultSet, "URL"));
                vo.setRoleCode(getString(resultSet, "ROLE_CODE"));
                vo.setObjectSeq(getInteger(resultSet, "OBJECT_SEQUENCE"));
                roles.add(vo);
            }

            return roles;

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(resultSet);
            close(preparedStatement);
        }
    }

    public List<RoleVO> search(String roleCode, String roleDesc) {

        NamedQuery query = getNamedQuery("ae.rta.security.dao.jdbc.Role.search");
        if (roleDesc != null) {
            query.appendWhereClause("  AND ROL.DESCRIPTION_A LIKE :roleDesc ", "roleDesc", "%" + roleDesc + "%");
        }
        if (roleCode != null) {
            query.appendWhereClause("  AND ROL.ROLE_CODE LIKE :roleCode ", "roleCode", "%" + roleCode + "%");
        }
        debugQuery(query);

        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            List<RoleVO> roles = new ArrayList<RoleVO>();
            while (resultSet.next()) {
                RoleVO vo = new RoleVO();
                vo.setId(getLong(resultSet, "ID"));
                vo.setDescriptionAr(getString(resultSet, "DESCRIPTION_A"));
                vo.setDescriptionEn(getString(resultSet, "DESCRIPTION_E"));
                vo.setAdminLevel(getInteger(resultSet, "ADMIN_LEVEL"));
                vo.setRoleCode(getString(resultSet, "ROLE_CODE"));
                roles.add(vo);
            }

            return roles;

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(resultSet);
            close(preparedStatement);
        }
    }


    /**
     * Get all roles that can be denied access to temporarily.
     *
     *  @param roleId
     * @return List of can be denied roles.
     */
    public List<RoleVO> getCanBeDeniedRoles(Long roleId) {

        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            NamedQuery query = getNamedQuery("ae.rta.security.dao.jdbc.Role.getCanBeDeniedRoles");
            // if roleId is null then retrieve all records.
            if (roleId != null) {
                query.appendWhereClause(" AND ROL.ID =:roleId", "roleId", roleId);
            }
            query.appendWhereClause(" ORDER BY ROL.DESCRIPTION_A ASC ");

            debugQuery(query);
            preparedStatement = prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            List<RoleVO> roles = new ArrayList<RoleVO>();
            while (resultSet.next()) {
                RoleVO vo = new RoleVO();
                vo.setId(getLong(resultSet, "ID"));
                vo.setDescriptionAr(getString(resultSet, "DESCRIPTION_A"));
                vo.setDescriptionEn(getString(resultSet, "DESCRIPTION_E"));
                roles.add(vo);
            }

            return roles;

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(resultSet);
            close(preparedStatement);
        }
    }

    /**
     * lookup for roles based on query search
     * @param query : text to search
     * @param lang : language for logged-in user
     * @param paramsMap : extra where condition Parameters Map
     * @return list of roles matches this query
     */
    @Override
    public List<RoleVO> lookup(String query, String lang, int pageSize, HashMap<String,String> paramsMap) {
        // Get Named Query
        NamedQuery namedQuery = getNamedQuery("ae.rta.common.dao.jdbc.Role.lookup");

        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;

        try {
            namedQuery.setParameter("rowNo", pageSize);
            namedQuery.setParameter("text", "%" + query + "%");

            prepStmt = prepareStatement(namedQuery);
            resultSet = prepStmt.executeQuery();
            List<RoleVO> rolesList = new ArrayList<RoleVO>();

            while (resultSet.next()) {
                RoleVO roleVO = new RoleVO();
                roleVO.setId(Long.parseLong(getString(resultSet, "ROLE_ID")));
                roleVO.setRoleCode(getString(resultSet, "CODE"));
                roleVO.setDescriptionAr(getString(resultSet, "ROLE_NAME_A"));
                roleVO.setDescriptionEn(getString(resultSet, "ROLE_NAME_E"));
                rolesList.add(roleVO);
            }
            return rolesList;
        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(resultSet);
            close(prepStmt);
        }
    }

    /**
     *  validate (id, text) for role recived from lookup componant
     * @param id : id of the role
     * @param text : role description based on language
     * @param lang : language for loogged-in user
     * @return true if data is valid
     */
    @Override
    public boolean validateLookup(String id, String text, String lang) {
        boolean result = false;
        // Get Named Query
        NamedQuery namedQuery = getNamedQuery("ae.rta.common.dao.jdbc.Role.validateLookup");

        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;

        try {
            namedQuery.setParameter("id", id);
            namedQuery.setParameter("text", text);

            prepStmt = prepareStatement(namedQuery);
            resultSet = prepStmt.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(resultSet);
            close(prepStmt);
        }
        return result;
    }
    
    /**
     * Get role info related to this newURL
     * 
     * @param newURL New-URL related to the requested role
     * @return role info related to this newURL
     */
    public RoleVO getByNewURL(String newURL) {
        NamedQuery namedQuery = getNamedQuery("ae.rta.common.dao.jdbc.Role.getByNewURL");
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        try {
            namedQuery.setParameter("newURL", newURL);
            prepStmt = prepareStatement(namedQuery);

            rs = prepStmt.executeQuery();
            if (! rs.next()) {
                return null;
            }
            
            RoleVO vo = new RoleVO();
            vo.setId(getLong(rs, "ID"));
            vo.setUrl(getString(rs, "URL"));
            vo.setNewURL(getString(rs, "NEW_URL"));
            vo.setRoleCode(getString(rs, "ROLE_CODE"));
            return vo;

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(rs);
            close(prepStmt);
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
        NamedQuery namedQuery = getNamedQuery("ae.rta.common.dao.jdbc.Role.getSecurityModeURLs");
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        try {
            
            prepStmt = prepareStatement(namedQuery);
            rs = prepStmt.executeQuery();
            
            Map<String, Integer> map = new HashMap<String, Integer>();
            while (rs.next()) {
                map.put(getString(rs, "NEW_URL"), getInteger(rs, "SECURITY_MODE"));
            }

            return map;

        } catch (Exception ex) {
            throw new DataAccessException(ex);
        } finally {
            close(rs);
            close(prepStmt);
        }
    }

	@Override
	public void connect(DataAccessObject1 dao) {
		// TODO Auto-generated method stub
		
	}
}
