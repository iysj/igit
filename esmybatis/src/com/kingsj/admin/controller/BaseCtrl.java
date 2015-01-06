package com.kingsj.admin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.kingsj.admin.vo.PageParams;

public class BaseCtrl {

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	public Map<String, Object> getNewMap(PageParams params) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start",params.getOffset());
		map.put("end", params.getRows());
		return map;
	}
}

/**
 * Copyright (c) 2014, kingsj.yuan@foxmail.com All rights reserved.
 */