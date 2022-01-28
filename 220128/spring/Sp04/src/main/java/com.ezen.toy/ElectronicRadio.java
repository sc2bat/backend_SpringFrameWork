package com.ezen.battery;

public class ElectronicRadio {
	
	private Battery bat;
	public ElectronicRadio(Battery bat){ 
		// 생성자에서 배터리가 초기화 되는데, 그 초기값이 생성자의 전달인수로 전달되어야 하는 형태입니다
		this.bat = bat;
	}
	
	public void setBattery(Battery battery){
		this.bat = battery;
		// 추후 배터리를 새로 교체 할수 있는 기능이 있습니다
	}
}

// 구매와 동시에 동봉된 새 배터리 또는 별도 구매한 배터리를 장착할 수 있는 기능이 있습니다