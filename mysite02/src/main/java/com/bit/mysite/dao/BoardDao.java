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
	
	public boolean insert(BoardVo vo, Long userNo) {
		
		try {
			conn = getConnection();
			
			String sql = "insert into board(title, contents, user_no) values(?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, userNo);
			
			int i = pstmt.executeUpdate();
			
			if( i > 0 ) {
				String groupSql = "update board set group_no = (select group_no from (select (MAX(group_no) + 1) as group_no from board) as board_tt) where no = (select no from (select MAX(no) as no from board) as board_t)";
				pstmt = conn.prepareStatement(groupSql);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				int j = pstmt.executeUpdate();
				
				if(rs != null) {
					rs.close();
				}
				
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
				
				if(j > 0 ) {
					return true;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean replyInsert(BoardVo vo, Long userNo) {
		try {
			conn = getConnection();
			
			String sql = "insert into board(title, contents, user_no, group_no) values(?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, userNo);
			pstmt.setInt(4, vo.getGroupNo());
			
			int i = pstmt.executeUpdate();
			
			if(i > 0) {
				String orderSql = "update board set order_no = (select order_no from (select (MAX(order_no) + 1) as order_no from board where group_no =?) as board_tt) where no = (select no from (select MAX(no) as no from board) as board_t)";
				pstmt = conn.prepareStatement(orderSql);
				pstmt.setInt(1, vo.getGroupNo());
			}
			
			int j = pstmt.executeUpdate();
			
			if(j > 0) {
				String orderSql = "update board set depth = (select depth from (select (MAX(depth) + 1) as depth from board where group_no = ?) as board_tt) where no = (select no from (select MAX(no) as no from board) as board_t)";
				pstmt = conn.prepareStatement(orderSql);
				pstmt.setInt(1, vo.getGroupNo());
			}
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				int z = pstmt.executeUpdate();
				
				if(rs != null) {
					rs.close();
				}
				
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
				
				if(z > 0 ) {
					return true;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public BoardVo findByNo(Long no) {
		BoardVo boardVo = null;
		
		try {
			conn = getConnection();
			
			String sql = "select b.no, b.title, b.contents, u.name, date_format(b.w_date, '%Y-%m-%d'), b.user_no, b.group_no"
					+ " from board b, user u"
					+ " where u.no = b.user_no and b.no = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Long bno = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				String author = rs.getString(4);
				Date wDate = rs.getDate(5);
				Long userNo = rs.getLong(6);
				int groupNo = rs.getInt(7);
				
				boardVo = new BoardVo();
				
				boardVo.setNo(bno);
				boardVo.setTitle(title);
				boardVo.setContents(contents);
				boardVo.setAuthor(author);
				boardVo.setwDate(wDate);
				boardVo.setUserNo(userNo);
				boardVo.setGroupNo(groupNo);
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
			
			String sql = "select b.no, b.title, b.contents, b.group_no, b.order_no, b.depth, u.name, date_format(b.w_date, '%Y-%m-%d'), b.v_count, b.user_no"
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
				Long userNo = rs.getLong(10);
				
				BoardVo vo = new BoardVo(no, title, contents, groupNo, orderNo, depth, author, wDate, vCount, userNo);
				
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
	
	public boolean update(BoardVo vo) {
		try {
			conn = getConnection();
			
			String sql = "update board set title = ?, contents = ? where no = ? and user_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());
			pstmt.setLong(4, vo.getUserNo());
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				int i = pstmt.executeUpdate();
				
				if(rs != null) {
					rs.close();
				}
				
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
				
				if( i > 0 ) {
					return true;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean delete(Long no) {
		try {
			conn = getConnection();
			
			String sql = "delete from board where no = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				int i = pstmt.executeUpdate();
				
				if(rs != null) {
					rs.close();
				}
				
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
				
				if( i > 0 ) {
					return true;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
		
	}
	
	public boolean upCount(Long no) {
		
		try {
			conn = getConnection();
			
			String sql = "update board set v_count = v_count + 1 where no = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				int i = pstmt.executeUpdate();
				
				if(rs != null) {
					rs.close();
				}
				
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
				
				if( i > 0 ) {
					return true;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
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
