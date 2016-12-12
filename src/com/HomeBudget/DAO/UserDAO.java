/**
 * 
 */
package com.HomeBudget.DAO;

import java.util.ArrayList;

import com.HomeBudget.DAO.JPA.DataAccessObject1;
import com.dataObject.UserVO;

/**
 * @author Shehab
 *
 */
public interface UserDAO extends DataAccessObject1 {

	
	public ArrayList<UserVO> getAllUsers();
	
	public void addUser(UserVO userVo) throws Exception;
	public boolean checkMail(String mail) throws Exception;
	public UserVO getActiveUser();
}
