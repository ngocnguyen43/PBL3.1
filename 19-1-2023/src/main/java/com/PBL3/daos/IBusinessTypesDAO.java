package com.PBL3.daos;

import com.PBL3.models.BusinessTypes;

import java.util.List;

public interface IBusinessTypesDAO extends GenericDAO<BusinessTypes> {
    String save(BusinessTypes businessTypes);

    BusinessTypes findOne(String name);

    List<BusinessTypes> FindAll();

}
