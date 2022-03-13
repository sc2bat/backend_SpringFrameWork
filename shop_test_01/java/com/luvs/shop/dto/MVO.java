package com.luvs.shop.dto;

import java.sql.Timestamp;

public class MVO {
	private String mid;
	private String pwd;
	private String name;
	private String email;
	private String phone;
	private String znum;
	private String addr1;
	private String addr2;
	private String useyn;
	private Timestamp mIndate;
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getZnum() {
		return znum;
	}
	public void setZnum(String znum) {
		this.znum = znum;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getUseyn() {
		return useyn;
	}
	public void setUseyn(String useyn) {
		this.useyn = useyn;
	}
	public Timestamp getmIndate() {
		return mIndate;
	}
	public void setmIndate(Timestamp mIndate) {
		this.mIndate = mIndate;
	}
	
}
