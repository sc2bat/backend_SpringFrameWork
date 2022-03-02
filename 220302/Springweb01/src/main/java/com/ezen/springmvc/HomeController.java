package com.ezen.springmvc;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */

// 서버에 요청되는 request 들을 다루는 클래스
// 클래스이름 위에 @Controller 라는 어노테이션을 표시하면 그안에 사용되는 @RequestMapping 에서 요청 리퀘스트가 검색되고 
// 선택되고, 실행됩니다
// 첫 페이지의 주소 http://localhost:8090/springmvc/ 는 localhost의 8090 포트 중 springmvc 로 대표되는 프로젝트에
// 요청을 보낸상ㅌ태이며, 요청의 키워드는 '/'입니다
// 이는 클래스 안에 있는 메서드들중 @RequestMapping('/') 을 찾아서 실행하고 리턴하라는 뜻입니다
@Controller
public class HomeController {

//	@Autowired
//	HomeDao hdao;
	
	@Autowired
	HomeService hs;
	
	// log 출력을 위한 준비
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	// method = RequestMethod.GET 은 생략가능. POST 방식은 반드시 써야합니다.
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date(); // 오늘 날짜 
		// 현재 지역에 맞는 날짜 형식 생성
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		// 생성한 형식에 오늘 날짜 적용
		String formattedDate = dateFormat.format(date);
		// request.setAttribute 를 대신할 정보전달 객체 : model, addAttribute 로 저장만 하면 목적지에 자동 전달됩니다
		// 메서드의 전달인수로 선언하고 사용합니다.
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
		// String 형 리턴
		// 리턴된 스트링은 servlet-context.xml 에 정의된 경로와 파일 확장자가 조립되어 져서
		// "/WEB-INF/views/" + "home" + ".jsp" 이 파일을 찾아서 웹브라우져에 표시되도록 응답합니다
	}
	
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/other")
	public String other(Model model) {
		
//		HomeDao hdao = new HomeDao(); // dao 객체 생성
		// 사용자들에 의해 hdao 를 여러번 만들면 서버에 부담이 됨
		// 이자리에서 Autowired 사용못함 위쪽에 전역변수로 사용하게
		
//		String message = hdao.getMessage(); // dao 의 getMessage 메서드  실행
		
		String message = hs.getMessage(); // service 의 getMessage 메서드  실행
		
		model.addAttribute("message", message); // 리턴받은 리턴값을 model 에 저장
		return "other";
	}
}
