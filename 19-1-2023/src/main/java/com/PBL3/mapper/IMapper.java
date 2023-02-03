package com.PBL3.mapper;

import java.sql.ResultSet;


public interface IMapper<T> {
	T mapRow(ResultSet result);
	
}
