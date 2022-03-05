package com.test.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.spring.dao.MemberTestDao;
import com.test.spring.dto.MemberTestDto;


@Service
public class MemberTestService {
	
	@Autowired
	MemberTestDao mdao;
	
	public MemberTestDto getMember(String id) {
		MemberTestDto mdto = mdao.getMember(id);
		return mdto;
	}

	public int insertMember(MemberTestDto mdto) {
		int result = mdao.insertMember(mdto);
		return result;
	}

	public int updateMember(MemberTestDto mdto) {
		return mdao.updateMember(mdto);
	}

}
