/**
 * 
 */
package com.HomeBudget.dataObject;

import java.util.Date;

import com.dataObject.CategoryVO;
import com.entities.models.Category;
import com.entities.models.Purchase;

/**
 * @author Shehab
 *
 */
public class CategoryHistoryVO extends ValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private double  actualValue;
	private double plannedValue;
	private double limitValue;
    private String creation_date;
	private int categoryTypeId;
	public int getCategoryTypeId() {
		return categoryTypeId;
	}
	public void setCategoryTypeId(int categoryTypeId) {
		this.categoryTypeId = categoryTypeId;
	}
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	public double getActualValue() {
		return actualValue;
	}
	public void setActualValue(double actualValue) {
		this.actualValue = actualValue;
	}
	public double getPlannedValue() {
		return plannedValue;
	}
	public void setPlannedValue(double plannedValue) {
		this.plannedValue = plannedValue;
	}
	public double getLimitValue() {
		return limitValue;
	}
	public void setLimitValue(double limitValue) {
		this.limitValue = limitValue;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	



	}
	
	

