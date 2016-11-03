package com;


import com.HomeBudget.Sessions.CategorySession;
import com.HomeBudget.Sessions.LocationSession;
import com.HomeBudget.Sessions.MonthlyBudgetSession;
import com.HomeBudget.Sessions.PurchaseSession;
import com.HomeBudget.Sessions.UserSession;
import com.dataObject.Constants;
import com.dataObject.ResponseBuilder;
import com.dataObject.TransactionServiceParser;
import com.dataObject.TransactionVO;

public class TransactionService {
	public String helloName(String name){
		return "Hello there " + name;
		 } 
	
	public String createTransaction(String xmlSata)
	{
		try
		{
		System.out.println(xmlSata);
		TransactionServiceParser serviceParser=new TransactionServiceParser();
	    TransactionVO transactionVO=	serviceParser.parseCreateTransaction(xmlSata);

		if(transactionVO.getServiceCode()==Constants.ADD_CATEGORY_SERVICE)
        {
        	CategorySession categorySession=new CategorySession();
        	categorySession.addCategory(transactionVO.getCategoryVO());
        }else if(transactionVO.getServiceCode()==Constants.ADD_PURCHASE_SERVICE)
        {
        	PurchaseSession purchaseSession=new PurchaseSession();
        	purchaseSession.addPurchase(transactionVO.getPurchaseVO());
        }else if(transactionVO.getServiceCode()==Constants.ADD_LOCATION_SERVICE)
        {
        	LocationSession locationSession=new LocationSession();
        	locationSession.addLocation(transactionVO.getLocationVO());
        }else if(transactionVO.getServiceCode()==Constants.ADD_USER_SERVICE)
        {
        	UserSession   userSession=new UserSession();
        	userSession.addUser(transactionVO.getUserVO());
        	
        }
        else if(transactionVO.getServiceCode()==Constants.ADD_BUDGET_SERVICE)
        {
         	MonthlyBudgetSession monthlyBudgetSession=new MonthlyBudgetSession();
        	monthlyBudgetSession.addMonthlyBudget(transactionVO.getMonthlyBudgetVO());
        
        }
        else if(transactionVO.getServiceCode()==Constants.EDIT_PURCHASE_SERVICE)
        {
        	PurchaseSession purchaseSession=new PurchaseSession();
        	purchaseSession.updatePurchase(transactionVO.getPurchaseVO());
        
        }
		return ResponseBuilder.getCreateTransactionResponse(transactionVO);
		}catch(Exception e)
		{
			System.out.print(e);
			return ResponseBuilder.getCreateTransactionResponse(e);
		}
	
	}
}