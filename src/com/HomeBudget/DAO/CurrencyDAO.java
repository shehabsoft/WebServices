/**
 * 
 */
package com.HomeBudget.DAO;

import java.util.ArrayList;

import com.HomeBudget.DAO.JPA.DataAccessObject1;
import com.dataObject.CurrencyVO;

/**
 * @author Shehab
 *
 */
public interface CurrencyDAO extends DataAccessObject1 {

	public ArrayList<CurrencyVO> getAllCurrencies();
}
