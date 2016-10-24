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
import com.dataObject.CountryVO;


public class CountrySession {
//	

	private EntityManagerFactory emfactory;
	private EntityManager entitymanager ;
	public CountrySession()
	{
		 emfactory = Persistence.createEntityManagerFactory("WebServices");
		 entitymanager = emfactory.createEntityManager();
	}
	public CountrySession(EntityManagerFactory emfactory,EntityManager entitymanager)
	{
		this.emfactory=emfactory;
		this.entitymanager=entitymanager;
	}
	
	

	public ArrayList<CountryVO> getAllCountries()
	{
		try
		{
		ArrayList<CountryVO>countriesVOS=new ArrayList<CountryVO>();
		Query query = (Query) entitymanager.createNamedQuery("findAllCountries");
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
