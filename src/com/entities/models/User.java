package com.entities.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USERS_ID_GENERATOR", sequenceName="SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USERS_ID_GENERATOR")
	private int id;

	private String address;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creation_date")
	private Date creationDate;

	private String email;

	@Column(name="gender_id")
	private int genderId;

	@Column(name="mobile_number")
	private int mobileNumber;

	private String name;

	private String password;

	@Column(name="status_id")
	private int statusId;

	 
	 
	//bi-directional many-to-one association to MonthlyBudget
	@OneToMany(mappedBy="user")
	private List<MonthlyBudget> monthlyBudgets;


	//bi-directional many-to-one association to Country
	@ManyToOne
	private Country country;

	//bi-directional many-to-one association to Currency
	@ManyToOne
	private Currency currency;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGenderId() {
		return this.genderId;
	}

	public void setGenderId(int genderId) {
		this.genderId = genderId;
	}

	public int getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatusId() {
		return this.statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public List<MonthlyBudget> getMonthlyBudgets() {
		return this.monthlyBudgets;
	}

	public void setMonthlyBudgets(List<MonthlyBudget> monthlyBudgets) {
		this.monthlyBudgets = monthlyBudgets;
	}

	public MonthlyBudget addMonthlyBudget(MonthlyBudget monthlyBudget) {
		getMonthlyBudgets().add(monthlyBudget);
		monthlyBudget.setUser(this);

		return monthlyBudget;
	}

	public MonthlyBudget removeMonthlyBudget(MonthlyBudget monthlyBudget) {
		getMonthlyBudgets().remove(monthlyBudget);
		monthlyBudget.setUser(null);

		return monthlyBudget;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Currency getCurrency() {
		return this.currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
 
}