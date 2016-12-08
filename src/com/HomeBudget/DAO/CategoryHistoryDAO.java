package com.HomeBudget.DAO;
import java.util.List;

import com.HomeBudget.dataObject.CategoryHistoryVO;
import com.dataObject.CategoryVO;
import com.entities.models.Category;
import com.entities.models.CategoryHistory;

public interface CategoryHistoryDAO {
	public CategoryHistory addCategoryHistory(Category category) throws Exception;
	public void updateCategoryHistory(CategoryHistoryVO categoryHistoryVO) throws Exception;
	public List<CategoryHistoryVO> getAllCategoriesHistory(int monthlyBudgetId) throws Exception;
}
