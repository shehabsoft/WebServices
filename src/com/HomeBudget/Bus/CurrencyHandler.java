package com.HomeBudget.Bus;

import java.util.ArrayList;

import com.HomeBudget.DAO.CurrencyDAO;
import com.HomeBudget.DAO.CurrencyDAOImpl;
import com.dataObject.CurrencyVO;

public class CurrencyHandler {
	
	private CurrencyDAO currencyDAO=null;
	public CurrencyHandler()
	{
		currencyDAO=new CurrencyDAOImpl();
	}
	public ArrayList<CurrencyVO> getAll()
	{
		return currencyDAO.getAllCurrencies();
	}

}
