/**
 * 
 */
package com.HomeBudget.DAO;

import java.util.ArrayList;

import com.dataObject.CategoryVO;

/**
 * @author Shehab
 *
 */
public interface CategoryDAO {
	public void updateCategory(CategoryVO categoryVO)throws Exception;
	
	public void addCategory(CategoryVO categoryVO) throws Exception;
	
	public CategoryVO getCategoryById(int id);
	
	public ArrayList<CategoryVO> getExpensesCategories(int monthlyBudgetId,int userId);
	
	public ArrayList<CategoryVO> getAllExpensesCategories(int userId);
	public ArrayList<CategoryVO> GetBudgetCategories(int monthlyBudgetId,int userId);
	
	public ArrayList<CategoryVO> GetAllBudgetCategories(int userId);
}
