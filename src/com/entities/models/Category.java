package com.entities.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CATEGORY_ID_GENERATOR", sequenceName="SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CATEGORY_ID_GENERATOR")
	private int id;

	@Column(name="actual_value")
	private double actualValue;

	@Column(name="arabic_description")
	private String arabicDescription;

	@Column(name="CATEGORY_STATUS")
	private int categoryStatus;

	@Column(name="category_type_id")
	private int categoryTypeId;

	@Column(name="english_description")
	private String englishDescription;

	@Column(name="limit_value")
	private double limitValue;

	@Column(name="PARENT_CATEGORY_ID")
	private int parentCategoryId;

	@Column(name="planed_value")
	private double planedValue;
 
	//bi-directional many-to-many association to MonthlyBudget
	@ManyToMany(mappedBy="categories")
	private List<MonthlyBudget> monthlyBudgets;


	//bi-directional many-to-one association to ApprovedPurchases
	@OneToMany(mappedBy="category")
	private List<ApprovedPurchases> approvedPurchases;
	@OneToMany(mappedBy="category")
	private List<MonthlyBudgetCategory> monthlyBudgetCategories;

	//bi-directional many-to-one association to Purchase
	@OneToMany(mappedBy="category")
	private List<Purchase> purchases;

	@OneToMany(mappedBy="category")
	private List<CategoryHistory> categoryHistories;
	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getActualValue() {
		return this.actualValue;
	}

	public void setActualValue(double actualValue) {
		this.actualValue = actualValue;
	}

	public String getArabicDescription() {
		return this.arabicDescription;
	}

	public void setArabicDescription(String arabicDescription) {
		this.arabicDescription = arabicDescription;
	}

	public int getCategoryStatus() {
		return this.categoryStatus;
	}

	public void setCategoryStatus(int categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	public int getCategoryTypeId() {
		return this.categoryTypeId;
	}

	public void setCategoryTypeId(int categoryTypeId) {
		this.categoryTypeId = categoryTypeId;
	}

	public String getEnglishDescription() {
		return this.englishDescription;
	}

	public void setEnglishDescription(String englishDescription) {
		this.englishDescription = englishDescription;
	}

	public double getLimitValue() {
		return this.limitValue;
	}

	public void setLimitValue(double limitValue) {
		this.limitValue = limitValue;
	}

	public int getParentCategoryId() {
		return this.parentCategoryId;
	}

	public void setParentCategoryId(int parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public double getPlanedValue() {
		return this.planedValue;
	}

	public void setPlanedValue(double planedValue) {
		this.planedValue = planedValue;
	}

	public List<CategoryHistory> getCategoryHistories() {
		return categoryHistories;
	}

 

	public List<MonthlyBudget> getMonthlyBudgets() {
		return this.monthlyBudgets;
	}

	public void setApprovedPurchases(List<ApprovedPurchases> approvedPurchases) {
		this.approvedPurchases = approvedPurchases;
	}
	public void setMonthlyBudgets(List<MonthlyBudget> monthlyBudgets) {
		this.monthlyBudgets = monthlyBudgets;
	}

	public ApprovedPurchases addApprovedPurchas(ApprovedPurchases approvedPurchas) {
		getApprovedPurchases().add(approvedPurchas);
		approvedPurchas.setCategory(this);
		return  approvedPurchas;
}
	public List<MonthlyBudgetCategory> getMonthlyBudgetCategories() {
		return this.monthlyBudgetCategories;
	}

	public void setMonthlyBudgetCategories(List<MonthlyBudgetCategory> monthlyBudgetCategories) {
		this.monthlyBudgetCategories = monthlyBudgetCategories;
	}

	public ApprovedPurchases removeApprovedPurchas(ApprovedPurchases approvedPurchas) {
		getApprovedPurchases().remove(approvedPurchas);
		approvedPurchas.setCategory(null);
        return approvedPurchas;
}
	public MonthlyBudgetCategory addMonthlyBudgetCategory(MonthlyBudgetCategory monthlyBudgetCategory) {
		getMonthlyBudgetCategories().add(monthlyBudgetCategory);
		monthlyBudgetCategory.setCategory(this);

		return monthlyBudgetCategory;
	}

	public MonthlyBudgetCategory removeMonthlyBudgetCategory(MonthlyBudgetCategory monthlyBudgetCategory) {
		getMonthlyBudgetCategories().remove(monthlyBudgetCategory);
		monthlyBudgetCategory.setCategory(null);

		return monthlyBudgetCategory;
	}

	public List<Purchase> getPurchases() {
		return this.purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public Purchase addPurchas(Purchase purchas) {
		getPurchases().add(purchas);
		purchas.setCategory(this);

		return purchas;
	}

	public Purchase removePurchas(Purchase purchas) {
		getPurchases().remove(purchas);
		purchas.setCategory(null);

		return purchas;
	}

	public List<ApprovedPurchases> getApprovedPurchases() {
		return approvedPurchases;
	}

	public void setCategoryHistories(List<CategoryHistory> categoryHistories) {
		this.categoryHistories = categoryHistories;
	}

}