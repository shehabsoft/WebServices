package com.HomeBudget.Bus;

import java.util.ArrayList;

import com.HomeBudget.DAO.CategoryDAO;
import com.HomeBudget.DAO.CategoryDAOImpl;
import com.HomeBudget.DAO.JPA.DataAccessObject1;
import com.dataObject.BusinessException;
import com.dataObject.BusinessObject;
import com.dataObject.CategoryVO;
import com.dataObject.DAOFactory;
import com.dataObject.DataAccessException;
import com.dataObject.MonthlyBudgetVO;

public class CategoryHandler extends BusinessObject{
	
	
	private CategoryHistoryHandler categoryHistoryHandler=null;
 	private MonthlyBudgetHandler monthlyBudgetHandler=null;
	public void add(CategoryVO categoryVO) throws Exception {
		   
	 CategoryDAO categoryDAO=null;
		 if(categoryVO==null)
			 {
			 throw new BusinessException("Null categoryVO");
			 }
	   try { 
		    categoryHistoryHandler=new CategoryHistoryHandler();
			categoryDAO = (CategoryDAO)getDAO(CategoryDAO.class);
			categoryDAO.addCategory(categoryVO);
			categoryHistoryHandler.add(categoryVO,categoryDAO);
			categoryDAO.commit();
	     }catch (DataAccessException ex) {
	       throw ex;
	     } catch (BusinessException ex) {
	            throw ex;
	      } catch (Exception ex) {
	            throw new BusinessException(ex);
	      } finally {
	        close(categoryDAO);
	      }
	}
	public void update(CategoryVO categoryVO)throws Exception 
	{  
	 if(categoryVO==null)
	  {
	   throw new BusinessException("Null categoryVO");
	  }
	  CategoryDAO categoryDAO=null;
	  try {
			categoryDAO = (CategoryDAO) DAOFactory.getDAO(CategoryDAO.class);
		    categoryDAO.updateCategory(categoryVO);
		    monthlyBudgetHandler=new MonthlyBudgetHandler();
		    boolean updatedValues=monthlyBudgetHandler.update(categoryVO, categoryDAO);
		   if(updatedValues)
		   {
			 categoryHistoryHandler=new CategoryHistoryHandler();
			 categoryHistoryHandler.add(categoryVO,categoryDAO);
		   }
		    categoryDAO.commit();
	  }catch (DataAccessException ex) {
	       throw ex;
	     } catch (BusinessException ex) {
	            throw ex;
	      } catch (Exception ex) {
	            throw new BusinessException(ex);
	      } finally {
	        close(categoryDAO);
	      }
	}
	
	public void updateCategoryActualValue(MonthlyBudgetVO monthlyBudgetVO,DataAccessObject1 parentDao)throws Exception
	{
		if(monthlyBudgetVO==null)
		  {
		   throw new BusinessException("Null monthlyBudgetVO");
		  }
		  CategoryDAO categoryDAO=null;
		categoryDAO = (CategoryDAO) DAOFactory.getDAO(CategoryDAO.class,parentDao);
		categoryDAO.updateCategoryActualValue(monthlyBudgetVO);
		
	}
	public CategoryVO getById(int id)
	{
	    CategoryDAO categoryDAO=null;
		return categoryDAO.getCategoryById(id);
	}
	
	public ArrayList<CategoryVO> getExpensesCategories(int monthlyBudgetId,int userId)
	{   CategoryDAO categoryDAO=null;
	   try {
			categoryDAO = (CategoryDAO) DAOFactory.getDAO(CategoryDAO.class);
			return categoryDAO.getExpensesCategories(monthlyBudgetId, userId);
	       }catch (DataAccessException ex) {
	       throw ex;
	     } catch (BusinessException ex) {
	            throw ex;
	      } catch (Exception ex) {
	            throw new BusinessException(ex);
	      } finally {
	        close(categoryDAO);
	      }
		
	}
	public ArrayList<CategoryVO> getAllExpensesCategories(int userId)
	{    
		CategoryDAO categoryDAO=null;
	 try {
		  categoryDAO = (CategoryDAO) DAOFactory.getDAO(CategoryDAO.class);
		  return categoryDAO.getAllExpensesCategories(userId);
	 }catch (DataAccessException ex) {
	       throw ex;
	     } catch (BusinessException ex) {
	            throw ex;
	      } catch (Exception ex) {
	            throw new BusinessException(ex);
	      } finally {
	        close(categoryDAO);
	      }
	}
	public ArrayList<CategoryVO> getBudgetCategories(int monthlyBudgetId,int userId)
	{   
		CategoryDAO categoryDAO=null;
		 try {
			  categoryDAO = (CategoryDAO) DAOFactory.getDAO(CategoryDAO.class);
		      return categoryDAO.GetBudgetCategories(monthlyBudgetId, userId);
		 }catch (DataAccessException ex) {
		       throw ex;
		     } catch (BusinessException ex) {
		            throw ex;
		      } catch (Exception ex) {
		            throw new BusinessException(ex);
		      } finally {
		        close(categoryDAO);
		      }
	}
	
	public ArrayList<CategoryVO> getAllBudgetCategories(int userId)
	{  
		CategoryDAO categoryDAO=null;
		 try {
			  categoryDAO = (CategoryDAO) DAOFactory.getDAO(CategoryDAO.class);
		      return categoryDAO.GetAllBudgetCategories(userId);
		 }catch (DataAccessException ex) {
		       throw ex;
		     } catch (BusinessException ex) {
		            throw ex;
		      } catch (Exception ex) {
		            throw new BusinessException(ex);
		      } finally {
		        close(categoryDAO);
		      }
	}
}
