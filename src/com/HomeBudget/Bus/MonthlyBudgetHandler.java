/**
 * 
 */
package com.HomeBudget.Bus;

import java.util.List;

import com.HomeBudget.DAO.MonthlyBudgetDAO;
import com.HomeBudget.DAO.MonthlyBudgetDAOImpl;
import com.dataObject.MonthlyBudgetVO;
import com.entities.models.MonthlyBudgetCategory;

/**
 * @author Shehab
 *
 */
public class MonthlyBudgetHandler {

	private MonthlyBudgetDAO monthlyBudgetDAO=null;
	public MonthlyBudgetHandler()
	{
		super();
		monthlyBudgetDAO=new MonthlyBudgetDAOImpl();
	}
	public void add(MonthlyBudgetVO monthlyBudgetVO) throws Exception
	{
		monthlyBudgetDAO.addMonthlyBudget(monthlyBudgetVO);
	}
	
	public List<MonthlyBudgetCategory> getMonthlyBudgetCategoriesById(int monthlyBudgetId)
	{
		return monthlyBudgetDAO.getMonthlyBudgetCategoriesById(monthlyBudgetId);
	}
	
	public boolean update(MonthlyBudgetVO newmonthlyBudgetVO)
	{
		return monthlyBudgetDAO.updateMonthlyBudget(newmonthlyBudgetVO);
	}
	
	public boolean deActivePreviousMonthlyBudget(int userId)
	{
		return monthlyBudgetDAO.deActivePreviousMonthlyBudget(userId);
	}
	
	public MonthlyBudgetVO getActiveMonthlyBudgetByUserId(int userId)
	{
		return monthlyBudgetDAO.getActiveMonthlyBudgetByUserId(userId);
	}
	
	public int getActiveMonthlyBudgetIdByUserId(int userId)
	{
		return monthlyBudgetDAO.getActiveMonthlyBudgetIdByUserId(userId);
	}
}
