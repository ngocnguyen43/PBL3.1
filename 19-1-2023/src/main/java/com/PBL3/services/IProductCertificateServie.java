package com.PBL3.services;

import com.PBL3.dtos.ProductCertificateDTO;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.UnexpectedException;
import com.PBL3.utils.response.Message;

public interface IProductCertificateServie {
    Message createOne(ProductCertificateDTO dto) throws DuplicateEntryException, CreateFailedException;

    Message deleteOne(String id) throws UnexpectedException;
}
