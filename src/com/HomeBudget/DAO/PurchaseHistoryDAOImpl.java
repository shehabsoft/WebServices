/**
 * 
 */
package com.HomeBudget.DAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.HomeBudget.DAO.JPA.JPADataAccessObject;
import com.HomeBudget.dataObject.PurchaseHistoryVO;
import com.dataObject.BusinessException;
import com.dataObject.PurchaseVO;
import com.entities.models.Location;
import com.entities.models.Purchase;
import com.entities.models.PurchaseHistory;

/**
 * @author Shehab
 *
 */
public class PurchaseHistoryDAOImpl extends JPADataAccessObject implements PurchaseHistoryDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.HomeBudget.DAO.PurchaseHistoryDAO#addPurchaseHistory(com.HomeBudget.
	 * dataObject.PurchaseHistoryVO)
	 */

	public PurchaseHistoryDAOImpl() {
		super();
	}

	@Override
	public PurchaseHistory addPurchaseHistory(PurchaseHistoryVO purchaseHistoryVO) {
		// TODO Auto-generated method stub
		PurchaseHistory purchaseHistory = null;
		Location location = getEntitymanager().find(Location.class, purchaseHistoryVO.getLocation_id());
		if (location == null) {
			throw new BusinessException("There IS No Location With ID " + purchaseHistoryVO.getLocation_id());
		}
		if (purchaseHistoryVO.getId() == null)// new Purchase History
		{
			purchaseHistory = new PurchaseHistory();
			purchaseHistory.setCreationDate(new Date());
			purchaseHistory.setPrice(purchaseHistoryVO.getPrice());
			purchaseHistory.setDetails(purchaseHistoryVO.getDetails());
			purchaseHistory.setLocation(location);
			purchaseHistory.setPurchase(purchaseHistoryVO.getPurchase());
			return purchaseHistory;

		} else //
		{
			PurchaseHistory newPurchasehistory = new PurchaseHistory();
			double oldPrice = purchaseHistory.getPrice();
			double newPrice = purchaseHistoryVO.getPrice();
			double updatedValue = 0;
			if (oldPrice > newPrice) {
				throw new BusinessException("New Price should Not Be Less Than Old Price(oldPrice)");
			} else {
				updatedValue = newPrice - oldPrice;
			}
			newPurchasehistory.setPrice(updatedValue);
			newPurchasehistory.setCreationDate(new Date());
			newPurchasehistory.setDetails(purchaseHistoryVO.getDetails());
			newPurchasehistory.setLocation(location);
			newPurchasehistory.setPurchase(purchaseHistoryVO.getPurchase());
			return newPurchasehistory;

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.HomeBudget.DAO.PurchaseHistoryDAO#updatePurchaseHistory(com.
	 * HomeBudget.dataObject.PurchaseHistoryVO)
	 */
	@Override
	public void updatePurchaseHistory(PurchaseHistoryVO purchaseHistoryVO) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		PurchaseHistory purchaseHistory = getEntitymanager().find(PurchaseHistory.class, purchaseHistoryVO.getId());
		purchaseHistory.setPrice(purchaseHistoryVO.getPrice());
		purchaseHistory.setDetails(purchaseHistoryVO.getDetails());
		Location location = getEntitymanager().find(Location.class, purchaseHistoryVO.getLocation_id());
		if (location == null) {
			throw new BusinessException("There IS No Location With ID " + purchaseHistoryVO.getLocation_id());
		} else {
			purchaseHistory.setLocation(location);
		}
		Purchase purchase = getEntitymanager().find(Purchase.class, purchaseHistoryVO.getPurchase_id());
		if (purchase == null) {
			throw new BusinessException("There IS No Purchase With ID " + purchaseHistoryVO.getPurchase_id());
		} else {
			purchaseHistory.setPurchase(purchase);
		}
		getEntitymanager().getTransaction().begin();
		getEntitymanager().getTransaction().commit();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.HomeBudget.DAO.PurchaseHistoryDAO#getPurchaseHistoryByPurchaseId(int)
	 */
	@Override
	public List<PurchaseHistoryVO> getPurchaseHistoryByPurchaseId(int purchaseId) throws Exception {
		try {
			ArrayList<PurchaseHistoryVO> purchaseHistoryVO = new ArrayList<PurchaseHistoryVO>();
			Query query = (Query) getEntitymanager().createNamedQuery("findPurchaseHistoryByPurchaseId");
			query.setParameter("id", purchaseId);
			List<PurchaseHistory> purchaseHistoryList = query.getResultList();
			for (PurchaseHistory purchase : purchaseHistoryList) {
				PurchaseHistoryVO purchaseVO = new PurchaseHistoryVO();

				purchaseVO.setLocation_id(purchase.getLocation().getId());
				purchaseVO.setPrice(purchase.getPrice());
				purchaseVO.setId(new Long(purchase.getId()));
				purchaseVO.setDetails(purchase.getDetails());
				purchaseVO.setPurchase_id(purchase.getPurchase().getId());
				purchaseVO.setLocationName(purchase.getLocation().getEnglishName());
				if (purchase.getCreationDate() != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					purchaseVO.setCreation_date(sdf.format(purchase.getCreationDate()));
				}
				purchaseHistoryVO.add(purchaseVO);
			}

			return purchaseHistoryVO;
		} catch (Exception e) {
			throw new Exception(e);

		}
	}

	@Override
	public PurchaseHistory addPurchaseHistory(PurchaseVO purchaseVO) {

		Purchase purchase = getEntitymanager().find(Purchase.class, purchaseVO.getId());
		PurchaseHistory purchaseHistory = new PurchaseHistory();
		purchaseHistory.setCreationDate(new Date());
		purchaseHistory.setDetails(purchaseVO.getDetails());
		purchaseHistory.setLocation(purchase.getLocation());
		purchaseHistory.setPurchase(purchase);
		if (purchaseVO.getNewPrice() > 0) {
			purchaseHistory.setPrice(purchaseVO.getNewPrice());
		} else {
			purchaseHistory.setPrice(purchaseVO.getPrice());
		}
		getEntitymanager().persist(purchaseHistory);
		return purchaseHistory;

	}

	@Override
	public PurchaseVO getPurchasesHistoryChartByApprovedPurchaseId(int approvedPurchaseId, int userId) {
		// TODO Auto-generated method stub
	 try
		{
		 Query query = (Query) getEntitymanager().createNamedQuery("getPurchasesHistoryChartByApprovedPurchaseId");
		 query.setParameter("userId", userId);
		 query.setParameter("approvedPurchaseId", approvedPurchaseId);
			List<PurchaseVO> purchaseVOList=new ArrayList<PurchaseVO>();
			List<Double>totalPriceStr=new ArrayList<Double>();
			List<String>creationDateStr=new ArrayList<String>();
			List<Object> result = query.getResultList();
			    Iterator itr = result.iterator();
			    PurchaseVO purchaseVO=new PurchaseVO();
			    while(itr.hasNext()){
			    	
			        Object[] obj = (Object[]) itr.next();
			        if(obj[0]==null)
			        {
			        	
			        	totalPriceStr.add((double) 0);
			        }else
			        {
			        	
			        	totalPriceStr.add((Double)obj[0]);
			        }
			        if(obj[1]!=null)
			        {
			        	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						String creationDate=sdf.format(obj[1]);
			        	
			        	creationDateStr.add(creationDate);
			        } 
			        
			        
			    }
			    
			    	String creationDateStrArr = Arrays.toString(creationDateStr.toArray()); 
			    	String  totalPriceStrArr = Arrays.toString(totalPriceStr.toArray()); 
			    	
			    	 
			    	String creationDateStrArrModify=creationDateStrArr.replace("[", "").replace("]", "");
			    	String TotalPriceStrModify=totalPriceStrArr.replace("[","").replace("]", "");
			    	purchaseVO.setCreationDateStr(creationDateStrArrModify);
 			    	 purchaseVO.setTotalPriceStr(TotalPriceStrModify);
//			     
			   
			   
			
				return purchaseVO;
			
		}catch(Exception e)
		{
			throw new BusinessException(e.toString());
		}
}
	

}

