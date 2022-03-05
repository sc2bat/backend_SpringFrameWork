package com.test.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.spring.dto.MemberTestDto;
import com.test.spring.util.DBM;

@Repository
public class MemberTestDao {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Autowired
	DBM dbm;

	public MemberTestDto getMember(String id) {
		MemberTestDto mdto = null;
		con = dbm.getConnection();
		String sql = "SELECT * FROM springMember WHERE userid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				mdto = new MemberTestDto();
				mdto.setUserid(id);
				mdto.setPass( rs.getString("pass" ) );
				mdto.setName( rs.getString("name") );
				mdto.setEmail( rs.getString("email") );
				mdto.setPhone( rs.getString("phone") );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.close(con, pstmt, rs);
		}
		
		return mdto;
	}

	public int insertMember(MemberTestDto mdto) {
		int result = 0;
		String sql = "INSERT INTO springMember(userid, pass, name, email, phone)"
				+ " VALUES(?, ?, ?, ?, ?)";
		con = dbm.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getUserid());
			pstmt.setString(2, mdto.getPass());
			pstmt.setString(3, mdto.getName());
			pstmt.setString(4, mdto.getEmail());
			pstmt.setString(5, mdto.getPhone());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.close(con, pstmt, rs);
		}
		return result;
	}

	public int updateMember(MemberTestDto mdto) {
		int result = 0;
		String sql = "UPDATE springMember SET pass=?, name=?, email=?, phone=? WHERE userid=?";
		con = dbm.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getPass());
			pstmt.setString(2, mdto.getName());
			pstmt.setString(3, mdto.getEmail());
			pstmt.setString(4, mdto.getPhone());
			pstmt.setString(5, mdto.getUserid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.close(con, pstmt, rs);
		}
		return result;
	}
	
}
