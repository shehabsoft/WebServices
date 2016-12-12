/**
 * 
 */
package com.HomeBudget.Bus;

import java.util.ArrayList;
import java.util.List;

import com.HomeBudget.DAO.PurchaseDAO;
import com.HomeBudget.DAO.PurchaseDAOImpl;
import com.HomeBudget.DAO.PurchaseHistoryDAO;
import com.HomeBudget.DAO.PurchaseHistoryDAOImpl;
import com.HomeBudget.DAO.JPA.DataAccessObject1;
import com.HomeBudget.dataObject.PurchaseHistoryVO;
import com.dataObject.BusinessException;
import com.dataObject.BusinessObject;
import com.dataObject.DataAccessException;
import com.dataObject.PurchaseVO;

/**
 * @author Shehab
 *
 */
public class PurchaseHistoryHandler extends BusinessObject{
	
	 
	public void add(PurchaseHistoryVO purchaseHistoryVO) throws Exception
	{
		PurchaseHistoryDAO purchaseHistoryDAO=null;
		try
		{
		purchaseHistoryDAO=(PurchaseHistoryDAO)getDAO(PurchaseHistoryDAO.class);
		purchaseHistoryDAO.addPurchaseHistory(purchaseHistoryVO);
		purchaseHistoryDAO.commit();
		}catch (DataAccessException ex) {
		  throw ex;
		 } catch (BusinessException ex) {
		 throw ex;
		 } catch (Exception ex) {
		 throw new BusinessException(ex);
		 } finally {
		 close(purchaseHistoryDAO);
		 }
	}
	public void add(PurchaseVO purchaseVO,DataAccessObject1 parentDao) throws Exception
	{
		PurchaseHistoryDAO purchaseHistoryDAO=null;
		purchaseHistoryDAO=(PurchaseHistoryDAO)getDAO(PurchaseHistoryDAO.class,parentDao);
		purchaseHistoryDAO.addPurchaseHistory(purchaseVO);
	}
	public void update(PurchaseHistoryVO purchaseHistoryVO) throws Exception
	{
		PurchaseHistoryDAO purchaseHistoryDAO=null;
		try
		{
		purchaseHistoryDAO=(PurchaseHistoryDAO)getDAO(PurchaseHistoryDAO.class);
		purchaseHistoryDAO.updatePurchaseHistory(purchaseHistoryVO);
		purchaseHistoryDAO.commit();
		}catch (DataAccessException ex) {
		 throw ex;
		 } catch (BusinessException ex) {
		 throw ex;
		 } catch (Exception ex) {
		 throw new BusinessException(ex);
		 } finally {
		 close(purchaseHistoryDAO);
		 }
	}
	public void update(PurchaseHistoryVO purchaseHistoryVO,DataAccessObject1 parentDao) throws Exception
	{
		PurchaseHistoryDAO purchaseHistoryDAO=null;
		purchaseHistoryDAO=(PurchaseHistoryDAO)getDAO(PurchaseHistoryDAO.class,parentDao);
		purchaseHistoryDAO.updatePurchaseHistory(purchaseHistoryVO);
	}
	public List<PurchaseHistoryVO> getAll(int purchaseId) throws Exception
	{

		PurchaseHistoryDAO purchaseHistoryDAO=null;
		try
		{
		purchaseHistoryDAO=(PurchaseHistoryDAO)getDAO(PurchaseHistoryDAO.class);
		return purchaseHistoryDAO.getPurchaseHistoryByPurchaseId(purchaseId);
		}catch (DataAccessException ex) {
		 throw ex;
		 } catch (BusinessException ex) {
		 throw ex;
		 } catch (Exception ex) {
		 throw new BusinessException(ex);
		 } finally {
		 close(purchaseHistoryDAO);
		 }
		
	}
}
