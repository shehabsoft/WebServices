package com.HomeBudget.Bus;

import java.util.ArrayList;

import com.HomeBudget.DAO.CountryDAO;
import com.HomeBudget.DAO.CountryDAOImpl;
import com.dataObject.BusinessException;
import com.dataObject.BusinessObject;
import com.dataObject.CountryVO;
import com.dataObject.DataAccessException;

public class CountryHandler extends BusinessObject{

 
	
	public ArrayList<CountryVO> getAll()
	{
		CountryDAO countryDAO=null;
		try
		{
			countryDAO=(CountryDAO)getDAO(CountryDAO.class);
			return countryDAO.getAllCountries();
		}catch (DataAccessException ex) {
		 throw ex;
		 } catch (BusinessException ex) {
		 throw ex;
		 } catch (Exception ex) {
		 throw new BusinessException(ex);
		 } finally {
		  close(countryDAO);
		  }
	}
}
