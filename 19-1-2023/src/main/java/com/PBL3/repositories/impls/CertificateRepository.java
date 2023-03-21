package com.PBL3.repositories.impls;

import com.PBL3.daos.ICertificateDAO;
import com.PBL3.models.Certificate;
import com.PBL3.repositories.ICeritificateRepository;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.List;

public class CertificateRepository implements ICeritificateRepository {

    @Inject
    private ICertificateDAO certificateDAO;

    @Override
    public void createCertificate(Certificate domain) throws CreateFailedException {
        try {
            certificateDAO.save(domain);
        } catch (Exception e) {
            throw new CreateFailedException("Create Certificate Failed");
        }
    }

    @Override
    public List<Certificate> findAll() throws  NotFoundException{
       List<Certificate> certificates = certificateDAO.findAll();
       if (certificates == null) throw new NotFoundException("Not Founds Certificates");
       return certificates;
    }
}
