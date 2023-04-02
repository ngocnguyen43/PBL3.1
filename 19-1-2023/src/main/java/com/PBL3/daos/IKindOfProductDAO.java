package com.PBL3.daos;

import com.PBL3.models.KindOfProductModel;

import java.util.List;

public interface IKindOfProductDAO extends GenericDAO<KindOfProductModel> {
    List<KindOfProductModel> findAll();

    void save(KindOfProductModel domain);

    KindOfProductModel findOne(String id);

}
