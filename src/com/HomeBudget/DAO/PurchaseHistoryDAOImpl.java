/**
 * 
 */
package com.HomeBudget.DAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.HomeBudget.dataObject.PurchaseHistoryVO;
import com.dataObject.BusinessException;
import com.entities.models.Location;
import com.entities.models.Purchase;
import com.entities.models.PurchaseHistory;

/**
 * @author Shehab
 *
 */
public class PurchaseHistoryDAOImpl extends DataAccessObject implements PurchaseHistoryDAO {

	/* (non-Javadoc)
	 * @see com.HomeBudget.DAO.PurchaseHistoryDAO#addPurchaseHistory(com.HomeBudget.dataObject.PurchaseHistoryVO)
	 */
	public PurchaseHistoryDAOImpl(EntityManagerFactory entityManager)
	{
		super(entityManager);
	}
	public PurchaseHistoryDAOImpl()
	{
		super();
	}
	@Override
	public PurchaseHistory addPurchaseHistory(PurchaseHistoryVO purchaseHistoryVO) {
		// TODO Auto-generated method stub
		PurchaseHistory purchaseHistory=null;
		Location location=getEntitymanager().find(Location.class, purchaseHistoryVO.getLocation_id());
		if(location==null)
		{
			throw new  BusinessException("There IS No Location With ID "+purchaseHistoryVO.getLocation_id());
		}
		if(purchaseHistoryVO.getId()==null)//new Purchase History
		{
			    purchaseHistory=new PurchaseHistory();
			    purchaseHistory.setCreationDate(new Date());
				purchaseHistory.setPrice(purchaseHistoryVO.getPrice());
				purchaseHistory.setDetails(purchaseHistoryVO.getDetails());
				purchaseHistory.setLocation(location);
				purchaseHistory.setPurchase(purchaseHistoryVO.getPurchase());
				return purchaseHistory;
				
		}else  //
		{
			PurchaseHistory newPurchasehistory=new PurchaseHistory();
			double oldPrice=purchaseHistory.getPrice();
			double newPrice=purchaseHistoryVO.getPrice();
			double updatedValue=0;
			if(oldPrice>newPrice)
			{
				throw new BusinessException("New Price should Not Be Less Than Old Price(oldPrice)");
			}else
			{
				updatedValue=newPrice-oldPrice;
			}
			newPurchasehistory.setPrice(updatedValue);
			newPurchasehistory.setCreationDate(new Date());
			newPurchasehistory.setDetails(purchaseHistoryVO.getDetails());
			newPurchasehistory.setLocation(location);
			newPurchasehistory.setPurchase(purchaseHistoryVO.getPurchase());
			return newPurchasehistory;
			
		}
		
		

	}

	/* (non-Javadoc)
	 * @see com.HomeBudget.DAO.PurchaseHistoryDAO#updatePurchaseHistory(com.HomeBudget.dataObject.PurchaseHistoryVO)
	 */
	@Override
	public void updatePurchaseHistory(PurchaseHistoryVO purchaseHistoryVO) {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
		PurchaseHistory purchaseHistory=getEntitymanager().find(PurchaseHistory.class, purchaseHistoryVO.getId());
		purchaseHistory.setPrice(purchaseHistoryVO.getPrice());
		purchaseHistory.setDetails(purchaseHistoryVO.getDetails());
		Location location=getEntitymanager().find(Location.class, purchaseHistoryVO.getLocation_id());
		if(location==null)
		{
			throw new  BusinessException("There IS No Location With ID "+purchaseHistoryVO.getLocation_id());
		}else
		{
			purchaseHistory.setLocation(location);
		}
		Purchase purchase=getEntitymanager().find(Purchase.class, purchaseHistoryVO.getPurchase_id());
		if(purchase==null)
		{
			throw new  BusinessException("There IS No Purchase With ID "+purchaseHistoryVO.getPurchase_id());
		}else
		{
			purchaseHistory.setPurchase(purchase);
		}
		getEntitymanager().getTransaction().begin();
		getEntitymanager().getTransaction().commit();

	}

	/* (non-Javadoc)
	 * @see com.HomeBudget.DAO.PurchaseHistoryDAO#getPurchaseHistoryByPurchaseId(int)
	 */
	@Override
	public List<PurchaseHistoryVO> getPurchaseHistoryByPurchaseId(int purchaseId) throws Exception {
		try
		{
		ArrayList<PurchaseHistoryVO>purchaseHistoryVO=new ArrayList<PurchaseHistoryVO>();
		Query query = (Query) getEntitymanager().createNamedQuery("findPurchaseHistoryByPurchaseId");
		query.setParameter("id", purchaseId);
	    List<PurchaseHistory> purchaseHistoryList =  query.getResultList();
		for (PurchaseHistory purchase : purchaseHistoryList) {
			PurchaseHistoryVO purchaseVO=new PurchaseHistoryVO();
			
			purchaseVO.setLocation_id(purchase.getLocation().getId());
			purchaseVO.setPrice(purchase.getPrice());
			purchaseVO.setId(new Long(purchase.getId()));
			purchaseVO.setDetails(purchase.getDetails());
			purchaseVO.setPurchase_id(purchase.getPurchase().getId());
			purchaseVO.setLocationName(purchase.getLocation().getEnglishName());
			if(purchase.getCreationDate()!=null)
				{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					purchaseVO.setCreation_date(sdf.format( purchase.getCreationDate()));
				}
			purchaseHistoryVO.add(purchaseVO);
		}

	
		return purchaseHistoryVO;
	}catch(Exception e)
		{
		throw new Exception(e);
		
		}
	}

}
