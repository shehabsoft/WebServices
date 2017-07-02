package com.HomeBudget.Bus;

import java.util.ArrayList;

import com.HomeBudget.DAO.UserDAO;
import com.HomeBudget.DAO.UserDAOImpl;
import com.dataObject.BusinessException;
import com.dataObject.BusinessObject;
import com.dataObject.DataAccessException;
import com.dataObject.UserVO;

public class UserHandler extends BusinessObject{
	public ArrayList<UserVO> getAll()
	{
		 UserDAO userDAO=null;
		 try
		 {
		  userDAO=(UserDAO)getDAO(UserDAO.class);
		  return userDAO.getAllUsers();
		 }catch (DataAccessException ex) {
		  throw ex;
		  } catch (BusinessException ex) {
		   throw ex;
		   } catch (Exception ex) {
		   throw new BusinessException(ex);
		   } finally {
		  close(userDAO);
		   }
	}
	
	public void add(UserVO userVo) throws Exception
	{

		 UserDAO userDAO=null;
		 try
		 {
		  userDAO=(UserDAO)getDAO(UserDAO.class);
		  userDAO.addUser(userVo);
		  userDAO.commit();
		 }catch (DataAccessException ex) {
		  throw ex;
		  } catch (BusinessException ex) {
		   throw ex;
		   } catch (Exception ex) {
		   throw new BusinessException(ex);
		   } finally {
		  close(userDAO);
		   }
		
	}
	
	public boolean checkMail(String mail) throws Exception
	{
		UserDAO userDAO=null;
		 try
		 {
		  userDAO=(UserDAO)getDAO(UserDAO.class);
		  return userDAO.checkMail(mail);
		 }catch (DataAccessException ex) {
		  throw ex;
		  } catch (BusinessException ex) {
		   throw ex;
		   } catch (Exception ex) {
		   throw new BusinessException(ex);
		   } finally {
		  close(userDAO);
		   }
		
		
	}
	public UserVO checkAccount(String email,String password) throws Exception
	{
		UserDAO userDAO=null;
		 try
		 {
		  userDAO=(UserDAO)getDAO(UserDAO.class);
		  return userDAO.checkAccount(email, password);
		 }catch (DataAccessException ex) {
		  throw ex;
		  } catch (BusinessException ex) {
		   throw ex;
		   } catch (Exception ex) {
		   throw new BusinessException(ex);
		   } finally {
		  close(userDAO);
		   }
		
		
	}
	public UserVO getActiveUser() throws Exception
	{
		UserDAO userDAO=null;
		 try
		 {
		  userDAO=(UserDAO)getDAO(UserDAO.class);
		  return userDAO.getActiveUser();
		 }catch (DataAccessException ex) {
		  throw ex;
		  } catch (BusinessException ex) {
		   throw ex;
		   } catch (Exception ex) {
		   throw new BusinessException(ex);
		   } finally {
		  close(userDAO);
		   }
		
	}
	
}
