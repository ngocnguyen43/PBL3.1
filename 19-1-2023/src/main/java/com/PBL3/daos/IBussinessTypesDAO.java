package com.PBL3.daos;

import com.PBL3.models.BusinessTypes;

public interface IBussinessTypesDAO extends GenericDAO<BusinessTypes>{
	String save(BusinessTypes businessTypes);
	BusinessTypes findOne(String name);
}
