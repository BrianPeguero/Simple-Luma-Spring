package models;

public class Product {
	
	private int id;
	private String sku;
	private String name;
	private int attribute_set;
	private double price;
	private String product_type;
	private Object[] category_id;
	private String image_file;
	
	
	public Product(int id, String sku, String name, int attribute_set, double price, String product_type,
			Object[] category_id, String image_file) {
		this.id = id;
		this.sku = sku;
		this.name = name;
		this.attribute_set = attribute_set;
		this.price = price;
		this.product_type = product_type;
		this.category_id = category_id;
		this.image_file = image_file;
	}
	
	
	public int getId() {
		return id;
	}
	public String getSku() {
		return sku;
	}
	public String getName() {
		return name;
	}
	public int getAttribute_set() {
		return attribute_set;
	}
	public double getPrice() {
		return price;
	}
	public String getProduct_type() {
		return product_type;
	}
	public Object[] getCategory_id() {
		return category_id;
	}
	public String getImage_file() {
		return image_file;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAttribute_set(int attribute_set) {
		this.attribute_set = attribute_set;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public void setCategory_id(Object[] category_id) {
		this.category_id = category_id;
	}
	public void setImage_file(String image_file) {
		this.image_file = image_file;
	}
	
}
