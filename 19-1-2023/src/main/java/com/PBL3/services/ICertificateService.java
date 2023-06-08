package com.PBL3.services;

import com.PBL3.dtos.CertificateDTO;
import com.PBL3.dtos.pagination.CertificatePaginationDTO;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.InvalidPropertiesException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.exceptions.dbExceptions.UpdateFailedException;
import com.PBL3.utils.response.Message;

public interface ICertificateService {
    Message createCertificate(CertificateDTO dto, String id) throws CreateFailedException;

    Message getAllCertificate(CertificatePaginationDTO dto);

    Message deleteCertificate(String id, String userId) throws InvalidPropertiesException, UpdateFailedException;

    Message updateCertificate(CertificateDTO dto, String id) throws NotFoundException, UpdateFailedException;

    Message getAll();

}
