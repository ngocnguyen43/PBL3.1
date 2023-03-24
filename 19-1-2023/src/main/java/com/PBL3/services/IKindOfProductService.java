package com.PBL3.services;

import com.PBL3.dtos.KindOfProductDTO;
import com.PBL3.utils.response.Message;

public interface IKindOfProductService {

    Message createNewKind(KindOfProductDTO dto);

    Message getAllKinds();
}
