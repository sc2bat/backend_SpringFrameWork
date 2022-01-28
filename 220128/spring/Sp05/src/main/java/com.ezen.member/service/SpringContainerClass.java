package com.ezen.member.service;

public class SpringContainerClass{
	
	private StudentDao sdao;
	
	private StudentSelectService ss;
	private StudentInsertService is;

	private StudentSelectOneService ssos;
	
	public SpringContainerClass(){ //new 인스턴스를 생성하는 생성자
		sdao = new StudentDao();
	
		// ss = new StudentSelectService();
		// is = new StudentInsertService();
		ss = new StudentSelectService(sdao);
		is = new StudentInsertService(sdao);
		
		ssos = new StudentSelectOneService(sdao);
	}
	
	
	
	// getter  생성
	public StudentSelectService getSs(){ // 보관된 객체를 리턴해주는 getter 메서드 // getBean 의 역할
		return ss;
	}
	public StudentInsertService getIs(){ // 보관된 객체를 리턴해주는 getter 메서드 // getBean 의 역할
		return is;
	}
	public StudentSelectOneServiceService getSsos(){ // 보관된 객체를 리턴해주는 getter 메서드 // getBean 의 역할
		return ssos;
	}
	
}