package com.ssafy.cafe.model.dto;

public class Favorite {
	Integer id;
	String userId;
	Integer productId;
	
	
	public Favorite(String userId, Integer productId) {
		super();
		this.userId = userId;
		this.productId = productId;
	}


	public Favorite(int id, String userId, Integer productId) {
		super();
		this.id = id;
		this.userId = userId;
		this.productId = productId;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
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


	@Override
	public String toString() {
		return "Favorite [id=" + id + ", userId=" + userId + ", productId=" + productId + "]";
	}
	
}
