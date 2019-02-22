package repositories;

import java.util.LinkedHashMap;
import java.util.List;

import models.Product;

public interface ProductInt {
	
	public Product getProductBySKU(String sku);
	
	public List<String> getAllProductSKU();
	
	public LinkedHashMap<String, Product> getAllSKUAndProduct();
}
