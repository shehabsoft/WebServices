package com.dataObject;


public class CategoryVO {
	
	private int Id;
	
	private String arabicDescription;
	
	private String englishDescription;

	private int categoryId;
	private int categoryTypeId;
	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getCategoryTypeId() {
		return categoryTypeId;
	}

	public void setCategoryTypeId(int categoryTypeId) {
		this.categoryTypeId = categoryTypeId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	private double price;
	
	private double planedValue;
	
	private double actualValue;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
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

	public void setEnglisDescription(String englisDescription) {
		this.englishDescription = englisDescription;
	}

	public int getParentCategoryId() {
		return categoryId;
	}

	public void setParentCategoryId(int parentCategoryId) {
		this.categoryId = parentCategoryId;
	}

	public double getLimit_value() {
		return price;
	}

	public void setLimit_value(double limit_value) {
		this.price = limit_value;
	}

	public double getPlanedValue() {
		return planedValue;
	}

	public void setPlanedValue(double planedValue) {
		this.planedValue = planedValue;
	}

	public double getActualValue() {
		return actualValue;
	}

	public void setActualValue(double actualValue) {
		this.actualValue = actualValue;
	}

	public int getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(int categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	int categoryStatus;
}
