package com.HomeBudget.DAO;

import java.util.ArrayList;

import com.HomeBudget.DAO.JPA.DataAccessObject1;
import com.dataObject.CountryVO;

public interface CountryDAO extends DataAccessObject1 {

	public ArrayList<CountryVO> getAllCountries();
}
