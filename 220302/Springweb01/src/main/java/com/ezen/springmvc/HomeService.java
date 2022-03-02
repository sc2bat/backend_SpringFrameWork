package com.ezen.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Service Ŭ������ ������ �����̳ʿ� �ֱ� ���� ������̼����δ� @Service �� ����մϴ�
// @Repository �� ����ص� ũ�� ������ ������ ��������� ��Ÿ�� ������ �����Ͽ� ����ϴ°� �����Դϴ�
// �ڼ��� ������ ���� �н������Դϴ�

@Service
public class HomeService {
	
	@Autowired
	HomeDao hdao;
	public String getMessage() {
		String message = hdao.getMessage();
		return message;
	}
	
}
