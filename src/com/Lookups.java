package com;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.apache.log4j.Logger;

import com.HomeBudget.Bus.CategoryHandler;
import com.HomeBudget.Bus.CategoryHistoryHandler;
import com.HomeBudget.Bus.CountryHandler;
import com.HomeBudget.Bus.CurrencyHandler;
import com.HomeBudget.Bus.LocationHandler;
import com.HomeBudget.Bus.MonthlyBudgetHandler;
import com.HomeBudget.Bus.PurchaseHandler;
import com.HomeBudget.Bus.PurchaseHistoryHandler;
import com.HomeBudget.Bus.UserHandler;
import com.HomeBudget.dataObject.CategoryHistoryVO;
import com.HomeBudget.dataObject.PurchaseHistoryVO;
import com.dataObject.CategoryVO;
import com.dataObject.CountryVO;
import com.dataObject.CurrencyVO;
import com.dataObject.LocationVO;
import com.dataObject.MonthlyBudgetVO;
import com.dataObject.PurchaseVO;
import com.dataObject.ServiceLocator;
import com.dataObject.StatusVO;
import com.dataObject.UserVO;
import com.google.gson.Gson;

@Path("/getData")
public class Lookups {

	Gson gson = new Gson();
	CategoryHandler categoryHandler = null;

	PurchaseHandler purchaseHandler = null;
	PurchaseHistoryHandler purchaseHistoryHandler = null;
	CategoryHistoryHandler categoryHistoryHandler = null;
	LocationHandler locationHandler = null;

	CurrencyHandler currencyHandler = null;

	CountryHandler countryHandler = null;

	UserHandler userHandler = null;

	MonthlyBudgetHandler monthlyBudgetHandler = null;

	Logger logger = Logger.getLogger(Lookups.class);

	@POST
	@Path("/GetExpensesCategories")
	@Produces("application/json;charset=utf-8;encoding=windows-1256")
	public String getExpensesCategories(@Context HttpHeaders headers, String content) throws Exception {
		try {
			logger.info("Calling GetExpensesCategories.............");
			int index = content.lastIndexOf("=");
			int userId = Integer.parseInt(content.substring(index + 1));
			monthlyBudgetHandler = new MonthlyBudgetHandler();
			int monthlyBudgetId = monthlyBudgetHandler.getActiveMonthlyBudgetIdByUserId(userId);
			categoryHandler = new CategoryHandler();
			ArrayList<CategoryVO> categoryVOList = categoryHandler.getExpensesCategories(monthlyBudgetId, userId);
			Gson gson = new Gson();
			String feeds = gson.toJson(categoryVOList);
			logger.info("Output............." + feeds);

			return "{CategoryVO:" + feeds + "}";
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	@POST
	@Path("/GetAllExpensesCategories")
	@Produces("application/json;charset=utf-8;encoding=windows-1256")
	public String getAllExpensesCategories(@Context HttpHeaders headers, String content) throws Exception {
		try {
			int index = content.lastIndexOf("=");
			int userId = Integer.parseInt(content.substring(index + 1));
			categoryHandler = new CategoryHandler();
			ArrayList<CategoryVO> categoryVOList = categoryHandler.getAllExpensesCategories(userId);
			Gson gson = new Gson();
			String feeds = gson.toJson(categoryVOList);

			return "{CategoryVO:" + feeds + "}";
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	@POST
	@Path("/GetAllPurchases")
	@Produces("application/json;charset=utf-8")
	public String GetAllPurchases(@Context HttpHeaders headers, String content) throws Exception {
		try {
			monthlyBudgetHandler = new MonthlyBudgetHandler();
			int index = content.lastIndexOf("=");
			int userId = Integer.parseInt(content.substring(index + 1));
			int monthlyBudgetId = monthlyBudgetHandler.getActiveMonthlyBudgetIdByUserId(userId);
			purchaseHandler = new PurchaseHandler();
			ArrayList<PurchaseVO> categoryVOList = (ArrayList<PurchaseVO>) purchaseHandler.getAll(monthlyBudgetId);
			Gson gson = new Gson();
			String feeds = gson.toJson(categoryVOList);

			return "{PurchaseVO:" + feeds + "}";
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}

	}

	@POST
	@Path("/getMonthlyPurchases")
	@Produces("application/json;charset=utf-8")
	public String getMonthlyPurchases(@Context HttpHeaders headers, String content) throws Exception {
		try {
			monthlyBudgetHandler = new MonthlyBudgetHandler();
			int index = content.lastIndexOf("=");
			int userId = Integer.parseInt(content.substring(index + 1));
			int monthlyBudgetId = monthlyBudgetHandler.getActiveMonthlyBudgetIdByUserId(userId);
			purchaseHandler = new PurchaseHandler();
			ArrayList<PurchaseVO> categoryVOList = (ArrayList<PurchaseVO>) purchaseHandler.getMonthlyPurchases(monthlyBudgetId);
			Gson gson = new Gson();
			String feeds = gson.toJson(categoryVOList);

			return "{PurchaseVO:" + feeds + "}";
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}

	}
	@POST
	@Path("/getMonthlyPurchasesListByCategory")
	@Produces("application/json;charset=utf-8")
	public String getMonthlyPurchasesListByCategory(@Context HttpHeaders headers, String content) throws Exception {
		try {
			monthlyBudgetHandler = new MonthlyBudgetHandler();
			int index = content.lastIndexOf("=");
			int userId = Integer.parseInt(content.substring(index + 1));
			int categoryId = Integer.parseInt(headers.getRequestHeader("categoryId").get(0));
			int monthlyBudgetId = monthlyBudgetHandler.getActiveMonthlyBudgetIdByUserId(userId);
			purchaseHandler = new PurchaseHandler();
			ArrayList<PurchaseVO> categoryVOList = (ArrayList<PurchaseVO>) purchaseHandler.getMonthlyPurchases(monthlyBudgetId,categoryId);
			Gson gson = new Gson();
			String feeds = gson.toJson(categoryVOList);

			return "{PurchaseVO:" + feeds + "}";
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}

	}
	@POST
	@Path("/getPurchasesByCategoryId")
	@Produces("application/json;charset=utf-8")
	public String getPurchasesByCategoryId(@Context HttpHeaders headers, String content) throws Exception {
		try {
			monthlyBudgetHandler = new MonthlyBudgetHandler();
			int index = content.lastIndexOf("=");
			int userId = Integer.parseInt(content.substring(index + 1));
			int categoryId = Integer.parseInt(headers.getRequestHeader("categoryId").get(0));
			int monthlyBudgetId = monthlyBudgetHandler.getActiveMonthlyBudgetIdByUserId(userId);
			purchaseHandler = new PurchaseHandler();
			ArrayList<PurchaseVO> categoryVOList = (ArrayList<PurchaseVO>) purchaseHandler.getAll(monthlyBudgetId,
					categoryId,userId);
			Gson gson = new Gson();
			String feeds = gson.toJson(categoryVOList);

			return "{PurchaseVO:" + feeds + "}";
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}

	}
	@POST
	@Path("/getPurchasesHistoryChartByApprovedPurchaseId")
	@Produces("application/json;charset=utf-8")
	public String getPurchasesHistoryChartByApprovedPurchaseId(@Context HttpHeaders headers, String content) throws Exception {
		try {
			 
			int index = content.lastIndexOf("=");
			int userId = Integer.parseInt(content.substring(index + 1));
			int approvedPurchaseId = Integer.parseInt(headers.getRequestHeader("approvedPurchaseId").get(0));
			 
			purchaseHistoryHandler = new PurchaseHistoryHandler();
			PurchaseVO PurchaseVO = ( PurchaseVO) purchaseHistoryHandler.getPurchasesHistoryChartByApprovedPurchaseId(approvedPurchaseId, userId);
					
			Gson gson = new Gson();
			String feeds = gson.toJson(PurchaseVO);

			return "{PurchaseVO:" + feeds + "}";
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}

	}
	@POST
	@Path("/showUnApprovedPurchases")
	@Produces("application/json;charset=utf-8")
	public String showUnApprovedPurchases(@Context HttpHeaders headers, String content) throws Exception {
		try {
			monthlyBudgetHandler = new MonthlyBudgetHandler();
			int index = content.lastIndexOf("=");
			int userId = Integer.parseInt(content.substring(index + 1));
			int categoryId = Integer.parseInt(headers.getRequestHeader("categoryId").get(0));
			purchaseHandler = new PurchaseHandler();
			ArrayList<PurchaseVO> categoryVOList = (ArrayList<PurchaseVO>) purchaseHandler.getUnApprovedPurchasesByCategoryId(categoryId);
				 
			Gson gson = new Gson();
			String feeds = gson.toJson(categoryVOList);

			return "{PurchaseVO:" + feeds + "}";
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}

	}

	@POST
	@Path("/getPurchaseHistory")
	@Produces("application/json;charset=utf-8")
	public String getPurchaseHistory(@Context HttpHeaders headers, String content) throws Exception {
		try {
		
			int purchaseId = Integer.parseInt(headers.getRequestHeader("purchaseId").get(0));
			purchaseHistoryHandler = new PurchaseHistoryHandler();
			
			ArrayList<PurchaseHistoryVO> purchaseHistoryVOs = (ArrayList<PurchaseHistoryVO>) purchaseHistoryHandler
					.getAll(purchaseId);
			Gson gson = new Gson();
			String feeds = gson.toJson(purchaseHistoryVOs);

			return "{PurchaseHistoryVO:" + feeds + "}";
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	@POST
	@Path("/getCategoryHistory")
	@Produces("application/json;charset=utf-8")
	public String getCategoryHistory(@Context HttpHeaders headers, String content) throws Exception {
		try {
			
			int categoryId = Integer.parseInt(headers.getRequestHeader("categoryId").get(0));
			categoryHistoryHandler = new CategoryHistoryHandler();
			ArrayList<CategoryHistoryVO> categoryHistoryVOs = (ArrayList<CategoryHistoryVO>) categoryHistoryHandler
					.getAll(categoryId);
			Gson gson = new Gson();
			String feeds = gson.toJson(categoryHistoryVOs);
			return "{CategoryHistoryVO:" + feeds + "}";
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	@GET
	@Path("/GetAllLocations")
	@Produces("application/json")
	public String GetAllLocations(@Context HttpHeaders headers) throws Exception {
		try {
			locationHandler = new LocationHandler();
			ArrayList<LocationVO> locationList = locationHandler.getAll();
			Gson gson = new Gson();
			String feeds = gson.toJson(locationList);

			return "{LocationVO:" + feeds + "}";
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	@POST
	@Path("/GetBudgetCategories")
	@Produces("application/json;charset=utf-8")
	public String GetBudgetCategories(@Context HttpHeaders headers, String content) throws Exception {
		try {
			categoryHandler = new CategoryHandler();
			monthlyBudgetHandler = new MonthlyBudgetHandler();
			int index = content.lastIndexOf("=");
			int userId = Integer.parseInt(content.substring(index + 1));
			int monthlyBudgetId = monthlyBudgetHandler.getActiveMonthlyBudgetIdByUserId(userId);
			ArrayList<CategoryVO> categoryVOList = categoryHandler.getBudgetCategories(monthlyBudgetId, userId);
			Gson gson = new Gson();
			String feeds = gson.toJson(categoryVOList);

			return "{CategoryVO:" + feeds + "}";
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	@POST
	@Path("/GetAllBudgetCategories")
	@Produces("application/json;charset=utf-8")
	public String GetAllBudgetCategories(@Context HttpHeaders headers, String content) throws Exception {
		try {
			categoryHandler = new CategoryHandler();
			int index = content.lastIndexOf("=");
			int userId = Integer.parseInt(content.substring(index + 1));
			ArrayList<CategoryVO> categoryVOList = categoryHandler.getAllBudgetCategories(userId);
			Gson gson = new Gson();
			String feeds = gson.toJson(categoryVOList);

			return "{CategoryVO:" + feeds + "}";
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	@GET
	@Path("/GetAllCurrencies")
	@Produces("application/json")
	public String GetAllCurrencies(@Context HttpHeaders headers) throws Exception {
		try {
			currencyHandler = new CurrencyHandler();
			ArrayList<CurrencyVO> categoryVOList = currencyHandler.getAll();
			Gson gson = new Gson();
			String feeds = gson.toJson(categoryVOList);

			return "{CurrencyVO:" + feeds + "}";
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	@GET
	@Path("/getAllCountries")
	@Produces("application/json")
	public String getAllCountries(@Context HttpHeaders headers) throws Exception {
		try {
			countryHandler = new CountryHandler();
			ArrayList<CountryVO> categoryVOList = countryHandler.getAll();
			Gson gson = new Gson();
			String feeds = gson.toJson(categoryVOList);

			return "{CountryVO:" + feeds + "}";
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	@POST
	@Path("/getActiveMonthlyBudgetByUserId")
	@Produces("application/json")
	public String getActiveMonthlyBudgetByUserId(@Context HttpHeaders headers, String content) throws Exception {
		try {
			int index = content.lastIndexOf("=");
			int userId = Integer.parseInt(content.substring(index + 1));
			monthlyBudgetHandler = new MonthlyBudgetHandler();
			MonthlyBudgetVO monthlyBudgetVO = monthlyBudgetHandler.getActiveMonthlyBudgetByUserId(userId);
			Gson gson = new Gson();
			String feeds = gson.toJson(monthlyBudgetVO);

			return "{MonthlyBudgetVO:" + feeds + "}";
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	@POST
	@Path("/getAllMonthlyBudgetByUserId")
	@Produces("application/json")
	public String getAllMonthlyBudgetByUserId(@Context HttpHeaders headers, String content) throws Exception {
		try {
			int index = content.lastIndexOf("=");
			int userId = Integer.parseInt(content.substring(index + 1));
			monthlyBudgetHandler = new MonthlyBudgetHandler();
			List<MonthlyBudgetVO> monthlyBudgetVOs = monthlyBudgetHandler.getAllByUserId(userId);
			Gson gson = new Gson();
			String feeds = gson.toJson(monthlyBudgetVOs);
			return "{MonthlyBudgetVOs:" + feeds + "}";
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}
	@POST
	@Path("/getAllMonthlyBudgetByCategoryIdAndUserId")
	@Produces("application/json")
	public String getAllMonthlyBudgetByCategoryIdAndUserId(@Context HttpHeaders headers, String content) throws Exception {
		try {
			int index = content.lastIndexOf("=");
			int userId = Integer.parseInt(content.substring(index + 1));
			int categoryId = Integer.parseInt(headers.getRequestHeader("categoryId").get(0));
			monthlyBudgetHandler = new MonthlyBudgetHandler();
			ServiceLocator.getInstance().getConfig().get("startYear_of_ExpernsesHistory");
			String startYear=(String)ServiceLocator.getInstance().getConfig().get("startYear_of_ExpernsesHistory");
			String endYear=(String)ServiceLocator.getInstance().getConfig().get("endYear_of_ExpernsesHistory");
			List<MonthlyBudgetVO> monthlyBudgetVOs = monthlyBudgetHandler.getAllMonthlyBudgetByCategoryIdAndUserId(categoryId, userId,startYear,endYear,null);
			Gson gson = new Gson();
			String feeds = gson.toJson(monthlyBudgetVOs);
			return "{MonthlyBudgetVOs:" + feeds + "}";
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	@GET
	@Path("/getActiveUser")
	@Produces("application/json")
	public String getActiveUser(@Context HttpHeaders headers) throws Exception {
		try {
			userHandler = new UserHandler();
			UserVO userVO = userHandler.getActiveUser();
			Gson gson = new Gson();
			String feeds = gson.toJson(userVO);
			return "{UserVO:" + feeds + "}";
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	@POST
	@Path("/checkEmail")
	@Produces("application/json")
	public String checkEmail(@Context HttpHeaders headers, String content) throws Exception {
		try {
			int index = content.lastIndexOf("=");
			String mail = content.substring(index + 1);
			userHandler = new UserHandler();

			boolean validMail = userHandler.checkMail(mail);
			StatusVO statusVO = new StatusVO();
			statusVO.setFlage(validMail);
			Gson gson = new Gson();
			String feeds = gson.toJson(statusVO);

			return "{StatusVO:" + feeds + "}";
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}
	@POST
	@Path("/checkAccount")
	@Produces("application/json")
	public String checkAccount(@Context HttpHeaders headers, String content) throws Exception {
		try {
			String email =  headers.getRequestHeader("email").get(0);
			String password =  headers.getRequestHeader("password").get(0);
			userHandler = new UserHandler();
			UserVO userVO= userHandler.checkAccount(email, password);
			Gson gson = new Gson();
			String feeds = gson.toJson(userVO);

			return "{UserVO:" + feeds + "}";
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

}