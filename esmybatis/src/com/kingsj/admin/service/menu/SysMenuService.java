package com.kingsj.admin.service.menu;

import java.util.List;

import com.kingsj.admin.po.SysMenu;
import com.kingsj.admin.service.BaseService;


public interface SysMenuService extends BaseService<SysMenu>{

	List<SysMenu> getMenus(String mtype);
	
	List<SysMenu> getMenusByUser(String mtype, Integer userId);
	
}
