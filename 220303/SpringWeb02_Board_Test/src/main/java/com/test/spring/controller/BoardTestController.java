package com.test.spring.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.test.spring.dto.BoardTestDto;
import com.test.spring.dto.ReplyTestDto;
import com.test.spring.service.BoardTestService;

@Controller
public class BoardTestController {
	
	@Autowired
	BoardTestService bts;
	@Autowired
	ServletContext context;
	
	@RequestMapping("/boardlist")
	public String main(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null) {
			return "member/loginForm";
		}else {
			ArrayList<BoardTestDto> boardlist = bts.getBoardMain();
			model.addAttribute("boardList", boardlist);
		}
		
		return "board/main";
	}
	
	@RequestMapping("/boardWriteForm")
	public String board_form(HttpServletRequest request, Model model) {
		String url = "board/boardWriteForm";
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null) {
			url = "member/loginForm";
		}
		return url;
	}
	
	@RequestMapping(value="/boardWrite", method=RequestMethod.POST)
	public String board_write(HttpServletRequest request, Model model) {
//		HttpSession session = request.getSession();
//		ServletContext context = session.getServletContext();
//		@Autowired
//		ServletContext context;
		
		String path = context.getRealPath("resources/upload");
		try {
			MultipartRequest multi = new MultipartRequest(
				request, path, 5 *1024 *1024, "UTF-8", new DefaultFileRenamePolicy()	
				);
			BoardTestDto bdto = new BoardTestDto();
			bdto.setUserid(multi.getParameter("userid"));
			bdto.setPass(multi.getParameter("pass"));
			bdto.setEmail(multi.getParameter("email"));
			bdto.setTitle(multi.getParameter("title"));
			bdto.setContent(multi.getParameter("content"));
			bdto.setImgfilename(multi.getFilesystemName("imgfilename"));
			
			bts.insertBoard(bdto);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return "redirect:/boardlist";
	}
	
	@RequestMapping("/boardView")
	public String board_view(HttpServletRequest request, Model model) {
		int num = Integer.parseInt(request.getParameter("num"));
		HashMap<String, Object> hm = bts.getBoardView(num);
		model.addAttribute("board", (BoardTestDto)hm.get("bdto"));
		model.addAttribute("replyList", (ArrayList<ReplyTestDto>)hm.get("replyList"));
		
		return "board/boardView";
	}
	
	@RequestMapping(value="/addReply", method=RequestMethod.POST)
	public String addReply(HttpServletRequest rq, Model model) {
		String boardnum = rq.getParameter("boardnum");
		ReplyTestDto rdto = new ReplyTestDto();
		rdto.setUserid(rq.getParameter("userid"));
		rdto.setContent(rq.getParameter("content"));
		rdto.setBoardnum(Integer.parseInt(boardnum));
		bts.addReply(rdto);
		return "redirect:/boardViewNoneCnt?num=" + boardnum;
	}
	
	@RequestMapping("/boardViewNoneCnt")
	public String boardViewNoneCnt(HttpServletRequest rq, Model model) {
		int num = Integer.parseInt(rq.getParameter("num"));
		HashMap<String, Object> hm = bts.getBoardViewNoneCnt(num);
		model.addAttribute("board", (BoardTestDto)hm.get("bdto"));
		model.addAttribute("replyList", (ArrayList<ReplyTestDto>)hm.get("replyList"));
		return "board/boardView";
	}
	
	@RequestMapping("/deleteReply")
	public String deleteReply(HttpServletRequest rq, Model md) {
		int rnum = Integer.parseInt(rq.getParameter("num"));
		String boardnum = rq.getParameter("boardnum");
		bts.deleteReply(rnum);
		return "redirect:/boardViewNoneCnt?num=" + boardnum;
	}

	@RequestMapping("/boardEditForm")
	public String boardEditForm(HttpServletRequest rq, Model md) {
		String boardnum = rq.getParameter("num");
		md.addAttribute("boardnum", boardnum);
		return "board/checkPassForm";
	}
	
	@RequestMapping("/boardEdit")
	public String boardEdit(HttpServletRequest rq, Model md) {
		String url = "";
		String boardnum = rq.getParameter("boardnum");
		String pass = rq.getParameter("pass");
		BoardTestDto bdto = bts.getBoardOne(Integer.parseInt(boardnum));
		md.addAttribute("boardnum", boardnum);
		if(bdto.getPass().equals(pass)) {
			url = "board/checkPass";
		}else {
			md.addAttribute("message", "password not matched");
			url = "board/checkPassForm";
		}
		return url;
	}
	
	@RequestMapping("/boardUpdateForm")
	public String boardUpdateForm(HttpServletRequest rq, Model md) {
		int num = Integer.parseInt(rq.getParameter("num"));
		md.addAttribute("board", bts.getBoardOne(num));
		return "board/boardEditForm";
	}
	
	@RequestMapping(value="/boardUpdate", method=RequestMethod.POST)
	public String boardUpdate(HttpServletRequest rq, Model md) {
		int boardnum = 0;
		try {
			MultipartRequest mt = new MultipartRequest(
				rq, context.getRealPath("resources/upload"), 5 *1024 *1024, "UTF-8", new DefaultFileRenamePolicy()
			);
			boardnum = Integer.parseInt(mt.getParameter("boardnum"));
			
			BoardTestDto bdto = new BoardTestDto();
			bdto.setNum(boardnum);
			bdto.setUserid(mt.getParameter("userid"));
			bdto.setPass(mt.getParameter("pass"));
			bdto.setName(mt.getParameter("name"));
			bdto.setEmail(mt.getParameter("email"));
			bdto.setTitle(mt.getParameter("title"));
			bdto.setContent(mt.getParameter("content"));
			if(mt.getFilesystemName("imgfilename") != null) {
				bdto.setImgfilename(mt.getFilesystemName("imgfilename"));
			}else {
				bdto.setImgfilename(mt.getFilesystemName("oldfilename"));
			}
			
			bts.boardUpdate(bdto);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/boardViewNoneCnt?num=" + boardnum;
	}
	
	@RequestMapping("/boardDeleteForm")
	public String boardDeleteForm(HttpServletRequest rq, Model md) {
		md.addAttribute("boardnum", rq.getParameter("num"));
		return "board/checkPassForm";
	}
	
	@RequestMapping("/boardDelete")
	public String boardDelete(HttpServletRequest rq, Model md) {
		bts.boardDelete(Integer.parseInt(rq.getParameter("num")));
		return "redirect:/boardlist";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
