package com.models.Documents;


import java.util.List;

import com.dataObject.PurchaseVO;

public class PurchasesKeyBasedDocument  extends KeyBasedDocument{

	private List<PurchaseVO>PurchaseVO;

	public List<PurchaseVO> getPurchaseVO() {
		return PurchaseVO;
	}

	public void setPurchaseVO(List<PurchaseVO> purchaseVO) {
		this.PurchaseVO = purchaseVO;
	}

	

	
}
