package com.HomeBudget.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.HomeBudget.DAO.JPA.JPADataAccessObject;
import com.dataObject.LocationVO;
import com.entities.models.Location;
import com.entities.models.Property;
 


public class PropertyDAOImpl extends JPADataAccessObject implements PropertyDAO{


	
	public PropertyDAOImpl()
	{
		super();
	}

	@Override
	public String getByName(String name) throws Exception {
		// TODO Auto-generated method stub
		try
		{
		Query query = (Query) getEntitymanager().createNamedQuery("findPropertyByName");
		query.setParameter("name", name);	
		
		 Object obj = (Object)query.getSingleResult();
		if(obj!=null)
		{
			return (String)obj;
		}else
		{
			throw new Exception("Property with Name: "+name +"Not Found In DataBase");
		}
		
			
		}catch(Exception exception)
		{
			exception.printStackTrace();
			throw exception;
		}
	}
	
	
 


 
	

}
