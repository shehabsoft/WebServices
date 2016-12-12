package com.HomeBudget.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.HomeBudget.DAO.JPA.JPADataAccessObject;
import com.dataObject.LocationVO;
import com.entities.models.Location;
 


public class LocationDAOImpl extends JPADataAccessObject implements LocationDAO{


	
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
			
				getEntitymanager().persist(location);
		
	    
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
