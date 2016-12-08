/**
 * 
 */
package com.HomeBudget.Bus;

import java.util.ArrayList;
import java.util.List;

import com.HomeBudget.DAO.CategoryDAOImp;
import com.HomeBudget.DAO.CategoryHistoryDAO;
import com.HomeBudget.DAO.CategoryHistoryDAOImpl;
import com.HomeBudget.DAO.PurchaseDAO;
import com.HomeBudget.DAO.PurchaseDAOImpl;
import com.HomeBudget.DAO.PurchaseHistoryDAO;
import com.HomeBudget.DAO.PurchaseHistoryDAOImpl;
import com.HomeBudget.dataObject.CategoryHistoryVO;
import com.HomeBudget.dataObject.PurchaseHistoryVO;
import com.dataObject.CategoryVO;
import com.dataObject.PurchaseVO;

/**
 * @author Shehab
 *
 */
public class CategoryHistoryHandler {
	private CategoryHistoryDAO categoryHistoryDAO;
	
	public CategoryHistoryHandler()
	{
		super();
		categoryHistoryDAO=new CategoryHistoryDAOImpl();
	}
	
	public void add(CategoryVO categoryVO) throws Exception
	{
		//categoryHistoryDAO.addCategoryHistory(categoryVO);
	}
	public void update(CategoryHistoryVO categoryHistoryVO) throws Exception
	{
		categoryHistoryDAO.updateCategoryHistory(categoryHistoryVO);
	}
	public List<CategoryHistoryVO> getAll(int categoryHistoryId) throws Exception
	{
		return categoryHistoryDAO.getAllCategoriesHistory(categoryHistoryId);
	}
}
