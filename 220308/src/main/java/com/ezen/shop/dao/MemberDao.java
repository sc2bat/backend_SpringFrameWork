package com.ezen.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ezen.shop.dto.AddressVO;
import com.ezen.shop.dto.MemberVO;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class MemberDao {
	
	private JdbcTemplate template;
	@Autowired
	public MemberDao(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	public MemberVO getMember(String id) {
		String sql ="SELECT * FROM member WHERE userid=?";
//		List<MemberVO> list = null;
//		list = template.query(sql, new RowMapper<MemberVO>() {
		List<MemberVO> list = template.query(sql, new RowMapper<MemberVO>() {

			@Override
			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberVO mvo = new MemberVO();
				mvo.setName(rs.getString("name"));
				mvo.setUserid(rs.getString("userid"));
				mvo.setPwd(rs.getString("pwd"));
				mvo.setEmail(rs.getString("email"));
				mvo.setPhone(rs.getString("phone"));
				mvo.setZip_num(rs.getString("zip_num"));
				mvo.setAddress(rs.getString("address"));
				mvo.setAddress2(rs.getString("address2"));
				return mvo;
			}
		}, id); 
		// ? 에 해당하는 값은 괄호가 끝나기전 , 로 구분하여 명시해줍니다\
		// ? 가 두개 이상이라면 역시 , 로 구분해서 순서대로 명시해줍니다
			
		if(list.size() == 0) {
			return null;
		}else {
			return list.get(0);
		}
		// 한명의 데이터를 리턴받아야하는 상황이라고, 결과를 리스트로 도출해서 첫번째 값을 리턴합니다 
	}

	public List<AddressVO> selectAddressByDong(String dong) {
		String sql = "SELECT * FROM address WHERE dong LIKE '%'||?||'%'";
		// 위 SQL 문은 검색어 ? 에 해당하는 단어가 있으면 그 단어를 포함하는 검색을 하고,
		// 없으면 모두 검색의 효과를 얻을 수 있습니다.
		List<AddressVO> list = template.query(sql, new RowMapper<AddressVO>() {
			@Override
			public AddressVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				AddressVO avo = new AddressVO();
				avo.setZip_num(rs.getString("zip_num"));
				avo.setSido(rs.getString("sido"));
				avo.setGugun(rs.getString("gugun"));
				avo.setDong(rs.getString("dong"));
				avo.setZip_code(rs.getString("zip_code"));
				avo.setBunji(rs.getString("bunji"));
				return avo;
			}
		}, dong);
		
		return list;
	}

	public void insertMember(MemberVO mvo) {
		String sql = "INSERT INTO member(userid, pwd, name, email, zip_num, address, address2, phone)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		template.update(sql, mvo.getUserid(), mvo.getPwd(), mvo.getName(), mvo.getEmail(), 
				mvo.getZip_num(), mvo.getAddress(), mvo.getAddress2(), mvo.getPhone());
	}

	public void updateMember(MemberVO mvo) {
		String sql = "UPDATE member SET pwd=?, name=?, email=?, zip_num=?, address=?, address2=?, phone=? WHERE userid=?";
		template.update(sql, mvo.getPwd(), mvo.getName(), mvo.getEmail(), 
				mvo.getZip_num(), mvo.getAddress(), mvo.getAddress2(), mvo.getPhone(), mvo.getUserid());
	}

}
