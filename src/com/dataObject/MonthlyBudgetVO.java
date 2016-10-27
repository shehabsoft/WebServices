package com.dataObject;

import java.util.Date;
import java.util.List;

import com.entities.models.Category;

public class MonthlyBudgetVO {
	
	private int Id;
	
	private Date startDate;
	private Date endDate;
	private String incomeCategoriesId;
	private String expenseCategoriesId;
	public String getIncomeCategoriesId() {
		return incomeCategoriesId;
	}


	public void setIncomeCategoriesId(String incomeCategoriesId) {
		this.incomeCategoriesId = incomeCategoriesId;
	}


	public String getExpenseCategoriesId() {
		return expenseCategoriesId;
	}


	public void setExpenseCategoriesId(String expenseCategoriesId) {
		this.expenseCategoriesId = expenseCategoriesId;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	private List<Category> categories;
	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public List<Category> getCategories() {
		return categories;
	}


	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}




	
}
