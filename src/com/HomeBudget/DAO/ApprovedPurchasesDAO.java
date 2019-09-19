/**
 * 
 */
package com.HomeBudget.DAO;

import java.util.ArrayList;

import com.HomeBudget.DAO.JPA.DataAccessObject1;
import com.dataObject.CategoryVO;
import com.dataObject.MonthlyBudgetVO;
import com.dataObject.PurchaseVO;

/**
 * @author Shehab
 *
 */
public interface ApprovedPurchasesDAO extends DataAccessObject1 {
	
	public Integer add(PurchaseVO purchaseVO)throws Exception;
	public boolean validateBeforeAdd(PurchaseVO purchaseVO)throws Exception;
	
}
