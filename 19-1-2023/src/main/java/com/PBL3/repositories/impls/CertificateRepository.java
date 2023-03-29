package com.PBL3.repositories.impls;

import com.PBL3.daos.ICertificateDAO;
import com.PBL3.models.Certificate;
import com.PBL3.repositories.ICeritificateRepository;
import com.PBL3.utils.exceptions.authExceptions.InvalidCredentialsException;
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
    public List<Certificate> findAll() throws NotFoundException {
        List<Certificate> certificates = certificateDAO.findAll();
        if (certificates == null) throw new NotFoundException("Not Founds Certificates");
        return certificates;
    }

    @Override
    public void deleteCertificate(String id) throws InvalidCredentialsException {
        if (id == null) throw new InvalidCredentialsException("Id is invalid");
        certificateDAO.delete(id);
    }

    @Override
    public void updateCertificate(Certificate domain) throws InvalidCredentialsException {
        try {
            Certificate old = this.findOne(domain.getId());
            if (old == null) throw new NotFoundException("Certificate not found");
            Certificate newDomain = new Certificate();
            newDomain.setName(domain.getName() == null ? old.getName() : domain.getName());
            newDomain.setDescription(domain.getDescription() == null ? old.getDescription() : domain.getDescription());
            newDomain.setPath(domain.getPath() == null ? old.getPath() : domain.getPath());
            newDomain.setAction(domain.getAction() == null ? old.getAction() : domain.getAction());
            newDomain.setModifiedBy(domain.getModifiedBy() == null ? old.getModifiedBy() : domain.getModifiedBy());
            certificateDAO.updateCertificateDao(newDomain);

        } catch (InvalidCredentialsException e) {
            throw new InvalidCredentialsException("Certificate Id is invalid!");
        }
    }

    @Override
    public Certificate findOne(String id) throws InvalidCredentialsException, NotFoundException {
        if (id == null) throw new InvalidCredentialsException("Id is invalid");
        Certificate certificate = certificateDAO.findOne(id);
        if (certificate == null) throw new NotFoundException("Certificate Not Found");
        return certificate;
    }
}
