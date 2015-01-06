package com.kingsj.admin.dao.sysUser;

import org.springframework.stereotype.Repository;

import com.kingsj.admin.dao.BaseDao;
import com.kingsj.admin.po.SysUser;

@Repository("userDao")
public interface SysUserDao extends BaseDao<SysUser> {
	
	SysUser queryLogin(SysUser user);
}
