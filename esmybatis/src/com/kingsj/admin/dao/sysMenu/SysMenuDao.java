package com.kingsj.admin.dao.sysMenu;

import java.util.List;

import com.kingsj.admin.dao.BaseDao;
import com.kingsj.admin.po.SysMenu;

public interface SysMenuDao extends BaseDao<SysMenu>{

	List<SysMenu> getMenus(String mtype);
}

/**
 * Copyright (c) 2014, kingsj.yuan@foxmail.com All rights reserved.
 */