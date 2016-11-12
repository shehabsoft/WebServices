/**
 * 
 */
package com.HomeBudget.DAO;

import java.util.ArrayList;

import com.dataObject.UserVO;

/**
 * @author Shehab
 *
 */
public interface UserDAO {

	
	public ArrayList<UserVO> getAllUsers();
	
	public void addUser(UserVO userVo) throws Exception;
	public boolean checkMail(String mail) throws Exception;
}
