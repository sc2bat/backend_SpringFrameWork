package com.ezen.word.dto;

public class WordSet {
	private String wordkey;
	private String wordValue;
	
	public WordSet(String wordkey, String wordValue) {
//		super();
		this.wordkey = wordkey;
		this.wordValue = wordValue;
	}
	
	public String getWordkey() {
		return wordkey;
	}
	public void setWordkey(String wordkey) {
		this.wordkey = wordkey;
	}
	public String getWordValue() {
		return wordValue;
	}
	public void setWordValue(String wordValue) {
		this.wordValue = wordValue;
	}
	
	
}
