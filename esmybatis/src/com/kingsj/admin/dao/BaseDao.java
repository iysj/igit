package com.kingsj.admin.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao<T> {

	void add(T t);
	
    void update(T t);
	
    void delete(Object id);
	
	T queryById(Object id);
	
	List<T> queryByList(Map<String, Object> map);
}
