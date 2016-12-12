/**
 * 
 */
package com.HomeBudget.Bus;

import java.util.List;

import com.HomeBudget.DAO.MonthlyBudgetDAO;
import com.HomeBudget.DAO.MonthlyBudgetDAOImpl;
import com.HomeBudget.DAO.JPA.DataAccessObject1;
import com.dataObject.BusinessException;
import com.dataObject.BusinessObject;
import com.dataObject.CategoryVO;
import com.dataObject.DataAccessException;
import com.dataObject.MonthlyBudgetVO;
import com.dataObject.PurchaseVO;
import com.entities.models.MonthlyBudgetCategory;

/**
 * @author Shehab
 *
 */
public class MonthlyBudgetHandler extends BusinessObject{

	
	
	public void add(MonthlyBudgetVO monthlyBudgetVO) throws Exception
	{
		MonthlyBudgetDAO monthlyBudgetDAO=null;
		try {
			monthlyBudgetDAO=(MonthlyBudgetDAO)getDAO(MonthlyBudgetDAO.class);
			monthlyBudgetDAO.addMonthlyBudget(monthlyBudgetVO);
			monthlyBudgetDAO.commit();
		}catch (DataAccessException ex) {
		       throw ex;
		     } catch (BusinessException ex) {
		            throw ex;
		      } catch (Exception ex) {
		            throw new BusinessException(ex);
		      } finally {
		        close(monthlyBudgetDAO);
		      }
	}
	
	public List<MonthlyBudgetCategory> getMonthlyBudgetCategoriesById(int monthlyBudgetId)
	{
		MonthlyBudgetDAO monthlyBudgetDAO=null;
		try {
			monthlyBudgetDAO=(MonthlyBudgetDAO)getDAO(MonthlyBudgetDAO.class);
			return monthlyBudgetDAO.getMonthlyBudgetCategoriesById(monthlyBudgetId);
		}catch (DataAccessException ex) {
		   throw ex;
		  } catch (BusinessException ex) {
		    throw ex;
		 } catch (Exception ex) {
		    throw new BusinessException(ex);
		  } finally {
		    close(monthlyBudgetDAO);
		  }
	}
	
	public boolean update(MonthlyBudgetVO newmonthlyBudgetVO)
	{
		MonthlyBudgetDAO monthlyBudgetDAO=null;
		try {
			monthlyBudgetDAO=(MonthlyBudgetDAO)getDAO(MonthlyBudgetDAO.class);
			return monthlyBudgetDAO.updateMonthlyBudget(newmonthlyBudgetVO);
		}catch (DataAccessException ex) {
			   throw ex;
			  } catch (BusinessException ex) {
			    throw ex;
			 } catch (Exception ex) {
			    throw new BusinessException(ex);
			  } finally {
			    close(monthlyBudgetDAO);
			  }
	}
	public boolean update(CategoryVO categoryVO,DataAccessObject1 parentDao) throws Exception
	{ 
		MonthlyBudgetDAO monthlyBudgetDAO=null;
		monthlyBudgetDAO=(MonthlyBudgetDAO)getDAO(MonthlyBudgetDAO.class,parentDao);
		return monthlyBudgetDAO.updateMonthlyBudget(categoryVO);
	}
	 public boolean update(PurchaseVO purchaseVO,DataAccessObject1 parentDao) throws Exception
	  {
		 MonthlyBudgetDAO monthlyBudgetDAO=null;
		 monthlyBudgetDAO=(MonthlyBudgetDAO)getDAO(MonthlyBudgetDAO.class,parentDao);
		 monthlyBudgetDAO.updateMonthlyBudget(purchaseVO);
		 return true;
		   
	  }
	public boolean deActivePreviousMonthlyBudget(int userId)
	{
		MonthlyBudgetDAO monthlyBudgetDAO=null;
		try {
			monthlyBudgetDAO=(MonthlyBudgetDAO)getDAO(MonthlyBudgetDAO.class);
			boolean deactive= monthlyBudgetDAO.deActivePreviousMonthlyBudget(userId);
			if(deactive)
			{
				monthlyBudgetDAO.commit();
			}
			return deactive;
		}
		catch (DataAccessException ex) {
			   throw ex;
			  } catch (BusinessException ex) {
			    throw ex;
			 } catch (Exception ex) {
			    throw new BusinessException(ex);
			  } finally {
			    close(monthlyBudgetDAO);
			  }
	}
	
	public MonthlyBudgetVO getActiveMonthlyBudgetByUserId(int userId)
	{
		MonthlyBudgetDAO monthlyBudgetDAO=null;
		try {
			monthlyBudgetDAO=(MonthlyBudgetDAO)getDAO(MonthlyBudgetDAO.class);
			return monthlyBudgetDAO.getActiveMonthlyBudgetByUserId(userId);
		    }
		catch (DataAccessException ex) {
			   throw ex;
			  } catch (BusinessException ex) {
			    throw ex;
			 } catch (Exception ex) {
			    throw new BusinessException(ex);
			  } finally {
			    close(monthlyBudgetDAO);
			  }
	}
	
	public int getActiveMonthlyBudgetIdByUserId(int userId)
	{   
		MonthlyBudgetDAO monthlyBudgetDAO=null;
		try {
			monthlyBudgetDAO=(MonthlyBudgetDAO)getDAO(MonthlyBudgetDAO.class);
			return monthlyBudgetDAO.getActiveMonthlyBudgetIdByUserId(userId);
	    } 
		catch (DataAccessException ex) {
			   throw ex;
			  } catch (BusinessException ex) {
			    throw ex;
			 } catch (Exception ex) {
			    throw new BusinessException(ex);
			  } finally {
			    close(monthlyBudgetDAO);
			  }
	}
	public  List<MonthlyBudgetVO>getAllByUserId(int userId)
	{  
		MonthlyBudgetDAO monthlyBudgetDAO=null;
		try {
			monthlyBudgetDAO=(MonthlyBudgetDAO)getDAO(MonthlyBudgetDAO.class);
			return monthlyBudgetDAO.getAllMonthlyBudgetByUserId(userId);
		 } 
		catch (DataAccessException ex) {
			   throw ex;
			  } catch (BusinessException ex) {
			    throw ex;
			 } catch (Exception ex) {
			    throw new BusinessException(ex);
			  } finally {
			    close(monthlyBudgetDAO);
			  }
	}
}
