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
import com.HomeBudget.dataObject.PurchaseHistoryVO;
import com.dataObject.PurchaseVO;

/**
 * @author Shehab
 *
 */
public class PurchaseHistoryHandler {
	private PurchaseHistoryDAO purchaseHistoryDAO;
	
	public PurchaseHistoryHandler()
	{
		super();
		purchaseHistoryDAO=new PurchaseHistoryDAOImpl();
	}
	
	public void add(PurchaseHistoryVO purchaseHistoryVO) throws Exception
	{
		purchaseHistoryDAO.addPurchaseHistory(purchaseHistoryVO);
	}
	public void update(PurchaseHistoryVO purchaseHistoryVO) throws Exception
	{
		purchaseHistoryDAO.updatePurchaseHistory(purchaseHistoryVO);
	}
	public List<PurchaseHistoryVO> getAll(int purchaseId) throws Exception
	{
		return purchaseHistoryDAO.getPurchaseHistoryByPurchaseId(purchaseId);
	}
}
