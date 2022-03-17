package com.luvs.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luvs.shop.dao.qnaDao;
import com.luvs.shop.dto.QVO;

@Service
public class qnaService {
	
	@Autowired
	qnaDao qd;

	public List<QVO> getQnaList(String mid) {
		return qd.getQnaList(mid);
	}

	public void insertQna(String mid, String subject, String content) {
		qd.insertQna(mid, subject, content);
	}

	public QVO qnaView(int qseq) {
		return qd.qnaView(qseq);
	}
}
