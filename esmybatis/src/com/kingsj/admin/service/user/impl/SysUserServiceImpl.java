package com.kingsj.admin.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingsj.admin.dao.sysUser.SysUserDao;
import com.kingsj.admin.po.SysUser;
import com.kingsj.admin.service.user.SysUserService;

@Service("userService")
public class SysUserServiceImpl implements SysUserService{
	
	@Autowired
	private SysUserDao userDao;

	public SysUser queryLogin(SysUser user){
		SysUser sysUser = userDao.queryLogin(user);
		return sysUser;
	}

}
