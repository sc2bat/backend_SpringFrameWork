package com.luvs.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.luvs.shop.dto.CVO;
import com.luvs.shop.dto.MVO;
import com.luvs.shop.service.cartService;

@Controller
public class cartController {

	@Autowired
	cartService cs;
	
	@RequestMapping("/cartInsert")
	public String cartInsert(HttpServletRequest rq, @RequestParam("pseq")int pseq, @RequestParam("quantity")int quantity) {
		HttpSession ss = rq.getSession();
		MVO mvo = (MVO)ss.getAttribute("loginUser");
		if(mvo == null) {
			return "member/loginForm";
		}else {
			CVO cvo = new CVO();
			cvo.setCid(mvo.getMid());
			cvo.setPseq(pseq);
			cvo.setQuantity(quantity);
			cs.insertCart(cvo);
			return "redirect:/cartList";
		}
	}
	
	@RequestMapping("/cartList")
	public ModelAndView cartList(HttpServletRequest rq){
		ModelAndView mav = new ModelAndView();
		HttpSession ss = rq.getSession();
		MVO mvo = (MVO)ss.getAttribute("loginUser");
		if(mvo == null) {
			mav.setViewName("member/loginForm");
		}else {
			List<CVO> cartList = cs.getCartList(mvo.getMid());
			int totalPrice = 0;
			for(CVO cvo : cartList) {
				totalPrice += cvo.getQuantity() * cvo.getPrice2();
			}
			mav.addObject("cartList", cartList);
			mav.addObject("totalPrice", totalPrice);
			mav.setViewName("cart/cartList");
		}
		return mav;
	}
	
	@RequestMapping("/cartDelete")
	public String cartDelete(HttpServletRequest rq, @RequestParam("cseq")String[] cseqArr) {
		HttpSession ss = rq.getSession();
		MVO mvo = (MVO)ss.getAttribute("loginUser");
		if(mvo == null) {
			return "member/loginForm";
		}else {
			for(String cseq : cseqArr) {
				cs.cartDelete(Integer.parseInt(cseq));
			}
			return "redirect:/cartList";
		}
	}
	
}
