/**
 * 
 */
package com.HomeBudget.DAO;

import java.util.ArrayList;

import com.HomeBudget.DAO.JPA.DataAccessObject1;
import com.dataObject.LocationVO;

/**
 * @author Shehab
 *
 */
public interface PropertyDAO extends DataAccessObject1 {

	public String getByName(String name) throws Exception;
	 
}
