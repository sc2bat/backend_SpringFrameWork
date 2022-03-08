package com.ezen.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.shop.dto.MemberVO;
import com.ezen.shop.dto.QnaVO;
import com.ezen.shop.service.QnaService;

@Controller
public class QnaController {
	@Autowired
	QnaService qs;
	
	@RequestMapping("/qnaList")
	public ModelAndView qna_list(HttpServletRequest rq, Model md) {
		ModelAndView mav = new ModelAndView();
		MemberVO mvo = (MemberVO)rq.getSession().getAttribute("loginUser");
		if(mvo == null) {
			mav.setViewName("member/login");
		}else {
			mav.addObject("qnaList", qs.listQna(mvo.getUserid()));
			mav.setViewName("qna/qnaList");
		}
		return mav;
	}
	
	@RequestMapping("/qnaWrite")
	public ModelAndView qnaWrite(HttpServletRequest rq, @RequestParam("subject")String subject, @RequestParam("content")String content) {
		ModelAndView mav = new ModelAndView();
		MemberVO mvo = (MemberVO)rq.getSession().getAttribute("loginUser");
		if(mvo == null) {
			mav.setViewName("member/login");
		}else {
			QnaVO qvo = new QnaVO();
			qvo.setSubject(subject);
			qvo.setContent(content);
			qs.insertQna(qvo, mvo.getUserid());
			mav.setViewName("redirect:/qnaList");
		}
		
		return mav;
	}
	
	@RequestMapping("/qnaView")
	public ModelAndView qnaView(HttpServletRequest rq, @RequestParam("qseq")int qseq) {
		ModelAndView mav = new ModelAndView();
		MemberVO mvo = (MemberVO)rq.getSession().getAttribute("loginUser");
		if(mvo == null) {
			mav.setViewName("member/login");
		}else {
			mav.addObject("qnaVO", qs.getQna(qseq));
			mav.setViewName("qna/qnaView");
		}
		return mav;
	}
}
