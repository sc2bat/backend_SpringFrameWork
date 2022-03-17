package com.luvs.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.luvs.shop.dto.MVO;
import com.luvs.shop.dto.QVO;
import com.luvs.shop.service.qnaService;

@Controller
public class qnaController {
	
	private ModelAndView mv;
	
	@Autowired
	qnaService qs;
	
	@RequestMapping("/qnaList")
	public String qnaList(HttpServletRequest rq, Model md) {
		MVO mvo = (MVO)rq.getSession().getAttribute("loginUser");
		if(mvo == null) {
			return "member/loginForm";
		}else {
			List<QVO> qnaList = qs.getQnaList(mvo.getMid());
			md.addAttribute("qnaList", qnaList);
			return "qna/qnaList";
		}
	}
	
	@RequestMapping("/qnaWriteForm")
	public String qnaWriteForm(HttpServletRequest rq) {
		MVO mvo = (MVO)rq.getSession().getAttribute("loginUser");
		if(mvo == null) {
			return "member/loginForm";
		}else {
			return "qna/qnaWrite";
		}
	}
	
	@RequestMapping("/qnaWrite")
	public ModelAndView qnaWrite(HttpServletRequest rq, 
			@RequestParam("subject")String subject, @RequestParam("content")String content) {
		mv = new ModelAndView();
		MVO mvo = (MVO)rq.getSession().getAttribute("loginUser");
		if(mvo == null) {
			mv.setViewName("member/loginForm");
		}else {
			qs.insertQna(mvo.getMid(), subject, content);
			mv.setViewName("redirect:/qnaList");
		}
		return mv;
	}
	
	@RequestMapping("/qnaView")
	public ModelAndView qnaView(HttpServletRequest rq, @RequestParam("qseq")int qseq) {
		mv = new ModelAndView();
		MVO mvo = (MVO)rq.getSession().getAttribute("loginUser");
		if(mvo == null) {
			mv.setViewName("member/loginForm");
		}else {
			mv.addObject("qnaVO", qs.qnaView(qseq));
			mv.setViewName("qna/qnaView");
		}
		return mv;
	}
	
	
	
}
