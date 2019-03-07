package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import models.Product;
import repositories.ProductInt;

public class ProductImp implements ProductInt {

	@Override
	public Product getProductBySKU(String sku) {
		Product product = null;
		
		String token = "xq0o8r3bkuvlf66xv5pmpwp8jax9vvvv";
		String url = "http://localhost/magento2/rest/V1/products/" + sku;
		
		//create the client to execute the request
		HttpClient client = HttpClientBuilder.create().build();
		
		
		//creates the request with HTTP request method for the target url
		HttpGet request = new HttpGet(url);
		
		//add to the head of the request using the token and content type you want back
		request.addHeader("Authorization", "Bearer " + token);
		request.addHeader("Accept", "application/xml");
		request.addHeader("Content-Type", "application/xml");
		
		try {
			//Gets the response from the call
			HttpResponse response = client.execute(request);
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        
	        Document doc = dBuilder.parse(response.getEntity().getContent());
	        
			
	        int id = Integer.parseInt(doc.getElementsByTagName("id").item(0).getTextContent());
	        String productsku = doc.getElementsByTagName("sku").item(0).getTextContent();
	    	String name = doc.getElementsByTagName("name").item(0).getTextContent();
	    	int attribute_set = Integer.parseInt(doc.getElementsByTagName("attribute_set").item(0).getTextContent());
	    	double price = Double.parseDouble(doc.getElementsByTagName("price").item(0).getTextContent());
	    	String product_type = doc.getElementsByTagName("product_type").item(0).getTextContent();
	    	
	    	NodeList category_id_node_list = doc.getElementsByTagName("category_id");
	    	LinkedList<Integer> category_id = new LinkedList<Integer>();
	    	for (int i = 0; i < category_id_node_list.getLength(); i++) {
	    		category_id.add(Integer.parseInt(category_id_node_list.item(i).getTextContent()));
	    	}
	    	
	    	String image_file = doc.getElementsByTagName("file").item(0).getTextContent();
	    	
	    	
	    	product = new Product(id, productsku, name, attribute_set, price, product_type, category_id.toArray(),image_file);
	    	
	        
		} catch (IOException e) {
			System.out.println("couldnt execute request");
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return product;
	}

	@Override
	public LinkedHashSet<String> getAllProductSKU() {
		
		String token = "xq0o8r3bkuvlf66xv5pmpwp8jax9vvvv";
		String url = "http://localhost/magento2/rest/V1/products/?searchCriteria=";
		
		//create the client to execute the request
		HttpClient client = HttpClientBuilder.create().build();
		
		//creates the request with HTTP request method for the target url
		HttpGet request = new HttpGet(url);
		
		//add to the head of the request using the token and content type you want back
		request.addHeader("Authorization", "Bearer " + token);
		request.addHeader("Accept", "application/xml");
		request.addHeader("Content-Type", "application/xml");
		
		LinkedHashSet<String> lhs = null;
		try {
			
			HttpResponse response = client.execute(request);
			
			//xml DOM document parser 
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        
	        Document doc = dBuilder.parse(response.getEntity().getContent());
	        
	        NodeList skuList = doc.getElementsByTagName("sku");
	        
	        lhs = new LinkedHashSet<String>();
	        
	        for(int i = 0; i < skuList.getLength(); i++) {
	        	String sku = skuList.item(i).getTextContent();
	        	lhs.add(sku);
	        }
	        
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lhs;
	}

	@Override
	public LinkedHashMap<String, Product> getAllSKUAndProduct() {
		String token = "xq0o8r3bkuvlf66xv5pmpwp8jax9vvvv";
		String url = "http://localhost/magento2/rest/V1/products/?searchCriteria=";
		
		//create the client to execute the request
		HttpClient client = HttpClientBuilder.create().build();
		
		//creates the request with HTTP request method for the target url
		HttpGet request = new HttpGet(url);
		
		//add to the head of the request using the token and content type you want back
		request.addHeader("Authorization", "Bearer " + token);
		request.addHeader("Accept", "application/xml");
		request.addHeader("Content-Type", "application/xml");
		
		LinkedHashMap<String, Product> lhm = null;
		try {
			
			HttpResponse response = client.execute(request);
			
			//xml DOM document parser 
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        
	        Document doc = dBuilder.parse(response.getEntity().getContent());
	        
	        NodeList skuList = doc.getElementsByTagName("sku");
	        
	        lhm = new LinkedHashMap<String, Product>();
	        
	        for(int i = 0; i < skuList.getLength(); i++) {
	        	String sku = skuList.item(i).getTextContent();
	        	Product product = getProductBySKU(sku);
	        	lhm.put(sku, product);
	        }
	        
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lhm;
	}

}
