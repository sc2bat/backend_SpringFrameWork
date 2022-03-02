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

// ������ ��û�Ǵ� request ���� �ٷ�� Ŭ����
// Ŭ�����̸� ���� @Controller ��� ������̼��� ǥ���ϸ� �׾ȿ� ���Ǵ� @RequestMapping ���� ��û ������Ʈ�� �˻��ǰ� 
// ���õǰ�, ����˴ϴ�
// ù �������� �ּ� http://localhost:8090/springmvc/ �� localhost�� 8090 ��Ʈ �� springmvc �� ��ǥ�Ǵ� ������Ʈ��
// ��û�� ���������̸�, ��û�� Ű����� '/'�Դϴ�
// �̴� Ŭ���� �ȿ� �ִ� �޼������ @RequestMapping('/') �� ã�Ƽ� �����ϰ� �����϶�� ���Դϴ�
@Controller
public class HomeController {

//	@Autowired
//	HomeDao hdao;
	
	@Autowired
	HomeService hs;
	
	// log ����� ���� �غ�
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	// method = RequestMethod.GET �� ��������. POST ����� �ݵ�� ����մϴ�.
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date(); // ���� ��¥ 
		// ���� ������ �´� ��¥ ���� ����
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		// ������ ���Ŀ� ���� ��¥ ����
		String formattedDate = dateFormat.format(date);
		// request.setAttribute �� ����� �������� ��ü : model, addAttribute �� ���常 �ϸ� �������� �ڵ� ���޵˴ϴ�
		// �޼����� �����μ��� �����ϰ� ����մϴ�.
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
		// String �� ����
		// ���ϵ� ��Ʈ���� servlet-context.xml �� ���ǵ� ��ο� ���� Ȯ���ڰ� �����Ǿ� ����
		// "/WEB-INF/views/" + "home" + ".jsp" �� ������ ã�Ƽ� ���������� ǥ�õǵ��� �����մϴ�
	}
	
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/other")
	public String other(Model model) {
		
//		HomeDao hdao = new HomeDao(); // dao ��ü ����
		// ����ڵ鿡 ���� hdao �� ������ ����� ������ �δ��� ��
		// ���ڸ����� Autowired ������ ���ʿ� ���������� ����ϰ�
		
//		String message = hdao.getMessage(); // dao �� getMessage �޼���  ����
		
		String message = hs.getMessage(); // service �� getMessage �޼���  ����
		
		model.addAttribute("message", message); // ���Ϲ��� ���ϰ��� model �� ����
		return "other";
	}
}
