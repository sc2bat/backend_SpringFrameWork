package com.luvs.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luvs.shop.dao.productDao;
import com.luvs.shop.dto.PVO;

@Service
public class productService {
	
	@Autowired
	productDao pdao;

	public List<PVO> getNewList() {
		return pdao.getNewList();
	}

	public List<PVO> getBestList() {
		return pdao.getBestList();
	}

	public Object productKindList(String kind) {
		return pdao.getProductKindList(kind);
	}

	public Object getProduct(int pseq) {
		return pdao.getProduct(pseq);
	}
}
