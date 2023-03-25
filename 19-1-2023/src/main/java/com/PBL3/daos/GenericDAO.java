package com.PBL3.daos;

import java.util.List;

import com.PBL3.utils.mapper.IMapper;

public interface GenericDAO<T> {
	<T> List<T> query(String sql,IMapper<T> mapper, Object... parameters);
	void update(String sql,Object...  params);
	void insert(String sql,Object... params);

	void delete(String sql,Object... params);
}
