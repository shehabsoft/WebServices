package com.HomeBudget.DAO;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.HomeBudget.DAO.JPA.JPADataAccessObject;
import com.dataObject.BusinessException;
import com.dataObject.CategoryVO;
import com.dataObject.Constants;
import com.dataObject.MonthlyBudgetVO;
import com.entities.models.Category;
import com.entities.models.CategoryHistory;
import com.entities.models.MonthlyBudget;
import com.entities.models.MonthlyBudgetCategory;
import com.entities.models.User;



public class CategoryDAOImpl extends JPADataAccessObject implements CategoryDAO{

	public CategoryDAOImpl()
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
				getEntitymanager().persist(category);
			}else
			{
			 if(categoryVO.getPlanedValue()>categoryVO.getLimitValue())
			   {
				 throw new BusinessException("Planed Value Should Be Less Than Limit Value");
			   }
			 if(categoryVO.getCategoryTypeId()!=Constants.CATEGORY_TYPE_REVENUES_ID)//update Actual Value In Case Of Revenes
			 {
				 category.setPlanedValue(categoryVO.getPlanedValue());
				 category.setLimitValue(categoryVO.getLimitValue());
			 } 
			 getEntitymanager().persist(category);
			}	
			 }catch(Exception e)
			  {
				throw new Exception(e);
			  }
		}
	public void updateCategoryActualValue(MonthlyBudgetVO monthlyBudgetVO)throws Exception
	{
		try
		{
			ArrayList <CategoryVO> incomecategoryVOs=(ArrayList<CategoryVO>) monthlyBudgetVO.getIncomeCategories();
			for(CategoryVO categoryVO: incomecategoryVOs)
			{
			Category category=getEntitymanager().find(Category.class, categoryVO.getId());
			 if(categoryVO.getNewValue()!=0)
			 {
			   category.setActualValue(categoryVO.getNewValue());
			   getEntitymanager().persist(category);
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
		if(categoryVO.getCategoryTypeId()==Constants.CATEGORY_TYPE_EXPENSES_ID&&categoryVO.getPlanedValue()>0)
		{
		category.setPlanedValue(categoryVO.getPlanedValue());
		}else 
		{
			if(categoryVO.getCategoryTypeId()==Constants.CATEGORY_TYPE_REVENUES_ID)
			{
				category.setPlanedValue(0);
			}else
			{
			throw new Exception("Planed Value Should not be Enter");
			}
		}
		if(categoryVO.getLimitValue()>=categoryVO.getPlanedValue())
		{
		category.setLimitValue(categoryVO.getLimitValue());
		}else
		{
			if(categoryVO.getCategoryTypeId()==Constants.CATEGORY_TYPE_EXPENSES_ID)
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
			if(categoryVO.getCategoryTypeId()==Constants.CATEGORY_TYPE_EXPENSES_ID)
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
		category.setId(Integer.parseInt(getNextKey().toString()));
		categoryVO.setId(category.getId());
		//CategoryHistoryDAO categoryHistoryDAO=new CategoryHistoryDAOImpl();
		//categoryHistoryDAO.addCategoryHistory(category);
		//getEntitymanager().getTransaction().begin();
		getEntitymanager().persist(category);
		//getEntitymanager().getTransaction().commit();
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
	public Long getNextKey()
	{
		Query q = getEntitymanager().createNativeQuery("SELECT Max(Auto_increment) FROM information_schema.tables WHERE table_name='category'");
		return (Long)q.getSingleResult();
	}

}
