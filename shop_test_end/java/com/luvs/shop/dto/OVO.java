package com.luvs.shop.dto;

import java.sql.Timestamp;

public class OVO {
	private int odseq;
	private int oseq;
	private Timestamp oIndate;
	private String mid;
	private String mname;
	private String znum;
	private String addr1;
	private String addr2;
	private String phone;
	private int pseq;
	private String pname;
	private int price2;
	private int quantity;
	private String odResult;
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public int getOdseq() {
		return odseq;
	}
	public void setOdseq(int odseq) {
		this.odseq = odseq;
	}
	public int getOseq() {
		return oseq;
	}
	public void setOseq(int oseq) {
		this.oseq = oseq;
	}
	public Timestamp getoIndate() {
		return oIndate;
	}
	public void setoIndate(Timestamp oIndate) {
		this.oIndate = oIndate;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getPseq() {
		return pseq;
	}
	public void setPseq(int pseq) {
		this.pseq = pseq;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPrice2() {
		return price2;
	}
	public void setPrice2(int price2) {
		this.price2 = price2;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getOdResult() {
		return odResult;
	}
	public void setOdResult(String odResult) {
		this.odResult = odResult;
	}
	
}
