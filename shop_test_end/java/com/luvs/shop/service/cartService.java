package com.luvs.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luvs.shop.dao.cartDao;
import com.luvs.shop.dto.CVO;

@Service
public class cartService {
	
	@Autowired
	cartDao cdao;

	public void insertCart(CVO cvo) {
		cdao.insertCart(cvo);
	}

	public List<CVO> getCartList(String mid) {
		return cdao.getCartList(mid);
	}

	public void cartDelete(int cseq) {
		cdao.cartDelete(cseq);
	}
}
