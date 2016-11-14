package com.HomeBudget.DAO;

import java.util.ArrayList;
import java.util.List;

import com.dataObject.PurchaseVO;

public interface PurchaseDAO {
	public void addPurchase(PurchaseVO purchaseVO) throws Exception;
	public void updatePurchase(PurchaseVO purchaseVO) throws Exception;
	public List<PurchaseVO> getAllPurchases(int monthlyBudgetId) throws Exception;
}
