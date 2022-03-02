package com.ezen.springmvc;

import org.springframework.stereotype.Repository;

// 만들어진 클래스를 스프링 컨테이너(스프링 프레임워크에서 제공)에 넣으면 어노테이션 @Repository를 사용합니다
@Repository
public class HomeDao {

	public String getMessage() {
		return "Hello Dao";
	}
	
	
}
