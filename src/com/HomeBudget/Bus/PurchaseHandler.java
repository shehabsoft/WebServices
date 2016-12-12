/**
 * 
 */
package com.HomeBudget.Bus;

import java.util.ArrayList;
import java.util.List;

import com.HomeBudget.DAO.PurchaseDAO;
import com.HomeBudget.DAO.PurchaseDAOImpl;
import com.dataObject.BusinessException;
import com.dataObject.BusinessObject;
import com.dataObject.DataAccessException;
import com.dataObject.PurchaseVO;

/**
 * @author Shehab
 *
 */
public class PurchaseHandler extends BusinessObject {
	private MonthlyBudgetHandler monthlyBudgetHandler;
	private PurchaseHistoryHandler purchaseHistoryHandler;
	 
	public void add(PurchaseVO purchaseVO) throws Exception
	{
		PurchaseDAO purchaseDAO=null;
		try
		{
	    purchaseDAO=(PurchaseDAO)getDAO(PurchaseDAO.class);
		purchaseDAO.addPurchase(purchaseVO);
		monthlyBudgetHandler=new MonthlyBudgetHandler();
		monthlyBudgetHandler.update(purchaseVO, purchaseDAO);
		purchaseHistoryHandler=new PurchaseHistoryHandler();
		purchaseHistoryHandler.add(purchaseVO, purchaseDAO);
		purchaseDAO.commit();
		}catch (DataAccessException ex) {
		       throw ex;
		     } catch (BusinessException ex) {
		            throw ex;
		      } catch (Exception ex) {
		            throw new BusinessException(ex);
		      } finally {
		        close(purchaseDAO);
		      }
	}
	public void update(PurchaseVO purchaseVO) throws Exception
	{
		PurchaseDAO purchaseDAO=null;
		try
		{
	    purchaseDAO=(PurchaseDAO)getDAO(PurchaseDAO.class);
		purchaseDAO.updatePurchase(purchaseVO);
		monthlyBudgetHandler=new MonthlyBudgetHandler();
		monthlyBudgetHandler.update(purchaseVO, purchaseDAO);
		purchaseHistoryHandler=new PurchaseHistoryHandler();
		purchaseHistoryHandler.add(purchaseVO, purchaseDAO);
		purchaseDAO.commit();
		}
		catch (DataAccessException ex) {
		       throw ex;
		     } catch (BusinessException ex) {
		            throw ex;
		      } catch (Exception ex) {
		            throw new BusinessException(ex);
		      } finally {
		        close(purchaseDAO);
		      }
	}
	public List<PurchaseVO> getAll(int monthlyBudgetId) throws Exception
	{  
		PurchaseDAO purchaseDAO=null;
		try
		{
	     purchaseDAO=(PurchaseDAO)getDAO(PurchaseDAO.class);
		 return purchaseDAO.getAllPurchases(monthlyBudgetId);
		}
		catch (DataAccessException ex) {
		       throw ex;
		     } catch (BusinessException ex) {
		            throw ex;
		      } catch (Exception ex) {
		            throw new BusinessException(ex);
		      } finally {
		        close(purchaseDAO);
		      }
	}
	public List<PurchaseVO> getAll(int monthlyBudgetId,int categoryId) throws Exception
	{
		PurchaseDAO purchaseDAO=null;
		try
		{
	    purchaseDAO=(PurchaseDAO)getDAO(PurchaseDAO.class);
		return purchaseDAO.getAllPurchases(monthlyBudgetId,categoryId);
		}
		catch (DataAccessException ex) {
		       throw ex;
		     } catch (BusinessException ex) {
		            throw ex;
		      } catch (Exception ex) {
		            throw new BusinessException(ex);
		      } finally {
		        close(purchaseDAO);
		      }
	}
}
