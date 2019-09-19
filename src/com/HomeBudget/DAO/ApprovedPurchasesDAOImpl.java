package com.HomeBudget.DAO;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.HomeBudget.DAO.JPA.JPADataAccessObject;
import com.HomeBudget.dataObject.CategoryHistoryVO;
import com.dataObject.BusinessException;
import com.dataObject.CategoryVO;
import com.dataObject.Constants;
import com.dataObject.MonthlyBudgetVO;
import com.dataObject.PurchaseVO;

import com.entities.models.ApprovedPurchases;
import com.entities.models.Category;
import com.entities.models.CategoryHistory;
import com.entities.models.Location;
import com.entities.models.MonthlyBudget;
import com.entities.models.MonthlyBudgetCategory;
import com.entities.models.Purchase;
import com.entities.models.User;



public class ApprovedPurchasesDAOImpl extends JPADataAccessObject implements ApprovedPurchasesDAO{

	public ApprovedPurchasesDAOImpl()
	{
		super();
	}

	
	public Integer add(PurchaseVO purchaseVO) throws Exception
	{
		ApprovedPurchases approvedPurchas=new ApprovedPurchases();
		if (purchaseVO.getArabicDescription() != null) {
			approvedPurchas.setArabicDescription(purchaseVO.getArabicDescription());	
		} else {
			throw new Exception("purchaseVO Arabic Description Should not be Null");
		}
		if (purchaseVO.getEnglishDescription() != null) {
			approvedPurchas.setEnglishDescription(purchaseVO.getEnglishDescription());
		} else {
			throw new Exception("purchaseVO English Description Should not be Null");
		}
		Category category=getEntitymanager().find(Category.class, purchaseVO.getCategoryId());
		approvedPurchas.setCategory(category);
		Integer id=Integer.parseInt(getNextKey().toString());
		approvedPurchas.setId(id);
		getEntitymanager().persist(approvedPurchas);
		return id;
		
		 
	}
	
	public Long getNextKey()
	{
		Query q = getEntitymanager().createNativeQuery("SELECT Max(Auto_increment) FROM information_schema.tables WHERE table_name='approved_purchases'");
		return (Long)q.getSingleResult();
	}


	@Override
	public boolean validateBeforeAdd(PurchaseVO purchaseVO) throws Exception {
		Query query = (Query) getEntitymanager().createNamedQuery("validateApprovedPurchase");
		query.setParameter("arabicDescription", purchaseVO.getArabicDescription());
		query.setParameter("englishDescription", purchaseVO.getEnglishDescription());
		query.setParameter("categoryId", purchaseVO.getCategoryId());
	    List<Purchase> purchase =  query.getResultList();
	    if(purchase.size()>0)
	    {
	    	return false;
	    }else
	    {
	    	return true;
	    }

	
		
		
	}

}
