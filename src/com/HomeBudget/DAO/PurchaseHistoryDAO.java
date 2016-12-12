/**
 * 
 */
package com.HomeBudget.DAO;

import java.util.List;

import com.HomeBudget.DAO.JPA.DataAccessObject1;
import com.HomeBudget.dataObject.PurchaseHistoryVO;
import com.dataObject.PurchaseVO;
import com.entities.models.PurchaseHistory;

/**
 * @author Shehab
 *
 */
public interface PurchaseHistoryDAO  extends DataAccessObject1{

	public PurchaseHistory addPurchaseHistory(PurchaseHistoryVO purchaseHistoryVO);
	
	public PurchaseHistory addPurchaseHistory(PurchaseVO purchaseVO);
	
	public void updatePurchaseHistory(PurchaseHistoryVO purchaseHistoryVO);
	
	public List<PurchaseHistoryVO>getPurchaseHistoryByPurchaseId(int purchaseId) throws Exception;
	
	
}
