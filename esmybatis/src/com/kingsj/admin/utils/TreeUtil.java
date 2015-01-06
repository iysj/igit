package com.kingsj.admin.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.kingsj.admin.po.SysMenu;
import com.kingsj.admin.vo.TreeNode;


/**
 * this class is for menu tree
 * @author <a href="mailto:kingsj.yuan@foxmail">Yuan,sj</a>
 * @history
 * <TABLE id="HistoryTable" border="1">
 * 	<TR><TD>时间</TD><TD>描述</TD><TD>作者</TD></TR>
 *	<TR><TD>2014年12月1日</TD><TD>创建初始版本</TD><TD>Yuan,sj</TD></TR>
 * </TABLE>
 */
public class TreeUtil {
	
	private static final String MENU_ID = "menu_";
	
	private List<SysMenu> parentList;
	private List<SysMenu> childList;
	
	public TreeUtil(List<SysMenu> parentList, List<SysMenu> childList) {
		this.parentList = parentList;
		this.childList = childList;
	}
	
	public List<TreeNode> getTreeNodes() {
		return getTreeNodesByList();
	}
	
	
	/**
	 * get List<TreeNode>  by  parentList and childList
	 * @return List<TreeNode>
	 */
	private List<TreeNode> getTreeNodesByList() {
		List<TreeNode> rootNodes = new ArrayList<>();
		for (SysMenu menu : parentList) {
			TreeNode treeNode = getTreeNodeByMenu(menu);
			if(!StringUtils.isEmpty(treeNode)) {
				rootNodes.add(treeNode);
				addChildNodes(treeNode);
			}
		}
		return rootNodes;
	}
	
	/**
	 * add the childNodes for each rootNodes;
	 * @param treeNode
	 */
	private void addChildNodes(TreeNode treeNode) {
		List<TreeNode> childNodes = new ArrayList<>();
		for (SysMenu menu : childList) {
			if(treeNode.getDataId().equals(menu.getParentId())) {
				TreeNode node = getTreeNodeByMenu(menu);
				childNodes.add(node);
			}
		}
		treeNode.setChildren(childNodes);
	}
	
	/**
	 * convert menu to treeNode
	 * @param menu
	 * @return
	 */
	private TreeNode getTreeNodeByMenu(SysMenu menu) {
		if(StringUtils.isEmpty(menu)) {
			return null;
		} else {
			TreeNode node = new TreeNode();
			node.setId(MENU_ID+menu.getId());
			node.setDataId(menu.getId());
			node.setText(menu.getName());
			node.setUrl(menu.getUrl());
			node.setParentId(menu.getParentId());
			node.getAttributes().put("type", "0");
			node.getAttributes().put("id", menu.getId());
			return node;
		}
	}

}

/**
 * Copyright (c) 2014, kingsj.yuan@foxmail.com All rights reserved.
 */