package ezen.member.service;

import java.util.ArrayList;

import ezen.member.dao.StudentDao;
import ezen.member.dto.Student;

public class StudentSelectService {
	private StudentDao sdao;
	
	StudentSelectService(StudentDao sdao){
		this.sdao = sdao;
	}
	
	public ArrayList<Student> selectStudent(){
		ArrayList<Student> list = new ArrayList<Student>();
		list = sdao.selectStudent();
		return list;
	}
}
