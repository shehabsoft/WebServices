package com.HomeBudget.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
@NamedNativeQueries({
	@NamedNativeQuery(
	name = "findCategoryByEnglishDescription",
	query = "select * from HomeBudget.Category c where c.english_description =:english_description",
        resultClass = Category.class
	),@NamedNativeQuery(
			name = "findAllCategories",
			query = "select * from HomeBudget.Category  ",
		        resultClass = Category.class
			)
})

//@Entity
//@Table(name="CATEGORY", schema="HomeBudget")
public class Category {
	@Id @GeneratedValue
	int Id;
	@Column(name="arabic_description")
	String arabicDescription;
	@Column(name="english_description")
	String englisDescription;
	@Column(name="parent_category_Id")
	int parentCategoryId;
	@Column(name="limit_value")
	double limit_value;
	@Column(name="planed_value")
	double planedValue;
	@Column(name="actual_value")
	double actualValue;
	@Column(name="category_Status")
	int categoryStatus;
	

	public String getArabicDescription() {
		return arabicDescription;
	}
	public void setArabicDescription(String arabicDescription) {
		this.arabicDescription = arabicDescription;
	}
	public String getEnglisDescription() {
		return englisDescription;
	}
	public void setEnglisDescription(String englisDescription) {
		this.englisDescription = englisDescription;
	}
	public int getParentCategoryId() {
		return parentCategoryId;
	}
	public void setParentCategoryId(int parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
	public double getLimit_value() {
		return limit_value;
	}
	public void setLimit_value(double limit_value) {
		this.limit_value = limit_value;
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
	public int getId() {
		return Id;
	}
	public int Id() {
		return Id;
	}
	public void setId(int Id) {
		this.Id = Id;
	}
	
	
}
