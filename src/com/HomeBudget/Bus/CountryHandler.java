package com.HomeBudget.Bus;

import java.util.ArrayList;

import com.HomeBudget.DAO.CountryDAO;
import com.HomeBudget.DAO.CountryDAOImpl;
import com.dataObject.CountryVO;

public class CountryHandler {

	private CountryDAO countryDAO=null;
	public CountryHandler()
	{
		countryDAO=new CountryDAOImpl();
	}
	public ArrayList<CountryVO> getAll()
	{
		return countryDAO.getAllCountries();
	}
}
