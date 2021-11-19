package com.ssafy.cafe.model.dto;

public class Product {
    private Integer id;
    private String name;
    private String type;
    private Integer price;
    private String img;
    
    public Product(Integer id, String name, String type, Integer price, String img) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.img = img;
    }
    
    public Product(String name, String type, Integer price, String img) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.img = img;
    }
    public Product() {}
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", type=" + type + ", price=" + price + ", img=" + img + "]";
	}
    
    
    
    
}
