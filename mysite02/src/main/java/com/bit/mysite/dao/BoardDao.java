package com.bit.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;

import com.bit.mysite.vo.BoardVo;
import com.bit.mysite.vo.UserVo;

public class BoardDao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public BoardVo findNameByNo(Long no) {
		BoardVo boardVo = null;
		
		try {
			conn = getConnection();
			
			String sql = "select title, contents from board where no = ?;";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String title = rs.getString(1);
				String contents = rs.getString(2);
				
				boardVo = new BoardVo();
				
				boardVo.setTitle(title);
				boardVo.setContents(contents);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if(rs != null) {
					rs.close();
				}
				
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return boardVo;
	}
	
	public List<BoardVo> findAll() {
		List<BoardVo> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			
			String sql = "select b.no, b.title, b.contents, b.group_no, b.order_no, b.depth, u.name, date_format(b.w_date, '%Y-%m-%d'), b.v_count"
					+ " from board b, user u"
					+ " where b.user_no = u.no"
					+ " order by group_no desc, order_no asc";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				int groupNo = rs.getInt(4);
				int orderNo = rs.getInt(5);
				int depth = rs.getInt(6);
				String author = rs.getString(7);
				Date wDate = rs.getDate(8);
				int vCount = rs.getInt(9);
				
				BoardVo vo = new BoardVo(no, title, contents, groupNo, orderNo, depth, author, wDate, vCount);
				
				list.add(vo);
			}
			
		} catch(SQLException e) {
			System.out.println("[error] " + e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close(); 
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
		
	}
	
	private Connection getConnection() throws SQLException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/webdb?characterEncoding=utf8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		} catch(ClassNotFoundException e) {
			System.out.println("[error] " + e.getMessage());
		} 
		
		return conn;
	}
	
}
