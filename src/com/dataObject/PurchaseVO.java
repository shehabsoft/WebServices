package com.dataObject;

import java.util.Date;

public class PurchaseVO {
	
	private int Id;
	
	private String arabicDescription;
	
	private String englishDescription;
	private String details;
    private String categoryName;
	private String  locationName;
	private String creationDate;

	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	private int categoryId;
	private int locationId;
	


	private double price;
	
	

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

	public void setPrice(double limit_value) {
		this.price = limit_value;
	}

	
	
}
