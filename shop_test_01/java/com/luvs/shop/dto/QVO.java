package com.luvs.shop.dto;

import java.sql.Timestamp;

public class QVO {
	private int qseq;
	private String qSubject;
	private String qContent;
	private String reply;
	private String qid;
	private String qResult;
	private Timestamp qIndate;
	public int getQseq() {
		return qseq;
	}
	public void setQseq(int qseq) {
		this.qseq = qseq;
	}
	public String getqSubject() {
		return qSubject;
	}
	public void setqSubject(String qSubject) {
		this.qSubject = qSubject;
	}
	public String getqContent() {
		return qContent;
	}
	public void setqContent(String qContent) {
		this.qContent = qContent;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getqResult() {
		return qResult;
	}
	public void setqResult(String qResult) {
		this.qResult = qResult;
	}
	public Timestamp getqIndate() {
		return qIndate;
	}
	public void setqIndate(Timestamp qIndate) {
		this.qIndate = qIndate;
	}
		
}
