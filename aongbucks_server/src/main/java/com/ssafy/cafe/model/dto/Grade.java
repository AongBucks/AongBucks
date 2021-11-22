package com.ssafy.cafe.model.dto;

public class Grade {
	private Integer id;
	private String grade;
	private Float discount;
	private String img;
	private Integer standard;
	public Grade(Integer id, String grade, Float discount, String img, Integer standard) {
		super();
		this.id = id;
		this.grade = grade;
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
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
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
