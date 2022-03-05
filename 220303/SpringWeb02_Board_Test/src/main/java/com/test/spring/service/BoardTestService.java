package com.test.spring.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.spring.dao.BoardTestDao;
import com.test.spring.dto.BoardTestDto;
import com.test.spring.dto.ReplyTestDto;

@Service
public class BoardTestService {
	
	@Autowired
	BoardTestDao bdao;

	public ArrayList<BoardTestDto> getBoardMain() {
		ArrayList<BoardTestDto> boardlist = bdao.getBoardMain();
		return boardlist;
	}

	public void insertBoard(BoardTestDto bdto) {
		bdao.insertBoard(bdto);
	}

	public HashMap<String, Object> getBoardView(int num) {
//		HashMap<String, Object> hm = new HashMap<String, Object>();
//		BoardTestDto bdto = bdao.getBoardOne(num);
//		bdao.plusReadCount(num);
//		ArrayList<ReplyTestDto> rlist = bdao.getReplyList(num);
//		hm.put("board", bdto);
//		hm.put("replyList", rlist);
		
		bdao.plusReadCount(num);
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("bdto", bdao.getBoardView(num));
		hm.put("replyList", bdao.getReplyList(num));
		return hm;
	}

	public void addReply(ReplyTestDto rdto) {
		bdao.addReply(rdto);
	}

	public HashMap<String, Object> getBoardViewNoneCnt(int num) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("bdto", bdao.getBoardView(num));
		hm.put("replyList", bdao.getReplyList(num));
		return hm;
	}

	public void deleteReply(int rnum) {
		bdao.deleteReply(rnum);
	}

	public BoardTestDto getBoardOne(int boardnum) {	
		return bdao.getBoardOne(boardnum);
	}

	public void boardUpdate(BoardTestDto bdto) {
		bdao.boardUpdate(bdto);
	}

	public void boardDelete(int boardnum) {
		bdao.boardDelete(boardnum);
	}
	
}
