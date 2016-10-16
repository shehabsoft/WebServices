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


public class CategorySession {
//	
//	   @PersistenceContext(unitName="WebServices",type = PersistenceContextType.EXTENDED) 
//	   EntityManager entitymanager; 
	private EntityManagerFactory emfactory;
	private EntityManager entitymanager ;
	public CategorySession()
	{
		 emfactory = Persistence.createEntityManagerFactory("WebServices");
		 entitymanager = emfactory.createEntityManager();
	}
	public CategorySession(EntityManagerFactory emfactory,EntityManager entitymanager)
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
	public void addCategory(CategoryVO categoryVO) throws Exception
	{
		
	
		Category category=new Category();
		category.setCategoryStatus(categoryVO.getCategoryStatus());
		category.setCategoryTypeId(categoryVO.getCategoryTypeId());
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
		if(categoryVO.getLimit_value()>=categoryVO.getPlanedValue())
		{
		category.setLimitValue(categoryVO.getLimit_value());
		}else
		{
			if(categoryVO.getCategoryTypeId()==2)
			{
			throw new Exception("Planed Value Should  be Less Than Limit Value");
			}else
			{
				category.setLimitValue(categoryVO.getLimit_value());
			}
		}
		if(categoryVO.getActualValue()<categoryVO.getPlanedValue())
		{
		category.setActualValue(categoryVO.getActualValue());
		}else
		{
			if(categoryVO.getCategoryTypeId()==2)
			{
			throw new Exception("Planed Value Should  be Less Than Limit Value");
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
		entitymanager.getTransaction().begin();
		entitymanager.persist(category);
		entitymanager.getTransaction().commit();
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
