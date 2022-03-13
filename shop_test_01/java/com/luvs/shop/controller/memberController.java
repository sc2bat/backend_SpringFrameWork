package com.luvs.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.luvs.shop.dto.ADD;
import com.luvs.shop.dto.MVO;
import com.luvs.shop.service.memberService;

@Controller
public class memberController {
	
	@Autowired
	memberService ms;
	
	@RequestMapping("/loginForm")
	public String loginForm(HttpServletRequest rq, Model md) {
		HttpSession ss = rq.getSession();
		MVO mvo = (MVO)ss.getAttribute("loginUser");
		
		if(mvo == null) {
			return "member/loginForm";
		}
		
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest rq, Model md) {
		String id = rq.getParameter("id");
		String pwd = rq.getParameter("pwd");
		MVO mvo = (MVO)ms.getMember(id);
		if(mvo == null) {
			md.addAttribute("message", "id null");
		}else if(mvo.getPwd() == null) {
			md.addAttribute("message", "pwd null");
		}else if(!mvo.getPwd().equals(pwd)) {
			md.addAttribute("message", "pwd wrong");
		}else if(mvo.getPwd().equals(pwd)) {
			rq.getSession().setAttribute("loginUser", mvo);
			return "redirect:/";
		}else {
			md.addAttribute("message", "cant login");
		}
		return "member/loginForm";
	}
	
	@RequestMapping("/contract")
	public String contract(HttpServletRequest rq, Model md) {
		return "member/contract";
	}
	
	@RequestMapping(value="/joinForm", method=RequestMethod.POST)
	public String joinForm(HttpServletRequest rq, Model md) {
		return "member/joinForm";
	}
	
	@RequestMapping("/idCheck")
	public String idCheck(@RequestParam("id")String id, Model md) {
		MVO mvo = ms.getMember(id);
		if(mvo == null) {
			md.addAttribute("result", "-1");
		}else {
			md.addAttribute("result", "1");
		}
		md.addAttribute("id", id);
		return "member/idcheck";
	}
	
	@RequestMapping("/findZipNum")
//	public String findZipNum(HttpServletRequest rq, Model md) {
//		String dong = rq.getParameter("dong");
	public String findZipNum(@RequestParam(value="dong", required = false)String dong, Model md) {
		if(dong != null && dong.trim().equals("") == false) {
			md.addAttribute("addressList", ms.getAddress(dong));
		}
		return "member/findZipNum";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(HttpServletRequest rq, Model md) {
		MVO mvo = new MVO();
		mvo.setMid(rq.getParameter("id"));
		mvo.setPwd(rq.getParameter("pwd"));
		mvo.setName(rq.getParameter("name"));
		mvo.setEmail(rq.getParameter("email"));
		mvo.setPhone(rq.getParameter("phone"));
		mvo.setZnum(rq.getParameter("zip_num"));
		mvo.setAddr1(rq.getParameter("addr1"));
		mvo.setAddr2(rq.getParameter("addr2"));
		ms.signUp(mvo);
		md.addAttribute("message", "signUp complete");
		return "member/loginForm";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest rq, Model md) {
		rq.getSession().removeAttribute("loginUser");
		return "redirect:/";
	}
	
	@RequestMapping("/memberEditForm")
	public String memberEditForm(HttpServletRequest rq, Model md) {
		return "member/memberUpdateForm";
	}
	
	@RequestMapping(value="/memberUpdate", method=RequestMethod.POST)
	public String memberUpdate(HttpServletRequest rq, Model md) {
		MVO mvo = new MVO();
		mvo.setMid(rq.getParameter("id"));
		mvo.setPwd(rq.getParameter("pwd"));
		mvo.setName(rq.getParameter("name"));
		mvo.setEmail(rq.getParameter("email"));
		mvo.setPhone(rq.getParameter("phone"));
		mvo.setZnum(rq.getParameter("zip_num"));
		mvo.setAddr1(rq.getParameter("addr1"));
		mvo.setAddr2(rq.getParameter("addr2"));
		ms.updateMember(mvo);
		rq.getSession().setAttribute("loginUser", mvo);
		return "redirect:/";
	}
}
