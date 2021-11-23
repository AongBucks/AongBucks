package com.ssafy.cafe.model.dto;

public class Grade {
	private Integer id;
	private String title;
	private Float discount;
	private String img;
	private Integer standard;
	public Grade(Integer id, String title, Float discount, String img, Integer standard) {
		super();
		this.id = id;
		this.title = title;
		this.discount = discount;
		this.img = img;
		this.standard = standard;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Float getDiscount() {
		return discount;
	}
	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getStandard() {
		return standard;
	}
	public void setStandard(Integer standard) {
		this.standard = standard;
	}
}
