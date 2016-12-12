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
import com.HomeBudget.DAO.JPA.JPADataAccessObject;
import com.HomeBudget.dataObject.PurchaseHistoryVO;
import com.dataObject.CategoryVO;
import com.dataObject.PurchaseVO;


public class PurchaseDAOImpl extends  JPADataAccessObject implements PurchaseDAO{
 
	
	public PurchaseDAOImpl()
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
		 purchase.setCategory(category);
		 int purchaseId=(int) (getNextKey()-1);
		 purchase.setId(purchaseId);
		 purchaseVO.setId(purchaseId);
		 getEntitymanager().persist(purchase);
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
			Purchase purchase=getEntitymanager().find(Purchase.class, purchaseVO.getId());
			purchase.setArabicDescription(purchaseVO.getArabicDescription());
			purchase.setEnglishDescription(purchaseVO.getEnglishDescription());
			purchase.setPrice(purchase.getPrice()+purchaseVO.getNewPrice());
		    Location location=getEntitymanager().find(Location.class, purchaseVO.getLocationId());
		    purchase.setLocation(location);
		    Category category=getEntitymanager().find(Category.class, purchaseVO.getCategoryId());
		    purchase.setCategory(category);
		    purchase.setCreationDate(new Date());
 			purchase.setDetails(purchaseVO.getDetails());
			getEntitymanager().persist(purchase);
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


	@Override
	public List<PurchaseVO> getAllPurchases(int monthlyBudgetId, int categoryId) throws Exception {
		try
		{
		ArrayList<PurchaseVO>purchases=new ArrayList<PurchaseVO>();
		Query query = (Query) getEntitymanager().createNamedQuery("findAllPurchasesByCategoryId");
		query.setParameter("id", monthlyBudgetId);
		query.setParameter("categoryId", categoryId);
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
	public Long getNextKey()
	{
		Query q = getEntitymanager().createNativeQuery("SELECT Max(Auto_increment) FROM information_schema.tables WHERE table_name='purchase'");
		return (Long)q.getSingleResult();
	}

}
