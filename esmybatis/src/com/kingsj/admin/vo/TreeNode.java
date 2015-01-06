package com.kingsj.admin.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeNode {

	private String id;
	
	private Integer dataId;
	
	private String text;
	
	private String url;
	
	private Integer parentId;
	
	private Map<String,Object> attributes = new HashMap<String, Object>();
	
	private List<TreeNode>  children;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public  Integer getParentId() {
		return parentId;
	}

	public void setParentId( Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getDataId() {
		return dataId;
	}

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	
}
