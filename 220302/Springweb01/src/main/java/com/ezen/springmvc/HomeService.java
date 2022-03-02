package com.ezen.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Service 클래스를 스프링 컨테이너에 넣기 위한 어노테이션으로는 @Service 를 사용합니다
// @Repository 를 사용해도 크게 무리는 없으나 실행시점과 기타의 이유로 구분하여 사용하는게 보통입니다
// 자세한 사유는 별도 학습예정입니다

@Service
public class HomeService {
	
	@Autowired
	HomeDao hdao;
	public String getMessage() {
		String message = hdao.getMessage();
		return message;
	}
	
}
