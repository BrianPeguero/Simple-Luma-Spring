package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

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
		BufferedReader br = null;
		
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
			
			System.out.println("Response: " + response);
			
			HttpEntity entity = response.getEntity();
			
			//using EntityUtils to read the xml file
			/*System.out.println("Entity: " + entity + "\n");
			String content = EntityUtils.toString(entity);
			System.out.println(content);*/
			
			//checks if entity is not null
			if(entity != null) {
				System.out.println("entity is not null");
				
				//using bufferedReader to read the response
				br = new BufferedReader(new InputStreamReader(entity.getContent()));
				
				String line = "";
				
				while((line = br.readLine()) != null) {
					System.out.println(line);
				}
				
			} else {
				System.out.println("entity is null");
			}
			
		} catch (IOException e) {
			System.out.println("couldnt execute request");
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

}
