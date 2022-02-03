package ezen.member.service;

import ezen.member.dao.StudentDao;
import ezen.member.dto.Student;

public class StudentUpdateService {
	
	StudentDao sdao;
	
	public StudentUpdateService(StudentDao sdao){
		this.sdao = sdao;
	}
	
	public void updateStudent(Student std) {
		sdao.updateStudent(std);
	}
}
