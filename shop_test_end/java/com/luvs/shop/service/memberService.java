package com.luvs.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luvs.shop.dao.memberDao;
import com.luvs.shop.dto.ADD;
import com.luvs.shop.dto.MVO;

@Service
public class memberService {

	@Autowired
	memberDao mdao;

	public MVO getMember(String id) {
		return mdao.getMember(id);
	}

	public List<ADD> getAddress(String dong) {
		return mdao.getAddress(dong);
	}

	public void signUp(MVO mvo) {
		mdao.signUp(mvo);
	}

	public void updateMember(MVO mvo) {
		mdao.updateMember(mvo);
	}
}
