package com.HomeBudget.Sessions;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;



import com.entities.models.*;
import com.dataObject.CategoryVO;
import com.dataObject.MonthlyBudgetVO;


public class MonthlyBudgetSession {
//	
//	   @PersistenceContext(unitName="WebServices",type = PersistenceContextType.EXTENDED) 
//	   EntityManager entitymanager; 
	private EntityManagerFactory emfactory;
	private EntityManager entitymanager ;
	public MonthlyBudgetSession()
	{
		 emfactory = Persistence.createEntityManagerFactory("WebServices");
		 entitymanager = emfactory.createEntityManager();
	}
	public MonthlyBudgetSession(EntityManagerFactory emfactory,EntityManager entitymanager)
	{
		this.emfactory=emfactory;
		this.entitymanager=entitymanager;
	}
	public void updateCategory(CategoryVO categoryVO)throws Exception
	{
		try
		{
		Category category=new Category();
		category.setCategoryStatus(categoryVO.getCategoryStatus());
		category.setEnglishDescription(categoryVO.getEnglishDescription());
		category.setArabicDescription(categoryVO.getArabicDescription());
	    category.setPlanedValue(categoryVO.getPlanedValue());
		category.setActualValue(categoryVO.getActualValue());
		category.setParentCategoryId(categoryVO.getParentCategoryId());
		category.setLimitValue(categoryVO.getLimit_value());
		/*//entitymanager.persist(category);
		//entitymanager.getTransaction().commit();
*/		}catch(Exception e)
		{
			entitymanager.getTransaction().rollback();
			throw new Exception(e);
		}
		
	}
	public void addMonthlyBudget(MonthlyBudgetVO monthlyBudgetVO) throws Exception
	{
		
	try
	{
		MonthlyBudget monthlyBudget=new MonthlyBudget();
		List<Category>categories=new ArrayList<Category>();
		monthlyBudget.setStartDate(monthlyBudgetVO.getStartDate());
		String incomeCategoryList=monthlyBudgetVO.getIncomeCategoriesId().replace("[","").replace("]", "");
		String []incomeCategories =incomeCategoryList.split(",");
		String expenseCategoriesList=monthlyBudgetVO.getExpenseCategoriesId().replace("[","").replace("]", "");
		String [] expenseCategories =expenseCategoriesList.split(",");
		int[] numbers = new int[incomeCategories.length];
		for(int i=0;i<incomeCategories.length;i++)
		{
			 Category category=entitymanager.find(Category.class, Integer.parseInt(incomeCategories[i].trim()));
		     categories.add(category);
		}
		for(int i=0;i<expenseCategories.length;i++)
		{
			 Category category=entitymanager.find(Category.class, Integer.parseInt(expenseCategories[i].trim()));
		     categories.add(category);
		}
		
		
		  
		 /*for(String id : expenseCategories) {
		     Category category=entitymanager.find(Category.class, id);
		     categories.add(category);
			  }*/
		monthlyBudget.setCategories(categories);
		entitymanager.getTransaction().begin();
		entitymanager.persist(monthlyBudget);
		entitymanager.getTransaction().commit();
	}catch(Exception e)
	{
		throw new Exception(e.toString());
	}
	}
	public CategoryVO getCategoryById(int id)
	{
		try
		{
		
		entitymanager.getTransaction().begin();
		Query query = (Query) entitymanager.createNamedQuery("findCategotyById").setParameter("id", id);
		Category category=(Category)query.getSingleResult();
		CategoryVO categoryVO=new CategoryVO();
		categoryVO.setActualValue(category.getActualValue());
		categoryVO.setArabicDescription(category.getArabicDescription());
		categoryVO.setCategoryStatus(category.getCategoryStatus());
		categoryVO.setLimit_value(category.getLimitValue());
		categoryVO.setPlanedValue(category.getPlanedValue());
		categoryVO.setEnglisDescription(category.getEnglishDescription());
		categoryVO.setId(category.getId());
		return categoryVO;
	}catch(Exception e)
		{
		System.out.println(e);
		return null;
		}
	}
	public ArrayList<CategoryVO> getExpensesCategories()
	{
		try
		{
		ArrayList<CategoryVO>categoryVOs=new ArrayList<CategoryVO>();
		Query query = (Query) entitymanager.createNamedQuery("findExpensesCategories");
	    List<Category> categoryList =  query.getResultList();
		for (Category category : categoryList) {
			CategoryVO categoryVO=new CategoryVO();
			categoryVO.setActualValue(category.getActualValue());
			categoryVO.setArabicDescription(category.getArabicDescription());
			categoryVO.setCategoryStatus(category.getCategoryStatus());
			categoryVO.setLimit_value(category.getLimitValue());
			categoryVO.setPlanedValue(category.getPlanedValue());
			categoryVO.setEnglisDescription(category.getEnglishDescription());
			categoryVO.setId(category.getId());
			categoryVOs.add(categoryVO);
		}

	
		return categoryVOs;
	}catch(Exception e)
		{
		System.out.println(e);
		return null;
		}
	}
	public ArrayList<CategoryVO> GetBudgetCategories()
	{
		try
		{
		ArrayList<CategoryVO>categoryVOs=new ArrayList<CategoryVO>();
		Query query = (Query) entitymanager.createNamedQuery("findBudgetCategories");
	    List<Category> categoryList =  query.getResultList();
		for (Category category : categoryList) {
			CategoryVO categoryVO=new CategoryVO();
			categoryVO.setActualValue(category.getActualValue());
			categoryVO.setArabicDescription(category.getArabicDescription());
			categoryVO.setCategoryStatus(category.getCategoryStatus());
			categoryVO.setLimit_value(category.getLimitValue());
			categoryVO.setPlanedValue(category.getPlanedValue());
			categoryVO.setEnglisDescription(category.getEnglishDescription());
			categoryVO.setId(category.getId());
			categoryVOs.add(categoryVO);
		}

	
		return categoryVOs;
	}catch(Exception e)
		{
		System.out.println(e);
		return null;
		}
	}
	public Category copyCategoryFromVO(CategoryVO categoryVO)
	{
		Category category=new Category();
		category.setActualValue(categoryVO.getActualValue());
		category.setArabicDescription(categoryVO.getArabicDescription());
		category.setCategoryStatus(categoryVO.getCategoryStatus());
		category.setLimitValue(categoryVO.getLimit_value());
		category.setPlanedValue(categoryVO.getPlanedValue());
		category.setEnglishDescription(categoryVO.getEnglishDescription());
		category.setId(categoryVO.getId());
		return category; 
	}
	

}
