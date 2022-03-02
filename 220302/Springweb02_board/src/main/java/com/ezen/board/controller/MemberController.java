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
		
		MemberDto mdto = ms.getMember(id); // id�� �̿��� ��ȸ�ϰ�, ȸ���� ��� ������ dto ���·� ����
		
		if(mdto == null) { // ��ȸ�� ���̵� ���� ���
			model.addAttribute("message", "���̵� �����ϴ�");
		}else if(mdto.getPw() == null) { // db ����
			model.addAttribute("message", "db ���� ��й�ȣ�� �����ϴ�");
		}else if(mdto.getPw().equals(pw)) { // ����α���
//			url = "board/main";
			url = "redirect:/boardList";
			// "redirect:/������Ʈ �̸� -> ������Ʈ�̸��� �ش��ϴ� �������� �̵�
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mdto);
			
		}else if(!mdto.getPw().equals(pw)) { // ��й�ȣ �ٸ�
			model.addAttribute("message", "��й�ȣ�� �ٸ��ϴ�");
		}else { // �˼����� ������ �α��� �Ұ�
			model.addAttribute("message", "�� �� ���� ������ �α��� �Ұ�");
		}
		
//		return "main";
		return url;
	}
}
