package com.ezen.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.board.dto.MemberDto;
import com.ezen.board.service.MemberService;

@Controller
public class MemberController {
	
	// 컨테이너활용 MemberService 에 @Service 를 넣어서 자동주입
	@Autowired
	MemberService ms;
	
	
//	@RequestMapping(value="/")
	@RequestMapping("/") // method 작성되지 않는다면 value 생략가능
	public String loginForm() {
		return "member/loginForm";
	}
	
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request) {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		/* 이전방식
		MemberDao mdao = new MemberDao();
		mdao.asdfasdf();
		*/
		
		// service 를 거쳐가는 방식
		// MemberService ms = new MemberService();
		
		/*컨테이너 활용
		@Autowired
		MemberService ms;*/
		
		MemberDto mdto = ms.getMember(id);
		
		return "board/main.jsp";
	}
}
