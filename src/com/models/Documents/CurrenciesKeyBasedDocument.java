package com.models.Documents;


import java.util.List;

import com.dataObject.CurrencyVO;

public class CurrenciesKeyBasedDocument extends KeyBasedDocument{

	private List<CurrencyVO>CurrencyVO;

	public List<CurrencyVO> getCurrencyVO() {
		return CurrencyVO;
	}

	public void setCurrencyVO(List<CurrencyVO> currencyVO) {
		CurrencyVO = currencyVO;
	}

	

	

	
}
