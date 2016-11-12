/**
 * 
 */
package com.HomeBudget.Bus;

import java.util.ArrayList;

import com.HomeBudget.DAO.PurchaseDAO;
import com.HomeBudget.DAO.PurchaseDAOImpl;
import com.dataObject.PurchaseVO;

/**
 * @author Shehab
 *
 */
public class PurchaseHandler {
	private PurchaseDAO purchaseDAO;
	
	public PurchaseHandler()
	{
		super();
		purchaseDAO=new PurchaseDAOImpl();
	}
	
	public void add(PurchaseVO purchaseVO) throws Exception
	{
		purchaseDAO.addPurchase(purchaseVO);
	}
	public void update(PurchaseVO purchaseVO) throws Exception
	{
		purchaseDAO.updatePurchase(purchaseVO);
	}
	public ArrayList<PurchaseVO> getAll(int monthlyBudgetId) throws Exception
	{
		return purchaseDAO.getAllPurchases(monthlyBudgetId);
	}
}
