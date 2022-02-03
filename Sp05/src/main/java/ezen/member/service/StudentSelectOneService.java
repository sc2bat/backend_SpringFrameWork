package ezen.member.service;

import ezen.member.dao.StudentDao;
import ezen.member.dto.Student;

public class StudentSelectOneService {
	private StudentDao sdao;
	
	public StudentSelectOneService(StudentDao sdao) {
		this.sdao = sdao;
	}
	
	public Student selectOneService(String snum) {
		Student std = sdao.selectOneService(snum);
		return std;
	}
}
