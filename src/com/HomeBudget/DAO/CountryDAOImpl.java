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
import com.dataObject.CountryVO;


public class CountryDAOImpl extends DataAccessObject implements CountryDAO {
//	

	
	
	public CountryDAOImpl()
	{
      super();
	}


	public ArrayList<CountryVO> getAllCountries()
	{
		try
		{
		ArrayList<CountryVO>countriesVOS=new ArrayList<CountryVO>();
		Query query = (Query) getEntitymanager().createNamedQuery("findAllCountries");
	    List<Country> categoryList =  query.getResultList();
		for (Country country : categoryList) {
			CountryVO countryVO=new CountryVO();
			countryVO.setArabicName(country.getArabicName());
			countryVO.setEnglishName(country.getEnglishName());
			countryVO.setId(country.getId());
			countriesVOS.add(countryVO);
		}

	
		return countriesVOS;
	}catch(Exception e)
		{
		System.out.println(e);
		return null;
		}
	}
	
}
