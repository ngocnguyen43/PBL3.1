package com.PBL3.services;

import com.PBL3.dtos.KindOfProductDTO;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.response.Message;

public interface IKindOfProductService {

    Message createNewKind(KindOfProductDTO dto) throws CreateFailedException;

    Message getAllKinds() throws NotFoundException;
}
