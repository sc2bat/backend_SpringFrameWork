package com.luvs.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.luvs.shop.dto.CVO;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class cartDao {
	
	private JdbcTemplate tpl;
	
	@Autowired
	public cartDao(ComboPooledDataSource ds) {
		this.tpl = new JdbcTemplate(ds);
	}

	public void insertCart(CVO cvo) {
		String sql = "INSERT INTO shopCart(cseq, cid, pseq, quantity)"
				+ " VALUES(Cseq.nextVal, ?, ?, ?)";
		tpl.update(sql, cvo.getCid(), cvo.getPseq(), cvo.getQuantity());
	}

	public List<CVO> getCartList(String mid) {
		String sql = "SELECT * FROM sc_view WHERE cid=? AND cResult='1'";
		List<CVO> list = tpl.query(sql, new RowMapper<CVO>() {
			@Override
			public CVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				CVO cvo = new CVO();
				cvo.setCseq(rs.getInt("cseq"));
				cvo.setCid(rs.getString("cid"));
				cvo.setMname(rs.getString("mname"));
				cvo.setPseq(rs.getInt("pseq"));
				cvo.setPname(rs.getString("pname"));
				cvo.setQuantity(rs.getInt("quantity"));
				cvo.setPrice2(rs.getInt("price2"));
				cvo.setcResult(rs.getString("cResult"));
				cvo.setcIndate(rs.getTimestamp("cIndate"));
				return cvo;
			}
		}, mid);
		return list;
	}

	public void cartDelete(int cseq) {
		String sql = "DELETE FROM shopCart WHERE cseq=?";
		tpl.update(sql, cseq);
	}
}
