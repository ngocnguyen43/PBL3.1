package com.PBL3.services;

import com.PBL3.dtos.ProductCertificateDTO;
import com.PBL3.utils.response.Message;

public interface IProductCertificateServie {
    Message createOne(ProductCertificateDTO dto);
    Message deleteOne(String id);
}
