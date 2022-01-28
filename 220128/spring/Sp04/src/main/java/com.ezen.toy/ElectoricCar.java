package com.ezen.battery;

public class ElectronicCar {
	
	private Battery bat; // 인터페이스 레퍼런스 변수. - 배터리 변수가 멤버변수
	public ElectronicCar(){ // 생성자에서 노멀배터리를 초기값으로 대입하고 시작합니다
		bat = new NormalBattery();
		// 인터페이스 멤버 변수에 battery를 임플리먼트한 클래스를 대입합니다
	}
}

// 이 장난감은 배터리가 출시당시 탑재(내장) 되서 교체가 불가능한 상태로 판매되는 형태입니다