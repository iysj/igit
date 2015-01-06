package com.kingsj.admin.vo;

public class PageParams {
	
	private int page = 1;
	
	private int rows= 10;
	
	public int getOffset() {
		return (this.page - 1) * this.rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

}

/**
 * Copyright (c) 2014, kingsj.yuan@foxmail.com All rights reserved.
 */