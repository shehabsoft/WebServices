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
import com.HomeBudget.Sessions.LocationSession;
import com.HomeBudget.Sessions.PurchaseSession;
import com.dataObject.CategoryVO;
import com.dataObject.LocationVO;
import com.dataObject.PurchaseVO;
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



	

	Logger logger = Logger.getLogger(Lookups.class);
	


//	@GET
//	@Path("/GetCategoryByEnglishName")
//	@Produces("application/json")
//	public String getCategoryByEnglishName(@Context HttpHeaders headers) 
//	{
//		categorySession=new CategorySession();
//		String englishcategoryName = headers.getRequestHeader("objectName").get(0);
//		CategoryVO categoryVO=categorySession.getCategoryByEnglishName(englishcategoryName);
//		Gson gson = new Gson();
//	    String feeds = gson.toJson(categoryVO);
//		return feeds;
//	}
	@GET
	@Path("/GetExpensesCategories")
	@Produces("application/json")
	public String getExpensesCategories(@Context HttpHeaders headers) 
	{
		categorySession=new CategorySession();
		ArrayList<CategoryVO> categoryVOList=categorySession.getExpensesCategories();
		Gson gson = new Gson();
	    String feeds = gson.toJson(categoryVOList);
	   
		return "{CategoryVO:" + feeds + "}";
	}
	@GET
	@Path("/GetAllPurchases")
	@Produces("application/json")
	public String GetAllPurchases(@Context HttpHeaders headers) throws Exception 
	{
		purchaseSession=new PurchaseSession();
		ArrayList<PurchaseVO> categoryVOList=purchaseSession.getAllPurchases();
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
	
	@GET
	@Path("/GetBudgetCategories")
	@Produces("application/json")
	public String GetBudgetCategories(@Context HttpHeaders headers) throws Exception 
	{
		categorySession=new CategorySession();
		ArrayList<CategoryVO> categoryVOList=categorySession.GetBudgetCategories();
		Gson gson = new Gson();
	    String feeds = gson.toJson(categoryVOList);
	   
		return "{CategoryVO:" + feeds + "}";
	}
	

	}