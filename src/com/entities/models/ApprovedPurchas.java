package com.entities.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the approved_purchases database table.
 * 
 */
@Entity
@Table(name="approved_purchases")
@NamedQuery(name="ApprovedPurchas.findAll", query="SELECT a FROM ApprovedPurchas a")
public class ApprovedPurchas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="arabic_description")
	private String arabicDescription;

	private String details;

	@Column(name="english_description")
	private String englishDescription;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	

	public ApprovedPurchas() {
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

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	

}