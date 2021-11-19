package com.ssafy.cafe.model.dto;


public class Stamp {
    private Integer id;
    private String userId;
    private Integer orderId;
    private Integer quantity;
    
    public Stamp(Integer id, String userId, Integer orderId, Integer quantity) {
        this.id = id;
        this.userId = userId;
        this.orderId = orderId;
        this.quantity = quantity;
    }
    
    public Stamp(String userId, Integer orderId, Integer quantity) {
        this.userId = userId;
        this.orderId = orderId;
        this.quantity = quantity;
    }
    
    public Stamp() {}

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

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Stamp [id=" + id + ", userId=" + userId + ", orderId=" + orderId + ", quantity=" + quantity + "]";
	}
    
    
    
}
