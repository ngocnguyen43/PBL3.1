package com.PBL3.services;

import com.PBL3.dtos.CertificateDTO;
import com.PBL3.utils.response.Message;

public interface ICertificateService {
    Message createCertificate(CertificateDTO dto);
    Message getAllCertificate();

    Message deleteCertificate(String id);

    Message updateCertificate(CertificateDTO dto);

}
