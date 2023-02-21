package com.PBL3.daos;

import com.PBL3.models.BussinessTypes;

public interface IBussinessTypesDAO extends GenericDAO<BussinessTypes>{
	String save(BussinessTypes bussinessTypes);
}
