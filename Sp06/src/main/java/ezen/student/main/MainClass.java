package ezen.student.main;

import java.util.Scanner;

import org.springframework.context.support.GenericXmlApplicationContext;

import ezen.student.dto.EMSInformationService;
import ezen.student.dto.Student;
import ezen.student.service.SelectAllService;
import ezen.student.service.SelectOneService;
import ezen.student.service.UpdateService;

public class MainClass {
	
	public static void main(String[] args) {
		String[] sNums= {
				"H39asdfaelu42o23", 
				"H39iiemamca8w9h4", 
				"H39lkmn754fghia7", 
				"H39plo865cuy8k92", 
				"H39mnbviiaed89q1", 
				"H399omjjyv56t3d5", 
				"H39lczaqwg644gj8", 
				"H39ymbcsh74thgh2", 
				"H39lesvj7544vf89"
				};
		String[] sIds= {"hippo", "raccoon", "elephant", "lion", "tiger", "pig", "horse", "bird", "deer"};
		String[] sPws= {"94875", "15284", "48765", "28661", "60915", "30028", "29801", "28645", "28465"};
		String[] sNames= {"barbara", "chris", "doris", "elva", "fiona", "holly", "jasmin", "lena", "melissa"};
		int[] sAges= {22, 20, 27, 19, 21, 19, 25, 22, 24};
		String[] sGenders= {"W", "W", "M", "M", "M", "W", "M", "W", "W"};
		String[] sMajors= {"Korean Literature", "French Literature", "Philosophy", "History", "Law", "Statistics", "Computer", "Economics", "Public Admin"};
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		/*
		InsertService is = ctx.getBean("insertService", InsertService.class);
		
		for(int i = 0; i < sNums.length; i++) {
			Student std = new Student(sNums[i], sIds[i], sPws[i], sNames[i], sAges[i], sGenders[i], sMajors[i]);
			is.insertStudent(std);
		}
		*/
		
		SelectAllService sas = ctx.getBean("selectAllService", SelectAllService.class);
		// 모든 학생 조회
		/*
		ArrayList<Student> list = sas.selectAllStudent();
		for(int i = 0; i < list.size(); i++) {
			System.out.println(i + "번");
			System.out.println("| sNum : " + list.get(i).getsNum() + "\t");
			System.out.println("| sId : " + list.get(i).getsId() + "\t");
			System.out.println("| sPw : " + list.get(i).getsPw() + "\t");
			System.out.println("| sName : " + list.get(i).getsName() + "\t");
			System.out.println("| sAge : " + list.get(i).getsAge() + "\t");
			System.out.println("| sGender : " + list.get(i).getsGender() + "\t");
			System.out.println("| sMajor : " + list.get(i).getsMajor() + "\t");
			System.out.println();
		}
		*/
		
		
		/*
		System.out.printf("조회할 학생의 아이디를 입력하세요");	
		Scanner sc = new Scanner(System.in);
		String ids = sc.nextLine();
		
		SelectOneService sos = ctx.getBean("selectOneService", SelectOneService.class);
		Student std = sos.selectOneStudent(ids);
		
		System.out.println(ids + "조회");
		System.out.print("| sNum : " + std.getsNum() + "\t");
		System.out.print("| sId : " + std.getsId() + "\t");
		System.out.print("| sPw : " + std.getsPw() + "\t");
		System.out.print("| sName : " + std.getsName() + "\n");
		System.out.print("| sAge : " + std.getsAge() + "\t");
		System.out.print("| sGender : " + std.getsGender() + "\t");
		System.out.print("| sMajor : " + std.getsMajor() + "\t");
		System.out.println();
		*/
		
		/*
		System.out.printf("수정할 학생의 아이디를 입력하세요 : ");
		ids = sc.nextLine();
		std = sos.selectOneStudent(ids);
		if(std == null) {
			System.out.println("조회한 " + ids + " 의 학생이 없습니다");
			return;
		}
		
		System.out.printf("기존 비밀번호 : %s - 수정할 비밀번호를 입력하세요 : ", std.getsPw());
		String pw = sc.nextLine();
		if(!pw.equals("")) {
			std.setsPw(pw);
		}

		System.out.printf("기존 이름 : %s - 수정할 이름 입력하세요 : ", std.getsName());
		String name = sc.nextLine();
		if(!name.equals("")) {
			std.setsName(name);
		}
		
		System.out.printf("기존 나이 : %d - 수정할 나이 입력하세요 : ", std.getsAge());
		String age = sc.nextLine();
		if(!age.equals("")) {
			std.setsAge(Integer.parseInt(age));
		}
		
		System.out.printf("기존 성별 : %s - 수정할 성별 입력하세요 : ", std.getsGender());
		String gender = sc.nextLine();
		if(!gender.equals("")) {
			std.setsGender(gender);
		}
		
		System.out.printf("기존 전공 : %s - 수정할 전공 입력하세요 : ", std.getsMajor());
		String major = sc.nextLine();
		if(!major.equals("")) {
			std.setsMajor(major);
		}
		
		UpdateService us = ctx.getBean("updateService", UpdateService.class);
		us.updateStudent(std);
		
		System.out.println("수정완료 결과");
		Student ustd = sos.selectOneStudent(ids);
		System.out.println(ids + "조회");
		System.out.print("| sNum : " + ustd.getsNum() + "\t");
		System.out.print("| sId : " + ustd.getsId() + "\t");
		System.out.print("| sPw : " + ustd.getsPw() + "\t");
		System.out.print("| sName : " + ustd.getsName() + "\n");
		System.out.print("| sAge : " + ustd.getsAge() + "\t");
		System.out.print("| sGender : " + ustd.getsGender() + "\t");
		System.out.print("| sMajor : " + ustd.getsMajor() + "\t");
		System.out.println();
		
		*/
		
		EMSInformationService eis = ctx.getBean("informationService", EMSInformationService.class);
		eis.outputEMSInformation();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
