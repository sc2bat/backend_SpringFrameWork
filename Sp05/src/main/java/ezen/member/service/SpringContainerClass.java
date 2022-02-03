package ezen.member.service;

import ezen.member.dao.StudentDao;

public class SpringContainerClass {
	private StudentDao sdao;
	private StudentSelectService ss;
	private StudentInsertService is;
	private StudentSelectOneService ssos;
	private StudentUpdateService sus;
	
	public SpringContainerClass() {
		sdao = new StudentDao();
		ss = new StudentSelectService(sdao);
		is = new StudentInsertService(sdao);
		ssos = new StudentSelectOneService(sdao);
		sus = new StudentUpdateService(sdao);
	}
	
	public StudentSelectService getSs() {
		return ss;
	}
	public StudentInsertService getIs() {
		return is;
	}
	public StudentSelectOneService getSsos() {
		return ssos;
	}

	public StudentUpdateService getSus() {
		return sus;
	}
	
	
}
