package com.entities.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the monthly_budget database table.
 * 
 */
@Entity
@Table(name="monthly_budget")
@NamedQuery(name="MonthlyBudget.findAll", query="SELECT m FROM MonthlyBudget m")
public class MonthlyBudget implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MONTHLY_BUDGET_ID_GENERATOR", sequenceName="SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MONTHLY_BUDGET_ID_GENERATOR")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")
	private Date startDate;

	//bi-directional many-to-many association to Category
	@ManyToMany
	@JoinTable(
		name="monthly_budget_category"
		, joinColumns={
			@JoinColumn(name="MonthlyBudget_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="categories_ID")
			}
		)
	private List<Category> categories;

	public MonthlyBudget() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}