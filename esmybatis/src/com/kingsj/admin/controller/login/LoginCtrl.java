package com.kingsj.admin.controller.login;

import java.util.List;

import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kingsj.admin.controller.BaseCtrl;
import com.kingsj.admin.po.SysMenu;
import com.kingsj.admin.po.SysUser;
import com.kingsj.admin.service.menu.SysMenuService;
import com.kingsj.admin.service.user.SysUserService;
import com.kingsj.admin.utils.TreeUtil;

/**
 * login controller
 * @author <a href="mailto:kingsj.yuan@foxmail">Yuan,sj</a>
 * @history
 * <TABLE id="HistoryTable" border="1">
 * 	<TR><TD>时间</TD><TD>描述</TD><TD>作者</TD></TR>
 *	<TR><TD>2014年11月2日</TD><TD>创建初始版本</TD><TD>Yuan,sj</TD></TR>
 * </TABLE>
 */
@Controller
@RequestMapping("/admin")
public class LoginCtrl extends BaseCtrl{
	
	@Autowired
	private SysUserService userService;
	
	@Autowired
	private SysMenuService menuService;
	
	@RequestMapping
	public String index() {
		return "/index/login.jsp";
	}
	
	@RequestMapping("/toLogin")
	public String toLogin(ModelMap model,SysUser sysUser) {
		SysUser user = userService.queryLogin(sysUser);
		if(StringUtils.isEmpty(user)) {
			model.put("msg", "账号或者密码输入错误");
			return "/index/login.jsp";
		} else {
			List<SysMenu> rootMenus = menuService.getMenus(null);
			List<SysMenu> childMenus = menuService.getMenus("childMenu");
			TreeUtil treeUtil = new TreeUtil(rootMenus, childMenus);
			model.put("menuList", treeUtil.getTreeNodes());
			return "/index/main.jsp";
		}
	}
}

/**
 * Copyright (c) 2014, kingsj.yuan@foxmail.com All rights reserved.
 */