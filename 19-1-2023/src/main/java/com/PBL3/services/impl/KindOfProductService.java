package com.PBL3.services.impl;

import com.PBL3.daos.IKindOfProductDAO;
import com.PBL3.dtos.KindOfProductDTO;
import com.PBL3.models.KindOfProductModel;
import com.PBL3.services.IKindOfProductService;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Data;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class KindOfProductService implements IKindOfProductService {
    @Inject
    private IKindOfProductDAO kindOfProductDAO;

    @Override
    public Message createNewKind(KindOfProductDTO dto) throws CreateFailedException {
        try {
            KindOfProductModel domain = Helper.objectMapper(dto, KindOfProductModel.class);
            String id = IDGeneration.generate();
            domain.setId(id);
            kindOfProductDAO.save(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("Created New Kind Successfully").build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new CreateFailedException("Create New Product Kind Failed");
        }
    }

    @Override
    public Message getAllKinds() throws NotFoundException {
        List<KindOfProductModel> kindOfProducts = kindOfProductDAO.findAll();
        if (kindOfProducts.isEmpty()) throw new NotFoundException("Not Found Any Kind Of Product");
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("Ok").build();
        Data data = new Data.Builder(null).withResults(kindOfProducts).build();
        return new Message.Builder(meta).withData(data).build();
//        try {
//            List<KindOfProductModel> kindOfProducts = iKindOfProductRepository.getAllKinds();
//        } catch (NotFoundException e) {
//            Meta meta = new Meta.Builder(500).withError(e.getMessage()).build();
//            return new Message.Builder(meta).build();
//        }
    }
}
