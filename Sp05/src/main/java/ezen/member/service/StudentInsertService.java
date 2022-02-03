package ezen.member.service;

import ezen.member.dao.StudentDao;
import ezen.member.dto.Student;

public class StudentInsertService {
	private StudentDao sdao;
	
	StudentInsertService(StudentDao sdao){
		this.sdao = sdao;
	}
	
	public void insertStudent(Student std) {
		sdao.insertStudent(std);
	}
}
