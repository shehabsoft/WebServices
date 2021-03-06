package com.HomeBudget.DAO;

import java.util.List;

import com.HomeBudget.DAO.JPA.DataAccessObject1;
import com.dataObject.PurchaseVO;

public interface PurchaseDAO extends DataAccessObject1 {
	public void addPurchase(PurchaseVO purchaseVO) throws Exception;
	public void approvePurchase(PurchaseVO purchaseVO) throws Exception;
	public void rejectPurchase(PurchaseVO purchaseVO) throws Exception;
	public void updatePurchase(PurchaseVO purchaseVO) throws Exception;
	public List<PurchaseVO> getAllPurchases(int monthlyBudgetId) throws Exception;
	public List<PurchaseVO> getAllPurchases(int monthlyBudgetId,int categoryId) throws Exception;
	public List<PurchaseVO> getUnApprovedPurchasesByCategoryId(int categoryId) throws Exception ;
	public List<PurchaseVO> getMonthlyPurchases(int monthlyBudgetId) throws Exception;
	public List<PurchaseVO> getMonthlyPurchases(int monthlyBudgetId,int categoryId) throws Exception;
}
