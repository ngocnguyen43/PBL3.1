package com.PBL3.services.impl;

import com.PBL3.daos.IKindOfProductDAO;
import com.PBL3.dtos.KindOfProductDTO;
import com.PBL3.models.KindOfProductModel;
import com.PBL3.services.IKindOfProductService;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Data;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;
import com.PBL3.utils.response.Response;

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
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(Response.CREATED).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new CreateFailedException(Response.FAILED);
        }
    }

    @Override
    public Message getAllKinds() {
        List<KindOfProductModel> kindOfProducts = kindOfProductDAO.findAll();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(kindOfProducts).build();
        return new Message.Builder(meta).withData(data).build();

    }
}
