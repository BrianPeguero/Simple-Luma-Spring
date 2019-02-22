package repositories;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import models.Product;

public interface ProductInt {
	
	public Product getProductBySKU(String sku);
	
	public LinkedHashSet<String> getAllProductSKU();
	
	public LinkedHashMap<String, Product> getAllSKUAndProduct();
}
