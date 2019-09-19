package com.dataObject;

import java.util.Date;
import java.util.List;

import com.entities.models.Category;

public class MonthlyBudgetVO {
	




	private int Id;
	
	private String startDate;
	private String endDate;
	private String incomeCategoriesId;
	private List<CategoryVO> incomeCategories;
	private String expenseCategoriesId;
	private double totalExpenses;
	private double totalIncomes;
	private String creationDate;
	private double completedRatio;
	private double limitValue;
	private double actualValue;
	private Integer unApprovedPurchaseCount=0;
	public double getCompletedRatio() {
		return completedRatio;
	}


	public double getLimitValue() {
		return limitValue;
	}


	public void setLimitValue(double limitValue) {
		this.limitValue = limitValue;
	}


	public double getActualValue() {
		return actualValue;
	}


	public void setActualValue(double actualValue) {
		this.actualValue = actualValue;
	}


	public List<CategoryVO> getIncomeCategories() {
		return incomeCategories;
	}


	public void setIncomeCategories(List<CategoryVO> incomeCategories) {
		this.incomeCategories = incomeCategories;
	}


	public void setCompletedRatio(double completedRatio) {
		this.completedRatio = completedRatio;
	}


	public String getIncomeCategoriesId() {
		return incomeCategoriesId;
	}
	public Integer getUnApprovedPurchaseCount() {
		return unApprovedPurchaseCount;
	}


	public void setUnApprovedPurchaseCount(Integer unApprovedPurchaseCount) {
		this.unApprovedPurchaseCount = unApprovedPurchaseCount;
	}



	public void setIncomeCategoriesId(String incomeCategoriesId) {
		this.incomeCategoriesId = incomeCategoriesId;
	}


	public double getTotalExpenses() {
		return totalExpenses;
	}


	public void setTotalExpenses(double totalExpenses) {
		this.totalExpenses = totalExpenses;
	}


	public double getTotalIncomes() {
		return totalIncomes;
	}


	public void setTotalIncomes(double totalIncomes) {
		this.totalIncomes = totalIncomes;
	}



	public String getExpenseCategoriesId() {
		return expenseCategoriesId;
	}


	public void setExpenseCategoriesId(String expenseCategoriesId) {
		this.expenseCategoriesId = expenseCategoriesId;
	}





	private List<Category> categories;
	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}



	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public String getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}


	public List<Category> getCategories() {
		return categories;
	}


	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}




	
}
