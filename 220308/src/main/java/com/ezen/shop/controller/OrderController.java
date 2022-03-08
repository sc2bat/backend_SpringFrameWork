package com.ezen.shop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.shop.dto.CartVO;
import com.ezen.shop.dto.MemberVO;
import com.ezen.shop.dto.OrderVO;
import com.ezen.shop.service.CartService;
import com.ezen.shop.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	OrderService os;
	
	@Autowired
	CartService cs;
	
	@RequestMapping("/orderInsert")
	public String orderInsert(HttpServletRequest rq, Model md) {
		int oseq = 0;
//		HttpSession session = rq.getSession();
//		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		MemberVO mvo = (MemberVO)rq.getSession().getAttribute("loginUser");
		if(mvo == null) {
			return "member/login";
		}else {
			List<CartVO> cartList = cs.listCart(mvo.getUserid());
			oseq = os.insertOrder(cartList, mvo.getUserid());
		}
		
		// 방금 주문한 주문번호로 리스트 조회 후 화면에 표시하러 이동합니다
		return "redirect:/orderList?oseq=" + oseq;
	}
	
	
	/*
	@RequestMapping("/orderList")
	public ModelAndView orderList(HttpServletRequest rq, Model md) {
		ModelAndView mva = new ModelAndView();
		
		HttpSession session = rq.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			mva.setViewName("member/login");
		}else {
			int oseq = Integer.parseInt(rq.getParameter("oseq"));
			List<OrderVO> orderList = os.getOrderList(oseq);
			int totalPrice = 0;
			for(OrderVO ovo : orderList) {
				totalPrice += ovo.getPrice2() * ovo.getQuantity();
			}
			mva.addObject("orderList", orderList);
			mva.addObject("totalPrice", totalPrice);
			mva.setViewName("mypage/orderList");
		}
		return mva;
	}*/
	@RequestMapping("/orderList")
	public ModelAndView orderList(@RequestParam("oseq")int oseq, HttpServletRequest rq) {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = rq.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			mav.setViewName("member/login");
		}else {
			List<OrderVO> list = os.listOrderByOseq(oseq);
			int totalPrice = 0;
			for(OrderVO ovo : list) {
				totalPrice += ovo.getPrice2() * ovo.getQuantity();
			}
			mav.addObject("orderList", list);
			mav.addObject("totalPrice", totalPrice);
			mav.setViewName("mypage/orderList");
		}
		return mav;
	}
	
	/*
	@RequestMapping("/orderOne")
	public String orderOne(HttpServletRequest rq, Model md) {
		MemberVO mvo = (MemberVO)rq.getSession().getAttribute("loginUser");
		if(mvo == null) {
			return "member/login";
		}else {
			int pseq = Integer.parseInt(rq.getParameter("pseq"));
			int quantity = Integer.parseInt(rq.getParameter("quantity"));
			int oseq = os.insertOrderone(mvo.getUserid(), pseq, quantity);
		}
		return "redirect:/orderList?oseq=" + oseq;
	}*/
	
	@RequestMapping("/orderOne")
	public String orderOne(HttpServletRequest rq, @RequestParam("pseq")int pseq, @RequestParam("quantity")int quantity) {
		int oseq = 0;
		HttpSession session = rq.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			return "member/login";
		}else {
			oseq = os.insertOrderOne(pseq, quantity, mvo.getUserid());
		}
		return "redirect:/orderList?oseq=" + oseq;
	}
	
	@RequestMapping("/myPage")
	public ModelAndView myPage(HttpServletRequest rq) {
		ModelAndView mav = new ModelAndView();
		MemberVO mvo = (MemberVO)rq.getSession().getAttribute("loginUser");
		if(mvo == null) {
			mav.setViewName("member/login");
		}else {
			ArrayList<OrderVO> orderList = new ArrayList<OrderVO>(); // mypage.jsp 에 전달될 리스트
			
			// 1. 아이디로 진행중인 주문의 주문번호들 조회
			List<Integer> oseqList = os.selectSeqOrderIng(mvo.getUserid());
			// 2. 조회된 주문번호들 가져오기
			for(int oseq : oseqList) {
				List<OrderVO> orderListIng = os.listOrderByOseq(oseq); // 주문번호 하나의 주문상품들 리스트
				// 3. 리스트의 첫번째 주문을 꺼내서, 이름을 "상품명 외 x건", 가격을 리스트 상품들의 총합으로 변경
				OrderVO ovo = orderListIng.get(0); // 첫번째 상품 
				ovo.setPname(ovo.getPname() + "포함 " + orderListIng.size() + " 건 "); // 이름 변경
				int totalPrice = 0;
				for(OrderVO ovo1 : orderListIng) {
					totalPrice += ovo1.getPrice2()*ovo1.getQuantity();
				}
				ovo.setPrice2(totalPrice);
				//4. mypage.jsp 에 전달한 리스트에 현재 ovo를 추가
				orderList.add(ovo);
			}
			
			mav.addObject("title", "진행중인 주문내역");
			mav.addObject("orderList", orderList);
			mav.setViewName("mypage/mypage");
		}
		return mav;
	}
	
	@RequestMapping("/orderAll")
	public ModelAndView orderAll(HttpServletRequest rq) {
		ModelAndView mav = new ModelAndView();
		MemberVO mvo = (MemberVO)rq.getSession().getAttribute("loginUser");
		if( mvo == null) {
			mav.setViewName("member/login");
		}else {
			ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
			List<Integer> oseqList = os.oseqListAll(mvo.getUserid()); // 주문번호들 조회
			for(int oseq : oseqList) {
				List<OrderVO> orderListAll = os.listOrderByOseq(oseq); // 주문번호로 주문상품 조회
				OrderVO ovo = orderListAll.get(0);
				ovo.setPname(ovo.getPname() + " 포함 " + orderListAll.size() + " 건"); // 상품명변경
				int totalPrice = 0;
				for(OrderVO ovop : orderListAll) {
					totalPrice += ovop.getPrice2() * ovop.getQuantity();
				}
				ovo.setPrice2(totalPrice);
				orderList.add(ovo);
			}
			mav.addObject("title", "총 주문 내역");
			mav.addObject("orderList", orderList);
			mav.setViewName("mypage/mypage");
		}
		return mav;
	}
	
	@RequestMapping("/orderDetail")
	public ModelAndView orderDetail(HttpServletRequest rq, @RequestParam("oseq")int oseq) {
		ModelAndView mav = new ModelAndView();
		MemberVO mvo = (MemberVO)rq.getSession().getAttribute("loginUser");
		if(mvo == null) {
			mav.setViewName("member/login");
		}else {
			List<OrderVO> orderList = os.listOrderByOseq(oseq); // 주문번호로 주문 상품들의 리스트 리턴
			int totalPrice = 0;
			for(OrderVO ovo : orderList) {
				totalPrice += ovo.getPrice2() * ovo.getQuantity();
			}
			mav.addObject("orderList", orderList);
			mav.addObject("totalPrice", totalPrice);
			mav.addObject("orderDetail", orderList.get(0));
			
			mav.setViewName("mypage/orderDetail");
		}
		return mav;
	}
	
	@RequestMapping("/qnaWriteForm")
	public String qnaWriteForm(HttpServletRequest rq) {
		MemberVO mvo = (MemberVO)rq.getSession().getAttribute("loginUser");
		if(mvo == null) {
			return "member/login";
		}
		return "qna/qnaWrite";
	}
	
	
}
