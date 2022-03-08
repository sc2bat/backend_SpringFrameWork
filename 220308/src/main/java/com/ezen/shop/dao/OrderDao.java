package com.ezen.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ezen.shop.dto.CartVO;
import com.ezen.shop.dto.OrderVO;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class OrderDao {
	private JdbcTemplate tpl;
	
	@Autowired
	public OrderDao(ComboPooledDataSource ds) {
		this.tpl = new JdbcTemplate(ds);
	}
	
	public void insertOrders(String userid) {
		String sql = "INSERT INTO orders(oseq, id) VALUES(orders_seq.nextVal, ?)";
		tpl.update(sql, userid);
	}
	
	
	
	public int lookupMaxOseq() {
		String sql = "SELECT MAX(oseq) AS moseq FROM orders";
		List<Integer> list = tpl.query(sql, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				int moseq = rs.getInt("moseq");
				return moseq;
			}
			
		});
		return list.get(0);
	}
	
	
	
	public void insertOrderDetail(CartVO cvo, int oseq) {
		String sql = "INSERT INTO order_detail(odseq, oseq, pseq, quantity)"
				+ " VALUES(order_detail_seq.nextVal, ?, ?, ?)";
		tpl.update(sql, oseq, cvo.getPseq(), cvo.getQuantity());
	}
	
	
	
	public void deleteCart(int cseq) {
		String sql = "DELETE FROM cart WHERE cseq=?";
		tpl.update(sql, cseq);
	}
	

	public List<OrderVO> listOrderByOseq(int oseq) {
		String sql = "SELECT * FROM order_view WHERE oseq=?";
		List<OrderVO> list = tpl.query(sql, new RowMapper<OrderVO>() {

			@Override
			public OrderVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				OrderVO ovo = new OrderVO();
				ovo.setOdseq(rs.getInt("odseq"));
				ovo.setOseq(rs.getInt("oseq"));
				ovo.setIndate(rs.getTimestamp("indate"));
				ovo.setId(rs.getString("id"));
				ovo.setMname(rs.getString("mname"));
				ovo.setZip_num(rs.getString("zip_num"));
				ovo.setAddress(rs.getString("address"));
				ovo.setAddress2(rs.getString("address2"));
				ovo.setPhone(rs.getString("phone"));
				ovo.setPseq(rs.getInt("pseq"));
				ovo.setQuantity(rs.getInt("quantity"));
				ovo.setPname(rs.getString("pname"));
				ovo.setPrice2(rs.getInt("price2"));
				ovo.setResult(rs.getString("result"));
				return ovo;
			}
			
		}, oseq);
		return list;
	}
	
	public void insertOrderDetailOne(int pseq, int quantity, int oseq) {
		String sql = "INSERT INTO order_detail(odseq, oseq, pseq, quantity) VALUES(order_detail_seq.nextVal, ?, ?, ?)";
		tpl.update(sql, oseq, pseq, quantity);
	}

	public List<Integer> selectSeqOrderIng(String userid) {
//		String sql = "SELECT DISTINCT oseq FROM order_view WHERE id=? AND result='1' ORDER BY oseq DESC";
		String sql = "SELECT oseq FROM order_view WHERE id=? AND result='1' ORDER BY oseq DESC";
		List<Integer> oseqList = tpl.query(sql, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("oseq");
			}
			
		}, userid);
		return oseqList;
	}

	public List<Integer> oseqListAll(String userid) {
		String sql = "SELECT DISTINCT oseq FROM order_view WHERE id=? ORDER BY oseq DESC";
		List<Integer> oseqList = tpl.query(sql, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("oseq");
			}
			
		}, userid);
		return oseqList;
	}




}
