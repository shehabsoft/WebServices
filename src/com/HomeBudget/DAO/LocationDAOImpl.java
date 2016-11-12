package com.HomeBudget.DAO;

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
import com.dataObject.LocationVO;
import com.dataObject.PurchaseVO;


public class LocationDAOImpl extends DataAccessObject implements LocationDAO{


	
	public LocationDAOImpl()
	{
		super();
	}
	
	
	public void addLocation(LocationVO locationVo) throws Exception
	{
		
		 Location location =new Location();
		try
		{
	  
					if(locationVo.getArabicName()!=null)
					{
						location.setArabicName(locationVo.getArabicName());
					}else
					{
						throw new Exception("Purchase Arabic Description Should not be Null");
					}
		
					if(locationVo.getEnglishName()!=null)
					{
						location.setEnglishName(locationVo.getEnglishName());
					}else
					{
						throw new Exception("Purchase English Description Should not be Null");
					}
			
				getEntitymanager().getTransaction().begin();
				getEntitymanager().persist(location);
				getEntitymanager().getTransaction().commit();
	    
		}catch(Exception e)
		{
			throw new Exception(e);
			
		}
		
		/*/
*/		
	}

	public ArrayList<LocationVO> getAllLocations() throws Exception
	{
		try
		{
		ArrayList<LocationVO>purchases=new ArrayList<LocationVO>();
		Query query = (Query) getEntitymanager().createNamedQuery("findAllLocations");
	    List<Location> purchaseList =  query.getResultList();
		for (Location purchase : purchaseList) {
			LocationVO purchaseVO=new LocationVO();
			
			purchaseVO.setArabicName(purchase.getArabicName());
			
			purchaseVO.setEnglishName(purchase.getEnglishName());
			purchaseVO.setId(purchase.getId());;
			purchases.add(purchaseVO);
		}

	
		return purchases;
	}catch(Exception e)
		{
		throw new Exception(e);
		
		}
	}
	

}
