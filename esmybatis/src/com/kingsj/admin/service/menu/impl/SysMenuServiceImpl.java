package com.kingsj.admin.service.menu.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingsj.admin.dao.sysMenu.SysMenuDao;
import com.kingsj.admin.po.SysMenu;
import com.kingsj.admin.service.menu.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService{
	
	@Autowired
	private SysMenuDao menuDao;

	@Override
	public List<SysMenu> getMenus(String mtype) {
		List<SysMenu> list = menuDao.getMenus(mtype);
		return list;
	}

	@Override
	public List<SysMenu> getMenusByUser(String mtype, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(SysMenu t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(SysMenu t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBySelective(SysMenu t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SysMenu queryById(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int queryByCount(Map<String, Object> map) {
		int size = queryByList(map).size();
		return size;
	}

	@Override
	public List<SysMenu> queryByList(Map<String, Object> map) {
		List<SysMenu> list = menuDao.queryByList(map);
		return list;
	}

}

/**
 * Copyright (c) 2014, kingsj.yuan@foxmail.com All rights reserved.
 */