package com.luvs.shop.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luvs.shop.dao.adminDao;
import com.luvs.shop.dao.productDao;
import com.luvs.shop.dto.AVO;
import com.luvs.shop.dto.OVO;
import com.luvs.shop.dto.Paging;

@Service
public class adminService {
	
	@Autowired
	adminDao ad;
	@Autowired
	productDao pd;

	public AVO getAdmin(String workId) {
		return ad.getAdmin(workId);
	}

	public int adminCheck(String adminId, String adminPwd) {
		return ad.adminCheck(adminId, adminPwd);
	}

	public HashMap<String, Object> listProduct(int page, String key) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		Paging paging = new Paging();
		paging.setPage(page);
		int count = ad.getAllCount("shopproduct", "name", key);
		paging.setTotalCount(count);
//		List<PVO> list = ad.getProductList(paging, key);
//		hm.put("productList", list);
		hm.put("productList", ad.getProductList(paging, key));
		hm.put("paging", paging);
		return hm;
	}

	public HashMap<String, Object> getOrderList(int page, String key) {
		Paging paging = new Paging();
		paging.setPage(page);
		int count = ad.getAllCount("so_view", "mname", key);
		paging.setTotalCount(count);
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		List<OVO> orderList = ad.getOrderList(paging, key);
		hashMap.put("paging", paging);
		hashMap.put("orderList", orderList);
		return hashMap;
	}

	public void updateOrderResult(int odseq) {
		ad.updateOrderResult(odseq);
	}
	
	
}
