package com.ezen.board.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.board.dao.BoardDao;
import com.ezen.board.dto.BoardDto;
import com.ezen.board.dto.Paging;
import com.ezen.board.dto.ReplyVO;

@Service
public class BoardService {

	@Autowired
	BoardDao bdao;

	/* 220304
	public ArrayList<BoardDto> getBoardsMain() {
		ArrayList<BoardDto> list = bdao.getBoardsMain();
		return list;
	}*/

	public HashMap<String, Object> getBoardsMain(int page) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		// 1 페이징 처리
		Paging paging = new Paging();
		paging.setPage(page);
		int count = bdao.getAllCount(); // 총 게시물 갯수 count
		paging.setTotalCount(count); // paging 객체의 각 변수 값 계산
		resultMap.put("paging", paging);
		
		// 2 paging 객체에 의한 게시물 조회
		ArrayList<BoardDto> list = bdao.getBoardsMain(paging);
		resultMap.put("boardList", list);
		
		// 3 댓글 갯수 조회
		for(BoardDto bdto : list) {
			int cnt = bdao.replyCount(bdto.getNum()); // 게시물 번호로 댓글 갯수를 카운트합니다
			bdto.setReplycnt(cnt);
		}
		
		return resultMap;
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

	public void deleteReply(int num) {
		bdao.deleteReply(num);
	}

	public BoardDto getBoardOne(int num) {
		BoardDto bdto = bdao.getBoardOne(num);
		return bdto;
	}

	public void boardUpdate(BoardDto bdto) {
		bdao.boardUpdate(bdto);
		
	}

	public void boardDelete(int num) {
		bdao.boardDelete(num);
	}
}
















