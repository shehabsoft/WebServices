/**
 * 
 */
package com.HomeBudget.Bus;

import java.util.ArrayList;

import com.HomeBudget.DAO.LocationDAO;
import com.HomeBudget.DAO.LocationDAOImpl;
import com.dataObject.BusinessException;
import com.dataObject.BusinessObject;
import com.dataObject.DataAccessException;
import com.dataObject.LocationVO;

/**
 * @author Shehab
 *
 */
public class LocationHandler extends BusinessObject{

 
	public void add(LocationVO locationVo) throws Exception
	{
		LocationDAO locationDAO=null;
		 try { 
		  locationDAO=(LocationDAO)getDAO(LocationDAO.class);
		  locationDAO.addLocation(locationVo);
		  locationDAO.commit();
		 }catch (DataAccessException ex) {
		       throw ex;
		     } catch (BusinessException ex) {
		            throw ex;
		      } catch (Exception ex) {
		            throw new BusinessException(ex);
		      } finally {
		        close(locationDAO);
		      }
	}
	public ArrayList<LocationVO> getAll() throws Exception
	{
		LocationDAO locationDAO=null;
		 try { 
		  locationDAO=(LocationDAO)getDAO(LocationDAO.class);
		  return locationDAO.getAllLocations();
		 }catch (DataAccessException ex) {
		       throw ex;
		     } catch (BusinessException ex) {
		            throw ex;
		      } catch (Exception ex) {
		            throw new BusinessException(ex);
		      } finally {
		        close(locationDAO);
		      }
		 
	}
	
}
