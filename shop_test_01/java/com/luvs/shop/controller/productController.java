package com.luvs.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.luvs.shop.service.productService;


@Controller
public class productController {
	
	@Autowired
	productService ps;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(HttpServletRequest rq, Model md) {
		md.addAttribute("newProList", ps.getNewList());
		md.addAttribute("bestProList", ps.getBestList());
		
		return "index";
	}
	
	@RequestMapping("/category")
	public ModelAndView category(@RequestParam("kind")String kind) { 
		ModelAndView mav = new ModelAndView();
		mav.addObject("productKindList", ps.productKindList(kind));
		mav.setViewName("product/productKind");
		return mav;
	}
	
	@RequestMapping("/productDetail")
	public ModelAndView productDetail(@RequestParam("pseq")int pseq) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("productVO", ps.getProduct(pseq));
		mav.setViewName("product/productDetail");
		return mav;
	}
}
