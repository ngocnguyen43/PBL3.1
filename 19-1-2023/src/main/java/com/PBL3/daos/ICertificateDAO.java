package com.PBL3.daos;

import com.PBL3.models.Certificate;
import com.PBL3.models.pagination.CertificatePaginationModel;

import java.util.List;

public interface ICertificateDAO extends GenericDAO<Certificate> {
    String save(Certificate domain);

    List<Certificate> findAll(CertificatePaginationModel domain);

    void delete(String id);


    void updateCertificateDao(Certificate domain);

    Certificate findOne(String id);

    Integer coutAllCertificates();

    List<Certificate> findAll();

}
