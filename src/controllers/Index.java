package controllers;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import models.Product;
import services.ProductImp;

@Controller
public class Index {
	
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index");
		LinkedHashMap<String, Product> firstFiveProduct = new ProductImp().getFirstFiveSKUAndProduct();		
		
		
		//passing in the head title
		mav.addObject("headTitle", "Luma Spring");
		//passing in the list of products
		mav.addObject("firstFiveProducts",firstFiveProduct);
		
		return mav;
	}
	
}