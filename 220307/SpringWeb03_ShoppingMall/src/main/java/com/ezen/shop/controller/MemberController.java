package com.ezen.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.shop.dao.MemberDao;
import com.ezen.shop.dto.MemberVO;
import com.ezen.shop.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService ms;
	@Autowired
	MemberDao mdao;
	
	@RequestMapping("loginForm")
	public String login_form(HttpServletRequest request, Model model) {
		return "member/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		MemberVO mvo = ms.getMember(id);
		if(mvo == null) {
			model.addAttribute("message", "ID가 없습니다");
			return "member/login";
		}else if(mvo.getPwd() == null) {
			model.addAttribute("message", "관리자에게 문의");
			return "member/login";
		}else if(!mvo.getPwd().equals(pwd)) {
			model.addAttribute("message", "비밀번호 다름");
			return "member/login";
		}else if(mvo.getPwd().equals(pwd)) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mvo);
			return "redirect:/";
		}else {
			model.addAttribute("message", "알수없는 이유로 로그인 불가");
			return "member/login";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		session.removeAttribute("loginUser");
		return "redirect:/";
	}
	
	@RequestMapping("/contract")
	public String contract(HttpServletRequest request, Model model) {
		return "member/contract";
	}
	
	@RequestMapping(value="/joinForm", method=RequestMethod.POST)
	public String joinForm(HttpServletRequest request, Model model) {
		return "member/joinForm";
	}
	
//	@RequestMapping(value="/idCheckForm", method=RequestMethod.POST)
	@RequestMapping("/idCheckForm")
	public String idCheck(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		MemberVO mvo = ms.getMember(id);
		if(mvo == null) {
			model.addAttribute("result", -1); // 사용가능
		}else {
			model.addAttribute("result", 1); // 사용불가
		}
		model.addAttribute("id", id);
		return "member/idcheck";
	}
	
	@RequestMapping("/findZipNum")
	public String findZipNum(HttpServletRequest request, Model model) {
		String dong = request.getParameter("dong");
		if(dong!= null && dong.trim().equals("") == false) {
			model.addAttribute("addressList", ms.selectAddressByDong(dong));
		}
		return "member/findZipNum";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(HttpServletRequest request, Model model) {
		MemberVO mvo = new MemberVO();
		mvo.setUserid(request.getParameter("id"));
		mvo.setPwd(request.getParameter("pwd"));
		mvo.setName(request.getParameter("name"));
		mvo.setEmail(request.getParameter("email"));
		mvo.setZip_num(request.getParameter("zip_num"));
//		mvo.setAddress(request.getParameter("addr1") + " " + request.getParameter("addr2"));
		mvo.setAddress(request.getParameter("addr1"));
		mvo.setAddress2(request.getParameter("addr2"));
		mvo.setPhone(request.getParameter("phone"));
		ms.insertMember(mvo);
		return "member/login";
	}
	
	@RequestMapping(value="/memberEditForm")
	public String memberEditForm(HttpServletRequest request, Model model) {
		return "member/memberUpdateForm";
	}
	
	@RequestMapping(value="/memberUpdate", method=RequestMethod.POST)
	public String memberUpdate(HttpServletRequest request, Model model) {
		MemberVO mvo = new MemberVO();
		mvo.setUserid(request.getParameter("id"));
		mvo.setPwd(request.getParameter("pwd"));
		mvo.setName(request.getParameter("name"));
		mvo.setEmail(request.getParameter("email"));
		mvo.setZip_num(request.getParameter("zip_num"));
		mvo.setAddress(request.getParameter("addr1"));
		mvo.setAddress2(request.getParameter("addr2"));
		mvo.setPhone(request.getParameter("phone"));
		ms.updateMember(mvo);
//		mdao.updateMember(mvo); mdao Autowired 해도 바로 됨
		
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", mvo);
		return "redirect:/";
	}
}
