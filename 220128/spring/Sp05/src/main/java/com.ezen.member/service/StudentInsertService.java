package com.ezen.member.service;

public class StudentInsertService{
	// private StudentDao sdao = new StudentDao();
	private StudentDao sdao;
	StudentInsertService(StudentDao sdao){
		this.sdao = sdao;
	}
	
	public void insertStudent(Student std){
		sdao.insertStudent(std);
	}
}