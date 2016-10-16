package com.entities.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the location database table.
 * 
 */
@Entity
@NamedQuery(name="findAllLocations", query="SELECT l FROM Location l")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LOCATION_ID_GENERATOR", sequenceName="SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOCATION_ID_GENERATOR")
	private int id;

	@Column(name="arabic_name")
	private String arabicName;

	@Column(name="english_name")
	private String englishName;

	

	@OneToMany(mappedBy="location")
	private List<Purchase> purchases;
	
	public Location() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArabicName() {
		return this.arabicName;
	}

	public void setArabicName(String arabicName) {
		this.arabicName = arabicName;
	}

	public String getEnglishName() {
		return this.englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}



}