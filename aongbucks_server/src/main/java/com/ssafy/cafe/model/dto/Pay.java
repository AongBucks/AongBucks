package com.ssafy.cafe.model.dto;

public class Pay {
	private Integer id;
	private String userId;
	private Integer price;
	public Pay(Integer id, String userId, Integer price) {
		super();
		this.id = id;
		this.userId = userId;
		this.price = price;
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
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
}
