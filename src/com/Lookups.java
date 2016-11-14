package com;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceRef;
import javax.xml.ws.handler.MessageContext;

import java.util.ArrayList;

import javax.annotation.Resource;



import org.apache.log4j.Logger;

import com.HomeBudget.Bus.CategoryHandler;
import com.HomeBudget.Bus.CountryHandler;
import com.HomeBudget.Bus.CurrencyHandler;
import com.HomeBudget.Bus.LocationHandler;
import com.HomeBudget.Bus.MonthlyBudgetHandler;
import com.HomeBudget.Bus.PurchaseHandler;
import com.HomeBudget.Bus.UserHandler;
import com.HomeBudget.Sessions.CategorySession;
import com.HomeBudget.Sessions.CountrySession;
import com.HomeBudget.Sessions.CurrencySession;
import com.HomeBudget.Sessions.LocationSession;
import com.HomeBudget.Sessions.MonthlyBudgetSession;
import com.HomeBudget.Sessions.PurchaseSession;
import com.HomeBudget.Sessions.UserSession;
import com.dataObject.CategoryVO;
import com.dataObject.CountryVO;
import com.dataObject.CurrencyVO;
import com.dataObject.LocationVO;
import com.dataObject.MonthlyBudgetVO;
import com.dataObject.PurchaseVO;
import com.dataObject.StatusVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.core.util.Base64;


@Path("/getData")
public class Lookups {
	

	Gson gson = new Gson();
    CategoryHandler categoryHandler=null;
   
    PurchaseHandler purchaseHandler=null;
   
    LocationHandler locationHandler=null;

    
    CurrencyHandler currencyHandler=null;
    
    CountryHandler countryHandler=null;
	
	UserHandler userHandler=null;
	
    MonthlyBudgetHandler monthlyBudgetHandler=null;

	

	Logger logger = Logger.getLogger(Lookups.class);
	



	@POST
	@Path("/GetExpensesCategories")
	@Produces("application/json;charset=utf-8;encoding=windows-1256")
	public String getExpensesCategories(@Context HttpHeaders headers,String content) 
	{
		logger.info("Calling GetExpensesCategories.............");
		int index = content.lastIndexOf("=");
		int userId =Integer.parseInt(content.substring(index + 1));
		monthlyBudgetHandler=new MonthlyBudgetHandler();
		int monthlyBudgetId	=monthlyBudgetHandler.getActiveMonthlyBudgetIdByUserId(userId);
		categoryHandler=new CategoryHandler();
		ArrayList<CategoryVO> categoryVOList=categoryHandler.getExpensesCategories(monthlyBudgetId,userId);
		Gson gson = new Gson();
	    String feeds = gson.toJson(categoryVOList);
	    logger.info("Output............."+feeds);
	   
		return "{CategoryVO:" + feeds + "}";
	}

	
	@POST
	@Path("/GetAllExpensesCategories")
	@Produces("application/json;charset=utf-8;encoding=windows-1256")
	public String getAllExpensesCategories(@Context HttpHeaders headers,String content) 
	{
		int index = content.lastIndexOf("=");
		int userId =Integer.parseInt(content.substring(index + 1));
		categoryHandler=new CategoryHandler();
		ArrayList<CategoryVO> categoryVOList=categoryHandler.getAllExpensesCategories(userId);
		Gson gson = new Gson();
	    String feeds = gson.toJson(categoryVOList);
	   
		return "{CategoryVO:" + feeds + "}";
	}
	@POST
	@Path("/GetAllPurchases")
	@Produces("application/json;charset=utf-8")
	public String GetAllPurchases(@Context HttpHeaders headers,String content) throws Exception 
	{
		monthlyBudgetHandler=new MonthlyBudgetHandler();
		int index = content.lastIndexOf("=");
		int userId =Integer.parseInt(content.substring(index + 1));
		int monthlyBudgetId	=monthlyBudgetHandler.getActiveMonthlyBudgetIdByUserId(userId);
		purchaseHandler=new PurchaseHandler();
		ArrayList<PurchaseVO> categoryVOList=(ArrayList<PurchaseVO>) purchaseHandler.getAll(monthlyBudgetId);
		Gson gson = new Gson();
	    String feeds = gson.toJson(categoryVOList);
	   
		return "{PurchaseVO:" + feeds + "}";
	}
	@GET
	@Path("/GetAllLocations")
	@Produces("application/json")
	public String GetAllLocations(@Context HttpHeaders headers) throws Exception 
	{
		locationHandler=new LocationHandler();
		ArrayList<LocationVO> locationList=locationHandler.getAll();
		Gson gson = new Gson();
	    String feeds = gson.toJson(locationList);
	   
		return "{LocationVO:" + feeds + "}";
	}
	
	@POST
	@Path("/GetBudgetCategories")
	@Produces("application/json;charset=utf-8")
	public String GetBudgetCategories(@Context HttpHeaders headers,String content) throws Exception 
	{
		categoryHandler=new CategoryHandler();
		monthlyBudgetHandler=new MonthlyBudgetHandler();
		int index = content.lastIndexOf("=");
		int userId =Integer.parseInt(content.substring(index + 1));
		int monthlyBudgetId	=monthlyBudgetHandler.getActiveMonthlyBudgetIdByUserId(userId);
		ArrayList<CategoryVO> categoryVOList=categoryHandler.getBudgetCategories(monthlyBudgetId,userId);
		Gson gson = new Gson();
	    String feeds = gson.toJson(categoryVOList);
	   
		return "{CategoryVO:" + feeds + "}";
	}
	@POST
	@Path("/GetAllBudgetCategories")
	@Produces("application/json;charset=utf-8")
	public String GetAllBudgetCategories(@Context HttpHeaders headers,String content) throws Exception 
	{
		categoryHandler=new CategoryHandler();
		int index = content.lastIndexOf("=");
		int userId =Integer.parseInt(content.substring(index + 1));
		ArrayList<CategoryVO> categoryVOList=categoryHandler.getAllBudgetCategories(userId);
		Gson gson = new Gson();
	    String feeds = gson.toJson(categoryVOList);
	   
		return "{CategoryVO:" + feeds + "}";
	}
	@GET
	@Path("/GetAllCurrencies")
	@Produces("application/json")
	public String GetAllCurrencies(@Context HttpHeaders headers) throws Exception 
	{
		currencyHandler=new CurrencyHandler();
		ArrayList<CurrencyVO> categoryVOList=currencyHandler.getAll();
		Gson gson = new Gson();
	    String feeds = gson.toJson(categoryVOList);
	   
		return "{CurrencyVO:" + feeds + "}";
	}
	@GET
	@Path("/getAllCountries")
	@Produces("application/json")
	public String getAllCountries(@Context HttpHeaders headers) throws Exception 
	{
		countryHandler=new  CountryHandler();
		ArrayList<CountryVO> categoryVOList=countryHandler.getAll();
		Gson gson = new Gson();
	    String feeds = gson.toJson(categoryVOList);
	   
		return "{CountryVO:" + feeds + "}";
	}

	@POST
	@Path("/getActiveMonthlyBudgetByUserId")
	@Produces("application/json")
	public String getActiveMonthlyBudgetByUserId(@Context HttpHeaders headers,String content) throws Exception 
	{
		int index = content.lastIndexOf("=");
		int userId =Integer.parseInt(content.substring(index + 1));
		monthlyBudgetHandler=new MonthlyBudgetHandler();
	    MonthlyBudgetVO monthlyBudgetVO=monthlyBudgetHandler.getActiveMonthlyBudgetByUserId(userId);
		Gson gson = new Gson();
	    String feeds = gson.toJson(monthlyBudgetVO);
	   
		return "{MonthlyBudgetVO:" + feeds + "}";
	}
	
	
	@POST
	@Path("/checkEmail")
	@Produces("application/json")
	public String checkEmail(@Context HttpHeaders headers,String content) throws Exception 
	{
		
		int index = content.lastIndexOf("=");
		String mail =content.substring(index + 1);
		userHandler=new  UserHandler();
		
	    boolean validMail=userHandler.checkMail(mail);
	    StatusVO statusVO=new StatusVO();
	    statusVO.setFlage(validMail);
		Gson gson = new Gson();
	    String feeds = gson.toJson(statusVO);
	   
		return "{StatusVO:" + feeds + "}";
	}
	
	

	}