package com.bit.mysite.vo;

import java.util.Date;

public class BoardVo {
	
	private Long no;
	private String title;
	private String contents;
	private int groupNo;
	private int orderNo;
	private int depth;
	private String author;
	private Date wDate;
	private int vCount;
	private Long userNo;
	private boolean tDelete;
	private boolean rDelete;
	
	public BoardVo() {	
	}
	
	
	
	
	public BoardVo(Long no, String title, String contents, int groupNo, int orderNo, int depth, String author,
			Date wDate, int vCount, Long userNo) {
		this.no = no;
		this.title = title;
		this.contents = contents;
		this.groupNo = groupNo;
		this.orderNo = orderNo;
		this.depth = depth;
		this.author = author;
		this.wDate = wDate;
		this.vCount = vCount;
		this.userNo = userNo;
	}




	public BoardVo(Long no, String title, String contents, int groupNo, int orderNo, int depth, String author,
			Date wDate, int vCount, Long userNo, boolean tDelete, boolean rDelete) {
		this(no, title, contents, groupNo, orderNo, depth, author, wDate, vCount, userNo);
		this.tDelete = tDelete;
		this.rDelete = rDelete;
	}

	public Long getNo() {
		return no;
	}
	
	public void setNo(Long no) {
		this.no = no;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getDepth() {
		return depth;
	}
	
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getwDate() {
		return wDate;
	}

	public void setwDate(Date wDate) {
		this.wDate = wDate;
	}

	public int getvCount() {
		return vCount;
	}

	public void setvCount(int vCount) {
		this.vCount = vCount;
	}

	public Long getUserNo() {
		return userNo;
	}


	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}


	public boolean istDelete() {
		return tDelete;
	}


	public void settDelete(boolean tDelete) {
		this.tDelete = tDelete;
	}


	public boolean isrDelete() {
		return rDelete;
	}


	public void setrDelete(boolean rDelete) {
		this.rDelete = rDelete;
	}


	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", contents=" + contents + ", groupNo=" + groupNo
				+ ", orderNo=" + orderNo + ", depth=" + depth + ", author=" + author + ", wDate=" + wDate + ", vCount="
				+ vCount + ", userNo=" + userNo + ", tDelete=" + tDelete + ", rDelete=" + rDelete + "]";
	}

	


	

	

	

	

	
	

	
	

}
