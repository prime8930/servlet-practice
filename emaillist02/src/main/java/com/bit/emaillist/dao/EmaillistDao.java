package com.bit.emaillist.dao;

import java.sql.*;
import java.util.*;

import com.bit.emaillist.vo.EmaillistVo;

public class EmaillistDao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public boolean insert(EmaillistVo vo) {
		
		try {
			
			conn = getConnection();
			
			// 3. SQL 준비
			String sql = "insert into emaillist values(null, ?, ?, ?);";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 바인딩
			pstmt.setString(1, vo.getFirstName());
			pstmt.setString(2, vo.getLastName());
			pstmt.setString(3, vo.getEmail());
			
			
			// 5. SQL 실행
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				//conn.commit();
				return true;
			}
		} catch(SQLException e) {
			// 1. 사과
			// 2. log
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
	
	public List<EmaillistVo> findAll() {
		List<EmaillistVo> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			
			// 3. SQL 준비
			String sql = "select no, first_name, last_name, email from emaillist order by no desc";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 바인딩
			
			// 5. SQL 실행
			rs = pstmt.executeQuery();
			
			// 6. 데이터 가져오기
			while(rs.next()) {
				Long no = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String email = rs.getString(4);
				
				EmaillistVo vo = new EmaillistVo();
				
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setEmail(email);
				
				list.add(vo);
			}
			
		} catch(SQLException e) {
			// 1. 사과
			// 2. log
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
			// 1. JDBC 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결
			String url = "jdbc:mysql://localhost:3306/webdb?characterEncoding=utf8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		} catch(ClassNotFoundException e) {
			// 1. 사과
			// 2. log
			System.out.println("[error] " + e.getMessage());
		} 
		
		return conn;
	}
}
