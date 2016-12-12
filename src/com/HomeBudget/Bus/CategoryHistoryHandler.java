/**
 * 
 */
package com.HomeBudget.Bus;

import java.util.ArrayList;
import java.util.List;

import com.HomeBudget.DAO.CategoryDAO;
import com.HomeBudget.DAO.CategoryDAOImpl;
import com.HomeBudget.DAO.CategoryHistoryDAO;
import com.HomeBudget.DAO.CategoryHistoryDAOImpl;
import com.HomeBudget.DAO.PurchaseDAO;
import com.HomeBudget.DAO.PurchaseDAOImpl;
import com.HomeBudget.DAO.PurchaseHistoryDAO;
import com.HomeBudget.DAO.PurchaseHistoryDAOImpl;
import com.HomeBudget.DAO.JPA.DataAccessObject1;
import com.HomeBudget.dataObject.CategoryHistoryVO;
import com.HomeBudget.dataObject.PurchaseHistoryVO;
import com.dataObject.BusinessException;
import com.dataObject.BusinessObject;
import com.dataObject.CategoryVO;
import com.dataObject.DAOFactory;
import com.dataObject.DataAccessException;
import com.dataObject.PurchaseVO;

/**
 * @author Shehab
 *
 */
public class CategoryHistoryHandler extends BusinessObject{
	private CategoryHistoryDAO categoryHistoryDAO;
	
	public CategoryHistoryHandler()
	{
		super();
	}
	
	public void add(CategoryVO categoryVO,DataAccessObject1 parentDao) throws Exception
	{
		 if(categoryVO==null)
		 {
		 throw new BusinessException("Null categoryVO");
		 }
		 categoryHistoryDAO=(CategoryHistoryDAO)getDAO(CategoryHistoryDAO.class,parentDao);
		 categoryHistoryDAO.addCategoryHistory(categoryVO);
 
	}
	public void update(CategoryHistoryVO categoryHistoryVO) throws Exception
	{
		categoryHistoryDAO=(CategoryHistoryDAO)getDAO(CategoryHistoryDAO.class);
		categoryHistoryDAO.updateCategoryHistory(categoryHistoryVO);
	}
	public List<CategoryHistoryVO> getAll(int categoryHistoryId) throws Exception
	{  
		categoryHistoryDAO=(CategoryHistoryDAO)getDAO(CategoryHistoryDAO.class);
		return categoryHistoryDAO.getAllCategoriesHistory(categoryHistoryId);
	}
}
