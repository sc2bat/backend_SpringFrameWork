package com.luvs.shop.dto;

import java.sql.Timestamp;

public class CVO {
	private int cseq;
	private String cid;
	private String mname;
	private int pseq;
	private String pname;
	private int quantity;
	private int price2;
	private String cResult;
	private Timestamp cIndate;
	public int getCseq() {
		return cseq;
	}
	public void setCseq(int cseq) {
		this.cseq = cseq;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice2() {
		return price2;
	}
	public void setPrice2(int price2) {
		this.price2 = price2;
	}
	public String getcResult() {
		return cResult;
	}
	public void setcResult(String cResult) {
		this.cResult = cResult;
	}
	public Timestamp getcIndate() {
		return cIndate;
	}
	public void setcIndate(Timestamp cIndate) {
		this.cIndate = cIndate;
	}
	
}
