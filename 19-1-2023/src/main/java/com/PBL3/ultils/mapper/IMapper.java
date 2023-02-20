package com.PBL3.ultils.mapper;

import java.sql.ResultSet;


public interface IMapper<T> {
	T mapRow(ResultSet result);
	
}
