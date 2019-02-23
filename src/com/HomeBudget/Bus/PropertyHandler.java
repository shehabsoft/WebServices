package com.HomeBudget.Bus;

import java.util.ArrayList;

import com.HomeBudget.DAO.CountryDAO;
import com.HomeBudget.DAO.CountryDAOImpl;
import com.HomeBudget.DAO.PropertyDAO;
import com.dataObject.BusinessException;
import com.dataObject.BusinessObject;
import com.dataObject.CountryVO;
import com.dataObject.DataAccessException;

public class PropertyHandler extends BusinessObject{

 
	
	public String getByName(String name)
	{
		PropertyDAO propertyDAO=null;
		try
		{
			propertyDAO=(PropertyDAO)getDAO(PropertyDAO.class);
			return propertyDAO.getByName(name);
		}catch (DataAccessException ex) {
		 throw ex;
		 } catch (BusinessException ex) {
		 throw ex;
		 } catch (Exception ex) {
		 throw new BusinessException(ex);
		 } finally {
		  close(propertyDAO);
		  }
	}
}
