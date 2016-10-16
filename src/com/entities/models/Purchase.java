package com.entities.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the purchase database table.
 * 
 */

@Entity
@NamedQuery(name="findAllPurchases", query="SELECT p FROM Purchase p")
public class Purchase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PURCHASE_ID_GENERATOR", sequenceName="SEQ")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="arabic_description")
	private String arabicDescription;

	private String details;

	@Column(name="english_description")
	private String englishDescription;

	private double price;

	//bi-directional many-to-one association to Location
	
	@ManyToOne
	private Location location;
	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	public Purchase() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArabicDescription() {
		return this.arabicDescription;
	}

	public void setArabicDescription(String arabicDescription) {
		this.arabicDescription = arabicDescription;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getEnglishDescription() {
		return this.englishDescription;
	}

	public void setEnglishDescription(String englishDescription) {
		this.englishDescription = englishDescription;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}



	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}


	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}