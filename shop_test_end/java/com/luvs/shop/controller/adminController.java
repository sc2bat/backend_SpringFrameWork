package com.luvs.shop.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.luvs.shop.dto.PVO;
import com.luvs.shop.dto.Paging;
import com.luvs.shop.service.adminService;
import com.luvs.shop.service.productService;

@Controller
public class adminController {
	private ModelAndView mv;
	
	@Autowired
	adminService as;
	
	@Autowired
	productService ps;
	
	@RequestMapping("/AdminLoginForm")
	public String AdminLoginForm() {
		return "admin/adminLoginForm";
	}
	
	/*
	@RequestMapping("adminLogin")
	public String adminLogin(HttpServletRequest rq, Model md,
			@RequestParam("workId")String workId, @RequestParam("workPwd")String workPwd) {
		AVO avo = as.getAdmin(workId);
		
		if(avo == null) {
			md.addAttribute("message", "id null");
			return "admin/adminLoginForm";
		}else if(!avo.getAdminPwd().equals(workPwd)) {
			md.addAttribute("message", "pwd not matched");
			return "admin/adminLoginForm";
		}else if(avo.getAdminPwd().equals(workPwd)) {
			md.addAttribute("loginAdmin", avo);
			md.addAttribute("productList", ps.getProductList());
			return "admin/productList";
		}else {
			md.addAttribute("message", "!?!?");
			return "admin/adminLoginForm";
		}
	}*/
	
	@RequestMapping("adminLogin")
	public ModelAndView adminLogin(@RequestParam("workId")String adminId, @RequestParam("workPwd")String adminPwd,
			HttpServletRequest rq) {
		mv = new ModelAndView();
		mv.setViewName("admin/adminLoginForm");
		int result = as.adminCheck(adminId, adminPwd);
		if(result == 0) {
			mv.setViewName("redirect:/productList");
			rq.getSession().setAttribute("loginAdmin", adminId);
		}else if(result == 1){
			mv.addObject("message", "Incorrect username");
		}else if(result == 2) {
			mv.addObject("message", "Incorrect password");
		}else if(result == 3) {
			mv.addObject("message", "ask to admin");
		}else {
			mv.addObject("message", "!?!?!?");
		}
		return mv;
	}
	
	@RequestMapping("/productList")
	public ModelAndView productList(HttpServletRequest rq) {
		mv = new ModelAndView();
//		String adminId = (String)rq.getSession().getAttribute("loginAdmin");
		HttpSession ss = rq.getSession();
		String adminId = (String)ss.getAttribute("loginAdmin");
		
		if(adminId == null) {
			mv.setViewName("redirect:/main");
		}else {
			if(rq.getParameter("first") != null) {
				ss.removeAttribute("page");
				ss.removeAttribute("key");
			}
			int page = 1;
			if(rq.getParameter("page") != null) {
				page = Integer.parseInt(rq.getParameter("page"));
				ss.setAttribute("page", page);
			}else if(ss.getAttribute("page") != null) {
				page = (int)ss.getAttribute("page");
			}else {
				ss.removeAttribute("page");
			}
			String key = "";
			if(rq.getParameter("key") != null) {
//				key = rq.getParameter("key");
//				ss.setAttribute("key", key);
				ss.setAttribute("key", rq.getParameter("key"));
			}else if(ss.getAttribute("key") != null) {
				key = (String) ss.getAttribute("key");
			}else {
				ss.removeAttribute("key");
			}
			
			HashMap<String, Object> resultMap = as.listProduct(page, key);
//			List<PVO> list = (List<PVO>)resultMap.get("productList");
//			Paging paging = (Paging)resultMap.get("paging");
			
//			mv.addObject("key", key);
//			mv.addObject("paging", paging);
//			mv.addObject("productList", list);
			
			mv.addObject("key", key);
			mv.addObject("paging", (Paging)resultMap.get("paging"));
			mv.addObject("productList", (List<PVO>)resultMap.get("productList"));
			
			mv.setViewName("admin/product/productList");
		}
		return mv;
	}
	
	@RequestMapping("/adminLogout")
	public String logout(HttpServletRequest rq) {
		rq.getSession().invalidate();
		return "redirect:/AdminLoginForm";
	}
	
	@RequestMapping("/adminOrderList")
	public ModelAndView adminOrderList(HttpServletRequest request) {
		mv = new ModelAndView();
		HttpSession session = request.getSession();
		String adminId = (String)session.getAttribute("loginAdmin");
		
		if(adminId == null) {
			mv.setViewName("admin/adminLoginForm");
		}else {
			if(request.getParameter("first") != null) {
				session.removeAttribute("page");
				session.removeAttribute("key");
			}
			int page = 1;
			if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			}else if(session.getAttribute("page") != null) {
				page = (int)session.getAttribute("page");
			}else {
				session.removeAttribute("page");
			}
			String key = "";
			if(request.getParameter("key") != null) {
				session.setAttribute("key", request.getParameter("key"));
			}else if(session.getAttribute("key") != null) {
				key = (String) session.getAttribute("key");
			}else {
				session.removeAttribute("key");
			}
			HashMap<String, Object> hashMap =  as.getOrderList(page, key);
			mv.addObject("key", key);
			mv.addObject("paging", hashMap.get("paging"));
			mv.addObject("orderList", hashMap.get("orderList"));
			mv.setViewName("admin/order/orderList");
		}
		return mv;
	}
	
	@RequestMapping("/orderUpdateResult")
	public ModelAndView orderUpdateResult(HttpServletRequest request, @RequestParam("result")int[] resultArr) {
		mv = new ModelAndView();
		String adminId = (String)request.getSession().getAttribute("loginAdmin");
		if(adminId == null) {
			mv.setViewName("redirect:/AdminLoginForm");
		}else {
			for(int odseq : resultArr) {
				as.updateOrderResult(odseq);
			}
			mv.setViewName("redirect:/adminOrderList");
		}
		return mv;
	}
	
}
