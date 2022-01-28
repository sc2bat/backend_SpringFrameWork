package com.ezen.member.service;

public class StudentSelectOneService{
	private StudentDao sdao;
	
	StudentSelectOneService(StudentDao sdao){
		this.sdao = sdao;
	}
	
	public Student selectOneService(String snum){
		Student std = sdao.selectOneService(snum);
		return std;
	}
}