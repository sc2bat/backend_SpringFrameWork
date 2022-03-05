package com.test.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.spring.dto.MemberTestDto;
import com.test.spring.service.MemberTestService;

@Controller
public class MemberTestController {
	
	@Autowired
	MemberTestService mts;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String firstRequest(HttpServletRequest request, Model model) {
		String url = "";
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") != null) {
			url = "redirect:/boardlist";
		}else {
			url = "member/loginForm";
		}
		return url;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String url = "member/loginForm";
		
		MemberTestDto mdto = mts.getMember(id);
		
		if(mdto == null) {
			model.addAttribute("message", "아이디가 없습니다");
		}else if(mdto.getPass() == null) {
			model.addAttribute("message", "비밀번호 없음 db 오류");
		}else if(!mdto.getPass().equals(pw)) {
			model.addAttribute("message", "비밀번호 다름");
		}else if(mdto.getPass().equals(pw)) {
			url = "redirect:/boardlist";
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mdto);
		}else {
			model.addAttribute("message", "아무튼 로그인 실패");
		}
		
		return url;
	}
	
	@RequestMapping("/memberJoinForm")
	public String memberJoinForm() {
		return "member/memberJoinForm";
	}
	
	@RequestMapping("/idCheck")
	public String idCheck(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		MemberTestDto mdto = mts.getMember(id);
		if(mdto == null) {
			model.addAttribute("result", -1);
		}else {
			model.addAttribute("result", 1);
		}
		model.addAttribute("id", id);
		return "member/idCheck";
	}
	
//	@RequestMapping("/memberJoin")
	@RequestMapping(value="/memberJoin", method=RequestMethod.POST)
	public String memberJoin(HttpServletRequest request, Model model) {
		MemberTestDto mdto = new MemberTestDto();
		mdto.setUserid(request.getParameter("id"));
		mdto.setPass(request.getParameter("pw"));
		mdto.setName(request.getParameter("name"));
		mdto.setPhone(request.getParameter("phone"));
		mdto.setEmail(request.getParameter("email"));
		int result = mts.insertMember(mdto);
		
		if(result == 1) {
			model.addAttribute("message", "join success");
		}else {
			model.addAttribute("message", "join failed please retry");
		}
		return "member/loginForm";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		session.invalidate();
//		return "member/loginForm";
		return "redirect:/";
	}
	
	@RequestMapping("/memberEditForm")
	public String memberEditForm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
//		MemberTestDto mdto = (MemberTestDto)session.getAttribute("loginUser");
		if(session.getAttribute("loginUser") == null) {
			return "member/loginForm";
		}
		return "member/memberEditForm";
	}
	
	@RequestMapping(value="/memberEdit", method=RequestMethod.POST)
	public String memberEdit(HttpServletRequest request, Model model) {
		MemberTestDto mdto = new MemberTestDto();
		mdto.setUserid(request.getParameter("id"));
		mdto.setPass(request.getParameter("pw"));
		mdto.setName(request.getParameter("name"));
		mdto.setEmail(request.getParameter("email"));
		mdto.setPhone(request.getParameter("phone"));
		
		int result = mts.updateMember(mdto);
		
		HttpSession session = request.getSession();
		if(result == 1) {
			session.setAttribute("loginUser", mdto);
		}
		
		return "redirect:/boardlist";
	}
	
	
	
	
	
}
