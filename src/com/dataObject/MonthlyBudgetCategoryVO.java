package com.dataObject;

import java.util.List;

public class MonthlyBudgetCategoryVO {
	
	private int Id;
	
	private List<MonthlyBudgetVO>monthlyBudgetVOs;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public List<MonthlyBudgetVO> getMonthlyBudgetVOs() {
		return monthlyBudgetVOs;
	}

	public void setMonthlyBudgetVOs(List<MonthlyBudgetVO> monthlyBudgetVOs) {
		this.monthlyBudgetVOs = monthlyBudgetVOs;
	}
	
	
}
