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
import com.dataObject.PurchaseVO;


public class PurchaseSession {
//	
//	   @PersistenceContext(unitName="WebServices",type = PersistenceContextType.EXTENDED) 
//	   EntityManager entitymanager; 
	private EntityManagerFactory emfactory ;
	private EntityManager entitymanager;
	CategorySession categorySession;
	public PurchaseSession()
	{
		emfactory = Persistence.createEntityManagerFactory("WebServices");
		entitymanager = emfactory.createEntityManager();
	}
	
	
	public void addPurchase(PurchaseVO purchaseVO) throws Exception
	{
		
		 Purchase purchase =new Purchase();
		try
		{
	    Category category=entitymanager.find(Category.class, purchaseVO.getCategoryId());
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
					  Location location=entitymanager.find(Location.class, purchaseVO.getLocationId());
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
					
					if((purchaseVO.getPrice()+category.getActualValue())<=category.getLimitValue())
					{
						purchase.setPrice(purchaseVO.getPrice());
					}else
					{
						throw new Exception("Purchase Price  Value Should  be Less Than Limit Value");
					}
					if(purchaseVO.getDetails()!=null)
					{
						purchase.setDetails(purchaseVO.getDetails());
					}
					category.setActualValue(category.getActualValue()+purchaseVO.getPrice());
				    entitymanager.getTransaction().begin();
					purchase.setCategory(category);
					entitymanager.persist(purchase);
			/*	//	categorySession.updateCategory(categoryVO);
			*/		entitymanager.getTransaction().commit();
	    }
		}catch(Exception e)
		{
			throw new Exception(e);
			
		}
		
		/*/
*/		
	}
	public PurchaseVO getPurchaseByEnglishName(String englishName)
	{
//		try
//		{
//		Configuration configuration=new Configuration();
//		configuration.configure();
//		ServiceRegistry sr= new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
//		SessionFactory sf=configuration.buildSessionFactory(sr);
//		Session open_session=sf.openSession();
//		open_session.beginTransaction();
//		Query query = open_session.getNamedQuery("findCategoryByEnglishDescription")
//				.setString("english_description", englishName);
//		Category category=(Category)query.uniqueResult();
//		purchase purchase=new purchase();
//		purchase.setActualValue(category.getActualValue());
//		purchase.setArabicDescription(category.getArabicDescription());
//		purchase.setCategoryStatus(category.getCategory_Status());
//		purchase.setLimit_value(category.getLimitValue());
//		purchase.setPlanedValue(category.getPlanedValue());
//		purchase.setEnglisDescription(category.getEnglishDescription());
//		purchase.setId(category.getId());
//		return purchase;
//	}catch(Exception e)
//		{
//		System.out.println(e);
//		return null;
//		}
		return null;
	}
	public ArrayList<PurchaseVO> getAllPurchases() throws Exception
	{
		try
		{
		ArrayList<PurchaseVO>purchases=new ArrayList<PurchaseVO>();
		Query query = (Query) entitymanager.createNamedQuery("findAllPurchases");
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
			purchases.add(purchaseVO);
		}

	
		return purchases;
	}catch(Exception e)
		{
		throw new Exception(e);
		
		}
	}
	

}
