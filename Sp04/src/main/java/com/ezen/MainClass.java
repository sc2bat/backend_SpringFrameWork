package com.ezen;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.ezen.battery.ChargeBattery;
import com.ezen.battery.NormalBattery;
import com.ezen.toy.ElectronicCar;
import com.ezen.toy.ElectronicRadio;

public class MainClass {
	public static void main(String[] args) {
		ElectronicCar carToy1 = new ElectronicCar();
		
		NormalBattery nbatt1 = new NormalBattery();
		ChargeBattery cbatt1 = new ChargeBattery();
		ElectronicRadio radioToy1 = new ElectronicRadio(nbatt1);
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		ElectronicCar carToy2 = ctx.getBean("car", ElectronicCar.class);
		ElectronicRadio radioToy2 = ctx.getBean("radio", ElectronicRadio.class);
		radioToy2.setBattery(nbatt1);
	}
}
