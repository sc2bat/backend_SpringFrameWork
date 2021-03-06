package com.ezen.sp03;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	
	public static void main(String[] args) {
		WalkClass wc = new WalkClass();
		wc.move();
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		WalkClass wc2 = ctx.getBean("cWalk", WalkClass.class);
		
		wc2.move();
		ctx.close();
	}
}
