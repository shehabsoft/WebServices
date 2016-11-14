package com;


import java.util.Date;

import org.apache.log4j.Logger;

import com.HomeBudget.Bus.CategoryHandler;
import com.HomeBudget.Bus.InfoTraceLogHandler;
import com.HomeBudget.Bus.LocationHandler;
import com.HomeBudget.Bus.MonthlyBudgetHandler;
import com.HomeBudget.Bus.PurchaseHandler;
import com.HomeBudget.Bus.PurchaseHistoryHandler;
import com.HomeBudget.Bus.UserHandler;
import com.HomeBudget.Sessions.CategorySession;
import com.HomeBudget.Sessions.LocationSession;
import com.HomeBudget.Sessions.MonthlyBudgetSession;
import com.HomeBudget.Sessions.PurchaseSession;
import com.HomeBudget.Sessions.UserSession;
import com.HomeBudget.common.TransactionServiceParser;
import com.HomeBudget.dataObject.InfoTraceLogVO;
import com.dataObject.Constants;
import com.dataObject.ResponseBuilder;
import com.dataObject.TransactionVO;

public class TransactionService {
	public String helloName(String name){
		return "Hello there " + name;
		 } 
	Logger logger=Logger.getLogger(TransactionService.class);
	private InfoTraceLogHandler infoTraceLogHandler;
	public String createTransaction(String xmlSata) 
	{
		InfoTraceLogVO infoTraceLogVO=new InfoTraceLogVO();
		String responseData="";
		try
		{
		logger.info("Calling CreateTransaction................");	
		infoTraceLogHandler=new InfoTraceLogHandler();
		logger.info("Transaction Request :"+xmlSata);
		System.out.println(xmlSata);
		TransactionServiceParser serviceParser=new TransactionServiceParser();
	    TransactionVO transactionVO=	serviceParser.parseCreateTransaction(xmlSata);
    
		if(transactionVO.getServiceCode()==Constants.ADD_CATEGORY_SERVICE)
        {
        	CategoryHandler categoryHandler=new CategoryHandler();
        	categoryHandler.add(transactionVO.getCategoryVO());
        }else if(transactionVO.getServiceCode()==Constants.ADD_PURCHASE_SERVICE)
        {
        	PurchaseHandler purchaseHandler=new PurchaseHandler();
        	purchaseHandler.add(transactionVO.getPurchaseVO());
        }else if(transactionVO.getServiceCode()==Constants.ADD_LOCATION_SERVICE)
        {
        	LocationHandler locationHandler=new LocationHandler();
        	locationHandler.add(transactionVO.getLocationVO());
        }else if(transactionVO.getServiceCode()==Constants.ADD_USER_SERVICE)
        {
        	UserHandler   userHandler=new UserHandler();
        	userHandler.add(transactionVO.getUserVO());
        	
        }
        else if(transactionVO.getServiceCode()==Constants.ADD_BUDGET_SERVICE)
        {
         	MonthlyBudgetHandler monthlyBudgetHandler=new MonthlyBudgetHandler();
         	monthlyBudgetHandler.add(transactionVO.getMonthlyBudgetVO());
        
        }
        else if(transactionVO.getServiceCode()==Constants.EDIT_PURCHASE_SERVICE)
        {
        	PurchaseHandler purchaseHandler=new PurchaseHandler();
        	purchaseHandler.update(transactionVO.getPurchaseVO());
        
        }
        else if(transactionVO.getServiceCode()==Constants.EDIT_CATEGORY_EXPENSES_SERVICE||transactionVO.getServiceCode()==Constants.EDIT_CATEGORY_REVENUES_SERVICE)
        {
        	CategoryHandler categoryHandler=new CategoryHandler();
        	categoryHandler.update(transactionVO.getCategoryVO());
        
        }else if(transactionVO.getServiceCode()==Constants.EDIT_MONTHLY_BUDGET_SERVICE)
        {
        	MonthlyBudgetHandler monthlyBudgetHandler=new MonthlyBudgetHandler();
        	monthlyBudgetHandler.update(transactionVO.getMonthlyBudgetVO());
        }else if(transactionVO.getServiceCode()==Constants.ADD_PURCHASE_HISTORY_SERVICE)
        {
        	PurchaseHistoryHandler purchaseHistoryHandler=new PurchaseHistoryHandler();
        	purchaseHistoryHandler.add(transactionVO.getPurchaseHistoryVO());
        }
	
       
        infoTraceLogVO.setRequestData(xmlSata);
        infoTraceLogVO.setRequestDate(new Date());	
        infoTraceLogVO.setResponseDate(new Date());	
		responseData=ResponseBuilder.getCreateTransactionResponse(transactionVO);
		infoTraceLogVO.setResponseData(responseData);
		infoTraceLogHandler.add(infoTraceLogVO);
		logger.info("Transaction Response :"+responseData);
		return responseData;
		}catch(Exception e)
		{
			try {
			System.out.print(e);
			responseData=ResponseBuilder.getCreateTransactionResponse(e);
			infoTraceLogVO.setResponseData(responseData);
			logger.error(e);
			infoTraceLogVO.setRequestData(xmlSata);
	        infoTraceLogVO.setRequestDate(new Date());	
	        infoTraceLogVO.setResponseDate(new Date());	
			infoTraceLogHandler.add(infoTraceLogVO);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				logger.error(e1);
				e1.printStackTrace();
			}
			return responseData;
		}
	
	}
}