/**
 * 
 */
package com.HomeBudget.Bus;

import java.util.ArrayList;

import com.HomeBudget.DAO.LocationDAO;
import com.HomeBudget.DAO.LocationDAOImpl;
import com.dataObject.LocationVO;

/**
 * @author Shehab
 *
 */
public class LocationHandler {

	LocationDAO locationDAO=null;
	public LocationHandler()
	{
		locationDAO=new LocationDAOImpl();
	}
	
	public void add(LocationVO locationVo) throws Exception
	{
		locationDAO.addLocation(locationVo);
	}
	public ArrayList<LocationVO> getAll() throws Exception
	{
		return locationDAO.getAllLocations();
	}
	
}
