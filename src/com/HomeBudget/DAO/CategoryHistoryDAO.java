package com.HomeBudget.DAO;
import java.util.List;

import com.HomeBudget.DAO.JPA.DataAccessObject1;
import com.HomeBudget.dataObject.CategoryHistoryVO;
import com.dataObject.CategoryVO;

import com.entities.models.CategoryHistory;

public interface CategoryHistoryDAO extends DataAccessObject1  {
	public CategoryHistory addCategoryHistory(CategoryVO category) throws Exception;
	public void updateCategoryHistory(CategoryHistoryVO categoryHistoryVO) throws Exception;
	public List<CategoryHistoryVO> getAllCategoriesHistory(int categoryId) throws Exception;
}
