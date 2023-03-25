package com.PBL3.repositories.impls;

import com.PBL3.daos.IKindOfProductDAO;
import com.PBL3.models.KindOfProductModel;
import com.PBL3.repositories.IKindOfProductRepository;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.List;

public class KindOfProductRepository implements IKindOfProductRepository {
    @Inject
    IKindOfProductDAO iKindOfProductDAO;
    @Override
    public void createNewKind(KindOfProductModel domain) throws CreateFailedException {
        try{

            iKindOfProductDAO.save(domain);
        }catch (Exception e){
            throw new CreateFailedException("Create New Product Kind Failed");
        }
    }

    @Override
    public List<KindOfProductModel> getAllKinds() throws NotFoundException {
        List<KindOfProductModel> kindOfProductModels = iKindOfProductDAO.findAll();
        if (kindOfProductModels == null) throw new NotFoundException("Not Found Any Kind Of Product");
        return kindOfProductModels;
    }
}
