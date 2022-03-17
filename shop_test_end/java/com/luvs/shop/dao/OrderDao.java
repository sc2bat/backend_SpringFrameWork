package com.luvs.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.luvs.shop.dto.CVO;
import com.luvs.shop.dto.OVO;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class OrderDao {
	private JdbcTemplate tpl;
	
	@Autowired
	public OrderDao(ComboPooledDataSource ds){
		this.tpl = new JdbcTemplate(ds);
	}

	public void insertOrder(String mid) {
		String sql = "INSERT INTO shopOrder(oseq, oid) VALUES(Oseq.nextVal, ?)";
		tpl.update(sql, mid);
	}

	public int lookupMaxOseq() {
		String sql = "SELECT MAX(oseq) AS maxoseq from shopOrder";
		List<Integer> list = tpl.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("maxoseq");
			}
		});
		return list.get(0);
	}

	public void insertOrderDetail(CVO cvo, int oseq) {
		String sql = "INSERT INTO shopOrderDetail(odseq, oseq, pseq, quantity)"
				+ " VALUES(Odseq.nextVal, ?, ?, ?)";
		tpl.update(sql, oseq, cvo.getPseq(), cvo.getQuantity());
	}

	public void deleteCart(int cseq) {
		String sql = "DELETE FROM shopCart WHERE cseq=?";
		tpl.update(sql, cseq);
	}

	public List<OVO> getOrderList(int oseq) {
		String sql = "SELECT * FROM so_view WHERE oseq=?";
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
		}, oseq);
		return list;
	}

	public void orderOne(CVO cvo, int oseq) {
		String sql = "INSERT INTO shopOrderDetail(odseq, oseq, pseq, quantity)"
				+ " VALUES(Odseq.nextVal, ?, ?, ?)";
		tpl.update(sql, oseq, cvo.getPseq(), cvo.getQuantity());
	}

	public List<Integer> getOseqList(String mid) {
		String sql = "SELECT DISTINCT oseq FROM so_view WHERE mid=? AND odResult='1' ORDER BY oseq DESC";
		List<Integer> list = tpl.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("oseq");
			}
		}, mid);
		return list;
	}
	
	

}
