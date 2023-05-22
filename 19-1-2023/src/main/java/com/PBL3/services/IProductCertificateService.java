package com.PBL3.services;

import com.PBL3.dtos.ProductCertificateDTO;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.UpdateFailedException;
import com.PBL3.utils.response.Message;

public interface IProductCertificateService {
    Message createOne(ProductCertificateDTO dto) throws DuplicateEntryException, CreateFailedException;

    Message deleteOne(ProductCertificateDTO dto) throws UpdateFailedException;
}
