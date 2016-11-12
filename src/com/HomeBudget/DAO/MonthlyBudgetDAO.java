package com.HomeBudget.DAO;

import java.util.List;

import com.dataObject.MonthlyBudgetVO;
import com.entities.models.MonthlyBudgetCategory;

public interface MonthlyBudgetDAO {

	public void addMonthlyBudget(MonthlyBudgetVO monthlyBudgetVO) throws Exception;
	
	public List<MonthlyBudgetCategory> getMonthlyBudgetCategoriesById(int monthlyBudgetId);
	
	public boolean updateMonthlyBudget(MonthlyBudgetVO newmonthlyBudgetVO);
	
	public boolean deActivePreviousMonthlyBudget(int userId);
	
	public MonthlyBudgetVO getActiveMonthlyBudgetByUserId(int userId);
	
	public int getActiveMonthlyBudgetIdByUserId(int userId);
}
