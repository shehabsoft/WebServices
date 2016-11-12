package com.HomeBudget.Bus;

import java.util.ArrayList;

import com.HomeBudget.DAO.CategoryDAO;
import com.HomeBudget.DAO.CategoryDAOImp;
import com.dataObject.CategoryVO;

public class CategoryHandler {
	private CategoryDAO categoryDAO=null;
	public CategoryHandler()
	{
		categoryDAO=new CategoryDAOImp();
	}
	
	public void add(CategoryVO categoryVO) throws Exception {
	
		categoryDAO.addCategory(categoryVO);
	}
	public void update(CategoryVO categoryVO)throws Exception 
	{
		categoryDAO.updateCategory(categoryVO);
	}
	public CategoryVO getById(int id)
	{
		return categoryDAO.getCategoryById(id);
	}
	
	public ArrayList<CategoryVO> getExpensesCategories(int monthlyBudgetId,int userId)
	{
		return categoryDAO.getExpensesCategories(monthlyBudgetId, userId);
	}
	public ArrayList<CategoryVO> getAllExpensesCategories(int userId)
	{
		return categoryDAO.getAllExpensesCategories(userId);
	}
	public ArrayList<CategoryVO> getBudgetCategories(int monthlyBudgetId,int userId)
	{
		return categoryDAO.GetBudgetCategories(monthlyBudgetId, userId);
	}
	
	public ArrayList<CategoryVO> getAllBudgetCategories(int userId)
	{
		return categoryDAO.GetAllBudgetCategories(userId);
	}
}
