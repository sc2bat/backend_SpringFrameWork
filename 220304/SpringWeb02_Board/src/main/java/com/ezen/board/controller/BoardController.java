package com.ezen.board.controller;

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

import com.ezen.board.dto.BoardDto;
import com.ezen.board.dto.Paging;
import com.ezen.board.dto.ReplyVO;
import com.ezen.board.service.BoardService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class BoardController {

	@Autowired
	BoardService bs;

	@Autowired
	ServletContext context;
	
	@RequestMapping("/boardList")
	public String main( HttpServletRequest request , Model model) {
		
		HttpSession session = request.getSession();
		if( session.getAttribute("loginUser") == null)	
			return "member/loginform";
		else {

//			ArrayList<BoardDto> list = bs.getBoardsMain();
//			model.addAttribute("boardList", list);

			//220304
//			HashMap<String, Object> resultMap = new HashMap<String, Object>();
//			resultMap = bs.getBoardsMain();
			
			int page = 1;
//			if(request.getParameter("page") != null) {
//				page = Integer.parseInt(request.getParameter("page"));
//			}
			if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			}else if(session.getAttribute("page") != null) {
				page = (int)session.getAttribute("page");
			}else {
				session.removeAttribute("page");
			}
			
			HashMap<String, Object> resultMap = bs.getBoardsMain(page);
			
			ArrayList<BoardDto> list = (ArrayList<BoardDto>)resultMap.get("boardList");
			
			Paging paging = (Paging)resultMap.get("paging");
			
			model.addAttribute("boardList", list);
			model.addAttribute("paging", paging);
		}		
		return "board/main";
	}
	
	@RequestMapping("/boardWriteForm")
	public String write_form(Model model, HttpServletRequest request) {
		String url = "board/boardWriteForm";
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null) {
			url = "loginForm";
		}
		return url;
	}
	
	
	@RequestMapping(value="boardWrite", method=RequestMethod.POST)
	public String board_write(Model model, HttpServletRequest request) {
		
//		HttpSession session = request.getSession();
//		ServletContext context = session.getServletContext();
		
		String path = context.getRealPath("resources/upload");
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request, path, 5 *1024 *1024, "UTF-8", new DefaultFileRenamePolicy());
			BoardDto bdto = new BoardDto();
			bdto.setUserid(multi.getParameter("userid"));
			bdto.setPass(multi.getParameter("pass"));
			bdto.setEmail(multi.getParameter("email"));
			bdto.setTitle(multi.getParameter("title"));
			bdto.setContent(multi.getParameter("content"));
			bdto.setImgfilename(multi.getFilesystemName("imgfilename"));
			
			bs.insertBoard(bdto);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/boardList";
	}
	
	
	@RequestMapping("/boardView")
	public String boardView(Model model, HttpServletRequest request) {
		
		// 보여줄 게시물 번호
		int num = Integer.parseInt(request.getParameter("num"));
		/*1.
		BoardDto bdto = bs.boardView(num);
		model.addAttribute("board", bdto);
		
		ArrayList<ReplyVO> list = bs.getReplysOne(num);
		model.addAttribute("replyList", list);
		*/
		
		// 
		/*
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.compute("bdto", null);
		paramMap.compute("replylist", null);
		
//		bs.boardView(paramMap, num);
		
		paramMap.put("num", num);
		bs.boardView(paramMap);
		*/
		// 2.
		HashMap<String, Object> paramMap = bs.boardView(num);
		
//		BoardDto bdto = (BoardDto)paramMap.get("bdto");
//		ArrayList<ReplyVO> list = (ArrayList<ReplyVO>)paramMap.get("replylist");
//		
//		model.addAttribute("board", bdto);
//		model.addAttribute("replyList", list);
		model.addAttribute("board", (BoardDto)paramMap.get("bdto"));
		model.addAttribute("replyList", (ArrayList<ReplyVO>)paramMap.get("replylist"));
		
		return "board/boardView";
	}
	
	@RequestMapping(value="/addReply", method=RequestMethod.POST)
	public String add_reply(Model model, HttpServletRequest request) {
		String boardnum = request.getParameter("boardnum");
		ReplyVO rvo = new ReplyVO();
		
		rvo.setUserid(request.getParameter("userid"));
		rvo.setContent(request.getParameter("content"));
		rvo.setBoardnum(Integer.parseInt(boardnum));
		
		bs.addReply(rvo);
		return "redirect:/boardViewWithoutCount?num=" + boardnum;
	}
	
	@RequestMapping("/boardViewWithoutCount")
	public String boardViewWithoutCount(Model model, HttpServletRequest request) {
		
		// 보여줄 게시물 번호
		int num = Integer.parseInt(request.getParameter("num"));
		HashMap<String, Object> paramMap = bs.boardViewWithoutCount(num);
		model.addAttribute("board", (BoardDto)paramMap.get("bdto"));
		model.addAttribute("replyList", (ArrayList<ReplyVO>)paramMap.get("replylist"));
		
		return "board/boardView";
	}
	
	
	@RequestMapping("/deleteReply")
	public String reply_delete(Model model, HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		String boardnum = request.getParameter("boardnum");
		
		bs.deleteReply(num);
		
		return "redirect:/boardViewWithoutCount?num=" + boardnum;
	}
	
	@RequestMapping("/boardEditForm")
	public String board_edit_form(HttpServletRequest request, Model model) {
		String num = request.getParameter("num");
		model.addAttribute("num", num);
		
		return "board/boardCheckPassForm";
	}
	
	@RequestMapping("/boardEdit")
	public String board_edit(HttpServletRequest request, Model model) {
		String num = request.getParameter("num");
		String pass= request.getParameter("pass");
		
		BoardDto bdto = bs.getBoardOne(Integer.parseInt(num));
		
		model.addAttribute("num", num);
		
		if(bdto.getPass().equals(pass)) {
			return "board/boardCheckPass";
		}else {
			model.addAttribute("message", "비밀번호가 맞지 않습니다. 확인해주세요");
			return "board/boardCheckPassForm";
		}
	}
	
	@RequestMapping("boardUpdateForm")
	public String board_update_form(HttpServletRequest request, Model model) {
		String num = request.getParameter("num");
		
		BoardDto bdto = bs.getBoardOne(Integer.parseInt(num));
		model.addAttribute("num", num);
		model.addAttribute("board", bdto);
		
		return "board/boardEditForm";
	}
	
	@RequestMapping(value="boardUpdate", method=RequestMethod.POST)
	public String board_update(HttpServletRequest request, Model model) {
		String path = context.getRealPath("resources/upload");
		
		int num = 0;
		
		try {
			MultipartRequest multi = new MultipartRequest(request, path, 5 *1024 *1024, "UTF-8", new DefaultFileRenamePolicy());
			num = Integer.parseInt(multi.getParameter("num"));
			BoardDto bdto = new BoardDto();
			bdto.setNum(num);
			bdto.setPass(multi.getParameter("pass"));
			bdto.setUserid(multi.getParameter("userid"));
			bdto.setEmail(multi.getParameter("email"));
			bdto.setTitle(multi.getParameter("title"));
			bdto.setContent(multi.getParameter("content"));
			String imgfilename = multi.getFilesystemName("imgfilename"); // 수정하고자 하는 파일이름
			if(imgfilename == null) {
				imgfilename = multi.getFilesystemName("oldfilename");
			}
			bdto.setImgfilename(imgfilename);
			
			bs.boardUpdate(bdto);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/boardViewWithoutCount?num=" + num;
	}
	
	@RequestMapping("/boardDeleteForm")
	public String board_delete_form(HttpServletRequest request, Model model) {
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDto bdto = bs.getBoardOne(num);
		model.addAttribute("num", num);
		model.addAttribute("board", bdto);
		
		return "board/boardCheckPassForm";
	}
	
	@RequestMapping("/boardDelete")
	public String board_delete(HttpServletRequest request, Model model) {
		int num = Integer.parseInt(request.getParameter("num"));
		bs.boardDelete(num);
		return "redirect:/boardList";
	}
}











