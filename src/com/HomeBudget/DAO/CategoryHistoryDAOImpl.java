package com.HomeBudget.DAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import com.HomeBudget.DAO.JPA.JPADataAccessObject;
import com.HomeBudget.dataObject.CategoryHistoryVO;
import com.dataObject.CategoryVO;
import com.entities.models.Category;
import com.entities.models.CategoryHistory;


public class CategoryHistoryDAOImpl extends  JPADataAccessObject implements CategoryHistoryDAO{
 
	

	
	public CategoryHistory addCategoryHistory(CategoryVO categoryVo) throws Exception
	{
		
		 CategoryHistory categoryHistory =new CategoryHistory();
		try
		{
			Category category=getEntitymanager().find(Category.class, categoryVo.getId());
			categoryHistory.setActualValue(categoryVo.getActualValue());	
			categoryHistory.setCategoryStatus(categoryVo.getCategoryStatus());
			categoryHistory.setCategory(category);
			categoryHistory.setCategoryTypeId(categoryVo.getCategoryTypeId());
			categoryHistory.setLimitValue(categoryVo.getLimitValue());
			categoryHistory.setPlanedValue(categoryVo.getPlanedValue());
			categoryHistory.setUserId(category.getUser().getId());
			categoryHistory.setActualValue(categoryVo.getActualValue());
			categoryHistory.setCreationDate(new Date());
			getEntitymanager().persist(categoryHistory);
			return categoryHistory;
		}catch(Exception e) 
		{
			throw new Exception(e);
			
		}
		

	
	}
	public void updateCategoryHistory(CategoryHistoryVO categoryHistoryVO) throws Exception
	{
		try
		{
			double updateExpenseValue=0;
			CategoryHistory categoryHistory=getEntitymanager().find(CategoryHistory.class, categoryHistoryVO.getId());
			if(categoryHistory.getActualValue()!=categoryHistoryVO.getActualValue()||categoryHistory.getPlanedValue()!=categoryHistoryVO.getPlannedValue()||categoryHistory.getLimitValue()!=categoryHistoryVO.getLimitValue())
			    {
	
				//update Purchase History 
				    CategoryHistory newpurchaseHistory=new CategoryHistory();
//					newpurchaseHistory.setCreationDate(new Date());
//					categoryHistory.setCategory(categoryHistoryVO.getCategory());
//					categoryHistory.setCategoryTypeId(categoryHistoryVO.getCategory().getCategoryTypeId());
//					categoryHistory.setUserId(categoryHistoryVO.getCategory().getUser().getId());
				
			    }else
			    {
			    	
			    }
		
			
			
			
		}catch(Exception e)
		{
			throw new Exception(e);
		}
	}
	
	public ArrayList<CategoryHistoryVO> getAllCategoriesHistory(int categoryId) throws Exception
	{
		try
		{
		ArrayList<CategoryHistoryVO>categoryHistoryVOs=new ArrayList<CategoryHistoryVO>();
		Query query = (Query) getEntitymanager().createNamedQuery("findAllCategoriesHistory");
		query.setParameter("id", categoryId);
	    List<CategoryHistory> CategoryHistoryList =  query.getResultList();
		for (CategoryHistory categoryHistory : CategoryHistoryList) {
			CategoryHistoryVO categoryHistoryVO=new CategoryHistoryVO();
			categoryHistoryVO.setActualValue(categoryHistory.getActualValue());
			categoryHistoryVO.setId(new Long(categoryHistory.getId()));
			categoryHistoryVO.setLimitValue(categoryHistory.getLimitValue());
			categoryHistoryVO.setPlannedValue(categoryHistory.getPlanedValue());
			categoryHistoryVO.setCategoryTypeId(categoryHistory.getCategoryTypeId());
			if(categoryHistory.getCreationDate()!=null)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				categoryHistoryVO.setCreation_date(sdf.format(categoryHistory.getCreationDate()) );
			}
			categoryHistoryVOs.add(categoryHistoryVO);
		}

	
		return categoryHistoryVOs;
	}catch(Exception e)
		{
		throw new Exception(e);
		
		}
	}


 


	

}
