package com.luvs.shop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.luvs.shop.dto.MVO;
import com.luvs.shop.dto.OVO;
import com.luvs.shop.service.cartService;
import com.luvs.shop.service.orderService;

@Controller
public class orderContoller {
	private ModelAndView mv;
	
	@Autowired
	orderService os;
	@Autowired
	cartService cs;
	
//	@RequestMapping("orderInsert")
	@RequestMapping(value="orderInsert", method=RequestMethod.POST)
	public String orderInsert(HttpServletRequest rq) {
		int oseq = 0;
		MVO mvo = (MVO)rq.getSession().getAttribute("loginUser");
		if(mvo == null) {
			return "member/loginForm";
		}else {
			oseq = os.insertOrder(cs.getCartList(mvo.getMid()), mvo.getMid());
			return "redirect:/orderList?oseq=" + oseq;
		}
	}
	
	@RequestMapping("/orderList")
	public ModelAndView orderList(HttpServletRequest rq, @RequestParam("oseq")int oseq) {
		mv = new ModelAndView();
		if(rq.getSession().getAttribute("loginUser") == null) {
			mv.setViewName("member/loginForm");
		}else {
			List<OVO> list = os.getOrderList(oseq);
//			System.out.println(list.size());
			int totalPrice = 0;
			for(OVO ovo : list) {
				totalPrice += ovo.getPrice2() * ovo.getQuantity();
			}
			mv.addObject("orderList", list);
			mv.addObject("totalPrice", totalPrice);
			mv.setViewName("order/orderList");
		}
		return mv;
	}
	
	@RequestMapping("/orderOne")
	public String orderOne(HttpServletRequest rq, @RequestParam("pseq")int pseq, @RequestParam("quantity")int quantity) {
		MVO mvo = (MVO)rq.getSession().getAttribute("loginUser");
		if(mvo == null) {
			return "member/loginForm";
		}else {
			int oseq = os.orderOne(mvo.getMid(), pseq, quantity);
			return "redirect:/orderList?oseq=" + oseq;
		}
	}
	
//	@RequestMapping("/mypage")
//	public String mypage(HttpServletRequest rq, Model md) {
//		MVO mvo = (MVO)rq.getSession().getAttribute("loginUser");
//		if(mvo == null) {
//			return "member/loginForm";
//		}else {
//			List<OVO> list = os.getOrderAll(mvo.getMid());
//			md.addAttribute("orderList", list);
//			return "cart/mypage";
//		}
//	}
	@RequestMapping("/myPage")
	public ModelAndView mypage(HttpServletRequest rq) {
		mv = new ModelAndView();
		MVO mvo = (MVO)rq.getSession().getAttribute("loginUser");
		if(mvo == null) {
			mv.setViewName("member/loginForm");
		}else {
			ArrayList<OVO> orderList = new ArrayList<OVO>();
			List<Integer> oseqList = os.getOseqList(mvo.getMid());
			for(int oseq : oseqList) {
				List<OVO> list = os.getOrderList(oseq);
				OVO ovo = list.get(0);
				ovo.setPname(ovo.getPname() + " 포함 " + list.size() + " 건");
				int totalPrice = 0;
				for(OVO o : list) {
					totalPrice += o.getPrice2() * o.getQuantity();
				}
				ovo.setPrice2(totalPrice);
				orderList.add(ovo);
//				System.out.println(orderList.size());
			}
			mv.addObject("title", "mypage Order");
			mv.addObject("orderList", orderList);
			mv.setViewName("cart/mypage");
		}
		return mv;
	}
	
	@RequestMapping("/orderDetail")
	public ModelAndView orderDetail(HttpServletRequest rq, @RequestParam("oseq")int oseq) {
		mv = new ModelAndView();
		MVO mvo = (MVO)rq.getSession().getAttribute("loginUser");
		if(mvo == null) {
			mv.setViewName("member/loginForm");
		}else {
			List<OVO> orderList = os.getOrderList(oseq);
			int totalPrice = 0;
			for(OVO ovo : orderList) {
				totalPrice += ovo.getPrice2() * ovo.getQuantity();
			}
			mv.addObject("totalPrice", totalPrice);
			mv.addObject("orderList", orderList);
			mv.addObject("orderDetail", orderList.get(0));
			mv.setViewName("order/orderDetail");
		}
		return mv;
	}
	
	@RequestMapping("/orderAll")
	public ModelAndView orderAll(HttpServletRequest rq) {
		mv = new ModelAndView();
		MVO mvo = (MVO)rq.getSession().getAttribute("loginUser");
		if(mvo == null) {
			mv.setViewName("member/loginForm");
		}else {
			ArrayList<OVO> orderList = new ArrayList<OVO>();
			List<Integer> list = os.getOseqList(mvo.getMid());
			for(int oseq : list) {
				List<OVO> orderListAll = os.getOrderList(oseq);
				OVO ovo = orderListAll.get(0);
				ovo.setPname(ovo.getPname() + " 포함 " + orderListAll.size() + " 건");
				int totalPrice = 0;
				for(OVO o : orderListAll) {
					totalPrice += o.getPrice2() * o.getQuantity();
				}
				ovo.setPrice2(totalPrice);
				orderList.add(ovo);
			}
			mv.addObject("title", "OrderAll");
			mv.addObject("orderList", orderList);
			mv.setViewName("cart/mypage");
		}
		return mv;
	}
	  
}
