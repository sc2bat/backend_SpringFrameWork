package com.luvs.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.luvs.shop.dto.AVO;
import com.luvs.shop.dto.OVO;
import com.luvs.shop.dto.PVO;
import com.luvs.shop.dto.Paging;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class adminDao {
	private JdbcTemplate tpl;
	
	@Autowired
	public adminDao(ComboPooledDataSource ds) {
		this.tpl = new JdbcTemplate(ds);
	}

	public AVO getAdmin(String workId) {
		String sql = "SELECT * FROM shopAdmin WHERE adminId=?";
		List<AVO> list = tpl.query(sql, new RowMapper<AVO>() {
			@Override
			public AVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				AVO avo = new AVO();
				avo.setAdminId(rs.getString("adminId"));
				avo.setAdminPwd(rs.getString("adminPwd"));
				avo.setAdminName(rs.getString("adminName"));
				avo.setAdminPhone(rs.getString("adminPhone"));
				avo.setAdminEmail(rs.getString("adminEmail"));
				return avo;
			}
		}, workId);
		return list.get(0);
	}

	public int adminCheck(String adminId, String adminPwd) {
		String sql = "SELECT adminpwd FROM shopAdmin WHERE adminid=?";
		int result = 0;
		List<String> list = tpl.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("adminpwd");
			}
		}, adminId);
		
		if(list.size() == 0) {
			result = 1;
		}else if(!list.get(0).equals(adminPwd)) {
			result = 2;
		}else if(list.get(0).equals(adminPwd)) {
			result = 0;
		}else {
			result = 3;
		}
		
		return result;
	}

	public List<PVO> getProductList(Paging paging, String key) {
		String sql = "SELECT * FROM ("
				+ "SELECT * FROM ("
				+ "SELECT ROWNUM AS rn, p.* FROM shopproduct p WHERE name LIKE '%'||?||'%' ORDER BY pseq DESC"
				+ ") WHERE rn >=?"
				+ ") WHERE rn <=?";
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
			
		}, key, paging.getStartNum(), paging.getEndNum());
		
		return list;
	}

	public int getAllCount(String tableName, String columnName, String key) {
		int count = 0;
		/*
		String sql = "SELECT COUNT(*) AS count FROM " + tableName + 
				" WHERE " + columnName + " LIKE '%'||?||'%'";
		List<Integer> list = tpl.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("count");
			}
		}, key);*/
		/* 이미 붙은 값에 || 사용하여 붙이기 하면 
		 * 	java.sql.SQLSyntaxErrorException: ORA-00936: missing expression 에러 발생
		String sql = "SELECT COUNT(*) AS count FROM " + tableName + 
				" WHERE " + columnName + " LIKE '%'" + key + "'%'";
		List<Integer> list = tpl.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("count");
			}
		});*/
		/*
		String sql = "SELECT COUNT(*) AS count FROM " + tableName + 
				" WHERE " + columnName + " LIKE '%'||" + key + "||'%'";
		List<Integer> list = tpl.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("count");
			}
		});*/
		/* java.sql.SQLSyntaxErrorException: ORA-00903: invalid table name 
		String sql = "SELECT COUNT(*) AS count FROM ? WHERE ? LIKE '%'||?||'%'";
		List<Integer> list = tpl.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("count");
			}
		}, tableName, columnName, key);*/
		/* from 뒤에 오는 테이블이름은 ?로 비워두면 에러발생 */
		String sql = "SELECT COUNT(*) AS count FROM " + tableName + " WHERE ? LIKE '%'||?||'%'";
		List<Integer> list = tpl.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("count");
			}
		}, columnName, key);
		count = list.get(0);
		return count;
	}

	public List<OVO> getOrderList(Paging paging, String key) {
		String sql = "SELECT * FROM ("
				+ "SELECT * FROM ("
				+ "SELECT ROWNUM AS rn, o.* FROM so_view o WHERE mname LIKE '%'||?||'%' ORDER BY oseq DESC"
				+ ") WHERE rn >= ?"
				+ ") WHERE rn <= ?";
		List<OVO> list = tpl.query(sql, new RowMapper<OVO>() {
			@Override
			public OVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				OVO ovo = new OVO();
				ovo.setOdseq(rs.getInt("odseq"));
				ovo.setOseq(rs.getInt("oseq"));
				ovo.setoIndate(rs.getTimestamp("oIndate"));
				ovo.setMid(rs.getString("mid"));
				ovo.setMname(rs.getString("mname"));
				ovo.setZnum(rs.getString("znum"));
				ovo.setAddr1(rs.getString("addr1"));
				ovo.setAddr2(rs.getString("addr2"));
				ovo.setPhone(rs.getString("phone"));
				ovo.setPseq(rs.getInt("pseq"));
				ovo.setPname(rs.getString("pname"));
				ovo.setPrice2(rs.getInt("price2"));
				ovo.setQuantity(rs.getInt("quantity"));
				ovo.setOdResult(rs.getString("odResult"));
				return ovo;
			}
		}, key, paging.getStartNum(), paging.getEndNum());
		return list;
	}

	public void updateOrderResult(int odseq) {
		String sql = "UPDATE shopOrderDetail SET odResult='2' WHERE odseq=?";
		tpl.update(sql, odseq);
	}
	
	
	
}
