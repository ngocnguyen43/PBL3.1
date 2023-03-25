package com.PBL3.repositories;

import com.PBL3.models.ProductCertificatesModel;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.UpdateFailedException;
import javassist.bytecode.DuplicateMemberException;

public interface IProductCertificateRepository {
    void createOne(ProductCertificatesModel domain) throws  CreateFailedException, DuplicateEntryException;
    void deleteOne(String id) throws UpdateFailedException;
}
