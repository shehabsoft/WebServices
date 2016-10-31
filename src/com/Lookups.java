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
	// This method is called if TEXT_PLAIN is request

	Gson gson = new Gson();
    CategorySession categorySession=null;
    PurchaseSession purchaseSession=null;
    LocationSession locationSession=null;
    CurrencySession currencySession=null;
    CountrySession countrySession=null;
	UserSession userSession=null;
	MonthlyBudgetSession monthlyBudgetSession=null;



	

	Logger logger = Logger.getLogger(Lookups.class);
	



	@POST
	@Path("/GetExpensesCategories")
	@Produces("application/json")
	public String getExpensesCategories(@Context HttpHeaders headers,String content) 
	{
		int index = content.lastIndexOf("=");
		int userId =Integer.parseInt(content.substring(index + 1));
		monthlyBudgetSession=new MonthlyBudgetSession();
		int monthlyBudgetId	=monthlyBudgetSession.getActiveMonthlyBudgetIdByUserId(userId);
		categorySession=new CategorySession();
		ArrayList<CategoryVO> categoryVOList=categorySession.getExpensesCategories(monthlyBudgetId,userId);
		Gson gson = new Gson();
	    String feeds = gson.toJson(categoryVOList);
	   
		return "{CategoryVO:" + feeds + "}";
	}

	
	@POST
	@Path("/GetAllExpensesCategories")
	@Produces("application/json")
	public String getAllExpensesCategories(@Context HttpHeaders headers,String content) 
	{
		int index = content.lastIndexOf("=");
		int userId =Integer.parseInt(content.substring(index + 1));
		categorySession=new CategorySession();
		ArrayList<CategoryVO> categoryVOList=categorySession.getAllExpensesCategories(userId);
		Gson gson = new Gson();
	    String feeds = gson.toJson(categoryVOList);
	   
		return "{CategoryVO:" + feeds + "}";
	}
	@POST
	@Path("/GetAllPurchases")
	@Produces("application/json")
	public String GetAllPurchases(@Context HttpHeaders headers,String content) throws Exception 
	{
		monthlyBudgetSession=new MonthlyBudgetSession();
		int index = content.lastIndexOf("=");
		int userId =Integer.parseInt(content.substring(index + 1));
		int monthlyBudgetId	=monthlyBudgetSession.getActiveMonthlyBudgetIdByUserId(userId);
		purchaseSession=new PurchaseSession();
		ArrayList<PurchaseVO> categoryVOList=purchaseSession.getAllPurchases(monthlyBudgetId);
		Gson gson = new Gson();
	    String feeds = gson.toJson(categoryVOList);
	   
		return "{PurchaseVO:" + feeds + "}";
	}
	@GET
	@Path("/GetAllLocations")
	@Produces("application/json")
	public String GetAllLocations(@Context HttpHeaders headers) throws Exception 
	{
		locationSession=new LocationSession();
		ArrayList<LocationVO> locationList=locationSession.getAllLocations();
		Gson gson = new Gson();
	    String feeds = gson.toJson(locationList);
	   
		return "{LocationVO:" + feeds + "}";
	}
	
	@POST
	@Path("/GetBudgetCategories")
	@Produces("application/json")
	public String GetBudgetCategories(@Context HttpHeaders headers,String content) throws Exception 
	{
		categorySession=new CategorySession();
		monthlyBudgetSession=new MonthlyBudgetSession();
		int index = content.lastIndexOf("=");
		int userId =Integer.parseInt(content.substring(index + 1));
		int monthlyBudgetId	=monthlyBudgetSession.getActiveMonthlyBudgetIdByUserId(userId);
		ArrayList<CategoryVO> categoryVOList=categorySession.GetBudgetCategories(monthlyBudgetId,userId);
		Gson gson = new Gson();
	    String feeds = gson.toJson(categoryVOList);
	   
		return "{CategoryVO:" + feeds + "}";
	}
	@POST
	@Path("/GetAllBudgetCategories")
	@Produces("application/json")
	public String GetAllBudgetCategories(@Context HttpHeaders headers,String content) throws Exception 
	{
		categorySession=new CategorySession();
		monthlyBudgetSession=new MonthlyBudgetSession();
		int index = content.lastIndexOf("=");
		int userId =Integer.parseInt(content.substring(index + 1));
		ArrayList<CategoryVO> categoryVOList=categorySession.GetAllBudgetCategories(userId);
		Gson gson = new Gson();
	    String feeds = gson.toJson(categoryVOList);
	   
		return "{CategoryVO:" + feeds + "}";
	}
	@GET
	@Path("/GetAllCurrencies")
	@Produces("application/json")
	public String GetAllCurrencies(@Context HttpHeaders headers) throws Exception 
	{
		currencySession=new CurrencySession();
		ArrayList<CurrencyVO> categoryVOList=currencySession.getAllCurrencies();
		Gson gson = new Gson();
	    String feeds = gson.toJson(categoryVOList);
	   
		return "{CurrencyVO:" + feeds + "}";
	}
	@GET
	@Path("/getAllCountries")
	@Produces("application/json")
	public String getAllCountries(@Context HttpHeaders headers) throws Exception 
	{
		countrySession=new CountrySession();
		ArrayList<CountryVO> categoryVOList=countrySession.getAllCountries();
		Gson gson = new Gson();
	    String feeds = gson.toJson(categoryVOList);
	   
		return "{CountryVO:" + feeds + "}";
	}
	@GET
	@Path("/test")
	@Produces("application/json")
	public String test(@Context HttpHeaders headers) throws Exception 
	{
	 Collections feeds=new Collections();
	 feeds.initialize();
	   
		return "{CountryVO:" + feeds + "}";
	}
	@POST
	@Path("/getActiveMonthlyBudgetByUserId")
	@Produces("application/json")
	public String getActiveMonthlyBudgetByUserId(@Context HttpHeaders headers,String content) throws Exception 
	{
		int index = content.lastIndexOf("=");
		int userId =Integer.parseInt(content.substring(index + 1));
		monthlyBudgetSession=new MonthlyBudgetSession();
	    MonthlyBudgetVO monthlyBudgetVO=monthlyBudgetSession.getActiveMonthlyBudgetByUserId(userId);
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
	    userSession=new UserSession();
		
	    boolean validMail=userSession.checkMail(mail);
	    StatusVO statusVO=new StatusVO();
	    statusVO.setFlage(validMail);
		Gson gson = new Gson();
	    String feeds = gson.toJson(statusVO);
	   
		return "{StatusVO:" + feeds + "}";
	}
	
	

	}