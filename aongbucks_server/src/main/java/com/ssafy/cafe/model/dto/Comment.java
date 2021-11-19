package com.ssafy.cafe.model.dto;

public class Comment {
    private Integer id;
    private String userId;
    private Integer productId;
    private Double rating;
    private String comment;
    
	public Comment(Integer id, String userId, Integer productId, Double rating, String comment) {
		super();
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.rating = rating;
		this.comment = comment;
	}
	
    public Comment( String userId, Integer productId, Double rating, String comment) {
        this.userId = userId;
        this.productId = productId;
        this.rating = rating;
        this.comment = comment;
    }
    
    public Comment() {
    	
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

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", userId=" + userId + ", productId=" + productId + ", rating=" + rating
				+ ", comment=" + comment + "]";
	}
    
    
    
}