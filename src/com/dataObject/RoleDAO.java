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
 * Data access object interface. This interface defines all data
 * access methods for security role.
 *
 * @author Alaa Salem
 * @version 1.00
 */
public interface RoleDAO extends DataAccessObject {

    /*
     * Methods.
     */

    /**
     * Find Role.
     *
     * @return Search Page Value Object
     * @param pageNo pagination page Number
     * @param roleVO role VO
     * @param actionUserAdminLevel Admin Level For Logged-In User.
     * @param parentSearch determine if this a parent search
     */
    SearchPageVO find(int pageNo, RoleVO roleVO, Integer actionUserAdminLevel, boolean parentSearch);

    /**
     * Get Child Role.
     *
     * @return List Of RoleVO
     * @param roleId Role ID
     */
    List getChildRoles(Long roleId);

    /**
     * Get Role via ID.
     *
     * @return Role VO.
     * @param roleId Role ID.
     */
    RoleVO getById(Long roleId);

    /**
     * Add Role.
     *
     * @return RoleVO Role VO
     * @param vo role VO
     */
    RoleVO add(RoleVO vo);

    /**
     * Update Role.
     *
     * @return RoleVO Role VO
     * @param vo role VO
     */
    RoleVO update(RoleVO vo);

    /**
     * Delete Role
     *
     * @param roleVO Role VO
     */
    void delete(RoleVO roleVO);

    /** Check if role arabic description is already exists.
     *
     * @return true if arabic description is already exists, otherwise false.
     * @param roleId role ID
     * @param descAr role arabic description
     */
    boolean isDescriptionArExists(Long roleId, String descAr);

    /** Check if role english description is already exists.
     *
     * @return true if english description is already exists, otherwise false.
     * @param roleId role ID
     * @param descAr role english description
     */
    boolean isDescriptionEnExists(Long roleId, String descEn);

    /** Check if role System Code is already exists.
     *
     * @return true if role System Code is already exists, otherwise false.
     * @param roleId role ID
     * @param code system code
     */
    boolean isSystemCodeExists(Long roleId, String code);

    /** Check if role Service Code is already exists.
     *
     * @return true if role Service Code is already exists, otherwise false.
     * @param roleId role ID
     * @param code service code
     */
    boolean isServiceCodeExists(Long roleId, Integer code);

    /** Check if role page URL is already exists.
     *
     * @return true if role page URL is already exists, otherwise false.
     * @param roleId role ID
     * @param url page URL
     */
    boolean isUrlExists(Long roleId, String url);

    /** Check if role code is already exists.
     *
     * @return true if role code is already exists, otherwise false.
     * @param roleId role ID
     * @param code Role Code
     */
    boolean isCodeExists(Long roleId, String code, Long parentRole);

    /** Check if Item Domain or Value is already exists.
     *
     * @return true if Item Domain or Value is already exists, otherwise false.
     * @param roleId role ID
     * @param itemDomain Item Domain
     * @param itemValue Item Value
     * @param parentRoleId : Parent Role Id
     */
    boolean isItemDomainOrValueExists(Long roleId, String itemDomain, String itemValue, Long parentRoleId);

    /** Check if role is a child to one of its childs.
     *
     * @return true if if role is a child to one of its childs.
     * @param roleId role ID
     * @param parentRoleId parent role ID
     */
    boolean isRoleChildToChilds(Long roleId, Long parentRoleId);

    /** Check if role path is already exists.
     *
     * @return true if path is already exists, otherwise false.
     * @param roleId role ID
     * @param path role path
     */
    boolean isPathExists(Long roleId, String path);

    /** Check if role is connected to a child role.
     *
     * @return true if role is connected to a child role, otherwise false.
     * @param roleId role ID
     */
    boolean isRoleHasChild(Long roleId);

    /** Check if role is connected to a group.
     *
     * @return true if role is connected to a group, otherwise false.
     * @param roleId role ID
     */
    boolean isRoleRelatedToGroup(Long roleId);

    /** Check if role is assigned to a user.
     *
     * @return true if role is assigned to a user, otherwise false.
     * @param roleId role ID
     */
    boolean isRoleAssignedToUser(Long roleId);

    /**
     * Check if role has active childs.
     *
     * @return true if role has active childs, otherwise false.
     * @param roleId role ID
     */
    boolean isRoleHasActiveChild(Long roleId);

    /**
     * Find Roles By Group ID.
     *
     * @return Search Page Value Object
     * @param pageNo pagination page Number
     * @param groupId group ID
     */
    SearchPageVO findByGroupId(int pageNo, Long groupId);

    /**
     * Update Role Status.
     *
     * @return Role VO
     * @param roleVO role VO
     */
    RoleVO updateStatus(RoleVO vo);

    /**
     * Get Role by URL.
     *
     * @param url Page-Role URL.
     * @return Role Info.
     */
    RoleVO getPrivatePage(String url);

    /**
     * Is Role Exist
     *
     * @param url Page URL
     * @return True if page url has Role
     */
    boolean isRoleExist(String url);

    /**
     * Get Sensitive Childrens Of Role.
     *
     * @param roleId : role Id.
     * @param userId : user Id.
     *
     * @return List of Childrens Roles.
     */
    List getSensitiveChildrensOfRole(Long roleId, Long userId);

    /**
     * Get Childrens Of Role.
     *
     * @param pageUrl : page Url.
     *
     * @return List of Childrens Roles.
     */
    List getChildrensOfRole(String pageUrl, Long userId, Integer objSeq);

    /**
     * Search
     *
     * @param roleCode : Role Code.
     *
     * @return List of Role VO
     */
    List<RoleVO> search(String roleCode, String roleDesc);

    /**
     * Get all roles that can be denied access to temporarily.
     *
     *  @param roleId
     * @return List of can be denied roles.
     */
    List<RoleVO> getCanBeDeniedRoles(Long roleId);

    /**
     * lookup for roles based on query search
     * @param query : text to search
     * @param lang : language for logged-in user
     * @param paramsMap : extra where condition Parameters Map
     * @return list of roles matches this query
     */
    List<RoleVO> lookup(String query, String lang, int pageSize, HashMap<String,String> paramsMap);

    /**
     *  validate (id, text) for role recived from lookup componant
     * @param id : id of the country
     * @param text : role description based on language
     * @param lang : language for loogged-in user
     * @return true if data is valid
     */
    public boolean validateLookup(String id, String text, String lang);

    /**
     * Get role info related to this newURL
     * 
     * @param newURL New-URL related to the requested role
     * @return role info related to this newURL
     */
    RoleVO getByNewURL(String newURL);

    /**
     * Get URLs related to pages migrated from old traffic system with custom security logic inside the old pages.
     * This Security-Mode will be used by security filter to apply the custome security logic on such URLs for backword
     * compatability
     * 
     * @return Map<URL, SecurityMode>
     */
    Map<String, Integer> getSecurityModeURLs();
}
