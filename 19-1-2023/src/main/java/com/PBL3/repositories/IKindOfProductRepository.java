package com.PBL3.repositories;

import com.PBL3.models.KindOfProductModel;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;

import java.util.List;

public interface IKindOfProductRepository {
    void createNewKind(KindOfProductModel domain) throws CreateFailedException;

    List<KindOfProductModel> getAllKinds() throws NotFoundException;
}
