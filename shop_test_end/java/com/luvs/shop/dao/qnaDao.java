package com.luvs.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.luvs.shop.dto.QVO;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class qnaDao {

	private JdbcTemplate tpl;
	
	@Autowired
	qnaDao(ComboPooledDataSource ds){
		this.tpl = new JdbcTemplate(ds);
	}

	public List<QVO> getQnaList(String mid) {
		String sql = "SELECT * FROM shopQna WHERE qid=?";
		List<QVO> list = tpl.query(sql, new RowMapper<QVO>() {
			@Override
			public QVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				QVO qvo = new QVO();
				qvo.setQseq(rs.getInt("qseq"));
				qvo.setqSubject(rs.getString("qSubject"));
				qvo.setqContent(rs.getString("qContent"));
				qvo.setReply(rs.getString("reply"));
				qvo.setQid(rs.getString("qid"));
				qvo.setqResult(rs.getString("qResult"));
				qvo.setqIndate(rs.getTimestamp("qIndate"));
				return qvo;
			}
		}, mid);
		return list;
	}

	public void insertQna(String mid, String subject, String content) {
		String sql = "INSERT INTO shopQna(qseq, qid, qSubject, qContent)"
				+ " VALUES(Qseq.nextVal, ?, ?, ?)";
		tpl.update(sql, mid, subject, content);
	}

	public QVO qnaView(int qseq) {
		String sql = "SELECT * FROM shopQna WHERE qseq=?";
		List<QVO> list = tpl.query(sql, new RowMapper<QVO>() {
			@Override
			public QVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				QVO qvo = new QVO();
				qvo.setQseq(rs.getInt("qseq"));
				qvo.setqSubject(rs.getString("qSubject"));
				qvo.setqContent(rs.getString("qContent"));
				qvo.setReply(rs.getString("reply"));
				qvo.setQid(rs.getString("qid"));
				qvo.setqResult(rs.getString("qResult"));
				qvo.setqIndate(rs.getTimestamp("qIndate"));
				return qvo;
			}
		}, qseq);
		return list.get(0);
	}
	
	
	
	
	
	
	
	
}
