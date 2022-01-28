package com.ezen.member.service;

import com.ezen.member.dao.StudentDao;

public class StudentInsertService{
	// private StudentDao sdao = new StudentDao();
	private StudentDao sdao;
	StudentSelectService(StudentDao sdao){
		this.sdao = sdao;
	}
	
	public ArrayList<Student> selectStudent
		ArrayList<Student> list = new ArrayList<Student>();
		list = sdao.selectStudent();
		
		return list;
	}
}