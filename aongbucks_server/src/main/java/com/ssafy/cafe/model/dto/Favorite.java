package com.ssafy.cafe.model.dto;

public class Favorite {
	String userId;
	Integer productId;
	
	public Favorite() {}
	
	public Favorite(String userId, Integer productId) {
		super();
		this.userId = userId;
		this.productId = productId;
	}

	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public Integer getProductId() {
		return productId;
	}


	public void setProductId(Integer productId) {
		this.productId = productId;
	}

}
