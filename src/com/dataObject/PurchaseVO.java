package com.dataObject;

import java.util.Date;

public class PurchaseVO {

	private int Id;
	private String arabicDescription;
	private String englishDescription;
	private String details;
	private String categoryName;
	private String locationName;
	private String creationDate;
	private int userId;
	private double updatedExpenseValue;
	private int categoryId;
	private int locationId;
	private double totalPrice;
	private int approvedPurchaseId;
	private String totalPriceStr;
	private String creationDateStr;

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	private double price;
	private double newPrice;

	public double getUpdatedExpenseValue() {
		return updatedExpenseValue;
	}

	public void setUpdatedExpenseValue(double updatedExpenseValue) {
		this.updatedExpenseValue = updatedExpenseValue;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public int getId() {
		return Id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getDetails() {
		return details;
	}

	public int getLocationId() {
		return locationId;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getArabicDescription() {
		return arabicDescription;
	}

	public void setArabicDescription(String arabicDescription) {
		this.arabicDescription = arabicDescription;
	}

	public String getEnglishDescription() {
		return englishDescription;
	}

	public void setEnglishDescription(String englisDescription) {
		this.englishDescription = englisDescription;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int parentCategoryId) {
		this.categoryId = parentCategoryId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}

	public int getApprovedPurchaseId() {
		return approvedPurchaseId;
	}

	public void setApprovedPurchaseId(int approvedPurchaseId) {
		this.approvedPurchaseId = approvedPurchaseId;
	}

	public String getTotalPriceStr() {
		return totalPriceStr;
	}

	public void setTotalPriceStr(String totalPriceStr) {
		this.totalPriceStr = totalPriceStr;
	}

	public String getCreationDateStr() {
		return creationDateStr;
	}

	public void setCreationDateStr(String creationDateStr) {
		this.creationDateStr = creationDateStr;
	}
	

}
