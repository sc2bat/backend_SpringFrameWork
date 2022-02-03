package ezen.member.main;

import ezen.member.dto.Student;
import ezen.member.service.SpringContainerClass;
import ezen.member.service.StudentInsertService;
import ezen.member.service.StudentSelectOneService;
import ezen.member.service.StudentUpdateService;

public class MainClass {
	public static void main(String[] args) {
		SpringContainerClass scc = new SpringContainerClass();
		
		StudentInsertService sis = scc.getIs();
		
		/*
		Student std = new Student("H39asdfaelu42o23", "hippo" , "94875" , "barbara" , 27 , "W", "Korean Literature");
		sis.insertStudent(std);
		*/
		
		/*
		StudentSelectService sss =scc.getSs();
		
		ArrayList<Student> list = sss.selectStudent();
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println("| sNum : " + list.get(i).getsNum() + "\t");
			System.out.println("| sId : " + list.get(i).getsId() + "\t");
			System.out.println("| sPw : " + list.get(i).getsPw() + "\t");
			System.out.println("| sName : " + list.get(i).getsName() + "\t");
			System.out.println("| sAge : " + list.get(i).getsAge() + "\t");
			System.out.println("| sGender : " + list.get(i).getsGender() + "\t");
			System.out.println("| sMajor : " + list.get(i).getsMajor() + "\t");
		}
		System.out.println();
		*/
		
		StudentSelectOneService ssos = scc.getSsos();
		Student std = ssos.selectOneService("H39asdfaelu42o23");
		System.out.println("| sNum : " + std.getsNum() + "\t");
		System.out.println("| sId : " + std.getsId() + "\t");
		System.out.println("| sPw : " + std.getsPw() + "\t");
		System.out.println("| sName : " + std.getsName() + "\t");
		System.out.println("| sAge : " + std.getsAge() + "\t");
		System.out.println("| sGender : " + std.getsGender() + "\t");
		System.out.println("| sMajor : " + std.getsMajor() + "\t");
		
		System.out.println();
	// 한명의 데이터를 수정한 후에 - StudentUpdateService 클래스의 인스턴스를 얻어서 그 안에 수정 메서드를 호출합니다
		StudentUpdateService sus = scc.getSus();
		std.setsNum("H39asdfaelu42o23");
		std.setsId("Hippo");
		std.setsAge(40);
		std.setsGender("N");
		std.setsMajor("Korean Literature");
		sus.updateStudent(std);
	// 다시 출력
		
		std = ssos.selectOneService("H39asdfaelu42o23");
		System.out.println("| sNum : " + std.getsNum() + "\t");
		System.out.println("| sId : " + std.getsId() + "\t");
		System.out.println("| sPw : " + std.getsPw() + "\t");
		System.out.println("| sName : " + std.getsName() + "\t");
		System.out.println("| sAge : " + std.getsAge() + "\t");
		System.out.println("| sGender : " + std.getsGender() + "\t");
		System.out.println("| sMajor : " + std.getsMajor() + "\t");
		
		
		
	}
}
