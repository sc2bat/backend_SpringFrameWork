package com.ezen.springmvc;

import org.springframework.stereotype.Repository;

// ������� Ŭ������ ������ �����̳�(������ �����ӿ�ũ���� ����)�� ������ ������̼� @Repository�� ����մϴ�
@Repository
public class HomeDao {

	public String getMessage() {
		return "Hello Dao";
	}
	
	
}
