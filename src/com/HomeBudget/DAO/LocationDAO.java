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
public interface LocationDAO extends DataAccessObject1 {

	public void addLocation(LocationVO locationVo) throws Exception;
	public ArrayList<LocationVO> getAllLocations() throws Exception;
}
