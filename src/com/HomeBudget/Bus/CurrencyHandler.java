package com.HomeBudget.Bus;

import java.util.ArrayList;

import com.HomeBudget.DAO.CurrencyDAO;
import com.HomeBudget.DAO.CurrencyDAOImpl;
import com.dataObject.BusinessException;
import com.dataObject.BusinessObject;
import com.dataObject.CurrencyVO;
import com.dataObject.DataAccessException;

public class CurrencyHandler extends BusinessObject {

	public ArrayList<CurrencyVO> getAll() {
		CurrencyDAO currencyDAO = null;
		try {
			currencyDAO = (CurrencyDAO) getDAO(CurrencyDAO.class);
			return currencyDAO.getAllCurrencies();
		} catch (DataAccessException ex) {
			throw ex;
		} catch (BusinessException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BusinessException(ex);
		} finally {
			close(currencyDAO);
		}
	}

}
