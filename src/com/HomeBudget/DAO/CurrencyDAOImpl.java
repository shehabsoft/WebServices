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
import com.HomeBudget.DAO.JPA.JPADataAccessObject;
import com.dataObject.CategoryVO;
import com.dataObject.CountryVO;
import com.dataObject.CurrencyVO;


public class CurrencyDAOImpl extends JPADataAccessObject implements CurrencyDAO{
//	
 
	public CurrencyDAOImpl()
	{
		super();
	}

	

	public ArrayList<CurrencyVO> getAllCurrencies()
	{
		try
		{
		ArrayList<CurrencyVO>countriesVOS=new ArrayList<CurrencyVO>();
		Query query = (Query) getEntitymanager().createNamedQuery("findAllCurrencies");
	    List<Currency> currencyList =  query.getResultList();
		for (Currency currency : currencyList) {
			CurrencyVO currncyVo=new CurrencyVO();
			currncyVo.setName(currency.getName());
			currncyVo.setId(currency.getId());
			countriesVOS.add(currncyVo);
		}

	
		return countriesVOS;
	}catch(Exception e)
		{
		System.out.println(e);
		return null;	}
	}
	
}