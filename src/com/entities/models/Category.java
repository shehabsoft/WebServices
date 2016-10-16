package com.entities.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@NamedQueries({@NamedQuery(name="findAllCategories", query="SELECT c FROM Category c"),
	           @NamedQuery(name="findCategotyById", query="SELECT c FROM Category c where c.id=:id"),
	           @NamedQuery(name="findExpensesCategories", query="SELECT c FROM Category c where c.categoryTypeId=2"),
	           @NamedQuery(name="findBudgetCategories", query="SELECT c FROM Category c where c.categoryTypeId=1")
	           
	           
	          

})
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

    @Version
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
	



	//bi-directional many-to-one association to Purchase
	@OneToMany(mappedBy="category")
	private List<Purchase> purchases;

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



	public int getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(int categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	public int getCategoryTypeId() {
		return categoryTypeId;
	}

	public void setCategoryTypeId(int categoryTypeId) {
		this.categoryTypeId = categoryTypeId;
	}

	public int getParentCategoryId() {
		return parentCategoryId;
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

}