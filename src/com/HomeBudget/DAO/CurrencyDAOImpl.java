package com.HomeBudget.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.HomeBudget.DAO.JPA.JPADataAccessObject;
import com.dataObject.CurrencyVO;
import com.entities.models.Currency;

public class CurrencyDAOImpl extends JPADataAccessObject implements CurrencyDAO {

	public CurrencyDAOImpl() {
		super();
	}

	public ArrayList<CurrencyVO> getAllCurrencies() {
		try {
			ArrayList<CurrencyVO> countriesVOS = new ArrayList<CurrencyVO>();
			Query query = (Query) getEntitymanager().createNamedQuery("findAllCurrencies");
			List<Currency> currencyList = query.getResultList();
			for (Currency currency : currencyList) {
				CurrencyVO currncyVo = new CurrencyVO();
				currncyVo.setName(currency.getName());
				currncyVo.setId(currency.getId());
				countriesVOS.add(currncyVo);
			}

			return countriesVOS;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
