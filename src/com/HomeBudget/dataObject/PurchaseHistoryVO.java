/**
 * 
 */
package com.HomeBudget.dataObject;

import java.util.Date;

import com.entities.models.Purchase;

/**
 * @author Shehab
 *
 */
public class PurchaseHistoryVO extends ValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int location_id;

	private int purchase_id;
	private Date creation_date;
	private String details;
	private double price;
	private Purchase purchase;
	private String locationName;
	public int getLocation_id() {
		return location_id;
	}
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}
	public int getPurchase_id() {
		return purchase_id;
	}
	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Purchase getPurchase() {
		return purchase;
	}
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	
}
