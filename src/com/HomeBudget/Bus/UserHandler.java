package com.HomeBudget.Bus;

import java.util.ArrayList;

import com.HomeBudget.DAO.UserDAO;
import com.HomeBudget.DAO.UserDAOImpl;
import com.dataObject.UserVO;

public class UserHandler {

	private UserDAO userDAO=null;
	public UserHandler(){
		userDAO=new UserDAOImpl();
	}
	public ArrayList<UserVO> getAll()
	{
		return userDAO.getAllUsers();
	}
	
	public void add(UserVO userVo) throws Exception
	{
		userDAO.addUser(userVo);
	}
	
	public boolean checkMail(String mail) throws Exception
	{
		return userDAO.checkMail(mail);
	}
}
