package com.models.Documents;


import java.util.List;

import com.dataObject.CategoryVO;

public class CategoriesKeyBasedDocument extends KeyBasedDocument {

	private List<CategoryVO>CategoryVO;

	public List<CategoryVO> getCategoryVO() {
		return CategoryVO;
	}

	public void setCategoryVO(List<CategoryVO> categoryVOs) {
		this.CategoryVO = categoryVOs;
	}

	
}
