/**
 * 
 */
package com.HomeBudget.DAO;

import java.util.List;

import com.HomeBudget.dataObject.PurchaseHistoryVO;
import com.entities.models.PurchaseHistory;

/**
 * @author Shehab
 *
 */
public interface PurchaseHistoryDAO {

	public PurchaseHistory addPurchaseHistory(PurchaseHistoryVO purchaseHistoryVO);
	
	public void updatePurchaseHistory(PurchaseHistoryVO purchaseHistoryVO);
	
	public List<PurchaseHistoryVO>getPurchaseHistoryByPurchaseId(int purchaseId) throws Exception;
	
	
}
