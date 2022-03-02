package com.ezen.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.board.dto.MemberDto;
import com.ezen.board.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService ms;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String firstRequest(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		String url = "";
		
		if(session.getAttribute("loginUser") != null) {
//			url = "board/main";
			url = "redirect:/board/main";
		}else {
			url = "member/loginForm";
		}
		
//		return "loginForm";
		return url;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String url = "member/loginForm";
		
		MemberDto mdto = ms.getMember(id); // id를 이용해 조회하고, 회원의 모든 정보를 dto 형태로 저장
		
		if(mdto == null) { // 조회한 아이디가 없는 경우
			model.addAttribute("message", "아이디가 없습니다");
		}else if(mdto.getPw() == null) { // db 오류
			model.addAttribute("message", "db 오류 비밀번호가 없습니다");
		}else if(mdto.getPw().equals(pw)) { // 정상로그인
//			url = "board/main";
			url = "redirect:/boardList";
			// "redirect:/리퀘스트 이름 -> 리퀘스트이름에 해당하는 매핑으로 이동
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mdto);
			
		}else if(!mdto.getPw().equals(pw)) { // 비밀번호 다름
			model.addAttribute("message", "비밀번호가 다릅니다");
		}else { // 알수없는 이유로 로그인 불가
			model.addAttribute("message", "알 수 없는 이유로 로그인 불가");
		}
		
//		return "main";
		return url;
	}
}
