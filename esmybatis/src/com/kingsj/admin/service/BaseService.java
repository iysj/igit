package com.kingsj.admin.service;

import java.util.List;
import java.util.Map;

public interface BaseService<T> {
	
	void add(T t);
	
    void update(T t);
	
    void updateBySelective(T t); 	
	
    void delete(Object id);
    
    T queryById(Object id);
    
    int queryByCount(Map<String, Object> map);
    
    List<T> queryByList(Map<String, Object> map);

}

/**
 * Copyright (c) 2014, kingsj.yuan@foxmail.com All rights reserved.
 */