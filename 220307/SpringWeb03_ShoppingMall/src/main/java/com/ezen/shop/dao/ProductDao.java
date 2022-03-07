package com.ezen.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ezen.shop.dto.ProductVO;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class ProductDao {
	
	// 데이터베이스 연결방식 변경
	// c3p0 mchange 데이터베이스 커넥션 jar 파일
	// servlet-context.xml
	
	// 데이터베이스에서 데이터의 삽입, 수정, 삭제, 조회(CRUD)를 담당할 객체를 선언합니다
	private JdbcTemplate template; // 이안에 con, pstmt, rs 가 모두 존재합니다
	
	/*
	// 스프링 컨테이너에 수동으로 넣어놓은 DBCP 연결 인스턴스를 꺼내옵니다.
	@Autowired
	ComboPooledDataSource dataSource;
	
	// dataSource 를 이용하여, template 객체를 수동으로 초기화를 해야하는 상황이므로,
	// 생성자에서 초기화합니다
	public ProductDao() {
		this.template = new JdbcTemplate(dataSource);
	}
	*/
	
	// Autowired 로 빈을 꺼내와서 담는 동작은 생성자의 전달인수에도 가능합니다
	@Autowired
	public ProductDao(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	
	public List<ProductVO> getNewList() {
		String sql = "SELECT * FROM new_pro_view";
//		List<ProductVO> nlist = => SELECT 조회 결과는 nlist 에 저장
		// template.query(); => 템플릿을 이용한 테이블 조회, 결과는 List<ProductVO> 형 리스트
//		List<ProductVO> nlist = template.query();
		
		//new RowMapper<ProductVO>() {} 생성자를 만든 익명함수
		// 익명 클래스 변수이름 = new RowMapper<ProductVO>() {};
		List<ProductVO> nlist = template.query(sql, new RowMapper<ProductVO>() {

			@Override
			public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductVO pvo = new ProductVO();
				pvo.setPseq(rs.getInt("pseq"));
				pvo.setName(rs.getString("name"));
				pvo.setPrice2(rs.getInt("price2"));
				pvo.setImage(rs.getString("image"));
				return pvo;
			}
			
		});
		
		return nlist;
	}

	public List<ProductVO> getBestList() {
		String sql = "SELECT * FROM best_pro_view";
		List<ProductVO> blist = template.query(sql, new RowMapper<ProductVO>() {

			@Override
			public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductVO pvo = new ProductVO();
				pvo.setPseq(rs.getInt("pseq"));
				pvo.setName(rs.getString("name"));
				pvo.setPrice2(rs.getInt("price2"));
				pvo.setImage(rs.getString("image"));
				return pvo;
			}
			
		});
		return blist;
	}


	public List<ProductVO> getKindList(String kind) {
		String sql = "SELECT * FROM product WHERE kind=?";
		List<ProductVO> kindlist = template.query(sql, new RowMapper<ProductVO>() {
			@Override
			public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductVO pvo = new ProductVO();
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
		return kindlist;
	}


	public ProductVO getProduct(int pseq) {
		String sql = "SELECT * FROM product WHERE pseq=?";
		List<ProductVO> list = template.query(sql, new RowMapper<ProductVO>() {
			@Override
			public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductVO pvo = new ProductVO();
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
//		if(list.size() == 0) {
//			return null;
//		}else {
//			return list.get(0);
//		}
		return list.get(0);
	}

}
