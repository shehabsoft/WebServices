package com;

import com.HomeBudget.Entities.HibernateMain;
import com.HomeBudget.Sessions.CategorySession;
import com.HomeBudget.Sessions.LocationSession;
import com.HomeBudget.Sessions.MonthlyBudgetSession;
import com.HomeBudget.Sessions.PurchaseSession;
import com.HomeBudget.Sessions.UserSession;
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

		if(transactionVO.getServiceCode()==1)
        {
        	CategorySession categorySession=new CategorySession();
        	categorySession.addCategory(transactionVO.getCategoryVO());
        }else if(transactionVO.getServiceCode()==2)
        {
        	PurchaseSession purchaseSession=new PurchaseSession();
        	purchaseSession.addPurchase(transactionVO.getPurchaseVO());
        }else if(transactionVO.getServiceCode()==3)
        {
        	LocationSession locationSession=new LocationSession();
        	locationSession.addLocation(transactionVO.getLocationVO());
        }else if(transactionVO.getServiceCode()==4)
        {
        	MonthlyBudgetSession monthlyBudgetSession=new MonthlyBudgetSession();
        	monthlyBudgetSession.addMonthlyBudget(transactionVO.getMonthlyBudgetVO());
        	
        }
        else if(transactionVO.getServiceCode()==5)
        {
        	UserSession   userSession=new UserSession();
        	userSession.addUser(transactionVO.getUserVO());
        }
		return ResponseBuilder.getCreateTransactionResponse(transactionVO);
		}catch(Exception e)
		{
			System.out.print(e);
			return ResponseBuilder.getCreateTransactionResponse(e);
		}
	
	}
}