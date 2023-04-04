package com.PBL3.services.impl;

import com.PBL3.daos.ICertificateDAO;
import com.PBL3.dtos.CertificateDTO;
import com.PBL3.models.Certificate;
import com.PBL3.services.ICertificateService;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.InvalidPropertiesException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.exceptions.dbExceptions.UpdateFailedException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Data;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;
import com.PBL3.utils.response.Response;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CertificateService implements ICertificateService {
    @Inject
    private ICertificateDAO certificateDAO;

    public Message createCertificate(CertificateDTO dto) throws CreateFailedException {
        try {
            Certificate domain = Helper.objectMapper(dto, Certificate.class);
            String id = IDGeneration.generate();
            domain.setId(id);
            certificateDAO.save(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(Response.CREATED).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new CreateFailedException(Response.CREATE_FAILED);
        }
    }

    @Override
    public Message getAllCertificate() {
        List<Certificate> certificateList = certificateDAO.findAll();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(certificateList).build();
        return new Message.Builder(meta).withData(data).build();
    }

    @Override
    public Message deleteCertificate(String id) throws InvalidPropertiesException, UpdateFailedException {
        if (id == null) throw new InvalidPropertiesException(Response.CERTIFICATE_ID_INVALID);
        try {
            certificateDAO.delete(id);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_NO_CONTENT).withMessage(Response.SUCCESS).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new UpdateFailedException(Response.FAILED);
        }
    }

    @Override
    public Message updateCertificate(CertificateDTO dto) throws NotFoundException, UpdateFailedException {
        Certificate domain = Helper.objectMapper(dto, Certificate.class);
        System.out.print(domain.getId());
        boolean isExist = certificateDAO.findOne(domain.getId()) == null;
        if (isExist) throw new NotFoundException(Response.NOT_FOUND);
        try {
            certificateDAO.updateCertificateDao(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_NO_CONTENT).withMessage(Response.SUCCESS).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new UpdateFailedException(Response.FAILED);
        }
    }

}
