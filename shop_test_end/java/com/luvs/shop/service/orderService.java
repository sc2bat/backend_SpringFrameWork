package com.luvs.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luvs.shop.dao.OrderDao;
import com.luvs.shop.dto.CVO;
import com.luvs.shop.dto.OVO;

@Service
public class orderService {
	
	@Autowired
	OrderDao od;

	public int insertOrder(List<CVO> cartList, String mid) {
		od.insertOrder(mid);
		int oseq = od.lookupMaxOseq();
		for(CVO cvo : cartList) {
			od.insertOrderDetail(cvo, oseq);
			od.deleteCart(cvo.getCseq());
		}
		return oseq;
	}

	public List<OVO> getOrderList(int oseq) {
		return od.getOrderList(oseq);
	}

	public int orderOne(String mid, int pseq, int quantity) {
		od.insertOrder(mid);
		int oseq = od.lookupMaxOseq();
		CVO cvo = new CVO();
		cvo.setPseq(pseq);
		cvo.setQuantity(quantity);
		od.orderOne(cvo, oseq);
		return oseq;
	}


	public List<Integer> getOseqList(String mid) {
		return od.getOseqList(mid);
	}
}
