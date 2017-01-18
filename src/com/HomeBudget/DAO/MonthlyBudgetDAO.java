package com.HomeBudget.DAO;

import java.util.List;

import com.HomeBudget.DAO.JPA.DataAccessObject1;
import com.dataObject.CategoryVO;
import com.dataObject.MonthlyBudgetCategoryVO;
import com.dataObject.MonthlyBudgetVO;
import com.dataObject.PurchaseVO;
import com.entities.models.MonthlyBudgetCategory;

public interface MonthlyBudgetDAO extends DataAccessObject1{

	public void addMonthlyBudget(MonthlyBudgetVO monthlyBudgetVO) throws Exception;
	
	public boolean closeMonthlyBudget(MonthlyBudgetVO monthlyBudgetVO) throws Exception;
	
	public List<MonthlyBudgetCategory> getMonthlyBudgetCategoriesById(int monthlyBudgetId);
	
	public boolean updateMonthlyBudget(MonthlyBudgetVO newmonthlyBudgetVO);

     public boolean updateMonthlyBudget(CategoryVO categoryVo) throws Exception;
     
     public void updateMonthlyBudget(PurchaseVO purchaseVO) throws Exception;
     
	public boolean deActivePreviousMonthlyBudget(int userId);
	
	public MonthlyBudgetVO getActiveMonthlyBudgetByUserId(int userId);
	
	public int getActiveMonthlyBudgetIdByUserId(int userId);
	
	public List<MonthlyBudgetVO>getAllMonthlyBudgetByUserId(int userId);
	
	public List<MonthlyBudgetVO>getAllMonthlyBudgetByCategoryIdAndUserId(int categoryId,int userId);
}
