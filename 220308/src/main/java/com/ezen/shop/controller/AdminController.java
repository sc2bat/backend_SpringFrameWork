package com.ezen.shop.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.shop.service.AdminService;
import com.ezen.shop.service.ProductService;
import com.ezen.shop.service.QnaService;

@Controller
public class AdminController {
	@Autowired
	AdminService as;
	@Autowired
	ProductService ps;
	@Autowired
	QnaService qs;
	@Autowired
	ServletContext context; // file upload
	
	@RequestMapping("/admin")
	public String admin() {
		return "admin/adminLoginForm";
	}
	
	@RequestMapping("/adminLogin")
	public ModelAndView adminLogin(HttpServletRequest rq, @RequestParam("workId")String wid, @RequestParam("workPwd")String wpwd) {
		ModelAndView mav = new ModelAndView();
		int result = as.workerCheck(wid, wpwd);
		// result 값이 1 이면 정상 로그인, 0 이면 비밀번호 오류, -1 이면 아이디 없음
		
		if(result == 1) {
			rq.getSession().setAttribute("workId", wid);
			mav.setViewName("redirect:/productList");
		}else if(result == 0) {
			mav.addObject("message", "비밀번호를 확인하세요");
			mav.setViewName("admin/adminLoginForm");
		}else if(result == -1) {
			mav.addObject("message", "아이디를 확인하세요");
			mav.setViewName("admin/adminLoginForm");
		}else {
			mav.addObject("message", "알 수 없는 이유로 로그인 불가");
			mav.setViewName("admin/adminLoginForm");
		}
		
		return mav;
	}
	
	@RequestMapping("/productList")
	public String product_list(HttpServletRequest rq) {
//		ModelAndView
		return "admin/product/productList";
	}
}
