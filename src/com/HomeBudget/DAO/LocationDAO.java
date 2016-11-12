/**
 * 
 */
package com.HomeBudget.DAO;

import java.util.ArrayList;

import com.dataObject.LocationVO;

/**
 * @author Shehab
 *
 */
public interface LocationDAO {

	public void addLocation(LocationVO locationVo) throws Exception;
	public ArrayList<LocationVO> getAllLocations() throws Exception;
}
