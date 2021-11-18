package com.ssafy.cafe.model.dto;

import java.util.Date;
import java.util.List;


public class Order {
    private Integer id;
    private String userId;
    private String orderTable;
    private Date orderTime;

    private Character completed;
    private List<OrderDetail> details ;
    
    public Order(Integer id, String userId, String orderTable, Date orderTime, Character complited) {
        this.id = id;
        this.userId = userId;
        this.orderTable = orderTable;
        this.orderTime = orderTime;
        this.completed = complited;
    }

    public Order(String userId, String orderTable, Date orderTime, Character complited) {
        this.userId = userId;
        this.orderTable = orderTable;
        this.orderTime = orderTime;
        this.completed = complited;
    }
    
    public Order() {}

        
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

	public String getOrderTable() {
		return orderTable;
	}

	public void setOrderTable(String orderTable) {
		this.orderTable = orderTable;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Character getCompleted() {
		return completed;
	}

	public void setCompleted(Character completed) {
		this.completed = completed;
	}

	public List<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", orderTable=" + orderTable + ", orderTime=" + orderTime
				+ ", completed=" + completed + ", details=" + details + "]";
	}
    
    
}
