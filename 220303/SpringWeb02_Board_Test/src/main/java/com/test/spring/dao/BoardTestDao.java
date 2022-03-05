package com.test.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.spring.dto.BoardTestDto;
import com.test.spring.dto.ReplyTestDto;
import com.test.spring.util.DBM;

@Repository
public class BoardTestDao {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Autowired
	DBM dbm;

	public ArrayList<BoardTestDto> getBoardMain() {
		ArrayList<BoardTestDto> boardlist = new ArrayList<BoardTestDto>();
		con = dbm.getConnection();
		String sql = "SELECT * FROM springBoard ORDER BY num DESC";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				BoardTestDto bdto = new BoardTestDto();
				bdto.setNum(rs.getInt("num"));
				bdto.setPass(rs.getString("pass"));
				bdto.setUserid(rs.getString("userid"));
				bdto.setTitle(rs.getString("title"));
				bdto.setEmail(rs.getString("email"));
				bdto.setContent(rs.getString("content"));
				bdto.setWritedate(rs.getTimestamp("writedate"));
				bdto.setReadcount(rs.getInt("readcount"));
				bdto.setImgfilename(rs.getString("imgfilename"));
				boardlist.add( bdto );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.close(con, pstmt, rs);
		}
		
		return boardlist;
	}

	public void insertBoard(BoardTestDto bdto) {
		String sql = "INSERT INTO springBoard(num, userid, pass, email, title, content, imgfilename)"
				+ " VALUES(sb_seq.nextVal, ?, ?, ?, ?, ?, ?)";
		con = dbm.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bdto.getUserid());
			pstmt.setString(2, bdto.getPass());
			pstmt.setString(3, bdto.getEmail());
			pstmt.setString(4, bdto.getTitle());
			pstmt.setString(5, bdto.getContent());
			pstmt.setString(6, bdto.getImgfilename());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.close(con, pstmt, rs);
		}
	}

	public void plusReadCount(int num) {
		String sql = "UPDATE springBoard SET readcount = readcount+1 WHERE num=?";

		con = dbm.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.close(con, pstmt, rs);
		}
	}

	public BoardTestDto getBoardView(int num) {
		BoardTestDto bdto = new BoardTestDto();
		String sql = "SELECT * FROM springBoard WHERE num=?";
		con = dbm.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bdto.setNum(rs.getInt("num"));
				bdto.setUserid(rs.getString("userid"));
				bdto.setPass(rs.getString("pass"));
				bdto.setEmail(rs.getString("email"));
				bdto.setTitle(rs.getString("title"));
				bdto.setContent(rs.getString("content"));
				bdto.setReadcount(rs.getInt("readcount"));
				bdto.setWritedate(rs.getTimestamp("writedate"));
				bdto.setReplycnt(rs.getInt("replycnt"));
				bdto.setImgfilename(rs.getString("imgfilename"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.close(con, pstmt, rs);
		}
		return bdto;
	}

	public ArrayList<ReplyTestDto> getReplyList(int num) {
		ArrayList<ReplyTestDto> rlist = new ArrayList<ReplyTestDto>();
		String sql = "SELECT * FROM springReply WHERE boardnum=? ORDER BY num";
		con = dbm.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReplyTestDto rdto = new ReplyTestDto();
				rdto.setNum(rs.getInt("num"));
				rdto.setBoardnum(rs.getInt("boardnum"));
				rdto.setUserid(rs.getString("userid"));
				rdto.setContent(rs.getString("content"));
				rdto.setWritedate(rs.getTimestamp("writedate"));
				rlist.add(rdto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.close(con, pstmt, rs);
		}
		return rlist;
	}

	public void addReply(ReplyTestDto rdto) {
		String sql = "INSERT INTO springReply(num, boardnum, userid, content)"
				+ " VALUES(sr_seq.nextVal, ?, ?, ?)";
		con = dbm.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rdto.getBoardnum());
			pstmt.setString(2, rdto.getUserid());
			pstmt.setString(3, rdto.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.close(con, pstmt, rs);
		}
		
	}

	public void deleteReply(int rnum) {
		String sql = "DELETE FROM springReply WHERE num=?";

		con = dbm.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rnum);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.close(con, pstmt, rs);
		}
	}

	public BoardTestDto getBoardOne(int boardnum) {
		BoardTestDto bdto = null;
		String sql = "SELECT * FROM springBoard WHERE num=?";
		con = dbm.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bdto = new BoardTestDto();
				bdto.setNum(boardnum);
				bdto.setUserid(rs.getString("userid"));
				bdto.setPass(rs.getString("pass"));
				bdto.setEmail(rs.getString("email"));
				bdto.setTitle(rs.getString("title"));
				bdto.setContent(rs.getString("content"));
				bdto.setReadcount(rs.getInt("readcount"));
				bdto.setWritedate(rs.getTimestamp("writedate"));
				bdto.setReplycnt(rs.getInt("replycnt"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.close(con, pstmt, rs);
		}
		return bdto;
	}

	public void boardUpdate(BoardTestDto bdto) {
		String sql = "UPDATE springBoard SET userid=?, pass=?, email=?, title=?, content=?, "
				+ "imgfilename=? WHERE num=?";
		con = dbm.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bdto.getUserid());
			pstmt.setString(2, bdto.getPass());
			pstmt.setString(3, bdto.getEmail());
			pstmt.setString(4, bdto.getTitle());
			pstmt.setString(5, bdto.getContent());
			pstmt.setString(6, bdto.getImgfilename());
			pstmt.setInt(7, bdto.getNum());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.close(con, pstmt, rs);
		}
	}

	public void boardDelete(int boardnum) {
		String sql = "DELETE FROM springBoard WHERE num=?";
		con = dbm.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardnum);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.close(con, pstmt, rs);
		}
	}

}
