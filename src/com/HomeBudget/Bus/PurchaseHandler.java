/**
 * 
 */
package com.HomeBudget.Bus;

import java.util.List;

import com.HomeBudget.DAO.ApprovedPurchasesDAO;
import com.HomeBudget.DAO.PurchaseDAO;
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

	public void add(PurchaseVO purchaseVO) throws BusinessException {
		PurchaseDAO purchaseDAO = null;
		try {
			purchaseDAO = (PurchaseDAO) getDAO(PurchaseDAO.class);
			purchaseDAO.addPurchase(purchaseVO);
			monthlyBudgetHandler = new MonthlyBudgetHandler();
			monthlyBudgetHandler.update(purchaseVO, purchaseDAO);
			purchaseHistoryHandler = new PurchaseHistoryHandler();
			purchaseHistoryHandler.add(purchaseVO, purchaseDAO);
			purchaseDAO.commit();
		} catch (DataAccessException ex) {
			throw ex;
		} catch (BusinessException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BusinessException(ex);
		} finally {
			close(purchaseDAO);
		}
	}
	public void approve(PurchaseVO purchaseVO) throws BusinessException {
		PurchaseDAO purchaseDAO = null;
		ApprovedPurchasesDAO approvedPurchasesDAO=null;
		try {
			
			approvedPurchasesDAO=(ApprovedPurchasesDAO)getDAO(ApprovedPurchasesDAO.class);
			boolean flage=approvedPurchasesDAO.validateBeforeAdd(purchaseVO);
			if(flage)
			{
			approvedPurchasesDAO.add(purchaseVO);
			purchaseDAO = (PurchaseDAO) getDAO(PurchaseDAO.class,approvedPurchasesDAO);
			purchaseDAO.approvePurchase(purchaseVO);
			purchaseDAO.commit();
			}else
			{
				throw new BusinessException("This Purchase Is Approved Before") ;
			}
		} catch (DataAccessException ex) {
			throw ex;
		} catch (BusinessException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BusinessException(ex);
		} finally {
			close(purchaseDAO);
		}
	}
	public void reject(PurchaseVO purchaseVO) throws BusinessException {
		PurchaseDAO purchaseDAO = null;
		try {
			
			
			purchaseDAO = (PurchaseDAO) getDAO(PurchaseDAO.class);
			purchaseDAO.rejectPurchase(purchaseVO);
			purchaseDAO.commit();
		} catch (DataAccessException ex) {
			throw ex;
		} catch (BusinessException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BusinessException(ex);
		} finally {
			close(purchaseDAO);
		}
	}

	public void update(PurchaseVO purchaseVO) throws BusinessException {
		PurchaseDAO purchaseDAO = null;
		try {
			purchaseDAO = (PurchaseDAO) getDAO(PurchaseDAO.class);
			purchaseDAO.updatePurchase(purchaseVO);
			monthlyBudgetHandler = new MonthlyBudgetHandler();
			monthlyBudgetHandler.update(purchaseVO, purchaseDAO);
			purchaseHistoryHandler = new PurchaseHistoryHandler();
			purchaseHistoryHandler.add(purchaseVO, purchaseDAO);
			purchaseDAO.commit();
		} catch (DataAccessException ex) {
			throw ex;
		} catch (BusinessException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BusinessException(ex);
		} finally {
			close(purchaseDAO);
		}
	}

	public List<PurchaseVO> getAll(int monthlyBudgetId) throws Exception {
		PurchaseDAO purchaseDAO = null;
		try {
			purchaseDAO = (PurchaseDAO) getDAO(PurchaseDAO.class);
			return purchaseDAO.getAllPurchases(monthlyBudgetId);
		} catch (DataAccessException ex) {
			throw ex;
		} catch (BusinessException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BusinessException(ex);
		} finally {
			close(purchaseDAO);
		}
	}

	public List<PurchaseVO> getAll(int monthlyBudgetId, int categoryId,int userId) throws Exception {
		PurchaseDAO purchaseDAO = null;
		purchaseHistoryHandler=new PurchaseHistoryHandler();
		try {
			purchaseDAO = (PurchaseDAO) getDAO(PurchaseDAO.class);
			List<PurchaseVO> purchaseVOs=purchaseDAO.getAllPurchases(monthlyBudgetId, categoryId);
			for(int i=0;i<purchaseVOs.size();i++)
			{
				PurchaseVO purchaseVO=purchaseVOs.get(i);
				PurchaseVO purchaseVOUpdated=purchaseHistoryHandler.getPurchasesHistoryChartByApprovedPurchaseId(purchaseVO.getApprovedPurchaseId(), userId);
				purchaseVO.setCreationDateStr(purchaseVOUpdated.getCreationDateStr());
				purchaseVO.setTotalPriceStr(purchaseVOUpdated.getTotalPriceStr());
				purchaseVOs.set(i, purchaseVO);
			}
			return  purchaseVOs;
		} catch (DataAccessException ex) {
			throw ex;
		} catch (BusinessException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BusinessException(ex);
		} finally {
			close(purchaseDAO);
		}
	}
	public List<PurchaseVO> getAllPurchases(int categoryId) throws Exception {
		PurchaseDAO purchaseDAO = null;
		try {
			purchaseDAO = (PurchaseDAO) getDAO(PurchaseDAO.class);
			return purchaseDAO.getAllPurchasesByCategoryId(categoryId);
		} catch (DataAccessException ex) {
			throw ex;
		} catch (BusinessException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BusinessException(ex);
		} finally {
			close(purchaseDAO);
		}
	}
	public List<PurchaseVO> getMonthlyPurchases(int monthlyBudgetId) throws Exception {
		PurchaseDAO purchaseDAO = null;
		try {
			purchaseDAO = (PurchaseDAO) getDAO(PurchaseDAO.class);
			return purchaseDAO.getMonthlyPurchases(monthlyBudgetId);
		} catch (DataAccessException ex) {
			throw ex;
		} catch (BusinessException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BusinessException(ex);
		} finally {
			close(purchaseDAO);
		}
	}
	public List<PurchaseVO> getMonthlyPurchases(int monthlyBudgetId,int categoryId) throws Exception {
		PurchaseDAO purchaseDAO = null;
		try {
			purchaseDAO = (PurchaseDAO) getDAO(PurchaseDAO.class);
			return purchaseDAO.getMonthlyPurchases(monthlyBudgetId,categoryId);
		} catch (DataAccessException ex) {
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
