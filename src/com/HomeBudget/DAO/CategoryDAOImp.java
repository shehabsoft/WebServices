package com.HomeBudget.DAO;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.dataObject.BusinessException;
import com.dataObject.CategoryVO;
import com.dataObject.Constants;
import com.dataObject.MonthlyBudgetVO;
import com.entities.models.Category;
import com.entities.models.CategoryHistory;
import com.entities.models.MonthlyBudget;
import com.entities.models.MonthlyBudgetCategory;
import com.entities.models.User;



public class CategoryDAOImp extends DataAccessObject implements CategoryDAO{

	public CategoryDAOImp()
	{
		super();
	}

	public void updateCategory(CategoryVO categoryVO)throws Exception
	{
		try
		{
			Category category=getEntitymanager().find(Category.class, categoryVO.getId());
			category.setEnglishDescription(categoryVO.getEnglishDescription());
			category.setArabicDescription(categoryVO.getArabicDescription());
			categoryVO.setCategoryTypeId(category.getCategoryTypeId());
			if(categoryVO.getCategoryStatus()!=Constants.STATUS_ACTIVE)
			{
				category.setCategoryStatus(categoryVO.getCategoryStatus());	
				getEntitymanager().getTransaction().begin();
				getEntitymanager().persist(category);
				getEntitymanager().getTransaction().commit();
				
			}else
			{
					if(categoryVO.getPlanedValue()>categoryVO.getLimitValue())
					{
						throw new BusinessException("Planed Value Should Be Less Than Limit Value");
					}
					
					
					getEntitymanager().getTransaction().begin();
					getEntitymanager().persist(category);
					//update Monthly Budget Category
					MonthlyBudgetDAOImpl monthlyBudgetSession=new MonthlyBudgetDAOImpl();
					
					MonthlyBudgetVO monthlyBudgetVo=monthlyBudgetSession.getActiveMonthlyBudgetByUserId(categoryVO.getUserId());
					Query query = (Query) getEntitymanager().createNamedQuery("getMonthlyBudgetCategoryByMonthlyBudgetIdAndCategoryId");
					query.setParameter("id", monthlyBudgetVo.getId());
					query.setParameter("categoryId", category.getId());
					CategoryHistoryDAO categoryHistoryDAO=new CategoryHistoryDAOImpl();
					CategoryHistory categoryHistory=null;
					
					if(category.getCategoryTypeId()==Constants.CATEGORY_TYPE_EXPENSES_ID)//Expenses
					{
						List<MonthlyBudgetCategory>budgetCategories=query.getResultList();
						for(MonthlyBudgetCategory budgetCategory:budgetCategories)
						{
							//add new Category History If Changing in planned value or limit Value 
							if(categoryVO.getPlanedValue()!=budgetCategory.getPlanedValue()||categoryVO.getLimitValue()!=budgetCategory.getLimitValue())
							{
								category.setActualValue(categoryVO.getActualValue());
								category.setPlanedValue(categoryVO.getPlanedValue());
								category.setLimitValue(categoryVO.getLimitValue());
								categoryHistory=categoryHistoryDAO.addCategoryHistory(category);
								getEntitymanager().persist(categoryHistory);
							}
							 budgetCategory.setPlanedValue(categoryVO.getPlanedValue());
							 budgetCategory.setLimitValue(categoryVO.getLimitValue());
							 getEntitymanager().persist(budgetCategory);
						
						}
					}else
					{
						List<MonthlyBudgetCategory>budgetCategories=query.getResultList();
						for(MonthlyBudgetCategory budgetCategory:budgetCategories)
						{
							boolean updatedValues=false;
							//add new Category History If Changing in planned value or limit Value 
							if(categoryVO.getPlanedValue()!=budgetCategory.getPlanedValue()||categoryVO.getLimitValue()!=budgetCategory.getLimitValue())
							{
							 budgetCategory.setPlanedValue(categoryVO.getPlanedValue());
							 budgetCategory.setLimitValue(categoryVO.getLimitValue());
							 updatedValues=true;
							} 
							 if(category.getActualValue()!=categoryVO.getActualValue())//update Actual Value in Monthly Budget Table 
							 {
								 budgetCategory.setActualValue(categoryVO.getActualValue());
								 MonthlyBudget monthlyBudget=getEntitymanager().find(MonthlyBudget.class, monthlyBudgetVo.getId());
								 double difference=categoryVO.getActualValue()-category.getActualValue();
								 category.setActualValue(categoryVO.getActualValue());
								 getEntitymanager().persist(category);
								 monthlyBudget.setTotalIncome(monthlyBudget.getTotalIncome()+difference);
								
								 getEntitymanager().persist(monthlyBudget);
								 updatedValues=true; 
							 }
							 if(updatedValues)
							 {
							 categoryHistory=categoryHistoryDAO.addCategoryHistory(category);
							 getEntitymanager().persist(categoryHistory);
							 }
							 getEntitymanager().persist(budgetCategory);
						
						}
					}
					if(getEntitymanager().getTransaction().isActive())
					{
					getEntitymanager().getTransaction().commit();
					}else
					{
						getEntitymanager().getTransaction().begin();
						getEntitymanager().getTransaction().commit();
					}
					
			}	
			
					}catch(Exception e)
					{
						throw new Exception(e);
					}
		}
		
	
	public void addCategory(CategoryVO categoryVO) throws Exception
	{
		
	
		Category category=new Category();
		category.setCategoryStatus(categoryVO.getCategoryStatus());
		category.setCategoryTypeId(categoryVO.getCategoryTypeId());
		int userId=categoryVO.getUserId();
		if(userId!=0)
		{
			User user=getEntitymanager().find(User.class, userId);
			if(user==null)
			{
				throw new BusinessException("Null User with ID"+userId);
			}
			category.setUser(user);
		}else
		{
			throw new Exception("User  Should not be Null");
		}
		if(categoryVO.getArabicDescription()!=null)
		{
		category.setArabicDescription(categoryVO.getArabicDescription());
		}else
		{
			throw new Exception("Category Arabic Description Should not be Null");
		}
		if(categoryVO.getEnglishDescription()!=null)
		{
		category.setEnglishDescription(categoryVO.getEnglishDescription());
		}else
		{
			throw new Exception("Category English Description Should not be Null");
		}
		if(categoryVO.getPlanedValue()>0)
		{
		category.setPlanedValue(categoryVO.getPlanedValue());
		}else
		{
			throw new Exception("Planed Value Should not be Enter");
		}
		if(categoryVO.getLimitValue()>=categoryVO.getPlanedValue())
		{
		category.setLimitValue(categoryVO.getLimitValue());
		}else
		{
			if(categoryVO.getCategoryTypeId()==2)
			{
			throw new Exception("Planed Value Should  be Less Than Limit Value");
			}else
			{
				category.setLimitValue(categoryVO.getLimitValue());
			}
		}
		if(categoryVO.getActualValue()<categoryVO.getPlanedValue())
		{
		category.setActualValue(categoryVO.getActualValue());
		}else
		{
			if(categoryVO.getCategoryTypeId()==2)
			{
			throw new Exception("Actual Value Should  be Less Than PLaned Value");
			}
			else
			{
				category.setActualValue(categoryVO.getActualValue());
			}
		}
		if(categoryVO.getParentCategoryId()!=0)
		{
		category.setParentCategoryId(categoryVO.getParentCategoryId());
		}
		if(categoryVO.getParentCategoryId()!=0)
		{
		category.setParentCategoryId(categoryVO.getParentCategoryId());
		}
		
		CategoryHistoryDAO categoryHistoryDAO=new CategoryHistoryDAOImpl();
		categoryHistoryDAO.addCategoryHistory(category);
		getEntitymanager().getTransaction().begin();
		getEntitymanager().persist(category);
		getEntitymanager().getTransaction().commit();
	}
	public CategoryVO getCategoryById(int id)
	{
		try
		{
		
			getEntitymanager().getTransaction().begin();
		Query query = (Query) getEntitymanager().createNamedQuery("findCategotyById").setParameter("id", id);
		Category category=(Category)query.getSingleResult();
		CategoryVO categoryVO=new CategoryVO();
		categoryVO.setActualValue(category.getActualValue());
		categoryVO.setArabicDescription(category.getArabicDescription());
		categoryVO.setCategoryStatus(category.getCategoryStatus());
		categoryVO.setLimitValue(category.getLimitValue());
		categoryVO.setPlanedValue(category.getPlanedValue());
		categoryVO.setEnglisDescription(category.getEnglishDescription());
		categoryVO.setId(category.getId());
		return categoryVO;
	}catch(Exception e)
		{
		System.out.println(e);
		throw new BusinessException(e.toString());
		}
	}
	public ArrayList<CategoryVO> getExpensesCategories(int monthlyBudgetId,int userId)
	{
		try
		{
		ArrayList<CategoryVO>categoryVOs=new ArrayList<CategoryVO>();
		Query query = (Query) getEntitymanager().createNamedQuery("findExpensesCategories");
		query.setParameter("id", monthlyBudgetId);
		query.setParameter("userId", userId);
	    List<Object> result = query.getResultList();
	    Iterator itr = result.iterator();
	    while(itr.hasNext()){
	        CategoryVO categoryVO=new CategoryVO();
	        Object[] obj = (Object[]) itr.next();
		    	 	categoryVO.setArabicDescription(String.valueOf(obj[1]));
	    	categoryVO.setEnglisDescription(String.valueOf(obj[2]));
	    	categoryVO.setCategoryTypeId(Integer.parseInt(String.valueOf(obj[3])));
		    categoryVO.setActualValue(Double.parseDouble(String.valueOf(obj[5])));
		    categoryVO.setLimitValue(Double.parseDouble(String.valueOf(obj[6])));
		    categoryVO.setPlanedValue(Double.parseDouble(String.valueOf(obj[7])));
		    categoryVO.setId(Integer.parseInt(String.valueOf(obj[0])));
		    categoryVO.setCategoryStatus(2);
		    if(categoryVO.getActualValue()<=categoryVO.getLimitValue())
			{
				categoryVO.setWithenLimit(true);
			}else
			{
				categoryVO.setWithenLimit(false);
			}
			    categoryVOs.add(categoryVO);
		   
		    }

	
		return categoryVOs;
	}catch(Exception e)
		{
		System.out.println(e);
		throw new BusinessException(e.toString());
		}
	}
	public ArrayList<CategoryVO> getAllExpensesCategories(int userId)
	{
		try
		{
		ArrayList<CategoryVO>categoryVOs=new ArrayList<CategoryVO>();
		Query query = (Query) getEntitymanager().createNamedQuery("findAllExpensesCategories");
		query.setParameter("userId", userId);
	    List<Category> categoryList =  query.getResultList();
	   for(Category category:categoryList)
	   {
	        CategoryVO categoryVO=new CategoryVO();
	    	categoryVO.setArabicDescription(category.getArabicDescription());
	    	categoryVO.setEnglisDescription(category.getEnglishDescription());
	    	categoryVO.setCategoryTypeId(category.getCategoryTypeId());
		    categoryVO.setActualValue(category.getActualValue());
		    categoryVO.setLimitValue(category.getLimitValue());
		    categoryVO.setPlanedValue(category.getPlanedValue());
		    categoryVO.setId(category.getId());
		    if(categoryVO.getActualValue()<=categoryVO.getLimitValue())
			{
				categoryVO.setCategoryStatus(2);
			}else
			{
				categoryVO.setCategoryStatus(1);
			}
		    categoryVOs.add(categoryVO);
	   
	    }

	
		return categoryVOs;
	}catch(Exception e)
		{
		System.out.println(e);
		throw new BusinessException(e.toString());
		}
	}
	public ArrayList<CategoryVO> GetBudgetCategories(int monthlyBudgetId,int userId)
	{
		try
		{
		ArrayList<CategoryVO>categoryVOs=new ArrayList<CategoryVO>();
		Query query = (Query) getEntitymanager().createNamedQuery("findBudgetCategories");
		query.setParameter("id", monthlyBudgetId);
		query.setParameter("userId", userId);
	    List<Object> result = query.getResultList();
	    Iterator itr = result.iterator();
	    while(itr.hasNext()){
	        CategoryVO categoryVO=new CategoryVO();
	        Object[] obj = (Object[]) itr.next();
		    categoryVO.setArabicDescription(String.valueOf(obj[1]));
	    	categoryVO.setEnglisDescription(String.valueOf(obj[2]));
	    	categoryVO.setCategoryTypeId(Constants.CATEGORY_TYPE_REVENUES_ID);
		    categoryVO.setActualValue(Double.parseDouble(String.valueOf(obj[5])));
		    categoryVO.setLimitValue(Double.parseDouble(String.valueOf(obj[6])));
		    categoryVO.setPlanedValue(Double.parseDouble(String.valueOf(obj[7])));
		    categoryVO.setId(Integer.parseInt(String.valueOf(obj[0])));
			    categoryVOs.add(categoryVO);
		   
		    }

	
		return categoryVOs;
	}catch(Exception e)
		{
		System.out.println(e);
		throw new BusinessException(e.toString());
		}
	}
	public ArrayList<CategoryVO> GetAllBudgetCategories(int userId)
	{
		try
		{
		ArrayList<CategoryVO>categoryVOs=new ArrayList<CategoryVO>();
		Query query = (Query) getEntitymanager().createNamedQuery("findAllBudgetCategories");
		query.setParameter("userId", userId);
	    List<Category> categoryList =  query.getResultList();
		for (Category category : categoryList) {
			CategoryVO categoryVO=new CategoryVO();
			categoryVO.setActualValue(category.getActualValue());
			categoryVO.setArabicDescription(category.getArabicDescription());
			categoryVO.setCategoryStatus(category.getCategoryStatus());
			categoryVO.setLimitValue(category.getLimitValue());
			categoryVO.setPlanedValue(category.getPlanedValue());
			categoryVO.setEnglisDescription(category.getEnglishDescription());
			categoryVO.setId(category.getId());
			categoryVOs.add(categoryVO);
		}

	
		return categoryVOs;
	}catch(Exception e)
		{
		System.out.println(e);
		throw new BusinessException(e.toString());
		}
	}
	public Category copyCategoryFromVO(CategoryVO categoryVO)
	{
		Category category=new Category();
		category.setActualValue(categoryVO.getActualValue());
		category.setArabicDescription(categoryVO.getArabicDescription());
		category.setCategoryStatus(categoryVO.getCategoryStatus());
		category.setLimitValue(categoryVO.getLimitValue());
		category.setPlanedValue(categoryVO.getPlanedValue());
		category.setEnglishDescription(categoryVO.getEnglishDescription());
		category.setId(categoryVO.getId());
		return category; 
	}
	

}
