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
	public String firstRequest( HttpServletRequest request , Model model ) {
		
		HttpSession session = request.getSession();
		String url = "";
		if( session.getAttribute("loginUser") != null )
			url = "redirect:/boardList";
		else 
			url = "member/loginForm";
		
		return url;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login( HttpServletRequest request, Model model ) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String url = "member/loginForm";
		
		MemberDto mdto = ms.getMember(id);  // id 瑜� �씠�슜�븯�뿬 �쉶�썝 議고쉶�븯怨� , �쉶�썝�쓽 紐⑤뱺�젙蹂대��  dto �삎�깭濡� 由ы꽩諛쏆뒿�땲�떎
		
		if( mdto == null ) {  // 議고쉶�븳 �븘�씠�뵒媛� �뾾�뒗 寃쎌슦
			model.addAttribute("message" , "�븘�씠�뵒媛� �뾾�뒿�땲�떎");
		} else if( mdto.getPw() == null ) { // DB �삤瑜�
			model.addAttribute("message" , "DB �삤瑜�, 愿�由ъ옄�뿉寃� 臾몄쓽�븯�꽭�슂");
		} else if( mdto.getPw().equals( pw) ) { // �젙�긽濡쒓렇�씤
			url = "redirect:/boardList";
			// "redirect:/由ы�섏뒪�듃�씠由�   -> 由ы�섏뒪�듃�씠由꾩뿉 �빐�떦�븯�뒗 留ㅽ븨�쑝濡� �씠�룞 
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mdto);
		} else if( !mdto.getPw().equals( pw) ) { // 鍮꾨쾲 ��由�
			model.addAttribute("message" , "鍮꾨�踰덊샇媛� ���졇�뒿�땲�떎");
		} else {  // �븣�닔 �뾾�뒗 �씠�쑀濡� 濡쒓렇�씤 遺덇�
			model.addAttribute("message" , "�븣�닔�뾾�뒗 �씠�쑀濡� 濡쒓렇�씤�씠 �븞�맗�땲�떎.");
		}
		
		return url;
	}
	

	
	@RequestMapping("/memberJoinForm")
	public String memberJoinForm() {
		return "member/memberJoinForm";
	}
	
	@RequestMapping("/idcheck")
	public String idcheck(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		
		MemberDto mdto = ms.getMember(id);
		if(mdto == null) {
			model.addAttribute("result", -1); // 사용 불가
		}else {
			model.addAttribute("result", 1); // 사용가능
		}
		model.addAttribute("id", id);
		
		return "member/idcheck";
	}
	
	@RequestMapping(value="/memberJoin", method=RequestMethod.POST)
	public String memberJoin(HttpServletRequest request, Model model) {
		MemberDto mdto = new MemberDto();
		mdto.setUserid(request.getParameter("id"));
		mdto.setPw(request.getParameter("pw"));
		mdto.setName(request.getParameter("name"));
		mdto.setPhone(request.getParameter("phone"));
		mdto.setEmail(request.getParameter("email"));
		
		int result = ms.insertMember(mdto);
		
		if(result == 1)model.addAttribute("message", "회원가입 성공, 로그인하세요");
		else model.addAttribute("message", "회원가입 실패. 다시 시도하세요");
		
		return "member/loginForm";
	}
	
	@RequestMapping("/memberEditForm")
	public String memberEditForm(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null) {
			return "loginForm";
		}
		return "member/memberEditForm";
	}
	
	@RequestMapping(value="/memberEdit", method=RequestMethod.POST)
	public String memberEdit(Model model, HttpServletRequest request) {
		MemberDto mdto = new MemberDto();
		mdto.setUserid(request.getParameter("id"));
		mdto.setPw(request.getParameter("pw"));
		mdto.setName(request.getParameter("name"));
		mdto.setPhone(request.getParameter("phone"));
		mdto.setEmail(request.getParameter("email"));
		
		int result = ms.updateMember(mdto);
		
		HttpSession session = request.getSession();
		if(result == 1)session.setAttribute("loginUser", mdto);
		
		// redirect:/boardList 으로 리턴
		return "redirect:/boardList";
	}
	
	@RequestMapping("/logout")
	public String logout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		session.invalidate();
		//session.removeAttribute("loginUser");
		
		//return "member/loginForm";
		return "redirect:/";
	}
	
	
}








