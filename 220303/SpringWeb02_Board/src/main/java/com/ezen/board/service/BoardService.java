package com.ezen.board.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.board.dao.BoardDao;
import com.ezen.board.dto.BoardDto;
import com.ezen.board.dto.ReplyVO;

@Service
public class BoardService {

	@Autowired
	BoardDao bdao;

	public ArrayList<BoardDto> getBoardsMain() {
		ArrayList<BoardDto> list = bdao.getBoardsMain();
		return list;
	}

	public void insertBoard(BoardDto bdto) {
		bdao.insert(bdto);
		
	}

	/*
	public BoardDto boardView(int num) {
		// 게시물조회
		BoardDto bdto = bdao.boardView(num);
		
		// 조회수 증가
		bdao.plusReadCount(num);
		
		return bdto;
	}

	public ArrayList<ReplyVO> getReplysOne(int num) {
		ArrayList<ReplyVO> list = bdao.getReply(num);
		return list;
	}
	*/
	
	public HashMap<String, Object> boardView(int num) {
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		// 게시물조회
		BoardDto bdto = bdao.boardView(num);
		paramMap.put("bdto", bdto);
		
		// 조회수 증가
		bdao.plusReadCount(num);
		
		// 댓글 조회
		ArrayList<ReplyVO> list = bdao.getReply(num);
		paramMap.put("replylist", list);
		
		return paramMap;
	}

	public void addReply(ReplyVO rvo) {
		bdao.addReply(rvo);
	}

	public HashMap<String, Object> boardViewWithoutCount(int num) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		// 게시물조회
		BoardDto bdto = bdao.boardView(num);
		paramMap.put("bdto", bdto);
		
		// 댓글 조회
		ArrayList<ReplyVO> list = bdao.getReply(num);
		paramMap.put("replylist", list);
		
		return paramMap;
	}
}
















