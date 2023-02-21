package com.PBL3.daos.impl;

import com.PBL3.daos.IBussinessTypesDAO;
import com.PBL3.models.BussinessTypes;

public class BussinessTypesDAO extends AbstractDAO<BussinessTypes> implements IBussinessTypesDAO {

	@Override
	public String save(BussinessTypes bussinessTypes) {
		String sql = "INSERT INTO bussiness_types (type_id,) VALUES ()";
		return null;
	}

}
