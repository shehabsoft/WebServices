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
import com.dataObject.CategoryVO;
import com.dataObject.PurchaseVO;


public class PurchaseSession extends SessionFactory{
 
	CategorySession categorySession;
	public PurchaseSession()
	{
		 super();
	}
	
	
	public void addPurchase(PurchaseVO purchaseVO) throws Exception
	{
		
		 Purchase purchase =new Purchase();
		try
		{
			
	    Category category=getEntitymanager().find(Category.class, purchaseVO.getCategoryId());
	    purchase.setCategory(category);
	   
	    if(category==null)
	    {
	    	throw new Exception("There is No Category with ID "+purchaseVO.getCategoryId());
	    }else
	    {
					if(purchaseVO.getArabicDescription()!=null)
					{
						purchase.setArabicDescription(purchaseVO.getArabicDescription());
					}else
					{
						throw new Exception("Purchase Arabic Description Should not be Null");
					}
					if(purchaseVO.getLocationId()!=0)
					{
					  Location location=getEntitymanager().find(Location.class, purchaseVO.getLocationId());
					  purchase.setLocation(location);;
					}else
					{
						throw new Exception("Location  Should not be Null or 0");
					}
					if(purchaseVO.getEnglishDescription()!=null)
					{
						purchase.setEnglishDescription(purchaseVO.getEnglishDescription());
					}else
					{
						throw new Exception("Purchase English Description Should not be Null");
					}
					
					if(purchaseVO.getPrice()>0)
					{
						purchase.setPrice(purchaseVO.getPrice());
					}else
					{
						throw new Exception("Purchase Price  Should be Greater than 0");
					}
					if(purchaseVO.getDetails()!=null)
					{
						purchase.setDetails(purchaseVO.getDetails());
					}
					purchase.setCreationDate(new Date());
				//	category.setActualValue(category.getActualValue()+purchaseVO.getPrice());
					getEntitymanager().getTransaction().begin();
					purchase.setCategory(category);
					Query query = (Query) getEntitymanager().createNamedQuery("getActiveMonthlyBudgetByUserId");
					query.setParameter("id", purchaseVO.getUserId());
					MonthlyBudget monthlyBudget=  (MonthlyBudget) query.getSingleResult();
					monthlyBudget.setTotalExpenses(monthlyBudget.getTotalExpenses()+purchaseVO.getPrice());
					purchase.setMonthlyBudget(monthlyBudget);
					getEntitymanager().persist(monthlyBudget);
					getEntitymanager().persist(purchase);
					int monthlyBudgetId=monthlyBudget.getId();
					Query query2 = (Query) getEntitymanager().createNamedQuery("getMonthlyBudgetCategoryByMonthlyBudgetIdAndCategoryId");
					query2.setParameter("id", monthlyBudgetId);
					query2.setParameter("categoryId", category.getId());
					MonthlyBudgetCategory monthlyBudgetCategory=(MonthlyBudgetCategory) query2.getSingleResult();
					monthlyBudgetCategory.setActualValue(monthlyBudgetCategory.getActualValue()+purchaseVO.getPrice());
					getEntitymanager().persist(monthlyBudgetCategory);
			/*	//	categorySession.updateCategory(categoryVO);
			*/		getEntitymanager().getTransaction().commit();
	    }
		}catch(Exception e) 
		{
			throw new Exception(e);
			
		}
		

	
	}
	public void updatePurchase(PurchaseVO purchaseVO) throws Exception
	{
		try
		{
			double updateExpenseValue=0;
			Purchase purchase=getEntitymanager().find(Purchase.class, purchaseVO.getId());
			if(purchase.getPrice()!=purchaseVO.getPrice())
			    {
				updateExpenseValue=purchaseVO.getPrice()-purchase.getPrice();
			    }
			purchase.setArabicDescription(purchaseVO.getArabicDescription());
			purchase.setEnglishDescription(purchaseVO.getEnglishDescription());
			purchase.setPrice(purchaseVO.getPrice());
			Category category=getEntitymanager().find(Category.class, purchaseVO.getCategoryId());
		    Location location=getEntitymanager().find(Location.class, purchaseVO.getLocationId());
		    purchase.setLocation(location);
		    Query query = (Query) getEntitymanager().createNamedQuery("getActiveMonthlyBudgetByUserId");
			query.setParameter("id", purchaseVO.getUserId());
			MonthlyBudget monthlyBudget=  (MonthlyBudget) query.getSingleResult();
			monthlyBudget.setTotalExpenses(monthlyBudget.getTotalExpenses()+updateExpenseValue);
			
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
						double actualValue=monthlyBudgetCategory.getActualValue();
						monthlyBudgetCategory.setActualValue(actualValue+updateExpenseValue);
						getEntitymanager().persist(monthlyBudgetCategory);
					}
				}
			}
		    purchase.setCategory(category);
		    purchase.setCreationDate(new Date());
		    purchase.setMonthlyBudget(monthlyBudget);
		   
			purchase.setDetails(purchaseVO.getDetails());
			getEntitymanager().getTransaction().begin();
			getEntitymanager().persist(purchase);
			getEntitymanager().getTransaction().commit();
			
			
			
		}catch(Exception e)
		{
			throw new Exception(e);
		}
	}
	
	public ArrayList<PurchaseVO> getAllPurchases(int monthlyBudgetId) throws Exception
	{
		try
		{
		ArrayList<PurchaseVO>purchases=new ArrayList<PurchaseVO>();
		Query query = (Query) getEntitymanager().createNamedQuery("findAllPurchases");
		query.setParameter("id", monthlyBudgetId);
	    List<Purchase> purchaseList =  query.getResultList();
		for (Purchase purchase : purchaseList) {
			PurchaseVO purchaseVO=new PurchaseVO();
			
			purchaseVO.setArabicDescription(purchase.getArabicDescription());
			purchaseVO.setCategoryId(purchase.getCategory().getId());
			purchaseVO.setPrice(purchase.getPrice());
			purchaseVO.setEnglishDescription(purchase.getEnglishDescription());
			purchaseVO.setId(purchase.getId());
			purchaseVO.setDetails(purchase.getDetails());
			purchaseVO.setLocationId(purchase.getLocation().getId());
			purchaseVO.setLocationName(purchase.getLocation().getEnglishName());
			purchaseVO.setCategoryName(purchase.getCategory().getEnglishDescription());
			
			if(purchase.getCreationDate()!=null)
				{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					purchaseVO.setCreationDate(sdf.format( purchase.getCreationDate()));
				}
			purchases.add(purchaseVO);
		}

	
		return purchases;
	}catch(Exception e)
		{
		throw new Exception(e);
		
		}
	}
	

}
