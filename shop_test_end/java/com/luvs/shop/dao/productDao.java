package com.luvs.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.luvs.shop.dto.PVO;
import com.mchange.v2.c3p0.ComboPooledDataSource;



@Repository
public class productDao {
	
	private JdbcTemplate tpl;
	
	@Autowired
	public productDao(ComboPooledDataSource ds) {
		this.tpl = new JdbcTemplate(ds);
	}

	public List<PVO> getNewList() {
		String sql = "SELECT * FROM newP_view";
		List<PVO> newlist = tpl.query(sql, new RowMapper<PVO>() {
			@Override
			public PVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				PVO pvo = new PVO();
				pvo.setPseq(rs.getInt("pseq"));
				pvo.setName(rs.getString("name"));
				pvo.setKind(rs.getString("kind"));
				pvo.setPrice1(rs.getInt("price1"));
				pvo.setPrice2(rs.getInt("price2"));
				pvo.setPrice3(rs.getInt("price3"));
				pvo.setContent(rs.getString("content"));
				pvo.setImage(rs.getString("image"));
				pvo.setUseyn(rs.getString("useyn"));
				pvo.setBestyn(rs.getString("bestyn"));
				pvo.setIndate(rs.getTimestamp("indate"));
				return pvo;
			}
		});
		return newlist;
	}

	public List<PVO> getBestList() {
		String sql = "SELECT * FROM bestP_view";
		List<PVO> bestlist = tpl.query(sql, new RowMapper<PVO>() {
			@Override
			public PVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				PVO pvo = new PVO();
				pvo.setPseq(rs.getInt("pseq"));
				pvo.setName(rs.getString("name"));
				pvo.setKind(rs.getString("kind"));
				pvo.setPrice1(rs.getInt("price1"));
				pvo.setPrice2(rs.getInt("price2"));
				pvo.setPrice3(rs.getInt("price3"));
				pvo.setContent(rs.getString("content"));
				pvo.setImage(rs.getString("image"));
				pvo.setUseyn(rs.getString("useyn"));
				pvo.setBestyn(rs.getString("bestyn"));
				pvo.setIndate(rs.getTimestamp("indate"));
				return pvo;
			}
		});
		return bestlist;
	}

	public Object getProductKindList(String kind) {
		String sql = "SELECT * FROM shopproduct WHERE kind=?";
		List<PVO> productList = tpl.query(sql, new RowMapper<PVO>() {

			@Override
			public PVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				PVO pvo = new PVO();
				pvo.setPseq(rs.getInt("pseq"));
				pvo.setName(rs.getString("name"));
				pvo.setKind(rs.getString("kind"));
				pvo.setPrice1(rs.getInt("price1"));
				pvo.setPrice2(rs.getInt("price2"));
				pvo.setPrice3(rs.getInt("price3"));
				pvo.setContent(rs.getString("content"));
				pvo.setImage(rs.getString("image"));
				pvo.setUseyn(rs.getString("useyn"));
				pvo.setBestyn(rs.getString("bestyn"));
				pvo.setIndate(rs.getTimestamp("indate"));
				return pvo;
			}
			
		}, kind);
		return productList;
	}

	public Object getProduct(int pseq) {
		String sql = "SELECT * FROM shopproduct WHERE pseq=?";
		List<PVO> list = tpl.query(sql, new RowMapper<PVO>() {

			@Override
			public PVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				PVO pvo = new PVO();
				pvo.setPseq(rs.getInt("pseq"));
				pvo.setName(rs.getString("name"));
				pvo.setKind(rs.getString("kind"));
				pvo.setPrice1(rs.getInt("price1"));
				pvo.setPrice2(rs.getInt("price2"));
				pvo.setPrice3(rs.getInt("price3"));
				pvo.setContent(rs.getString("content"));
				pvo.setImage(rs.getString("image"));
				pvo.setUseyn(rs.getString("useyn"));
				pvo.setBestyn(rs.getString("bestyn"));
				pvo.setIndate(rs.getTimestamp("indate"));
				return pvo;
			}
			
		}, pseq);
		return list.get(0);
	}

	public List<PVO> getProductList() {
		String sql = "SELECT * FROM shopproduct ORDER BY pseq DESC";
		List<PVO> list = tpl.query(sql, new RowMapper<PVO>() {
			@Override
			public PVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				PVO pvo = new PVO();
				pvo.setPseq(rs.getInt("pseq"));
				pvo.setName(rs.getString("name"));
				pvo.setKind(rs.getString("kind"));
				pvo.setPrice1(rs.getInt("price1"));
				pvo.setPrice2(rs.getInt("price2"));
				pvo.setPrice3(rs.getInt("price3"));
				pvo.setContent(rs.getString("content"));
				pvo.setImage(rs.getString("image"));
				pvo.setUseyn(rs.getString("useyn"));
				pvo.setBestyn(rs.getString("bestyn"));
				pvo.setIndate(rs.getTimestamp("indate"));
				return pvo;
			}
		});
		return list;
	}

}
