package com.ezen.word.service;

import com.ezen.word.dao.WordDao;
import com.ezen.word.dto.WordSet;

public class WordinsertService {
	private WordDao worddao;
	
	public WordinsertService(WordDao worddao) {
		this.worddao = worddao;
	}
	
	public void insert(WordSet wordSet) {
		worddao.insert(wordSet);
	}
}
