package com.kingsj.admin.controller.menu;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingsj.admin.controller.BaseCtrl;
import com.kingsj.admin.po.SysMenu;
import com.kingsj.admin.service.menu.SysMenuService;
import com.kingsj.admin.vo.PageParams;

/**
 * the system's menu controller
 * @author <a href="mailto:kingsj.yuan@foxmail">Yuan,sj</a>
 * @history
 * <TABLE id="HistoryTable" border="1">
 * 	<TR><TD>时间</TD><TD>描述</TD><TD>作者</TD></TR>
 *	<TR><TD>2014年12月14日</TD><TD>创建初始版本</TD><TD>Yuan,sj</TD></TR>
 * </TABLE>
 */
@Controller
@RequestMapping("/admin/sysMenu")
public class MenuCtrl extends BaseCtrl{
	
	@Autowired
	private SysMenuService menuService;
	
	@RequestMapping("/view")
	public String menuView() {
		return "/sys/sysMenu.jsp";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> menuList(PageParams params) {
		Map<String, Object> map = getNewMap(params);
		List<SysMenu> list = menuService.queryByList(map);
		int count = menuService.queryByCount(map);
		map.clear();
		map.put("total", count);
		map.put("rows", list);
		return map;
	}

}

/**
 * Copyright (c) 2014, kingsj.yuan@foxmail.com All rights reserved.
 */