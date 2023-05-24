package com.PBL3.services.impl;

import com.PBL3.daos.ICertificateDAO;
import com.PBL3.daos.IUserDAO;
import com.PBL3.dtos.CertificateDTO;
import com.PBL3.dtos.pagination.CertificatePaginationDTO;
import com.PBL3.models.Certificate;
import com.PBL3.models.Notification;
import com.PBL3.models.pagination.CertificatePaginationModel;
import com.PBL3.services.ICertificateService;
import com.PBL3.services.INotificationService;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.InvalidPropertiesException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.exceptions.dbExceptions.UpdateFailedException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

import static com.PBL3.utils.Constants.Pagination.PER_PAGE;

public class CertificateService implements ICertificateService {
    @Inject
    private ICertificateDAO certificateDAO;
    @Inject
    private IUserDAO userDAO;
    @Inject
    private INotificationService iNotificationService;

    public Message createCertificate(CertificateDTO dto, String userId) throws CreateFailedException {
        String creator = userDAO.getUserName(userId);
        Notification notification = new Notification
                .Builder(IDGeneration.generate())
                .withCreator(userId)
                .withMods(Collections.singletonList("all"))
                .withAdmin(true)
                .withMessage("A new certificate has been created by " + creator)
                .build();
        try {
            Certificate domain = Helper.objectMapper(dto, Certificate.class);
            String id = IDGeneration.generate();
            domain.setId(id);
            certificateDAO.save(domain);
            iNotificationService.create(notification);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(Response.CREATED).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new CreateFailedException(Response.CREATE_FAILED);
        }
    }

    @Override
    public Message getAllCertificate(CertificatePaginationDTO dto) {
        CertificatePaginationModel domain = Helper.objectMapper(dto, CertificatePaginationModel.class);
        List<Certificate> certificateList = certificateDAO.findAll(domain);
        Integer pages = certificateDAO.coutAllCertificates();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(certificateList).build();
        Pagination pagination = new Pagination.Builder().
                withCurrentPage(domain.getPage()).
                withTotalPages((int) Math.ceil((double) pages / PER_PAGE)).
                withTotalResults(pages).build();
        return new Message.Builder(meta).withData(data).withPagination(pagination).build();
    }

    @Override
    public Message deleteCertificate(String id, String userId) throws InvalidPropertiesException, UpdateFailedException {
        if (id == null) throw new InvalidPropertiesException(Response.CERTIFICATE_ID_INVALID);
        Certificate existedCertificate = certificateDAO.findOne(id);
        String creator = userDAO.getUserName(userId);
        Notification notification = new Notification
                .Builder(IDGeneration.generate())
                .withCreator(userId)
                .withMods(Collections.singletonList("all"))
                .withAdmin(true)
                .withMessage(existedCertificate.getName() + " certificate has been deleted by " + creator)
                .build();
        try {
            certificateDAO.delete(id);
            iNotificationService.create(notification);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_NO_CONTENT).withMessage(Response.SUCCESS).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new UpdateFailedException(Response.FAILED);
        }
    }

    @Override
    public Message updateCertificate(CertificateDTO dto, String userId) throws NotFoundException, UpdateFailedException {

        Certificate domain = Helper.objectMapper(dto, Certificate.class);
        System.out.print(domain.getId());
        Certificate existedCertificate = certificateDAO.findOne(domain.getId());
        if (existedCertificate == null) throw new NotFoundException("Certificate Not Found");
        String creator = userDAO.getUserName(userId);
        Notification notification = new Notification
                .Builder(IDGeneration.generate())
                .withCreator(userId)
                .withMods(Collections.singletonList("all"))
                .withAdmin(true)
                .withMessage(existedCertificate.getName() + " certificate has been updated by " + creator)
                .build();
        //if (existedCertificate == null) throw new NotFoundException(Response.NOT_FOUND);
        if (domain.getName() == null) domain.setName(existedCertificate.getName());
        if (domain.getDescription() == null) domain.setDescription(existedCertificate.getDescription());
        if (domain.getPath() == null) domain.setPath(existedCertificate.getPath());
        if (domain.getModifiedBy() == null) domain.setModifiedBy(existedCertificate.getModifiedBy());
        try {
            certificateDAO.updateCertificateDao(domain);
            iNotificationService.create(notification);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_NO_CONTENT).withMessage(Response.SUCCESS).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new UpdateFailedException(Response.FAILED);
        }
    }

    @Override
    public Message getAll() {
        List<Certificate> certificateList = certificateDAO.findAll();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(certificateList).build();
        return new Message.Builder(meta).withData(data).build();
    }

}
