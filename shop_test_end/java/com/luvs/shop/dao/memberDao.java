package com.luvs.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.luvs.shop.dto.ADD;
import com.luvs.shop.dto.MVO;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class memberDao {
	
	private JdbcTemplate tpl;
	
	@Autowired
	public memberDao(ComboPooledDataSource da) {
		this.tpl = new JdbcTemplate(da);
	}

	public MVO getMember(String id) {
		String sql = "SELECT * FROM shopMem WHERE mid=?";
		List<MVO> list = tpl.query(sql, new RowMapper<MVO>() {
			@Override
			public MVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MVO mvo = new MVO();
				mvo.setMid(rs.getString("mid"));
				mvo.setPwd(rs.getString("pwd"));
				mvo.setName(rs.getString("name"));
				mvo.setEmail(rs.getString("email"));
				mvo.setPhone(rs.getString("phone"));
				mvo.setZnum(rs.getString("znum"));
				mvo.setAddr1(rs.getString("addr1"));
				mvo.setAddr2(rs.getString("addr2"));
				mvo.setUseyn(rs.getString("useyn"));
				mvo.setmIndate(rs.getTimestamp("mindate"));
				return mvo;
			}
		}, id);
		if(list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	public List<ADD> getAddress(String dong) {
		String sql = "SELECT * FROM address WHERE dong LIKE '%'||?||'%'";
		List<ADD> list = tpl.query(sql, new RowMapper<ADD>() {
			@Override
			public ADD mapRow(ResultSet rs, int rowNum) throws SQLException {
				ADD add = new ADD();
				add.setZip_num(rs.getString("zip_num"));
				add.setSido(rs.getString("sido"));
				add.setGugun(rs.getString("gugun"));
				add.setDong(rs.getString("dong"));
				add.setZip_code(rs.getString("zip_code"));
				add.setBunji(rs.getString("bunji"));
				return add;
			}
		}, dong);
		return list;
	}

	public void signUp(MVO mvo) {
		String sql = "INSERT INTO shopMem(mid, pwd, name, email, phone, znum, addr1, addr2)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		tpl.update(sql, mvo.getMid(), mvo.getPwd(), mvo.getName(), mvo.getEmail(), mvo.getPhone(), 
				mvo.getZnum(), mvo.getAddr1(), mvo.getAddr2());
	}

	public void updateMember(MVO mvo) {
		String sql = "UPDATE shopMem SET pwd=?, name=?, email=?, phone=?, znum=?, addr1=?, addr2=? WHERE mid=?";
		tpl.update(sql, mvo.getPwd(), mvo.getName(), mvo.getEmail(), mvo.getPhone(), mvo.getZnum(), 
				mvo.getAddr1(), mvo.getAddr2(), mvo.getMid());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
