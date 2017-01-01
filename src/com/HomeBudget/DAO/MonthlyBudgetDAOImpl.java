package com.HomeBudget.DAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;



import com.entities.models.*;
import com.HomeBudget.DAO.JPA.JPADataAccessObject;
import com.dataObject.BusinessException;
import com.dataObject.CategoryVO;
import com.dataObject.Constants;
import com.dataObject.MonthlyBudgetVO;
import com.dataObject.PurchaseVO;


public class MonthlyBudgetDAOImpl  extends JPADataAccessObject implements MonthlyBudgetDAO {
 
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
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = formatter.parse(monthlyBudgetVO.getStartDate());
		monthlyBudget.setStartDate(date);
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
		Query queryUser = (Query) getEntitymanager().createNamedQuery("getActiveUser");
		User user=(User)queryUser.getResultList().get(0);
		monthlyBudget.setUser(user);
		monthlyBudget.setTotalIncome(totalIncome);
		monthlyBudget.setTotalExpenses(totalExpenese);
		Date enddate = formatter.parse(monthlyBudgetVO.getEndDate());
		monthlyBudget.setEndDate(enddate);
		deActivePreviousMonthlyBudget(user.getId());
		monthlyBudget.setStatus(2);
		monthlyBudget.setCreationDate(new Date());
		monthlyBudget.setCategories(categories);
		int monthlyBudgetId=(int) (getNextKey()-0);
		
		monthlyBudget.setId(monthlyBudgetId);
		getEntitymanager().persist(monthlyBudget);
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
	public boolean updateMonthlyBudget(CategoryVO categoryVo) throws Exception
	{
		 Category category=getEntitymanager().find(Category.class, categoryVo.getId());
		 MonthlyBudgetDAOImpl monthlyBudgetSession=new MonthlyBudgetDAOImpl();
		 MonthlyBudgetVO monthlyBudgetVo=monthlyBudgetSession.getActiveMonthlyBudgetByUserId(categoryVo.getUserId());
		 Query query = (Query) getEntitymanager().createNamedQuery("getMonthlyBudgetCategoryByMonthlyBudgetIdAndCategoryId");
		 query.setParameter("id", monthlyBudgetVo.getId());
		 query.setParameter("categoryId", category.getId());
		 boolean updatedValues=false;
			if(category.getCategoryTypeId()==Constants.CATEGORY_TYPE_EXPENSES_ID)//Expenses
			{
				List<MonthlyBudgetCategory>budgetCategories=query.getResultList();
				for(MonthlyBudgetCategory budgetCategory:budgetCategories)
				{
					if(budgetCategory.getPlanedValue()!=categoryVo.getPlanedValue()||budgetCategory.getLimitValue()!=categoryVo.getLimitValue())
					 {
					 updatedValues=true;
					 budgetCategory.setPlanedValue(categoryVo.getPlanedValue());
					 budgetCategory.setLimitValue(categoryVo.getLimitValue());
					 getEntitymanager().persist(budgetCategory);
					 }
				
				}
			}else
			{
				List<MonthlyBudgetCategory>budgetCategories=query.getResultList();
				for(MonthlyBudgetCategory budgetCategory:budgetCategories)
				{
					//add new Category History If Changing in planned value or limit Value 
					if(categoryVo.getPlanedValue()!=budgetCategory.getPlanedValue()||categoryVo.getLimitValue()!=budgetCategory.getLimitValue())
					{
					 budgetCategory.setPlanedValue(categoryVo.getPlanedValue());
					 budgetCategory.setLimitValue(categoryVo.getLimitValue());
					 updatedValues=true;
					} 
					 if(budgetCategory.getActualValue()!=categoryVo.getActualValue())//update Actual Value in Monthly Budget Table 
					 {
						 budgetCategory.setActualValue(categoryVo.getActualValue());
						 MonthlyBudget monthlyBudget=getEntitymanager().find(MonthlyBudget.class, monthlyBudgetVo.getId());
						 double difference=categoryVo.getActualValue()-category.getActualValue();
						 category.setActualValue(categoryVo.getActualValue());
						 getEntitymanager().persist(category);
						 monthlyBudget.setTotalIncome(monthlyBudget.getTotalIncome()+difference);
						 getEntitymanager().persist(monthlyBudget);
						 updatedValues=true; 
					 }
					 getEntitymanager().persist(category);
					 getEntitymanager().persist(budgetCategory);
					 return updatedValues;
				
				}
				
			}
		return updatedValues;
		
	}
	public boolean updateMonthlyBudget(MonthlyBudgetVO newmonthlyBudgetVO)
	{
		//get old Monthly Budget by Id
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
			    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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
		Query q = getEntitymanager().createNativeQuery("SELECT Auto_increment FROM information_schema.tables WHERE table_name='monthly_budget' and Table_SCHEMA='homebudgetProduction'");
		return (Long)q.getSingleResult();
	}



	@Override
	public List<MonthlyBudgetVO> getAllMonthlyBudgetByUserId(int userId) {
		try
		{
			Query query = (Query) getEntitymanager().createNamedQuery("getAllMonthlyBudgetByUserId");
			query.setParameter("id", userId);
			MonthlyBudgetVO monthlyBudgetVO=null;
			List<MonthlyBudgetVO> monthlyBudgetVOList=new ArrayList<MonthlyBudgetVO>();
			List<MonthlyBudget> monthlyBudgetList=(List<MonthlyBudget>) query.getResultList();
		    Iterator itr = monthlyBudgetList.iterator();
 	    while(itr.hasNext()){
 	    	    MonthlyBudget monthlyBudget=  (MonthlyBudget) itr.next();
 	    	    monthlyBudgetVO=new MonthlyBudgetVO();
			    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			    String creationDate=sdf.format( monthlyBudget.getCreationDate());
			    monthlyBudgetVO.setCreationDate(creationDate);
				monthlyBudgetVO.setStartDate(sdf.format(monthlyBudget.getStartDate()));
				monthlyBudgetVO.setEndDate(sdf.format(monthlyBudget.getEndDate()));
				monthlyBudgetVO.setCreationDate(sdf.format(monthlyBudget.getCreationDate()));
				monthlyBudgetVO.setTotalExpenses(monthlyBudget.getTotalExpenses());
				monthlyBudgetVO.setTotalIncomes(monthlyBudget.getTotalIncome());
				monthlyBudgetVO.setId(monthlyBudget.getId());
				monthlyBudgetVOList.add(monthlyBudgetVO);
		}
		 
			return monthlyBudgetVOList;
			
		}catch(Exception e)
		{
			throw new BusinessException(e.toString());
		}
	}



	@Override
	public void updateMonthlyBudget(PurchaseVO purchaseVO) throws Exception {
		// TODO Auto-generated method stub
		 Purchase purchase=getEntitymanager().find(Purchase.class, purchaseVO.getId());
		 Query query = (Query) getEntitymanager().createNamedQuery("getActiveMonthlyBudgetByUserId");
		 query.setParameter("id", purchaseVO.getUserId());
		 MonthlyBudget monthlyBudget=  (MonthlyBudget) query.getSingleResult();
		 if(purchaseVO.getNewPrice()>0)//in case edit purchase new value will be added to monthly budget 
		 {
			 monthlyBudget.setTotalExpenses(monthlyBudget.getTotalExpenses()+purchaseVO.getNewPrice());
			 
		 }else
		 {
		   monthlyBudget.setTotalExpenses(monthlyBudget.getTotalExpenses()+purchaseVO.getPrice());
		 }
		 purchase.setMonthlyBudget(monthlyBudget);
		 getEntitymanager().persist(monthlyBudget);
			//update If The Category Changed 
			List<MonthlyBudgetCategory> monthlyBudgetCategoryList=null;
			if(purchase.getCategory().getId()!=purchaseVO.getCategoryId())
			{
				 //reduce value From Old Category in Table Monthly Budget Category 
				 query = (Query) getEntitymanager().createNamedQuery("getAllbyMonthlyBudget");
				 query.setParameter("id", monthlyBudget.getId());
				 monthlyBudgetCategoryList=  (List<MonthlyBudgetCategory>) query.getResultList();
				for(MonthlyBudgetCategory monthlyBudgetCategory:monthlyBudgetCategoryList)
				{
					if(purchase.getCategory().getId()==monthlyBudgetCategory.getCategory().getId())
					{
						monthlyBudgetCategory.setActualValue(monthlyBudgetCategory.getActualValue()-purchaseVO.getPrice());
						getEntitymanager().persist(monthlyBudgetCategory);
					}else if(purchaseVO.getCategoryId()==monthlyBudgetCategory.getCategory().getId())
					{
						monthlyBudgetCategory.setActualValue(monthlyBudgetCategory.getActualValue()+purchaseVO.getPrice());
						getEntitymanager().persist(monthlyBudgetCategory);
					}
					
				}
			}else //Update Monthly Budget in Case change In Actual Value
			{
				query = (Query) getEntitymanager().createNamedQuery("getAllbyMonthlyBudget");
				query.setParameter("id", monthlyBudget.getId());
				 monthlyBudgetCategoryList=  (List<MonthlyBudgetCategory>) query.getResultList();
				for(MonthlyBudgetCategory monthlyBudgetCategory:monthlyBudgetCategoryList)
				{
					if(purchase.getCategory().getId()==monthlyBudgetCategory.getCategory().getId())
					{
						double actualValue=monthlyBudgetCategory.getActualValue();//30
						if(purchaseVO.getNewPrice()==0)//if the new price is zero this mean that this is the first purchase in selected Expenses Category
						{
						    monthlyBudgetCategory.setActualValue(actualValue+purchaseVO.getPrice());//
						}else
						{
							monthlyBudgetCategory.setActualValue(actualValue+purchaseVO.getNewPrice());//
						}
						getEntitymanager().persist(monthlyBudgetCategory);
					}
				}
			}
		 
		 
		 
		 
	}



	@Override
	public boolean closeMonthlyBudget(MonthlyBudgetVO monthlyBudgetVO) throws BusinessException {
		// TODO Auto-generated method stub
		try
		{
			MonthlyBudget monthlyBudget=getEntitymanager().find(MonthlyBudget.class, monthlyBudgetVO.getId());
			monthlyBudget.setStatus(3);
		}catch(Exception e)
		{
			throw new BusinessException(e.toString());
		}
		return true;
		
	}
}
