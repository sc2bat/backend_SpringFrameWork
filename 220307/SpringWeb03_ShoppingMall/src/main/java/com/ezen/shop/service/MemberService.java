package com.ezen.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.shop.dao.MemberDao;
import com.ezen.shop.dto.AddressVO;
import com.ezen.shop.dto.MemberVO;

@Service
public class MemberService {
	@Autowired
	MemberDao mdao;

	public MemberVO getMember(String id) {
		return mdao.getMember(id);
	}

	public List<AddressVO> selectAddressByDong(String dong) {	
		return mdao.selectAddressByDong(dong);
	}

	public void insertMember(MemberVO mvo) {
		mdao.insertMember(mvo);
	}

	public void updateMember(MemberVO mvo) {
		mdao.updateMember(mvo);
	}
}
