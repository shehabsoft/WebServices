package com.HomeBudget.DAO;

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
import com.dataObject.Constants;
import com.dataObject.MonthlyBudgetVO;
import com.dataObject.PurchaseVO;


public class MonthlyBudgetDAOImpl extends DataAccessObject implements MonthlyBudgetDAO {
 
	public MonthlyBudgetDAOImpl()
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
	
	public List<MonthlyBudgetCategory> getMonthlyBudgetCategoriesById(int monthlyBudgetId)
	{
		Query query = (Query) getEntitymanager().createNamedQuery("getAllbyMonthlyBudget").setParameter("id", monthlyBudgetId);
		List<MonthlyBudgetCategory>budgetCategories=query.getResultList();
		return budgetCategories;
		
		
	}
	public boolean updateMonthlyBudget(MonthlyBudgetVO newmonthlyBudgetVO)
	{
		//get old Monthly Budget by Id
		getEntitymanager().getTransaction().begin();
		MonthlyBudget oldmonthlyBudget=getEntitymanager().find(MonthlyBudget.class, newmonthlyBudgetVO.getId());
		//get old Monthly Budget Categories 
		List<MonthlyBudgetCategory>oldbudgetCategories=getMonthlyBudgetCategoriesById(oldmonthlyBudget.getId());
		//filter new Monthly Budget Categories
		String incomeCategoryList=newmonthlyBudgetVO.getIncomeCategoriesId().replace("[","").replace("]", "");
		String []incomeCategories =incomeCategoryList.split(",");
		String expenseCategoriesList=newmonthlyBudgetVO.getExpenseCategoriesId().replace("[","").replace("]", "");
		String [] expenseCategories =expenseCategoriesList.split(",");
		
		
		
		//filter with income Categories
		boolean changedInMonthlyBudget=false;
		boolean changedInMonthlyBudgetCategories=false;
		int categoryId=0;
		boolean found=false;
		for(int i=0;i<incomeCategories.length;i++)
		{
			found=false;
			categoryId=Integer.parseInt(incomeCategories[i].trim());
			for(MonthlyBudgetCategory monthlyBudgetCategory:oldbudgetCategories)
			{
				if(categoryId==monthlyBudgetCategory.getCategory().getId())
				{
					found=true;
					break;
					
				}
			}
			if(found==false)//add new Monthly Budget Category
			{
				MonthlyBudgetCategory newbudgetCategory=new MonthlyBudgetCategory();
				Category category=getEntitymanager().find(Category.class, categoryId);
				newbudgetCategory.setCategory(category);
				newbudgetCategory.setActualValue(category.getActualValue());
				newbudgetCategory.setMonthlyBudget(oldmonthlyBudget);
				newbudgetCategory.setPlanedValue(category.getPlanedValue());
				newbudgetCategory.setLimitValue(category.getLimitValue());
				newbudgetCategory.setMonthlyBudget(oldmonthlyBudget);
				if(category.getCategoryTypeId()==Constants.CATEGORY_TYPE_REVENUES_ID)//update monthly budget
				{
					oldmonthlyBudget.setTotalIncome(oldmonthlyBudget.getTotalIncome()+category.getActualValue());
					changedInMonthlyBudget=true;
				}
				changedInMonthlyBudgetCategories=true;
				getEntitymanager().persist(newbudgetCategory);
			}
			
		}
		//filter with Expenses Categorie
		for(int i=0;i<expenseCategories.length;i++)
		{
			found=false;
			categoryId=Integer.parseInt(expenseCategories[i].trim());
			for(MonthlyBudgetCategory monthlyBudgetCategory:oldbudgetCategories)
			{
				if(categoryId==monthlyBudgetCategory.getCategory().getId())
				{
					found=true;
					break;
					
				}
			}
			if(found==false)//add new Monthly Budget Category
			{
				Category category=getEntitymanager().find(Category.class, categoryId);
				MonthlyBudgetCategory newbudgetCategory=new MonthlyBudgetCategory();
				newbudgetCategory.setCategory(category);
				newbudgetCategory.setActualValue(category.getActualValue());
				newbudgetCategory.setMonthlyBudget(oldmonthlyBudget);
				newbudgetCategory.setPlanedValue(category.getPlanedValue());
				newbudgetCategory.setLimitValue(category.getLimitValue());
				getEntitymanager().persist(newbudgetCategory);
				changedInMonthlyBudgetCategories=true;
			}
			
		}
		
		if(changedInMonthlyBudget)
		{
		getEntitymanager().persist(oldmonthlyBudget);
		}
		if(changedInMonthlyBudgetCategories)
		{
		getEntitymanager().getTransaction().commit();
		}
		
		
		
		
		return true;
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
		Query q = getEntitymanager().createNativeQuery("SELECT Max(Auto_increment) FROM information_schema.tables WHERE table_name='monthly_budget'");
		return (Long)q.getSingleResult();
	}
}
