package com.PBL3.services.impl;

import com.PBL3.dtos.CertificateDTO;
import com.PBL3.models.Certificate;
import com.PBL3.repositories.ICeritificateRepository;
import com.PBL3.services.ICertificateService;
import com.PBL3.utils.enums.ErrorStatusCodes;
import com.PBL3.utils.exceptions.authExceptions.InvalidCredentialsException;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Data;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CertificateService implements ICertificateService {
    @Inject
    private ICeritificateRepository certificateRepository;

    public Message createCertificate(CertificateDTO dto) {
        try {
            Certificate domain = Helper.objectMapper(dto, Certificate.class);
            String id = IDGeneration.generate();
            domain.setId(id);
            certificateRepository.createCertificate(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("Created Certificate Successfully").build();
            return new Message.Builder(meta).build();

        } catch (CreateFailedException e) {
            Meta meta = new Meta.Builder(e.getStatusCode()).withErrCode(e.getErrorCode()).withError(e.getMessage()).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            Meta meta = new Meta.Builder(500).withError(e.getMessage()).build();
            return new Message.Builder(meta).build();
        }
    }

    @Override
    public Message getAllCertificate() {
        try {
            List<Certificate> certificateList = certificateRepository.findAll();
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("Success").build();
            Data data = new Data.Builder(null).withResults(certificateList).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (NotFoundException e) {
            Meta meta = new Meta.Builder(HttpServletResponse.SC_NOT_FOUND).withMessage(e.getMessage()).build();

            return new Message.Builder(meta).build();
        }
    }

    @Override
    public Message deleteCertificate(String id) {
        try {
            certificateRepository.deleteCertificate(id);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_ACCEPTED).withMessage("Delete Success").build();
            return new Message.Builder(meta).build();
        }catch (InvalidCredentialsException e){
            Meta meta = new Meta.Builder( ErrorStatusCodes.InvalidCredentialsException.getValue()).withMessage(e.getMessage()).build();

            return new Message.Builder(meta).build();
        }
    }

    @Override
    public Message updateCertificate(CertificateDTO dto) {
        Certificate domain = Helper.objectMapper(dto,Certificate.class);
        System.out.print(domain.getId());
        try{
            certificateRepository.findOne(domain.getId());
            certificateRepository.updateCertificate(domain);
            Meta meta = new Meta.Builder(201).withMessage("Update Success!").build();
            return new Message.Builder(meta).build();
        }catch (NotFoundException | InvalidCredentialsException e){
            Meta meta = new Meta.Builder( ErrorStatusCodes.InvalidCredentialsException.getValue()).withMessage(e.getMessage()).build();

            return new Message.Builder(meta).build();
        }
    }

}
