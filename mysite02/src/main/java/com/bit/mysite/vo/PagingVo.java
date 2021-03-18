package com.bit.mysite.vo;

import com.bit.mysite.dao.BoardDao;

public class PagingVo {
	private final int boardSize = 10; // 페이지당 보여줄 리스트
	private final int pageSize = 5; // 페이지 그룹

	private int currentPage = 0;
	private int startNum = 0;
	private int endNum = 0;
	private int count = 0;
	private int prevPageNum = 0;
	private int nextPageNum = 0;

	public PagingVo(int pageNum) {
		setCurrentPage(pageNum);
		
		setStartNum( ((currentPage - 1) / pageSize) * pageSize + 1);
		
		setEndNum( ((currentPage - 1) / pageSize) * pageSize + pageSize);
		
		setCount(new BoardDao().getCount());
		

		setPrevPageNum(startNum - 1);
		setNextPageNum(endNum + 1);
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getBoardSize() {
		return boardSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPrevPageNum() {
		return prevPageNum;
	}

	public void setPrevPageNum(int prevPageNum) {
		this.prevPageNum = prevPageNum;
	}

	public int getNextPageNum() {
		return nextPageNum;
	}

	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}



}
