package com.bit.mysite.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.bit.mysite.vo.GuestBookVo;

public class GuestBookDao {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public boolean insert(GuestBookVo vo) {
		try {
			conn = getConnection();
			
			String sql = "insert into guestbook(name, password, contents) values(?, ?, ?);";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContents());
			
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				return true;
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
		
		return false;
	}
	
	public boolean delete(Long no, String password) {
		try {
			
			conn = getConnection();

			String sql = "delete from guestbook where no = ? and password = ?;";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);
			pstmt.setString(2, password);

			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				return true;
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
		return false;
	}

	public List<GuestBookVo> findAll() {
		List<GuestBookVo> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			
			String sql = "select no, name, date_format(reg_date, '%Y-%m-%d') as date, contents, name from guestbook order by reg_date desc;";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				Date date = rs.getDate(3);
				String contents = rs.getString(4);
				
				GuestBookVo vo = new GuestBookVo();
				
				vo.setNo(no);
				vo.setName(name);
				vo.setReg_date(date);
				vo.setContents(contents);
				
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
