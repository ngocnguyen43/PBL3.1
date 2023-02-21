package com.PBL3.daos;

import com.PBL3.models.Business;

public interface IBusinessDAO extends GenericDAO<Business>{
	String save(Business business);
	
	Business findOne(String name);
}
