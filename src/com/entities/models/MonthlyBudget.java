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
@NamedQueries({@NamedQuery(name="getActiveMonthlyBudgetByUserId", query="SELECT m FROM MonthlyBudget m where m.user.id=:id and m.status=2"),
	@NamedQuery(name="deActivePreviosMonthlyBudget",query="update  MonthlyBudget m set m.status=1  where m.user.id=:id")})

public class MonthlyBudget implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MONTHLY_BUDGET_ID_GENERATOR", sequenceName="SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MONTHLY_BUDGET_ID_GENERATOR")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creation_date")
	private Date creationDate;
	private int status;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_date")
	private Date endDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")
	private Date startDate;

	@Column(name="total_expenses")
	private double totalExpenses;

	@Column(name="total_income")
	private double totalIncome;

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

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	//bi-directional many-to-one association to MonthlyBudgetCategory
	@OneToMany(mappedBy="monthlyBudget")
	private List<MonthlyBudgetCategory> monthlyBudgetCategories;

	public MonthlyBudget() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public double getTotalExpenses() {
		return this.totalExpenses;
	}

	public void setTotalExpenses(double totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	public double getTotalIncome() {
		return this.totalIncome;
	}

	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<MonthlyBudgetCategory> getMonthlyBudgetCategories() {
		return this.monthlyBudgetCategories;
	}

	public void setMonthlyBudgetCategories(List<MonthlyBudgetCategory> monthlyBudgetCategories) {
		this.monthlyBudgetCategories = monthlyBudgetCategories;
	}

	public MonthlyBudgetCategory addMonthlyBudgetCategory(MonthlyBudgetCategory monthlyBudgetCategory) {
		getMonthlyBudgetCategories().add(monthlyBudgetCategory);
		monthlyBudgetCategory.setMonthlyBudget(this);

		return monthlyBudgetCategory;
	}

	public MonthlyBudgetCategory removeMonthlyBudgetCategory(MonthlyBudgetCategory monthlyBudgetCategory) {
		getMonthlyBudgetCategories().remove(monthlyBudgetCategory);
		monthlyBudgetCategory.setMonthlyBudget(null);

		return monthlyBudgetCategory;
	}

}