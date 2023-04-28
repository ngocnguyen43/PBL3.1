package com.PBL3.daos;

import com.PBL3.models.Business;

import java.util.List;

public interface IBusinessDAO extends GenericDAO<Business> {
    String save(Business business);

    Business findOne(String name);

    Business findOneById(String id);

    List<Business> findAll();
}
