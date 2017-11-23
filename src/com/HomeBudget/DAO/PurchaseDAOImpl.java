package com.HomeBudget.DAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import com.HomeBudget.DAO.JPA.JPADataAccessObject;
import com.dataObject.BusinessException;
import com.dataObject.PurchaseVO;
import com.entities.models.Category;
import com.entities.models.Location;
import com.entities.models.Purchase;

public class PurchaseDAOImpl extends JPADataAccessObject implements PurchaseDAO {

	public PurchaseDAOImpl() {
		super();
	}

	public void addPurchase(PurchaseVO purchaseVO) throws Exception {
		Purchase purchase = new Purchase();
		try {
			Category category = getEntitymanager().find(Category.class, purchaseVO.getCategoryId());
			purchase.setCategory(category);
			if (category == null) {
				throw new BusinessException("There is No Category with ID " + purchaseVO.getCategoryId());
			} else {
				if (purchaseVO.getArabicDescription() != null) {
					purchase.setArabicDescription(purchaseVO.getArabicDescription());
				} else {
					throw new BusinessException("Purchase Arabic Description Should not be Null");
				}
				if (purchaseVO.getLocationId() != 0) {
					Location location = getEntitymanager().find(Location.class, purchaseVO.getLocationId());
					purchase.setLocation(location);
					;
				} else {
					throw new BusinessException("Location  Should not be Null or 0");
				}
				if (purchaseVO.getEnglishDescription() != null) {
					purchase.setEnglishDescription(purchaseVO.getEnglishDescription());
				} else {
					throw new BusinessException("Purchase English Description Should not be Null");
				}
				if (purchaseVO.getPrice() > 0) {
					purchase.setPrice(purchaseVO.getPrice());
				} else {
					throw new BusinessException("Purchase Price  Should be Greater than 0");
				}
				if (purchaseVO.getDetails() != null) {
					purchase.setDetails(purchaseVO.getDetails());
				}
				purchase.setStatus(1);//new
				purchase.setCreationDate(new Date());
				purchase.setCategory(category);
				int purchaseId = (int) (getNextKey() - 1);
				purchase.setId(purchaseId);
				purchaseVO.setId(purchaseId);
				getEntitymanager().persist(purchase);
			}
		} catch (Exception e) {
			throw new Exception(e);

		}

	}

	public void updatePurchase(PurchaseVO purchaseVO) throws Exception {
		try {
			Purchase purchase = getEntitymanager().find(Purchase.class, purchaseVO.getId());
			purchase.setArabicDescription(purchaseVO.getArabicDescription());
			purchase.setEnglishDescription(purchaseVO.getEnglishDescription());
			purchase.setPrice(purchase.getPrice() + purchaseVO.getNewPrice());
			Location location = getEntitymanager().find(Location.class, purchaseVO.getLocationId());
			purchase.setLocation(location);
			Category category = getEntitymanager().find(Category.class, purchaseVO.getCategoryId());
			purchase.setCategory(category);
			purchase.setCreationDate(new Date());
			purchase.setDetails(purchaseVO.getDetails());
			getEntitymanager().persist(purchase);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public ArrayList<PurchaseVO> getAllPurchases(int monthlyBudgetId) throws Exception {
		try {
			ArrayList<PurchaseVO> purchases = new ArrayList<PurchaseVO>();
			Query query = (Query) getEntitymanager().createNamedQuery("findAllPurchases");
			query.setParameter("id", monthlyBudgetId);
			List<Purchase> purchaseList = query.getResultList();
			for (Purchase purchase : purchaseList) {
				PurchaseVO purchaseVO = new PurchaseVO();

				purchaseVO.setArabicDescription(purchase.getArabicDescription());
				purchaseVO.setCategoryId(purchase.getCategory().getId());
				purchaseVO.setPrice(purchase.getPrice());
				purchaseVO.setEnglishDescription(purchase.getEnglishDescription());
				purchaseVO.setId(purchase.getId());
				purchaseVO.setDetails(purchase.getDetails());
				purchaseVO.setLocationId(purchase.getLocation().getId());
				purchaseVO.setLocationName(purchase.getLocation().getEnglishName());
				purchaseVO.setCategoryName(purchase.getCategory().getEnglishDescription());

				if (purchase.getCreationDate() != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					purchaseVO.setCreationDate(sdf.format(purchase.getCreationDate()));
				}
				purchases.add(purchaseVO);
			}

			return purchases;
		} catch (Exception e) {
			throw new Exception(e);

		}
	}

	@Override
	public List<PurchaseVO> getAllPurchases(int monthlyBudgetId, int categoryId) throws Exception {
		try {
			ArrayList<PurchaseVO> purchases = new ArrayList<PurchaseVO>();
			Query query = (Query) getEntitymanager().createNamedQuery("findAllPurchasesByCategoryId");
			query.setParameter("id", monthlyBudgetId);
			query.setParameter("categoryId", categoryId);
			List<Purchase> purchaseList = query.getResultList();
			for (Purchase purchase : purchaseList) {
				PurchaseVO purchaseVO = new PurchaseVO();
				if(purchase.getApprovedPurchas()!=null)
				{
					purchaseVO.setApprovedPurchaseId(purchase.getApprovedPurchas().getId());
				}	
				purchaseVO.setArabicDescription(purchase.getArabicDescription());
				purchaseVO.setCategoryId(purchase.getCategory().getId());
				purchaseVO.setPrice(purchase.getPrice());
				purchaseVO.setEnglishDescription(purchase.getEnglishDescription());
				purchaseVO.setId(purchase.getId());
				purchaseVO.setDetails(purchase.getDetails());
				if(purchase.getLocation()!=null)
				{
				purchaseVO.setLocationId(purchase.getLocation().getId());
				purchaseVO.setLocationName(purchase.getLocation().getEnglishName());
				} 
				
				purchaseVO.setCategoryName(purchase.getCategory().getEnglishDescription());

				if (purchase.getCreationDate() != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					purchaseVO.setCreationDate(sdf.format(purchase.getCreationDate()));
				}
				purchases.add(purchaseVO);
			}

			return purchases;
		} catch (Exception e) {
			throw new Exception(e);

		}
	}

	public Long getNextKey() {
		Query q = getEntitymanager().createNativeQuery(
				"SELECT Max(Auto_increment) FROM information_schema.tables WHERE table_name='purchase'");
		return (Long) q.getSingleResult();
	}

	@Override
	public List<PurchaseVO> getMonthlyPurchases(int monthlyBudgetId) throws Exception {
		Query query = getEntitymanager().createNativeQuery(
				"SELECT DATE(creation_date) as creation_date,SUM(price)Total FROM purchase where monthlyBudget_ID="
						+ monthlyBudgetId + " group by  DATE(creation_date)");
		query.setParameter("id", monthlyBudgetId);
		List<Object> result = query.getResultList();
		List<PurchaseVO> purchaseVOs = new ArrayList<PurchaseVO>();
		Iterator itr = result.iterator();
		while (itr.hasNext()) {
			PurchaseVO purchaseVO = new PurchaseVO();
			Object[] obj = (Object[]) itr.next();
			purchaseVO.setCreationDate(String.valueOf(obj[0]));
			purchaseVO.setTotalPrice(Double.parseDouble(String.valueOf(obj[1])));
			purchaseVOs.add(purchaseVO);
		}

		return purchaseVOs;
	}

	@Override
	public List<PurchaseVO> getMonthlyPurchases(int monthlyBudgetId, int categoryId) throws Exception {
		Query query = getEntitymanager().createNativeQuery(
				"SELECT Date(ph.creation_date),sum(ph.price) FROM purchase p,purchase_history ph where p.id=ph.Purchase_Id and     p.monthlyBudget_ID="
						+ monthlyBudgetId + " and p.Category_ID=" + categoryId +" Group by p.Category_ID,Date(ph.creation_date)"
						 );
		query.setParameter("id", monthlyBudgetId);
		List<Object> result = query.getResultList();
		List<PurchaseVO> purchaseVOs = new ArrayList<PurchaseVO>();
		Iterator itr = result.iterator();
		while (itr.hasNext()) {
			PurchaseVO purchaseVO = new PurchaseVO();
			Object[] obj = (Object[]) itr.next();
			purchaseVO.setCreationDate(String.valueOf(obj[0]));
			purchaseVO.setTotalPrice(Double.parseDouble(String.valueOf(obj[1])));
			purchaseVOs.add(purchaseVO);
		}

		return purchaseVOs;
	}

	@Override
	public List<PurchaseVO> getAllPurchasesByCategoryId(int categoryId) throws Exception {
		try {
			ArrayList<PurchaseVO> purchases = new ArrayList<PurchaseVO>();
			Query query = (Query) getEntitymanager().createNamedQuery("cleansing.findAllPurchasesByCategoryId");
			query.setParameter("categoryId", categoryId);
			List<Purchase> purchaseList = query.getResultList();
			for (Purchase purchase : purchaseList) {
				PurchaseVO purchaseVO = new PurchaseVO();

				purchaseVO.setArabicDescription(purchase.getArabicDescription());
				purchaseVO.setCategoryId(purchase.getCategory().getId());
				purchaseVO.setPrice(purchase.getPrice());
				purchaseVO.setEnglishDescription(purchase.getEnglishDescription());
				purchaseVO.setId(purchase.getId());
				purchaseVO.setDetails(purchase.getDetails());
				purchaseVO.setLocationId(purchase.getLocation().getId());
				purchaseVO.setLocationName(purchase.getLocation().getEnglishName());
				purchaseVO.setCategoryName(purchase.getCategory().getEnglishDescription());

				if (purchase.getCreationDate() != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					purchaseVO.setCreationDate(sdf.format(purchase.getCreationDate()));
				}
				purchases.add(purchaseVO);
			}

			return purchases;
		} catch (Exception e) {
			throw new Exception(e);

		}
	}

	@Override
	public void approvePurchase(PurchaseVO purchaseVO) throws Exception {
		try {
			Purchase purchase = getEntitymanager().find(Purchase.class, purchaseVO.getId());
			purchase.setStatus(2);
			getEntitymanager().persist(purchase);
		} catch (Exception e) {
			throw new Exception(e);
		}
		
	}
	@Override
	public void rejectPurchase(PurchaseVO purchaseVO) throws Exception {
		try {
			Purchase purchase = getEntitymanager().find(Purchase.class, purchaseVO.getId());
			purchase.setStatus(3);
			getEntitymanager().persist(purchase);
		} catch (Exception e) {
			throw new Exception(e);
		}
		
	}

}
