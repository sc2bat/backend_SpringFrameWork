package com.ezen.toy;

import com.ezen.battery.Battery;
import com.ezen.battery.NormalBattery;

public class ElectronicCar {
	private Battery bat;
	
	public ElectronicCar() {
		bat = new NormalBattery();
	}
}
