package com.HomeBudget.Bus;

import java.util.ArrayList;
import java.util.List;

import com.HomeBudget.DAO.CategoryDAO;
import com.HomeBudget.DAO.CategoryDAOImpl;
import com.HomeBudget.DAO.CategoryHistoryDAO;
import com.HomeBudget.DAO.JPA.DataAccessObject1;
import com.HomeBudget.dataObject.CategoryHistoryVO;
import com.dataObject.BusinessException;
import com.dataObject.BusinessObject;
import com.dataObject.CategoryVO;
import com.dataObject.DAOFactory;
import com.dataObject.DataAccessException;
import com.dataObject.MonthlyBudgetVO;

public class CategoryHandler extends BusinessObject {

	private CategoryHistoryHandler categoryHistoryHandler = null;
	private MonthlyBudgetHandler monthlyBudgetHandler = null;
	public void add(CategoryVO categoryVO) throws Exception {

		CategoryDAO categoryDAO = null;
		if (categoryVO == null) {
			throw new BusinessException("Null categoryVO");
		}
		try {
			categoryHistoryHandler = new CategoryHistoryHandler();
			categoryDAO = (CategoryDAO) getDAO(CategoryDAO.class);
			categoryDAO.addCategory(categoryVO);
			categoryHistoryHandler.add(categoryVO, categoryDAO);
			categoryDAO.commit();
		} catch (DataAccessException ex) {
			throw ex;
		} catch (BusinessException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BusinessException(ex);
		} finally {
			close(categoryDAO);
		}
	}

	public void update(CategoryVO categoryVO) throws Exception {
		if (categoryVO == null) {
			throw new BusinessException("Null categoryVO");
		}
		CategoryDAO categoryDAO = null;
		try {
			categoryDAO = (CategoryDAO) DAOFactory.getDAO(CategoryDAO.class);
			categoryDAO.updateCategory(categoryVO);
			monthlyBudgetHandler = new MonthlyBudgetHandler();
			boolean updatedValues = monthlyBudgetHandler.update(categoryVO, categoryDAO);
			if (updatedValues) {
				categoryHistoryHandler = new CategoryHistoryHandler();
				categoryHistoryHandler.add(categoryVO, categoryDAO);
			}
			categoryDAO.commit();
		} catch (DataAccessException ex) {
			throw ex;
		} catch (BusinessException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BusinessException(ex);
		} finally {
			close(categoryDAO);
		}
	}

	public void updateCategoryActualValue(MonthlyBudgetVO monthlyBudgetVO, DataAccessObject1 parentDao)
			throws Exception {
		if (monthlyBudgetVO == null) {
			throw new BusinessException("Null monthlyBudgetVO");
		}
		CategoryDAO categoryDAO = null;
		categoryDAO = (CategoryDAO) DAOFactory.getDAO(CategoryDAO.class, parentDao);
		categoryDAO.updateCategoryActualValue(monthlyBudgetVO);

	}

	public CategoryVO getById(int id) {
		CategoryDAO categoryDAO = null;
		return categoryDAO.getCategoryById(id);
	}

	public ArrayList<CategoryVO> getExpensesCategories(int monthlyBudgetId, int userId) {
		CategoryDAO categoryDAO = null;
		try {
			 String year=(String)getConfig().get("endYear_of_ExpernsesHistory");
			 categoryDAO = (CategoryDAO) DAOFactory.getDAO(CategoryDAO.class);
			 ArrayList<CategoryVO> categoryVOs= categoryDAO.getExpensesCategories(monthlyBudgetId, userId,year);
			 String actualValue="";
			 monthlyBudgetHandler=new MonthlyBudgetHandler();
			 String status_date="";
			 String startYear=(String)getConfig().get("startYear_of_ExpernsesHistory");
			 String endYear=(String)getConfig().get("endYear_of_ExpernsesHistory");
			for(int i=0;i<categoryVOs.size();i++)
			{
			 List<MonthlyBudgetVO> monthlyBudgetVOs = monthlyBudgetHandler.getAllMonthlyBudgetByCategoryIdAndUserId(categoryVOs.get(i).getId(), userId,startYear,endYear,categoryDAO);
			 for (int j = 0; j < monthlyBudgetVOs.size(); j++) {
					if (j == monthlyBudgetVOs.size() - 1) {
						actualValue += monthlyBudgetVOs.get(j).getActualValue();
						status_date+=monthlyBudgetVOs.get(j).getCreationDate();
					
					} else {
						actualValue += monthlyBudgetVOs.get(j).getActualValue() + ",";
						status_date+=monthlyBudgetVOs.get(j).getCreationDate()+ ",";
						
					}
					 categoryVOs.get(i).setUnApprovedPurchaseCount(monthlyBudgetVOs.get(j).getUnApprovedPurchaseCount());
					
				}
			 categoryVOs.get(i).setActualValueStr(actualValue);
			 categoryVOs.get(i).setStatusDate(status_date);
			
			 actualValue="";
			 status_date="";
			}
			
             return categoryVOs;
		} catch (DataAccessException ex) {
			throw ex;
		} catch (BusinessException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BusinessException(ex);
		} finally {
			close(categoryDAO);
		}

	}

	public ArrayList<CategoryVO> getAllExpensesCategories(int userId) {
		CategoryDAO categoryDAO = null;
		try {
			categoryDAO = (CategoryDAO) DAOFactory.getDAO(CategoryDAO.class);
			return categoryDAO.getAllExpensesCategories(userId);
		} catch (DataAccessException ex) {
			throw ex;
		} catch (BusinessException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BusinessException(ex);
		} finally {
			close(categoryDAO);
		}
	}

	public ArrayList<CategoryVO> getBudgetCategories(int monthlyBudgetId, int userId) {
		CategoryDAO categoryDAO = null;
		try {
			 categoryDAO = (CategoryDAO) DAOFactory.getDAO(CategoryDAO.class);
			 ArrayList<CategoryVO> categoryVOs=categoryDAO.GetBudgetCategories(monthlyBudgetId, userId);
			 String actualValue="";
			 categoryHistoryHandler=new CategoryHistoryHandler();
			for(int i=0;i<categoryVOs.size();i++)
			{
			ArrayList<CategoryHistoryVO> categoryHistoryVOs=(ArrayList<CategoryHistoryVO>) categoryHistoryHandler.getAll(categoryVOs.get(i).getId());
			 for (int j = 0; j < categoryHistoryVOs.size()-1; j++) {
					if (j == categoryHistoryVOs.size() - 2) {
						actualValue += categoryHistoryVOs.get(j).getActualValue();
					
					} else {
						actualValue += categoryHistoryVOs.get(j).getActualValue() + ",";
					}
				}
			 categoryVOs.get(i).setActualValueStr(actualValue);
			 actualValue="";
			}
			
             return categoryVOs;
		} catch (DataAccessException ex) {
			throw ex;
		} catch (BusinessException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BusinessException(ex);
		} finally {
			close(categoryDAO);
		}
	}

	public ArrayList<CategoryVO> getAllBudgetCategories(int userId) {
		CategoryDAO categoryDAO = null;
		try {
			categoryDAO = (CategoryDAO) DAOFactory.getDAO(CategoryDAO.class);
			return categoryDAO.GetAllBudgetCategories(userId);
		} catch (DataAccessException ex) {
			throw ex;
		} catch (BusinessException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BusinessException(ex);
		} finally {
			close(categoryDAO);
		}
	}
}
