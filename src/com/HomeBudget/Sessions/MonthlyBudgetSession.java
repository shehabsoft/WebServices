package com.HomeBudget.Sessions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;



import com.entities.models.*;
import com.dataObject.BusinessException;
import com.dataObject.CategoryVO;
import com.dataObject.MonthlyBudgetVO;
import com.dataObject.PurchaseVO;


public class MonthlyBudgetSession extends SessionFactory {
 
	public MonthlyBudgetSession()
	{
		super();
	}
	


	public void addMonthlyBudget(MonthlyBudgetVO monthlyBudgetVO) throws Exception
	{
		
	try
	{
		MonthlyBudget monthlyBudget=new MonthlyBudget();
		List<Category>categories=new ArrayList<Category>();
		monthlyBudget.setStartDate(new Date(monthlyBudgetVO.getStartDate()));
		String incomeCategoryList=monthlyBudgetVO.getIncomeCategoriesId().replace("[","").replace("]", "");
		String []incomeCategories =incomeCategoryList.split(",");
		String expenseCategoriesList=monthlyBudgetVO.getExpenseCategoriesId().replace("[","").replace("]", "");
		String [] expenseCategories =expenseCategoriesList.split(",");
		double totalIncome=0;
		double totalExpenese=0;
		for(int i=0;i<incomeCategories.length;i++)
		{
			 Category category=getEntitymanager().find(Category.class, Integer.parseInt(incomeCategories[i].trim()));
		     categories.add(category);
		     totalIncome+=category.getActualValue();
		   
		     
		}
		for(int i=0;i<expenseCategories.length;i++)
		{
			 Category category=getEntitymanager().find(Category.class, Integer.parseInt(expenseCategories[i].trim()));
		     categories.add(category);
		     
		    
		     
		}
		User user=getEntitymanager().find(User.class, 37);
		monthlyBudget.setUser(user);
		monthlyBudget.setTotalIncome(totalIncome);
		monthlyBudget.setTotalExpenses(totalExpenese);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		monthlyBudget.setEndDate(new Date(monthlyBudgetVO.getEndDate()));
		getEntitymanager().getTransaction().begin();
		deActivePreviousMonthlyBudget(37);
		monthlyBudget.setStatus(2);
		monthlyBudget.setCreationDate(new Date());
		monthlyBudget.setCategories(categories);
		getEntitymanager().persist(monthlyBudget);
		getEntitymanager().getTransaction().commit();
		int monthlyBudgetId=(int) (getNextKey()-1);
		getEntitymanager().getTransaction().begin();
		//copy (planed limit actual) Values From Category to MonthlyBudgetCategory Table
		Query query = (Query) getEntitymanager().createNamedQuery("getAllbyMonthlyBudget").setParameter("id", monthlyBudgetId);
		List<MonthlyBudgetCategory>budgetCategories=query.getResultList();
		for(MonthlyBudgetCategory budgetCategory:budgetCategories)
		{
			 Category category=getEntitymanager().find(Category.class,budgetCategory.getCategory().getId());
			 budgetCategory.setPlanedValue(category.getPlanedValue());
			 budgetCategory.setLimitValue(category.getLimitValue());
			 budgetCategory.setActualValue(category.getActualValue());
			 getEntitymanager().persist(budgetCategory);
		
		}
		
		getEntitymanager().getTransaction().commit();
		
	}catch(Exception e)
	{
	
		throw new Exception(e.toString());
	}
	}
	
		
	public boolean deActivePreviousMonthlyBudget(int userId)
	{
		Query query = (Query) getEntitymanager().createNamedQuery("deActivePreviosMonthlyBudget");
		query.setParameter("id", userId);
		int num=query.executeUpdate();
		if(num>0)
		return true;
			else
		return false;
	}
	public boolean updateMonthlyBudgetByPurchase(PurchaseVO purchaseVO) throws Exception
	{
		try
		{
			getEntitymanager().getTransaction().begin();
			Query query = (Query) getEntitymanager().createNamedQuery("getActiveMonthlyBudgetByUserId");
			query.setParameter("id", purchaseVO.getUserId());
			MonthlyBudget monthlyBudget=  (MonthlyBudget) query.getSingleResult();
			monthlyBudget.setTotalExpenses(monthlyBudget.getTotalExpenses()+purchaseVO.getUpdatedExpenseValue());
			getEntitymanager().getTransaction().commit();
			return true;
			
		}catch(Exception e)
		{
			throw new Exception(e.toString());
		}
	}
	
	public MonthlyBudgetVO getActiveMonthlyBudgetByUserId(int userId)
	{
		try
		{
			Query query = (Query) getEntitymanager().createNamedQuery("getActiveMonthlyBudgetByUserId");
			query.setParameter("id", userId);
			MonthlyBudgetVO monthlyBudgetVO=new MonthlyBudgetVO();
			List<MonthlyBudget> monthlyBudget=(List<MonthlyBudget>) query.getResultList();
			if(monthlyBudget.size()>0)
			{
			    monthlyBudgetVO=new MonthlyBudgetVO();
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    String creationDate=sdf.format( monthlyBudget.get(0).getCreationDate());
			    monthlyBudgetVO.setCreationDate(creationDate);
				monthlyBudgetVO.setStartDate(sdf.format(monthlyBudget.get(0).getStartDate()));
				monthlyBudgetVO.setEndDate(sdf.format(monthlyBudget.get(0).getEndDate()));
				monthlyBudgetVO.setCreationDate(sdf.format(monthlyBudget.get(0).getCreationDate()));
				monthlyBudgetVO.setTotalExpenses(monthlyBudget.get(0).getTotalExpenses());
				monthlyBudgetVO.setTotalIncomes(monthlyBudget.get(0).getTotalIncome());
				monthlyBudgetVO.setId(monthlyBudget.get(0).getId());
				
			}
			return monthlyBudgetVO;
			
		}catch(Exception e)
		{
			throw new BusinessException(e.toString());
		}
	}
	public int getActiveMonthlyBudgetIdByUserId(int userId)
	{
		try
		{
			Query query = (Query) getEntitymanager().createNamedQuery("getActiveMonthlyBudgetByUserId");
			query.setParameter("id", userId);
			List<MonthlyBudget> monthlyBudget=(List<MonthlyBudget>) query.getResultList();
			if(monthlyBudget.size()>0)
			{
			return monthlyBudget.get(0).getId();
			}else
			{
				return 0;
			}
			
		}catch(Exception e)
		{
			throw new BusinessException(e.toString());
		}
	}
	public Long getNextKey()
	{
		Query q = getEntitymanager().createNativeQuery("SELECT Auto_increment FROM information_schema.tables WHERE table_name='monthly_budget'");
		return (Long)q.getSingleResult();
	}
}
