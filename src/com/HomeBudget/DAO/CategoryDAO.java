/**
 * 
 */
package com.HomeBudget.DAO;

import java.util.ArrayList;

import com.HomeBudget.DAO.JPA.DataAccessObject1;
import com.dataObject.CategoryVO;
import com.dataObject.MonthlyBudgetVO;

/**
 * @author Shehab
 *
 */
public interface CategoryDAO extends DataAccessObject1 {
	
	public void updateCategory(CategoryVO categoryVO)throws Exception;
	public void updateCategoryActualValue(MonthlyBudgetVO monthlyBudgetVO)throws Exception;
	public void addCategory(CategoryVO categoryVO) throws Exception;
	
	public CategoryVO getCategoryById(int id);
	
	public ArrayList<CategoryVO> getExpensesCategories(int monthlyBudgetId,int userId,String year);
	
	public ArrayList<CategoryVO> getAllExpensesCategories(int userId);
	public ArrayList<CategoryVO> GetBudgetCategories(int monthlyBudgetId,int userId);
	
	public ArrayList<CategoryVO> GetAllBudgetCategories(int userId);
}
