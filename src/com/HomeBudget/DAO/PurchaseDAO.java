package com.HomeBudget.DAO;

import java.util.ArrayList;

import com.dataObject.PurchaseVO;

public interface PurchaseDAO {
	public void addPurchase(PurchaseVO purchaseVO) throws Exception;
	public void updatePurchase(PurchaseVO purchaseVO) throws Exception;
	public ArrayList<PurchaseVO> getAllPurchases(int monthlyBudgetId) throws Exception;
}
